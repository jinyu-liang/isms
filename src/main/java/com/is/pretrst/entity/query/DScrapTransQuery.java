package com.is.pretrst.entity.query;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.is.pretrst.entity.DScrapTrans;

/**
 *
 * @ClassName: DScrapTrans
 * @Description: DScrapTrans表的对应的查询对象，增加Date/Double/int型字段的区间查询属性字段
 * @author 
 * @date 2013-09-10 10:26:53
 *
 */
 
public class DScrapTransQuery extends DScrapTrans {

    private static final long serialVersionUID = 1L;

    
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("transId",getTransId())
            .append("fromWsCd",getFromWsCd())
            .append("transUserCd",getTransUserCd())
            .append("sender",getSender())
            .append("toWsCd",getToWsCd())
            .append("receiver",getReceiver())
            .append("tCompanyNm",getTCompanyNm())
            .append("driver",getDriver())
            .append("weight",getWeight())
            .append("deliveryTm",getDeliveryTm())
            .append("arrivalTm",getArrivalTm())
            .append("memo",getMemo())
            .append("statusCd",getStatusCd())
            .toString();
    }
    
}