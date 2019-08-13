/**
 * Copyright (C), 2015-2019, 金科教育
 * FileName: CatController
 * Author:   dell
 * Date:     2019/8/12 19:18
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名 ：刘博文   修改时间   new Date()  版本号              描述
 */
package com.jk.controller;

import com.jk.service.CatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author dell
 * @create 2019/8/12
 * @since 1.0.0
 */
@Controller
@RequestMapping("car")
public class CatController {

    @Autowired
    private CatService catservice;

    @RequestMapping("toShow")
    public String toShow(){
        return  "show";
    }
    @RequestMapping("toShow1")
    public String toShow1(){
        return  "show1";
    }
    @RequestMapping("toShow2")
    public String toShow2(){
        return  "show2";
    }
    @RequestMapping("toShow3")
    public String toShow3(){
        return  "show3";
    }
    @RequestMapping("queryCat")
    @ResponseBody
    public List<Map<String,Object>> queryBook(){
        List<Map<String,Object>> list= catservice.queryBook();
        List<Map<String,Object>> list1=new ArrayList<>();

        for (Map<String,Object> map1:list) {
            Map<String,Object> map=new HashMap<>();
            map.put("name",map1.get("月份"));
            map.put("y",map1.get("数量"));
            list1.add(map);
        }
        return list1;


    }


    @RequestMapping("queryCar")
    @ResponseBody
    public List<Map<String,Object>> queryCar(){
        List<Map<String,Object>> list= catservice.queryCar1();
        List<Map<String,Object>> list1 =new ArrayList<>();
        for(Map<String,Object> map1 :list){
            Map<String,Object> map=new HashMap<>();
            Integer  object = Integer.parseInt(map1.get("颜色").toString());
            Integer  nian = Integer.parseInt(map1.get("月").toString());
            if(object==1){
                map.put("name",nian+"月"+"蓝色") ;
            }else if(object==2){
                map.put("name",nian+"月"+"白色");
            }else{
                map.put("name",nian+"月"+"黑色");
            }
            //map.put("name",map1.get("日期"));
            map.put("y",map1.get("个数"));
            map.put("sliced","true");
            map.put("selected","true");
            list1.add(map);
        }

        return list1;
    }
    @RequestMapping("queryCarZhu")
    @ResponseBody
    public List<Map<String, Object>> queryCarZhu(){

        List<Map<String,Object>> list= catservice.queryZhuxing();
        List<Map<String,Object>> list1=new ArrayList<>();
        for (Map<String,Object> map1:list){
            Map<String,Object> map=new HashMap<>();
            Integer  object = Integer.parseInt(map1.get("颜色").toString());
            if(object==1){
                map.put("name","蓝色") ;
            }else if(object==2){
                map.put("name","白色");
            }else{
                map.put("name","黑色");
            }
            Integer count = Integer.parseInt(map1.get("个数").toString());
            map.put("name",map1.get("时间"));
            int[] bb = new int[]{count};
            map.put("data",bb);
            list1.add(map);
        }
        return list1;
    }
    @RequestMapping("querycar4")
    @ResponseBody
    public List<Map<String,Object>>querycar(){
        List<Map<String,Object>> list= catservice.querycar4();
        List<Map<String,Object>> list1=new ArrayList<>();
        for(Map<String,Object>map1:list){
            Map<String,Object> map=new HashMap<>();
            Integer  object = Integer.parseInt(map1.get("颜色").toString());
            if(object==1){
                map.put("name","蓝色") ;
            }else if(object==2){
                map.put("name","白色");
            }else if (object==3){
                map.put("name","黑色");
            }else{
                map.put("name","红色");
            }
            Integer  aa = Integer.parseInt(map1.get("日").toString());
            Integer  bb = Integer.parseInt(map1.get("总个数").toString());
            System.out.println(aa);
            System.out.println(bb);
            int [] cc =new int[]{aa,bb};
            map.put("data",cc);
            list1.add(map);

        }
        return list1;
    }
}
