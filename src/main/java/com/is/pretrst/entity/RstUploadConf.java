package com.is.pretrst.entity;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.base.mybatis.AbstractBaseEntity;

/**
 *
 * @ClassName: RstUploadConf
 * @Description: RstUploadConf表的对应的java对象，该文件为工具自动生成，请勿手工修改!
 * @author 
 * @date 2013-10-26 12:44:21
 *
 */
public class RstUploadConf extends AbstractBaseEntity{

    private static final long serialVersionUID = 1L;
    
	
	/* 文件类型 */
    public static final String ALIAS_FILE_TYPE = "fileType";
	
	/* 文件大小设置 */
    public static final String ALIAS_SIZE_CONF = "sizeConf";
	
	/* 状态 */
    public static final String ALIAS_STATUS_CD = "statusCd";
	
	/* 操作用户Id */
    public static final String ALIAS_OPER_USER_ID = "operUserId";
	
	/* 操作用户名称 */
    public static final String ALIAS_OPER_USER_NAME = "operUserName";
	
	/* 操作时间 */
    public static final String ALIAS_OPER_TIME = "operTime";
	
	/* 文件类型 */
		private String fileType;
	
	/* 文件大小设置 */
		private Integer sizeConf;
	
	/* 状态 */
		private String statusCd;
	
	/* 操作用户Id */
		private String operUserId;
	
	/* 操作用户名称 */
		private String operUserName;
	
	/* 操作时间 */
		private Date operTime;

	
	
	
		public String getFileType(){
			return this.fileType;
		}
		public void setFileType(String fileType){
			this.fileType = fileType;
		}
	
	
		public Integer getSizeConf(){
			return this.sizeConf;
		}
		public void setSizeConf(Integer sizeConf){
			this.sizeConf = sizeConf;
		}
	
	
		public String getStatusCd(){
			return this.statusCd;
		}
		public void setStatusCd(String statusCd){
			this.statusCd = statusCd;
		}
	
	
		public String getOperUserId(){
			return this.operUserId;
		}
		public void setOperUserId(String operUserId){
			this.operUserId = operUserId;
		}
	
	
		public String getOperUserName(){
			return this.operUserName;
		}
		public void setOperUserName(String operUserName){
			this.operUserName = operUserName;
		}
	
	
		public Date getOperTime(){
			return this.operTime;
		}
		public void setOperTime(Date operTime){
			this.operTime = operTime;
		}

	/**
	 * toString方法
	 */
	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("fileType",getFileType())
            .append("sizeConf",getSizeConf())
            .append("statusCd",getStatusCd())
            .append("operUserId",getOperUserId())
            .append("operUserName",getOperUserName())
            .append("operTime",getOperTime())
            .toString();
    }
}