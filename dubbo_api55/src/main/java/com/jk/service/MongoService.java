package com.jk.service;

import com.jk.model.LogModel;

import java.util.List;

public interface MongoService {


    List<LogModel> queryLogList();
}
