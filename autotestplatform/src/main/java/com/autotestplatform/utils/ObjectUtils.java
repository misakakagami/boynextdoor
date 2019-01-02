package com.autotestplatform.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.autotestplatform.dto.common.PageDto;

/**
 * 对象工具类
 * @author : 孔德华
 * @date : 2018年5月8日 下午3:56:46  
 * @version : 2018年5月8日  孔德华 创建类
 */
@Component
public class ObjectUtils {

    /**
     * @param <T>
     * @Description：为空时初始化实例，若有页码对象则初始化一个页码对象实例
     * @param object: 返回结果描述
     * @return void: 返回值类型
     * @throws
     */
    public static <T> Object setValueIfNull(Object object, Class<T> clazz, Integer pageSize) throws Exception {
        //为空则根据类型实例化
        if (object == null) {
            object = clazz.newInstance();
        }
        //若为空的话实例化页码对象
        try {
            Method m = object.getClass().getMethod("getPageDto", new Class[] {});
            if (m.invoke(object, new Object[] {}) == null) {
                Field field = clazz.getDeclaredField("pageDto");
                field.setAccessible(true);
                field.set(object, new PageDto(1, pageSize));
            }
        } catch (Exception e) {
            //对象不包含页码对象
        }
        return object;
    }

    /**
     * @Description：相同属性的对象转换（dto与实体类）（以object的成员属性为准）
     * @param object 原对象
     * @param clazz 目标对象的类型
     * @throws Exception: 返回结果描述
     * @throws
     */
    public static <T> Object exchangeObj(Object object, Class<T> clazz) throws Exception {
        //if null then return a instance
        Object returnObj = clazz.newInstance();
        if (object == null) {
            return returnObj;
        }
        //set value from object to new instance
        try {
            Field[] fields = object.getClass().getDeclaredFields();
            Method m = null;
            Field setField = null;
            String objectGetMethod = null;
            Object value = null;
            for (Field field : fields) {
                try {
                    if ("serialVersionUID".equals(field.getName())) {
                        continue;
                    }
                    objectGetMethod = "get" + field.getName().substring(0, 1).toUpperCase()
                            + field.getName().substring(1, field.getName().length());
                    m = object.getClass().getMethod(objectGetMethod, new Class[] {});
                    value = m.invoke(object, new Object[] {});
                    setField = clazz.getDeclaredField(field.getName());
                    setField.setAccessible(true);
                    setField.set(returnObj, value);
                } catch (Exception e) {
                    continue;
                }
            }
        } catch (Exception e) {
            //exception
            e.printStackTrace();
        }
        return returnObj;
    }

}
