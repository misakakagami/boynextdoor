# -*- coding: utf-8 -*-
"""
-------------------------------------------------
   File Name：     GP_4.py 
   Description :  
   Author :        李清霞
   date：          2018/03/02
   Copyright:      (c)  李清霞2018
       公式 ：round(?K?*?Get?/?VPU?*GP,0)
      适用于：1508，1147
-------------------------------------------------
"""
import json
import logging
from decimal import *
from aeonlifebase.risk.Risksql import Risksql
from aeonlifebase.CaseBase import CaseBase
from  aeonlifebase.risk.Config import Config

class GP_4(CaseBase):
    def __init__(self):
        CaseBase.__init__(self, Config())
        
    def getPrem(k,amnt,vpu,age,sex,payEndYear,payEndYearFlag,riskCode):
        logging.info("执行公式：GP_4")
        Prem= CaseBase.roundUp(Decimal(k)*Decimal(amnt)/Decimal(vpu)*(GP_4.getGP4(riskCode,age,sex,payEndYear,payEndYearFlag)))
        logging.info(Prem)
        return  Prem        
                
    def getGP4(riskCode,age1,sex1,payEndYear1,payEndYearFlag1):
        #调用GP中sql的拼接查询
        sql=Risksql.getGP(riskCode, age=age1, sex=sex1, payEndYear=payEndYear1,payEndYearFlag=payEndYearFlag1)
        GP4_sql4 = CaseBase.executeSql(sql)
        logging.info(GP4_sql4)
        GP4 = GP4_sql4[0][0] 
        logging.info(GP4)          
        return GP4

# if __name__ == '__main__':
#     G = GP_4()
#     G.getGP4('1508','46','1','5','Y')
#     G.getPrem('1', '100000', 1000, '46', '1', '5','Y', '1508')