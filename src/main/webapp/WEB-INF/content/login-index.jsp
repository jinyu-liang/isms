<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="com.base.security.SpringSecurityUtils"%>
<%@ taglib prefix="pt" uri="/is-tags"%>
<%
    Object obj = SpringSecurityUtils.getCurrentUser();
    if (obj == null)
    {
        response.sendRedirect("login.action");
    }
    String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<title>智慧工地</title>
<script type="text/javascript">
var basePath="<%=basePath%>";
var CKEDITOR_BASEPATH="<%=basePath%>js/ckeditor/";
var jsessionid="<%=request.getSession(false).getId()%>";
var flexSocketMsg=null;
var msgInteractRemind="1";
</script>
<style type="text/css" media="screen">
object:focus {
    outline: none;
}

#flashContent {
    display: none;
}
</style>
<link href="${basePath}css/dwz/default/style.css" rel="stylesheet" type="text/css" media="screen" />
<link href="${basePath}css/dwz/css/core.css" rel="stylesheet" type="text/css" media="screen" />
<link href="${basePath}css/dwz/css/print.css" rel="stylesheet" type="text/css" media="print" />
<link href="${basePath}css/alerts/jquery.alerts.css" rel="stylesheet" type="text/css" media="screen" />

<link href="${basePath}fileupload/css/swfupload.css" rel="stylesheet" type="text/css" />

<link href="${basePath}js/GalleryView/css/jquery.galleryview-3.0-dev.css" rel="stylesheet" type="text/css" />

<!--[if IE]>
<link href="${basePath}css/dwz/css/ieHack.css" rel="stylesheet" type="text/css" media="screen"/>
<![endif]-->

<!--[if lte IE 9]>
<script src="${basePath}js/dwz/speedup.js" type="text/javascript"></script>
<![endif]-->
<script src="${basePath}js/c.js" type="text/javascript"></script>
<script src="${basePath}js/dwz/dwz.regional.zh.js" type="text/javascript"></script>
<script src="${basePath}js/ckeditor/config.js" type="text/javascript"></script>
<script src="${basePath}js/jquery/jquery.alerts.js" type="text/javascript"></script>
<script type="text/javascript">
    function dealSocketMsg()
    {
        if("RemoteLogin"==flexSocketMsg.result||"SessionOutTime"==flexSocketMsg.result)
        {
            if("RemoteLogin"==flexSocketMsg.result)
            {
                alertMsg.error("异地登录");
            }
            else if("SessionOutTime"==flexSocketMsg.result)
            {
                alertMsg.error("登录超时");
            }
            window.location.href=basePath;
        }
        else if(msgInteractRemind=="1"&&flexSocketMsg.result=="data")
        {
            var msghtml="";
            $.each(flexSocketMsg.data,function(i,o){
                msghtml+="<div class='alertInner' onclick='viewmsginterdetail(this)'>";
                msghtml+="<h1>"+decodeURIComponent(o.theme);
                msghtml+="</h1>";
                msghtml+="<div class='msg'>"+decodeURIComponent(o.msg);
                msghtml+="</div>";
                msghtml+="</div>";
            });
            $("#msgboxfrage").append(msghtml);
            alertMsg.correct("<a class='msginteractinfo' href='javascript:;' onclick='msginteractreceive(0)'>新消息("+flexSocketMsg.data.length+")条</a>",{title:"提示"});
        }
        
        flexSocketMsg=null;
    }
    function socketjsreceiver(msg)
    {
        flexSocketMsg=msg;
        setTimeout('dealSocketMsg();',10);
        if(msg.result=="connectsuccess")
    	{
            $("#${_}login").attr("src",basePath+"msg-interact/online.jpg");
    	}
        else if(msg.result=="data")
        {
            $("#${_}login").attr("src",basePath+"msg-interact/msginfo.gif");
        }
    }
    function connectsocketserver()
    {
        if(document.getElementById("WebMsgClient")==null)
        {
            setTimeout('connectsocketserver()',100);
        }
        else
        {
            try{
                if(document.getElementById("WebMsgClient").socketflexconnect)
                {
                    document.getElementById("WebMsgClient").socketflexconnect('<%=request.getServerName()%>',9999,'{"action":"handle","data":{"JSESSIONID":"<%=request.getSession(false).getId()%>","USERID":"${sessionUser.userId}"}}');
                }
                else
                {
                    setTimeout('connectsocketserver()',50);
                }
            }catch(e){setTimeout('connectsocketserver()',50);}
        }
    }
    function viewmsginterdetail(o)
    {
        $("#msgboxfragewin").slideToggle();
        $(o).remove();
        $('#leftside #sidebar .accordionHeaderpiano:first a[href="msg/msg-interact.action"]').click();
    }
    function msginteractreceive(o)
    {
        if($("#msgboxfrage>div").length>0)
        {
            $("#msgboxfragewin").slideToggle();
        }
        if(o)
        {
            $("#${_}login").attr("src",basePath+"msg-interact/online.jpg");
        }
    }
    $(function() {
        fileuploadtool.load();
        DWZ.init("dwz.frag.xml", {
            loginUrl : basePath+"login.action",// 跳到登录页面
            loginTitle : null, // 弹出登录对话框
            statusCode : {
                ok : 200,
                error : 300,
                timeout : 301
            }, //【可选】
            pageInfo : {
                pageNum : "pageNumber",
                numPerPage : "entity.pageSize",
                orderField : "orderField",
                orderDirection : "orderDirection"
            }, //【可选】
            debug : true, // 调试模式 【true|false】
            callback : function() {
                initEnv();
                /* $("#themeList").theme({
                    themeBase : "css/dwz"
                }); // themeBase 相对于index页面的主题base路径 */
            }
        });
       
        $("#WebMsgClient").ready( function() {
            swfobject.embedSWF(
                "${basePath}msg-interact/WebMsgClient.swf", "flashContent", 
                "0", "0", 
                "11.1.0", "playerProductInstall.swf", 
                {},{quality:"high",
                bgcolor:"#ffffff",
                allowscriptaccess:"sameDomain",
                allowfullscreen:"true"}, {id:"WebMsgClient",
                name:"WebMsgClient",
                align:"middle"});
            swfobject.createCSS("#flashContent", "display:block;text-align:left;");
            connectsocketserver();
        });
    });
    
    function exitConfirm()
    {
    	jConfirm('您确定要退出系统吗？', '退出系统确认', function(r) {
			if(r)
			{
				window.location.href="${basePath}j_spring_security_logout";
			} else {}
		});
    }
  //点击进度年月节点填充搜索框内容
	function exprogressClick(dtStr){
	    var dtArr = dtStr.split("-");
	    var myDate = new Date(dtArr[0],dtArr[1],0);
	    var year = myDate.getFullYear();
	    var month = myDate.getMonth()+1;
	    var lastDay = myDate.getDate();
	    if(month<10){
	    	month="0"+month;
	    }
	    $("#dateSelect").empty();
	    $("#dateSelect").append("<option value='0'>选择日期</option>");
	     for(var i=1;i<=lastDay;i++){
	    	 if(i<10){
			     $("#dateSelect").append("<option value='"+year+"-"+month+"-0"+i+"'>"+year+"年"+month+"月0"+i+"日"+"</option>");
	    	 }else{
	    		 $("#dateSelect").append("<option value='"+year+"-"+month+"-"+i+"'>"+year+"年"+month+"月"+i+"日"+"</option>");
	    	 }
	    	
	    }
	}
  function refreshCurrentNavTabSearch(json){
      if(json.statusCode == DWZ.statusCode.error) {
			if(json.message && alertMsg) alertMsg.error(json.message);
		} else if (json.statusCode == DWZ.statusCode.timeout) {
			if(alertMsg) alertMsg.error(json.message || DWZ.msg("sessionTimout"), {okCall:DWZ.loadLogin});
			else DWZ.loadLogin();
		} else {
			if(json.warnMessage && alertMsg)
			{
				alertMsg.error(json.warnMessage);
			}
			else if(json.infoMessage && alertMsg)
			{
				alertMsg.info(json.infoMessage);
			}
			else if(json.message && alertMsg)
			{
				alertMsg.correct(json.message);
				var $pagerForm = $("#pagerForm", navTab.getCurrentPanel()).get(0)||$("form", navTab.getCurrentPanel()).get(0)||$("#pagerForm", $.pdialog.getCurrent()).get(0)||$("form", $.pdialog.getCurrent()).get(0);
				navTabSearch($pagerForm);
			}
		}
  }
</script>
</head>
<body scroll="no">
    <div style="z-index: 10000">
        <div id="flashContent">
            <p>要查看此页面需要安装Adobe Flash Player11.1.0或以上版本。</p>
            <script type="text/javascript">
        if($.browser.msie)
        {
            document.write("<a href='<%=basePath%>fileupload/Adobe Flash Player forIE_11.9.exe'><img src='<%=basePath%>fileupload/images/get_flash_player.gif'></a>");
        }
        else if($.browser.mozilla)
        {
            document.write("<a href='<%=basePath%>fileupload/Adobe Flash Player Plugin_11.9.exe'><img src='<%=basePath%>fileupload/images/get_flash_player.gif'></a>");
        }
        else
        {
            document.write("<a href='<%=basePath%>fileupload/Adobe Flash Player forIE_11.9.exe'><img src='<%=basePath%>fileupload/images/get_flash_player.gif'>IE</a><a href='<%=basePath%>fileupload/Adobe Flash Player Plugin_11.9.exe'><img src='<%=basePath%>fileupload/images/get_flash_player.gif'>其他</a>");
        }
        </script>
        </div>
    </div>
    <div id="layout">
        <div id="header">
        
            <div class="headerNav">
                <a class="logo" href="http://www.is.com/" target="_blank">标志</a>
                <ul class="nav">
                    <li><s:action name="rst-ver-info!getNewVerInfo" executeResult="true" ignoreContextParams="false" flush="true"
                            namespace="/rst">
                        </s:action></li>
                    <li><font color="#B22400"><span>当前登陆人：<s:property value="%{sessionUser.username}" />
                                <!-- 职位显示(<pt:dictshow name="sessionUser.userId" listValue="dictName" listKey="dictCode" list="userPostList">) -->
                                </pt:dictshow></span></font></li>
                    <li><img id="${_}login" src="${basePath}msg-interact/offline.jpg" style="height: 20px;" onclick="msginteractreceive(1)" />
                    <div class="msgboxfrage" id="msgboxfragewin" style="display: none;">
                        <div id="msgboxfrage" class="info"></div>
                    </div>
                    </li>
                    <li><s:url id="toModiPasswordUrl" namespace="/ggkz" action="ggkz-user-info" method="toModiPassword"></s:url> <s:a
                            href="%{toModiPasswordUrl}" encode="false" width="400" height="280" target="dialog" mask="true">
                            <font color="#B22400">修改密码</font>
                        </s:a></li>
                    <li><a href="#" onclick="exitConfirm();"><font color="#B22400">退出</font></a></li>
                </ul>
                <!--  <ul class="themeList" id="themeList">
                    <li theme="default"><div class="selected">蓝色</div></li>
                    <li theme="green"><div>绿色</div></li>
                    <li theme="purple"><div>紫色</div></li>
                    <li theme="silver"><div>银色</div></li>
                    <li theme="azure"><div>天蓝</div></li>
                </ul> -->
            </div>
        </div>
        <div id="leftside">
            <div id="sidebar_s">
                <div class="collapse">
                    <div class="toggleCollapse">
                        <div></div>
                    </div>
                </div>
            </div>
            <div id="sidebar">
                <div class="toggleCollapse">
                    <h2>
                        <img src="${basePath}image/gndh.png" alt="" style="vertical-align: middle;" /> &nbsp功能导航
                    </h2>
                    <div>收缩</div>
                </div>
                <div class="accordion" fillSpace="sidebar">
                    <div class="accordionHeaderpiano">
                        <a href="msg/msg-interact.action" target="navTab">
                            <h2>
                                <img src="${basePath}image/xxhd.png" alt="" style="vertical-align: middle;" />信息互动
                            </h2>
                        </a>
                    </div>
                    <s:iterator value="%{sessionUser.resource}" var="resource">
                        <s:if
                            test="%{(#resource.higerResourceCode == null || #resource.higerResourceCode == '') && #resource.resourceUrl != null && #resource.resourceUrl != ''}">
                            <div class="accordionHeaderpiano">
                                <a href="<s:property value='%{#resource.resourceUrl}'/>" target="navTab"
                                    rel="<s:property value='%{#resource.resourceId}'/>">
                                    <h2>
                                        <img src="${basePath}<s:property value='%{#resource.note}'/>" alt="" style="vertical-align: middle;" />
                                        <s:property value="%{#resource.resourceName}" />
                                    </h2>
                                </a>
                            </div>
                        </s:if>
                        <s:elseif test="%{(#resource.higerResourceCode == null || #resource.higerResourceCode == '')&&#resource.resourceId=='jdgl'}">
                            <div class="accordionHeader">
                                <h2>
                                    <img src="${basePath}<s:property value='%{#resource.note}'/>" alt="" style="vertical-align: middle;" />
                                    <s:property value="%{#resource.resourceName}" />
                                </h2>
                            </div>
                            <div class="accordionContent">
                                <s:set var="reportMap" value="@com.is.utils.authUtil.JdglTreeUtil@getJDGLTree()"></s:set>
                                <a id="exprogress" href="" target="navTab" onclick="return false;" title=""></a>
                                <s:set var="jdglsubcount" value="#reportMap.keySet().size()"></s:set>
                                <s:set var="jdglsubwsCd" value=""></s:set>
                                <ul class="tree collapse toggle">
                                    <s:iterator value="#reportMap.keySet()" var="key" status="stuts">
                                        <s:if test="#jdglsubcount==#stuts.index+1">
                                            <li class="expand">
                                        </s:if>
                                        <s:else>
                                            <li>
                                        </s:else>
                                        <a
                                            href="/ISMS/rst/d-ex-progress!treeList.action?reportDt=<s:property value='#reportMap.get(#key)[0].wsCd' />"
                                            target="navTab" onclick="return exprogressClick('<s:property value='#reportMap.get(#key)[0].wsCd' />');"
                                            title="<s:property value="#key" />进度报告"> <s:property value="#key" />
                                        </a>
                                        <ul class="zworktree">
                                            <s:iterator value="#reportMap.get(#key)">
                                                <li><a
                                                    href="/ISMS/rst/d-ex-progress!treeList.action?reportDt=<s:property value='wsCd' />&userCd=<s:property value='userCd' />"
                                                    target="navTab" title="<pt:usernameShow userId="userCd" />进度报告"><pt:usernameShow
                                                            userId="userCd" /> </a></li>
                                            </s:iterator>
                                        </ul>
                                        </li>
                                        <s:if test="#jdglsubcount==#stuts.index+1">
                                            <s:set var="jdglsubwsCd" value="#reportMap.get(#key)[0].wsCd"></s:set>
                                        </s:if>
                                    </s:iterator>
                                </ul>

                                <%-- <s:select list="#{2013年11月1日:'2013-11-1',2013年11月2日:'2013-11-2'}"></s:select> --%>
                                <span style="text-align: right; white-space: nowrap;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
                                <s:select id="dateSelect" list="#{'0':'选择日期'}" label="选择日期" listKey="key" listValue="value"></s:select>
                                <script type="text/javascript">exprogressClick('${jdglsubwsCd}');</script>
                            </div>
                        </s:elseif>
                        <s:elseif test="%{#resource.higerResourceCode == null || #resource.higerResourceCode == ''}">
                            <div class="accordionHeader">
                                <h2>
                                    <img src="${basePath}<s:property value='%{#resource.note}'/>" alt="" style="vertical-align: middle;" />
                                    <s:property value="%{#resource.resourceName}" />
                                </h2>
                            </div>
                            <div class="accordionContent">
                                <s:iterator value="%{sessionUser.resource}" var="son">
                                    <ul class="zworktree">
                                        <s:if test="%{#son.higerResourceCode == #resource.resourceId}">
                                            <li><s:if test="%{#son.resourceUrl == ''}">
                                                    <a href="#" onclick="return false;"> <s:property value="%{#son.resourceName}" />
                                                    </a>
                                                </s:if> <s:else>
                                                    <a href="<s:property value='%{#son.resourceUrl}'/>" target="navTab"
                                                        rel="<s:property value='%{#son.resourceId}'/>"> <s:property value="%{#son.resourceName}" />
                                                    </a>
                                                </s:else></li>
                                        </s:if>
                                    </ul>
                                </s:iterator>
                            </div>
                        </s:elseif>
                    </s:iterator>
                </div>
            </div>
        </div>
        <div id="container">
            <div id="navTab" class="tabsPage">
            
                <div class="tabsPageHeader">
                    <div class="tabsPageHeaderContent">
                        <!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
                        <ul class="navTab-tab">
                            <li tabid="main" class="main"><a href="javascript:;"><span><span class="home_icon">我的主页</span></span></a></li>
                        </ul>
                    </div>
                    <div class="tabsLeft">left</div>
                    <!-- 禁用只需要添加一个样式 class="tabsLeft tabsLeftDisabled" -->
                    <div class="tabsRight">right</div>
                    <!-- 禁用只需要添加一个样式 class="tabsRight tabsRightDisabled" -->
                    <div class="tabsMore">more</div>
                </div>
                <ul class="tabsMoreList">
                    <li><a href="javascript:;">我的主页</a></li>
                </ul>
               
                <div class="navTab-panel tabsPageContent layoutBox">
                    <div id="pms_main_page" class="page unitBox" style="overflow: auto;">
                        <s:action name="main-index" executeResult="true" ignoreContextParams="false" flush="true" namespace="/">
                        </s:action>
                    </div>
                </div>
            </div>
        </div>
    </div>
     
    <script>
    //搜索框change事件
    	$(function(){
    		$("#dateSelect").change(function(){
    			var selectVal = $('#dateSelect').val();
    			if(selectVal==0){
    				return false;
    			}
    			$("#exprogress").attr("href","/ISMS/rst/d-ex-progress!treeList.action?reportDt="+selectVal);
    			$("#exprogress").attr("title",selectVal+"进度报告").click();
    		});
    	});
    </script>
</body>
</html>