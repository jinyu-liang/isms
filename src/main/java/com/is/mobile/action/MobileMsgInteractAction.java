package com.is.mobile.action;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.base.mybatis.BaseStruts2Action;
import com.base.mybatis.Page;
import com.is.ggkz.entity.UserInfoMobile;
import com.is.ggkz.entity.query.GgkzUserInfoQuery;
import com.is.ggkz.service.GgkzUserInfoServiceImpl;
import com.is.pms.msginteract.entity.MsgMessageInfo;
import com.is.pms.msginteract.service.MsgMessageInfoServiceImpl;
import com.is.session.context.IsSessionContext;
import com.is.sys.entity.SysAttach;
import com.is.utils.PublicDict;
import com.is.utils.date.DateUtil;
import com.is.utils.json.DateJsonValueProcessor;
import com.is.utils.keyUtils.KeyGen;
import com.is.utils.swfUtil.PublicAttachOper;
import com.is.utils.swfUtil.SWFUploadHelper;

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
@Namespace("/mobile")
public class MobileMsgInteractAction extends BaseStruts2Action
{

    private static final long         serialVersionUID       = 1L;

    private static final Logger       LOGGER                 = LoggerFactory.getLogger(MobileMsgInteractAction.class);

    private String                    jsonEntity;

    private MsgMessageInfo            entity;

    private GgkzUserInfoQuery         userQueryEntity;

    private IsSessionContext    isSessionContext = null;

    @Autowired
    private MsgMessageInfoServiceImpl msgMessageInfoServiceImpl;

    @Autowired
    private GgkzUserInfoServiceImpl   ggkzUserInfoServiceImpl;

    private List<String>              ids;

    private List<String>              receivers;

    private String                    msgbox;

    private String                    tomsgbox;

    private int                       unreadcount            = 0;

    private int                       forwardTp              = -1;

    private String                    jsondata               = "[]";

    private String                    result                 = "0";

    private String                    userId;

    private String                    lastTime               = "";

    private String                    userName;

    private List<File>                userfile;

    private List<String>              userfileFileName;

    private List<String>              userfileContentType;

    public MobileMsgInteractAction()
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

    @SuppressWarnings("unchecked")
    public String toSend()
    {
        try
        {
            entity.setOwner(userId);
            page = msgMessageInfoServiceImpl.queryPageSend(entity);

            List<MsgMessageInfo> data = (List<MsgMessageInfo>) page.getData();
            List<MsgMessageInfo> transData = new ArrayList<MsgMessageInfo>();
            if (data != null)
            {
                for (MsgMessageInfo item : data)
                {
                    item.setReceivername(false);
                    //                    item.setAttachmentobj();
                    transData.add(item);
                }
            }
            data.clear();
            page.setData(transData);
            entity.setFlag(MsgMessageInfo.READ);
            entity.setSender(null);
            unreadcount = msgMessageInfoServiceImpl.selectUnreadMsgCount(entity);
            result = "1";
            setMessage("查询成功");

            JSONObject jsonobj = new JSONObject();

            //json配置
            JsonConfig jsonConfig = new JsonConfig();
            //json日期格式化
            DateJsonValueProcessor dateJsonValueProcessor = new DateJsonValueProcessor();
            //注册值转换器
            jsonConfig.registerJsonValueProcessor(Date.class, dateJsonValueProcessor);
            //排除字段集合
            String[] excludes = {"callbacks", "pageSize", "deleteCd", "dbType", "distinct", "receiver", "sender", "body", "attachment",
                    "attachmentobj"};
            //注册排除字段
            jsonConfig.setExcludes(excludes);
            //加载集合
            jsondata = jsonobj.accumulate("jsondata", page, jsonConfig).getJSONObject("jsondata").toString();
            if (LOGGER.isDebugEnabled())
            {
                LOGGER.debug(jsondata);
            }
        }
        catch (Exception e)
        {
            if (LOGGER.isErrorEnabled())
            {
                LOGGER.error("toReceived异常：{}", e);
            }
            setMessage("没有消息");
            result = "0";
        }
        return "mobile-msg-interact/data";
    }

    @SuppressWarnings("unchecked")
    public String toReceived()
    {
        try
        {
            //            GgkzUserInfo ggkzUserInfo = ggkzUserInfoServiceImpl.selectOneByEntity(ggkzUserInfoQuery);
            //            if ("1".equals(ggkzUserInfo.getPost()))
            //            {
            //                entity.setPost("1");
            //            }
            //            if (ggkzUserInfo != null && "1".equals(ggkzUserInfo.getPost()))
            //            {
            //                entity.setOwner(null);
            //            }
            //            else
            //            {
            entity.setOwner(userId);
            //            }
            page = msgMessageInfoServiceImpl.queryPageReceive(entity);
            entity.setFlag(MsgMessageInfo.READ);
            unreadcount = msgMessageInfoServiceImpl.selectUnreadMsgCount(entity);
            List<MsgMessageInfo> data = (List<MsgMessageInfo>) page.getData();
            List<MsgMessageInfo> transData = new ArrayList<MsgMessageInfo>();
            if (data != null)
            {
                for (MsgMessageInfo item : data)
                {
                    item.setReceivername(false);
                    //                    item.setAttachmentobj();
                    transData.add(item);
                }
            }
            data.clear();
            page.setData(transData);
            result = "1";
            setMessage("查询成功");

            JSONObject jsonobj = new JSONObject();

            //json配置
            JsonConfig jsonConfig = new JsonConfig();
            //json日期格式化
            DateJsonValueProcessor dateJsonValueProcessor = new DateJsonValueProcessor();
            //注册值转换器
            jsonConfig.registerJsonValueProcessor(Date.class, dateJsonValueProcessor);
            //排除字段集合
            String[] excludes = {"callbacks", "pageSize", "deleteCd", "dbType", "distinct", "receiver", "sender", "body", "attachment",
                    "attachmentobj"};
            //注册排除字段
            jsonConfig.setExcludes(excludes);
            //加载集合
            jsondata = jsonobj.accumulate("jsondata", page, jsonConfig).getJSONObject("jsondata").toString();
            if (LOGGER.isDebugEnabled())
            {
                LOGGER.debug(jsondata);
            }
        }
        catch (Exception e)
        {
            if (LOGGER.isErrorEnabled())
            {
                LOGGER.error("toReceived异常：{}", e);
            }
            setMessage("没有消息");
            result = "0";
        }
        return "mobile-msg-interact/data";
    }

    @SuppressWarnings("unchecked")
    public String toOther()
    {
        try
        {
            //            if ("1".equals(ggkzUserInfo.getPost()))
            //            {
            //                entity.setPost("1");
            //            }
            //            if (ggkzUserInfo != null && "1".equals(ggkzUserInfo.getPost()))
            //            {
            //                entity.setOwner(null);
            //            }
            //            else
            //            {
            entity.setOwner(userId);
            //            }
            page = msgMessageInfoServiceImpl.queryPageAll(entity);
            List<MsgMessageInfo> data = (List<MsgMessageInfo>) page.getData();
            List<MsgMessageInfo> transData = new ArrayList<MsgMessageInfo>();
            if (data != null)
            {
                for (MsgMessageInfo item : data)
                {
                    item.setReceivername(false);
                    //                    item.setAttachmentobj();
                    transData.add(item);
                }
            }
            data.clear();
            page.setData(transData);
            result = "1";
            setMessage("查询成功");

            JSONObject jsonobj = new JSONObject();

            //json配置
            JsonConfig jsonConfig = new JsonConfig();
            //json日期格式化
            DateJsonValueProcessor dateJsonValueProcessor = new DateJsonValueProcessor();
            //注册值转换器
            jsonConfig.registerJsonValueProcessor(Date.class, dateJsonValueProcessor);
            //排除字段集合
            String[] excludes = {"callbacks", "pageSize", "deleteCd", "dbType", "distinct", "receiver", "sender", "body", "attachment",
                    "attachmentobj"};
            //注册排除字段
            jsonConfig.setExcludes(excludes);
            //加载集合
            jsondata = jsonobj.accumulate("jsondata", page, jsonConfig).getJSONObject("jsondata").toString();
            if (LOGGER.isDebugEnabled())
            {
                LOGGER.debug(jsondata);
            }
        }
        catch (Exception e)
        {
            if (LOGGER.isErrorEnabled())
            {
                LOGGER.error("toReceived异常：{}", e);
            }
            setMessage("没有消息");
            result = "0";
        }
        return "mobile-msg-interact/data";
    }

    @SuppressWarnings("unchecked")
    public String toDraft()
    {
        try
        {
            entity.setOwner(userId);
            page = msgMessageInfoServiceImpl.queryPageDraft(entity);
            List<MsgMessageInfo> data = (List<MsgMessageInfo>) page.getData();
            List<MsgMessageInfo> transData = new ArrayList<MsgMessageInfo>();
            if (data != null)
            {
                for (MsgMessageInfo item : data)
                {
                    item.setReceivername(false);
                    //                    item.setAttachmentobj();
                    transData.add(item);
                }
            }
            data.clear();
            page.setData(transData);
            result = "1";
            setMessage("查询成功");

            JSONObject jsonobj = new JSONObject();

            //json配置
            JsonConfig jsonConfig = new JsonConfig();
            //json日期格式化
            DateJsonValueProcessor dateJsonValueProcessor = new DateJsonValueProcessor();
            //注册值转换器
            jsonConfig.registerJsonValueProcessor(Date.class, dateJsonValueProcessor);
            //排除字段集合
            String[] excludes = {"callbacks", "pageSize", "deleteCd", "dbType", "distinct", "receiver", "sender", "body", "attachment",
                    "attachmentobj"};
            //注册排除字段
            jsonConfig.setExcludes(excludes);
            //加载集合
            jsondata = jsonobj.accumulate("jsondata", page, jsonConfig).getJSONObject("jsondata").toString();
            if (LOGGER.isDebugEnabled())
            {
                LOGGER.debug(jsondata);
            }
        }
        catch (Exception e)
        {
            if (LOGGER.isErrorEnabled())
            {
                LOGGER.error("toReceived异常：{}", e);
            }
            setMessage("没有消息");
            result = "0";
        }
        return "mobile-msg-interact/data";
    }

    @SuppressWarnings("unchecked")
    public String toGarbage()
    {
        try
        {
            entity.setOwner(userId);
            page = msgMessageInfoServiceImpl.queryPageGarbage(entity);
            List<MsgMessageInfo> data = (List<MsgMessageInfo>) page.getData();
            List<MsgMessageInfo> transData = new ArrayList<MsgMessageInfo>();
            if (data != null)
            {
                for (MsgMessageInfo item : data)
                {
                    item.setReceivername(false);
                    //                    item.setAttachmentobj();
                    transData.add(item);
                }
            }
            data.clear();
            page.setData(transData);
            result = "1";
            setMessage("查询成功");

            JSONObject jsonobj = new JSONObject();

            //json配置
            JsonConfig jsonConfig = new JsonConfig();
            //json日期格式化
            DateJsonValueProcessor dateJsonValueProcessor = new DateJsonValueProcessor();
            //注册值转换器
            jsonConfig.registerJsonValueProcessor(Date.class, dateJsonValueProcessor);
            //排除字段集合
            String[] excludes = {"callbacks", "pageSize", "deleteCd", "dbType", "distinct", "receiver", "sender", "body", "attachment",
                    "attachmentobj"};
            //注册排除字段
            jsonConfig.setExcludes(excludes);
            //加载集合
            jsondata = jsonobj.accumulate("jsondata", page, jsonConfig).getJSONObject("jsondata").toString();
            if (LOGGER.isDebugEnabled())
            {
                LOGGER.debug(jsondata);
            }
        }
        catch (Exception e)
        {
            if (LOGGER.isErrorEnabled())
            {
                LOGGER.error("toReceived异常：{}", e);
            }
            setMessage("没有消息");
            result = "0";
        }
        return "mobile-msg-interact/data";
    }

    public String create()
    {
        String attachMentId = uploadFiles(userfile);
        entity.setAttachment(attachMentId);
        String id = entity.getId();
        if (id == null || "".equals(id))
        {
            String msgId = KeyGen.getCommonKeyGen(PublicDict.MsgSend_id);
            entity.setId(msgId);
            entity.setSendid(msgId);
        }
        entity.setFlag(MsgMessageInfo.UNREAD);
        entity.setSender(userId);
        entity.setOwner(userId);
        Date sendtime = entity.getSendtime();
        if (sendtime == null)
        {
            entity.setSendtime(new Date());
        }
        int rst = msgMessageInfoServiceImpl.insertEntitySend(entity, ids, msgbox);
        if (rst == 1)
        {
            result = "1";
            setMessage("发送成功");
        }
        else
        {
            result = "0";
            setMessage("发送失败");
        }
        return "mobile-msg-interact/data";
    }

    public String collectError()
    {
        collectFiles(userfile);
        result = "1";
        setMessage("发送成功");
        return "mobile-msg-interact/data";
    }

    private String uploadFiles(List<File> files)
    {
        if (files == null || files.size() < 1)
        {
            return "";
        }
        StringBuffer sb = new StringBuffer();

        // 实现上传
        String path = "upload" + File.separatorChar + MsgMessageInfo.MODELFILEDIRPATH + File.separatorChar;// 附件上传目录路径
        for (int i = 0; i < files.size(); i++)
        {
            File file = files.get(i);
            try
            {
                String filePath = path + DateUtil.getCurDateByyyyymmdd();
                String sysFileName = KeyGen.getCommonKeyGen("attach") + "." + SWFUploadHelper.getFileType(userfileFileName.get(i));// 系统生成的文件名称
                SWFUploadHelper.uploadFiles(file, ServletActionContext.getServletContext().getRealPath("/") + filePath, sysFileName);
                // 保存附件表
                PublicAttachOper.publicAbbachSave(MsgMessageInfo.BUSINESSID, sysFileName, filePath, file, userfileFileName.get(i),
                        MsgMessageInfo.MODELFILEDIRPATH, userId, userName);
                if (sb.length() > 0)
                {
                    sb.append(";");
                }
                sb.append(sysFileName);
            }
            catch (Exception e)
            {
                if (LOGGER.isErrorEnabled())
                {
                    LOGGER.error("uploadFiles异常：{}", e);
                }
            }
        }

        return sb.toString();
    }

    private void collectFiles(List<File> files)
    {
        if (files == null || files.size() < 1)
        {
            return;
        }

        // 实现上传
        String path = "upload" + File.separatorChar + MsgMessageInfo.MODELERRORFILEDIRPATH + File.separatorChar;// 附件上传目录路径
        for (int i = 0; i < files.size(); i++)
        {
            File file = files.get(i);
            try
            {
                String filePath = path + DateUtil.getCurDateByyyyymmdd();
                String sysFileName = KeyGen.getCommonKeyGen("log") + "." + SWFUploadHelper.getFileType(userfileFileName.get(i));// 系统生成的文件名称
                SWFUploadHelper.uploadFiles(file, ServletActionContext.getServletContext().getRealPath("/") + filePath, sysFileName);
            }
            catch (Exception e)
            {
                if (LOGGER.isErrorEnabled())
                {
                    LOGGER.error("uploadFiles异常：{}", e);
                }
            }
        }
    }

    /**
     * 清空垃圾箱
     * @return
     */
    public String cleanGarbage()
    {
        if (userId == null)
        {
            setMessage("清空垃圾箱失败");
            result = "0";
        }
        int rst = msgMessageInfoServiceImpl.deleteGarbage(entity, userId);
        if (rst >= 0)
        {
            result = "1";
            setMessage("清空垃圾箱成功");
        }
        else
        {
            result = "0";
            setWarnMessage("清空垃圾箱失败");
        }
        return "mobile-msg-interact/data";
    }

    /**
     * 保存草稿
     * @return
     */
    public String createDraft()
    {
        String attachMentId = uploadFiles(userfile);
        entity.setAttachment(attachMentId);
        String id = entity.getId();
        if (id == null || "".equals(id))
        {
            String msgId = KeyGen.getCommonKeyGen(PublicDict.MsgDraft_id);
            entity.setId(msgId);
            entity.setSendid(msgId);
        }
        entity.setFlag(MsgMessageInfo.UNREAD);
        entity.setSender(userId);
        entity.setOwner(userId);
        Date sendtime = entity.getSendtime();
        if (sendtime == null)
        {
            entity.setSendtime(new Date());
        }
        int rst = msgMessageInfoServiceImpl.insertEntityDraft(entity);
        if (rst == 1)
        {
            result = "1";
            setMessage("保存草稿成功!");
        }
        else
        {
            result = "0";
            setWarnMessage("保存草稿失败!");
        }
        return "mobile-msg-interact/data";

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
            result = "1";
            setMessage("删除成功！");
        }
        else
        {
            result = "0";
            setWarnMessage("删除失败！");
        }
        return "mobile-msg-interact/data";
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
            result = "1";
            setMessage("删除成功！");
        }
        else
        {
            result = "0";
            setWarnMessage("删除失败！");
        }
        return "mobile-msg-interact/data";
    }

    public String toForwardSend()
    {
        List<MsgMessageInfo> msgMessageInfoList = msgMessageInfoServiceImpl.selectEntityByIds(ids, msgbox);
        if (msgMessageInfoList == null || msgMessageInfoList.size() < 1)
        {
            result = "0";
            setMessage("查询失败");
        }
        else
        {
            result = "1";
            setMessage("查询成功");
            if (forwardTp > -1 || ids.size() == 1)
            {
                MsgMessageInfo msgMessageInfo = msgMessageInfoList.get(0);
                msgMessageInfo.setSendername();
                msgMessageInfo.setAttachmentobj();
                msgMessageInfo.setReceivername(true);
                StringBuffer sbf = new StringBuffer();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                sbf.append("<div style=\"background:#eee;border:1px solid #ccc;padding:5px 10px;\"><p>主题： <strong>")
                        .append(msgMessageInfo.getTheme()).append("</strong></p>").append("<p>发件人： ").append(msgMessageInfo.getSendername())
                        .append("</p>").append("<p>时间： ");
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
                String path = ServletActionContext.getRequest().getContextPath();
                String basePath = ServletActionContext.getRequest().getScheme() + "://" + ServletActionContext.getRequest().getServerName() + ":"
                        + ServletActionContext.getRequest().getServerPort() + path + "/";

                for (SysAttach attach : attachList)
                {
                    sbf.append("<span>").append(attach.getAttachName()).append("&nbsp;&nbsp; (").append(attach.getFileSize())
                            .append("kb)&nbsp;&nbsp; ").append("<a href=\"").append(basePath)
                            .append("sys/sys-attach!downFile.action?sysAttachentity.attachId=").append(attach.getAttachId()).append("\">")
                            .append("下载文件 </a></span><br/>");
                }
                sbf.append("</p><p>&nbsp;</p><p>正文：</p>").append("<p>").append(msgMessageInfo.getBody()).append("</p></div>");
                msgMessageInfo.setBody(sbf.toString());
                msgMessageInfoList.clear();
                msgMessageInfoList.add(msgMessageInfo);
            }
            JSONArray jsonArray = new JSONArray();
            //json配置
            JsonConfig jsonConfig = new JsonConfig();
            //json日期格式化
            DateJsonValueProcessor dateJsonValueProcessor = new DateJsonValueProcessor();
            //注册值转换器
            jsonConfig.registerJsonValueProcessor(Date.class, dateJsonValueProcessor);
            //排除字段集合
            String[] excludes = {"callbacks", "bodysub", "dbType", "distinct", "pageNumber", "pageSize"};
            //注册排除字段
            jsonConfig.setExcludes(excludes);
            //加载集合
            jsonArray.addAll(msgMessageInfoList, jsonConfig);
            jsondata = jsonArray.toString();
            if (LOGGER.isDebugEnabled())
            {
                LOGGER.debug(jsondata);
            }
        }
        return "mobile-msg-interact/data";
    }

    public String toEditSend()
    {
        List<MsgMessageInfo> msgMessageInfoList = msgMessageInfoServiceImpl.selectEntityByIds(ids, msgbox);
        if (msgMessageInfoList == null || msgMessageInfoList.size() < 1)
        {
            result = "0";
            setMessage("查询失败");
        }
        else
        {
            result = "1";
            setMessage("查询成功");
            if (forwardTp > -1 || ids.size() == 1)
            {
                MsgMessageInfo msgMessageInfo = msgMessageInfoList.get(0);
                msgMessageInfo.setSendername();
                msgMessageInfo.setAttachmentobj();
                msgMessageInfo.setReceivername(true);
                //                StringBuffer sbf = new StringBuffer();
                //                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                //                sbf.append("<div style=\"background:#eee;border:1px solid #ccc;padding:5px 10px;\"><p>主题： <strong>")
                //                .append(msgMessageInfo.getTheme()).append("</strong></p>").append("<p>发件人： ").append(msgMessageInfo.getSendername())
                //                .append("</p>").append("<p>时间： ");
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
                //                String path = ServletActionContext.getRequest().getContextPath();
                //                String basePath = ServletActionContext.getRequest().getScheme() + "://" + ServletActionContext.getRequest().getServerName() + ":"
                //                        + ServletActionContext.getRequest().getServerPort() + path + "/";
                //                
                //                for (SysAttach attach : attachList)
                //                {
                //                    sbf.append("<span>").append(attach.getAttachName()).append("&nbsp;&nbsp; (").append(attach.getFileSize())
                //                    .append("kb)&nbsp;&nbsp; ").append("<a href=\"").append(basePath)
                //                    .append("sys/sys-attach!downFile.action?sysAttachentity.attachId=").append(attach.getAttachId()).append("\">")
                //                    .append("下载文件 </a></span><br/>");
                //                }
                //                sbf.append("</p><p>&nbsp;</p><p>正文：</p>").append("<p>").append(msgMessageInfo.getBody()).append("</p></div>");
                //                msgMessageInfo.setBody(sbf.toString());
                msgMessageInfoList.clear();
                msgMessageInfoList.add(msgMessageInfo);
            }
            JSONArray jsonArray = new JSONArray();
            //json配置
            JsonConfig jsonConfig = new JsonConfig();
            //json日期格式化
            DateJsonValueProcessor dateJsonValueProcessor = new DateJsonValueProcessor();
            //注册值转换器
            jsonConfig.registerJsonValueProcessor(Date.class, dateJsonValueProcessor);
            //排除字段集合
            String[] excludes = {"callbacks", "bodysub", "dbType", "distinct", "pageNumber", "pageSize"};
            //注册排除字段
            jsonConfig.setExcludes(excludes);
            //加载集合
            jsonArray.addAll(msgMessageInfoList, jsonConfig);
            jsondata = jsonArray.toString();
            if (LOGGER.isDebugEnabled())
            {
                LOGGER.debug(jsondata);
            }
        }
        return "mobile-msg-interact/data";
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
            result = "1";
            setMessage("移动成功！");
        }
        else
        {
            result = "0";
            setWarnMessage("移动失败！");
        }
        return "mobile-msg-interact/data";
    }

    /**
     * 查看消息
     * @return
     */
    public String viewMsg()
    {
        try
        {
            if ("receive".equals(msgbox))
            {
                entity.setFlag(MsgMessageInfo.READ);
                msgMessageInfoServiceImpl.updateEntityByEntity(entity, msgbox);
            }
            entity = msgMessageInfoServiceImpl.selectOneEntityByEntity(entity, msgbox);
            if (entity == null)
            {
                setMessage("没有消息");
                result = "0";
            }
            else
            {
                result = "1";
                setMessage("查询成功");
                if (page == null)
                {
                    page = new Page();
                }
                List<MsgMessageInfo> dataList = new ArrayList<MsgMessageInfo>();
                entity.setAttachmentobj();
                entity.setReceivername(true);
                entity.setSendername();
                entity.setBody("<div style=\"background:#eee;border:1px solid #ccc;padding:5px 10px;\">" + entity.getBody() + "</div>");
                dataList.add(entity);
                page.setData(dataList);
                JSONObject jsonobj = new JSONObject();

                //json配置
                JsonConfig jsonConfig = new JsonConfig();
                //json日期格式化
                DateJsonValueProcessor dateJsonValueProcessor = new DateJsonValueProcessor();
                //注册值转换器
                jsonConfig.registerJsonValueProcessor(Date.class, dateJsonValueProcessor);
                //排除字段集合
                String[] excludes = {"callbacks", "pageNumber", "pageSize", "deleteCd", "dbType", "distinct", "receiver", "sender", "bodysub",
                        "attachment", "operUserId", "operName", "operTime", "attachState", "attachDesc", "modelCode"};

                //注册排除字段
                jsonConfig.setExcludes(excludes);
                //加载集合
                jsondata = jsonobj.accumulate("jsondata", page, jsonConfig).getJSONObject("jsondata").toString();
                if (LOGGER.isDebugEnabled())
                {
                    LOGGER.debug(jsondata);
                }
            }
        }
        catch (Exception e)
        {
            if (LOGGER.isErrorEnabled())
            {
                LOGGER.error("toReceived异常：{}", e);
            }
            setMessage("没有消息");
            result = "0";
        }
        return "mobile-msg-interact/data";
    }

    public String adressBook() throws Exception
    {
        if (userQueryEntity == null)
        {
            userQueryEntity = new GgkzUserInfoQuery();
        }
        userQueryEntity.setPageSize(1000);
        userQueryEntity.setUserState("Y");
        try
        {
            GgkzUserInfoQuery ggkzUserInfoQuery = new GgkzUserInfoQuery();
            if (lastTime != null && !"".equals(lastTime))
            {
                Long lastTimes = Long.parseLong(lastTime);
                ggkzUserInfoQuery.setUpdateTm(new Date(lastTimes));
            }
            lastTime = String.valueOf(System.currentTimeMillis());
            List<UserInfoMobile> userList = ggkzUserInfoServiceImpl.selectMoblieUsers(ggkzUserInfoQuery);

            //            page = ggkzUserInfoServiceImpl.selectUserPage(userQueryEntity);

            if (userList == null || userList.size() < 1)
            {
                setMessage("通讯录为空");
                result = "0";
            }
            else
            {
                result = "1";
                setMessage("查询成功");
                JSONArray jsonArray = new JSONArray();
                //                JSONObject jsonobj = new JSONObject();
                //json配置
                JsonConfig jsonConfig = new JsonConfig();
                //json日期格式化
                DateJsonValueProcessor dateJsonValueProcessor = new DateJsonValueProcessor();
                //注册值转换器
                jsonConfig.registerJsonValueProcessor(Date.class, dateJsonValueProcessor);
                //排除字段集合
                String[] excludes = {"callbacks"};
                //注册排除字段
                jsonConfig.setExcludes(excludes);
                //加载集合
                jsonArray.addAll(userList, jsonConfig);
                jsondata = jsonArray.toString();
                if (LOGGER.isDebugEnabled())
                {
                    LOGGER.debug(jsondata);
                }
            }
        }
        catch (Exception e)
        {
            if (LOGGER.isErrorEnabled())
            {
                LOGGER.error("toReceived异常：{}", e);
            }
            setMessage("通讯录为空");
            result = "0";
        }
        return "mobile-msg-interact/data";
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

    public String getTomsgbox()
    {
        return tomsgbox;
    }

    public void setTomsgbox(String tomsgbox)
    {
        this.tomsgbox = tomsgbox;
    }

    public String getJsonEntity()
    {
        return jsonEntity;
    }

    public void setJsonEntity(String jsonEntity)
    {
        this.jsonEntity = jsonEntity;
        JSONObject jsonobj = JSONObject.fromObject(jsonEntity);
        if (entity == null)
        {
            entity = new MsgMessageInfo();
        }
        if (jsonobj.get("id") != null)
        {

            entity.setId(jsonobj.get("id").toString());
        }
        if (jsonobj.get("receiver") != null)
        {
            entity.setReceiver(jsonobj.get("receiver").toString());
        }
        if (jsonobj.get("sender") != null)
        {
            entity.setSender(jsonobj.get("sender").toString());
        }
        if (jsonobj.get("theme") != null)
        {
            entity.setTheme(jsonobj.get("theme").toString());
        }
        if (jsonobj.get("attachment") != null)
        {
            entity.setAttachment(jsonobj.get("attachment").toString());
        }
        if (jsonobj.get("body") != null)
        {
            entity.setBody(jsonobj.get("body").toString());
        }
        if (jsonobj.get("sendtime") != null)
        {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            try
            {
                entity.setSendtime(sdf.parse(jsonobj.get("sendtime").toString()));
            }
            catch (ParseException e)
            {
                if (LOGGER.isErrorEnabled())
                {
                    LOGGER.error("setJsonEntity异常：{}", e);
                }
            }
        }
        if (jsonobj.get("flag") != null)
        {
            entity.setFlag(jsonobj.get("flag").toString());
        }
        if (jsonobj.get("pageNumber") != null)
        {
            int pageNumber = 1;
            try
            {
                pageNumber = Integer.parseInt(jsonobj.get("pageNumber").toString());
            }
            catch (NumberFormatException e)
            {
                if (LOGGER.isErrorEnabled())
                {
                    LOGGER.error("setJsonEntity异常：{}", e);
                }
            }
            entity.setPageNumber(pageNumber);
        }
        if (jsonobj.get("pageSize") != null)
        {
            int pageSize = 10;
            try
            {
                pageSize = Integer.parseInt(jsonobj.get("pageSize").toString());
            }
            catch (NumberFormatException e)
            {
                if (LOGGER.isErrorEnabled())
                {
                    LOGGER.error("setJsonEntity异常：{}", e);
                }
            }
            entity.setPageSize(pageSize);
        }

    }

    public String getJsondata()
    {
        return jsondata;
    }

    public void setJsondata(String jsondata)
    {
        this.jsondata = jsondata;
    }

    public String getResult()
    {
        return result;
    }

    public void setResult(String result)
    {
        this.result = result;
    }

    public String getLastTime()
    {
        return lastTime;
    }

    public void setLastTime(String lastTime)
    {
        this.lastTime = lastTime;
    }

    @Override
    public String getMessage()
    {
        return super.getMessage();
    }

    public List<File> getUserfile()
    {
        return userfile;
    }

    public void setUserfile(List<File> userfile)
    {
        this.userfile = userfile;
    }

    public List<String> getUserfileFileName()
    {
        return userfileFileName;
    }

    public void setUserfileFileName(List<String> userfileFileName)
    {
        this.userfileFileName = userfileFileName;
    }

    public List<String> getUserfileContentType()
    {
        return userfileContentType;
    }

    public void setUserfileContentType(List<String> userfileContentType)
    {
        this.userfileContentType = userfileContentType;
    }

    public GgkzUserInfoQuery getUserQueryEntity()
    {
        return userQueryEntity;
    }

    public void setUserQueryEntity(GgkzUserInfoQuery userQueryEntity)
    {
        this.userQueryEntity = userQueryEntity;
    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId.replaceAll("\r\n", "");
    }

    public String getUserName()
    {
        return userName;
    }

    public void setUserName(String userName)
    {
        this.userName = userName;
    }

    public int getForwardTp()
    {
        return forwardTp;
    }

    public void setForwardTp(int forwardTp)
    {
        this.forwardTp = forwardTp;
    }

}
