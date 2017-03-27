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
	<script type="text/javascript" src="http://127.0.0.1:8778/ISMS/js/ztree/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="http://127.0.0.1:8778/ISMS/js/ztree/jquery.ztree.core.js"></script>
	<script type="text/javascript" src="http://127.0.0.1:8778/ISMS/js/ztree/jquery.ztree.excheck.js"></script>
 

	<SCRIPT type="text/javascript">
		<!--		
		var setting = {
        data: {
            simpleData: {
                enable: true
            }
        },
			callback: {
				onClick: onClick
			}
    };
     
    function createTree () {
        var zNodes;
        $.ajax({
            url: '/ISMS/ggkz/ggkz-depart-info!gettree.action', //url  action是方法的名称
            type: 'POST',
            dataType: "json", //可以是text，如果用text，返回的结果为字符串；如果需要json格式的，可是设置为json
            ContentType: "application/json; charset=utf-8",
            success: function(data) {
                zNodes = data.jsondata;
                $.fn.zTree.init($("#treeDemo"), setting, eval('[' + zNodes + ']'));
            },
            error: function(msg) {
                alert("获取部门信息失败");
            }
        });
    }
    
    function onClick(event, treeId, treeNode, clickFlag) {
			$("#parrentid").val(treeNode.id);
			$("#parrentname").val(treeNode.name);
		}	

    $(document).ready(function() {
    alert(1);
        createTree();
    });
    
    function closewd()
    {    	
    	var containerId = "${callbackId}";
				var idzone = "${checkid}";
				var namezone = "${checkname}";
				var ids = "";
				var names = "";
				var checkojb = $("#${_} :radio[checked]");
				if($(checkojb).val() != undefined){
					var idname = $(checkojb).val().split("|");
					ids += idname[0];
					names += idname[1];
					try{
						if(containerId != ''){
							$("#"+ containerId + ",#" + idzone).val(ids);
							$("#"+ containerId + ",#" + namezone).val(names);
							if("${toolTpid}"!="")
							{
								$("#"+ containerId + ",#${toolTpid}").val("user");
							}
						}
					}catch(e){}
				}

				$.pdialog.closeCurrent();
    }
	</SCRIPT>
</HEAD>

<BODY>
<div class="content_wrap">
	<div class="zTreeDemoBackground left">
		<ul id="treeDemo" class="ztree"></ul>
	</div>
</div>
<input type="button" value="ok" onclick="closewd()">
<s:url id="gettreeUrl" namespace="/ggkz" action="ggkz-depart-info" method="gettree">
</s:url>
<s:hidden name="parrentid" id="parrentid" />
<s:hidden name="parrentname" id="parrentname" />
<s:hidden name="_"></s:hidden>
<s:hidden name="callbackId"></s:hidden>
<s:hidden name="checkid"></s:hidden>
<s:hidden name="checkname"></s:hidden>
<s:hidden name="toolTpid"></s:hidden>
</BODY>
</HTML>