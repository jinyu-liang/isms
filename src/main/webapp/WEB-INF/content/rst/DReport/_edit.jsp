<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div>
	<label for="entity_title">计划表标题</label>
	
		<span>
				<s:textfield name="entity.title"  id="entity_title"  maxlength="200"/>
  	</span>
	
	<label for="entity_projectCode">项目代码</label>
	
		<span>
				<s:textfield name="entity.projectCode"  id="entity_projectCode"  maxlength="32"/>
  	</span>
	
</div>
<div>
	<label for="entity_projName">项目名称</label>
	
		<span>
				<s:textfield name="entity.projName"  id="entity_projName"  maxlength="100"/>
  	</span>
	
	<label for="entity_unitPrice">单价</label>
	
		<span>
				<s:textfield name="entity.unitPrice"  id="entity_unitPrice" />
  	</span>
	
</div>
<div>
	<label for="entity_amount">金额</label>
	
		<span>
				<s:textfield name="entity.amount"  id="entity_amount" />
  	</span>
	
	<label for="entity_number">数量</label>
	
		<span>
				<s:textfield name="entity.number"  id="entity_number" />
  	</span>
	
</div>
<div>
	<label for="entity_memo">备注</label>
	
		<span>
				<s:textfield name="entity.memo"  id="entity_memo" />
  	</span>
	
</div>
<div>
	<label for="entity_reportUserCd">报告用户编码</label>
	
		<span>
				<s:textfield name="entity.reportUserCd"  id="entity_reportUserCd"  maxlength="32"/>
  	</span>
	
	<label for="entity_reportTm">报告时间</label>
	
		<span>
				<s:textfield name="entity.reportTm"  id="entity_reportTm" />
  	</span>
	
</div>
<div>
	<label for="entity_processUserCd">处理用户编码</label>
	
		<span>
				<s:textfield name="entity.processUserCd"  id="entity_processUserCd"  maxlength="32"/>
  	</span>
	
	<label for="entity_processTm">处理时间</label>
	
		<span>
				<s:textfield name="entity.processTm"  id="entity_processTm"  maxlength="32"/>
  	</span>
	
</div>
<div>
	<label for="entity_comment">审批意见</label>
	
		<span>
				<s:textfield name="entity.comment"  id="entity_comment"  maxlength="100"/>
  	</span>
	
	<label for="entity_verifiedUserCd">审批用户Cd</label>
	
		<span>
				<s:textfield name="entity.verifiedUserCd"  id="entity_verifiedUserCd"  maxlength="32"/>
  	</span>
	
</div>
<div>
	<label for="entity_verifiedHeadTm">部长审核时间</label>
	
		<span>
				<s:textfield name="entity.verifiedHeadTm"  id="entity_verifiedHeadTm" />
  	</span>
	
</div>
<div>
	<label for="entity_verifiedHeadStatus">部长审核结果</label>
	
		<span>
				<s:textfield name="entity.verifiedHeadStatus"  id="entity_verifiedHeadStatus"  maxlength="1"/>
  	</span>
	<label for="entity_verifiedHeadMemo">部长审核备注</label>
	
		<span>
				<s:textfield name="entity.verifiedHeadMemo"  id="entity_verifiedHeadMemo"  maxlength="100"/>
  	</span>
	

	
</div>
