# -*- coding: utf-8 -*-
"""
------------------------------------------------
    File Name:      PolicyDao.py
    Description:    在线承保 数据处理层
    Author:         李清霞
    date:           2018/04/12
    Copyright:      (c)   李清霞2018
------------------------------------------------
"""

import json
from aeonlifebase.CaseBase import CaseBase
from aeonlifebase.policy.Config import Config
from decimal import *
import logging
import time
from copy import deepcopy


class CustomerDao():
    customerBaseColumnList="""
        customer_id,customer_code,name,sex,id_type,id_no,birthday,
        id_validate,stature,avoirdopois,marriage,native_place,
        degree,salary,occupation,is_smoke,has_social_security,
        phone,other_phone,email,rgt_address,postal_address,customer_level，
        vip_level,vip_date,vip_prem,locale,is_deleted,create_user,
        create_date,update_user,update_date """
    
    addressBaseColumnList="""
        address_id,address_code,province,city,county,address_text,
        postal_zip_code,locale,is_deleted,create_user,create_date,
        update_user,update_date """
        
    customerHistoryBaseColumnList="""
        customer_history_id,customer_code,has_loans_not_pay,has_t_refused_record,
        has_t_health_notice,has_t_abnor_impart,has_t_health_questionnaire,
        has_claim_record,has_unfinished_uw_notice,has_uw_notice,has_past_health,
        locale,is_deleted,create_user,create_date,update_user,update_date"""
        
    impartBaseColumnList="""
        impart_id,customer_id,customer_code,version,code,param,start_date,end_date,
        locale,is_deleted,create_user,create_date,update_user,update_date"""
        
    potentialCustomerBaseColumnList="""
        potential_customer_id,customer_code,name,sex,id_type,id_no,birthday,id_validate,
        stature,avoirdupois,marriage,native_place,degree,salary,occupation,is_smoke,
        has_social_security,phone,other_phone,email,rgt_address,postal_address,
        from_user,locale,is_deleted,create_user,create_date,update_user,update_date"""
        
    relCustomerUserBaseColumnList="""    
        rel_customer_user_id,customer_code,user_id,user_code,locale,is_deleted,
        create_user,create_date,update_user,update_date"""
        
    #根据客户编号获得客户信息列表
    def getCustomerList(conditionStr):
        sqlStr="select" + CustomerDao.customerBaseColumnList +" from customer"
        if   conditionStr != None:
            sqlStr += conditionStr
        result = CaseBase.executeSql(sqlStr)
        customerList =[]
        for row in customerList:
            customerMap = {}
            customerMap['customerId'] = row[0]
            customerMap['customerCode'] = row[1]
            customerMap['name'] = row[2]
            customerMap['sex'] = row[3]
            customerMap['idType'] = row[4]
            customerMap['idNo'] = row[5]
            customerMap['birthday'] = row[6]
            customerMap['idValidate'] = row[7]
            customerMap['stature'] = row[8]
            customerMap['avoirdopois'] = row[9]
            customerMap['marriage'] = row[10]
            customerMap['nativePlace'] = row[11]
            customerMap['degree'] = row[12]
            customerMap['salary'] = row[13]
            customerMap['occupation'] = row[14]
            customerMap['isSmoke'] = row[15]
            customerMap['hasSocialSecurity'] = row[16]
            customerMap['phone'] = row[17]
            customerMap['otherPhone'] = row[18]
            customerMap['email'] = row[19]
            customerMap['rgtAddress'] = row[20]
            customerMap['postalAddress'] = row[21]
            customerMap['customerLevel'] = row[22]
            customerMap['vipLevel'] = row[23]
            customerMap['vipDate'] = row[24]
            customerMap['vipPrem'] = row[25]
            customerMap['locale'] = row[26]
            customerMap['isDeleted'] = row[27]
            customerMap['createUser'] = row[28]
            customerMap['createDate'] = row[29]
            customerMap['updateUser'] = row[30]
            customerMap['updateDate'] = row[31]
            customerList.append(customerMap)
        return customerList
            
    def getAddressList(conditionStr):
        sqlStr="select" + CustomerDao.addressBaseColumnList +" from address"
        if   conditionStr != None:
            sqlStr += conditionStr
        result = CaseBase.executeSql(sqlStr)
        addressList =[]
        for row in addressList:
            addressMap = {} 
            addressMap['addressId'] = row[0]
            addressMap['addressCode'] = row[1]
            addressMap['province'] = row[2]
            addressMap['city'] = row[3]
            addressMap['county'] = row[4]
            addressMap['addressText'] = row[5]
            addressMap['postalZipCode'] = row[6]
            addressMap['locale'] = row[7]
            addressMap['isDeleted'] = row[8]
            addressMap['createUser'] = row[9]
            addressMap['createDate'] = row[10]
            addressMap['updateUser'] = row[11]
            addressMap['updateDate'] = row[12] 
            addressList.append(addressMap)
        return  addressList     
            
    def getCustomerHistoryList(conditionStr):
        sqlStr="select" + CustomerDao.customerHistoryBaseColumnList +" from customer_history"
        if   conditionStr != None:
            sqlStr += conditionStr
        result = CaseBase.executeSql(sqlStr)
        customerHistoryList =[]
        for row in customerHistoryList:
            customerHistoryMap = {}                  
            customerHistoryMap['customerHistoryId'] = row[0]
            customerHistoryMap['customerCode'] = row[1]
            customerHistoryMap['hasLoansNotPay'] = row[2]
            customerHistoryMap['hasTRefusedRecord'] = row[3]
            customerHistoryMap['hasTHealthNotice'] = row[4]
            customerHistoryMap['hasTAbnorImpart'] = row[5]
            customerHistoryMap['hasTHealthQuestionnaire'] = row[6]
            customerHistoryMap['hasClaimRecord'] = row[7]
            customerHistoryMap['hasUnfinishedUwNotice'] = row[8]
            customerHistoryMap['hasUwNotice'] = row[9]
            customerHistoryMap['hasPastHealth'] = row[10]
            customerHistoryMap['locale'] = row[11]
            customerHistoryMap['isDeleted'] = row[12]
            customerHistoryMap['createUser'] = row[13]
            customerHistoryMap['createDate'] = row[14]
            customerHistoryMap['updateUser'] = row[15]
            customerHistoryMap['updateDate'] = row[16]
            customerHistoryList.append(customerHistoryMap)
        return customerHistoryList
            
    def getImpartList(conditionStr):
        sqlStr="select" + CustomerDao.impartBaseColumnList +" from impart"
        if   conditionStr != None:
            sqlStr += conditionStr
        result = CaseBase.executeSql(sqlStr)
        impartList =[]
        for row in impartList:
            impartMap = {}  
            impartMap['impartId'] = row[0]
            impartMap['customerId'] = row[1]
            impartMap['customerCode'] = row[2]
            impartMap['version'] = row[3]
            impartMap['code'] = row[4]
            impartMap['param'] = row[5]
            impartMap['startDate'] = row[6]
            impartMap['endDate'] = row[7]
            impartMap['locale'] = row[8]
            impartMap['isDeleted'] = row[9]
            impartMap['createUser'] = row[10]
            impartMap['createDate'] = row[11]
            impartMap['updateUser'] = row[12]
            impartMap['updateDate'] = row[13]
            impartList.append(impartMap)
        return impartList
            
    def getPotentialCustomerList(conditionStr):
        sqlStr="select" + CustomerDao.potentialCustomerBaseColumnList +" from potential_customer"
        if   conditionStr != None:
            sqlStr += conditionStr
        result = CaseBase.executeSql(sqlStr)
        potentialCustomerList =[]
        for row in potentialCustomerList:
            potentialCustomerMap = {} 
            potentialCustomerMap['potentialCustomerId'] = row[0]
            potentialCustomerMap['customerCode'] = row[1]
            potentialCustomerMap['name'] = row[2]
            potentialCustomerMap['sex'] = row[3]
            potentialCustomerMap['idType'] = row[4]
            potentialCustomerMap['idNo'] = row[5]
            potentialCustomerMap['birthday'] = row[6]
            potentialCustomerMap['idValidate'] = row[7]
            potentialCustomerMap['stature'] = row[8]
            potentialCustomerMap['avoirdupois'] = row[9]
            potentialCustomerMap['marriage'] = row[10]
            potentialCustomerMap['nativePlace'] = row[11]
            potentialCustomerMap['degree'] = row[12]
            potentialCustomerMap['salary'] = row[13]
            potentialCustomerMap['occupation'] = row[14]
            potentialCustomerMap['isSmoke'] = row[15]
            potentialCustomerMap['hasSocialSecurity'] = row[16]
            potentialCustomerMap['phone'] = row[17]
            potentialCustomerMap['otherPhone'] = row[18]
            potentialCustomerMap['email'] = row[19]
            potentialCustomerMap['rgtAddress'] = row[20]
            potentialCustomerMap['postalAddress'] = row[21]
            potentialCustomerMap['fromUser'] = row[22]
            potentialCustomerMap['locale'] = row[23]
            potentialCustomerMap['isDeleted'] = row[24]
            potentialCustomerMap['createUser'] = row[25]
            potentialCustomerMap['createDate'] = row[26]
            potentialCustomerMap['updateUser'] = row[27]
            potentialCustomerMap['updateDate'] = row[28]  
            potentialCustomerList.append(potentialCustomerMap)
        return potentialCustomerList
            
    def getRelCustomerUserList(conditionStr):
        sqlStr="select" + CustomerDao.relCustomerUserBaseColumnList +" from rel_customer_user"
        if   conditionStr != None:
            sqlStr += conditionStr
        result = CaseBase.executeSql(sqlStr)
        relCustomerUserList =[]
        for row in relCustomerUserList:
            relCustomerUserMap = {} 
            relCustomerUserMap['relCustomerUserId'] = row[0]
            relCustomerUserMap['customerCode'] = row[1]
            relCustomerUserMap['userId'] = row[2]
            relCustomerUserMap['userCode'] = row[3]
            relCustomerUserMap['locale'] = row[4]
            relCustomerUserMap['isDeleted'] = row[5]
            relCustomerUserMap['createUser'] = row[6]
            relCustomerUserMap['createDate'] = row[7]
            relCustomerUserMap['updateUser'] = row[8]
            relCustomerUserMap['updateDate'] = row[9]
            relCustomerUserList.append(relCustomerUserMap)
        return relCustomerUserList
    def insertTestData(inputParam):
        paramDict = deepcopy(inputParam)
         #sql插入语句
         #订单，待填：orderno   
        customerSql = "INSERT INTO customer\
                        (customer_code,name,sex,id_type,id_no,birthday,id_validate,stature,avoirdopois,marriage,native_place,degree,salary,occupation,is_smoke,has_social_security, phone,other_phone,email,rgt_address,postal_address,customer_level,vip_level,vip_date,vip_prem,locale,is_deleted,create_user, create_date,update_user,update_date)\
                        VALUES \
                        ('%s', '刘鲁明', '1', '2', '230229196707193666', '1979-05-08', '3000-01-23', '172.00', '70.00', '1', 'CHN', '1', '300000.00', '2010101', 'Y', null, '13879857412', '13879857412', '12345@163.com', %d, %d, '1', '1', '2018-2-1', '20000.0', 'zh_CN', 'n', 111, '2018-02-08 14:47:56', 222, '2018-02-09 10:19:55')"
    
        addressSql = "INSERT INTO address \
                        (address_code,province,city,county,address_text,postal_zip_code,locale,is_deleted,create_user,create_date,update_user,update_date)\
                        VALUES \
                        (54321, '%s', '%s', '%s', '黑龙江哈尔滨阿城市后港区18号楼', '%s', 'zh_CN', 'n', 111, '2018-02-08 14:47:56', 222, '2018-02-09 10:19:55')"
    
    
        customerHistorySql = "INSERT INTO customer_history \
                             (customer_code,has_loans_not_pay,has_t_refused_record,has_t_health_notice,has_t_abnor_impart,has_t_health_questionnaire,has_claim_record,has_unfinished_uw_notice,has_uw_notice,has_past_health,locale,is_deleted,create_user,create_date,update_user,update_date) \
                             VALUES \
                             ('%s', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'Y', 'zh_CN', 'n', 111, '2018-02-08 14:47:56', 222, '2018-02-09 10:19:55')"
                             
        impartSql = "INSERT INTO impart \
                    (customer_id,customer_code,version,code,param,start_date,end_date,locale,is_deleted,create_user,create_date,update_user,update_date)\
                     VALUES \
                     (%d,'%s', '1', '001', '172.0,65.0', '2018-01-18', '2018-01-18', 'zh_CN', 'n', 111, '2018-02-08 14:47:56', 222, '2018-02-09 10:19:55')"
                                      
        potentialCustomerSql = "INSERT INTO potential_customer \
                                (customer_code,name,sex,id_type,id_no,birthday,id_validate,stature,avoirdupois,marriage,native_place,degree,salary,occupation,is_smoke,has_social_security,phone,other_phone,email,rgt_address,postal_address,from_user,locale,is_deleted,create_user,create_date,update_user,update_date) \
                                VALUES \
                                ('%s', '王', '1', '01', '1', '1989-03-04', '1111-11-11', '1.00', '1.00', '1', '1', '1', '1.00', '1', '1', null, '12311111111', '32122222222', '123456', '89', null, 666666, 'zh_CN', 'n', 111, '2018-02-08 14:47:56', 222, '2018-02-09 10:19:55')"
        
        relCustomerUserSql = "INSERT INTO rel_customer_user\
                             (customer_code,user_id,user_code,locale,is_deleted,create_user,create_date,update_user,update_date)\
                             VALUES \
                             ('%s', 321, '%s', 'zh_CN', 'n', null, '2018-02-08 14:05:13', null, null)"
         
        smoLogSql = "INSERT INTO smo_log\
                             (user_id,action_type,log_time,ip_address,log_data,service_name,method_name,log_comment)\
                             VALUES \
                             (...)"
                             
        smoPvLogSql = "INSERT INTO smo_pv_log\
                             (user_id,action_type,log_time,ip_address,log_data,service_name,method_name,log_comment,is_deleted)\
                             VALUES \
                             (...)"                     
        #定义部分参数1
        userCode = paramDict.get('usercode', '66666666')
        customerCode = paramDict.get('customerCode', '90000000066668')
        
         #需要用到的主键
        customerId = 0
        addressId = 0
        customerHistoryId = 0
        impartId = 0
        potentialCustomerId = 0
        relCustomerUserId = 0
        #各个测试数据的主键，用于测试完成后删除。结构：{表名:[主键列表],...}
        delCustomer = []
        delAddress = []
        delCustomerHistory = []
        delImpart = []
        delPotentialCustomer = []
        delRelCustomerUser = []
        delSmoLog = []
        delSmoPvLog = []
      
        #开始插入
        try:
            #2 地址表
#             did = CaseBase.executeInsertSql(addressSql%(addressCode))
#             delAddress.append(did)
            #3.1 客户-通讯地址
            postalAddressId = CaseBase.executeInsertSql(addressSql%('660000', '660001', '660002', '663405'))
            delAddress.append(postalAddressId)
            #3.2 客户-户籍地址
            rgtAddressId = CaseBase.executeInsertSql(addressSql%('650000', '650001', '650002', '663400'))
            delAddress.append(rgtAddressId)
            
            #1 客户表
            did = CaseBase.executeInsertSql(customerSql%(customerCode,rgtAddressId,postalAddressId))
            delCustomer.append(did)
           
            #3 客户历史信息表
            did = CaseBase.executeInsertSql(customerHistorySql%(customerCode))
            delCustomerHistory.append(did)
            
            #4.告知表
            did = CaseBase.executeInsertSql(impartSql%(customerId,customerCode))
            delImpart.append(did)
            
            #5.潜在客户表
            did = CaseBase.executeInsertSql(potentialCustomerSql%(customerCode))
            delPotentialCustomer.append(did)
            
            #6.客户与用户关系表
            did = CaseBase.executeInsertSql(relCustomerUserSql%(customerCode,userCode))
            delRelCustomerUser.append(did)
            
            #fin 提交操作
            CaseBase.commit()
            #整理待删除记录
            delDict = {
                'customer': delCustomer,
                'address': delAddress,
                'customer_history': delCustomerHistory,
                'impart': delImpart,
                'potential_customer': delPotentialCustomer,
                'rel_customer_user': delRelCustomerUser,
                'smo_log': delSmoLog,
                'smo_pv_log': delSmoPvLog,
                'userCode':userCode,
                'customerCode':customerCode,
                'isSuccess':'Y'
                }
        except BaseException as e:
            delDict = {
               'customer': delCustomer,
                'address': delAddress,
                'customer_history': delCustomerHistory,
                'impart': delImpart,
                'potential_customer': delPotentialCustomer,
                'rel_customer_user': delRelCustomerUser,
                'smo_log': delSmoLog,
                'smo_pv_log': delSmoPvLog,
                'userCode':userCode,
                'customerCode':customerCode,
                'isSuccess': e
                }
            CustomerDao.delTestData(delDict)
        #返回待删除测试数据的主键记录
        return delDict
        
    def delTestData(delDict):
        for key, value in delDict.items():
            if key != 'userCode' and key != 'customerCode' and key != 'isSuccess':
                sqlStr = 'delete from ' + key + ' where ' + key + '_id = %d'
                for primaryKey in value:
                    CaseBase.executeDelSql(sqlStr%primaryKey)
        CaseBase.commit()
        
  