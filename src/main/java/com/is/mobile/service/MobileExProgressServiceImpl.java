package com.is.mobile.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.hibernate.tool.hbm2x.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.is.ggkz.dao.GgkzUserInfoDaoImpl;
import com.is.ggkz.entity.GgkzUserInfo;
import com.is.mobile.util.WsUtil;
import com.is.mq.producer.PmsMessageTopicProducer;
import com.is.pretrst.dao.DExProgressDaoImpl;
import com.is.pretrst.dao.DExProjectDaoImpl;
import com.is.pretrst.dao.DProgressImageDaoImpl;
import com.is.pretrst.dao.DProgressReportDaoImpl;
import com.is.pretrst.dao.MWorkshopDaoImpl;
import com.is.pretrst.entity.DExProgress;
import com.is.pretrst.entity.DExProgressExt;
import com.is.pretrst.entity.DExProject;
import com.is.pretrst.entity.DExProjectExt;
import com.is.pretrst.entity.DProgressImage;
import com.is.pretrst.entity.DProgressReport;
import com.is.pretrst.entity.MWorkshop;
import com.is.utils.DateUtils;
import com.is.utils.PublicDict;
import com.is.utils.keyUtils.KeyGen;
import com.is.utils.sysOperLog.OperLogUtil;

/**
 * 
 * <p>
 * 文件名称: MobileExProgressServiceImpl.java
 * </p>
 * <p>
 * 文件描述: 手机对进度模块操作的实现
 * </p>
 * <p>
 * 版权所有: 版权所有(C)2013-2020
 * </p>
 * <p>
 * 公 司: IS软件事业部
 * </p>
 * <p>
 * 内容摘要:
 * <p>
 * 其他说明:只要能够请求到本方法，即有进度的的权限
 * </p>
 * <p>
 * 完成日期：2014年9月14日
 * </p>
 * <p>
 * 修改记录0：无
 * </p>
 * 
 * @version 1.0
 * @author 
 */
@Component
@Transactional
public class MobileExProgressServiceImpl
{
    @SuppressWarnings("unused")
    private static Logger           logger   = LoggerFactory.getLogger(MobileExProgressServiceImpl.class);

    @Autowired
    private DExProgressDaoImpl      dexProgressDaoImpl;

    @Autowired
    private MWorkshopDaoImpl        mworkshopDaoImpl;

    @Autowired
    private DExProjectDaoImpl       dexProjectDaoImpl;

    @Autowired
    private DProgressReportDaoImpl  dprogressReportDaoImpl;

    @Autowired
    private DProgressImageDaoImpl   dprogressImageDaoImpl;

    @Autowired
    private PmsMessageTopicProducer pmsMessageTopicProducer;

    @Autowired
    private GgkzUserInfoDaoImpl     ggkzUserInfoDaoImpl;

    String                          statusCd = "";

    /**
     * 根据角色获得进行中的的项目
     * 
     * @param userId
     * @return
     */

    public List<DExProject> getProgressList(String userId)
    {
        List<DExProject> projectList = new ArrayList<DExProject>();
        String post = WsUtil.getPostByUserId(userId);// 当前用户的职务代码
        if ("38".equals(post))
        {// 职务为区域部长
            projectList = this.areaHeadProject(userId);
        }
        else if ("1".equals(post) || "3".equals(post))
        {// 职务为总经理或部长
            projectList = this.allProject();// 查询所有
        }
        else
        {// 项目经理和其他职务只查询自己相关的数据
            projectList = this.siteProject(userId);
        }
        return this.getProgressByLastReportId(projectList, userId);
    }

    /**
     * 总经理与部长，查询所有的进行中外线工地信息
     * 
     * @return
     */
    public List<DExProject> allProject()
    {
        /*List<MWorkshop> shopList = mworkshopDaoImpl.selectAllEntityByNow();// 查询所有进行中的项目集合
        List<DExProject> projectList = new ArrayList<DExProject>();
        for (MWorkshop p : shopList) {
        	DExProject project = new DExProject();
        	project.setWsCd(p.getWsCd());
        	DExProject pro = dexProjectDaoImpl.selectOneByEntity(project);
        	if (pro != null) {
        		projectList.add(pro);
        	}
        	;
        }
        return projectList;*/
        return dexProjectDaoImpl.selectDoingProject();
    }

    /**
     * 区域部长本人的和下属的进行中的外线工地信息
     * 
     * @param Id
     * @return
     */
    public List<DExProject> areaHeadProject(String userId)
    {
        List<DExProject> projectList = new ArrayList<DExProject>();
        projectList.addAll(this.siteProject(userId));
        List<String> strList = WsUtil.getSiteUserIdByEareaheadUserId(userId);// 查询区域部长所有下属项目经理的id
        for (String siteId : strList)
        {
            List<DExProject> siteList = this.siteProject(siteId);
            if (siteList != null && siteList.size() != 0)
            {
                projectList.addAll(siteList);// 查询区域部长所有下属项目经理的进行中的项目
            }
        }
        return projectList;
    }

    /**
     * 项目经理查询跟自己相关的外线工地信息
     * 
     * @param userId
     * @return
     */
    public List<DExProject> siteProject(String userId)
    {
        MWorkshop shop = new MWorkshop();
        shop.setManagerUserId(userId);
        List<MWorkshop> shopList = mworkshopDaoImpl.selectEntityByNow(shop);// 查询进行中的项目集合
        List<DExProject> projectList = new ArrayList<DExProject>();
        for (MWorkshop p : shopList)
        {
            DExProject project = new DExProject();
            project.setWsCd(p.getWsCd());
            DExProject pro = dexProjectDaoImpl.selectOneByEntity(project);
            if (pro != null)
            {
                projectList.add(pro);
            }
            ;
        }
        return projectList;
    }

    /**
     * 循环封装每一个dexproject对象对应的最新dexprogress
     * 
     * @param projectList
     * @return
     */
    public List<DExProject> getProgressByLastReportId(List<DExProject> projectList, String userId)
    {
        List<DExProject> proList = new ArrayList<DExProject>();
        for (DExProject p : projectList)
        {
            if (userId.equals(getProjectIdByManger(p.getWsCd())))
            {// 判断当前登陆人是否有更新的权限
                p.setIsUpdate("1");
            }
            else
            {
                p.setIsUpdate("0");
            }
            DExProgress progress = new DExProgress();
            progress.setReportId(p.getLastReportId());
            progress.setProjectId(p.getProjectId());
            //			DExProgress progressInfo=dexProgressDaoImpl.selectGressByLastReportId(progress);

            if (p.getLastReportId() != null && !"".equals(p.getLastReportId()))
            {// 当前项目有进度，查询最近的进度信息给前台
                progress.setReportId(p.getLastReportId());// 取出最新的进度reportId
                progress = dexProgressDaoImpl.selectOneByEntityMobile(progress);// 查询工程进度
                if ("1".equals(p.getIsUpdate()) && !p.getLastReportDt().equals(DateUtils.newymdDate()))
                {//本人并且不是当天需要去掉图片展示
                    progress.setDprogressImage(null);
                }
            }
            else
            {
                progress.setWsLeader(getNameById(getProjectIdByManger(p.getWsCd())));// 带队人员
                progress.setViceManager(getAreaNameBysiteId(getProjectIdByManger(p.getWsCd())));// 分管副部长
            }
            p.setdExProgresss(progress);
            proList.add(p);

        }
        return proList;
    }

    /**
     * 增加外线进度信息，增加外线报告信息，更新进度项目信息|-或者-|更新外线进度信息，更新外线报告信息，更新进度项目信息
     * 
     * @param projectId
     * @param entity
     * @return
     * @throws IOException
     */
    public String insert(String projectId, String lastReportDt, DExProgress entity, String userId, List<File> userfile) throws IOException
    {
        int flag = 0;
        DProgressReport report = new DProgressReport();
        Date today = DateUtils.newymdDate();
        DExProject project = new DExProject();
        project.setProjectId(projectId);
        Date proDt = dexProjectDaoImpl.selectOneByEntity(project).getLastReportDt();
        project.setLastReportDt(DateUtils.newymdDate());
        flag += dexProjectDaoImpl.updateByEntity(project);// 更新外线工程最新的进度报告id

        project = dexProjectDaoImpl.selectOneByEntity(project);
        if (project != null && proDt != null && proDt.equals(today))
        {// 时间相等，是今天
            DExProgress gress = new DExProgress();
            gress.setReportId(project.getLastReportId());
            flag += dexProgressDaoImpl.deleteByEntity(gress);// 删除以前的进度
            // 删除本条进度的所有图片，前台会把以前的再传过来
            /*DProgressImage img = new DProgressImage();
            img.setReportId(entity.getReportId());
            List<DProgressImage> imgList = dprogressImageDaoImpl.selectByEntity(img);
            String path = ServletActionContext.getServletContext().getRealPath("/");// 系统根目录
            for (DProgressImage image : imgList)
            {// 删除以前的进度图片
                if (StringUtils.isNotEmpty(image.getFilename()))
                {
                    File file = new File(path + image.getFilename());
                    // 路径为文件且不为空则进行删除
                    if (file.isFile() && file.exists())
                    {
                        file.delete();
                    }
                }
            }
            flag += dprogressImageDaoImpl.deleteByEntity(img);//删除以前的进度图片数据*/
            entity.setReportId(project.getLastReportId());
            flag += dexProgressDaoImpl.insert(entity);// 添加进度信息对象
            flag += this.saveImage(userfile, entity, userId);//保存图片
            report.setReportId(entity.getReportId());
            report.setUpdateTm(new Date());
            flag += dprogressReportDaoImpl.updateByEntity(report);//更新进度报告对象
            //发送通知消息
            pmsMessageTopicProducer.sendTopic("post3", "项目进度通知", this.getNameById(userId) + "上传了项目进度报告，请您及时查看", userId);
        }
        else
        {//不是今天上传
            entity.setReportId(KeyGen.getCommonKeyGen(PublicDict.D_EXPROGRESS));//设置外线进度主键
            report.setReportId(entity.getReportId());
            report.setWsCd(this.getExProject(projectId));
            report.setCreateTm(new Date());
            report.setReportDt(new Date());
            report.setUserCd(userId);
            report.setUpdateTm(new Date());
            flag = dprogressReportDaoImpl.insert(report);//添加进度报告对象
            flag += dexProgressDaoImpl.insert(entity);//添加进度信息对象
            flag += this.saveImage(userfile, entity, userId);//保存图片
            project.setLastReportId(entity.getReportId());
            flag += dexProjectDaoImpl.updateByEntity(project);// 更新外线工程最新的进度报告id
            //发送通知消息
            pmsMessageTopicProducer.sendTopic("post3", "项目进度通知", this.getNameById(userId) + "上传了项目进度报告，请您及时查看", userId);
        }
        if (flag != 0)
        {
            // 添加日志
            OperLogUtil.insertMobileOperLog(entity.getReportId(), userId, PublicDict.MODEL_EXPROGRESS, "进度管理", PublicDict.OPER_LOG_EVENT_UPDATE,
                    "更新", "手机端更新外线进度信息", "更新成功", "手机端更新外线进度信息", "d_ex_progress");
            // 添加日志
            OperLogUtil.insertMobileOperLog(entity.getReportId(), userId, PublicDict.MODEL_EXPROGRESS, "进度管理", PublicDict.OPER_LOG_EVENT_UPDATE,
                    "更新", "手机端更新外线进度图片", "更新成功", "手机端更新外线进度图片", "d_progress_image");
            return "0";// 成功
        }
        else
            return "1";
    }

    /**
     * 保存图片
     * @param userfile
     * @return
     * @throws IOException 
     */
    public int saveImage(List<File> userfile, DExProgress entity, String userId) throws IOException
    {
        int flag = 0;
        if (userfile != null && userfile.size() > 0)
        {
            for (int i = 0; i < userfile.size(); i++)// 保存图片
            {
                String photoId = KeyGen.getCommonKeyGen(PublicDict.USERPHOTO);
                String rootPath = Thread.currentThread().getContextClassLoader().getResource("/").getPath();
                rootPath = rootPath.substring(0, rootPath.length() - 16) + "upload/";
                String filename = photoId + ".jpg";
                FileInputStream in = new FileInputStream(userfile.get(i));
                FileOutputStream out = new FileOutputStream(rootPath + filename);
                byte[] b = new byte[10240];
                int len = 0;
                while ((len = in.read(b)) > 0)
                {
                    out.write(b, 0, len);
                }
                in.close();
                out.close();
                DProgressImage image = new DProgressImage();
                image.setReportId(entity.getReportId());
                filename = "/upload/" + filename;
                image.setFilename(filename);
                image.setPhotoUserCd(userId);
                image.setUploadTm(new Date());
                image.setImageId(KeyGen.getCommonKeyGen(PublicDict.USERPHOTO));// 主键
                flag += dprogressImageDaoImpl.insert(image);
            }
        }
        return flag;
    }

    /**
     * 根据projectId查询工地Cd
     * 
     * @param projectId
     * @return
     */
    public String getExProject(String projectId)
    {
        DExProject project = new DExProject();
        project.setProjectId(projectId);
        project = dexProjectDaoImpl.selectOneByEntity(project);
        if (project != null && StringUtils.isNotEmpty(project.getWsCd()))
        {
            return project.getWsCd();
        }
        return "";
    }

    /**
     * 根据用户id查询用户名
     * 
     * @param userId
     * @return 用户名（String）
     */
    public String getNameById(String userId)
    {
        GgkzUserInfo entity = new GgkzUserInfo();
        entity.setUserId(userId);
        entity = ggkzUserInfoDaoImpl.selectOneByEntity(entity);
        if (entity != null && StringUtils.isNotEmpty(entity.getName()))
        {
            return entity.getName();
        }
        return "";
    }

    /**
     * 根据用户id查询区域部长名称
     * 
     * @param siteId
     *            项目经理id
     * @return
     */
    public String getAreaNameBysiteId(String siteId)
    {
        GgkzUserInfo info = new GgkzUserInfo();
        if (StringUtils.isNotEmpty(siteId))
        {
            info.setUserId(siteId);
            info = ggkzUserInfoDaoImpl.selectOneByEntity(info);
            if (StringUtils.isNotEmpty(info.getHeadUserCd()))
            {
                return this.getNameById(info.getHeadUserCd());
            }
        }
        return "";
    }

    /**
     * 根据项目wscd查询负责人id
     * 
     * @param wsCd
     * @return
     */
    public String getProjectIdByManger(String wsCd)
    {
        MWorkshop shop = new MWorkshop();
        if (StringUtils.isNotEmpty(wsCd))
        {
            shop.setWsCd(wsCd);
            shop = mworkshopDaoImpl.selectOneByEntity(shop);
            if (StringUtils.isNotEmpty(shop.getManagerUserId()))
            {
                return shop.getManagerUserId();
            }
        }
        return "";

    }

    /*------------------------------------------------------------------------------------ */

    /**
     * 根据角色获得进行中的的项目
     * 
     * @param userId
     * @return
     */

    public List<DExProjectExt> getProjectExtList(String userId)
    {
        List<DExProjectExt> projectList = new ArrayList<DExProjectExt>();
        String post = WsUtil.getPostByUserId(userId);// 当前用户的职务代码
        if ("38".equals(post))
        {// 职务为区域部长
            projectList = this.areaHeadProjectExt(userId);
        }
        else if ("1".equals(post) || "3".equals(post) || "11".equals(post) || "2".equals(post) || "6".equals(post))
        {// 职务为总经理或部长或信息主管和部门领导信息主管和董事长
            projectList = this.allProjectExt();// 查询所有
        }
        else
        {// 项目经理和其他职务只查询自己相关的数据
            projectList = this.siteProjectExt(userId);
        }
        return projectList;
    }

    /**
     * 区域部长本人的和下属的进行中的外线工地信息
     * 
     * @param userId 区域部长的id
     * @return
     */
    public List<DExProjectExt> areaHeadProjectExt(String userId)
    {
        List<DExProjectExt> projectExtList = new ArrayList<DExProjectExt>();
        projectExtList.addAll(this.siteProjectExt(userId));//查询自己相关的项目
        projectExtList.addAll(dexProjectDaoImpl.selectAreaDoingProjectExt(userId));
        return projectExtList;
    }

    /**
     * 项目经理查询跟自己相关的外线工地信息
     * 
     * @param userId 项目经理的id
     * @return
     */
    public List<DExProjectExt> siteProjectExt(String userId)
    {
        return dexProjectDaoImpl.selectSiteDoingProjectExt(userId);
    }

    /**
     * 总经理与部长，查询所有的进行中外线工地信息
     * 
     * @return
     */
    public List<DExProjectExt> allProjectExt()
    {
        return dexProjectDaoImpl.selectAllDoingProjectExt();
    }

    /**
     * 获得最新的进度信息
     * @param lastReportDt
     * @param lastReportId
     * @param userId
     * @return
     */
    public DExProgressExt getProgress(String projectId, String lastReportDt, String lastReportId, String wsCd, String userId)
    {
        DExProgressExt progressExt = new DExProgressExt();
        String isUpdate = "0";
        if (userId.equals(selectMangerIdByWsCd(wsCd)))
        {// 判断当前登陆人有更新的权限
            isUpdate = "1";
        }
        if (lastReportId != null && !"".equals(lastReportId))
        {// 当前项目有进度，查询最近的进度信息给前台
            DExProgressExt proExt = dexProgressDaoImpl.selectProgressExtByReportId(lastReportId);
            if (proExt != null)// 查询工程进度
            {
                progressExt = proExt;
            }
            else
            {//因为进度信息与展示的项目的三个字段是一个对象，如果查不到到进度，然后单独去查这三个字段
                progressExt = dexProgressDaoImpl.selectProjectByProjectId(projectId);//把详情界面展示工地的三个字段和一个主键查出来
            }
            if ("1".equals(isUpdate) && !lastReportDt.equals(DateUtils.dateTimeToString(DateUtils.newymdDate(), "yyyy-MM-dd")))
            {//本人并且不是当天查看进度不要图片展示
                progressExt.setDprogressImage(null);
            }
            else
            {
                progressExt.setDprogressImage(dexProgressDaoImpl.selectImgByReportId(lastReportId));//查询进度图片
            }
        }
        else
        //没有进度时封装默认展示的内容
        {
            progressExt = dexProgressDaoImpl.selectProjectByProjectId(projectId);//把详情界面展示工地的三个字段和一个主键查出来
            progressExt.setWsLeader(getNameById(selectMangerIdByWsCd(wsCd)));// 带队人员
            progressExt.setViceManager(getAreaNameBysiteId(selectMangerIdByWsCd(wsCd)));// 分管副部长
        }
        progressExt.setDexItemExt(dexProgressDaoImpl.selectItemByProjectId(projectId));//根据工程ID查询工地产品详情
        progressExt.setIsUpdate(isUpdate);
        return progressExt;
    }

    /**
     * 根据项目WSCD查询负责人id
     * @param wsCd
     * @return String 工地的MangerId
     */
    public String selectMangerIdByWsCd(String wsCd)
    {
        return dexProgressDaoImpl.selectMangerIdByWsCd(wsCd);
    }
}
