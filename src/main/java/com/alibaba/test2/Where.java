package com.alibaba.test2;

import java.io.Serializable;

public class Where implements Serializable {

    /**
     * 属性
     */
    private String property;

    /**
     * 条件
     */
    private ConditionEnum condition;

    /**
     * 值
     */
    private Object value;

    public Where(String property, ConditionEnum condition, Object value) {
        this.property = property;
        this.condition = condition;
        this.value = value;
    }

    public ConditionEnum getCondition() {
        return condition;
    }

    public Where setCondition(ConditionEnum condition) {
        this.condition = condition;
        return this;
    }

    public Object getValue() {
        return value;
    }

    public Where setValue(Object value) {
        this.value = value;
        return this;
    }

    public String getProperty() {
        return property;
    }

    public Where setProperty(String property) {
        this.property = property;
        return this;
    }
}
