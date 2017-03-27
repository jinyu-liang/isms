<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pt" uri="/is-tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="com.is.pretrst.entity.DDeliveryPlan" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>发料计划部长审批</title>
</head>
<body>
    <div class="page" style="width: 100%; height: 100%;">
        <s:property value="message" escapeHtml="false" />
        <div class="pageContent" style="width: 100%; height: 100%;">
            <s:form id="%{_}" method="post" action="d-delivery-plan!waitDisEdit" cssClass="pageForm" onsubmit="return validateCallback(this,closeAndRefresh);"
                cssStyle="width: 100%;height: 100%;">
                <div style="width: 100%; height: 100%; overflow: hidden;">
                    <s:hidden name="entity.planId" value="%{entity.planId}" />
                    <s:hidden name="entity.verifiedSiteStatus" value="%{entity.verifiedSiteStatus}" />
                    <s:hidden name="entity.toWsCd" value="%{entity.toWsCd}" />
                    <s:hidden name="entity.createUserCd" value="%{entity.createUserCd}" />
                    <table cellpadding="0" cellspacing="0" width="100%" height="100%" style="width: 100%; height: 100%;">
                        <tbody>
                            <tr>
                                <td width="500px" ><!-- width="500px" height="480px" style="width: 500px;height: 480px;" align="center" -->
                                <div style="width: 100%;height:650px; overflow:scroll; margin-top: 0px">
                                
                                   <%--  <img src="<s:property value="entity.planImagePath" />" alt="计划表图片"
                                        title="计划表图片" width="500px" height="900px" />
                                         --%>
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
                               <img src="<%=str[i]%>" alt="计划表图片" title="计划表图片" width="600px" height="900px" />
                        <%
                                }
                            }
                        %>
                                </div>
                                </td>
                                <td valign="top"><table class="list" width="100%">
                                        <tr>
                                            <td><font style="font-weight: bold;">创建用户</font></td>
                                            <td><span><pt:usernameShow userId="entity.createUserCd" /> </span></td>
                                        </tr>
                                        <tr>
                                            <td><font style="font-weight: bold;">发货请求时间</font></td>
                                            <td><span> <s:date name="entity.deliveryReqTm" format="yyyy-MM-dd HH:mm:ss" />
                                            </span></td>
                                        </tr>
                                        <tr>
                                            <td><font style="font-weight: bold;">收货工作中心</font></td>
                                            <td><span><pt:workshorpnameShow wsCd="entity.toWsCd" /> </span></td>
                                        </tr>
                                        <tr>
                                            <td><font style="font-weight: bold;">卸货地点</font></td>
                                            <td><span> <s:property value="entity.unloadPlaceNm" />
                                            </span></td>
                                        </tr>
                                        <tr>
                                            <td><font style="font-weight: bold;">审核项目经理</font></td>
                                            <td><span><pt:usernameShow userId="entity.verifiedSiteUserCd" /> </span></td>
                                        </tr>
                                        <tr>
                                            <td><font style="font-weight: bold;">项目经理审核时间</font></td>
                                            <td><span> <s:date name="entity.verifiedSiteTm" format="yyyy-MM-dd HH:mm:ss" />
                                            </span></td>
                                        </tr>
                                        <tr>
                                            <td><font style="font-weight: bold;">审核结果</font></td>
                                            <td><span>
                                            	<s:if test="entity.verifiedSiteStatus==\"3\"">
                                            	同意
                                            	</s:if>  
                                            	<s:if test="entity.verifiedSiteStatus==\"4\"">
                                            	不同意
                                            	</s:if>  
                                            </span></td>
                                        </tr>
                                        <tr>
                                            <td><font style="font-weight: bold;">项目经理审核备注</font></td>
                                            <td><span> <s:property value="entity.verifiedSiteMemo" />
                                            </span></td>
                                        </tr>
                                        <tr>
                                            <td colspan="2" style="border-top: 1px solid #000000"></td>
                                        </tr>
                                        <tr>
                                            <td><font style="font-weight: bold;">审核部长</font></td>
                                            <td><span><pt:usernameShow userId="entity.verifiedHeadUserCd" /> </span></td>
                                        </tr>
                                        <tr>
                                            <td><font style="font-weight: bold;">部长审核结果</font></td>
                                            <td><span> <s:radio id="verifiedHeadStatus" name="entity.verifiedHeadStatus" 
                                                        list="#{'3':'同意','4':'有问题'}" value="entity.verifiedHeadStatus" cssClass="required">
                                                    </s:radio></span></td>
                                        </tr>
                                        <tr>
                                            <td><font style="font-weight: bold;">部长审核意见</font></td>
                                            <td><span> <s:textarea name="entity.verifiedHeadMemo" cols="45" rows="3"
                                                        id="entity_verifiedHeadMemo" cssClass="required" maxlength="400" />
                                            </span></td>
                                        </tr>
                                    </table></td>
                            </tr>
                            <tr>
                                <td height="30px" style="height: 30px;" colspan="2"><div class="formBar">
                                        <ul>
                                        <sec:authorize ifAllGranted="发料管理-发料计划审批">
                                            <li>
                                            <s:if test="sessionUser.userId!=entity.verifiedHeadUserCd||entity.verifiedHeadStatus!=null">
                                                <div class="buttonDisabled">
                                                    <div class="buttonContent">
                                                        <button disabled="disabled">确定审批</button>
                                                    </div>
                                                </div>
                                             </s:if>
                                             <s:else>
                                             
                                                <div class="buttonActive">
                                                    <div class="buttonContent">
                                                        <button <s:if test="sessionUser.userId!=entity.verifiedHeadUserCd">disabled="disabled"</s:if>
                                                            onclick="return check()">确定审批</button>
                                                    </div>
                                                </div>
                                             </s:else>
                                            </li>
                                         </sec:authorize>
                                            <li>
                                                <div class="button">
                                                    <div class="buttonContent">
                                                        <button type="button" onclick="$.pdialog.closeCurrent();">关闭</button>
                                                    </div>
                                                </div>
                                            </li>
                                        </ul>
                                    </div></td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </s:form>
        </div>
    </div>
    <script type="text/javascript">
					function check() {
							alertMsg.confirm("确定审批该发料计划?",{
								okCall: function(){
									 var valradio = $('input:radio[name="entity.verifiedHeadStatus"]:checked').val();
									 var textarea = $("#entity_verifiedHeadMemo").val().trim();
									 if(valradio==undefined){
										alertMsg.warn("请选择同意或者不同意!");
									 }
									 if(valradio=='4'&&textarea==''){
										 alertMsg.warn("请填写不同意的意见!");
									 }else{
										$("#${_}").submit();
									 }
			        		     }
			    			});
							return false;
					};
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
