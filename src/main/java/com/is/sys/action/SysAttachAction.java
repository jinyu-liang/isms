package com.is.sys.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.base.mybatis.BaseStruts2Action;
import com.is.sys.entity.SysAttach;
import com.is.sys.entity.SysAttachAuth;
import com.is.sys.entity.query.SysAttachQuery;
import com.is.sys.service.SysAttachAuthServiceImpl;
import com.is.sys.service.SysAttachServiceImpl;
import com.is.utils.ImgMagick;
import com.is.utils.StringUtils;
import com.is.utils.date.DateUtil;
import com.is.utils.keyUtils.KeyGen;
import com.is.utils.swfUtil.PublicAttachOper;
import com.is.utils.swfUtil.SWFUploadHelper;

/**
 * 附件处理action
 * 
 * @author
 */
@Namespace("/sys")
public class SysAttachAction extends BaseStruts2Action
{
    private static final long        serialVersionUID = 1L;

    private static final Logger      LOGGER           = LoggerFactory.getLogger(SysAttachAction.class);

    /* 定义业务实体对象，用于新增/修改/查看 */
    protected SysAttach              sysAttachentity;

    /* 定义业务实体对象，用于文件的下载 */
    protected SysAttach              entity;

    /* 定义查询类业务实体对象，用于查询条件对象 */
    protected SysAttachQuery         queryEntity;

    private SysAttachServiceImpl     sysAttachServiceImpl;

    private SysAttachAuthServiceImpl sysAttachAuthServiceImpl;

    private List<SysAttach>          sysAttachList;                                                    // 模块

    private String                   inputPath;

    private String                   contentType;

    private String                   filename;

    private String                   downloadFlag;

    private File                     file;

    private String                   fileFileName;

    private String                   fileContentType;

    /* 上传文件的目录路径 */
    private String                   filePath;

    /* 上传文件的后缀名称 */
    private String                   attachRecord;

    /* 附件的系统生成名称 */
    private String                   attachSysName;

    /* 文件上传的提示信息 */
    private String                   uploadTip;

    /* 生成的table的id */
    private String                   upFileTable;

    /* 显示附件的时候的类型（1：编辑，2：查看） */
    public String                    fileShowType;

    /* 附件管理的业务ID */
    private String                   busiId;

    /* 附件ID */
    private String                   attachId;

    /* 已存在的附件的ID */
    private String                   fileIds;

    /* 附件列表页面中存放附件ID的父页面组件ID */
    private String                   hiddenfileIds;

    /**
     * 执行初始化方法
     */
    private void init()
    {
        // 如果查询条件为空，初始化对象，否则查询时会抛出空异常
        if (queryEntity == null)
        {
            queryEntity = new SysAttachQuery();
        }
    }

    public SysAttachAction()
    {
        super();
        init();
    }

    public InputStream getInputStream() throws Exception
    {
        return ServletActionContext.getServletContext().getResourceAsStream(inputPath);
    }

    /**
     * 文件打包下载
     * @return
     */
    public String packDownload()
    {
        boolean downsuccess = false;

        if (attachId != null && !"".equals(attachId))
        {
            SysAttach attach = null;
            String[] attachIdArr = attachId.split(";");
            if (attachIdArr != null && attachIdArr.length > 0)
            {
                String basePath = ServletActionContext.getServletContext().getRealPath("/");
                ArrayList<File> filesToAdd = new ArrayList<File>();
                for (String item : attachIdArr)
                {
                    attach = sysAttachServiceImpl.getAttachById(item);
                    if (attach != null)
                    {
                        String inputFilePath = attach.getAttachPath() + "/" + attach.getAttachId();// 要下载的文件名称

                        String filepath = basePath + inputFilePath;
                        File downloadfile = new File(filepath);
                        if (downloadfile.exists())
                        {
                            filesToAdd.add(downloadfile);
                        }
                    }
                }
                if (!filesToAdd.isEmpty())
                {
                    OutputStream fos = null;
                    BufferedOutputStream bos = null;
                    InputStream fis = null;
                    BufferedInputStream bis = null;
                    File downloadTempDic = new File(basePath + "/downloadTemp");
                    boolean createtempDirsuc = true;
                    if (!downloadTempDic.exists())
                    {
                        if (!downloadTempDic.mkdirs())
                        {
                            createtempDirsuc = false;
                        }
                    }
                    if (createtempDirsuc)
                    {
                        String zipfname = DateUtil.getCurDateTimeMil() + ".zip";
                        String zipfilePath = basePath + "/downloadTemp/" + zipfname;
                        try
                        {
                            ZipFile zipFile = new ZipFile(zipfilePath);
                            ZipParameters parameters = new ZipParameters();
                            parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
                            parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_NORMAL);
                            zipFile.addFiles(filesToAdd, parameters);

                            fis = new FileInputStream(zipfilePath);
                            bis = new BufferedInputStream(fis);
                            fos = ServletActionContext.getResponse().getOutputStream();
                            bos = new BufferedOutputStream(fos);
                            ServletActionContext.getResponse().setHeader("Content-disposition",
                                    "attachment;filename=\"" + new String(zipfname.getBytes("UTF-8"), "ISO-8859-1") + "\"");
                            int bytesRead = 0;
                            byte[] buffer = new byte[8192];
                            while ((bytesRead = bis.read(buffer, 0, 8192)) != -1)
                            {
                                bos.write(buffer, 0, bytesRead);
                                bos.flush();
                            }
                            downsuccess = true;
                        }
                        catch (Exception e)
                        {
                            if (LOGGER.isErrorEnabled())
                            {
                                LOGGER.error("packDownloa异常：{}", e);
                            }
                        }
                        finally
                        {
                            try
                            {
                                if (fis != null)
                                    fis.close();
                                if (fos != null)
                                    fos.close();
                                if (bos != null)
                                    bos.close();
                                if (bis != null)
                                    bis.close();
                            }
                            catch (Exception e)
                            {
                            }
                            File deleteFile = new File(zipfilePath);
                            if (deleteFile.exists())
                            {
                                deleteFile.delete();
                            }
                        }
                    }
                }
            }
        }
        if (!downsuccess)
        {
            OutputStream fos = null;
            BufferedOutputStream bos = null;
            InputStream fis = null;
            BufferedInputStream bis = null;
            try
            {
                fos = ServletActionContext.getResponse().getOutputStream();
                bos = new BufferedOutputStream(fos);
                ServletActionContext.getResponse().setHeader("Content-disposition",
                        "attachment;filename=\"" + new String("文件损坏".getBytes("UTF-8"), "ISO-8859-1") + "\"");
                bos.write("文件损坏或者转移到其他地方，请联系管理员！".getBytes());
                bos.flush();
            }
            catch (Exception e)
            {
            }
            finally
            {
                try
                {
                    if (fis != null)
                        fis.close();
                    if (fos != null)
                        fos.close();
                    if (bos != null)
                        bos.close();
                    if (bis != null)
                        bis.close();
                }
                catch (Exception e)
                {
                }
            }

        }
        return null;
    }

    /**
     * 文件的下载
     * 
     * @return
     * @throws Exception
     */
    public String downFile()
    {
        OutputStream fos = null;
        BufferedOutputStream bos = null;
        InputStream fis = null;
        BufferedInputStream bis = null;
        try
        {
            SysAttach attach = sysAttachServiceImpl.getAttachById(sysAttachentity.getAttachId());
            String inputFilePath = attach.getAttachPath() + "/" + attach.getAttachId();// 要下载的文件名称

            String filepath = ServletActionContext.getServletContext().getRealPath("/") + inputFilePath;
            File updownloadfile = new File(filepath);

            try
            {
                if (!updownloadfile.exists())
                {
                    fos = ServletActionContext.getResponse().getOutputStream();
                    bos = new BufferedOutputStream(fos);
                    ServletActionContext.getResponse().setHeader("Content-disposition",
                            "attachment;filename=\"" + new String("文件损坏".getBytes("UTF-8"), "ISO-8859-1") + "\"");
                    bos.write("文件损坏或者转移到其他地方，请联系管理员！".getBytes());
                    bos.flush();
                }
                fis = new FileInputStream(updownloadfile);
                bis = new BufferedInputStream(fis);
                fos = ServletActionContext.getResponse().getOutputStream();
                bos = new BufferedOutputStream(fos);
                ServletActionContext.getResponse().setHeader("Content-disposition",
                        "attachment;filename=\"" + new String(attach.getAttachName().getBytes("UTF-8"), "ISO-8859-1") + "\"");
                int bytesRead = 0;
                byte[] buffer = new byte[8192];
                while ((bytesRead = bis.read(buffer, 0, 8192)) != -1)
                {
                    bos.write(buffer, 0, bytesRead);
                    bos.flush();
                }
            }
            catch (Exception e)
            {
            }
            finally
            {
                try
                {
                    if (fis != null)
                        fis.close();
                    if (fos != null)
                        fos.close();
                    if (bos != null)
                        bos.close();
                    if (bis != null)
                        bis.close();
                }
                catch (Exception e)
                {
                }
            }
        }
        catch (Exception e)
        {
            try
            {
                fos = ServletActionContext.getResponse().getOutputStream();
                bos = new BufferedOutputStream(fos);
                ServletActionContext.getResponse().setHeader("Content-disposition",
                        "attachment;filename=\"" + new String("文件损坏".getBytes("UTF-8"), "ISO-8859-1") + "\"");
                bos.write("文件损坏或者转移到其他地方，请联系管理员！".getBytes());
                bos.flush();
            }
            catch (IOException e1)
            {
                // TODO Auto-generated catch block
                if (LOGGER.isErrorEnabled())
                {
                    LOGGER.error("downFile异常：{}", e1);
                }
            }
            finally
            {
                try
                {
                    if (fis != null)
                        fis.close();
                    if (fos != null)
                        fos.close();
                    if (bos != null)
                        bos.close();
                    if (bis != null)
                        bis.close();
                }
                catch (Exception ex)
                {
                }
            }
        }
        return null;
    }

    /**
     * 
     * @return
     * @throws Exception
     */
    public String toUpload() throws Exception
    {

        String userId = sessionUser.getUserId();

        try
        {
            sysAttachList = new ArrayList<SysAttach>();
            String businessId = ServletActionContext.getRequest().getParameter("busiId");// 业务Id
            LOGGER.debug("业务Id=【{}】和员工Id=【{}】", businessId, userId);
            SysAttachAuth attachAuth = new SysAttachAuth();
            attachAuth.setBusiId(businessId);
            attachAuth.setUserId(userId);
            List<SysAttachAuth> sysAttachAuthList = sysAttachAuthServiceImpl.selectByEntity(attachAuth);
            if (sysAttachAuthList != null && sysAttachAuthList.size() > 0)
            {
                for (int i = 0; i < sysAttachAuthList.size(); i++)
                {
                    String attId = sysAttachAuthList.get(i).getAttachId();
                    if (StringUtils.isNotEmpty(attId))
                    {
                        SysAttach attach = new SysAttach();
                        attach = sysAttachServiceImpl.getAttachById(attId);
                        if (attach != null)
                        {
                            sysAttachList.add(attach);
                        }
                    }
                }
            }
        }
        catch (Exception e)
        {
        }
        return "uploadFiles";
    }

    /**
     * 已经上传的附件列表获取
     * 
     * @return
     * @throws Exception
     */
    public String exsitsFiles() throws Exception
    {
        if (fileShowType == null || fileShowType == "")
        {
            fileShowType = "2";
        }
        if (attachId != null && !"".equals(attachId))
        {
            String files = "";
            SysAttach attach = null;
            String[] attachIdArr = attachId.split(";");
            if (sysAttachList == null)
            {
                sysAttachList = new ArrayList<SysAttach>();
            }
            for (String item : attachIdArr)
            {
                attach = sysAttachServiceImpl.getAttachById(item);
                if (attach != null)
                {
                    sysAttachList.add(attach);
                    files = files + attach.getAttachId() + ";";
                }
            }
        }
        else
        {
            String userId = sessionUser.getUserId();
            try
            {
                String files = "";// 获取有权限的所有附件ID
                sysAttachList = new ArrayList<SysAttach>();
                if (busiId == null || "".equals(busiId))
                {
                    return "up-down-load/_exsits_files";
                }
                LOGGER.debug("业务Id=【{}】和员工Id=【{}】", busiId, userId);
                if (StringUtils.isEmpty(busiId))
                {
                }
                else
                {
                    SysAttachAuth attachAuth = new SysAttachAuth();
                    attachAuth.setBusiId(busiId);
                    List<SysAttachAuth> sysAttachAuthList = sysAttachAuthServiceImpl.selectByEntity(attachAuth);
                    if (sysAttachAuthList != null && sysAttachAuthList.size() > 0)
                    {
                        for (int i = 0; i < sysAttachAuthList.size(); i++)
                        {
                            String attId = sysAttachAuthList.get(i).getAttachId();
                            if (StringUtils.isNotEmpty(attId))
                            {
                                SysAttach attach = new SysAttach();
                                attach = sysAttachServiceImpl.getAttachById(attId);
                                if (attach != null)
                                {
                                    sysAttachList.add(attach);
                                    files = files + attach.getAttachId() + ";";
                                }
                            }
                        }
                    }
                }
                fileIds = files;
            }
            catch (Exception e)
            {
            }
        }
        return "up-down-load/_exsits_files";
    }

    /**
     * SWFUpload文件上传控件的路径
     * 
     * @return
     * @throws Exception
     */
    public String uploadFiles() throws Exception
    {

        String businessId = ServletActionContext.getRequest().getParameter("busiId");// 业务Id
        String userId = ServletActionContext.getRequest().getParameter("userId");
        String username = ServletActionContext.getRequest().getParameter("username");
        String modelFileDirPath = ServletActionContext.getRequest().getParameter("modelCodeForAttach");// 模块代码

        if (StringUtils.isEmpty(modelFileDirPath))
        {
            modelFileDirPath = "pub";
        }

        LOGGER.debug("获取到附件模块代码[{}]", modelFileDirPath);

        // 实现上传
        String path = "/upload/" + modelFileDirPath;// 附件上传目录路径
        String dateStr = DateUtil.getCurDateByyyyymmdd();
        String dirName = path + "/" + dateStr;
        String fsuffix = SWFUploadHelper.getFileType(fileFileName);
        String sysFileName = KeyGen.getCommonKeyGen("attach") + "." + fsuffix;// 系统生成的文件名称

        try
        {
            LOGGER.debug("附件目录为【{}】", ServletActionContext.getServletContext().getRealPath("/") + dirName);
            SWFUploadHelper.uploadFiles(file, ServletActionContext.getServletContext().getRealPath("/") + dirName, sysFileName);
            String filesuffix = fsuffix.toLowerCase().toLowerCase();
            if ("jpeg".equals(filesuffix) || "jpg".equals(filesuffix) || "jpe".equals(filesuffix) || "gif".equals(filesuffix)
                    || "bmp".equals(filesuffix) || "cod".equals(filesuffix) || "ief".equals(filesuffix) || "jfif".equals(filesuffix)
                    || "svg".equals(filesuffix) || "tif".equals(filesuffix) || "tiff".equals(filesuffix) || "ras".equals(filesuffix)
                    || "cmx".equals(filesuffix) || "ico".equals(filesuffix) || "pnm".equals(filesuffix) || "pbm".equals(filesuffix)
                    || "pgm".equals(filesuffix) || "ppm".equals(filesuffix) || "rgb".equals(filesuffix) || "xbm".equals(filesuffix)
                    || "xpm".equals(filesuffix) || "xwd".equals(filesuffix))
            {
                String rst = ImgMagick.resizeImg(ServletActionContext.getServletContext().getRealPath("/") + dirName + File.separator + sysFileName);
                if (rst != null)
                {
                    sysFileName = rst + "." + fsuffix;
                    file = new File(ServletActionContext.getServletContext().getRealPath("/") + dirName + File.separator + sysFileName);
                }
            }
            uploadTip = "附件上传成功！";
        }
        catch (Exception e)
        {
            uploadTip = "附件上传失败！";
        }
        filePath = dirName;
        attachSysName = sysFileName;

        StringBuffer sb = new StringBuffer();
        sb.append("{filePath:'").append(filePath).append("',attachRecord:'").append(attachRecord).append("',uploadTip:'").append(uploadTip)
                .append("',attachSysName:'").append(attachSysName).append("',createTime:'").append(dateStr).append("'}");

        ServletActionContext.getResponse().setCharacterEncoding("UTF-8");
        PrintWriter out = ServletActionContext.getResponse().getWriter();
        out.write(sb.toString());
        // 保存附件表
        PublicAttachOper.publicAbbachSave(businessId, sysFileName, dirName, file, fileFileName, modelFileDirPath, userId, username);

        return null;
    }

    /**
     * 进行附件的删除操作
     * 
     * @return
     * @throws Exception
     */
    public String deleteUpFile() throws Exception
    {
        String businessId = ServletActionContext.getRequest().getParameter("busiId");// 业务Id
        String upUrl = ServletActionContext.getRequest().getParameter("upurl");
        String fileUrl = ServletActionContext.getServletContext().getRealPath("/") + upUrl;

        String modelFileDirPath = ServletActionContext.getRequest().getParameter("modelCodeForAttach");// 模块代码
        if (StringUtils.isEmpty(modelFileDirPath))
        {
            modelFileDirPath = "pub";
        }
        String path = "/upload/" + modelFileDirPath;// 附件上传目录路径

        String dateStr = DateUtil.getCurDateByyyyymmdd();
        String dirName = path + "/" + dateStr;
        LOGGER.debug("存放的路径为{},业务Id为{}", dirName, businessId);

        String fileName = SWFUploadHelper.getFileName(fileUrl);

        LOGGER.debug("要删除的文件是{}" + fileName + "文件路径" + fileUrl);

        File upFile = new File(fileUrl);
        if (upFile.exists())
        {
            try
            {
                upFile.delete();
                setMessage("删除成功！");
            }
            catch (Exception e)
            {
                setWarnMessage("删除失败！");
            }
        }
        else
        {
            setInfoMessage("文件不存在！");
        }
        // 删除数据库对应的文件
        SysAttach att = new SysAttach();
        att.setAttachId(fileName);
        SysAttachAuth attauth = new SysAttachAuth();
        attauth.setAttachId(fileName);
        attauth.setBusiId(businessId);
        sysAttachServiceImpl.deleteFile(attauth, att);

        return JSON_DATA;
    }

    public String toFileUpload() throws Exception
    {
        return "up-down-load/fileUpload";
    }

    public SysAttach getSysAttachentity()
    {
        return sysAttachentity;
    }

    public void setSysAttachentity(SysAttach sysAttachentity)
    {
        this.sysAttachentity = sysAttachentity;
    }

    public SysAttach getEntity()
    {
        return entity;
    }

    public void setEntity(SysAttach entity)
    {
        this.entity = entity;
    }

    public SysAttachQuery getQueryEntity()
    {
        return queryEntity;
    }

    public void setQueryEntity(SysAttachQuery queryEntity)
    {
        this.queryEntity = queryEntity;
    }

    public List<SysAttach> getSysAttachList()
    {
        return sysAttachList;
    }

    public void setSysAttachList(List<SysAttach> sysAttachList)
    {
        this.sysAttachList = sysAttachList;
    }

    public String getInputPath()
    {
        return inputPath;
    }

    public void setInputPath(String inputPath)
    {
        this.inputPath = inputPath;
    }

    public String getContentType()
    {
        return contentType;
    }

    public void setContentType(String contentType)
    {
        this.contentType = contentType;
    }

    public String getFilename()
    {
        return filename;
    }

    public void setFilename(String filename)
    {
        this.filename = filename;
    }

    public String getDownloadFlag()
    {
        return downloadFlag;
    }

    public void setDownloadFlag(String downloadFlag)
    {
        this.downloadFlag = downloadFlag;
    }

    public File getFile()
    {
        return file;
    }

    public void setFile(File file)
    {
        this.file = file;
    }

    public String getFileFileName()
    {
        return fileFileName;
    }

    public void setFileFileName(String fileFileName)
    {
        this.fileFileName = fileFileName;
    }

    public String getFileContentType()
    {
        return fileContentType;
    }

    public void setFileContentType(String fileContentType)
    {
        this.fileContentType = fileContentType;
    }

    public String getFilePath()
    {
        return filePath;
    }

    public void setFilePath(String filePath)
    {
        this.filePath = filePath;
    }

    public String getAttachRecord()
    {
        return attachRecord;
    }

    public void setAttachRecord(String attachRecord)
    {
        this.attachRecord = attachRecord;
    }

    public String getAttachSysName()
    {
        return attachSysName;
    }

    public void setAttachSysName(String attachSysName)
    {
        this.attachSysName = attachSysName;
    }

    public String getUploadTip()
    {
        return uploadTip;
    }

    public void setUploadTip(String uploadTip)
    {
        this.uploadTip = uploadTip;
    }

    public String getUpFileTable()
    {
        return upFileTable;
    }

    public void setUpFileTable(String upFileTable)
    {
        this.upFileTable = upFileTable;
    }

    public String getFileShowType()
    {
        return fileShowType;
    }

    public void setFileShowType(String fileShowType)
    {
        this.fileShowType = fileShowType;
    }

    public String getBusiId()
    {
        return busiId;
    }

    public void setBusiId(String busiId)
    {
        this.busiId = busiId;
    }

    public String getFileIds()
    {
        return fileIds;
    }

    public void setFileIds(String fileIds)
    {
        this.fileIds = fileIds;
    }

    public String getHiddenfileIds()
    {
        return hiddenfileIds;
    }

    public void setHiddenfileIds(String hiddenfileIds)
    {
        this.hiddenfileIds = hiddenfileIds;
    }

    public void setSysAttachServiceImpl(SysAttachServiceImpl sysAttachServiceImpl)
    {
        this.sysAttachServiceImpl = sysAttachServiceImpl;
    }

    public void setSysAttachAuthServiceImpl(SysAttachAuthServiceImpl sysAttachAuthServiceImpl)
    {
        this.sysAttachAuthServiceImpl = sysAttachAuthServiceImpl;
    }

    public String getAttachId()
    {
        return attachId;
    }

    public void setAttachId(String attachId)
    {
        this.attachId = attachId;
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

}
