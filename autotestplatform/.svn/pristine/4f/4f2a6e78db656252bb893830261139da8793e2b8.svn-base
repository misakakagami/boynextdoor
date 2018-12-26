# -*- coding: utf-8 -*-
"""
-------------------------------------------------
   File Name：     GP_11.py 
   Description :  
   Author :        李清霞
   date：          2018/03/02
   Copyright:      (c)  李清霞2018
       公式 ：round(?Prem?/?VPU?/?K?*GP,0))
      适用于：1033
-------------------------------------------------
"""
import json
import logging
from decimal import *
from aeonlifebase.risk.Risksql import Risksql
from aeonlifebase.CaseBase import CaseBase
from  aeonlifebase.risk.Config import Config

class GP_11(CaseBase):
    def __init__(self):
        CaseBase.__init__(self, Config())
        
    def getAmnt(k, prem, vpu, age, sex, insuYear,insuYearFlag, payEndYear, payEndYearFlag, riskCode):
        logging.info("执行公式：GP_11")
        Amnt=CaseBase.roundUp(Decimal(prem)/Decimal(vpu)/Decimal(k)*(GP_11.getGP11(riskCode, age,sex, insuYear,insuYearFlag, payEndYear, payEndYearFlag)))
        logging.info(Amnt)
        return Amnt        
                
    def getGP11(riskCode,age1,sex1,insuYear1,insuYearFlag1, payEndYear1, payEndYearFlag1):
        #调用GP中sql的拼接查询
        sql=Risksql.getGP(riskCode, age=age1,sex=sex1,insuYear=insuYear1,insuYearFlag=insuYearFlag1, payEndYear=payEndYear1, payEndYearFlag=payEndYearFlag1)
        GP11_sql11 = CaseBase.executeSql(sql)
        GP11 = GP11_sql11[0][0] 
        logging.info(GP11)          
        return GP11

# if __name__ == '__main__':
#     G = GP_11()
#     G.getGP11('1033','46','1','15','Y','5','Y')
#     G.getAmnt('1', '100000', 1000,'46','1','15','Y','5','Y','1033')