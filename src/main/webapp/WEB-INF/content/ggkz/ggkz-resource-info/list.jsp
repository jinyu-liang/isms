<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>RFID资源管理列表</title>
	<meta name="decorator" content="main"/>
	
	<script type="text/javascript">
		/**
	    function selectAllBox(){
		 alert('1');
		  var checkAll=document.getElementById("checkAll");
		  
		  var resourceids=document.getElementByName("resourceids");
		  
		       if(checkAll.selected==true){
		  
				  for(var i=0;i<resourceids.length;i++){
				 
				    resourceids[i].selected=true;
				  }
				 }else{
				 
				     for(var j=0;j<resourceids.length;j++){
				       resourceids[i].selected=false;
			         }
		          }
		 }
		*/
	
	</script>
</head>

	 <body>
		<!-- <form name="checkForm" id="checkForm" method="post" action="ggkz-resource-info!delete.action">
		　　<ul>
		 　　　　<li><input type="checkbox" name="checkAll" id="checkAll" onclick="selectAllBox()"/>全选</li>
		 
		　　　　<li><input type="checkbox" name="resourceids" value="1" />1</li>
		 
		　　　　<li><input type="checkbox" name="resourceids" value="2" />2</li>
		 
		 　　　　<li><input type="checkbox" name="resourceids" value="3" />3</li>
		
		 　　　　<li><input type="checkbox" name="resourceids" value="4" />4</li>
		 
		 　　　　<li><input type="checkbox" name="resourceids" value="5" />5</li>
		 
		 　　　　<li><a href="javascript:document.getElementById('checkForm').submit();">提交</a></li>
		 
		 　　</ul>
	   </form> -->
	   
<div id="query"></div>
<div id="tools"></div>
<div id="table">
	<table>
		<tr>
			<td>
			角色id
			</td>
			<td>
			权限id
			</td>
			<td>
			操作时间
			</td>
			<td>
			操作用户id
			</td>
			<td>
			操作
			</td>
		</tr>
 		<s:iterator value="page.data">
			<tr>
				<td>${roleId}</td>
				<td>${authId}</td>
				<td>${operTime}</td>
				<td>${operUserId}</td>
				<td><a href="ggkz-role-auth!update.action?authId=${authId}&roleId=${roleId}">修改</a>/<a href="ggkz-role-auth!delete.action?authId=${authId}&roleId=${roleId}" onclick="return deleteConfirm()">删除</a></td>
			</tr>
		</s:iterator>  
		记录个数 :${page.pageSize}
		总页数 :${page.pageCount}
		 页面上显示的页码个数:${page.pageNumShown}
	</table>
	<script type="text/javascript">
        function deleteConfirm(){
	   		if(confirm("是否删除本条数据?"))
				return true;
	   	 	else return false;
    	}
    </script>  
</div>
<div id="page"></div>
	</body>
	
</html>
