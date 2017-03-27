package com.is.pretrst.entity;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.base.mybatis.AbstractBaseEntity;

/**
 *
 * @ClassName: DInvoice
 * @Description: DInvoice表的对应的java对象，该文件为工具自动生成，请勿手工修改!
 * @author 
 * @date 2013-09-10 10:26:12
 *
 */
public class DInvoice extends AbstractBaseEntity
{

    private static final long  serialVersionUID       = 1L;

    /* 发货单ID */
    public static final String ALIAS_INVOICE_ID       = "invoiceId";

    /* 发货计划ID */
    public static final String ALIAS_PLAN_ID          = "planId";

    /* 销售订单号 */
    public static final String ALIAS_SELL_ORDER_CODE  = "sellOrderCode";

    /* 计划表标题 */
    public static final String ALIAS_TITLE            = "title";

    /* 运输公司名称 */
    public static final String ALIAS_T_COMPANY_NM     = "tcompanyNm";

    /* 运输公司电话 */
    public static final String ALIAS_T_COMPANY_TEL    = "tcompanyTel";

    /* 司机 */
    public static final String ALIAS_DRIVER           = "driver";

    /* 车号 */
    public static final String ALIAS_TRUCK_NUM        = "truckNum";

    /* 司机电话 */
    public static final String ALIAS_DRIVER_TEL       = "driverTel";

    /* 发货仓库编码 */
    public static final String ALIAS_FROM_WS_CD       = "fromWsCd";

    /* 收货工作中心编码 */
    public static final String ALIAS_TO_WS_CD         = "toWsCd";

    /* 审核人名 */
    public static final String ALIAS_VERIFIED_BY      = "verifiedBy";

    /* 起单用户ID */
    public static final String ALIAS_INVOICE_USER_ID  = "invoiceUserId";

    /* 发料请求时间 */
    public static final String ALIAS_APPROVAL_REQ_TM  = "approvalReqTm";

    /* 发货批准时间 */
    public static final String ALIAS_APPROVAL_TM      = "approvalTm";

    /* 发货批准用户 */
    public static final String ALIAS_APPROVAL_USER_CD = "approvalUserCd";
    
    /* 确认发车时间 */
    public static final String ALIAS_SURE_DELIVERY_TM = "sureDeliveryTm";

    /* 发货时间 */
    public static final String ALIAS_DELIVERY_TM      = "deliveryTm";

    /* 到货时间 */
    public static final String ALIAS_ARRIVAL_TM       = "arrivalTm";

    /* 备注 */
    public static final String ALIAS_MEMO             = "memo";

    /* 最后更新时间 */
    public static final String ALIAS_UPDAGE_TM        = "updateTm";

    /* 状态 */
    public static final String ALIAS_STATUS_CD        = "statusCd";
    
    /* 出门单图片 */
    public static final String ALIAS_DINVOICE_IMAGE_PATH        = "dinvoiceImagePath";

    /* 删除区分 */
    public static final String ALIAS_DELETE_CD        = "deleteCd";

    /* 发货单ID */
    private String             invoiceId;

    /* 发货计划ID */
    private String             planId;

    /* 销售订单号 */
    private String             sellOrderCode;

    /* 计划表标题 */
    private String             title;

    /* 运输公司名称 */
    private String             tcompanyNm;

    /* 运输公司电话 */
    private String             tcompanyTel;

    /* 司机 */
    private String             driver;

    /* 车号 */
    private String             truckNum;

    /* 司机电话 */
    private String             driverTel;

    /* 发货仓库编码 */
    private String             fromWsCd;

    /* 收货工作中心编码 */
    private String             toWsCd;

    /* 审核人名 */
    private String             verifiedBy;

    /* 起单用户ID */
    private String             invoiceUserId;

    /* 发料请求时间 */
    private Date               approvalReqTm;

    /* 发货批准时间 */
    private Date               approvalTm;

    /* 发货批准用户 */
    private String             approvalUserCd;

    /* 发货时间 */
    private Date               deliveryTm;

    /* 到货时间 */
    private Date               arrivalTm;

    /* 备注 */
    private String             memo;

    /* 最后更新时间 */
    private Date               updateTm;

    /* 状态 */
    private String             statusCd;

    /* 删除区分 */
    private String             deleteCd;

    /* 收货工作中心名称 */
    private String             toWsNm;

    /* 发货仓库名称 */
    private String             fromWsNm;

    /* 发货批准用户名称 */
    private String             approvalUserNm;
    
    /*发货状态名称*/
    private String statusNm;
    
    /*确认发车时间*/
    private Date sureDeliveryTm;
    
    /* 出门单缩略图片 */
    private String dinvoiceImagePath;
    
    private List<String> dinvoiceImages;


    public String getInvoiceId()
    {
        return this.invoiceId;
    }

    public void setInvoiceId(String invoiceId)
    {
        this.invoiceId = invoiceId;
    }

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

    public String getTcompanyNm()
    {
        return tcompanyNm;
    }

    public void setTcompanyNm(String tcompanyNm)
    {
        this.tcompanyNm = tcompanyNm;
    }

    public String getTcompanyTel()
    {
        return tcompanyTel;
    }

    public void setTcompanyTel(String tcompanyTel)
    {
        this.tcompanyTel = tcompanyTel;
    }

    public String getDriver()
    {
        return this.driver;
    }

    public void setDriver(String driver)
    {
        this.driver = driver;
    }

    public String getTruckNum()
    {
        return this.truckNum;
    }

    public void setTruckNum(String truckNum)
    {
        this.truckNum = truckNum;
    }

    public String getDriverTel()
    {
        return this.driverTel;
    }

    public void setDriverTel(String driverTel)
    {
        this.driverTel = driverTel;
    }

    public String getFromWsCd()
    {
        return this.fromWsCd;
    }

    public void setFromWsCd(String fromWsCd)
    {
        this.fromWsCd = fromWsCd;
    }

    public String getToWsCd()
    {
        return this.toWsCd;
    }

    public void setToWsCd(String toWsCd)
    {
        this.toWsCd = toWsCd;
    }

    public String getVerifiedBy()
    {
        return this.verifiedBy;
    }

    public void setVerifiedBy(String verifiedBy)
    {
        this.verifiedBy = verifiedBy;
    }

    public String getInvoiceUserId()
    {
        return this.invoiceUserId;
    }

    public void setInvoiceUserId(String invoiceUserId)
    {
        this.invoiceUserId = invoiceUserId;
    }

    public Date getApprovalReqTm()
    {
        return this.approvalReqTm;
    }

    public void setApprovalReqTm(Date approvalReqTm)
    {
        this.approvalReqTm = approvalReqTm;
    }

    public Date getApprovalTm()
    {
        return this.approvalTm;
    }

    public void setApprovalTm(Date approvalTm)
    {
        this.approvalTm = approvalTm;
    }

    public String getApprovalUserCd()
    {
        return this.approvalUserCd;
    }

    public void setApprovalUserCd(String approvalUserCd)
    {
        this.approvalUserCd = approvalUserCd;
    }

    public Date getDeliveryTm()
    {
        return this.deliveryTm;
    }

    public void setDeliveryTm(Date deliveryTm)
    {
        this.deliveryTm = deliveryTm;
    }

    public Date getArrivalTm()
    {
        return this.arrivalTm;
    }

    public void setArrivalTm(Date arrivalTm)
    {
        this.arrivalTm = arrivalTm;
    }

    public String getMemo()
    {
        return this.memo;
    }

    public void setMemo(String memo)
    {
        this.memo = memo;
    }

    public Date getUpdateTm()
    {
        return updateTm;
    }

    public void setUpdateTm(Date updateTm)
    {
        this.updateTm = updateTm;
    }

    public String getStatusCd()
    {
        return this.statusCd;
    }

    public void setStatusCd(String statusCd)
    {
        if("".equals(statusCd)){
            this.statusNm = "";
        }else if("6".equals(statusCd)){
            this.statusNm = "刚上传";
        }else if("0".equals(statusCd)){
            this.statusNm = "待审批";
        }else if("1".equals(statusCd)){
            this.statusNm = "可发车";
        }else if("2".equals(statusCd)){
            this.statusNm = "发车有问题";
        }else if("3".equals(statusCd)){
            this.statusNm = "发货中";
        }else if("4".equals(statusCd)){
            this.statusNm = "已收货";
        }else if("5".equals(statusCd)){
            this.statusNm = "收货有问题";
        }else if("7".equals(statusCd)){ 
            this.statusNm = "收货问题解决";
        }
        this.statusCd=statusCd;
    }

    public String getDeleteCd()
    {
        return this.deleteCd;
    }

    public void setDeleteCd(String deleteCd)
    {
        this.deleteCd = deleteCd;
    }

    public String getToWsNm()
    {
        return toWsNm;
    }

    public void setToWsNm(String toWsNm)
    {
        this.toWsNm = toWsNm;
    }

    public String getFromWsNm()
    {
        return fromWsNm;
    }

    public void setFromWsNm(String fromWsNm)
    {
        this.fromWsNm = fromWsNm;
    }

    public String getApprovalUserNm()
    {
        return approvalUserNm;
    }

    public void setApprovalUserNm(String approvalUserNm)
    {
        this.approvalUserNm = approvalUserNm;
    }

    public String getStatusNm()
    {
        return statusNm;
    }

    public Date getSureDeliveryTm()
    {
        return sureDeliveryTm;
    }
    
    public void setSureDeliveryTm(Date sureDeliveryTm)
    {
        this.sureDeliveryTm = sureDeliveryTm;
    }

    public void setStatusNm(String statusNm)
    {
        this.statusNm = statusNm;
    }

    /* 关联对象 */
    private List<DInvoiceItem> dInvoiceItems;

    public List<DInvoiceItem> getDInvoiceItems()
    {
        return this.dInvoiceItems;
    }

    public void setDInvoiceItems(List<DInvoiceItem> dInvoiceItems)
    {
        this.dInvoiceItems = dInvoiceItems;
    }

    public List<String> getDinvoiceImages()
    {
        return dinvoiceImages;
    }

    public void setDinvoiceImages(List<String> dinvoiceImages)
    {
        this.dinvoiceImages = dinvoiceImages;
    }

    public String getDinvoiceImagePath()
    {
        return dinvoiceImagePath;
    }

    public void setDinvoiceImagePath(String dinvoiceImagePath)
    {
        this.dinvoiceImagePath = dinvoiceImagePath;
    }

    /**
     * toString方法
     */
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("invoiceId", getInvoiceId()).append("planId", getPlanId())
                .append("sellOrderCode", getSellOrderCode()).append("title", getTitle()).append("tcompanyNm", getTcompanyNm())
                .append("tcompanyTel", getTcompanyTel()).append("driver", getDriver()).append("truckNum", getTruckNum())
                .append("driverTel", getDriverTel()).append("fromWsCd", getFromWsCd()).append("toWsCd", getToWsCd())
                .append("verifiedBy", getVerifiedBy()).append("invoiceUserId", getInvoiceUserId()).append("approvalReqTm", getApprovalReqTm())
                .append("approvalTm", getApprovalTm()).append("approvalUserCd", getApprovalUserCd()).append("deliveryTm", getDeliveryTm())
                .append("arrivalTm", getArrivalTm()).append("memo", getMemo()).append("updageTm", getUpdateTm()).append("statusCd", getStatusCd())
                .append("deleteCd", getDeleteCd()).append("dinvoiceImagePath",getDinvoiceImagePath()).toString();
    }
}