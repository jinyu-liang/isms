<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.UUID"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pt" uri="/is-tags"%>
<%
    String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<p style="width: 250px;">
	<label for="entity_title" style="width: 90px;">输入版本号：</label> <span> <s:textfield
			name="entity.verCode" id="entity_title" maxlength="200" cssClass="required" maxLength="6" /> </span>
</p>
<p style="width: 250px;">
	<label for="entity_prodDate" style="width: 90px;">投产时间：</label> <span> <s:textfield
			name="entity.prodDate" id="entity_prodDate" cssClass="date required"  
			maxlength="32" readOnly="true"><s:param name="value">
                <s:date name="entity.prodDate" format="yyyy-MM-dd" />
            </s:param>
        </s:textfield><a class="inputDateButton" href="javascript:;">选择</a> </span>
</p>
<p style="width: 250px;">
	<label for="entity_finishDate" style="width: 90px;">开发完成时间：</label> <span> <s:textfield
			name="entity.finishDate"  id="entity_teamCd" maxlength="32" cssClass="date required"   readOnly="true">
		<s:param name="value">
                <s:date name="entity.finishDate" format="yyyy-MM-dd" />
            </s:param>
        </s:textfield><a class="inputDateButton" href="javascript:;">选择</a>
	</span> 
</p>


		<p style="width: 300px;">
	<label for="entity_statusCd" style="width: 110px;">是否启用：</label> <span class="labelnarrow"> <s:radio
			id="entity.statusCd" name="entity.statusCd" cssClass="required" 
			list="#{'1':'立即启用','0':'暂不启用'}">
		</s:radio> </span>
</p>
<p class="anatomy container">
	<%
		String anatomyuuid = UUID.randomUUID().toString()
				.replaceAll("-", "");
	%>
	<%-- <s:hidden name="entity.fileName" id="filenames"  ></s:hidden> --%>
	<input type="text"  name="entity.fileName" id="filenames"  class="required" style="widows: 0px;height: 0px;border: 0px;z-index: -1;margin-left: -200px;" size="0"/>
	<a class="anatomy action" href="javascript:;"  no-repeat scroll 0 4px rgba(0, 0, 0, 0)"><var>
	<%-- <a class="anatomy action" href="javascript:;" style="background:url('<%=basePath%>/image/apk.ico') no-repeat scroll 0 4px rgba(0, 0, 0, 0)"><var> --%>
			{"button_action":"SWFUpload.BUTTON_ACTION.SELECT_FILE","file_types":"*.apk","file_types_description":"*.apk","file_upload_limit":"1","custom_settings":{"progressTarget":"<%=anatomyuuid%>","displayTarget":"<%=anatomyuuid%>","fillTarget":"filenames"}}
		</var><span>上传新版本</span> </a>
	<br>
	<span id="<%=anatomyuuid%>" class="fsUploadDisplay">
	<s:action name="sys-attach!exsitsFiles"
				executeResult="true" ignoreContextParams="true" flush="true"
		namespace="/sys">
		<s:param name="busiId" value="entity.wsCd"></s:param>
		<s:param name="attachId" value="entity.fileName"></s:param>
		<s:param name="fileShowType" value="3"></s:param>
	</s:action>
	</span>
</p>

<p style="width: auto;">
	<label for="entity_memo" style="width: 60px;">备注</label> <span> <s:textarea cols="50"
			 name="entity.memo" id="entity_memo" cssClass="required" maxlength="200"/> </span>
</p>
<script type="text/javascript">
$(function () {
	$("#entity_title").change(function(){
		var code = $("#entity_title").val();
		$.ajax({
	  		  url:'/ISMS/rst/rst-ver-info!isExist.action',
	  		  type:'post',
	  		  async: 'false',
	  		  data:{'entity.verCode':code},
	  		  dataType:'json',
	      		  complete:function(data){
	      			var s = data.responseText;
	      			if(s=='1'){
	      				alertMsg.info("版本号已经存在");
	      			}
	      			return false;
	      	}
	     });
	});
});
</script>