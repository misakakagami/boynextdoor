package com.autotestplatform.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Component;

/**
 * MD5工具类
 * @author : 孔德华
 * @date : 2018年5月7日 下午5:46:06  
 * @version : 2018年5月7日 孔德华 创建登录工具类
 */
@Component
public class MD5Utils {

    /**
     * @throws NoSuchAlgorithmException 
     * @Description：得到str的md5加密字符串
     * @param str salt
     * @return: String 返回加密结果
     * @throws
     */
    public static String getMD5(String str, String salt) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(str.getBytes());
        str = new BigInteger(1, md.digest()).toString();
        md.update((str + salt).getBytes());
        str = new BigInteger(1, md.digest()).toString();
        if (str.length() > 10) {
            str = str.substring(0, 9);
        }
        return str;
    }
}
