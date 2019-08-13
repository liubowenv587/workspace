package com.jk.service.impl;

import com.jk.model.Cat;

import java.util.HashMap;

public interface BookService {

    HashMap<String, Object> findBootstrap(Integer page, Integer rows, Cat cat);

    Cat findBootDialogById(String id);

    void updatecat(Cat cat);

    void addcat(Cat cat);

    void deleteCat(String id);
}
