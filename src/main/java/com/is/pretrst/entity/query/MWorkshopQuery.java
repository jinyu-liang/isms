package com.is.pretrst.entity.query;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.is.pretrst.entity.MWorkshop;

/**
 *
 * @ClassName: MWorkshop
 * @Description: MWorkshop表的对应的查询对象，增加Date/Double/int型字段的区间查询属性字段
 * @author 
 * @date 2013-09-10 10:26:58
 *
 */
 
public class MWorkshopQuery extends MWorkshop {

    private static final long serialVersionUID = 1L;

    /**
     * Date字段：beginTime  的查询条件
     */
    
    /* 查询条件：BeginTime  的开始区间  */
    private Date beginTimeStart;
    
    public Date getBeginTimeStart(){
        return beginTimeStart;
    }
    public void setBeginTimeStart(Date beginTimeStart){
        this.beginTimeStart = beginTimeStart;
    }
    
    /*  查询条件：beginTime  的结束区间     */
    private Date beginTimeEnd;
    
    public Date getBeginTimeEnd(){
        return beginTimeEnd;
    }
    public void setBeginTimeEnd(Date beginTimeEnd){
        this.beginTimeEnd = beginTimeEnd;
    }
    /**
     * Date字段：endTime  的查询条件
     */
    
    /* 查询条件：EndTime  的开始区间  */
    private Date endTimeStart;
    
    public Date getEndTimeStart(){
        return endTimeStart;
    }
    public void setEndTimeStart(Date endTimeStart){
        this.endTimeStart = endTimeStart;
    }
    
    /*  查询条件：endTime  的结束区间     */
    private Date endTimeEnd;
    
    public Date getEndTimeEnd(){
        return endTimeEnd;
    }
    public void setEndTimeEnd(Date endTimeEnd){
        this.endTimeEnd = endTimeEnd;
    }
    
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("wsCd",getWsCd())
            .append("wsNm",getWsNm())
            .append("divCd",getDivCd())
            .append("typeCd",getTypeCd())
            .append("managerUserId",getManagerUserId())
            .append("beginTime",getBeginTime())
            .append("beginTimeStart",getBeginTimeStart())
            .append("beginTimeEnd",getBeginTimeEnd())
            .append("endTime",getEndTime())
            .append("endTimeStart",getEndTimeStart())
            .append("endTimeEnd",getEndTimeEnd())
            .append("deleteCd",getDeleteCd())
            .toString();
    }
    
}