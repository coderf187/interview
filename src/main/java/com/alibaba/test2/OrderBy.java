package com.alibaba.test2;

import java.io.Serializable;

public class OrderBy implements Serializable {

    /**
     * 属性
     */
    private String property;

    /**
     * 默认升序
     */
    private boolean asc = true;

    public OrderBy(String property, boolean asc) {
        this.property = property;
        this.asc = asc;
    }

    public String getProperty() {
        return property;
    }

    public OrderBy setProperty(String property) {
        this.property = property;
        return this;
    }

    public boolean isAsc() {
        return asc;
    }

    public OrderBy setAsc(boolean asc) {
        this.asc = asc;
        return this;
    }
}
