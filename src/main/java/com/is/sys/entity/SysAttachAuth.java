package com.is.sys.entity;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.base.mybatis.AbstractBaseEntity;

/**
 * 
 * @ClassName: BzzcAttachAuth
 * @Description: BzzcAttachAuth表的对应的java对象，该文件为工具自动生成，请勿手工修改!
 * @author 
 * @date 2011-12-21 09:30:05
 * 
 */
public class SysAttachAuth extends AbstractBaseEntity {

	private static final long serialVersionUID = 1L;

	/* 附件编号 */
	public static final String ALIAS_ATTACH_ID = "attachId";

	/* 员工ID */
	public static final String ALIAS_EMP_ID = "userId";

	/* 业务Id */
	public static final String ALIAS_BUSI_ID = "busiId";

	/* 附件编号 */
	private String attachId;

	/* 员工ID */
	private String userId;

	/* 业务Id */
	private String busiId;

	public String getAttachId() {
		return this.attachId;
	}

	public void setAttachId(String attachId) {
		this.attachId = attachId;
	}

	public String getBusiId() {
		return this.busiId;
	}

	public void setBusiId(String busiId) {
		this.busiId = busiId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	/**
	 * toString方法
	 */
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("attachId", getAttachId())
				.append("userId", getUserId()).append("busiId", getBusiId())
				.toString();
	}
}