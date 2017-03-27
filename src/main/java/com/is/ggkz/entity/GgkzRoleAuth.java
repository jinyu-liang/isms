package com.is.ggkz.entity;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.base.mybatis.AbstractBaseEntity;

/**
 *
 * @ClassName: GgkzRoleAuth
 * @Description: GgkzRoleAuth表的对应的java对象，该文件为工具自动生成，请勿手工修改!
 * @author 
 * @date 2013-02-27 14:19:30
 *
 */
public class GgkzRoleAuth extends AbstractBaseEntity{

    private static final long serialVersionUID = 1L;
    
	
	/* 角色Id */
    public static final String ALIAS_ROLE_ID = "roleId";
	
	/* 权限Id */
    public static final String ALIAS_AUTH_ID = "authId";
	
	/* 操作时间 */
    public static final String ALIAS_OPER_TIME = "operTime";
	
	/* 操作用户Id */
    public static final String ALIAS_OPER_USER_ID = "operUserId";
	
	/* 角色Id */
		private String roleId;
	
	/* 权限Id */
		private String authId;
	
	/* 操作时间 */
		private Date operTime;
	
	/* 操作用户Id */
		private String operUserId;

	
	
	
		public String getRoleId(){
			return this.roleId;
		}
		public void setRoleId(String roleId){
			this.roleId = roleId;
		}
	
	
		public String getAuthId(){
			return this.authId;
		}
		public void setAuthId(String authId){
			this.authId = authId;
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
            .append("roleId",getRoleId())
            .append("authId",getAuthId())
            .append("operTime",getOperTime())
            .append("operUserId",getOperUserId())
            .toString();
    }
}