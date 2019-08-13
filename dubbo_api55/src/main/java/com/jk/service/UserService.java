package com.jk.service;

import com.jk.model.*;
import com.jk.utils.ResultPage;

import java.util.List;

public interface UserService {

	Integer findCount();
	ResultPage findUserList(Integer page, Integer rows, UserBean userBean);
	List<DeptBean> findDept();
	List<AreaBean> findArea(Integer id);
	List<RoleBean> findRoleList();
	void saveUser(UserBean userBean);
	Boolean delRoleByIds(Integer[] ids);
	List<NavBean> findRolePower(Integer roleId);
	UserBean findUserInfoById(Integer id);
	void updateUser(UserBean userBean);
	void saveRolePower(Integer roleId, Integer[] powerIds);
	void saveRole(RoleBean roleBean);
	List<PowerMenuBean> findPowerMenuList(PowerMenuBean pmb);
	void savePowerDetailMenu(PowerMenuBean pmb);
	Boolean delDetailByIds(Integer[] ids);
	PowerMenuBean findDetailInfoById(Integer id);
	void updatePowerDetailMenu(PowerMenuBean pmb);

//拦截查权限
	List<String> findUserPowerList(Integer userId);

	void doLock(Integer id, Integer status);

	UserBean login(UserBean user);

    List<UserBean> query();

    void addBook(List<UserBean> list);
}
