<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<div style="height:330px;width: 100%;border-bottom: 1px #B8D0D6 solid;">
	<div style="float: left; width: 405px;  ">
		<p style="width: 250px;">
			<label for="entity_title" style="width: 60px;">标题</label> <span> <s:textfield
					name="entity.title" id="entity_title" maxlength="200" readOnly="false"/> </span>
		</p>
		<p style="width: 250px;">
			<label for="entity_workCenterId" style="width: 60px;">工地中心</label> <span> <s:textfield
					name="entity.workCenterId" id="entity_workCenterId"
					maxlength="32" readOnly="false"/> </span>
		</p>
		<p style="width: 250px;">
			<label for="entity_reportUserName" style="width: 60px;">报告人</label> <span> <s:textfield
					name="entity.reportUserName" id="entity_reportUserName"
					maxlength="32" readOnly="false"/> </span>
		</p>
		<p style="width: 250px;">
			<label for="entity_reportTm" style="width: 60px;">报告时间</label> <span><input type="text"   readonly="readonly" value="<s:date
		            name="entity.reportTm"  format="yyyy-MM-dd"/>" />  </span>
		</p>
		<p style="width: auto;">
			<label for="entity_memo" style="width: 60px;">备注</label> <span> <s:textarea cols="50"
					rows="4" name="entity.memo" id="entity_memo" readOnly="false"/> </span>
		</p>
		<p style="width: 380px;"/>
		<p style="width: 380px;"/>
		
		<p style="width: 380px;">
			<label for="entity_statusCd" style="width: 60px;">状态</label> <span class="labelnarrow"> <s:radio
					id="entity.statusCd" name="entity.statusCd" cssClass="required"
					list="#{'Yes':'已处理','No':'有问题'}">
				</s:radio> </span>
		</p>
		<p style="width: auto;">
			<label for="entity_dealComment" style="width: 60px;">处理意见</label> <span> <s:textarea
					cols="50" rows="3" name="entity.dealComment" id="entity_dealComment" cssClass="required"
					maxlength="200" /> </span>
		</p>
	</div>
	<div style="float: right; width: 480px;">
		<ul id="${_}myGallery">
			<s:if test="entity.fileName!=null&&entity.fileName.size()>0">
				<s:iterator value="entity.fileName" id="image">
					<li><img src="<%=basePath%>/<s:property value='image'/>" />
					</li>
				</s:iterator>
			</s:if>
			<s:else>
				<li><img src="<%=basePath%>/image/pub.jpg" />
				</li>
			</s:else>
		</ul>
	</div>
</div>
<div style="width: 100%;float: none;overflow: auto;">
<jsp:include page="_edit_deal_info.jsp"></jsp:include>
</div>


