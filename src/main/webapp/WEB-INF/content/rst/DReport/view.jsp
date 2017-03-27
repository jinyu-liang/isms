<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pt" uri="/is-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>采购详情页面</title>
</head>
<body>
	<div class="page">
		<s:property value="message" escapeHtml="false" />
		<div class="pageContent">
			<s:form method="post" action="dReport!toEdit" cssClass="pageForm"
				onsubmit="return navTabSearch(this);">
				<div>



					<table class="list" width="100%">
						<tr>
							<th colspan="4" align="center"><font color="#3A5FCD">采购报告详情</font></th>

						</tr>
						<tr>
							<td><label class="abc">采购项目：</label></td>
							<td><span> <s:property value="entity.title" /></br> </span>
							</td>
							<td><label class="abc">采购Id：</label></td>
							<td><span> <s:property value="entity.reportId" /></br> </span>
							</td>
							
						</tr>
						<tr>
							<td><label class="abc">状态：</label></td>
							<td><span> <s:if test="entity.statusCd==\"0\"">
										<font style="color: #6495ED;">未处理</font>
									</s:if> <s:if test="entity.statusCd==\"1\"">
										<font style="color: green;">已审批</font>
									</s:if> <s:if test="entity.statusCd==\"2\"">
										<font style="color: green;">发票待处理</font>
									</s:if> <s:if test="entity.statusCd==\"3\"">
										<font style="color: green;">发票已处理</font>
									</s:if> </span>
							</td>
							<td><label class="abc">项目代码：</label></td>
							<td><span> <s:property value="entity.projectCode" /></br>



							</span>
							</td>
						</tr>
						<tr>
							<td><label align="left" class="abc">项目名称：</label></td>
							<td><span> <s:property value="entity.projName" /></br> </span>
							</td>
							<td><label class="abc">单价：</label></td>
							<td><span> <s:property value="entity.unitPrice" /></br> </span>
							</td>
						</tr>
						<tr>
							<td><label class="abc">金额：</label></td>
							<td><span> <s:property value="entity.amount" /></br> </span>
							</td>
							<td><label class="abc">数量：</label></td>
							<td><span> <s:property value="entity.number" /></br> </span>
							</td>
						</tr>
						<tr>
							<td><label class="abc">报告人：</label></td>
							<td><span> <pt:usernameShow
										userId="entity.reportUserCd"></pt:usernameShow> </span>
							</td>
							<td><label class="abc">报告时间：</label></td>
							<td><span> <s:date name="entity.reportTm" /></br> </span>
							</td>
						</tr>
						<tr>
							<td><label class="abc">备注：</label></td>
							<td colspan="3"><span> <s:property value="entity.memo" /></br> </span>
							</td>
						</tr>
						
						<tr>
							<th colspan="4" align="center"><font color="#3A5FCD">审批情况</font></th>

						</tr>
						<tr>
							<td><label class="abc">审批人：</label></td>
							<td><span> <pt:usernameShow
										userId="entity.verifiedUserCd"></pt:usernameShow> </span>
							</td>

							<td><label class="abc">审批结果：</label></td>
							<td><span> 
							<s:if test="entity.verifiedHeadStatus==\"0\"">
										待审批
									</s:if>
							<s:if test="entity.verifiedHeadStatus==\"No\"">
										<font style="color: red;">有问题</font>
									</s:if> <s:if test="entity.verifiedHeadStatus==\"Yes\"">
										<font style="color: green;">同意</font>
									</s:if> </span>
							</td>

						</tr>
						<tr>
							<td ><label class="abc">审批意见：</label></td>
							<td colspan="3"><span> <s:property
										value="entity.verifiedHeadMemo" /></br> </span>
							</td>



						</tr>
						<tr>
							<td><label class="abc">审批时间：</label></td>
							<td><span> <s:date name="entity.verifiedHeadTm" /></br> </span>
							</td>
						</tr>
						<tr>
							<th colspan="4" align="center"><font color="#3A5FCD">发票处理情况</font></th>

						</tr>
						<tr>
							<td><label class="abc">处理人：</label></td>
							<td><span> <pt:usernameShow
										userId="entity.processUserCd"></pt:usernameShow> </span>
							</td>
							<td><label class="abc">处理结果：</label></td>
							<td><span> 

									<s:if test="entity.dealResult==\"Yes\"">
										<font style="color: green;">同意</font>
									</s:if> <s:if test="entity.dealResult==\"No\"">
										<font style="color: red;">有问题</font>
									</s:if> </span>
							</td>
						</tr>
						<tr>
							<td><label class="abc">处理意见：</label></td>
							<td colspan="3"><span> <s:property
										value="entity.comment" /></br> </span>
							</td>
						</tr>
						<tr>
							<td><label class="abc">处理时间：</label></td>
							<td><span> <s:date name="entity.processTm" /></br> </span>
							</td>
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
