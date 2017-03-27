<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div>
	<label for="entity_invoiceId">发货单ID</label>
	
		<span>
				<s:textfield name="entity.invoiceId"  id="entity_invoiceId"  maxlength="32"/>
  	</span>
	
</div>
<div>
	<label for="entity_planId">发货计划ID</label>
	
		<span>
				<s:textfield name="entity.planId"  id="entity_planId"  maxlength="32"/>
  	</span>
	
</div>
<div>
	<label for="entity_sellOrderCode">销售订单号</label>
	
		<span>
				<s:textfield name="entity.sellOrderCode"  id="entity_sellOrderCode"  maxlength="32"/>
  	</span>
	
</div>
<div>
	<label for="entity_title">计划表标题</label>
	
		<span>
				<s:textfield name="entity.title"  id="entity_title"  maxlength="200"/>
  	</span>
	
</div>
<div>
	<label for="entity_tcompanyNm">运输公司名称</label>
	
		<span>
				<s:textfield name="entity.tcompanyNm"  id="entity_tcompanyNm"  maxlength="50"/>
  	</span>
	
</div>
<div>
	<label for="entity_tcompanyTel">运输公司电话</label>
	
		<span>
				<s:textfield name="entity.tcompanyTel"  id="entity_tcompanyTel"  maxlength="32"/>
  	</span>
	
</div>
<div>
	<label for="entity_driver">司机</label>
	
		<span>
				<s:textfield name="entity.driver"  id="entity_driver"  maxlength="20"/>
  	</span>
	
</div>
<div>
	<label for="entity_truckNum">车号</label>
	
		<span>
				<s:textfield name="entity.truckNum"  id="entity_truckNum"  maxlength="10"/>
  	</span>
	
</div>
<div>
	<label for="entity_driverTel">司机电话</label>
	
		<span>
				<s:textfield name="entity.driverTel"  id="entity_driverTel"  maxlength="20"/>
  	</span>
	
</div>
<div>
	<label for="entity_fromWsCd">发货仓库编码</label>
	
		<span>
				<s:textfield name="entity.fromWsCd"  id="entity_fromWsCd"  maxlength="10"/>
  	</span>
	
</div>
<div>
	<label for="entity_toWsCd">收货工作中心编码</label>
	
		<span>
				<s:textfield name="entity.toWsCd"  id="entity_toWsCd"  maxlength="10"/>
  	</span>
	
</div>
<div>
	<label for="entity_verifiedBy">审核人名</label>
	
		<span>
				<s:textfield name="entity.verifiedBy"  id="entity_verifiedBy"  maxlength="20"/>
  	</span>
	
</div>
<div>
	<label for="entity_invoiceUserId">起单用户ID</label>
	
		<span>
				<s:textfield name="entity.invoiceUserId"  id="entity_invoiceUserId"  maxlength="32"/>
  	</span>
	
</div>
<div>
	<label for="entity_approvalReqTm">发料请求时间</label>
	
		<span>
				<s:textfield name="entity.approvalReqTm"  id="entity_approvalReqTm" />
  	</span>
	
</div>
<div>
	<label for="entity_approvalTm">发货批准时间</label>
	
		<span>
				<s:textfield name="entity.approvalTm"  id="entity_approvalTm" />
  	</span>
	
</div>
<div>
	<label for="entity_approvalUserCd">发货批准用户</label>
	
		<span>
				<s:textfield name="entity.approvalUserCd"  id="entity_approvalUserCd"  maxlength="4"/>
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
	<label for="entity_updageTm">最后更新时间</label>
	
		<span>
				<s:textfield name="entity.updageTm"  id="entity_updageTm" />
  	</span>
	
</div>
<div>
	<label for="entity_statusCd">状态</label>
	
		<span>
				<s:textfield name="entity.statusCd"  id="entity_statusCd"  maxlength="1"/>
  	</span>
	
</div>
<div>
	<label for="entity_deleteCd">删除区分</label>
	
		<span>
				<s:textfield name="entity.deleteCd"  id="entity_deleteCd"  maxlength="1"/>
  	</span>
	
</div>
