package com.jk.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.jk.mapper.NavMapper;
import com.jk.model.NavBean;
import com.jk.service.NavService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import sun.reflect.generics.tree.Tree;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;



@Service
public class NavServiceImpl implements NavService {
	@Autowired
	private NavMapper navMapper;

	//@Resource
	/*@Resource
	private RedisTemplate  redisTemplate;*/


	@Override
	public List<NavBean> findNavList() {
		Integer id=0;
		List<NavBean> node = getNode(id);
		return node;
	}

	private List<NavBean> getNode(Integer id) {
		List<NavBean> findTreeListByPid = navMapper.findTreeListByPid(id);
		for (NavBean tree : findTreeListByPid) {
			Integer id2 = tree.getId();
			List<NavBean> node = getNode(id2);
			tree.setChildren(node);
		}
		return findTreeListByPid;
	}

	@Override
	public void savePower(NavBean n) {
		// TODO Auto-generated method stub
		navMapper.savePower(n);
	}



	@Override
	public void delPowerNode(Integer powerId) {
		// TODO Auto-generated method stub
		//构建一个记录所有子节点id的集合
		ArrayList<Integer> powerArr = new ArrayList<Integer>();
		//将当前节点id放到要被删除集合中
		powerArr.add(powerId);
		//查找当前节点id下的子节点
		findPowerChildren(powerId, powerArr);
		//批量删除
		navMapper.delPowerByIds(powerArr);
		
	}


	private void findPowerChildren(Integer powerId, ArrayList<Integer> powerArr) {
		//递归
		List<NavBean> findNavChildren=navMapper.findNavChildren(powerId);
		for (NavBean navBean : findNavChildren) {
			powerArr.add(navBean.getId());
			findPowerChildren(navBean.getId(),powerArr);
		}
	}





	@Override
	public NavBean findPowerInfo(Integer powerId) {
		// TODO Auto-generated method stub
		
		return navMapper.findPowerInfo(powerId);
	}





	@Override
	public void updatePower(NavBean n) {
		// TODO Auto-generated method stub
		navMapper.updatePower(n);
	}





	

}
