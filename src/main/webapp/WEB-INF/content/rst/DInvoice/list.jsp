<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pt" uri="/is-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>出门单列表</title>
<style type="text/css">
.autocut {
    width:200px;
    overflow:hidden;
    white-space:nowrap;
    text-overflow:ellipsis;
    -o-text-overflow:ellipsis;
    -icab-text-overflow: ellipsis;
    -khtml-text-overflow: ellipsis;
    -moz-text-overflow: ellipsis;
    -webkit-text-overflow: ellipsis;
}

</style>
</head>
<body>

    <s:property value="message" escapeHtml="false" />
    <s:form id="plan_child_form" method="post" action="d-invoice!list" style="height:100%;width:100%;"
        onsubmit="return divSearch(this,'plan_child_plan');">
        <div class="page" style="height: 100%; width: 100%;">
            <div class="layout">
                <div region="center">
                    <table class="table" style="width:1800px; overflow:scroll;">
                        <thead>
                            <tr style="text-align:center;">
                                <th style="width:3%;">图片</th>
                                <th style="width:6%;">状态</th>
                                <th style="width:7%;">申请发车时间</th>
                                <th style="width:4%;">起单用户</th>
                                <th style="width:6%;">收货工作中心</th>
                                <th style="width:4%;">发货时间</th>
                                <th style="width:4%;">审核人</th>
                                <th style="width:6%;">运输公司名称</th>
                                <th style="width:4%;">司机</th>
                                <th style="width:4%;">车号</th>
                                <th style="width:5%;">发货仓库</th>
                                <th style="width:8%;">发货批准时间</th>
                                <th style="width:6%;">发货批准用户</th>
                                <th style="width:8%;">确认发车时间</th>
                                <th style="width:8%;">收货时间</th>
                                <th>备注</th>
                                <s:if test="sessionUser.userId=='USER0047'">
                                    <th style="width:3%;">操作</th>
                                </s:if>

                                <!-- <th>发货单ID</th>
                                <th>发货计划ID</th>
                                <th>销售订单号</th>
                                <th>计划表标题</th>
                                <th>运输公司电话</th>
                                <th>司机电话</th>
                                <th>最后更新时间</th>
                                <th>删除区分</th> -->
                            </tr>
                        </thead>
                        <tbody>
                            <s:iterator value="page.data">
                                <tr target="entity_invoiceId" rel='<s:property value="invoiceId" />'  style="text-align:center;">
                                    <td><s:a namespace='/rst' action="d-invoice" method="getInvoiceImage" encode="false"
                                             cssClass="view button" target="dialog" mask="true" width="1025" height="738">
                                             <s:param name="entity.invoiceId" value="invoiceId" />
                                             <s:param name="callbackId" value="%{_}"></s:param>
                                            <span>图片</span>
                                        </s:a>
                                    </td>
                                    <td><s:if test="statusCd==0"> <font style="color: #6495ED;">待审批</font></s:if>
                                         <s:elseif test="statusCd==1"><font style="color: blue;">可发车</font></s:elseif> 
                                         <s:elseif test="statusCd==2"><font style="color: red;">发车有问题</font></s:elseif> 
                                         <s:elseif test="statusCd==3"><font style="color: #FFC125">送货中</font></s:elseif>
                                         <s:elseif test="statusCd==4"><font style="color: green;">已收货</font></s:elseif>
                                         <s:elseif test="statusCd==5"><font style="color: red;">收货有问题</font></s:elseif>
                                         <s:elseif test="statusCd==6">刚上传</s:elseif>
                                         <s:elseif test="statusCd==7"><font style="color: green;">收货问题解决</font></s:elseif></td>
                                    <td><s:date name="approvalReqTm" format="yyyy-MM-dd HH:mm:ss" /></td>
                                    <td><pt:usernameShow userId="invoiceUserId" /></td>
                                    <td><pt:workshorpnameShow wsCd="toWsCd" /></td>
                                    <td><s:date name="deliveryTm" format="yyyy-MM-dd" /></td>
                                    <td><s:property value="verifiedBy" /></td>
                                    <td><s:property value="tcompanyNm" /></td>
                                    <td><s:property value="driver" /></td>
                                    <td><s:property value="truckNum" /></td>
                                    <td><pt:workshorpnameShow wsCd="fromWsCd" /></td>
                                    <td><s:date name="approvalTm" format="yyyy-MM-dd HH:mm:ss" /></td>
                                    <td><pt:usernameShow userId="approvalUserCd" /></td>
                                    <td><s:date name="sureDeliveryTm" format="yyyy-MM-dd HH:mm:ss" /></td>
                                    <td><s:date name="arrivalTm" format="yyyy-MM-dd HH:mm:ss" /></td>
                                    <td title="<s:property value="memo" />"><div class="autocut" ><s:property value="memo" /></div></td>

                                    <%-- <td><s:property value="invoiceId" /></td>
                                    <td><s:property value="planId" /></td>
                                    <td><s:property value="sellOrderCode" /></td>
                                    <td><s:property value="title" /></td>
                                    <td><s:property value="tcompanyTel" /></td>
                                    <td><s:property value="driverTel" /></td>
                                    <td><s:property value="updageTm" /></td>
                                    <td><s:property value="deleteCd" /></td> --%>
                                    <s:if test="sessionUser.userId=='USER0047'">
                                    <td><a style="cursor:pointer" onclick=" return deleteInvoice('<s:property value="invoiceId" />')" >删除 </a>
                                    </td>
                                    </s:if>
                                </tr>
                            </s:iterator>
                        </tbody>
                    </table>
                </div>
                <div region="south" height="26">
                    <div class="panelBar">
                        <div class="pages">
                            <span>显示</span>
                            <s:select list="page.pageSizeList" name="queryEntity.pageSize" cssClass="pageSize" rel="plan_child_plan" form="plan_child_form"/>
                            <span>条，共[<s:property value="page.count" />]条
                            </span>
                        </div>
                        <s:hidden name="queryEntity.pageNumber" id="pageNumber" />
                        <s:hidden name="_" value="plan_child_form"/>
                        <div class="pagination" targetType="ajax" rel="plan_child_plan" totalCount='<s:property value="page.count"/>' numPerPage='<s:property value="page.pageSize"/>'
                            pageNumShown='<s:property value="page.pageNumShown"/>' currentPage='<s:property value="page.pageNumber"/>'></div>
                    </div>
                </div>
            </div>
        </div>
    </s:form>
</body>
</html>
