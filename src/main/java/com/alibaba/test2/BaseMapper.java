package com.alibaba.test2;

import java.util.*;
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
        order(example.getOrderBy());
        limit(example.getLimit());
        group(example.getGroupBys());
        return data;
    }

    /**
     * 根据条件过滤
     * todo in、like等
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
        Map<String, List<T>> listMap = data.stream()
                .collect(Collectors.groupingBy(t -> {
                    StringBuffer groupKey = new StringBuffer();
                    for (String groupBy : groupBys) {
                        Object val = ReflectUtil.getAttributeValue(t, groupBy);
                        groupKey.append(String.valueOf(val));
                    }
                    return groupKey.toString();
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
     * @param orderBy
     */
    public void order(OrderBy orderBy) {
        if (null == orderBy) {
            return;
        }
        List<T> orderResult = data.stream()
                .sorted((a, b) -> {
                    Object valueA = ReflectUtil.getAttributeValue(a, orderBy.getProperty());
                    Object valueB = ReflectUtil.getAttributeValue(b, orderBy.getProperty());
                    int result = String.valueOf(valueA).compareTo(String.valueOf(valueB));
                    if (orderBy.isAsc()) {
                        return result;
                    }
                    return Math.negateExact(result);
                }).collect(Collectors.toList());
        data = orderResult;
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
