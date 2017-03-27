package com.is.mobile.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.is.ggkz.dao.GgkzUserInfoDaoImpl;
import com.is.ggkz.entity.GgkzUserInfo;
import com.is.ggkz.entity.query.GgkzUserInfoQuery;
import com.is.mobile.util.WsUtil;
import com.is.mq.producer.PmsMessageTopicProducer;
import com.is.pretrst.dao.DReportImageDaoImpl;
import com.is.pretrst.dao.ExPersonInfoDaoImpl;
import com.is.pretrst.dao.ExPersonManagerDaoImpl;
import com.is.pretrst.dao.ExWorkTypeDaoImpl;
import com.is.pretrst.dao.MTeamDaoImpl;
import com.is.pretrst.dao.MWorkshopDaoImpl;
import com.is.pretrst.entity.DReportImage;
import com.is.pretrst.entity.ExPersonInfo;
import com.is.pretrst.entity.ExPersonManager;
import com.is.pretrst.entity.ExWorkType;
import com.is.pretrst.entity.MTeam;
import com.is.pretrst.entity.MWorkshop;
import com.is.pretrst.entity.query.ExPersonInfoQuery;
import com.is.pretrst.entity.query.ExPersonManagerQuery;
import com.is.pretrst.entity.query.ExWorkTypeQuery;
import com.is.pretrst.entity.query.MTeamQuery;
import com.is.pretrst.service.ExPersonManagerServiceImpl;
import com.is.utils.ChineseFirstLetterUtil;
import com.is.utils.PublicDict;
import com.is.utils.StringUtils;
import com.is.utils.keyUtils.KeyGen;
import com.is.utils.sysOperLog.OperLogUtil;

/**
 * 
 * <p>
 * 文件名称: MobileSendMetralServiceImpl.java
 * </p>
 * <p>
 * 文件描述: 手机登录验证服务实现
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
 * 其他说明:只要能够请求到本方法，即有外线人员的的权限
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
public class MobilePersonManageServiceImpl
{
    @Autowired
    private ExPersonManagerServiceImpl exPersonManagerServiceImpl;

    @Autowired
    private ExPersonManagerDaoImpl     exPersonManagerDaoImpl;

    // 外线人员图片
    @Autowired
    private ExPersonInfoDaoImpl        exPersonInfoDaoImpl;

    @Autowired
    private DReportImageDaoImpl        dReportImageDaoImpl;

    @Autowired
    private GgkzUserInfoDaoImpl        ggkzUserInfoDaoImpl;

    @Autowired
    private PmsMessageTopicProducer    pmsMessageTopicProducer;

    @Autowired
    private MWorkshopDaoImpl           mWorkshopDaoImpl;

    @Autowired
    private MTeamDaoImpl               mTeamDaoImpl;

    @Autowired
    private ExWorkTypeDaoImpl          exWorkTypeDaoImpl;

    @SuppressWarnings("unused")
    private static Logger              LOGGER   = LoggerFactory.getLogger(MobilePersonManageServiceImpl.class);

    String                             statusCd = "";

    /**
     * 外线人员安卓客户端上传接口
     * 
     * @param title
     *            标题
     * @param workCenterId
     *            工地中心
     * @param teamCd
     *            班组代码
     * @param memo
     *            备注
     * @param reportUserCd
     *            报告人
     * @param inStreamList
     *            图片流
     *@param     personInfoList 保险人员列表        
     * @return String 0:成功 ;1：失败
     * @throws IOException
     */
    public String PersonManageUpload(String reportId, String title, String workCenterId, String teamCd, String memo, String reportUserCd, List<File> userfile,
            List<ExPersonInfo> personInfoList) throws IOException
    {
        String logFlag = reportId;
        LOGGER.debug("传入的值为=======[{}]", personInfoList);
        int i = 0;
        ExPersonManager report = new ExPersonManager();
        if (StringUtils.isEmpty(reportId))
        {
            report.setStatusCd("0");
            reportId = KeyGen.getCommonKeyGen(PublicDict.DReport_id);
            report.setReportId(reportId);
            report.setTitle(title);
            report.setTeamCd(teamCd);
            report.setWorkCenterId(workCenterId);
            report.setMemo(memo);
            report.setReportTm(new Date());
            report.setReportUserCd(reportUserCd);
            report.setReportUserName(getUserNameByAndroid(reportUserCd));
            i += exPersonManagerDaoImpl.insert(report);
        }
        else
        {
            report.setReportId(reportId);
            report = exPersonManagerServiceImpl.getPersonManager(report);
            report.setTitle(title);
            report.setStatusCd("0");
            report.setMemo(memo);
            i += exPersonManagerDaoImpl.updateByEntity(report);
        }
        //处理上传保险人员明细
        LOGGER.debug("reportId的值为=【{}】", reportId);
        if (personInfoList != null && personInfoList.size() > 0)
        {
            i += personListDealUpload(personInfoList, reportId, workCenterId);
        }

        if (userfile != null && userfile.size() > 0)
        {
            // 图片处理方法
            i += imageDeal(reportId, reportUserCd, userfile);
        }
        if (i != 0)
        {// 成功
         // 添加日志
            if (StringUtils.isEmpty(logFlag))
            {
                OperLogUtil.insertMobileOperLog(reportId, reportUserCd, PublicDict.MODEL_PERSON, "外线人员管理", PublicDict.OPER_LOG_EVENT_ADD, "添加", "手机端添加外线人员信息", "添加成功",
                        "手机端添加外线人员信息", "ex_person_manager");
            }
            else
            {
                OperLogUtil.insertMobileOperLog(reportId, reportUserCd, PublicDict.MODEL_PERSON, "外线人员管理", PublicDict.OPER_LOG_EVENT_ADD, "再次上传", "手机端添加外线人员信息再次上传信息", "上传成功",
                        "手机端添加外线人员信息手机端添加外线人员信息再次上传信息", "ex_person_manager");
            }
            // 发消息接口,发给部长
            pmsMessageTopicProducer.sendTopic("post3", "【" + report.getReportUserName() + "】发起工地外线人员申请", "【" + report.getReportUserName() + "】在工地【" + report.getWorkCenterId()
                    + "】已经发起外线人员申请【" + report.getTitle() + "】,请处理", report.getReportUserCd());
            return "0";
        }
        else
        {
            return "1";
        }
    }

    /**
     * 获取工地的施工队
     * @param workCenterId
     * @return
     */
    public List<String> getMteamList(String workCenterId)
    {
        MTeamQuery mTeam = new MTeamQuery();
        mTeam.setWsNm(workCenterId);
        List<String> teamList = new ArrayList<String>();
        List<MTeam> tList = new ArrayList<MTeam>();
        tList = mTeamDaoImpl.selectByEntity(mTeam);
        if (tList != null && tList.size() > 0)
        {
            for (int i = 0; i < tList.size(); i++)
            {
                teamList.add(tList.get(i).getTeamNm());
            }

        }
        return teamList;
    }

    /**
     * 获取工地的施工队
     * @param workCenterId
     * @return
     */
    public List<String> getWorkTypeList()
    {
        List<String> teamList = new ArrayList<String>();
        List<ExWorkType> wList = new ArrayList<ExWorkType>();
        wList = exWorkTypeDaoImpl.selectAll();
        if (wList != null && wList.size() > 0)
        {
            for (int i = 0; i < wList.size(); i++)
            {
                teamList.add(wList.get(i).getWorkNm());
            }
        }
        return teamList;
    }

    /**
     * 处理外线人员上传的明细列表
     * @param entity
     */
    public int personListDealUpload(List<ExPersonInfo> infoList, String reportId, String wsNm)
    {
        int i = 0;
        LOGGER.debug("传入的reportId=========【{}】", reportId);
        if (infoList != null && infoList.size() > 0)
        {
            for (int j = 0; j < infoList.size(); j++)
            {
                ExPersonInfo info = new ExPersonInfo();
                info = infoList.get(i);
                //				info=exPersonInfoDaoImpl.selectOneByEntity(info);
                info.setWsCd(getWsCdByWsNm(wsNm));
                info.setWsNm(wsNm);
                info.setReportId(reportId);
                info.setUpdateDate(new Date());
                //申请撤保和申请加保
                if (!info.getFlag().equals("2"))
                {
                    info.setStatusCd(info.getFlag());
                }
                else
                {
                    info.setFlag(null);
                }
                //施工队id
                info.setTeamId(getTeamIdByName(info.getWsCd(), info.getTeamName()));
                //工种id
                info.setWorkType(getWorkCdByName(info.getWorkTypeName()));
                ExPersonInfo selectInfo = new ExPersonInfo();
                selectInfo = exPersonInfoDaoImpl.selectOneByEntity(info);
                if (selectInfo != null)
                {
                    if (selectInfo.getStatusCd().equals("1") && info.getFlag().equals("1"))
                    {
                        LOGGER.debug("原来就已加入保险，无需再次申请加保【{}】", info.getName());
                    }
                    else if (selectInfo.getStatusCd().equals("0") && info.getFlag().equals("0"))
                    {
                        LOGGER.debug("原来就无保险，无需再次撤保【{}】", info.getName());
                    }
                    else
                    {
                        i += exPersonInfoDaoImpl.updateByEntity(info);
                    }

                }
                else
                {
                    i += exPersonInfoDaoImpl.insert(info);
                }
                info.setFlag("1");
            }
        }
        return i;
    }

    private String getWorkCdByName(String workTypeName)
    {
        ExWorkTypeQuery work = new ExWorkTypeQuery();
        work.setWorkNm(workTypeName);
        return exWorkTypeDaoImpl.selectOneByEntity(work).getWorkCd();
    }

    private String getTeamIdByName(String wsCd, String teamName)
    {
        MTeamQuery mTeam = new MTeamQuery();
        mTeam.setWsCd(wsCd);
        mTeam.setTeamNm(teamName);
        return mTeamDaoImpl.selectOneByEntity(mTeam).getTeamCd();
    }

    private String getWsCdByWsNm(String wsNm)
    {
        MWorkshop mWorkshop = new MWorkshop();
        mWorkshop.setWsNm(wsNm);
        return mWorkshopDaoImpl.selectOneByEntity(mWorkshop).getWsCd();
    }

    /**
     * 根据userId获取userName
     * @param userId
     * @return
     */
    public String getUserNameByAndroid(String userId)
    {
        GgkzUserInfo entity = new GgkzUserInfo();
        entity.setUserId(userId);
        GgkzUserInfo userIndo = ggkzUserInfoDaoImpl.selectOneByEntity(entity);
        if (userIndo != null)
        {
            return userIndo.getName();
        }
        else
        {
            return userId;
        }

    }

    /**
     * 外线人员处理操作
     * 
     * @param reportId
     * @param status
     * @param comment
     * @return
     * @throws IOException
     */
    public String personManagerDeal(String reportId, String userId, String status, String comment)
    {
        int i = 0;
        ExPersonManager personManager = new ExPersonManager();
        personManager.setReportId(reportId);
        personManager = exPersonManagerServiceImpl.getPersonManager(personManager);
        personManager.setDealComment(comment);
        personManager.setProcessTm(new Date());
        personManager.setProcessUserCd(userId);
        personManager.setProcessUserName(getUserNameById(userId));
        personManager.setStatusCd(status);// 0未处理Y已处理 N有问题
        i += exPersonManagerDaoImpl.updateByEntity(personManager);
        i += personListDeal(personManager);
        if (i != 0)
        {// 成功
         // 添加日志
            OperLogUtil.insertMobileOperLog(reportId, userId, PublicDict.MODEL_PERSON, "外线人员管理", PublicDict.OPER_LOG_EVENT_UPDATE, "更新", "手机端审批外线人员申请", "更新成功", "手机端审批外线人员申请",
                    "ex_person_manager");

            if (status.equals("Yes"))
            {
                // 发消息接口
                pmsMessageTopicProducer.sendTopic(personManager.getReportUserCd(), "【" + getUserNameById(userId) + "】已经处理您的外线人员报告", "【" + getUserNameById(userId) + "】已经处理您从【"
                        + personManager.getWorkCenterId() + "】发起的【" + personManager.getTitle() + "】", personManager.getProcessUserCd());
            }
            else
            {
                // 发消息接口
                pmsMessageTopicProducer.sendTopic(personManager.getReportUserCd(), "【" + getUserNameById(userId) + "】已经处理您的外线人员报告", "您从【" + personManager.getWorkCenterId()
                        + "】发起的【" + personManager.getTitle() + "】有问题，问题为【" + comment + "】", personManager.getProcessUserCd());
            }
            return "0";
        }
        else
        {
            return "1";
        }
    }

    /**
     * 管理员处理外线人员明细列表
     * @param entity
     */
    private int personListDeal(ExPersonManager entity)
    {
        ExPersonInfo info = new ExPersonInfo();
        info.setReportId(entity.getReportId());
        info.setFlag("1");
        int i = 0;
        if (entity.getStatusCd().equals("Yes"))
        {
            i = exPersonInfoDaoImpl.perInfoDeal(info);
        }
        return i;
    }

    /**
     * 根据姓名获取userId
     * 
     * @param name
     * @return
     */
    public String getUserNameById(String userId)
    {
        GgkzUserInfoQuery entity = new GgkzUserInfoQuery();
        entity.setUserId(userId);
        GgkzUserInfo info = ggkzUserInfoDaoImpl.selectOneByEntity(entity);
        if (info != null)
        {
            return info.getName();
        }
        return userId;
    }

    /**
     * 图片处理方法
     * 
     * @param reportId
     * @param userId
     * @param userfile
     * @return
     */

    public int imageDeal(String reportId, String userId, List<File> userfile)
    {
        int j = 0;
        try
        {
            if (userfile != null && userfile.size() > 0)
            {
                for (int i = 0; i < userfile.size(); i++)
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
                    DReportImage image = new DReportImage();
                    image.setReportId(reportId);
                    filename = "/upload/" + filename;
                    image.setFileName(filename);
                    image.setPhotoUserCd(userId);
                    image.setUploadTm(new Date());
                    image.setStatusCd("0");
                    image.setPhotoId(photoId);// 主键
                    j += dReportImageDaoImpl.insert(image);
                }
            }

        }
        catch (Exception e)
        {
        }
        return j;
    }

    /**
     * 外线人员已上传列表
     * 
     * @param username
     * @return List<DReport>
     */
    public List<ExPersonManager> getPersonManager(String userId) throws Exception
    {
        if (StringUtils.isEmpty(userId))
        {
            return null;
        }
        List<ExPersonManager> reportList = new ArrayList<ExPersonManager>();
        reportList.addAll(this.getExPersonManagerListByUserId(userId));
        return reportList;
    }

    /**
     * 外线人员已上传列表点击打开明细
     * 
     * @param username
     * @return List<DReport>
     */
    public List<ExPersonManager> getPersonManagerDetail(String reportId) throws Exception
    {
        if (StringUtils.isEmpty(reportId))
        {
            return null;
        }
        List<ExPersonManager> reportList = new ArrayList<ExPersonManager>();
        //		reportList.addAll(this.getExPersonManagerListByUserId(userId));
        return reportList;
    }

    /**
     * 根据当前用户id和状态码外线人员列表数据
     * 
     * @param userId
     * @param statusCd
     * @return
     */
    public List<ExPersonManager> getExPersonManagerListByUserId(String userId)
    {
        List<ExPersonManager> planList = new ArrayList<ExPersonManager>();
        String post = WsUtil.getPostByUserId(userId);// 当前用户的职务代码
        if ("38".equals(post))
        {// 职务为区域部长
            planList = verifiedSiteHead(userId);
        }
        else if ("1".equals(post) || "3".equals(post) || "11".equals(post) || "2".equals(post) || "6".equals(post))
        {// 职务为1总经理或3部长11信息主管，2部门领导,6董事长
            planList = verifiedAll(statusCd);// 查询所有
        }
        else
        {// 项目经理和其他职务只查询自己相关的数据
            planList = verifiedSiteLevel(userId);
        }
        return planList;
    }

    /**
     * 项目经理级别的查询跟自己相关的数据
     * 
     * @param userId
     * @param statusCd
     * @return
     */
    public List<ExPersonManager> verifiedSiteLevel(String userId)
    {
        List<ExPersonManager> levelList = new ArrayList<ExPersonManager>();// 中间list
        levelList = new ArrayList<ExPersonManager>();
        ExPersonManagerQuery entity = new ExPersonManagerQuery();
        entity.setReportUserCd(userId);// 报告者为当前用户
        levelList.addAll(exPersonManagerDaoImpl.selectByEntity(entity));
        return levelList;
    }

    /**
     * 区域部长的查询跟自己相关的数据和下属项目经理的数据
     * 
     * @param userId
     * @param statusCd
     * @return
     */
    public List<ExPersonManager> verifiedSiteHead(String userId)
    {
        List<ExPersonManager> siteHeadList = new ArrayList<ExPersonManager>();
        ExPersonManager entity = new ExPersonManager();
        entity.setStatusCd(statusCd);
        siteHeadList.addAll(this.verifiedSiteLevel(userId));// 查询跟自己相关的数据
        List<String> strList = WsUtil.getSiteUserIdByEareaheadUserId(userId);// 查询区域部长所有下属项目经理的id
        for (String siteId : strList)
        {
            siteHeadList.addAll(this.verifiedSiteLevel(siteId));// 查询区域部长所有下属项目经理的待审批
        }
        return siteHeadList;
    }

    /**
     * 总经理与部长的查询所有的数据
     * 
     * @param userId
     * @param statusCd
     * @return
     */
    public List<ExPersonManager> verifiedAll(String statusCd)
    {
        List<ExPersonManager> allList = new ArrayList<ExPersonManager>();
        ExPersonManager entity = new ExPersonManager();
        entity.setStatusCd(statusCd);
        allList = exPersonManagerDaoImpl.selectByEntity(entity);
        return allList;
    }

    /**获取外线报告下的信息
     * @param reportId
     * @return
     */
    public List<ExPersonInfo> getPersonInfoDetail(String reportId)
    {
        ExPersonInfoQuery infoQuery = new ExPersonInfoQuery();
        infoQuery.setReportId(reportId);
        return exPersonInfoDaoImpl.selectByEntity(infoQuery);
    }

    /**获取外线报告下人员信息，不在工地的
     * @param reportId
     * @return
     */
    public List<ExPersonInfo> getPersonInfoListByName()
    {
        List<ExPersonInfo> infoList = new ArrayList<ExPersonInfo>();
        infoList = exPersonInfoDaoImpl.getPersonInfoListByName();
        List<ExPersonInfo> returninfoList = new ArrayList<ExPersonInfo>();
        if (infoList != null && infoList.size() > 0)
        {
            for (int i = 0; i < infoList.size(); i++)
            {
                ExPersonInfo info = new ExPersonInfo();
                info = infoList.get(i);
                //取姓名首字母放入wscd
                info.setWsCd(ChineseFirstLetterUtil.getAllFirstLetter(info.getName()));
                returninfoList.add(info);
            }
        }
        return returninfoList;
    }

    /**
     * 新增获取保险列表
     * @param workCenterId
     * @return
     */
    public List<ExPersonInfo> getPersonInfoListToMobile(String workCenterId)
    {
        ExPersonInfoQuery infoQuery = new ExPersonInfoQuery();
        infoQuery.setWsNm(workCenterId);
        return exPersonInfoDaoImpl.getPersonInfoListToMobile(infoQuery);
    }

    public ExPersonManagerServiceImpl getExPersonManagerServiceImpl()
    {
        return exPersonManagerServiceImpl;
    }

    public void setExPersonManagerServiceImpl(ExPersonManagerServiceImpl exPersonManagerServiceImpl)
    {
        this.exPersonManagerServiceImpl = exPersonManagerServiceImpl;
    }

    public ExPersonManagerDaoImpl getExPersonManagerDaoImpl()
    {
        return exPersonManagerDaoImpl;
    }

    public void setExPersonManagerDaoImpl(ExPersonManagerDaoImpl exPersonManagerDaoImpl)
    {
        this.exPersonManagerDaoImpl = exPersonManagerDaoImpl;
    }

    public DReportImageDaoImpl getdReportImageDaoImpl()
    {
        return dReportImageDaoImpl;
    }

    public void setdReportImageDaoImpl(DReportImageDaoImpl dReportImageDaoImpl)
    {
        this.dReportImageDaoImpl = dReportImageDaoImpl;
    }

    public String getStatusCd()
    {
        return statusCd;
    }

    public void setStatusCd(String statusCd)
    {
        this.statusCd = statusCd;
    }
}
