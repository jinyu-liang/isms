package com.is.mobile.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.base.mybatis.BaseStruts2Action;
import com.is.mobile.service.MobilePersonManageServiceImpl;
import com.is.mobile.service.MobilePurchServiceImpl;
import com.is.pretrst.entity.ExPersonInfo;
import com.is.pretrst.entity.ExPersonManager;
import com.is.utils.StringUtils;
import com.is.utils.json.DateJsonValueProcessor;

@Namespace("/mobile")
public class MobilePersonManageAction extends BaseStruts2Action
{
    private static final long             serialVersionUID = 1L;

    private static final Logger           LOGGER           = LoggerFactory.getLogger(MobileSendMetralAction.class);

    private String                        userId;

    private String                        sessionId;

    private String                        reportId;                                                                //采购Id

    private String                        workCenterId;                                                            //工地中心代码

    private String                        title;                                                                   //标题

    private String                        personName;                                                              //标题

    private String                        orderNo;                                                                 //班组编号

    private String                        status;                                                                  //审批状态

    private String                        comment;                                                                 //处理意见/备注

    private String                        personInfoList;                                                          //外线人员列表

    @Autowired
    private MobilePersonManageServiceImpl mobilePersonManageServiceImpl;

    @Autowired
    private MobilePurchServiceImpl        mobilePurchServiceImpl;

    private List<File>                    userfile;

    /**
     * 外线人员已上传列表
     */
    public void getPersonManageList()
    {
        String flag = "0";
        List<ExPersonManager> rstList = new ArrayList<ExPersonManager>();
        try
        {
            rstList = mobilePersonManageServiceImpl.getPersonManager(userId);
        }
        catch (Exception e1)
        {
            flag = "1";
            if (LOGGER.isErrorEnabled())
            {
                LOGGER.error("getSendMetralApproval异常：{}", e1);
            }
        }
        this.jsonUtil(rstList, flag);
    }

    /**
     * 外线人员已上传列表打开详情
     */
    public void getPersonInfoDetail()
    {
        String flag = "0";
        List<ExPersonInfo> rstList = new ArrayList<ExPersonInfo>();
        try
        {
            rstList = mobilePersonManageServiceImpl.getPersonInfoDetail(reportId);
        }
        catch (Exception e1)
        {
            flag = "1";
            if (LOGGER.isErrorEnabled())
            {
                LOGGER.error("getSendMetralApproval异常：{}", e1);
            }
        }
        this.jsonUtil(rstList, flag);
    }

    /**
     * 手机端外线人员保险信息获取时根据姓名或者首字母获取姓名
     */
    public void getPersonInfoListByName()
    {
        String flag = "0";
        List<ExPersonInfo> rstList = new ArrayList<ExPersonInfo>();
        try
        {
            rstList = mobilePersonManageServiceImpl.getPersonInfoListByName();
        }
        catch (Exception e1)
        {
            flag = "1";
            if (LOGGER.isErrorEnabled())
            {
                LOGGER.error("getSendMetralApproval异常：{}", e1);
            }
        }
        this.jsonUtil(rstList, flag);
    }

    /**
     * 外线人员已上传列表打开详情
     */
    public void getWscdPersonInfoList()
    {
        String flag = "0";

        List<ExPersonInfo> rstList = new ArrayList<ExPersonInfo>();
        List<String> teamList = new ArrayList<String>();
        List<String> workTypeList = new ArrayList<String>();
        List<ExPersonInfo> nList = new ArrayList<ExPersonInfo>();
        try
        {
            rstList = mobilePersonManageServiceImpl.getPersonInfoListToMobile(workCenterId);
            teamList = mobilePersonManageServiceImpl.getMteamList(workCenterId);
            workTypeList = mobilePersonManageServiceImpl.getWorkTypeList();
            nList = mobilePersonManageServiceImpl.getPersonInfoListByName();

        }
        catch (Exception e1)
        {
            flag = "1";
            if (LOGGER.isErrorEnabled())
            {
                LOGGER.error("getSendMetralApproval异常：{}", e1);
            }
        }
        this.jsonUtil(rstList, teamList, workTypeList, nList, flag);
    }

    /**
     * 本类中共用的封装json字符串并返回给前台
     * @param rstList
     * @param flag
     */
    public void jsonUtil(List<?> rstList, List<?> teamList, List<?> wList, List<?> nList, String flag)
    {
        Map<String, Object> secrityMap = new HashMap<String, Object>();
        secrityMap.put("flag", flag);
        secrityMap.put("data", rstList);
        secrityMap.put("tdata", teamList);
        secrityMap.put("wdata", wList);
        secrityMap.put("ndata", nList);
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

    /**
     * 获取工地中心list和部长总经理数据
     * 
     */
    public void getWorkAndVerfieder()
    {

        String flag = "0";
        List<String> workList = new ArrayList<String>();
        List<String> userList = new ArrayList<String>();
        try
        {
            workList = mobilePurchServiceImpl.getMworkShop(userId);
            userList = mobilePurchServiceImpl.getGgkzUserInfoList(userId);
        }
        catch (Exception e1)
        {
            flag = "1";
            if (LOGGER.isErrorEnabled())
            {
                LOGGER.error("getSendMetralApproval异常：{}", e1);
            }
        }
        Map<String, Object> secrityMap = new HashMap<String, Object>();
        secrityMap.put("flag", flag);
        secrityMap.put("workdata", workList);
        secrityMap.put("userdata", userList);
        secrityMap.put("sessionId", sessionId);
        JSONObject jsonobj = new JSONObject();

        // json配置
        JsonConfig jsonConfig = new JsonConfig();
        // json日期格式化
        DateJsonValueProcessor dateJsonValueProcessor = new DateJsonValueProcessor();
        // 注册值转换器
        jsonConfig.registerJsonValueProcessor(Date.class, dateJsonValueProcessor);
        // 排除字段集合
        String[] excludes = {"callbacks", "pageNumber", "pageSize", "deleteCd", "dbType", "distinct"};
        // 注册排除字段
        jsonConfig.setExcludes(excludes);

        // 加载集合
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
     * 外线人员安卓客户端上传接口
     * @param title   标题	
     * @param workCenterId 工地中心
     * @param teamCd   班组代码
     * @param memo  备注
     * @param userId 报告人
     * @param userfile  图片流
     * @return String 0:成功 ;1：失败
     * @throws IOException
     */
    public void PersonManageUpload() throws IOException
    {//LOGGER.debug("执行上传           personInfoList====[{}]",personInfoList);
        String flag = "0";

        if (StringUtils.isNotEmpty(personInfoList))
        {
            personInfoList = personInfoList.substring(personInfoList.indexOf("["), personInfoList.indexOf("]") + 1);
            JSONArray jsonArray = JSONArray.fromObject(personInfoList);

            List<ExPersonInfo> infoList = JSONArray.toList(jsonArray, ExPersonInfo.class);
            LOGGER.debug("执行上传           ====");
            flag = mobilePersonManageServiceImpl.PersonManageUpload(reportId, title, workCenterId, orderNo, comment, userId, userfile, infoList);
        }
        else
        {
            flag = mobilePersonManageServiceImpl.PersonManageUpload(reportId, title, workCenterId, orderNo, comment, userId, userfile, null);
        }

        this.jsonUtil(null, flag);
    }

    /**
     *	外线人员处理人处理操作
     * @param reportId 业务编号
     * @param userId   处理人Id
     * @param status   处理结果
     * @param comment   处理意见
     * @return
     * @throws IOException
     */
    public void personManagerDeal() throws IOException
    {
        String flag = "0";
        flag = mobilePersonManageServiceImpl.personManagerDeal(reportId, userId, status, comment);
        this.jsonUtil(null, flag);
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

    public String getReportId()
    {
        return reportId;
    }

    public void setReportId(String reportId)
    {
        this.reportId = reportId.replaceAll("\r\n", "");
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title.replaceAll("\r\n", "");
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status.replaceAll("\r\n", "");
    }

    public String getComment()
    {
        return comment;
    }

    public void setComment(String comment)
    {
        this.comment = comment.replaceAll("\r\n", "");
    }

    /**
     * 
     * @return
     */
    public void ApprovalDispose()
    {

    }

    public String getUserId()
    {
        return userId;
    }

    public void setUserId(String userId)
    {
        this.userId = userId.replaceAll("\r\n", "");
    }

    public String getSessionId()
    {
        return sessionId;
    }

    public void setSessionId(String sessionId)
    {
        this.sessionId = sessionId;
    }

    public String getWorkCenterId()
    {
        return workCenterId;
    }

    public void setWorkCenterId(String workCenterId)
    {
        this.workCenterId = workCenterId.replaceAll("\r\n", "");
    }

    public String getOrderNo()
    {
        return orderNo;
    }

    public void setOrderNo(String orderNo)
    {
        this.orderNo = orderNo.replaceAll("\r\n", "");
    }

    public MobilePersonManageServiceImpl getMobilePersonManageServiceImpl()
    {
        return mobilePersonManageServiceImpl;
    }

    public void setMobilePersonManageServiceImpl(MobilePersonManageServiceImpl mobilePersonManageServiceImpl)
    {
        this.mobilePersonManageServiceImpl = mobilePersonManageServiceImpl;
    }

    public void setUserfile(List<File> userfile)
    {
        this.userfile = userfile;
    }

    public List<File> getUserfile()
    {
        return userfile;
    }

    public MobilePurchServiceImpl getMobilePurchServiceImpl()
    {
        return mobilePurchServiceImpl;
    }

    public void setMobilePurchServiceImpl(MobilePurchServiceImpl mobilePurchServiceImpl)
    {
        this.mobilePurchServiceImpl = mobilePurchServiceImpl;
    }

    public String getPersonInfoList()
    {
        return personInfoList;
    }

    public void setPersonInfoList(String personInfoList)
    {
        this.personInfoList = personInfoList;
    }

    public String getPersonName()
    {
        return personName;
    }

    public void setPersonName(String personName)
    {
        this.personName = personName;
    }

}
