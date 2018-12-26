# -*- coding: utf-8 -*-
"""
-------------------------------------------------
   File Name：     GP_9.py 
   Description :  
   Author :        李清霞
   date：          2018/03/02
   Copyright:      (c)  李清霞2018
       公式 ：round(?K? *( 1/ ?VPU?+(?Mult?-1) / ?VPU? *0.5 )* GP, 0)
      适用于：5004,5006,5010
-------------------------------------------------
"""
import json
import logging
from decimal import *
from aeonlifebase.risk.Risksql import Risksql
from aeonlifebase.CaseBase import CaseBase
from  aeonlifebase.risk.Config import Config

class GP_9(CaseBase):
    def __init__(self):
        CaseBase.__init__(self, Config())
        
    def getPrem(k,mult,vpu,age,getRate,riskCode):
        logging.info("执行公式：GP_9")
        Prem = CaseBase.roundUp(Decimal(k)*(1/Decimal(vpu)+(Decimal(mult)-1)/Decimal(vpu)*Decimal(0.5))*(GP_9.getGP9(riskCode,age,getRate)))
        logging.info(Prem)
        return  Prem        
                
    def getGP9(riskCode,age1,getRate1):
        #调用GP中sql的拼接查询
        sql=Risksql.getGP(riskCode, age=age1,getRate=getRate1)
        GP9_sql9 = CaseBase.executeSql(sql)
        GP9 = GP9_sql9[0][0] 
        logging.info(GP9)          
        return GP9

# if __name__ == '__main__':
#     G = GP_9()
#     G.getGP9('5010','28','1')
#     G.getPrem('1', '3', 1, '28', '1','5010')