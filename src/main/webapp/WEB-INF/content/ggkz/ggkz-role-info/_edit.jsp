<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div>
    <label for="entity.roleName">角色名称:</label> <span> <s:textfield name="entity.roleName" id="entity_roleName"
            cssClass="required" maxlength="60"/>
    </span>
    <label for="entity.note" >权限配置:</label><span><font color="red">(注:信息互动所有人员都有权限，此处不再提供分配)</font></span>
</div>
<div>
    <label for="entity.note">备注:</label> <span> <s:textarea name="entity.note" id="entity_note" rows="1" cols="50" maxlength="1024" />
    </span>
</div>

<div>
<span style="width: 50%;float: left;">
    <label for="entity.note"> <font style="font-weight: bolder;">Web端权限设置:</font></label>
    <div>
        <ul class="tree treeCheck collapse fillsub toggle">
            <s:set value="" var="prefix"/>
            <s:iterator value="ggkzAuthInfoList" var="item">
            <s:if test="authName.split('-')[0]!=#prefix">
            <s:if test="#prefix!=null&&#prefix!=''">
            </ul></li>
            <s:set value="" var="prefix"/>
            </s:if>
            <s:set value="authName.split('-')[0]" var="prefix"/>
            <s:if test="checkedReView.get(#prefix)==0">
            <li class="checked">
            </s:if>
            <s:elseif test="checkedReView.get(#prefix) lt 0">
            <li class="indeterminate">
            </s:elseif>
            <s:else>
            <li>
            </s:else>
            <a href="javascript:;" class="indeterminately" tname="checkbox" tvalue="${authId}">${prefix}</a>
                <ul>
            </s:if>
            <s:if test="#prefix!=authName">
            <s:if test="ggkzAuthInfoListChecked.contains(#item)">
            <li class="checked">
            </s:if>
            <s:else>
            <li>
            </s:else>
            <a href="javascript:;" tname="checkbox" tvalue="${authId}">${authName}</a>
            </li>
            </s:if>
            </s:iterator>
            <s:if test="#prefix!=null&&#prefix!=''">
            </ul></li>
            </s:if>
        </ul>
    </div>
</span>
<span style="width: 50%;float: right;">
    <label for="entity.note"><font style="font-weight: bolder;">手持终端权限设置:</font></label>
    <div>
        <ul class="tree treeCheck collapse fillsub toggle">
            <s:set value="" var="prefix"/>
            <s:iterator value="ggkzAuthMobileInfoList" var="item">
            <s:if test="authName.split('-')[0]!=#prefix">
            <s:if test="#prefix!=null&&#prefix!=''">
            </ul></li>
            <s:set value="" var="prefix"/>
            </s:if>
            <s:set value="authName.split('-')[0]" var="prefix"/>
            <s:if test="checkedMobileReView.get(#prefix)==0">
            <li class="checked">
            </s:if>
            <s:elseif test="!checkedMobileReView.get(#prefix)>=0">
            <li class="indeterminate">
            </s:elseif>
            <s:else>
            <li>
            </s:else>
            <a href="javascript:;">${prefix}</a>
                <ul>
            </s:if>
            <s:if test="ggkzAuthMobileInfoListChecked.contains(#item)">
            <li class="checked">
            </s:if>
            <s:else>
            <li>
            </s:else><s:property value="#item.index"/>
            <a href="javascript:;" tname="mobileCheckbox" tvalue="${authId}">${authName}</a></li>
            </s:iterator>
            <s:if test="#prefix!=null&&#prefix!=''">
            </ul></li>
            </s:if>
        </ul>
    </div>
</span>
</div>