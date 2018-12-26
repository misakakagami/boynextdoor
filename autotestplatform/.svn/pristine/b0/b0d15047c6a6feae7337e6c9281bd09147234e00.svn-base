# -*- coding: utf-8 -*-
"""
-------------------------------------------------
   File Name：     GP_3.py 
   Description :  
   Author :        李清霞
   date：          2018/03/02
   Copyright:      (c)  李清霞2018
       公式 ：round(?K?*?Get?/?VPU?*GP,0)
      适用于：1030,1504,5018
-------------------------------------------------
"""
import json
import logging
from decimal import *
from aeonlifebase.risk.Risksql import Risksql
from aeonlifebase.CaseBase import CaseBase
from  aeonlifebase.risk.Config import Config

class GP_3(CaseBase):
    def __init__(self):
        CaseBase.__init__(self, Config())
        
    def getPrem(k,amnt,vpu,age,sex,payEndYear,getYear,riskCode):
        logging.info("执行公式：GP_3")
        Prem=CaseBase.roundUp(Decimal(k)*Decimal(amnt)/Decimal(vpu)*(GP_3.getGP3(riskCode,age,sex,payEndYear,getYear)))
        logging.info(Prem)
        return  Prem        
                
    def getGP3(riskCode,age1,sex1,payEndYear1,getYear1):
        #调用GP中sql的拼接查询
        sql=Risksql.getGP(riskCode, age=age1, sex=sex1, payEndYear=payEndYear1,getYear=getYear1)
        GP3_sql3 = CaseBase.executeSql(sql)
        GP3 = GP3_sql3[0][0] 
        logging.info(GP3)          
        return GP3

# if __name__ == '__main__':
#     G = GP_3()
#     G.getGP3('1030','27','1','15','70')
#     G.getPrem('1', '100000', 1000, '27', '1', '15','70', '1030')