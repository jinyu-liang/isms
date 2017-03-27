package com.is.pretrst.entity;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.base.mybatis.AbstractBaseEntity;

public class MWorkshopJoblog extends AbstractBaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	public String ID;
	public String fquserid;
	public String fqusername;
	public String ws_cd;
	public String ws_name;
	public Date accepttime;
	public String wheather;
	public Date starttime;
	public Date endtime;
	public String jobcontent;
	public String schedule;
	public String Isplan;
	public String daySituation;
	public String nightSituation;
	public String qualitySituation;
	public String qualityMeasures;
	public String SecuritySituation;
	public String SecurityMeasures;
	public String materialSituation;
	public String materialMeasures;
	public String DrawingSituation;
	public String DrawingMeasures;
	public String VisaSituation;
	public String VisaMeasures;
	public String OtherSituation;
	public String problemTrack;
	public String status;
	
	public Date start_date;
	public Date end_date;

	public Integer pageNumber;
	public Integer pageSize;

	



	public int getPageNumber() {
		return pageNumber;
	}




	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}




	public Integer getPageSize() {
		return pageSize;
	}




	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}




	public Date getStart_date() {
		return start_date;
	}




	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}




	public Date getEnd_date() {
		return end_date;
	}




	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}




	public String getID() {
		return ID;
	}




	public void setID(String iD) {
		ID = iD;
	}




	public String getFquserid() {
		return fquserid;
	}




	public void setFquserid(String fquserid) {
		this.fquserid = fquserid;
	}




	public String getFqusername() {
		return fqusername;
	}




	public void setFqusername(String fqusername) {
		this.fqusername = fqusername;
	}




	public String getWs_cd() {
		return ws_cd;
	}




	public void setWs_cd(String ws_cd) {
		this.ws_cd = ws_cd;
	}




	public String getWs_name() {
		return ws_name;
	}




	public void setWs_name(String ws_name) {
		this.ws_name = ws_name;
	}




	public Date getAccepttime() {
		return accepttime;
	}




	public void setAccepttime(Date accepttime) {
		this.accepttime = accepttime;
	}




	public String getWheather() {
		return wheather;
	}




	public void setWheather(String wheather) {
		this.wheather = wheather;
	}




	public Date getStarttime() {
		return starttime;
	}




	public void setStarttime(Date starttime) {
		this.starttime = starttime;
	}




	public Date getEndtime() {
		return endtime;
	}




	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}




	public String getJobcontent() {
		return jobcontent;
	}




	public void setJobcontent(String jobcontent) {
		this.jobcontent = jobcontent;
	}




	public String getSchedule() {
		return schedule;
	}




	public void setSchedule(String schedule) {
		this.schedule = schedule;
	}




	public String getIsplan() {
		return Isplan;
	}




	public void setIsplan(String isplan) {
		Isplan = isplan;
	}




	public String getDaySituation() {
		return daySituation;
	}




	public void setDaySituation(String daySituation) {
		this.daySituation = daySituation;
	}




	public String getNightSituation() {
		return nightSituation;
	}




	public void setNightSituation(String nightSituation) {
		this.nightSituation = nightSituation;
	}




	public String getQualitySituation() {
		return qualitySituation;
	}




	public void setQualitySituation(String qualitySituation) {
		this.qualitySituation = qualitySituation;
	}




	public String getQualityMeasures() {
		return qualityMeasures;
	}




	public void setQualityMeasures(String qualityMeasures) {
		this.qualityMeasures = qualityMeasures;
	}




	public String getSecuritySituation() {
		return SecuritySituation;
	}




	public void setSecuritySituation(String securitySituation) {
		SecuritySituation = securitySituation;
	}




	public String getSecurityMeasures() {
		return SecurityMeasures;
	}




	public void setSecurityMeasures(String securityMeasures) {
		SecurityMeasures = securityMeasures;
	}




	public String getMaterialSituation() {
		return materialSituation;
	}




	public void setMaterialSituation(String materialSituation) {
		this.materialSituation = materialSituation;
	}




	public String getMaterialMeasures() {
		return materialMeasures;
	}




	public void setMaterialMeasures(String materialMeasures) {
		this.materialMeasures = materialMeasures;
	}




	public String getDrawingSituation() {
		return DrawingSituation;
	}




	public void setDrawingSituation(String drawingSituation) {
		DrawingSituation = drawingSituation;
	}




	public String getDrawingMeasures() {
		return DrawingMeasures;
	}




	public void setDrawingMeasures(String drawingMeasures) {
		DrawingMeasures = drawingMeasures;
	}




	public String getVisaSituation() {
		return VisaSituation;
	}




	public void setVisaSituation(String visaSituation) {
		VisaSituation = visaSituation;
	}




	public String getVisaMeasures() {
		return VisaMeasures;
	}




	public void setVisaMeasures(String visaMeasures) {
		VisaMeasures = visaMeasures;
	}




	public String getOtherSituation() {
		return OtherSituation;
	}




	public void setOtherSituation(String otherSituation) {
		OtherSituation = otherSituation;
	}




	public String getProblemTrack() {
		return problemTrack;
	}




	public void setProblemTrack(String problemTrack) {
		this.problemTrack = problemTrack;
	}




	public String getStatus() {
		return status;
	}




	public void setStatus(String status) {
		this.status = status;
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
        .toString();
    }
	
	
}
