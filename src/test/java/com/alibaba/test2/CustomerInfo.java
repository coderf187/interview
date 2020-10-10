package com.alibaba.test2;

import java.io.Serializable;

public class CustomerInfo implements Serializable {

    /**
     * 姓名
     */
    private String name;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 城市
     */
    private String city;

    /**
     * 等级
     */
    private String level;

    public CustomerInfo(String name, Integer age, String city, String level) {
        this.name = name;
        this.age = age;
        this.city = city;
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "姓名：" + name + "年龄：" + age + "城市：" + city + "等级：" + level;
    }
}
