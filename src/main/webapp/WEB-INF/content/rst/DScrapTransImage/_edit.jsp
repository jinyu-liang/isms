<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div>
	<label for="entity_photoId">照片Id</label>
	
		<span>
				<s:textfield name="entity.photoId"  id="entity_photoId" />
  	</span>
	
</div>
<div>
	<label for="entity_transId">运输Id</label>
	
		<span>
				<s:textfield name="entity.transId"  id="entity_transId"  maxlength="32"/>
  	</span>
	
</div>
<div>
	<label for="entity_fileName">文件名</label>
	
		<span>
				<s:textfield name="entity.fileName"  id="entity_fileName"  maxlength="80"/>
  	</span>
	
</div>
<div>
	<label for="entity_photoTm">拍照时间</label>
	
		<span>
				<s:textfield name="entity.photoTm"  id="entity_photoTm" />
  	</span>
	
</div>
<div>
	<label for="entity_memo">备注</label>
	
		<span>
				<s:textfield name="entity.memo"  id="entity_memo" />
  	</span>
	
</div>
<div>
	<label for="entity_checkTm">验货时间</label>
	
		<span>
				<s:textfield name="entity.checkTm"  id="entity_checkTm" />
  	</span>
	
</div>
<div>
	<label for="entity_checkUserCd">验货用户编码</label>
	
		<span>
				<s:textfield name="entity.checkUserCd"  id="entity_checkUserCd"  maxlength="32"/>
  	</span>
	
</div>
<div>
	<label for="entity_statusCd">状态</label>
	
		<span>
				<s:textfield name="entity.statusCd"  id="entity_statusCd"  maxlength="1"/>
  	</span>
	
</div>
<div>
	<label for="entity_checkBy">验货人</label>
	
		<span>
				<s:textfield name="entity.checkBy"  id="entity_checkBy"  maxlength="32"/>
  	</span>
	
</div>
