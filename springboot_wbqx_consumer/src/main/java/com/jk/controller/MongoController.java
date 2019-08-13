package com.jk.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jk.model.LogModel;
import com.jk.service.MongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("mongodb")
public class MongoController {

    @Reference
    private MongoService mongoService;

    @Autowired
    private MongoTemplate mongoTemplate;

    @RequestMapping("queryLogList")
    @ResponseBody
    public List<LogModel> queryLogList(){
        return mongoService.queryLogList();
    }

}
