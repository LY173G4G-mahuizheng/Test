package com.example.demo.https;

import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {

    public static void main(String[] args) {
        String time = "1429239425000";
        System.out.println(getDate(time));

    }


    public static  String test(){
        String url_1 = "https://open.readse.com/qqreader/test/book.search.action";
        long time = System.currentTimeMillis() / 1000;
        String sige =  MD5("D66FD7CF988EA8677ED1D426439F2EC1appflag100001keywordspage1timestamp"+time);
        String url_2 = "?appflag=100001&keywords=中国&page=1&timestamp="+time +"&sign="+sige;
        System.out.println(url_1+url_2);
        //String str =  HttpsUtils.sendGet(url_1,url_2,false);
        String str =HttpClientUtil.sendGetSSLRequest(url_1+url_2,"utf-8");
        return str;
    }


    public final static String MD5(String s) {
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'A', 'B', 'C', 'D', 'E', 'F' };
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

}

    public static String getDate(String time){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sd = sdf.format(new Date(Long.parseLong(String.valueOf(time))));
        return sd;
    }
}