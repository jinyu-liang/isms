<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.UUID"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pt" uri="/is-tags"%>
<div>
	<label for="entity_wsNm">工地中心名称</label> <span> <s:textfield
			name="entity.wsNm" id="entity_wsNm" cssClass="required"
			maxlength="64" /> </span>

</div>
<div>
<s:hidden name="ww" id="ww" value="ww1"></s:hidden>
	<label for="entity_divCd">部门编码</label> <span>
			
			<s:select id="dePartList" list="userDepartList" name="entity.divCd" listKey="dictCode" listValue="dictName" headerKey="" headerValue="请选择..." cssClass="required"></s:select>

</div>
<div>
	<label for="entity_typeCd">区分编码</label>
	<s:select list="#{'':'请选择类型','0':'管理部门','1':'内线车间','2':'外线工地','3':'仓库' }" name="entity.typeCd"
		cssClass="required" id="entity_typeCd">
	</s:select>

</div>
<div>
	<label for="entity_managerUserId">负责人</label> <span><s:hidden
			name="entity.managerUserId" id="entity_managerUserId_hidden"/> <input type="text"
			id="entity_managerUserId_view" class="required" maxlength="32" readOnly="true" value="<pt:usernameShow userId="entity.managerUserId"></pt:usernameShow>">
		<s:a namespace="/ggkz" action="ggkz-choose-user"
			method="publicSingleChooseUserByPostFilter" encode="false"
			cssClass="add" target="dialog" rel="%{_}" width="850" 
			minable="false" resizable="false" mask="true">
			<s:param name="callbackId" value="%{_}"></s:param>
			<s:param name="checkid" value="'entity_managerUserId_hidden'"></s:param>
			<s:param name="checkname" value="'entity_managerUserId_view'"></s:param>
			<s:param name="queryEntity.postName" value="'项目经理'"></s:param>
			<s:param name="previewids" value="'1,2'"></s:param>
            <s:param name="queryEntity.pageSize" value="1000" ></s:param>
			<span>选择</span>
		</s:a>
	</span>



</div>
<div>




	<label for="entity_beginTime">开始时间</label> <span> <s:textfield
			name="entity.beginTime" cssClass="required date"
			id="entity_beginTime" dateFmt="yyyy-MM-dd" readonly="true">
			<s:param name="value">
				<s:date name="entity.beginTime" format="yyyy-MM-dd" />
			</s:param>
		</s:textfield> <a class="inputDateButton" href="javascript:;">选择</a> <%-- <s:textfield name="entity.beginTime" id="entity_beginTime" cssClass="date" >
				<s:param name="value">
					<s:date name="entity.beginTime" format="yyyy-MM-dd" />
				</s:param>
			</s:textfield> --%> </span>


</div>
<div>
	<label for="entity_endTime">结束时间</label> <span> <s:textfield
			name="entity.endTime" cssClass="required date" id="entity_endTime"
			dateFmt="yyyy-MM-dd" readonly="true">
			<s:param name="value">
				<s:date name="entity.endTime" format="yyyy-MM-dd" />
			</s:param>
		</s:textfield> <a class="inputDateButton" href="javascript:;">选择</a> <%-- 		<s:textfield name="entity.endTime" id="entity_endTime" cssClass="date" >
				<s:param name="value">
					<s:date name="entity.endTime" format="yyyy-MM-dd" />
				</s:param>
			</s:textfield --%> </span>
</div>
<div>
	<label>附件：</label> <span>
		<div class="anatomy container">
			<%
				String anatomyuuid = UUID.randomUUID().toString()
						.replaceAll("-", "");
			%>
			<s:hidden name="entity.filenames" id="filenames"></s:hidden>
			<a class="anatomy action" href="javascript:;"><var>
					{"button_action":"SWFUpload.BUTTON_ACTION.SELECT_FILES","file_types":"*.jpg;*.gif;*.png;*.bmp","file_upload_limit":"0","custom_settings":{"progressTarget":"<%=anatomyuuid%>","displayTarget":"<%=anatomyuuid%>","fillTarget":"filenames"}}
				</var><span>添加附件</span> </a>
			<div id="<%=anatomyuuid%>" class="fsUploadDisplay">
				<s:action name="sys-attach!exsitsFiles"
						executeResult="true" ignoreContextParams="true" flush="true"
						namespace="/sys">
						<s:param name="busiId" value="entity.wsCd"></s:param>
						<s:param name="attachId" value="entity.filenames"></s:param>
						<s:param name="fileShowType" value="3"></s:param>
						<%-- <s:param name="upFileTable"><%=tabStrLoad%></s:param>
                                                <s:param name="hiddenfileIds"><%="fileIds" + tabStrLoad%></s:param> --%>
					</s:action>
			</div>
		</div> <%-- <%@ page import="java.util.Date" %>
                  <%
                      String tabStrLoad = new Date().getTime()+"";
                  %>
                  <input type="hidden" name="fileIds" id="<%="fileIds"+tabStrLoad %>"/>
                  <s:url id="toFileUploadUrl" namespace="/sys" action="sys-attach!toFileUpload">
                      <s:param name="modelCodeForAttach" value="'gdgl'"></s:param>
                      <s:param name="upFileTable" ><%=tabStrLoad %></s:param>
                      <s:param name="fileIds"><%="fileIds"+tabStrLoad %></s:param>
                  </s:url>
                  <iframe id="<%="iframe"+tabStrLoad %>" frameborder="0" scrolling=no width="60%" height="36px" src="${toFileUploadUrl}">
                  </iframe>
                  <div class="onbeforeclose" call="destroySWFByUpFile('<%="iframe"+tabStrLoad %>');"></div>
                  <div class="page fileUploadArea">
                      <span>
                      <s:action name="sys-attach!exsitsFiles" executeResult="true" ignoreContextParams="true" flush="true" namespace="/sys">
                          <s:param name="busiId" value="%{entity.wsCd}"></s:param>
                          <s:param name="fileShowType" value="2"></s:param>
                          <s:param name="upFileTable"><%=tabStrLoad%></s:param>
                          <s:param name="hiddenfileIds"><%="fileIds" + tabStrLoad%></s:param>
                      </s:action>
                      </span>
                  </div> --%> </span>
</div>
