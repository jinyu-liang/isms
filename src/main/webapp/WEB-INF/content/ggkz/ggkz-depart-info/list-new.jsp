<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pt" uri="/is-tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>用户信息列表</title>
<s:url var="_url" value="/css/jquery.jOrgChart.css"></s:url>
<link rel="stylesheet" href="${_url}"/>
<s:url var="_url" value="/css/custom.css"></s:url>
<link rel="stylesheet" href="${_url}"/>
<s:url var="_url" value="/css/jquery.contextMenu.css"></s:url>
<link rel="stylesheet" href="${_url}"/>
    
<s:url var="_url" value="/js/prettify.js"></s:url>
<script src="${_url}" type="text/javascript"></script>
<s:url var="_url" value="/js/jquery.contextMenu.js"></s:url>
<script src="${_url}" type="text/javascript"></script>
<s:url var="_url" value="/js/jquery.jOrgChart.js"></s:url>
<script src="${_url}" type="text/javascript"></script>
<s:url var="add_url" namespace="/ggkz" action="ggkz-depart-info" method="toAdd">
    <s:param name="callbackId" value="%{_}"></s:param>
</s:url>
<s:url var="delete_url" namespace="/ggkz" action="ggkz-depart-info" method="delete">
    <s:param name="callbackId" value="%{_}"></s:param>
</s:url>
<s:url var="update_url" namespace="/ggkz" action="ggkz-depart-info" method="toUpdate">
    <s:param name="callbackId" value="%{_}"></s:param>
</s:url>
<s:url var="_url" namespace="/ggkz" action="ggkz-depart-info" method="toAdd">
    <s:param name="callbackId" value="%{_}"></s:param>
</s:url>
<script type="text/javascript" language="javascript">
	jQuery(document).ready(function() {
		$("#org").jOrgChart({
			chartElement : '#orgChart',
			dragAndDrop : true
		});
		if($("#orgChart > div.jOrgChart > table").length==0)
		{
			$.contextMenu({
		          selector: '#orgChart > div.jOrgChart' , 
		          callback: function(key, options) {
		        	  if("add"==key)
	        		  {
		        		  var href="${add_url}"+"&entity.departId=0";
		        		  $("#createdepart").attr("href",href).trigger("click");
	        		  } 
		          },
		          items: {
		              "add": {name: "创建主部门", icon: "cut"}
		          }
		      });
		}
		else
		{
			$.contextMenu({ 
    	          selector: '#orgChart > .jOrgChart > table td.node-cell > div', 
    	          callback: function(key, options) {
    	        	  var triggerid= options.$trigger.attr('id');
    	        	  if("add"==key)
	        		  {
    	        		  var href="${add_url}"+"&entity.higherDepartId="+triggerid;
		        		  $("#createdepart").attr("href",href).trigger("click");
	        		  }
    	        	  else if("delete"==key)
	        		  {
		        		  var href="${delete_url}"+"&entity.departId="+triggerid;
		        		  $("#deletedepart").attr("href",href).trigger("click");
	        		  } 
		        	  else if("edit"==key)
	        		  {
		        		  var href="${update_url}"+"&entity.departId="+triggerid;
		        		  $("#createdepart").attr("href",href).trigger("click");
	        		  } 
    	          },
    	          items: {
    	              "add": {name: "添加", icon: "add"},
    	              "delete": {name: "删除", icon: "delete"},
    	              "edit": {name: "修改", icon: "edit"}
    	          }
    	      });
		}
	});
</script>
</head>
<body>
<s:form method="post" action="ggkz-depart-info!list.action" cssStyle="height:100%;width:100%;" id="%{_}">
    <s:a id="createdepart" nameSpace="/ggkz" action="ggkz-depart-info" method="toAdd" target="dialog" mask="true" title="添加部门" width="500" height="200" cssClass="add">
   <span>添加部门</span>
    </s:a>
    <s:a id="deletedepart" namespace="/ggkz" action="ggkz-depart-info" method="delete" title="是否删除部门?" target="navTabTodo" encode="false" callback="function refreshCurrentPageContainer(){navTabSearch('${_}');}" cssClass="delete">
    <s:param name="entity.roleId" value="'{list_data_roleId}'"/>
				<span>删除角色</span>
    </s:a>
    ${orgChart}
    <div id="orgChart" class="orgChart"></div>
    <script>
         jQuery(document).ready(function() {
            $("#show-list").click(function(e){
                e.preventDefault();
                $('#list-html').toggle('fast', function(){
                    if($(this).is(':visible')){
                        $('#show-list').text('Hide underlying list.');
                        $(".topbar").fadeTo('fast',0.9);
                    }else{
                        $('#show-list').text('Show underlying list.');
                        $(".topbar").fadeTo('fast',1);                  
                    }
                });
            });
            
            $('#list-html').text($('#org').html());
            
            $("#org").bind("DOMSubtreeModified", function() {
                $('#list-html').text('');
                
                $('#list-html').text($('#org').html());
                
                prettyPrint();                
            });
        }); 
    </script>
    </s:form>
</body>
</html>
