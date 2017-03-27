package com.is.pretrst.entity;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import com.base.mybatis.AbstractBaseEntity;

/**
 *
 * @ClassName: DDeliveryPlan
 * @Description: DDeliveryPlan表的对应的java对象，该文件为工具自动生成，请勿手工修改!
 * @author 
 * @date 2013-09-09 09:25:29
 *
 */
public class DDeliveryPlan extends AbstractBaseEntity
{

    private static final long  serialVersionUID            = 1L;

    /* 发货计划ID */
    public static final String ALIAS_PLAN_ID               = "planId";

    /* 销售订单号 */
    public static final String ALIAS_SELL_ORDER_CODE       = "sellOrderCode";

    /* 计划表标题 */
    public static final String ALIAS_TITLE                 = "title";

    /* 收货工作中心编码 */
    public static final String ALIAS_TO_WS_CD              = "toWsCd";

    /* 卸货地点 */
    public static final String ALIAS_UNLOAD_PLACE_NM       = "unloadPlaceNm";

    /* 计划发货时间 */
    public static final String ALIAS_DELIVERY_PLAN_TM      = "deliveryPlanTm";

    /* 审核项目经理用户Cd */
    public static final String ALIAS_VERIFIED_SITE_USER_CD = "verifiedSiteUserCd";

    /* 项目经理审核时间 */
    public static final String ALIAS_VERIFIED_SITE_TM      = "verifiedSiteTm";

    /* 审核结果 */
    public static final String ALIAS_VERIFIED_SITE_STATUS  = "verifiedSiteStatus";

    /* 项目经理审核备注 */
    public static final String ALIAS_VERIFIED_SITE_MEMO    = "verifiedSiteMemo";

    /* 审核部长用户Cd */
    public static final String ALIAS_VERIFIED_HEAD_USER_CD = "verifiedHeadUserCd";

    /* 部长审核时间 */
    public static final String ALIAS_VERIFIED_HEAD_TM      = "verifiedHeadTm";

    /* 部长审核结果 */
    public static final String ALIAS_VERIFIED_HEAD_STATUS  = "verifiedHeadStatus";

    /* 部长审核备注 */
    public static final String ALIAS_VERIFIED_HEAD_MEMO    = "verifiedHeadMemo";

    /* 发货请求时间 */
    public static final String ALIAS_DELIVERY_REQ_TM       = "deliveryReqTm";

    /* 发货完成时间 */
    public static final String ALIAS_DELIVERY_DONE_TM      = "deliveryDoneTm";

    /* 备注 */
    public static final String ALIAS_MEMO                  = "memo";

    /* 计划表文件路径 */
    public static final String ALIAS_PLAN_FILE_PATH        = "planFilePath";

    /* 状态 */
    public static final String ALIAS_STATUS_CD             = "statusCd";

    /* 创建用户Id */
    public static final String ALIAS_CREATE_USER_CD        = "createUserCd";

    /* 创建时间 */
    public static final String ALIAS_CREATE_TM             = "createTm";

    /* 修改时间 */
    public static final String ALIAS_UPDATE_TM             = "updateTm";

    /* 删除区分 */
    public static final String ALIAS_DELETE_CD             = "deleteCd";

    /*合同图片存放地址 */
    public static final String ALIAS_PLAN_IMAGE_PATH       = "planImagePath";

    /* 发货计划ID */
    private String             planId;

    /* 销售订单号 */
    private String             sellOrderCode;

    /* 计划表标题 */
    private String             title;

    /* 收货工作中心编码 */
    private String             toWsCd;

    /* 卸货地点 */
    private String             unloadPlaceNm;

    /* 计划发货时间 */
    private Date               deliveryPlanTm;

    /* 审核项目经理用户Cd */
    private String             verifiedSiteUserCd;

    /* 项目经理审核时间 */
    private Date               verifiedSiteTm;

    /* 审核结果 */
    private String             verifiedSiteStatus;

    /* 项目经理审核备注 */
    private String             verifiedSiteMemo;

    /* 审核部长用户Cd */
    private String             verifiedHeadUserCd;

    /* 部长审核时间 */
    private Date               verifiedHeadTm;

    /* 部长审核结果 */
    private String             verifiedHeadStatus;

    /* 部长审核备注 */
    private String             verifiedHeadMemo;

    /* 发货请求时间 */
    private Date               deliveryReqTm;

    /* 发货完成时间 */
    private Date               deliveryDoneTm;

    /* 备注 */
    private String             memo;

    /* 计划表文件路径 */
    private String             planFilePath;

    /*计划表图片路径*/
    private String             planImagePath;

    /* 状态 */
    private String             statusCd;

    /* 创建用户Id */
    private String             createUserCd;

    /* 创建时间 */
    private Date               createTm;

    /* 修改时间 */
    private Date               updateTm;

    /* 删除区分 */
    private String             deleteCd;
    
    /*新增*/
    private String createUserNm;/* 创建用户名称 */
    private String toWsNm;/* 收货工作中心名称 */
    private String verifiedHeadUserNm;/* 审核部长用户名称 */
    private String verifiedSiteUserNm;/* 审核项目经理用户名称 */
    private List<DRecordUserMapping> dRecordUserMappingList;//回复映射
    private int dataAction;
//    private String invoiceStatus;/*相关联的出门单的状态，用于发货员查看已下发计划表时展示按钮的操作*/
    private String statusNm;//数据状态名
    private String recordReplyId;//回复id
    private Date recordUpdateTm;//最后回复时间
    private String recordTitle;
    private String recordContent;
    private List<DDeliveryItem> ddeliveryItemList;//存放产品集合
    private List<String> dinvoiceImages;//与发货员关联的出门单详情图片
    private List<String> dinvoicePaths;//与发货员关联的出门单缩略图
    private String isUploadInvoice ;//本条计划是否上传了出门单
    private String invoiceId;//出门单id;手机端发货员查看已经下发计划表时对应自己上传的出门单id，用于发货员申请发车
    private Date downSendTm;//计划表下发时间
    private String isReple; //是否有回复本条计划的权限
    public String getPlanId()
    {
        return this.planId;
    }

    public void setPlanId(String planId)
    {
        this.planId = planId;
    }

    public String getSellOrderCode()
    {
        return this.sellOrderCode;
    }

    public void setSellOrderCode(String sellOrderCode)
    {
        this.sellOrderCode = sellOrderCode;
    }

    public String getTitle()
    {
        return this.title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getToWsCd()
    {
        return this.toWsCd;
    }

    public void setToWsCd(String toWsCd)
    {
        this.toWsCd = toWsCd;
    }

    public String getUnloadPlaceNm()
    {
        return this.unloadPlaceNm;
    }

    public void setUnloadPlaceNm(String unloadPlaceNm)
    {
        this.unloadPlaceNm = unloadPlaceNm;
    }

    public Date getDeliveryPlanTm()
    {
        return this.deliveryPlanTm;
    }

    public void setDeliveryPlanTm(Date deliveryPlanTm)
    {
        this.deliveryPlanTm = deliveryPlanTm;
    }

    public String getVerifiedSiteUserCd()
    {
        return this.verifiedSiteUserCd;
    }

    public void setVerifiedSiteUserCd(String verifiedSiteUserCd)
    {
        this.verifiedSiteUserCd = verifiedSiteUserCd;
    }

    public Date getVerifiedSiteTm()
    {
        return this.verifiedSiteTm;
    }

    public void setVerifiedSiteTm(Date verifiedSiteTm)
    {
        this.verifiedSiteTm = verifiedSiteTm;
    }

    public String getVerifiedSiteStatus()
    {
        return this.verifiedSiteStatus;
    }

    public void setVerifiedSiteStatus(String verifiedSiteStatus)
    {
        this.verifiedSiteStatus = verifiedSiteStatus;
    }

    public String getVerifiedSiteMemo()
    {
        return this.verifiedSiteMemo;
    }

    public void setVerifiedSiteMemo(String verifiedSiteMemo)
    {
        this.verifiedSiteMemo = verifiedSiteMemo;
    }

    public String getVerifiedHeadUserCd()
    {
        return this.verifiedHeadUserCd;
    }

    public void setVerifiedHeadUserCd(String verifiedHeadUserCd)
    {
        this.verifiedHeadUserCd = verifiedHeadUserCd;
    }

    public Date getVerifiedHeadTm()
    {
        return this.verifiedHeadTm;
    }

    public void setVerifiedHeadTm(Date verifiedHeadTm)
    {
        this.verifiedHeadTm = verifiedHeadTm;
    }

    public String getVerifiedHeadStatus()
    {
        return this.verifiedHeadStatus;
    }

    public void setVerifiedHeadStatus(String verifiedHeadStatus)
    {
        this.verifiedHeadStatus = verifiedHeadStatus;
    }

    public String getVerifiedHeadMemo()
    {
        return this.verifiedHeadMemo;
    }

    public void setVerifiedHeadMemo(String verifiedHeadMemo)
    {
        this.verifiedHeadMemo = verifiedHeadMemo;
    }

    public Date getDeliveryReqTm()
    {
        return this.deliveryReqTm;
    }

    public void setDeliveryReqTm(Date deliveryReqTm)
    {
        this.deliveryReqTm = deliveryReqTm;
    }

    public Date getDeliveryDoneTm()
    {
        return this.deliveryDoneTm;
    }

    public void setDeliveryDoneTm(Date deliveryDoneTm)
    {
        this.deliveryDoneTm = deliveryDoneTm;
    }

    public String getMemo()
    {
        return this.memo;
    }

    public void setMemo(String memo)
    {
        this.memo = memo;
    }

    public String getPlanFilePath()
    {
        return this.planFilePath;
    }

    public void setPlanFilePath(String planFilePath)
    {
        this.planFilePath = planFilePath;
    }

    public String getPlanImagePath()
    {
        return planImagePath;
    }

    public void setPlanImagePath(String planImagePath)
    {
        this.planImagePath = planImagePath;
    }

    public String getStatusCd()
    {
        return this.statusCd;
    }

    public void setStatusCd(String statusCd)
    {
        if(statusCd==null)
        {
            this.statusNm = "";
        }
        if("0".equals(statusCd))
        {
            this.statusNm = "待审批";
        }
        else if("1".equals(statusCd))
        {
            this.statusNm = "可下发";
        }
        else if("2".equals(statusCd))
        {
            this.statusNm = "有问题";
        }
        else if("3".equals(statusCd))
        {
            this.statusNm = "已下发";
        }
        this.statusCd = statusCd;  
    }

    public String getCreateUserCd()
    {
        return this.createUserCd;
    }

    public void setCreateUserCd(String createUserCd)
    {
        this.createUserCd = createUserCd;
    }

    public Date getCreateTm()
    {
        return this.createTm;
    }

    public void setCreateTm(Date createTm)
    {
        this.createTm = createTm;
    }

    public Date getUpdateTm()
    {
        return this.updateTm;
    }

    public void setUpdateTm(Date updateTm)
    {
        this.updateTm = updateTm;
    }

    public String getDeleteCd()
    {
        return this.deleteCd;
    }

    public void setDeleteCd(String deleteCd)
    {
        this.deleteCd = deleteCd;
    }
    

    public String getCreateUserNm()
    {
        return createUserNm;
    }

    public void setCreateUserNm(String createUserNm)
    {
        this.createUserNm = createUserNm;
    }

    public String getToWsNm()
    {
        return toWsNm;
    }

    public void setToWsNm(String toWsNm)
    {
        this.toWsNm = toWsNm;
    }

    public String getVerifiedHeadUserNm()
    {
        return verifiedHeadUserNm;
    }

    public void setVerifiedHeadUserNm(String verifiedHeadUserNm)
    {
        this.verifiedHeadUserNm = verifiedHeadUserNm;
    }

    public String getVerifiedSiteUserNm()
    {
        return verifiedSiteUserNm;
    }

    public void setVerifiedSiteUserNm(String verifiedSiteUserNm)
    {
        this.verifiedSiteUserNm = verifiedSiteUserNm;
    }

    public List<DRecordUserMapping> getdRecordUserMappingList()
    {
        return dRecordUserMappingList;
    }
    public void setdRecordUserMappingList(List<DRecordUserMapping> dRecordUserMappingList)
    {
        this.dRecordUserMappingList = dRecordUserMappingList;
    }

    public int getDataAction()
    {
        return dataAction;
    }

    public void setDataAction(int dataAction)
    {
        this.dataAction = dataAction;
    }

    public String getStatusNm()
    {
        return statusNm;
    }

    public void setStatusNm(String statusNm)
    {
        this.statusNm = statusNm;
    }
    public String getRecordReplyId()
    {
        return recordReplyId;
    }

    public void setRecordReplyId(String recordReplyId)
    {
        this.recordReplyId = recordReplyId;
    }

    public Date getRecordUpdateTm()
    {
        return recordUpdateTm;
    }

    public void setRecordUpdateTm(Date recordUpdateTm)
    {
        this.recordUpdateTm = recordUpdateTm;
    }

    public String getRecordTitle()
    {
        return recordTitle;
    }

    public void setRecordTitle(String recordTitle)
    {
        this.recordTitle = recordTitle;
    }

    public String getRecordContent()
    {
        return recordContent;
    }

    public void setRecordContent(String recordContent)
    {
        this.recordContent = recordContent;
    }

    public List<DDeliveryItem> getDdeliveryItemList()
    {
        return ddeliveryItemList;
    }

    public void setDdeliveryItemList(List<DDeliveryItem> ddeliveryItemList)
    {
        this.ddeliveryItemList = ddeliveryItemList;
    }

    public List<String> getDinvoiceImages()
    {
        return dinvoiceImages;
    }

    public void setDinvoiceImages(List<String> dinvoiceImages)
    {
        this.dinvoiceImages = dinvoiceImages;
    }

    public List<String> getDinvoicePaths()
    {
        return dinvoicePaths;
    }
    public void setDinvoicePaths(List<String> dinvoicePaths)
    {
        this.dinvoicePaths = dinvoicePaths;
    }

    public String getIsUploadInvoice()
    {
        return isUploadInvoice;
    }

    public void setIsUploadInvoice(String isUploadInvoice)
    {
        this.isUploadInvoice = isUploadInvoice;
    }

    public String getInvoiceId()
    {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId)
    {
        this.invoiceId = invoiceId;
    }

    public Date getDownSendTm()
    {
        return downSendTm;
    }

    public void setDownSendTm(Date downSendTm)
    {
        this.downSendTm = downSendTm;
    }

    public String getIsReple()
    {
        return isReple;
    }

    public void setIsReple(String isReple)
    {
        this.isReple = isReple;
    }

    /**
     * toString方法
     */
    public String toString()
    {
                return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                    .append("planId",getPlanId())
                    .append("sellOrderCode",getSellOrderCode())
                    .append("title",getTitle())
                    .append("toWsCd",getToWsCd())
                    .append("unloadPlaceNm",getUnloadPlaceNm())
                    .append("deliveryPlanTm",getDeliveryPlanTm())
                    .append("verifiedSiteUserCd",getVerifiedSiteUserCd())
                    .append("verifiedSiteTm",getVerifiedSiteTm())
                    .append("verifiedSiteStatus",getVerifiedSiteStatus())
                    .append("verifiedSiteMemo",getVerifiedSiteMemo())
                    .append("verifiedHeadUserCd",getVerifiedHeadUserCd())
                    .append("verifiedHeadTm",getVerifiedHeadTm())
                    .append("verifiedHeadStatus",getVerifiedHeadStatus())
                    .append("verifiedHeadMemo",getVerifiedHeadMemo())
                    .append("deliveryReqTm",getDeliveryReqTm())
                    .append("deliveryDoneTm",getDeliveryDoneTm())
                    .append("memo",getMemo())
                    .append("planFilePath",getPlanFilePath())
                    .append("planImagePath",getPlanImagePath())
                    .append("statusCd",getStatusCd())
                    .append("createUserCd",getCreateUserCd())
                    .append("createTm",getCreateTm())
                    .append("updateTm",getUpdateTm())
                    .append("deleteCd",getDeleteCd())
                    .append("dRecordUserMappingList",getdRecordUserMappingList())
                    .append("ddeliveryItemList",getDdeliveryItemList())
                    .append("dinvoicePaths",getDinvoicePaths())
                    .append("dinvoiceImages",getDinvoiceImages())
                    .append("isUploadInvoice",getIsUploadInvoice())
                    .append("statusNm",getStatusNm())
                    .append("isReply",getIsReple())
                    .toString();
    }
}