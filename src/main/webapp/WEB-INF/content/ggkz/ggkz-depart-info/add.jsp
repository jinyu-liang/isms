<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>添加角色</title>
	</head>
	<body>
		<div class="page" >
		<s:property value="message" escapeHtml="false"/>
			<div class="pageContent">
				<s:form method="post" namespace="/ggkz" action="ggkz-depart-info!create.action" cssClass="pageForm" onsubmit="return validateCallback(this,dialogAjaxDone);">
					<div class="pageFormContent col1" layoutH="56">
						<s:include value="_edit.jsp" />
					</div>
					<div class="formBar">
							<ul>
								<li>
									<div class="buttonActive">
										<div class="buttonContent">
											<button type="submit" id="subform">确定</button>
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
        function closeAndRefresh(obj) {
            var callbackId = '${callbackId}';
            if (callbackId != '') {
            	if($('#navTab div.navTab-panel > div.tab_page[tabid="'+callbackId+'"] #org').length==0)
            	{
            		var _html='<ul id="org" style="display: none"><li>'+$('#${_} #entity_departName').val()+'</li></ul>';
            		$('#navTab div.navTab-panel > div.tab_page[tabid="'+callbackId+'"] #orgChart').before(_html);
            	}
            	else
        		{
            		var depId=$('#${_} #entity_departId').val();
            		var parentDepId=$('#${_} #entity_higherDepartId').val();
            		var deptName=$('#${_} #entity_departName').val();
            		var parentElem=$('#navTab div.navTab-panel > div.tab_page[tabid="'+callbackId+'"] #org #'+parentDepId);
            		var ulelem=parentElem.find("> ul");
            		if(ulelem.length>0)
        			{
            			var _html='<li id="'+depId+'">'+deptName+'</li>';
            			ulelem.append(_html);
        			}
            		else
            		{
            			var _html='<ul><li id="'+depId+'">'+deptName+'</li></ul>';
            			parentElem.append(_html);
            		}
        		}
            	$('#navTab div.navTab-panel > div.tab_page[tabid="'+callbackId+'"] #orgChart div.node').unbind();
            	$('#navTab div.navTab-panel > div.tab_page[tabid="'+callbackId+'"] #orgChart li').unbind();
            	$('#navTab div.navTab-panel > div.tab_page[tabid="'+callbackId+'"] #orgChart > div.jOrgChart').contextMenu(false);
            	$('#navTab div.navTab-panel > div.tab_page[tabid="'+callbackId+'"] #orgChart').empty();
            	$('#navTab div.navTab-panel > div.tab_page[tabid="'+callbackId+'"] #org').jOrgChart({
        			chartElement : '#navTab div.navTab-panel > div.tab_page[tabid="'+callbackId+'"] #orgChart',
        			dragAndDrop : false
        		});
            	
            	$.contextMenu({ 
                  selector: '#navTab div.navTab-panel > div.tab_page[tabid="'+callbackId+'"] #orgChart > .jOrgChart > table td.node-cell > div', 
                  callback: function(key, options) {
                      var m = "clicked: " + key;
                      window.console && console.log(m) || alert(m); 
                  },
                  items: {
                      "edit": {name: "修改", icon: "edit"},
                      "add": {name: "添加", icon: "cut"},
                      "delete": {name: "删除", icon: "copy"}
                  }
                });
            }
            closeCurrentPageContainer(obj);
        }
    </script>
	</body>
</html>
