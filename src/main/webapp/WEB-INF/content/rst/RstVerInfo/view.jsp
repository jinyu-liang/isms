<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%

    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path ;
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>最新版本</title>
</head>
<body>
	<div class="page">
		<s:property value="message" escapeHtml="false" />
		<div class="pageContent">
			<s:form method="post" action="rstVerInfo!toEdit" cssClass="pageForm"
				onsubmit="return pageSubmit(this);">
				<div>

					<s:hidden name="entity.verId" value="%{entity.verId}" />


					<table class="list" width="100%">
						<tr>
							<td  class="mob"><label><font size="30">版本号:&nbsp&nbsp</font></label>
							<span> <s:property value="entity.verCode" /> </span><br></br>  </td>
						</tr>
						<tr>
							<td class="mob"><label><font size="30"><font size="30">生产日期:</font></label>
							<span> <s:date name="entity.prodDate"
										format="yyyy-MM-dd" /> </span><br></br>  </td>
						</tr>
						<tr>
							<td class="mob"><label><font size="30">竣工日期:</font></label>
							<span> <s:date name="entity.finishDate"
										format="yyyy-MM-dd" /> </span><br></br> </td>
						</tr>
						<tr>
							<td class="mob"><label><font size="30">备注:最新版本</font></label></td>
						</tr>
						<tr>
							<td><span> <s:property value="entity.memo" /></span><br></br></td>
						</tr>
						<tr>
							<td colspan="2" class="mob">
							 <a   href="<%=basePath%>/<s:property value='entity.fileName'/>" ><font size="40">下载最新版本</font><br></br></a> 
							</td>
						</tr>
					</table>
					<div class="formBar">
						<ul>
							<li>                            <div class="button">
                                <div class="buttonContent">
                                    <button type="button" onclick="$.pdialog.closeCurrent();">关闭</button>
                                </div>
                            </div></li>
						</ul>
					</div>

				</div>
			</s:form>
		</div>
	</div>
	<script >
	$(function(){
		$("#getNewVersion").click(
			function(){
			//	alert(123);
				$("#newA").click();
				return false;
			}
		);
	});
	</script>
</body>
</html>
