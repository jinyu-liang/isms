<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pt" uri="/is-tags" %>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>查看工作日志</title>
	</head>
	<body>
		<div class="page">
			<s:property value="message" escapeHtml="false"/>
			<div class="pageContent">
				<s:form method="post" action="rst-user-info!toEdit.action" cssClass="pageForm" onsubmit="return navTabSearch(this);">
					<div class="pageFormContent col1" layoutH="56">
					<table class="list" width="100%" style="border-collapse:separate; border-spacing:0px 1px;">
						<tr>
							<th colspan="4" align="center" >工作日志信息</th>
						</tr>
						<tr>
							<td><label   style="letter-spacing: 6px;">登录名</label></td>
							<td><span>
								<s:property value="entity.fqusername"/>
							</span></td>
							
								<td><label  style="letter-spacing:1em;">工号</label></td>
							<td><span>
							<s:property value="entity.fquserid"/>
							</span></td>
						</tr>
						<tr>
							<td><label   style="letter-spacing: 6px;">开始时间</label></td>
							<td>
								<span>
								    <s:date name="entity.starttime" format="yyyy-MM-dd hh:mm:ss" />
								</span>
							</td>
							<td><label  style="letter-spacing:1em;">结束时间</label></td>
							<td>
								<span>
								    <s:date name="entity.endtime" format="yyyy-MM-dd hh:mm:ss" />
								</span>
							</td>
						</tr>
						 <tr>
						  	<td><label   style="letter-spacing: 6px;">部门名称</label></td>
							<td><span>
							<pt:dictshow name="entity.ws_name" name="entity.ws_name" listValue="dictName" listKey="dictName" list="userDepartList"></pt:dictshow>
							
							</span></td>
							
							<td><label  style="letter-spacing:1em;">天气</label></td>
							<td><span>
							
								<s:select list="#{'':'请选择状态','晴':'晴','阴':'阴','多云':'多云','雨':'雨' }" name="entity.wheather"
									cssClass="required" id="entity_dwheather">
								</s:select>
							</span></td>
						</tr>
						 <tr>
						<tr>
							<td><label   style="letter-spacing: 6px;">日志内容</label></td>
							<td colspan="3"><span>
							<s:property value="entity.jobcontent"/>
							</span></td>
						</tr>
						<tr>
							<td><label   style="letter-spacing: 6px;">当日工程形象进度</label></td>
							<td><span>
							<s:property value="entity.schedule"/>
							</span></td>
							
								<td><label  style="letter-spacing:1em;">与计划对比</label></td>
							<td><span>
							<s:property value="entity.Isplan"/>
							</span></td>
						</tr>
							
						<tr>
							<td><label>人、材、机组织情况（白天）</label></td>
							<td colspan="3"><span>
							<s:property value="entity.daySituation"/>
 							</span></td>
						</tr>
						<tr>
							<td><label>人、材、机组织情况（晚上）</label></td>
							<td colspan="3"><span>
							<s:property value="entity.nightSituation"/>
 							</span></td>
						</tr>
						<tr>
							<td><label>质量情况或问题</label></td>
							<td colspan="3"><span>
							<s:property value="entity.qualitySituation"/>
 							</span></td>
						</tr>
						<tr>
							<td><label>质量情况或问题处理措施</label></td>
							<td colspan="3"><span>
							<s:property value="entity.qualityMeasures"/>
 							</span></td>
						</tr>
						<tr>
							<td><label>安全、文明施工情况或问题</label></td>
							<td colspan="3"><span>
							<s:property value="entity.SecuritySituation"/>
 							</span></td>
						</tr>
						<tr>
							<td><label>安全、文明施工情况或问题处理措施</label></td>
							<td colspan="3"><span>
							<s:property value="entity.SecurityMeasures"/>
 							</span></td>
						</tr>
						<tr>
							<td><label>进场材料及出现的问题</label></td>
							<td colspan="3"><span>
							<s:property value="entity.materialSituation"/>
 							</span></td>
						</tr>
						<tr>
							<td><label>进场材料及出现的问题处理措施</label></td>
							<td colspan="3"><span>
							<s:property value="entity.materialMeasures"/>
 							</span></td>
						</tr>
						<tr>
							<td><label>设计图纸中出现的问题</label></td>
							<td colspan="3"><span>
							<s:property value="entity.DrawingSituation"/>
 							</span></td>
						</tr>
						<tr>
							<td><label>设计图纸中出现的问题处理措施</label></td>
							<td colspan="3"><span>
							<s:property value="entity.DrawingMeasures"/>
 							</span></td>
						</tr>
						<tr>
							<td><label>产生变更与签证问题</label></td>
							<td colspan="3"><span>
							<s:property value="entity.VisaSituation"/>
 							</span></td>
						</tr>
						<tr>
							<td><label>产生变更与签证问题原因分析</label></td>
							<td colspan="3"><span>
							<s:property value="entity.VisaMeasures"/>
 							</span></td>
						</tr>
						<tr>
							<td><label>其他</label></td>
							<td colspan="3"><span>
							<s:property value="entity.OtherSituation"/>
 							</span></td>
						</tr>
						<tr>
							<td><label>问题处理情况跟踪</label></td>
							<td colspan="3"><span>
							<s:property value="entity.problemTrack"/>
 							</span></td>
						</tr>
						<tr>
							<td><label>问题处理情况状态</label></td>
							<td colspan="3"><span class="labelnarrow">
								<s:radio id="entity_status" name="entity.status" cssClass="required"
									list="#{'1':'问题处理中','2':'处理完成'}" value="entity.status"></s:radio>
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
