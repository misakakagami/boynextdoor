# -*- coding: utf-8 -*-
"""
-------------------------------------------------
   File Name   : testPolicyList.py 
   Description : 在线承保-获取保单列表  
   Author      : 孔德华
   date        : 2018/03/06
   UpdateTime  : 2018/03/06
-------------------------------------------------
"""

from  aeonlifebase.CaseBase import CaseBase
from  aeonlifebase.policy.PolicyDao import PolicyDao
from  aeonlifebase.policy.Config import Config
import logging
import xlrd
import json
import os

# 在线承保-获取保单列表测试脚本
class testPolicyList(CaseBase):
    
    def __init__(self):
        CaseBase.__init__(self, Config())

    def trueM(self,trueInput,point):
        logging.info(trueInput)
        logging.info(point)
        return [True]

    def falseM(self,trueInput,point):
        logging.info(trueInput)
        logging.info(point)
        return [True]


    def startTest(self):
        #插入测试数据
        logging.info('*****************start insert test data*****************')
#         delDict = PolicyDao.insertTestData(paramDict2)
#         delDict = PolicyDao.insertTestData(p)
        try:
            logging.info('*****************insert test data finish*****************\n')
            pass
            #读excel
            excel = str(os.path.abspath('.')) + "/src/main/webapp" + "/exampleUpload/use6.xlsx"#1"5161718192021"
            logging.info(excel)
            successList = CaseBase.readInputListByColumnName(excel, '测试成功案例', '执行案例数据')
            successPoint = CaseBase.readInputListByColumnName(excel, '测试成功案例', '测试点')
            successTagList = CaseBase.readInputListByColumnName(excel, '测试成功案例', 'Tag')
            print('sss')
            # 测试正确入参
            for index in range(len(successList)):
                if CaseBase.isMatch(successTagList[index], "T"):
                    trueInput = json.loads(successList[index])
                    point = successPoint[index]
                    CaseBase.startCase('TrueCase_%03d'%(index+1),self.trueM, trueInput, point)
            # 测试错误入参
            failedList = CaseBase.readInputListByColumnName(excel, '测试失败案例', '执行案例数据')
            faildPoint = CaseBase.readInputListByColumnName(excel, '测试失败案例', '测试点')
            failedTagList = CaseBase.readInputListByColumnName(excel, '测试失败案例', 'Tag')
            for index in range(len(failedList)):
                if CaseBase.isMatch(failedTagList[index], "T"):
                    faildInput = json.loads(failedList[index])
                    point = faildPoint[index]
                    CaseBase.startCase('FalseCase_%03d'%(index+1),self.falseM, faildInput, point)
        finally:
            #删除测试数据
            logging.info('*****************start delete test data*****************')
            logging.info('*****************delete test data finish*****************\n')


if __name__ == '__main__':
    c = testPolicyList()
    c.startTest()
    CaseBase.printTestResult()
