<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="com.base.security.SpringSecurityUtils"%>
<%@ taglib prefix="pt" uri="/is-tags"%>
<!DOCTYPE html>
<HTML>
<HEAD>
	<TITLE> ZTREE DEMO - radio</TITLE>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
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
    
    function onClick(event, treeId, treeNode, clickFlag) {
		   var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
			var nodes = treeObj.getChangeCheckedNodes();
			var json = JSON.stringify(nodes);
			alert(json);
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
</HEAD>

<BODY>
	<div class="page" id="${_}">
			<div class="pageContent">
								<div class="content_wrap">
	<div class="zTreeDemoBackground left">
		<ul id="treeDemo" class="ztree"></ul>
	</div>
	</div>
					<div class="formBar">
							<ul>
								<li>
									<div class="buttonActive">
										<div class="buttonContent">
											<button type="submit" id="subform" onclick="onClick()">确定</button>
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
			

<s:url id="gettreeUrl" namespace="/ggkz" action="ggkz-depart-info" method="getroletree">
</s:url>
<s:hidden name="parrentid" id="parrentid" />
<s:hidden name="parrentname" id="parrentname" />

<s:hidden id="entity_depdns" name="entity.depdns"></s:hidden>
<s:hidden id="entity_depdn" name="entity.depdn"></s:hidden>


<s:hidden name="_"></s:hidden>
<s:hidden name="callbackId"></s:hidden>
<s:hidden name="checkid"></s:hidden>
<s:hidden name="checkname"></s:hidden>
<s:hidden name="toolTpid"></s:hidden>
</BODY>
</HTML>