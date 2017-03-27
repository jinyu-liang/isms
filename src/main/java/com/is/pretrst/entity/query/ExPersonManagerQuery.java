package com.is.pretrst.entity.query;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.is.pretrst.entity.ExPersonManager;

/**
 *
 * @ClassName: ExPersonManager
 * @Description: ExPersonManager表的对应的查询对象，增加Date/Double/int型字段的区间查询属性字段
 * @author 
 * @date 2013-09-11 17:24:48
 *
 */
 
public class ExPersonManagerQuery extends ExPersonManager {

    private static final long serialVersionUID = 1L;

    
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("reportId",getReportId())
            .append("workCenterId",getWorkCenterId())
            .append("title",getTitle())
            .append("teamCd",getTeamCd())
            .append("reportUserCd",getReportUserCd())
            .append("reportUserName",getReportUserName())
            .append("reportTm",getReportTm())
            .append("memo",getMemo())
            .append("processUserCd",getProcessUserCd())
            .append("processTm",getProcessTm())
            .append("dealComment",getDealComment())
            .append("statusCd",getStatusCd())
            .toString();
    }
    
}