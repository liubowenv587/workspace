/** 
 * <pre>项目名称:jk_1812_mongodb 
 * 文件名称:ConstanConf.java 
 * 包名:com.jk.common 
 * 创建日期:2019年6月10日下午7:33:34 
 * Copyright (c) 2019, yuxy123@gmail.com All Rights Reserved.</pre> 
 */  
package com.jk.common;

public class ConstanConf {
	
/**
 * 左侧导航树缓存key
 */
	public static final String NAV_CACHE="navCache";

	
	/**
	 * 权限缓存key
	 */
	public static final String POWER_CACHE="powerCache";
	
	/**
	 * 部门缓存
	 */
	public static final String DEPT_CACHE="deptCache";
/**
 * 角色缓存key
 */
	public static final String ROLE_CACHE="roleCache";
	public static final String  AREA_CACHE = "areaCache";//二级联动省 
	
	
	/**
	 * 短信验证码服务地址
	 */
	public  static  final String SMS_WANG_YI = "https://api.netease.im/sms/sendcode.action";
	

	public  static  final String APPSECRET = "52bb174f4cb6";
	public  static  final String APPKEY = "61589acb0f7eaa658b6838500dca97d4";
	public  static  final String TEMPLATEID = "14801156";
	public  static  final String LOGINSMS = "loginSms";

	
	
	/**
	 * 智能机器人
	 */
	public  static  final String ZHINENG = "zhiNeng";
/**
 * 酷狗
 */
	public  static  final String KUGOY_SONG_LIST = "https://api.itooi.cn/music/kugou/search";
	public  static  final String KUGOY_MV_LIST = "https://api.itooi.cn/music/kugou/mv";
/**
 *酷狗请求秘钥
 */
	public  static  final String KUGOY_KEY = "579621905";
/**
 * 酷狗请求类型
 */
	public  static  final String KUGOY_SEARCH_TYPE = "mv";
}
