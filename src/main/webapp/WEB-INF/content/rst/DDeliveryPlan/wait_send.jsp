<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pt" uri="/is-tags"%>
<%@ page import="com.is.pretrst.entity.DDeliveryPlan" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>dDeliveryPlan查看</title>
</head>
<body>
    <div class="page" style="width: 100%; height: 100%;">
        <s:property value="message" escapeHtml="false" />
        <div class="pageContent" style="width: 100%; height: 100%;">
            <s:form method="post" action="d-delivery-plan!waitSendEdit" cssClass="pageForm" onsubmit="return validateCallback(this,closeAndRefresh);" id="%{_}waitSendEditForm"
                cssStyle="width: 100%;height: 100%;" >
                <div style="width: 100%; height: 100%; overflow: hidden;">
                    <s:hidden name="entity.planId" value="%{entity.planId}" />
                    <s:hidden name="entity.createUserCd" value="%{entity.createUserCd}" />
                    <!-- 图片左边 -->
                    <table cellpadding="0" cellspacing="0" width="100%" height="100%" style="width: 100%; height: 100%;">
                        <tbody>
                            <tr>
                                <td valign="top"><table class="list" width="100%" style="width: 100%;">
                                        <tr>
                                            <td style=" width: 15%"><font style="font-weight: bold;">审核部长用户</font></td>
                                            <td style=" width: 35%"><span><pt:usernameShow userId="entity.verifiedHeadUserCd" /> </span></td>
                                            <td style=" width: 15%"></tdstyle><font style="font-weight: bold;">部长审核时间</font></td>
                                            <td style=" width: 35%"><span> <s:date name="entity.verifiedHeadTm" format="yyyy-MM-dd HH:mm:ss" />
                                            </span></td>
                                        </tr>
                                        <tr>
                                            <td><font style="font-weight: bold;width: 25%">审核项目经理用户</font></td>
                                            <td><span><pt:usernameShow userId="entity.verifiedSiteUserCd" /> </span></td>
                                            <td><font style="font-weight: bold;">项目经理审核时间</font></td>
                                            <td><span> <s:date name="entity.verifiedSiteTm" format="yyyy-MM-dd HH:mm:ss" />
                                            </span></td>
                                        </tr>
                                        <tr>
                                            <td><font style="font-weight: bold;">其他领导</font></td>
                                            <td colspan="3">
                                                <%-- <s:select list=""></s:select> --%>
                                                <s:hidden name="userMappingIds" id="mappingids"/>
                                                <s:textfield name="mappingnames" id="mappingnames"></s:textfield>
                                                <s:a namespace="/ggkz" action="ggkz-choose-user" method="publicMultiChooseUserByPostFilter" encode="false" cssClass="add" rel="${_}" target="dialog" maxable="false" width="850" minable="false" resizable="false" mask="true">
                                                        <s:param name="callbackId" value="%{_}"></s:param>
                                                        <s:param name="checkid" value="'mappingids'"></s:param>
                                                        <s:param name="checkname" value="'mappingnames'"></s:param>
                                                        <s:param name="queryEntity.postName" value="'部长'"></s:param>
                                                        <s:param name="previewids" value="'%{userMappingIds}'" ></s:param> 
                                                        <s:param name="queryEntity.pageSize" value="1000" ></s:param>
                                                        <span>选择</span>
                                                </s:a>
                                            </td>
                                        </tr>
                                    </table></td>
                            </tr>
                            <tr>
                                <td ><!-- align="center" valign="middle" width="100%" height="400px" style="width: 100%; height: 400px; overflow: visible;" -->
                                    <div style="width: 100%;height:600px; overflow:scroll;border:solid 1px #eef4f5; ">
                                        <%-- <img src="<s:property value="entity.planImagePath" />" alt="计划表图片" title="计划表图片" width="870px" height="1250px" /> --%>
                                        
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
                                               <img src="<%=str[i]%>" alt="计划表图片" title="计划表图片"width="1190px" height="1684px" />
                                        <%
                                                }
                                            }
                                        %>
                                    </div>
                                 </td>
                            </tr>
                            <tr>
                                <td height="30px" style="height: 30px;"><div class="formBar">
                                        <ul>
                                            <li>
                                            <s:if test="sessionUser.userId!=entity.createUserCd||entity.statusCd!=1">
                                                <div class="buttonDisabled">
                                                    <div class="buttonContent">
                                                        <button  disabled="disabled">确定下发</button>
                                                    </div>
                                                </div>
                                            </s:if>
                                            <s:else>
                                                <div class="button">
                                                   <div class="buttonContent">
                                                        <%--  <s:submit type="button" value="确定下发" /> --%>
                                                        <button onclick="return checkWaitSend()" >确定下发</button>
                                                    </div>
                                                </div>
                                             </s:else>
                                            </li>
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
                    function checkWaitSend()
                    {
                    	var ids = $("#mappingids").val();
                    	if(ids==""||ids==null){
                    		alertMsg.confirm("未选择其他领导，是否下发计划表?", {
                    			okCall: function(){
                    				$("#${_}waitSendEditForm").submit();
                    			}
                    		});
                		}else{
                			alertMsg.confirm("是否下发计划表?", {
                    			okCall: function(){
                    				$("#${_}waitSendEditForm").submit();
                    			}
                    		});
                		}
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
