package com.example.demo.jsoup;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class Test {


    public static Object getlist() throws Exception {


        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheets = wb.createSheet();
        XSSFRow row = null;
        Cell cell = null;
        XSSFCellStyle style = wb.createCellStyle();
        style.setWrapText(true);
        Document document = null;

        //20000
        for (int i = 250001; i < 1000000; i++) {
            List<String> list = new ArrayList<>();
            //位数不足补0
            String format = String.format("%06d", i);
            System.out.println(i);
            final String URL =
                "https://www.capub.cn:8443/pdm/business/queryBookAction.do?method=queryDetail&id=005"+format+"CPP01";

            System.out.println(URL);
            try {
                 document = Jsoup.connect(URL).timeout(3000 * 10)
                        .ignoreContentType(true).ignoreHttpErrors(true).get();
            }catch (Exception e){
                TimeUnit.SECONDS.sleep(3);
            }

            for (int j = 0; j < 18; j++) {


                try{
                String css = "tbody td.bizformdatacell";
                Element select = document.select(css).get(j);
                String  str = select.text();
                System.out.println(str);
                list.add(str);
                }catch (Exception e){
                    continue;
                }
                }
              System.out.println(i);
              row = sheets.createRow(i);
              for (int j = 0; j < list.size(); j++) {
                cell = row.createCell(j);
                cell.setCellValue(list.get(j));
            }

              if (i%10000==0){
                  FileOutputStream output = new FileOutputStream("E:\\1.xlsx");
                  wb.write(output);
                  output.flush();
              }

            }




            return null;
    }

    public static void main(String[] args)throws Exception {
               getlist();
    }
}