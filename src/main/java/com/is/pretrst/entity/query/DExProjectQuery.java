package com.is.pretrst.entity.query;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.is.pretrst.entity.DExProject;

/**
 *
 * @ClassName: DExProject
 * @Description: DExProject表的对应的查询对象，增加Date/Double/int型字段的区间查询属性字段
 * @author 
 * @date 2013-09-10 10:26:07
 *
 */
 
public class DExProjectQuery extends DExProject {

    private static final long serialVersionUID = 1L;

    /**
     * Integer字段：projectId  的查询条件
     */
    
    /* 查询条件：ProjectId  的开始区间  */
    private Integer projectIdStart;
    
    public Integer getProjectIdStart(){
        return projectIdStart;
    }
    public void setProjectIdStart(Integer projectIdStart){
        this.projectIdStart = projectIdStart;
    }
    
    /*  查询条件：projectId  的结束区间     */
    private Integer projectIdEnd;
    
    public Integer getProjectIdEnd(){
        return projectIdEnd;
    }
    public void setProjectIdEnd(Integer projectIdEnd){
        this.projectIdEnd = projectIdEnd;
    }
    /**
     * Date字段：contractStartDate  的查询条件
     */
    
    /* 查询条件：ContractStartDate  的开始区间  */
    private Date contractStartDateStart;
    
    public Date getContractStartDateStart(){
        return contractStartDateStart;
    }
    public void setContractStartDateStart(Date contractStartDateStart){
        this.contractStartDateStart = contractStartDateStart;
    }
    
    /*  查询条件：contractStartDate  的结束区间     */
    private Date contractStartDateEnd;
    
    public Date getContractStartDateEnd(){
        return contractStartDateEnd;
    }
    public void setContractStartDateEnd(Date contractStartDateEnd){
        this.contractStartDateEnd = contractStartDateEnd;
    }
    /**
     * Date字段：startDate  的查询条件
     */
    
    /* 查询条件：StartDate  的开始区间  */
    private Date startDateStart;
    
    public Date getStartDateStart(){
        return startDateStart;
    }
    public void setStartDateStart(Date startDateStart){
        this.startDateStart = startDateStart;
    }
    
    /*  查询条件：startDate  的结束区间     */
    private Date startDateEnd;
    
    public Date getStartDateEnd(){
        return startDateEnd;
    }
    public void setStartDateEnd(Date startDateEnd){
        this.startDateEnd = startDateEnd;
    }
    /**
     * Date字段：lastReportDt  的查询条件
     */
    
    /* 查询条件：LastReportDt  的开始区间  */
    private Date lastReportDtStart;
    
    public Date getLastReportDtStart(){
        return lastReportDtStart;
    }
    public void setLastReportDtStart(Date lastReportDtStart){
        this.lastReportDtStart = lastReportDtStart;
    }
    
    /*  查询条件：lastReportDt  的结束区间     */
    private Date lastReportDtEnd;
    
    public Date getLastReportDtEnd(){
        return lastReportDtEnd;
    }
    public void setLastReportDtEnd(Date lastReportDtEnd){
        this.lastReportDtEnd = lastReportDtEnd;
    }
    /**
     * Integer字段：lastReportId  的查询条件
     */
    
    /* 查询条件：LastReportId  的开始区间  */
    private Integer lastReportIdStart;
    
    public Integer getLastReportIdStart(){
        return lastReportIdStart;
    }
    public void setLastReportIdStart(Integer lastReportIdStart){
        this.lastReportIdStart = lastReportIdStart;
    }
    
    /*  查询条件：lastReportId  的结束区间     */
    private Integer lastReportIdEnd;
    
    public Integer getLastReportIdEnd(){
        return lastReportIdEnd;
    }
    public void setLastReportIdEnd(Integer lastReportIdEnd){
        this.lastReportIdEnd = lastReportIdEnd;
    }
    
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("projectId",getProjectId())
            .append("projectIdStart",getProjectIdStart())
            .append("projectIdEnd",getProjectIdEnd())
            .append("wsCd",getWsCd())
            .append("projectNm",getProjectNm())
            .append("weight",getWeight())
            .append("contractStartDate",getContractStartDate())
            .append("contractStartDateStart",getContractStartDateStart())
            .append("contractStartDateEnd",getContractStartDateEnd())
            .append("startDate",getStartDate())
            .append("startDateStart",getStartDateStart())
            .append("startDateEnd",getStartDateEnd())
            .append("contractOtherReq",getContractOtherReq())
            .append("lastReportDt",getLastReportDt())
            .append("lastReportDtStart",getLastReportDtStart())
            .append("lastReportDtEnd",getLastReportDtEnd())
            .append("lastReportId",getLastReportId())
            .append("lastReportIdStart",getLastReportIdStart())
            .append("lastReportIdEnd",getLastReportIdEnd())
            .toString();
    }
    
}