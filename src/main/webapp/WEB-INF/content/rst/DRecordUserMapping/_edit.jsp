<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div>
	<label for="entity_mappingId">映射Id</label>
	
		<span>
				<s:textfield name="entity.mappingId"  id="entity_mappingId"  maxlength="32"/>
  	</span>
	
</div>
<div>
	<label for="entity_recordId">流水号</label>
	
		<span>
				<s:textfield name="entity.recordId"  id="entity_recordId"  maxlength="32"/>
  	</span>
	
</div>
<div>
	<label for="entity_recordType">记录种类</label>
	
		<span>
				<s:textfield name="entity.recordType"  id="entity_recordType"  maxlength="1"/>
  	</span>
	
</div>
<div>
	<label for="entity_mappingUserCd">对应用户CD</label>
	
		<span>
				<s:textfield name="entity.mappingUserCd"  id="entity_mappingUserCd"  maxlength="32"/>
  	</span>
	
</div>
<div>
	<label for="entity_levelFlg">权限区分</label>
	
		<span>
				<s:textfield name="entity.levelFlg"  id="entity_levelFlg"  maxlength="1"/>
  	</span>
	
</div>
<div>
	<label for="entity_createTm">创建时间</label>
	
		<span>
			<s:textfield name="entity.createTm" id="entity_createTm" cssClass="date" >
				<s:param name="value">
					<s:date name="entity.createTm" format="yyyy-MM-dd" />
				</s:param>
			</s:textfield>
		</span>
		
	
</div>
<div>
	<label for="entity_updateTm">修改时间</label>
	
		<span>
			<s:textfield name="entity.updateTm" id="entity_updateTm" cssClass="date" >
				<s:param name="value">
					<s:date name="entity.updateTm" format="yyyy-MM-dd" />
				</s:param>
			</s:textfield>
		</span>
		
	
</div>
<div>
	<label for="entity_deleteCd">删除区分</label>
	
		<span>
				<s:textfield name="entity.deleteCd"  id="entity_deleteCd"  maxlength="1"/>
  	</span>
	
</div>
