<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pt" uri="/is-tags" %>
<%@ page import="java.util.Date" %>	
<s:hidden id="entity_acceptdepname" name="entity.acceptdepname"></s:hidden>
<s:hidden id="entity_acceptdepmangername" name="entity.acceptdepmangername"></s:hidden>
<s:hidden id="entity_leadername" name="entity.leadername"></s:hidden>
<s:hidden id="entity_acceptusername" name="entity.acceptusername"></s:hidden>
<s:hidden id="entity_ws_nm" name="entity.ws_nm"></s:hidden>
<s:hidden id="entity_status" name="entity.status" value="0"></s:hidden>



<table class="list" width="100%" style="border-collapse:separate; border-spacing:0px 1px;">
<tr>
	<th colspan="4" align="center" >工作联系单基本信息</th>
</tr>
<tr>
	<td><label   style="letter-spacing: 6px;">发起人姓名</label></td>
	<td><span>
		<s:textfield name="entity.fqusername" id="entity_fqusername" cssClass="required" maxlength="50" readonly="true"/>
	</span></td>
	
		<td><label  style="letter-spacing:6px;">发起人工号</label></td>
	<td><span>
		<s:textfield name="entity.fquserid" id="entity_fquserid" cssClass="required" maxlength="50" readonly="true"/>
	</span></td>
</tr>
<tr>
	<td><label   style="letter-spacing: 6px;">联系单标题</label></td>
	<td colspan="3"><span>
		<s:textfield name="entity.title" id="entity_title" cssClass="required" maxlength="500" style="width:400px"/>
	</span></td>
</tr>
<tr>
	<td><label   style="letter-spacing: 6px;">联系单内容</label></td>
	<td colspan="3"><span>
		<s:textarea name="entity.detail" cols="90" rows="5" id="entity_detail" cssClass="required" />
	</span></td>
</tr>

 <tr>
   	<td><label   style="letter-spacing: 6px;">项目名称</label></td>
	<td><span>
		<s:select id="entity_ws_cd" list="workList" name="entity.ws_cd" listKey="dictCode" value="entity.ws_cd" listValue="dictName" 
		headerKey="" headerValue="请选择..." cssClass="required" readonly="true" onchange="chd(this)" cssClass="required"></s:select>
	</span></td>
</tr>
<tr>
  	<td><label   style="letter-spacing: 6px;">接收部门</label></td>
	<td><span>
		<s:select id="entity_acceptdepID" list="userDepartList" name="entity.acceptdepID" listKey="dictCode" value="entity.acceptdepID" listValue="dictName" 
		headerKey="" headerValue="请选择..." cssClass="required" readonly="true" onchange="cha(this)" cssClass="required"></s:select>
	</span></td>
		<td><label  style="letter-spacing:2px;">接收部门审核人</label></td>
	<td><span>
		<s:select id="entity_acceptuserID" list="userInfoList" name="entity.acceptuserID" listKey="dictCode" value="entity.acceptdepmangerID" listValue="dictName" 
		headerKey="" headerValue="请选择..." cssClass="required" readonly="true" onchange="che(this)" cssClass="required"></s:select>
	</span></td>
</tr>

 <tr>
	<td><label  style="letter-spacing:2px;">部门领导审核人</label></td>
	<td><span>
		<s:select id="entity_acceptdepmangerID" list="userInfoList" name="entity.acceptdepmangerID" listKey="dictCode" value="entity.acceptdepmangerID" listValue="dictName" 
		headerKey="" headerValue="请选择..." cssClass="required" readonly="true" onchange="chb(this)" cssClass="required"></s:select>
	</span></td>
</tr>
</table>
<script type="text/javascript">
        function chk(obj) {
    		var allFlag = obj.checked;
    		if (allFlag) {
    			$("#entity_leaderID").attr("disabled", false);
    			$("#entity_leaderdesc").attr("disabled", false);
    		} else {
    			$("#entity_leaderID").attr("disabled", true);
    			$("#entity_leaderID").val("");
    			$("#entity_leaderdesc").attr("disabled", true);
    			$("#entity_leaderdesc").val("");
    		}
    	}
    	function cha(obj){
    		var acceptdepname=$("#entity_acceptdepID").find("option:selected").text();
    		$("#entity_acceptdepname").val(acceptdepname); 
    	}
    	function chb(obj){
    		var acceptdepmangername=$("#entity_acceptdepmangerID").find("option:selected").text();
    		$("#entity_acceptdepmangername").val(acceptdepmangername); 
    	}
    	function chc(obj){
    		var leadername=$("#entity_leaderID").find("option:selected").text();
    		$("#entity_leadername").val(leadername); 
    	}
    	function chd(obj){
    		var ws_nm=$("#entity_ws_cd").find("option:selected").text();
    		$("#entity_ws_nm").val(ws_nm); 
    	}
    	function che(obj){
    		var acceptuserID=$("#entity_acceptuserID").find("option:selected").text();
    		$("#entity_acceptusername").val(acceptuserID); 
    	}
 </script>
