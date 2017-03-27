package com.is.pretrst.entity.query;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.is.pretrst.entity.MWorkshopItem;

/**
 *
 * @ClassName: MWorkshopItem
 * @Description: MWorkshopItem表的对应的查询对象，增加Date/Double/int型字段的区间查询属性字段
 * @author 
 * @date 2013-10-31 18:27:43
 *
 */
 
public class MWorkshopItemQuery extends MWorkshopItem {

    private static final long serialVersionUID = 1L;

    /**
     * Integer字段：totalAmount  的查询条件
     */
    
    /* 查询条件：TotalAmount  的开始区间  */
    private Integer totalAmountStart;
    
    public Integer getTotalAmountStart(){
        return totalAmountStart;
    }
    public void setTotalAmountStart(Integer totalAmountStart){
        this.totalAmountStart = totalAmountStart;
    }
    
    /*  查询条件：totalAmount  的结束区间     */
    private Integer totalAmountEnd;
    
    public Integer getTotalAmountEnd(){
        return totalAmountEnd;
    }
    public void setTotalAmountEnd(Integer totalAmountEnd){
        this.totalAmountEnd = totalAmountEnd;
    }
    /**
     * Integer字段：remainNumber  的查询条件
     */
    
    /* 查询条件：RemainNumber  的开始区间  */
    private Integer remainNumberStart;
    
    public Integer getRemainNumberStart(){
        return remainNumberStart;
    }
    public void setRemainNumberStart(Integer remainNumberStart){
        this.remainNumberStart = remainNumberStart;
    }
    
    /*  查询条件：remainNumber  的结束区间     */
    private Integer remainNumberEnd;
    
    public Integer getRemainNumberEnd(){
        return remainNumberEnd;
    }
    public void setRemainNumberEnd(Integer remainNumberEnd){
        this.remainNumberEnd = remainNumberEnd;
    }
    
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("itemId",getItemId())
            .append("wsCd",getWsCd())
            .append("categoryCd",getCategoryCd())
            .append("materialCd",getMaterialCd())
            .append("modelNo",getModelNo())
            .append("totalAmount",getTotalAmount())
            .append("totalAmountStart",getTotalAmountStart())
            .append("totalAmountEnd",getTotalAmountEnd())
            .append("remainNumber",getRemainNumber())
            .append("remainNumberStart",getRemainNumberStart())
            .append("remainNumberEnd",getRemainNumberEnd())
            .append("unit",getUnit())
            .append("usePlace",getUsePlace())
            .append("materialNm",getMaterialNm())
            .append("categoryNm",getCategoryNm())
            .append("memo",getMemo())
            .toString();
    }
    
}