package com.jk.mapper;

import com.jk.model.*;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface UserMapper {

	Integer findCount();

	int findUserCount(HashMap<String, Object> params);

	List<UserBean> findUserList(HashMap<String, Object> params);
	@Select("select d.id,d.name as text from t_dept d where d.pid=#{value}")
	List<DeptBean> findDeptChildren(Integer id);
	@Select("select * from t_area where pid=#{value}")
	List<AreaBean> findArea(Integer id);
	@Select("select * from t_role")
	List<RoleBean> findRoleList();

	void saveUser(UserBean userBean);

	int delRoleByIds(Integer[] ids);

	void saveUserRole(ArrayList<UserRoleBean> userRoleParams);
	@Select("select * from t_role_power where roleId=#{value}")
	List<RolePowerBean> findRolePower(Integer roleId);
	@Select("select id,text,pid from t_nav where pid=#{value}")
	List<NavBean> findNavChildren(Integer id);
	//GROUP_CONCAT(tur.`roleId`) AS roleIds多个角色拼接
	@Select("SELECT tu.id,tu.account,tu.`dept_id` AS deptId,tu.`age`,tu.`sex`,tu.`provinceCode`,tu.`cityCode`,GROUP_CONCAT(tur.`roleId`) AS roleIds FROM t_user tu LEFT JOIN t_user_role tur ON tu.`id`=tur.`userId` WHERE tu.`id`=#{value} GROUP BY tu.`id`")
	UserBean findUserInfoById(Integer id);
	@Update("update t_user set account=#{account},dept_id=#{deptId},age=#{age},sex=#{sex},provinceCode=#{provinceCode},cityCode=#{cityCode}  where id=#{id}")
	void updateUser(UserBean userBean);


	@Delete("delete from t_user_role where userId=#{value}")
	void delUserRole(Integer id);
	@Delete("delete from t_role_power where roleId=#{value}")
	void delRolePower(Integer roleId);

	void saveRolePower(ArrayList<RolePowerBean> rolePowers);
	@Insert("insert into t_role (name,createTime) values (#{name},#{createTime})")
	void saveRole(RoleBean roleBean);

	List<PowerMenuBean> findPowerMenuList(PowerMenuBean pmb);
	@Insert("insert into t_nav_menu(name,url,remark,createTime,powerId)values(#{name},#{url},#{remark},now(),#{powerId}) ")
	void savePowerDetailMenu(PowerMenuBean pmb);

	int delDetailByIds(Integer[] ids);
	@Select("SELECT name,url,remark from t_nav_menu where id=#{value}")
	PowerMenuBean findDetailInfoById(Integer id);
	@Update("update t_nav_menu set name=#{name},url=#{url},remark=#{remark} where id=#{id}")
	void updatePowerDetailMenu(PowerMenuBean pmb);
	//登录
	@Select("select * from t_user where account = #{account}")
	UserBean findUserInfo(String account);
	//拦截查权限
	@Select("select distinct tn.url from t_nav tn left join t_role_power trp on tn.id=trp.powerId left join t_user_role tur on trp.roleId=tur.roleId where tur.userId=#{value} and tn.url is not null union select distinct tnm.url  from t_nav_menu tnm  left join t_role_power trp on tnm.powerId=trp.powerId left join t_user_role tur on trp.roleId=tur.roleId where tur.userId=#{value} and tnm.url is not null")
	List<String> findUserPowerList(Integer userId);

	@Update("update t_user set status=1 where id=#{id}")
	void openLock(Integer id);

	@Update("update t_user set status=0  where id=#{id}")
	void lock(Integer id);
@Select("select * from t_user")
    List<UserBean> query();

    void addBook(List<UserBean> list);
}
