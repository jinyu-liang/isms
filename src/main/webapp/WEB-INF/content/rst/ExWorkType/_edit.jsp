<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pt" uri="/is-tags"%>
<div>   
 
    <label for="entity_wsNm">工地中心名称</label>
    
        <span>
                 <%-- <s:select id="shopList" list="shopList" name="entity.wsCd" listKey="wsCd" listValue="wsNm"
                 headerKey="" headerValue="请选择..." cssClass="required" onchange="changeHidenValue(this)" cssClass="required"></s:select>
            <s:hidden id="shopName" name="entity.wsNm"></s:hidden> --%>
            <input type="text" name="entity.work_ws_nm" value="<pt:workshorpnameShow wsCd='entity.work_ws_cd' />"  readonly="readonly"/>
            <s:hidden name="entity.work_ws_cd" value="%{entity.work_ws_cd}"></s:hidden>
    </span> 
</div><br/>
<div> 
	<label for="entity_workNm">工种名称</label>
		<span>
				<s:textfield name="entity.workNm"  id="entity_workNm"  cssClass="required" maxlength="64"/>        
  	</span>
</div><br/>
<div>
	<label for="entity_work_count_type">计价方式</label>
	<s:select list="#{'':'请选择类型','0':'按天','1':'按量','2':'其它' }" name="entity.work_count_type"
		cssClass="required" id="entity_work_count_type">
	</s:select>
</div><br/>
<div>
	<label for="entity_workerCount">工资待遇</label>
	<span>
		<s:textfield name="entity.work_pay"  id="entity_work_pay" cssClass="required digits" maxlength="5"/>
  	</span>	
</div>

<script >
	function changeHidenValue(elem){

    	var optionVal= $(elem).find("option:selected").text();  // 取到选中的listValue(Option)的值

    $("#shopName").val(optionVal);

}


</script>