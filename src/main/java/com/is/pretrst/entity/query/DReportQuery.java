package com.is.pretrst.entity.query;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.is.pretrst.entity.DReport;

/**
 *
 * @ClassName: DReport
 * @Description: DReport表的对应的查询对象，增加Date/Double/int型字段的区间查询属性字段
 * @author 
 * @date 2013-09-11 17:24:27
 *
 */
 
public class DReportQuery extends DReport {

    private static final long serialVersionUID = 1L;

    /**
     * Integer字段：number  的查询条件
     */
    
    /* 查询条件：Number  的开始区间  */
    private Integer numberStart;
    
    public Integer getNumberStart(){
        return numberStart;
    }
    public void setNumberStart(Integer numberStart){
        this.numberStart = numberStart;
    }
    
    /*  查询条件：number  的结束区间     */
    private Integer numberEnd;
    
    public Integer getNumberEnd(){
        return numberEnd;
    }
    public void setNumberEnd(Integer numberEnd){
        this.numberEnd = numberEnd;
    }
    
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("reportId",getReportId())
            .append("title",getTitle())
            .append("projectCode",getProjectCode())
            .append("projName",getProjName())
            .append("unitPrice",getUnitPrice())
            .append("amount",getAmount())
            .append("number",getNumber())
            .append("numberStart",getNumberStart())
            .append("numberEnd",getNumberEnd())
            .append("memo",getMemo())
            .append("reportUserCd",getReportUserCd())
            .append("reportTm",getReportTm())
            .append("processUserCd",getProcessUserCd())
            .append("processTm",getProcessTm())
            .append("statusCd",getStatusCd())
            .append("verifiedUserCd",getVerifiedUserCd())
            .append("verifiedHeadTm",getVerifiedHeadTm())
            .append("verifiedHeadMemo",getVerifiedHeadMemo())
            .append("verifiedHeadStatus",getVerifiedHeadStatus())
            .toString();
    }
    
}