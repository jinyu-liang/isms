package com.is.pretrst.entity.query;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.is.pretrst.entity.DReportImage;

/**
 *
 * @ClassName: DReportImage
 * @Description: DReportImage表的对应的查询对象，增加Date/Double/int型字段的区间查询属性字段
 * @author 
 * @date 2013-09-10 10:26:45
 *
 */
 
public class DReportImageQuery extends DReportImage {

    private static final long serialVersionUID = 1L;

    /**
     * Integer字段：photoId  的查询条件
     */
    
    /* 查询条件：PhotoId  的开始区间  */
    private Integer photoIdStart;
    
    public Integer getPhotoIdStart(){
        return photoIdStart;
    }
    public void setPhotoIdStart(Integer photoIdStart){
        this.photoIdStart = photoIdStart;
    }
    
    /*  查询条件：photoId  的结束区间     */
    private Integer photoIdEnd;
    
    public Integer getPhotoIdEnd(){
        return photoIdEnd;
    }
    public void setPhotoIdEnd(Integer photoIdEnd){
        this.photoIdEnd = photoIdEnd;
    }
    
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("photoId",getPhotoId())
            .append("photoIdStart",getPhotoIdStart())
            .append("photoIdEnd",getPhotoIdEnd())
            .append("invoiceId",getReportId())
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