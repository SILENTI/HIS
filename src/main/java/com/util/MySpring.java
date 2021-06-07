package com.util;

import java.util.HashMap;

public class MySpring {

    /**
     * 控制权反转——>对象的实例化，依托于另一对象
     * */

    //用于存储实例化对象
    private static HashMap<String,Object> map = new HashMap<>();

    public static <T>T getBean (String className){
       T object = (T) map.get(className);
       //获取对象

        //进行判断
       if (object==null){
          //则证明，该该对象还没有实例化
           try {
               //通过反射，获取该类
               Class clazz = Class.forName(className);
               //获取实例化
               object = (T) clazz.newInstance();
               //将对象，存储到map集合中
               map.put(className,clazz);
           } catch (Exception e) {
               e.printStackTrace();
           }
       }
       return object;
    }

}
