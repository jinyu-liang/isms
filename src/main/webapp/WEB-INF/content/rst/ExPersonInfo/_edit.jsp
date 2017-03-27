<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div>
	<label for="entity_name">姓名</label>
	
		<span>
				<s:textfield name="entity.name"  id="entity_name"  validate="{maxlength:50}"/>
  	</span>
	
</div>
<div>
	<label for="entity_identyCardCode">身份证号</label>
	
		<span>
		<s:if test="entity.identyCardCode>4">
			<s:textfield name="entity.identyCardCode"  id="entity_identyCardCode"  readonly="true"/>
		</s:if>
		<s:else>
		<s:textfield name="entity.identyCardCode"  id="entity_identyCardCode"  validate="{maxlength:18}"/>
		</s:else> 
				
  	    </span>
	
</div>
<div>
	<label for="entity_telephone">联系电话</label>
	
		<span>
				<s:textfield name="entity.telephone"  id="entity_telephone"  validate="{maxlength:20}"/>
  	</span>
	
</div>
<div> 
	<label for="entity_workType">工作类型名称</label>
	
	<s:select id="dePartList" list="userDepartList" name="entity.workType" listKey="dictCode" listValue="dictName" onchange="changeHidenValue(this)" headerKey="" headerValue="请选择..." cssClass="required"></s:select>
	<s:hidden name="entity.workTypeName" id="workTypeName"></s:hidden>
</div>
<div>
	<label for="entity_statusCd">状态</label>
  	
  	<s:select list="#{'1':'启用','0':'停用' }" name="entity.statusCd"
		cssClass="required" id="entity_work_count_type">
	</s:select>
	
</div>
<div>
	<label for="entity_bank_name">工资卡归属</label>
  	
  	<s:select list="#{'农业银行':'农业银行','工商银行':'工商银行','中国银行':'中国银行','建设银行':'建设银行','光大银行':'光大银行','民生银行':'民生银行','华夏银行':'华夏银行','交通银行':'交通银行','招商银行':'招商银行' }" name="entity.bank_name"
		cssClass="required" id="entity_bank_name">
	</s:select>
	
</div>
<div>
	<label for="entity_bank_id">隐行卡卡号</label>
	
		<span>
				<s:textfield name="entity.bank_id"  id="entity_bank_id"  validate="{maxlength:30}" />
  	</span>
	
</div>
<div>
	<label for="entity_wsCd">工程中心编码</label>
	
		<span>
				<s:textfield name="entity.wsCd"  id="entity_wsCd"  validate="{maxlength:4}" readonly="true"/>
  	</span>
	
</div>
<div>
	<label for="entity_wsNm">工地名称</label>
	
		<span>
				<s:textfield name="entity.wsNm"  id="entity_wsNm"  validate="{maxlength:64}" readonly="true"/>
  	</span>
	
</div>

<div>
	<label for="entity_updateDate">上班日期</label> <span> <s:textfield
			name="entity.updateDate" cssClass="required date" id="entity_updateDate"
			dateFmt="yyyy-MM-dd" readonly="true">
			<s:param name="value">
				<s:date name="entity.updateDate" format="yyyy-MM-dd" />
			</s:param>
		</s:textfield> <a class="inputDateButton" href="javascript:;">选择</a></span>
</div>

<div>
	<label for="entity_termName">施工队名</label>
	
		<span>
				<s:textfield name="entity.teamName"  id="entity_teamName"  validate="{maxlength:32}" readonly="true"/>
  	</span>
	
</div>
<div>
	<label for="entity_termId">施工队编号</label>
	
		<span>
				<s:textfield name="entity.teamId"  id="entity_teamId"  validate="{maxlength:32}" readonly="true"/>
  	</span>
	
</div>

<script >
	function changeHidenValue(elem){
    	var optionVal= $(elem).find("option:selected").text();  // 取到选中的listValue(Option)的值
    $("#workTypeName").val(optionVal);
}
</script>
