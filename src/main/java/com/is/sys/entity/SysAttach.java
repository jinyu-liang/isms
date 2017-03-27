package com.is.sys.entity;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.base.mybatis.AbstractBaseEntity;

/**
 *
 * @ClassName: Attach
 * @Description: Attach表的对应的java对象，该文件为工具自动生成，请勿手工修改!
 * @author 
 * @date 2011-12-21 09:30:01
 *
 */
public class SysAttach extends AbstractBaseEntity {

    private static final long serialVersionUID = 1L;
    
	
	/* 附件编号 */
    public static final String ALIAS_ATTACH_ID = "attachId";
	
	/* 附件名称 */
    public static final String ALIAS_ATT_NAME = "attachName";
	
	/* 附件路径 */
    public static final String ALIAS_ATTACH_PATH = "attachPath";
	
	/* 文件类型 */
    public static final String ALIAS_FILE_TYPE = "fileType";
	
	/* 文件大小 */
    public static final String ALIAS_FILE_SIZE = "fileSize";
	
	/* 操作人员工Id */
    public static final String ALIAS_OPER_EMP_ID = "operUserId";
	
	/* 操作员员工姓名 */
    public static final String ALIAS_OPER_EMP_NAME = "operName";
	
	/* 操作时间 */
    public static final String ALIAS_OPER_TIME = "operTime";
	
	/* 附件状态 */
    public static final String ALIAS_ATTACH_STATE = "attachState";
	
	/* 附件描述 */
    public static final String ALIAS_ATTACH_DESC = "attachDesc";
	
	/* 来源业务模块 */
    public static final String ALIAS_SOURCE_BUSI_MODEL = "sourceBusiModel";
	
	/* 附件编号 */
    private String attachId;
	
	/* 附件名称 */
    private String attachName;
	
	/* 附件路径 */
    private String attachPath;
	
	/* 文件类型 */
    private String fileType;
	
	/* 文件大小 */
    private String fileSize;
	
	/* 操作人员工Id */
    private String operUserId;
	
	/* 操作员员工姓名 */
    private String operName;
	
	/* 操作时间 */
    private Date operTime;
	
	/* 附件状态 */
    private String attachState;
	
	/* 附件描述 */
    private String attachDesc;
	
	/* 来源业务模块 */
    private String modelCode;
    
    public String getAttachId(){
		return this.attachId;
	}
	public void setAttachId(String attachId){
		this.attachId = attachId;
	}
	
	public String getAttachName(){
		return this.attachName;
	}
	public void setAttachName(String attachName){
		this.attachName = attachName;
	}
	
	public String getAttachPath(){
		return this.attachPath;
	}
	public void setAttachPath(String attachPath){
		this.attachPath = attachPath;
	}
	
	public String getFileSize() {
		return fileSize;
	}
	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}
	public String getFileType(){
		return this.fileType;
	}
	public void setFileType(String fileType){
		this.fileType = fileType;
	}
	
	public String getOperUserId() {
		return operUserId;
	}
	public void setOperUserId(String operUserId) {
		this.operUserId = operUserId;
	}
	public String getOperName() {
		return operName;
	}
	public void setOperName(String operName) {
		this.operName = operName;
	}
	public Date getOperTime(){
		return this.operTime;
	}
	public void setOperTime(Date operTime){
		this.operTime = operTime;
	}
	
	public String getAttachState(){
		return this.attachState;
	}
	public void setAttachState(String attachState){
		this.attachState = attachState;
	}
	
	public String getAttachDesc(){
		return this.attachDesc;
	}
	public void setAttachDesc(String attachDesc){
		this.attachDesc = attachDesc;
	}
	
	public String getModelCode() {
		return modelCode;
	}
	public void setModelCode(String modelCode) {
		this.modelCode = modelCode;
	}

	/* 关联对象 */
    private List<SysAttachAuth> AttachAuths;
    
    public List<SysAttachAuth> getAttachAuths(){
        return this.AttachAuths;
    }
    public void setAttachAuths(List<SysAttachAuth> AttachAuths){
        this.AttachAuths = AttachAuths;
    }

	/**
	 * toString方法
	 */
	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("attachId",getAttachId())
            .append("attachName",getAttachName())
            .append("attachPath",getAttachPath())
            .append("fileType",getFileType())
            .append("fileSize",getFileSize())
            .append("operUserId",getOperUserId())
            .append("operName",getOperName())
            .append("operTime",getOperTime())
            .append("attachState",getAttachState())
            .append("attachDesc",getAttachDesc())
            .append("modelCode",getModelCode())
            .toString();
    }
}