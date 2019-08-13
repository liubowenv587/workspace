<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Add and Remove Layout - jQuery EasyUI Demo</title>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/easyui/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/easyui/themes/icon.css">
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/easyui/demo/demo.css">
	<script type="text/javascript" src="<%=request.getContextPath()%>/easyui/jquery.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/easyui/jquery.easyui.min.js"></script>
</head>
<script type="text/javascript">
	(function($){
		$(document).ready(function() {
			var menu=[{
				"id":1,
				"text":"CMS管理系统",
				"children":[{
					"id":11,
					"text":"权限管理",
					"state":"closed",
					"children":[{
						"id":111,
						"text":"权限管理",
						"target":"$('#center')",
						"attributes":{"url":"flow_findevent.action"}
					},{
						"id":112,
						"text":"角色管理",
						"attributes":{"url":"flow_findevent.action"}
					},{
						"id":113,
						"text":"用户管理",
						"attributes":{"url":"flow_findevent.action"},
						"children":[{
							"id":1131,
							"text":"注册用户",
							"attributes":{"url":"<%=request.getContextPath()%>/index.jsp"}
						}]
					}]
				},{
					"id":12,
					"text":"新闻管理",
					"state":"closed",
					"children":[{
						"id":121,
						"text":"栏目管理",
						"attributes":{"url":"channel_list.action"}
					},{
						"id":122,
						"text":"新闻管理",
						"attributes":{"url":"news_list.action"}
					}]
				}]
			}];
			$("#menu").tree({
				
				data:menu,
				method:'get',
				animate:true,
				lines:true,
				onClick:function(node){
					if(node&&node.attributes&&node.attributes.url){
						var url=node.attributes.url;
						$("#workspace").attr({"src":url});
					}
				} 
			});
			
			//退出
			$("#logout").click(function(){
				$.ajax({
					type : "POST",
					url : "logout.action",
					dataType:"json",
					success : function(resp) {
						if (resp.ok) {
							window.location = "user_login.action";
						} else {
							alert(resp.message);
						}
					},
					error : function() {
						alert("退出失败");
					}
				});
			});
			
			$("#tab").tabs({
				border:0,
				onSelect:function (title){
				
				alert(title);
				}
			
			
			})
			
			
		});
	})(jQuery);
</script>
<body>
	<h2>金科管理系统</h2>
	<div class="demo-info">
		<div class="demo-tip icon-tip"></div>
		<div>用户:${userName}未定义权限</div>
	</div>
	<div style="margin:10px 0;">
		<span>Select Region Panel:</span>
		<select id="region">
			<option value="north">North</option>
			<option value="south">South</option>
			<option value="east">East</option>
			<option value="west">West</option>
		</select>
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="addPanel()">添加广告位</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="removePanel()">移除广告位</a>
        <a href="javascript:void(0)" class="easyui-linkbutton" onclick="window('open')">Open</a>
		<a href="javascript:void(0)" class="easyui-linkbutton" onclick="window('close')">Close</a>
	</div>
	
	
	
	
	
	
	
	<div id="cc" class="easyui-layout" style="width:1000px;height:960px;">
		
		
		<div data-options="region:'north'" style="height:50px">
			<font style="font-size: 50px;color: red;">广告位招商</font>
		</div>
		
		<div data-options="region:'south',split:true" style="height:50px;">下边</div>
		
		<div data-options="region:'east',split:true" title="East" style="width:200px;">右</div>
		
		<div data-options="region:'west',split:true" title="West"   style="width:200px;">
		左
		 
			<%-- <ul class="easyui-tree"
			data-options="url:'<%=ctx%>/tree_data1.json',method:'get',animate:true,lines:true"></ul> --%>
		<ul id="menu"/>
	
		</div>
		<div id="center" name="center" data-options="region:'center',title:'Center'">
		<!--  	<div class="easyui-tabs"  id="tab" style="width:500px:height:250px">
			<div title="Tab1" style="padding:20px;display:none;">
    			</div>
   			 <div title="Tab2" data-options="closable:true" style="overflow:auto;padding:20px;display:none;">
					
   			 </div>
    		<div title="tab3"     data-options="iconCls:'icon-reload',closable:true" style="padding:20px;display:none;">
    		</div>
			
		</div>-->
		
		<div class="easyui-tabs"  id="tab" style="overflow:auto;border:false">
			
			<div title="公司背景"  data-options="closable:false"  style="overflow:auto;padding:10px">
			<p style="font-size:14px">公司背景</p>
			<ul>
				<li>建立与2008年</li>
				<li>easyui provides essential functionality for building modem, interactive, javascript applications.</li>
				<li>using easyui you don't need to write many javascript code, you usually defines user-interface by writing some HTML markup.</li>
				<li>complete framework for HTML5 web page.</li>
				<li>easyui save your time and scales while developing your products.</li>
				<li>easyui is very easy but powerful.</li>
			</ul>
		</div>
		
		<div title="公司理念" style="padding:10px">
			
			<ul>
				<li>创新研发</li>
				<li>easyui provides essential functionality for building modem, interactive, javascript applications.</li>
				<li>using easyui you don't need to write many javascript code, you usually defines user-interface by writing some HTML markup.</li>
				<li>complete framework for HTML5 web page.</li>
				<li>easyui save your time and scales while developing your products.</li>
				<li>easyui is very easy but powerful.</li>
			</ul>
			
<!--			<ul class="easyui-tree" data-options="url:'tree_data1.json',method:'get',animate:true"></ul>-->
		</div>
	<!--	<div title="Help" data-options="iconCls:'icon-help',closable:true" style="padding:10px">
			<jsp:include page="/poster/jk.jsp"></jsp:include>
		</div>-->
	</div>
		
		
		
			
	</div>
<!--	<div>-->
<!--	<iframe id="workspace" scrolling="auto" width="100%" height="500"></iframe>-->
<!--		</div>-->
	
<!--	<div id="w" class="easyui-window" title="Basic Window" data-options="zIndex:9000,inline:true,iconCls:'icon-save',modal:true" style="width:500px;height:200px;padding:10px;">-->
<!--		<iframe id="workspace" scrolling="auto" width="100%" height="500" src="<%=request.getContextPath()%>/poster/jk.jsp"  ></iframe>-->
<!--	<jsp:include page="/poster/jk.jsp"></jsp:include>	-->
<!--	</div>-->
	  <div id="win" class="easyui-window" title="My Window" style="width:600px;height:400px"
   	 data-options="iconCls:'icon-save',modal:true">
    <div class="easyui-tabs"  id="tab" style="overflow:auto;border:false">
		<div title="公司介绍"  data-options="closable:false"  style="overflow:auto;padding:10px">
			<p style="font-size:50px;color: red;">欢迎用户${userName}登录OA管理系统</p>
<!--			<ul>-->
<!--				<li>公司位于海淀区悦路</li>-->
<!--				<li>easyui provides essential functionality for building modem, interactive, javascript applications.</li>-->
<!--				<li>using easyui you don't need to write many javascript code, you usually defines user-interface by writing some HTML markup.</li>-->
<!--				<li>complete framework for HTML5 web page.</li>-->
<!--				<li>easyui save your time and scales while developing your products.</li>-->
<!--				<li>easyui is very easy but powerful.</li>-->
<!--			</ul>-->
		</div>
		<div title="公司业务" style="padding:10px">
			<p ><a href="#" style="font-size: 50px">培养IT精英</a></p>		
			<p><a href="#" style="font-size: 50px">创新IT理念</a></p>
		</div>
		<div title="广告位招商" style="padding:10px"></div>
		<div title="广告位招商" style="padding:10px"></div>
	
	</div>
<!--    <div class="easyui-layout" data-options="fit:true">-->
<!--		<div data-options="region:'center'">-->
<!--			<iframe id="workspace" scrolling="false" width="100%" height="500" src="<%=request.getContextPath()%>/poster/jk.jsp"   ></iframe>-->
<!--		</div>-->
<!--    </div>-->
</div>
	
	<script type="text/javascript">
		function addPanel(){
			var region = $('#region').val();
			var options = {
				region: region
			};
			if (region=='north' || region=='south'){
				options.height = 50;
			} else {
				options.width = 100;
				options.split = true;
				options.title = $('#region option:selected').text();
			}
			$('#cc').layout('add', options);
		}
		function removePanel(){
			$('#cc').layout('remove', $('#region').val());
		}
	</script>
</body>
</html>