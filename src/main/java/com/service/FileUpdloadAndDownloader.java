package com.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileUpdloadAndDownloader {
   /* public CommonResponse importExcel(MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
//            log.info("============fileName=============="+file.getOriginalFilename());
            List list = readExcel(inputStream, file.getOriginalFilename());
            inputStream.close();
            for (int i = 0; i < list.size(); i++) {
                Object object = list.get(i);
//                log.info("============object=============="+object.toString());

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return CommonResponse.success();
    }
    public static List readExcel(InputStream in, String fileName) throws Exception {
        List list = new ArrayList<>();
        //创建Excel工作薄
        Workbook work = getWorkbook(in, fileName);
        if (null == work) {
            throw new Exception("创建Excel工作薄为空！");
        }
        Row row = null;
        Cell cell = null;

        Sheet sheet = work.getSheetAt(0);
        // 获取第一个实际行的下标
//        log.info("======sheet.getFirstRowNum()=========" + sheet.getFirstRowNum());
        // 获取最后一个实际行的下标
        log.info("======sheet.getLastRowNum()=========" + sheet.getLastRowNum());
        for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum(); i++) {
            row = sheet.getRow(i);
            // 获取在某行第一个单元格的下标
            log.info("======row.getFirstCellNum()=========" + row.getFirstCellNum());
            // 获取在某行的列数
            log.info("======row.getLastCellNum()=========" + row.getLastCellNum());
            if (row == null || row.getFirstCellNum() == i) {
                continue;
            }
            List<Object> li = new ArrayList<>();
            for (int j = row.getFirstCellNum(); j < row.getLastCellNum(); j++) {
                cell = row.getCell(j);
                li.add(cell);
            }
            list.add(li);
        }
        work.close();//这行报错的话  看下导入jar包的版本
        return list;
        public static Workbook getWorkbook (InputStream inStr, String fileName) throws Exception {
            Workbook workbook = null;
            String fileType = fileName.substring(fileName.lastIndexOf("."));
            log.info("==========fileType========" + fileType);
            if (".xls".equals(fileType)) {
                workbook = new HSSFWorkbook(inStr);
            } else if (".xlsx".equals(fileType)) {
                workbook = new XSSFWorkbook(inStr);
            } else {
                throw new Exception("请上传excel文件！");
            }
            return workbook;
        }*/
    }