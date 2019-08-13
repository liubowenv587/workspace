/**
 * Copyright (C), 2015-2019, 金科教育
 * FileName: BookServiceImpl
 * Author:   dell
 * Date:     2019/8/6 19:44
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名 ：刘博文   修改时间   new Date()  版本号              描述
 */
package com.jk.service.impl;

import com.jk.mapper.BookMapper;
import com.jk.model.Cat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author dell
 * @create 2019/8/6
 * @since 1.0.0
 */
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;


    @Override
    public HashMap<String, Object> findBootstrap(Integer page, Integer rows, Cat cat) {
        Integer start = (page - 1) * rows;
        Integer end = start + rows;
        HashMap<String, Object> map = new HashMap<>();
        map.put("start", start);
        map.put("end", end);
        map.put("cat", cat);
        HashMap<String, Object> hash = new HashMap<>();
        Integer count = bookMapper.queryHeroCount(map);
        hash.put("total", count);
        List<Cat> list = bookMapper.queryHero(map);
        hash.put("rows", list);
        return hash;
    }

    @Override
    public Cat findBootDialogById(String id) {
        return bookMapper.findBootDialogById(id);
    }

    @Override
    public void updatecat(Cat cat) {
        bookMapper.updatecat(cat);
    }

    @Override
    public void addcat(Cat cat) {
        bookMapper.addcat(cat);

    }

    @Override
    public void deleteCat(String id) {
        bookMapper.deleteCat(id);
    }

    @Override
    public List<Cat> query() {
        return bookMapper.query();
    }

    @Override
    public void addBook(List<Cat> list) {
        bookMapper.addBook(list);
    }


}
