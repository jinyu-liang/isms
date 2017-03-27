<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pt" uri="/is-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>发料计划表回复</title>
</head>
<body>
     <div>
                                            <div>
                                                <br /> <span><font style="font-size: 12pt;font-weight: bolder;">我的回复</font>  <s:hidden name="record.replyId"
                                                        value="%{record.replyId}" />  
                                            </div>
                                            <br />
                                            <div>
                                                <label>上次回复时间:</label> <span> <s:date name="record.updateTm" format="yyyy-MM-dd  HH:mm:ss" />
                                                </span>

                                            </div>
                                            <br />
                                            <div>
                                                <label>标题:</label> <span> <s:textfield name="record.title" id="record_title"
                                                        maxlength="80" /> </span>
                                            </div>
                                            <br />
                                            <div>
                                                <label>内容:</label> <span> <s:textarea name="record.content" cols="45" rows="3"
                                                        id="record_content" maxlength="400" /> </span>

                                            </div>
                                        </div>
                                        <div style="border-top: 1px solid #000000; margin-top: 12px; margin-bottom: 12px;"></div>
                                        <!-- 全部回复 -->
                                        <div >
                            <label>全部回复：</label><br />
                            <s:iterator value="recordPage.data">
                                <div>
                                    <span>回复者：<pt:usernameShow userId="replyUserCd"></pt:usernameShow>
                                    </span>
                                </div>
                                <div>
                                    <span>更新时间：<s:date name="updateTm" format="yyyy-MM-dd  HH:mm:ss" />
                                    </span>
                                </div>
                                <div>
                                    <span>标题：<s:property value="title" />
                                    </span>
                                </div>
                                <div>
                                    <span>内容：<s:property value="content" />
                                    </span>
                                </div>
                                <div style="border-top: 1px dashed #000000; margin-top: 12px; margin-bottom: 12px;"></div>
                            </s:iterator>
                            </div>
                       
</body>
</html>
