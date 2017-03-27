<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pt" uri="/is-tags"%>
<p style="width: 500px;">
	<label for="entity_title" style="width: 60px;">采购项目</label> <span>
		<s:textfield name="entity.title" id="entity_title" cssClass="required"
			maxlength="200"  readOnly="true" size="50"/> </span>
</p>
<p style="width: 250px;">
	<label for="entity_projName" style="width: 60px;">工地中心</label> <span>
		<s:textfield name="entity.projName" id="entity_projName"
			maxlength="100" readOnly="true" /> </span>
</p>
<p style="width: 250px;">
	<label for="entity_unitPrice" style="width: 60px;">单价</label> <span>
		<s:textfield name="entity.unitPrice" id="entity_unitPrice" readOnly="true"/> </span>
</p>
<p style="width: 250px;">
	<label for="entity_amount" style="width: 60px;">金额</label> <span>
		<s:textfield name="entity.amount" id="entity_amount" readOnly="true"/> </span>
</p>
<p style="width: 250px;">
	<label for="entity_number" style="width: 60px;">数量</label> <span>
		<s:textfield name="entity.number" id="entity_number" readOnly="true"/> </span>
</p>
<p style="width: 250px;">
	<label for="entity_reportUserCd" style="width: 60px;">采购申请人</label> <span><input type="text" readonly="readonly"
            value="<pt:usernameShow userId='entity.reportUserCd'/>"/>
             </span>
</p>
<p style="width: 250px;">
	<label for="entity_reportTm" style="width: 60px;">申请时间</label> <span>
    <input type="text"  readonly="readonly"  
    value="<s:date name="entity.reportTm" format="yyyy-MM-dd"/>"/>
	</span>
</p>
<p style="width: 500px;">
	<label for="entity_memo" style="width: 60px;">备注</label> 
			<span>
			<s:textarea name="entity.memo" cols="50"
			rows="4" id="entity.memo" maxlength="100" readOnly="true"/>
			</span>
</p> 
<p style="width: 380px;">
</p>
<p style="width: 380px;">
</p>
<p style="width: 250px;">
	<label for="entity_verifiedHeadStatus" style="width: 60px;">审核结果</label>
	<span class="labelnarrow"><s:radio
			id="entity.verifiedHeadStatus" name="entity.verifiedHeadStatus" cssClass="required"
			list="#{'Yes':'同意','No':'有问题'}">
		</s:radio> </span>
</p>
<p style="width: auto;">
	<label for="entity_verifiedHeadMemo" style="width: 60px;">审核意见</label>
	<span> <s:textarea name="entity.verifiedHeadMemo" cols="50"
			rows="5" id="entity_verifiedHeadMemo" maxlength="100" />
	</span>
</p>
