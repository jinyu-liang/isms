<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div>
	<label for="entity_replyId">回复Id</label>
	
		<span>
				<s:textfield name="entity.replyId"  id="entity_replyId"  maxlength="32"/>
  	</span>
	
</div>
<div>
	<label for="entity_recordId">流水号</label>
	
		<span>
				<s:textfield name="entity.recordId"  id="entity_recordId"  maxlength="32"/>
  	</span>
	
</div>
<div>
	<label for="entity_replyType">回复类别</label>
	
		<span>
				<s:textfield name="entity.replyType"  id="entity_replyType"  maxlength="2"/>
  	</span>
	
</div>
<div>
	<label for="entity_rootReplyId">根回复项目ID</label>
	
		<span>
				<s:textfield name="entity.rootReplyId"  id="entity_rootReplyId"  maxlength="32"/>
  	</span>
	
</div>
<div>
	<label for="entity_parentReplyId">父回复项目Id</label>
	
		<span>
				<s:textfield name="entity.parentReplyId"  id="entity_parentReplyId"  maxlength="32"/>
  	</span>
	
</div>
<div>
	<label for="entity_title">计划表标题</label>
	
		<span>
				<s:textfield name="entity.title"  id="entity_title"  maxlength="200"/>
  	</span>
	
</div>
<div>
	<label for="entity_content">内容</label>
	
		<span>
				<s:textfield name="entity.content"  id="entity_content"  maxlength="400"/>
  	</span>
	
</div>
<div>
	<label for="entity_replyUserCd">回复者用户Cd</label>
	
		<span>
				<s:textfield name="entity.replyUserCd"  id="entity_replyUserCd"  maxlength="32"/>
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
<div>
	<label for="entity_extraFlg">额外区分</label>
	
		<span>
				<s:textfield name="entity.extraFlg"  id="entity_extraFlg"  maxlength="1"/>
  	</span>
	
</div>
<div>
	<label for="entity_deleteCd">删除区分</label>
	
		<span>
				<s:textfield name="entity.deleteCd"  id="entity_deleteCd"  maxlength="1"/>
  	</span>
	
</div>
