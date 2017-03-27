<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pt" uri="/is-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>外线人员管理表查看</title>
</head>
<body>
    <div class="page">
        <s:property value="message" escapeHtml="false" />
        <div class="pageContent">
            <s:form method="post" action="ex-person-manager!toEdit" cssClass="pageForm" onsubmit="return dialogSearch(this);">
                <div>
                    <s:hidden name="entity.reportId" value="%{entity.reportId}" />
                    <table class="list" width="100%">
                    
                         <tr>
                            <td class="abc" colspan="4" align="center"><label><font color="#3A5FCD">图片</font></label></td>
                          
                         </tr>  
					      <tr >
									<td class="abc" colspan="4"><label>外线人员查看图片</label><s:if test="fileName.size()>0">
										<s:a id="%{_}showMsg" namespace="/rst"
											action="ex-person-manager" method="imgView" encode="false"
											 target="dialog" mask="true" width="1000"
											height="800" title="外线人员管理图片">
											<s:param name="queryEntity.reportId" value="reportId" />
											<s:param name="callbackId" value="%{_}"></s:param>
											<span>查看图片</span>
										</s:a>
									</s:if></td>
						</tr>
                    
                        <tr>
                            <td class="abc" colspan="4" align="center"><label><font color="#3A5FCD">汇 报 情 况</font></label></td>
                          
                         </tr>
                        <tr>
                            <td class="abc"><label>标题：</label></td>
                            <td><span> <s:property value="entity.title" />
                            </span></td>
                            <td class="abc"><label>工作中心：</label></td>
                            <td><span> 
                            <s:property value="entity.workCenterId" />
                            </span></td>
                         </tr>
                        <tr>
                            <td class="abc"><label>报告人：</label></td>
                            <td><span> <s:property value="entity.reportUserName" />
                            </span></td>
                            <td class="abc"><label>报告时间：</label></td>
                            <td><span> <s:date name="entity.reportTm"  format="yyyy-MM-dd HH:mm:ss" />
                            </span></td>
                        </tr>
                        <tr>
                            <td class="abc"><label>备注：</label></td>
                            <td colspan="3"><span> <s:property value="entity.memo" />
                            </span></td>
                        </tr>
                         <tr>
                            <td class="abc" colspan="4" align="center"><label><font color="#3A5FCD">处 理 情 况</font></label></td>
                          
                         </tr>
                        <tr>
                            <td class="abc"><label>处理人：</label></td>
                            <td><span>
                            <pt:usernameShow userId="entity.processUserCd"></pt:usernameShow>
                            
                            </span></td>
                            <td class="abc"><label>处理时间：</label></td>
                            <td><span> <s:date name="entity.processTm"  format="yyyy-MM-dd HH:mm:ss" />
                            </span></td>
                        </tr>
                        <tr> <td class="abc"><label>状态：</label></td>
                            <td >
                            <s:if test="entity.statusCd==\"Yes\"">
							<font style="color: green;">已处理</font></s:if>
							<s:if test="entity.statusCd==\"0\"">
							<font style="color:#6495ED;">待处理</font></s:if>
							<s:if test="entity.statusCd==\"No\"">
							<font style="color: red;">有问题</font></s:if>													
							</td>
                            <td class="abc"><label>处理意见：</label></td>
                            <td ><span> <s:property value="entity.dealComment" />
                            </span></td>
                        </tr>
                         <tr>
                            <td class="abc" colspan="4" align="center"><label><font color="#3A5FCD">明细情况</font></label></td>
                          
                         </tr>
	                    <div style="width: 100%;float: none;overflow: auto;">
							<jsp:include page="view_edit_deal_info.jsp"></jsp:include>
						</div>
                    </table>

                    
                    <div class="formBar">
                        <ul>
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
 
</body>

</html>
