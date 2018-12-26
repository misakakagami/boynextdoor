#!/usr/bin/env python
# coding=utf-8
# FileName:MoreCaseBase.py
# Todo：所有测试用例的基类


import json
import os, sys
import logging
import pymysql
import requests

class MoreCaseBase:
    __caseCount = 0
    __successCount = 0
    __faildCount = 0

    # 数据库链接游标，用于数据库操作。
    __cursor = 0
    __config=None
    
    def createConnection(self,config):
        # 打开数据库连接
        self.__config = config
        self.__db = pymysql.connect(self.__config.mysql_address, self.__config.mysql_name,
                                        self.__config.mysql_password, self.__config.mysql_schema,
                                        charset='utf8mb4')
        # 使用cursor()方法获取操作游标
        self.__cursor = self.__db.cursor()

    # 执行sql的通用方法
    def executeSql(self, sql):
        # 执行SQL语句
#         logging.info("*****************start executing************************")
        logging.info("execute sql: " + sql)
        self.__cursor.execute(sql)
#         logging.info("*****************finish executing************************")
        # 获取所有记录列表
        return self.__cursor.fetchall()
    
    # 执行sql的通用方法(sql语句,list)
    def executeSqlList(self, sql,list):
        # 执行SQL语句
#         logging.info("*****************start executing************************")
#         logging.info("execute sql: " + sql)
        self.__cursor.execute(sql,list)
#         logging.info("*****************finish executing************************")
        # 获取所有记录列表【返回多个元组，即返回多个记录(rows),如果没有结果 则返回 ()】
        return self.__cursor.fetchall()

    def closeConnection(self):
#         logging.info('****************test end**********************')
#         logging.info('case count: ' + str(MoreCaseBase.__caseCount) + ', success count: ' + str(
#             MoreCaseBase.__successCount) + ', faild count: ' + str(MoreCaseBase.__faildCount))
#         logging.info('**********************************************')
        self.__db.rollback()
        self.__db.cursor().close()
        # 关闭数据库连接
        self.__db.close()
        # todo 向测试系统上报测试结果
#         if MoreCaseBase.__faildCount != 0:
#             sys.exit(-1)
#         sys.exit(0)


 # 接口测试
    @staticmethod
    def interfaceTest(conn, request_url, request_data, request_method):
        headers = {'Content-Type': 'application/json;charset=UTF-8',
                   'Connection': 'keep-alive',
                   'Referer': 'http://' + conn.__config.apihost,
                   'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.134 Safari/537.36'}
        logging.info('===start request===')
        logging.info(str(request_method) + ', ' + str(request_url) + ', ' + str(request_data).replace('\'', '\"').replace('None', 'null'))
        if request_method == 'POST':
            request = requests.post('http://' + conn.__config.apihost + request_url, json=request_data,
                                    headers=headers)
        elif request_method == 'GET':
            request = requests.get('http://' + conn.__config.apihost + request_url, params=request_data,
                                   headers=headers)
        else:
            logging.error('request请求方法错误，请确认[Request Method]字段是否正确！！！')
            logging.info('===end request===')
            conn.checkInterfaseHead([400, request_method])
            return 400, request_method
        status = request.status_code
        respjson = request.json()
        resptext = request.text

        resp = resptext.encode("utf-8")
        if status == 200:
            logging.info('成功，' + str(status) + ', ' + resptext)
            logging.info(' ===end request===')
            # CaseBase.checkInterfaseHead([status, resptext])
            conn.checkInterfaseHead([status, json.loads(resp)])
            return [status, json.loads(resp)]
        else:
            logging.error(' 失败！！！, [ ' + str(status) + ' ], ' + str(respjson))
            logging.info('===end request===')
            conn.checkInterfaseHead([status, json.loads(resp)])
            return status, resptext
        
    @staticmethod
    def checkInterfaseHead(result):
        code = result[0]
        if code != 200:
            raise Exception(code, "接口调用失败")
        isSuccess = result[1]["success"]
        # print("isSuccess",isSuccess)
        if isSuccess != "true":
            resultCode = result[1]['resultCode']
            resultMsg = result[1]['resultMsg']
            logging.info(resultCode + ",返回错误," + resultMsg)
            # raise Exception(resultCode,"返回错误："+str(resultMsg))

    # 通过key获取redis内的value
    @staticmethod
    def getRedisValueByKey(redisKey):
        r = redis.Redis(host=MoreCaseBase.__config.redis_host,port=MoreCaseBase.__config.redis_port,db=0)
        redisValue = r.get(redisKey).decode('utf-8').replace('\"', '')
        return redisValue
