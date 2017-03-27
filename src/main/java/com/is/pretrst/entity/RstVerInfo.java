package com.is.pretrst.entity;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.base.mybatis.AbstractBaseEntity;

/**
 *
 * @ClassName: RstVerInfo
 * @Description: RstVerInfo表的对应的java对象，该文件为工具自动生成，请勿手工修改!
 * @author 
 * @date 2013-10-26 12:44:18
 *
 */
public class RstVerInfo extends AbstractBaseEntity{

    private static final long serialVersionUID = 1L;
    
	
	/* 版本Id */
    public static final String ALIAS_VER_ID = "verId";
	
	/* 版本号 */
    public static final String ALIAS_VER_CODE = "verCode";
	
	/* 生产日期 */
    public static final String ALIAS_PROD_DATE = "prodDate";
	
	/* 竣工日期 */
    public static final String ALIAS_FINISH_DATE = "finishDate";
	
	/* 状态 */
    public static final String ALIAS_STATUS_CD = "statusCd";
	
	/* 备注 */
    public static final String ALIAS_MEMO = "memo";
	
	/* 文件名 */
    public static final String ALIAS_FILE_NAME = "fileName";
	
	/* 更新方式 */
    public static final String ALIAS_UPDATE_MODE = "updateMode";
	
	/* 版本Id */
		private Integer verId;
	
	/* 版本号 */
		private String verCode;
	
	/* 生产日期 */
		private Date prodDate;
	
	/* 竣工日期 */
		private Date finishDate;
	
	/* 状态 */
		private String statusCd;
	
	/* 备注 */
		private String memo;
	
	/* 文件名 */
		private String fileName;
	
	/* 更新方式 */
		private String updateMode;
		
		private String operUserId;
		private String operUserName;
		private Date   operTime;

	
	
	
		public Integer getVerId(){
			return this.verId;
		}
		public void setVerId(Integer verId){
			this.verId = verId;
		}
	
	
		public String getVerCode(){
			return this.verCode;
		}
		public void setVerCode(String verCode){
			this.verCode = verCode;
		}
	
	
		public Date getProdDate(){
			return this.prodDate;
		}
		public void setProdDate(Date prodDate){
			this.prodDate = prodDate;
		}
	
	
		public Date getFinishDate(){
			return this.finishDate;
		}
		public void setFinishDate(Date finishDate){
			this.finishDate = finishDate;
		}
	
	
		public String getStatusCd(){
			return this.statusCd;
		}
		public void setStatusCd(String statusCd){
			this.statusCd = statusCd;
		}
	
	
		public String getMemo(){
			return this.memo;
		}
		public void setMemo(String memo){
			this.memo = memo;
		}
	
	
		public String getFileName(){
			return this.fileName;
		}
		public void setFileName(String fileName){
			this.fileName = fileName;
		}
	
	
		public String getUpdateMode(){
			return this.updateMode;
		}
		public void setUpdateMode(String updateMode){
			this.updateMode = updateMode;
		}

	public String getOperUserId() {
			return operUserId;
		}
		public void setOperUserId(String operUserId) {
			this.operUserId = operUserId;
		}
		public String getOperUserName() {
			return operUserName;
		}
		public void setOperUserName(String operUserName) {
			this.operUserName = operUserName;
		}
		public Date getOperTime() {
			return operTime;
		}
		public void setOperTime(Date operTime) {
			this.operTime = operTime;
		}
	/**
	 * toString方法
	 */
	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("verId",getVerId())
            .append("verCode",getVerCode())
            .append("prodDate",getProdDate())
            .append("finishDate",getFinishDate())
            .append("statusCd",getStatusCd())
            .append("memo",getMemo())
            .append("fileName",getFileName())
            .append("updateMode",getUpdateMode())
            .append("operUserId",getOperUserId())
            .append("operUserName",getOperUserName())
            .append("operTime",getOperTime())
            .toString();
    }
}