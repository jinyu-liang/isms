<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>dExItem列表</title>
    </head>
    <body>
		<s:property value="message" escapeHtml="false"/>
		<s:form method="post" action="dExItem!list" cssStyle="height:100%;width:100%;" onsubmit="return navTabSearch(this);" id="%{_}">
				<div class="page" style="height:100%;width:100%;">
						<div class="layout">
								<div region="north" height="95">
										<div class="pageContent">
										</div>
								</div>		
										
										
								<div region="center">
									
									<table class="table">
										<thead>
											<tr>
												<th>设备名称</th>
												<th>数量</th>
												<th>备注</th>
											</tr>
										</thead>
										<tbody>
											<s:iterator value="page.data">
												<tr key="entity.itemId" value='<s:property value="itemId" />'>
													<td><s:property value="itemNm" /></td>
													<td><s:property value="amount" /></td>
													<td><s:property value="memo" /></td>
												</tr>
											</s:iterator>
										</tbody>
									</table>
								</div>
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
					</s:form>
		</body>
</html>
