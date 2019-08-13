/**
 * Copyright (C), 2015-2019, 金科教育
 * FileName: BookController
 * Author:   dell
 * Date:     2019/8/6 19:35
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名 ：刘博文   修改时间   new Date()  版本号              描述
 */
package com.jk.controller;

import com.jk.model.Cat;
import com.jk.service.impl.BookService;
import com.jk.util.ExportExcel;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("boot")
public class BookController {
    @Autowired
    private BookService bookservice;


    @RequestMapping("findBootstrap")
    @ResponseBody
    public HashMap<String,Object> findBootstrap(Integer page, Integer rows, Cat cat){
        return  bookservice.findBootstrap(page,rows,cat);
    }
    @RequestMapping("findDialog")
    public String findDialog(String id, Model model){
        if (id!=null) {
            Cat cat = bookservice.findBootDialogById(id);
            model.addAttribute("cat", cat);
        }
        return "bootDialog2";
    }
    @RequestMapping("findBootDialogById")
    public String findBootDialogById(String id, Model model){

        return "bootDialog";
    }


    @RequestMapping("findCat")
    @ResponseBody
    public void saveCat(Cat cat){

            if(cat.getId()!=null) {
                bookservice.updatecat(cat);
            }else {
                bookservice.addcat(cat);
            }



    }
    @RequestMapping("deleteCat")
    @ResponseBody
    public Boolean deleteCat(String []id){
        try {
            for (int i = 0; i < id.length; i++) {
                bookservice.deleteCat(id[i]);
            }
            return true;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            return  false;
        }

    }



    @RequestMapping("exportExcel")
    public void exportExcel(HttpServletResponse response){
        //导出的excel的标题
        String title = "1902B猫咪百科";
        //导出excel的列名
        String[] rowName = {"编号","猫咪名称","猫咪类型","出生日期","健康状态"};
        //导出的excel数据
        List<Object[]> dataList = new ArrayList<Object[]>();
        //查询的数据库的书籍信息
        List<Cat> list=   bookservice.query();
        //循环书籍信息
        for(Cat cat:list){
            Object[] obj =new Object[rowName.length];
            obj[0]=cat.getId();
            obj[1]=cat.getCatName();
            if(cat.getCatType()==1){
                obj[2]="家猫";
            }else{
                obj[2]="野猫";
            }
            SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
            String format = sdf.format(cat.getCreateTime());
            obj[3]=format;
            if(cat.getStatus()==1){
                obj[4]="正常";
            }else{
                obj[4]="生病";
            }
            dataList.add(obj);
        }
        ExportExcel exportExcel =new ExportExcel(title,rowName,dataList,response);
        try {
            exportExcel.export();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("importExcel")
    public String importExcel(MultipartFile file, HttpServletResponse response){
        //获得上传文件上传的类型
        String contentType = file.getContentType();
        //上传文件的名称
        String fileName = file.getOriginalFilename();
        //获得文件的后缀名
        String fileType = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        //上传文件的新的路径
        //生成新的文件名称
        String filePath = "../src/main/resources/templates/fileupload/";
        //创建list集合接收excel中读取的数据
        List<Cat> list =new ArrayList<>();
        try {
            uploadFile(file.getBytes(), filePath, fileName);
            SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
            //通过文件忽的WorkBook
            FileInputStream fileInputStream = new FileInputStream(filePath+fileName);
            Workbook workbook = WorkbookFactory.create(fileInputStream);
            //通过workbook对象获得sheet页 有可能不止一个sheet
            for(int i=0 ;i<workbook.getNumberOfSheets();i++){
                //获得里面的每一个sheet对象
                Sheet sheetAt = workbook.getSheetAt(i);
                //通过sheet对象获得每一行
                for(int j=3;j<sheetAt.getPhysicalNumberOfRows();j++){
                    //创建一个book对象接收excel的数据
                Cat cat=new Cat();
                    //获得每一行的数据
                    Row row = sheetAt.getRow(j);

                    //获得每一个单元格的数据
                    if(row.getCell(1)!=null && !"".equals(row.getCell(1))){
                        cat.setCatName(this.getCellValue(row.getCell(1)));
                    }
                    if(row.getCell(2)!=null && !"".equals(row.getCell(2))){
                        String cellValue = this.getCellValue(row.getCell(2));
                        if("家猫".equals(cellValue)){
                            cat.setCatType(1);
                        }else{
                            cat.setCatType(0);
                        }

                    }
                    if(row.getCell(3)!=null && !"".equals(row.getCell(3))){
                        cat.setCreateTime(sdf.parse(this.getCellValue(row.getCell(3))));
                    }
                    if(row.getCell(4)!=null && !"".equals(row.getCell(4))){
                        String cellValue = this.getCellValue(row.getCell(4));
                        if("正常".equals(cellValue)){
                            cat.setStatus(1);
                        }else{
                            cat.setStatus(0);
                        }

                    }
                    list.add(cat);
                }
            }
            bookservice.addBook(list);

        } catch (Exception e) {
            e.printStackTrace();
        }


        return "Booklist";
    }

    //上传文件的方法
    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath + fileName);
        out.write(file);
        out.flush();
        out.close();
    }

    //判断从Excel文件中解析出来数据的格式
    private static String getCellValue(Cell cell){
        String value = null;
        //简单的查检列类型
        switch(cell.getCellType())
        {
            case Cell.CELL_TYPE_STRING://字符串
                value = cell.getRichStringCellValue().getString();
                break;
            case Cell.CELL_TYPE_NUMERIC://数字
                long dd = (long)cell.getNumericCellValue();
                value = dd+"";
                break;
            case Cell.CELL_TYPE_BLANK:
                value = "";
                break;
            case Cell.CELL_TYPE_FORMULA:
                value = String.valueOf(cell.getCellFormula());
                break;
            case Cell.CELL_TYPE_BOOLEAN://boolean型值
                value = String.valueOf(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_ERROR:
                value = String.valueOf(cell.getErrorCellValue());
                break;
            default:
                break;
        }
        return value;
    }
    //判断从Excel文件中解析出来数据的格式
    private static String getCellValue(XSSFCell cell){
        String value = null;
        //简单的查检列类型
        switch(cell.getCellType())
        {
            case HSSFCell.CELL_TYPE_STRING://字符串
                value = cell.getRichStringCellValue().getString();
                break;
            case HSSFCell.CELL_TYPE_NUMERIC://数字
                long dd = (long)cell.getNumericCellValue();
                value = dd+"";
                break;
            case HSSFCell.CELL_TYPE_BLANK:
                value = "";
                break;
            case HSSFCell.CELL_TYPE_FORMULA:
                value = String.valueOf(cell.getCellFormula());
                break;
            case HSSFCell.CELL_TYPE_BOOLEAN://boolean型值
                value = String.valueOf(cell.getBooleanCellValue());
                break;
            case HSSFCell.CELL_TYPE_ERROR:
                value = String.valueOf(cell.getErrorCellValue());
                break;
            default:
                break;
        }
        return value;
    }

}
