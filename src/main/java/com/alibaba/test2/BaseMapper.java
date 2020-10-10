package com.alibaba.test2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
     */
    public void filter(List<Where> wheres) {
        if (null == wheres || wheres.size() == 0) {
            return;
        }
        List<T> filterData = data.stream()
                .filter(t -> {
                    Where where = wheres.get(0);
                    Object value = ReflectUtil.getAttributeValue(t, where.getProperty());
                    boolean equalsFlag = value.equals(where.getValue());

                    if (wheres.size() == 1) {
                        return equalsFlag;
                    }

                    for (int i = 1; i < wheres.size(); i++) {
                        Where otherWhere = wheres.get(i);
                        Object val = ReflectUtil.getAttributeValue(t, otherWhere.getProperty());
                        if (ConditionEnum.AND.getCode().equals(otherWhere.getCondition().getCode())) {
                            equalsFlag = equalsFlag && val.equals(otherWhere.getValue());
                        } else {
                            equalsFlag = equalsFlag || val.equals(otherWhere.getValue());
                        }
                    }
                    return equalsFlag;
                }).collect(Collectors.toList());
        data = filterData;
        return;
    }

    /**
     * 分组
     *
     * @param groupBys
     */
    public void group(List<String> groupBys) {
        if (null == groupBys || groupBys.size() == 0) {
            return;
        }
        Map<T, List<T>> listMap = data.stream()
                .collect(Collectors.groupingBy(t -> {
                    return t;
                }));
        List<T> groupData = new ArrayList<>();
        listMap.values()
                .forEach(values -> groupData.addAll(values));
        data = groupData;
        return;
    }

    /**
     * 排序
     *
     * @param orderBys
     */
    public void order(List<OrderBy> orderBys) {
        if (null == orderBys || orderBys.size() == 0) {
            return;
        }

        return;
    }

    /**
     * 根据范围取数
     *
     * @param limit
     */
    public void limit(Limit limit) {
        if (null == limit) {
            return;
        }
        int rows = limit.getRows();
        int offset = limit.getOffset();

        List<T> result = new ArrayList<>();
        Iterator<T> iterator = data.iterator();
        int index = 0;
        while (iterator.hasNext()) {
            index++;
            T next = iterator.next();
            if (rows < index && index <= offset + rows) {
                result.add(next);
            }
        }
        data = result;
        return;
    }
}
