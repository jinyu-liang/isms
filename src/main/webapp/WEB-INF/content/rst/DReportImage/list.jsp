<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>dReportImage列表</title>
    </head>
    <body>
		<s:property value="message" escapeHtml="false"/>
		<s:form method="post" action="dReportImage!list" cssStyle="height:100%;width:100%;" onsubmit="return navTabSearch(this);" id="%{_}">
				<div class="page" style="height:100%;width:100%;">
						<div class="layout">
								<div region="north" height="95">
										<div class="pageHeader">
												<div class="searchBar">
														<ul class="searchContent">
																				<li>
																						
																						
																						<label for="entity_photoId">照片Id</label>
																							<s:textfield id="queryEntity_photoId" name="queryEntity.photoId" />
																							
																				</li>
																				<li>
																						
																						
																						<label for="entity_invoiceId">发货单ID</label>
																							<s:textfield id="queryEntity_invoiceId" name="queryEntity.invoiceId" maxLength="32"/>
																							
																				</li>
																				<li>
																						
																						
																						<label for="entity_fileName">文件名</label>
																							<s:textfield id="queryEntity_fileName" name="queryEntity.fileName" maxLength="80"/>
																							
																				</li>
																				<li>
																						
																						
																						<label for="entity_posLongitude">经纬位置</label>
																							<s:textfield id="queryEntity_posLongitude" name="queryEntity.posLongitude" maxLength="9"/>
																							
																				</li>
																				<li>
																						
																						
																						<label for="entity_posLatitude">维度位置</label>
																							<s:textfield id="queryEntity_posLatitude" name="queryEntity.posLatitude" maxLength="9"/>
																							
																				</li>
																				<li>
																						
																						
																						<label for="entity_posHeight">位置高度</label>
																							<s:textfield id="queryEntity_posHeight" name="queryEntity.posHeight" maxLength="5"/>
																							
																				</li>
																				<li>
																						
																						
																						<label for="entity_photoUserCd">拍摄用户编码</label>
																							<s:textfield id="queryEntity_photoUserCd" name="queryEntity.photoUserCd" maxLength="32"/>
																							
																				</li>
																				<li>
																						
																						
																						<label for="entity_photoTm">拍照时间</label>
																							<s:textfield id="queryEntity_photoTm" name="queryEntity.photoTm" />
																							
																				</li>
																				<li>
																						
																						
																						<label for="entity_uploadTm">上传时间</label>
																							<s:textfield id="queryEntity_uploadTm" name="queryEntity.uploadTm" />
																							
																				</li>
																				<li>
																						
																						
																						<label for="entity_memo">备注</label>
																							<s:textfield id="queryEntity_memo" name="queryEntity.memo" />
																							
																				</li>
																				<li>
																						
																						
																						<label for="entity_checkTm">验货时间</label>
																							<s:textfield id="queryEntity_checkTm" name="queryEntity.checkTm" />
																							
																				</li>
																				<li>
																						
																						
																						<label for="entity_checkUserCd">验货用户编码</label>
																							<s:textfield id="queryEntity_checkUserCd" name="queryEntity.checkUserCd" maxLength="32"/>
																							
																				</li>
																				<li>
																						
																						
																						<label for="entity_statusCd">状态</label>
																							<s:textfield id="queryEntity_statusCd" name="queryEntity.statusCd" maxLength="1"/>
																							
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
																<s:param name="callbackId" value="%{_}"></s:param>
																<span>添加</span>
															</s:a>
														</li>
														<li>
															<s:a encode="false" width="960" height="550" cssClass="add" target="dialog" mask="true">
																<s:param name="callbackId" value="%{_}"></s:param>
																<span>修改</span>
															</s:a>
														</li>
														<li>
															<s:a cssClass="delete" callback="function refreshCurrentPageContainer(){navTabSearch('${_}');}" target="navTabTodo">
																<s:param name="callbackId" value="%{_}"></s:param>
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
												<th>照片Id</th>
												<th>发货单ID</th>
												<th>文件名</th>
												<th>经纬位置</th>
												<th>维度位置</th>
												<th>位置高度</th>
												<th>拍摄用户编码</th>
												<th>拍照时间</th>
												<th>上传时间</th>
												<th>备注</th>
												<th>验货时间</th>
												<th>验货用户编码</th>
												<th>状态</th>
											</tr>
										</thead>
										<tbody>
											<s:iterator value="page.data">
												<tr key="entity.photoId" value='<s:property value="photoId" />'>
													<td><s:property value="photoId" /></td>
													<td><s:property value="invoiceId" /></td>
													<td><s:property value="fileName" /></td>
													<td><s:property value="posLongitude" /></td>
													<td><s:property value="posLatitude" /></td>
													<td><s:property value="posHeight" /></td>
													<td><s:property value="photoUserCd" /></td>
													<td><s:property value="photoTm" /></td>
													<td><s:property value="uploadTm" /></td>
													<td><s:property value="memo" /></td>
													<td><s:property value="checkTm" /></td>
													<td><s:property value="checkUserCd" /></td>
													<td><s:property value="statusCd" /></td>
												</tr>
											</s:iterator>
										</tbody>
									</table>
								</div>
								<div region="south" height="26">
										<div class="panelBar">
												<div class="pages">
													<span>显示</span>
													<s:select list="page.pageSizeList" name="queryEntity.pageSize" cssClass="pageSize" form="${_}"/>
													<span>条，共[<s:property value="page.count"/>]条</span>
												</div>
												<s:hidden name="queryEntity.pageNumber" id="pageNumber" />
												<s:hidden name="_"/>
												<div class="pagination" totalCount='<s:property value="page.count"/>' numPerPage='<s:property value="page.pageSize"/>' pageNumShown='<s:property value="page.pageNumShown"/>' currentPage='<s:property value="page.pageNumber"/>'></div>
										</div>
							</div>
					</s:form>
		</body>
</html>
