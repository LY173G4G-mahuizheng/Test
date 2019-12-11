package com.example.demo.Excel;

import java.io.File;
import java.util.List;

public class Main {

//    public static String readtxt ()throws Exception {
//
//        String file = null;
//        List<String> fileContent = new ArrayList<String>();
//        try {
//
//            File f = new File("E:/book/部编版一年级（上）比尾巴.txt");
//            if (f.exists()) {
//                FileReader fr = new FileReader("E:/book/部编版一年级（上）比尾巴.txt");
//                BufferedReader br = new BufferedReader(fr); // 建立BufferedReader对象，并实例化为br
//                String line = br.readLine(); // 从文件读取一行字符串
//                // 判断读取到的字符串是否不为空
//
//                while (line != null) {
//                    if (line.length() > 0) {
//                        file += line + "/r/n";
//                    }
//                    line = br.readLine(); // 从文件中继续读取一行数据
//                }
//                //排序
//                br.close(); // 关闭BufferedReader对象
//                fr.close(); // 关闭文件
//            }
//
//
//
//        } catch (IOException e) {
//            throw e;
//        }
//        return file;
//
//    }


    public static void main(String[] args) throws  Exception {

        // new File("E:/book/部编版一年级（上）比尾巴1.mp3").renameTo(new File("E:/book/部编版一年级（上）比尾巴.mp3"));

        //输入
        File xlsFile = new File("E:\\0.xlsx");
        //输出
        File file = new File("E:\\1.xlsx");


        List<Object> list;
        List<String> listl;



        read.write(file,book.getPybk(read.getCell(xlsFile)));


    }
}
