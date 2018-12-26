#!/usr/bin/env python
# coding=utf-8
import hashlib

class PwdCreateMD5:

    @staticmethod
    def createMD5(str):
        chars64 = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O',
                   'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
                   'j',
                   'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3',
                   '4',
                   '5', '6', '7', '8', '9', '+', '/']
        m2 = hashlib.md5()
        m2.update(bytes(str, encoding='utf-8'))
        code = m2.digest()
        byteCount = 0
        carryOver = 0
        li = list(code)
        res = []
        for index in range(len(li)):
            bc = byteCount % 3
            # tb = li[index]
            # b = ord(tb)
            b = li[index]
            lookup = 0
            if bc == 0:
                lookup = b >> 2 & 0x3F
                carryOver = b & 0x3
                res.append(chars64[lookup])
            elif bc == 1:
                lookup = carryOver << 4 | b >> 4 & 0xF
                carryOver = b & 0xF
                res.append(chars64[lookup])
            elif bc == 2:
                lookup = carryOver << 2 | b >> 6 & 0x3
                res.append(chars64[lookup])
                lookup = b & 0x3F
                res.append(chars64[lookup])
                carryOver = 0
            byteCount = byteCount + 1
        if byteCount % 3 == 1:
            lookup = carryOver << 4 & 0xF0
            res.append(chars64[lookup])
            res.append('=')
            res.append('=')
        elif byteCount % 3 == 2:
            lookup = carryOver << 2 & 0x3C
            res.append(chars64[lookup])
            res.append('=')
        return ''.join(res)