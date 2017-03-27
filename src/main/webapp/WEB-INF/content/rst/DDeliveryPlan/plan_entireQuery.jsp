<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>计划列表</title>
</head>
<body>
    <s:property value="message" escapeHtml="false" />
    <s:form method="post" action="" rel="plan_main_plan" target="ajax" onsubmit="return pageSubmit(this);" >
        <div class="page" style="height:100%;width:100%;">
            <div class="layout">
                <div region="north" height="70">
                    <div class="pageHeader">
                        <div class="searchBar">
                            <ul class="searchContent">
                                    <li>
                                            <label for="entity_planId">发货计划ID</label>
                                                <s:textfield id="queryEntity_planId" name="queryEntity.planId" maxLength="32"/>
                                                
                                    </li>
                                    <li>
                                            
                                            
                                            <label for="entity_sellOrderCode">销售订单号</label>
                                                <s:textfield id="queryEntity_sellOrderCode" name="queryEntity.sellOrderCode" maxLength="32"/>
                                                
                                    </li>
                                    <li>
                                            
                                            
                                            <label for="entity_title">计划表标题</label>
                                                <s:textfield id="queryEntity_title" name="queryEntity.title" maxLength="200"/>
                                                
                                    </li>
                                    <li>
                                            
                                            
                                            <label for="entity_toWsCd">收货工作中心编码</label>
                                                <s:textfield id="queryEntity_toWsCd" name="queryEntity.toWsCd" maxLength="10"/>
                                                
                                    </li>
                                    <li>
                                            
                                            
                                            <label for="entity_unloadPlaceNm">卸货地点</label>
                                                <s:textfield id="queryEntity_unloadPlaceNm" name="queryEntity.unloadPlaceNm" maxLength="64"/>
                                                
                                    </li>
                                    <li>
                                            
                                            
                                            <label for="entity_deliveryPlanTm">计划发货时间</label>
                                                <s:textfield id="queryEntity_deliveryPlanTm" name="queryEntity.deliveryPlanTm" />
                                                
                                    </li>
                                    <li>
                                            
                                            
                                            <label for="entity_verifiedSiteUserCd">审核项目经理用户Cd</label>
                                                <s:textfield id="queryEntity_verifiedSiteUserCd" name="queryEntity.verifiedSiteUserCd" maxLength="4"/>
                                                
                                    </li>
                                    <li>
                                            
                                            
                                            <label for="entity_verifiedSiteTm">项目经理审核时间</label>
                                                <s:textfield id="queryEntity_verifiedSiteTm" name="queryEntity.verifiedSiteTm" />
                                                
                                    </li>
                                    <li>
                                            
                                            
                                            <label for="entity_verifiedSiteStatus">审核结果</label>
                                                <s:textfield id="queryEntity_verifiedSiteStatus" name="queryEntity.verifiedSiteStatus" maxLength="1"/>
                                                
                                    </li>
                                    <li>
                                            
                                            
                                            <label for="entity_verifiedSiteMemo">项目经理审核备注</label>
                                                <s:textfield id="queryEntity_verifiedSiteMemo" name="queryEntity.verifiedSiteMemo" maxLength="100"/>
                                                
                                    </li>
                                    <li>
                                            
                                            
                                            <label for="entity_verifiedHeadUserCd">审核部长用户Cd</label>
                                                <s:textfield id="queryEntity_verifiedHeadUserCd" name="queryEntity.verifiedHeadUserCd" maxLength="4"/>
                                                
                                    </li>
                                    <li>
                                            
                                            
                                            <label for="entity_verifiedHeadTm">部长审核时间</label>
                                                <s:textfield id="queryEntity_verifiedHeadTm" name="queryEntity.verifiedHeadTm" />
                                                
                                    </li>
                                    <li>
                                            
                                            
                                            <label for="entity_verifiedHeadStatus">部长审核结果</label>
                                                <s:textfield id="queryEntity_verifiedHeadStatus" name="queryEntity.verifiedHeadStatus" maxLength="1"/>
                                                
                                    </li>
                                    <li>
                                            
                                            
                                            <label for="entity_verifiedHeadMemo">部长审核备注</label>
                                                <s:textfield id="queryEntity_verifiedHeadMemo" name="queryEntity.verifiedHeadMemo" maxLength="100"/>
                                                
                                    </li>
                                    <li>
                                            
                                            
                                            <label for="entity_deliveryReqTm">发货请求时间</label>
                                                <s:textfield id="queryEntity_deliveryReqTm" name="queryEntity.deliveryReqTm" />
                                                
                                    </li>
                                    <li>
                                            
                                            
                                            <label for="entity_deliveryDoneTm">发货完成时间</label>
                                                <s:textfield id="queryEntity_deliveryDoneTm" name="queryEntity.deliveryDoneTm" />
                                                
                                    </li>
                                    <li>
                                            
                                            
                                            <label for="entity_planFilePath">计划表文件路径</label>
                                                <s:textfield id="queryEntity_planFilePath" name="queryEntity.planFilePath" maxLength="100"/>
                                                
                                    </li>
                                    <li>
                                            
                                            
                                            <label for="entity_statusCd">状态</label>
                                                <s:textfield id="queryEntity_statusCd" name="queryEntity.statusCd" maxLength="1"/>
                                                
                                    </li>
                                    <li>
                                            
                                            
                                            <label for="entity_createUserCd">创建用户Id</label>
                                                <s:textfield id="queryEntity_createUserCd" name="queryEntity.createUserCd" maxLength="32"/>
                                                
                                    </li>
                                    <li>
                                            
                                            
                                            <label for="entity_createTm">创建时间</label>
                                                <s:textfield id="queryEntity_createTm" name="queryEntity.createTm" cssClass="date"/>
                                                
                                    </li>
                                    <li>
                                            
                                            
                                            <label for="entity_updateTm">修改时间</label>
                                                <s:textfield id="queryEntity_updateTm" name="queryEntity.updateTm" cssClass="date" />
                                                
                                    </li>
                            </ul>
                            <div class="subBar">
                                <ul>
                                    <li>
                                        <div class="buttonActive">
                                            <div class="buttonContent">
                                                <s:submit type="button">查询</s:submit>
                                            </div>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </s:form>
    <script>
    </script>
</body>
</html>
