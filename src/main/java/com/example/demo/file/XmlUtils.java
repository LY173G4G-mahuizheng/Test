package com.example.demo.file;


import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class XmlUtils {

    /**
     *
     * 通过XML转换为Map<String,Object>
     *
     * @param xml 为String类型的Xml
     * @return 第一个为Root节点，Root节点之后为Root的元素，如果为多层，可以通过key获取下一层Map
     */
    public static Map<String, Object> createMapByXml(String xml) {
        Document doc = null;
        try {
            doc = DocumentHelper.parseText(xml);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        Map<String, Object> map = new HashMap<String, Object>();
        if (doc == null)
            return map;
        Element rootElement = doc.getRootElement();
        elementTomap(rootElement,map);
        return map;
    }

    /***
     *
     * XmlToMap核心方法，里面有递归调用
     *
     * @param
     * @param
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> elementTomap (Element outele,Map<String,Object> outmap) {
        List<Element> list = outele.elements();
        int size = list.size();
        if(size == 0){
            outmap.put(outele.getName(), outele.getTextTrim());
        }else{
            Map<String, Object> innermap = new HashMap<String, Object>();
            for(Element ele1 : list){
                String eleName = ele1.getName();
                Object obj =  innermap.get(eleName);
                if(obj == null){
                    elementTomap(ele1,innermap);
                }else{
                    if(obj instanceof java.util.Map){
                        List<Map<String, Object>> list1 = new ArrayList<Map<String, Object>>();
                        list1.add((Map<String, Object>) innermap.remove(eleName));
                        elementTomap(ele1,innermap);
                        list1.add((Map<String, Object>) innermap.remove(eleName));
                        innermap.put(eleName, list1);
                    }else{
                        elementTomap(ele1,innermap);
                        ((List<Map<String, Object>>)obj).add(innermap);
                    }
                }
            }
            outmap.put(outele.getName(), innermap);
        }
        return outmap;
    }

}