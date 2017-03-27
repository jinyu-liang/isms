<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div>
	<label for="entity_reportId">报告Id</label>
	
		<span>
				<s:textfield name="entity.reportId"  id="entity_reportId"  maxlength="32"/>
  	</span>
	
</div>
<div>
	<label for="entity_workCenterId">工作中心编码</label>
	
		<span>
				<s:textfield name="entity.workCenterId"  id="entity_workCenterId"  maxlength="32"/>
  	</span>
	
</div>
<div>
	<label for="entity_title">计划表标题</label>
	
		<span>
				<s:textfield name="entity.title"  id="entity_title"  maxlength="200"/>
  	</span>
	
</div>
<div>
	<label for="entity_teamCd">班组编号</label>
	
		<span>
				<s:textfield name="entity.teamCd"  id="entity_teamCd"  maxlength="32"/>
  	</span>
	
</div>
<div>
	<label for="entity_reportUserCd">报告用户编码</label>
	
		<span>
				<s:textfield name="entity.reportUserCd"  id="entity_reportUserCd"  maxlength="32"/>
  	</span>
	
</div>
<div>
	<label for="entity_reportUserName">报告用户名称</label>
	
		<span>
				<s:textfield name="entity.reportUserName"  id="entity_reportUserName"  maxlength="32"/>
  	</span>
	
</div>
<div>
	<label for="entity_reportTm">报告时间</label>
	
		<span>
				<s:textfield name="entity.reportTm"  id="entity_reportTm" />
  	</span>
	
</div>
<div>
	<label for="entity_memo">备注</label>
	
		<span>
				<s:textfield name="entity.memo"  id="entity_memo" />
  	</span>
	
</div>
<div>
	<label for="entity_processUserCd">处理用户编码</label>
	
		<span>
				<s:textfield name="entity.processUserCd"  id="entity_processUserCd"  maxlength="32"/>
  	</span>
	
</div>
<div>
	<label for="entity_processTm">处理时间</label>
	
		<span>
				<s:textfield name="entity.processTm"  id="entity_processTm"  maxlength="32"/>
  	</span>
	
</div>
<div>
	<label for="entity_dealComment">处理意见</label>
	
		<span>
				<s:textfield name="entity.dealComment"  id="entity_dealComment"  maxlength="200"/>
  	</span>
	
</div>
<div>
	<label for="entity_statusCd">状态</label>
	
		<span>
				<s:textfield name="entity.statusCd"  id="entity_statusCd"  maxlength="1"/>
  	</span>
	
</div>
