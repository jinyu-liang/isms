package com.is.pretrst.entity.query;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.is.pretrst.entity.DRecordReply;

/**
 *
 * @ClassName: DRecordReply
 * @Description: DRecordReply表的对应的查询对象，增加Date/Double/int型字段的区间查询属性字段
 * @author 
 * @date 2013-09-10 10:26:34
 *
 */
 
public class DRecordReplyQuery extends DRecordReply {

    private static final long serialVersionUID = 1L;

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
            .append("replyId",getReplyId())
            .append("recordId",getRecordId())
            .append("replyType",getRecordType())
            .append("rootReplyId",getRootReplyId())
            .append("parentReplyId",getParentReplyId())
            .append("title",getTitle())
            .append("content",getContent())
            .append("replyUserCd",getReplyUserCd())
            .append("createTm",getCreateTm())
            .append("createTmStart",getCreateTmStart())
            .append("createTmEnd",getCreateTmEnd())
            .append("updateTm",getUpdateTm())
            .append("updateTmStart",getUpdateTmStart())
            .append("updateTmEnd",getUpdateTmEnd())
            .append("extraFlg",getExtraFlg())
            .append("deleteCd",getDeleteCd())
            .append("replyUserNm",getReplyUserNm())
            .toString();
    }
    
}