package com.is.ggkz.entity;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.base.mybatis.AbstractBaseEntity;

/**
 *
 * @ClassName: GgkzResourceInfo
 * @Description: GgkzResourceInfo表的对应的java对象，该文件为工具自动生成，请勿手工修改!
 * @author 
 * @date 2013-02-27 14:19:25
 *
 */
public class GgkzResourceInfo extends AbstractBaseEntity{

    private static final long serialVersionUID = 1L;
    
	
	/* 资源Id */
    public static final String ALIAS_RESOURCE_ID = "resourceId";
	
	/* 上级资源代码 */
    public static final String ALIAS_HIGER_RESOURCE_CODE = "higerResourceCode";
	
	/* 资源名称 */
    public static final String ALIAS_RESOURCE_NAME = "resourceName";
	
	/* 资源类型 */
    public static final String ALIAS_RESOURCE_TYPE = "resourceType";
	
	/* 简称 */
    public static final String ALIAS_SHORT_NAME = "shortName";
	
	/* 资源url */
    public static final String ALIAS_RESOURCE_URL = "resourceUrl";
	
	/* 排序号 */
    public static final String ALIAS_ORDER_NUM = "orderNum";
	
	/* 模块编号 */
    public static final String ALIAS_MODEL_CODE = "modelCode";
	
	/* 有效状态 */
    public static final String ALIAS_VALID_STATE = "validState";
	
	/* 备注 */
    public static final String ALIAS_NOTE = "note";
	
	/* 操作时间 */
    public static final String ALIAS_OPER_TIME = "operTime";
	
	/* 操作用户Id */
    public static final String ALIAS_OPER_USER_ID = "operUserId";
	
	/* 资源Id */
		private String resourceId;
	
	/* 上级资源代码 */
		private String higerResourceCode;
	
	/* 资源名称 */
		private String resourceName;
	
	/* 资源类型 */
		private String resourceType;
	
	/* 简称 */
		private String shortName;
	
	/* 资源url */
		private String resourceUrl;
	
	/* 排序号 */
		private String orderNum;
	
	/* 模块编号 */
		private String modelCode;
	
	/* 有效状态 */
		private String validState;
	
	/* 备注 */
		private String note;
	
	/* 操作时间 */
		private Date operTime;
	
	/* 操作用户Id */
		private String operUserId;

	
	
	
		public String getResourceId(){
			return this.resourceId;
		}
		public void setResourceId(String resourceId){
			this.resourceId = resourceId;
		}
	
	
		public String getHigerResourceCode(){
			return this.higerResourceCode;
		}
		public void setHigerResourceCode(String higerResourceCode){
			this.higerResourceCode = higerResourceCode;
		}
	
	
		public String getResourceName(){
			return this.resourceName;
		}
		public void setResourceName(String resourceName){
			this.resourceName = resourceName;
		}
	
	
		public String getResourceType(){
			return this.resourceType;
		}
		public void setResourceType(String resourceType){
			this.resourceType = resourceType;
		}
	
	
		public String getShortName(){
			return this.shortName;
		}
		public void setShortName(String shortName){
			this.shortName = shortName;
		}
	
	
		public String getResourceUrl(){
			return this.resourceUrl;
		}
		public void setResourceUrl(String resourceUrl){
			this.resourceUrl = resourceUrl;
		}
	
	
		public String getOrderNum(){
			return this.orderNum;
		}
		public void setOrderNum(String orderNum){
			this.orderNum = orderNum;
		}
	
	
		public String getModelCode(){
			return this.modelCode;
		}
		public void setModelCode(String modelCode){
			this.modelCode = modelCode;
		}
	
	
		public String getValidState(){
			return this.validState;
		}
		public void setValidState(String validState){
			this.validState = validState;
		}
	
	
		public String getNote(){
			return this.note;
		}
		public void setNote(String note){
			this.note = note;
		}
	
	
		public Date getOperTime(){
			return this.operTime;
		}
		public void setOperTime(Date operTime){
			this.operTime = operTime;
		}
	
	
		public String getOperUserId(){
			return this.operUserId;
		}
		public void setOperUserId(String operUserId){
			this.operUserId = operUserId;
		}
	
	/* 关联对象 */
    private List<GgkzAuthResource> ggkzAuthResources;
    
    public List<GgkzAuthResource> getGgkzAuthResources(){
        return this.ggkzAuthResources;
    }
    public void setGgkzAuthResources(List<GgkzAuthResource> ggkzAuthResources){
        this.ggkzAuthResources = ggkzAuthResources;
    }

	/**
	 * toString方法
	 */
	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("resourceId",getResourceId())
            .append("higerResourceCode",getHigerResourceCode())
            .append("resourceName",getResourceName())
            .append("resourceType",getResourceType())
            .append("shortName",getShortName())
            .append("resourceUrl",getResourceUrl())
            .append("orderNum",getOrderNum())
            .append("modelCode",getModelCode())
            .append("validState",getValidState())
            .append("note",getNote())
            .append("operTime",getOperTime())
            .append("operUserId",getOperUserId())
            .toString();
    }
}