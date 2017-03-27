<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%

    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path ;
%>
<s:property value="message" escapeHtml="false" />
<div class="layout">
	<div region="north" height="50">
		<div class="pageHeader">
			<div class="searchBar">
				<ul class="searchContent">
					<span>历史版本</span>
				</ul>
			</div>
		</div>
	</div>
	<div class="gridScroller" style="width: 525px; height: 430px;"
		height="430px">
		<div class="gridTbody">
			<table class="list">
				<thead>
					<tr>
						<th>序号</th>
						<th>操作</th>
						<th>版本号</th>
						<th>投产日期</th>
						<th>开发完成日期</th>
						<th>状态</th>
						<th width="30%">备注</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="page.data">
						<tr key="entity.verId" value='<s:property value="verId" />'>
							<td><s:property value="verId" />
							</td>
							<td><s:if test="statusCd==0">
									<s:a namespace="/rst" action="rst-ver-info"
										method="verStartStop" target="ajaxTodo"
										callback="function refreshdivrel(json){versionctrl(json,'${_}history','${_}historylist');}">
										<s:param name="entity.verCode" value="%{verCode}"></s:param>
										<s:param name="entity.statusCd" value="'1'"></s:param>
										<span> <font color="green">启用</font> </span>
									</s:a>
								</s:if> <s:if test="statusCd==1">
									<s:a namespace="/rst" action="rst-ver-info"
										method="verStartStop" target="ajaxTodo"
										callback="function refreshdivrel(json){versionctrl(json,'${_}history','${_}historylist');}">
										<s:param name="entity.verCode" value="%{verCode}"></s:param>
										<s:param name="entity.statusCd" value="'0'"></s:param>
										<span> <font color="red">停用</font> </span>
									</s:a>
                                       <%--  <s:a href="<%=basePath%>/<s:property value='fileName'/>"><font style=" border: 1px solid #B2B3A8;">下载</font></s:a> --%>
								</s:if></td>
							<td><s:property value="verCode" />
							</td>
							<td><s:date name="prodDate" format="yyyy-MM-dd" />
							</td>
							<td><s:date name="finishDate" format="yyyy-MM-dd" />
							</td>
							<td><s:if test="statusCd==1">
									<font color="green">使用中</font>
								</s:if> <s:else>
									<font color="red">未启用</font>
								</s:else>
							</td>
							<td><s:property value="memo" />
							</td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
		</div>
	</div>
</div>
