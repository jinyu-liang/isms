<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pt" uri="/is-tags" %>
<%@ page import="java.util.Date" %>	
<table class="list" width="100%">
<tr>
	<th colspan="4" align="center" >用户基本信息</th>
</tr>
<tr>
	<td><label for="entity_loginName" style="letter-spacing: 6px;">登录名</label></td>
	<td><span>
		<s:textfield name="entity.loginName" id="entity_loginName" cssClass="required" maxlength="50"/>
	</span></td>
	
		<td><label for="entity_workCode" style="letter-spacing:2em;">工号</label></td>
	<td><span>
		<s:textfield name="entity.workCode" id="entity_workCode" cssClass="required" maxlength="60"/>
	</span></td>
</tr>
<tr>
	<td><label for="entity_name" style="letter-spacing: 6px;">用户姓名</label></td>
	<td><span>
		<s:textfield name="entity.name" id="entity_name" cssClass="required" maxlength="50"/>
	</span></td>
		<td><label for="entity_post" style="letter-spacing:2em;">职务</label></td>
	<td><span>
		<s:select id="postList" list="userPostList" name="entity.post" listKey="dictCode" listValue="dictName" headerKey="" headerValue="请选择..." cssClass="required"></s:select>
	</span><font color="red">*</font></td>
</tr>
<tr>
  	<td><label for="entity_birthDate">出生日期</label></td>
	<td><span>
		<s:textfield name="entity.birthDate" id="entity_birthDate" cssClass="date" >
			<s:param name="value"><s:date name="entity.birthDate" format="yyyy-MM-dd"/></s:param> 
		</s:textfield>
	</span></td>
	  <td><label for="entity_departId" style="letter-spacing:2em;">部门</label></td>
    <td><span>
        <s:select list="userDepartList" name="entity.departId" listKey="dictCode" listValue="dictName" headerKey="" headerValue="请选择..." cssClass="required"></s:select>
    </span><font color="red">*</font></td>
</tr>
 <tr>
	<th colspan="4" align="center">密码管理</th>
</tr> 
<tr>
    <td><label for="entity_mobileSn" style="letter-spacing:2px;">密码(6-20位)</label></td>
    <td><span id="pass">
        <input type="password"  id="entity_password" maxlength="12" value="<s:property value='entity.newPassword'/>" />
    </span></td>
    <td><label for="entity_mobileId" style="letter-spacing:2px;">确认密码(6-20位)</label></td>
    <td>
    	<span id="newpass">
	         <input type="password"  name="entity.newPassword" id="entity_newPassword" value="<s:property value='entity.newPassword'/>"  maxlength="12" equalto="#entity_password" minlength="6" maxlength="20" />
	    </span>
	        <INPUT TYPE="checkbox" id="showPass" title="显示密码" 
	        	onclick="var temp=document.getElementById('entity_password').value;
                        var newtemp=document.getElementById('entity_newPassword').value;
				if(this.checked)
				{
					document.getElementById('pass').innerHTML='<input type=text id=entity_password  class=textInput />';
				    document.getElementById('newpass').innerHTML='<input type=text id=entity_newPassword name=entity.newPassword class=textInput equalto=#entity_password minlength=6 maxlength=20/>';
                }
				else{
					document.getElementById('pass').innerHTML='<input type=password id=entity_password  class=textInput  />';
				    document.getElementById('newpass').innerHTML='<input type=password id=entity_newPassword name=entity.newPassword class=textInput equalto=#entity_password minlength=6 maxlength=20 />';
                }
                if(temp!=null){
				    document.getElementById('entity_password').value=temp;
                }if(newtemp!=null){
				    document.getElementById('entity_newPassword').value=newtemp;
                }
				">显示密码
	</td>
</tr>
<tr></tr>
<tr id="areaDiv" style="">
<div>
	<td><label for="entity_headUserCd">区域部长</label></td>
	<td><span>
        <s:select id="areaList" list="headUserList" name="entity.headUserCd" listKey="dictCode" listValue="dictName" headerKey="" headerValue="请选择..." cssClass="required"></s:select>
	</span></td>
	<td colspan="2"/>
</tr>
<tr>
	<th colspan="4" align="center">用户联系信息</th>

</tr>
<tr>
    <td><label for="entity_officeTel">办公电话</label></td>
    <td><span>
        <s:textfield name="entity.officeTel" id="entity_officeTel" cssClass="telePhone" maxlength="18"/>
    </span></td>
    <td><label for="entity_mobileTel">移动电话</label></td>
    <td><span>
        <s:textfield name="entity.mobileTel" id="entity_mobileTel" cssClass="mobilePhone" maxlength="11"/>
    </span></td>
</tr>
<tr>
    <td><label for="entity_email" style="letter-spacing:4px;">Email</label></td>
    <td><span>
        <s:textfield name="entity.email" id="entity_email" cssClass="email" maxlength="60"/>
    </span></td>
	<td><label for="entity_nowResideAddress">现居住地</label></td>
	<td><span>
		<s:textfield name="entity.nowResideAddress" id="entity_nowResideAddress" maxlength="200"/>
	</span></td>
</tr>
<tr>
	<th colspan="4" align="center">手持终端信息</th>

</tr>
<s:if test="entity.orgId==0||entity.orgId==null">
<tr>
    <td colspan="4">
     <label for="entity_orgId" style="width: 120px;">设为信任用户</label> <span class="labelnarrow"> <s:radio
			id="entity.orgId" name="entity.orgId" value="entity.orgId" cssClass="required"
			list="#{'1':'自动注册设备并登陆'}"> 
		</s:radio></td>
</tr>
</s:if>
<tr>
    <td><label for="entity_mobileSn" style="letter-spacing:2px;">设备SN</label></td>
    <td><span>
        <s:textfield name="entity.mobileSn" id="entity_mobileSn" readOnly="true" maxlength="50"/>
    </span></td>
    <td><label for="entity_mobileId" style="letter-spacing:2px;">设备ID</label></td>
    <td><span>
        <s:textfield name="entity.mobileId" id="entity_mobileId" readOnly="true" maxlength="50"/>
    </span></td>
</tr>

<tr>
	<th colspan="4" align="center">权限分配</th>

</tr>
<tr>
    <td><label for="roles" style="letter-spacing:2em;">角色</label></td>
    <td colspan="3"><span>
        <table>
            <tbody>
                <s:if test="%{ggkzRoleInfoList.size()>0}">
                    <s:iterator value="ggkzRoleInfoList" status="st" id="obj">
                        <s:if test="%{#st.index%3==0}">
                            <tr>
                                <td>
                                    <s:if test="checkedRoleList.get(ggkzRoleInfoList[#st.index].roleId)!=null">
                                        <s:checkbox name="roles" checked="true" fieldValue="%{ggkzRoleInfoList[#st.index].roleId}"></s:checkbox>
                                    </s:if>
                                    <s:else>
                                        <s:checkbox name="roles" fieldValue="%{ggkzRoleInfoList[#st.index].roleId}"></s:checkbox>
                                    </s:else>
                                    <s:property value="%{ggkzRoleInfoList[#st.index].roleName}"/>
                                </td>
                                <td>
                                    <s:if test="%{ggkzRoleInfoList.size()>#st.index+1}">
                                        <s:if test="checkedRoleList.get(ggkzRoleInfoList[#st.index + 1].roleId)!=null">
                                            <s:checkbox name="roles" checked="true" fieldValue="%{ggkzRoleInfoList[#st.index+1].roleId}"></s:checkbox>
                                        </s:if>
                                        <s:else>
                                            <s:checkbox name="roles" fieldValue="%{ggkzRoleInfoList[#st.index+1].roleId}"></s:checkbox>
                                        </s:else>
                                        <s:property value="%{ggkzRoleInfoList[#st.index+1].roleName}"/>
                                    </s:if>
                                </td>
                                <td>
                                    <s:if test="%{ggkzRoleInfoList.size()>#st.index+2}">
                                        <s:if test="checkedRoleList.get(ggkzRoleInfoList[#st.index + 2].roleId)!=null">
                                            <s:checkbox name="roles" checked="true" fieldValue="%{ggkzRoleInfoList[#st.index+2].roleId}"></s:checkbox>
                                        </s:if>
                                        <s:else>
                                            <s:checkbox name="roles" fieldValue="%{ggkzRoleInfoList[#st.index+2].roleId}"></s:checkbox>
                                        </s:else>
                                        <s:property value="%{ggkzRoleInfoList[#st.index+2].roleName}"/>
                                    </s:if>
                                </td>
                            </tr>
                        </s:if>
                    </s:iterator>
                </s:if>
            </tbody>
        </table> 
    </span></td>
</tr>

</div>
</table>
<%-- <s:if test="%{entity == null || entity.userId == null || entity.userId == ''}">
	<tr>
		<label for=entity_password>密码</label></td>
		<td><span>
			<s:checkbox name="entity.password" id="entity_password" fieldValue="1" label="默认和用户名保持一致"></s:checkbox>
		（不选中默认不设置密码）
		</span></td>
	</tr>
</s:if>


<tr>
	<label for="entity_sex">性别</label></td>
	<td><span>
		<s:select name="entity.sex" id="entity_sex" list="sexList" listKey="dictCode" listValue="dictName" headerKey="" headerValue="请选择..."></s:select>
	</span></td>
</tr>

<tr>
	<label for="entity_birthDate">出生日期</label></td>
	<td><span>
		<s:textfield name="entity.birthDate" id="entity_birthDate" cssClass="date">
			<s:param name="value"><s:date name="entity.birthDate" format="yyyy-MM-dd"/></s:param> 
		</s:textfield>
	</span></td>
</tr>

<tr>
	<label for="entity_certificateCode">证件类型</label></td>
	<td><span>
		<s:select name="entity.certificateType" id="entity_certificateType" list="certificateTypeList" listKey="dictCode" listValue="dictName"></s:select>
	</span></td>
</tr>

<tr>
	<label for="entity_certificateCode">证件号码</label></td>
	<td><span>
		<s:textfield name="entity.certificateCode" id="entity_certificateCode" maxlength="80"/>
	</span></td>
</tr>

<tr>
	<label for="entity_nowResideAddress">现居住地</label></td>
	<td><span>
		<s:textfield name="entity.nowResideAddress" id="entity_nowResideAddress" maxlength="200"/>
	</span></td>
</tr>
<tr>
	<label for="entity_postcode">邮编</label></td>
	<td><span>
		<s:textfield name="entity.postcode" id="entity_postcode" cssClass="digits"/>
	</span></td>
</tr> --%>

<script type="text/javascript"> 
	$(document).ready(function(){
		//页面加载时
			var val  = $("#postList").val();
			$("#areaDiv").attr("style","");
			if(val=="5"){
				$("#areaDiv").attr("style","");
			}else{
				$("#areaDiv").attr("style","display: none");
			}
		//chage事件中
    		$("#postList").unbind("change").bind("change",function(){
    			var val  = $("#postList").val();
    			if(val=="5"){
    				$("#areaDiv").attr("style","");
    			}else{
    				$("#areaDiv").attr("style","display: none");
    				$("#areaList").attr("value","");
    			}
    		});
	});
</script>
