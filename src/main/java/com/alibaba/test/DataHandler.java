package com.alibaba.test;

import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author fengjian
 * @since 2020/9/24
 */
public class DataHandler {
    /**
     * 初始化待处理数据容量
     */
    private static final Integer QUEUE_SIZE = 100;
    /**
     * 待处理数据队列
     */
    private LinkedBlockingQueue<DataStruct> queue = new LinkedBlockingQueue<>(QUEUE_SIZE);

    /**
     * 处理后的分组数据
     */
    private ConcurrentHashMap<Integer, TreeSet<DataStruct>> dataMap = new ConcurrentHashMap<>();

    /**
     * 分组排序
     */
    private TreeSet<Integer> groupIds = new TreeSet<>();

    /**
     * 单例
     */
    private static DataHandler dataHandler = new DataHandler();

    private DataHandler() {
    }

    public static DataHandler getInstance() {
        return dataHandler;
    }

    public LinkedBlockingQueue<DataStruct> getQueue() {
        return queue;
    }

    public ConcurrentHashMap<Integer, TreeSet<DataStruct>> getDataMap() {
        return dataMap;
    }

    public TreeSet<Integer> getGroupTreeSet() {
        return groupIds;
    }

}
