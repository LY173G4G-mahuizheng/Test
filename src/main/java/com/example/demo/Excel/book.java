package com.example.demo.Excel;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class book {


    public static List<String> getPyds(List<Object> list)throws Exception{
        XSSFWorkbook wb = new XSSFWorkbook();
        //创建表单Sheet对象
        XSSFSheet sheets = wb.createSheet();
        XSSFRow row;
        XSSFCell cell;
        Object o;
        String relHref = "";
        String ur;
        String isbn = "";
        List<String> list1 = null;
        for (int i = 0; i < list.size(); i++) {
         o =list.get(i);
        ur = o.toString();
        String URL = "https://www.dushu.com";
        Document document = Jsoup.connect("https://www.dushu.com/search.aspx?wd=" + ur)
                .ignoreContentType(true).ignoreHttpErrors(true).get();
        String cssQuery = "div.img152.float-left.margin-right a";
        Elements select = document.select(cssQuery);
        Element link = select.select("a").first();

        try {
            relHref =   link.attr("href");
            String urlpath = URL + relHref;

            Document document2 = Jsoup.connect(urlpath)
                    .ignoreContentType(true).ignoreHttpErrors(true).get();
//        String css = "div.book-details table td.rt";
//        Elements select1 = document2.select(css);
//        String text = select1.first().text();
            String css1 = "div.book-details table td.rt";
            Elements select1 = document2.select(css1);
            isbn = select1.first().text();
             }catch (Exception e) {
            System.out.println("出现异常");
            }
            System.out.println(isbn);
                list1.add(isbn);
            }
        return list1;
    }

    public static List<String> getPybk(List<Object> list) throws Exception{
        XSSFWorkbook wb = new XSSFWorkbook();
        //创建表单Sheet对象
        XSSFSheet sheets = wb.createSheet();
        XSSFRow row;
        XSSFCell cell;
        Object o;
        String relHref;
        String name ="";
        List<String> list1 = null;


        for (int i = 0; i < list.size(); i++) {
            try{
            o =list.get(i);
            name = o.toString();
        Document document = Jsoup.connect("https://www.bookuu.com/search.php?k="
            + name ).ignoreContentType(true).ignoreHttpErrors(true).get();
//        String cssQuery = "div.wd-180.fl.mr-20.pr div.oh.scale.pd-10 a";
//        Elements select = document.select(cssQuery);
////        String text = select.first().text();
//        relHref =   select.attr("href");
//        Document document2 = Jsoup.connect("https:"+relHref)
//            .ignoreContentType(true).ignoreHttpErrors(true).get();
//        String css = "div.wd-970.fr.clearfix.pr div.lh-30.cl-6";
//        Elements select1 = document2.select(css);
//        String text = select1.get(1).text();
                String cssQuery = "div.wd-30p.fl.to-hd.cl-9.mr-10 a";
                Elements select = document.select(cssQuery);
//        String text = select.first().text();
                String text = select.first().text();
                System.out.println(text);
        System.out.println(text);
            list1.add(text);
            }catch (Exception e){
                System.out.println("出现错误");
                continue;
            }finally {
                TimeUnit.SECONDS.sleep(3);
            }
        }


        return list1;
    }
}
