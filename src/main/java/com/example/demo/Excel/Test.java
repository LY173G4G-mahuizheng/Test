package com.example.demo.Excel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Test {
    public static void main(String[] args)throws Exception{
//
//        String relHref = "做人要有手段";
//
//        System.out.println(relHref);
        System.out.println( frist("564894152" ));



    }

    public static String  frist(String name) throws Exception{
        String relHref = null;
        String text = null;
        Document document = Jsoup.connect("https://www.bookuu.com/search.php?k="
                + name ).ignoreContentType(true).ignoreHttpErrors(true).get();
        String cssQuery = "div.wd-180.fl.mr-20.pr div.oh.scale.pd-10 a";
        Elements select = document.select(cssQuery);
//        String text = select.first().text();
        try {
            relHref = select.attr("href");
            System.out.println(relHref);


            Document document2 = Jsoup.connect("https:" + relHref)
                    .ignoreContentType(true).ignoreHttpErrors(true).get();
            String css = "div.wd-970.fr.clearfix.pr div.lh-30.cl-6";
            Elements select1 = document2.select(css);
            text = select1.get(1).text();
        }catch (Exception e) {
            return "";
        }
        System.out.println(text);
        return text;
    }

}
