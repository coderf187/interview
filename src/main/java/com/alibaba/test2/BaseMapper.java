package com.alibaba.test2;

import java.util.List;

public class BaseMapper {

    /**
     * 根据条件查询
     *
     * @param data
     * @param example
     * @return
     */
    List<Object> query(List<Object> data, Example example) {
        if (null == data || data.size() == 0) {
            return null;
        }
        if (null == example) {
            return data;
        }
        List<Where> wheres = example.getWheres();
        data = filterByWhere(data, wheres);
        example.getGroupBys();
        example.getOrderBys();
        example.getLimit();
        return null;
    }

    /**
     * 根据条件过滤
     *
     * @param data
     * @param wheres
     * @return
     */
    List<Object> filterByWhere(List<Object> data, List<Where> wheres) {
        if (null == wheres || wheres.size() == 0) {
            return data;
        }
        return null;
    }

}
