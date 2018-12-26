# -*- coding: utf-8 -*-
"""
-------------------------------------------------
   File Name：     Plansql.py 
                               建议书系统调用数据库方法
   Description :  1.获取建议书险种计算信息             单晓伟
                  2.获取建议书表投被保人信息         单晓伟
                  3.获取建议书险种关联信息             张悦
                  4.获取追加领取信息                        张悦
                  5.获取代理人所持建议书信息         张悦
                  代码整合                            张悦
   Author :        单晓伟  张悦
   date：          2018/03/09
-------------------------------------------------
"""


from  aeonlifebase.CaseBase import CaseBase

from  aeonlifebase.plan.Config import Config

import logging

import decimal  as dc

class Plansql(CaseBase):
    
    def __init__(self):
        CaseBase.__init__(self, Config())
    
        
    # 获取建议书险种关联信息，入参为(追加建议书的入参，planId)返回值为planRiskRelationId的集合
    def getRiskInfo(planInsertDetialIn, planId):
        riskInfoList = planInsertDetialIn['insuredInfo']['riskInfoList']
        resultList = []
        if riskInfoList:
            for riskInfo in riskInfoList:
                # 取到入参中的单个险种信息
                strSql = {}
                strSql['plan_id'] = planId
                strSql['risk_code'] = riskInfo['riskCode']
                strSql['amnt'] = dc.Decimal(riskInfo['amnt']) if riskInfo['amnt'] else None
                strSql['prem'] = dc.Decimal(riskInfo['prem']) if riskInfo['prem'] else None
                strSql['pay_intv'] = dc.Decimal(riskInfo['payIntv']) if riskInfo['payIntv'] else None
                strSql['pay_end_year'] = dc.Decimal(riskInfo['payEndYear']) if riskInfo['payEndYear'] else None
                strSql['pay_end_year_flag'] = riskInfo['payEndYearFlag']
                strSql['insu_year'] = dc.Decimal(riskInfo['insuYear']) if riskInfo['insuYear'] else None
                strSql['insu_year_flag'] = riskInfo['insuYearFlag']
                strSql['get_year'] = dc.Decimal(riskInfo['getYear']) if riskInfo['getYear'] else None
                strSql['get_year_flag'] = riskInfo['getYearFlag']
                strSql['get_rate'] = dc.Decimal(riskInfo['getRate']) if riskInfo['getRate'] else None
                strSql['clm_get_intv'] = riskInfo['clmGetIntv']
                strSql['renew_flag'] = riskInfo['rnewFlag']
                strSql['mult'] = dc.Decimal(riskInfo['mult']) if riskInfo['mult'] else None
                # 初始化sql查询语句
                str = CaseBase.strAppend(strSql)
                sqlSelectPlanRiskRelation = "SELECT plan_risk_relation_id FROM plan_risk_relation WHERE %s"%str    
                res = CaseBase.executeSql(sqlSelectPlanRiskRelation)
                # 结果条数
                if res:
                    planRiskRelationId = res[0][0]
                    resultList.append(planRiskRelationId)
            return resultList
        return resultList
    
    # 获取追加领取信息，入参为(追加建议书的入参，planCode)返回值为planOperationId的集合
    def getAddGetInfo(planInsertDetialIn, planCode):
        addGetInfoList = planInsertDetialIn['insuredInfo']['addGetInfoList']
        resultList = []
        if addGetInfoList:
            for addGetInfo in addGetInfoList:
                # 取到入参中的单个追加领取信息
                strSql = {}
                strSql['operator_type'] = addGetInfo['type']
                strSql['risk_code'] = addGetInfo['riskCode']
                strSql['start_year'] = int(addGetInfo['startYear'])
                strSql['end_year'] = int(addGetInfo['endYear'])
                strSql['money'] = dc.Decimal(addGetInfo['prem']) if addGetInfo['prem'] else None
                strSql['plan_code'] = planCode
                # 初始化sql查询语句
                str = CaseBase.strAppend(strSql)
                sqlSelectPlanOperation = "SELECT plan_operation_id FROM plan_operation WHERE %s"%str
                res = CaseBase.executeSql(sqlSelectPlanOperation)
                # 结果条数
                if res:
                    planOperationId = res[0][0]
                    resultList.append(planOperationId)
            return resultList
        return resultList
    
    
    # 获取代理人所持建议书信息，入参为(userPlan)返回值为userPlanId的集合
    def getUserPlanInfo(userPlan):
        resultList = []
        # 取到入参中的单个追加领取信息
        strSql = {}
        strSql['insured_id'] = userPlan['insurredId']
        strSql['appnt_id'] = userPlan['appntId']
        strSql['plan_id'] = userPlan['planId']
        strSql['user_code'] = userPlan['userCode']
        strSql['rela_to_appnt'] = userPlan['relationToAppnt']
        strSql['sale_mode'] = userPlan['saleMode']
        strSql['plan_code'] = userPlan['planCode']
        # 初始化sql查询语句
        str = CaseBase.strAppend(strSql)
        sqlSelectUserPlan = "SELECT user_plan_id FROM user_plan WHERE %s"%str
        res = CaseBase.executeSql(sqlSelectUserPlan)
        # 结果条数
        if res:
            UserPlanId = res[0][0]
            resultList.append(UserPlanId)
        return resultList
    
    
    # 获取建议书planId   
    def savePlan(planCode):
        sql ="select plan_id from plan where plan_code='%s' "%planCode
        resultSql = CaseBase.executeSql(sql)
        plan = {}
        if resultSql:
            plan['plan_id']=resultSql[0][0]
            planId =plan['plan_id']
            return planId
        return None
    
    
    # 获取投保人appntId
    def saveAppntInfo(data):
        customerCode = data['appntInfo']['code']
        name = data['appntInfo']['name']
        sex = data['appntInfo']['sex']
        birthday = data['appntInfo']['birthday']
        occupationCode = data['appntInfo']['occupationCode']
        param=""
        if customerCode:
            param += " and customer_code ='%s' "%customerCode
        if name:
            param +=" and name='%s'"%name
        if sex:
            param +=" and sex='%s'"%sex
        if birthday:
            param +=" and birthday='%s'"%birthday
        if occupationCode:
            param +=" and occupation='%s'"%occupationCode
        sql = "select plan_customer_id from plan_customer where is_deleted='n' "+param +" ORDER BY plan_customer_id DESC"
        resultSql = CaseBase.executeSql(sql)
        # 返回查询结果
        if resultSql:
            appntId = resultSql[0][0]
            return appntId
        return None
    
    
    # 获取被保人insuredId
    def saveInsuredInfo(data):
        customerCode = data['insuredInfo']['code']
        name = data['insuredInfo']['name']
        sex = data['insuredInfo']['sex']
        birthday = data['insuredInfo']['birthday']
        occupationCode = data['insuredInfo']['occupationCode']
        socialSecurityFlag = data['insuredInfo']['socialSecurityFlag']
        ifSmoke = data['insuredInfo']['ifSmoke']
        
        param=""
        if customerCode:
            param += " and customer_code ='%s' "%customerCode
        if name:
            param +=" and name='%s'"%name
        if sex:
            param +=" and sex='%s'"%sex
        if birthday:
            param +=" and birthday='%s'"%birthday
        if occupationCode:
            param +=" and occupation='%s'"%occupationCode
        if socialSecurityFlag:
            param +=" and has_social_security='%s'"%socialSecurityFlag
        if ifSmoke:
            param +=" and is_smoke='%s'"%ifSmoke
        sql = "select plan_customer_id from plan_customer where is_deleted='n' "+param + " ORDER BY plan_customer_id DESC"
        resultSql = CaseBase.executeSql(sql)
        if resultSql:
            # 返回查询结果
            insuredId = resultSql[0][0]
            return insuredId
        return None
    
    
    # 当投保人code为空时,将投保人信息追加至潜加客户,并获取投保人code
    def savePotentialCustomer(data):
        #切换到customer库中
        CaseBase.changeSchema('iudp_zt_customer')
        userCode = data['userCode']
        customerCode = data['appntInfo']['code']
        name = data['appntInfo']['name']
        sex = data['appntInfo']['sex']
        birthday = data['appntInfo']['birthday']
        occupationCode = data['appntInfo']['occupationCode']
        param=""
        if userCode:
            param += " and r.user_code ='%s' "%userCode
        if customerCode:
            param += " and p.customer_code ='%s' "%customerCode
        if name:
            param +=" and p.name='%s'"%name
        if sex:
            param +=" and p.sex='%s'"%sex
        if birthday:
            param +=" and p.birthday='%s'"%birthday
        if occupationCode:
            param +=" and p.occupation='%s'"%occupationCode
        sql = "select p.customer_code from potential_customer p ,rel_customer_user r  \
                 WHERE r.customer_code =p.customer_code and p.is_deleted='n' "+param+ " ORDER BY p.customer_code DESC"
        resultSql = CaseBase.executeSql(sql)
        #切换回plan库中
        CaseBase.changeSchema('iudp_zt_plan')
        if resultSql:
            #返回查询结果
            appntCode = resultSql[0][0]
            return appntCode
        return None
    
    
    def startTest(self):
        planInsertDetialIn =  { 
                "userCode":"somnus", 
                "planCode":"plan1520587986100",
                "appntInfo":{
                    "code":"111", 
                    "name":"李小三", 
                    "sex":"1", 
                    "birthday":"1990-11-22", 
                    "occupationCode":"2110102"
                    }, 
                "insuredInfo":{ 
                    "code":"11", 
                    "name":"王小四", 
                    "sex":"0", 
                    "birthday":"1998-2-2", 
                    "occupationCode":"2110102", 
                    "relationToAppnt":"1", 
                    "socialSecurityFlag":"1", 
                    "ifSmoke":"Y", 
                    "riskInfoList":[
                        { 
                            "riskCode":"1032", 
                            "prem":"22231", 
                            "amnt":"1", 
                            "mult":"1", 
                            "getRate":"", 
                            "payIntv":"2", 
                            "payEndYear":"2", 
                            "payEndYearFlag":"1", 
                            "insuYear":"1", 
                            "insuYearFlag":"1", 
                            "getYear":"1", 
                            "getYearFlag":"1", 
                            "clmGetIntv":"1", 
                            "rnewFlag":"Y" 
                            } 
                        ],
                    "addGetInfoList":[ 
                        {
                            "type":"1", 
                            "riskCode":"1032", 
                            "startYear":"1", 
                            "endYear":"10", 
                            "prem":"11"  
                            } 
                        ] 
                    } 
                }
