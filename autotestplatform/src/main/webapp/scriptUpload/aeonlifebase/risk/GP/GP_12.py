# -*- coding: utf-8 -*-
"""
-------------------------------------------------
   File Name：     GP_12.py 
   Description :  
   Author :        李清霞
   date：          2018/03/02
   Copyright:      (c)  李清霞2018
       公式 ：round(?Prem?/?VPU?/?K?*GP,0)
      适用于：1151
-------------------------------------------------
"""
import json
import logging
from decimal import *
from aeonlifebase.risk.Risksql import Risksql
from aeonlifebase.CaseBase import CaseBase
from aeonlifebase.risk.Config import Config

class GP_12(CaseBase):
    def __init__(self):
        CaseBase.__init__(self, Config())
        
    def getAmnt(k,prem,vpu,age,sex,payEndYear,payEndYearFlag,getYear,getYearFlag,riskCode):
        logging.info("执行公式：GP_12")
        Amnt=CaseBase.roundUp(Decimal(prem)/Decimal(vpu)/Decimal(k)*Decimal(GP_12.getGP12(riskCode,age,sex,payEndYear,payEndYearFlag,getYear,getYearFlag)))
        logging.info(Amnt)
        return  Amnt        
                
    def getGP12(riskCode,age1,sex1,payEndYear1,payEndYearFlag1,getYear1,getYearFlag1):
        #调用GP中sql的拼接查询
        sql=Risksql.getGP(riskCode, age=age1, sex=sex1, payEndYear=payEndYear1,payEndYearFlag=payEndYearFlag1,getYear=getYear1,getYearFlag=getYearFlag1)
        GP12_sql12 = CaseBase.executeSql(sql)
        GP12 = GP12_sql12[0][0] 
        logging.info(GP12)          
        return GP12

# if __name__ == '__main__':
#     G = GP_12()
#     G.getGP12('1151','6.00','1','3.00','Y','60.00','Y')
#     G.getAmnt('1', '100000', 1000, '6.00','1','3.00','Y','60.00','Y', '1151')