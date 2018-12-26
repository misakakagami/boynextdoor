# -*- coding: utf-8 -*-
"""
-------------------------------------------------
   File Name：     GP_16.py 
   Description :  
   Author :        张文博
   date：          2018/04/12
   Copyright:      (c)  张文博2018
       公式 ：round(?Prem?/?VPU?/?K?*GP,0)
      适用于：5022 ,1036
-------------------------------------------------
"""
import json
import logging
from decimal import *
from aeonlifebase.risk.Risksql import Risksql
from aeonlifebase.CaseBase import CaseBase
from  aeonlifebase.risk.Config import Config

class GP_16(CaseBase):
    def __init__(self):
        CaseBase.__init__(self, Config())
        
    def getPrem(k,amnt,vpu,age,sex,insuYear,insuYearFlag,payEndYear,payEndYearFlag,riskCode):
        logging.info("执行公式：GP_16")
        Prem=CaseBase.roundUp(Decimal(amnt)/Decimal(vpu)/Decimal(k)*Decimal(GP_16.getGP16(riskCode, age, sex, payEndYear,payEndYearFlag, insuYear,insuYearFlag)))
        return  Prem        
                
    def getGP16(riskCode,age1,sex1,payEndYear1,payEndYearFlag1,insuYear1,insuYearFlag1):
        #调用GP中sql的拼接查询
        sql=Risksql.getGP(riskCode, age=age1, sex=sex1,payEndYear=payEndYear1, payEndYearFlag=payEndYearFlag1 ,insuYear=insuYear1, insuYearFlag=insuYearFlag1)
        GP16_sql16 = CaseBase.executeSql(sql)
        GP16 = GP16_sql16[0][0] 
        logging.info(GP16)          
        return GP16

# if __name__ == '__main__':
#     G = GP_14()
#     G.getGP14('5011','1','0')
#     G.getPrem('1', 1000, '1', '0','100000','5011')