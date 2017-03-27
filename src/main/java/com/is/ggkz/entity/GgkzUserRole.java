package com.is.ggkz.entity;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.base.mybatis.AbstractBaseEntity;

/**
 *
 * @ClassName: GgkzUserRole
 * @Description: GgkzUserRole表的对应的java对象，该文件为工具自动生成，请勿手工修改!
 * @author 
 * @date 2013-02-27 14:19:27
 *
 */
public class GgkzUserRole extends AbstractBaseEntity{

    private static final long serialVersionUID = 1L;
    
	
	/* 用户Id */
    public static final String ALIAS_USER_ID = "userId";
	
	/* 角色Id */
    public static final String ALIAS_ROLE_ID = "roleId";
	
	/* 操作时间 */
    public static final String ALIAS_OPER_TIME = "operTime";
	
	/* 操作用户Id */
    public static final String ALIAS_OPER_USER_ID = "operUserId";
	
	/* 用户Id */
		private String userId;
	
	/* 角色Id */
		private String roleId;
	
	/* 操作时间 */
		private Date operTime;
	
	/* 操作用户Id */
		private String operUserId;

	
	
	
		public String getUserId(){
			return this.userId;
		}
		public void setUserId(String userId){
			this.userId = userId;
		}
	
	
		public String getRoleId(){
			return this.roleId;
		}
		public void setRoleId(String roleId){
			this.roleId = roleId;
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
            .append("userId",getUserId())
            .append("roleId",getRoleId())
            .append("operTime",getOperTime())
            .append("operUserId",getOperUserId())
            .toString();
    }
}