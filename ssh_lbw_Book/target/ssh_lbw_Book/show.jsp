<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
  <a href="<%=request.getContextPath()%>/customerAction!toAdd.action">新增</a>
  <table border="1">
     <tr>
       <td>主键</td>
       <td>备注</td>
       <td>公司名称</td>
       <td>地区</td>
       <td>录入时间</td>
       <td>跟进时间</td>
       <td>电话号码</td>
       <td>部门</td>
       <td>操作</td>
     </tr>
  <c:forEach items="${list}" var="l">
   <tr>
       <td>${l[0].id}</td>
       <td>${l[0].description}</td>
       <td>${l[0].name}</td>
       <td>${l[2].name}</td>
       <td>${l[0].create_time}</td>
       <td>${l[0].gj_time}</td>
       <td>${l[0].phone}</td>
       <td>${l[1].name}</td>
       <td><input type="button" onclick="del(${l[0].id})" value="删除"></td>
     </tr>
  
  
  </c:forEach>
  
  </table>
</body>
<script type="text/javascript">

  function del(id){
	  location.href="<%=request.getContextPath()%>/customerAction!delCustomer.action?id="+id;
  }


</script>
</html>