<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pt" uri="/is-tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page import="com.is.pretrst.entity.DDeliveryPlan" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>发料计划表回复</title>
</head>
<body>
    <div class="page" style="width: 100%; height: 100%;">
        <s:property value="message" escapeHtml="false" />
        <div class="pageContent" style="width: 100%; height: 100%;">
            <s:form method="post" action="d-delivery-plan!edit" cssClass="pageForm" target="ajax" id="%{_}"
                onsubmit="return validateCallback(this,closeAndRefresh);" cssStyle="width: 100%;height: 100%;">
                <div style="width: 100%; height: 100%; overflow: hidden;">
                    <s:hidden name="entity.planId" value="%{entity.planId}" />
                    <table cellpadding="0" cellspacing="0" width="100%" height="100%" style="width: 100%; height: 100%;">
                        <tbody>
                            <tr>
                                <td width="500px" ><!-- height="474px" style="width: 500px; height: 474px; overflow: auto;" align="center" -->
                                    <div style="width: 100%;height:500px; overflow:scroll">
                                        <%-- <img src="<s:property value="entity.planImagePath" />" alt="发料计划表" title="发料计划表" width="500px" height="800px" /> --%>
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
                                    <table class="list" width="100%">
                                        <tr>
                                            <td><font style="font-weight: bold;">状态</font></td>
                                            <td><span> <s:if test="entity.statusCd==3">
                                                        <font style="color: green; size: 30">已下发</font>
                                                    </s:if> </span>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td><font style="font-weight: bold;">上传者</font></td>
                                            <td><span><pt:usernameShow userId="entity.createUserCd" />
                                             </span>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td><font style="font-weight: bold;">审核部长</font></td>
                                            <td><span><pt:usernameShow userId="entity.verifiedHeadUserCd" /> </span>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td><font style="font-weight: bold;">审核项目经理</font></td>
                                            <td><span><pt:usernameShow userId="entity.verifiedSiteUserCd" /> </span>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td><font style="font-weight: bold;">上传时间</font></td>
                                            <td><span> <s:date name="entity.createTm" format="yyyy-MM-dd" /> </span>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td><font style="font-weight: bold;">部长批准时间</font></td>
                                            <td><span> <s:date name="entity.verifiedHeadTm" format="yyyy-MM-dd HH:mm:ss" /> </span>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td><font style="font-weight: bold;">项目经理批准时间</font></td>
                                            <td><span> <s:date name="entity.verifiedSiteTm" format="yyyy-MM-dd HH:mm:ss" /> </span>
                                            </td>
                                        </tr>
                                    </table></td>
                                    <td width="20px"></td>
                                <td height="474px" style="height: 474px;">
                                    <div id="${_}resp" style="overflow: auto; height: 100%; width: 100%;">
                                        <div>
                                            <div>
                                                <br /> <span><font style="font-size: 12pt;font-weight: bolder;">我的回复</font>  <s:hidden name="record.replyId"
                                                        value="%{record.replyId}" /> </span> 
                                            </div>
                                            <br />
                                            <div>
                                                <label>上次回复时间:</label> <span> <s:date name="record.updateTm" format="yyyy-MM-dd  HH:mm:ss" />
                                                </span>

                                            </div>
                                            <br />
                                            <div>
                                                <label>标题:</label> <span> <s:textfield name="record.title" id="record_title"
                                                        maxlength="80" /> </span>
                                            </div>
                                            <br />
                                            <div>
                                                <label>内容:</label> <span> <s:textarea name="record.content" cols="45" rows="3"
                                                        id="record_content" maxlength="400" /> </span>

                                            </div>
                                        </div>
                                        <div style="border-top: 1px solid #000000; margin-top: 12px; margin-bottom: 12px;"></div>
                                        <!-- 全部回复 -->
                                        <div >
                                            <label><font style="font-size: 12pt;font-weight: bolder;">全部回复</font></label> <br /><br />
                                            <s:iterator value="recordPage.data">
                                                <div>
                                                    <span>回复者：<pt:usernameShow userId="replyUserCd" /> </span>
                                                </div>
                                                <div>
                                                    <span>更新时间：<s:date name="updateTm" format="yyyy-MM-dd  HH:mm:ss" /> </span>
                                                </div>
                                                <div>
                                                    <span>标题：<s:property value="title" /> </span>
                                                </div>
                                                <div>
                                                    <span>内容：<s:property value="content" /> </span>
                                                </div>
                                                <div style="border-top: 1px dashed #000000; margin-top: 12px; margin-bottom: 12px;"></div>
                                            </s:iterator>
                                        </div>
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td height="30px" style="height: 30px;" colspan="3">
                                    <div class="formBar">
                                        <ul>
                                         <sec:authorize ifAllGranted="发料管理-计划表回复">
                                                <s:if test="isReply==\"1\"">
                                                			<li>
                                                                <div class="buttonActive">
                                                                    <div class="buttonContent">
                                                                        <s:submit type="button" value="发表回复" />
                                                                    </div>
                                                                </div>
                                                             </li>
                                                
                                                </s:if>
                                                <s:else>
                                                		<li>
	                                                        <div class="buttonDisabled">
	                                                        <div class="buttonContent">
	                                                            <button  disabled="disabled">发表回复</button>
	                                                        </div>
	                                                    	</div>
	                                                    </li>
                                                </s:else>
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
            <s:form method="post" action="d-delivery-plan!reply" cssClass="pageForm" target="ajax" id="%{_}reply"
                onsubmit="return validateCallback(this,closeAndRefresh);" >
                <s:hidden name="queryRecord.recordId" id="%{_}recordId"></s:hidden>
            </s:form>
        </div>
    </div>
    <script type="text/javascript">
					function closeAndRefresh(json) {
						if (json.statusCode == DWZ.statusCode.error) {
							if (json.message && alertMsg)
								alertMsg.error(json.message);
						} else if (json.statusCode == DWZ.statusCode.timeout) {
							if (alertMsg)
								alertMsg.error(json.message
										|| DWZ.msg("sessionTimout"), {
									okCall : DWZ.loadLogin
								});
							else
								DWZ.loadLogin();
						} else {
							if (json.warnMessage && alertMsg) {
								alertMsg.error(json.warnMessage);
							} else if (json.infoMessage && alertMsg) {
								alertMsg.info(json.infoMessage);
							} else if (json.message && alertMsg) {
								alertMsg.correct(json.message);
								$("#${_}recordId").val(json.jsondata);
								var $pagerForm = $("#${_}reply").get(0);
								divSearch($pagerForm, "${_}resp");
							}
							//queryRecord.setRecordId
						}
					}
				</script>
</body>
</html>
