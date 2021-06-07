package com.util;

import java.util.HashMap;

/**
 * 用于控制ThreadLocal对象
 */

public class ThreadLocalManager {

    //map存储ThreadLocal
    private static HashMap<String,ThreadLocal> TreadMap = new HashMap<>();

    public static ThreadLocal getTreadLocal (String key){
        //获取map中的存储的TreadLocal对象
        ThreadLocal threadLocal = TreadMap.get(key);

        //判断
        if (threadLocal == null){
            //如果取出来的ThreadLogin为null，则重新创建一个
            threadLocal = new ThreadLocal();
            //存储到map集合中
            TreadMap.put(key,threadLocal);
        }
        return threadLocal;
    }
}
