<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>人员工资统计</title>
<SCRIPT type="text/javascript">
function createduban () {
	var json = JSON.stringify(nodes);
	
}


function setKW(){
 var count=0;
	localStorage.setItem("BaseSummary" , "催办1111");
	localStorage.setItem("Detail","EL_TTM_TTH111");
	window.open("http://127.0.0.1:8778/eoms/ultrabpp/view.action?mode=NEW&baseSchema=PRESS_URGE");
	return false;
}


</script>
</head>
<body>
	<s:property value="message" escapeHtml="false" />
	<s:form 
		cssStyle="height:100%;width:100%;"
		onsubmit="return navTabSearch(this);" id="%{_}">
		<div class="page" style="height: 100%; width: 100%;" method="post">
		    <s:hidden name="entity.name" id="entity_name" />
			<div class="layout">
				<div region="north" height="95">
					<div class="pageHeader">
					
					</div>								
					</div>
						<div region="center">
							<table class="table" id="t" name="t" >
								<thead>
									<tr>
										<th>工地名称</th>
										<th>总工作时长</th>
										<th>总薪资</th>
										<th>月份</th>
										<th>状态</th>
									</tr>
								</thead>
								<tbody>
									<s:iterator value="page.data">
										<tr key="entity.pid" value='<s:property value="pid" />'>
											<td><s:property value="work_ws_nm" />
											</td>
											<td><s:property value="work_timer" />
											</td>
											<td>
											<s:property value="work_pay" />
											</td>
											<td><s:date name="addtime" format="yyyy-MM" />
											</td>
											<td><s:property value="work_status" />
											<s:if test="work_status==1">录入</s:if>
											<s:if test="work_status==2">确认完成</s:if>
											<s:if test="work_status==4">发放完成</s:if>
											</td>
										</tr>
									</s:iterator>
								</tbody>
							</table>
		
						</div>
						<div region="south" height="26">
						<div class="formBar">
							<ul>
								<li>
									<div class="buttonActive">
										<div class="buttonContent">
											<button type="button" id="subform" onclick="setKW()">确定</button>
										</div>
									</div>
								</li>
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
					
				<div region="south" height="36">
					<div class="panelBar">
						<div class="pages">
							<span>显示</span>
							<s:select list="page.pageSizeList" name="queryEntity.pageSize"
								cssClass="pageSize" form="${_}"/>
							<span>条，共[<s:property value="page.count" />]条</span>
						</div>
						<s:hidden name="queryEntity.pageNumber" id="pageNumber" />
						<s:hidden name="_"></s:hidden>
						<div class="pagination"
							totalCount='<s:property value="page.count"/>'
							numPerPage='<s:property value="page.pageSize"/>'
							pageNumShown='<s:property value="page.pageNumShown"/>'
							currentPage='<s:property value="page.pageNumber"/>'></div>
					</div>
				</div></div></div>
	</s:form>
</body>
</html>
