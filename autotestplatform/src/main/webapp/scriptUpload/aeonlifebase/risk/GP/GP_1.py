# -*- coding: utf-8 -*-
"""
-------------------------------------------------
   File Name：     GP_1.py 
   Description :  
   Author :        李清霞
   date：          2018/02/28
   Copyright:      (c)  李清霞2018
       公式 ： round(?K?*?Get?/?VPU?*?GP?,0) 
       适用于： 1003，1026，1501，1502，1505，1506，1143，1145，5013，5016，5104，5501，5502
-------------------------------------------------
"""
import json
import logging
from decimal import *
from aeonlifebase.risk.Risksql import Risksql
from aeonlifebase.CaseBase import CaseBase
from  aeonlifebase.risk.Config import Config

class GP_1(CaseBase):
    def __init__(self):
        CaseBase.__init__(self, Config())
        
    def getPrem(k,amnt,vpu,age,sex,payEndYear,insuYear,riskCode):
        logging.info("执行公式：GP_1")
        Prem = CaseBase.roundUp(Decimal(k)*Decimal(amnt)/Decimal(vpu)*(GP_1.getGP1(riskCode,age,sex,payEndYear,insuYear)))
#         Prem2 = (Decimal(k)*Decimal(amnt)/Decimal(vpu)).quantize(0)*(GP_1.getGP1(riskCode, age, sex, payEndYear, insuYear))
        logging.info(Prem)
        return  Prem
                
    def getGP1(riskCode, age1, sex1, payEndYear1, insuYear1):
        #调用GP中sql的拼接查询
        sql1=Risksql.getGP(riskCode,age=age1,sex=sex1,job=None,payEndYear=payEndYear1,payEndYearFlag=None,
              insuYear=insuYear1,insuYearFlag=None,getYear=None,getYearFlag=None,getRate=None,
              smokeFlag=None)
        GP1_sql1 = CaseBase.executeSql(sql1)
        GP = GP1_sql1[0][0] 
        logging.info(GP)          
        return GP

# if __name__ == '__main__':
#     G = GP_1()
#     G.getGP1('1003','55','1',payEndYear='5',insuYear='65')
#     G.getPrem(k, amnt, vpu, '55', '1', '5', '65', '1003')