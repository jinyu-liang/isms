<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pt" uri="/is-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<div
    style="float: left; display: block; margin: 10px; overflow: auto; width: 180px; height: 400px; border: solid 1px #CCC; line-height: 21px; background: #FFF;">
    <ul class="tree">
        <!-- <li><a href="" target="navTab">报告分类</a>
            <ul>
                <li><a href="main.html" target="navTab" rel="main">我的主页</a></li>
                <li><a href="newPage1.html" target="navTab" rel="page1">页面一</a></li>
                <li><a href="newPage2.html" target="navTab" rel="page1">替换页面一</a></li>
                <li><a href="newPage2.html" target="navTab" rel="page2">页面二</a></li>
                <li><a href="newPage3.html" target="navTab" rel="page3" title="页面三（自定义标签名）">页面三</a></li>
            </ul>
        </li>
        <li><a href="w_panel.html" target="navTab" rel="w_panel">面板</a></li>
        <li><a href="w_tabs.html" target="navTab" rel="w_tabs">选项卡面板</a></li>
        <li><a href="w_dialog.html" target="navTab" rel="w_dialog">弹出窗口</a></li>
        <li><a href="w_alert.html" target="navTab" rel="w_alert">提示窗口</a></li>
        <li><a href="w_table.html" target="navTab" rel="w_table">表格容器</a></li>
        <li><a href="w_tree.html" target="navTab" rel="w_tree">树形菜单</a></li>
        <li><a href="w_editor.html" target="navTab" rel="w_editor">编辑器</a></li> -->

        <s:iterator value="reportMap.keySet()" id="key" status="stuts">
            <s:property value="#stuts.index+1" />
            <ul class="zworktree">
                <li><a href="" target="navTab"><s:property value="#key" /></a>
                    <ul>
                        <s:iterator value="reportMap.get(#key)">
                            <li><a href="main.html" target="navTab" rel="main"><pt:usernameShow userId="userCd" /> </a></li>
                        </s:iterator>
                    </ul></li>
            </ul>
        </s:iterator>
    </ul>


</div>