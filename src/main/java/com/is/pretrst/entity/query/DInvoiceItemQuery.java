package com.is.pretrst.entity.query;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.is.pretrst.entity.DInvoiceItem;

/**
 *
 * @ClassName: DInvoiceItem
 * @Description: DInvoiceItem表的对应的查询对象，增加Date/Double/int型字段的区间查询属性字段
 * @author 
 * @date 2013-09-10 10:26:16
 *
 */
 
public class DInvoiceItemQuery extends DInvoiceItem {

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
     * Integer字段：itemNo  的查询条件
     */
    
    /* 查询条件：ItemNo  的开始区间  */
    private Integer itemNoStart;
    
    public Integer getItemNoStart(){
        return itemNoStart;
    }
    public void setItemNoStart(Integer itemNoStart){
        this.itemNoStart = itemNoStart;
    }
    
    /*  查询条件：itemNo  的结束区间     */
    private Integer itemNoEnd;
    
    public Integer getItemNoEnd(){
        return itemNoEnd;
    }
    public void setItemNoEnd(Integer itemNoEnd){
        this.itemNoEnd = itemNoEnd;
    }
    /**
     * Integer字段：sendAmount  的查询条件
     */
    
    /* 查询条件：SendAmount  的开始区间  */
    private Integer sendAmountStart;
    
    public Integer getSendAmountStart(){
        return sendAmountStart;
    }
    public void setSendAmountStart(Integer sendAmountStart){
        this.sendAmountStart = sendAmountStart;
    }
    
    /*  查询条件：sendAmount  的结束区间     */
    private Integer sendAmountEnd;
    
    public Integer getSendAmountEnd(){
        return sendAmountEnd;
    }
    public void setSendAmountEnd(Integer sendAmountEnd){
        this.sendAmountEnd = sendAmountEnd;
    }
    /**
     * Integer字段：receivedAmount  的查询条件
     */
    
    /* 查询条件：ReceivedAmount  的开始区间  */
    private Integer receivedAmountStart;
    
    public Integer getReceivedAmountStart(){
        return receivedAmountStart;
    }
    public void setReceivedAmountStart(Integer receivedAmountStart){
        this.receivedAmountStart = receivedAmountStart;
    }
    
    /*  查询条件：receivedAmount  的结束区间     */
    private Integer receivedAmountEnd;
    
    public Integer getReceivedAmountEnd(){
        return receivedAmountEnd;
    }
    public void setReceivedAmountEnd(Integer receivedAmountEnd){
        this.receivedAmountEnd = receivedAmountEnd;
    }
    
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("itemId",getItemId())
            .append("itemIdStart",getItemIdStart())
            .append("itemIdEnd",getItemIdEnd())
            .append("invoiceId",getInvoiceId())
            .append("itemNo",getItemNo())
            .append("itemNoStart",getItemNoStart())
            .append("itemNoEnd",getItemNoEnd())
            .append("categoryCd",getCategoryCd())
            .append("materialCd",getMaterialCd())
            .append("modelNo",getModelNo())
            .append("identificationCd",getIdentificationCd())
            .append("sender",getSender())
            .append("receiver",getReceiver())
            .append("sendAmount",getSendAmount())
            .append("sendAmountStart",getSendAmountStart())
            .append("sendAmountEnd",getSendAmountEnd())
            .append("receivedAmount",getReceivedAmount())
            .append("receivedAmountStart",getReceivedAmountStart())
            .append("receivedAmountEnd",getReceivedAmountEnd())
            .append("unit",getUnit())
            .append("memo",getMemo())
            .append("statusCd",getStatusCd())
            .append("materialNm",getMaterialNm())
            .append("categoryNm",getCategoryNm())
            .toString();
    }
    
}