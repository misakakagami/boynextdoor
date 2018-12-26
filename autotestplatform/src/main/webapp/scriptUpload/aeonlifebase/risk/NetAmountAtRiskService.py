# -*- coding: utf-8 -*-
"""
-------------------------------------------------
   File Name：     NetAmountAtRiskService.py 
   Description :  保额保费计算类
   Author :        自动化测试组
   date：          2018/03/09
   Copyright:      (c)  自动化测试组 2018
   UpdateTime  : 2018/04/02
-------------------------------------------------
   Change Activity:
       getnetAmnt方法注释else代码块    2018/04/02
"""
import os
import json
import time
from decimal import *
from aeonlifebase.CaseBase import CaseBase
from aeonlifebase.risk.Risksql import Risksql
from aeonlifebase.risk.CalculateService import CalculateService
from aeonlifebase.risk.CaculateOperator import CaculateOperator


class NetAmountAtRiskService:
    
    def getnetAmnt(riskCommonDto):
        base = riskCommonDto['base']
        insuredInfo = riskCommonDto['insuredInfo']
        riskInfoList = insuredInfo['riskInfoList']
        netAmountInfoList =[]
        now = None
        if CaseBase.isMatch(base.get('cvaliDate', ""),""):
            now = time.strftime("%Y-%m-%d", time.localtime())
        else:
            now = riskCommonDto['base']['cvaliDate']   
        age = CalculateService.getAgeByBirthday(insuredInfo['birthday'], now)
        for riskInfoDto in riskInfoList:
            netAmntAtRiskDto = {}
            riskCode = riskInfoDto['riskCode']
            netAmntAtRiskDto['age'] = str(age)
            netAmntAtRiskDto['amnt'] = riskInfoDto['amnt']
            netAmntAtRiskDto['prem'] = riskInfoDto['prem']
            netAmntAtRiskDto['payEndYear'] = riskInfoDto['payEndYear']
            
            riskCalNetAmnt = NetAmountAtRiskService.getRiskCalNetAmnt(riskCode)
            netAmountInfoDto ={}
            for key, value in riskCalNetAmnt.items():
                if CaseBase.isMatch(key, '10'):
                    lRiskAmount = CaculateOperator.caculate(value, netAmntAtRiskDto)
#                     lRiskAmount = Decimal(lRiskAmountOld).quantize(Decimal('0'))
                    netAmountInfoDto['lRiskAmount'] = str(lRiskAmount)
                    netAmountInfoDto['lRiskAmountCalType']='10'
                elif CaseBase.isMatch(key, '11'):
                    dRiskAmount = CaculateOperator.caculate(value, netAmntAtRiskDto)
#                     dRiskAmountOld = Decimal(eval(value))
#                     dRiskAmount = Decimal(dRiskAmountOld).quantize(Decimal('0'))
                    netAmountInfoDto['dRiskAmount'] = str(dRiskAmount)
                    netAmountInfoDto['dRiskAmountCalType'] = '11'
                elif CaseBase.isMatch(key, '12'):
                    aRiskAmount = CaculateOperator.caculate(value, netAmntAtRiskDto)
#                     aRiskAmountOld = Decimal(eval(value))
#                     aRiskAmount = Decimal(aRiskAmountOld).quantize(Decimal('0'))
                    netAmountInfoDto['aRiskAmount'] = str(aRiskAmount)
                    netAmountInfoDto['aRiskAmountCalType'] = '12'
                elif CaseBase.isMatch(key, '13'):
                    baRiskAmount = CaculateOperator.caculate(value, netAmntAtRiskDto)
#                     baRiskAmountOld = Decimal(eval(value))
#                     baRiskAmount = Decimal(baRiskAmountOld).quantize(Decimal('0'))
                    netAmountInfoDto['baRiskAmount'] = str(baRiskAmount)
                    netAmountInfoDto['baRiskAmountCalType'] = '13' 
#                 else:
# #                     人身险风险保额
#                     laRiskAmount = CaculateOperator.caculate(value, netAmntAtRiskDto)
# #                     laRiskAmountOld = Decimal(eval(value))
# #                     laRiskAmount = Decimal(laRiskAmountOld).quantize(Decimal('0'))
#                     netAmountInfoDto['laRiskAmount'] = laRiskAmount
#                     netAmountInfoDto['laRiskAmount'] = str(laRiskAmount)
#                     netAmountInfoDto['laRiskAmountCalType'] = '16'
            netAmountInfoDto['riskCode']= riskCode
            netAmountInfoList.append(netAmountInfoDto)
        return netAmountInfoList


    def getReinsuranceRisk(riskCommonDto):
        insuredInfo = riskCommonDto['insuredInfo']
        riskInfoList = insuredInfo['riskInfoList']
        netAmountInfoList = []
        for riskInfoDto in riskInfoList:
            netAmntAtRiskDto = {}
            riskCode = riskInfoDto['riskCode']
            netAmntAtRiskDto['amnt'] = riskInfoDto['amnt']
            riskCalNetAmnt = NetAmountAtRiskService.getRiskCalNetAmnt(riskCode)
            netAmountInfoDto ={}
            if len(riskCalNetAmnt) != 0:
                for key, value in riskCalNetAmnt.items():
                    if CaseBase.isMatch(key, '1'):
                        netAmountInfoDto['lRiskAmount'] = str(CaculateOperator.caculate(value, netAmntAtRiskDto))
                        netAmountInfoDto['lRiskAmountCalType'] = '1'
                    elif CaseBase.isMatch(key, '3'):
                        netAmountInfoDto['dRiskAmount'] = str(CaculateOperator.caculate(value, netAmntAtRiskDto))
                        netAmountInfoDto['dRiskAmountCalType'] = '3'
                    elif CaseBase.isMatch(key, '4'):
                        netAmountInfoDto['dRiskAmount'] = str(CaculateOperator.caculate(value, netAmntAtRiskDto))
                        netAmountInfoDto['dRiskAmountCalType'] = '4'
                    elif CaseBase.isMatch(key, '7'):
                        netAmountInfoDto['aRiskAmount'] = str(CaculateOperator.caculate(value, netAmntAtRiskDto))
                        netAmountInfoDto['aRiskAmountCalType'] = '7'
            else:
                netAmountInfoDto['lRiskAmount'] = '0'
                netAmountInfoDto['dRiskAmount'] = '0'
                netAmountInfoDto['aRiskAmount'] = '0'
            netAmountInfoDto['riskCode'] = str(riskCode)
            netAmountInfoList.append(netAmountInfoDto)
        return netAmountInfoList
            


    def getRiskCalNetAmnt(riskCode):
        list = Risksql.selectExpressListByRiskcode(riskCode)
        netAmntDB = {}
        for riskCal in list:
            if not CaseBase.isMatch(riskCal.get('directExpression', None), None):
                netAmntDB[riskCal['subCalType']] = riskCal['directExpression']
        return netAmntDB    
            
            
      
      
        