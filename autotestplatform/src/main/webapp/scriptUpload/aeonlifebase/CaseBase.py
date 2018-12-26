#!/usr/bin/env python
# coding=utf-8
# Updatetime：2018/04/03
# msg：新增执行删除sql方法

import json
import http.client, mimetypes
import os, sys
import logging
import pymysql
import time
import requests
import xlrd
import redis
import random
import hashlib

class CaseBase:
    __caseCount = 0
    __successCount = 0
    __faildCount = 0

    # 数据库链接游标，用于数据库操作。
    __cursor = 0
    __db = None

    def __init__(self, config):
        CaseBase.__config = config
        # 打开数据库连接
        CaseBase.__db = pymysql.connect(CaseBase.__config.mysql_address, CaseBase.__config.mysql_name,
                                        CaseBase.__config.mysql_password, CaseBase.__config.mysql_schema,
                                        charset='utf8mb4')
        # 使用cursor()方法获取操作游标
        CaseBase.__cursor = CaseBase.__db.cursor()
        # 日志设置
        num = random.randint(1000, 9999)
        now = time.strftime("%Y%m%d%H%M", time.localtime())
        file_name = 'InterfaceDemoTest' + now + str(num) +'.log'
        #src开始的全相对路径(生成日志的路径)
        log_file = 'src/main/webapp/testLogs/' + file_name
        #webapp开始的相对路径(py寻找日志的路径)
        rela_log_file_path = 'testLogs/' + file_name
        print("log_file", log_file)
        log_format = '[%(asctime)s] [%(levelname)s] %(message)s'
        logging.basicConfig(format=log_format, filename=log_file, filemode='w', level=logging.DEBUG)
        console = logging.StreamHandler()
        console.setLevel(logging.DEBUG)
        formatter = logging.Formatter(log_format)
        console.setFormatter(formatter)
        logging.getLogger('').addHandler(console)
    
    
    #更换数据库方法
    def changeSchema(schema):
        CaseBase.__db = pymysql.connect(CaseBase.__config.mysql_address, CaseBase.__config.mysql_name,
                                        CaseBase.__config.mysql_password, schema,
                                        charset='utf8mb4')
        CaseBase.__cursor = CaseBase.__db.cursor()
        
        
    #事务提交
    def commit():
        CaseBase.__db.commit()
        
    #事务回滚
    def rollBack():
        CaseBase.__db.rollback()

        
    # 执行sql的通用方法
    @staticmethod
    def executeSql(sql):
        # 执行SQL语句
#         logging.info("execute sql: " + sql)
        CaseBase.__cursor.execute(sql)
        # 获取所有记录列表
        return CaseBase.__cursor.fetchall()
        
    # 专门执行删除的方法
    @staticmethod
    def executeDelSql(sql):
        # 执行删除SQL语句
#         logging.info("execute sql: " + sql)
        CaseBase.__cursor.execute(sql)
        # 获取所有记录列表
#         return CaseBase.__cursor.fetchall()
    
    
    # 执行sql（插入）获得序列号的方法
    @staticmethod
    def executeInsertSql(sql):
        # 执行SQL语句
#         logging.info("execute sql: " + sql)
        CaseBase.__cursor.execute(sql)
        # 获取所有记录列表
        return CaseBase.__cursor.lastrowid
    

    @staticmethod
    def startCase(caseid, func, *args):
        CaseBase.__caseCount += 1
        logging.info("*****************start case " + caseid + "************************")
        try:
            result = func(*args)
            # print("startCaseresult:", result)
        except Exception as e:
            logging.error(e)
            CaseBase._addFaild(caseid, e.args[1], e.args[0])
            return
        if result[0] == True:
            CaseBase._addSuccess(caseid)
        else:
            CaseBase._addFaild(caseid, result[1], result[2])

    @staticmethod
    def _addSuccess(caseid):
        CaseBase.__successCount += 1
        logging.info("****************case " + caseid + " success****************")
        # todo 在这个位置增加上报操作，把结果上报给自动测试系统
        logging.info("本场景测试通过！")
        logging.info("****************case " + caseid + " success****************\n")


    @staticmethod
    def _addFaild(caseid, message, code):
        CaseBase.__faildCount += 1
        logging.error("***************case " + caseid + " faild******************")
        logging.error("error code: " + str(code))
        logging.error("error message: " + message)
        # todo 在这个位置增加上报操作，把结果上报给自动测试系统
        logging.error("***************case " + caseid + " faild******************\n")


    @staticmethod
    def printTestResult():
        logging.info('****************test end**********************')
        logging.info('case count: ' + str(CaseBase.__caseCount) + ', success count: ' + str(
            CaseBase.__successCount) + ', faild count: ' + str(CaseBase.__faildCount))
        logging.info('**********************************************')
        CaseBase.__db.rollback()
        CaseBase.__db.cursor().close()
        # 关闭数据库连接
        CaseBase.__db.close()
        # todo 向测试系统上报测试结果
        if CaseBase.__faildCount != 0:
            sys.exit(-1)
        sys.exit(0)


    @staticmethod
    def printTestResultByCommit():
        logging.info('****************test end**********************')
        logging.info('case count: ' + str(CaseBase.__caseCount) + ', success count: ' + str(
            CaseBase.__successCount) + ', faild count: ' + str(CaseBase.__faildCount))
        logging.info('**********************************************')
        CaseBase.__db.commit()
        CaseBase.__db.cursor().close()
        # 关闭数据库连接
        CaseBase.__db.close()
        # todo 向测试系统上报测试结果
        if CaseBase.__faildCount != 0:
            sys.exit(-1)
        sys.exit(0)


    @staticmethod
    def checkInterfaseHead(result):
        code = result[0]
        if code != 200:
            raise Exception(code, "接口调用失败")


    @staticmethod
    def isMatch(first, second):
        if first is None and second == 0:
            return True
        if first == 0 and second is None:
            return True
        if first == second:
            return True
        return False

    
    @staticmethod
    def store(file ,data):
        with open(file, 'w') as json_file:
            json_file.write(json.dumps(data))


    @staticmethod
    def load(file):
        with open(file, encoding='utf-8') as json_file:
            data = json.load(json_file)
            return data


    @staticmethod
    def getColumnIndex(table, columnName):
        columnIndex = None
        for i in range(table.ncols):
            if (table.cell_value(0, i) == columnName):
                columnIndex = i
                break
        return columnIndex


    @staticmethod
    def readExcelDataByIndex(fileName, sheetIndex):
        data = xlrd.open_workbook(fileName)
        table = data.sheet_by_index(sheetIndex)
        return table.cell_value()

    
    @staticmethod
    def readInputListByIndex(fileName, sheetName, index):
        workbook = xlrd.open_workbook(fileName)
        sheet = workbook.sheet_by_name(sheetName)
        inputlist = sheet.col_values(index, start_rowx=1)
        return inputlist
    
    
    @staticmethod
    def readInputListByColumnName(fileName, sheetName, columnName):
        workbook = xlrd.open_workbook(fileName)
        sheet = workbook.sheet_by_name(sheetName)
        index = CaseBase.getColumnIndex(sheet, columnName)
        inputlist = sheet.col_values(index, start_rowx=1)
        return inputlist


    @staticmethod
    def readInputDataBySheetname(fileName, sheetName, x,y ):
        workbook = xlrd.open_workbook(fileName)
        sheet = workbook.sheet_by_name(sheetName)
        return sheet.cell(x,y)
    
    
    # 通过key获取redis内的value
    @staticmethod
    def getRedisValueByKey(redisKey):
        r = redis.Redis(host=CaseBase.__config.redis_host,port=CaseBase.__config.redis_port,db=0)
        redisValue = r.get(redisKey).decode('utf-8').replace('\"', '')
        return redisValue


    # 接口测试
    @staticmethod
    def interfaceTest(request_url, request_data, request_method):
        headers = {'Content-Type': 'application/json;charset=UTF-8',
                   'Connection': 'keep-alive',
                   'Referer': 'http://' + CaseBase.__config.apihost,
                   'User-Agent': 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/43.0.2357.134 Safari/537.36'}
        logging.info('===start request===')
        logging.info(str(request_method) + ', ' + str(request_url) + ', ' + str(request_data).replace('\'', '\"').replace('None', 'null'))
        if request_method == 'POST':
            request = requests.post('http://' + CaseBase.__config.apihost + request_url, json=request_data,
                                    headers=headers)
        elif request_method == 'GET':
            request = requests.get('http://' + CaseBase.__config.apihost + request_url, params=request_data,
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
            logging.error(' 失败！！！, [ ' + str(status) + ' ], ' + str(respjson))
            logging.info('===end request===')
            CaseBase.checkInterfaseHead([status, json.loads(resp)])
            return status, resptext


    # 将字典变成，key='value' AND key='value'的形式
    @staticmethod
    def strAppend(dictIn):
        str1 = []
        for k, v in dictIn.items():
            if v:
                sqlStr = "%s ='%s'" %(str(k), str(v))
                str1.append(' ' + sqlStr + ' ')
        return ' AND '.join(str1)
    
    
    # 执行测试案例,参数为 (需要读取的表格，测试成功案例，测试的接口)
    # 优化：以往测试失败案例判断条件几乎相同，代码冗余
    @staticmethod
    def executeTestCase(fileName, func, interfaceName):
        CaseBase.trueCase(fileName, func, interfaceName)
        CaseBase.falseCase(fileName, interfaceName)
    
    
    # 测试正确入参
    @staticmethod
    def trueCase(fileName, func, interfaceName):
        testPointList = CaseBase.readInputListByColumnName(fileName, '测试成功案例', '测试点')
        successList = CaseBase.readInputListByColumnName(fileName, '测试成功案例', '执行案例数据')
        successTagList = CaseBase.readInputListByColumnName(fileName, '测试成功案例', 'Tag')
        for index in range(len(successList)):
            if CaseBase.isMatch(successTagList[index], "T"):
                data1 = eval(successList[index])
                logging.info('测试点:%s'%testPointList[index])
                CaseBase.startCase('成功案例表格第%03d条'%(index+2), func, interfaceName, data1)
    
    
    # 测试错误入参，需要传入失败案例函数(适用于失败案例函数特殊的情况)
    @staticmethod
    def falseCaseFunc(fileName, func, interfaceName):
        testPointList = CaseBase.readInputListByColumnName(fileName, '测试失败案例', '测试点')
        failedList = CaseBase.readInputListByColumnName(fileName, '测试失败案例', '执行案例数据')
        failedTagList = CaseBase.readInputListByColumnName(fileName, '测试失败案例', 'Tag')
        for index in range(len(failedList)):
            if CaseBase.isMatch(failedTagList[index], "T"):
                data2 = eval(failedList[index])
                logging.info('测试点:%s'%testPointList[index])
                CaseBase.startCase('失败案例表格第%03d条'%(index+2), func, interfaceName, data2)
    
    
    # 测试错误入参
    @staticmethod
    def falseCase(fileName, interfaceName):
        testPointList = CaseBase.readInputListByColumnName(fileName, '测试失败案例', '测试点')
        failedList = CaseBase.readInputListByColumnName(fileName, '测试失败案例', '执行案例数据')
        failedTagList = CaseBase.readInputListByColumnName(fileName, '测试失败案例', 'Tag')
        for index in range(len(failedList)):
            if CaseBase.isMatch(failedTagList[index], "T"):
                data2 = eval(failedList[index])
                logging.info('测试点:%s'%testPointList[index])
                CaseBase.startCase('失败案例表格第%03d条'%(index+2), CaseBase.testFalseCase, interfaceName, data2)
    
    
    # 错误入参，程序未通过即成功
    @staticmethod
    def testFalseCase(interfaceName, data):
        # 接口测试执行返回的数据(状态码,出参)
        result = CaseBase.interfaceTest(interfaceName, data, 'POST')
        if CaseBase.isMatch(result[1]['success'],'false'):
            if CaseBase.isMatch(result[1]['resultMsg'], '未知系统异常'):
                return [False,"测试失败,接口出现未知系统异常",-1]
            return [True]
        return [False,"测试失败,错误入参不能识别",-1]
    
    
    # 四舍五入
    @staticmethod        
    def roundUp(num):
        if not ('.' in str(num)):
            return int(num)
        else:
            numList = str(num).split('.')
            if int(numList[1][0]) >= 5:
                return int(numList[0]) + 1
            else:
                return int(numList[0])
    