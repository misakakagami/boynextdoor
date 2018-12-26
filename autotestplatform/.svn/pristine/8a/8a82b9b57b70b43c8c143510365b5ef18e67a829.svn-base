# -*- coding: utf-8 -*-
"""
------------------------------------------------
    File Name:      UserDao.py
    Description:    用户管理 数据处理层
    Author:         孔德华
    date:           2018/04/12
    Copyright:      (c)   孔德华2018
------------------------------------------------
    Change Record:
       2018/04/12 创建文件，测试数据插入方法，全字段查询方法
"""

import json
from aeonlifebase.CaseBase import CaseBase
from aeonlifebase.user.Config import Config
from decimal import *
import logging
import time
from copy import deepcopy

class UserDao():
    
    userColumn = ""
    
    def getUserInfo(paramDict):
        