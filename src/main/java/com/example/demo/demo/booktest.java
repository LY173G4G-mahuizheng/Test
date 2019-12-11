package com.example.demo.demo;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class booktest {


    public static void main(String[] args) throws Exception {
        //创建一个Excel对象
        XSSFWorkbook wb = new XSSFWorkbook();

        //创建表单Sheet对象
        XSSFSheet sheets = wb.createSheet();
        Object bookname;
        String bookpath;
        String bookn;
        String relHref;
        String jianjie2;
        Document Rul;
        String isbn;
        String text;

        File xlsFile = new File("E:\\0.xlsx");
        List<Object> list1 = main.getlist(xlsFile);
        for (int i = 0; i < list1.size(); i++) {
            bookname = list1.get(i);

          try {
              bookn =  bookname.toString();
          }catch (Exception e) {
            continue;
          }



            System.out.println(bookn);




            XSSFRow row1;XSSFRow row2;
            XSSFCell cell1;XSSFCell cell2;
            row2 = sheets.createRow(i);
            cell2 = row2.createCell(0);
            cell2.setCellValue(bookn);

//            row1 = sheets.createRow(i);
//            cell1 = row1.createCell(3);
//            cell1.setCellValue(isbn);


                FileOutputStream output = new FileOutputStream("E:\\1.xlsx");
                wb.write(output);
                output.flush();
        }




    }

}
