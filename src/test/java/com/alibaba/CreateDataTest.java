package com.alibaba;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author: fengjian
 * @description: 创建测试数据
 * @date: 2020/09/23
 */
public class CreateDataTest {

    public static void main(String[] args) throws IOException {
        String path = "src/main/resources/";
        for (int i = 1; i < 100; i++) {
            File file = new File(path + i + ".txt");
            if (!file.exists()) {
                file.createNewFile();
            }
            try (FileWriter fileWriter = new FileWriter(file);
                 BufferedWriter br = new BufferedWriter(fileWriter)) {
                for (int j = 0; j < 100; j++) {
                    br.write(createData());
                    br.newLine();
                }
            }
        }
        System.out.println("success");
    }

    private static String createData() {
        Integer id = (int) (Math.random() * 10000);
        Integer groupId = (int) (Math.random() * 1000);
        Float quota = (int) (Math.random() * 1000) / 10.0f + 60;
        return id + "," + groupId + "," + quota;
    }
}