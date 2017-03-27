package com.is.pretrst.entity.query;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.is.pretrst.entity.DExItem;

/**
 *
 * @ClassName: DExItem
 * @Description: DExItem表的对应的查询对象，增加Date/Double/int型字段的区间查询属性字段
 * @author 
 * @date 2013-09-10 10:26:01
 *
 */
 
public class DExItemQuery extends DExItem {

    private static final long serialVersionUID = 1L;

    /**
     * Integer字段：itemId  的查询条件
     */
    
    /* 查询条件：ItemId  的开始区间  */
    private Integer itemIdStart;
    
    public Integer getItemIdStart(){
        return itemIdStart;
    }
    public void setItemIdStart(Integer itemIdStart){
        this.itemIdStart = itemIdStart;
    }
    
    /*  查询条件：itemId  的结束区间     */
    private Integer itemIdEnd;
    
    public Integer getItemIdEnd(){
        return itemIdEnd;
    }
    public void setItemIdEnd(Integer itemIdEnd){
        this.itemIdEnd = itemIdEnd;
    }
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
    
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("itemId",getItemId())
            .append("itemIdStart",getItemIdStart())
            .append("itemIdEnd",getItemIdEnd())
            .append("projectId",getProjectId())
            .append("projectIdStart",getProjectIdStart())
            .append("projectIdEnd",getProjectIdEnd())
            .append("itemNm",getItemNm())
            .append("amount",getAmount())
            .append("memo",getMemo())
            .toString();
    }
    
}