<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div>
	<label for="entity_logId">日志id</label>
	
		<span>
				<s:textfield name="entity.logId"  id="entity_logId"  maxlength="32"/>
  	</span>
	
</div>
<div>
	<label for="entity_busiId">业务Id</label>
	
		<span>
				<s:textfield name="entity.busiId"  id="entity_busiId"  maxlength="32"/>
  	</span>
	
</div>
<div>
	<label for="entity_modelId">模块编码</label>
	
		<span>
				<s:textfield name="entity.modelId"  id="entity_modelId"  maxlength="10"/>
  	</span>
	
</div>
<div>
	<label for="entity_modelName">模块名称</label>
	
		<span>
				<s:textfield name="entity.modelName"  id="entity_modelName"  maxlength="40"/>
  	</span>
	
</div>
<div>
	<label for="entity_operFuncCode">操作功能代码</label>
	
		<span>
				<s:textfield name="entity.operFuncCode"  id="entity_operFuncCode"  maxlength="10"/>
  	</span>
	
</div>
<div>
	<label for="entity_operFuncName">操作功能名称</label>
	
		<span>
				<s:textfield name="entity.operFuncName"  id="entity_operFuncName"  maxlength="100"/>
  	</span>
	
</div>
<div>
	<label for="entity_operResult">操作结果</label>
	
		<span>
				<s:textfield name="entity.operResult"  id="entity_operResult"  maxlength="60"/>
  	</span>
	
</div>
<div>
	<label for="entity_operateTime">操作时间</label>
	
		<span>
			<s:textfield name="entity.operateTime" id="entity_operateTime" cssClass="date" >
				<s:param name="value">
					<s:date name="entity.operateTime" format="yyyy-MM-dd HH:mm:ss" />
				</s:param>
			</s:textfield>
		</span>
		
	
</div>
<div>
	<label for="entity_userId">用户Id</label>
	
		<span>
				<s:textfield name="entity.userId"  id="entity_userId"  maxlength="32"/>
  	</span>
	
</div>
<div>
	<label for="entity_name">姓名</label>
	
		<span>
				<s:textfield name="entity.name"  id="entity_name"  maxlength="50"/>
  	</span>
	
</div>
<div>
	<label for="entity_operIp">操作人Ip</label>
	
		<span>
				<s:textfield name="entity.operIp"  id="entity_operIp"  maxlength="64"/>
  	</span>
	
</div>
<div>
	<label for="entity_note">备注</label>
	
		<span>
				<s:textfield name="entity.note"  id="entity_note" />
  	</span>
	
</div>
