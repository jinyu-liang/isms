<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div>
	<label for="entity_transId">运输Id</label>
	
		<span>
				<s:textfield name="entity.transId"  id="entity_transId"  maxlength="32"/>
  	</span>
	
</div>
<div>
	<label for="entity_fromWsCd">发货仓库编码</label>
	
		<span>
				<s:textfield name="entity.fromWsCd"  id="entity_fromWsCd"  maxlength="10"/>
  	</span>
	
</div>
<div>
	<label for="entity_transUserCd">运输用户ID</label>
	
		<span>
				<s:textfield name="entity.transUserCd"  id="entity_transUserCd"  maxlength="32"/>
  	</span>
	
</div>
<div>
	<label for="entity_sender">发货人</label>
	
		<span>
				<s:textfield name="entity.sender"  id="entity_sender"  maxlength="20"/>
  	</span>
	
</div>
<div>
	<label for="entity_toWsCd">收货工作中心编码</label>
	
		<span>
				<s:textfield name="entity.toWsCd"  id="entity_toWsCd"  maxlength="10"/>
  	</span>
	
</div>
<div>
	<label for="entity_receiver">接收人</label>
	
		<span>
				<s:textfield name="entity.receiver"  id="entity_receiver"  maxlength="11"/>
  	</span>
	
</div>
<div>
	<label for="entity_tCompanyNm">运输公司名称</label>
	
		<span>
				<s:textfield name="entity.tCompanyNm"  id="entity_tCompanyNm"  maxlength="50"/>
  	</span>
	
</div>
<div>
	<label for="entity_driver">司机</label>
	
		<span>
				<s:textfield name="entity.driver"  id="entity_driver"  maxlength="20"/>
  	</span>
	
</div>
<div>
	<label for="entity_weight">净重(吨)</label>
	
		<span>
				<s:textfield name="entity.weight"  id="entity_weight" />
  	</span>
	
</div>
<div>
	<label for="entity_deliveryTm">发货时间</label>
	
		<span>
				<s:textfield name="entity.deliveryTm"  id="entity_deliveryTm" />
  	</span>
	
</div>
<div>
	<label for="entity_arrivalTm">到货时间</label>
	
		<span>
				<s:textfield name="entity.arrivalTm"  id="entity_arrivalTm" />
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
