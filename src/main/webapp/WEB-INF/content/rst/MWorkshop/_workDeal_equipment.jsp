<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pt" uri="/is-tags"%>
<p><label><b>产品一览</b></label></p>
<table class="list nowrap itemDetail" addButton="添加行" width="100%" type="noRowNum">
    <thead>
        <tr>
            <th type="text" name="itemList[#index#].itemNm" size="18" fieldClass="required" fieldAttrs="{maxlength:32}" width="160"
                style="width: 120px;">产品名称</th>
            <th type="text" name="itemList[#index#].amount" defaultVal="1" size="12" fieldClass="required digits" fieldAttrs="{maxlength:12}"
                width="90" style="width: 90px;">数量(台)</th>
            <th type="text" name="itemList[#index#].memo" defaultVal="" size="34" fieldAttrs="{maxlength:255}">备注</th>
            <th type="del" width="30" style="width: 30px;">操作</th>
        </tr>
    </thead>
    <tbody>
        <s:iterator value="itemList" status="sta">
            <tr class="unitBox">
                <td><input type="text" maxlength="32" class="required textInput" size="17" value="<s:property value="itemNm"/>"
                    name="itemList[${sta.index}].itemNm"></td>
                <td><input type="text" maxlength="12" class="required digits textInput" size="12" value="<s:property value="amount"/>"
                    name="itemList[${sta.index}].amount"></td>
                <td><input type="text" maxlength="255" class="textInput" size="34" value="<s:property value="memo"/>"
                    name="itemList[${sta.index}].memo"></td>
                <td><a class="btnDel " href="javascript:void(0)">删除</a></td>
            </tr>
        </s:iterator>
    </tbody>
</table>
