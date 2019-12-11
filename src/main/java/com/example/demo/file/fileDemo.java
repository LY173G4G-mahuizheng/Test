package com.example.demo.file;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 */
public class fileDemo {

    //1-获取XML-IO流
    private static InputStream  getXmlInputStream(String xmlPath){
        InputStream inputStream = null;
        try {
            //1-把要解析的 XML 文档转化为输入流，以便 DOM 解析器解析它
            inputStream= new FileInputStream(xmlPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inputStream;
    }

        public static Map<String,Object> getmsg(Object o ){
            Map<String, Object> stringObjectMap = new HashMap<>();
            String[] name = {"Title","Decade","Creator"};
            String s = o.toString();
            for (int i = 0; i < 3; i++) {
                int i1 = s.indexOf(name[i]);
                int i2 = s.indexOf(",", i1);
                String substring = s.substring(i1 + name[i].length()+1, i2);
                stringObjectMap.put(name[i],substring);
            }

            return stringObjectMap;
        }

        public static void main(String[] args) throws Exception {
        String name = "E:\\hua\\sa.00000000000020000413.xml";
        //1-获取XML-IO流
        InputStream xmlInputStream = getXmlInputStream(name);
            int size =  xmlInputStream.available() ;
            byte[] array = new byte[size];
            //4.把数据读取到数组中；
            xmlInputStream.read(array) ;
            //5.根据获取到的Byte数组新建一个字符串，然后输出；
            String result = new String(array);
            Map<String, Object> stringObjectMap = XmlUtils.createMapByXml(result);

            Map<String, Object> record = getmsg(stringObjectMap.get("record"));
            record.forEach((k,v) -> System.out.println(k + "------->" + v));

        }
}
