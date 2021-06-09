package com.util;

import java.util.HashMap;

public class LayuiUtil {

    private static HashMap<String,Object> returnMap= null;

    public static HashMap<String,Object> ReturnMapInfo (String key,Object values){
        returnMap = new HashMap<>();
        returnMap.put("code",0);
        returnMap.put("msg","");
        returnMap.put(key,values);
        return returnMap;
    }
}
