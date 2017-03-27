package com.is.pretrst.entity.query;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.is.pretrst.entity.DInvoice;

/**
 *
 * @ClassName: DInvoice
 * @Description: DInvoice表的对应的查询对象，增加Date/Double/int型字段的区间查询属性字段
 * @author 
 * @date 2013-09-10 10:26:12
 *
 */
 
public class DInvoiceQuery extends DInvoice {

    private static final long serialVersionUID = 1L;

    
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("invoiceId",getInvoiceId())
            .append("planId",getPlanId())
            .append("sellOrderCode",getSellOrderCode())
            .append("title",getTitle())
            .append("tcompanyNm",getTcompanyNm())
            .append("tcompanyTel",getTcompanyTel())
            .append("driver",getDriver())
            .append("truckNum",getTruckNum())
            .append("driverTel",getDriverTel())
            .append("fromWsCd",getFromWsCd())
            .append("toWsCd",getToWsCd())
            .append("verifiedBy",getVerifiedBy())
            .append("invoiceUserId",getInvoiceUserId())
            .append("approvalReqTm",getApprovalReqTm())
            .append("approvalTm",getApprovalTm())
            .append("approvalUserCd",getApprovalUserCd())
            .append("deliveryTm",getDeliveryTm())
            .append("arrivalTm",getArrivalTm())
            .append("memo",getMemo())
            .append("updageTm",getUpdateTm())
            .append("statusCd",getStatusCd())
            .append("deleteCd",getDeleteCd())
            .append("dinvoiceImagePath",getDinvoiceImagePath())
            .toString();
    }
    
}