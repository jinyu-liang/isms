<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>上传大小控制表列表</title>
    </head>
    <body>
		<s:property value="message" escapeHtml="false"/>
		<s:form method="post" action="rstUploadConf!list" cssStyle="height:100%;width:100%;" onsubmit="return pageSubmit(this);">
				<div class="page" style="height:100%;width:100%;">
						<div class="layout">
								<div region="north" height="95">
										<div class="pageHeader">
												<div class="searchBar">
														<ul class="searchContent">
																				<li>
																						
																						
																						<label for="entity_fileType">文件类型</label>
																							<s:textfield id="queryEntity_fileType" name="queryEntity.fileType" maxLength="10"/>
																							
																				</li>
																				<li>
																						
																						
																						<label for="entity_sizeConf">文件大小设置</label>
																							<s:textfield id="queryEntity_sizeConf" name="queryEntity.sizeConf" />
																							
																				</li>
																				<li>
																						
																						
																						<label for="entity_statusCd">状态</label>
																							<s:textfield id="queryEntity_statusCd" name="queryEntity.statusCd" maxLength="1"/>
																							
																				</li>
																				<li>
																						
																						
																						<label for="entity_operUserId">操作用户Id</label>
																							<s:textfield id="queryEntity_operUserId" name="queryEntity.operUserId" maxLength="32"/>
																							
																				</li>
																				<li>
																						
																						
																						<label for="entity_operUserName">操作用户名称</label>
																							<s:textfield id="queryEntity_operUserName" name="queryEntity.operUserName" maxLength="32"/>
																							
																				</li>
																				<li>
																						
																						
																						<label for="entity_operTime">操作时间</label>
																							<s:textfield id="queryEntity_operTime" name="queryEntity.operTime" cssClass="date" />
																							
																				</li>
														 </ul>					
														 <div class="subBar">
																<ul>
																		<li>
																			<div class="buttonActive">
																				<div class="buttonContent">
																					<s:submit type="button">检索</s:submit>
																				</div>
																			</div>
																		</li>
																</ul>
														</div>
												</div>
										</div>
										
										<div class="pageContent">
												<div class="panelBar">
														<div>
														
												    </div>
												
													<ul class="toolBar">
														<li>
															<s:a encode="false" width="960" height="550" cssClass="add" target="dialog" mask="true">
																<s:param name="callbackId" value="%{dwz_page_rel}"></s:param>
																<span>添加</span>
															</s:a>
														</li>
														<li>
															<s:a encode="false" width="960" height="550" cssClass="add" target="dialog" mask="true">
																<s:param name="callbackId" value="%{dwz_page_rel}"></s:param>
																<span>修改</span>
															</s:a>
														</li>
														<li>
															<s:a cssClass="delete" callback="refreshCurrentPageContainer(this)" target="navTabTodo">
																<s:param name="callbackId" value="%{dwz_page_rel}"></s:param>
															<span>删除</span>
															
															</s:a>
														</li>
														<li>
															<s:a encode="false" width="960" height="550" cssClass="add" target="dialog" mask="true">
																	
																	 <span>查看</span>
															</s:a>
														</li>
														<li class="line">line</li>
													</ul>
												</div>
										</div>
								</div>		
										
										
								<div region="center">
									
									<table class="table">
										<thead>
											<tr>
												<th>文件类型</th>
												<th>文件大小设置</th>
												<th>状态</th>
												<th>操作用户Id</th>
												<th>操作用户名称</th>
												<th>操作时间</th>
											</tr>
										</thead>
										<tbody>
											<s:iterator value="page.data">
												<tr key="entity.fileType" value='<s:property value="fileType" />'>
													<td><s:property value="fileType" /></td>
													<td><s:property value="sizeConf" /></td>
													<td><s:property value="statusCd" /></td>
													<td><s:property value="operUserId" /></td>
													<td><s:property value="operUserName" /></td>
													<td><s:date name="operTime" format="yyyy-MM-dd" /></td>
												</tr>
											</s:iterator>
										</tbody>
									</table>
								</div>
								<div region="south" height="26">
										<div class="panelBar">
												<div class="pages">
													<span>显示</span>
													<s:select list="page.pageSizeList" name="queryEntity.pageSize" cssClass="pageSize" />
													<span>条，共[<s:property value="page.count"/>]条</span>
												</div>
												<s:hidden name="queryEntity.pageNumber" id="pageNumber" />
												<div class="pagination" count='<s:property value="page.count"/>' pageSize='<s:property value="page.pageSize"/>' pageNumShown='<s:property value="page.pageNumShown"/>' pageNumber='<s:property value="page.pageNumber"/>'></div>
										</div>
							</div>
                            </div>
                        </div>
					</s:form>
		</body>
</html>
