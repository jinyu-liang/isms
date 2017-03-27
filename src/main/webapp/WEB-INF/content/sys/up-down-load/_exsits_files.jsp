
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<div>
    <s:iterator value="sysAttachList">
        <s:if test="fileShowType == 1">
            <a id="${attachId}" href="javascript:;" class="anatomy display" onclick="fileuploadtool.deleteUpFile('${attachPath}/${attachId}',this);">
                <span>${attachName}&nbsp;&nbsp;(${fileSize}kb)</span>
                <span class="del">删除</span>
            </a>
        </s:if>
        <s:elseif test="fileShowType == 2">
            <s:a namespace="/sys" action="sys-attach" method="downFile" cssClass="anatomy display">
                <s:param name="sysAttachentity.attachId" value="attachId" />
                <span><s:property value="attachName" />&nbsp;&nbsp;(<s:property value="fileSize" />kb)</span>
                <span class="down">下载</span>
            </s:a>
        </s:elseif>
        <s:elseif test="fileShowType == 3">
        <span class="anatomy display del">
            ${attachName}&nbsp;&nbsp;(${fileSize}kb)
            <a id="${attachId}" href="javascript:;"  onclick="fileuploadtool.deleteUpFile('${attachPath}/${attachId}',this);">
                <span class="del">删除</span>
            </a>
            <s:a namespace="/sys" action="sys-attach" method="downFile" >
                <s:param name="sysAttachentity.attachId" value="attachId" />
                <span class="down">下载</span>
            </s:a>
        </span>
        </s:elseif>
    </s:iterator>
</div>
