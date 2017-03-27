package com.is.pretrst.entity;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.base.mybatis.AbstractBaseEntity;

/**
 *
 * @ClassName: DReport
 * @Description: DReport表的对应的java对象，该文件为工具自动生成，请勿手工修改!
 * @author 
 * @date 2013-09-11 17:24:27
 *
 */
public class DReport extends AbstractBaseEntity{

    private static final long serialVersionUID = 1L;
    
	
	/* 报告Id */
    public static final String ALIAS_REPORT_ID = "reportId";
	
	/* 计划表标题 */
    public static final String ALIAS_TITLE = "title";
	
	/* 项目代码 */
    public static final String ALIAS_PROJECT_CODE = "projectCode";
	
	/* 项目名称 */
    public static final String ALIAS_PROJ_NAME = "projName";
	
	/* 单价 */
    public static final String ALIAS_UNIT_PRICE = "unitPrice";
	
	/* 金额 */
    public static final String ALIAS_AMOUNT = "amount";
	
	/* 数量 */
    public static final String ALIAS_NUMBER = "number";
	
	/* 备注 */
    public static final String ALIAS_MEMO = "memo";
	
	/* 报告用户编码 */
    public static final String ALIAS_REPORT_USER_CD = "reportUserCd";
	
	/* 报告时间 */
    public static final String ALIAS_REPORT_TM = "reportTm";
	
	/* 处理用户编码 */
    public static final String ALIAS_PROCESS_USER_CD = "processUserCd";
	
	/* 处理时间 */
    public static final String ALIAS_PROCESS_TM = "processTm";
	
	/* 处理意见 */
    public static final String ALIAS_COMMENT = "comment";
    
    /* 处理结果*/ 
    public static final String ALIAS_DEALRESULT="dealResult";
	/* 状态 */
    public static final String ALIAS_STATUS_CD = "statusCd";

	
	/* 审批用户Cd */
    public static final String ALIAS_VERIFIED_USER_CD = "verifiedUserCd";
	
	/* 部长审核时间 */
    public static final String ALIAS_VERIFIED_HEAD_TM = "verifiedHeadTm";
	
	/* 部长审核备注 */
    public static final String ALIAS_VERIFIED_HEAD_MEMO = "verifiedHeadMemo";
	
	/* 部长审核结果 */
    public static final String ALIAS_VERIFIED_HEAD_STATUS = "verifiedHeadStatus";
	
	/* 报告Id */
		private String reportId;
	
	/* 计划表标题 */
		private String title;
	
	/* 项目代码 */
		private String projectCode;
	
	/* 项目名称 */
		private String projName;
	
	/* 单价 */
		private Double unitPrice;
	
	/* 金额 */
		private Double amount;
	
	/* 数量 */
		private Integer number;
	
	/* 备注 */
		private String memo;
	
	/* 报告用户编码 */
		private String reportUserCd;
		/* 报告用户编码 */
		private String reportUserName;
	
	/* 报告时间 */
		private Date reportTm;
	
	/* 处理用户编码 */
		private String processUserCd;
		
		/* 处理用户编码 */
		private String processUserName;
	
	/* 处理时间 */
		private Date processTm;
		
	/* 处理意见	*/	
		private String comment;
		
	/* 处理结果*/
		private String dealResult;
	
	/* 状态 */
		private String statusCd;
	
	/* 审批用户Cd */
		private String verifiedUserCd;
		/* 审批用户Cd */
		private String verifiedUserName;
	
	/* 审核时间 */
		private Date verifiedHeadTm;
	
	/* 审核备注 */
		private String verifiedHeadMemo;
	
	/* 审核结果 */
		private String verifiedHeadStatus;
	/* 图片文件路径*/	
		private List<String> fileName;

	
	
	
		public String getReportId(){
			return this.reportId;
		}
		public void setReportId(String reportId){
			this.reportId = reportId;
		}
	
	
		public String getTitle(){
			return this.title;
		}
		public void setTitle(String title){
			this.title = title;
		}
	
	
		public String getProjectCode(){
			return this.projectCode;
		}
		public void setProjectCode(String projectCode){
			this.projectCode = projectCode;
		}
	
	
		public String getProjName(){
			return this.projName;
		}
		public void setProjName(String projName){
			this.projName = projName;
		}
	
	
		public Double getUnitPrice(){
			return this.unitPrice;
		}
		public void setUnitPrice(double unitPrice){
			this.unitPrice = unitPrice;
		}
	
		public Double getAmount() {
			return amount;
		}
		public void setAmount(Double amount) {
			this.amount = amount;
		}
		public Integer getNumber(){
			return this.number;
		}
		public void setNumber(Integer number){
			this.number = number;
		}
	
	
		public String getMemo(){
			return this.memo;
		}
		public void setMemo(String memo){
			this.memo = memo;
		}
	
	
		public String getReportUserCd(){
			return this.reportUserCd;
		}
		public void setReportUserCd(String reportUserCd){
			this.reportUserCd = reportUserCd;
		}
	
	
		public Date getReportTm(){
			return this.reportTm;
		}
		public void setReportTm(Date reportTm){
			this.reportTm = reportTm;
		}
	
	
		public String getProcessUserCd(){
			return this.processUserCd;
		}
		public void setProcessUserCd(String processUserCd){
			this.processUserCd = processUserCd;
		}
	
	
		public Date getProcessTm(){
			return this.processTm;
		}
		public void setProcessTm(Date processTm){
			this.processTm = processTm;
		}
	
	
		public String getStatusCd(){
			return this.statusCd;
		}
		public void setStatusCd(String statusCd){
			this.statusCd = statusCd;
		}
	
	
	
	
		public String getVerifiedUserCd(){
			return this.verifiedUserCd;
		}
		public void setVerifiedUserCd(String verifiedUserCd){
			this.verifiedUserCd = verifiedUserCd;
		}
	
	
	
	
		public Date getVerifiedHeadTm(){
			return this.verifiedHeadTm;
		}
		public void setVerifiedHeadTm(Date verifiedHeadTm){
			this.verifiedHeadTm = verifiedHeadTm;
		}
	
	
		public String getVerifiedHeadMemo(){
			return this.verifiedHeadMemo;
		}
		public void setVerifiedHeadMemo(String verifiedHeadMemo){
			this.verifiedHeadMemo = verifiedHeadMemo;
		}
	
	
		public String getVerifiedHeadStatus(){
			return this.verifiedHeadStatus;
		}
		public void setVerifiedHeadStatus(String verifiedHeadStatus){
			this.verifiedHeadStatus = verifiedHeadStatus;
		}

	/**
	 * toString方法
	 */
	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("reportId",getReportId())
            .append("title",getTitle())
            .append("projectCode",getProjectCode())
            .append("projName",getProjName())
            .append("unitPrice",getUnitPrice())
            .append("amount",getAmount())
            .append("number",getNumber())
            .append("memo",getMemo())
            .append("reportUserCd",getReportUserCd())
            .append("reportTm",getReportTm())
            .append("processUserCd",getProcessUserCd())
            .append("processTm",getProcessTm())
            .append("dealResult",getDealResult())
            .append("comment",getComment())
            .append("statusCd",getStatusCd())
            .append("verifiedUserCd",getVerifiedUserCd())
            .append("verifiedHeadTm",getVerifiedHeadTm())
            .append("verifiedHeadMemo",getVerifiedHeadMemo())
            .append("verifiedHeadStatus",getVerifiedHeadStatus())
            .toString();
    }
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getComment() {
		return comment;
	}
	public void setFileName(List<String> fileName) {
		this.fileName = fileName;
	}
	public List<String> getFileName() {
		return fileName;
	}
	public void setDealResult(String dealResult) {
		this.dealResult = dealResult;
	}
	public String getDealResult() {
		return dealResult;
	}
	public String getReportUserName() {
		return reportUserName;
	}
	public void setReportUserName(String reportUserName) {
		this.reportUserName = reportUserName;
	}
	public String getProcessUserName() {
		return processUserName;
	}
	public void setProcessUserName(String processUserName) {
		this.processUserName = processUserName;
	}
	public String getVerifiedUserName() {
		return verifiedUserName;
	}
	public void setVerifiedUserName(String verifiedUserName) {
		this.verifiedUserName = verifiedUserName;
	}
	
}