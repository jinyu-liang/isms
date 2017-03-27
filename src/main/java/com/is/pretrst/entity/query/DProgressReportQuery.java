package com.is.pretrst.entity.query;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.is.pretrst.entity.DProgressReport;

/**
 *
 * @ClassName: DProgressReport
 * @Description: DProgressReport表的对应的查询对象，增加Date/Double/int型字段的区间查询属性字段
 * @author 
 * @date 2013-09-10 10:26:30
 *
 */
 
public class DProgressReportQuery extends DProgressReport {

    private static final long serialVersionUID = 1L;

    /**
     * Date字段：reportDt  的查询条件
     */
    
    /* 查询条件：ReportDt  的开始区间  */
    private Date reportDtStart;
    
    public Date getReportDtStart(){
        return reportDtStart;
    }
    public void setReportDtStart(Date reportDtStart){
        this.reportDtStart = reportDtStart;
    }
    
    /*  查询条件：reportDt  的结束区间     */
    private Date reportDtEnd;
    
    public Date getReportDtEnd(){
        return reportDtEnd;
    }
    public void setReportDtEnd(Date reportDtEnd){
        this.reportDtEnd = reportDtEnd;
    }
    /**
     * Date字段：createTm  的查询条件
     */
    
    /* 查询条件：CreateTm  的开始区间  */
    private Date createTmStart;
    
    public Date getCreateTmStart(){
        return createTmStart;
    }
    public void setCreateTmStart(Date createTmStart){
        this.createTmStart = createTmStart;
    }
    
    /*  查询条件：createTm  的结束区间     */
    private Date createTmEnd;
    
    public Date getCreateTmEnd(){
        return createTmEnd;
    }
    public void setCreateTmEnd(Date createTmEnd){
        this.createTmEnd = createTmEnd;
    }
    /**
     * Date字段：updateTm  的查询条件
     */
    
    /* 查询条件：UpdateTm  的开始区间  */
    private Date updateTmStart;
    
    public Date getUpdateTmStart(){
        return updateTmStart;
    }
    public void setUpdateTmStart(Date updateTmStart){
        this.updateTmStart = updateTmStart;
    }
    
    /*  查询条件：updateTm  的结束区间     */
    private Date updateTmEnd;
    
    public Date getUpdateTmEnd(){
        return updateTmEnd;
    }
    public void setUpdateTmEnd(Date updateTmEnd){
        this.updateTmEnd = updateTmEnd;
    }
    
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("reportId",getReportId())
            .append("wsCd",getWsCd())
            .append("reportDt",getReportDt())
            .append("reportDtStart",getReportDtStart())
            .append("reportDtEnd",getReportDtEnd())
            .append("userCd",getUserCd())
            .append("createTm",getCreateTm())
            .append("createTmStart",getCreateTmStart())
            .append("createTmEnd",getCreateTmEnd())
            .append("updateTm",getUpdateTm())
            .append("updateTmStart",getUpdateTmStart())
            .append("updateTmEnd",getUpdateTmEnd())
            .toString();
    }
    
}