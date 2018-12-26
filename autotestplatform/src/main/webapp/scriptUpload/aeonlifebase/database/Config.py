#!/usr/bin/env python
#coding=utf-8
#FileName:Config.py

import os,sys
import logging
import time

class Config:
    #需要测试接口的host
    apihost = '10.10.20.111:8090'

    #日志输出位置
    log_path='/log'

    #需要链接mysql的信息
    mysql_address='10.2.11.206'
    mysql_name='root'
    mysql_password='funing'
    mysql_schema='iudp_zt_basedata'
