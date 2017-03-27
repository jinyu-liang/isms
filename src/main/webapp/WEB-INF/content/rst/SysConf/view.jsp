<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pt" uri="/is-tags"%>
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
                            <td><label>报告Id</label></td>
                            <td><span> <s:property value="entity.reportId" />
                            </span></td>
                        </tr>
                        <tr>
                            <td><label>工作中心</label></td>
                            <td><span> 
                            <s:property value="entity.workCenterId" />
                            </span></td>
                        </tr>
                        <tr>
                            <td><label>标题</label></td>
                            <td><span> <s:property value="entity.title" />
                            </span></td>
                        </tr>
                        <tr>
                            <td><label>报告人</label></td>
                            <td><span> <s:property value="entity.reportUserName" />
                            </span></td>
                        </tr>
                        <tr>
                            <td><label>报告时间</label></td>
                            <td><span> <s:property value="entity.reportTm" />
                            </span></td>
                        </tr>
                        <tr>
                            <td><label>备注</label></td>
                            <td><span> <s:property value="entity.memo" />
                            </span></td>
                        </tr>
                        <tr>
                            <td><label>处理人</label></td>
                            <td><span>
                            <pt:usernameShow userId="entity.processUserCd"></pt:usernameShow>
                            
                            </span></td>
                        </tr>
                        <tr>
                            <td><label>处理时间</label></td>
                            <td><span> <s:property value="entity.processTm" />



                            </span></td>
                        </tr>
                        <tr>
                            <td><label>处理意见</label></td>
                            <td><span> <s:property value="entity.dealComment" />
                            </span></td>
                        </tr>
                        <tr>
                            <td><label>状态</label></td>
                            <td><span> 
                            <s:if test="entity.statusCd==1">
							<font style="color: green;">已处理</font></s:if>
							<s:if test="entity.statusCd==0">
							<font style="color:#6495ED;">待处理</font></s:if>
							<s:if test="entity.statusCd==2">
							<font style="color: red;">有问题</font></s:if>													
							</td>
                            
                            </span></td>
                        </tr>
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
