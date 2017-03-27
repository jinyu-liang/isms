<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>添加施工队</title>
		
	</head>
	<body>
			<s:property value="message" escapeHtml="false"/>
			
			<div class="page">
					<div class="pageContent">
							<form name="tt"  method="post" action="rst/excel-import!importTemplate.action"   enctype="multipart/form-data" id="tt" callback="closeAndRefresh(this)" cssClass="pageForm"  target="hidden_frame">
									<div class="pageFormContent col1" layoutH="56">
									<s:hidden name="entity.wsCd" value="%{entity.wsCd}"></s:hidden>
									<s:hidden name="entity.teamCd" value="%{entity.teamCd}"></s:hidden>
									<s:hidden name="entity.teamNm" ></s:hidden>
									<s:hidden name="bankid" value="1111"></s:hidden>
									<s:hidden name="bankname" ></s:hidden>
					<fieldset class="fieldset_style">
						<legend>
							导入当前施工队人员信息
						</legend>
						<table id="tab" class="tableborder">
							<tr>
								<td width="20%">
								</td>
								<td>
									<a href="/ISMS/js/jquery/importDemo.xls" target="_blank">
										<font color="red">点击下载导入模板</font> 
									</a>
								</td>
							</tr>
							<tr>
								<td width="20%" align="right">
									EXCEL文件：
								</td>
								<td >
									 <s:file id="doc" name="doc" label="Upload File111"  value="123"/>
								</td>
							</tr>
						</table>
					</fieldset><span id="returnmsg"></span>
									</div>				
								  <div class="formBar">
											<ul>
												<li>
													<input type="button" name="button" id="button" value="提交"  onclick="submitCheck();" />
												</li>
												<li>
													<div class="buttonActive">
														<div class="buttonContent">
															<button type="button" onclick="$.pdialog.closeCurrent();">关闭</button>
														</div>
													</div>
												</li>
											</ul>
									</div>
									
							</form>
							<iframe name="hidden_frame" id="hidden_frame" style="display:none"></iframe>		
					</div>
					
			</div>	
			
				<script type="text/javascript">
					function submitCheck(){
				//验证
				var files = $("#doc");
			    var fname;
			 	fname = $(files).attr("value");

			 	if(fname.length == '0'){
			 		alert("请选择你要导入的文件!");
			 		return false;
			 	}
			 	var index = fname.lastIndexOf(".");
			 	var ext = fname.substring(index + 1, fname.length);
				if(ext != "xls") {
					alert("上传的文件格式不正确，请选择97-2003Excel文件(*.xls)！");
			 		return false;
				} 
			 	
			 	if(!fname.match(/\.xls/)){
			 		alert("只能导入EXCEL格式的文件!");
			 		return false;
			 	}
			 	
			 	var ee = $("#tt");
				ee.submit();
				
			}
			function callback(returnmsg){
				var str=returnmsg.replaceAll(";;","<br/>");  
    			document.getElementById("returnmsg").innerHTML = str;   
			}
			
		</script>
	</body>
</html>
