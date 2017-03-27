package com.is.pretrst.entity.query;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.is.pretrst.entity.DInvoiceImage;

/**
 *
 * @ClassName: DInvoiceImage
 * @Description: DInvoiceImage表的对应的查询对象，增加Date/Double/int型字段的区间查询属性字段
 * @author 
 * @date 2013-09-10 10:26:21
 *
 */
 
public class DInvoiceImageQuery extends DInvoiceImage {

    private static final long serialVersionUID = 1L;

    /**
     * Integer字段：photoId  的查询条件
     */
    
    
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("photoId",getPhotoId())
            .append("invoiceId",getInvoiceId())
            .append("fileName",getFileName())
            .append("posLongitude",getPosLongitude())
            .append("posLatitude",getPosLatitude())
            .append("posHeight",getPosHeight())
            .append("photoUserCd",getPhotoUserCd())
            .append("photoTm",getPhotoTm())
            .append("uploadTm",getUploadTm())
            .append("memo",getMemo())
            .append("checkTm",getCheckTm())
            .append("checkUserCd",getCheckUserCd())
            .append("statusCd",getStatusCd())
            .toString();
    }
    
}