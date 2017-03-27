<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="com.is.pretrst.entity.DInvoice" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>dDeliveryPlan查看</title>
</head>
<body>
    <div class="page">
        <s:property value="message" escapeHtml="false" />
        <div class="pageContent">
            <s:form id="form1" method="post" action="d-invoice!uploadInvoice" cssClass="pageForm" onsubmit="return validateCallback(this,closeAndRefresh);">
                <div  >
                    <!-- 图片左边 -->
                    <div style="float:top ">
                        <s:hidden   value="%{entity.invoiceId}" name="entity.invoiceId" id="invoiceId"/> 
                        <s:hidden   value="%{entity.planId}" name="entity.planId" id="planId"/> 
                        <s:hidden name="entity.sellOrderCode" value="%{entity.sellOrderCode}"/>
                        <s:hidden name="entity.title" value="%{entity.title}"/>
                        <s:hidden name="entity.tcompanyNm" value="%{entity.tcompanyNm}"/>
                        <s:hidden name="entity.tcompanyTel" value="%{entity.tcompanyTel}"/>
                        <s:hidden name="entity.driver" value="%{entity.driver}"/>
                        <s:hidden name="entity.truckNum" value="%{entity.truckNum}"/>
                        <s:hidden name="entity.driverTel" value="%{entity.driverTel}"/>
                        <s:hidden name="entity.fromWsCd" value="%{entity.fromWsCd}" id="fromWsCd"/>
                        <s:hidden name="entity.toWsCd" value="%{entity.toWsCd}" id="toWsCd"/>
                        <s:hidden name="entity.toWsNm" value="%{entity.toWsNm}" id="toWsNm"/>
                        <s:hidden name="entity.invoiceUserId" value="%{entity.invoiceUserId}"id="invoiceUserId"/>
                        <s:hidden name="entity.approvalUserCd" value="%{entity.approvalUserCd}"id="approvalUserCd"/>
                        <s:hidden name="planWsCd" value="%{planWsCd}" id="planWsCd"/>
                        <div style="display:none">
                            <input type="text"  id="deliveryTm" name="entity.deliveryTm" value="<s:date name="entity.deliveryTm" format="yyyy-mm-dd" />"/>
                        </div>
                        <s:hidden name="entity.dinvoiceImagePath" value="%{entity.dinvoiceImagePath}"/>
                    </div>
                    <div style="width: 100%;height:720px; overflow:scroll">
                       <%--  <img src="<s:property value="entity.dinvoiceImagePath" />" alt="" title="出门单预览" width="760px" height="1200px" /> --%>
                   <!-- 把planImagePath用，号分隔显示  -->
                        <%
                            DInvoice invoice = new DInvoice();
                            invoice = (DInvoice)(request.getAttribute("entity"));
                            invoice = (DInvoice)(request.getAttribute("entity")==null?invoice:request.getAttribute("entity"));
                            String  imgPath = invoice.getDinvoiceImagePath();
                            if(imgPath!=null&&!"".equals(imgPath)){
                                String []str = imgPath.split(",");
                                for(int i=0;i<str.length;i++){
                        %>
                                <img src="<%=str[i] %>" alt="" title="出门单详单" width="1190px" height="1684px" />
                        <%
                                }
                            }
                        %>
                   </div> 
                    
                    <div class="formBar">
                        <ul>
                            <li>
                                <div class="buttonActive">
                                    <div class="buttonContent">
                                       <%--  <s:submit type="button" value="确定下发" /> --%>
                                       <button type="button" onclick="upload()" >确定上传</button>
                                    </div>
                                </div>
                            </li>
                            <li>
                                <div class="button">
                                    <div class="buttonContent">
                                        <button type="button" onclick="$.pdialog.closeCurrent();">关闭</button>
                                    </div>
                                </div>
                            </li>
                        </ul>
                    </div>
                </div>
            </s:form>
        </div>
    </div>
        <script type="text/javascript">
        function upload(){
        	alertMsg.confirm("是否上传该出门单?", {
    			okCall: function(){
    				var invoiceUserId = $("#invoiceUserId").val();
    				var approvalUserCd = $("#approvalUserCd").val();
                	var planId = $("#planId").val();
                	var toWsCd = $("#toWsCd").val();
                	var toWsNm = $("#toWsNm").val();
                	var planWsCd = $("#planWsCd").val();
                	var deliveryTm = $("#deliveryTm").val();
                	var fromWsCd = $("#fromWsCd").val();
                	if(planWsCd!=toWsCd){/*所选择的计划收货工地与出门单中的收货工地是否一致 */
                		alertMsg.error("由您上传的'"+deliveryTm+"'发往'"+toWsNm+"'的出门单与发料计划的收货工作中心不一致，无法上传。");
          				return false;
          			}
                	if(fromWsCd==""){/*制表人有没有负责的仓库 */
                		alertMsg.warn("没有找到制表人负责的仓库，请在工地管理中添加制表人负责的仓库后上传。");
          				return false;
          			}
                	$.ajax({
        		  		  url:'/ISMS/rst/d-invoice!isExist.action',
        		  		  type:'post',
        		  		  async: 'false',
        		  		  data:{'entity.invoiceUserId':invoiceUserId,'entity.approvalUserCd':approvalUserCd,'entity.toWsCd':toWsCd,'entity.deliveryTm':deliveryTm,'planWsCd':planWsCd,'entity.planId':planId},
        		  		  dataType:'json',
        		      		  complete:function(data){
        		      			var s = data.responseText;
        		      			if(s==1){
        		      				isExist(deliveryTm,toWsNm);/*如果存在重复的*/
        		      			}else if(s==2){
        		      				issueed(deliveryTm,toWsNm);
        		      			}else {
        		      				$("#form1").submit();
        		      			}
        		      			return false;
        		      	}
        		     });
            	}
    			});
             }
		/*重复时提示*/
		function isExist(deliveryTm,toWsNm){
			alertMsg.confirm("您上传的'"+deliveryTm+"'发往'"+toWsNm+"'的出门单已经存在，是否覆盖?",{
				okCall: function(){
					$("#form1").attr("action","/ISMS/rst/d-invoice!reUpload.action").submit();
    				}
				});
		}
		/*重复并已经审批了的*/
		function issueed(deliveryTm,toWsNm){
			alertMsg.error("由您上传的"+deliveryTm+"发往"+toWsNm+"的出门单已经存在，并且已批准，无法上传.");
		}
		function closeAndRefresh(json){
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
    				var $pagerForm = $("#plan_child_form", navTab.getCurrentPanel()).get(0)||$("form", navTab.getCurrentPanel()).get(0);
    				divSearch($pagerForm,"plan_child_plan");
    				$.pdialog.closeCurrent();
    			}
    		}
        }
		 //清除session中的产品集合
        function removeSession(){
     	   var invoiceId = $("#invoiceId").val();
     	   $.ajax({
 		  		  url:'/ISMS/rst/d-invoice!removeSession.action',
 		  		  type:'post',
 		  		  async: 'true',
 		  		  data:{'entity.invoiceId':invoiceId},
 		  		  dataType:'json',
 		      		  complete:function(data){
 		      	}
 		     });
        }
    </script>
</body>
</html>
