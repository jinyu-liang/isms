package com.is.pretrst.entity;

import java.util.Date;

import com.base.mybatis.AbstractBaseEntity;

public class ExPersonInfoPay extends AbstractBaseEntity{
	
	 private static final long serialVersionUID = 1L;
	 
	 public String pid;
	 
	 /* 身份证号 */
	 public String identy_card_code;
		
		/* 姓名 */
	 public String name;
	
	/* 电话号码 */
	 public String telephone;
	
	/* 工作类型 */
	 public String work_type;
	
	/* 工作类型名称 */
	 public String work_type_name;
	
	/* 状态 */
	 public String work_count_type;
	 
	 public String work_pay;
	
	/* 工程中心编码 */
	 public String work_ws_cd;
	
	/* 工地中心名称 */
	 public String work_ws_nm;

	/* 申请工程中心编码 */
	 public String team_name;
	
	/* 申请工地中心名称 */
	 public String team_id;
	
	/* 更新日期 */
	 public Date addtime;
	 
	 /* 更新日期 */
	 public Date start_time_am;
	 
	 /* 更新日期 */
	 public Date end_time_am;
	 
	 /* 更新日期 */
	 public Date start_time_pm;
	 
	 /* 更新日期 */
	 public Date end_time_pm;
	 
	 /* 更新日期 */
	 public Date start_time_other;
	 /* 更新日期 */
	 public Date end_time_other;
	 
	 /* 申请工地中心名称 */
	 public String work_timer;
	
	/* 标志 */
	 public String work_status;
	
	/* 施工队名 */
	 public String remark;
	
	/* 施工队编号 */
	 public String dayormonth;
	
		
	public String bank_card;
		
	public String bank_card_name;



	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getWork_type() {
		return work_type;
	}

	public void setWork_type(String work_type) {
		this.work_type = work_type;
	}

	public String getWork_type_name() {
		return work_type_name;
	}

	public void setWork_type_name(String work_type_name) {
		this.work_type_name = work_type_name;
	}

	public String getWork_count_type() {
		return work_count_type;
	}

	public void setWork_count_type(String work_count_type) {
		this.work_count_type = work_count_type;
	}

	public String getWork_pay() {
		return work_pay;
	}

	public void setWork_pay(String work_pay) {
		this.work_pay = work_pay;
	}

	public String getWork_ws_cd() {
		return work_ws_cd;
	}

	public void setWork_ws_cd(String work_ws_cd) {
		this.work_ws_cd = work_ws_cd;
	}

	public String getWork_ws_nm() {
		return work_ws_nm;
	}

	public void setWork_ws_nm(String work_ws_nm) {
		this.work_ws_nm = work_ws_nm;
	}

	public String getTeam_name() {
		return team_name;
	}

	public void setTeam_name(String team_name) {
		this.team_name = team_name;
	}

	public String getTeam_id() {
		return team_id;
	}

	public void setTeam_id(String team_id) {
		this.team_id = team_id;
	}

	public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public Date getStart_time_am() {
		return start_time_am;
	}

	public void setStart_time_am(Date start_time_am) {
		this.start_time_am = start_time_am;
	}

	public Date getEnd_time_am() {
		return end_time_am;
	}

	public void setEnd_time_am(Date end_time_am) {
		this.end_time_am = end_time_am;
	}

	public Date getStart_time_pm() {
		return start_time_pm;
	}

	public void setStart_time_pm(Date start_time_pm) {
		this.start_time_pm = start_time_pm;
	}

	public Date getEnd_time_pm() {
		return end_time_pm;
	}

	public void setEnd_time_pm(Date end_time_pm) {
		this.end_time_pm = end_time_pm;
	}

	public Date getStart_time_other() {
		return start_time_other;
	}

	public void setStart_time_other(Date start_time_other) {
		this.start_time_other = start_time_other;
	}

	public Date getEnd_time_other() {
		return end_time_other;
	}

	public void setEnd_time_other(Date end_time_other) {
		this.end_time_other = end_time_other;
	}

	public String getWork_timer() {
		return work_timer;
	}

	public void setWork_timer(String work_timer) {
		this.work_timer = work_timer;
	}

	public String getWork_status() {
		return work_status;
	}

	public void setWork_status(String work_status) {
		this.work_status = work_status;
	}

	public String getRemark() {
		return remark;
	}

	public String getIdenty_card_code() {
		return identy_card_code;
	}

	public void setIdenty_card_code(String identy_card_code) {
		this.identy_card_code = identy_card_code;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDayormonth() {
		return dayormonth;
	}

	public void setDayormonth(String dayormonth) {
		this.dayormonth = dayormonth;
	}

	public String getBank_card() {
		return bank_card;
	}

	public void setBank_card(String bank_card) {
		this.bank_card = bank_card;
	}

	public String getBank_card_name() {
		return bank_card_name;
	}

	public void setBank_card_name(String bank_card_name) {
		this.bank_card_name = bank_card_name;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}
 
	@Override
	public String toString() {
		return "ExPersonInfoPay [identy_card_code=" + identy_card_code + ", name="
				+ name + ", telephone=" + telephone + ", work_type="
				+ work_type + ", work_type_name=" + work_type_name
				+ ", work_count_type=" + work_count_type + ", work_pay="
				+ work_pay + ", work_ws_cd=" + work_ws_cd + ", work_ws_nm="
				+ work_ws_nm + ", team_name=" + team_name + ", team_id="
				+ team_id + ", addtime=" + addtime + ", start_time_am="
				+ start_time_am + ", end_time_am=" + end_time_am
				+ ", start_time_pm=" + start_time_pm + ", end_time_pm="
				+ end_time_pm + ", start_time_other=" + start_time_other
				+ ", end_time_other=" + end_time_other + ", work_timer="
				+ work_timer + ", work_status=" + work_status + ", remark="
				+ remark + ", dayormonth=" + dayormonth + ", bank_card="
				+ bank_card + ", bank_card_name=" + bank_card_name + "]";
	}
}
