package com.alibaba.test;

import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.LinkedBlockingQueue;


/**
 * @author: fengjian
 * @description: 消费者线程
 * @date: 2020/09/23
 */
public class Consumer implements Runnable {

    private CountDownLatch countDownLatch;
    private DataHandler dataHandler = DataHandler.getInstance();

    public Consumer(CountDownLatch countDownLatch) {
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            LinkedBlockingQueue<DataStruct> queue = dataHandler.getQueue();
            ConcurrentHashMap<Integer, TreeSet<DataStruct>> dataMap = dataHandler.getDataMap();
            TreeSet<Integer> groupTreeSet = dataHandler.getGroupTreeSet();

            while (true) {
                if (!queue.isEmpty()) {
                    DataStruct dataStruct = queue.take();
                    Integer groupId = dataStruct.getGroupId();
                    TreeSet<DataStruct> currentGroup = dataMap.get(groupId);

                    if (currentGroup == null) {
                        TreeSet<DataStruct> set = new TreeSet<>();
                        set.add(dataStruct);
                        dataMap.put(groupId, set);
                        groupTreeSet.add(Integer.valueOf(groupId));
                    } else {
                        currentGroup.add(dataStruct);
                        dataMap.put(groupId, currentGroup);
                    }
                } else {
                    if (countDownLatch.getCount() == 1) {
                        countDownLatch.countDown();
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
