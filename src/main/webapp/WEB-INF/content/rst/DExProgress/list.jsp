<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pt" uri="/is-tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>进度管理</title>
<style type="text/css">
.autocut {
    width:200px;
    overflow:hidden;
    white-space:nowrap;
    text-overflow:ellipsis;
    -o-text-overflow:ellipsis;
    -icab-text-overflow: ellipsis;
    -khtml-text-overflow: ellipsis;
    -moz-text-overflow: ellipsis;
    -webkit-text-overflow: ellipsis;
}

</style>
</head>
<body>
    <s:property value="message" escapeHtml="false" />
        <s:form method="post" action="d-ex-progress!treeList" cssStyle="height:100%;width:100%;" onsubmit="return navTabSearch(this);" id="%{_}">
        <s:hidden name="reportDt" value="%{reportDt}"></s:hidden>
        <s:hidden name="userCd" value="%{userCd}"></s:hidden>
        <div class="page" style="height: 100%; width: 100%;">
            <div class="layout" style="height: 100%; width: 100%;">
                <div region="north" height="95" style="width: 100%;">
                    <div class="pageHeader">
                        <div class="searchBar">
                            <ul class="searchContent">
                                <%-- <li><label for="entity_reportId">报告Id</label> <s:textfield id="queryEntity_reportId" name="queryEntity.reportId"
                                        maxLength="32" />
                                </li>--%>
                                <li><label for="entity_projectId">工程名称</label> <s:textfield id="queryEntity_projectId"
                                        name="queryEntity.projectId" />
                                </li>
                          <%--  <li><label for="entity_progressStatus">进度状况</label> <s:textfield id="queryEntity_progressStatus"
                                        name="queryEntity.progressStatus" maxLength="200" />
                                </li>
                                <li><label for="entity_fbWorkshop">现场反馈问题</label> <s:textfield id="queryEntity_fbWorkshop"
                                        name="queryEntity.fbWorkshop" maxLength="100" />
                                </li>
                                <li><label for="entity_fbDelivery">厂内发货问题</label> <s:textfield id="queryEntity_fbDelivery"
                                        name="queryEntity.fbDelivery" maxLength="100" />
                                </li>
                                <li><label for="entity_fbQuality">质量问题反馈</label> <s:textfield id="queryEntity_fbQuality"
                                        name="queryEntity.fbQuality" maxLength="100" />
                                </li>
                                <li><label for="entity_fbSecurity">安全问题反馈</label> <s:textfield id="queryEntity_fbSecurity"
                                        name="queryEntity.fbSecurity" maxLength="100" />
                                </li>
                                <li><label for="entity_fbManner">文明施工问题</label> <s:textfield id="queryEntity_fbManner"
                                        name="queryEntity.fbManner" maxLength="100" />
                                </li>
                                <li><label for="entity_fbMmaterial">主材情况</label> <s:textfield id="queryEntity_fbMmaterial"
                                        name="queryEntity.fbMmaterial" maxLength="100" />
                                </li>
                                <li><label for="entity_fbSmaterial">配件情况</label> <s:textfield id="queryEntity_fbSmaterial"
                                        name="queryEntity.fbSmaterial" maxLength="100" />
                                </li>
                                <li><label for="entity_fbEquipment">设备情况</label> <s:textfield id="queryEntity_fbEquipment"
                                        name="queryEntity.fbEquipment" maxLength="100" />
                                </li>
                                <li><label for="entity_totalCost">计划定额费用</label> <s:textfield id="queryEntity_totalCost"
                                        name="queryEntity.totalCost" maxLength="16" />
                                </li>
                                <li><label for="entity_currentCost">实际费用</label> <s:textfield id="queryEntity_currentCost"
                                        name="queryEntity.currentCost" maxLength="16" />
                                </li>
                                <li><label for="entity_totalExpense">总费用</label> <s:textfield id="queryEntity_totalExpense"
                                        name="queryEntity.totalExpense" maxLength="16" />
                                </li>
                                <li><label for="entity_currentExpense">易耗费用已发额</label> <s:textfield id="queryEntity_currentExpense"
                                        name="queryEntity.currentExpense" maxLength="16" />
                                </li> --%>
                                <li><label for="entity_teamLeader">施工队长</label> <s:textfield id="queryEntity_teamLeader"
                                        name="queryEntity.teamLeader" maxLength="32" />
                                </li>
                                <%-- <li><label for="entity_welder">主要焊工</label> <s:textfield id="queryEntity_welder" name="queryEntity.welder"
                                        maxLength="32" />
                                </li>
                                <li><label for="entity_riveter">主要铆工</label> <s:textfield id="queryEntity_riveter" name="queryEntity.riveter"
                                        maxLength="32" />
                                </li>
                                <li><label for="entity_workerCount">施工总人数</label> <s:textfield id="queryEntity_workerCount"
                                        name="queryEntity.workerCount" />
                                </li> --%>
                                <li><label for="entity_viceManager">分管副部长</label> <s:textfield id="queryEntity_viceManager"
                                        name="queryEntity.viceManager" maxLength="32" />
                                </li>
                                <li><label for="entity_wsLeader">现场带队人员</label> <s:textfield id="queryEntity_wsLeader"
                                        name="queryEntity.wsLeader" maxLength="32" />
                                </li>
                                <%-- <li><label for="entity_memo">备注</label> <s:textfield id="queryEntity_memo" name="queryEntity.memo" />
                                </li> --%>
                            </ul>
                            <div class="subBar">
                                <ul>
                                    <li>
                                        <div class="buttonActive">
                                            <div class="buttonContent">
                                                <s:submit type="button">查询</s:submit>
                                            </div>
                                        </div>
                                    </li>
                                </ul>
                            </div>
                        </div>
                    </div>

                    <%-- 										<div class="pageContent">
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
										</div> --%>
                </div>
                <div region="center" style="overflow: auto; width: 100%;" layoutH="85">
                    <table class="list complextable">
                        <thead>
                            <tr valign="middle" align="center">
                                <!-- <th rowspan="2" style="line-height: 2em;">报告Id</th> -->
                                <th rowspan="2">更新时间</th>
                                <th rowspan="2">工程名称</th>
                                <th rowspan="2">&nbsp;&nbsp;&nbsp;&nbsp;图片&nbsp;&nbsp;&nbsp;&nbsp;</th>
                                <th colspan="4">设备</th>
                                <th colspan="3">进驻日期及合同约定</th>
                                <th rowspan="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;进度状况&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                                <th colspan="8">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;现场情况反馈&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                                <th colspan="2">现场费用</th>
                                <th colspan="2">现场易耗费用</th>
                                <th colspan="4">施工队人员</th>
                                <th colspan="2">现场负责人</th>
                                <th rowspan="2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;备注&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th>
                            </tr>
                            <tr valign="middle" align="center">
                                <th>净重(吨)</th>
                                <th>名称</th>
                                <th>数量(台)</th>
                                <th>备注</th>
                                <th>实际进驻日期</th>
                                <th>合同其他要求</th>
                                <th>合同要求进驻日期</th>
                                <th>&nbsp;&nbsp;&nbsp;&nbsp;现场反馈问题&nbsp;&nbsp;&nbsp;&nbsp;</th>
                                <th>&nbsp;&nbsp;&nbsp;&nbsp;厂内发货问题&nbsp;&nbsp;&nbsp;&nbsp;</th>
                                <th>&nbsp;&nbsp;&nbsp;&nbsp;质量情况&nbsp;&nbsp;&nbsp;&nbsp;</th>
                                <th>&nbsp;&nbsp;&nbsp;&nbsp;安全情况&nbsp;&nbsp;&nbsp;&nbsp;</th>
                                <th>&nbsp;&nbsp;&nbsp;&nbsp;文明施工情况&nbsp;&nbsp;&nbsp;&nbsp;</th>
                                <th>&nbsp;&nbsp;&nbsp;&nbsp;主材情况&nbsp;&nbsp;&nbsp;&nbsp;</th>
                                <th>&nbsp;&nbsp;&nbsp;&nbsp;配件情况&nbsp;&nbsp;&nbsp;&nbsp;</th>
                                <th>&nbsp;&nbsp;&nbsp;&nbsp;设备情况&nbsp;&nbsp;&nbsp;&nbsp;</th>
                                
                                <th>&nbsp;&nbsp;&nbsp;&nbsp;预计定额&nbsp;&nbsp;&nbsp;&nbsp;</th>
                                <th>&nbsp;&nbsp;&nbsp;&nbsp;实际费用&nbsp;&nbsp;&nbsp;&nbsp;</th>
                                
                                <th>&nbsp;&nbsp;&nbsp;&nbsp;总定额金额&nbsp;&nbsp;&nbsp;&nbsp;</th>
                                <th>&nbsp;&nbsp;&nbsp;&nbsp;已发金额&nbsp;&nbsp;&nbsp;&nbsp;</th>
                                
                                <th>&nbsp;&nbsp;&nbsp;&nbsp;施工队长&nbsp;&nbsp;&nbsp;&nbsp;</th>
                                <th>&nbsp;&nbsp;&nbsp;&nbsp;主要焊工&nbsp;&nbsp;&nbsp;&nbsp;</th>
                                <th>&nbsp;&nbsp;&nbsp;&nbsp;主要铆工&nbsp;&nbsp;&nbsp;&nbsp;</th>
                                <th>现场总人数</th>
                                
                                <th>&nbsp;&nbsp;&nbsp;&nbsp;分管副部长&nbsp;&nbsp;&nbsp;&nbsp;</th>
                                <th>&nbsp;&nbsp;&nbsp;&nbsp;带队人员&nbsp;&nbsp;&nbsp;&nbsp;</th>
                            </tr>
                        </thead>
                        <tbody>
                            <div style="display: none">
                                <s:a id="editProgress" namespace="/rst" action="" method="view" encode="false" cssClass="view"
                                    target="dialog" mask="true" width="635" height="640">
                                    <s:param name="entity.planId" value="planId" />
                                    <s:param name="callbackId" value="%{_}"></s:param>
                                    <span>进度编辑</span>
                                </s:a>
                            </div>
                            <sec:authorize ifAllGranted="进度管理-编辑进度信息">
                            <s:iterator value="page.data" id="entityData">
                                <s:set var="count" value="%{exItems.size()}"></s:set>
                                <s:iterator value="exItems" id="childData" status="sta">
                                    <s:if test="#sta.index==0">
                                        <tr target="entity_reportId" rel='<s:property value="reportId" />'
                                            ondblclick="handleWisely2('<s:property value="reportId" />','<s:property value="projectId" />','<s:property value="%{_}" />',event.type)">
                                            <%-- <td rowspan="${count}"><s:property value="reportId" /></td> --%>
                                            <td rowspan="${count}">
                                                <s:date name="progressReport.updateTm" format="yyyy-MM-dd HH:mm:ss" />
                                            </td>
                                            <%-- <td rowspan="${count}" ><s:property value="projectId" /></td> --%>
                                            <td rowspan="${count}">  <pt:projectnameShow projectId="projectId" />  </td>
                                            <td rowspan="${count}">
                                                <s:if test="progressReport.progressImage.size()>0">
                                                    <div class="buttonContent">
                                                        <s:a id="showView" namespace="/rst" action="d-ex-progress" method="listPic" encode="false"
                                                            cssClass="view button" target="dialog" mask="true" width="1025" height="738">
                                                            <s:param name="entity.reportId" value="reportId" />
                                                            <s:param name="callbackId" value="%{_}"></s:param>
                                                            <span>图片</span>
                                                        </s:a>
                                                    </div>
                                                </s:if>
                                            </td>
                                            <td rowspan="${count}">
                                             <s:if test="dexProject.weight!=null&&dexProject.weight!=''">
                                            <s:text name="format.number">  
									  	      <s:param value="dexProject.weight"/>  
									        </s:text>  
									    </s:if>
									    <s:else>
									        --
									    </s:else>
                                           </td>
                                            <!-- 设备-->
                                            <td><s:property value="#childData.itemNm" /></td>
                                            <td><s:property value="#childData.amount" /></td>
                                            <td><s:property value="#childData.memo" /></td>
                                            <!--进驻日期及合同约定-->
                                            <td rowspan="${count}"><s:property value="dexProject.startDate"/></td>
                                            <td rowspan="${count}"><s:property value="dexProject.contractOtherReq"/></td>
                                            <td rowspan="${count}"><s:property value="dexProject.contractStartDate"/></td> 
                                            <!-- 工程进度 -->
                                            <td rowspan="${count}"><s:property value="progressStatus" /></td>
                                            <!-- 现场情况反馈 -->
                                            <td rowspan="${count}"><s:property value="fbWorkshop" /></td>
                                            <td rowspan="${count}"><s:property value="fbDelivery" /></td>
                                            <td rowspan="${count}"><s:property value="fbQuality" /></td>
                                            <td rowspan="${count}"><s:property value="fbSecurity" /></td> 
                                            <td rowspan="${count}"><s:property value="fbManner" /></td>
                                            <td rowspan="${count}"><s:property value="fbMmaterial" /></td>
                                            <td rowspan="${count}"><s:property value="fbSmaterial" /></td>
                                            <td rowspan="${count}"><s:property value="fbEquipment" /></td>
                                            <!-- 现场费用 -->
                                            <td rowspan="${count}"><s:property value="totalCost" /></td>
                                            <td rowspan="${count}">
                                            	 <s:if test="currentCost!=null&&currentCost!=''">
		                                            <s:text name="format.number">  
											  	      <s:param value="currentCost"/>  
											     	</s:text>
											     </s:if>
											    <s:else>
											        --
											    </s:else>  
                                            	</td>
                                            <!-- 现场易耗费用 -->
                                            <td rowspan="${count}"><s:property value="totalExpense" /></td>
                                            <td rowspan="${count}"><s:property value="currentExpense" /></td>
                                            <!-- 施工队人员 -->
                                            <td rowspan="${count}"><s:property value="teamLeader" /></td>
                                            <td rowspan="${count}"><s:property value="welder" /></td>
                                            <td rowspan="${count}"><s:property value="riveter" /></td>
                                            <td rowspan="${count}"><s:property value="workerCount" /></td>
                                            
                                            <!-- 现场负责人 -->
                                            <td rowspan="${count}"><s:property value="viceManager" /></td>
                                            <td rowspan="${count}"><s:property value="wsLeader" /></td>
                                            <!-- 备注 -->
                                            <td rowspan="${count}" title="<s:property value='#entityData.memo' />"><div class="autocut"><s:property value="#entityData.memo" /></div></td>
                                        </tr>
                                    </s:if>
                                    <s:else>
                                        <tr>
                                            <td><s:property value="#childData.itemNm" /></td>
                                            <td><s:property value="#childData.amount" /></td>
                                            <td><s:property value="#childData.memo" /></td>
                                        </tr>
                                    </s:else>
                                </s:iterator>
                            </s:iterator>
                            </sec:authorize>
                            <sec:authorize ifNotGranted ="进度管理-编辑进度信息">
                                <s:iterator value="page.data" id="entityData">
                                <s:set var="count" value="%{exItems.size()}"></s:set>
                                <s:iterator value="exItems" id="childData" status="sta">
                                    <s:if test="#sta.index==0">
                                        <tr target="entity_reportId" rel='<s:property value="reportId" />'
                                            ondblclick="handleWiselyNotAuth(event.type)">
                                            <%-- <td rowspan="${count}"><s:property value="reportId" /></td> --%>
                                            <td rowspan="${count}">
                                                <div style="display: none">
                                                    <s:a id="editProgress" namespace="/rst" action="" method="view" encode="false" cssClass="view"
                                                        target="dialog" mask="true" width="600" height="630">
                                                        <s:param name="entity.planId" value="planId" />
                                                        <s:param name="callbackId" value="%{_}"></s:param>
                                                        <span><s:date name="progressReport.updateTm" format="yyyy年MM月dd日" />&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="teamLeader" />队&nbsp;&nbsp;&nbsp;&nbsp;工程<font color="red"><pt:workshorpnameShow wsCd="progressReport.wsCd" /></font>进度编辑</span>
                                                    </s:a>
                                                </div><s:date name="progressReport.updateTm" format="yyyy-MM-dd HH:mm:ss" />
                                            </td>
                                            <%-- <td rowspan="${count}" ><s:property value="projectId" /></td> --%>
                                            <td rowspan="${count}"><pt:workshorpnameShow wsCd="progressReport.wsCd" /></td>
                                            <td rowspan="${count}">
                                                <s:if test="progressImage.size()>0">
                                                    <div class="buttonContent">
                                                        <s:a id="showView" namespace="/rst" action="d-ex-progress" method="listPic" encode="false"
                                                            cssClass="view button" target="dialog" mask="true" width="1025" height="738">
                                                            <s:param name="entity.reportId" value="reportId" />
                                                            <s:param name="callbackId" value="%{_}"></s:param>
                                                            <span>图片</span>
                                                        </s:a>
                                                    </div>
                                                </s:if>
                                            </td>
                                            <td rowspan="${count}"><s:property value="dexProject.weight" /></td>
                                            <!-- 设备-->
                                            <td><s:property value="#childData.itemNm" /></td>
                                            <td><s:property value="#childData.amount" /></td>
                                            <td><s:property value="#childData.memo" /></td>
                                            <!--进驻日期及合同约定-->
                                            <td rowspan="${count}"><s:property value="dexProject.startDate"/></td>
                                            <td rowspan="${count}"><s:property value="dexProject.contractOtherReq"/></td>
                                            <td rowspan="${count}"><s:property value="dexProject.contractStartDate"/></td> 
                                            <!-- 工程进度 -->
                                            <td rowspan="${count}"><s:property value="progressStatus" /></td>
                                            <!-- 现场情况反馈 -->
                                            <td rowspan="${count}"><s:property value="fbWorkshop" /></td>
                                            <td rowspan="${count}"><s:property value="fbDelivery" /></td>
                                            <td rowspan="${count}"><s:property value="fbQuality" /></td>
                                            <td rowspan="${count}"><s:property value="fbSecurity" /></td> 
                                            <td rowspan="${count}"><s:property value="fbManner" /></td>
                                            <td rowspan="${count}"><s:property value="fbMmaterial" /></td>
                                            <td rowspan="${count}"><s:property value="fbSmaterial" /></td>
                                            <td rowspan="${count}"><s:property value="fbEquipment" /></td>
                                            <!-- 现场费用 -->
                                            <td rowspan="${count}"><s:property value="totalCost" /></td>
                                            <td rowspan="${count}">
                                            	 <s:if test="currentCost!=null&&currentCost!=''">
		                                            <s:text name="format.number">  
											  	      <s:param value="currentCost"/>  
											     	</s:text>
											     </s:if>
											    <s:else>
											        --
											    </s:else>  
                                            </td>
                                            <!-- 现场易耗费用 -->
                                            <td rowspan="${count}"><s:property value="totalExpense" /></td>
                                            <td rowspan="${count}"><s:property value="currentExpense" /></td>
                                            <!-- 施工队人员 -->
                                            <td rowspan="${count}"><s:property value="teamLeader" /></td>
                                            <td rowspan="${count}"><s:property value="welder" /></td>
                                            <td rowspan="${count}"><s:property value="riveter" /></td>
                                            <td rowspan="${count}"><s:property value="workerCount" /></td>
                                            
                                            <!-- 现场负责人 -->
                                            <td rowspan="${count}"><s:property value="viceManager" /></td>
                                            <td rowspan="${count}"><s:property value="wsLeader" /></td>
                                            <!-- 备注 -->
                                            <td rowspan="${count}" title="<s:property value='#entityData.memo' />"><div class="autocut"><s:property value="#entityData.memo" /></div></td>
                                        </tr>
                                    </s:if>
                                    <s:else>
                                        <tr>
                                            <td><s:property value="#childData.itemNm" /></td>
                                            <td><s:property value="#childData.amount" /></td>
                                            <td><s:property value="#childData.memo" /></td>
                                        </tr>
                                    </s:else>
                                </s:iterator>
                            </s:iterator>
                            </sec:authorize>
                        </tbody>
                    </table>
                </div>
                <div region="south" height="26">
                    <div class="panelBar">
                        <div class="pages">
                            <span>显示</span>
                            <s:select list="page.pageSizeList" name="queryEntity.pageSize" cssClass="pageSize" form="${_}" />
                            <span>条，共[<s:property value="page.count" />]条</span>
                        </div>
                        <s:hidden name="queryEntity.pageNumber" id="pageNumber" />
                        <s:hidden name="_"></s:hidden>
                        <div class="pagination" totalCount='<s:property value="page.count"/>' numPerPage='<s:property value="page.pageSize"/>'
                            pageNumShown='<s:property value="page.pageNumShown"/>' currentPage='<s:property value="page.pageNumber"/>'></div>
                    </div>
                </div>
            </div>
        </div>
    </s:form>
    <script type="text/javascript">
					var flag;
					var dcTime = 250; // doubleclick time
					var dcDelay = 100; // no clicks after doubleclick
					var dcAt = 0; // time of doubleclick
					var savEvent = null; // save Event for handling doClick().
					var savEvtTime = 0; // save time of click event.
					var savTO = null; // handle of click setTimeOut
					/*点击查看出门单信息*/
					/* 查看事件  */
					function handleWisely2(reportId, projectId, callbackId,
							which) {
						switch (which) {
						case "click":
							break;
						case "dblclick":
							doDoubleClick(reportId, projectId, callbackId,
									which);
							break;
						default:
						}
					}
					/* 双击 */
					function doDoubleClick(reportId, projectId, callbackId,
							which) {
						$
								.ajax({
									url : '/ISMS/rst/d-ex-progress!isAuth.action',
									type : 'post',
									async : 'false',
									dataType : 'json',
									complete : function(data) {
										if (data.responseText == 1) {
											var d = new Date();
											dcAt = d.getTime();
											if (savTO != null) {
												savTO = null;
											}
											var url = "/ISMS/rst/d-ex-progress!toEdit.action?entity.projectId="
													+ projectId
													+ "&callbackId="
													+ callbackId
													+ "&entity.reportId="
													+ reportId;
											$("#editProgress")
													.attr("href", url).click();
										} else {
											alertMsg.info("权限不足，不可以编辑。");
											return false;
										}
									}
								});
					}
					/* 没有权限的时候查看事件  */
					function handleWiselyNotAuth(which) {
						switch (which) {
						case "click":
							break;
						case "dblclick":
							doDoubleClickNotAuth(which);
							break;
						default:
						}
					}
					/* 双击 */
					function doDoubleClickNotAuth(which) {
						alertMsg.info("权限不足，不可以编辑。");
						return false;
					}

				</script>
</body>
</html>
