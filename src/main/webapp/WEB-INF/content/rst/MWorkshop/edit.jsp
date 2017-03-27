<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>编辑mWorkshop</title>
	</head>
	<body>
			  <div class="page">
						<s:property value="message" escapeHtml="false"/>
							 <div class="pageContent">
									 <s:form method="post" action="m-workshop!edit"  cssClass="pageForm" onsubmit="return validateCallback(this,$.pdialog.closeCurrentRefresh);"id="%{_}">
										
										   <div class="pageFormContent col1" layoutH="56">
													<s:hidden name="entity.wsCd" value="%{entity.wsCd}" />
													<s:include value="_edit.jsp" />
											 </div>		
											 <div class="formBar">
													<ul>
														<li>
															<div class="buttonActive">
																<div class="buttonContent">
																	<button type="button" onclick="return checkStoreEdit()">确定</button>
																</div>
															</div>
														</li>
														<li>
															 <div class="button">
								                                <div class="buttonContent">
								                                    <button type="button" onclick="$.pdialog.closeCurrent();">关闭</button>
								                                </div>
								                            </div>
														</li>
													</ul>
											 </div>			
									 </s:form>
						  </div>		
		    </div>
			<script type="text/javascript">
			function checkStoreEdit(){
				var managerUserId = $("#entity_managerUserId_hidden").val();
				var managerUserNm = $("#entity_managerUserId_view").val();
				var typeCd = $("#entity_typeCd").val();
				var wsCd = document.getElementsByName("entity.wsCd")[0].value;
				$.ajax({
  		  		  url:'/ISMS/rst/m-workshop!checkStore.action',
  		  		  type:'post',
  		  		  async: 'false',
  		  		  data:{'entity.managerUserId':managerUserId,'entity.typeCd':typeCd,'entity.wsCd':wsCd},
  		  		  dataType:'json',
  		      		  complete:function(data){
  		      			var s = data.responseText;
  		      			if(s==1){
  		      				alertMsg.warn("您所选择的负责人"+managerUserNm+"已经负责了其他仓库，请更换其他人");
  		      			}else{
		      				$("#${_}").submit();
		      			}
  		      			return false;
  		      	}
  		     });
			}
			
				function closeAndRefresh(obj){
					var callbackId = '{callbackId}';
					closeCurrentPageContainer(obj);
					if(callbackId != ''){
						refreshPageContainer(callbackId);	
					}
			}
		</script>
	</body>
</html>
