package com.is.pms.msginteract.entity;

import java.util.Date;
import java.util.List;

import com.base.dict.UserUtil;
import com.base.mybatis.AbstractBaseEntity;
import com.is.sys.entity.SysAttach;
import com.is.sys.util.SysAttachUtil;

/**
 * 
 * <p>文件名称: MsgMessageInfo.java</p>
 * <p>文件描述: 消息互动消息实体</p>
 * <p>版权所有: 版权所有(C)2013-2020</p>
 * <p>公   司: IS软件事业部</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2014年9月18日</p>
 * <p>修改记录0：无</p>
 * @version 1.0
 * @author  
 */
public class MsgMessageInfo extends AbstractBaseEntity
{

    private static final long serialVersionUID      = 1L;

    private String            id;

    private String            sendid;

    private String            receiver;

    private String            owner;

    private String            receivername          = "";

    private String            sender;

    private String            sendername;

    private String            theme;

    private String            attachment;

    private List<SysAttach>   attachmentobj;

    private String            body;

    private String            bodysub;

    private Date              sendtime;

    private String            flag;

    private String            post;

    public static String      READ                  = "1";

    public static String      UNREAD                = "0";

    public static String      MODELFILEDIRPATH      = "msg";

    public static String      MODELERRORFILEDIRPATH = "logs";

    public static String      BUSINESSID            = "MsgInteract";

    public String getId()
    {
        return id;
    }

    public String getReceiver()
    {
        return receiver;
    }

    public void setReceiver(String receiver)
    {
        this.receiver = receiver;
    }

    public String getReceivername()
    {
        return receivername;
    }

    /**
     * 反显示收件人名
     * @param all
     */
    public void setReceivername(boolean all)
    {
        if (receiver != null)
        {
            String[] receiverarray = receiver.split(",");
            for (int i = 0; i < receiverarray.length; i++)
            {
                if(receivername!=null&&!"".equals(receivername))
                {
                    receivername+=";";
                }
                if(!all&&i>3)
                {
                    receivername +="...";
                    break;
                }
                receivername += UserUtil.getUserNameById(receiverarray[i]);
            }
        }
    }

    public void setReceivername()
    {
        setReceivername(true);
    }

    public String getOwner()
    {
        return owner;
    }

    public void setOwner(String owner)
    {
        this.owner = owner;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getSendid()
    {
        return sendid;
    }

    public void setSendid(String sendid)
    {
        this.sendid = sendid;
    }

    public String getSender()
    {
        return sender;
    }

    public void setSender(String sender)
    {
        this.sender = sender;
        if (sender != null)
        {
            sendername = UserUtil.getUserNameById(sender);
        }
    }

    public String getSendername()
    {
        return sendername;
    }

    public void setSendername()
    {
        if (sender != null)
        {
            sendername = UserUtil.getUserNameById(sender);
        }
    }

    public String getTheme()
    {
        return theme;
    }

    public void setTheme(String theme)
    {
        this.theme = theme;
    }

    public String getBody()
    {
        return body;
    }

    public void setBody(String body)
    {
        this.body = body;
        this.bodysub = body;
        if (bodysub != null)
        {
            int bodylen = bodysub.length();
            if (bodylen > 50)
            {
                bodysub = bodysub.substring(0, 50);
            }
            bodysub = bodysub.replaceAll("<[^<>]*>", "").replaceFirst("<[^<]*", "");
            if (bodysub.length() > 18)
            {
                bodysub = bodysub.substring(0, 15);
            }
            if (bodylen > 18)
            {
                bodysub += "...";
            }
        }
    }

    public String getBodysub()
    {
        return bodysub;
    }

    public String getAttachment()
    {
        return attachment;
    }

    public void setAttachment(String attachment)
    {
        this.attachment = attachment;
    }

    public List<SysAttach> getAttachmentobj()
    {
        return attachmentobj;
    }

    public void setAttachmentobj()
    {
        this.attachmentobj = SysAttachUtil.getSysAttachById(attachment);
    }

    public Date getSendtime()
    {
        return sendtime;
    }

    public void setSendtime(Date sendtime)
    {
        this.sendtime = sendtime;
    }

    public String getFlag()
    {
        return flag;
    }

    public void setFlag(String flag)
    {
        this.flag = flag;
    }

    public String getPost()
    {
        return post;
    }

    public void setPost(String post)
    {
        this.post = post;
    }

}