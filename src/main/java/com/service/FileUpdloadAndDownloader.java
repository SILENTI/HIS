package com.service;

import com.dao.UserDao;
import com.domain.User;
import com.util.MybatisUtil;
import org.apache.poi.ss.usermodel.*;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileUpdloadAndDownloader {
    public String userInfoImport (MultipartFile file) {
            //解析MultipartFile
        Workbook book = null ;
        UserDao userDao = MybatisUtil.getMapper(UserDao.class,true);
        try {
            //解析得到的内容，写入数据库
                book = WorkbookFactory.create(file.getInputStream());
                Sheet sheet = book.getSheetAt(0) ;
            for(int i=1;i<sheet.getLastRowNum();i++){
                Row row = sheet.getRow(i);

                Cell c1 = row.getCell(0);
                Cell c2 = row.getCell(1);

                String username = c1.toString() ;
                //读取excel是所有的数字形式的数据都会变成浮点 "123.0"->"123"
                String password = c2.toString() ;
                password = password.replace(".0","");

                System.out.println(username + "  "+password);

                //upass = upass.split(".")[0];
                //upass = upass.substring(0,upass.indexOf("."));

                User user = new User(null,username,password,null,null);
                userDao.inportUserInfo(user);
            }

            } catch (IOException e) {
                e.printStackTrace();
                //如果抛出该异常，则证明没有导入成功
                return "导入失败";
            }finally {
            try {
                book.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
            return "导入成功";
        }
    }