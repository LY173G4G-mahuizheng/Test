package com.example.demo.Excel;

import org.apache.poi.xssf.usermodel.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class read {

    public  static  List<Object> getCell(File xlsFile )throws Exception{
        //File xlsFile = new File("E:\\date详细数据.xlsx");
        List<Object> list = new ArrayList<>();


        XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(xlsFile));
        // 获得工作表
        XSSFSheet sheet = workbook.getSheetAt(0);
        int rows = sheet.getPhysicalNumberOfRows();

        for (int i = 1; i < 2970; i++) {
            // 获取第i行数据
            XSSFRow sheetRow = sheet.getRow(i);
            // 获取第0格数据
            XSSFCell cell = sheetRow.getCell(4);
            Object s = cell;
            Double d = null;
            try{
             d = Double.parseDouble( s.toString() );
            } catch (Exception e){
                continue;
            }

            java.text.DecimalFormat formatter = new java.text.DecimalFormat("########");
            String str = formatter.format(d);
            System.out.println(str);
            System.out.println(i);

            list.add(str);
        }
        return list;
    }


    public static void write (File xlsFile,List<String> list1)throws Exception{
        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheets = wb.createSheet();
        XSSFRow row;
        XSSFCell cell;
        XSSFCellStyle style = wb.createCellStyle();
        style.setWrapText(true);
        for (int i = 0; i < list1.size(); i++) {
            row = sheets.createRow(i);
            cell = row.createCell(0);
            cell.setCellValue(list1.get(i));
        }
        FileOutputStream output = new FileOutputStream(xlsFile);
        wb.write(output);
        output.flush();

    }

    public static List<String> getSize(List<Object> list){

        List<String> urllist = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            String url = list.get(i).toString();
            File filepath = new File(url);
            Long size = filepath.length();
            size = size / 1024;
            String s = size + "kb";
            urllist.add(s);
        }
        return urllist;
    }

    public static List<String> rename (List<Object> list)throws Exception{
        List<String> list1 = new ArrayList<>();
        FileInputStream in;
        FileOutputStream out;
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
            File file = new File(list.get(i).toString());
            System.out.println(list.get(i).toString());
//            long time = System.currentTimeMillis();
//            Date date = new Date(time);
//            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
//            String nyr = dateFormat.format(date);

            int num = (int) ((Math.random() * 9 + 1) * 100000000);
            System.out.println("1551"+num+"upload");

            File tofile = new File("E:\\book\\"+"1551"+num+"upload");

            boolean b = file.renameTo(tofile);
            System.out.println(b);
            list1.add("E:\\book\\"+"1551"+num+"upload");
        }
        return list1;
    }

    public static void copy(List<Object> list) throws Exception{

        FileInputStream in;
        FileOutputStream out;
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
            File file = new File(list.get(i).toString());
            in = new FileInputStream(file);
            out = new FileOutputStream(new File("E:\\book\\"+file.getName()));
            byte[] buff = new byte[512];
            int n = 0;
            while ((n = in.read(buff)) != -1) {
                out.write(buff, 0, n);
            }
            out.flush();
            in.close();
            out.close();


        }
    }

    public static List<String> sub(List<Object> list){
        List<String> lists = new ArrayList<>();
        String substring = "";
        String s;
        for (int i = 0; i < list.size(); i++) {
            s = list.get(i).toString();
            String sl = "http://www.360elib.com:10004/Read?id=" + s;

            lists.add(sl);
        }
        return lists;
    }

    public static List<String> readtxt (List<Object> list)throws Exception {

        String file = null;
        List<String> fileContent = new ArrayList<>();
        try {
            for (int i = 1; i < list.size(); i++) {


            File f = new File(list.get(i).toString());
            if (f.exists()) {
                FileReader fr = new FileReader(list.get(i).toString());
                BufferedReader br = new BufferedReader(fr); // 建立BufferedReader对象，并实例化为br
                String line = br.readLine(); // 从文件读取一行字符串
                // 判断读取到的字符串是否不为空

                while (line != null) {
                    if (line.length() > 0) {
                        file += line + "/r/n";
                    }
                    line = br.readLine(); // 从文件中继续读取一行数据
                }
                //排序
                br.close(); // 关闭BufferedReader对象
                fr.close(); // 关闭文件
            }
                fileContent.add(file);
            file = "";
            }
        } catch (IOException e) {
        }
        return fileContent;

    }

}

