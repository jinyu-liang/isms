<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pt" uri="/is-tags"%>
<p style="width: 500px;">
	<label for="entity_title" style="width: 50px;">采购项目</label> <span>
		<s:textfield name="entity.title" id="entity_title" cssClass="required"
			maxlength="200"  readOnly="true" size="50"/> </span>
</p>
<p style="width: 200px;">
	<label for="entity_projName" style="width: 50px;">工地中心</label> <span>
		<s:textfield name="entity.projName" id="entity_projName"
			maxlength="100" readOnly="true" /> </span>
</p>
<p style="width: 200px;">
	<label for="entity_unitPrice" style="width: 50px;">单价</label> <span>
		<s:textfield name="entity.unitPrice" id="entity_unitPrice" readOnly="true"/> </span>
</p>
<p style="width: 200px;">
	<label for="entity_amount" style="width: 50px;">金额</label> <span>
		<s:textfield name="entity.amount" id="entity_amount" readOnly="true"/> </span>
</p>
<p style="width: 200px;">
	<label for="entity_number" style="width: 50px;">数量</label> <span>
		<s:textfield name="entity.number" id="entity_number" readOnly="true"/> </span>
</p>
<p style="width: 200px;">
	<label for="entity_reportUserCd" style="width: 50px;">申请人</label> <span> 
		 <input type="text" value="<pt:usernameShow userId='entity.reportUserCd' />" readonly="readonly"/></span>
</p>
<p style="width: 200px;">
	<label for="entity_reportTm" style="width: 50px;">申请时间</label> <span>
    <input type="text" readonly="readonly"
     value="<s:date name="entity.reportTm" format="yyyy-MM-dd"/>"/>
		
	</span>
</p>
<p style="width: 500px;">
	<label for="entity_memo" style="width: 50px;">备注</label> 
			<span>
			<s:textarea name="entity.memo" cols="50"
			rows="3" id="entity.memo" maxlength="100" readOnly="true"/>
			</span>
</p> 
<p style="width: 380px;">
</p>
<p style="width: 380px;">
</p>
<p style="width: 200px;">
	<label for="entity_verifiedHeadStatus" style="width: 50px;">审核结果</label>
	<span class="labelnarrow">
	<s:if test="entity.verifiedHeadStatus==\"No\"">
      <font style="color: red;">有问题</font>
         </s:if> 
         <s:if test="entity.verifiedHeadStatus==\"Yes\"">
             <font style="color: green;">同意</font>
         </s:if> 
         <s:if test="entity.verifiedHeadStatus==\"0\"">
             <font style="color: #6495ED;">待审批</font>
         </s:if>
	
 </span>
</p>
<p style="width: auto;">
	<label for="entity_verifiedHeadMemo" style="width: 50px;">审核意见</label>
	<span> <s:textarea name="entity.verifiedHeadMemo" cols="50"
			rows="3" id="entity_verifiedHeadMemo" maxlength="100" readOnly="true" />
	</span>
</p>
<p style="width: 380px;">
</p>
<p style="width: 380px;">
</p>
<p style="width: 200px;">
	<label for="entity_dealResult" style="width: 50px;">处理结果</label>
	<span class="labelnarrow"><s:radio
			id="entity.dealResult" name="entity.dealResult"
			list="#{'Yes':'同意','No':'有问题'}" cssClass="required">
		</s:radio> </span>
</p>
<p style="width: auto;">
	<label for="entity_comment" style="width: 50px;">处理意见</label>
	<span> <s:textarea name="entity.comment" cols="50"
			rows="3" id="entity_comment" maxlength="100" cssClass="required"/>
	</span>
</p>
