# -*- coding: utf-8 -*-
"""
-------------------------------------------------
   File Name：     testCalPA.py 
   Description :  
   Author :        张文博
   date：          2018/07/11
   Copyright:      (c)  张文博 2018
-------------------------------------------------
   Change Activity:
                   2018/12/25:
-------------------------------------------------
"""
import sys
sys.path.append("../")
import os
import logging
import json
from aeonlifebase.CaseBase import CaseBase
from aeonlifebase.risk.Config import Config

class testCalPA(CaseBase):
    
    def __init__(self):
#         CaseBase.__init__(self, Config())
        # 获取当前py文件绝对路径
        pathExcel = sys._getframe().f_code.co_filename
#         print(pathExcel)
        # p文件所在目录，为文件名
        p,f=os.path.split(pathExcel) 
#         print("dir is:" + p)  
#         print("file is:" + f)
        # g为文件名(无后缀)，h为文件名后缀
        g,h =  os.path.splitext(f)
#         print(g)
#         print(h)
        CaseBase.__init__(self, Config(), g)
    
    def testCalPASuccess(self, trueInput, trueOutput):
        result = CaseBase.interfaceTest('/pa/calPA', trueInput, 'POST')
#         if CaseBase.isMatch(result[1]['success'], "true"):
            
        compareResult = CaseBase.compareDict(result[1], trueOutput)
        logging.info("接口出参:" + str(result[1]))
        logging.info("预期出参:" + str(trueOutput))
        if compareResult[0] == True:
            return [True]
        return [False, "测试失败"+str(compareResult[1]), -1]
#         return [False, '保额保费接口测试失败', -1]

    def testCalPAFaild(self, falseInput, falseOutput):
        result = CaseBase.interfaceTest('/pa/calPA', falseInput, 'POST')
        compareResult = CaseBase.compareDict(result[1], falseOutput)
        logging.info("接口出参:" + str(result[1]))
        logging.info("预期出参:" + str(falseOutput))
        if compareResult[0] == True:
            return [True]
        return [False, '测试失败', -1]
 
    def startTest(self):

        pathHead = os.getcwd()
        if "\\" in pathHead:
            pathHead = pathHead[:os.getcwd().rfind("\\")]
        else:
            pathHead = pathHead[:os.getcwd().rfind("/")]
        excel = (pathHead + "/aeonlifebase/risk/calPAData.xlsx").replace("\\", "/")
        successSheet ="成功案例"
        faildSheet ="失败案例"
        successTagList = CaseBase.readInputListByColumnName(excel, successSheet, 'Tag')
        successList = CaseBase.readInputListByColumnName(excel, successSheet, '执行案例数据')
        resultList = CaseBase.readInputListByColumnName(excel, successSheet, '预期结果')
        successPointList = CaseBase.readInputListByColumnName(excel, successSheet, '测试点')
        faildTagList = CaseBase.readInputListByColumnName(excel, faildSheet, 'Tag')
        faildList = CaseBase.readInputListByColumnName(excel, faildSheet,'执行案例数据')
        faildPointList = CaseBase.readInputListByColumnName(excel, faildSheet, '测试点')
        falseresultList = CaseBase.readInputListByColumnName(excel, faildSheet, '预期结果')

         
        for index in range(len(successTagList)):
            if CaseBase.isMatch(successTagList[index], "T"):
                logging.info("测试成功案例 \n测试点:"+str(successPointList[index]))
                trueInput =eval(successList[index])
                trueOutput = eval(resultList[index])
                CaseBase.startCase("成功案例" + str(index + 2), self.testCalPASuccess, trueInput, trueOutput)
 
        for index in range(len(faildTagList)):
            if CaseBase.isMatch(faildTagList[index], "T"):
                logging.info("测试失败案例 \n测试点:"+str(faildPointList[index]))
                falseInput= eval(faildList[index])
                falseOutput= eval(falseresultList[index])
                CaseBase.startCase("失败案例" + str(index + 2), self.testCalPAFaild, falseInput, falseOutput)
         
if __name__ == '__main__':
    testCalPA().startTest()
    CaseBase.printTestResult()
