<%@ page language="java" contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
	<head>
	</head>
	<body>
		<span id="showDiv">
		</span>

		<input type="button"  value="显示所有文件" onclick="showFiles('<s:property value="%{sessionUser.userId}"/>')"/>
		<script type="text/javascript">
		function showFiles(id){
	    	  var htmlobj =$.ajax({url:"sys/file-download!viewFile.action?userId="+id,async:false});
	    	  var htmlobj1 = htmlobj.responseText;
	    	  var dataObj=eval("("+htmlobj1+")");
	    	  	var HTMLTable="<table align='center' width='50%' border='1'><tr><td>id</td><td>name</td><td>path</td></tr>";
	    	  	$.each(dataObj.data,function(idx,item){ 
	    	  		/* if(idx==0){ 
	    	  		return true;//同countinue，返回false同break 
	    	  		} */  
	    	  		HTMLTable+="<tr id="+item.attachId+"><td>"+item.attachId+"</td>";
	    	  		HTMLTable+="<td>"+item.attachName+"</td>";
	    	  		HTMLTable+="<td><a href='sys/file-download!downloadFile.action";
	    	  		HTMLTable+="?sysAttach.attachName="+item.attachName;
	    	  		HTMLTable+="&sysAttach.attachPath="+item.attachPath+"\\"+item.attachId;
	    	  		HTMLTable+="'>下载</a>";
	    	  		HTMLTable+="<a href='javascript:void(0)' onclick='deleteFile(\"";
	    	  		HTMLTable+=item.attachId+"\")'>|删除</a>"; 
	    	  		/* HTMLTable+="|<a href='sys/file-download!deleteFile.action";
	    	  		HTMLTable+="?sysAttach.attachId="+item.attachId;
	    	  		HTMLTable+="&sysAttach.attachPath="+item.attachPath+"\\"+item.attachId;
	    	  		HTMLTable+="&sysAttachAuth.attachId="+item.attachId;
	    	  		HTMLTable+="'>删除</a>"; */
	    	  		HTMLTable+="</td></tr>";
	    	  	}); 
	    	  	HTMLTable+="</table>";
	    	  	$("#showDiv").html(HTMLTable);    
	    };
	    //删除提示
	    function deleteConfirm(){
	   		if(confirm("是否删除本条数据?"))
				return true;
	   	 	else return false;
    	}
	    //删除并隐藏
	    function deleteFile(fileId){
        	 /*删除  */
 		    	if( deleteConfirm()){//确认提示
 		    	  	htmlobj=$.ajax({url:"sys/file-download!deleteFile.action?sysAttach.attachId="+fileId+"&sysAttachAuth.attachId="+fileId,async:false});
 		    	  	var t=eval(htmlobj.responseText);
 		    	  	if(t==0){
	 		    		$("#"+fileId).hide();
	 		    	 }else{
	 		    	 alert("系统内部错误！");
	 		    	 }
 		    	  }};
		</script>
	</body>
</html>