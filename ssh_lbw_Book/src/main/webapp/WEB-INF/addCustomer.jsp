<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/jquery-2.1.1.min.js"></script>
<!-- 引入上传文件 插件 -->
<script type="text/javascript" src="<%=request.getContextPath()%>/uploadify/jquery.uploadify.min.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/uploadify/uploadify.css" />
</head>
<body>
      <form id="form" method="post">
           备注:<input type="text" name="name" ><br/>
          公司名称:<input type="text" name="description"><br/>
          地区:<input type="text" name="location_id"><br/>
         录入时间:<input type="date" name="create_time"><br/>
         跟进时间:<input type="date" name="gj_time"><br/>
         电话号码:<input type="text" name="phone"><br/>
         部门     :<input type="text" name="department_id" ><br/>
        <input type="button" value="提交" onclick="addCustomer()">
      </form>
      
</body>
<script type="text/javascript">

    function addCustomer(){
    	
    	$.ajax({
           url:"<%=request.getContextPath()%>/customerAction!addCustomer.action",
           data:$("#form").serialize(),
           type:"post",
    	   success:function(){
    		   location.href="<%=request.getContextPath()%>/customerAction!querycustomer.action"
    		   
    	   }
    		
    	})
    	
    	
    }



</script>
</html>