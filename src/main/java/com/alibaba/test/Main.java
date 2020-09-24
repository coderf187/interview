package com.alibaba.test;

import java.io.File;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.concurrent.*;


/**
 * @author: fengjian
 * @description: 测试类
 * @date: 2020/09/23
 */
public class Main {

    private static final Integer POOL_SIZE = 10;

    public static void main(String[] args) {
        String dirPath = "src/main/resources";
        File dirFile = new File(dirPath);
        File[] files = dirFile.listFiles();
        //文件数量+1个消费者
        CountDownLatch countDownLatch = new CountDownLatch(files.length + 1);
        ExecutorService pool = new ThreadPoolExecutor(POOL_SIZE, POOL_SIZE, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());

        for (File file : files) {
            Producer producer = new Producer(file, countDownLatch);
            //生产者线程使用线程池
            pool.execute(producer);
        }
        Consumer consumer = new Consumer(countDownLatch);
        //一个消费者线程消费
        new Thread(consumer).start();
        try {
            //生产者线程和消费者线程执行完成，关闭线程池，输出结果
            countDownLatch.await();
            pool.shutdownNow();
        } catch (Exception e) {
            e.printStackTrace();
        }

        DataHandler dataHandler = DataHandler.getInstance();
        ConcurrentHashMap<Integer, TreeSet<DataStruct>> dataMap = dataHandler.getDataMap();
        TreeSet<Integer> groupTreeSet = dataHandler.getGroupTreeSet();

        Iterator<Integer> it = groupTreeSet.iterator();
        while (it.hasNext()) {
            Integer groupId = it.next();
            DataStruct first = dataMap.get(groupId).first();
            System.out.println(groupId + "," + first.getId() + "," + first.getQuota());
        }
    }
}