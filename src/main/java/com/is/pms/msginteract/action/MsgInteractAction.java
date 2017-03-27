package com.is.pms.msginteract.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.base.mybatis.BaseStruts2Action;
import com.base.mybatis.Page;
import com.is.pms.msginteract.entity.MsgMessageInfo;
import com.is.pms.msginteract.service.MsgMessageInfoServiceImpl;
import com.is.session.context.IsSessionContext;
import com.is.sys.entity.SysAttach;
import com.is.utils.PublicDict;
import com.is.utils.keyUtils.KeyGen;

/**
 * 
 * <p>文件名称: MsgInteractAction.java</p>
 * <p>文件描述: 消息互动</p>
 * <p>版权所有: 版权所有(C)2013-2020</p>
 * <p>公   司: IS软件事业部</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2014年9月26日</p>
 * <p>修改记录0：无</p>
 * @version 1.0
 * @author  
 */
@Namespace("/msg")
public class MsgInteractAction extends BaseStruts2Action
{

    private static final long         serialVersionUID       = 1L;

    private static final Logger       LOGGER                 = LoggerFactory.getLogger(MsgInteractAction.class);

    private MsgMessageInfo            entity;

    private IsSessionContext   isSessionContext = null;

    @Autowired
    private MsgMessageInfoServiceImpl msgMessageInfoServiceImpl;

    private List<String>              ids;

    private List<String>              receivers;

    private String                    msgbox;

    private String                    tomsgbox;

    private String                    post;

    private int                       unreadcount            = 0;

    private int                       forwardTp              = -1;

    public MsgInteractAction()
    {
        super();
        if (entity == null)
        {
            entity = new MsgMessageInfo();
        }
        if (isSessionContext == null)
        {
            isSessionContext = IsSessionContext.getInstance();
        }
    }

    @Override
    public String execute() throws Exception
    {
        post = getSessionUser().getPost();
        return "index";
    }

    public String toAdd()
    {
        return "msg-interact/add";
    }

    public String toSend()
    {
        entity.setOwner(getSessionUser().getUserId());
        page = msgMessageInfoServiceImpl.queryPageSend(entity);
        return "msg-interact/send";
    }

    public String toReceived()
    {
        //                if ("1".equals(getSessionUser().getPost()))
        //                {
        //                    entity.setPost("1");
        //                }
        //        else
        //        {
        entity.setOwner(getSessionUser().getUserId());
        //        }
        page = msgMessageInfoServiceImpl.queryPageReceive(entity);
        entity.setFlag(MsgMessageInfo.READ);
        unreadcount = msgMessageInfoServiceImpl.selectUnreadMsgCount(entity);
        return "msg-interact/receive";
    }

    public String toDraft()
    {
        entity.setOwner(getSessionUser().getUserId());
        page = msgMessageInfoServiceImpl.queryPageDraft(entity);
        return "msg-interact/draft";
    }

    public String toGarbage()
    {
        entity.setOwner(getSessionUser().getUserId());
        page = msgMessageInfoServiceImpl.queryPageGarbage(entity);
        return "msg-interact/garbage";
    }

    public String toSetup()
    {
        return "msg-interact/setup";
    }

    public String toSearch()
    {
        //        if ("1".equals(getSessionUser().getPost()))
        //        {
        //            entity.setPost("1");

        //        }
        if ("1".equals(entity.getPost()))
        {
            return toOther();
        }
        entity.setOwner(getSessionUser().getUserId());
        page = msgMessageInfoServiceImpl.queryPageSearch(entity);
        tomsgbox = "search";
        return "msg-interact/search";
    }

    public String toOther()
    {
        entity.setOwner(getSessionUser().getUserId());
        page = msgMessageInfoServiceImpl.queryPageAll(entity);
        entity.setPost("1");
        return "msg-interact/search";
    }

    public String create()
    {
        String id = entity.getId();
        if (id == null || "".equals(id))
        {
            String msgId = KeyGen.getCommonKeyGen(PublicDict.MsgSend_id);
            entity.setId(msgId);
            entity.setSendid(msgId);
        }
        entity.setFlag(MsgMessageInfo.UNREAD);
        entity.setSender(getSessionUser().getUserId());
        entity.setOwner(getSessionUser().getUserId());
        Date sendtime = entity.getSendtime();
        if (sendtime == null)
        {
            entity.setSendtime(new Date());
        }
        int rst = msgMessageInfoServiceImpl.insertEntitySend(entity, ids, msgbox);
        if (rst == 1)
        {
            setMessage("发送成功");
            //            String receiver = entity.getReceiver();
            //            String[] receivers = receiver.split(";");
            //            for (String item : receivers)
            //            {
            //                GgkzUserInfo user = new GgkzUserInfo();
            //                user.setUserId(item);
            //                pmsMessageProducer.sendQueue(user, entity.getTheme());
            //            }
            //            return "msg-interact/success";
        }
        else
        {
            setWarnMessage("发送失败");
            //            return "msg-interact/add";
        }
        return JSON_DATA;
    }

    /**
     * 清空垃圾箱
     * @return
     */
    public String cleanGarbage()
    {
        int rst = msgMessageInfoServiceImpl.deleteGarbage(entity, getSessionUser());
        if (rst >= 0)
        {
            return "msg-interact/garbage";
        }
        else
        {
            setWarnMessage("清空垃圾箱失败!");
            return JSON_DATA;
        }
    }

    /**
     * 保存草稿
     * @return
     */
    public String createDraft()
    {
        String id = entity.getId();
        if (id == null || "".equals(id))
        {
            String msgId = KeyGen.getCommonKeyGen(PublicDict.MsgDraft_id);
            entity.setId(msgId);
            entity.setSendid(msgId);
        }
        entity.setSender(getSessionUser().getUserId());
        entity.setOwner(getSessionUser().getUserId());
        int rst = msgMessageInfoServiceImpl.insertEntityDraft(entity);
        if (rst == 1)
        {
            setMessage("保存草稿成功!");
        }
        else
        {
            setWarnMessage("保存草稿失败!");
        }
        return JSON_DATA;
    }

    /**
     * 删除发件箱
     * @return
     */
    public String deleteMsg()
    {
        int rst = msgMessageInfoServiceImpl.deleteMsg(ids, msgbox);
        if (rst > 0)
        {
            setMessage("删除成功！");
        }
        else
        {
            setWarnMessage("删除失败！");
        }
        return JSON_DATA;
    }

    /**
     * 彻底删除发件箱
     * @return
     */
    public String deleteMsgCompletely()
    {
        int rst = msgMessageInfoServiceImpl.deleteSendCompletely(ids, msgbox);
        if (rst > 0)
        {
            setMessage("删除成功！");
        }
        else
        {
            setWarnMessage("删除失败！");
        }
        return JSON_DATA;
    }

    /**
     * 级联删除发件箱和收件箱
     * @return
     */
    public String deleteMsgCascade()
    {
        int rst = msgMessageInfoServiceImpl.deleteMsgCascade(ids);
        if (rst > 0)
        {
            setMessage("删除成功！");
        }
        else
        {
            setWarnMessage("删除失败！");
        }
        return JSON_DATA;
    }

    @SuppressWarnings("unchecked")
    public String toForwardSend()
    {
        if (page == null)
        {
            page = new Page();
        }
        page.setData(msgMessageInfoServiceImpl.selectEntityByIds(ids, msgbox));
        if (forwardTp > -1 || ids.size() == 1)
        {
            List<MsgMessageInfo> data = (List<MsgMessageInfo>) page.getData();
            if (data != null && data.size() == 1)
            {
                MsgMessageInfo msgMessageInfo = data.get(0);
                if (forwardTp == 0)
                {
                    entity.setReceiver(msgMessageInfo.getSender());
                    entity.setReceivername();
                }
                else if (forwardTp == 1)
                {
                    entity.setReceiver(msgMessageInfo.getReceiver());
                    entity.setReceivername();
                }
                if (forwardTp != -1)
                {
                    entity.setTheme("回复:" + msgMessageInfo.getTheme());
                }
                else
                {
                    entity.setTheme("转发:" + msgMessageInfo.getTheme());
                }
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                msgMessageInfo.setSendername();
                msgMessageInfo.setAttachmentobj();
                msgMessageInfo.setReceivername();
                StringBuffer sbf = new StringBuffer();
                sbf.append("<div class=\"oldmsgbodydivelem\"><p>主题： <strong>").append(msgMessageInfo.getTheme()).append("</strong></p>")
                        .append("<p>发件人： ").append(msgMessageInfo.getSendername()).append("</p>").append("<p>时间： ");
                try
                {
                    sbf.append(msgMessageInfo.getSendtime() == null ? "" : sdf.format(msgMessageInfo.getSendtime()));
                }
                catch (Exception e)
                {
                    if (LOGGER.isErrorEnabled())
                    {
                        LOGGER.error("{}", e);
                    }
                }
                sbf.append("</p>").append("<p>收件人：").append(msgMessageInfo.getReceivername()).append("</p>").append("<p>附件：</p>").append("<p>");
                List<SysAttach> attachList = msgMessageInfo.getAttachmentobj();
                for (SysAttach attach : attachList)
                {
                    sbf.append(attach.getAttachName()).append("&nbsp;&nbsp; (").append(attach.getFileSize()).append("kb)&nbsp;&nbsp; ")
                            .append("<a href=\"sys/sys-attach!downFile.action?sysAttachentity.attachId=").append(attach.getAttachId())
                            .append("\">下载文件 </a>");
                }
                sbf.append("</p><p>&nbsp;</p><p>正文：</p>").append("<p>").append(msgMessageInfo.getBody()).append("</p></div><p></p>");
                entity.setBody(sbf.toString());
            }
        }

        return "msg-interact/add";
    }

    @SuppressWarnings("unchecked")
    public String toEditSend()
    {
        forwardTp = 2;
        if (page == null)
        {
            page = new Page();
        }
        page.setData(msgMessageInfoServiceImpl.selectEntityByIds(ids, msgbox));
        if (forwardTp > -1 || ids.size() == 1)
        {
            List<MsgMessageInfo> data = (List<MsgMessageInfo>) page.getData();
            if (data != null && data.size() == 1)
            {
                MsgMessageInfo msgMessageInfo = data.get(0);
                if (msgMessageInfo != null)
                {
                    entity = msgMessageInfo;
                    entity.setReceivername();
                }
                //                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                //                msgMessageInfo.setSendername();
                //                msgMessageInfo.setAttachmentobj();
                //                msgMessageInfo.setReceivername();

                //                StringBuffer sbf = new StringBuffer();
                //                sbf.append("<p>主题： <strong>").append(msgMessageInfo.getTheme()).append("</strong></p>").append("<p>发件人： ")
                //                .append(msgMessageInfo.getSendername()).append("</p>").append("<p>时间： ");
                //                try
                //                {
                //                    sbf.append(msgMessageInfo.getSendtime() == null ? "" : sdf.format(msgMessageInfo.getSendtime()));
                //                }
                //                catch (Exception e)
                //                {
                //                    if (LOGGER.isErrorEnabled())
                //                    {
                //                        LOGGER.error("{}", e);
                //                    }
                //                }
                //                sbf.append("</p>").append("<p>收件人：").append(msgMessageInfo.getReceivername()).append("</p>").append("<p>附件：</p>").append("<p>");
                //                List<SysAttach> attachList = msgMessageInfo.getAttachmentobj();
                //                for (SysAttach attach : attachList)
                //                {
                //                    sbf.append("<a href=\"sys/sys-attach!downFile.action?sysAttachentity.attachId=").append(attach.getAttachId()).append("\">")
                //                    .append(attach.getAttachName()).append("&nbsp;&nbsp; (").append(attach.getFileSize()).append("kb)&nbsp;&nbsp; 下载文件 </a>");
                //                }
                //                sbf.append("</p><p>&nbsp;</p><p>正文：</p>").append("<p>").append(msgMessageInfo.getBody()).append("</p>");

                //                entity.setBody(sbf.toString());
            }
        }

        return "msg-interact/add";
    }

    /**
     * 移动发件箱
     * @return
     */
    public String removeTo()
    {
        int rst = msgMessageInfoServiceImpl.insertMove(ids, tomsgbox, msgbox);
        if (rst > 0)
        {
            setMessage("移动成功！");
        }
        else
        {
            setWarnMessage("移动失败！");
        }
        return JSON_DATA;
    }

    /**
     * 查看消息
     * @return
     */
    public String viewMsg()
    {
        if ("receive".equals(msgbox))
        {
            entity.setFlag(MsgMessageInfo.READ);
            msgMessageInfoServiceImpl.updateEntityByEntity(entity, msgbox);
        }
        entity = msgMessageInfoServiceImpl.selectOneEntityByEntity(entity, msgbox);
        return "msg-interact/view";
    }

    public MsgMessageInfo getEntity()
    {
        return entity;
    }

    public void setEntity(MsgMessageInfo entity)
    {
        this.entity = entity;
    }

    public List<String> getIds()
    {
        return ids;
    }

    public void setIds(List<String> ids)
    {
        this.ids = ids;
    }

    public List<String> getReceivers()
    {
        return receivers;
    }

    public void setReceivers(List<String> receivers)
    {
        this.receivers = receivers;
    }

    public String getMsgbox()
    {
        return msgbox;
    }

    public void setMsgbox(String msgbox)
    {
        this.msgbox = msgbox;
    }

    public int getUnreadcount()
    {
        return unreadcount;
    }

    public void setUnreadcount(int unreadcount)
    {
        this.unreadcount = unreadcount;
    }

    @Override
    public String getWarnMessage()
    {
        // TODO Auto-generated method stub
        return super.getWarnMessage();
    }

    @Override
    public String getMessage()
    {
        // TODO Auto-generated method stub
        return super.getMessage();
    }

    @Override
    public String getInfoMessage()
    {
        // TODO Auto-generated method stub
        return super.getInfoMessage();
    }

    public String getTomsgbox()
    {
        return tomsgbox;
    }

    public void setTomsgbox(String tomsgbox)
    {
        this.tomsgbox = tomsgbox;
    }

    public int getForwardTp()
    {
        return forwardTp;
    }

    public void setForwardTp(int forwardTp)
    {
        this.forwardTp = forwardTp;
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
