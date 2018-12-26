package com.autotestplatform.utils;

import org.springframework.stereotype.Component;

/**
 * 字符串工具类
 * @author : 孔德华
 * @date : 2018年5月7日 下午6:57:43  
 * @version : 2018年5月7日 孔德华 创建工具类
 */
@Component
public class StringUtils {

    /**
    StringUtils.isEmpty(null)      = true<br/>
    StringUtils.isEmpty("")        = true<br/>
    StringUtils.isEmpty(" ")       = false<br/>
    StringUtils.isEmpty("bob")     = false<br/>
    StringUtils.isEmpty("  bob  ") = false<br/>
     * @Description：
     * @param cs
     * @return: 返回结果描述
     * @return boolean: 返回值类型
     * @throws
     */
    public static boolean isEmpty(CharSequence cs) {
        return (cs == null) || (cs.length() == 0);
    }

    /**
    StringUtils.isBlank(null)      = true<br/>
    StringUtils.isBlank("")        = true<br/>
    StringUtils.isBlank(" ")       = true<br/>
    StringUtils.isBlank("bob")     = false<br/>
    StringUtils.isBlank("  bob  ") = false<br/>
     * @Description：
     * @param cs
     * @return: 返回结果描述
     * @return boolean: 返回值类型
     * @throws
     */
    public static boolean isBlank(CharSequence cs) {
        int strLen;
        if ((cs == null) || ((strLen = cs.length()) == 0))
            return true;
        strLen = cs.length();
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }

}
