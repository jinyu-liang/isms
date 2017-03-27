package com.is.pretrst.entity.query;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.is.pretrst.entity.ExWorkType;

/**
 *
 * @ClassName: ExWorkType
 * @Description: ExWorkType表的对应的查询对象，增加Date/Double/int型字段的区间查询属性字段
 * @author 
 * @date 2013-12-18 15:07:37
 *
 */
 
public class ExWorkTypeQuery extends ExWorkType {

    private static final long serialVersionUID = 1L;

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
    /**
     * Date字段：operTime  的查询条件
     */
    
    /* 查询条件：OperTime  的开始区间  */
    private Date operTimeStart;
    
    public Date getOperTimeStart(){
        return operTimeStart;
    }
    public void setOperTimeStart(Date operTimeStart){
        this.operTimeStart = operTimeStart;
    }
    
    /*  查询条件：operTime  的结束区间     */
    private Date operTimeEnd;
    
    public Date getOperTimeEnd(){
        return operTimeEnd;
    }
    public void setOperTimeEnd(Date operTimeEnd){
        this.operTimeEnd = operTimeEnd;
    }
    
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("teamCd",getWorkCd())
            .append("teamNm",getWorkNm())
            .append("workerCountStart",getWorkerCountStart())
            .append("workerCountEnd",getWorkerCountEnd())
            .append("operUserId",getOperUserId())
            .append("operUserName",getOperUserName())
            .append("operTime",getOperTime())
            .append("operTimeStart",getOperTimeStart())
            .append("operTimeEnd",getOperTimeEnd())
            .toString();
    }
    
}