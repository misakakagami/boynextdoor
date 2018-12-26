# -*- coding: utf-8 -*-
"""
------------------------------------------------
    File Name:      NoticeDao.py
    Description:    通知书 数据处理层
    Author:         孔德华
    date:           2018/04/23
    Copyright:      (c)   孔德华2018
------------------------------------------------
    Change Record:
       2018/04/23 创建类
"""

import json
from aeonlifebase.CaseBase import CaseBase
from aeonlifebase.notice.Config import Config
from  aeonlifebase.notice.EnumOfNotice import EnumOfNotice
from decimal import *
import logging
import time
from copy import deepcopy

class NoticeDao():
    
    noticeAgentRelationColumnList = """
        notice_agent_relation_id, notice_no, agent_code, agent_name, agent_phone, manage_com_code, manage_com_name, agent_com_name, is_main_agent, locale, is_deleted, create_user, create_date, update_user, update_date
        """
    
    noticeCustomerRelationColumnList = """
        notice_customer_relation_id, notice_no, customer_type, customer_code, customer_name, customer_sex, customer_age, customer_phone, locale, is_deleted, create_user, create_date, update_user, update_date
        """
    
    noticeFileRelationColumnList = """
        notice_file_relation_id, notice_info_id, notice_no, file_type, url, file_id, file_order, locale, is_deleted, create_user, create_date, update_user, update_date
        """
    
    noticeInfoColumnList = """
        notice_info_id, notice_no, business_type, business_no, notice_type, seq_no, sale_channel, status, branch_comname, send_date, end_date, sign_role, reply_type, cancel_reason, specific_notice_content, locale, is_deleted, create_user, create_date, update_user, update_date
        """
    
    #通知书 代理人关系列表
    def getNoticeAgentRelationList(conditionStr):
        sqlStr = "select " + NoticeDao.noticeAgentRelationColumnList + " from notice_agent_relation "
        if conditionStr != None:
            sqlStr += conditionStr
        result = CaseBase.executeSql(sqlStr)
        noticeList = []
        for row in result:
            noticeMap = {}
            noticeMap['noticeAgentRelationId'] = row[0]
            noticeMap['noticeNo'] = row[1]
            noticeMap['agentCode'] = row[2]
            noticeMap['agentName'] = row[3]
            noticeMap['agentPhone'] = row[4]
            noticeMap['manageComCode'] = row[5]
            noticeMap['manageComName'] = row[6]
            noticeMap['agentComName'] = row[7]
            noticeMap['isMainAgent'] = row[8]
            noticeMap['locale'] = row[9]
            noticeMap['isDeleted'] = row[10]
            noticeMap['createUser'] = row[11]
            noticeMap['createDate'] = row[12]
            noticeMap['updateUser'] = row[13]
            noticeMap['updateDate'] = row[14]
            noticeList.append(noticeMap)
        return noticeList

    #通知书 客户关系列表
    def getNoticeCustomerRelationList(conditionStr):
        sqlStr = "select " + NoticeDao.noticeCustomerRelationColumnList+ " from notice_customer_relation"
        if conditionStr != None:
            sqlStr += conditionStr
        result = CaseBase.executeSql(sqlStr)
        noticeList = []
        for row in result:
            noticeMap = {}
            noticeMap['noticeCustomerRelationId'] = row[0]
            noticeMap['noticeNo'] = row[1]
            noticeMap['customerType'] = row[2]
            noticeMap['customerCode'] = row[3]
            noticeMap['customerName'] = row[4]
            noticeMap['customerSex'] = row[5]
            noticeMap['customerAge'] = row[6]
            noticeMap['customerPhone'] = row[7]
            noticeMap['locale'] = row[8]
            noticeMap['isDeleted'] = row[9]
            noticeMap['createUser'] = row[10]
            noticeMap['createDate'] = row[11]
            noticeMap['updateUser'] = row[12]
            noticeMap['updateDate'] = row[13]
            noticeList.append(noticeMap)
        return noticeList

    #通知书 文件关系列表
    def getNoticeFileRelationList(conditionStr):
        sqlStr = "select " + NoticeDao.noticeFileRelationColumnList+ " from notice_file_relation"
        if conditionStr != None:
            sqlStr += conditionStr
        result = CaseBase.executeSql(sqlStr)
        noticeList = []
        for row in result:
            noticeMap = {}
            noticeMap['noticeFileRelationId'] = row[0]
            noticeMap['noticeInfoId'] = row[1]
            noticeMap['noticeNo'] = row[2]
            noticeMap['fileType'] = row[3]
            noticeMap['url'] = row[4]
            noticeMap['fileId'] = row[5]
            noticeMap['fileOrder'] = row[6]
            noticeMap['locale'] = row[7]
            noticeMap['isDeleted'] = row[8]
            noticeMap['createUser'] = row[9]
            noticeMap['createDate'] = row[10]
            noticeMap['updateUser'] = row[11]
            noticeMap['updateDate'] = row[12]
            noticeList.append(noticeMap)
        return noticeList

    #通知书 信息列表
    def getNoticeInfoList(conditionStr):
        sqlStr = "select " + NoticeDao.noticeInfoColumnList + " from notice_info"
        if conditionStr != None:
            sqlStr += conditionStr
        result = CaseBase.executeSql(sqlStr)
        noticeList = []
        for row in result:
            noticeMap = {}
            noticeMap['noticeInfoId'] = row[0]
            noticeMap['noticeNo'] = row[1]
            noticeMap['businessType'] = row[2]
            noticeMap['businessNo'] = row[3]
            noticeMap['noticeType'] = row[4]
            noticeMap['seqNo'] = row[5]
            noticeMap['saleChannel'] = row[6]
            noticeMap['status'] = row[7]
            noticeMap['branchComname'] = row[8]
            noticeMap['sendDate'] = row[9]
            noticeMap['endDate'] = row[10]
            noticeMap['signRole'] = row[11]
            noticeMap['replyType'] = row[12]
            noticeMap['cancelReason'] = row[13]
            noticeMap['specificNoticeContent'] = row[14]
            noticeMap['locale'] = row[15]
            noticeMap['isDeleted'] = row[16]
            noticeMap['createUser'] = row[17]
            noticeMap['createDate'] = row[18]
            noticeMap['updateUser'] = row[19]
            noticeMap['updateDate'] = row[20]
            noticeList.append(noticeMap)
        return noticeList


    #入参paramDict可包含项(都可不传):
#     {
#       'noticeNo': '自定义编码',
#       'notice_info':{
#         notice_no, business_type, business_no, notice_type, seq_no, sale_channel, status, branch_comname, send_date, end_date, sign_role, reply_type, cancel_reason, specific_notice_content, locale, is_deleted, create_user, create_date, update_user, update_date
#       },
#       'notice_agent_relation':[{
#         notice_no, agent_code, agent_name, agent_phone, manage_com_code, manage_com_name, agent_com_name, is_main_agent, locale, is_deleted, create_user, create_date, update_user, update_date
#       }],
#       'notice_customer_relation':[{
#         notice_no, customer_type, customer_code, customer_name, customer_age, customer_sex, customer_phone, locale, is_deleted, create_user, create_date, update_user, update_date
#       }],
#       'notice_file_relation':[{
#         notice_no, file_type, url, file_id, file_order, locale, is_deleted, create_user, create_date, update_user, update_date
#       }]默认没有
#     }
    def insertTestData(inputParam):
        paramDict = deepcopy(inputParam)
        #默认值
        noticeNo = paramDict.get('noticeNo', '10086')
        customerCode = '800000000200'
        customerName = '智乃'
        customerAge = 20
        agentCode = '4880000002'
        agentName = '金石'
        #需要用到的主键
        noticeInfoId = 0
        #各个测试数据的主键，用于测试完成后删除。结构：{表名:[主键列表],...}
        delAgent = []
        delCustomer = []
        delFile = []
        delInfo = []
        #开始插入
        try:
            #1 notice_info
            noticeInfo = paramDict.get('notice_info', {})
            NoticeDao.getParam('notice_no', noticeNo, noticeInfo, 'varchar')
            NoticeDao.getParam('business_type', '0', noticeInfo, 'varchar')
            NoticeDao.getParam('business_no', '1', noticeInfo, 'varchar')
            NoticeDao.getParam('notice_type', '1050', noticeInfo, 'varchar')
            NoticeDao.getParam('seq_no', '1', noticeInfo, 'varchar')
            NoticeDao.getParam('sale_channel', '1', noticeInfo, 'varchar')
            NoticeDao.getParam('status', '0', noticeInfo, 'varchar')
            NoticeDao.getParam('branch_comname', '1', noticeInfo, 'varchar')
            NoticeDao.getParam('send_date', '2018-04-27 12:00:00', noticeInfo, 'datetime')
            NoticeDao.getParam('end_date', '2018-04-27 12:00:00', noticeInfo, 'datetime')
            NoticeDao.getParam('sign_role', '1', noticeInfo, 'varchar')
            NoticeDao.getParam('reply_type', '1', noticeInfo, 'varchar')
            NoticeDao.getParam('cancel_reason', 'null', noticeInfo, 'varchar')
            noticeType = noticeInfo.get('notice_type', None)
            str = ''
            # 契约作业通知书1051
            if CaseBase.isMatch(noticeType, EnumOfNotice.NEW_BUSINESS_WORK.value):
                str = '{"customerComment":"123","InssueInfoList":[{"issueIndex": "33333","issueContent": "44444"},{"issueIndex": "555","issueContent": "666"}]}'
            # 核保作业通知书1057
            elif CaseBase.isMatch(noticeType, EnumOfNotice.UWWORK.value):
                str = '{"customerComment":"123","uwWorkInfo":{"issueContent": "1111","customerType": "2222"}}'
            # 核保决定通知书1069
            elif CaseBase.isMatch(noticeType, EnumOfNotice.UWDECISION.value):
                str = '{"customerComment":"123","uwReason":"0055","riskInfoList":[{"id":"111","riskCode":"222","riskName":"333","assessment":"444","acceptable":""}],"payInfo": {"sumPrem": "5555","payedPrem": "6666","payonPrem": "7777","unpayedPrem": "888","payModeName": "999"},"bankInfo": {"bankCode": "000","bankCodeName": "1212","bankAccNo":"45454"}}'
            # 转账收费不成功通知书(默认TRANSFERPAY)1050
            else:
                str = '{"content":"111","customerreply1":"2222","customerreply2":"333","bankCode":"444","bankName":"555","bankAccNo":"666","customerreply3":"777","otherContent":"888"}'
            NoticeDao.getParam('specific_notice_content', str, noticeInfo, 'varchar')
            NoticeDao.getParam('locale', 'zh_CN', noticeInfo, 'varchar')
            NoticeDao.getParam('is_deleted', 'n', noticeInfo, 'varchar')
            NoticeDao.getParam('create_user', 'null', noticeInfo, 'varchar')
            NoticeDao.getParam('create_date', '2018-04-27 12:00:00', noticeInfo, 'datetime')
            NoticeDao.getParam('update_user', 'null', noticeInfo, 'varchar')
            NoticeDao.getParam('update_date', '2018-04-27 12:00:00', noticeInfo, 'datetime')
            noticeInfoSql = 'INSERT INTO notice_info (' + noticeInfo['sqlColumn'] + ') VALUES (' + noticeInfo['sqlValue'] + ')'
            noticeInfoId = CaseBase.executeInsertSql(noticeInfoSql)
            delInfo.append(noticeInfoId)
            #2 notice_agent_relation
            if paramDict.get('notice_agent_relation', None) == None:
                paramDict['notice_agent_relation'] = [{}]
            for noticeAgentRelation in paramDict['notice_agent_relation']:
                NoticeDao.getParam('notice_no', noticeNo, noticeAgentRelation, 'varchar')
                NoticeDao.getParam('agent_code', agentCode, noticeAgentRelation, 'varchar')
                NoticeDao.getParam('agent_name', agentName, noticeAgentRelation, 'varchar')
                NoticeDao.getParam('agent_phone', 'null', noticeAgentRelation, 'varchar')
                NoticeDao.getParam('manage_com_code', '86880101', noticeAgentRelation, 'varchar')
                NoticeDao.getParam('manage_com_name', '百年人寿保险股份有限公司大连分公司', noticeAgentRelation, 'varchar')
                NoticeDao.getParam('agent_com_name', '大连分公司本部营业区', noticeAgentRelation, 'varchar')
                NoticeDao.getParam('is_main_agent', 'Y', noticeAgentRelation, 'varchar')
                NoticeDao.getParam('locale', 'zh_CN', noticeAgentRelation, 'varchar')
                NoticeDao.getParam('is_deleted', 'n', noticeAgentRelation, 'varchar')
                NoticeDao.getParam('create_user', 'null', noticeAgentRelation, 'varchar')
                NoticeDao.getParam('create_date', '2018-04-27 12:00:00', noticeAgentRelation, 'datetime')
                NoticeDao.getParam('update_user', 'null', noticeAgentRelation, 'varchar')
                NoticeDao.getParam('update_date', '2018-04-27 12:00:00', noticeAgentRelation, 'datetime')
                noticeAgentRelationSql = 'INSERT INTO notice_agent_relation (' + noticeAgentRelation['sqlColumn'] + ') VALUES (' + noticeAgentRelation['sqlValue'] + ')'
                did = CaseBase.executeInsertSql(noticeAgentRelationSql)
                delAgent.append(did)
            #3 notice_customer_relation
            if paramDict.get('notice_customer_relation', None) == None:
                paramDict['notice_customer_relation'] = [{}]
            for noticeCustomerRelation in paramDict['notice_customer_relation']:
                NoticeDao.getParam('notice_no', noticeNo, noticeCustomerRelation, 'varchar')
                NoticeDao.getParam('customer_type', '0', noticeCustomerRelation, 'varchar')
                NoticeDao.getParam('customer_code', customerCode, noticeCustomerRelation, 'varchar')
                NoticeDao.getParam('customer_name', customerName, noticeCustomerRelation, 'varchar')
                NoticeDao.getParam('customer_age', customerAge, noticeCustomerRelation, 'int')
                NoticeDao.getParam('customer_sex', '1', noticeCustomerRelation, 'varchar')
                NoticeDao.getParam('customer_phone', '18012344321', noticeCustomerRelation, 'varchar')
                NoticeDao.getParam('locale', 'zh_CN', noticeCustomerRelation, 'varchar')
                NoticeDao.getParam('is_deleted', 'n', noticeCustomerRelation, 'varchar')
                NoticeDao.getParam('create_user', 'null', noticeCustomerRelation, 'varchar')
                NoticeDao.getParam('create_date', '2018-04-27 12:00:00', noticeCustomerRelation, 'datetime')
                NoticeDao.getParam('update_user', 'null', noticeCustomerRelation, 'varchar')
                NoticeDao.getParam('update_date', '2018-04-27 12:00:00', noticeCustomerRelation, 'datetime')
                noticeCustomerRelationSql = 'INSERT INTO notice_customer_relation (' + noticeCustomerRelation['sqlColumn'] + ') VALUES (' + noticeCustomerRelation['sqlValue'] + ')'
                did = CaseBase.executeInsertSql(noticeCustomerRelationSql)
                delCustomer.append(did)
            #4 notice_file_relation
            if paramDict.get('notice_file_relation', None) != None:
                for noticeFileRelation in paramDict['notice_file_relation']:
                    NoticeDao.getParam('notice_info_id', noticeInfoId, noticeFileRelation, 'int')
                    NoticeDao.getParam('notice_no', noticeNo, noticeFileRelation, 'varchar')
                    NoticeDao.getParam('file_type', '9001', noticeFileRelation, 'varchar')
                    NoticeDao.getParam('url', 'www.ex', noticeFileRelation, 'varchar')
                    NoticeDao.getParam('file_id', '1', noticeFileRelation, 'varchar')
                    NoticeDao.getParam('file_order', '1', noticeFileRelation, 'varchar')
                    NoticeDao.getParam('locale', 'zh_CN', noticeFileRelation, 'varchar')
                    NoticeDao.getParam('is_deleted', 'n', noticeFileRelation, 'varchar')
                    NoticeDao.getParam('create_user', 'null', noticeFileRelation, 'varchar')
                    NoticeDao.getParam('create_date', '2018-04-27 12:00:00', noticeFileRelation, 'datetime')
                    NoticeDao.getParam('update_user', 'null', noticeFileRelation, 'varchar')
                    NoticeDao.getParam('update_date', '2018-04-27 12:00:00', noticeFileRelation, 'datetime')
                    noticeFileRelationSql = 'INSERT INTO notice_file_relation (' + noticeFileRelation['sqlColumn'] + ') VALUES (' + noticeFileRelation['sqlValue'] + ')'
                    did = CaseBase.executeInsertSql(noticeFileRelationSql)
                    delFile.append(did)
            #fin 提交操作
            CaseBase.commit()
            #整理待删除记录
            delDict = {
                'notice_info': delInfo,
                'notice_agent_relation': delAgent,
                'notice_customer_relation': delCustomer,
                'notice_file_relation': delFile,
                'noticeNo': noticeNo,
                'isSuccess':'Y'
                }
        except BaseException as e:
            print(e.message)
            CaseBase.rollBack()
            delDict = {'isSuccess':e}
        return delDict
        
    def delTestData(delDict):
        for key, value in delDict.items():
            if key != 'isSuccess':
                sqlStr = 'delete from ' + key + ' where ' + key + '_id = %d'
                for primaryKey in value:
                    CaseBase.executeDelSql(sqlStr%primaryKey)
        CaseBase.executeDelSql(delSql)
#         delSql = 'delete from policy_file where orderno ="%s"'%orderno
#         CaseBase.executeSql(sqlStr%primaryKey)
        CaseBase.commit()
    
    #根据noticeNo删除
    def delTestDataByNoticeNo(delDict):
        for key, value in delDict.items():
            if key != 'isSuccess' and key != 'noticeNo':
                sqlStr = 'delete from ' + key + ' where notice_no="' + delDict['noticeNo'] + '"'
                CaseBase.executeDelSql(sqlStr)
        CaseBase.commit()        
        
    #获取值字典{字段名:值}
    #keyValue:键值
    #initValue:初始值，默认为null
    #paramDict:入参字典
    #type:int整形，varchar字符串 datetime日期 
    def getParam(keyValue, initValue, paramDict, type):
        if initValue == None:
            initValue = 'null'
        returnObj = paramDict.get(keyValue, initValue)
        if 'int' == type and returnObj != 'null':
#             returnObj = int(float(returnObj))
            pass
        elif 'varchar' == type and returnObj != None and returnObj != 'null':
            if returnObj.find("'") != -1:
                returnObj = '"' + returnObj + '"'
            else:
                returnObj = "'" + returnObj + "'"
        elif 'date' == type and returnObj != None and returnObj != 'null':
            returnObj = "'" + returnObj + "'"
        elif 'datetime' == type and returnObj != None and returnObj != 'null':
            returnObj = "'" + returnObj + "'"
        #拼接sql
        if paramDict.get('sqlColumn', None) == None:
            paramDict['sqlColumn'] = keyValue
        else:
            paramDict['sqlColumn'] = paramDict['sqlColumn'] + ', ' + keyValue
        if paramDict.get('sqlValue', None) == None:
            paramDict['sqlValue'] = str(returnObj)
        else:
            paramDict['sqlValue'] = paramDict['sqlValue'] + ', ' + str(returnObj)
#         return {keyValue, returnObj}
        