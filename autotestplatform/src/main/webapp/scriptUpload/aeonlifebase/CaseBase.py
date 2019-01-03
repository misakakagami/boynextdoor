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
import urllib.request
import datetime
import re
import xmltodict
from sqlalchemy.util.langhelpers import NoneType
import chardet

class CaseBase:
    __caseCount = 0
    __successCount = 0
    __faildCount = 0
    __faildCasesId = []
    key = ""
    listKey = ""
    compareResult = True
    compareResultList = []
    compareResultDict = []
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
#         console = logging.StreamHandler()
        console = logging.FileHandler(log_file, encoding='utf-8')
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
        CaseBase.__faildCasesId.append(caseid)
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
        if not CaseBase.isMatch(CaseBase.__faildCount, 0):
            logging.info("faild count id:" + str(CaseBase.__faildCasesId))
        CaseBase.__db.rollback()
        CaseBase.__db.cursor().close()
        # 关闭数据库连接
        CaseBase.__db.close()
        # todo 向测试系统上报测试结果
        try:
            if not CaseBase.isMatch(CaseBase.__faildCount, 0):
                sys.exit(-1)
            sys.exit(0)
        finally:
            logging.info('**********************************************')
            if not CaseBase.isMatch(CaseBase.__faildCount, 0):
                return [False, CaseBase.__faildCasesId]
            return True

#     @staticmethod
#     def printTestResultByCommit():
#         logging.info('****************test end**********************')
#         logging.info('case count: ' + str(CaseBase.__caseCount) + ', success count: ' + str(
#             CaseBase.__successCount) + ', faild count: ' + str(CaseBase.__faildCount))
#         if not CaseBase.isMatch(CaseBase.__faildCount, 0):
#             logging.info("faild count id:" + str(CaseBase.__faildCasesId))
#         CaseBase.__db.commit()
#         CaseBase.__db.cursor().close()
#         # 关闭数据库连接
#         CaseBase.__db.close()
#         # todo 向测试系统上报测试结果
#         try:
#             if not CaseBase.isMatch(CaseBase.__faildCount, 0):
#                 sys.exit(-1)
#             sys.exit(0)
#         finally:
#             logging.info('**********************************************')
#             if not CaseBase.isMatch(CaseBase.__faildCount, 0):
#                 return [False, CaseBase.__faildCasesId]
#             return True

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
    
    # webservice接口测试
    @staticmethod
    def webServiceInteTest(request_url, paramData):
        postcontent = '<?xml version="1.0" encoding="utf-8"?>'
        postcontent += paramData
        req = urllib.request.Request('http://' + CaseBase.__config.apihost + request_url,data=postcontent.encode('GBK'),headers={'Content-Type': 'text/xml'})
        rsp = urllib.request.urlopen(req)
        resList = rsp.readlines()
        resStr = ""
        for row in resList:
            try:
                rs = row.decode()
            except:
                #解码出错时找到对应编码格式进行解码    
                rs = row.decode(chardet.detect(row)['encoding'])
            rs = rs.replace("&lt;", "<")
            #汉子转码解析             
            rs = re.sub(r'&#x....;', lambda match: CaseBase.convert(match.group()),rs)
            resStr += rs
        return resStr
    
    @staticmethod
    def convert(s):
        s = s.strip('&#x;')
        #转成ascii bytes         
        s = bytes(r'\u' + s, 'ascii')
        return s.decode('unicode_escape')
    
    #===========================================================================
    # 生成14位时间戳与随机数混合数字字符
    #===========================================================================
    @staticmethod
    def generateRandom():
        nowTime=datetime.datetime.now().strftime("%Y%m%d%H%M%S")
        result = nowTime[5:] + str(random.randint(10000,99999))
        return result
    
    
    #===========================================================================
    # 定义xml转dict的函数  
    #===========================================================================
    @staticmethod
    def xmlToJson(xmlstr):  
        xmlParse = xmltodict.parse(xmlstr)  
        jsonStr = json.dumps(xmlParse,indent=1)
        jsonStr = json.loads(jsonStr)
        return jsonStr
    
    
    #===========================================================================
    # 对比两个dict【以dict1为准】,返回结果为(最终对比结果,各节点的对比结果组装成的list),
    # list的元素为dictkey和对比结果的信息。例如：[{"key":True/False}]
    # 当前方法元素为dict,当dict内value为列表时不要求顺序
    # 如:{"a":[1,4,7]}与{"a":[4,1,7]}最后对比结果为True
    #===========================================================================
    @staticmethod
    def compareDict(dict1, dict2):
        if not CaseBase.isMatch(type(dict1), type(dict2)):
            return False, [{"对比结果:":"接口与脚本结果类型不一致"}]
        if not (isinstance(dict1, (dict, NoneType)) and isinstance(dict2, (dict, NoneType))):
            return False, [{"对比结果:":"传入类型不为dict"}]
        if not dict1:
            dict1 = {"传入空值时特殊处理":"NoneType"}
        if not dict2:
            dict2 = {"传入空值时特殊处理":"NoneType"}
        result = True
        compareResultList = []
        for k1,v1 in dict1.items():
            resultInfo = None
            ifHasThisKey = None
            for k2,v2 in dict2.items():
                if k1 == k2:
                    # 目前无法处理list形式如["a",{"b":1}]形式的,此种情况无法对list进行排序
                    # [非要支持此种验证还要加两层循环]，另外中台目前所有接口没有这种形式的，列表内全为映射数据
                    key = "Test"
                    if isinstance(v1, list):
                        if v1:
                            # list中元素为dict
                            if isinstance(v1[0], dict):
                                
                                for k,v in v1[0].items():
                                    key = k
                                    break
                                v1.sort(key = lambda k: (k.get(key, 0)))
                            else:
                                v1.sort()
                    if isinstance(v2, list):
                        if v2 and not CaseBase.isMatch(key, "Test"):
                            if isinstance(v2[0], dict):
                                v2.sort(key = lambda k: (k.get(key, 0)))
                            else:
                                v2.sort()
                    resultInfo = {"%s" % k1: v1 == v2}
                    compareResultList.append(resultInfo)
                    break
            # 是否有此键
            for compareResult in compareResultList:
                ifHasThisKey = compareResult.__contains__("%s" % k1)
                if ifHasThisKey:
                    break
            if not ifHasThisKey:
                resultInfo = {"%s" % k1: False}
                compareResultList.append(resultInfo)
            if not resultInfo["%s" % k1]:
                result = False
        return result, compareResultList
    
    
    #===========================================================================
    # 获取带有绝对路径的文件名
    #===========================================================================
    @staticmethod
    def getAbsolutePathFileName(fileName):
        pathHead = os.getcwd()
        if "\\" in pathHead:
            pathHead = pathHead[:os.getcwd().rfind("\\")]
        else:
            pathHead = pathHead[:os.getcwd().rfind("/")]
        fileName = (pathHead + fileName).replace("\\", "/")
        return fileName
    
    
    #===========================================================================
    # json格式数据对比,以dict1为准，对比dict2（dict1与dict2可以为任何类型）,
    # 第三个参数为是否关心取值[如1，与'1']，第四个参数为是否关心列表顺序,
    # 第五个参数为是否需要对列表再次排序
    #===========================================================================
    @staticmethod
    def compareDictOrList(dict1, dict2, ifCareValue = False, ifCareSequence = False, ifOrder = False):
        compareResultList = []
        if isinstance(dict1, dict):
            if isinstance(dict2, dict): 
                for key in dict1:
                    CaseBase.key = key
                    if dict2.__contains__(key):
                        CaseBase.compareDictOrList(dict1[key], dict2[key])
                    else:
                        CaseBase.compareResult = False
                        resultInfo = {key: False}
                        CaseBase.compareResultList.append(resultInfo)
                return CaseBase.compareResult, CaseBase.compareResultList
            else:
                CaseBase.compareResult = False
                resultInfo = {CaseBase.key: False}
                CaseBase.compareResultList.append(resultInfo)
                return CaseBase.compareResult, CaseBase.compareResultList
        elif isinstance(dict1, list):
            CaseBase.listKey = CaseBase.key
            if isinstance(dict2, list):
                if not ifCareSequence:
                    if ifOrder:
                        try:
                            for k1, v1 in dict1[0].items():
                                dict1.sort(key = lambda k: (k.get(k1, "None")))
                                break
                        except:
                            dict1.sort()
                        try:
                            dict2.sort(key = lambda k: (k.get(k1, "None")))
                        except:
                            dict2.sort()
            else:
                resultInfo = {CaseBase.listKey : False}
                CaseBase.compareResult = False
                CaseBase.compareResultList.append(resultInfo)
                return CaseBase.compareResult, CaseBase.compareResultList
            if len(dict1) == len(dict2):
                for index in range(len(dict1)):
                    CaseBase.compareDictOrList(dict1[index], dict2[index])
                    compareResultList = CaseBase.compareResultList[-len(dict1[index]):]
                    CaseBase.compareResultList = CaseBase.compareResultList[:-len(dict1[index])]
                    CaseBase.compareResultList.append(compareResultList)
                compareResultList = CaseBase.compareResultList[-len(dict1):]
                CaseBase.compareResultList = CaseBase.compareResultList[:-len(dict1)]
                CaseBase.compareResultList.append(compareResultList)
            else:
                CaseBase.compareResult = False
                if CaseBase.listKey:
                    resultInfo = {"%s" % CaseBase.listKey: False}
                else:
                    resultInfo = {"列表长度不一致" : False}
                CaseBase.compareResultList.append(resultInfo)
            return CaseBase.compareResult, CaseBase.compareResultList
        else:
            result = dict1 == dict2
            if not result:
                if not ifCareValue:
                    try:
                        dict1 = str(dict1).rstrip('0')
                        dict2 = str(dict2).rstrip('0')
                    except:
                        pass
                    result = dict1 == dict2
            if not result:
                CaseBase.compareResult = False
            resultInfo = {CaseBase.key: result}
            CaseBase.compareResultList.append(resultInfo)
            return CaseBase.compareResult, CaseBase.compareResultList
    
    
    #===========================================================================
    # json格式dict节点值为空时去掉节点，第二个参数为是否保留值为list的节点
    #===========================================================================
    @staticmethod
    def deleteJsonNonePoint(Key, ifSaveNoneListPoint = True):
        for key in list(Key.keys()):
            if ifSaveNoneListPoint:
                if isinstance(Key[key], list):
                    continue
            if not Key[key]:
                Key.pop(key)

