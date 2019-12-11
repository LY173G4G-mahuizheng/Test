package com.example.demo.Excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class douban {
    public static void dban(List<Object> isbn) throws  Exception{

        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheets = wb.createSheet();
        XSSFRow row = null;
        Cell cell = null;
        XSSFCellStyle style = wb.createCellStyle();
        style.setWrapText(true);
        Document document = null;
        String relHref = "";
        Element link = null;


        // 获得工作表

        for (int k = 0; k < isbn.size(); k++) {
            ArrayList<String > strs = new ArrayList<>();


            String URL = "https://www.dushu.com";
            try {
             document = Jsoup.connect("https://www.dushu.com/search.aspx?wd=" + isbn.get(k))
                    .ignoreContentType(true).ignoreHttpErrors(true).get();
            }catch (Exception e){
                TimeUnit.SECONDS.sleep(3);
            }
            Document document2 = null;
            try{
            String cssQuery = "div.img152.float-left.margin-right a";
            Elements select = document.select(cssQuery);
             link = select.select("a").first();


          relHref =   link.attr("href");
            String urlpath = URL + relHref;
            System.out.println(urlpath);
             document2 = Jsoup.connect(urlpath)
                        .ignoreContentType(true).ignoreHttpErrors(true).get();
            }catch (Exception e){
                continue;
            }


            //获取封面连接
        String bookurl = "div.book-pic img";
        Elements book = document2.select(bookurl);
        String src = book.attr("src");
        strs.add(src);

        //作者和出版社
            String c = "div.book-details tbody tr td";
        for (int j = 0; j < 20; j++) {
           if(j % 2 ==1){
               Element sele  = document2.select(c).get(j);
               String text = sele.text();

               strs.add(text);
           }
        }

         //内容介绍和作者介绍
        String css = "div.book-summary div.text.txtsummary";
        for (int i = 0; i < 2; i++) {
        Element select1 = document2.select(css).get(i);
        String text = select1.text();
            strs.add(text);
        }



        row = sheets.createRow(k);
        for (int j = 0; j < strs.size(); j++) {
            cell = row.createCell(j);
            cell.setCellValue(strs.get(j));
        }


                FileOutputStream output = new FileOutputStream("E:\\1.xlsx");
                wb.write(output);
                output.flush();


            System.out.println("第"+k+"本书");
        }

    }

    public static void main(String[] args) throws Exception {

        List<Object> cell = read.getCell(new File("E:\\下载的新书.xlsx"));
        dban(cell);
    }
}
