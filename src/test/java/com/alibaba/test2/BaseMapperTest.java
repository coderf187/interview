package com.alibaba.test2;

import java.util.ArrayList;
import java.util.List;

public class BaseMapperTest {

    public static void main(String[] args) {
        //筛选城市=四川或者广东，按照年龄升序，过滤前2条取10条数据，根据等级分组
        List<CustomerInfo> data = initData();
        BaseMapper<CustomerInfo> baseMapper = new BaseMapper<>(data);
        Example example = new Example();
        example.addWhere(new Where("city", ConditionEnum.AND, "四川"))
                .addWhere(new Where("city", ConditionEnum.OR, "广东"))
                .addGroupBy("level")
                .setOrderBy(new OrderBy("age", true))
                .setLimit(new Limit(2, 10));
        List<CustomerInfo> result = baseMapper.query(example);
        for (CustomerInfo customerInfo : result) {
            System.out.println(customerInfo.toString());
        }
    }


    public static List<CustomerInfo> initData() {
        List<CustomerInfo> data = new ArrayList<>();
        CustomerInfo customerInfo1 = new CustomerInfo("姓名1", 30, "四川", "A");
        CustomerInfo customerInfo2 = new CustomerInfo("姓名2", 32, "广东", "B");
        CustomerInfo customerInfo3 = new CustomerInfo("姓名3", 12, "四川", "C");
        CustomerInfo customerInfo4 = new CustomerInfo("姓名4", 30, "广东", "A");
        CustomerInfo customerInfo5 = new CustomerInfo("姓名5", 22, "广东", "B");
        CustomerInfo customerInfo6 = new CustomerInfo("姓名6", 57, "四川", "A");
        CustomerInfo customerInfo7 = new CustomerInfo("姓名7", 44, "四川", "C");
        CustomerInfo customerInfo8 = new CustomerInfo("姓名8", 33, "四川", "A");
        CustomerInfo customerInfo9 = new CustomerInfo("姓名9", 65, "四川", "A");
        CustomerInfo customerInfo10 = new CustomerInfo("姓名10", 32, "四川", "C");
        CustomerInfo customerInfo11 = new CustomerInfo("姓名11", 23, "四川", "A");
        CustomerInfo customerInfo12 = new CustomerInfo("姓名12", 21, "四川", "B");
        CustomerInfo customerInfo13 = new CustomerInfo("姓名13", 22, "四川", "A");
        CustomerInfo customerInfo14 = new CustomerInfo("姓名14", 30, "海南", "B");
        CustomerInfo customerInfo15 = new CustomerInfo("姓名15", 54, "四川", "A");
        CustomerInfo customerInfo16 = new CustomerInfo("姓名16", 64, "海南", "A");
        CustomerInfo customerInfo17 = new CustomerInfo("姓名17", 32, "四川", "C");
        CustomerInfo customerInfo18 = new CustomerInfo("姓名18", 46, "海南", "A");
        data.add(customerInfo1);
        data.add(customerInfo2);
        data.add(customerInfo3);
        data.add(customerInfo4);
        data.add(customerInfo5);
        data.add(customerInfo6);
        data.add(customerInfo7);
        data.add(customerInfo8);
        data.add(customerInfo9);
        data.add(customerInfo10);
        data.add(customerInfo11);
        data.add(customerInfo12);
        data.add(customerInfo13);
        data.add(customerInfo14);
        data.add(customerInfo15);
        data.add(customerInfo16);
        data.add(customerInfo17);
        data.add(customerInfo18);
        return data;
    }
}
