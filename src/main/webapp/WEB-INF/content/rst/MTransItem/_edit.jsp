<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div>
	<label for="entity_itemId">发货计划货物Id</label>
	
		<span>
				<s:textfield name="entity.itemId"  id="entity_itemId"  maxlength="32"/>
  	</span>
	
</div>
<div>
	<label for="entity_transId">运输Id</label>
	
		<span>
				<s:textfield name="entity.transId"  id="entity_transId"  maxlength="32"/>
  	</span>
	
</div>
<div>
	<label for="entity_wsCd">工程中心编码</label>
	
		<span>
				<s:textfield name="entity.wsCd"  id="entity_wsCd"  maxlength="4"/>
  	</span>
	
</div>
<div>
	<label for="entity_categoryCd">分类代码</label>
	
		<span>
				<s:textfield name="entity.categoryCd"  id="entity_categoryCd"  maxlength="8"/>
  	</span>
	
</div>
<div>
	<label for="entity_materialCd">材质代码</label>
	
		<span>
				<s:textfield name="entity.materialCd"  id="entity_materialCd"  maxlength="8"/>
  	</span>
	
</div>
<div>
	<label for="entity_modelNo">型号</label>
	
		<span>
				<s:textfield name="entity.modelNo"  id="entity_modelNo"  maxlength="64"/>
  	</span>
	
</div>
<div>
	<label for="entity_totalAmount">总数</label>
	
		<span>
				<s:textfield name="entity.totalAmount"  id="entity_totalAmount" />
  	</span>
	
</div>
<div>
	<label for="entity_unit">单位</label>
	
		<span>
				<s:textfield name="entity.unit"  id="entity_unit"  maxlength="10"/>
  	</span>
	
</div>
<div>
	<label for="entity_usePlace">使用位置</label>
	
		<span>
				<s:textfield name="entity.usePlace"  id="entity_usePlace"  maxlength="100"/>
  	</span>
	
</div>
<div>
	<label for="entity_materialNm">材质名称</label>
	
		<span>
				<s:textfield name="entity.materialNm"  id="entity_materialNm"  maxlength="64"/>
  	</span>
	
</div>
<div>
	<label for="entity_categoryNm">分类名称</label>
	
		<span>
				<s:textfield name="entity.categoryNm"  id="entity_categoryNm"  maxlength="80"/>
  	</span>
	
</div>
<div>
	<label for="entity_memo">备注</label>
	
		<span>
				<s:textfield name="entity.memo"  id="entity_memo" />
  	</span>
	
</div>
