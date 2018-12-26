# -*- coding: utf-8 -*-
"""
-------------------------------------------------
   File Name：     CaculateOperator.py 
   Description :  java三目运算式转换python三目运算式
   Author :        孔德华
   date：          2018/03/13
   Copyright:      (c)  孔德华 2018
   UpdateTime  : 2018/03/13
-------------------------------------------------
   Change Activity:
                   2018/03/10:孔德华-分析三目运算场景，尝试使用if-elif-else解析，过于繁琐舍弃
                   2018/03/12:孔德华-重写，尝试拆分条件、正误结果后重组成python三目格式，遇到括号优先级、java的BigDecimal运算方法的问题
                   2018/03/13:孔德华-增加括号分组功能以处理优先级问题，测试通过
-------------------------------------------------

"""

from decimal import *
from aeonlifebase.CaseBase import CaseBase


class CaculateOperator():

    #根据java代码、参数字典转换并计算
    @staticmethod
    def caculate(expression, netAmntAtRiskDto):
        #替换java转型、数据类型代码为python代码
        expression = CaculateOperator.replaceParam(expression)
        #括号分组，检查是否有括号包裹的高优先级三目运算
        lc = []
        rc = []
        for charIndex in range(len(expression)):
            if expression[charIndex] == '(':
                lc.append(charIndex)
            elif expression[charIndex] == ')':
                rc.append(charIndex)
        coupleDict = dict.fromkeys(lc, None)
        for li in sorted(coupleDict.keys(), reverse = True):
            for ri in sorted(rc):
                if ri > li:
                    coupleDict[li] = ri
                    rc.remove(ri)
                    break
        for keyName in sorted(coupleDict.keys(), reverse = True):
            if expression[int(keyName): coupleDict[keyName]].find('?') != -1:
                expression = expression[0: int(keyName)] + CaculateOperator.analyzeOperator(expression[int(keyName) + 1: coupleDict[keyName] - 1]) + expression[coupleDict[keyName]: len(expression)]
        expression = CaculateOperator.analyzeOperator(expression)
        return CaseBase.roundUp(Decimal(eval(expression)))

    #解析当前最底层的三目运算式
    @staticmethod
    def analyzeOperator(expressionStr):
        #循环转换最底层的三目运算式，直到完成
        while expressionStr.find('?') != -1:
            #获得？、：索引列表，列表项为包含索引、符号的字典
            symbolList = []
            index = 0
            for charStr in expressionStr:
                if charStr == '?':
                    symbolList.append({'index': index, 'symbol': '?'})
                elif charStr == ':':
                    symbolList.append({'index': index, 'symbol': ':'})
                index += 1
            #寻找下一项和下下项都为冒号的问号，将此组三目运算转换
            index = len(symbolList) - 2
            while index >= 0:
                #如果不是问号则进行下一次循环
                if symbolList[index]['symbol'] == '?':
                    #如果最后两个符号是?:则直接转换
                    if len(symbolList) == (index + 2):
                        if symbolList[index + 1]['symbol'] == ':':
                            #如果这个问号就是第一个符号，则条件从头开始截取
                            if index == 0:
                                expressionStr = CaculateOperator.exchangeExpression(expressionStr, 0, symbolList[index]['index'], symbolList[index + 1]['index'], len(expressionStr))
                            #如果这个问号之前还有符号，则条件从上一个符号之后开始截取
                            else:
                                expressionStr = CaculateOperator.exchangeExpression(expressionStr, symbolList[index - 1]['index'] + 1, symbolList[index]['index'], symbolList[index + 1]['index'], len(expressionStr))
                                symbolList.pop(index + 1)
                                symbolList.pop(index)
                    #如果不是最后一组则判断下下项是否为冒号
                    else:
                        if symbolList[index + 1]['symbol'] == ':' and symbolList[index + 2]['symbol'] == ':':
                            #如果这个问号就是第一个符号，则条件从头开始截取
                            if index == 0:
                                expressionStr = CaculateOperator.exchangeExpression(expressionStr, 0, symbolList[index]['index'], symbolList[index + 1]['index'], symbolList[index + 2]['index'])
                            #如果这个问号之前还有符号，则条件从上一个符号之后开始截取
                            else:
                                expressionStr = CaculateOperator.exchangeExpression(expressionStr, symbolList[index - 1]['index'] + 1, symbolList[index]['index'], symbolList[index + 1]['index'], symbolList[index + 2]['index'])
                                symbolList.pop(index + 1)
                                symbolList.pop(index)
                index -= 1
        return expressionStr

    #根据问号始末、冒号、结尾索引，转换一个三目运算式
    @staticmethod
    def exchangeExpression(expressionStr, questBeginIndex, questIndex, colonIndex, endIndex):
        #拆分条件、两个公式
        check = expressionStr[questBeginIndex: questIndex]
        checkTrue = expressionStr[questIndex + 1: colonIndex]
        checkFalse = expressionStr[colonIndex + 1: endIndex]
        #转换
        expressionStr = expressionStr[0: questBeginIndex] + '(' + checkTrue + ' if ' + check + ' else ' + checkFalse + ')' + expressionStr[endIndex: len(expressionStr)]
        return expressionStr

    #替换java转型、数据类型代码为python代码
    @staticmethod
    def replaceParam(expressionStr):
        expressionStr = expressionStr.replace('new BigDecimal(netAmntAtRiskDto.getAge())','Decimal(netAmntAtRiskDto["age"])')
        expressionStr = expressionStr.replace('new BigDecimal(netAmntAtRiskDto.getAmnt())','Decimal(netAmntAtRiskDto["amnt"])')
        expressionStr = expressionStr.replace('new BigDecimal(netAmntAtRiskDto.getPrem())','Decimal(netAmntAtRiskDto["prem"])')
        expressionStr = expressionStr.replace('new BigDecimal(netAmntAtRiskDto.getInsuYear())','Decimal(netAmntAtRiskDto["insuyear"])')
        expressionStr = expressionStr.replace('new BigDecimal(netAmntAtRiskDto.getPayEndYear())','Decimal(netAmntAtRiskDto["payEndYear"])')
        expressionStr = expressionStr.replace('netAmntAtRiskDto.getAge()','netAmntAtRiskDto["age"]')
        expressionStr = expressionStr.replace('netAmntAtRiskDto.getAmnt()','netAmntAtRiskDto["amnt"]')
        expressionStr = expressionStr.replace('netAmntAtRiskDto.getPrem()','netAmntAtRiskDto["prem"]')
        expressionStr = expressionStr.replace('netAmntAtRiskDto.getInsuYear()','netAmntAtRiskDto["insuyear"]')
        expressionStr = expressionStr.replace('netAmntAtRiskDto.getPayEndYear()','netAmntAtRiskDto["payEndYear"]')
        expressionStr = expressionStr.replace('new BigDecimal','Decimal')
        expressionStr = expressionStr.replace('Integer.parseInt','int')
        expressionStr = expressionStr.replace('.add','+')
        expressionStr = expressionStr.replace('.subtract','-')
        expressionStr = expressionStr.replace('.multiply','*')
        expressionStr = expressionStr.replace('.divide','/')
        return expressionStr

# e = 'Integer.parseInt(netAmntAtRiskDto.getAge())>=18?new BigDecimal(0):new BigDecimal(0)'
# d = {'age':'20','amnt':'5000','prem':'6000','insuYear':'2000-01-01','payEndYear':'5'}
# r = CaculateOperator.caculate(e,d)
# print(r)
#{'age':'','amnt':'','prem':'','insuYear':'','payEndYear':''}