<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div>
	<label for="entity_itemId">发货计划货物Id</label>
	
		<span>
				<s:textfield name="entity.itemId"  id="entity_itemId" />
  	</span>
	
</div>
<div>
	<label for="entity_projectId">工程ID</label>
	
		<span>
				<s:textfield name="entity.projectId"  id="entity_projectId" />
  	</span>
	
</div>
<div>
	<label for="entity_itemNm">设备名称</label>
	
		<span>
				<s:textfield name="entity.itemNm"  id="entity_itemNm"  maxlength="100"/>
  	</span>
	
</div>
<div>
	<label for="entity_amount">金额</label>
	
		<span>
				<s:textfield name="entity.amount"  id="entity_amount" />
  	</span>
	
</div>
<div>
	<label for="entity_memo">备注</label>
	
		<span>
				<s:textfield name="entity.memo"  id="entity_memo" />
  	</span>
	
</div>
