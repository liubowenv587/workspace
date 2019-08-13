$(function (){
	
	$("#editRole").click(function (){
		var user=$("#userTable").datagrid('getSelected');
		var roleArray=$("#roleTable").datagrid('getSelections');
		var roleIds="";
		$.each(roleArray,function (i,obj){
			roleIds+=obj.roleId+","; 
		});
		$.ajax({
			type:'post',
			url:"../user/saveRoleByUser.do",
			data:{'userId':user.userId,'roleIds':roleIds},
			success:function (data){
				alert("修改成功");
				
			}
		});
			
		});
		
	
	
	$("#userTable").datagrid({// 定义一下  数据表格
		width:800,
		//fit:true,
		url:"../user/findUser.do",
		title:"用户列表",
		//toolbar:"#tb",  // 定义工具栏
		rownumbers:true,    //  显示序列号
		singleSelect:true,
		idField:"userId",
		onClickRow:function (rowIndex, rowData){
			$.ajax({
				type:'post',
				url:"user/findRoleByUserId.do",
				data:{'userId':rowData.userId},
				dataType:"json",
				success:function (data){
					$("#roleTable").datagrid('clearSelections');
					$.each(data,function (i,obj){
						$("#roleTable").datagrid('selectRecord',obj);
					});
					
				}
			});
			
		},
		//loader:myLoader,
		columns:[[     //  列属性     因为 他们可以合并单元格
		           {field:'chkid',checkbox:true,width:100}, 
		           {field:'userId',title:'用户ID',width:100},      //  列    field   代表是 后台  传过来的值
		           {field:'userName',title:'用户名称',width:100},    
		           {field:'userPassWord',title:'用户密码',width:100}
		          /*  ,{field:'crud',title:'用户赋角色',width:100,
		        	formatter: function (value,row,index){ 
			        		return "<a href='javascript:findUserRole("+row.userId+")'>用户赋角色</a>";
			        }   
		           } */
		       ]],
		       pagination:true,
		       pageSize: 3,//每页显示的记录条数，默认为10     
		       pageList: [3,5,7],//可以设置每页记录条数的列表 
	})
	
	
		//  角色表格
	$("#roleTable").datagrid({// 定义一下  数据表格
		width:800,
		//fit:true,
		url:"../user/findRole.do",
		title:"角色列表",
		toolbar:"#tb",  // 定义工具栏
		rownumbers:true,    //  显示序列号
		idField:"roleId",
		//loader:myLoader,
		columns:[[     //  列属性     因为 他们可以合并单元格
		           {field:'chkid',checkbox:true,width:100}, 
		           {field:'roleId',title:'角色ID',width:100},      //  列    field   代表是 后台  传过来的值
		           {field:'roleName',title:'角色名称',width:100},    
		       ]]
	})
	
})
//----------------------------------------------------------------------
//树  直接沾
$(function(){
	var   tree=[{    
	    "id":1,    
	    "text":"用户模块",    
	    "iconCls":"icon-save",    
	    "children":[{    
	        "text":"用户赋角色",  
	        "url":"/dataGrid.jsp", 
	        "checked":true   
	    },
	    {    
	        "text":"角色赋权限",  
	        "url":"/role.jsp", 
	        "checked":true   
	    },
	    {    
	        "text":"权限明细",  
	        "url":"/powerinfo.jsp", 
	        "checked":true   
	    },{    
	        "text":"asdasd",    
	        "state":"open",    
	        "attributes":{    
	            "price":100    
	        },    
	        "children":[{    
	            "text":"PhotoShop",    
	            "checked":true   
	        },{    
	            "id": 8,    
	            "text":"Sub Bookds",    
	            "state":"closed"   
	        }]    
	    }]    
	},{    
	    "text":"Languages",    
	    "state":"closed",    
	    "children":[{    
	        "text":"Java"   
	    },{    
	        "text":"C#"   
	    }]    
	}];
	
	
	$("#menu").tree({
		data:tree,
		onClick:function(node){
		var  flag=$("#tab").tabs("exists",node.text); //  当前选项卡是否存在
		if(!flag){
		if(node.url){
		//单击tree的时候  添加一个新的选项卡  
			$('#tab').tabs('add',{    
				title:node.text, 
				content:'<iframe id="work"  src="<%=request.getContextPath()%>/'+node.url+'" style="width:100%;height:100%" ></iframe>',    
				closable:true,   
				tools:[{    
    			iconCls:'icon-mini-refresh',    //选项卡  上的刷新
   				handler:function(){    
				// 调用 'refresh' 方法更新选项卡面板的内容
				var tab = $('#tab').tabs('getSelected');  // 获取选择的面板tab.panel('refresh', 'get_content.php');
					$("#tab").tabs('update',{
					tab:tab,
					 options : {
    						  content : '<iframe id="work" data-options="fit:true" src="<%=request.getContextPath()%>/'+node.url+'" scrolling="auto" width="100%" height="100%"></iframe>',
					 }
			});
	        }
	    }]    
	}); 
	}
	}else{
	$("#tab").tabs('select',node.text);
	}
		}
		});
	
	
	
	
	
	
	
	
})