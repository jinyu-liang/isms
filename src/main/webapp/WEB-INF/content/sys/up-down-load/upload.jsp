<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <%-- <script type="text/javascript" src="<%=basePath%>js/swfupload/js/uploadSetting.js"></script> --%>
  </head>
  
  <body>
	<div id="content">
		<form  method="post" name="thisform" enctype="multipart/form-data">
			<div>
				<div class="fieldset flash" id="fsUploadProgress">
					<span class="legend">File Upload Site</span> 
				</div>
				<div>
					<span id="spanButtonPlaceholder"></span>
					<input id="btnCancel1" type="button" value="Cancel Uploads" onclick="cancelQueue(uploadSetting);" disabled="disabled" style="margin-left: 2px; height: 22px; font-size: 8pt;" />
					<br/>
				</div>
			</div>
		</form>
	</div>
    <link   type="text/css"  href="<%=basePath%>js/swfupload/css/default.css" rel="stylesheet"  />
   	<script type="text/javascript" src="<%=basePath%>js/swfupload/js/swfupload.js"></script>
   	<script type="text/javascript" src="<%=basePath%>js/swfupload/js/swfupload.queue.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/swfupload/js/fileprogress.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/swfupload/js/handlers.js"></script>
    <script type="text/javascript" src="<%=basePath%>js/swfupload/js/uploadSetting.js"></script>
  </body>
</html>
