<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pt" uri="/is-tags" %>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>查看工作联系单</title>
	</head>
	<body>
		<div class="page">
			<s:property value="message" escapeHtml="false"/>
			<div class="pageContent">
				<s:form method="post" action="m-workshop-contact!toEdit.action" cssClass="pageForm" onsubmit="return navTabSearch(this);">
					<div class="pageFormContent col1" layoutH="56">
					<table class="list" width="100%" style="border-collapse:separate; border-spacing:0px 1px;">
						<tr>
							<th colspan="4" align="center" >工作联系单信息</th>
						</tr>
						<tr>
							<td><label   >创建姓名</label></td>
							<td><span>
								<s:property value="entity.fqusername"/>
							</span></td>
							
								<td><label  >工号</label></td>
							<td><span>
							<s:property value="entity.fquserid"/>
							</span></td>
						</tr>
						<tr>
							<td><label   >项目ID</label></td>
							<td><span>
								<s:property value="entity.ws_cd"/>
							</span></td>
							
								<td><label  >项目名称</label></td>
							<td><span>
							<s:property value="entity.ws_nm"/>
							</span></td>
						</tr>
						<tr>
							<td><label   >标题</label></td>
							<td><span>
								<s:property value="entity.title"/>
							</span></td>
							<td><label   >联系单发起时间</label></td>
							<td><span>
								<s:date name="entity.addtime" format="yyyy-MM-dd hh:mm:ss" />
							</span></td>
						</tr>
						<tr>	
							<td><label  >联系单内容</label></td>
							<td colspan="3"><span>
							<s:property value="entity.detail"/>
							</span></td>
						</tr>
						
						<tr>
							<td><label   >部门领导审核人</label></td>
							<td><span>
								<s:property value="entity.acceptdepmangername"/>
							</span></td>
							<td><label   >部门领导审核时间</label></td>
							<td><span>
								<s:date name="entity.acceptdepmangertime" format="yyyy-MM-dd hh:mm:ss" />
							</span></td>
						</tr>
						<tr>	
							<td><label  >部门领导审核人签注</label></td>
							<td colspan="3"><span>
							<s:property value="entity.acceptdepmangerdesc"/>
							</span></td>
						</tr>
						
						<tr>
							<td><label   >主管领导审核人</label></td>
							<td><span>
								<s:property value="entity.leadername"/>
							</span></td>
							<td><label   >主管领导审核时间</label></td>
							<td><span>
								<s:date name="entity.leadertime" format="yyyy-MM-dd hh:mm:ss" />
							</span></td>
						</tr>
						<tr>	
							<td><label  >主管领导审核人签注</label></td>
							<td colspan="3"><span>
							<s:property value="entity.leaderdesc"/>
							</span></td>
						</tr>
						
						<tr>
							<td><label   >接收部门是否接受</label></td>
							<td><span>
								<s:if test="entity.Isaccept==\"true\"">接收</s:if>
								<s:if test="entity.Isaccept==\"false\"">拒绝</s:if>
							</span></td>
							<td><label   >接收部门操作时间</label></td>
							<td><span>
								<s:date name="entity.accepttime" format="yyyy-MM-dd hh:mm:ss" />
							</span></td>
						</tr>
						<tr>	
							<td><label  >接收部门操作签注</label></td>
							<td colspan="3"><span>
							<s:property value="entity.acceptdesc"/>
							</span></td>
						</tr>
						<tr>	
							<td><label  >工作联系单状态</label></td>
							<td><span>
							<s:if test="entity.status==0">发起</s:if>
							<s:if test="entity.status==1">部门领导审核完毕</s:if>
							<s:if test="entity.status==2">主管领导审核完毕</s:if>
							<s:if test="entity.status==3">接收部门接收</s:if>
							<s:if test="entity.status==4">接收部门驳回</s:if>
							</span></td>
						</tr>
						
						
						</table>
						</div>	
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
				</s:form>
			</div>
		</div>
		
	</body>
</html>
