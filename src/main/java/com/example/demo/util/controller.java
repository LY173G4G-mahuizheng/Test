package com.example.demo.util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/test")
public class controller {


    @RequestMapping("/getTextdushu")
    public static @ResponseBody String getTextdushu(@RequestParam String name)throws Exception{

        String relHref = "";

        String URL = "https://www.dushu.com";
        Document document = Jsoup.connect("https://www.dushu.com/search.aspx?wd=" + name)
                .ignoreContentType(true).ignoreHttpErrors(true).get();
        String cssQuery = "div.img152.float-left.margin-right a";
        Elements select = document.select(cssQuery);
        Element link = select.select("a").first();


        relHref =   link.attr("href");
        String urlpath = URL + relHref;

        Document document2 = Jsoup.connect(urlpath)
                .ignoreContentType(true).ignoreHttpErrors(true).get();
        String css = "div.book-summary div.text.txtsummary";
        Elements select1 = document2.select(css);
        String text = select1.first().text();
        System.out.println(text);
        return text;
    }


    @RequestMapping("/getTextboku")
    public static @ResponseBody String getTextboku(@RequestParam String name)throws Exception{
        String relHref;
        Document document = Jsoup.connect("https://www.bookuu.com/search.php?k="
                + name ).ignoreContentType(true).ignoreHttpErrors(true).get();
        String cssQuery = "div.wd-180.fl.mr-20.pr div.oh.scale.pd-10 a";
        Elements select = document.select(cssQuery);
//        String text = select.first().text();
        relHref =   select.attr("href");
        System.out.println(relHref);



        Document document2 = Jsoup.connect("https:"+relHref)
                .ignoreContentType(true).ignoreHttpErrors(true).get();
        String css = "div.wd-970.fr.clearfix.pr div.lh-30.cl-6";
        Elements select1 = document2.select(css);
        String text = select1.get(1).text();
        System.out.println(text);

        return text;
    }
}
