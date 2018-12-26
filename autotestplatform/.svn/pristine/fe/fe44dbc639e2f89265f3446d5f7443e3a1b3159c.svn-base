# -*- coding: utf-8 -*-
"""
-------------------------------------------------
   File Name：     PolicyChargeSql.py 
                                在线承保数据库操作方法
   Description :  1.根据订单号获取保单信息
                  2.根据订单号获取订单信息
                  3.根据订单号投保单号获取保单险种关联信息
                  4.根据订单号获得组别信息列表
                  5.根据投保单号获取电子保单地址
                  6.如果收费方式为批次转账，判断入库的保单状态和收费方式是否正确
                  7.根据投保单号获取被保人生日
                  8.根据投保单号获取投保人生日
                  9.根据投保单号判断保单中是否存在豁免险
   Author :        单晓伟 
   date：          2018/04/08
-------------------------------------------------
"""
from aeonlifebase.CaseBase import CaseBase
from aeonlifebase.policy.Config import Config
from aeonlifebase.policy.PolicyDao import PolicyDao
import logging
from pip._vendor.distlib._backport.tarfile import TUREAD

class PolicyChargeSql(CaseBase):
    
    def __init__(self):
         CaseBase.__init__(self, Config())
    
    # 根据订单号获取保单信息
    def getPolicyInfoByOrderNo(orderNo):
        allColumns ="policy_id, orderno, grpno, contno, prtno, managecom,salechnl, appnt_id,appnt_code,\
            new_bank_code,new_bank_acc_no,new_acc_name, new_pay_mode, bank_code, bank_acc_no, acc_name,\
            sum_prem,pol_apply_date, status, app_flag, uw_flag, sign_date,sign_time, cvalidate, pol_mode, pol_get_date,\
             pol_get_time,custom_get_pol_date,sale_mode, sub_sale_mode, has_callback,immediate_callback, rela_prt_no,\
            need_email_subscribe,need_rule,remark, cancel_reason, locale, is_deleted, create_user,\
            create_date,update_user,update_date,need_dr,dr_date,cvalidate_flag"
        sql ="SELECT "+allColumns+" from policy where orderno = '%s' and app_flag != '3'"%orderNo
        result = CaseBase.executeSql(sql)
        policyList =[]
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
            policyMap['newBankCode'] = row[9]
            policyMap['newBankAccNo'] = row[10]
            policyMap['newAccName'] = row[11]
            policyMap['newPayMode'] = row[12]
            policyMap['bankCode'] = row[13]
            policyMap['bankAccNo'] = row[14]
            policyMap['accName'] = row[15]
            policyMap['sumPrem'] = row[16]
            policyMap['polApplyDate'] = row[17]
            policyMap['status'] = row[18]
            policyMap['appFlag'] = row[19]
            policyMap['uwFlag'] = row[20]
            policyMap['signDate'] = row[21]
            policyMap['signTime'] = row[22]
            policyMap['cvalidate'] = row[23]
            policyMap['polMode'] = row[24]
            policyMap['polGetDate'] = row[25]
            policyMap['polGetTime'] = row[26]
            policyMap['customGetPolDate'] = row[27]
            policyMap['saleMode'] = row[28]
            policyMap['subSaleMode'] = row[29]
            policyMap['hasCallback'] = row[30]
            policyMap['immediateCallback'] = row[31]
            policyMap['relaPrtNo'] = row[32]
            policyMap['needEmailSubscribe'] = row[33]
            policyMap['needRule'] = row[34]
            policyMap['remark'] = row[35]
            policyMap['cancelReason'] = row[36]
            policyMap['locale'] = row[37]
            policyMap['isDeleted'] = row[38]
            policyMap['createUser'] = row[39]
            policyMap['createDate'] = row[40]
            policyMap['updateUser'] = row[41]
            policyMap['updateDate'] = row[42]
            policyMap['needDr'] = row[43]
            policyMap['drDate'] = row[44]
            policyMap['cvalidateFlag'] = row[45]
            policyList.append(policyMap)
        return policyList

    #根据订单号获得订单信息列表
    def getOrderList(orderno):
        sql = "select orderinfo_id, orderno, state, charge_state, charge_date,cancel_reason, locale,\
            is_deleted, create_user,create_date,update_user, update_date  from orderinfo where orderno = '%s' "%orderno
        result = CaseBase.executeSql(sql)
        orderList = []
        for row in result:
            orderMap = {}
            orderMap['orderinfoId'] = row[0]
            orderMap['orderno'] = row[1]
            orderMap['state'] = row[2]
            orderMap['chargeState'] = row[3]
            orderMap['chargeDate'] = row[4]
            orderMap['cancelReason'] = row[5]
            orderMap['locale'] = row[6]
            orderMap['isDeleted'] = row[7]
            orderMap['createUser'] = row[8]
            orderMap['createDate'] = row[9]
            orderMap['updateUser'] = row[10]
            orderMap['updateDate'] = row[11]
            orderList.append(orderMap)
        return orderList      

    #根据订单号和投保单号获取保单险种关联信息
    def getPolicyRiskRelation(orderNo,prtno):
        allColumns ="policy_risk_relation_id, policy_id, orderno, grpno, prtno, risk_code, \
            cvalidate, amnt, prem, mult, pay_intv, pay_end_year, pay_end_year_flag,\
            insu_year,insu_year_flag, get_year, get_year_flag, get_rate, acc_auto_pay_flag, auto_pay_flag,\
            bonus_get_mode, clm_get_intv, live_get_mode, renew_flag, app_flag,invalid_flag,\
            locale, is_deleted,create_user, create_date, update_user, update_date"
        sql = "SELECT "+allColumns+" from policy_risk_relation where orderno = '%s' and prtno = '%s' and app_flag != '3'"%(orderNo,prtno)
        result = CaseBase.executeSql(sql)
        policyRiskRelationList = []
        for row in result:
            policyRiskRelationMap = {}
            policyRiskRelationMap['policyRiskRelationId'] = row[0]
            policyRiskRelationMap['policyId'] = row[1]
            policyRiskRelationMap['orderno'] = row[2]
            policyRiskRelationMap['grpno'] = row[3]
            policyRiskRelationMap['prtno'] = row[4]
            policyRiskRelationMap['riskCode'] = row[5]
            policyRiskRelationMap['cvalidate'] = row[6]
            policyRiskRelationMap['amnt'] = row[7]
            policyRiskRelationMap['prem'] = row[8]
            policyRiskRelationMap['mult'] = row[9]
            policyRiskRelationMap['payIntv'] = row[10]
            policyRiskRelationMap['payEndYear'] = row[11]
            policyRiskRelationMap['payEndYearFlag'] = row[12]
            policyRiskRelationMap['insuYear'] = row[13]
            policyRiskRelationMap['insuYearFlag'] = row[14]
            policyRiskRelationMap['getYear'] = row[15]
            policyRiskRelationMap['getYearFlag'] = row[16]
            policyRiskRelationMap['getRate'] = row[17]
            policyRiskRelationMap['accAutoPayFlag'] = row[18]
            policyRiskRelationMap['autoPayFlag'] = row[19]
            policyRiskRelationMap['bonusGetMode'] = row[20]
            policyRiskRelationMap['clmGetIntv'] = row[21]
            policyRiskRelationMap['liveGetMode'] = row[22]
            policyRiskRelationMap['renewFlag'] = row[23]
            policyRiskRelationMap['appFlag'] = row[24]
            policyRiskRelationMap['invalidFlag'] = row[25]
            policyRiskRelationMap['locale'] = row[26]
            policyRiskRelationMap['isDeleted'] = row[27]
            policyRiskRelationMap['createUser'] = row[28]
            policyRiskRelationMap['createDate'] = row[29]
            policyRiskRelationMap['updateUser'] = row[30]
            policyRiskRelationMap['updateDate'] = row[31]
            policyRiskRelationList.append(policyRiskRelationMap)
        return policyRiskRelationList

    #根据订单号获得组别信息列表
    def getGrpList(orederNo):
        sql = "select grp_id, orderno, grpno, state, cancel_reason, locale, is_deleted,\
            create_user, create_date,update_user, update_date  from grp where orderno = '%s' "%(orederNo)
        result = CaseBase.executeSql(sql)
        grpList = []
        for row in result:
            grpMap = {}
            grpMap['grpId'] = row[0]
            grpMap['orderno'] = row[1]
            grpMap['grpno'] = row[2]
            grpMap['state'] = row[3]
            grpMap['cancelReason'] = row[4]
            grpMap['locale'] = row[5]
            grpMap['isDeleted'] = row[6]
            grpMap['createUser'] = row[7]
            grpMap['createDate'] = row[8]
            grpMap['updateUser'] = row[9]
            grpMap['updateDate'] = row[10]
            grpList.append(grpMap)
        return grpList


    #根据投保单号获取电子保单地址
    def getFirstFileUrl(prtNo):    
        sql_fileUrl= "select file_url from policy_file where prtno = '%s' and file_type = '1999' order by policy_file_id desc "%(prtNo)
        fileUrlList = CaseBase.executeSql(sql_fileUrl)
        if fileUrlList:
            print(fileUrlList[0][0])
            return fileUrlList[0][0]
        return  None
        
    #如果收费方式为批次转账，判断入库的保单状态和收费方式是否正确
    def getStatusAndPayMode(orderNo):
        sql = "select status,new_pay_mode from policy where orderNo='%s'  "%orderNo
        result = CaseBase.executeSql(sql)
        statusAndPayMode = {}
        statusAndPayMode['status'] = result[0][0]
        statusAndPayMode['payMode'] = result[0][1]
        return statusAndPayMode
    
    #根据投保单号获取被保人生日
    def getInsuredBirthday(prtno):
        sql = " select birthday from customer c ,policy_insured_relation r where c.customer_id = r.customer_id\
            and r.prtno = '%s' "%prtno
        result = CaseBase.executeSql(sql)
        logging.info(str(result[0][0]))
        return result[0][0]
    
    #根据投保单号获取投保人生日
    def getAppntBirthday(prtno):
        sql = " select birthday from customer c ,policy p where c.customer_id = p.appnt_id\
            and p.prtno = '%s' "%prtno
        result = CaseBase.executeSql(sql)
        logging.info(str(result[0][0]))
        return result[0][0]
    

    #根据投保单号判断保单中是否存在豁免险
    def isExepmt(prtno,riskCaseBase):
        sql_policy = "select risk_code from policy_risk_relation where prtno = '%s' "%prtno
        result_policy = CaseBase.executeSql(sql_policy)
        if result_policy:
            for risk in result_policy:
                sql_risk = "select exepmt_flag from risk where risk_code = '%s' "%risk[0]
                result_risk = riskCaseBase.executeSql(sql_risk)
                if result_risk:
                    if (result_risk[0][0]=='EA' or result_risk[0][0]=='EI'):
                        logging.info("exepmt_flag:"+str(result_risk[0][0]))
                        return True
            return False
                 

    def startTest(self):
         self.getPolicyInfoByOrderNo('1')
        
if(__name__ == "__main__"):
    PolicyChargeSql().startTest()

