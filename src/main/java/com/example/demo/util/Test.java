package com.example.demo.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

public class Test {

    public static void getSize() throws  Exception {
        File file = new File("E:\\app数据\\ok图书/国学古籍/国学普及读物/东京梦华录/东京梦华录.txt");
        File file1 = new File("E:\\app数据\\txt封面\\ResourceData/MBCI/14/4099714.jpg");

        FileInputStream in = new FileInputStream(file1);
        FileOutputStream out = new FileOutputStream(new File("E:\\photo\\"+file1.getName()));
        byte[] buff = new byte[512];
        int n = 0;
        while ((n = in.read(buff)) != -1) {
            out.write(buff, 0, n);
        }
        out.flush();
        in.close();
        out.close();



        Long size = file.length();
        size = size / 1024;
        System.out.println("Size: " + size + " kb");

    }

    public static void main(String[] args) throws Exception {
//         getSize();
//        Master master = new Master(new Worker(),10);
//        Random r = new Random();
//        for (int i = 0; i < 100; i++) {
//            Task t = new Task();
//            t.setId(i);
//            t.setName("任务" + i);
//            t.setResult(Integer.toString(r.nextInt(1000)));
//            master.submit(t);
//        }
//        Master.execute();

        String s = "E:\\app数据\\ok图书/国学古籍/国学普及读物/千家诗4/千家诗4.txt";








    }
}
