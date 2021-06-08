package com.controller;

import com.service.FileUpdloadAndDownloader;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Controller
public class FileUploadAndDownloadController {

    /*文件上传*/
    @RequestMapping(value = "/userInfoImport", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    public Map<String,Object> userInfoImport ( MultipartFile file) {
        //调用Service层
        System.out.println("用户信息导入！！！");
        //返回结果-导入成功/导入失败
        Map returnOb = new HashMap();
        String judg = new FileUpdloadAndDownloader().userInfoImport(file);
        returnOb.put("message",judg);
        returnOb.put("code",0);
        /*{
        //返回标准的JOSN形式
          "code": 0
          ,"msg": ""
          ,"data": {
            "src": "http://cdn.layui.com/123.jpg"
          }
        }
         */
        return returnOb;
    }
}
