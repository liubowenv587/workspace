package com.jk.mapper;

import com.jk.model.Cat;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.HashMap;
import java.util.List;

public interface BookMapper {
    @Select("select count(1) from t_cat")
    Integer queryHeroCount(HashMap<String, Object> map);
    @Select("select * from t_cat limit ${start},${end}")
    List<Cat> queryHero(HashMap<String, Object> map);

    @Select("select * from t_cat where id=#{value}")
    Cat findBootDialogById(String id);
    @Update("update  t_cat set catName='${catName}',catType=${catType},imgId='${imgId}',status=${status}  where id=${id}")
    void updatecat(Cat cat);

    @Insert("insert into  t_cat  (catName,catType,createTime,status) VALUES (#{catName},#{catType},#{createTime},#{status})")
    void addcat(Cat cat);
    @Delete("delete from t_cat where  id=#{id}")
    void deleteCat(String id);
}
