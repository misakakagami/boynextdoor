# -*- coding: utf-8 -*-
"""
------------------------------------------------
    File Name:      PolicyDao.py
    Description:    在线承保 数据处理层
    Author:         孔德华
    date:           2018/03/15
    Copyright:      (c)   孔德华2018
------------------------------------------------
    Change Record:
       2018/03/15 孔德华 增加根据订单号、投保单号、用户编码获得保单列表的方法
"""

import json
from aeonlifebase.CaseBase import CaseBase
from aeonlifebase.policy.Config import Config
from decimal import *
import logging
import time
from copy import deepcopy

class PolicyDao():
    
    addressBaseColumnList = """
        address_id, province, city, county, address_text, postal_zip_code, locale, is_deleted, create_user, create_date, update_user, update_date
        """
    
    bntBaseColumnList = """
        bnt_id, policy_id, orderno, grpno, prtno, type, relationtoappnt, relationtoinsured, bnfgrade, bnflot, customer_id, customer_code, locale, is_deleted, create_user, create_date, update_user, update_date
        """
    
    customerBaseColumnList = """
        customer_id, orderno, prtno, customer_code, name, sex, id_type, id_no, birthday, id_validate, stature, avoirdopois, marriage, native_place, degree, salary, occupation, is_smoke, has_social_security, phone, other_phone, email, rgt_address, postal_address, customer_level, locale, is_deleted, create_user, create_date, update_user, update_date
        """
    
    customerRiskAmntBaseColumnList = """
        customer_riskamnt_id, customer_code, prtno, riskamnt_l, riskamnt_d, riskamnt_a, riskamnt_ba, riskamnt_la, locale, is_deleted, create_user, create_date, update_user, update_date
        """
    
    grpBaseColumnList = """
        grp_id, orderno, grpno, state, cancel_reason, locale, is_deleted, create_user, create_date, update_user, update_date
        """
    
    impartInfoBaseColumnList = """
        impartinfo_id, customer_id, version, code, param, start_date, end_date, locale, is_deleted, create_user, create_date, update_user, update_date
        """
    
    orderBaseColumnList = """
        orderinfo_id, orderno, state, charge_state, charge_date, cancel_reason, app_id, locale, is_deleted, create_user, create_date, update_user, update_date, pay_plat_type
        """
    
    policyBaseColumnList = """
        p.policy_id, p.orderno, p.grpno, p.contno, p.prtno, p.managecom, p.salechnl, p.appnt_id, p.appnt_code, p.insured_id, p.insured_code, p.new_bank_code, p.new_bank_acc_no, p.new_acc_name, p.new_pay_mode, p.bank_code, p.bank_acc_no, p.acc_name, p.sum_prem, p.sum_amnt, p.pol_apply_date, p.status, p.app_flag, p.uw_flag, p.sign_date, p.sign_time, p.cvalidate, p.pol_mode, p.pol_get_date, p.pol_get_time, p.custom_get_pol_date, p.sale_mode, p.sub_sale_mode, p.has_callback, p.immediate_callback, p.rela_prt_no, p.need_email_subscribe, p.need_rule, p.need_dr, p.need_bnf, p.dr_date, p.upload_date, p.app_id, p.agent_code, p.remark, p.cancel_reason, p.locale, p.is_deleted, p.create_user, p.create_date, p.update_user, p.update_date, p.cvalidate_flag 
        """
    
    policyAgentRelationBaseColumnList = """
        policy_agent_relation_id, policy_id, orderno, grpno, prtno, agent_code, main_agent_flag, locale, is_deleted, create_user, create_date, update_user, update_date
        """
    
    #policybak
    #policyConfigSwitch
    
    policyDutyGetBaseColumnList = """
        policy_duty_get_id, policy_risk_relation_id, policy_risk_duty_id, orderno, grpno, contno, prtno, risk_code, duty_code, get_duty_code, get_duty_kind, insured_code, live_get_mode, live_get_type, get_limit, get_rate, urge_get_flag, add_rate, can_get, need_acc, need_cancel_acc, stand_money, actu_get, sum_money, get_intv, get_to_date, get_start_date, get_end_date, get_end_state, state, locale, is_deleted, create_user, create_date, update_user, update_date
        """
    
    policyDutyGetRelaBaseColumnList = """
        policy_duty_get_rela_id, policy_risk_relation_id, policy_risk_duty_id, orderno, grpno, contno, prtno, risk_code, duty_code, rela_type, get_duty_code, rela_code, rela_name, locale, is_deleted, create_user, create_date, update_user, update_date
        """
    
    policyDutyPremBaseColumnList = """
        policy_duty_prem_id, policy_risk_relation_id, policy_risk_duty_id, orderno, grpno, contno, prtno, risk_code, duty_code, pay_plan_code, pay_plan_type, appnt_type, appnt_code, urge_pay_flag, need_acc, pay_times, pay_start_date, pay_end_date, pay_to_date, prem, stand_prem, sum_prem, pay_intv, supp_risk_score, free_flag, free_rate, free_start_date, free_end_date, state, addFeeDirect, secInsuAddPoint, addFeeWay, locale, is_deleted, create_user, create_date, update_user, update_date
        """
    
    policyDutyPremAccBaseColumnList = """
        policy_duty_prem_acc_id, policy_duty_prem_id, orderno, grpno, contno, prtno, risk_code, duty_code, pay_plan_code, insu_acc_no, rate, new_flag, cal_flag, locale, is_deleted, create_user, create_date, update_user, update_date
        """
    
    policyFileBaseColumnList = """
        policy_file_id, policy_id, orderno, grpno, prtno, file_type, file_id, file_url, file_order, locale, is_deleted, create_user, create_date, update_user, update_date
        """
    
    policyInsuredRelationBaseColumnList = """
        policy_insured_relation_id, policy_id, orderno, grpno, prtno, customer_id, customer_code, relation_to_main_insured, relation_to_appnt, insured_type, locale, is_deleted, create_user, create_date, update_user, update_date
        """
    
    policyRiskDataBaseColumnList = """
        policy_risk_data_id, policy_id, orderno, grpno, prtno, risk_code, rela_type, code, name, locale, is_deleted, create_user, create_date, update_user, update_date
        """
    
    policyRiskDutyBaseColumnList = """
        policy_risk_duty_id, policy_risk_relation_id, orderno, grpno, contno, prtno, risk_code, duty_code, duty_name, mult, stand_prem, prem, sum_prem, amnt, risk_amnt, pay_intv, cvalidate, pay_years, years, pay_end_date, pay_to_date, float_rate, first_pay_date, get_year, get_year_flag, pay_end_year, pay_end_year_flag, insu_year, insu_year_flag, end_date, get_start_date, get_start_type, live_get_mode, bonus_get_mode, clm_get_intv, has_social_security, peak_line, get_limit, get_rate, cal_rule, cal_mode, locale, is_deleted, create_user, create_date, update_user, update_date
        """
    
    policyRiskRelationBaseColumnList = """
        policy_risk_relation_id, policy_id, orderno, grpno, prtno, risk_code, risk_name, sub_risk_flag, cvalidate, amnt, prem, mult, pay_intv, pay_end_year, pay_end_year_flag, insu_year, insu_year_flag, get_year, get_year_flag, get_rate, acc_auto_pay_flag, auto_pay_flag, bonus_get_mode, clm_get_intv, live_get_mode, renew_flag, app_flag, invalid_flag, locale, is_deleted, create_user, create_date, update_user, update_date
        """
    
    policyServiceBaseColumnList = """
        policy_service_id, policy_id, orderno, grpno, prtno, service_code, service_type, service_order, send_name, send_day, send_day_type, send_way, address_id, email, mobile, locale, is_deleted, create_user, create_date, update_user, update_date
        """
    
    policyUwflagResultBaseColumnList = """
        policy_uwflag_result_id, policy_id, orderno, grpno, prtno, uw_type, uw_no, serial_no, uw_rule_code, uw_rule_message, locale, is_deleted, create_user, create_date, update_user, update_date
        """

    #根据获得地址信息列表
    def getAddressList(conditionStr):
        sqlStr = "select" + PolicyDao.addressBaseColumnList + " from address"
        if conditionStr != None:
            sqlStr += conditionStr
        result = CaseBase.executeSql(sqlStr)
        addressList = []
        for row in result:
            policyMap = {}
            policyMap['addressId'] = row[0]
            policyMap['province'] = row[1]
            policyMap['city'] = row[2]
            policyMap['county'] = row[3]
            policyMap['addressText'] = row[4]
            policyMap['postalZipCode'] = row[5]
            policyMap['locale'] = row[6]
            policyMap['isDeleted'] = row[7]
            policyMap['createUser'] = row[8]
            policyMap['createDate'] = row[9]
            policyMap['updateUser'] = row[10]
            policyMap['updateDate'] = row[11]
            addressList.append(policyMap)
        return addressList

    #根据获得受益人信息列表
    def getBntList(conditionStr):
        sqlStr = "select" + PolicyDao.bntBaseColumnList + " from bnt"
        if conditionStr != None:
            sqlStr += conditionStr
        result = CaseBase.executeSql(sqlStr)
        bntList = []
        for row in result:
            policyMap = {}
            policyMap['bntId'] = row[0]
            policyMap['policyId'] = row[1]
            policyMap['orderno'] = row[2]
            policyMap['grpno'] = row[3]
            policyMap['prtno'] = row[4]
            policyMap['type'] = row[5]
            policyMap['relationtoappnt'] = row[6]
            policyMap['relationtoinsured'] = row[7]
            policyMap['bnfgrade'] = row[8]
            policyMap['bnflot'] = row[9]
            policyMap['customerId'] = row[10]
            policyMap['customerCode'] = row[11]
            policyMap['locale'] = row[12]
            policyMap['isDeleted'] = row[13]
            policyMap['createUser'] = row[14]
            policyMap['createDate'] = row[15]
            policyMap['updateUser'] = row[16]
            policyMap['updateDate'] = row[17]
            bntList.append(policyMap)
        return bntList

    #根据获得客户信息列表
    def getCustomerList(conditionStr):
        sqlStr = "select" + PolicyDao.customerBaseColumnList + " from customer"
        if conditionStr != None:
            sqlStr += conditionStr
        result = CaseBase.executeSql(sqlStr)
        customerList = []
        for row in result:
            policyMap = {}
            policyMap['customerId'] = row[0]
            policyMap['orderno'] = row[1]
            policyMap['prtno'] = row[2]
            policyMap['customerCode'] = row[3]
            policyMap['name'] = row[4]
            policyMap['sex'] = row[5]
            policyMap['idType'] = row[6]
            policyMap['idNo'] = row[7]
            policyMap['birthday'] = row[8]
            policyMap['idValidate'] = row[9]
            policyMap['stature'] = row[10]
            policyMap['avoirdopois'] = row[11]
            policyMap['marriage'] = row[12]
            policyMap['nativePlace'] = row[13]
            policyMap['degree'] = row[14]
            policyMap['salary'] = row[15]
            policyMap['occupation'] = row[16]
            policyMap['isSmoke'] = row[17]
            policyMap['hasSocialSecurity'] = row[18]
            policyMap['phone'] = row[19]
            policyMap['otherPhone'] = row[20]
            policyMap['email'] = row[21]
            policyMap['rgtAddress'] = row[22]
            policyMap['postalAddress'] = row[23]
            policyMap['customerLevel'] = row[24]
            policyMap['locale'] = row[25]
            policyMap['isDeleted'] = row[26]
            policyMap['createUser'] = row[27]
            policyMap['createDate'] = row[28]
            policyMap['updateUser'] = row[29]
            policyMap['updateDate'] = row[30]
            customerList.append(policyMap)
        return customerList

    #根据获得客户风险保额信息列表
    def getCustomerRiskAmntList(conditionStr):
        sqlStr = "select" + PolicyDao.customerRiskAmntBaseColumnList + " from customer_riskamnt"
        if conditionStr != None:
            sqlStr += conditionStr
        result = CaseBase.executeSql(sqlStr)
        customerRiskAmntList = []
        for row in result:
            policyMap = {}
            policyMap['customerRiskamntId'] = row[0]
            policyMap['customerCode'] = row[1]
            policyMap['prtno'] = row[2]
            policyMap['riskamntL'] = row[3]
            policyMap['riskamntD'] = row[4]
            policyMap['riskamntA'] = row[5]
            policyMap['riskamntBa'] = row[6]
            policyMap['riskamntLa'] = row[7]
            policyMap['locale'] = row[8]
            policyMap['isDeleted'] = row[9]
            policyMap['createUser'] = row[10]
            policyMap['createDate'] = row[11]
            policyMap['updateUser'] = row[12]
            policyMap['updateDate'] = row[13]
            customerRiskAmntList.append(policyMap)
        return customerRiskAmntList

    #根据获得组别信息列表
    def getGrpList(conditionStr):
        sqlStr = "select" + PolicyDao.grpBaseColumnList + " from grp"
        if conditionStr != None:
            sqlStr += conditionStr
        result = CaseBase.executeSql(sqlStr)
        grpList = []
        for row in result:
            policyMap = {}
            policyMap['grpId'] = row[0]
            policyMap['orderno'] = row[1]
            policyMap['grpno'] = row[2]
            policyMap['state'] = row[3]
            policyMap['cancelReason'] = row[4]
            policyMap['locale'] = row[5]
            policyMap['isDeleted'] = row[6]
            policyMap['createUser'] = row[7]
            policyMap['createDate'] = row[8]
            policyMap['updateUser'] = row[9]
            policyMap['updateDate'] = row[10]
            grpList.append(policyMap)
        return grpList

    #根据获得告知信息列表
    def getImpartInfoList(conditionStr):
        sqlStr = "select" + PolicyDao.impartInfoBaseColumnList + " from impartinfo"
        if conditionStr != None:
            sqlStr += conditionStr
        result = CaseBase.executeSql(sqlStr)
        impartInfoList = []
        for row in result:
            policyMap = {}
            policyMap['impartinfoId'] = row[0]
            policyMap['customerId'] = row[1]
            policyMap['version'] = row[2]
            policyMap['code'] = row[3]
            policyMap['param'] = row[4]
            policyMap['startDate'] = row[5]
            policyMap['endDate'] = row[6]
            policyMap['locale'] = row[7]
            policyMap['isDeleted'] = row[8]
            policyMap['createUser'] = row[9]
            policyMap['createDate'] = row[10]
            policyMap['updateUser'] = row[11]
            policyMap['updateDate'] = row[12]
            impartInfoList.append(policyMap)
        return impartInfoList

    #根据获得订单信息列表
    def getOrderList(conditionStr):
        sqlStr = "select" + PolicyDao.orderBaseColumnList + " from orderinfo"
        if conditionStr != None:
            sqlStr += conditionStr
        result = CaseBase.executeSql(sqlStr)
        orderList = []
        for row in result:
            policyMap = {}
            policyMap['orderinfoId'] = row[0]
            policyMap['orderno'] = row[1]
            policyMap['state'] = row[2]
            policyMap['chargeState'] = row[3]
            policyMap['chargeDate'] = row[4]
            policyMap['cancelReason'] = row[5]
            policyMap['appId'] = row[6]
            policyMap['locale'] = row[7]
            policyMap['isDeleted'] = row[8]
            policyMap['createUser'] = row[9]
            policyMap['createDate'] = row[10]
            policyMap['updateUser'] = row[11]
            policyMap['updateDate'] = row[12]
            policyMap['payPlatType'] = row[13]
            orderList.append(policyMap)
        return orderList
    
    #根据订单号、投保单号、用户编码获得保单列表
    def getPolicyList(conditionStr):
        sqlStr = "select" + PolicyDao.policyBaseColumnList + " from policy p "
        if conditionStr != None:
            sqlStr += conditionStr
        result = CaseBase.executeSql(sqlStr)
        policyList = []
        for row in result:
            policyMap = {}
            policyMap['policyId'] = row[0]
            policyMap['orderno'] = row[1]
            policyMap['grpno'] = row[2]
            policyMap['contno'] = row[3]
            policyMap['prtno'] = row[4]
            policyMap['managecom'] = row[5]
            policyMap['salechnl'] = row[6]
            policyMap['appntId'] = row[7]
            policyMap['appntCode'] = row[8]
            policyMap['insuredId'] = row[9]
            policyMap['insuredCode'] = row[10]
            policyMap['newBankCode'] = row[11]
            policyMap['newBankAccNo'] = row[12]
            policyMap['newAccName'] = row[13]
            policyMap['newPayMode'] = row[14]
            policyMap['bankCode'] = row[15]
            policyMap['bankAccNo'] = row[16]
            policyMap['accName'] = row[17]
            policyMap['sumPrem'] = row[18]
            policyMap['sumAmnt'] = row[19]
            policyMap['polApplyDate'] = row[20]
            policyMap['status'] = row[21]
            policyMap['appFlag'] = row[22]
            policyMap['uwFlag'] = row[23]
            policyMap['signDate'] = row[24]
            policyMap['signTime'] = row[25]
            policyMap['cvalidate'] = row[26]
            policyMap['polMode'] = row[27]
            policyMap['polGetDate'] = row[28]
            policyMap['polGetTime'] = row[29]
            policyMap['customGetPolDate'] = row[30]
            policyMap['saleMode'] = row[31]
            policyMap['subSaleMode'] = row[32]
            policyMap['hasCallback'] = row[33]
            policyMap['immediateCallback'] = row[34]
            policyMap['relaPrtNo'] = row[35]
            policyMap['needEmailSubscribe'] = row[36]
            policyMap['needRule'] = row[37]
            policyMap['needDr'] = row[38]
            policyMap['needBnf'] = row[39]
            policyMap['drDate'] = row[40]
            policyMap['uploadDate'] = row[41]
            policyMap['appId'] = row[42]
            policyMap['agentCode'] = row[43]
            policyMap['remark'] = row[44]
            policyMap['cancelReason'] = row[45]
            policyMap['locale'] = row[46]
            policyMap['isDeleted'] = row[47]
            policyMap['createUser'] = row[48]
            policyMap['createDate'] = row[49]
            policyMap['updateUser'] = row[50]
            policyMap['updateDate'] = row[51]
            policyMap['cvalidateFlag'] = row[52]
            policyList.append(policyMap)
        return policyList

    #根据获得代理人信息列表
    def getPolicyAgentRelationList(conditionStr):
        sqlStr = "select" + PolicyDao.policyAgentRelationBaseColumnList + " from policy_agent_relation"
        if conditionStr != None:
            sqlStr += conditionStr
        result = CaseBase.executeSql(sqlStr)
        policyAgentRelationList = []
        for row in result:
            policyMap = {}
            policyMap['policyAgentRelationId'] = row[0]
            policyMap['policyId'] = row[1]
            policyMap['orderno'] = row[2]
            policyMap['grpno'] = row[3]
            policyMap['prtno'] = row[4]
            policyMap['agentCode'] = row[5]
            policyMap['mainAgentFlag'] = row[6]
            policyMap['locale'] = row[7]
            policyMap['isDeleted'] = row[8]
            policyMap['createUser'] = row[9]
            policyMap['createDate'] = row[10]
            policyMap['updateUser'] = row[11]
            policyMap['updateDate'] = row[12]
            policyAgentRelationList.append(policyMap)
        return policyAgentRelationList

    #policyDutyGet
    def getPolicyDutyGet(conditionStr):
        sqlStr = "select" + PolicyDao.policyDutyGetBaseColumnList + " from policy_duty_get"
        if conditionStr != None:
            sqlStr += conditionStr
        result = CaseBase.executeSql(sqlStr)
        policyDutyGetList = []
        for row in result:
            policyMap = {}
            policyMap['policyDutyGetId'] = row[0]
            policyMap['policyRiskRelationId'] = row[1]
            policyMap['policyRiskDutyId'] = row[2]
            policyMap['orderno'] = row[3]
            policyMap['grpno'] = row[4]
            policyMap['contno'] = row[5]
            policyMap['prtno'] = row[6]
            policyMap['riskCode'] = row[7]
            policyMap['dutyCode'] = row[8]
            policyMap['getDutyCode'] = row[9]
            policyMap['getDutyKind'] = row[10]
            policyMap['insuredCode'] = row[11]
            policyMap['liveGetMode'] = row[12]
            policyMap['liveGetType'] = row[13]
            policyMap['getLimit'] = row[14]
            policyMap['getRate'] = row[15]
            policyMap['urgeGetFlag'] = row[16]
            policyMap['addRate'] = row[17]
            policyMap['canGet'] = row[18]
            policyMap['needAcc'] = row[19]
            policyMap['needCancelAcc'] = row[20]
            policyMap['standMoney'] = row[21]
            policyMap['actuGet'] = row[22]
            policyMap['sumMoney'] = row[23]
            policyMap['getIntv'] = row[24]
            policyMap['getToDate'] = row[25]
            policyMap['getStartDate'] = row[26]
            policyMap['getEndDate'] = row[27]
            policyMap['getEndState'] = row[28]
            policyMap['state'] = row[29]
            policyMap['locale'] = row[30]
            policyMap['isDeleted'] = row[31]
            policyMap['createUser'] = row[32]
            policyMap['createDate'] = row[33]
            policyMap['updateUser'] = row[34]
            policyMap['updateDate'] = row[35]
            policyDutyGetList.append(policyMap)
        return policyDutyGetList
    
    #policyDutyGetRela
    def getPolicyDutyGetRela(conditionStr):
        sqlStr = "select" + PolicyDao.policyDutyGetRelaBaseColumnList + " from policy_duty_get_rela"
        if conditionStr != None:
            sqlStr += conditionStr
        result = CaseBase.executeSql(sqlStr)
        policyDutyGetRelaList = []
        for row in result:
            policyMap = {}
            policyMap['policyDutyGetRelaId'] = row[0]
            policyMap['policyRiskRelationId'] = row[1]
            policyMap['policyRiskDutyId'] = row[2]
            policyMap['orderno'] = row[3]
            policyMap['grpno'] = row[4]
            policyMap['contno'] = row[5]
            policyMap['prtno'] = row[6]
            policyMap['riskCode'] = row[7]
            policyMap['dutyCode'] = row[8]
            policyMap['relaType'] = row[9]
            policyMap['getDutyCode'] = row[10]
            policyMap['relaCode'] = row[11]
            policyMap['relaName'] = row[12]
            policyMap['locale'] = row[13]
            policyMap['isDeleted'] = row[14]
            policyMap['createUser'] = row[15]
            policyMap['createDate'] = row[16]
            policyMap['updateUser'] = row[17]
            policyMap['updateDate'] = row[18]
            policyDutyGetRelaList.append(policyMap)
        return policyDutyGetRelaList

    #policyDutyPrem
    def getPolicyDutyPrem(conditionStr):
        sqlStr = "select" + PolicyDao.policyDutyPremBaseColumnList + " from policy_duty_prem"
        if conditionStr != None:
            sqlStr += conditionStr
        result = CaseBase.executeSql(sqlStr)
        policyDutyPremList = []
        for row in result:
            policyMap = {}
            policyMap['policyDutyPremId'] = row[0]
            policyMap['policyRiskRelationId'] = row[1]
            policyMap['policyRiskDutyId'] = row[2]
            policyMap['orderno'] = row[3]
            policyMap['grpno'] = row[4]
            policyMap['contno'] = row[5]
            policyMap['prtno'] = row[6]
            policyMap['riskCode'] = row[7]
            policyMap['dutyCode'] = row[8]
            policyMap['payPlanCode'] = row[9]
            policyMap['payPlanType'] = row[10]
            policyMap['appntType'] = row[11]
            policyMap['appntCode'] = row[12]
            policyMap['urgePayFlag'] = row[13]
            policyMap['needAcc'] = row[14]
            policyMap['payTimes'] = row[15]
            policyMap['payStartDate'] = row[16]
            policyMap['payEndDate'] = row[17]
            policyMap['payToDate'] = row[18]
            policyMap['prem'] = row[19]
            policyMap['standPrem'] = row[20]
            policyMap['sumPrem'] = row[21]
            policyMap['payIntv'] = row[22]
            policyMap['suppRiskScore'] = row[23]
            policyMap['freeFlag'] = row[24]
            policyMap['freeRate'] = row[25]
            policyMap['freeStartDate'] = row[26]
            policyMap['freeEndDate'] = row[27]
            policyMap['state'] = row[28]
            policyMap['addFeeDirect'] = row[29]
            policyMap['secInsuAddPoint'] = row[30]
            policyMap['addFeeWay'] = row[31]
            policyMap['locale'] = row[32]
            policyMap['isDeleted'] = row[33]
            policyMap['createUser'] = row[34]
            policyMap['createDate'] = row[35]
            policyMap['updateUser'] = row[36]
            policyMap['updateDate'] = row[37]
            policyDutyPremList.append(policyMap)
        return policyDutyPremList
    
    #policyDutyPremAcc
    def getPolicyDutyPremAcc(conditionStr):
        sqlStr = "select" + PolicyDao.policyDutyPremAccBaseColumnList + " from policy_duty_prem_acc"
        if conditionStr != None:
            sqlStr += conditionStr
        result = CaseBase.executeSql(sqlStr)
        policyDutyPremAccList = []
        for row in result:
            policyMap = {}
            policyMap['policyDutyPremAccId'] = row[0]
            policyMap['policyDutyPremId'] = row[1]
            policyMap['orderno'] = row[2]
            policyMap['grpno'] = row[3]
            policyMap['contno'] = row[4]
            policyMap['prtno'] = row[5]
            policyMap['riskCode'] = row[6]
            policyMap['dutyCode'] = row[7]
            policyMap['payPlanCode'] = row[8]
            policyMap['insuAccNo'] = row[9]
            policyMap['rate'] = row[10]
            policyMap['newFlag'] = row[11]
            policyMap['calFlag'] = row[12]
            policyMap['locale'] = row[13]
            policyMap['isDeleted'] = row[14]
            policyMap['createUser'] = row[15]
            policyMap['createDate'] = row[16]
            policyMap['updateUser'] = row[17]
            policyMap['updateDate'] = row[18]
            policyDutyPremAccList.append(policyMap)
        return policyDutyPremAccList
    
    #根据获得文件信息列表
    def getPolicyFileList(conditionStr):
        sqlStr = "select" + PolicyDao.policyFileBaseColumnList + " from policy_file"
        if conditionStr != None:
            sqlStr += conditionStr
        result = CaseBase.executeSql(sqlStr)
        policyFileList = []
        for row in result:
            policyMap = {}
            policyMap['policyFileId'] = row[0]
            policyMap['policyId'] = row[1]
            policyMap['orderno'] = row[2]
            policyMap['grpno'] = row[3]
            policyMap['prtno'] = row[4]
            policyMap['fileType'] = row[5]
            policyMap['fileId'] = row[6]
            policyMap['fileUrl'] = row[7]
            policyMap['fileOrder'] = row[8]
            policyMap['locale'] = row[9]
            policyMap['isDeleted'] = row[10]
            policyMap['createUser'] = row[11]
            policyMap['createDate'] = row[12]
            policyMap['updateUser'] = row[13]
            policyMap['updateDate'] = row[14]
            policyFileList.append(policyMap)
        return policyFileList

    #根据获得被保人信息列表
    def getPolicyInsuredRelationList(conditionStr):
        sqlStr = "select" + PolicyDao.policyInsuredRelationBaseColumnList + " from policy_insured_relation"
        if conditionStr != None:
            sqlStr += conditionStr
        result = CaseBase.executeSql(sqlStr)
        policyInsuredRelationList = []
        for row in result:
            policyMap = {}
            policyMap['policyInsuredRelationId'] = row[0]
            policyMap['policyId'] = row[1]
            policyMap['orderno'] = row[2]
            policyMap['grpno'] = row[3]
            policyMap['prtno'] = row[4]
            policyMap['customerId'] = row[5]
            policyMap['customerCode'] = row[6]
            policyMap['relationToMainInsured'] = row[7]
            policyMap['relationToAppnt'] = row[8]
            policyMap['insuredType'] = row[9]
            policyMap['locale'] = row[10]
            policyMap['isDeleted'] = row[11]
            policyMap['createUser'] = row[12]
            policyMap['createDate'] = row[13]
            policyMap['updateUser'] = row[14]
            policyMap['updateDate'] = row[15]
            policyInsuredRelationList.append(policyMap)
        return policyInsuredRelationList

    #根据获得险种信息列表
    def getPolicyRiskDataList(conditionStr):
        sqlStr = "select " + PolicyDao.policyRiskDataBaseColumnList + " from policy_risk_data"
        if conditionStr != None:
            sqlStr += conditionStr
        result = CaseBase.executeSql(sqlStr)
        policyRiskDataList = []
        for row in result:
            policyMap = {}
            policyMap['policyRiskDataId'] = row[0]
            policyMap['policyId'] = row[1]
            policyMap['orderno'] = row[2]
            policyMap['grpno'] = row[3]
            policyMap['prtno'] = row[4]
            policyMap['riskCode'] = row[5]
            policyMap['relaType'] = row[6]
            policyMap['code'] = row[7]
            policyMap['name'] = row[8]
            policyMap['locale'] = row[9]
            policyMap['isDeleted'] = row[10]
            policyMap['createUser'] = row[11]
            policyMap['createDate'] = row[12]
            policyMap['updateUser'] = row[13]
            policyMap['updateDate'] = row[14]
            policyRiskDataList.append(policyMap)
        return policyRiskDataList

    #policyRiskDuty
    def getPolicyRiskDutyList(conditionStr):
        sqlStr = "select " + PolicyDao.policyRiskDutyBaseColumnList + " from policy_risk_duty"
        if conditionStr != None:
            sqlStr += conditionStr
        result = CaseBase.executeSql(sqlStr)
        policyRiskDutyList = []
        for row in result:
            policyMap = {}
            policyMap['policyRiskDutyId'] = row[0]
            policyMap['policyRiskRelationId'] = row[1]
            policyMap['orderno'] = row[2]
            policyMap['grpno'] = row[3]
            policyMap['contno'] = row[4]
            policyMap['prtno'] = row[5]
            policyMap['riskCode'] = row[6]
            policyMap['dutyCode'] = row[7]
            policyMap['dutyName'] = row[8]
            policyMap['mult'] = row[9]
            policyMap['standPrem'] = row[10]
            policyMap['prem'] = row[11]
            policyMap['sumPrem'] = row[12]
            policyMap['amnt'] = row[13]
            policyMap['riskAmnt'] = row[14]
            policyMap['payIntv'] = row[15]
            policyMap['cvalidate'] = row[16]
            policyMap['payYears'] = row[17]
            policyMap['years'] = row[18]
            policyMap['payEndDate'] = row[19]
            policyMap['payToDate'] = row[20]
            policyMap['floatRate'] = row[21]
            policyMap['firstPayDate'] = row[22]
            policyMap['getYear'] = row[23]
            policyMap['getYearFlag'] = row[24]
            policyMap['payEndYear'] = row[25]
            policyMap['payEndYearFlag'] = row[26]
            policyMap['insuYear'] = row[27]
            policyMap['insuYearFlag'] = row[28]
            policyMap['endDate'] = row[29]
            policyMap['getStartDate'] = row[30]
            policyMap['getStartType'] = row[31]
            policyMap['liveGetMode'] = row[32]
            policyMap['bonusGetMode'] = row[33]
            policyMap['clmGetIntv'] = row[34]
            policyMap['hasSocialSecurity'] = row[35]
            policyMap['peakLine'] = row[36]
            policyMap['getLimit'] = row[37]
            policyMap['getRate'] = row[38]
            policyMap['calRule'] = row[39]
            policyMap['calMode'] = row[40]
            policyMap['locale'] = row[41]
            policyMap['isDeleted'] = row[42]
            policyMap['createUser'] = row[43]
            policyMap['createDate'] = row[44]
            policyMap['updateUser'] = row[45]
            policyMap['updateDate'] = row[46]
            policyRiskDutyList.append(policyMap)
        return policyRiskDutyList

    #根据获得险种信息列表
    def getPolicyRiskRelationList(conditionStr):
        sqlStr = "select" + PolicyDao.policyRiskRelationBaseColumnList + " from policy_risk_relation"
        if conditionStr != None:
            sqlStr += conditionStr
        result = CaseBase.executeSql(sqlStr)
        policyRiskRelationList = []
        for row in result:
            policyMap = {}
            policyMap['policyRiskRelationId'] = row[0]
            policyMap['policyId'] = row[1]
            policyMap['orderno'] = row[2]
            policyMap['grpno'] = row[3]
            policyMap['prtno'] = row[4]
            policyMap['riskCode'] = row[5]
            policyMap['riskName'] = row[6]
            policyMap['subRiskFlag'] = row[7]
            policyMap['cvalidate'] = row[8]
            policyMap['amnt'] = row[9]
            policyMap['prem'] = row[10]
            policyMap['mult'] = row[11]
            policyMap['payIntv'] = row[12]
            policyMap['payEndYear'] = row[13]
            policyMap['payEndYearFlag'] = row[14]
            policyMap['insuYear'] = row[15]
            policyMap['insuYearFlag'] = row[16]
            policyMap['getYear'] = row[17]
            policyMap['getYearFlag'] = row[18]
            policyMap['getRate'] = row[19]
            policyMap['accAutoPayFlag'] = row[20]
            policyMap['autoPayFlag'] = row[21]
            policyMap['bonusGetMode'] = row[22]
            policyMap['clmGetIntv'] = row[23]
            policyMap['liveGetMode'] = row[24]
            policyMap['renewFlag'] = row[25]
            policyMap['appFlag'] = row[26]
            policyMap['invalidFlag'] = row[27]
            policyMap['locale'] = row[28]
            policyMap['isDeleted'] = row[29]
            policyMap['createUser'] = row[30]
            policyMap['createDate'] = row[31]
            policyMap['updateUser'] = row[32]
            policyMap['updateDate'] = row[33]
            policyRiskRelationList.append(policyMap)
        return policyRiskRelationList

    #根据获得服务信息列表
    def getPolicyServiceList(conditionStr):
        sqlStr = "select" + PolicyDao.policyServiceBaseColumnList + " from policy_service"
        if conditionStr != None:
            sqlStr += conditionStr
        result = CaseBase.executeSql(sqlStr)
        policyServiceList = []
        for row in result:
            policyServiceMap = {}
            policyServiceMap['policyServiceId'] = row[0]
            policyServiceMap['policyId'] = row[1]
            policyServiceMap['orderno'] = row[2]
            policyServiceMap['grpno'] = row[3]
            policyServiceMap['prtno'] = row[4]
            policyServiceMap['serviceCode'] = row[5]
            policyServiceMap['serviceType'] = row[6]
            policyServiceMap['serviceOrder'] = row[7]
            policyServiceMap['sendName'] = row[8]
            policyServiceMap['sendDay'] = row[9]
            policyServiceMap['sendDayType'] = row[10]
            policyServiceMap['sendWay'] = row[11]
            policyServiceMap['addressId'] = row[12]
            policyServiceMap['email'] = row[13]
            policyServiceMap['mobile'] = row[14]
            policyServiceMap['locale'] = row[15]
            policyServiceMap['isDeleted'] = row[16]
            policyServiceMap['createUser'] = row[17]
            policyServiceMap['createDate'] = row[18]
            policyServiceMap['updateUser'] = row[19]
            policyServiceMap['updateDate'] = row[20]
            policyServiceList.append(policyServiceMap)
        return policyServiceList

    def selectBnfInfoDto(prtno):
        sqlStr = """
                select
                c.name,c.sex,
                DATE_FORMAT(c.birthday, '%Y-%m-%d') AS birthday,
                c.id_type as idType,c.id_no as idNo,
                DATE_FORMAT(c.id_validate, '%Y-%m-%d') AS idValidate,
                b.bnfgrade as grade,
                b.bnflot as proportion,
                b.relationtoinsured as relationToInsured,
                b.type as type
                from
                customer c,bnt b
                where
                c.customer_id = b.customer_id
                and b.prtno = '""" + prtno+"'"
        result = CaseBase.executeSql(sqlStr)
        resultList = []
        for row in result:
            bnfInfoDto= {}
            bnfInfoDto['name'] = row[0]
            bnfInfoDto['sex'] = row[1]
            bnfInfoDto['birthday'] = row[2]
            bnfInfoDto['idType'] = row[3]
            bnfInfoDto['idNo'] = row[4]
            bnfInfoDto['idValidate'] = row[5]
            bnfInfoDto['grade'] = row[6]
            bnfInfoDto['proportion'] = row[7]
            bnfInfoDto['relationToInsured'] = row[8]
            bnfInfoDto['type'] = row[9]
            resultList.append(bnfInfoDto)
        return resultList
    
    def selectSumByPrtNo(prtno):
        sqlStr = """SELECT
            (IFNULL(sum(
                CASE r.pay_intv
                WHEN 0 THEN
                    r.prem
                WHEN 1 THEN
                    r.prem * 12 * (
                        CASE r.pay_end_year_flag
                        WHEN 'Y' THEN
                            r.pay_end_year
                        WHEN 'A' THEN
                            (r.pay_end_year +1 - TIMESTAMPDIFF(YEAR, c.birthday, CURDATE()))
                        END
                    )
                WHEN 3 THEN
                    r.prem * 4 * (
                        CASE r.pay_end_year_flag
                        WHEN 'Y' THEN
                            r.pay_end_year
                        WHEN 'A' THEN
                            (r.pay_end_year +1 - TIMESTAMPDIFF(YEAR,
                            c.birthday, CURDATE()))
                        END
                    )
                WHEN 6 THEN
                    r.prem * 2 * (
                        CASE r.pay_end_year_flag
                        WHEN 'Y' THEN
                            r.pay_end_year
                        WHEN 'A' THEN
                            (r.pay_end_year +1 - TIMESTAMPDIFF(YEAR, c.birthday, CURDATE()))
                        END
                    )
                WHEN 12 THEN
                (
                    CASE r.insu_year
                    WHEN 1000 THEN
                        r.prem
                    ELSE
                        r.prem * (
                            CASE r.pay_end_year_flag
                            WHEN 'Y' THEN
                                r.pay_end_year
                            WHEN 'A' THEN
                                (r.pay_end_year +1 - TIMESTAMPDIFF(YEAR, c.birthday, CURDATE()))
                            END
                        )
                    END
                )
                END
            ), 0)) AS tftPrem
        FROM
            policy_risk_relation r,
            policy p,
            customer c
        WHERE
            p.prtno = r.prtno
            AND p.appnt_id = c.customer_id
            AND r.app_flag = '0'
            AND p.salechnl IN ('1', '3', '5', '6')
            AND p.new_pay_mode NOT IN ('1', '9')
            AND P.prtno = '%s'"""%prtno
        result = CaseBase.executeSql(sqlStr)
        return result[0][0]

    def selectSumByAppntCode(appntCode, prtno):
        sqlStr = """
        SELECT
            (IFNULL(sum(
                CASE r.pay_intv
                WHEN 0 THEN
                    r.prem
                WHEN 1 THEN
                    r.prem * 12 * (
                        CASE r.pay_end_year_flag
                        WHEN 'Y' THEN
                            r.pay_end_year
                        WHEN 'A' THEN
                            (r.pay_end_year +1 - TIMESTAMPDIFF(YEAR, c.birthday, CURDATE()))
                        END
                    )
                WHEN 3 THEN
                    r.prem * 4 * (
                        CASE r.pay_end_year_flag
                        WHEN 'Y' THEN
                            r.pay_end_year
                        WHEN 'A' THEN
                            (r.pay_end_year +1 - TIMESTAMPDIFF(YEAR, c.birthday, CURDATE()))
                        END
                    )
                WHEN 6 THEN
                    r.prem * 2 * (
                        CASE r.pay_end_year_flag
                        WHEN 'Y' THEN
                            r.pay_end_year
                        WHEN 'A' THEN
                            (r.pay_end_year +1 - TIMESTAMPDIFF(YEAR, c.birthday, CURDATE()))
                        END
                    )
                WHEN 12 THEN
                    (CASE r.insu_year
                    WHEN 1000 THEN
                        r.prem
                    ELSE
                        r.prem * (
                            CASE r.pay_end_year_flag
                            WHEN 'Y' THEN
                                r.pay_end_year
                            WHEN 'A' THEN
                                (r.pay_end_year +1 - TIMESTAMPDIFF(YEAR, c.birthday, CURDATE()))
                            END
                        )
                    END
                    )
                END
                ), 0)) AS tftPrem
        FROM
            policy_risk_relation r,
            policy p,
            customer c
        WHERE
            p.prtno = r.prtno
            AND    p.appnt_id = c.customer_id
            AND r.app_flag = '1'
            AND    p.salechnl IN ('1', '3', '5', '6')
            AND p.new_pay_mode NOT IN ('1', '9')
            AND p.appnt_code = '%s'
            AND P.prtno !='%s' """%(appntCode, prtno)
        result = CaseBase.executeSql(sqlStr)
        return result[0][0]
    
    #双录
    def getNeedDouble(managecom, riskInfoList, appntBirth):
            needDouble = "N"
            doubleFlag = False
            for riskInfoDto in riskInfoList:
                count = int(riskInfoDto.get('insuYear', '0'))
                if count > 1:
                    doubleFlag = True
            if doubleFlag:
                if managecom != None and managecom != '':
                    if managecom[0: 4] == '8688':
                        needDouble = 'Y'
                    else:
                        age = PolicyDao.calInterval(appntBirth ,time.strftime('%Y-%m-%d', time.localtime()))
                        if age >= 60:
                            needDouble = 'Y'
            return needDouble
    
    #被保人-保单详情-其他关系首期本单保费金额+首期已承保保费金额 >= 200000,需要收集首期为转账的已承保保费金额
    def getNeedBnfDocument(bnfInfoList, policy):
        needBnfDocument = 'N'
        prtno = policy['prtno']
        directRel = ['00', '01', '02']
        for bnfInfo in bnfInfoList:
            directRel.append(bnfInfo.get('relationToInsured', None))
        directRel = set(directRel)
        if len(directRel) != 3:
            prem = PolicyDao.selectSumByPrtNo(prtno)
            sumtftPrem = PolicyDao.selectSumByAppntCode(policy['appntCode'], policy['prtno'])
            if Decimal(prem + sumtftPrem) >= Decimal(200000):
                needBnfDocument = 'Y'
        return needBnfDocument

    def calInterval(startDate, endDate):
        sDate = time.strptime(startDate,"%Y-%m-%d")
        sYears = sDate[0]
        sMonths = sDate[1]
        sDays = sDate[2]
        eDate= time.strptime(endDate,"%Y-%m-%d")
        eYears = eDate[0]
        eMonths = eDate[1]
        eDays = eDate[2]
        interval = eYears - sYears
        if (eMonths < sMonths):
           interval = interval - 1
        else :
            if (eMonths == sMonths and eDays < sDays):  
                interval = interval - 1
                if (eMonths == 2):
                    if ((sYears % 4 == 0) and (eYears % 4 != 0)):
                        if (eDays == 28):
                            interval = interval + 1
        return interval
    
    
    #入参paramDict可包含项: (带*必传)
#         orderState(默认'0')
#         orderChargeState(默认null)
#         grpState(默认'0')
#         policyStatus(默认'0')
#         policyAppFlag(默认'0')
#         policyUWFlag(默认null)*
#         policyPolApplyDate(默认'2018-03-22 12:00:00')
#         policyUploadDate(默认null)
#         updateDate(传'now'为当前时间)
#         policyRisk[
#                {riskCode, riskName, subRiskFlag, amnt, prem, mult, payIntv, payEndYear, payEndYearFlag, insuYear, insuYearFlag, getYear, getYearFlag, getRate, renewFlag, appFlag,
#                  'policyRiskDuty':[{
#                       riskCode, dutyCode, dutyName, mult, standPrem, prem, sumPrem, amnt, riskAmnt, payIntv, cvalidate, payYears, years, payEndDate, payToDate, floatRate, firstPayDate, getYear, getYearFlag, payEndYear, payEndYearFlag, insuYear, insuYearFlag, endDate, getStartDate, getStartType, liveGetMode, bonusGetMode, clmGetIntv, hasSocialSecurity, peakLine, getLimit, getRate, calRule, calMode,
#                       'policyDutyGet':[
#                          {riskCode, dutyCode, getDutyCode, getDutyKind, insuredCode, liveGetMode, liveGetType, getLimit, getRate, urgeGetFlag, addRate, canGet, needAcc, needCancelAcc, standMoney, actuGet, sumMoney, getIntv, getToDate, getStartDate, getEndDate, getEndState, state}
#                       ],
#                       'policyDutyGetRela':[
#                          {riskCode, dutyCode, relaType, getDutyCode, relaCode, relaName}
#                       ],
#                       'policyDutyPrem':[
#                          {riskCode, dutyCode, payPlanCode, payPlanType, appntType, appntCode, urgePayFlag, needAcc, payTimes, payStartDate, payEndDate, payToDate, prem, standPrem, sumPrem, payIntv, suppRiskScore, freeFlag, freeRate, freeStartDate, freeEndDate, state, addFeeDirect, secInsuAddPoint, addFeeWay,
#                         'policyDutyPremAcc':[
#                            {riskCode, dutyCode, payPlanCode, insuAccNo, rate, newFlag, calFlag}
#                         ]}
#                       ]
#                     }]
#                 }
#             ](默认1029)
#         riskData[{riskCode, relaType, code, name}] (默认有一条1032)
#         customerRiskAmnt[{customerCode, riskamnt_l, riskamnt_d, riskamnt_a,
#              riskamnt_ba, riskamnt_la}] (默认无数据)
#         file[{fileType,fileUrl}] (默认fileType:1096)
#         orderno （默认'2123456789012345678')
#         grpno (默认'1200000000000')
#         contno (默认'0000030000000000')
#         prtno (默认'1320180000001000')
#         appntCode (默认'800000000200')
#         insuredCode (默认'800000000201')
#         bnfCode (默认'800000000202')
#         agentCode (默认'4880000002')
#         newBankCode, (默认'07')
#         newBankAccNo, (默认'8634123456789012309')
#         newAccName, (账户名，默认'巴麻美')
#         newPayMode (默认'R')
#         haveAppntCustomer(默认有一条投保人；传'n'则不插入投保人，appntId为0)
#         haveInsuredCustomer(默认有一条被保人；传'n'则不插入被保人，insuredId为0)
#         orderChargeDate(默认'null')
#         appntName(投保人名字,默认'巴麻美')
#         appntBirth(默认'1989-10-11')
#         insuredBirth(默认'1989-10-11')
#         policyCvalidate(默认'2001-10-10 06:20:22')
#         policyNeedDr(默认'N')
#         managecom(默认'86150101')(重庆机构managecom '86500101',agentCode ='8500100001' "file":[{"fileType":"1076"}] ；)
#         relaPrtNo(默认为空)
#         hasCallback(默认为null)
#         immediateCallback(默认为null)
#         policyDrDate(默认为null)
#       20180424新增：
#         orderPayPlatType(默认为null)
#         (riskRelation中增加riskName,subRiskFlag，责任表数据)
#         insuredRelationToAppnt(默认03)
#         policyCvalidateFlag(默认为N)
#       20180515
#         policySaleMode
#         policySubSaleMode
    def insertTestData(inputParam):
        paramDict = deepcopy(inputParam)
        #初始化入参
        if paramDict.get('updateDate', None) == 'now':
            paramDict['updateDate'] = time.strftime("%Y-%m-%d %H:%M:%S", time.localtime())
#         orderState
        if paramDict.get('orderState', 'nothing') != 'nothing' and paramDict['orderState'] != None and paramDict['orderState'] != 'null':
            paramDict['orderState'] = "'" + paramDict['orderState'] + "'"
#         orderChargeState
        if paramDict.get('orderChargeState', 'nothing') != 'nothing' and paramDict['orderChargeState'] != None and paramDict['orderChargeState'] != 'null':
            paramDict['orderChargeState'] = "'" + paramDict['orderChargeState'] + "'"
#         orderChargeDate
        if paramDict.get('orderChargeDate', 'nothing') != 'nothing' and paramDict['orderChargeDate'] != None and paramDict['orderChargeDate'] != 'null':
            paramDict['orderChargeDate'] = "'" + paramDict['orderChargeDate'] + "'"
#         orderPayPlatType
        if paramDict.get('orderPayPlatType', 'nothing') != 'nothing' and paramDict['orderPayPlatType'] != None:
            paramDict['orderPayPlatType'] = "'" + paramDict['orderPayPlatType'] + "'"
#         grpState
        if paramDict.get('grpState', 'nothing') != 'nothing' and paramDict['grpState'] != None and paramDict['grpState'] != 'null':
            paramDict['grpState'] = "'" + paramDict['grpState'] + "'"
#         policyStatus
        if paramDict.get('policyStatus', 'nothing') != 'nothing' and paramDict['policyStatus'] != None and paramDict['policyStatus'] != 'null':
            paramDict['policyStatus'] = "'" + paramDict['policyStatus'] + "'"
#         policyAppFlag
        if paramDict.get('policyAppFlag', 'nothing') != 'nothing' and paramDict['policyAppFlag'] != None and paramDict['policyAppFlag'] != 'null':
            paramDict['policyAppFlag'] = "'" + paramDict['policyAppFlag'] + "'"
#         policyUWFlag
        if paramDict.get('policyUWFlag', 'nothing') != 'nothing' and paramDict['policyUWFlag'] != None and paramDict['policyUWFlag'] != 'null':
            paramDict['policyUWFlag'] = "'" + paramDict['policyUWFlag'] + "'"
#         policyUploadDate
        if paramDict.get('policyUploadDate', 'nothing') != 'nothing':
            paramDict['policyUploadDate'] = "'" + paramDict['policyUploadDate'] + "'"
#         policyCvalidate
        if paramDict.get('policyCvalidate', 'nothing') != 'nothing' and paramDict['policyCvalidate'] != None:
            paramDict['policyCvalidate'] = "'" + paramDict['policyCvalidate'] + "'"
#         relaPrtNo
        if paramDict.get('relaPrtNo', 'nothing') != 'nothing' and paramDict['relaPrtNo'] != None:
            paramDict['relaPrtNo'] = "'" + paramDict['relaPrtNo'] + "'"
#         hasCallback
        if paramDict.get('hasCallback', 'nothing') != 'nothing' and paramDict['hasCallback'] != None:
            paramDict['hasCallback'] = "'" + paramDict['hasCallback'] + "'"
#         immediateCallback
        if paramDict.get('immediateCallback', 'nothing') != 'nothing' and paramDict['immediateCallback'] != None:
            paramDict['immediateCallback'] = "'" + paramDict['immediateCallback'] + "'"
#         drDate
        if paramDict.get('policyDrDate', 'nothing') != 'nothing' and paramDict['policyDrDate'] != None:
            paramDict['policyDrDate'] = "'" + paramDict['policyDrDate'] + "'"
        sumPrem = Decimal(0)
        sumAmnt = Decimal(0)
        if paramDict.get('policyRisk', 'nothing') != 'nothing':
            for riskRelation in paramDict['policyRisk']:
        #         amnt
                if riskRelation.get('amnt', 'nothing') != 'nothing' and riskRelation['amnt'] != None:
                    sumAmnt = sumAmnt + Decimal(riskRelation['amnt'])
                    riskRelation['amnt'] = "'" + riskRelation['amnt'] + "'"
        #         prem
                if riskRelation.get('prem', 'nothing') != 'nothing' and riskRelation['prem'] != None:
                    sumPrem = sumPrem + Decimal(riskRelation['prem'])
                    riskRelation['prem'] = "'" + riskRelation['prem'] + "'"
        #         mult
                if riskRelation.get('mult', 'nothing') != 'nothing' and riskRelation['mult'] != None:
                    riskRelation['mult'] = "'" + riskRelation['mult'] + "'"
        #         payIntv
                if riskRelation.get('payIntv', 'nothing') != 'nothing' and riskRelation['payIntv'] != None:
                    riskRelation['payIntv'] = "'" + riskRelation['payIntv'] + "'"
        #         payEndYear
                if riskRelation.get('payEndYear', 'nothing') != 'nothing' and riskRelation['payEndYear'] != None:
                    riskRelation['payEndYear'] = "'" + riskRelation['payEndYear'] + "'"
        #         payEndYearFlag
                if riskRelation.get('payEndYearFlag', 'nothing') != 'nothing' and riskRelation['payEndYearFlag'] != None:
                    riskRelation['payEndYearFlag'] = "'" + riskRelation['payEndYearFlag'] + "'"
        #         insuYear
                if riskRelation.get('insuYear', 'nothing') != 'nothing' and riskRelation['insuYear'] != None:
                    riskRelation['insuYear'] = "'" + riskRelation['insuYear'] + "'"
        #         insuYearFlag
                if riskRelation.get('insuYearFlag', 'nothing') != 'nothing' and riskRelation['insuYearFlag'] != None:
                    riskRelation['insuYearFlag'] = "'" + riskRelation['insuYearFlag'] + "'"
        #         getYear
                if riskRelation.get('getYear', 'nothing') != 'nothing' and riskRelation['getYear'] != None:
                    riskRelation['getYear'] = "'" + riskRelation['getYear'] + "'"
        #         getYearFlag
                if riskRelation.get('getYearFlag', 'nothing') != 'nothing' and riskRelation['getYearFlag'] != None:
                    riskRelation['getYearFlag'] = "'" + riskRelation['getYearFlag'] + "'"
        #         getRate
                if riskRelation.get('getRate', 'nothing') != 'nothing' and riskRelation['getRate'] != None:
                    riskRelation['getRate'] = "'" + riskRelation['getRate'] + "'"
        else:
            sumPrem = Decimal(50000)
            sumAmnt = Decimal(100000)
        #sql插入语句
        #订单，待填：orderno
        orderSql = "INSERT INTO orderinfo \
                    (orderno, state, charge_state, charge_date, cancel_reason, app_id, locale, is_deleted, create_user, create_date, update_user, update_date, pay_plat_type) \
                    VALUES \
                    ('%s', " + paramDict.get('orderState', '0') + ", " + paramDict.get('orderChargeState', 'null') + ", " + paramDict.get('orderChargeDate', 'null') + ", null, null, 'zh_CN', 'n', null, '2018-03-20 18:19:25', null, null, " + paramDict.get('orderPayPlatType', 'null') + ")"
        #组别，待填：orderno, grpno
        grpSql = "INSERT INTO grp \
                    (orderno, grpno, state, cancel_reason, locale, is_deleted, create_user, create_date, update_user, update_date) \
                    VALUES \
                    ('%s', '%s', " + paramDict.get('grpState', '0') + ", null, 'zh_CN', 'n', null, '2018-03-20 18:19:26', null, null)"
        #保单，待填：orderno, grpno, contno, prtno, appntId, appntCode, status
        policySql = "INSERT INTO policy \
                        (orderno, grpno, contno, prtno, managecom, salechnl, appnt_id, appnt_code, insured_id, insured_code, new_bank_code, new_bank_acc_no, new_acc_name, new_pay_mode, bank_code, bank_acc_no, acc_name, sum_prem, sum_amnt, pol_apply_date, status, app_flag, uw_flag, sign_date, sign_time, cvalidate,cvalidate_flag, pol_mode, pol_get_date, pol_get_time, custom_get_pol_date, sale_mode, sub_sale_mode, has_callback, immediate_callback, rela_prt_no, need_email_subscribe, need_rule, need_dr, need_bnf, dr_date, upload_date, app_id, agent_code, remark, cancel_reason, locale, is_deleted, create_user, create_date, update_user, update_date) \
                        VALUES \
                        ('%s', '%s', '%s', '%s', '" + paramDict.get('managecom', '86150101') + "', '1', %d, '%s', %d, '%s', '%s', '%s', '%s', '%s', '07', '8634123456789012340', 'ws', '" + str(sumPrem) + "', '" + str(sumAmnt) + "', '" + paramDict.get('policyPolApplyDate', '2018-03-22 12:00:00') + "', " + paramDict.get('policyStatus', '0') +", " + paramDict.get('policyAppFlag', '0') + ", " + paramDict.get('policyUWFlag', 'null') + ", null, null, " + paramDict.get('policyCvalidate', 'null') + ",'" + paramDict.get('policyCvalidateFlag','N') +"','Y', null, null, null, '"+paramDict.get('policySaleMode','2')+"','"+paramDict.get("policySubSaleMode","M")+"', " + paramDict.get('hasCallback', 'null') + ", " + paramDict.get('immediateCallback', 'null') + ", " + paramDict.get('relaPrtNo', 'null') + ", 'N', 'Y', '" + paramDict.get('policyNeedDr', 'N') + "', null, " + paramDict.get('policyDrDate', 'null') + ", " + paramDict.get('policyUploadDate', 'null') + ", null, '%s', null, null, 'zh_CN', 'n', null, '2018-03-20 18:35:26', null, '" + paramDict.get('updateDate', '2018-03-22 12:00:00') + "')"
        #代理人，待填：policyId, orderno, grpno, prtno, agentCode
        agentSql = "INSERT INTO policy_agent_relation \
                    (policy_id, orderno, grpno, prtno, agent_code, main_agent_flag, locale, is_deleted, create_user, create_date, update_user, update_date) \
                    VALUES \
                    (%d, '%s', '%s', '%s', '%s', 'Y', 'zh_CN', 'n', null, '2018-03-20 18:19:26', null, null)"
        #服务，待填：policyId, orderno, grpno, prtno, addressId
        serviceSql = "INSERT INTO policy_service \
                    (policy_id, orderno, grpno, prtno, service_code, service_type, service_order, send_name, send_day, send_day_type, send_way, address_id, email, mobile, locale, is_deleted, create_user, create_date, update_user, update_date) \
                    VALUES \
                    (%d, '%s', '%s', '%s', 'LOVE', '1', '1', '1', '2010-10-10 00:00:00', '1', null, %d, 'email@xx.com', '18233582157', 'zh_CN', 'n', null, '2018-03-20 18:35:26', null, null)"
        #文件，待填：policyId, orderno, grpno, prtno
        fileSql = "INSERT INTO policy_file \
                    (policy_id, orderno, grpno, prtno, file_type, file_id, file_url, file_order, locale, is_deleted, create_user, create_date, update_user, update_date) \
                    VALUES \
                    (%d, '%s', '%s', '%s', '%s', '1', '%s', '1', 'zh_CN', 'n', null, '2018-03-20 18:35:26', null, '2018-03-20 18:35:26')"
        #重疾，待填：policyId, orderno, grpno, prtno
        riskDataSql = "INSERT INTO policy_risk_data \
                    (policy_id, orderno, grpno, prtno, risk_code, rela_type, code, name, locale, is_deleted, create_user, create_date, update_user, update_date) \
                    VALUES \
                    (%d, '%s', '%s', '%s', '%s', '%s', '%s', '%s', 'zh_CN', 'n', null, '2018-03-20 18:19:26', null, '2018-03-20 18:19:26')"
        #客户，待填：orderno, prtno, customerCode, name, rgt_address, postal_address
        customerSql = "INSERT INTO customer\
                        (orderno, prtno, customer_code, name, sex, id_type, id_no, birthday, id_validate, stature, avoirdopois, marriage, native_place, degree, salary, occupation, is_smoke, has_social_security, phone, other_phone, email, rgt_address, postal_address, customer_level, locale, is_deleted, create_user, create_date, update_user, update_date) \
                        VALUES \
                        ('%s', '%s', '%s', '%s', '%s', '1', '1', '%s', '2001-10-10', '168', '40', '1', 'CHN', '1', null, '2110102', '0', '1', '15600027603', '18233582157', 'anotherws@163', %s, %s, '1', 'zh_CN', 'n', null, '2018-03-20 18:19:26', null, '2018-03-20 18:19:26')"
        #风险保额                                                               (customer_code, prtno, sumriskamnt_l, sumriskamnt_d, sumriskamnt_a, sumriskamnt_ba, sumriskamnt_la, histsumriskamnt_l, diffstationsumriskamnt_l, diffstationsumriskamnt_d, diffstationsumriskamnt_a, sumtransferprem, locale, is_deleted, create_user, create_date, update_user, update_date)\
        customerRiskAmntSql = "insert into customer_riskamnt \
                                (customer_code, prtno, riskamnt_l, riskamnt_d, riskamnt_a, riskamnt_ba, riskamnt_la, locale, is_deleted, create_user, create_date, update_user, update_date)\
                                values \
                                ('%s','%s','%s','%s','%s','%s','%s','zh_CN','n',null,'2018-03-23 12:00:00',null,'" + paramDict.get('updateDate', '2018-03-23 12:00:00') + "')"
        #地址，待填：province, city, addressText, postalZipCode(自己加引号)
        addressSql = "INSERT INTO address \
                        (province, city, county, address_text, postal_zip_code, locale, is_deleted, create_user, create_date, update_user, update_date) \
                        VALUES \
                        ('%s', '%s', null, '%s', %s, 'zh_CN', 'n', null, '2018-03-20 18:19:26', null, '2018-03-20 18:19:26')"
        #告知，待填：customerId
        impartSql = "INSERT INTO impartinfo \
                        (customer_id, version, code, param, start_date, end_date, locale, is_deleted, create_user, create_date, update_user, update_date) \
                        VALUES \
                        (%d, '18', '001', '180,65', '2018-03-20', '9999-01-01', 'zh_CN', 'n', null, '2018-03-20 18:19:26', null, null)"
        #被保人关系，待填：policyId, orderno, grpno, prtno, customerId, customerCode
        insuredSql = "INSERT INTO policy_insured_relation \
                        (policy_id, orderno, grpno, prtno, customer_id, customer_code, relation_to_main_insured, relation_to_appnt, insured_type, locale, is_deleted, create_user, create_date, update_user, update_date) \
                        VALUES \
                        (%d, '%s', '%s', '%s', %d, '%s', null, '" + paramDict.get('insuredRelationToAppnt', '03') + "', '0', 'zh_CN', 'n', null, null, null, null)"
        #受益人，待填：policyId, orderno, grpno, prtno, customerId, customerCode
        bnfSql = "INSERT INTO bnt \
                    (policy_id, orderno, grpno, prtno, type, relationtoappnt, relationtoinsured, bnfgrade, bnflot, customer_id, customer_code, locale, is_deleted, create_user, create_date, update_user, update_date) \
                    VALUES \
                    (%d, '%s', '%s', '%s', '1', null, '01', '1', '1.0000', %d, '%s', 'zh_CN', 'n', null, null, null, null)"
        #险种关系，待填：policyId, orderno, grpno, prtno
        policyRiskSql = "INSERT INTO policy_risk_relation \
                        (policy_id, orderno, grpno, prtno, risk_code, risk_name, sub_risk_flag, cvalidate, amnt, prem, mult, pay_intv, pay_end_year, pay_end_year_flag, insu_year, insu_year_flag, get_year, get_year_flag, get_rate, acc_auto_pay_flag, auto_pay_flag, bonus_get_mode, clm_get_intv, live_get_mode, renew_flag, app_flag, invalid_flag, locale, is_deleted, create_user, create_date, update_user, update_date) \
                        VALUES \
                        (%d, '%s', '%s', '%s', '%s','%s', '%s', %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, 'N', 'Y', '1', '1', '1', '%s', '%s', null, 'zh_CN', 'n', null, '2018-03-20 18:19:26', null, '" + paramDict.get('updateDate', '2018-03-22 12:00:00') + "')"
        #riskDuty
        riskDutySql = "INSERT INTO policy_risk_duty \
                        (policy_risk_relation_id, orderno, grpno, contno, prtno, risk_code, duty_code, duty_name, mult, stand_prem, prem, sum_prem, amnt, risk_amnt, pay_intv, cvalidate, pay_years, years, pay_end_date, pay_to_date, float_rate, first_pay_date, get_year, get_year_flag, pay_end_year, pay_end_year_flag, insu_year, insu_year_flag, end_date, get_start_date, get_start_type, live_get_mode, bonus_get_mode, clm_get_intv, has_social_security, peak_line, get_limit, get_rate, cal_rule, cal_mode, locale, is_deleted, create_user, create_date, update_user, update_date) \
                        VALUES \
                        (%d, '%s', '%s', '%s', '%s', %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, 'zh_CN', 'n', null, '2018-03-20 12:00:00', null, '2018-03-22 12:00:00')"
        #dutyGet
        dutyGetSql = "INSERT INTO policy_duty_get \
                      (policy_risk_relation_id, policy_risk_duty_id, orderno, grpno, contno, prtno, risk_code, duty_code, get_duty_code, get_duty_kind, insured_code, live_get_mode, live_get_type, get_limit, get_rate, urge_get_flag, add_rate, can_get, need_acc, need_cancel_acc, stand_money, actu_get, sum_money, get_intv, get_to_date, get_start_date, get_end_date, get_end_state, state, locale, is_deleted, create_user, create_date, update_user, update_date) \
                      VALUES \
                      (%d, %d, '%s', '%s', '%s', '%s', %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s, 'zh_CN', 'n', null, '2018-03-20 12:00:00', null, '2018-03-22 12:00:00')"
        #dutyGetRela
        dutyGetRelaSql = "INSERT INTO policy_duty_get_rela \
                          (policy_risk_relation_id, policy_risk_duty_id, orderno, grpno, contno, prtno, risk_code, duty_code, rela_type, get_duty_code, rela_code, rela_name, locale, is_deleted, create_user, create_date, update_user, update_date) \
                          VALUES \
                          (%d, %d, '%s', '%s', '%s', '%s', %s, %s, %s, %s, %s, %s, 'zh_CN', 'n', null, '2018-03-20 12:00:00', null, '2018-03-22 12:00:00')"
        #DutyPrem
        dutyPremSql = "INSERT INTO policy_duty_prem \
                       (policy_risk_relation_id, policy_risk_duty_id, orderno, grpno, contno, prtno, risk_code, duty_code, pay_plan_code, pay_plan_type, appnt_type, appnt_code, urge_pay_flag, need_acc, pay_times, pay_start_date, pay_end_date, pay_to_date, prem, stand_prem, sum_prem, pay_intv, supp_risk_score, free_flag, free_rate, free_start_date, free_end_date, state, addFeeDirect, secInsuAddPoint, addFeeWay, locale, is_deleted, create_user, create_date, update_user, update_date) \
                       VALUES \
                       (%d, %d ,'%s' ,'%s' ,'%s' ,'%s' ,%s ,%s ,%s ,%s ,%s ,%s ,%s ,%s ,%s ,%s ,%s ,%s ,%s ,%s ,%s ,%s ,%s ,%s ,%s ,%s ,%s ,%s ,%s ,%s ,%s , 'zh_CN', 'n', null, '2018-03-20 12:00:00', null, '2018-03-22 12:00:00')"
        #dutyPremAcc
        dutyPremAccSql = "INSERT INTO policy_duty_prem_acc \
                          (policy_duty_prem_id, orderno, grpno, contno, prtno, risk_code, duty_code, pay_plan_code, insu_acc_no, rate, new_flag, cal_flag, locale, is_deleted, create_user, create_date, update_user, update_date) \
                          VALUES \
                          (%d, '%s', '%s', '%s', '%s', %s, %s, %s, %s, %s, %s, %s, 'zh_CN', 'n', null, '2018-03-20 12:00:00', null, '2018-03-22 12:00:00')"
        #消息队列
        mqSql = "insert into mq_push_detail \
                (push_module, push_type, push_data, push_time, push_state, receive_time, fail_count, locale, is_deleted, create_user, create_date, update_user, update_date) \
                values\
                (...)"
        #抽检
        samplingSql = "INSERT INTO sampling_ratio \
                        (risk_code, managecom, sampleratio, start_date, end_date, locale, is_deleted, create_user, create_date, update_user, update_date) \
                        VALUES \
                        ('5003', '862110', '0.15', '2018-01-10', '2018-04-10', 'zh_CN', 'n', null, '2018-03-05 10:42:29', null, '2018-03-05 10:49:37')"
        #管理机构
        policyConfigSwitchSql = "INSERT INTO policy_config_switch \
                                (managecom, switch_key, switch_status, note, locale, is_deleted, create_user, create_date, update_user, update_date) \
                                VALUES \
                                ('8613', 'doublerecordmanage', 'ON', '河北机构双录标识', 'zh_CN', 'n', null, null, null, null)"
        #定义部分参数1
        orderno = paramDict.get('orderno', '2123456789012345678')
        grpno = paramDict.get('grpno', '1200000000000')
        contno = paramDict.get('contno', '0000030000000000')
        prtno = paramDict.get('prtno', '1320180000001000')
#         customerCode = '80000000020000'
        appntCode = paramDict.get('appntCode', '800000000200')
        insuredCode = paramDict.get('insuredCode', '800000000201')
        bnfCode = paramDict.get('bnfCode', '800000000202')
        agentCode = paramDict.get('agentCode', '4880000002')
        #地址：province,city,addressText,postalZipCode（自己加引号）
        #客户：name
        #需要用到的主键
        policyId = 0
        appntId = 0
        insuredId = 0
        bnfId = 0
        rgtAddressId = 0
        postalAddressId = 0
        riskRelationId = 0
        #各个测试数据的主键，用于测试完成后删除。结构：{表名:[主键列表],...}
        delOrder = []
        delGrp = []
        delPolicy = []
        delCustomer = []
        delAddress = []
        delImpart = []
        delInsured = []
        delBnf = []
        delAgent = []
        delFile = []
        delService = []
        delRiskRelation = []
        delRiskData = []
        delCustomerRiskAmnt = []
        delMq = []
        delConfigSwitch = []
        delSampling = []
        delPolicyRiskDuty = []
        delPolicyDutyGet = []
        delPolicyDutyGetRela = []
        delPolicyDutyPrem = []
        delPolicyDutyPremAcc = []
        #开始插入
        try:
            #1 订单表
            did = CaseBase.executeInsertSql(orderSql%(orderno))
            delOrder.append(did)
            #2 组别表
            did = CaseBase.executeInsertSql(grpSql%(orderno, grpno))
            delGrp.append(did)
            if paramDict.get('haveAppntCustomer', None) != 'n':
                #3.1 投保人-通讯地址
                postalAddressId = CaseBase.executeInsertSql(addressSql%('210000', '210300', '210300', '"030003"'))
                delAddress.append(postalAddressId)
                #3.2 投保人-户籍地址
                rgtAddressId = CaseBase.executeInsertSql(addressSql%('210000', '210200', '210204', 'null'))
                delAddress.append(rgtAddressId)
                #3.3 投保人-客户表
                appntName = paramDict.get('appntName', '巴麻美')
                appntId = CaseBase.executeInsertSql(customerSql%(orderno, prtno, appntCode, appntName, '1', paramDict.get('appntBirth', '1991-02-01'), str(rgtAddressId), str(postalAddressId)))
                delCustomer.append(appntId)
                #3.4 投保人-告知信息
                did = CaseBase.executeInsertSql(impartSql%(appntId))
                delImpart.append(did)
            if paramDict.get('haveInsuredCustomer' ,None) != 'n':
                if insuredCode != appntCode:
                    #4.1 被保人-通讯地址
                    postalAddressId = CaseBase.executeInsertSql(addressSql%('130000', '130100', '130100', '"031003"'))
                    delAddress.append(postalAddressId)
                    #4.2 被保人-户籍地址
                    rgtAddressId = CaseBase.executeInsertSql(addressSql%('220000', '220100', '220100', 'null'))
                    delAddress.append(rgtAddressId)
                    #4.3 被保人-客户表
                    insuredName = '自来也'
                    insuredId = CaseBase.executeInsertSql(customerSql%(orderno, prtno, insuredCode, insuredName, '1', paramDict.get('insuredBirth', '1968-1-30'), str(rgtAddressId), str(postalAddressId)))
                    delCustomer.append(insuredId)
                    #4.4 被保人-告知信息
                    did = CaseBase.executeInsertSql(impartSql%(insuredId))
                    delImpart.append(did)
                else:
                    insuredId = appntId
            #4.5 保单表
            policyId = CaseBase.executeInsertSql(policySql%(orderno, grpno, contno, prtno, appntId, appntCode, insuredId, insuredCode, paramDict.get('newBankCode', '07'),
                        paramDict.get('newBankAccNo', '8634123456789012309'), paramDict.get('newAccName', '巴麻美'), paramDict.get('newPayMode', 'R'), agentCode))
            delPolicy.append(policyId)
            #4.6 被保人关系
            did = CaseBase.executeInsertSql(insuredSql%(policyId, orderno, grpno, prtno, insuredId, insuredCode))
            delInsured.append(did)
            if bnfCode != appntCode and bnfCode != insuredCode:
                #5.1 受益人-客户表
                bnfName = '李华'
                bnfId = CaseBase.executeInsertSql(customerSql%(orderno, prtno, bnfCode, bnfName, '0', '1990-03-20', 'null', 'null'))
                delCustomer.append(bnfId)
                #5.2 受益人-告知信息
                did = CaseBase.executeInsertSql(impartSql%(bnfId))
                delImpart.append(did)
                #5.3 受益人关系
                did = CaseBase.executeInsertSql(bnfSql%(policyId, orderno, grpno, prtno, bnfId, bnfCode))
                delBnf.append(did)
            elif bnfCode == appntCode:
                pass
            elif bnfCode == insuredCode:
                pass
            #6 代理人
            did = CaseBase.executeInsertSql(agentSql%(policyId, orderno, grpno, prtno, agentCode))
            delAgent.append(did)
            #7 文件
            if paramDict.get('file', None) != None:
                for fileObj in paramDict['file']:
                    did = CaseBase.executeInsertSql(fileSql%(policyId, orderno, grpno, prtno, fileObj.get('fileType', '1094'), fileObj.get('fileUrl', 'ContNo=00002172&ECCode=41C6FF91832FA4F1')))
                    delFile.append(did)
            else:
                did = CaseBase.executeInsertSql(fileSql%(policyId, orderno, grpno, prtno, '1999', paramDict.get('policyFileUrl', 'ContNo=00002172&ECCode=41C6FF91832FA4F1')))
                delFile.append(did)
            #8.1 服务-书信地址
            addressId = CaseBase.executeInsertSql(addressSql%('210000', '210300', '210304', 'null'))
            delAddress.append(addressId)
            #8.2 服务
            did = CaseBase.executeInsertSql(serviceSql%(policyId, orderno, grpno, prtno, addressId))
            delService.append(did)
            #9 风险保额
            if paramDict.get('customerRiskAmnt', None) != None:
                for customerRiskAmnt in paramDict['customerRiskAmnt']:
                    did = CaseBase.executeInsertSql(customerRiskAmntSql%(customerRiskAmnt.get('customerCode', insuredCode), prtno, customerRiskAmnt.get('riskamnt_l', '0'),
                        customerRiskAmnt.get('riskamnt_d', '50000'), customerRiskAmnt.get('riskamnt_a', '80000'), customerRiskAmnt.get('riskamnt_ba', '2000000'), customerRiskAmnt.get('riskamnt_la', '0')))
                    delCustomerRiskAmnt.append(did)
    #         else:
    #             did = CaseBase.executeInsertSql(customerRiskAmntSql%(insuredCode, prtno, ''))
    #             delCustomerRiskAmnt.append(did)
            #10 险种关联 
            if paramDict.get('policyRisk', None) != None:
                for policyRisk in paramDict['policyRisk']:
                    riskRelationId = CaseBase.executeInsertSql(policyRiskSql%(policyId, orderno, grpno, prtno, policyRisk.get('riskCode', '1029'), policyRisk.get('riskName', '险种名称'), policyRisk.get('subRiskFlag', 'M'), '"' + policyRisk.get('updateDate', '2018-03-22 12:00:00') + '"', 
                        policyRisk.get('amnt', '"100000"'), policyRisk.get('prem', '"50000"'), policyRisk.get('mult', 'null'), policyRisk.get('payIntv', '"12"'), policyRisk.get('payEndYear', '"3"'),
                          policyRisk.get('payEndYearFlag', '"Y"'), policyRisk.get('insuYear', '"30"'), policyRisk.get('insuYearFlag', '"Y"'), policyRisk.get('getYear', 'null'),
                           policyRisk.get('getYearFlag', 'null'), policyRisk.get('getRate', 'null'), policyRisk.get('renewFlag', 'N'), policyRisk.get('appFlag', '0')))
                    delRiskRelation.append(riskRelationId)
                    #+ policyRiskDuty policyDutyGet policyDutyGetRela policyDutyPrem policyDutyPremAcc
                    PolicyDao.addDuty(policyRisk, riskRelationId, orderno, grpno, contno, prtno, appntCode, insuredCode, riskDutySql, dutyGetSql, dutyGetRelaSql, dutyPremSql, dutyPremAccSql, delPolicyRiskDuty, delPolicyDutyGet, delPolicyDutyGetRela, delPolicyDutyPrem, delPolicyDutyPremAcc)
            else:
                riskRelationId = CaseBase.executeInsertSql(policyRiskSql%(policyId, orderno, grpno, prtno, '1029', '百年行天下两全保险', 'M', '"2018-03-22 12:00:00"', '"100000"', '"50000"', 'null', '"12"', '"3"', '"Y"', '"30"', '"Y"', 'null', 'null', 'null', 'N', '0'))
                delRiskRelation.append(riskRelationId)
                PolicyDao.addDuty({}, riskRelationId, orderno, grpno, contno, prtno, appntCode, insuredCode, riskDutySql, dutyGetSql, dutyGetRelaSql, dutyPremSql, dutyPremAccSql, delPolicyRiskDuty, delPolicyDutyGet, delPolicyDutyGetRela, delPolicyDutyPrem, delPolicyDutyPremAcc)
            #+ 重疾
            if paramDict.get('riskData', None) != None:
                for riskData in paramDict['riskData']:
                    did = CaseBase.executeInsertSql(riskDataSql%(policyId, orderno, grpno, prtno, riskData.get('riskCode', '5022'), riskData.get('relaType', '001'),
                             riskData.get('code', '00001'), riskData.get('name', '恶性肿瘤')))
                    delRiskData.append(did)
            
            #fin 提交操作
            CaseBase.commit()
            #整理待删除记录
            delDict = {
                'address': delAddress,
                'bnt': delBnf,
                'customer': delCustomer,
                'customer_riskamnt': delCustomerRiskAmnt,
                'grp': delGrp,
                'impartinfo': delImpart,
                'mq_push_detail': delMq,
                'orderinfo': delOrder,
                'policy': delPolicy,
                'policy_agent_relation': delAgent,
                'policy_config_switch': delConfigSwitch,
                'policy_file': delFile,
                'policy_insured_relation': delInsured,
                'policy_risk_data': delRiskData,
                'policy_risk_relation': delRiskRelation,
                'policy_service': delService,
                'policy_risk_duty': delPolicyRiskDuty,
                'policy_duty_get': delPolicyDutyGet,
                'policy_duty_get_rela': delPolicyDutyGetRela,
                'policy_duty_prem': delPolicyDutyPrem,
                'policy_duty_prem_acc': delPolicyDutyPremAcc,
                'sampling_ratio': delSampling,
                'orderno': orderno,
                'prtno': prtno,
                'isSuccess':'Y'
                }
        except BaseException as e:
            print(e.message)
            delDict = {
                'address': delAddress,
                'bnt': delBnf,
                'customer': delCustomer,
                'customer_riskamnt': delCustomerRiskAmnt,
                'grp': delGrp,
                'impartinfo': delImpart,
                'mq_push_detail': delMq,
                'orderinfo': delOrder,
                'policy': delPolicy,
                'policy_agent_relation': delAgent,
                'policy_config_switch': delConfigSwitch,
                'policy_file': delFile,
                'policy_insured_relation': delInsured,
                'policy_risk_data': delRiskData,
                'policy_risk_relation': delRiskRelation,
                'policy_service': delService,
                'policy_risk_duty': delPolicyRiskDuty,
                'policy_duty_get': delPolicyDutyGet,
                'policy_duty_get_rela': delPolicyDutyGetRela,
                'policy_duty_prem': delPolicyDutyPrem,
                'policy_duty_prem_acc': delPolicyDutyPremAcc,
                'sampling_ratio': delSampling,
                'orderno': orderno,
                'prtno': prtno,
                'isSuccess': e
                }
            CaseBase.rollBack()
        #返回待删除测试数据的主键记录
        return delDict
        
    def delTestData(delDict):
#         #订单
#         orderSql = "delete from orderinfo where orderinfo_id = %d"
#         #组别
#         grpSql = "delete from grp where grp_id = %d"
#         #保单
#         policySql = "delete from policy where policy_id = %d"
#         #代理人
#         agentSql = "delete from policy_agent_relation where policy_agent_relation_id = %d"
#         #服务
#         serviceSql = "delete from policy_service where policy_service_id = %d"
#         #文件
#         fileSql = "delete from policy_file where policy_file_id = %d"
#         #重疾
#         riskDataSql = "delete from policy_risk_data where policy_risk_data_id = %d"
#         #客户
#         customerSql = "delete from customer where customer_id = %d"
#         #风险保额
#         customerRiskAmntSql = "delete from customer_riskamnt where customer_riskamnt_id = %d"
#         #地址
#         addressSql = "delete from address where address_id = %d"
#         #告知
#         impartSql = "delete from impartinfo where impartinfo_id = %d"
#         #被保人关系
#         insuredSql = "delete from policy_insured_relation where policy_insured_relation_id = %d"
#         #受益人
#         bnfSql = "delete from bnt where bnt_id = %d"
#         #险种关系
#         policyRiskSql = "delete from policy_risk_relation where policy_risk_relation_id = %d"
#         #消息队列
#         mqSql = "delete from mq_push_detail where mq_push_detail_id = %d"
#         #抽检
#         samplingSql = "delete from sampling_ratio where sampling_ratio_id = %d"
#         #管理机构
#         policyConfigSwitchSql = "delete from policy_config_switch where policy_config_switch_id = %d"
        for key, value in delDict.items():
            if key != 'orderno' and key != 'prtno' and key != 'isSuccess':
                sqlStr = 'delete from ' + key + ' where ' + key + '_id = %d'
                for primaryKey in value:
                    CaseBase.executeDelSql(sqlStr%primaryKey)
        delSql = 'delete from policy_file where orderno ="%s"'%delDict.get('orderno', '0')
        CaseBase.executeDelSql(delSql)
#         delSql = 'delete from policy_file where orderno ="%s"'%orderno
#         CaseBase.executeSql(sqlStr%primaryKey)
        CaseBase.commit()
        
    #获取值
    #keyValue:键值
    #initValue:初始值，默认为null
    #paramDict:入参字典
    #type:int整形，varchar字符串
    def getParam(keyValue, initValue, paramDict, type):
        if initValue == None:
            initValue = 'null'
        returnObj = paramDict.get(keyValue, initValue)
        if 'int' == type and returnObj != 'null':
            returnObj = int(float(returnObj))
        elif 'varchar' == type and returnObj != None and returnObj != 'null':
            returnObj = "'" + returnObj + "'"
        elif 'date' == type and returnObj != None and returnObj != 'null':
            returnObj = "'" + returnObj + "'"
        elif 'datetime' == type and returnObj != None and returnObj != 'null':
            returnObj = "'" + returnObj + "'"
        return returnObj
    
    #新增的责任5表
    #policyRisk,id,sql,删除列表
    def addDuty(paramDict, riskRelationId, orderno, grpno, contno, prtno, appntCode, insuredCode, riskDutySql, dutyGetSql, dutyGetRelaSql, dutyPremSql, dutyPremAccSql, delPolicyRiskDuty, delPolicyDutyGet, delPolicyDutyGetRela, delPolicyDutyPrem, delPolicyDutyPremAcc):
        policyRiskDutyId = 0
        #+ policyRiskDuty policyDutyGet policyDutyGetRela policyDutyPrem policyDutyPremAcc
        #1 (1)policyRiskDuty
        if paramDict.get('policyRiskDuty', None) == None:
            paramDict['policyRiskDuty'] = [{}]
        for policyRiskDuty in paramDict['policyRiskDuty']:
            policyRiskDutyId = CaseBase.executeInsertSql(
                riskDutySql%(
                    int(riskRelationId),
                    orderno,
                    grpno,
                    contno,
                    prtno,
                    PolicyDao.getParam('riskCode', '1029', policyRiskDuty, 'varchar'),
                    PolicyDao.getParam('dutyCode', '102901', policyRiskDuty, 'varchar'),
                    PolicyDao.getParam('dutyName', '百年行天下保险基本责任', policyRiskDuty, 'varchar'),
                    PolicyDao.getParam('mult', 'null', policyRiskDuty, 'varchar'),
                    PolicyDao.getParam('standPrem', '50000', policyRiskDuty, 'varchar'),
                    PolicyDao.getParam('prem', '50000', policyRiskDuty, 'varchar'),
                    PolicyDao.getParam('sumPrem', '50000', policyRiskDuty, 'varchar'),
                    PolicyDao.getParam('amnt', '100000', policyRiskDuty, 'varchar'),
                    PolicyDao.getParam('riskAmnt', '100000', policyRiskDuty, 'varchar'),
                    PolicyDao.getParam('payIntv', '12', policyRiskDuty, 'varchar'),
                    PolicyDao.getParam('cvalidate', '2018-03-22 12:00:00', policyRiskDuty, 'datetime'),
                    PolicyDao.getParam('payYears', '3', policyRiskDuty, 'varchar'),
                    PolicyDao.getParam('years', '30', policyRiskDuty, 'varchar'),
                    PolicyDao.getParam('payEndDate', '2021-03-22 12:00:00', policyRiskDuty, 'datetime'),
                    PolicyDao.getParam('payToDate', '2019-03-22 12:00:00', policyRiskDuty, 'datetime'),
                    PolicyDao.getParam('floatRate', '1', policyRiskDuty, 'varchar'),
                    PolicyDao.getParam('firstPayDate', 'null', policyRiskDuty, 'varchar'),
                    PolicyDao.getParam('getYear', 'null', policyRiskDuty, 'varchar'),
                    PolicyDao.getParam('getYearFlag', 'null', policyRiskDuty, 'varchar'),
                    PolicyDao.getParam('payEndYear', '3', policyRiskDuty, 'varchar'),
                    PolicyDao.getParam('payEndYearFlag', 'Y', policyRiskDuty, 'varchar'),
                    PolicyDao.getParam('insuYear', '30', policyRiskDuty, 'varchar'),
                    PolicyDao.getParam('insuYearFlag', 'Y', policyRiskDuty, 'varchar'),
                    PolicyDao.getParam('endDate', '2048-03-22 12:00:00', policyRiskDuty, 'datetime'),
                    PolicyDao.getParam('getStartDate', '2018-03-22 12:00:00', policyRiskDuty, 'datetime'),
                    PolicyDao.getParam('getStartType', 'null', policyRiskDuty, 'varchar'),
                    PolicyDao.getParam('liveGetMode', 'null', policyRiskDuty, 'int'),
                    PolicyDao.getParam('bonusGetMode', 'null', policyRiskDuty, 'varchar'),
                    PolicyDao.getParam('clmGetIntv', 'null', policyRiskDuty, 'varchar'),
                    PolicyDao.getParam('hasSocialSecurity', '1', policyRiskDuty, 'varchar'),
                    PolicyDao.getParam('peakLine', 'null', policyRiskDuty, 'varchar'),
                    PolicyDao.getParam('getLimit', 'null', policyRiskDuty, 'varchar'),
                    PolicyDao.getParam('getRate', 'null', policyRiskDuty, 'varchar'),
                    PolicyDao.getParam('calRule', 'null', policyRiskDuty, 'varchar'),
                    PolicyDao.getParam('calMode', 'G', policyRiskDuty, 'varchar')
                )
            )
            delPolicyRiskDuty.append(policyRiskDutyId)
            #2 (1.1)policyDutyGet
            if policyRiskDuty.get('policyDutyGet', None) == None:
                policyRiskDuty['policyDutyGet'] = [{'getDutyCode': '1029G1', 'getDutyKind': '1', 'urgeGetFlag': 'N', 'needCancelAcc': '1', 'sumMoney': '0', 'state': 'Y'},
                                                   {'getDutyCode': '1029G2', 'getDutyKind': '0', 'urgeGetFlag': 'Y', 'needCancelAcc': '0', 'sumMoney': '330', 'state': 'N'}]
            for policyDutyGet in policyRiskDuty['policyDutyGet']:
                did = CaseBase.executeInsertSql(
                    dutyGetSql%(
                        int(riskRelationId),
                        policyRiskDutyId,
                        orderno,
                        grpno,
                        contno,
                        prtno,
                        PolicyDao.getParam('riskCode', '1029', policyDutyGet, 'varchar'),
                        PolicyDao.getParam('dutyCode', '102901', policyDutyGet, 'varchar'),
                        PolicyDao.getParam('getDutyCode', '1029G1', policyDutyGet, 'varchar'),
                        PolicyDao.getParam('getDutyKind', '1', policyDutyGet, 'varchar'),
                        insuredCode,
                        PolicyDao.getParam('liveGetMode', 'null', policyDutyGet, 'int'),
                        PolicyDao.getParam('liveGetType', 'null', policyDutyGet, 'varchar'),
                        PolicyDao.getParam('getLimit', '0', policyDutyGet, 'varchar'),
                        PolicyDao.getParam('getRate', '0', policyDutyGet, 'varchar'),
                        PolicyDao.getParam('urgeGetFlag', 'N', policyDutyGet, 'varchar'),
                        PolicyDao.getParam('addRate', '0', policyDutyGet, 'varchar'),
                        PolicyDao.getParam('canGet', '1', policyDutyGet, 'varchar'),
                        PolicyDao.getParam('needAcc', '0', policyDutyGet, 'varchar'),
                        PolicyDao.getParam('needCancelAcc', '1', policyDutyGet, 'varchar'),
                        PolicyDao.getParam('standMoney', '1555', policyDutyGet, 'varchar'),
                        PolicyDao.getParam('actuGet', '1555', policyDutyGet, 'varchar'),
                        PolicyDao.getParam('sumMoney', '0', policyDutyGet, 'varchar'),
                        PolicyDao.getParam('getIntv', '0', policyDutyGet, 'varchar'),
                        PolicyDao.getParam('getToDate', '2018-03-22 12:00:00', policyDutyGet, 'datetime'),
                        PolicyDao.getParam('getStartDate', '2018-03-22 12:00:00', policyDutyGet, 'datetime'),
                        PolicyDao.getParam('getEndDate', '2048-03-22 12:00:00', policyDutyGet, 'datetime'),
                        PolicyDao.getParam('getEndState', 'null', policyDutyGet, 'varchar'),
                        PolicyDao.getParam('state', 'Y', policyDutyGet, 'varchar')
                    )
                )
                delPolicyDutyGet.append(did)
            #3 (1.2)policyDutyGetRela (除5022外无数据)
            if policyRiskDuty.get('policyDutyGetRela', None) != None:
                for policyDutyGetRela in policyRiskDuty['policyDutyGetRela']:
                    did = CaseBase.executeInsertSql(
                        dutyGetRelaSql%(
                            int(riskRelationId),
                            policyRiskDutyId,
                            orderno,
                            grpno,
                            contno,
                            prtno,
                            PolicyDao.getParam('riskCode', '1029', policyDutyGetRela, 'varchar'),
                            PolicyDao.getParam('dutyCode', '102901', policyDutyGetRela, 'varchar'),
                            PolicyDao.getParam('relaType', 'disease', policyDutyGetRela, 'varchar'),
                            PolicyDao.getParam('getDutyCode', '1029G1', policyDutyGetRela, 'varchar'),
                            PolicyDao.getParam('relaCode', '00001', policyDutyGetRela, 'varchar'),
                            PolicyDao.getParam('relaName', '恶性肿瘤', policyDutyGetRela, 'varchar')
                        )
                    )
                    delPolicyDutyGetRela.append(did)
            #4 (1.3)policyDutyPrem
            if policyRiskDuty.get('policyDutyPrem', None) == None:
                policyRiskDuty['policyDutyPrem'] = [{}]
            for policyDutyPrem in policyRiskDuty['policyDutyPrem']:
                policyDutyPremId = CaseBase.executeInsertSql(
                    dutyPremSql%(
                        int(riskRelationId),
                        policyRiskDutyId,
                        orderno,
                        grpno,
                        contno,
                        prtno,
                        PolicyDao.getParam('riskCode', '1029', policyDutyPrem, 'varchar'),
                        PolicyDao.getParam('dutyCode', '102901', policyDutyPrem, 'varchar'),
                        PolicyDao.getParam('payPlanCode', '1029P1', policyDutyPrem, 'varchar'),
                        PolicyDao.getParam('payPlanType', '0', policyDutyPrem, 'varchar'),
                        PolicyDao.getParam('appntType', '1', policyDutyPrem, 'varchar'),
                        appntCode,
                        PolicyDao.getParam('urgePayFlag', 'Y', policyDutyPrem, 'varchar'),
                        PolicyDao.getParam('needAcc', '0', policyDutyPrem, 'varchar'),
                        PolicyDao.getParam('payTimes', '1', policyDutyPrem, 'varchar'),
                        PolicyDao.getParam('payStartDate', '2018-03-22 12:00:00', policyDutyPrem, 'datetime'),
                        PolicyDao.getParam('payEndDate', '2021-03-22 12:00:00', policyDutyPrem, 'datetime'),
                        PolicyDao.getParam('payToDate', '2019-03-22 12:00:00', policyDutyPrem, 'datetime'),
                        PolicyDao.getParam('prem', '50000', policyDutyPrem, 'varchar'),
                        PolicyDao.getParam('standPrem', '1550', policyDutyPrem, 'varchar'),
                        PolicyDao.getParam('sumPrem', '50000', policyDutyPrem, 'varchar'),
                        PolicyDao.getParam('payIntv', '12', policyDutyPrem, 'varchar'),
                        PolicyDao.getParam('suppRiskScore', '0.00', policyDutyPrem, 'varchar'),
                        PolicyDao.getParam('freeFlag', 'null', policyDutyPrem, 'varchar'),
                        PolicyDao.getParam('freeRate', '0', policyDutyPrem, 'varchar'),
                        PolicyDao.getParam('freeStartDate', 'null', policyDutyPrem, 'varchar'),
                        PolicyDao.getParam('freeEndDate', 'null', policyDutyPrem, 'varchar'),
                        PolicyDao.getParam('state', '0', policyDutyPrem, 'varchar'),
                        PolicyDao.getParam('addFeeDirect', 'null', policyDutyPrem, 'varchar'),
                        PolicyDao.getParam('secInsuAddPoint', '0', policyDutyPrem, 'varchar'),
                        PolicyDao.getParam('addFeeWay', 'null', policyDutyPrem, 'varchar')
                    )
                )
                delPolicyDutyPrem.append(policyDutyPremId)
                #5 (1.3.1)policyDutyPremAcc
                if policyDutyPrem.get('policyDutyPremAcc', None) == None:
                    policyDutyPrem['policyDutyPremAcc'] = [{}]
                for policyDutyPremAcc in policyDutyPrem['policyDutyPremAcc']:
                    did = CaseBase.executeInsertSql(
                        dutyPremAccSql%(
                            policyDutyPremId,
                            orderno,
                            grpno,
                            contno,
                            prtno,
                            PolicyDao.getParam('riskCode', '1029', policyDutyPremAcc, 'varchar'),
                            PolicyDao.getParam('dutyCode', '102901', policyDutyPremAcc, 'varchar'),
                            PolicyDao.getParam('payPlanCode', '1029P1', policyDutyPremAcc, 'varchar'),
                            PolicyDao.getParam('insuAccNo', '8634123456789012309', policyDutyPremAcc, 'varchar'),
                            PolicyDao.getParam('rate', '0', policyDutyPremAcc, 'varchar'),
                            PolicyDao.getParam('newFlag', 'N', policyDutyPremAcc, 'varchar'),
                            PolicyDao.getParam('calFlag', 'N', policyDutyPremAcc, 'varchar')
                        )
                    )
                    delPolicyDutyPremAcc.append(did)
    