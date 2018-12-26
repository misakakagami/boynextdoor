# -*- coding: utf-8 -*-
"""
-------------------------------------------------
   File Name：     GP_10.py 
   Description :  
   Author :        李清霞
   date：          2018/03/02
   Copyright:      (c)  李清霞2018
       公式 ：round(?K?*?Get?/?VPU?*GP,0)
      适用于：5020
-------------------------------------------------
"""
import json
import logging
from decimal import *
from aeonlifebase.risk.Risksql import Risksql
from aeonlifebase.CaseBase import CaseBase
from  aeonlifebase.risk.Config import Config

class GP_10(CaseBase):
    def __init__(self):
        CaseBase.__init__(self, Config())
        
    def getPrem(k, amnt, vpu, age, sex, payEndYear, payEndYearFlag, smokeFlag, riskCode):
        logging.info("执行公式：GP_10")
        Prem=CaseBase.roundUp(Decimal(k)*Decimal(amnt)/Decimal(vpu)*(GP_10.getGP10(riskCode,age,sex,payEndYear, payEndYearFlag, smokeFlag)))
        logging.info(Prem)
        return Prem        
                
    def getGP10(riskCode,age1,sex1,payEndYear1, payEndYearFlag1, smokeFlag1):
        #调用GP中sql的拼接查询
        sql=Risksql.getGP(riskCode, age=age1,sex=sex1,payEndYear=payEndYear1, payEndYearFlag=payEndYearFlag1, smokeFlag=smokeFlag1)
        GP10_sql10 = CaseBase.executeSql(sql)
        GP10 = GP10_sql10[0][0] 
        logging.info(GP10)          
        return GP10

# if __name__ == '__main__':
#     G = GP_10()
#     G.getGP10('5020','43','1','60','A','N')
#     G.getPrem('1', '100000', 1000, '43', '1', '60','A','N','5020')