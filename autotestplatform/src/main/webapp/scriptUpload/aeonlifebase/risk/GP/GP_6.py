# -*- coding: utf-8 -*-
"""
-------------------------------------------------
   File Name：     GP_6.py 
   Description :  
   Author :        李清霞
   date：          2018/03/02
   Copyright:      (c)  李清霞2018
       公式 ：round(?K?*?Get?/?VPU?*GP,0)
      适用于：3001，3002，3003
-------------------------------------------------
"""
import json
import logging
from decimal import *
from aeonlifebase.risk.Risksql import Risksql
from aeonlifebase.CaseBase import CaseBase
from  aeonlifebase.risk.Config import Config

class GP_6(CaseBase):
    def __init__(self):
        CaseBase.__init__(self, Config())
        
    def getPrem(k,amnt,vpu,job,riskCode):
        logging.info("执行公式：GP_6")
        Prem=CaseBase.roundUp(Decimal(k)*Decimal(amnt)/Decimal(vpu)*(GP_6.getGP6(riskCode,job)))
        logging.info(Prem)
        return  Prem        
                
    def getGP6(riskCode,job1):
        #调用GP中sql的拼接查询
        sql=Risksql.getGP(riskCode, job=job1)
        GP6_sql6 = CaseBase.executeSql(sql)
        GP6 = GP6_sql6[0][0] 
        logging.info(GP6)          
        return GP6

# if __name__ == '__main__':
#     G = GP_6()
#     G.getGP6('3001','5')
#     G.getPrem('1', '100000', 1000, '5', '3001')