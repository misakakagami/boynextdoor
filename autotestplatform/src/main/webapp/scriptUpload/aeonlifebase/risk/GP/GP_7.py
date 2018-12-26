# -*- coding: utf-8 -*-
"""
-------------------------------------------------
   File Name：     GP_7.py 
   Description :  
   Author :        李清霞
   date：          2018/03/02
   Copyright:      (c)  李清霞2018
       公式 ：round(?K?*?Get?/?VPU?*GP,0)
      适用于：3005
-------------------------------------------------
"""
import json
import logging
from decimal import *
from aeonlifebase.risk.Risksql import Risksql
from aeonlifebase.CaseBase import CaseBase
from  aeonlifebase.risk.Config import Config

class GP_7(CaseBase):
    def __init__(self):
        CaseBase.__init__(self, Config())
        
    def getPrem(k,amnt,vpu,age,riskCode):
        logging.info("执行公式：GP_7")
        Prem=CaseBase.roundUp(Decimal(k)*Decimal(amnt)/Decimal(vpu)*(GP_7.getGP7(riskCode,age)))
        logging.info(Prem)
        return  Prem        
                
    def getGP7(riskCode,age1):
        #调用GP中sql的拼接查询
        sql=Risksql.getGP(riskCode, age=age1)
        GP7_sql7 = CaseBase.executeSql(sql)
        GP7 = GP7_sql7[0][0] 
        logging.info(GP7)          
        return GP7

# if __name__ == '__main__':
#     G = GP_7()
#     G.getGP7('3005','1')
#     G.getPrem('1', '100000', 1000, '1','3005')