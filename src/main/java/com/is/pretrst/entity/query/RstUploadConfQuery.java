package com.is.pretrst.entity.query;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.is.pretrst.entity.RstUploadConf;

/**
 *
 * @ClassName: RstUploadConf
 * @Description: RstUploadConf表的对应的查询对象，增加Date/Double/int型字段的区间查询属性字段
 * @author 
 * @date 2013-10-26 12:44:20
 *
 */
 
public class RstUploadConfQuery extends RstUploadConf {

    private static final long serialVersionUID = 1L;

    /**
     * Integer字段：sizeConf  的查询条件
     */
    
    /* 查询条件：SizeConf  的开始区间  */
    private Integer sizeConfStart;
    
    public Integer getSizeConfStart(){
        return sizeConfStart;
    }
    public void setSizeConfStart(Integer sizeConfStart){
        this.sizeConfStart = sizeConfStart;
    }
    
    /*  查询条件：sizeConf  的结束区间     */
    private Integer sizeConfEnd;
    
    public Integer getSizeConfEnd(){
        return sizeConfEnd;
    }
    public void setSizeConfEnd(Integer sizeConfEnd){
        this.sizeConfEnd = sizeConfEnd;
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
            .append("fileType",getFileType())
            .append("sizeConf",getSizeConf())
            .append("sizeConfStart",getSizeConfStart())
            .append("sizeConfEnd",getSizeConfEnd())
            .append("statusCd",getStatusCd())
            .append("operUserId",getOperUserId())
            .append("operUserName",getOperUserName())
            .append("operTime",getOperTime())
            .append("operTimeStart",getOperTimeStart())
            .append("operTimeEnd",getOperTimeEnd())
            .toString();
    }
    
}