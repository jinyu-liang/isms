<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pt" uri="/is-tags"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>外线人员列表</title>
</head>
<style type="text/css">


body {
font: normal 11px auto "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif;
color: #000000;
background: #E6EAE9;
}

a {
}

#mytable {
width: 100%;
padding: 0;
margin: 0;
border-collapse:collapse;
}

caption {
padding: 0 0 5px 0;
width: 700px;
font: italic 11px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif;
text-align: right;
}

th {
font: bold 11px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif;
color: #4f6b72;
border-right: 1px solid #C1DAD7;
border-bottom: 1px solid #C1DAD7;
border-top: 1px solid #C1DAD7;
letter-spacing: 2px;
text-transform: uppercase;
text-align: left;
padding: 6px 6px 6px 12px;
background: #CAE8EA  no-repeat;
}

th.nobg {
border-top: 0;
border-left: 0;
border-right: 1px solid #C1DAD7;
background: none;
}

td {
border-right: 1px solid #C1DAD7;
border-bottom: 1px solid #C1DAD7;
background: #fff;
font-size:11px;
padding: 6px 6px 6px 12px;
color: #4f6b72;
}


td.alt {
background: #F5FAFA;
color: #797268;
}

th.spec {
border-left: 1px solid #C1DAD7;
border-top: 0;
background: #fff no-repeat;
font: bold 10px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif;
}

th.specalt {
border-left: 1px solid #C1DAD7;
border-top: 0;
background: #f5fafa no-repeat;
font: bold 10px "Trebuchet MS", Verdana, Arial, Helvetica, sans-serif;
color: #797268;
}
/*---------for IE 5.x bug*/
html>body td{ font-size:11px;}
body,td,th {
font-family: 宋体, Arial;
font-size: 14px;
}
</style>
<!-- <p style="width: 380px;"/>
<p style="width: 380px;"/>
<p style="width: 380px;"/>
<p style="width: 380px;"/> -->

<!-- <p > -->
<s:form method="post" action="m-workshop!perinfoListLiew"
		cssStyle="height:100%;width:100%;"
		onsubmit="return navTabSearch(this);" id="%{_}">
		<s:hidden name="queryEntity.wsCd" />
    <table id="mytable" cellspacing="0">
            <tr>
                <td><font style="font-weight:bold;">工地名称:</font></td>
                <td scope="col"><s:property value="entity.wsNm"/></td>
                <td scope="col"><font style="font-weight:bold;">订单号:</font></td>
                <td scope="col"><s:property value="orderNo"/></td>
                <td scope="col"><font style="font-weight:bold;">带队人员:</font></td>
                <td scope="col">
                <pt:usernameShow userId="entity.managerUserId"></pt:usernameShow>
                </td>
                <td scope="col"><font style="font-weight:bold;">联系电话:</font></td>
                <td scope="col"><s:property value="entity.mobileTel" /></td>
            </tr>
    </table>
    <s:iterator value="expersonList">
       <!--  <div style="border-style:solid"> -->
            <table id="mytable" cellspacing="0">
                <tr >
                    <td>施工队：</td>
                    <td scope="col"><s:property value="teamNm"/></td>
                    <td scope="col">电话：</td>
                    <td scope="col"><s:property value="mobileTel"/></td>
                    <td scope="col">身份证号:</td>
                    <td scope="col"><s:property value="identyCardCode"/></td>
                    <td scope="col">最近更新日期:</td>
                    <td scope="col"><s:date name="updateDate" format="yyyy-MM-dd"/></td>
                </tr>
            </table>
            <table class="list" style="width:100%">
                <thead>
                    <tr>
                        <th>参保状态</th>
                        <th>姓名</th>
                        <th>联系电话</th>
                        <th style="width: 19%">身份证号</th>
                        <th>主要工种</th>
                        <th>加入/撤消</th>
                        <th>参保工地</th>
                        <th>备注</th>
                    </tr>
                </thead>
                <tbody>
                     <s:iterator value="exPersonList">
                        <tr>
                            <td><s:if test="flag==null || flag==''">
                                <s:if test="wsCd==null || wsCd==''">剪出</s:if>
                                <s:elseif test='statusCd=="0"'>无保</s:elseif>
                                <s:elseif test='statusCd=="1"'>在保</s:elseif>
                                </s:if>
                                <s:else>
                                 <s:if test='flag=="0"'><font style="font-weight:bold;color: #c75f3e">(申请)撤保</font></s:if>
                                <s:elseif test='flag=="1"'><font style="font-weight:bold;color: #c75f3e">(申请)加保</font></s:elseif>
                                </s:else>
                            </td>
                            <td><s:property value="name" />
                            </td>
                            <td><s:property value="telephone" />
                            </td>
                            <td><s:property value="identyCardCode" />
                            </td>
                            <td><s:property value="workTypeName" />
                            </td>
                            <td>
                                <s:if test='flag=="0"'><font style="font-weight:bold;color: #c75f3e">撤保</font></s:if>
                                <s:elseif test='flag=="1"'><font style="font-weight:bold;color: #c75f3e">加保</font></s:elseif>
                            </td>
                            <td><s:property value="wsNm" />
                            </td>
                            <td colspan="2"><s:property value="memo" />
                            </td>
                        </tr>
                     </s:iterator>  
                </tbody>
            </table>
     </s:iterator>
<!-- </p> -->

</s:form>

</body>
</html>

