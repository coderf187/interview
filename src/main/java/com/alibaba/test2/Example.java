package com.alibaba.test2;

import java.util.ArrayList;
import java.util.List;

public class Example {

    /**
     * 查询条件集合
     */
    private List<Where> wheres = new ArrayList<>();

    /**
     * 分组属性集合
     */
    private List<String> groupBys = new ArrayList<>();

    /**
     * 取数范围
     */
    private Limit limit;

    /**
     * 排序集合
     */
    private OrderBy orderBy;

    public List<Where> getWheres() {
        return wheres;
    }

    public List<String> getGroupBys() {
        return groupBys;
    }

    public Limit getLimit() {
        return limit;
    }

    public Example setLimit(Limit limit) {
        this.limit = limit;
        return this;
    }

    public OrderBy getOrderBy() {
        return orderBy;
    }

    public Example addWhere(Where where) {
        wheres.add(where);
        return this;
    }

    public Example addGroupBy(String groupBy) {
        groupBys.add(groupBy);
        return this;
    }

    public Example setOrderBy(OrderBy orderBy) {
        this.orderBy = orderBy;
        return this;
    }
}
