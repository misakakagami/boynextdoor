# -*- coding: utf-8 -*-
"""
-------------------------------------------------
   File Name：     RisksqlBase.py 
                               产品平台调用数据库方法
   Description :  1、获取所有险种信息方法；
                  2、根据险种代码riskCode，再保风险保额类subCalType 查询riskCal信息
                  3、根据职业代码occupationCode获取职业信息
                  4.获取公式表信息
                  5.查询公式因子关联表
   Author :        单晓伟  张悦
   date：          2018/02/28
   Copyright:    (b)  单晓伟 2018
-------------------------------------------------
   Change Activity:
                   2018/03/02:
-------------------------------------------------
"""
import os
from decimal import *
from aeonlifebase.CaseBase import CaseBase
from aeonlifebase.risk.Config import Config
from aeonlifebase.risk.Config import Config
import logging
import json
from copy import deepcopy

class Risksql(CaseBase):
    def __init__(self):
        CaseBase.__init__(self, Config())
        
    def getAllRisk():
#             获取所有险种详细信息
        sql = "select t.risk_id risk_id,t.risk_code risk_code, t.risk_name risk_name,\
                      t.risk_short_name risk_short_name, t.sub_risk_flag sub_risk_flag,\
                t.risk_chnl risk_chnl,\
                t.exepmt_flag exepmt_flag,\
                t.get_flag get_flag, t.bonus_flag bonus_flag, t.risk_type risk_type,\
                t.risk_sub_type risk_sub_type, t.risk_plan_type risk_plan_type,\
                t.auto_pay_flag auto_pay_flag,\
                t.risk_warning_flag risk_warning_flag,\
                t.rnew_flag rnew_flag, t.reinsure_flag reinsure_flag,\
                t.locale locale, t.is_deleted is_deleted, t.create_user create_user, \
                t.create_date create_date,  t.update_user update_user, t.update_date update_date from risk t where \
                t.is_deleted = 'n'";
        allRisk = CaseBase.executeSql(sql)
        result = dict()
        for risk in allRisk:
            riskCode = risk[1]
            result[riskCode] = risk
#         print(result)
        return result   
        
    #获取所有险种信息 
    def getAllRiskMap():
        sql="select risk_id,risk_code,risk_name,risk_short_name,sub_risk_flag,risk_chnl,\
        exepmt_flag , get_flag, bonus_flag, risk_type, risk_sub_type, risk_plan_type,\
        auto_pay_flag, risk_warning_flag,rnew_flag, cal_mode, reinsure_flag, prem_zero_flag,\
        amnt_zero_flag, risk_occupation_type, locale, is_deleted, create_user, create_date,\
        update_user,update_date from risk  where is_deleted = 'n'"
        resultBySql = CaseBase.executeSql(sql)
        allAiskMap={}
        i=0
        for row in resultBySql:
            key= resultBySql[i][1]
            value={}
            value['riskId']=row[0]
            value['riskCode']=row[1]
            value['riskName']=row[2]
            value['riskShortName']=row[3]
            value['subRiskFlag']=row[4]
            value['riskChnl']=row[5]
            value['exepmtFlag']=row[6]
            value['getFlag']=row[7]
            value['bonusFlag']=row[8]
            value['riskType']=row[9]
            value['riskSubType']=row[10]
            value['riskPlanType']=row[11]
            value['autoPayFlag']=row[12]
            value['riskWarningFlag']=row[13]
            value['rnewFlag']=row[14]
            value['calMode']=row[15]
            value['reinsurEflag']=row[16]
            value['premZeroFlag']=row[17]
            value['amntZeroFlag']=row[18]
            value['riskOccupationType']=row[19]
            value['locale']=row[20]
            value['isDeleted']=row[21]
            value['createUser']=row[22]
            value['createDate']=row[23]
            value['updateUser']=row[24]
            value['updateDate']=row[25]
            allAiskMap[key]=value
            i+=1
#         logging.info(allAiskMap)
#         print(allAiskMap['1032'])
        return allAiskMap
    
    #根据险种代码riskCode，风险保额类型subCalType查询riskCal
    #复用，根据险种代码查险种信息     
    def selectExpressByRiskcode(riskCode,subCalType): 
        sql = "select risk_cal_id, risk_cal_code, risk_code, cal_type, sub_cal_type, expression_id,\
            direct_expression, sort_order, vpu, cal_desc, locale, is_deleted, create_user, create_date,\
            update_user,update_date from risk_cal \
            where risk_code = '"+riskCode+"' and sub_cal_type = '"+subCalType+"' and  is_deleted='n'"
        resultBySql = CaseBase.executeSql(sql)
        riskCal = {}
        riskCal['riskCalId']=resultBySql[0][0]
        riskCal['riskCalCode']=resultBySql[0][1]
        riskCal['riskCode']=resultBySql[0][2]
        riskCal['calType']=resultBySql[0][3]
        riskCal['subCalType']=resultBySql[0][4]
        riskCal['expressionId']=resultBySql[0][5]
        riskCal['directExpression']=resultBySql[0][6]
        riskCal['sortOrder']=resultBySql[0][7]
        riskCal['vpu']=resultBySql[0][8]
        riskCal['calDesc']=resultBySql[0][9]
        riskCal['locale']=resultBySql[0][10]
        riskCal['isDeleted']=resultBySql[0][11]
        riskCal['createUser']=resultBySql[0][12]
        riskCal['createDate']=resultBySql[0][13]
        riskCal['updateUser']=resultBySql[0][14]
        riskCal['updateDate']=resultBySql[0][15]
        logging.info(riskCal)
        return riskCal
    
    def selectExpressListByRiskcode(riskCode):
        sql = "select risk_cal_id, risk_cal_code, risk_code, cal_type, sub_cal_type, expression_id,\
            direct_expression, sort_order, vpu, cal_desc, locale, is_deleted, create_user, create_date,\
            update_user,update_date from risk_cal \
            where risk_code = '"+riskCode+"' and  is_deleted='n'"
        resultBySql = CaseBase.executeSql(sql)
        riskList = []
        for risk in resultBySql:
            riskDic = {}
            riskDic['riskCalId']=risk[0]
            riskDic['riskCalCode']=risk[1]
            riskDic['riskCode']=risk[2]
            riskDic['calType']=risk[3]
            riskDic['subCalType']=risk[4]
            riskDic['expressionId']=risk[5]
            riskDic['directExpression']=risk[6]
            riskDic['sortOrder']=risk[7]
            riskDic['vpu']=risk[8]
            riskDic['calDesc']=risk[9]
            riskDic['locale']=risk[10]
            riskDic['isDeleted']=risk[11]
            riskDic['createUser']=risk[12]
            riskDic['createDate']=risk[13]
            riskDic['updateUser']=risk[14]
            riskDic['updateDate']=risk[15]
            riskList.append(riskDic) 
        return riskList    
    
    
    #根据职业代码获取职业信息
    def getOccupationByCode(occupationCode, insusysCaseBase):
#         CaseBase.changeSchema('iudp_zt_insusys')
        sql = "select occupation_id,occupation_code,occupation_name,occupation_type,hospital_risk\
         from occupation where occupation_code ='%s'" %(occupationCode)
        resultBySql = insusysCaseBase.executeSql(sql)
        occupation = {}
        occupation['occupationId']=resultBySql[0][0]
        occupation['occupationCode']=resultBySql[0][1]
        occupation['occupationName']=resultBySql[0][2]
        occupation['occupationType']=resultBySql[0][3]
        occupation['hospitalRisk']=resultBySql[0][4]
#         logging.info(occupation)
#         CaseBase.changeSchema('iudp_zt_risk')
        return occupation
       
       
    # 获取公式表信息
    def selectExpressByExpressionId(expressionId):
        # 初始化SQL查询语句,根据exprssionId获取公式表信息详情
        sqlGetExpressByRiskCode = "SELECT expression_id, expression_scope, \
                    expression_content, expression_desc, locale, is_deleted, create_user, \
                    create_date, update_user, update_date FROM `expression` WHERE is_deleted='n' \
                    AND expression_id = %s"%expressionId
        # sql查询结果
        queryResult1 = CaseBase.executeSql(sqlGetExpressByRiskCode)
        expression = {}
        for i in range(len(queryResult1[0])):
            expression['expressionId'] = queryResult1[0][i]
            i += 1
            expression['expressionScope'] = queryResult1[0][i]
            i += 1
#             expression['expressionMethod'] = queryResult1[0][i]
#             i += 1
            expression['expressionContent'] = queryResult1[0][i]
            i += 1
            expression['expressionDesc'] = queryResult1[0][i]
            i += 1
            expression['locale'] = queryResult1[0][i]
            i += 1
            expression['isDeleted'] = queryResult1[0][i]
            i += 1
            expression['createUser'] = queryResult1[0][i]
            i += 1
            expression['createDate'] = queryResult1[0][i]
            i += 1
            expression['updateUser'] = queryResult1[0][i]
            i += 1
            expression['updateDate'] = queryResult1[0][i]
#             logging.info('-----------------------公式表信息expression-------------------------')
#             logging.info(expression)
            return expression
        
    
    # 查询公式因子关联表
    def selectFactorListByRelaExpressionId(expression):
        relaExpressionId = expression['expressionId']
        # 初始化SQL查询语句,根据relaExpressionId获取公式表信息详情
        sqlGetFactorListByExpressionId = "SELECT s.factor_get , t.sort_order FROM `expression_factor` t, \
                    factor s WHERE t.factor_id = s.factor_id AND t.is_deleted='n' \
                    AND t.expression_id = %s"%relaExpressionId
        # sql查询结果
        queryResult2 = CaseBase.executeSql(sqlGetFactorListByExpressionId)
        factoryList = []
        for factory in queryResult2:
            FactoryBySort = {}
            FactoryBySort['factorGet'] = factory[0]
            FactoryBySort['sortOrder'] = factory[1]
            factoryList.append(FactoryBySort)
#         logging.info('-----------------------factoryList公式因子关联-----------------------')
#         logging.info(factoryList)
        return factoryList    
    
    
    def getGP(riskCode,age=None,sex=None,job=None,payEndYear=None,payEndYearFlag=None,
              insuYear=None,insuYearFlag=None,getYear=None,getYearFlag=None,getRate=None,
              smokeFlag=None):
         # 初始化SQL 查询语
        str = ""
        if age:
            str += " AND age = '%s"%age + "'"
        if sex:
            str += " AND sex = '%s"%sex + "'"
        if job:
            str += " AND job = '%s"%job + "'"
        if payEndYear:
            str += " AND payEndYear = '%s"%payEndYear + "'"
        if payEndYearFlag:
            str += " AND payEndYearFlag = '%s"%payEndYearFlag + "'"    
        if insuYear:
            str += " AND insuYear = '%s"%insuYear + "'"
        if insuYearFlag:
            str += " AND insuYearFlag = '%s"%insuYearFlag + "'"
#         if insuYearFlag:
#             str += " AND insuYearFlag = '%s"%insuYearFlag + "'"
        if getYear:
            str += " AND getYear = '%s"%getYear + "'"
        if getYearFlag:
            str += " AND getYearFlag = '%s"%getYearFlag + "'"    
        if getRate:
            str += " AND getRate = '%s"%getRate + "'"
        if smokeFlag:
            str += " AND smokeFlag = '%s"%smokeFlag + "'"  
              
        sql1 = "select GP from gpinfo where riskCode ='%s' %s"%(riskCode,str)
        return sql1
    
    
    def selectCalModeByRiskCode(selectRiskCode):
        sqlStr = "select \
                risk_duty_id, risk_code, duty_code, duty_name, ChoFlag, cal_mode, prem_zero_flag,\
                amnt_zero_flag, risk_occupation_type\
                from risk_duty\
                where risk_code = '%s'"%selectRiskCode
        resultBySql = CaseBase.executeSql(sqlStr)
        riskDutyList = []
        for rowData in resultBySql:
            riskDuty = {}
            riskDuty['riskDutyId'] = rowData[0]
            riskDuty['riskCode'] = rowData[1]
            riskDuty['dutyCode'] = rowData[2]
            riskDuty['dutyName'] = rowData[3]
            riskDuty['choflag'] = rowData[4]
            riskDuty['calMode'] = rowData[5]
            riskDuty['premZeroFlag'] = rowData[6]
            riskDuty['amntZeroFlag'] = rowData[7]
            riskDuty['riskOccupationType'] = rowData[8]
            riskDutyList.append(riskDuty)
        return riskDutyList
    
    def selectRiskMaxAppAge(riskCode):
        sqlStr = "select \
                risk_renew_param_id, risk_code, risk_role, sex, min_app_age_flag, min_app_age, max_app_age_flag, \
                max_app_age, locale, is_deleted, create_user, create_date, update_user, update_date\
                from risk_renew_param \
                where risk_code = '%s'"%riskCode
        resultBySql = CaseBase.executeSql(sqlStr)
        riskList = []
        for rowData in resultBySql:
            risk = {}
            risk['riskRenewParamId'] = rowData[0]
            risk['riskCode'] = rowData[1]
            risk['riskRole'] = rowData[2]
            risk['sex'] = rowData[3]
            risk['minAppAgeFlag'] = rowData[4]
            risk['minAppAge'] = rowData[5]
            risk['maxAppAgeFlag'] = rowData[6]
            risk['maxAppAge'] = rowData[7]
            risk['locale'] = rowData[8]
            risk['isDeleted'] = rowData[9]
            risk['createUser'] = rowData[10]
            risk['createDate'] = rowData[11]
            risk['updateUser'] = rowData[12]
            risk['updateDate'] = rowData[13]
            riskList.append(risk)
        return riskList
    
#     def startTest(self):
# #         self.getAllRiskMap()
#         self.selectExpressByRiskcode('1032', "")
# #         self.getOccupationByCode('2110102')
#             
#           
# if(__name__ == "__main__"):
#     print(os.getcwd())
#     RisksqlBase().startTest()

        