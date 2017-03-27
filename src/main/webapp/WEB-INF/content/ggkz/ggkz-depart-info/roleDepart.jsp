<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pt" uri="/is-tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>部门信息列表</title>
		<link rel="stylesheet" href="http://127.0.0.1:8778/ISMS/css/ztree/demo.css" type="text/css">
	<link rel="stylesheet" href="http://127.0.0.1:8778/ISMS/css/ztree/zTreeStyle/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="http://127.0.0.1:8778/ISMS/js/ztree/jquery.ztree.core.js"></script>
	<script type="text/javascript" src="http://127.0.0.1:8778/ISMS/js/ztree/jquery.ztree.excheck.js"></script>
 

	<SCRIPT type="text/javascript">
		<!--		
		var setting = {
		check: {
				enable: true,
				chkStyle: "checkbox",
				chkboxType: { "Y": "s", "N": "ps" }
			},
        data: {
            simpleData: {
                enable: true
            }
        },
        callback: {
        		onClick: cuizhang	
			}
			
    };
     
    function createTree () {
        var zNodes;
        $.ajax({
            url: '/ISMS/ggkz/ggkz-depart-info!getroletree.action', //url  action是方法的名称
            type: 'POST',
            dataType: "json", //可以是text，如果用text，返回的结果为字符串；如果需要json格式的，可是设置为json
            ContentType: "application/json; charset=utf-8",
            success: function(data) {
                zNodes = data.jsondata;
                $.fn.zTree.init($("#treeDemo"), setting, eval('[' + zNodes + ']'));
            },
            error: function(msg) {
                alert("获取部门信息失败11");
            }
        });
    }
    
    function onClick1(event, treeId, treeNode, clickFlag) {
		   var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
			var nodes = treeObj.getChangeCheckedNodes();
			var json = JSON.stringify(nodes);
			$.ajax({
            url: '/ISMS/ggkz/ggkz-depart-info!roleupdate.action', //url  action是方法的名称
            type: 'POST',
            data:{'entity.higherDepartId':json},
            dataType: "json", //可以是text，如果用text，返回的结果为字符串；如果需要json格式的，可是设置为json
            ContentType: "application/json; charset=utf-8",
            success: function(data) {
               alert("设置成功");
                
            },
            error: function(msg) {
                alert("获取部门信息失败");
            }
        });
	}	
	
	function cuizhang(event, treeId, treeNode, clickFlag) {
			alert(treeNode.target);
			$("#entity_name").val(treeNode.target);
			
	}

    $(document).ready(function() {
        createTree();
    });
    
    function closewd()
    {    	
    			var containerId = "${callbackId}";
				var idzone = "${checkid}";
				var namezone = "${checkname}";
				var ids = $("#parrentid").attr("value"); 
				var names =$("#parrentname").attr("value"); 
				var depns = $("#entity_depdns").attr("value"); 
				var depn = $("#entity_depdn").attr("value"); 
					try{
						if(containerId != ''){
							$("#"+ containerId + ",#entity_higherDepartId").val(ids);
							$("#"+ containerId + ",#entity_depdns").val(depns);
							$("#"+ containerId + ",#entity_depdn").val(depn);
							$("#"+ containerId + ",#entity_departfullname").val(names);
							if("${toolTpid}"!="")
							{
								$("#"+ containerId + ",#${toolTpid}").val("user");
							}
						}
					}catch(e){}

				$.pdialog.closeCurrent();
    }
	</SCRIPT>
    </head>
    <body>
		<s:form method="post" action="ggkz-depart-info!list.action" onsubmit="return navTabSearch(this);" cssStyle="height:100%;width:100%;" id="%{_}">
			<div class="page" style="height:100%;width:100%;">
			
				<div class="layout">
					<div region="north" height="93">
					<s:property value="message" escapeHtml="false"/>
						<div class="pageHeader">
							<s:hidden name="entity.name" id="entity_name" />
						</div>
						<div class="pageContent">
							<div class="panelBar">
								<ul class="toolBar">
									<li>
										<s:a nameSpace="/rst" action="ex-person-info-pay" method="cuizhang" encode="false" cssClass="edit" target="dialog" mask="true" width="700" height="500" title="催帐"  >
											<s:param name="entity.name" value="'{entity_name}'"/>
											<span>催办账款</span>
										</s:a>
									</li>
									<li class="line">line</li>
								</ul>
							</div>
						</div>
					</div>
					<div region="center">
					<div class="zTreeDemoBackground left">
						<ul id="treeDemo" class="ztree"></ul>
					</div>
					</div>
				
					<div region="south" height="26">
						<div class="formBar">
							<ul>
								<li>
									<div class="buttonActive">
										<div class="buttonContent">
											<button type="submit" id="subform" onclick="onClick1()">确定</button>
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
					</div>
				</div>
			</div>
		</s:form>
	</body>
</html>