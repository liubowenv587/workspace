package com.jk.mapper;

import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface CatMapper {

    @Select(" SELECT count(*) 数量,DATE_FORMAT(carTime,'%m')  月份 from t_car b GROUP BY DATE_FORMAT(carTime,'%m')")
    List<Map<String, Object>> queryBook();
@Select("select count(color) 个数,color 颜色, DATE_FORMAT(carTime,'%Y') 月 from t_car c GROUP BY DATE_FORMAT(carTime,'%Y')")
    List<Map<String, Object>> queryCar1();
    @Select("select count(color) 个数,color 颜色, DATE_FORMAT(carTime,'%w') 月 from t_car c GROUP BY DATE_FORMAT(carTime,'%w')")
    List<Map<String, Object>> queryZhuxing();
    @Select("select  DATE_FORMAT(carTime,'%d') 日,count(color)  总个数,color 颜色   from t_car  t  GROUP  BY color")
    List<Map<String, Object>> querycar4();
}
