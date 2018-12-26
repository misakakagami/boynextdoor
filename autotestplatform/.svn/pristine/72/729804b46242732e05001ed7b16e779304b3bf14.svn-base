# -*- coding: utf-8 -*-
"""
-------------------------------------------------
   File Name：     GP_13.py 
   Description :  
   Author :        李清霞
   date：          2018/03/02
   Copyright:      (c)  李清霞2018
       公式 ：round(? K ? *(主险、所有保险期间超过1年的其它附加险的未来应收保险费之和) / ? VPU ? * GP,0)),1)
      适用于：1007,5005
-------------------------------------------------
"""
import json
import logging
from decimal import *
from aeonlifebase.risk.Risksql import Risksql
from aeonlifebase.CaseBase import CaseBase
from  aeonlifebase.risk.Config import Config

class GP_13(CaseBase):
    def __init__(self):
        CaseBase.__init__(self, Config())
        
    def getPrem(k, vpu, age, sumPrem, riskCode):
        print(k, vpu, age, sumPrem, riskCode)
        logging.info("执行公式：GP_13")
        Prem = CaseBase.roundUp(Decimal(k)*Decimal(sumPrem)/Decimal(vpu)*Decimal(GP_13.getGP13(riskCode,age)))
        if Prem < Decimal(1):
            Prem = Decimal(1)
        logging.info(Prem)
        return  Prem        
                
    def getGP13(riskCode,age1):
        #调用GP中sql的拼接查询
        sql=Risksql.getGP(riskCode, age=age1)
        GP13_sql13 = CaseBase.executeSql(sql)
        GP13 = GP13_sql13[0][0] 
        logging.info(GP13)          
        return GP13

# if __name__ == '__main__':
#     G = GP_13()
#     G.getGP13('5005','18')
#     G.getPrem('1', 1000, '18','100000','5005')