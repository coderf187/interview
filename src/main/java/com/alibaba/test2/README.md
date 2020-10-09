用Java实现一个类似SQL查询的服务，参数模型自己设计：
 入参：
  - 类比某张表内全量数据
  - 过滤条件，支持多个，与/或
  - 排序，支持多个，以及升序倒序
  - 分组，支持多个
  - 最大返回结果数
 返回：
  - 查询结果

大概这样：

    List<Object> query(List<Object> data, Where where, OrderBy orderBy, GroupBy groupBy, Limit limit) {
        //你的代码实现。。。
    }
    
实现思路：参考mybatis-plus