package com.is.pretrst.entity.query;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.is.pretrst.entity.DExProgress;

/**
 *
 * @ClassName: DExProgress
 * @Description: DExProgress表的对应的查询对象，增加Date/Double/int型字段的区间查询属性字段
 * @author 
 * @date 2013-09-10 10:25:54
 *
 */
 
public class DExProgressQuery extends DExProgress {

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
     * Integer字段：workerCount  的查询条件
     */
    
    /* 查询条件：WorkerCount  的开始区间  */
    private Integer workerCountStart;
    
    public Integer getWorkerCountStart(){
        return workerCountStart;
    }
    public void setWorkerCountStart(Integer workerCountStart){
        this.workerCountStart = workerCountStart;
    }
    
    /*  查询条件：workerCount  的结束区间     */
    private Integer workerCountEnd;
    
    public Integer getWorkerCountEnd(){
        return workerCountEnd;
    }
    public void setWorkerCountEnd(Integer workerCountEnd){
        this.workerCountEnd = workerCountEnd;
    }
    
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("reportId",getReportId())
            .append("projectId",getProjectId())
            .append("projectIdStart",getProjectIdStart())
            .append("projectIdEnd",getProjectIdEnd())
            .append("progressStatus",getProgressStatus())
            .append("fbWorkshop",getFbWorkshop())
            .append("fbDelivery",getFbDelivery())
            .append("fbQuality",getFbQuality())
            .append("fbSecurity",getFbSecurity())
            .append("fbManner",getFbManner())
            .append("fbMmaterial",getFbMmaterial())
            .append("fbSmaterial",getFbSmaterial())
            .append("fbEquipment",getFbEquipment())
            .append("totalCost",getTotalCost())
            .append("currentCost",getCurrentCost())
            .append("totalExpense",getTotalExpense())
            .append("currentExpense",getCurrentExpense())
            .append("teamLeader",getTeamLeader())
            .append("welder",getWelder())
            .append("riveter",getRiveter())
            .append("workerCount",getWorkerCount())
            .append("workerCountStart",getWorkerCountStart())
            .append("workerCountEnd",getWorkerCountEnd())
            .append("viceManager",getViceManager())
            .append("wsLeader",getWsLeader())
            .append("memo",getMemo())
            .toString();
    }
    
}