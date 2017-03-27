package com.is.pretrst.entity;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.base.mybatis.AbstractBaseEntity;

/**
 *
 * @ClassName: ExPersonInfo
 * @Description: ExPersonInfo表的对应的java对象，该文件为工具自动生成，请勿手工修改!
 * @author 
 * @date 2013-12-13 16:28:10
 *
 */
public class ExPersonInfo extends AbstractBaseEntity{

    private static final long serialVersionUID = 1L;
    
	
	/* 人员Id */
    public static final String ALIAS_EMP_ID = "empId";
	
	/* 报告Id */
    public static final String ALIAS_REPORT_ID = "reportId";
	
	/* 姓名 */
    public static final String ALIAS_NAME = "name";
	
	/* 身份证号 */
    public static final String ALIAS_IDENTY_CARD_CODE = "identyCardCode";
	
	/* 电话号码 */
    public static final String ALIAS_TELEPHONE = "telephone";
	
	/* 工作类型 */
    public static final String ALIAS_WORK_TYPE = "workType";
	
	/* 工作类型名称 */
    public static final String ALIAS_WORK_TYPE_NAME = "workTypeName";
	
	/* 状态 */
    public static final String ALIAS_STATUS_CD = "statusCd";
	
	/* 工程中心编码 */
    public static final String ALIAS_WS_CD = "wsCd";
	
	/* 工地中心名称 */
    public static final String ALIAS_WS_NM = "wsNm";
	
	/* 更新日期 */
    public static final String ALIAS_UPDATE_DATE = "updateDate";
	
	/* 标志 */
    public static final String ALIAS_FLAG = "flag";
	
	/* 施工队名 */
    public static final String ALIAS_team_NAME = "teamName";
	
	/* 施工队编号 */
    public static final String ALIAS_team_ID = "teamId";
	
    /* 身份证号 */
	private String identyCardCode;
	/* 报告Id */
		private String reportId;
	
	/* 姓名 */
		private String name;
	
	/* 电话号码 */
		private String telephone;
	
	/* 工作类型 */
		private String workType;
	
	/* 工作类型名称 */
		private String workTypeName;
	
	/* 状态 */
		private String statusCd;
	
	/* 工程中心编码 */
		private String wsCd;
	
	/* 工地中心名称 */
		private String wsNm;

	/* 申请工程中心编码 */
		private String toWsCd;
	
	/* 申请工地中心名称 */
		private String toWsNm;
	
	/* 更新日期 */
		private Date updateDate;
	
	/* 标志 */
		private String flag;
	
	/* 施工队名 */
		private String teamName;
	
	/* 施工队编号 */
		private String teamId;
	
		
		public String bank_id;
		
		public String bank_name;
		
		
		public String getReportId(){
			return this.reportId;
		}
		public void setReportId(String reportId){
			this.reportId = reportId;
		}
	
	
		public String getName(){
			return this.name;
		}
		public void setName(String name){
			this.name = name;
		}
	
	
		public String getIdentyCardCode(){
			return this.identyCardCode;
		}
		public void setIdentyCardCode(String identyCardCode){
			this.identyCardCode = identyCardCode;
		}
	
	
		public String getTelephone(){
			return this.telephone;
		}
		public void setTelephone(String telephone){
			this.telephone = telephone;
		}
	
	
		public String getWorkType(){
			return this.workType;
		}
		public void setWorkType(String workType){
			this.workType = workType;
		}
	
	
		public String getWorkTypeName(){
			return this.workTypeName;
		}
		public void setWorkTypeName(String workTypeName){
			this.workTypeName = workTypeName;
		}
	
	
		public String getStatusCd(){
			return this.statusCd;
		}
		public void setStatusCd(String statusCd){
			this.statusCd = statusCd;
		}
	
	
		public String getWsCd(){
			return this.wsCd;
		}
		public void setWsCd(String wsCd){
			this.wsCd = wsCd;
		}
	
	
		public String getWsNm(){
			return this.wsNm;
		}
		public void setWsNm(String wsNm){
			this.wsNm = wsNm;
		}
	
	
		public Date getUpdateDate(){
			return this.updateDate;
		}
		public void setUpdateDate(Date updateDate){
			this.updateDate = updateDate;
		}
	
	
		public String getFlag(){
			return this.flag;
		}
		public void setFlag(String flag){
			this.flag = flag;
		}
	
        public String getTeamName()
        {
            return teamName;
        }
        public void setTeamName(String teamName)
        {
            this.teamName = teamName;
        }
        public String getTeamId()
        {
            return teamId;
        }
        public void setTeamId(String teamId)
        {
            this.teamId = teamId;
        }
        public String getToWsCd() {
			return toWsCd;
		}
		public void setToWsCd(String toWsCd) {
			this.toWsCd = toWsCd;
		}
		public String getToWsNm() {
			return toWsNm;
		}
		public void setToWsNm(String toWsNm) {
			this.toWsNm = toWsNm;
		}
		
		
		public String getBank_id() {
			return bank_id;
		}
		public void setBank_id(String bank_id) {
			this.bank_id = bank_id;
		}
		public String getBank_name() {
			return bank_name;
		}
		public void setBank_name(String bank_name) {
			this.bank_name = bank_name;
		}
	/**
	 * toString方法
	 */
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
            .append("flag",getFlag())
            .append("teamName",getTeamName())
            .append("teamId",getTeamId())
            .append("toWsCd",getToWsCd())
            .append("toWsNm",getToWsNm())
            .append("bank_id",getBank_id())
            .append("bank_name",getBank_name())
            .toString();
    }
}