<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<s:hidden id="entity_departId" name="entity.departId"></s:hidden>
<s:hidden id="entity_higherDepartId" name="entity.higherDepartId"></s:hidden>
<s:hidden id="entity_depdns" value="%{entity.depdns}"  name="entity.depdns"></s:hidden>
<s:hidden id="entity_depdn" value="%{entity.depdn}"  name="entity.depdn"></s:hidden>
<div>
    <label for="entity.departName">部门名称:</label> <span> <s:textfield name="entity.departName" id="entity_departName" cssClass="required" maxlength="60" />
    </span>
</div>
<div>
	<label for="entity_departtype">部门类型</label>
	<s:select list="#{'':'请选择类型','开发企业':'开发企业','总包企业':'总包企业','分包企业':'分包企业','劳务公司':'劳务公司','监理公司':'监理公司' }" name="entity.departtype"
		cssClass="required" id="entity_departtype">
	</s:select>

</div>

<div>
    <label for="higherDepartId">上级部门:</label> <span> <s:textfield name="entity.departfullname" id="entity_departfullname" value="%{entity_departfullname}"  maxlength="600" />
    <s:a namespace="/ggkz" action="ggkz-depart-info"
			method="gettreePostFilter" encode="false"
			cssClass="add" target="dialog" rel="%{_}" width="800" height = "550"
			minable="false" resizable="true" mask="true">
			<s:param name="callbackId" value="%{_}"></s:param>
			<s:param name="checkid" value="'entity_managerUserId_hidden'"></s:param>
			<s:param name="checkname" value="'entity_managerUserId_view'"></s:param>
			<s:param name="queryEntity.postName" value="'项目经理'"></s:param>
			<s:param name="previewids" value="'1,2'"></s:param>
            <s:param name="queryEntity.pageSize" value="1000" ></s:param>
			<span>选择</span>
		</s:a>
    </span>
    
</div>
<div>
    <label for="entity.ordernum">排序:</label> <span> <s:textfield name="entity.ordernum" id="entity_ordernum" cssClass="required" maxlength="60" />
    </span>
</div>
<div>
    <label for="entity.note">备注:</label> <span> <s:textarea name="entity.note" id="entity_note" rows="3" cols="50" maxlength="1024" />
    </span>
</div>