<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pt" uri="/is-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>整改详情页面</title>
</head>
<body>
	<div class="page">
		<s:property value="message" escapeHtml="false" />
		<div class="pageContent">
			<s:form method="post" action="DQuality!toEdit" cssClass="pageForm"
				onsubmit="return navTabSearch(this);">
				<div style="overflow-y:scroll">
					<table class="list" width="100%" style="table-layout:fixed;word-break:break-all">
						<tr>
							<th colspan="4" align="center"><font color="#3A5FCD">整改报告详情</font></th>

						</tr>
						<tr>
							<td><label class="abc">项目名称：</label></td>
							<td><span> <s:property value="entity.ws_nm" /></br> </span>
							</td>
							<td><label class="abc">项目代码：</label></td>
							<td><span> <s:property value="entity.ws_cd" /></br> </span>
							</td>
							
						</tr>
						<tr>
							<td><label class="abc">状态：</label></td>
							<td><span> <s:if test="entity.fqstatus==\"1\"">
										<font style="color: #6495ED;">录入</font>
									</s:if> <s:if test="entity.statusCd==\"2\"">
										<font style="color: green;">处理中</font>
									</s:if> <s:if test="entity.statusCd==\"3\"">
										<font style="color: green;">甲方确认完成</font>
									</s:if> <s:if test="entity.statusCd==\"4\"">
										<font style="color: green;">监理确认完成</font>
									</s:if> </span>
							</td>
						</tr>

						
						<tr>
							<th colspan="4" align="center"><font color="#3A5FCD">发起方情况</font></th>
						</tr>
							<tr>
								<td><label class="abc">发起人ID：</label></td>
								<td><span> <s:property value="entity.fquserid" /></br> </span>
								<td><label class="abc">发起人名字：</label></td>
								<td><span> <s:property value="entity.fqusername" /></br> </span>
							</tr>
							<tr>
								<td><label class="abc">发起时间：</label></td>
								<td><span> <s:date name="entity.fqaddtime" /></br></span></td>
								<td><label class="abc">发起人要求完成时间：</label></td>
								<td><span> <s:date name="entity.fqyqfinishtime" /></br></span></td>
							</tr>
							<tr>
								<td><label class="abc">发起描述：</label></td>
								<td colspan="3"><span> <s:property value="entity.fqdesc" /></br> </span>
								</td>
							</tr>
						<tr>
							<th colspan="4" align="center"><font color="#3A5FCD">整改方情况</font></th>
						</tr>
						
							<tr>
								<td><label class="abc">整改处理人ID：</label></td>
								<td><span> <s:property value="entity.zguserid" /></br> </span>
								<td><label class="abc">整改处理人：</label></td>
								<td><span> <s:property value="entity.zgusername" /></br> </span>
							</tr>
							<tr>
								<td><label class="abc">整改时间：</label></td>
								<td><span> <s:date name="entity.zgaddtime" /></br></span></td>
								<td><label class="abc">整改计划完成时间：</label></td>
								<td><span> <s:date name="entity.zgjhfinishtime" /></br></span></td>
							</tr>
							<tr>
								<td><label class="abc">整改部门确认完成时间：</label></td>
								<td><span> <s:date name="entity.zgfinishtime" /></br></span></td>
								<td><label class="abc">整改是否超过计划完成时间：</label></td>
								<td><span> <s:property value="entity.isovertime" /></br></span></td>
							</tr>
							<tr>
								<td><label class="abc">监理确认完成时间：</label></td>
								<td><span> <s:date name="entity.jlqrfinishtime" /></br></span></td>
							</tr>
							<tr>
								<td><label class="abc">整改描述：</label></td>
								<td colspan="3"><span> <s:property value="entity.zgdesc" /></br> </span>
								
							</tr>
					</table>

					<div class="formBar">
						<ul>
							<li>
								<div class="button">
									<div class="buttonContent">
										<button type="button" onclick="$.pdialog.closeCurrent();">关闭</button>
									</div>
								</div>
							</li>
						</ul>
					</div>

				</div>
			</s:form>
		</div>
	</div>

</body>
</html>
