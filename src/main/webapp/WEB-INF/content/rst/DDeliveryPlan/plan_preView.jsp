<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="com.is.pretrst.entity.DDeliveryPlan" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>dDeliveryPlan查看</title>
<style>
  /**去除jquery ui close按钮**/
  .my-dialog .ui-dialog-titlebar-close{
    display: none;
  }
 </style>
</head>
<body>
    <div class="page">
        <s:property value="message" escapeHtml="false" />
        <div class="pageContent">
            <s:form id="%{_}" method="post" action="d-delivery-plan!uploadPlan" cssClass="pageForm" onsubmit="return validateCallback(this,closeAndRefresh);">
                <div>
                    <!-- 图片左边 -->
                    <div style="float:top ">
                        <s:hidden   value="%{entity.planId}" name="entity.planId" id="planId"/> 
                        <s:hidden name="entity.sellOrderCode" value="%{entity.sellOrderCode}"/>
                        <s:hidden name="entity.title" value="%{entity.title}"/>
                        <s:hidden name="entity.toWsCd" value="%{entity.toWsCd}" id="toWsCd"/>
                        <s:hidden name="entity.toWsNm" value="%{entity.toWsNm}" id="toWsNm"/>
                        <s:hidden name="entity.unloadPlaceNm" value="%{entity.unloadPlaceNm}"/>
                        <%-- <s:date name="entity.deliveryPlanTm" format="yyyy-MM-dd" id="deliveryPlanTm"/> --%><!--  value="%{entity.deliveryPlanTm}" -->
                        <%-- <s:hidden type="hidden" name="entity.deliveryPlanTm" id="deliveryPlanTm"/> --%>
                        <div style="display:none">
                            <input type="text"  id="deliveryPlanTm" name="entity.deliveryPlanTm" value="<s:date name="entity.deliveryPlanTm" format="yyyy-mm-dd" />"/>
                        </div>
                        <s:hidden name="entity.verifiedSiteUserCd" value="%{entity.verifiedSiteUserCd}"/>
                        <s:hidden name="entity.deliveryReqTm" value="%{entity.deliveryReqTm}"/>
                        <s:hidden name="entity.planFilePath" value="%{entity.planFilePath}"/>
                        <s:hidden name="entity.planImagePath" value="%{entity.planImagePath}"/>
                        <s:hidden name="entity.createUserCd" value="%{entity.createUserCd}" id="createUserCd"/>
                        <div style="display:none">
                            <input type="text"   name="entity.createTm" value="<s:date name="entity.createTm" />"/>
                        </div>
                <%--     <s:hidden name="entity.ddeliveryItemList" value="%{entity.ddeliveryItemList}"/> --%>
                        
                        <table class="list">
                            <tr>
                                <td><font style="font-weight: bold;">审核部长</font></td>
                                <td><span>
                                <s:hidden  name="entity.verifiedHeadUserCd" id="userIds"/>
                                <s:textfield name="mynamebz" id="userNames" cssClass="required" readonly="true"></s:textfield>
                                <s:a namespace="/ggkz" action="ggkz-choose-user" method="publicSingleChooseUserByPostFilter" encode="false" cssClass="add" target="dialog" rel="${_}" maxable="false" width="850" height="600" minable="false" resizable="false" mask="true">
                                        <s:param name="callbackId" value="%{_}"></s:param>
                                        <s:param name="checkid" value="'userIds'"></s:param>
                                        <s:param name="checkname" value="'userNames'"></s:param>
                                        <s:param name="queryEntity.postName" value="'部长'"></s:param>
                                        <s:param name="previewids" value="'1,2'" ></s:param>
                                        <s:param name="queryEntity.pageSize" value="1000" ></s:param>
                                        <span>选择</span>
                                    </s:a>


                                </span><!-- verifiedHeadUserCd -->
                                </td>
                            </tr>
                            <tr>
                             <td><font style="font-weight: bold;">审核项目经理</font></td>
                                <td><span>&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="entity.verifiedSiteUserNm"/> </span>
                                </td>
                            </tr>
                            <tr>
                                <td><font style="font-weight: bold;">备注</font></td>
                                <td colspan="3"> <s:textarea name="entity.memo" cols="65" rows="3" id="entity_memo"
                                        maxlength="400" /> </td>
                            </tr>
                        </table>
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
                                        <button type="button" onclick="$.pdialog.closeCurrent();">关闭</button><!-- $.pdialog.closeCurrent(); -->
                                    </div>
                                </div>
                            </li>
                        </ul>
                    </div>
                    <div style="width: 100%;height:600px; overflow:scroll;border:solid 1px #eef4f5; ">
                       <%--  <img src="<s:property value="entity.planImagePath" />" alt="" title="发货计划" width="895px" height="1250px" /> --%>
                        
                        <!-- 把planImagePath用，号分隔显示  -->
                        <%
                            DDeliveryPlan plan = new DDeliveryPlan();
                            plan = (DDeliveryPlan)(request.getAttribute("entity"));
                            plan = (DDeliveryPlan)(request.getAttribute("entity")==null?plan:request.getAttribute("entity"));
                            String  imgPath = plan.getPlanImagePath();
                            if(imgPath!=null&&!"".equals(imgPath)){
                                String []str = imgPath.split(",");
                                for(int i=0;i<str.length;i++){
                        %>
                                <img src="<%=str[i] %>" alt="" title="发货计划" width="1190px" height="1684px" />
                        <%
                                }
                            }
                        %>
                   </div> 
                    
                    

                </div>
            </s:form>
        </div>
    </div>
        <script type="text/javascript">
        //清除session中的产品集合
        function removeSession(){
     	   var planId = $("#planId").val();
     	   $.ajax({
 		  		  url:'/ISMS/rst/d-delivery-plan!removeSession.action',
 		  		  type:'post',
 		  		  async: 'true',
 		  		  data:{'entity.planId':planId},
 		  		  dataType:'json',
 		      		  complete:function(data){
 		      	}
 		     });
        }
        function upload(){
        	if( $("#userIds").val()=="" || $("#userIds").val()==null){
        		alertMsg.warn("请选择审批部长");
        		return false;
        	}
        	alertMsg.confirm("是否上传该计划表?", {
    			okCall: function(){
    				var createUserCd = $("#createUserCd").val();
                	var planId = $("#planId").val();
                	var toWsCd = $("#toWsCd").val();
                	var toWsNm = $("#toWsNm").val();
                	var deliveryPlanTm = $("#deliveryPlanTm").val();
                	
                	$.ajax({
        		  		  url:'/ISMS/rst/d-delivery-plan!isExist.action',
        		  		  type:'post',
        		  		  async: 'false',
        		  		  data:{'entity.createUserCd':createUserCd,'entity.planId':planId,'entity.toWsCd':toWsCd,'entity.deliveryPlanTm':deliveryPlanTm},
        		  		  dataType:'json',
        		      		  complete:function(data){
        		      			var s = data.responseText;
        		      			if(s==1){
        		      				isExist(deliveryPlanTm,toWsNm);//如果存在重复的
        		      			}else if(s==2){
        		      				Issueed(deliveryPlanTm,toWsNm);//重复并已经下发了
        		      			}else if(s==3){
        		      				numberIssueed(planId);//没有重复但发运计划编号已经使用了
        		      			}else{
        		      				$("#${_}").submit();
        		      			}
        		      			return false;
        		      	}
        		     });
    			}
    		});
        }
		/*重复时提示*/
		function isExist(deliveryPlanTm,toWsNm){
			alertMsg.confirm("您上传的"+deliveryPlanTm+"发往"+toWsNm+"的计划表已经存在，是否覆盖?", {
    			okCall: function(){
    				$("#${_}").attr("action","/ISMS/rst/d-delivery-plan!reUpload.action").submit();
    			}
    		});
		}
		/*重复并已经下发了*/
		function Issueed(deliveryPlanTm,toWsNm){
			alertMsg.error("由您上传的"+deliveryPlanTm+"发往"+toWsNm+"的计划表已经存在，但状态并不是已审批，无法上传.");
			
		}
		/*发运计划编号已经使用了*/
		function numberIssueed(planId){
			alertMsg.error("发运计划编号"+planId+"已经被其他计划表使用,请修改.");
			
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
    				var $pagerForm = $("#pagerForm", navTab.getCurrentPanel()).get(0)||$("form", navTab.getCurrentPanel()).get(0);
    				divSearch($pagerForm,"plan_main_plan");
    				$.pdialog.closeCurrent();
    			}
    		}
        }
    </script>
</body>
</html>
