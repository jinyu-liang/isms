<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pt" uri="/is-tags"%>
<div>   
 
    <label for="entity_wsNm">工地中心名称</label>
    
        <span>
                 <%-- <s:select id="shopList" list="shopList" name="entity.wsCd" listKey="wsCd" listValue="wsNm"
                 headerKey="" headerValue="请选择..." cssClass="required" onchange="changeHidenValue(this)" cssClass="required"></s:select>
            <s:hidden id="shopName" name="entity.wsNm"></s:hidden> --%>
            <input type="text" name="entity.wsNm" value="<pt:workshorpnameShow wsCd='entity.wsCd' />"  readonly="readonly"/>
            <s:hidden name="entity.wsCd" value="%{entity.wsCd}"></s:hidden>
    </span>
    
</div><br/>
<%-- <div>
	<label for="entity_teamCd">施工队编号</label>
	
		<span>
				<s:textfield name="entity.teamCd"  id="entity_teamCd"  cssClass="required" maxlength="32"/>
  	</span>
</div><br/>--%> 
<div> 
 
	<label for="entity_teamNm">施工队名称</label>
	
		<span>
				<s:textfield name="entity.teamNm"  id="entity_teamNm"  cssClass="required" maxlength="64"/>
               
  	</span>
	
</div><br/>
<div>
	<label for="entity_workerCount">施工总人数</label>
	
		<span>
				<s:textfield name="entity.workerCount"  id="entity_workerCount" cssClass="required digits" maxlength="5"/>
  	</span>	
</div>

<script >
	function changeHidenValue(elem){

    	var optionVal= $(elem).find("option:selected").text();  // 取到选中的listValue(Option)的值

    $("#shopName").val(optionVal);

}


</script>