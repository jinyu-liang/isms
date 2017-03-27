<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title>欢迎访问智慧工地-现场管理系统</title>
	<!--[if lt IE 9]>
	<script src="js/html5/html5.js"></script>
    <![endif]-->
	<!--[if lt IE 7]>
	<script src="js/iepng/iepng.js" type="text/javascript"></script>
	<script type="text/javascript">
	    EvPNG.fix('div, ul, img, li, input'); //EvPNG.fix('包含透明PNG图片的标签'); 多个标签之间用英文逗号隔开。
    </script>
	<![endif]-->
    <style type="text/css">
    html{background-image:none}label.iPhoneCheckLabelOn span{padding-left:0px}#versionBar{background-color:#212121;position:fixed;width:100%;height:35px;bottom:0;left:0;text-align:center;line-height:35px;z-index:11;-webkit-box-shadow:black 0px 10px 10px-10px inset;-moz-box-shadow:black 0px 10px 10px-10px inset;box-shadow:black 0px 10px 10px-10px inset}.copyright{text-align:center;font-size:10px;color:#CCC}.copyright a{color:#A31F1A;text-decoration:none}#login.logo{width:500px;height:51px}
    </style>
    <s:url value="/css/login/login.css" id="login_css"></s:url>
    <link href="${login_css}" media="screen" rel="stylesheet" type="text/css" />
    <s:url value="/css/button/buttons.css" id="buttons_css"></s:url>
    <link href="${buttons_css}" media="screen" rel="stylesheet" type="text/css" />
    <s:url value="/css/tipsy/tipsy.css" id="tipsy_css"></s:url>
    <link href="${tipsy_css}" media="screen" rel="stylesheet" type="text/css" />
</head>
<body class="login_bodybg">
	<div class="login_bk">
        <div id="alertMessage"></div>
        <div id="successLogin"></div>
        <div class="text_success">
            <img src="css/login/images/loader_green.gif" alt="Please wait" />
            <span>登陆成功!请稍后....</span>
        </div>
        <div id="login">
    	    <div class="ribbon" style="background-image:url(css/login/images/typelogin.png);"></div>
    	    <div class="inner">
    		    <div class="logo">
    		        <img src="css/login/images/toplogo-jeecg.png"/>
    		    </div>
    		    <div class="formLogin">
    		        <form name="formLogin" id="formLogin" action="${pageContext.request.contextPath}/login!index.action" check="${pageContext.request.contextPath}/j_spring_security_check" method="post">
    		            <div class="tip">
    		                <input class="userName" name="j_username" type="text" id="j_username" title="用户名" iscookie="true" value=""  nullmsg="请输入用户名!"/>
    		            </div>
    		            <div class="tip">
    		                <input class="password" name="j_password" type="password" id="j_password" title="密码" value="" nullmsg="请输入密码!"/>
    		            </div>
    		            <div class="loginButton">
    		                <div style="float: left; margin-left: 15px;">
    			                <input type="checkbox" id="on_off" name="_spring_security_remember_me" checked="ture" class="on_off_checkbox" value="0" />
    			                <span class="f_help">是否记住用户名 ?</span>
    		            	</div>
    			            <div style="float: right; padding: 3px 0; margin-right: 15px;">
    			                <div>
    			                    <ul class="uibutton-group">
    			                        <li>
    			                            <a class="uibutton normal" href="#" id="but_login">登陆</a>
    			                        </li>
    			                        <li>
    			                            <a class="uibutton normal" href="#" id="forgetpass">重置</a>
    			                        </li>
    			                    </ul>
    			                </div>
    			                <div>
    			                <li>
    			                <s:action name="rst-ver-info!getNewVerInfo" executeResult="true" ignoreContextParams="false" flush="true"
                            namespace="/rst">
                        </s:action>
    			                </li>
    			                </div>
    			            </div>
    			            <div class="clear"></div>
    		        	</div>
    		        </form>
    		    </div>
    	    </div>
        	<div class="shadow"></div>
        </div>
        <div class="clear"></div>
        <div id="versionBar">
    	    <div class="copyright">
    	        &copy; 使用者
    	        <span class="tip"><a href="#" title="IS">IS公司</a> (推荐使用IE8+,谷歌浏览器可以获得更快,更安全的页面响应速度)技术支持:<a href="#" title="IS">IS软件开发部</a></span>
    	    </div>
        </div>
    </div>
    <s:url value="/js/jquery/jquery-1.7.2.min.js" id="jquery_lib"></s:url>
	<script type="text/javascript" src="${jquery_lib}"></script>
    <s:url value="/js/jquery/jquery.jrumble.1.3.min.js" id="jquery_jrumble"></s:url>
    <script type="text/javascript" src="${jquery_jrumble}"></script>
	<s:url value="/js/jquery/jquery.cookie.js" id="jquery_cookie"></s:url>
	<script type="text/javascript" src="${jquery_cookie}"></script>
	<s:url value="/js/jquery/jquery.tipsy.js" id="jquery_tipsy"></s:url>
	<script type="text/javascript" src="${jquery_tipsy}"></script>
	<s:url value="/js/iphone/iphone.check.js" id="iphone_check"></s:url>
	<script type="text/javascript" src="${iphone_check}"></script>
	<s:url value="/js/login/login.js" id="login"></s:url>
	<script type="text/javascript" src="${login}"></script>
</body>
</html>