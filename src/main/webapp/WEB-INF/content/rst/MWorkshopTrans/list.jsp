<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pt" uri="/is-tags"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>工地转移列表</title>
<style type="text/css">
.autocut {
    width:200px;
    overflow:hidden;
    white-space:nowrap;
    text-overflow:ellipsis;
    -o-text-overflow:ellipsis;
    -icab-text-overflow: ellipsis;
    -khtml-text-overflow: ellipsis;
    -moz-text-overflow: ellipsis;
    -webkit-text-overflow: ellipsis;
}

</style>
</head>
<body>
	<s:property value="message" escapeHtml="false" />
	<s:form method="post" action="m-workshop-trans!list"
		cssStyle="height:100%;width:100%;"
		onsubmit="return navTabSearch(this);" id="%{_}">
		<div class="page" style="height: 100%; width: 100%;">
			<div class="layout">
				<div region="north" height="95">
					<div class="pageHeader">
						<div class="searchBar">
							<ul class="searchContent">
								<li><label for="entity_fromWsCd">发货工地中心</label> <s:textfield
										id="queryEntity_fromWsCd" name="queryEntity.fromWsCd"
										maxLength="10" /></li>
								<li><label for="entity_toWsCd">收货工地中心</label> <s:textfield
										id="queryEntity_toWsCd" name="queryEntity.toWsCd"
										maxLength="10" /></li>										
								<li><label for="entity_sender">发货人</label> <s:textfield
										id="queryEntity_sender" name="queryEntity.sender"
										maxLength="20" /></li>
								<li><label for="entity_statusCd">状态</label>
								<s:select list="#{'':'请选择状态','No':'有问题','0':'运输中','Yes':'已收货' }" name="queryEntity.statusCd"
									cssClass="required"></s:select></li>
								<li><label for="entity_tCompanyNm">运输公司名称</label> <s:textfield
										id="queryEntity_tCompanyNm" name="queryEntity.tCompanyNm"
										maxLength="50" /></li>
								<li><label for="entity_driver">司机</label> <s:textfield
										id="queryEntity_driver" name="queryEntity.driver"
										maxLength="20" /></li>
							</ul>
							<div class="subBar">
								<ul>
									<li>
										<div class="buttonActive">
											<div class="buttonContent">
												<s:submit type="button">查询</s:submit>
											</div>
										</div></li>
								</ul>
							</div>
						</div>
					</div>
				</div>


				<div region="center">

					<table class="table"  style="width:1400px; overflow:scroll;">
						<thead>
							<tr>
								<th>操作</th>
								<th>状态</th>
								<th>发货仓库</th>
								<th>起单用户</th>
								<th>发货人</th>
								<th>收货工作中心</th>
								<th>接收人</th>
								<th>运输公司名称</th>
								<th>司机</th>
								<th>重量(吨)</th>
								<th>发货时间</th>
								<th>到货时间</th>
								<th>审批意见</th>
                                <s:if test="sessionUser.userId=='USER0047'">
                                    <th>操作</th>
                                </s:if>
							</tr>
						</thead>
						<tbody>
							<s:iterator value="page.data">
								<tr target="entity_transId" rel='<s:property value="transId" />'>
									<td>
											<s:a id="showView" namespace="/rst" action="m-workshop-trans"
												method="view" encode="false" cssClass="view button"
												target="dialog" mask="true" width="1100" height="540"
												title="详情">
												<s:param name="entity.transId" value="transId" />
												<s:param name="callbackId" value="%{_}"></s:param>
												<span>查看</span>
											</s:a>
										</td>
									<td><s:if test='statusCd=="0"'>
											<font style="color: #6495ED;">运输中</font>
										</s:if> <s:elseif test="statusCd==\"Yes\"">
											<font style="color: green;">已收货</font>
										</s:elseif> <s:elseif test="statusCd=='No'">
											<font style="color: red;">有问题</font>
										</s:elseif></td>
									<td><s:property value="fromWsCd" />
									</td>
									<td><s:property value="transUserName" />
									</td>
									<td><s:property value="sender" />
									</td>
									<td><s:property value="toWsCd" />
									</td>
									<td><s:property value="receiver" />
									</td>
									<td><s:property value="tCompanyNm" />
									</td>
									<td><s:property value="driver" />
									</td>
									<td>
									<s:if test="weight!=null&&weight!=''">
                                            <s:text name="format.number">  
									  	      <s:param value="weight"/>  
									        </s:text>  
									    </s:if>
									    <s:else>
									        --
									    </s:else>
									</td>
									<td><s:date name="deliveryTm" format="yyyy-MM-dd HH:mm:ss" />
									</td>
									<td><s:date name="arrivalTm" format="yyyy-MM-dd HH:mm:ss" />
									</td>
									<td title="<s:property value="memo" />"><div class="autocut"><s:property value="dealComment" /></div>
									</td>
                                    <s:if test="sessionUser.userId=='USER0047'">
                                        <td><s:a style="cursor:pointer" namespace="/rst" action="m-workshop-trans" method="deleteWorkTrans" title="是否删除本条工地转移信息?"
                                        callback="refreshCurrentNavTabSearch" target="ajaxTodo" encode="false" cssClass="delete">
                                        <s:param name="entity.transId" value="transId" />
                                        <span>删除</span> 
                                        </s:a>
                                        </td>
                                    </s:if>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</div>
				<div region="south" height="26">
					<div class="panelBar">
						<div class="pages">
							<span>显示</span>
							<s:select list="page.pageSizeList" name="queryEntity.pageSize"
								cssClass="pageSize" form="${_}" />
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
				</div>
			</div>
		</div>
	</s:form>
	<script type="text/javascript">
		/* function showView(transId){
			 var url="/ISMS/rst/m-workshop-trans!view.action?entity.transId="+transId;
			 $("#showView").attr("href",url).click();
		 }; */
	</script>
</body>
</html>
