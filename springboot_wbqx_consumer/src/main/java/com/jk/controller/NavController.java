package com.jk.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jk.common.ConstanConf;
import com.jk.model.NavBean;
import com.jk.service.NavService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Controller
@RequestMapping("nav")
public class NavController {
	@Reference
	private NavService navService;

	@Autowired
	private  RedisTemplate redisTemplate;

	@RequestMapping("findNavList")
	@ResponseBody
	public List<NavBean> findNavList(){
		List<NavBean> tree = new ArrayList<>();
		//1、定义缓存key
		String key = ConstanConf.NAV_CACHE+"emp";
		//2、从缓存查看是否有当前用户的权限树
		if(!redisTemplate.hasKey(key)){
			System.out.println("======走数据库");
			//3、如果没有：a、从数据库查  b、把数据缓存到redis
			tree =navService.findNavList();
			//b、把数据缓存到redis
			redisTemplate.opsForValue().set(key, tree);
			//设置过期时间
			redisTemplate.expire(key, 30, TimeUnit.MINUTES);
		}else{
			System.out.println("======走缓存");
			//4、如果有：从缓存获取数据，返回数据
			tree = (List<NavBean>) redisTemplate.opsForValue().get(key);
		}
		return tree;
	}
	//添加nav权限导航节点
		@RequestMapping("savePower")
		@ResponseBody
		public Boolean savePower(NavBean n){
			try {
				if (n.getId()!=null) {
					navService.updatePower(n);
				}else{
				navService.savePower(n);
				}
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		
			//删除nav节点
			@RequestMapping("delPowerNode")
			@ResponseBody
			public Boolean delPowerNode(Integer powerId){
				try {
					navService.delPowerNode(powerId);
					return true;
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
			}
			//修改nav节点 
			@RequestMapping("findPowerInfo")
			@ResponseBody
			public NavBean findPowerInfo(Integer powerId){
				return navService.findPowerInfo(powerId);
				
			}
			
			
			
	
	
	

}
