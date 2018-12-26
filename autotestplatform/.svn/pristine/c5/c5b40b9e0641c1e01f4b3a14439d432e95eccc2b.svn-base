package com.autotestplatform.utils;

/**
 * 自定义异常类
 * @author : 孔德华
 * @date : 2018年5月8日 下午3:03:23  
 * @version : 2018年5月8日  孔德华 创建类
 */
public class CustomException extends Exception {

    /** 
    * @Fields serialVersionUID 
    */  
    private static final long serialVersionUID = 1339211096623435857L;
    
    /**
     * 错误代码
     */
    private String errorCode;
    
    /**
     * 消息是否为属性中的key
     */
    private boolean propertiesKey = true;
    
    public CustomException(String message) {
        super(message);
    }
    
    public CustomException(String errorCode,String message) {
        this(errorCode,message, true);
    }
    
    public CustomException(String errorCode,String message,Throwable cause) {
        this(errorCode,message,cause, true);
    }
    
    public CustomException(String errorCode,String message,boolean propertiesKey) {
        super(message);
        this.setErrorCode(errorCode);
        this.setPropertiesKey(propertiesKey);
    }
    
    public CustomException(String errorCode, String message, Throwable cause, boolean propertiesKey) {
        super(message, cause);
        this.setErrorCode(errorCode);
        this.setPropertiesKey(propertiesKey);
    }
    
    public CustomException(String message,Throwable cause) {
        super(message, cause);
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public boolean isPropertiesKey() {
        return propertiesKey;
    }

    public void setPropertiesKey(boolean propertiesKey) {
        this.propertiesKey = propertiesKey;
    }

}
