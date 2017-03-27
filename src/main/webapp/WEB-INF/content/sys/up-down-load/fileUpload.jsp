<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="java.util.Date"%>
<%
	
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String dateStr = new Date().getTime()+"";
	String modelCodeForAttach = request.getParameter("modelCodeForAttach");
	String fileIds = request.getParameter("fileIds");
	String upFileTable = request.getParameter("upFileTable");
	
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<body bgcolor="white" style="margin: 0;padding: 0;">
    <%-- 引入jquery 相关js文件--%>
    <s:url var="_url" value="/js/jquery-1.4.4.min.js"></s:url>
    <script src="${_url}" type="text/javascript"></script>
	<%-- 引入插件 SWFUpload--%>
	<s:url var="_url" value="/plugins/swfupload/fileprogress.js"></s:url>
	<script src="${_url}" type="text/javascript"></script>
	<s:url var="_url" value="/plugins/swfupload/handlers.js"></s:url>
	<script src="${_url}" type="text/javascript"></script>
	<s:url var="_url" value="/plugins/swfupload/swfupload.js"></s:url>
	<script src="${_url}" type="text/javascript"></script>
	<s:url var="_url" value="/plugins/swfupload/swfupload.queue.js"></s:url>
	<script src="${_url}" type="text/javascript"></script>
	<s:url var="_url" value="/plugins/swfupload/css/default.css"></s:url>
	<link rel="stylesheet" type="text/css" href="${_url}"/>
	
		<style type="text/css">
			.fileUploadArea table{
				width:500px;
				font:Georgia 11px;
				font-size:12px;
				color:#333333;
				text-align:left;
				background: #E0E8F5;
				margin-bottom: 5px;
			}
			.fileUploadArea table tr{margin: 5px;margin-bottom: 0px;text-align:left;}
		</style>
<script language="JavaScript">
	var fileIds = "<%=fileIds %>";
	var dateStr = "<%=dateStr %>";
	var upFileTable = "<%=upFileTable %>";
	var trShuaXinSwf = "trShuaXinSwf"+dateStr;
	var swfupload;
	
	//清除swfupload的对象
	function desplaySwfUploadByUpFile(){
		try{
			if(swfupload != null){
				swfupload.destroy();
			}
		}catch(e){}
	}
	
	//SWFUpload控件上传文件成功后，将上传成功的附件ID存入调用页面的fileIds控件中
	function loadFileValue(str){
		try{
			$(window.parent.document).find("#"+fileIds).attr("value",$(window.parent.document).find("#"+fileIds).attr("value")+str+";");
		}catch(e){
		}
	}
	
	//为table添加内容
	function upTableAppend(str){
		try{
			$(window.parent.document).find("#"+upFileTable).append(str);
		}catch(e){}
	}
	
	//根据传入的Id移除元素
	function upTrRemove(id){
		try{
			$(window.parent.document).find("#"+id).remove();
		}catch(e){}
	}
	
	//加载SWF上传控件  SWFUpload
  	$(function(){
  		//文件是上传成功后，调用方法，进行业务处理
  		var swfUploadSuccessEventHandler = function (file, data) {
  			try{
	  			data = eval("("+data+")");
	  			
				var fileSize = (file.size/1024).toFixed(2);
	  			
	  			loadFileValue(data.attachSysName);
	  			
	 			//向table中添加包含删除按钮的tr
	 			var tr_id = $("#"+upFileTable+">tbody>tr:last").attr("id");
	 			if(tr_id == "" || tr_id == undefined || tr_id == NaN){
	 				tr_id = new Date().getTime();
	 			}else{
	 				tr_id = new Date().getTime();
	 			}
	 			
	 			//添加图标
	 			var imgurl = "<img src='<%=basePath %>/image/ico/u149.png' />";
	 			
	 			//添加删除按钮
	 			var upStr = "<a href=\"javascript:deleteUpFile('"
	 					+data.filePath+"/"+data.attachSysName+"','"+tr_id+"','"+data.attachSysName+"','"+fileIds+"');\">删除</a>";
	 					
	 			var str = "<tr id = '"+ tr_id+ "'>"
	 				+"<td style='line-height:15px;'>"+imgurl+file.name+"&nbsp;&nbsp;("
	 				+fileSize+"kb)&nbsp;&nbsp;"
	 				+upStr+"</td></tr>";
	 			
	 			upTrRemove(trShuaXinSwf);
	 			upTableAppend(str);
  			}catch(e){
  			}
 		};
 		
 		//上传控件开始上传前，调用的方法
 		var swfUploadStartHander = function(file) {
 			try {
 				/* if(file.size == 0){
 					parent.alertMsg.warn("文件大小为0，不能上传！");
 	 				return true;
 	 			}
 				if(file.size>10485760){
 					parent.alertMsg.warn("文件大小超出10M的限制！");
 	 				return true;
 	 			} */
 				//添加图标
 	 			var imgurl = "<img src='<%=basePath %>/image/ico/u152.png' />";
 	 			
 	 			//添加上传加载动画
 	 			var upurl = "<img src='<%=basePath %>/plugins/swfupload/images/20080320132838323.gif' />";
 				
 				var str = "<tr id = '"+trShuaXinSwf+"'><td width='100%'>"+imgurl+file.name+upurl+"<div id='divSXBySwf' class='progressName'></div></td></tr>";
 				upTableAppend(str);
 				
 		  		swfupload.addPostParam("busiId","${busiId}");
 		  		swfupload.addPostParam("modelCodeForAttach","<%=modelCodeForAttach %>");
 				//var progress = new FileProgress(file, "divSXBySwf");
 				//progress.toggleCancel(true, this);
 			}
 			catch (ex) {
 			}
 			
 			return true;
 		};
 		
 		//实现上传期间的进度条功能
 		var swfUploadProgressHander = function(file, bytesLoaded, bytesTotal) {
			
			try {
				var percent = Math.ceil((bytesLoaded / bytesTotal) * 100);
				var progress = new FileProgress(file, "divSXBySwf");
				progress.setProgress(percent);
			} catch (ex) {
			}
		};
		
 		var basePath = "<%=basePath %>";
 		var flshurl = basePath+"plugins/swfupload/swfupload.swf";
		var butImgUrl = basePath+"plugins/swfupload/images/XPButtonUploadText_61x22.png";
		var buttGtId = "spanButtonPlaceholder1"+dateStr;
		var buttonQuXiaoId = "btnCancel1"+dateStr;
		
		swfupload = new SWFUpload({
  			
 			// Backend Settings
 			upload_url: "<%=basePath %>/sys/sys-attach!uploadFiles.action",
 			// post_params: {"picSESSID" : "songhao"},
 			file_post_name: "file",
 			// File Upload Settings
 			file_size_limit : "10240",	// 10MB
 			file_types : "*.*",
 			file_types_description : "All Files",
 			file_upload_limit : "10",
 			file_queue_limit : "0",

 			// Event Handler Settings (all my handlers are in the Handler.js file)
 			file_dialog_start_handler : fileDialogStart,
 			file_queued_handler : fileQueued,
 			file_queue_error_handler : fileQueueError,
 			file_dialog_complete_handler : fileDialogComplete,
 			upload_start_handler : swfUploadStartHander,//???????????
 			upload_progress_handler : swfUploadProgressHander,//???????????
 			upload_error_handler : uploadError,
 			upload_success_handler : swfUploadSuccessEventHandler,//???????????
 			upload_complete_handler : uploadComplete,

 			// Button Settings
 			button_image_url : butImgUrl,
 			button_placeholder_id : buttGtId,
 			button_width: 90,
 			button_height: 22,
 			
 			// Flash Settings
 			flash_url : flshurl,
 			

 			custom_settings : {
 				cancelButtonId : buttonQuXiaoId
 			},
 			// Debug Settings
 			debug: false
 		});
	});
	
	//删除上传成功的文件
  	function deleteUpFile(upurl,trId,fileId){
		try{
			upTrRemove(trId);
	  		var fileValue = $(window.parent.document).find("#"+fileIds).attr("value");
	  		$(window.parent.document).find("#"+fileIds).attr("value",fileValue.replace(fileId+";",""));
			$.post("<%=basePath %>/sys/sys-attach!deleteUpFile.action?upurl="+upurl,function(data){
				parent.alertMsg.correct(data);
			});
		}catch(e){
		}
	}
  	
</script>
		<div>
					<span id="spanButtonPlaceholder1<%=dateStr %>"></span>
					<input id="btnCancel1<%=dateStr %>" type="hidden" value="关闭上传" onclick="cancelQueue(swfupload);" disabled="disabled" style="margin-left: 2px; height: 22px; font-size: 8pt;" />
		</div>
	</body>
</html>