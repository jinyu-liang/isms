<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pt" uri="/is-tags" %>
<%@ page import="java.util.Date" %>	


<table class="list" width="100%" style="border-collapse:separate; border-spacing:0px 1px;">
<tr>
	<th colspan="4" align="center" >工作联系单基本信息</th>
</tr>
<tr>
	<td><label   style="letter-spacing: 6px;">发起人姓名</label></td>
	<td><span>
	<s:property value="entity.fqusername" />
	</span></td>
	
		<td><label  style="letter-spacing:6px;">发起人工号</label></td>
	<td><span>
	<s:property value="entity.fquserid" />
	</span></td>
</tr>
<tr>
	<td><label   style="letter-spacing: 6px;">联系单标题</label></td>
	<td colspan="3"><span>
	<s:property value="entity.title" />
	</span></td>
</tr>
<tr>
	<td><label   style="letter-spacing: 6px;">联系单内容</label></td>
	<td colspan="3"><span>
	<s:property value="entity.detail" />
	</span></td>
</tr>

 <tr>
   	<td><label   style="letter-spacing: 6px;">项目名称</label></td>
	<td><span>
	<s:property value="entity.ws_nm" />
	</span></td>
  	<td><label   style="letter-spacing: 6px;">接收部门</label></td>
	<td><span>
	<s:property value="entity.acceptdepname" />
	</span></td>
</tr>

 <tr>
	<td><label  style="letter-spacing:2px;">部门领导审核人</label></td>
	<td><span>
	<s:property value="entity.acceptdepmangername" />
	</span></td>
</tr>

<tr>
	<td><label  >部门领导审核人签注</label></td>
	<td colspan="3"><span>
	<s:property value="entity.acceptdepmangerdesc" />
	</span></td>
</tr>
<tr>
	<td><label  >主管领导审核</label></td>
	<td colspan="3"><span>
	<s:property value="entity.leadername" />
	</span></td>
</tr>
<tr>
	<td><label  >主管领导签注</label></td>
	<td colspan="3"><span>
	<s:property value="entity.leaderdesc" />
	</span></td>
</tr>

<tr>
<td><label  style="letter-spacing:2px;">是否接收</label></td>
	<td><span>
 <s:checkbox id="entity_Isaccept" name="entity.Isaccept" checked="true"></s:checkbox> 
 <font style="color:red;">勾选：接收  </font>
 <font style="color:green;">不勾选：拒绝 </font>
</span></td>

</tr>
<tr>
	<td><label  >接受部门意见</label></td>
	<td colspan="3"><span>
		<s:textarea name="entity.acceptdesc" cols="90" rows="5" id="entity_acceptdesc"  />
	</span></td>
</tr>

<s:if test="sessionUser.userId==entity.leaderID">
<s:hidden id="entity_status" name="entity.status" value="2"></s:hidden>

</s:if>
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
    	function chc(obj){
    		var leadername=$("#entity_leaderID").find("option:selected").text();
    		$("#entity_leadername").val(leadername); 
    	}
 </script>
