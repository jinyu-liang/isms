<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>版本维护</title>
</head>
<body>
    <div class="page unitBox">
        <s:property value="message" escapeHtml="false" />
        <div class="pageContent">
            <s:form method="post" action="rst-ver-info!verConf" cssClass="pageForm"
                onsubmit="return validateCallback(this,$.pdialog.closeCurrentRefresh);" id="%{_}">
                <s:hidden name="queryEntity.verId"></s:hidden>
                <div>
	                <div class="pageFormContent" layoutH="56" style="float: left; width: 450px;">
	                    <s:include value="_edit.jsp" />
	                </div>
	                <div style="float: right; width: 525px;" id="${_}historylist">
						<s:include value="list.jsp" />
					</div>
				</div>
                <div class="formBar">
                    <ul>
                    		<li>
								<div class="buttonActive">
									<div class="buttonContent">
										<s:submit type="button" value="确定" />
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
            <s:form namespace="/rst" method="post" action="rst-ver-info!list" id="%{_}history">
            <s:hidden name="_"></s:hidden>
            </s:form>
        </div>
    </div>
    <script type="text/javascript">
    function versionctrl(json,f,r)
    {
    	if (json.statusCode == DWZ.statusCode.error) {
            if (json.message && alertMsg) alertMsg.error(json.message);
        }
        else if (json.statusCode == DWZ.statusCode.timeout) {
            if (alertMsg) alertMsg.error(json.message || DWZ.msg("sessionTimout"), {
                        okCall : DWZ.loadLogin
                    });
            else DWZ.loadLogin();
        }
        else {
            if (json.warnMessage && alertMsg) {
                alertMsg.error(json.warnMessage);
            }
            else if (json.infoMessage && alertMsg) {
                alertMsg.info(json.infoMessage);
            }
            else if (json.message && alertMsg) {
                alertMsg.correct(json.message);
                divSearch(f,r);
            }
        }
    }
    </script>
</body>
</html>
