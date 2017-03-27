package com.is.pretrst.entity;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.base.mybatis.AbstractBaseEntity;

public class MWorkshopContact extends AbstractBaseEntity {
	
	private static final long serialVersionUID = 1L;
	
	public String ID;
	public String ws_cd;
	public String ws_nm;
	public String fquserid;
	public String fqusername;
	public String title;
	public String detail;
	public Date addtime;
	public String acceptdepname;
	public String acceptdepID;
	public String senderdepname;
	public String senderdepID;
	public String acceptdepmangername;
	public String acceptdepmangerID;
	public String acceptdepmangerdesc;
	public Date acceptdepmangertime;
	public String leadername;
	public String leaderID;
	public String leaderdesc;
	public Date leadertime;
	public String Isaccept;
	public Date accepttime;
	public String acceptdesc;
	public String status;
	public String remark;
	public Integer pageNumber;
	public Integer pageSize;
	public String acceptuserID;
	public String acceptusername;
	
	
	
	
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
	public String getID() {
		return ID;
	}
	public void setID(String iD) {
		ID = iD;
	}
	public String getWs_cd() {
		return ws_cd;
	}
	public void setWs_cd(String ws_cd) {
		this.ws_cd = ws_cd;
	}
	public String getWs_nm() {
		return ws_nm;
	}
	public void setWs_nm(String ws_nm) {
		this.ws_nm = ws_nm;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public Date getAddtime() {
		return addtime;
	}
	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}
	public String getAcceptdepname() {
		return acceptdepname;
	}
	public void setAcceptdepname(String acceptdepname) {
		this.acceptdepname = acceptdepname;
	}
	public String getAcceptdepID() {
		return acceptdepID;
	}
	public void setAcceptdepID(String acceptdepID) {
		this.acceptdepID = acceptdepID;
	}
	public String getSenderdepname() {
		return senderdepname;
	}
	public void setSenderdepname(String senderdepname) {
		this.senderdepname = senderdepname;
	}
	public String getSenderdepID() {
		return senderdepID;
	}
	public void setSenderdepID(String senderdepID) {
		this.senderdepID = senderdepID;
	}
	public String getAcceptdepmangername() {
		return acceptdepmangername;
	}
	public void setAcceptdepmangername(String acceptdepmangername) {
		this.acceptdepmangername = acceptdepmangername;
	}
	public String getAcceptdepmangerID() {
		return acceptdepmangerID;
	}
	public void setAcceptdepmangerID(String acceptdepmangerID) {
		this.acceptdepmangerID = acceptdepmangerID;
	}
	public String getAcceptdepmangerdesc() {
		return acceptdepmangerdesc;
	}
	public void setAcceptdepmangerdesc(String acceptdepmangerdesc) {
		this.acceptdepmangerdesc = acceptdepmangerdesc;
	}
	public Date getAcceptdepmangertime() {
		return acceptdepmangertime;
	}
	public void setAcceptdepmangertime(Date acceptdepmangertime) {
		this.acceptdepmangertime = acceptdepmangertime;
	}
	public String getLeadername() {
		return leadername;
	}
	public void setLeadername(String leadername) {
		this.leadername = leadername;
	}
	public String getLeaderID() {
		return leaderID;
	}
	public void setLeaderID(String leaderID) {
		this.leaderID = leaderID;
	}
	public String getLeaderdesc() {
		return leaderdesc;
	}
	public void setLeaderdesc(String leaderdesc) {
		this.leaderdesc = leaderdesc;
	}
	public Date getLeadertime() {
		return leadertime;
	}
	public void setLeadertime(Date leadertime) {
		this.leadertime = leadertime;
	}
	public String getIsaccept() {
		return Isaccept;
	}
	public void setIsaccept(String isaccept) {
		Isaccept = isaccept;
	}
	public Date getAccepttime() {
		return accepttime;
	}
	public void setAccepttime(Date accepttime) {
		this.accepttime = accepttime;
	}
	public String getAcceptdesc() {
		return acceptdesc;
	}
	public void setAcceptdesc(String acceptdesc) {
		this.acceptdesc = acceptdesc;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public String getAcceptuserID() {
		return acceptuserID;
	}
	public void setAcceptuserID(String acceptuserID) {
		this.acceptuserID = acceptuserID;
	}
	public String getAcceptusername() {
		return acceptusername;
	}
	public void setAcceptusername(String acceptusername) {
		this.acceptusername = acceptusername;
	}
	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
        .append("ID",this.getID())
        .append("ws_cd",this.getWs_cd())
        .append("ws_nm",this.getWs_nm())
        .append("fquserid",this.getFquserid())
        .append("fqusername",this.getFqusername())
        .append("title",this.getTitle())
        .append("detail",this.getDetail())
        .append("addtime",this.getAddtime())
        .append("acceptdepname",this.getAcceptdepname())
        .append("acceptdepID",this.getAcceptdepID())
        .append("senderdepname",this.getSenderdepname())
        .append("senderdepID",this.getSenderdepID())
        .append("acceptdepmangername",this.getAcceptdepmangername())
        .append("acceptdepmangerID",this.getAcceptdepmangerID())
        .append("acceptdepmangerdesc",this.getAcceptdepmangerdesc())
        .append("acceptdepmangertime",this.getAcceptdepmangertime())
        .append("leadername",this.getLeadername())
        .append("leaderID",this.getLeaderID())
        .append("leaderdesc",this.getLeaderdesc())
        .append("leadertime",this.getLeadertime())
        .append("Isaccept",this.getIsaccept())
        .append("accepttime",this.getAccepttime())
        .append("acceptdesc",this.getAcceptdesc())
        .append("status",this.getStatus())
        .append("remark",this.getRemark())
        .append("acceptuserID",this.getAcceptuserID())
        .append("acceptusername",this.getAcceptusername())
        .toString();
    }
	
	
}
