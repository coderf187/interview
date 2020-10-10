package com.alibaba.test2;

import java.lang.reflect.Field;

public class ReflectUtil {

    /**
     * 得到属性值
     *
     * @param obj
     * @param property
     */
    public static Object getAttributeValue(Object obj, String property) {
        Object value = null;
        //得到class
        Class cls = obj.getClass();
        //得到所有属性
        Field[] fields = cls.getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            try {
                //得到属性
                Field field = fields[i];
                //打开私有访问
                field.setAccessible(true);
                //获取属性
                String name = field.getName();
                if (property.equals(name)) {
                    //获取属性值
                    value = field.get(obj);
                }
            } catch (IllegalAccessException e) {

            }
        }
        return value;
    }
}
