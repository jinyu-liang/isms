<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
	<div>选择权限:</div>
							<table  class="table">
								<thead>
									<tr>
										<th width="80px"><input type="checkbox" name="checkboxAll" id="checkboxAll" title="全选" value=""/>全选</th>
										<th>权限名称</th>
										<th>权限简称</th>
										<th>备注</th>
									</tr>
								</thead>
								<tbody>
									<s:iterator value="ggkzAuthInfoList" id="ggkzAuthInfo" status='st'>
										
										 <tr <s:if test="#st.Odd">style="background-color:#F8F8F8;"</s:if>>
											<td>
												<input width="56px" type="checkbox" name="checkbox" id="checkbox" value="${ggkzAuthInfo.authId}"
												<s:iterator value="ggkzAuthInfoListChecked" id="ggkzAuthInfoChecked">
													<s:if test="#ggkzAuthInfo.authId==authId">checked="checked"</s:if>
												</s:iterator> />
											</td>
											<td><s:property value="#ggkzAuthInfo.authName" /></td>
											<td><s:property value="#ggkzAuthInfo.shortName" /></td>
											<td><s:property value="#ggkzAuthInfo.note" /></td>
										</tr>
									</s:iterator>
								</tbody>
							</table>
	

