package com.alibaba.test2;

import java.util.List;

public class BaseMapper<T> {

    private List<T> data;

    public BaseMapper(List<T> data) {
        this.data = data;
    }

    public List<T> getData() {
        return data;
    }

    /**
     * 根据条件查询
     *
     * @param example
     * @return
     */
    public List<T> query(Example example) {
        if (null == data || data.size() == 0) {
            return null;
        }
        if (null == example) {
            return data;
        }
        filter(example.getWheres());
        group(example.getGroupBys());
        order(example.getOrderBys());
        limit(example.getLimit());
        return data;
    }

    /**
     * 根据条件过滤
     *
     * @param wheres
     * @return
     */
    public List<T> filter(List<Where> wheres) {
        if (null == wheres || wheres.size() == 0) {
            return data;
        }
        return null;
    }

    /**
     * 分组
     *
     * @param groupBys
     * @return
     */
    public List<T> group(List<String> groupBys) {
        if (null == groupBys || groupBys.size() == 0) {
            return data;
        }
        return null;
    }

    /**
     * 排序
     *
     * @param orderBys
     * @return
     */
    public List<T> order(List<OrderBy> orderBys) {
        if (null == orderBys || orderBys.size() == 0) {
            return data;
        }
        return null;
    }

    /**
     * 根据范围取数
     *
     * @param limit
     * @return
     */
    public List<T> limit(Limit limit) {
        if (null == limit) {
            return data;
        }
        return null;
    }
}
