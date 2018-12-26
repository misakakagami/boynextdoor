# -*- coding: utf-8 -*-
"""
-------------------------------------------------
   File Name：     GP_14.py 
   Description :  
   Author :        李清霞
   date：          2018/03/02
   Copyright:      (c)  李清霞2018
       公式 ：round(? K ? *(主险、所有保险期间超过1年的其它附加险的未来应收保险费之和) / ? VPU ? * GP,0)),1)
      适用于：5011
-------------------------------------------------
"""
import json
import logging
from decimal import *
from aeonlifebase.risk.Risksql import Risksql
from aeonlifebase.CaseBase import CaseBase
from  aeonlifebase.risk.Config import Config

class GP_14(CaseBase):
    def __init__(self):
        CaseBase.__init__(self, Config())
        
    def getPrem(k,vpu,age,sex,sumPrem,riskCode):
        logging.info("执行公式：GP_14")
        Prem=CaseBase.roundUp(Decimal(k)*Decimal(sumPrem)/Decimal(vpu)*Decimal(GP_14.getGP14(riskCode,age,sex)))
        if Prem < Decimal(1):
            Prem = Decimal(1)
        logging.info(Prem)
        return  Prem        
                
    def getGP14(riskCode,age1,sex1):
        #调用GP中sql的拼接查询
        sql=Risksql.getGP(riskCode, age=age1, sex=sex1)
        GP14_sql14 = CaseBase.executeSql(sql)
        GP14 = GP14_sql14[0][0] 
        logging.info(GP14)          
        return GP14

# if __name__ == '__main__':
#     G = GP_14()
#     G.getGP14('5011','1','0')
#     G.getPrem('1', 1000, '1', '0','100000','5011')