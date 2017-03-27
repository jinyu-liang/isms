package com.is.mobile.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.base.mybatis.BaseStruts2Action;
import com.is.mobile.service.MobileExProgressServiceImpl;
import com.is.pretrst.entity.DExProgress;
import com.is.pretrst.entity.DExProgressExt;
import com.is.pretrst.entity.DExProject;
import com.is.pretrst.entity.DExProjectExt;
import com.is.utils.authUtil.JdglTreeUtil;
import com.is.utils.json.DateJsonValueProcessor;

@Namespace("/mobile")
public class MobileExProgressAction extends BaseStruts2Action
{
    private static final long           serialVersionUID = 1L;

    private static final Logger         LOGGER           = LoggerFactory.getLogger(MobileSendMetralAction.class);

    private String                      userId;

    private String                      sessionId;

    private String                      projectId;                                                                //项目id

    private String                      reportId;                                                                 //报告id

    private String                      lastReportDt;                                                             //项目的最新报告日期

    private String                      progressStatus;                                                           //工程进度

    private String                      fbWorkshop;                                                               //现场反馈情况

    private String                      fbEquipment;                                                              //设备情况

    private String                      currentCost;                                                              //实际费用

    private String                      teamLeader;                                                               //施工队长

    private String                      welder;                                                                   //主要焊工

    private String                      riveter;                                                                  //主要铆工

    private String                      workerCount;                                                              //现场总人数

    private String                      viceManager;                                                              //分管副部长

    private String                      wsLeader;                                                                 //带队人员

    private List<File>                  userfile;                                                                 //图片集合

    private String                      lastReportId;                                                             //最后报告id

    private String                      wsCd;                                                                     //项目id

    @Autowired
    private MobileExProgressServiceImpl mobileExProgressServiceImpl;

    /**
     * 查询所管理的项目列表
     */
    /* public void getProgressList()
     {
         String flag = "0";
         List<DExProject> rstList = new ArrayList<DExProject>();
         try
         {
             rstList = mobileExProgressServiceImpl.getProgressList(userId);
         }
         catch (Exception e1)
         {
             flag = "1";
             if (LOGGER.isErrorEnabled())
             {
                 LOGGER.error("getSendMetralApproval异常：{}", e1);
             }
         }
         this.jsonUtil( rstList,flag);
     }*/

    public void getProgressList()
    {
        String flag = "0";
        List<DExProjectExt> rstList = new ArrayList<DExProjectExt>();
        try
        {
            rstList = mobileExProgressServiceImpl.getProjectExtList(userId);
        }
        catch (Exception e1)
        {
            flag = "1";
            if (LOGGER.isErrorEnabled())
            {
                LOGGER.error("getProgressList异常：{}", e1);
            }
        }
        this.jsonUtil(rstList, flag);
    }

    /**
     * 查询进度详情
     */
    public void getProgressDetail()
    {
        String flag = "0";
        DExProgressExt proExt = new DExProgressExt();
        try
        {
            proExt = mobileExProgressServiceImpl.getProgress(projectId,lastReportDt, lastReportId, wsCd, userId);
        }
        catch (Exception e)
        {
            flag = "1";
            if (LOGGER.isErrorEnabled())
            {
                LOGGER.error("getProgressDetail异常：{}", e);
            }
        }
        this.jsonUtilObj(proExt, flag);
    }

    /**
     * 项目经理上传进度信息
     */
    public void insertProgress()
    {
        
        String flag = "0";
        List<DExProject> rstList = new ArrayList<DExProject>();
        if(StringUtils.isEmpty(projectId)){//如果项目id为空，则直接返回失败
            this.jsonUtil(rstList, "1");
        }else{
            try
            {
                DExProgress entity = new DExProgress();
                entity.setReportId(reportId);
                entity.setProgressStatus(progressStatus);
                entity.setFbWorkshop(fbWorkshop);//现场反馈情况
                entity.setFbEquipment(fbEquipment);//设备情况
                if (StringUtils.isNotEmpty(currentCost))
                {
                    entity.setCurrentCost(Float.parseFloat(currentCost.trim()));//实际费用
                }
                entity.setTeamLeader(teamLeader);//施工队长
                entity.setWelder(welder);//主要焊工
                entity.setRiveter(riveter);//主要铆工
                if (StringUtils.isNotEmpty(workerCount))
                {
                    entity.setWorkerCount(Integer.parseInt(workerCount.trim()));//现场总人数
                }
                entity.setViceManager(viceManager);//分管副部长
                entity.setWsLeader(wsLeader);//带队人员
                entity.setProjectId(projectId);
                flag = mobileExProgressServiceImpl.insert(projectId, lastReportDt, entity, userId, userfile);//保存progress信息
                JdglTreeUtil.refresh();
            }
            catch (Exception e1)
            {
                flag = "1";
                if (LOGGER.isErrorEnabled())
                {
                    LOGGER.error("insertProgress异常：{}", e1);
                }
            }
            this.jsonUtil(rstList, flag);
        }
    }

    /**
     * 本类中共用的封装json字符串并返回给前台
     * @param rstList
     * @param flag
     */
    public void jsonUtil(List<?> rstList, String flag)
    {
        Map<String, Object> secrityMap = new HashMap<String, Object>();
        secrityMap.put("flag", flag);
        secrityMap.put("data", rstList);
        secrityMap.put("sessionId", sessionId);
        JSONObject jsonobj = new JSONObject();

        //json配置
        JsonConfig jsonConfig = new JsonConfig();
        //json日期格式化
        DateJsonValueProcessor dateJsonValueProcessor = new DateJsonValueProcessor();
        //注册值转换器
        jsonConfig.registerJsonValueProcessor(Date.class, dateJsonValueProcessor);
        //排除字段集合
        String[] excludes = {"callbacks", "pageNumber", "pageSize", "deleteCd", "dbType", "distinct", "DExItems", "progressReport"};
        //注册排除字段
        jsonConfig.setExcludes(excludes);

        //加载集合
        jsonobj.accumulateAll(secrityMap, jsonConfig);

        PrintWriter out = null;
        try
        {
            out = ServletActionContext.getResponse().getWriter();
            out.write(jsonobj.toString());
            out.flush();
        }
        catch (IOException e)
        {
            if (LOGGER.isErrorEnabled())
            {
                LOGGER.error("login异常：{}", e);
            }
        }
        finally
        {
            if (out != null)
            {
                out.close();
            }
        }
    }
    /**
     * 本类中共用的封装json字符串并返回给前台
     * @param rstList
     * @param flag
     */
    public void jsonUtilObj(Object obj, String flag)
    {
        Map<String, Object> secrityMap = new HashMap<String, Object>();
        secrityMap.put("flag", flag);
        secrityMap.put("data", obj);
        secrityMap.put("sessionId", sessionId);
        JSONObject jsonobj = new JSONObject();

        //json配置
        JsonConfig jsonConfig = new JsonConfig();
        //json日期格式化
        DateJsonValueProcessor dateJsonValueProcessor = new DateJsonValueProcessor();
        //注册值转换器
        jsonConfig.registerJsonValueProcessor(Date.class, dateJsonValueProcessor);
        //排除字段集合
        String[] excludes = {"callbacks", "pageNumber", "pageSize", "deleteCd", "dbType", "distinct"};
        //注册排除字段
        jsonConfig.setExcludes(excludes);

        //加载集合
        jsonobj.accumulateAll(secrityMap, jsonConfig);

        PrintWriter out = null;
        try
        {
            out = ServletActionContext.getResponse().getWriter();
            out.write(jsonobj.toString());
            out.flush();
        }
        catch (IOException e)
        {
            if (LOGGER.isErrorEnabled())
            {
                LOGGER.error("login异常：{}", e);
            }
        }
        finally
        {
            if (out != null)
            {
                out.close();
            }
        }
    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId;
    }

    public String getSessionId()
    {
        return sessionId;
    }

    public String getProjectId()
    {
        return projectId;
    }

    public void setProjectId(String projectId)
    {
        this.projectId = projectId;
    }

    public String getProgressStatus()
    {
        return progressStatus;
    }

    public void setProgressStatus(String progressStatus)
    {
        this.progressStatus = progressStatus;
    }

    public String getFbWorkshop()
    {
        return fbWorkshop;
    }

    public void setFbWorkshop(String fbWorkshop)
    {
        this.fbWorkshop = fbWorkshop;
    }

    public String getFbEquipment()
    {
        return fbEquipment;
    }

    public void setFbEquipment(String fbEquipment)
    {
        this.fbEquipment = fbEquipment;
    }

    public String getTeamLeader()
    {
        return teamLeader;
    }

    public void setTeamLeader(String teamLeader)
    {
        this.teamLeader = teamLeader;
    }

    public String getWelder()
    {
        return welder;
    }

    public void setWelder(String welder)
    {
        this.welder = welder;
    }

    public String getRiveter()
    {
        return riveter;
    }

    public void setRiveter(String riveter)
    {
        this.riveter = riveter;
    }

    public String getCurrentCost()
    {
        return currentCost;
    }

    public void setCurrentCost(String currentCost)
    {
        this.currentCost = currentCost;
    }

    public String getWorkerCount()
    {
        return workerCount;
    }

    public void setWorkerCount(String workerCount)
    {
        this.workerCount = workerCount;
    }

    public String getViceManager()
    {
        return viceManager;
    }

    public void setViceManager(String viceManager)
    {
        this.viceManager = viceManager;
    }

    public String getWsLeader()
    {
        return wsLeader;
    }

    public void setWsLeader(String wsLeader)
    {
        this.wsLeader = wsLeader;
    }

    public List<File> getUserfile()
    {
        return userfile;
    }

    public void setUserfile(List<File> userfile)
    {
        this.userfile = userfile;
    }

    public String getLastReportDt()
    {
        return lastReportDt;
    }

    public void setLastReportDt(String lastReportDt)
    {
        this.lastReportDt = lastReportDt;
    }

    public String getReportId()
    {
        return reportId;
    }

    public void setReportId(String reportId)
    {
        this.reportId = reportId;
    }

    public void setSessionId(String sessionId)
    {
        this.sessionId = sessionId;
    }

    public void setMobileExProgressServiceImpl(MobileExProgressServiceImpl mobileExProgressServiceImpl)
    {
        this.mobileExProgressServiceImpl = mobileExProgressServiceImpl;
    }

    /**
     * @return Returns the lastReportId.
     */
    public String getLastReportId()
    {
        return lastReportId;
    }

    public void setLastReportId(String lastReportId)
    {
        this.lastReportId = lastReportId;
    }

    public String getWsCd()
    {
        return wsCd;
    }

    public void setWsCd(String wsCd)
    {
        this.wsCd = wsCd;
    }

    public MobileExProgressServiceImpl getMobileExProgressServiceImpl()
    {
        return mobileExProgressServiceImpl;
    }

}
