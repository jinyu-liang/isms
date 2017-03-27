package com.is.ggkz.entity;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.base.mybatis.AbstractBaseEntity;

/**
 *
 * @ClassName: GgkzAuthResource
 * @Description: GgkzAuthResource表的对应的java对象，该文件为工具自动生成，请勿手工修改!
 * @author 
 * @date 2013-02-27 14:19:32
 *
 */
public class GgkzAuthResource extends AbstractBaseEntity{

    private static final long serialVersionUID = 1L;
    
	
	/* 权限Id */
    public static final String ALIAS_AUTH_ID = "authId";
	
	/* 资源Id */
    public static final String ALIAS_RESOURCE_ID = "resourceId";
	
	/* 操作时间 */
    public static final String ALIAS_OPER_TIME = "operTime";
	
	/* 操作用户Id */
    public static final String ALIAS_OPER_USER_ID = "operUserId";
	
	/* 权限Id */
		private String authId;
	
	/* 资源Id */
		private String resourceId;
	
	/* 操作时间 */
		private Date operTime;
	
	/* 操作用户Id */
		private String operUserId;

	
	
	
		public String getAuthId(){
			return this.authId;
		}
		public void setAuthId(String authId){
			this.authId = authId;
		}
	
	
		public String getResourceId(){
			return this.resourceId;
		}
		public void setResourceId(String resourceId){
			this.resourceId = resourceId;
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

	/**
	 * toString方法
	 */
	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("authId",getAuthId())
            .append("resourceId",getResourceId())
            .append("operTime",getOperTime())
            .append("operUserId",getOperUserId())
            .toString();
    }
}