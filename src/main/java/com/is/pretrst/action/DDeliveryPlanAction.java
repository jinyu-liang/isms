package com.is.pretrst.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.hibernate.tool.hbm2x.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.base.mybatis.BaseStruts2Action;
import com.base.mybatis.Page;
import com.is.mq.producer.PmsMessageTopicProducer;
import com.is.pretrst.entity.DDeliveryItem;
import com.is.pretrst.entity.DDeliveryPlan;
import com.is.pretrst.entity.DRecordReply;
import com.is.pretrst.entity.MWorkshop;
import com.is.pretrst.entity.query.DDeliveryPlanQuery;
import com.is.pretrst.entity.query.DRecordReplyQuery;
import com.is.pretrst.service.DDeliveryPlanServiceImpl;
import com.is.pretrst.service.DRecordReplyServiceImpl;
import com.is.pretrst.service.MWorkshopServiceImpl;
import com.is.sys.entity.SysAttach;
import com.is.sys.service.SysAttachServiceImpl;

/**
 *
 * @ClassName: DDeliveryPlanAction
 * @Description: DDeliveryPlan表对应的Action类
 * @author 
 * @date 2013-09-10 10:25:57 *
 */
@Namespace("/rst")
@SuppressWarnings("unchecked")
public class DDeliveryPlanAction extends BaseStruts2Action
{
    private static final long        serialVersionUID = 1L;

    private static final Logger      LOGGER           = LoggerFactory.getLogger(DDeliveryPlanAction.class);

    private DDeliveryPlan            entity;

    private DDeliveryPlanQuery       queryEntity;

    private DRecordReply             record;                                                               //回复信息对象

    private DRecordReplyQuery        queryRecord;                                                          //回复查询对象

    private Page                     recordPage;                                                           //回复信息page

    private String                   fileIds;                                                              //附件id

    private String                   userMappingIds;                                                       //接收其他领导的ids

    // private DInvoiceQuery                      invoiceQuery;//出门单的query对象

    private String                   isReply;                                                               //存放本条计划表中能够回复的权限

    /*private List<DDeliveryItem> ddeliveryItemList;*/

    String                           jsondata;

    @Autowired
    private DDeliveryPlanServiceImpl deliveryPlanServiceImpl;

    @Autowired
    private DRecordReplyServiceImpl  drecordReplyServiceImpl;

    @Autowired
    private SysAttachServiceImpl     sysAttachServiceImpl;

    @Autowired
    private PmsMessageTopicProducer  pmsMessageTopicProducer;

    @Autowired
    private MWorkshopServiceImpl     mworkshopServiceImpl;

    //    @Autowired
    //    public DInvoiceAction  invoiceAction;

    HttpSession                      session          = ServletActionContext.getRequest().getSession();

    public DDeliveryPlanAction()
    {
        super();
        if (entity == null)
        {
            entity = new DDeliveryPlan();
        }
        if (queryEntity == null)
        {
            queryEntity = new DDeliveryPlanQuery();
        }
        if (record == null)
        {
            record = new DRecordReply();
        }
        if (queryRecord == null)
        {
            queryRecord = new DRecordReplyQuery();
        }
    }

    public DDeliveryPlan getEntity()
    {
        return entity;
    }

    public void setEntity(DDeliveryPlan entity)
    {
        this.entity = entity;
    }

    public DDeliveryPlanQuery getQueryEntity()
    {
        return queryEntity;
    }

    public void setQueryEntity(DDeliveryPlanQuery queryEntity)
    {
        this.queryEntity = queryEntity;
    }

    public DRecordReply getRecord()
    {
        return record;
    }

    public void setRecord(DRecordReply record)
    {
        this.record = record;
    }

    public DRecordReplyQuery getQueryRecord()
    {
        return queryRecord;
    }

    public void setQueryRecord(DRecordReplyQuery queryRecord)
    {
        this.queryRecord = queryRecord;
    }

    public Page getRecordPage()
    {
        return recordPage;
    }

    public void setRecordPage(Page recordPage)
    {
        this.recordPage = recordPage;
    }

    public void setDeliveryPlanServiceImpl(DDeliveryPlanServiceImpl deliveryPlanServiceImpl)
    {
        this.deliveryPlanServiceImpl = deliveryPlanServiceImpl;
    }

    public void setDrecordReplyServiceImpl(DRecordReplyServiceImpl drecordReplyServiceImpl)
    {
        this.drecordReplyServiceImpl = drecordReplyServiceImpl;
    }

    public String create() throws Exception
    {
        return null;
    }

    public String toEdit() throws Exception
    {
        return null;
    }

    public String getFileIds()
    {
        return fileIds;
    }

    public void setFileIds(String fileIds)
    {
        this.fileIds = fileIds;
    }

    public String getUserMappingIds()
    {
        return userMappingIds;
    }

    public void setUserMappingIds(String userMappingIds)
    {
        this.userMappingIds = userMappingIds;
    }

    //    public void setInvoiceAction(DInvoiceAction invoiceAction)
    //    {
    //        this.invoiceAction = invoiceAction;
    //    }

    //    public DInvoiceQuery getInvoiceQuery()
    //    {
    //        return invoiceQuery;
    //    }
    //
    //    public void setInvoiceQuery(DInvoiceQuery invoiceQuery)
    //    {
    //        this.invoiceQuery = invoiceQuery;
    //    }

    public String getIsReply()
    {
        return isReply;
    }

    public void setIsReply(String isReply)
    {
        this.isReply = isReply;
    }

    @Override
    public String getWarnMessage()
    {
        return super.getWarnMessage();
    }

    @Override
    public String getMessage()
    {
        return super.getMessage();
    }

    @Override
    public String getInfoMessage()
    {
        return super.getInfoMessage();
    }

    /**
     * 各角色回复
     * @return
     * @throws Exception
     */
    public String edit() throws Exception
    {
        entity = deliveryPlanServiceImpl.selectOneByEntity(entity);//计划表对象
        record.setRecordId(entity.getPlanId());
        record.setReplyUserCd(sessionUser.getUserId());
        if (drecordReplyServiceImpl.updateByEntity(record) == 1)
        {//更新
            setMessage("回复成功");
            jsondata = record.getRecordId();
        }
        else
        {
            setInfoMessage("回复失败");
        }
        return JSON_DATA;
    }

    public String reply()
    {
        recordPage = drecordReplyServiceImpl.pageQuery(queryRecord);//当前计划所有回复信息
        record.setRecordId(queryRecord.getRecordId());
        if (sessionUser.getUserId() != null && StringUtils.isNotEmpty(sessionUser.getUserId()))
        {
            record.setReplyUserCd(sessionUser.getUserId());
            record = drecordReplyServiceImpl.selectOneByEntity(record);
        }
        return "DDeliveryPlan/_dispose";
    }

    /**
     * 审批的edit方法//审批部长更新意见
     * @return
     */
    public String waitDisEdit() throws Exception
    {
        if ("4".equals(entity.getVerifiedHeadStatus()))
        {
            entity.setStatusCd("2");//不同意把这条数据设置为有问题
            //发送通知消息
            pmsMessageTopicProducer.sendTopic(entity.getCreateUserCd(), "发料计划已审批通知", "您上传的发往" + selectShopNameByCd(entity.getToWsCd())
                    + "的计划表有问题，请修改后重新上传", sessionUser.getUserId());
        }
        if ("3".equals(entity.getVerifiedSiteStatus()) && "3".equals(entity.getVerifiedHeadStatus()))
        {
            entity.setStatusCd("1");//项目经理或部长都同意了就把状态设置为可下发
            //发送通知消息
            pmsMessageTopicProducer.sendTopic(entity.getCreateUserCd(), "发料计划已审批通知", "您上传的发往" + selectShopNameByCd(entity.getToWsCd())
                    + "的计划表已经审批，请尽快下发", sessionUser.getUserId());

        }
        entity.setVerifiedHeadTm(new Date());
        if (deliveryPlanServiceImpl.updateByEntity(entity) == 1)
        {
            setMessage("审批成功");
        }
        else
        {
            setInfoMessage("审批失败");
        }
        return JSON_DATA;
    }

    /**
     * 根据工地cd查询工地名称
     */
    public String selectShopNameByCd(String shopCd)
    {
        MWorkshop shop = new MWorkshop();
        shop.setWsCd(shopCd);
        shop = mworkshopServiceImpl.selectOneByEntity(shop);
        if (shop != null)
        {
            return shop.getWsNm();
        }
        return "";
    }

    /**
     * 下发
     * @return
     * @throws Exception
     */
    public String waitSendEdit() throws Exception
    {
        entity.setStatusCd("3");
        if (deliveryPlanServiceImpl.waitSendEdit(entity, userMappingIds) != 0)
        {
            setMessage("下发成功");
        }
        else
        {
            setInfoMessage("下发失败");
        }
        return JSON_DATA;
    }

    /**
     * 查看对象
     * @return 
     * @throws Exception
     */
    public String view() throws Exception
    {
        entity = deliveryPlanServiceImpl.selectOneByEntity(entity);//计划表对象
        record.setRecordId(entity.getPlanId());
        record.setReplyUserCd(sessionUser.getUserId());
        record = drecordReplyServiceImpl.selectOneByEntity(record);//当前用户回复信息
        queryRecord.setRecordId(entity.getPlanId());
        recordPage = drecordReplyServiceImpl.pageQuery(queryRecord);//当前计划所有回复信息
        if (entity.getStatusCd().equals("3")) //已下发页面
        {
            isReply = deliveryPlanServiceImpl.isReply(entity, sessionUser.getUserId());//当前登陆人是否有已下发计划表的回复权限
            return "DDeliveryPlan/already_dispose";
        }
        else if (entity.getStatusCd().equals("0"))//待审批页面
        {
            return "DDeliveryPlan/wait_dispose";
        }
        else
        //状态1和2 待下发页面
        {
            return "DDeliveryPlan/wait_send";
        }
    }

    /**
     * 跳转到发料计划列表页面
     * @return page
     * @throws Exception
     */
    public String list() throws Exception
    {
        page = deliveryPlanServiceImpl.pageQuery(queryEntity);
        //        if(invoiceQuery.getInvoiceId()==null||"".equals(invoiceQuery.getInvoiceId())){
        //            invoiceAction.setQueryEntity(invoiceQuery);
        //            invoiceAction.list();
        //        }
        return "DDeliveryPlan/list";
    }

    /**
     * 加载发料首页。包含发料计划与出门单列表
     * @return
     */
    public String toPlanList()
    {
        return "DDeliveryPlan/planlist";
    }

    /**
     * 计划表预览
     * @return
     */
    public String preViewPlan()
    {
        SysAttach attach = sysAttachServiceImpl.getAttachById(fileIds);
        String inputFilePath = attach.getAttachPath() + "/" + attach.getAttachId();// 要下载的文件名称
        String filetype = attach.getFileType();

        String path = ServletActionContext.getServletContext().getRealPath("/");//系统根目录，用于存入生成的图片用
        String fullInputFilePath = (path + inputFilePath).replace("/", "\\");//完整的附件存放路径
        InputStream is = null;
        try
        {
            File file = new File(fullInputFilePath);
            is = new FileInputStream(file);
        }
        catch (Exception e)
        {
            setInfoMessage("请重新上传附件");
            return "DDeliveryPlan/plan_preView_err";
        }
        try
        {
            if (("xls").equals(filetype) || "xlsx".equals(filetype))
            {
                List<String[]> retList = deliveryPlanServiceImpl.analyseExcel(is, filetype);
                if (retList != null && retList.size() != 0)
                {
                    String planId = retList.get(1)[6];//发运计划编号
                    String toWs = retList.get(2)[0];//收货中心
                    String unloadPlaceNm = retList.get(2)[4];//卸货地点
                    String sellOrderCode = retList.get(1)[0];//销售订单号
                    String createUserNm = "";//制表人名称
                    String createDate ="";//制表日期
                    for (int i = 3; i < retList.size(); i++)//获得制表人名称与制表日期
                    {//从第四行开始
                        String[] row = retList.get(i);
                        if(row[5].contains("制表人")){//如果这一行的第6列包含制表人这三个字
                            createUserNm = row[5];//制表人名称
                            createDate = row[7];//制表日期
                            break;
                        }
                    }

                    //发运计划编号
                    if (StringUtils.isNotEmpty(planId) && StringUtils.isNotEmpty(planId.substring(planId.indexOf("：") + 1).trim()))
                    {//发运计划编号存在
                        planId = planId.substring(planId.indexOf("：") + 1).trim();
                        DDeliveryPlan plan = new DDeliveryPlan();
                        plan.setPlanId(planId);
                        plan = deliveryPlanServiceImpl.selectOneByEntity(plan);
                        if (plan != null && "3".equals(plan.getStatusCd()))
                        {
                            setInfoMessage("发运计划编号为 '" + planId + "' 的计划表已存在并且状态是已下发，不能覆盖上传。");
                            return "DDeliveryPlan/plan_preView_err";
                        }
                    }
                    else
                    {//发运计划编号不存在
                        setInfoMessage("发运计划编号不存在。");
                        return "DDeliveryPlan/plan_preView_err";
                    }
                    //收货中心 
                    if (StringUtils.isNotEmpty(toWs) && StringUtils.isNotEmpty(toWs.substring(toWs.indexOf("：") + 1).trim()))
                    {//收货单位存在
                        toWs = toWs.substring(toWs.indexOf("：") + 1).trim();
                        int ex = deliveryPlanServiceImpl.shopIsEx(toWs);
                        if (ex == 0)
                        {
                            setInfoMessage("收货单位名称" + toWs + "不存在，请新建该收货单位。");
                            return "DDeliveryPlan/plan_preView_err";
                        }
                        if (ex == 2)
                        {
                            setInfoMessage("'" + toWs + "'项目已过期。");
                            return "DDeliveryPlan/plan_preView_err";
                        }
                    }
                    else
                    {//收货单位不存在
                        setInfoMessage("收货单位名称不存在，请新建该收货单位。");
                        return "DDeliveryPlan/plan_preView_err";
                    }
                    //卸货地点 
                    if (StringUtils.isNotEmpty(unloadPlaceNm)
                            && StringUtils.isNotEmpty(unloadPlaceNm.substring(unloadPlaceNm.indexOf("：") + 1).trim()))
                    {//卸货地点存在
                        unloadPlaceNm = unloadPlaceNm.substring(unloadPlaceNm.indexOf("：") + 1).trim();
                    }
                    else
                    {//卸货地点不存在
                        setInfoMessage("卸货地点不存在。");
                        return "DDeliveryPlan/plan_preView_err";
                    }
                    //销售订单号  
                    if (StringUtils.isNotEmpty(sellOrderCode)
                            && StringUtils.isNotEmpty(sellOrderCode.substring(sellOrderCode.indexOf("：") + 1).trim()))
                    {//销售订单号存在
                        sellOrderCode = sellOrderCode.substring(sellOrderCode.indexOf("：") + 1).trim();
                    }
                    else
                    {//销售订单号不存在
                        setInfoMessage("销售订单号不存在。");
                        return "DDeliveryPlan/plan_preView_err";
                    }
                    //制表人名称 
                    if (StringUtils.isNotEmpty(createUserNm) && StringUtils.isNotEmpty(createUserNm.substring(createUserNm.indexOf("：") + 1).trim()))
                    {//制表人名称存在
                        createUserNm = createUserNm.substring(createUserNm.indexOf("：") + 1).trim();
                        if (!sessionUser.getUsername().equals(createUserNm))
                        {
                            setInfoMessage("请在制表人一栏填写自己的名字。");
                            return "DDeliveryPlan/plan_preView_err";
                        }
                    }
                    else
                    {//制表人名称不存在
                        setInfoMessage("制表人名称不存在。");
                        return "DDeliveryPlan/plan_preView_err";
                    }
                    //制表日期 
                    if (StringUtils.isNotEmpty(createDate)
                            && StringUtils.isNotEmpty(createDate.substring(createDate.indexOf("：") + 1).replace(" ", "").trim()))
                    {//制表日期存在
                        createDate = createDate.substring(createDate.indexOf("：") + 1).replace(" ", "");
                    }
                    else
                    {//制表日期不存在
                        setInfoMessage("制表日期不存在。");
                        return "DDeliveryPlan/plan_preView_err";
                    }
                    entity = deliveryPlanServiceImpl.excelToPlan(fullInputFilePath, path, retList,createUserNm);
                    if ("1".equals(entity.getTitle()))
                    {
                        setInfoMessage("您上传的计划表页数太多，请在打印预览-设置-页面-缩放-调整为(F): 1页宽   9页高");
                        return "DInvoice/invoice_preView_err";
                    }
                    session.setAttribute(entity.getPlanId() + "ddeliveryItemList", entity.getDdeliveryItemList());//把产品集合放在session中
                    entity.setPlanFilePath(inputFilePath);//附件的存放路径
                    entity.setCreateTm(new Date());//上传时间
                }
                else
                {
                    setInfoMessage("您上传的计划表格式不正确.");
                    return "DDeliveryPlan/plan_preView_err";
                }
            }
            else
            {
                setInfoMessage("请选用发料计划模板.");
                return "DDeliveryPlan/plan_preView_err";
            }
        }
        catch (Exception e)
        {
            LOGGER.debug("解析文件错误:{[]}", e);
            setInfoMessage("您上传的计划表格式不正确.");
            return "DDeliveryPlan/plan_preView_err";
        }
        return "DDeliveryPlan/plan_preView";
    }

    /**
     * 上传计划表
     */
    public String uploadPlan()
    {
        entity.setDdeliveryItemList((List<DDeliveryItem>) session.getAttribute(entity.getPlanId() + "ddeliveryItemList"));
        session.removeAttribute(entity.getPlanId() + "ddeliveryItemList");
        entity.setDeleteCd("0");
        entity.setUpdateTm(new Date());
        entity.setStatusCd("0");//待审批
        if (deliveryPlanServiceImpl.insert(entity) != 0)
        {
            setMessage("上传成功");
        }
        else
        {
            setInfoMessage("上传失败");
        }
        return JSON_DATA;
    }

    /**
     * 校验是否有重复的计划表（AJAX）
     * @throws IOException 
     */
    public String isExist() throws IOException
    {
        DDeliveryPlan plan = new DDeliveryPlan();
        plan.setPlanId(entity.getPlanId());

        plan.setCreateUserCd(entity.getCreateUserCd());
        plan.setToWsCd(entity.getToWsCd());
        plan.setDeliveryPlanTm(entity.getDeliveryPlanTm());
        plan = deliveryPlanServiceImpl.selectOneByEntity(plan);
        if (plan != null)//本条计划表存在，以后是覆盖操作
        {
            if (StringUtils.isNotEmpty(plan.getStatusCd()) && "3".equals(plan.getStatusCd()))
            {
                ServletActionContext.getResponse().getWriter().print(2);//有重复的并且是已经下发了的
            }
            else
                ServletActionContext.getResponse().getWriter().print(1);//有重复的
        }
        else
        {//本条计划表不存在，以后是新建操作，判断发运计划表编号是否已经使用
            plan = new DDeliveryPlan();
            plan.setPlanId(entity.getPlanId());
            List<DDeliveryPlan> pList = deliveryPlanServiceImpl.selectByEntity(plan);
            if (pList != null && pList.size() > 0)
            {
                ServletActionContext.getResponse().getWriter().print(3);//发运计划编号已经被使用了
                return null;
            }
            //  ServletActionContext.getResponse().getWriter().print(0);//没有重复
        }
        return null;

    }

    /**
     * 重新上传发料计划
     */
    public String reUpload()
    {
        entity.setDdeliveryItemList((List<DDeliveryItem>) session.getAttribute(entity.getPlanId() + "ddeliveryItemList"));
        session.removeAttribute(entity.getPlanId() + "ddeliveryItemList");
        entity.setDeleteCd("0");
        entity.setUpdateTm(new Date());
        entity.setStatusCd("0");//待审批
        if (deliveryPlanServiceImpl.reUpload(entity) != 0)
        {
            setMessage("上传成功");
        }
        else
        {
            setInfoMessage("上传失败");
        }
        return JSON_DATA;
    }

    /**
     * 跳转到高级查询页面
     * @return
     */
    public String toEntireQuery()
    {
        return "DDeliveryPlan/plan_entireQuery";
    }

    /**
     * 首页列表查看图片
     * @return
     */
    public String listPic()
    {
        return "DDeliveryPlan/planList_pic";

    }

    /**
     * 预览后不上传点击关闭，清除session中的产品集合
     * @throws IOException 
     */
    public String removeSession() throws IOException
    {

        if (entity.getPlanId() != null)
        {
            session.removeAttribute(entity.getPlanId() + "ddeliveryItemList");
        }

        return null;

    }
    
    /**
     * 删除计划表
     * @return
     */
    public String deletePlan(){
        if (deliveryPlanServiceImpl.deletePlan(entity) != 0)
        {
            setMessage("删除成功");
        }
        else
        {
            setInfoMessage("删除失败");
        }
        return JSON_DATA;
    }
    
    public String getJsondata()
    {
        return jsondata;
    }

    public void setJsondata(String jsondata)
    {
        this.jsondata = jsondata;
    }

}