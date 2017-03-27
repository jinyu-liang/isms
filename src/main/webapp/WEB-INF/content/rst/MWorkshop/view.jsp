<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pt" uri="/is-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>mWorkshop查看</title>
</head>
<body>
	<div class="page">
		<s:property value="message" escapeHtml="false" />
		<div class="pageContent">
			<s:form method="post" action="m-workshop!toEdit" cssClass="pageForm"
				onsubmit="return navTabSearch(this);">
				<div>

					<s:hidden name="entity.wsCd" value="%{entity.wsCd}" />


					<table class="list" width="100%">

						<!-- <font color="#3A5FCD"> -->
						<tr>
							<td><label>工地中心名称</label>
							</td>
							<td><span> <s:property value="entity.wsNm" /></br> </span></td>
					
							<td><label>部门编码</label>
							</td>
							<td><span> <pt:dictshow name="entity.divCd"
										listValue="dictName" listKey="dictCode" list="userDepartList"></pt:dictshow>
									</br> </span></td>
						</tr>
						<tr>
							<td><label>区分编码</label>
							</td>
							<td><span> 
									<s:if test="entity.typeCd==1">内线车间</s:if>
										<s:if test="entity.typeCd==0">管理部门</s:if>
										<s:if test="entity.typeCd==2">外线工地</s:if>
										<s:if test="entity.typeCd==3">仓库</s:if>
								</br> </span></td>
							<td><label>负责人</label>
							</td>
							<td><span><pt:usernameShow
										userId="entity.managerUserId"></pt:usernameShow> </br> </span></td>
								</tr>
						<tr><td><label>开始时间</label>
							</td>
							<td><span> <s:date name="entity.beginTime"
										format="yyyy-MM-dd" /></br> </span></td>
							<td><label>结束时间</label>
							</td>
							<td><span> <s:date name="entity.endTime"
										format="yyyy-MM-dd" /></br> </span></td>
						</tr>
						<div>
							<th colspan="4" align="center"><font color="#3A5FCD">设备明细一览</font></th>
							</div>
							<div>
								<table class="table">
									<thead>
										<tr>
											<th>设备名称</th>
											<th>规格型号</th>
											<th>总数</th>
											<th>剩余量</th>
											<th>备注</th>
										</tr>
									</thead>
									<tbody>
										<s:iterator value="entity.itemList">
											<tr key="entity.itemId" value='<s:property value="itemId" />'>
												<td><s:property value="categoryNm" />
												</td>
												<td><s:property value="modelNo" />
												</td>
												<td><s:property value="totalAmount" />
												</td>
												<td><s:property value="remainNumber" />
												</td>
												<td><s:property value="memo" />
												</td>
												
											</tr>
										</s:iterator>
									</tbody>
								</table>
							</div>
						
					</table>
					<div class="formBar">
						<ul>
							<li>
								<div class="button">
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
</body>
</html>
