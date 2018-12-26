# -*- coding: utf-8 -*-
"""
-------------------------------------------------
   File Name：     PolicysqlBase.py 
                               在线承保-投核保交易/投核保交易暂存查库方法
   Description :  1、        查询订单1
                  2、        查询代理人权限
                  3、        查询组别2
                  4、        查询保单4
                  5、        查询代理人信息10
                  6、        查询被保人关系8
                  7、        查询险种信息6
                  8、        查询地址
                  9、        查询告知
                  10、     查询投被保人37
                  11、     查询受益人9
                  12、     查询服务5
                  13、     查询文件
                  14、    查询风险保额
   Author :              张悦
   date：          2018/03/16
-------------------------------------------------
"""

from aeonlifebase.CaseBase import CaseBase

from aeonlifebase.policy.Config import Config

from  aeonlifebase.policy.PolicyDao import PolicyDao

import logging

from datetime import datetime


'''
此类中除2.查询代理人权限以外所有方法入参均为dict，拼接在sql后作为参数
key为所查表字段名，value为需要赋的值，可以自己定义条件，特殊sql在自己
负责模块中编写。以1.查询订单为例，需要查b字段时，在sqlGetOrderInfo
中添加需要的字段名，if results：内为其赋值.调用方法时，若所传dict内
的值全部为空，请在调用方法前做判断，否则出错
'''
class PolicysqlBase(CaseBase):
    def __init__(self):
        CaseBase.__init__(self, Config())
        
    # 1.查询订单        
    # 查询orderinfo表  
    def getOrderInfo(info):
        param = CaseBase.strAppend(info)
        sqlGetOrderInfo = 'SELECT orderinfo_id, state FROM `orderinfo` WHERE %s' % param
        results = CaseBase.executeSql(sqlGetOrderInfo)
        orderInfo = {}
        if results:
            orderInfo['orderInfoId'] = results[0][0]
            orderInfo['state'] = results[0][1]
        return orderInfo
    
    
#     # 2.查询代理人权限
#     # 根据orderNo, agentNo查询代理人下是否有该订单的处理权限
#     def getAuthority(orderNo, agentCode):
#         info = {
#             'orderno' : orderNo, 
#             'agent_code' : agentCode
#             }
#         param = CaseBase.strAppend(info)
#         sqlGetAuthorityInfo = 'SELECT COUNT(policy_agent_relation_id) FROM `policy_agent_relation` WHERE %s' % param
#         results = CaseBase.executeSql(sqlGetAuthorityInfo)
#         return result[0][0] > 0
    
    
    # 3.查询组别
    # 查询grp组别表
    def getGrpInfoList(info):
        param = CaseBase.strAppend(info)
        grpInfoList = []
        sqlGetGrpInfo = 'SELECT grp_id FROM `grp` WHERE %s' % param
        results = CaseBase.executeSql(sqlGetGrpInfo)
        if results:
            for result in results:
                grpInfo = {}
                grpInfo['grpId'] = result[0]
                grpInfoList.append(grpInfo)
        return grpInfoList
    
    
    # 4.查询保单
    # 查询policy保单表
    def getPolicyInfoList(info):
        param = CaseBase.strAppend(info)
        policyInfoList = []
        sqlGetPolicyInfo = '''SELECT policy_id, appnt_id, managecom, appnt_code, prtno, grpno, 
            contno, sale_mode, sub_sale_mode FROM `policy` WHERE %s''' % param
        results = CaseBase.executeSql(sqlGetPolicyInfo)
        if results:
            for result in results:
                policyInfo = {}
                policyInfo['policyId'] = result[0]
                policyInfo['appntId'] = result[1]  # 投保人主键
                policyInfo['managecom'] = result[2]  # 管理机构
                policyInfo['appntCode'] = result[3]  # 投保人Code
                policyInfo['prtno'] = result[4]  # 投保单号
                policyInfo['grpNo'] = result[5]  # 组别号
                policyInfo['contNo'] = result[6]  # 保单号
                policyInfo['saleMode'] = result[7]  # sale_mode销售方式 由哪个平台销售 mobile：移动出单 wx：微信 pc 官网
                policyInfo['subSaleMode'] = result[8]  # sub_sale_mode、moble下暂时分M,P等
                policyInfoList.append(policyInfo)
        return policyInfoList
    
    
    # 5.查询代理人信息
    # 查询policy_agent_relation保单代理人关联表
    def getPolicyAgentRelationInfoList(info):
        param = CaseBase.strAppend(info)
        policyAgentRelationInfoList = []
        sqlGetPolicyAgentRelationInfo = 'SELECT policy_agent_relation_id FROM `policy_agent_relation` WHERE %s' % param
        results = CaseBase.executeSql(sqlGetPolicyAgentRelationInfo)
        if results:
            for result in results:
                policyAgentRelationInfo = {}
                policyAgentRelationInfo['policyAgentRelationId'] = result[0]
                policyAgentRelationInfoList.append(policyAgentRelationInfo)
        return policyAgentRelationInfoList
    
    # 6.查询被保人关系
    # 查询policy_insured_relation保单被保险人关联表
    def getPolicyInsuredRelationInfoList(info):
        param = CaseBase.strAppend(info)
        policyInsuredRelationInfoList = []
        sqlGetPolicyInsuredRelationInfo = 'SELECT policy_insured_relation_id, customer_id FROM `policy_insured_relation` WHERE %s' % param
        results = CaseBase.executeSql(sqlGetPolicyInsuredRelationInfo)
        if results:
            for result in results:
                policyInsuredRelationInfo = {}
                policyInsuredRelationInfo['policyInsuredRelationId'] = result[0]
                policyInsuredRelationInfo['customerId'] = result[1]  # 被保人主键
                policyInsuredRelationInfoList.append(policyInsuredRelationInfo)
        return policyInsuredRelationInfoList
    
    
    # 7.查询险种信息
    # 查询policy_risk_relation保单险种关联表,返回包含主键的dict
    def getPolicyRiskRelationInfoList(info):
        param = CaseBase.strAppend(info)
        policyRiskRelationInfoList = []
        sqlGetPolicyRiskRelationInfo = 'SELECT policy_risk_relation_id, insu_year FROM `policy_risk_relation` WHERE %s' % param
        results = CaseBase.executeSql(sqlGetPolicyRiskRelationInfo)
        if results:
            for result in results:
                policyRiskRelationInfo = {}
                policyRiskRelationInfo['policyRiskRelationId'] = result[0]
                policyRiskRelationInfo['insuYear'] = result[1]
                policyRiskRelationInfoList.append(policyRiskRelationInfo)
        return policyRiskRelationInfoList
    
    
    # 8.查询地址
    # 根据rgtAddress(户籍)/postalAddress(地址编码)查询address地址信息表
    def getAddressInfo(info):
        param = CaseBase.strAppend(info)
        sqlGetAddressInfo = 'SELECT address_id FROM `address` WHERE %s' % param
        results = CaseBase.executeSql(sqlGetAddressInfo)
        if results:
            addressInfo = {}
            addressInfo['addressId'] = results[0][0]
        return addressInfo
    
    
    # 9.查询告知
    # 根据customerId查询impartinfo告知信息表,返回包含主键的dict
    def getImpartinfoInfoList(info):
        param = CaseBase.strAppend(info)
        impartinfoInfoList = []
        sqlGetImpartinfoInfo = 'SELECT impartinfo_id FROM `impartinfo` WHERE %s' % param
        results = CaseBase.executeSql(sqlGetImpartinfoInfo)
        if results:
            for result in results:
                impartinfoInfo = {}
                impartinfoInfo['impartinfoId'] = result[0]
                impartinfoInfoList.append(impartinfoInfo)
        return impartinfoInfoList
    
    # 10.查询投被保人
    # 根据orderNo查询customer客户信息表，返回包含客户信息的客户列表
    def getCustomerInfoList(info):
        param = CaseBase.strAppend(info)
        customerInfoList = []
        sqlGetCustomerInfo = 'SELECT customer_id, rgt_address, postal_address, birthday FROM `customer` WHERE %s' % param
        results = CaseBase.executeSql(sqlGetCustomerInfo)
        if results:
            for result in results:
                customerInfo = {}
                customerInfo['customerId'] = result[0]
                customerInfo['rgtAddress'] = result[1]
                customerInfo['postalAddress'] = result[2]
                customerInfo['birthday'] = result[3]
                customerInfoList.append(customerInfo)
        return customerInfoList
    
    # 11.查询受益人
    # 根据orderNo查询bnt受益人信息表
    def getBntInfoList(info):
        param = CaseBase.strAppend(info)
        bntInfoList = []
        sqlGetBntInfo = 'SELECT bnt_id, customer_id FROM `bnt` WHERE %s' % param
        results = CaseBase.executeSql(sqlGetBntInfo)
        if results:
            for result in results:
                bntInfo = {}
                bntInfo['bntId'] = result[0]
                bntInfo['customerId'] = result[1]  # 受益人主键
                bntInfoList.append(bntInfo)
        return bntInfoList
    
    
    # 12.查询服务
    # 根据orderNo查询policy_service保单服务关联表
    def getPolicyServiceRelationInfoList(info):
        param = CaseBase.strAppend(info)
        policyServiceInfoList = []
        sqlGetBntInfo = 'SELECT policy_service_id FROM `policy_service` WHERE %s' % param
        results = CaseBase.executeSql(sqlGetBntInfo)
        if results:
            for result in results:
                policyServiceInfo = {}
                policyServiceInfo['policyServiceId'] = result[0]
                policyServiceInfoList.append(policyServiceInfo)
        return policyServiceInfoList
    
    
    # 13.查询文件
    # 根据orderNo查询policy_file保单文件关联表,返回包含主键的dict
    def getPolicyFileRelationInfoList(info):
        param = CaseBase.strAppend(info)
        policyFileInfoList = [] 
        sqlGetBntInfo = 'SELECT policy_file_id FROM `policy_file` WHERE %s' % param
        results = CaseBase.executeSql(sqlGetBntInfo)
        if results:
            for result in results:
                policyFileInfo = {}
                policyFileInfo['policyFileId'] = result[0]
                policyFileInfoList.append(policyFileInfo)
        return policyFileInfoList
    
    
    # 14.查询风险保额
    # 根据prtNo查询customer_riskamnt客户风险保额表,返回包含主键的dict
    def getCustomerRiskamntInfoList(info):
        param = CaseBase.strAppend(info)
        sqlGetCustomerRiskamntInfo = 'SELECT customer_riskamnt_id FROM `customer_riskamnt` WHERE %s' % param
        results = CaseBase.executeSql(sqlGetCustomerRiskamntInfo)
        customerRiskamntInfo = {}
        if results:
            customerRiskamntInfo['CustomerRiskamntId'] = results[0][0]
        return customerRiskamntInfo
    
    
    # 判断此四表状态是否正确
    def statusTrue(selectResult):
        orderInfo = selectResult['orderInfo'] 
        grpInfoList = selectResult['grpInfoList'] 
        policyInfoList = selectResult['policyInfoList']
        policyRiskRelationInfoList = selectResult['policyRiskRelationInfoList']
        infoList = [orderInfo, grpInfoList, policyInfoList, policyRiskRelationInfoList]
        for info in infoList:
            if not info:
                return False
        return True
    
    
    # 判断查询结果是否全部为空(以此判断数据是否落库)
    def isNotEmptyDict(selectResultDict):
        for k, v in selectResultDict.items():
            if v:
                return True
        return False
    
    
    def isNotEmptyList(selectResultList):
        for selectResult in selectResultList:
            if not selectResult:
                return False
        return True
    
    # 判断风险保额表是否有数据落库
    def customerRiskamntInfoIsNotEmpty(selectResult):
        if selectResult['customerRiskamntInfo']:
            return True
        return False
    
    
    # 投保人与被保人关系，判断落库信息是否正确
    def judgeRelationOfAppntAndInsured(relationToAppntIn, selectResult):
        customers = selectResult['customerInfoList'][-1]
        if CaseBase.isMatch(relationToAppntIn, '00'):
            # 同一人时此三条数据为空
            if not selectResult['impartinfoInfoList'] or selectResult['rgtAddressInfoList'] or selectResult['postalAddressInfoList']:
                if CaseBase.isMatch(customers['appntId'], customers['insuredCustomerId']):
                    return True
                return False
            return False
        return True
    
    
    # 受益人与被保人关系，判断落库信息是否正确
    def judgeRelationOfbntAndInsured(bntInfoListIn, selectResult):
        customers = selectResult['customerInfoList'][-1]
        for bntInfo in bntInfoListIn:
            if CaseBase.isMatch(bntInfo['relationToInsured'], '00'):
                if customers['appntId'] in customers['bntCustomerIdList']:
                    return True
                return False
            return True
        
    
    # 判断投被保人受益人之间关系，依此关系落库数据是否正确
    def judgeRelation(relationToAppntIn, bntInfoListIn, selectResult):
        if PolicysqlBase.judgeRelationOfAppntAndInsured(relationToAppntIn, selectResult) and \
        PolicysqlBase.judgeRelationOfbntAndInsured(bntInfoListIn, selectResult):
            return True
        return False
    
    #===================五表====================
    '''遍历riskInfoList,利用如下几个方法计算'''
    # 计算交费年期
    def calcPayYears(riskInfo, data):
        payEndYear = riskInfo['payEndYear']
        payEndYearFlag = riskInfo['payEndYearFlag']
        cvalidate = data['insuredInfoList'][0]['contInfo']['cvalidate']
        # cvalidate如果传入的话以cvalidate处理,如果没有传入的话以当前系统时间为准
        if not cvalidate:
            cvalidate = datetime.now().strftime("%Y-%m-%d")
        birthday = data['insuredInfoList'][0]['birthday']
        payYears = None
        if CaseBase.isMatch(payEndYearFlag, 'Y'):
            payYears = payEndYear
        elif CaseBase.isMatch(payEndYearFlag, 'A'):
            insuredAge = PolicyDao.calInterval(birthday, cvalidate)
            payYears = payEndYear - insuredAge
        return payYears
    
    
    # 计算保险年期
    def calcYears(riskInfo, data):
        insuYear = int(riskInfo['insuYear'])
        insuYearFlag = riskInfo['insuYearFlag']
        cvalidate = data['insuredInfoList'][0]['contInfo']['cvalidate']
        # cvalidate如果传入的话以cvalidate处理,如果没有传入的话以当前系统时间为准
        if not cvalidate:
            cvalidate = datetime.now().strftime("%Y-%m-%d")
        birthday = data['insuredInfoList'][0]['birthday']
        endDate = None
        if CaseBase.isMatch(insuYearFlag, 'Y'):
            endDate = insuYear
        elif CaseBase.isMatch(insuYearFlag, 'A'):
            insuredAge = PolicyDao.calInterval(birthday, cvalidate)
            endDate = insuYear - insuredAge
        return endDate
    
    
    # 计算交至日期
    def calcPayEndDate(riskInfo, data):
        payEndYearIn = riskInfo['payEndYear']
        payEndYearFlag = riskInfo['payEndYearFlag']
        cvalidate = data['insuredInfoList'][0]['contInfo']['cvalidate']
        # cvalidate如果传入的话以cvalidate处理,如果没有传入的话以当前系统时间为准
        if not cvalidate:
            cvalidate = datetime.now().strftime("%Y-%m-%d")
        birthday = data['insuredInfoList'][0]['birthday']
        payEndDate = None
        if CaseBase.isMatch(payEndYearFlag, 'Y'):
            payEndDate = PolicysqlBase.deltaTime(cvalidate, year = int(payEndYearIn))
        elif CaseBase.isMatch(payEndYearFlag, 'A'):
            insuredAge = PolicyDao.calInterval(birthday, cvalidate)
            payEndDate = PolicysqlBase.deltaTime(cvalidate, year = (payEndYearIn - insuredAge))
        return payEndDate
    
    
    # 计算应缴日期
    def calcPayToDate(riskInfo, data):
        payToDate = None
        payIntv = riskInfo['payIntv']
        cvalidate = data['insuredInfoList'][0]['contInfo']['cvalidate']
        # cvalidate如果传入的话以cvalidate处理,如果没有传入的话以当前系统时间为准
        if not cvalidate:
            cvalidate = datetime.now().strftime("%Y-%m-%d")
        if CaseBase.isMatch(payIntv, '12'):
            payToDate = PolicysqlBase.deltaTime(cvalidate, year = 1)
        elif CaseBase.isMatch(payIntv, '1'):
            payToDate = PolicysqlBase.deltaTime(cvalidate, year = 1000)
        return payToDate
    
    
    # 计算保险责任终止日期
    def calcEndDate(riskInfo, data):
        insuYear = int(riskInfo['insuYear'])
        insuYearFlag = riskInfo['insuYearFlag']
        cvalidate = data['insuredInfoList'][0]['contInfo']['cvalidate']
        # cvalidate如果传入的话以cvalidate处理,如果没有传入的话以当前系统时间为准
        if not cvalidate:
            cvalidate = datetime.now().strftime("%Y-%m-%d")
        birthday = data['insuredInfoList'][0]['birthday']
        endDate = None
        if CaseBase.isMatch(insuYearFlag, 'Y'):
            endDate = PolicysqlBase.deltaTime(cvalidate, year = insuYear)
        elif CaseBase.isMatch(insuYearFlag, 'A'):
            insuredAge = PolicyDao.calInterval(birthday, cvalidate)
            endDate = PolicysqlBase.deltaTime(cvalidate, year = (insuYear - insuredAge))
        return endDate
    
    
    # 此查询也要随riskInfoList遍历
    # 查询policy_risk_duty表对应的字段落库值(依此判断与计算的值是否匹配)
    '''
    info = {
        "orderno" : orderNo, 
        "prtno" : prtNo,
        "risk_code" : riskCode
        }
    '''
    def queryPolicyRiskDuty(info):
        str1 = CaseBase.strAppend(info)
        sqlPolicyRiskRelationId = '''SELECT policy_risk_relation_id from policy_risk_relation WHERE %s''' % str1
        sql = '''SELECT psd.policy_risk_duty_id, psd.pay_years, psd.years, psd.pay_end_date, psd.pay_to_date, 
            psd.end_date FROM `policy_risk_duty` psd, `policy_risk_relation` prr WHERE psd.policy_risk_relation_id 
            = prr.policy_risk_relation_id AND prr.policy_risk_relation_id in (%s)''' % sqlPolicyRiskRelationId
        results = CaseBase.executeSql(sql)
        resultList = []
        if results:
            for result in results:
                result0 = []
                result1 = {
                    'policyRiskDutyId' : result[0]
                    }
                result2 = {
                    'payYears' : str(result[1]), 
                    'years' : result[2], 
                    'payEndDate' : result[3],
                    'payToDate' : result[4],
                    'endDate' : result[5]
                    }
                result0.append(result1)
                result0.append(result2)
                resultList.append(result0)
        else:
            result0 = []
            result1 = None
            result2 = None
            result0.append(result1)
            result0.append(result2)
            resultList.append(result0)
        return resultList
    
    
    # policy_duty_get 领取项信息表
    def queryPolicyDutyGet(info):
        str1 = CaseBase.strAppend(info)
        sql = '''SELECT COUNT(*) FROM `policy_duty_get` WHERE %s''' % str1
        results = CaseBase.executeSql(sql)
        return results[0][0]
    
    
    # policy_duty_prem 保费详细信息
    def queryPayPlanCode(info):
        str1 = CaseBase.strAppend(info)
        sql = '''SELECT COUNT(*) FROM `policy_duty_prem` WHERE %s''' % str1
        results = CaseBase.executeSql(sql)
        return results[0][0]
    
    
    # policy_duty_prem_acc 保费项表和客户帐户表的关联表
    def queryPolicyDutyPremAcc(info):
        str1 = CaseBase.strAppend(info)
        sql = '''SELECT COUNT(*) FROM `policy_duty_prem_acc` WHERE %s''' % str1
        results = CaseBase.executeSql(sql)
        return results[0][0] > 0
    
    
    # policy_duty_get_rela 保单险种责任领取项扩展信息表
    def queryPolicyDutyGetRela(info):
        str1 = CaseBase.strAppend(info)
        sql = '''SELECT COUNT(*) FROM `policy_duty_get_rela` WHERE %s''' % str1
        results = CaseBase.executeSql(sql)
        return results[0][0] > 0
    
    # 日期处理
    def deltaTime(time, year = 0, month = 0, day = 0):
        times = time.split(" ")
        time = times[0]
        t = datetime.strptime(time, '%Y-%m-%d')
        return "%04d-%02d-%02d"%(t.year + year, t.month + month, t.day + day)
    
