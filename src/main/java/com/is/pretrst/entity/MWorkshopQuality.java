package com.is.pretrst.entity;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.base.mybatis.AbstractBaseEntity;

public class MWorkshopQuality extends AbstractBaseEntity{
	
	 private static final long serialVersionUID = 1L;
	 
	 public String pid;
	 
	 /* 身份证号 */
	 public String fquserid;
		
		/* 姓名 */
	 public String fqusername;
	
	/* 电话号码 */
	 public String ws_cd;
	
	/* 工作类型 */
	 public String ws_nm;
	
	/* 工作类型名称 */
	 public String div_cd;
	
	 
	 public String zgattchid;
	
	/* 工程中心编码 */
	 public String fqdesc;
	
	/* 工地中心名称 */
	 public String zgdesc;

	/* 申请工程中心编码 */
	 public String zgusername;
	
	/* 申请工地中心名称 */
	 public String zguserid;
	
	/* 更新日期 */
	 public Date zgaddtime;
	 
	 /* 更新日期 */
	 public Date fqaddtime;
	
	/* 施工队名 */
	 public String remak;

	 /* 发起图片id */
	 public String  fqattchid;
	 
	 /* 处理状态 */
	 public String fqstatus;
	 
	 /* 整改计划完成时间 */
	 public Date zgjhfinishtime;
	 
	 /* 整改部门确认完成时间 */
	 public Date zgfinishtime;
	 
	 /* 整改是否超过计划完成时间 */
	 public String isovertime;
	 
	 /* 监理确认完成时间*/
	 public Date jlqrfinishtime;
	 
	 /* */
	 public Date fqrqrfinishtime;
	 
	 /* 发起人要求完成时间*/
	 public Date fqyqfinishtime;

//	 private List<DContractImage> contractImage;
	 
	 // 整改确认人ID
	 public String zgfinishuserid;
	 
	 // 整改确认人姓名
	 public String zgfinishusername;
	 
	 // 整改人完成处理说明
	 public String zgfinishdesc;
	 

	// 监理待办人ID，可以多个
	 public String jluserid;
	 
	// 监理处理人姓名
	 public String jlusername;
	 
	// 监理完成人ID
	 public String jlfinishuerid;
	 
	// 监理完成人姓名
	 public String jlfinishusername;
		 
	// 监理处理人描述
	 public String jlDesc;
	 
	// 发起人确认完成描述
	 public String fqrqdesc;
	 
	 
	 //处理步骤
	 public String dealType;

	 
	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
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

	public String getFqattchid() {
		return fqattchid;
	}

	public void setFqattchid(String fqattchid) {
		this.fqattchid = fqattchid;
	}

	public String getWs_nm() {
		return ws_nm;
	}

	public void setWs_nm(String ws_nm) {
		this.ws_nm = ws_nm;
	}

	public String getDiv_cd() {
		return div_cd;
	}

	public void setDiv_cd(String div_cd) {
		this.div_cd = div_cd;
	}
	
	public String getZgattchid() {
		return zgattchid;
	}

	public void setZgattchid(String zgattchid) {
		this.zgattchid = zgattchid;
	}

	public String getFqdesc() {
		return fqdesc;
	}

	public void setFqdesc(String fqdesc) {
		this.fqdesc = fqdesc;
	}

	public String getZgdesc() {
		return zgdesc;
	}

	public void setZgdesc(String zgdesc) {
		this.zgdesc = zgdesc;
	}

	public String getZgusername() {
		return zgusername;
	}

	public void setZgusername(String zgusername) {
		this.zgusername = zgusername;
	}

	public String getZguserid() {
		return zguserid;
	}

	public void setZguserid(String zguserid) {
		this.zguserid = zguserid;
	}

	public Date getZgaddtime() {
		return zgaddtime;
	}

	public void setZgaddtime(Date zgaddtime) {
		this.zgaddtime = zgaddtime;
	}

	public Date getFqaddtime() {
		return fqaddtime;
	}

	public void setFqaddtime(Date fqaddtime) {
		this.fqaddtime = fqaddtime;
	}


	 
	 
    public String getRemak() {
		return remak;
	}

	public void setRemak(String remak) {
		this.remak = remak;
	}

	public String getFqstatus() {
		return fqstatus;
	}

	public void setFqstatus(String fqstatus) {
		this.fqstatus = fqstatus;
	}

	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("pid",getPid())
            .append("fquserid",getFquserid())
            .append("fqusername",getFqusername())
            .append("ws_cd",getWs_cd())
            .append("ws_nm",getWs_nm())
            .append("fqattchid",getFqattchid())
            .append("zgattchid",getZgattchid())
            .append("fqdesc",getFqdesc())
            .append("zgdesc",getZgdesc())
            .append("zgusername",getZgusername())
            .append("zguserid",getZguserid())
            .append("zgaddtime",getZgaddtime())
            .append("fqaddtime",getFqaddtime())
            .append("fqstatus",getFqstatus())
            .append("remak",getRemak())
            .append("zgjhfinishtime",this.getZgjhfinishtime())
            .append("zgfinishtime",this.getZgfinishtime())
            .append("isovertime",this.getIsovertime())
            .append("jlqrfinishtime",this.getJlqrfinishtime())
            .append("fqrqrfinishtime",this.getFqrqrfinishtime())
            .append("fqyqfinishtime",this.getFqyqfinishtime())
            .append("zgfinishuserid",this.getZgfinishuserid())
            .append("zgfinishusername",this.getZgfinishusername())
            .append("zgfinishdesc",this.getZgfinishdesc())
            .append("fqrqdesc",this.getFqrqdesc())
            
            
            .toString();
    }
	 
//    public void setContractImage(List<DContractImage> contractImage)
//    {
//        this.contractImage = contractImage;
//    }
//
//    public List<DContractImage> getContractImage()
//    {
//        return contractImage;
//    }

	public Date getZgjhfinishtime() {
		return zgjhfinishtime;
	}

	public void setZgjhfinishtime(Date zgjhfinishtime) {
		this.zgjhfinishtime = zgjhfinishtime;
	}

	public Date getZgfinishtime() {
		return zgfinishtime;
	}

	public void setZgfinishtime(Date zgfinishtime) {
		this.zgfinishtime = zgfinishtime;
	}

	public String getIsovertime() {
		return isovertime;
	}

	public void setIsovertime(String isovertime) {
		this.isovertime = isovertime;
	}

	public Date getJlqrfinishtime() {
		return jlqrfinishtime;
	}

	public void setJlqrfinishtime(Date jlqrfinishtime) {
		this.jlqrfinishtime = jlqrfinishtime;
	}

	public Date getFqrqrfinishtime() {
		return fqrqrfinishtime;
	}

	public void setFqrqrfinishtime(Date fqrqrfinishtime) {
		this.fqrqrfinishtime = fqrqrfinishtime;
	}

	public Date getFqyqfinishtime() {
		return fqyqfinishtime;
	}

	public void setFqyqfinishtime(Date fqyqfinishtime) {
		this.fqyqfinishtime = fqyqfinishtime;
	}

	public String getZgfinishuserid() {
		return zgfinishuserid;
	}

	public void setZgfinishuserid(String zgfinishuserid) {
		this.zgfinishuserid = zgfinishuserid;
	}

	public String getZgfinishusername() {
		return zgfinishusername;
	}

	public void setZgfinishusername(String zgfinishusername) {
		this.zgfinishusername = zgfinishusername;
	}

	public String getZgfinishdesc() {
		return zgfinishdesc;
	}

	public void setZgfinishdesc(String zgfinishdesc) {
		this.zgfinishdesc = zgfinishdesc;
	}

	public String getJluserid() {
		return jluserid;
	}

	public void setJluserid(String jluserid) {
		this.jluserid = jluserid;
	}

	public String getJlusername() {
		return jlusername;
	}

	public void setJlusername(String jlusername) {
		this.jlusername = jlusername;
	}

	public String getJlfinishuerid() {
		return jlfinishuerid;
	}

	public void setJlfinishuerid(String jlfinishuerid) {
		this.jlfinishuerid = jlfinishuerid;
	}

	public String getJlfinishusername() {
		return jlfinishusername;
	}

	public void setJlfinishusername(String jlfinishusername) {
		this.jlfinishusername = jlfinishusername;
	}

	public String getJlDesc() {
		return jlDesc;
	}

	public void setJlDesc(String jlDesc) {
		this.jlDesc = jlDesc;
	}

	public String getFqrqdesc() {
		return fqrqdesc;
	}

	public void setFqrqdesc(String fqrqdesc) {
		this.fqrqdesc = fqrqdesc;
	}

	public String getDealType() {
		return dealType;
	}

	public void setDealType(String dealType) {
		this.dealType = dealType;
	}
    
}
