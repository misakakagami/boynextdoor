# -*- coding: utf-8 -*-
"""
-------------------------------------------------
   File Name：     NoticeSqlBase.py 
                               通知书接口/签名交易接口
                               契约作业通知书签名查库方法
   Description         :  
            1 . 获取建议书状态与类型 
            2 . 查询契约通知书
            3 . 查询投保人签名明文PLAINAPPNTSIGN
            4 . 查询投保人签名密文CIPHERAPPNTSIGN
            5 . 查询被保人签名明文PLAININSUREDSIGN
            6 . 查询被保人签名密文CIPHERINSUREDSIGN
   Author              :     张 悦
   date                :     2018/04/24
   UpdateTimeFirst     :     2018/04/27
-------------------------------------------------
"""

from aeonlifebase.CaseBase import CaseBase

from aeonlifebase.notice.Config import Config

from aeonlifebase.notice.EnumOfNotice import EnumOfNotice

from  aeonlifebase.notice.NoticeDao import NoticeDao

import logging

import requests

import json

class NoticeSqlBase(CaseBase):
    
    def __init__(self):
        CaseBase.__init__(self, Config())
        
    
    # 1.获取建议书状态与类型
    # 条件:agent_code=usercode and notice_info_id=noticeNO
    def getNoticeInfoByQueryParam(data):
        userCode = data['userCode']
        noticeNo = data['noticeNo']
        info = {
            "agent_code"    :   userCode, 
            "notice_no"     :   noticeNo
            }
        sqlGetNoticeNoByAgentCode = '''SELECT notice_no FROM `notice_agent_relation` nar 
            WHERE nar.agent_code = %s ''' % info['agent_code']
        sqlGetNoticeInfo = '''SELECT status, notice_type, notice_info_id, sign_role FROM `notice_info` WHERE notice_no 
            in (%s) AND notice_no = '%s' ''' % (sqlGetNoticeNoByAgentCode, info['notice_no'])
        results = CaseBase.executeSql(sqlGetNoticeInfo)
        noticeInfo = {}
        if results:
            noticeInfo = {"status"          :   results[0][0], 
                          "noticeType"      :   results[0][1], 
                          "noticeInfoId"      :   results[0][2], 
                          "signRole"        :   results[0][3]
                          }
        return noticeInfo
    
    
    # 2.查询契约/核保作业/核保决定/转账收费不成功通知书
    def oneOfNoticeFileRelationInfo(data, noticeInfo):
        NoticeInfoId = noticeInfo['noticeInfoId']
        noticeNo = data['noticeNo']
        fileType = noticeInfo['noticeType']
        info = {
            "notice_info_id"    :   NoticeInfoId, 
            "notice_no"         :   noticeNo, 
            "file_type"         :   fileType, 
            "file_id"           :   None, 
            "file_order"        :   None, 
            "url"               :   None
                }
        fileInfoList = data.get('fileInfoList', None)
        businessWorkInfoResults = []
        if fileInfoList:
            for fileInfo in fileInfoList:
                fileId = fileInfo['fileId']
                fileOrder = fileInfo['order']
                url = fileInfo['fileUrl']
                info['file_id'] = fileId
                info['file_order'] = fileOrder
                info['url'] = url
                str = CaseBase.strAppend(info)
                sql = '''SELECT notice_file_relation_id FROM `notice_file_relation` WHERE %s''' % str
                result = CaseBase.executeSql(sql)
                if result:
                    noticeFileRelationInfo = {'noticeFileRelationId' : result[0][0]}
                    businessWorkInfoResults.append(noticeFileRelationInfo)
        return businessWorkInfoResults
    
    
    # 3.查询投保人签名明文PLAINAPPNTSIGN
    def getPlainAppntSignPLAINAPPNTSIGN(data, noticeInfo):
        NoticeInfoId = noticeInfo['noticeInfoId']
        noticeNo = data['noticeNo']
        fileType = EnumOfNotice.PLAINAPPNTSIGN.value
        fileOrder = "1"
        fileId = data['plainAppntSignUrl']
        url = data['plainAppntSignUrl']
        info = {
            "notice_info_id"    :   NoticeInfoId, 
            "notice_no"         :   noticeNo, 
            "file_type"         :   fileType, 
            "file_id"           :   fileId, 
            "file_order"        :   fileOrder, 
            "url"               :   url
                }
        str = CaseBase.strAppend(info)
        sql = '''SELECT notice_file_relation_id FROM `notice_file_relation` WHERE %s''' % str
        result = CaseBase.executeSql(sql)
        plainInsuredSignResults = []
        if result:
            noticeFileRelationInfo = {'notice_file_relation_id' : result[0][0]}
            plainInsuredSignResults.append(noticeFileRelationInfo)
        return plainInsuredSignResults
    
    
    # 4.查询投保人签名密文CIPHERAPPNTSIGN
    def getPlainAppntSignCIPHERAPPNTSIGN(data, noticeInfo):
        NoticeInfoId = noticeInfo['noticeInfoId']
        noticeNo = data['noticeNo']
        fileType = EnumOfNotice.CIPHERAPPNTSIGN.value
        fileOrder = "1"
        fileId = data['cipherAppntSignUrl']
        url = data['cipherAppntSignUrl']
        info = {
            "notice_info_id"    :   NoticeInfoId, 
            "notice_no"         :   noticeNo, 
            "file_type"         :   fileType, 
            "file_id"           :   fileId, 
            "file_order"        :   fileOrder, 
            "url"               :   url
                }
        str = CaseBase.strAppend(info)
        sql = '''SELECT notice_file_relation_id FROM `notice_file_relation` WHERE %s''' % str
        result = CaseBase.executeSql(sql)
        plainInsuredSignResults = []
        if result:
            noticeFileRelationInfo = {'notice_file_relation_id' : result[0][0]}
            plainInsuredSignResults.append(noticeFileRelationInfo)
        return plainInsuredSignResults
    
    
    # 5.查询被保人签名明文PLAININSUREDSIGN
    def getPlainInsuredSignPLAININSUREDSIGN(data, noticeInfo):
        NoticeInfoId = noticeInfo['noticeInfoId']
        noticeNo = data['noticeNo']
        fileType = EnumOfNotice.PLAININSUREDSIGN.value
        fileOrder = "1"
        fileId = data.get('plainInsuredSignUrl', None)
        url = data.get('plainInsuredSignUrl', None)
        info = {
            "notice_info_id"    :   NoticeInfoId, 
            "notice_no"         :   noticeNo, 
            "file_type"         :   fileType, 
            "file_id"           :   fileId, 
            "file_order"        :   fileOrder, 
            "url"               :   url
                }
        str = CaseBase.strAppend(info)
        sql = '''SELECT notice_file_relation_id FROM `notice_file_relation` WHERE %s''' % str
        result = CaseBase.executeSql(sql)
        plainInsuredSignResults = []
        if result:
            noticeFileRelationInfo = {'notice_file_relation_id' : result[0][0]}
            plainInsuredSignResults.append(noticeFileRelationInfo)
        return plainInsuredSignResults
    
    
    # 6.查询被保人签名密文CIPHERINSUREDSIGN
    def getPlainInsuredSignCIPHERINSUREDSIGN(data, noticeInfo):
        NoticeInfoId = noticeInfo['noticeInfoId']
        noticeNo = data['noticeNo']
        fileType = EnumOfNotice.CIPHERINSUREDSIGN.value
        fileOrder = "1"
        fileId = data.get('cipherInsuredSignUrl', None)
        url = data.get('cipherInsuredSignUrl', None)
        info = {
            "notice_info_id"    :   NoticeInfoId, 
            "notice_no"         :   noticeNo, 
            "file_type"         :   fileType, 
            "file_id"           :   fileId, 
            "file_order"        :   fileOrder, 
            "url"               :   url
                }
        str = CaseBase.strAppend(info)
        sql = '''SELECT notice_file_relation_id FROM `notice_file_relation` WHERE %s''' % str
        result = CaseBase.executeSql(sql)
        plainInsuredSignResults = []
        if result:
            noticeFileRelationInfo = {'notice_file_relation_id' : result[0][0]}
            plainInsuredSignResults.append(noticeFileRelationInfo)
        return plainInsuredSignResults
    
    
    # 7.签名交易专用请求接口方法
    def interfaceTest(request_url, request_data, request_method, Config):
        headers = {
            'content-type': "multipart/form-data; boundary=----WebKitFormBoundary7MA4YWxkTrZu0gW;charset=UTF-8",
            'Cache-Control': "no-cache",
            'Postman-Token': "ba05d087-2a07-18eb-6249-79c4edb12739"
            }
        logging.info('===start request===')
        logging.info(str(request_method) + ', ' + str(request_url) + ', ' + str(request_data).replace('\'', '\"').replace('None', 'null'))
        if request_method == 'POST':
            request = requests.post('http://' + Config.apihost + request_url, data=request_data,
                                    headers=headers)
        else:
            logging.error('request请求方法错误，请确认[Request Method]字段是否正确！！！')
            logging.info('===end request===')
            CaseBase.checkInterfaseHead([400, request_method])
            return 400, request_method
        status = request.status_code
        respjson = request.json()
        resptext = request.text

        resp = resptext.encode("utf-8")
        if status == 200:
            logging.info('成功，' + str(status) + ', ' + resptext)
            logging.info(' ===end request===')
            # CaseBase.checkInterfaseHead([status, resptext])
            CaseBase.checkInterfaseHead([status, json.loads(resp)])
            return [status, json.loads(resp)]
        else:
            logging.error(' 失败！！！, [ ' + str(status) + ' ], ')
            logging.info('===end request===')
            CaseBase.checkInterfaseHead([status, json.loads(resp)])
            return status, resptext
    
       
    # 8.签名交易插入数据方法
    def insertData():
        # 成功案例
        # 1057：核保作业通知书
        insertParamsSuccess1057 = {
              'noticeNo': '10570000',
              'notice_info':{
                  'notice_type':EnumOfNotice.UWWORK.value,
                  'status':'0'
                },
              'notice_agent_relation':[{}],
              'notice_customer_relation':[{}]
        }
        # 1051：契约作业通知书
        insertParamsSuccess1051 = {
              'noticeNo': '10510000',
              'notice_info':{
                  'notice_type':EnumOfNotice.NEW_BUSINESS_WORK.value,
                  'status':'0'
                },
              'notice_agent_relation':[{}],
              'notice_customer_relation':[{}]
        }
        # 1069：核保决定通知书
        insertParamsSuccess1069 = {
              'noticeNo': '10690000',
              'notice_info':{
                  'notice_type':EnumOfNotice.UWDECISION.value,
                  'status':'0'
                },
              'notice_agent_relation':[{}],
              'notice_customer_relation':[{}]
        }
        # 1050：转账收费不成功通知书
        insertParamsSuccess1050 = {
              'noticeNo': '10500000',
              'notice_info':{
                  'notice_type':EnumOfNotice.TRANSFERPAY.value,
                  'status':'0'
                },
              'notice_agent_relation':[{}],
              'notice_customer_relation':[{}]
        }
        # 失败案例
        # 1057：核保作业通知书
        insertParamsFaild1057 = {
              'noticeNo': '10571111',
              'notice_info':{
                  'notice_type':EnumOfNotice.UWWORK.value,
                  'status':'0'
                },
              'notice_agent_relation':[{}],
              'notice_customer_relation':[{}]
        }
        # 1051：契约作业通知书
        insertParamsFaild1051 = {
              'noticeNo': '10511111',
              'notice_info':{
                  'notice_type':EnumOfNotice.NEW_BUSINESS_WORK.value,
                  'status':'0'
                },
              'notice_agent_relation':[{}],
              'notice_customer_relation':[{}]
        }
        # 1069：核保决定通知书
        insertParamsFaild1069 = {
              'noticeNo': '10691111',
              'notice_info':{
                  'notice_type':EnumOfNotice.UWDECISION.value,
                  'status':'0'
                },
              'notice_agent_relation':[{}],
              'notice_customer_relation':[{}]
        }
        # 1050：转账收费不成功通知书
        insertParamsFaild1050 = {
              'noticeNo': '10501111',
              'notice_info':{
                  'notice_type':EnumOfNotice.TRANSFERPAY.value,
                  'status':'0'
                },
              'notice_agent_relation':[{}],
              'notice_customer_relation':[{}]
        }
        
        insertParamsList = [insertParamsSuccess1057, insertParamsSuccess1051, insertParamsSuccess1069, 
            insertParamsSuccess1050, insertParamsFaild1057, insertParamsFaild1051, insertParamsFaild1069, insertParamsFaild1050]
        deltDictList = []
        for insertParams in insertParamsList:
            deltDict = {}
            delDict = NoticeDao.insertTestData(insertParams)
            deltDictList.append(delDict)
        return deltDictList
    
    
    # 9.签名交易删除数据方法
    def deleteData(deltDictList):
        for deltDict in deltDictList:
            NoticeDao.delTestDataByNoticeNo(deltDict)
    
    
    
