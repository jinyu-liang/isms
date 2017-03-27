package com.is.pretrst.entity.query;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.is.pretrst.entity.MTransItem;

/**
 *
 * @ClassName: MTransItem
 * @Description: MTransItem表的对应的查询对象，增加Date/Double/int型字段的区间查询属性字段
 * @author 
 * @date 2013-10-31 18:27:50
 *
 */
 
public class MTransItemQuery extends MTransItem {

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
    
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("itemId",getItemId())
            .append("transId",getTransId())
            .append("wsCd",getWsCd())
            .append("categoryCd",getCategoryCd())
            .append("materialCd",getMaterialCd())
            .append("modelNo",getModelNo())
            .append("totalAmount",getTotalAmount())
            .append("totalAmountStart",getTotalAmountStart())
            .append("totalAmountEnd",getTotalAmountEnd())
            .append("unit",getUnit())
            .append("usePlace",getUsePlace())
            .append("materialNm",getMaterialNm())
            .append("categoryNm",getCategoryNm())
            .append("memo",getMemo())
            .toString();
    }
    
}