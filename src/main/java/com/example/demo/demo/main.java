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
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

public class main {


    public static List<Object> getlist(File xlsFile) throws Exception {
        List<Object> list = new ArrayList<>();
        XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(xlsFile));
        // 获得工作表
        XSSFSheet sheet = workbook.getSheetAt(0);
        int rows = sheet.getPhysicalNumberOfRows();
        for (int i = 0; i < rows; i++) {
            System.out.println(i);
            // 获取第i行数据
            XSSFRow sheetRow = sheet.getRow(i);
            // 获取第0格数据
            try {
                XSSFCell cell = sheetRow.getCell(1);
                // 调用toString方法获取内容
                list.add(cell);
            }catch (Exception E){
                continue;
            }


        }
        return list;
    }

    public static void main(String[] args)throws Exception {

        XSSFWorkbook wb = new XSSFWorkbook();
        //创建表单Sheet对象
        XSSFSheet sheets = wb.createSheet();

        int num=14290;
        XSSFRow row;
        XSSFCell cell;
//        //30863
//        for (int i = 1; i < list.size(); i++) {
//
//            row = sheets.createRow(i);
//            cell = row.createCell(7);
//            cell.setCellValue(list.get(i));
//        }
        final String URL = "https://www.dushu.com";
        File xlsFile = new File("E:\\date详细数据.xlsx");
        Object o;
        String jianjie = "";
        List<Object> list = getlist(xlsFile);
        //list.size()
        for (int i = 14290; i < list.size(); i++) {
            o = list.get(i);
            num++;
            Document document = Jsoup.connect("https://www.dushu.com/search.aspx?wd=" + o)
                    .ignoreContentType(true).ignoreHttpErrors(true).get();
            String cssQuery = "div.searchlist h3";
            Elements select = document.select(cssQuery);
            Element link = select.select("a").first();
            String relHref = null;
            try {
                relHref = link.attr("href");
            } catch (Exception e) {
                jianjie="未能找到该书内容";
                continue;
            }
            String urlpath = URL + relHref;
            Document document2 = Jsoup.connect(urlpath)
                    .ignoreContentType(true).ignoreHttpErrors(true).get();
            String css = "div.book-summary";
            Elements select1 = document2.select(css);
            String text = select1.first().text();

            jianjie = text.substring(4);
            System.out.println(jianjie);
//            for (int j = 0; j < 30863; j++) {
            row = sheets.createRow(num);
            cell = row.createCell(7);
            cell.setCellValue(jianjie);

//            continue;
//        }
            FileOutputStream output = new FileOutputStream("E:\\a.xlsx");
            wb.write(output);
            output.flush();
        }
    }
}