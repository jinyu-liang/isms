package com.is.sys.entity.query;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.is.sys.entity.SysAttach;

/**
 *
 * @ClassName: BzzcAttach
 * @Description: BzzcAttach表的对应的查询对象，增加Date/Double/int型字段的区间查询属性字段
 * @author 
 * @date 2012-01-29 16:57:19
 *
 */
 
public class SysAttachQuery extends SysAttach {

    private static final long serialVersionUID = 1L;

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
    /**
     * Date字段：finalUpdateTime  的查询条件
     */
    
    /* 查询条件：FinalUpdateTime  的开始区间  */
    private Date finalUpdateTimeStart;
    
    public Date getFinalUpdateTimeStart(){
        return finalUpdateTimeStart;
    }
    public void setFinalUpdateTimeStart(Date finalUpdateTimeStart){
        this.finalUpdateTimeStart = finalUpdateTimeStart;
    }
    
    /*  查询条件：finalUpdateTime  的结束区间     */
    private Date finalUpdateTimeEnd;
    
    public Date getFinalUpdateTimeEnd(){
        return finalUpdateTimeEnd;
    }
    public void setFinalUpdateTimeEnd(Date finalUpdateTimeEnd){
        this.finalUpdateTimeEnd = finalUpdateTimeEnd;
    }
    
    private String keyWords;
    
    public String getKeyWords() {
        return keyWords;
    }
    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("attachId",getAttachId())
            .append("attachName",getAttachName())
            .append("attachPath",getAttachPath())
            .append("fileType",getFileType())
            .append("fileSize",getFileSize())
            .append("operUserId",getOperUserId())
            .append("operName",getOperName())
            .append("operTime",getOperTime())
            .append("operTimeStart",getOperTimeStart())
            .append("operTimeEnd",getOperTimeEnd())
            .append("attachState",getAttachState())
            .append("attachDesc",getAttachDesc())
            .append("finalUpdateTimeStart",getFinalUpdateTimeStart())
            .append("finalUpdateTimeEnd",getFinalUpdateTimeEnd())
            .append("modelCode",getModelCode())
            .toString();
    }
    
}