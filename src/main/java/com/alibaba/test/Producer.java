package com.alibaba.test;

import java.io.*;
import java.util.concurrent.CountDownLatch;


/**
 * @author: fengjian
 * @description: 生产者
 * @date: 2020/09/23
 */
public class Producer implements Runnable {

    private File file;
    private CountDownLatch countDownLatch;
    private DataHandler dataHandler = DataHandler.getInstance();

    public Producer(File file, CountDownLatch countDownLatch) {
        this.file = file;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try (InputStreamReader read = new InputStreamReader(new FileInputStream(file));
             BufferedReader br = new BufferedReader(read)) {
            String line = "";
            String[] arrs;
            while ((line = br.readLine()) != null) {
                if (line.equals("")) {
                    continue;
                }
                arrs = line.split(",");
                DataStruct dataStruct = new DataStruct();
                dataStruct.setId(arrs[0]);
                dataStruct.setGroupId(Integer.valueOf(arrs[1]));
                dataStruct.setQuota(new Float(arrs[2]));
                //生产数据，当超过初始化容量后阻塞进程等待空间再加入元素
                dataHandler.getQueue().put(dataStruct);
            }

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            countDownLatch.countDown();
        }

    }

}