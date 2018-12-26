# -*- coding: utf-8 -*-
"""
-------------------------------------------------
   File Name：     CalculateClass.py 
   Description :  保额保费计算类
   Author :        自动化测试组
   date：          2018/03/01
   Copyright:      (c)  自动化测试组 2018
   UpdateTime  : 2018/03/01
-------------------------------------------------
   Change Activity:
                   2018/03/01:张文博-创建文件、编写入口方法calculateEntrance、整合
                   2018/03/01:孔德华-编写豁免、非豁免非万能险保额保费计算方法及子方法
                   2018/03/05:单晓伟-编写各个数据库操作方法
                   2018/03/05:张悦-分析编写eval执行方法
                   2018/03/05:李清霞-编写、核对公式类
                   2018/03/06:张文博-整合代码、添加注释；修改年龄计算方法（2月29生人统一2月28增加一岁）
                   2018/03/07:张文博-编写组合险种销售校验
                   2018/03/08:孔德华-检查各个方法，修改字典、列表的取赋值错误
-------------------------------------------------
"""

import os
import json
import time
import calendar
from datetime import datetime
from copy import deepcopy
from decimal import *
from aeonlifebase.CaseBase import CaseBase
from aeonlifebase.risk.Risksql import Risksql
from aeonlifebase.risk.GP.GP_1 import GP_1
from aeonlifebase.risk.GP.GP_2 import GP_2
from aeonlifebase.risk.GP.GP_3 import GP_3
from aeonlifebase.risk.GP.GP_4 import GP_4
from aeonlifebase.risk.GP.GP_5 import GP_5
from aeonlifebase.risk.GP.GP_6 import GP_6
from aeonlifebase.risk.GP.GP_7 import GP_7
from aeonlifebase.risk.GP.GP_8 import GP_8
from aeonlifebase.risk.GP.GP_9 import GP_9
from aeonlifebase.risk.GP.GP_10 import GP_10
from aeonlifebase.risk.GP.GP_11 import GP_11
from aeonlifebase.risk.GP.GP_12 import GP_12
from aeonlifebase.risk.GP.GP_13 import GP_13
from aeonlifebase.risk.GP.GP_14 import GP_14
from aeonlifebase.risk.GP.GP_16 import GP_16

class CalculateService():
    
    __insusysCaseBase = None
    
#   保额保费计算方法
    def calculateService(trueInput, engineFlag, insusysCaseBase):
        #设置职业数据库连接
        CalculateService.__insusysCaseBase = insusysCaseBase
        #获得当前选中险种的代码
        selectRiskCode = deepcopy(trueInput['base']['selectRiskCode'])
#       获取所有险种详细信息
        riskInfoDict = Risksql.getAllRisk()
#       获取选中险种信息
        selectRiskInfo = riskInfoDict[selectRiskCode]
        exepmtFlag = selectRiskInfo[6]
        planType = selectRiskInfo[11]
#       豁免险计算方式  
        if not CaseBase.isMatch(exepmtFlag ,'EN'):
            premAmntCal = CalculateService.getExemptionCal(trueInput, riskInfoDict)
            
        elif CaseBase.isMatch(planType ,'4'):
#               万能险直接返回 客户所填的保费
            premAmntCal = trueInput
        else:
            premAmntCal = CalculateService.calculateEntrance(trueInput, riskInfoDict)
#                 返回计算完之后的险种
        returnRiskCal = premAmntCal['insuredInfo']['riskInfoList']
        sumPrem = CalculateService.getSumPrem(premAmntCal);
        returnRiskCal = premAmntCal['insuredInfo']['riskInfoList']
        for riskinfo in returnRiskCal:
            if selectRiskInfo[1] != riskinfo['riskCode']:
                risk = riskInfoDict[riskinfo['riskCode']]
                if not CaseBase.isMatch('EN', risk[6]):
    #               重新给豁免险赋值保额
                    riskinfo['amnt'] = sumPrem
    #               改变所选险种 重新计算豁免险保费
                    trueInput['base']['selectRiskCode'] = risk[1]
    #               调用保额计算保费方法
                    premAmntCal = CalculateService.getExemptionCal(trueInput, riskInfoDict)
#       再将原来真正选中的险种 赋值上去
        premAmntCal['base']['selectRiskCode'] = selectRiskCode
#·                组装正确险种信息
        riskInfoList = premAmntCal['insuredInfo']['riskInfoList']
        result = {}
        calOutResult = []
        checkOutInfo = []
        if 'ON' == engineFlag:
            ruleEngineType = 'simple'
            #规则引擎 = 
            ruleEngineResult = ''
            if '1' == ruleEngineResult['isApproved']:
                calOutResult = CalculateService.calOutResult(premAmntCal, riskInfoDict)
                result['premCalInfoListDto'] = calOutResult
            elif '0' == ruleEngineResult['isApproved']:
                checkOutInfo = CalculateService.checkOutInfo(ruleEngineResult)
                result['checkInfoListDto'] = checkOutInfo
        else:
            calOutResult = CalculateService.calOutResult(premAmntCal, riskInfoDict)
            result['premCalInfoListDto'] = calOutResult
        return result
    
#   组装返回数据（增加参数全部险种列表，以减少若干次根据险种代码查险种信息的操作）
    def calOutResult(premAmntCal, riskInfoMap):
        premCalInfoList = []
        selectRiskCode = premAmntCal['base']['selectRiskCode']
        riskInfoList = premAmntCal['insuredInfo']['riskInfoList']
        for riskInfo in riskInfoList:        
            mult  = riskInfo["mult"]
            riskByRiskCode = riskInfoMap[riskInfo['riskCode']]
            if riskInfo['riskCode'] == selectRiskCode  or 'EN' != riskByRiskCode[6]:
                premCalInfoDto = {}
                premCalInfoDto['riskCode'] = riskInfo['riskCode']
#                 amnt = CalculateService.calAmnt(selectRiskCode, mult, riskInfo)
                amnt = riskInfo['amnt']
                prem = riskInfo['prem']
                if not CaseBase.isMatch(riskInfo['prem'], None):
                    prem = str(prem)
                if not CaseBase.isMatch(riskInfo['amnt'], None):
                    amnt = str(amnt)
                premCalInfoDto['prem'] = prem
                premCalInfoDto['amnt'] = amnt
                premCalInfoList.append(premCalInfoDto)
        return premCalInfoList
    
#   校验险种信息与错误信息
    def checkOutInfo(ruleEngineResult):
        premCheckInfoDtoList = []
        verifyResult = ruleEngineResult['verifyResult']
        if verifyResult != None:
            for verify in verifyResult:
                premCheckInfoDto = {}
                premCheckInfoDto['code'] = verify['ruleName']
                premCheckInfoDto['msg'] = verify['returnInfo']
                premCheckInfoDtoList.append(premCheckInfoDto)
        return premCheckInfoDtoList

#  豁免险方法
    def getExemptionCal(riskCommonDto ,riskInfoMap):
        riskBase = riskCommonDto['base']
        riskInfoDto = deepcopy(riskCommonDto['insuredInfo']['riskInfoList'][0])
        selectRiskCode = deepcopy(riskBase['selectRiskCode'])
        paramRiskInfoList = riskCommonDto['insuredInfo']['riskInfoList']
        paramRiskCodeList =[]
#        豁免险责任
        selectRiskDutyInfo = Risksql.selectCalModeByRiskCode(selectRiskCode)
#         TODO
        sumPrem = CalculateService.getSumPrem(riskCommonDto)
        count = 0
        sumAmnt =0
        for riskDuty in selectRiskDutyInfo:

            subCalType = '14'
#             riskCalParam = CalculateService.getRiskCalParam(subCalType, selectRiskCode)
#             if selectRiskCode != None or selectRiskCode !='':
#             riskCalParam['riskCode']=selectRiskCode
#             riskCalParm['subCalType']=subCalType
#             
            selectExpressByRiskcode = Risksql.selectExpressByRiskcode(selectRiskCode, subCalType)
            vpu = selectExpressByRiskcode['vpu']
            expressionFactor = CalculateService.getExpressionFactor(selectExpressByRiskcode['expressionId'], selectRiskCode)
            factorInfoDto = CalculateService.putIntoDto(riskCommonDto, riskInfoDto, vpu, sumPrem, riskInfoMap,riskDuty)
            factorInfoDto['riskCode'] = selectRiskCode
            
            amnt =CalculateService.getCalculateResult(expressionFactor, selectRiskCode, factorInfoDto)
            sumAmnt = sumAmnt+amnt
            count = count+1
            if count == len(selectRiskDutyInfo):
                for riskInfo in paramRiskInfoList:
                    if CaseBase.isMatch(selectRiskCode, riskInfo['riskCode']):
                        riskInfo['amnt'] = str(sumPrem)
                        riskInfo['prem'] = str(amnt)
                    paramRiskCodeList.append(riskInfo['riskCode'])     
        return riskCommonDto
    
#     def getExpressByRiskcode(riskCalParam):
#         riskCode = riskCalParam['riskCode']
#         subCalType = riskCalParam['subCalType']
#         riskCal = Risksql.selectExpressByRiskcode(riskCalParam)
#         return riskCal
#     
    
#   根据险种列表查询保额
    def getSumPrem(riskCommonDto):
        paramRiskInfoList = riskCommonDto['insuredInfo']['riskInfoList']
        sumPrem = 0
        for riskInfo in paramRiskInfoList:
            payEndYear = int(riskInfo['payEndYear'])
            if payEndYear == 1000:
                sumPrem = sumPrem
            if payEndYear > 1 and payEndYear != 1000 and CaseBase.isMatch(riskInfo['payEndYearFlag'], 'Y') :
                sumPrem = (payEndYear - 1) * int(float(riskInfo['prem'])) + sumPrem
            if CaseBase.isMatch(riskInfo['payEndYearFlag'], 'A'):
                birthday = riskCommonDto['insuredInfo']['birthday']
                cvalidate = riskCommonDto['base']['cvaliDate']
                age = CalculateService.getAgeByBirthday(birthday, cvalidate)
                sumPrem = (payEndYear-age-1) * int(float(riskInfo['prem'])) + sumPrem
        return sumPrem

#   获取执行所需因子（排序后）
    def getExpressionFactor(expressionId, selectRiskCode):
        expression = Risksql.selectExpressByExpressionId(expressionId)
        expressionContent = expression['expressionContent']
        factorList = Risksql.selectFactorListByRelaExpressionId(expression)
        factorList = CalculateService.orderSort(factorList)
        result = {'expressionContent':expressionContent,'factorList':factorList}
        return result

#   排序
    def orderSort(factorList):
        factorList = sorted(factorList, key = CalculateService.reversed_cmp, reverse = False)
        return factorList

#   自定义排序规则 ，sortOrder升序排序 
    def reversed_cmp(x):
        return x['sortOrder']

#   封装 公式所需因子
    def putIntoDto(riskCommonDto, riskInfoDto, vpu, sumPrem, riskInfoMap, riskDuty):
        
        riskOccupationType = riskDuty['riskOccupationType']
        riskOccupationType = riskInfoMap[riskCommonDto['base']['selectRiskCode']][19]
        factorInfoDto = {}
        sex = riskCommonDto['insuredInfo'].get('sex', None)
        age = 0
        if 'EN' == riskInfoMap[riskCommonDto['base']['selectRiskCode']][6]:
            age = CalculateService.getAgeByBirthday(riskCommonDto['insuredInfo']['birthday'], riskCommonDto['base']['cvaliDate'])
        else:
            riskRenewParam = Risksql.selectRiskMaxAppAge(riskCommonDto['base']['selectRiskCode'])
            if '03' == riskRenewParam[0]['riskRole']:
                age = CalculateService.getAgeByBirthday(riskCommonDto['insuredInfo']['birthday'], riskCommonDto['base']['cvaliDate'])
            else:
                age = CalculateService.getAgeByBirthday(riskCommonDto['appntInfo']['birthday'], riskCommonDto['base']['cvaliDate'])
        smokeflag = deepcopy(riskCommonDto['insuredInfo']['ifSmoke'])
        occupation = Risksql.getOccupationByCode(riskCommonDto['insuredInfo']['occupationCode'], CalculateService.__insusysCaseBase)
        job = ''
        if riskOccupationType != None and riskOccupationType != '':
            if riskOccupationType == '4':
                job = deepcopy(occupation['hospitalRisk'])
            else:
                job = deepcopy(occupation['occupationType'])
        if age >= 0:
            factorInfoDto['age'] = str(age)
        if sex != None:
            factorInfoDto['sex'] = sex
        if job != None:
            factorInfoDto['job'] = job
        if vpu != None:
            factorInfoDto['vpu'] = str(vpu)
        if riskInfoDto.get('prem', None) != None:
            factorInfoDto['prem'] = riskInfoDto['prem']
        if riskInfoDto.get('mult', None) != None:
            factorInfoDto['mult'] = riskInfoDto['mult']
        if riskInfoDto.get('amnt', None) != None:
            factorInfoDto['amnt'] = riskInfoDto['amnt']
        if sumPrem != None:
#             factorInfoDto['sumPrem'] = str(sumPrem)
            factorInfoDto['amnt'] = str(sumPrem)
        if riskInfoDto.get('payEndYear', None) != None:
            factorInfoDto['payEndYear'] = riskInfoDto['payEndYear']
        if riskInfoDto.get('getRate', None) != None:
            factorInfoDto['getRate'] = riskInfoDto['getRate']
        if riskInfoDto.get('getYear', None) != None:
            factorInfoDto['getYear'] = riskInfoDto['getYear']
        if riskInfoDto.get('getYearFlag', None) != None:
            factorInfoDto['getYearFlag'] = riskInfoDto['getYearFlag']
        if riskInfoDto.get('insuYear', None) != None:
            factorInfoDto['insuYear'] = riskInfoDto['insuYear']
        if riskInfoDto.get('insuYearFlag', None) != None:
            factorInfoDto['insuYearFlag'] = riskInfoDto['insuYearFlag']
        if riskInfoDto.get('payEndYearFlag', None) != None:
            factorInfoDto['payEndYearFlag'] = riskInfoDto['payEndYearFlag']
        if smokeflag != None:
            factorInfoDto['smokeFlag'] = smokeflag
        #设置K值为1
        factorInfoDto['k'] = "1"
        return factorInfoDto

#   获取年龄，根据核心代码
    def getAgeByBirthday(birthday, cvalidate):
        bir = datetime.strptime(birthday,'%Y-%m-%d')
        cva = datetime.strptime(cvalidate,'%Y-%m-%d')
        # like java localDate
        bir_y = int(bir.strftime('%Y'))
        bir_m = int(bir.strftime('%m'))
        bir_d = int(bir.strftime('%d'))
        cva_y = int(cva.strftime('%Y'))
        cva_m = int(cva.strftime('%m'))
        cva_d = int(cva.strftime('%d'))
        totalMonths = (cva_y - bir_y) * 12 + cva_m - bir_m
        if cva_d < bir_d:
            totalMonths = totalMonths - 1
            temp, maxDay = calendar.monthrange(cva_y, cva_m)
            if cva_d == maxDay or (bir_m == 2 and cva_m == 2 and cva_d == 28 and maxDay == 29):
                totalMonths = totalMonths + 1
        return int(totalMonths / 12)

#   非豁免，非万能险执行方法
    def calculateEntrance(riskCommonDto, riskInfoMap):
        riskBase = riskCommonDto['base']
#         选中代码
        selectRiskCode = deepcopy(riskBase['selectRiskCode'])
#         获取入参所有的险种
        paramRiskInfoList = riskCommonDto['insuredInfo']['riskInfoList']
#        获取险种责任
        selectRiskDutyInfo = Risksql.selectCalModeByRiskCode(selectRiskCode)
        
        count = 0
        sumPremOrAmnt=0
        for riskDuty in selectRiskDutyInfo:
            if 'G' == riskDuty['calMode']:
#                 保额算保费
                subCalType = '14'
            elif 'P' == riskDuty['calMode']:
#                 保费算保额
                subCalType = '15'
#       selectRiskInfo[1]: rick_code
            elif '1036' == riskDuty['riskCode']:
#                 保额保费互算
                subCalType = '16'
            else:
#                 其他因素算保额
                subCalType = '14'
        
            for riskInfo in paramRiskInfoList:
                if '5022' == riskInfo['riskCode']:
                    risk5022Info = riskInfo
            for riskInfo in paramRiskInfoList:
                if selectRiskCode == riskInfo['riskCode']:
                    if '16' == subCalType:
                        if '1036' == selectRiskCode:
                            if risk5022Info !=None:
                                mainSumAnnualStandPrem = CalculateService.mainSumAnnualStandPrem(riskInfo, risk5022Info)
                                riskInfo['amnt'] = mainSumAnnualStandPrem
                    riskInfoDto = riskInfo
                    break    
            riskCalParam = {}
#             riskCalParm['riskCode'] = selectRiskCode
#             riskCalParam['subCalType'] = subCalType
            
            selectExpressByRiskcode = Risksql.selectExpressByRiskcode(selectRiskCode, subCalType)
            vpu = selectExpressByRiskcode['vpu']
            expressionFactor = CalculateService.getExpressionFactor(selectExpressByRiskcode['expressionId'], selectRiskCode)
            factorInfoDto = CalculateService.putIntoDto(riskCommonDto, riskInfoDto, vpu, None, riskInfoMap, riskDuty)
            factorInfoDto['riskCode'] = selectRiskCode
            premOrAmnt = CalculateService.getCalculateResult(expressionFactor, selectRiskCode, factorInfoDto)
#             sumPremOrAmnt = sumPremOrAmn
            sumPremOrAmnt = sumPremOrAmnt + premOrAmnt
            count = count+1
            if count == len(selectRiskDutyInfo):
                for risk in paramRiskInfoList:
                    if selectRiskCode == risk['riskCode']:
                        mult = risk['mult']
                        risk = CalculateService.calAmnt(selectRiskCode, mult, risk)
#                         risk = CalculateService.calAmnt(risk,selectExpressByRiskcode)
                        if '14' == subCalType:
                            risk['prem'] = str(sumPremOrAmnt)
                        elif '15' == subCalType:
                            risk['amnt'] = str(sumPremOrAmnt)
                        elif '16' == subCalType:
                            risk['prem'] = str(sumPremOrAmnt)
        return riskCommonDto
    
    
    #   计算5003,5004,5006,5010险种的amnt(保额保费计算单位：vpu=1按份数计算)
    def calAmnt(selectRiskCode, mult, riskInfo):
        if CaseBase.isMatch(selectRiskCode, "5003"):
            riskInfo['amnt'] =str(int(mult) * 20)
#             amnt = str(int(mult) * 20)
        elif selectRiskCode in ["5004", "5006", "5010"]:
            riskInfo['amnt'] =str(int(mult) * 5000)
#             amnt = str(int(mult) * 5000)
        else:   
            riskInfo['amnt'] =riskInfo['amnt'] 
        return riskInfo   
            
    
    def multCalAmnt(risk, selectExpressByRiskcode): 
        mult = risk['mult']
        
#    计算主险保费年交之和
    def mainSumAnnualStandPrem(risk1036Info, risk5022Info):
        if risk5022Info['payEndYear'] =='1000':
            sumAmnt = risk5022Info['prem']
        else:
            sumAmnt = CaseBase.roundUp(Decimal(risk5022Info['payEndYear']) * Decimal(risk5022Info['prem']))
        return sumAmnt
    
#   根据因子计算保额保费 
    def getCalculateResult(expressionFactor, selectRiskCode, factorInfoDto):
        #expressionFactor(expressionContent公式类名；factorList所需因子) selectRiskCode当前险种代码 factorInfoDto因子值
        paramStr = ''
        for factorObj in expressionFactor['factorList']:
            paramName = factorObj['factorGet'].split('.get')[1].split('()')[0]
            paramName = paramName[0: 1].lower() + paramName[1: len(paramName)]
            paramStr = paramStr + '"' + str(factorInfoDto[paramName]) + '",'
        paramStr = paramStr + '"' + selectRiskCode + '"'
        premOrAmnt = eval(expressionFactor['expressionContent'] + '(' + paramStr + ')')
        return premOrAmnt
    
#   组合险种销售校验 返回{'msg':'error'}为计算不正确，
    def getRiskSalMix(riskCommonDto):
        newRiskCommonDto = riskCommonDto
        newRiskInfoList = newRiskCommonDto['insuredInfo']['riskInfoList']
        notENRiskList = []
        riskList = Risksql.getAllRisk()
        amnt = ''
        prem = ''
        for newRiskInfoDto in newRiskInfoList:
            amnt = newRiskInfoDto.get('amnt',None)
            prem = newRiskInfoDto.get('prem',None)
            risk = riskList[newRiskInfoDto['riskCode']]
            if 'EN' == risk[6]:
                riskCommonDto['base']['selectRiskCode'] = newRiskInfoDto['riskCode']
                calculateEntrance = CalculateService.calculateService(riskCommonDto, 'OFF')
                premCalInfoListDto = calculateEntrance['premCalInfoListDto']
                for premCalInfoDto in premCalInfoListDto:
                    if premCalInfoDto['riskCode'] == newRiskInfoDto['riskCode']:
                        if premCalInfoDto['prem'] != prem or premCalInfoDto['amnt'] != amnt:
                            #计算不正确
                            return {'msg':'error'}
            else:
                notENRiskList.extend(newRiskInfoDto)
        if notENRiskList != None:
            for notENRisk in notENRiskList:
                riskCommonDto['base']['selectRiskCode'] = notENRisk['riskCode']
                calculateEntrance = CalculateService.calculateService(riskCommonDto, 'OFF')
                premCalInfoListDto = calculateEntrance['premCalInfoListDto']
                for premCalInfoDto in premCalInfoListDto:
                    if premCalInfoDto['riskCode'] == notENRisk['riskCode']:
                        if premCalInfoDto['prem'] != notENRisk['prem'] or premCalInfoDto['amnt'] != notENRisk['amnt']:
                            #计算不正确
                            return {'msg':'error'}
        result = {}
#         premCheckInfoDtoList = []
#         verifyResult#规则引擎
#         if verifyResult != None:
#             for verify in verifyResult:
#                 premCheckInfoDto = {}
#                 premCheckInfoDto['code'] = verify['ruleName']
#                 premCheckInfoDto['msg'] = verify['returnInfo']
#                 premCheckInfoDtoList.extend(premCheckInfoDto)
#         result['checkInfoListDto'] = premCheckInfoDtoList
        return result

# if __name__ == '__main__':
#     c=testRiskDetail()
#     
#     c.startTest()
#     CaseBase.printTestResult()
