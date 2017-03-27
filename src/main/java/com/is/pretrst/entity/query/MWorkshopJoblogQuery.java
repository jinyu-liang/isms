package com.is.pretrst.entity.query;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.is.pretrst.entity.MWorkshopJoblog;


public class MWorkshopJoblogQuery extends MWorkshopJoblog {

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
        .append("ID",this.getID())
        .append("fquserid",this.getFquserid())
        .append("fqusername",this.getFqusername())
        .append("ws_cd",this.getWs_cd())
        .append("ws_name",this.getWs_name())
        .append("accepttime",this.getAccepttime())
        .append("wheather",this.getWheather())
        .append("starttime",this.getStarttime())
        .append("endtime",this.getEndtime())
        .append("jobcontent",this.getJobcontent())
        .append("schedule",this.getSchedule())
        .append("Isplan",this.getIsplan())
        .append("daySituation",this.getDaySituation())
        .append("nightSituation",this.getNightSituation())
        .append("qualitySituation",this.getQualitySituation())
        .append("qualityMeasures",this.getQualityMeasures())
        .append("SecuritySituation",this.getSecuritySituation())
        .append("SecurityMeasures",this.getSecurityMeasures())
        .append("materialSituation",this.getMaterialSituation())
        .append("materialMeasures",this.getMaterialMeasures())
        .append("DrawingSituation",this.getDrawingSituation())
        .append("DrawingMeasures",this.getDrawingMeasures())
        .append("VisaSituation",this.getVisaSituation())
        .append("VisaMeasures",this.getVisaMeasures())
        .append("OtherSituation",this.getOtherSituation())
        .append("problemTrack",this.getProblemTrack())
        .append("status",this.getStatus())
        .append("start_date",this.getStart_date())
        .append("end_date",this.getEnd_date())
        .append("pageNumber",this.getPageNumber())
        .append("pageSize",this.getPageSize())
        .append("numberStart",this.getNumberStart())
        .append("numberEnd",this.getNumberEnd())
        .toString();
    }
}
