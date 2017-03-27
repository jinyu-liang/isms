package com.is.pretrst.entity.query;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.is.pretrst.entity.DExProjectExt;

/**
 *
 * @ClassName: DExProject
 * @Description: DExProject表的对应的查询对象，增加Date/Double/int型字段的区间查询属性字段
 * @author 
 * @date 2013-09-10 10:26:07
 *
 */
 
public class DExProjectExtQuery extends DExProjectExt {

    private static final long serialVersionUID = 1L;

    
    
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("projectNm",getProjectNm())
            .append("weight",getWeight())
            .append("startDate",getStartDate())
            .append("lastReportDt",getLastReportDt())
            .append("lastReportId",getLastReportId())
            .append("progressStatus",getProgressStatus())
            .append("wsCd",getWsCd())
            .toString();
    }
    
}