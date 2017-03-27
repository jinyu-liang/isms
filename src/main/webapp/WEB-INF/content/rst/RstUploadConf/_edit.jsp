<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div>
	<label for="entity_fileType">文件类型</label>
	
		<span>
				<s:textfield name="entity.fileType"  id="entity_fileType" maxlength="12"  cssClass="required " readonly="true" />
  	</span>
	
</div>
<div>
	<label for="entity_sizeConf">文件大小设置(k)</label>
	
		<span>
				<s:textfield name="entity.sizeConf"  id="entity_sizeConf" maxlength="10"  cssClass="required digits" />
  	</span>
	
</div>
<%-- <div>
	<label for="entity_statusCd">状态</label>
	
		<span>
				<s:textfield name="entity.statusCd"  id="entity_statusCd"  maxlength="1"/>
  	</span>
	
</div>
<div>
	<label for="entity_operUserId">操作用户Id</label>
	
		<span>
				<s:textfield name="entity.operUserId"  id="entity_operUserId"  maxlength="32"/>
  	</span>
	
</div>
<div>
	<label for="entity_operUserName">操作用户名称</label>
	
		<span>
				<s:textfield name="entity.operUserName"  id="entity_operUserName"  maxlength="32"/>
  	</span>
	
</div>
<div>
	<label for="entity_operTime">操作时间</label>
	
		<span>
			<s:textfield name="entity.operTime" id="entity_operTime" cssClass="date" >
				<s:param name="value">
					<s:date name="entity.operTime" format="yyyy-MM-dd" />
				</s:param>
			</s:textfield>
		</span>
		
	
</div> --%>
