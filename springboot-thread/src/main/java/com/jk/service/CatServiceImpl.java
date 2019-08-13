/**
 * Copyright (C), 2015-2019, 金科教育
 * FileName: CatServiceImpl
 * Author:   dell
 * Date:     2019/8/12 19:24
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名 ：刘博文   修改时间   new Date()  版本号              描述
 */
package com.jk.service;

import com.jk.mapper.CatMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
@Service
public class CatServiceImpl implements CatService {
    @Autowired
    private CatMapper catMapper;

    @Override
    public List<Map<String, Object>> queryBook() {
        return catMapper.queryBook();
    }

    @Override
    public List<Map<String, Object>> queryCar1() {
        return catMapper.queryCar1();
    }

    @Override
    public List<Map<String, Object>> queryZhuxing() {
        return catMapper.queryZhuxing();
    }

    @Override
    public List<Map<String, Object>> querycar4() {
        return catMapper.querycar4();
    }
}
