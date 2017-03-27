<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<div>
	<label for="entity_photoId">照片Id</label>
	
		<span>
				<s:textfield name="entity.photoId"  id="entity_photoId" />
  	</span>
	
</div>
<div>
	<label for="entity_invoiceId">发货单ID</label>
	
		<span>
				<s:textfield name="entity.invoiceId"  id="entity_invoiceId"  maxlength="32"/>
  	</span>
	
</div>
<div>
	<label for="entity_fileName">文件名</label>
	
		<span>
				<s:textfield name="entity.fileName"  id="entity_fileName"  maxlength="80"/>
  	</span>
	
</div>
<div>
	<label for="entity_posLongitude">经纬位置</label>
	
		<span>
				<s:textfield name="entity.posLongitude"  id="entity_posLongitude" />
  	</span>
	
</div>
<div>
	<label for="entity_posLatitude">维度位置</label>
	
		<span>
				<s:textfield name="entity.posLatitude"  id="entity_posLatitude" />
  	</span>
	
</div>
<div>
	<label for="entity_posHeight">位置高度</label>
	
		<span>
				<s:textfield name="entity.posHeight"  id="entity_posHeight" />
  	</span>
	
</div>
<div>
	<label for="entity_photoUserCd">拍摄用户编码</label>
	
		<span>
				<s:textfield name="entity.photoUserCd"  id="entity_photoUserCd"  maxlength="32"/>
  	</span>
	
</div>
<div>
	<label for="entity_photoTm">拍照时间</label>
	
		<span>
				<s:textfield name="entity.photoTm"  id="entity_photoTm" />
  	</span>
	
</div>
<div>
	<label for="entity_uploadTm">上传时间</label>
	
		<span>
				<s:textfield name="entity.uploadTm"  id="entity_uploadTm" />
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
