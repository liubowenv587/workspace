package com.jk.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.jk.mapper.UserMapper;
import com.jk.model.*;
import com.jk.service.UserService;
import com.jk.utils.Md5Util;
import com.jk.utils.ResultPage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;

	@Resource
	private RedisTemplate<String, Object> redisTemplate;
	@Override
	public Integer findCount() {
		// TODO Auto-generated method stub
		return userMapper.findCount();
	}

	@Override
	public ResultPage findUserList(Integer page, Integer rows, UserBean userBean) {
		// TODO Auto-generated method stub
		ResultPage resultPage = new ResultPage();
		HashMap<String, Object> params=new HashMap<>();
		params.put("userBean", userBean);
		int count=userMapper.findUserCount(params);
		resultPage.setTotal(count);
		params.put("startIndex", (page-1)*rows);
		params.put("endIndex", rows);
		List<UserBean> users=userMapper.findUserList(params);
		resultPage.setRows(users);
		return resultPage;
	}

	@Override
	public List<DeptBean> findDept() {
		Integer id=0;
		List<DeptBean> depts=userMapper.findDeptChildren(id);
		return depts;
	}


	@Override
	public List<AreaBean> findArea(Integer id) {
		// TODO Auto-generated method stub
		return userMapper.findArea(id);

	}

	@Override
	public List<RoleBean> findRoleList() {
		List<RoleBean> findRoleList = userMapper.findRoleList();

		return findRoleList;
	}

	@Override
	public void saveUser(UserBean userBean) {
		// TODO Auto-generated method stub
		//保存用户信息到用户表，将保存的主键id返回
		/*userMapper.saveUser(userBean);*/
		//密码加密
		String md516 = Md5Util.getMd516(userBean.getPassword());
		userBean.setPassword(md516);

		userMapper.saveUser(userBean);
		//将用户角色信息批量保存至用户角色关联表
		ArrayList<UserRoleBean> userRoleParams = new ArrayList<UserRoleBean>();
		String[] roleIds = userBean.getRoleIds().split(",");
		for (String roleId : roleIds) {
			UserRoleBean userRoleBean = new UserRoleBean();
			userRoleBean.setRoleId(Integer.parseInt(roleId));
			userRoleBean.setUserId(userBean.getId());
			userRoleParams.add(userRoleBean);
		}
		userMapper.saveUserRole(userRoleParams);
	}

	@Override
	public Boolean delRoleByIds(Integer[] ids) {
		// TODO Auto-generated method stub
		int count=userMapper.delRoleByIds(ids);
		//判断删除条数是否小于0或和传来id数量是否相等，删除失败
		if (count<=0||ids.length!=count) {
			return false;
		}
		return true;
	}

	@Override
	public List<NavBean> findRolePower(Integer roleId) {
		// TODO Auto-generated method stub
		//查询当前角色拥有的权限
		List<RolePowerBean> rolePowers=userMapper.findRolePower(roleId);
		//查看权限导航树
		Integer id=0;

		List<NavBean> findNavNode = findNavNode(rolePowers,id);
		ArrayList<NavBean> rootNode = new ArrayList<NavBean>();
		//构建虚拟根节点
		NavBean navBean = new NavBean();
		navBean.setId(0);
		navBean.setText("根节点");
		navBean.setChildren(findNavNode);
		rootNode.add(navBean);
		return rootNode;



	}
	//递归树
	private List<NavBean> findNavNode(List<RolePowerBean> rolePowers, Integer id) {
		List<NavBean> navs=userMapper.findNavChildren(id);
		for (NavBean navBean : navs) {
			for (RolePowerBean rolePower : rolePowers) {
				if (navBean.getId()==rolePower.getPowerId()) {
					navBean.setChecked(true);
				}
			}
			List<NavBean> findNavNode = findNavNode(rolePowers,navBean.getId());
			navBean.setChildren(findNavNode);
		}
		return navs;
	}

	@Override
	public UserBean findUserInfoById(Integer id) {
		// TODO Auto-generated method stub
		return userMapper.findUserInfoById(id);
	}

	@Override
	public void updateUser(UserBean userBean) {
		// TODO Auto-generated method stub
		//修改用户表
		userMapper.updateUser(userBean);
		//修改用户角色，先删除原有角色
		userMapper.delUserRole(userBean.getId());
		//添加新角色
		String[] roles = userBean.getRoleIds().split(",");

		ArrayList<UserRoleBean> userRoles = new ArrayList<UserRoleBean>();
		for (String roleId : roles) {
			UserRoleBean userRoleBean = new UserRoleBean();
			userRoleBean.setRoleId(Integer.parseInt(roleId));
			userRoleBean.setUserId(userBean.getId());
			userRoles.add(userRoleBean);
		}
		userMapper.saveUserRole(userRoles);



	}

	@Override
	public void saveRolePower(Integer roleId, Integer[] powerIds) {
		// TODO Auto-generated method stub
		//删除角色原有权限

		userMapper.delRolePower(roleId);


		//添加角色新选择权限
		if (powerIds.length>0) {
			ArrayList<RolePowerBean> rolePowers = new ArrayList<RolePowerBean>();
			for (Integer powerId : powerIds) {
				RolePowerBean rolePowerBean = new RolePowerBean();
				rolePowerBean.setPowerId(powerId);
				rolePowerBean.setRoleId(roleId);
				rolePowers.add(rolePowerBean);
			}
			userMapper.saveRolePower(rolePowers);
		}
	}

	@Override
	public void saveRole(RoleBean roleBean) {
		// TODO Auto-generated method stub
		userMapper.saveRole(roleBean);
	}

	@Override
	public List<PowerMenuBean> findPowerMenuList(PowerMenuBean pmb) {
		// TODO Auto-generated method stub
		return userMapper.findPowerMenuList(pmb);
	}

	@Override
	public void savePowerDetailMenu(PowerMenuBean pmb) {
		// TODO Auto-generated method stub
		userMapper.savePowerDetailMenu(pmb);
	}

	@Override
	public Boolean delDetailByIds(Integer[] ids) {
		int count=userMapper.delDetailByIds(ids);
		//判断删除条数是否小于0或和传来id数量是否相等，删除失败
		if (count<=0||ids.length!=count) {
			return false;
		}
		return true;
	}

	@Override
	public PowerMenuBean findDetailInfoById(Integer id) {
		// TODO Auto-generated method stub
		return userMapper.findDetailInfoById(id);
	}

	@Override
	public void updatePowerDetailMenu(PowerMenuBean pmb) {
		// TODO Auto-generated method stub
		userMapper.updatePowerDetailMenu(pmb);
	}


	@Override
	public List<String> findUserPowerList(Integer userId) {
		// TODO Auto-generated method stub
		return userMapper.findUserPowerList(userId);
	}

	/* (non-Javadoc)
	 * @see com.jk.service.UserService#doLock(java.lang.Integer, java.lang.Integer)
	 */
	@Override
	public void doLock(Integer id, Integer status) {
		// TODO Auto-generated method stub
		if(status==1){
			userMapper.openLock(id);
		}else{

			userMapper.lock(id);

		}

	}

	@Override
	public UserBean login(UserBean user) {

		return userMapper.findUserInfo(user.getAccount());
	}

	@Override
	public List<UserBean> query() {
		return userMapper.query();
	}

	@Override
	public void addBook(List<UserBean> list) {
		userMapper.addBook(list);
	}


}
