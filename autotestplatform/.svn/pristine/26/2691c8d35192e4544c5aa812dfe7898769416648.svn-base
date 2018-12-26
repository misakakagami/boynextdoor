# -*- coding: utf-8 -*-
"""
-------------------------------------------------
   File Name：     GP_5.py 
   Description :  
   Author :        李清霞
   date：          2018/03/02
   Copyright:      (c)  李清霞2018
       公式 ：round(?Prem?/?VPU?/?K?*GP,0)
      适用于：1149,1032
-------------------------------------------------
"""
import json
import logging
from decimal import *
from aeonlifebase.risk.Risksql import Risksql
from aeonlifebase.CaseBase import CaseBase
from  aeonlifebase.risk.Config import Config

class GP_5(CaseBase):
    def __init__(self):
        CaseBase.__init__(self, Config())
        
    def getAmnt(k,prem,vpu,age,sex,payEndYear,payEndYearFlag,riskCode):
        logging.info("执行公式：GP_5")
        Amnt = CaseBase.roundUp(Decimal(prem)/Decimal(vpu)/Decimal(k)*(GP_5.getGP5(riskCode,age,sex,payEndYear,payEndYearFlag)))
        logging.info(Amnt)
        return  Amnt        
                
    def getGP5(riskCode,age1,sex1,payEndYear1,payEndYearFlag1):
        #调用GP中sql的拼接查询
        sql=Risksql.getGP(riskCode, age=age1, sex=sex1, payEndYear=payEndYear1,payEndYearFlag=payEndYearFlag1)
        GP5_sql5 = CaseBase.executeSql(sql)
        GP5 = GP5_sql5[0][0] 
#         logging.info("=================")
        logging.info(GP5)          
        return GP5

# if __name__ == '__main__':
#     G = GP_5()
#     G.getGP5('1032','35','0','5','10')
#     G.getAmnt('1', '100000', 1000, '35', '0', '5','10', '1032')