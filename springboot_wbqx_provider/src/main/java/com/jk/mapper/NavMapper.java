package com.jk.mapper;

import com.jk.model.NavBean;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.ArrayList;
import java.util.List;

public interface NavMapper {
	
	@Select("select distinct tn.* from t_nav tn left join t_role_power trp on tn.id=trp.powerId left join t_user_role tur on trp.roleId=tur.roleId where tur.userId=#{value}")
	List<NavBean> findNavList(Integer userId);
	@Insert("insert into t_nav (text,url,pid) values(#{text},#{url},#{pid})")
	void savePower(NavBean n);
	void delPowerByIds(ArrayList<Integer> powerArr);
	@Select("select * from t_nav where pid=#{value}")
	List<NavBean> findNavChildren(Integer powerId);
	@Select("select * from t_nav where id=#{value}")
	NavBean findPowerInfo(Integer powerId);
	@Update("update t_nav set text=#{text},url=#{url} where id=#{id}")
	void updatePower(NavBean n);

	@Select("select * from t_nav where pid=#{value}")
    List<NavBean> findTreeListByPid(Integer id);
}
