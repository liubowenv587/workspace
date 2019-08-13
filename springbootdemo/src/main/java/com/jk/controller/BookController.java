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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
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

}
