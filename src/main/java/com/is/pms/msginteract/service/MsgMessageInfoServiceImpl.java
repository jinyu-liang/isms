package com.is.pms.msginteract.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.base.mybatis.Page;
import com.is.ggkz.entity.GgkzUserInfo;
import com.is.ggkz.entity.ext.UserDetail;
import com.is.mq.producer.PmsMessageTopicProducer;
import com.is.pms.msginteract.dao.MsgMessageInfoDaoImpl;
import com.is.pms.msginteract.entity.MsgMessageInfo;
import com.is.sys.entity.SysAttach;
import com.is.utils.PublicDict;
import com.is.utils.keyUtils.KeyGen;
import com.is.utils.thread.ThreadPool;

/**
 * 
 * <p>文件名称: MsgMessageInfoServiceImpl.java</p>
 * <p>文件描述: 消息互动 Service</p>
 * <p>版权所有: 版权所有(C)2013-2020</p>
 * <p>公   司: IS软件事业部</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2014年9月18日</p>
 * <p>修改记录0：无</p>
 * @version 1.0
 * @author  
 */
// Spring Bean的标识.
@Component
// 默认将类中的所有函数纳入事务管理.
@Transactional
public class MsgMessageInfoServiceImpl
{
    private static Logger           LOGGER = LoggerFactory.getLogger(MsgMessageInfoServiceImpl.class);

    @Autowired
    private MsgMessageInfoDaoImpl   msgMessageInfoDaoImpl;

    @Autowired
    private PmsMessageTopicProducer pmsMessageTopicProducer;

    public Page queryPageDraft(MsgMessageInfo entity)
    {
        return msgMessageInfoDaoImpl.pageQuery("MsgMessageInfo.selectDraftByPage", entity);
    }

    public Page queryPageGarbage(MsgMessageInfo entity)
    {
        return msgMessageInfoDaoImpl.pageQuery("MsgMessageInfo.selectGarbageByPage", entity);
    }

    public Page queryPageReceive(MsgMessageInfo entity)
    {
        return msgMessageInfoDaoImpl.pageQuery("MsgMessageInfo.selectReceiveByPage", entity);
    }

    public int selectUnreadMsgCount(MsgMessageInfo entity)
    {
        return msgMessageInfoDaoImpl.selectUnreadMsgCount(entity);
    }

    public Page queryPageSend(MsgMessageInfo entity)
    {
        return msgMessageInfoDaoImpl.pageQuery("MsgMessageInfo.selectSendByPage", entity);
    }

    public Page queryPageSearch(MsgMessageInfo entity)
    {
        return msgMessageInfoDaoImpl.pageQuery("MsgMessageInfo.selectSerachByPage", entity);
    }

    public Page queryPageAll(MsgMessageInfo entity)
    {
        return msgMessageInfoDaoImpl.pageQuery("MsgMessageInfo.selectAllByPage", entity);
    }

    /**
     * 插入发件箱
     * @param entity
     * @return
     */
    public int insertEntitySend(MsgMessageInfo entity, List<String> ids, String tp)
    {
        String receiver = entity.getReceiver();
        String[] receivers = receiver.split(",");
        List<MsgMessageInfo> forwordMsg = msgMessageInfoDaoImpl.selectEntityByIds(ids, tp);
        if (forwordMsg != null && !forwordMsg.isEmpty())
        {
            StringBuffer sbf = new StringBuffer();
            sbf.append("<p>" + entity.getBody()
                    + "</p><p align=\"center\"style=\"text-align:center;\">--------------------原始消息--------------------</p>");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String path = ServletActionContext.getRequest().getContextPath();
            String basePath = ServletActionContext.getRequest().getScheme() + "://" + ServletActionContext.getRequest().getServerName() + ":"
                    + ServletActionContext.getRequest().getServerPort() + path + "/";
            for (MsgMessageInfo item : forwordMsg)
            {
                item.setSendername();
                item.setAttachmentobj();
                item.setReceivername();
                sbf.append("<p>主题： <strong>").append(item.getTheme()).append("</strong></p>").append("<p>发件人： ").append(item.getSendername())
                        .append("</p>").append("<p>时间： ");
                try
                {
                    sbf.append(item.getSendtime() == null ? "" : sdf.format(item.getSendtime()));
                }
                catch (Exception e)
                {
                    if (LOGGER.isErrorEnabled())
                    {
                        LOGGER.error("{}", e);
                    }
                }
                sbf.append("</p>").append("<p>收件人：").append(item.getReceivername()).append("</p>").append("<p>附件：</p>").append("<p>");
                List<SysAttach> attachList = item.getAttachmentobj();
                for (SysAttach attach : attachList)
                {
                    sbf.append(attach.getAttachName()).append("&nbsp;&nbsp; (").append(attach.getFileSize()).append("kb)&nbsp;&nbsp; ")
                            .append("<a href=\"").append(basePath).append("sys/sys-attach!downFile.action?sysAttachentity.attachId=")
                            .append(attach.getAttachId()).append("\">下载文件 </a>");
                }
                sbf.append("</p><p>&nbsp;</p><p>正文：</p>").append("<p>").append(item.getBody()).append("</p>");
            }
            entity.setBody(sbf.toString());
        }
        int rst = msgMessageInfoDaoImpl.insertEntitySend(entity);
        entity.setSendername();
        entity.setReceivername();
        List<Integer> insertDbrst = new ArrayList<Integer>();
        for (String item : receivers)
        {
            entity.setId(KeyGen.getCommonKeyGen(PublicDict.MsgRceive_id));
            entity.setOwner(item);
            insertDbrst.add(msgMessageInfoDaoImpl.insertEntityReceive(entity));
            //            {
            //                GgkzUserInfo user = new GgkzUserInfo();
            //                user.setUserId(item);
            //                if (LOGGER.isDebugEnabled())
            //                {
            //                    LOGGER.debug(item);
            //                }
            //                pmsMessageTopicProducer.sendTopic(user, entity.getBodysub(), entity);
            //            }
        }
        ThreadPool.getInstance().exec(new SendMsgThread(receivers, entity, insertDbrst));
        return rst;
    }

    /**
     * 清空垃圾箱
     * @param entity
     * @return
     */
    public int deleteGarbage(MsgMessageInfo entity)
    {
        return msgMessageInfoDaoImpl.deleteGarbage(entity);
    }

    /**
     * 删除邮件
     * @param entity
     * @param user
     * @return
     */
    public int deleteGarbage(MsgMessageInfo entity, UserDetail user)
    {
        entity.setOwner(user.getUserId());
        int rst = msgMessageInfoDaoImpl.deleteGarbage(entity);
        return rst;
    }

    public int deleteGarbage(MsgMessageInfo entity, String userId)
    {
        entity.setOwner(userId);
        int rst = msgMessageInfoDaoImpl.deleteGarbage(entity);
        return rst;
    }

    /**
     * 删除发件箱
     * @param ids
     * @return
     */
    public int deleteMsg(List<String> ids, String tp)
    {
        if (tp != null && "garbage".equals(tp))
        {
            return msgMessageInfoDaoImpl.deleteMsgByIds(ids, tp);
        }
        int rst = msgMessageInfoDaoImpl.insertGarbageselect(ids, tp);
        if (rst == ids.size())
        {
            rst = msgMessageInfoDaoImpl.deleteMsgByIds(ids, tp);
        }
        return rst;
    }

    /**
     * 彻底删除发件箱
     * @param ids
     * @return
     */
    public int deleteSendCompletely(List<String> ids, String tp)
    {
        return msgMessageInfoDaoImpl.deleteMsgByIds(ids, tp);
    }

    /**
     * 级联删除发件箱和收件箱
     * @param ids
     * @param tp
     * @return
     */
    public int deleteMsgCascade(List<String> ids)
    {
        msgMessageInfoDaoImpl.deleteReceiveMsgByIds(ids);
        return msgMessageInfoDaoImpl.deleteMsgByIds(ids, "send");
    }

    /**
     * 消息转发
     * @param ids
     * @param receivers
     * @param user
     * @return
     */
    public int insertForwardSend(List<String> ids, List<String> receivers, UserDetail user)
    {
        int rst = 0;
        for (String receiver : receivers)
        {
            for (String id : ids)
            {
                MsgMessageInfo queryEntity = new MsgMessageInfo();
                queryEntity.setId(id);
                queryEntity = msgMessageInfoDaoImpl.selectSendByEntity(queryEntity);
                if (queryEntity != null)
                {
                    MsgMessageInfo msgMessageInfo = new MsgMessageInfo();
                    msgMessageInfo.setReceiver(receiver);
                    msgMessageInfo.setSender(user.getUserId());
                    msgMessageInfo.setTheme(queryEntity.getTheme());
                    msgMessageInfo.setAttachment(queryEntity.getAttachment());
                    msgMessageInfo.setBody(queryEntity.getBody());
                    msgMessageInfo.setSendtime(new Date());
                    msgMessageInfo.setFlag(MsgMessageInfo.READ);
                    int rt = msgMessageInfoDaoImpl.insertEntitySend(msgMessageInfo);
                    if (rt == 1)
                    {
                        GgkzUserInfo msguser = new GgkzUserInfo();
                        msguser.setUserId(receiver);
                        pmsMessageTopicProducer.sendTopic(msguser, queryEntity.getTheme(), queryEntity);
                    }
                    rst += rt;
                }
            }
        }
        return rst;
    }

    /**
     * 插入草稿箱
     * @param entity
     * @return
     */
    public int insertEntityDraft(MsgMessageInfo entity)
    {
        return msgMessageInfoDaoImpl.insertEntityDraft(entity);
    }

    /**
     * 移动消息
     * @param ids
     * @param msgbox
     * @return
     */
    public int insertMove(List<String> ids, String msgbox, String tp)
    {
        int rst = msgMessageInfoDaoImpl.insertRemove(ids, msgbox, tp);
        if (rst == ids.size())
        {
            rst = msgMessageInfoDaoImpl.deleteRemove(ids, tp);
        }
        return rst;
    }

    /**
     * 查询消息
     * @param queryEntity
     * @param tp
     * @return
     */
    public MsgMessageInfo selectOneEntityByEntity(MsgMessageInfo queryEntity, String tp)
    {
        return msgMessageInfoDaoImpl.selectOneEntityByEntity(queryEntity, tp);
    }

    /**
     * 更新消息
     * @param queryEntity
     * @param tp
     * @return
     */
    public int updateEntityByEntity(MsgMessageInfo queryEntity, String tp)
    {
        return msgMessageInfoDaoImpl.updateEntityByEntity(queryEntity, tp);
    }

    /**
     * 查询消息
     * @param ids
     * @param tp
     * @return
     */
    public List<MsgMessageInfo> selectEntityByIds(List<String> ids, String tp)
    {
        return msgMessageInfoDaoImpl.selectEntityByIds(ids, tp);
    }

    private class SendMsgThread implements Runnable
    {
        String[]       receivers;

        MsgMessageInfo entity;

        List<Integer>  insertDbrst;

        SendMsgThread(String[] _receivers, MsgMessageInfo _entity, List<Integer> _insertDbrst)
        {
            this.receivers = _receivers;
            this.entity = _entity;
            this.insertDbrst = _insertDbrst;
        }

        @Override
        public void run()
        {
            for (int i = 0; i < receivers.length; i++)
            {
                String item = receivers[i];
                entity.setId(KeyGen.getCommonKeyGen(PublicDict.MsgRceive_id));
                entity.setOwner(item);
                if (insertDbrst.size() > i && insertDbrst.get(i) == 1)
                {
                    GgkzUserInfo user = new GgkzUserInfo();
                    user.setUserId(item);
                    if (LOGGER.isDebugEnabled())
                    {
                        LOGGER.debug(item);
                    }
                    pmsMessageTopicProducer.sendTopic(user, entity.getBodysub(), entity);
                }
            }
        }
    }
}
