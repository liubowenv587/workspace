/**
 * Copyright (C), 2015-2019, 金科教育
 * FileName: CatService
 * Author:   dell
 * Date:     2019/8/12 19:21
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名 ：刘博文   修改时间   new Date()  版本号              描述
 */
package com.jk.service;

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
public interface CatService {
    List<Map<String, Object>> queryBook();

    List<Map<String, Object>> queryCar1();

    List<Map<String, Object>> queryZhuxing();

    List<Map<String, Object>> querycar4();
}
