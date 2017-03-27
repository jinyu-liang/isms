<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.UUID"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pt" uri="/is-tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>计划列表</title>
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
    <s:form method="post" action="d-delivery-plan!list" onsubmit="return divSearch(this,'plan_main_plan');" style="height:100%;width:100%;" id="%{_}">
        <div class="page" style="height: 100%; width: 100%;">
            <div class="layout">
                <div region="north" height="100" style="height: 100px;">
                    <div class="pageHeader" style="height: 100%; width: 100%;">
                        <div class="searchBar" style="height: 100%; width: 100%;">
                            <table ><!-- class="searchContent" -->
                                <tbody>
                                    <tr>
                                        <td>计划起单用户 <s:textfield id="queryEntity_createUserCd"
                                                name="queryEntity.createUserCd" maxLength="32" /></td>
                                        <td>&nbsp;&nbsp;收货中心 <s:textfield id="queryEntity_toWsCd"
                                                name="queryEntity.toWsCd" maxLength="10" /></td>
                                        <td>&nbsp;&nbsp;审核项目经理 <s:textfield
                                                id="queryEntity_verifiedSiteUserCd" name="queryEntity.verifiedSiteUserCd" maxLength="8" /></td>
                                    
                                        <td>&nbsp;&nbsp;审核部长 <s:textfield id="queryEntity_verifiedHeadUserCd"
                                                name="queryEntity.verifiedHeadUserCd" maxLength="8" /></td>
                                         <td>&nbsp;&nbsp;出门单起单用户 <s:textfield id="pinvoiceUserId"
                                                name="pinvoiceUserId" maxLength="8" /></td>
                                     </tr>
                                     <tr>
                                         <td>发货仓库&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <s:textfield id="pfromWsCd"
                                                name="pfromWsCd" maxLength="10" /></td>
                                         <td>&nbsp;&nbsp;车牌号&nbsp;&nbsp;&nbsp;&nbsp; <s:textfield id="ptruckNum"
                                                name="ptruckNum" maxLength="10" /></td>
                                    </tr>
                                </tbody>
                            </table>
                            <table class="searchContent" style="width: 100%" width="100%">
                                <tbody>
                                    <tr>
                                        <td style="widows: 80px;" width="80px"><div class="anatomy container">
                                                <%
                                                    String anatomyuuid = UUID.randomUUID().toString()
                                                						.replaceAll("-", "");
                                                				String anatomyKeyuuid = UUID.randomUUID().toString()
                                                						.replaceAll("-", "");
                                                %>
                                                <sec:authorize ifAnyGranted="发料管理-发料计划预览与下发,发料管理-出门单预览">
                                                <input type="hidden" name="fileIds" class="required" id="<%=anatomyKeyuuid%>" /> <a class="anatomy action"
                                                    href="javascript:;"><var>{"button_action":"SWFUpload.BUTTON_ACTION.SELECT_FILE","file_upload_limit":"1","file_types":"*.xls;*.xlsx","file_types_description":"*.xls;*.xlsx","custom_settings":{"progressTarget":"<%=anatomyuuid%>","displayTarget":"<%=anatomyuuid%>","fillTarget":"<%=anatomyKeyuuid%>"}}</var><span>添加附件</span></a>
                                                        <!-- {"Settings":{"button_action":"SWFUpload.BUTTON_ACTION.SELECT_FILE","file_types":"xls,xlsx","file_types_description":"xls Files"}} -->
                                            </sec:authorize>
                                            </div> </td>
                                            <sec:authorize ifAllGranted="发料管理-发料计划预览与下发">
                                                <td style="widows: 80px;" width="80px">
                                                <s:a id="showMsg" namespace="/rst" action="d-delivery-plan" method="view" encode="false" cssClass="view"
                                                    target="dialog" mask="true" width="1225" height="795" title="发料计划表预览" close="(function deliveryPlan_remove_Session(){removeSession();return 1;})">
                                                    <s:param name="entity.planId" value="planId" />
                                                    <s:param name="callbackId" value="%{_}"></s:param>
                                                </s:a>
                                                <div class="button">
                                                    <div class="buttonContent">
                                                        <button id="yulanButton" onclick="return previewPlan('<s:property value="%{_}" />')">发料计划表预览</button>
                                                    </div>
                                                 </div>
                                                </td>
                                                <td style="widows: 80px;" width="80px">
                                                    <s:a href="upload/plan_templet.xls"><font style=" border: 1px solid #B2B3A8;">下载发料计划模版</font></s:a>
                                                </td>
                                             </sec:authorize>
                                         <sec:authorize ifAllGranted="发料管理-出门单预览">    
                                            <td style="widows: 80px;" width="80px">
                                                <s:a id="previewinvoiceaction" namespace="/rst" action="d-invoice" method="preViewInvoice" encode="false" 
                                                    target="dialog" mask="true" width="1225" height="795" title="出门单预览" close="(function dinvoice_remove_Session(){removeSession();return 1;})">
                                                    <s:param name="fileIds" value="'`~!'"></s:param>
                                                    <s:param name="callbackId" value="%{_}"></s:param>
                                                    <s:param name="planWsCd" value="'{entity_toWsCd}'"></s:param>
                                                    <s:param name="status" value="'{entity_status}'"></s:param>
                                                    <s:param name="entity.planId" value="'{entity_planId}'"></s:param>
                                                </s:a>
                                                <div class="button">
                                                    <div class="buttonContent">
                                                        <button id="yulanButton" onclick="return previewInvoice()">出门单预览</button>
                                                    </div>
                                                </div>
                                            </td>
                                            <td style="width: 50px;" width="50px">
                                                <s:a href="upload/invoice_templet.xls"><font style=" border: 1px solid #B2B3A8;">下载出门单模版</font></s:a>
                                            </td>
                                        </sec:authorize>
                                        <td><div class="subBar" style="padding-right: 10px;">
                                                <ul>
                                                    <li><div class="buttonActive">
                                                            <div class="buttonContent">
                                                                <s:submit type="button" onclick="return queryInvoiceAndPlan()"  value="查询"/>
                                                            </div>
                                                        </div></li>
                                                    <%-- <li><s:a  cssClass="button" namespace="/rst" action="d-delivery-plan" method="toEntireQuery" encode="false" target="dialog" mask="true" width="700" height="400" title="高级查询">
                                                            <span>高级查询</span>
                                                        </s:a></li> --%>
                                             <%--        <li><s:a  cssClass="button"   href="javascript:void(0)">
                                                            <span>高级查询</span>
                                                        </s:a></li> --%>
                                                </ul>
                                            </div></td>
                                    </tr>
                                    <tr>
                                        <td><div id="<%=anatomyuuid%>" class="fsUploadDisplay"></div></td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
                <div region="center">
                    <div style="display: none">
                        <s:a id="showPic"  encode="false" cssClass="view"
                            target="dialog" mask="true" width="1020" height="740" title="发料计划图片">
                            <s:param name="callbackId" value="%{_}"></s:param>
                        </s:a>
                        <s:a id="deletePlan"  callback="refPagePl" target="ajaxTodo" encode="false" ></s:a>
                        <s:a id="deleteInvoice"  callback="refPageIn" target="ajaxTodo" encode="false" ></s:a>
                    </div>
                    <table class="table" style="width:2250px; overflow:scroll;">
                        <thead>
                            <tr style="text-align:center;">
                                <th style="width:2.3%;">图片</th>
                                <th style="width:3%;">状态</th>
                                <th style="width:4%;">计划发货时间</th>
                                <th style="width:3%;">起单用户</th>
                                <th style="width:5%;">收货工作中心</th>
                                <th style="width:3%;">卸货地址</th>
                                <th style="width:5%;">审核项目经理</th>
                                <th style="width:7%;">项目经理审核时间</th>
                                <th style="width:6%;">项目经理审核结果</th>
                                <th style="width:10%;">项目经理审核意见</th>
                                <th style="width:3%;">审核部长</th>
                                <th style="width:6%;">部长审核时间</th>
                                <th style="width:4%;">部长审核结果</th>
                                <th style="width:10%;">部长审核意见</th>
                                <th style="width:6%;">最新申请发车时间</th>
                                <th style="width:6%;">完成时间</th>
                                <th style="width:5%;">创建时间</th>
                                <th style="width:6%;">最后更新时间</th>
                                <th >备注</th>
                                <s:if test="sessionUser.userId=='USER0047'">
                                    <th style="width:3%;">操作</th>
                                </s:if>
                                

                                <!-- <th>发货计划ID</th>
                                <th>销售订单号</th>
                                <th>计划表标题</th>
                                <th>发货完成时间</th>
                               
                                <th>计划表文件路径</th>
                                <th>状态</th> -->



                            </tr>
                        </thead>
                        <tbody>
                            <s:iterator value="page.data">
                                <tr target="entity_planId,entity_toWsCd,entity_status"
                                    rel='<s:property value="planId" />,<s:property value="toWsCd" />,<s:property value="statusCd" />'
                                    onclick="handleWisely1('<s:property value="planId" />','<s:property value="%{_}" />',event.type)"
                                    ondblclick="handleWisely1('<s:property value="planId" />','<s:property value="%{_}" />',event.type)" style="text-align:center;">
                                    <td width="30px" style="width:30px;text-align: center;" >
                                        <div class="button"><div class="buttonContent" style="text-align:center;" ><button onclick=" return bigPic('<s:property value="planId" />','<s:property value="planImagePath" />',event)">图片</button></div></div></td>
                                    <td><s:if test="statusCd==0">
                                            <font style="color: #6495ED;">待审批</font>
                                        </s:if> <s:elseif test="statusCd==1">
                                            <font style="color: blue;">可下发</font>
                                        </s:elseif> <s:elseif test="statusCd==2">
                                            <font style="color: red;">有问题</font>
                                        </s:elseif> <s:elseif test="statusCd==3">
                                            <font style="color: green;">已下发</font>
                                        </s:elseif></td>
                                    <td><s:date name="deliveryPlanTm" format="yyyy-MM-dd" /></td>
                                    <td><pt:usernameShow userId="createUserCd"></pt:usernameShow></td>
                                    <td><pt:workshorpnameShow wsCd="toWsCd" /></td>
                                    <td><s:property value="unloadPlaceNm" /></td>
                                    <td><pt:usernameShow userId="verifiedSiteUserCd" /></td>
                                    <td><s:date name="verifiedSiteTm" format="yyyy-MM-dd HH:mm:ss" /></td>
                                    <td>
                                        <s:if test="verifiedSiteStatus==3">同意 </s:if><s:elseif test="verifiedSiteStatus==4" >不同意</s:elseif>
                                    </td>
                                    <td  title="<s:property value="verifiedSiteMemo" />"><div class="autocut"><s:property value="verifiedSiteMemo" /></div></td>
                                    <td><pt:usernameShow userId="verifiedHeadUserCd" /></td>
                                    <td><s:date name="verifiedHeadTm" format="yyyy-MM-dd HH:mm:ss" /></td>
                                    <td> 
                                        <s:if test="verifiedHeadStatus==3">同意 </s:if><s:elseif test="verifiedHeadStatus==4" >不同意</s:elseif>
                                    </td>
                                    <td style="" title="<s:property value="verifiedHeadMemo" />"><div class="autocut"><s:property value="verifiedHeadMemo" /></div></td>
                                    <td><s:date name="deliveryReqTm" format="yyyy-MM-dd HH:mm:ss" /></td>
                                    <td><s:date name="deliveryDoneTm" format="yyyy-MM-dd HH:mm:ss" /></td>
                                    <td><s:date name="createTm" format="yyyy-MM-dd" /></td>
                                    <td><s:date name="updateTm" format="yyyy-MM-dd HH:mm:ss" /></td>
                                    <td title="<s:property value="memo" />"><div class="autocut"><s:property value="memo" /></div></td>

                                    <%--   <td><s:property value="planId" />
                                    </td>
                                    <td><s:property value="sellOrderCode" />
                                    </td>
                                    <td><s:property value="title" />
                                    </td>
                                    <td><s:property value="deliveryDoneTm" />
                                    </td>
                                   
                                    <td><s:property value="planFilePath" />
                                    </td>
                                    <td><s:property value="createUserCd" />
                                    </td> --%>
                                    <s:if test="sessionUser.userId=='USER0047'">
                                    <td><a style="cursor:pointer"  onclick="return deletePlan('<s:property value="planId" />',event)" >删除 </a>
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
                            <s:select list="page.pageSizeList" name="queryEntity.pageSize" cssClass="pageSize" rel="plan_main_plan" form="${_}"/>
                            <span>条，共[<s:property value="page.count" />]条
                            </span>
                        </div>
                        <s:hidden name="queryEntity.pageNumber" id="pageNumber" />
                        <s:hidden name="_"></s:hidden>
                        <div class="pagination" targetType="ajax" rel="plan_main_plan" totalCount='<s:property value="page.count"/>' numPerPage='<s:property value="page.pageSize"/>'
                            pageNumShown='<s:property value="page.pageNumShown"/>' currentPage='<s:property value="page.pageNumber"/>'></div>
                    </div>
                </div>
            </div>
        </div>
    </s:form>
        <div style="display: none;">
        <s:a id="showDetail" namespace="/rst" action="d-delivery-plan" method="view" encode="false" cssClass="view"
            target="dialog" mask="true" width="1225" height="745" title="发料计划表查看">
            <s:param name="entity.planId" value="planId" />
            <s:param name="callbackId" value="%{_}"></s:param>
        </s:a>
    </div>
    <s:url namespace="/rst" action="d-invoice" method="preViewInvoice" var="previewinvoiceaction" encode="false">
        <s:param name="fileIds" value="'`~!'"></s:param>
        <s:param name="callbackId" value="%{_}"></s:param>
        <s:param name="planWsCd" value="'{entity_toWsCd}'"></s:param>
        <s:param name="status" value="'{entity_status}'"></s:param>
        <s:param name="entity.planId" value="'{entity_planId}'"></s:param>
    </s:url>
    <script type="text/javascript">
    	var dcTime=250;    // doubleclick time
        var dcDelay=100;   // no clicks after doubleclick
        var dcAt=0;        // time of doubleclick
        var savEvent=null; // save Event for handling doClick().
        var savEvtTime=0;  // save time of click event.
        var savTO=null;    // handle of click setTimeOut
		/*点击查看出门单信息*/
		/* 查看事件  */
		function handleWisely1(planId,callbackId,which) {
			   switch (which) {
			     case "click":      
			       savEvent = which;
			       plancd = planId;//不转换不行？
			       d = new Date();
			       savEvtTime = d.getTime();
			       savTO = setTimeout("doClick(plancd,savEvent)", dcTime);
			       break;
			     case "dblclick":
			       doDoubleClickplan(planId,callbackId,which);
			       break;
			     default:
			   }
			 }
			 /* 单击  */
			 function doClick(planId,which) {
			   if (savEvtTime - dcAt <= 0) {
			     return false;
			   }
			   var url = "rst/d-invoice!list.action?queryEntity.planId="
					+ planId;
				$("#plan_child_form").attr("action", url).submit();
			 }
			 /* 双击 */
			 function doDoubleClickplan(planId,callbackId,which) {
			   var d = new Date();
			   dcAt = d.getTime();
			   if (savTO != null) {
			     savTO = null;
			   }
			    var url = "/ISMS/rst/d-delivery-plan!view.action?entity.planId="+planId+"&callbackId="+callbackId;
				$("#showDetail").attr("href",url).click();
			 }
			 /*计划表预览*/
			 function previewPlan(callbackId){
				 var id = document.getElementsByName("fileIds")[0].value;
				if(id==""){
					alertMsg.info("请选择要上传的文件!");
				}else{
    				var url = "/ISMS/rst/d-delivery-plan!preViewPlan.action?fileIds="+id+"&callbackId="+callbackId;
    				$("#showMsg").attr("href",url).click();
			 	}
				return false;
			 }
			 /*出门单预览*/
			 function previewInvoice(){
					var id = document.getElementsByName("fileIds")[0].value;
					if(id==""){
						alertMsg.info("请选择要上传的文件!");	
					}
					else
					{
						var o=$("#${_} #previewinvoiceaction");
						o.attr("href","${previewinvoiceaction}".replace(/&amp;/g,"&").replace("%60%7E%21",id));
						o.click();
					}
					return false;
			 }

			 function bigPic(palnId,planImagePath,event){
				 var url="/ISMS/rst/d-delivery-plan!listPic.action?entity.planImagePath="+planImagePath+"&entity.planId="+palnId;
				 $("#showPic").attr("href",url).click();
				 event.stopPropagation();
				 return false;
			 }
			 /**查询**/
			 function queryInvoiceAndPlan(){
				 var invoiceUserid = $("#pinvoiceUserId").val();
				 var fromWsCd = $("#pfromWsCd").val();
				 var truckNum = $("#ptruckNum").val();
				 
				 var url = "rst/d-invoice!list.action?queryEntity.invoiceUserId="+invoiceUserid+"&queryEntity.fromWsCd="+fromWsCd+"&queryEntity.truckNum="+truckNum;
				 $("#${_}").submit();	
				 $("#plan_child_form").attr("action", url).submit();
				 return false;
			 }
			 /*删除计划表*/
			function deletePlan(planId,event){
    				 event.stopPropagation();
					alertMsg.confirm("是否删除本条计划?", {
	    			okCall: function(){
        				 var url="/ISMS/rst/d-delivery-plan!deletePlan.action?entity.planId="+planId;
        				 $("#deletePlan").attr("href", url).click();
        				//$("#plan_child_form").submit();//刷新上下列表
        				 return false;
	    			}
				});
					return false;
			 }
			 
			
			 /*删除出门单*/
			function deleteInvoice(invoiceId){
				alertMsg.confirm("是否删除本条出门单?", {
	    			okCall: function(){
	    				var url="/ISMS/rst/d-invoice!deleteInvoice.action?entity.invoiceId="+invoiceId;
       				 $("#deleteInvoice").attr("href", url).click();
       				return false;
	    			}
			 });
				return false;
			 }
			 
			 function refPagePl(json){
				 var url = "rst/d-invoice!list.action";
				 $("#${_}").submit();	
				 $("#plan_child_form").attr("action", url).submit();
				 if(json.statusCode == DWZ.statusCode.error) {
		    			if(json.message && alertMsg) alertMsg.error(json.message);
		    		} else if (json.statusCode == DWZ.statusCode.timeout) {
		    			if(alertMsg) alertMsg.error(json.message || DWZ.msg("sessionTimout"), {okCall:DWZ.loadLogin});
		    			else DWZ.loadLogin();
		    		} else {
		    			if(json.warnMessage && alertMsg)
		    			{
		    				alertMsg.error(json.warnMessage);
		    			}
		    			else if(json.infoMessage && alertMsg)
		    			{
		    				alertMsg.info(json.infoMessage);
		    			}
		    			else if(json.message && alertMsg)
		    			{
	    					alertMsg.correct(json.message);	
			 			}
		    		}
			 }
			 function refPageIn(json){
				 var url = "rst/d-invoice!list.action";
				 $("#plan_child_form").attr("action", url).submit();
				 if(json.statusCode == DWZ.statusCode.error) {
		    			if(json.message && alertMsg) alertMsg.error(json.message);
		    		} else if (json.statusCode == DWZ.statusCode.timeout) {
		    			if(alertMsg) alertMsg.error(json.message || DWZ.msg("sessionTimout"), {okCall:DWZ.loadLogin});
		    			else DWZ.loadLogin();
		    		} else {
		    			if(json.warnMessage && alertMsg)
		    			{
		    				alertMsg.error(json.warnMessage);
		    			}
		    			else if(json.infoMessage && alertMsg)
		    			{
		    				alertMsg.info(json.infoMessage);
		    			}
		    			else if(json.message && alertMsg)
		    			{
	    					alertMsg.correct(json.message);	
			 			}
		    		}
			 }
			
	</script>
</body>
</html>
