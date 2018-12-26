# -*- coding: utf-8 -*-
"""
-------------------------------------------------
   File Name：     GP_2.py 
   Description :  
   Author :        李清霞
   date：          2018/03/01
   Copyright:      (c)  李清霞2018
       公式 ：round(?K?*?Get?/?VPU?*GP,0)
      适用于：1029，1133 ，1141 ，1503 ，5001 ，5008 ，5014 ，510101
-------------------------------------------------
"""
import json
import logging
from decimal import *
from aeonlifebase.risk.Risksql import Risksql
from aeonlifebase.CaseBase import CaseBase
from  aeonlifebase.risk.Config import Config

class GP_2(CaseBase):
    def __init__(self):
        CaseBase.__init__(self, Config())
        
    def getPrem(k,amnt,vpu,age,sex,payEndYear,riskCode):
        logging.info("执行公式：GP_2")
        Prem = CaseBase.roundUp(Decimal(k)*Decimal(amnt)/Decimal(vpu)*(GP_2.getGP2(riskCode,age,sex,payEndYear)))
#         logging.info("+++++++++++++++++++")
        logging.info(Prem)
        return  Prem        
                
    def getGP2(riskCode,age1,sex1,payEndYear1):
        #调用GP中sql的拼接查询
        sql=Risksql.getGP(riskCode, age=age1, sex=sex1, payEndYear=payEndYear1)
        GP2_sql2 = CaseBase.executeSql(sql)
        GP2 = GP2_sql2[0][0] 
        logging.info(GP2)          
        return GP2

# if __name__ == '__main__':
#     G = GP_2()
#     G.getGP2('1029','49','1','10')
#     G.getPrem('1', '200000', 1000, '49', '1', '10', '1029')