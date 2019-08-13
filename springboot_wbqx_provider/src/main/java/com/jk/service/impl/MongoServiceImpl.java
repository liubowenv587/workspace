package com.jk.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.jk.model.LogModel;
import com.jk.service.MongoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;


@Service
public class MongoServiceImpl implements MongoService {


    @Autowired
    private MongoTemplate mongoTemplate;


    @Override
    public  List<LogModel> queryLogList() {
        Query query = new Query();
        return mongoTemplate.find(query,LogModel.class,"logdb2");
    }
}
