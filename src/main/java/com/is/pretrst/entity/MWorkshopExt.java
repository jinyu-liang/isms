package com.is.pretrst.entity;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.base.mybatis.AbstractBaseEntity;

/**
 * 
 * @ClassName: MWorkshop
 * @Description: MWorkshop表的对应的java对象，该文件为工具自动生成，请勿手工修改!
 * @author 
 * @date 2013-09-10 10:26:59
 * 
 */
public class MWorkshopExt extends AbstractBaseEntity {

	private static final long serialVersionUID = 1L;

	/* 工程中心编码 */
	private String wsCd;

	/* 工地中心名称 */
	private String wsNm;

	/* 开始时间 */
	private Date beginTime;

	/* 结束时间 */
	private Date endTime;

	private Date newtime;

	private String flag;
	
	private String managerUserId;
	
	 /* 部门编码 */
    private String               divCd;

	public String getWsCd() {
		return this.wsCd;
	}

	public Date getNewtime() {
		return newtime;
	}

	public void setNewtime(Date newtime) {
		this.newtime = newtime;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public void setWsCd(String wsCd) {
		this.wsCd = wsCd;
	}

	public String getWsNm() {
		return this.wsNm;
	}

	public void setWsNm(String wsNm) {
		this.wsNm = wsNm;
	}

	public Date getBeginTime() {
		return this.beginTime;
	}

	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}

	public Date getEndTime() {
		return this.endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public String getManagerUserId() {
		return managerUserId;
	}

	public void setManagerUserId(String managerUserId) {
		this.managerUserId = managerUserId;
	}

	public String getDivCd() {
		return divCd;
	}

	public void setDivCd(String divCd) {
		this.divCd = divCd;
	}

	/**
	 * toString方法
	 */
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("wsCd", getWsCd()).append("wsNm", getWsNm())
				.append("beginTime", getBeginTime())
				.append("divCd", getDivCd())
				.append("endTime", getEndTime()).toString();
	}

}