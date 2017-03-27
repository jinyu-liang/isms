package com.is.ggkz.action;

import org.apache.struts2.convention.annotation.Namespace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.base.mybatis.BaseStruts2Action;

/*
 *
 */
@Namespace("/ggkz")
public class GgkzUserWorkgroupToolAction extends BaseStruts2Action
{
    private static final long   serialVersionUID = 1L;

    @SuppressWarnings("unused")
    private static final Logger LOGGER           = LoggerFactory.getLogger(GgkzUserWorkgroupToolAction.class);

    public GgkzUserWorkgroupToolAction()
    {
        super();
    }

    /**
     * 调用控件的dom元素ID
     */
    private String callbackId;

    /**
     * 控件填充key的dom元素ID
     */
    private String checkid;

    /**
     * 班组类别ID
     */
    private String checkTp;

    /**
     * 控件填充value的dom元素id
     */
    private String checkname;

    /**
     * 控件类别填充dom元素ID
     */
    private String toolTpid;

    /**
     * 单选用户列表
     *
     * @return
     * @throws Exception
     */
    public String publicUserWorkgroupChooser()
    {
        return "ggkz-user-workgroup-tool/chooser";
    }

    public String getCallbackId()
    {
        return callbackId;
    }

    public void setCallbackId(String callbackId)
    {
        this.callbackId = callbackId;
    }

    public String getCheckid()
    {
        return checkid;
    }

    public void setCheckid(String checkid)
    {
        this.checkid = checkid;
    }

    public String getCheckTp()
    {
        return checkTp;
    }

    public void setCheckTp(String checkTp)
    {
        this.checkTp = checkTp;
    }

    public String getCheckname()
    {
        return checkname;
    }

    public void setCheckname(String checkname)
    {
        this.checkname = checkname;
    }

    public String getToolTpid()
    {
        return toolTpid;
    }

    public void setToolTpid(String toolTpid)
    {
        this.toolTpid = toolTpid;
    }

}
