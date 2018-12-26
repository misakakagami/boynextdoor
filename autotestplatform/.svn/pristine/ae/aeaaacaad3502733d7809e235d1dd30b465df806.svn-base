# -*- coding: utf-8 -*-
"""
-------------------------------------------------
   File Name：     GP_8.py 
   Description :  
   Author :        李清霞
   date：          2018/03/02
   Copyright:      (c)  李清霞2018
       公式 ：round(?K?*?Mult?/?VPU?*GP,0)
      适用于：5003
-------------------------------------------------
"""
import json
import logging
from decimal import *
from aeonlifebase.risk.Risksql import Risksql
from aeonlifebase.CaseBase import CaseBase
from  aeonlifebase.risk.Config import Config

class GP_8(CaseBase):
    def __init__(self):
        CaseBase.__init__(self, Config())
        
    def getPrem(k,mult,vpu,age,sex,riskCode):
        logging.info("执行公式：GP_8")
        Prem=CaseBase.roundUp(Decimal(k)*Decimal(mult)/Decimal(vpu)*(GP_8.getGP8(riskCode,age,sex)))
        logging.info(Prem)
        return  Prem        
                
    def getGP8(riskCode,age1,sex1):
        #调用GP中sql的拼接查询
        sql=Risksql.getGP(riskCode, age=age1,sex=sex1)
        GP8_sql8 = CaseBase.executeSql(sql)
        GP8 = GP8_sql8[0][0] 
        logging.info(GP8)          
        return GP8

# if __name__ == '__main__':
#     G = GP_8()
#     G.getGP8('5003','50','1')
#     G.getPrem('1', '10', 1, '50', '1','5003')