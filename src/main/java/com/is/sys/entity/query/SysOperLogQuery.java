package com.is.sys.entity.query;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.is.sys.entity.SysOperLog;

/**
 *
 * @ClassName: SysOperLog
 * @Description: SysOperLog表的对应的查询对象，增加Date/Double/int型字段的区间查询属性字段
 * @author 
 * @date 2013-02-16 17:31:20
 *
 */
 
public class SysOperLogQuery extends SysOperLog {

    private static final long serialVersionUID = 1L;

    /**
     * Date字段：operateTime  的查询条件
     */
    
    /* 查询条件：OperateTime  的开始区间  */
    private Date operateTimeStart;
    
    public Date getOperateTimeStart(){
        return operateTimeStart;
    }
    public void setOperateTimeStart(Date operateTimeStart){
        this.operateTimeStart = operateTimeStart;
    }
    
    /*  查询条件：operateTime  的结束区间     */
    private Date operateTimeEnd;
    
    public Date getOperateTimeEnd(){
        return operateTimeEnd;
    }
    public void setOperateTimeEnd(Date operateTimeEnd){
        this.operateTimeEnd = operateTimeEnd;
    }
    
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("logId",getLogId())
            .append("busiId",getBusiId())
            .append("modelId",getModelId())
            .append("modelName",getModelName())
            .append("operFuncCode",getOperFuncCode())
            .append("operFuncName",getOperFuncName())
            .append("operResult",getOperResult())
            .append("operateTime",getOperateTime())
            .append("operateTimeStart",getOperateTimeStart())
            .append("operateTimeEnd",getOperateTimeEnd())
            .append("userId",getUserId())
            .append("name",getName())
            .append("operIp",getOperIp())
            .append("note",getNote())
            .toString();
    }
    
}