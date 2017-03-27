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

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.base.mybatis.BaseStruts2Action;
import com.is.mobile.service.MobileSendMetralServiceImpl;
import com.is.pretrst.entity.DDeliveryPlan;
import com.is.pretrst.entity.DInvoice;
import com.is.pretrst.entity.DRecordReply;
import com.is.utils.json.CollectionJsonValueProcessor;
import com.is.utils.json.DateJsonValueProcessor;

@Namespace("/mobile")
public class MobileSendMetralAction extends BaseStruts2Action
{
    private static final long           serialVersionUID = 1L;

    private static final Logger         LOGGER           = LoggerFactory.getLogger(MobileSendMetralAction.class);

    private String                      userId;                                                                  //用户id

    private String                      sessionId;                                                               //sessionId

    private String                      planId;                                                                   //计划表Id

    private String                      invoiceId;                                                               //出门单id

    private String                      status;                                                                  //审批状态

    private String                      memo;                                                                    //审批意见
    
    private String                      title;//回复标题
    private List<File>                  userfile;//多附件上传参数

    @Autowired
    private MobileSendMetralServiceImpl mobileSendMetralServiceImpl;

    /**
     * 发料待审批
     */
    public void getSendMetralApproval()
    {
        String flag = "0";
        List<DDeliveryPlan> rstList = new ArrayList<DDeliveryPlan>();
        try
        {
            rstList = mobileSendMetralServiceImpl.getSendMetralApproval(userId);
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
     * 发料已审批 
     * @return
     */
    public void getSendMetralApprovaled()
    {
        String flag = "0";
        List<DDeliveryPlan> rstList = new ArrayList<DDeliveryPlan>();
        try
        {
            rstList = mobileSendMetralServiceImpl.getSendMetralApprovaled(userId);
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
     * 发料已下发 
     * @return
     */
    public void getSendMetralIssueed()
    {
        String flag = "0";
        List<DDeliveryPlan> rstList = new ArrayList<DDeliveryPlan>();
        try
        {
            rstList = mobileSendMetralServiceImpl.getSendMetralIssueed(userId);
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
     * 待审批出门 单 
     * @return
     */
    public void getSendMetralOutApproval()
    {
        String flag = "0";
        List<DInvoice> rstList = new ArrayList<DInvoice>();
        try
        {
            rstList = mobileSendMetralServiceImpl.getSendMetralOutApproval(userId);
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
     * 已审批出门 单 
     * @return
     */
    public void getSendMetralOutApprovaled()
    {
        String flag = "0";
        List<DInvoice> rstList = new ArrayList<DInvoice>();
        try
        {
            rstList = mobileSendMetralServiceImpl.getSendMetralOutApprovaled(userId);
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
     * 送货中出门 单 
     * @return
     */
    public void getSendMetralTrans()
    {
        String flag = "0";
        List<DInvoice> rstList = new ArrayList<DInvoice>();
        try
        {
            rstList = mobileSendMetralServiceImpl.getSendMetralTrans(userId);
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
     * 已送达出门 单 
     * @return
     */
    public void getSendMetralArrived()
    {
        String flag = "0";
        List<DInvoice> rstList = new ArrayList<DInvoice>();
        try
        {
            rstList = mobileSendMetralServiceImpl.getSendMetralArrived(userId);
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
     * 发货员申请发车
     */
    public void applyStartOff()
    {
        String flag = "0";
        try
        {
            flag = mobileSendMetralServiceImpl.applyStartOff(userId, invoiceId, userfile);
        }
        catch (IOException e)
        {
            if (LOGGER.isErrorEnabled())
            {
                LOGGER.error("getSendMetralApproval异常：{}", e);
            }
        }
        this.jsonUtil(null, flag);
    }

    /**
     * 项目经理审批发车
     * 成功 flag:0; 失败 flag:1
     */
    public void siteDisposeSender()
    {
        String flag = "0";
        try
        {
            flag = mobileSendMetralServiceImpl.siteDisposeSender(userId, invoiceId, status, memo);
        }
        catch (Exception e)
        {
            if (LOGGER.isErrorEnabled())
            {
                LOGGER.error("getSendMetralApproval异常：{}", e);
            }
        }
        this.jsonUtil(null, flag);
    }

    /**
     * 发货员确认发车
     * 成功 flag:0; 失败 flag:1
     */
    public void senderAffirm()
    {
        String flag = "0";
        try
        {
            flag = mobileSendMetralServiceImpl.senderAffirm(invoiceId);
        }
        catch (Exception e)
        {
            if (LOGGER.isErrorEnabled())
            {
                LOGGER.error("getSendMetralApproval异常：{}", e);
            }
        }
        this.jsonUtil(null, flag);
    }

    /**
     * 项目经理确认收货
     * 成功 flag:0; 失败 flag:1
     */
    public void siteAffirmReceive()
    {
        String flag = "0";
        try
        {
            flag = mobileSendMetralServiceImpl.siteAffirmReceive(invoiceId, status, memo);
        }
        catch (Exception e)
        {
            if (LOGGER.isErrorEnabled())
            {
                LOGGER.error("getSendMetralApproval异常：{}", e);
            }
        }
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
        //json集合格式化
        CollectionJsonValueProcessor collectionJsonValueProcessor = new CollectionJsonValueProcessor();
        //注册值转换器
        jsonConfig.registerJsonValueProcessor(Date.class, dateJsonValueProcessor);
        //注册值转换器
        //jsonConfig.registerJsonValueProcessor(List.class, collectionJsonValueProcessor);
        //排除字段集合
        String[] excludes = {"callbacks", "pageNumber", "pageSize", "deleteCd", "dbType", "distinct","DInvoiceItems","dDeliveryItemList"};
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
     * 根据planId查询plan对象
     */
    public void selectPlanById()
    {
        String flag = "0";
        List<DDeliveryPlan> rstList = new ArrayList<DDeliveryPlan>();
        try
        {
             rstList.add(mobileSendMetralServiceImpl.selectPlanById(planId));
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
     * 根据invoiceId查询出门单对象
     */
    public void selectInvoiceById()
    {
        String flag = "0";
        List<DInvoice> rstList = new ArrayList<DInvoice>();
        try
        {
             rstList.add(mobileSendMetralServiceImpl.selectInvoiceById(invoiceId));
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
     * 计划表更新备注
     */
    public void updateMemo(){
        String flag = "0";
        try
        {
            flag = mobileSendMetralServiceImpl.updateMemo( planId, memo);
        }
        catch (Exception e1)
        {
            flag = "1";
            if (LOGGER.isErrorEnabled())
            {
                LOGGER.error("getSendMetralApproval异常：{}", e1);
            }
        }
        this.jsonUtil(null, flag);
    }
    
    /**
     * 审批项目经理或审批部长处理待审批发料计划 （更新审批意见也用这个，status不传）
     */
    public void disposePlan(){
        String flag = "0";
        try
        {
            flag = mobileSendMetralServiceImpl.disposePlan(userId, planId, status, memo);
        }catch (Exception e1)
        {
            flag = "1";
            if (LOGGER.isErrorEnabled())
            {
                LOGGER.error("getSendMetralApproval异常：{}", e1);
            }
        }
        this.jsonUtil(null, flag);
    }
    /**
     * 查看计划表回复信息 (因为这个需要过滤的字段比较多，所以单独用了一个map转json的方法)
     * @return
     */
    public String recordView(){
         String flag = "0";
         List<DRecordReply> replyList = new ArrayList<DRecordReply>();
         try
         {
             replyList = mobileSendMetralServiceImpl.recordView(planId);;
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
        secrityMap.put("data", replyList);
        secrityMap.put("sessionId", sessionId);
        JSONObject jsonobj = new JSONObject();

        //json配置
        JsonConfig jsonConfig = new JsonConfig();
        //json日期格式化
        DateJsonValueProcessor dateJsonValueProcessor = new DateJsonValueProcessor();
        //json集合格式化
        CollectionJsonValueProcessor collectionJsonValueProcessor = new CollectionJsonValueProcessor();
        //注册值转换器
        jsonConfig.registerJsonValueProcessor(Date.class, dateJsonValueProcessor);
        //注册值转换器
       // jsonConfig.registerJsonValueProcessor(List.class, collectionJsonValueProcessor);
        //排除字段集合
        String[] excludes = {"callbacks", "pageNumber", "pageSize", "deleteCd", "dbType", "distinct","DInvoiceItems","createTm","extraFlg","parentReplyId","recordId","recordType","replyId","replyUserCd","rootReplyId"};
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
        return null;
    }
    
    /**
     * 更新回复，没有回复过则添加
     */
    public void updateRecordReply(){
        
        String flag = "0";
        try
        {
            flag = mobileSendMetralServiceImpl.updateRecordReply(userId, planId, title, memo);
        }catch (Exception e1)
        {
            flag = "1";
            if (LOGGER.isErrorEnabled())
            {
                LOGGER.error("getSendMetralApproval异常：{}", e1);
            }
        }
        this.jsonUtil(null, flag);
    }
    //getter setter
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

    public String getInvoiceId()
    {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId)
    {
        this.invoiceId = invoiceId.replaceAll("\r\n", "");
    }


    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getMemo()
    {
        return memo;
    }

    public void setMemo(String memo)
    {
        this.memo = memo;
    }

    public String getPlanId()
    {
        return planId;
    }

    public void setPlanId(String planId)
    {
        this.planId = planId;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public void setUserfile(List<File> userfile)
    {
        this.userfile = userfile;
    }

    public List<File> getUserfile()
    {
        return userfile;
    }

}
