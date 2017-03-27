<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div>
	<label for="entity_reportId">报告Id</label>
	
		<span>
				<s:textfield name="entity.reportId"  id="entity_reportId"  maxlength="32"/>
  	</span>
	
</div>
<div>
	<label for="entity_wsCd">工程中心编码</label>
	
		<span>
				<s:textfield name="entity.wsCd"  id="entity_wsCd"  maxlength="4"/>
  	</span>
	
</div>
<div>
	<label for="entity_reportDt">进度报告日</label>
	
		<span>
			<s:textfield name="entity.reportDt" id="entity_reportDt" cssClass="date" >
				<s:param name="value">
					<s:date name="entity.reportDt" format="yyyy-MM-dd" />
				</s:param>
			</s:textfield>
		</span>
		
	
</div>
<div>
	<label for="entity_userCd">用户编码</label>
	
		<span>
				<s:textfield name="entity.userCd"  id="entity_userCd"  maxlength="32"/>
  	</span>
	
</div>
<div>
	<label for="entity_createTm">创建时间</label>
	
		<span>
			<s:textfield name="entity.createTm" id="entity_createTm" cssClass="date" >
				<s:param name="value">
					<s:date name="entity.createTm" format="yyyy-MM-dd" />
				</s:param>
			</s:textfield>
		</span>
		
	
</div>
<div>
	<label for="entity_updateTm">修改时间</label>
	
		<span>
			<s:textfield name="entity.updateTm" id="entity_updateTm" cssClass="date" >
				<s:param name="value">
					<s:date name="entity.updateTm" format="yyyy-MM-dd" />
				</s:param>
			</s:textfield>
		</span>
		
	
</div>
