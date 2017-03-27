package com.is.pretrst.entity.query;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.is.pretrst.entity.ExPersonInfo;

/**
 *
 * @ClassName: ExPersonInfo
 * @Description: ExPersonInfo表的对应的查询对象，增加Date/Double/int型字段的区间查询属性字段
 * @author 
 * @date 2013-12-13 16:28:10
 *
 */
 
public class ExPersonInfoQuery extends ExPersonInfo {

    private static final long serialVersionUID = 1L;

    /**
     * Date字段：updateDate  的查询条件
     */
    
    /* 查询条件：UpdateDate  的开始区间  */
    private Date updateDateStart;
    
    public Date getUpdateDateStart(){
        return updateDateStart;
    }
    public void setUpdateDateStart(Date updateDateStart){
        this.updateDateStart = updateDateStart;
    }
    
    /*  查询条件：updateDate  的结束区间     */
    private Date updateDateEnd;
    
    public Date getUpdateDateEnd(){
        return updateDateEnd;
    }
    public void setUpdateDateEnd(Date updateDateEnd){
        this.updateDateEnd = updateDateEnd;
    }
    
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("reportId",getReportId())
            .append("name",getName())
            .append("identyCardCode",getIdentyCardCode())
            .append("telephone",getTelephone())
            .append("workType",getWorkType())
            .append("workTypeName",getWorkTypeName())
            .append("statusCd",getStatusCd())
            .append("wsCd",getWsCd())
            .append("wsNm",getWsNm())
            .append("updateDate",getUpdateDate())
            .append("updateDateStart",getUpdateDateStart())
            .append("updateDateEnd",getUpdateDateEnd())
            .append("flag",getFlag())
            .append("teamName",getTeamName())
            .append("teamId",getTeamId())
            .toString();
    }
    
}