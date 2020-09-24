package com.alibaba.test;

import java.util.Objects;

/**
 * @author: fengjian
 * @description: 数据结构
 * @date: 2020/09/23
 */
public class DataStruct implements Comparable<DataStruct> {

    /**
     * id
     */
    private String id;
    /**
     * 分组
     */
    private Integer groupId;
    /**
     * 指标
     */
    private Float quota;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Float getQuota() {
        return quota;
    }

    public void setQuota(Float quota) {
        this.quota = quota;
    }


    @Override
    public int compareTo(DataStruct data) {
        return compare(this.getQuota(), data.getQuota());
    }

    public static int compare(float quota1, float quota2) {
        return (quota1 > quota2 ? 1 : (quota1 == quota2 ? 0 : -1));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DataStruct)) {
            return false;
        }
        DataStruct dataStruct = (DataStruct) o;
        return groupId.equals(dataStruct.groupId) &&
                Float.compare(dataStruct.quota, quota) == 0 &&
                Objects.equals(id, dataStruct.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, groupId, quota);
    }

}
