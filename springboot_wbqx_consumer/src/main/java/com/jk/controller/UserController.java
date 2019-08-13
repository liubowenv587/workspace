package com.jk.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.jk.model.*;
import com.jk.service.UserService;
import com.jk.util.ExportExcel;
import com.jk.utils.ResultPage;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("user")
public class UserController {
	@Reference
	private UserService userService;


//快捷登陆
//登陆
@RequestMapping("login")
@ResponseBody
public String loginUser(UserBean user, HttpServletRequest request) {

	String str = "0";
	UserBean user2 = userService.login(user);

	if(user2==null){
		return "userError";
	}
	if(!user2.getPassword().equals(user.getPassword())){
		return "passError";
	}

	request.getSession().setAttribute("user",user2);

	str = "1";

	return str;

}

	@RequestMapping("findCount")
	@ResponseBody
	public Integer findCount(){
		return userService.findCount();
		
	}
	@RequestMapping("findUserList")
	@ResponseBody
	public ResultPage findUserList(Integer page,Integer rows,UserBean userBean){
		return userService.findUserList(page, rows, userBean);
	}
	//部门下拉树
			@RequestMapping("findDept")
			@ResponseBody
			public List<DeptBean> findDept(){
				
				return userService.findDept();
			}
	@RequestMapping("findArea")
	@ResponseBody
	public List<AreaBean> findArea(Integer id){
		
		return userService.findArea(id);
		
	}
	@RequestMapping("findRoleList")
	@ResponseBody
	public List<RoleBean> findRoleList(){
		
		return userService.findRoleList();
		
	}
	@RequestMapping("saveUser")
	@ResponseBody
	public Boolean saveUser(UserBean userBean){
		try {
			if (userBean.getId()!=null) {
				userService.updateUser(userBean);
			}else{
				userService.saveUser(userBean);
			}
			
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
			return false;
		}
		
		
	}
	//新增角色
	@RequestMapping("saveRole")
	@ResponseBody
	public Boolean saveRole(RoleBean roleBean){
		try {
			
				userService.saveRole(roleBean);
			
			
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
			return false;
		}
		
		
	}
	
	//批删
	@RequestMapping("delRoleByIds")
	@ResponseBody
	public Boolean delRoleByIds(Integer[] ids){
		return userService.delRoleByIds(ids);
	}
	
	@RequestMapping("findRolePower")
	@ResponseBody
	public List<NavBean> findRolePower(Integer roleId){
		return userService.findRolePower(roleId);
	}
	
	@RequestMapping("findUserInfoById")
	@ResponseBody
	public UserBean findUserInfoById(Integer id){
		return userService.findUserInfoById(id);
	}
	//角色绑定权限
	
	@RequestMapping("saveRolePower")
	@ResponseBody
	public Boolean saveRolePower(Integer roleId,Integer[] powerIds){
		try {
			userService.saveRolePower(roleId,powerIds);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		
	}
	//查详细菜单
	@RequestMapping("findPowerMenuList")
	@ResponseBody
	public List<PowerMenuBean> findPowerMenuList(PowerMenuBean pmb){
		return userService.findPowerMenuList(pmb);
		
	}
	//详情新增
	@RequestMapping("savePowerDetailMenu")
	@ResponseBody
	public Boolean savePowerDetailMenu(PowerMenuBean pmb){
		try {
			if (pmb.getId()!=null) {
				userService.updatePowerDetailMenu(pmb);
			}else{
				userService.savePowerDetailMenu(pmb);
			}
			
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
	}
	
	//批删详情
	
	@RequestMapping("delDetailByIds")
	@ResponseBody
	public Boolean delDetailByIds(Integer[] ids){
		return userService.delDetailByIds(ids);
	}
	//修改详情
	
	@RequestMapping("findDetailInfoById")
	@ResponseBody
	public PowerMenuBean findDetailInfoById(Integer id){
		return userService.findDetailInfoById(id);
	}

		 @RequestMapping("zhuxiao")
		 public String zhuxiao(HttpServletRequest request){
			HttpSession session = request.getSession();
			session.removeAttribute(session.getId());
			  return "../login";
		  }
			@RequestMapping("doLock")
			@ResponseBody
			public String doLock(Integer id,Integer status){
				userService.doLock(id,status);
				return "";
			}


	@RequestMapping("exportExcel")
	public void exportExcel(HttpServletResponse response){
		//导出的excel的标题
		String title = "刘博文";
		//导出excel的列名
		String[] rowName = {"id","账户","密码","年龄","角色信息","性别","状态","开通时间"};
		//导出的excel数据
		List<Object[]> dataList = new ArrayList<Object[]>();
		//查询的数据库的书籍信息
		List<UserBean> list=   userService.query();
		//循环书籍信息
		for(UserBean userBean:list){
			Object[] obj =new Object[rowName.length];
			obj[0]=userBean.getId();
			obj[1]=userBean.getAccount();
			obj[2]=userBean.getPassword();
			obj[3]=userBean.getAge();
			if(userBean.getSex()==1){
				obj[4]="男";
			}else{
				obj[4]="女";
			}
			obj[5]=userBean.getStatus();
			if(userBean.getStatus()==1){
				obj[6]="锁定";
			}else{
				obj[6]="正常";
			}
			SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
			String format = sdf.format(userBean.getCreateTime());
			obj[7]=format;
			dataList.add(obj);
		}
		ExportExcel exportExcel =new ExportExcel(title,rowName,dataList,response);
		try {
			exportExcel.export();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@RequestMapping("importExcel")
	public String importExcel(MultipartFile file, HttpServletResponse response){
		//获得上传文件上传的类型
		String contentType = file.getContentType();
		//上传文件的名称
		String fileName = file.getOriginalFilename();
		//获得文件的后缀名
		String fileType = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
		//上传文件的新的路径
		//生成新的文件名称
		String filePath = "../src/main/resources/templates/fileupload/";
		//创建list集合接收excel中读取的数据
		List<UserBean> list =new ArrayList<>();
		try {
			uploadFile(file.getBytes(), filePath, fileName);
			SimpleDateFormat sdf =new SimpleDateFormat("yyyy-MM-dd");
			//通过文件忽的WorkBook
			FileInputStream fileInputStream = new FileInputStream(filePath+fileName);
			Workbook workbook = WorkbookFactory.create(fileInputStream);

			//通过workbook对象获得sheet页 有可能不止一个sheet
			for(int i=0 ;i<workbook.getNumberOfSheets();i++){
				//获得里面的每一个sheet对象
				Sheet sheetAt = workbook.getSheetAt(i);
				//通过sheet对象获得每一行
				for(int j=3;j<sheetAt.getPhysicalNumberOfRows();j++){
					//创建一个book对象接收excel的数据
					UserBean userBean=new UserBean();
					//获得每一行的数据
					Row row = sheetAt.getRow(j);

					//获得每一个单元格的数据
					if(row.getCell(1)!=null && !"".equals(row.getCell(1))){
						userBean.setAccount(this.getCellValue(row.getCell(1)));
					}
					if(row.getCell(2)!=null && !"".equals(row.getCell(2))){
						userBean.setPassword(this.getCellValue(row.getCell(2)));
					}
					if(row.getCell(3)!=null && !"".equals(row.getCell(3))){
						userBean.setAge(Integer.parseInt(this.getCellValue(row.getCell(3))));
					}
					if(row.getCell(4)!=null && !"".equals(row.getCell(4))){
						userBean.setRoleName(this.getCellValue(row.getCell(4)));
					}
					if(row.getCell(5)!=null && !"".equals(row.getCell(5))){
						String cellValue = this.getCellValue(row.getCell(5));
						if("男".equals(cellValue)){
							userBean.setSex(1);
						}else{
							userBean.setSex(0);
						}
					}
					if(row.getCell(6)!=null && !"".equals(row.getCell(7))){
						String cellValue = this.getCellValue(row.getCell(7));
						if("锁定".equals(cellValue)){
							userBean.setStatus(1);
						}else{
							userBean.setStatus(0);
						}
					}
					if(row.getCell(7)!=null && !"".equals(row.getCell(7))){
						userBean.setCreateTime(sdf.parse(this.getCellValue(row.getCell(7))));
					}
					list.add(userBean);
				}
			}
			userService.addBook(list);

		} catch (Exception e) {
			e.printStackTrace();
		}


		return "userlist";
	}

	//上传文件的方法
	public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
		File targetFile = new File(filePath);
		if (!targetFile.exists()) {
			targetFile.mkdirs();
		}
		FileOutputStream out = new FileOutputStream(filePath + fileName);
		out.write(file);
		out.flush();
		out.close();
	}

	//判断从Excel文件中解析出来数据的格式
	private static String getCellValue(Cell cell){
		String value = null;
		//简单的查检列类型
		switch(cell.getCellType())
		{
			case Cell.CELL_TYPE_STRING://字符串
				value = cell.getRichStringCellValue().getString();
				break;
			case Cell.CELL_TYPE_NUMERIC://数字
				long dd = (long)cell.getNumericCellValue();
				value = dd+"";
				break;
			case Cell.CELL_TYPE_BLANK:
				value = "";
				break;
			case Cell.CELL_TYPE_FORMULA:
				value = String.valueOf(cell.getCellFormula());
				break;
			case Cell.CELL_TYPE_BOOLEAN://boolean型值
				value = String.valueOf(cell.getBooleanCellValue());
				break;
			case Cell.CELL_TYPE_ERROR:
				value = String.valueOf(cell.getErrorCellValue());
				break;
			default:
				break;
		}
		return value;
	}
	//判断从Excel文件中解析出来数据的格式
	private static String getCellValue(XSSFCell cell){
		String value = null;
		//简单的查检列类型
		switch(cell.getCellType())
		{
			case HSSFCell.CELL_TYPE_STRING://字符串
				value = cell.getRichStringCellValue().getString();
				break;
			case HSSFCell.CELL_TYPE_NUMERIC://数字
				long dd = (long)cell.getNumericCellValue();
				value = dd+"";
				break;
			case HSSFCell.CELL_TYPE_BLANK:
				value = "";
				break;
			case HSSFCell.CELL_TYPE_FORMULA:
				value = String.valueOf(cell.getCellFormula());
				break;
			case HSSFCell.CELL_TYPE_BOOLEAN://boolean型值
				value = String.valueOf(cell.getBooleanCellValue());
				break;
			case HSSFCell.CELL_TYPE_ERROR:
				value = String.valueOf(cell.getErrorCellValue());
				break;
			default:
				break;
		}
		return value;
	}
}
