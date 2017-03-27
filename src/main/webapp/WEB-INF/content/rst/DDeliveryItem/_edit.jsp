<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div>
	<label for="entity_itemId">发货计划货物Id</label>
	
		<span>
				<s:textfield name="entity.itemId"  id="entity_itemId" />
  	</span>
	
</div>
<div>
	<label for="entity_planId">发货计划ID</label>
	
		<span>
				<s:textfield name="entity.planId"  id="entity_planId"  maxlength="32"/>
  	</span>
	
</div>
<div>
	<label for="entity_itemNo">序号</label>
	
		<span>
				<s:textfield name="entity.itemNo"  id="entity_itemNo" />
  	</span>
	
</div>
<div>
	<label for="entity_classCd">货物类别代码-备用</label>
	
		<span>
				<s:textfield name="entity.classCd"  id="entity_classCd"  maxlength="4"/>
  	</span>
	
</div>
<div>
	<label for="entity_classNm">货物类别名称</label>
	
		<span>
				<s:textfield name="entity.classNm"  id="entity_classNm"  maxlength="50"/>
  	</span>
	
</div>
<div>
	<label for="entity_categoryCd">分类代码</label>
	
		<span>
				<s:textfield name="entity.categoryCd"  id="entity_categoryCd"  maxlength="8"/>
  	</span>
	
</div>
<div>
	<label for="entity_categoryNm">分类名称</label>
	
		<span>
				<s:textfield name="entity.categoryNm"  id="entity_categoryNm"  maxlength="80"/>
  	</span>
	
</div>
<div>
	<label for="entity_materialCd">材质代码</label>
	
		<span>
				<s:textfield name="entity.materialCd"  id="entity_materialCd"  maxlength="8"/>
  	</span>
	
</div>
<div>
	<label for="entity_materialNm">材质名称</label>
	
		<span>
				<s:textfield name="entity.materialNm"  id="entity_materialNm"  maxlength="64"/>
  	</span>
	
</div>
<div>
	<label for="entity_modelNo">型号</label>
	
		<span>
				<s:textfield name="entity.modelNo"  id="entity_modelNo"  maxlength="64"/>
  	</span>
	
</div>
<div>
	<label for="entity_identificationCd">标识编码</label>
	
		<span>
				<s:textfield name="entity.identificationCd"  id="entity_identificationCd"  maxlength="20"/>
  	</span>
	
</div>
<div>
	<label for="entity_identificationNm">标识名称</label>
	
		<span>
				<s:textfield name="entity.identificationNm"  id="entity_identificationNm"  maxlength="50"/>
  	</span>
	
</div>
<div>
	<label for="entity_sender">发货人</label>
	
		<span>
				<s:textfield name="entity.sender"  id="entity_sender"  maxlength="20"/>
  	</span>
	
</div>
<div>
	<label for="entity_receiver">接收人</label>
	
		<span>
				<s:textfield name="entity.receiver"  id="entity_receiver"  maxlength="11"/>
  	</span>
	
</div>
<div>
	<label for="entity_sendAmount">发货数</label>
	
		<span>
				<s:textfield name="entity.sendAmount"  id="entity_sendAmount" />
  	</span>
	
</div>
<div>
	<label for="entity_receivedAmount">实收数</label>
	
		<span>
				<s:textfield name="entity.receivedAmount"  id="entity_receivedAmount" />
  	</span>
	
</div>
<div>
	<label for="entity_unit">单位</label>
	
		<span>
				<s:textfield name="entity.unit"  id="entity_unit"  maxlength="10"/>
  	</span>
	
</div>
<div>
	<label for="entity_memo">备注</label>
	
		<span>
				<s:textfield name="entity.memo"  id="entity_memo" />
  	</span>
	
</div>
<div>
	<label for="entity_statusCd">状态</label>
	
		<span>
				<s:textfield name="entity.statusCd"  id="entity_statusCd"  maxlength="1"/>
  	</span>
	
</div>
<div>
	<label for="entity_usePlace">使用位置</label>
	
		<span>
				<s:textfield name="entity.usePlace"  id="entity_usePlace"  maxlength="100"/>
  	</span>
	
</div>
