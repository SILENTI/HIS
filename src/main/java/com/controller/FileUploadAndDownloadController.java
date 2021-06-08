package com.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

@Controller
public class FileUploadAndDownloadController {

    /*文件上传*/
    @RequestMapping(value = "/userInfoImport", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseBody
    public void userInfoImport (HttpServletRequest request, MultipartFile file) {
        //上传的文件是导入用户的信息-账号和密码t
        System.out.println("用户信息导入");
        System.out.println(file.toString());
        //解析file内容，获取其内内容
        

    }
}
