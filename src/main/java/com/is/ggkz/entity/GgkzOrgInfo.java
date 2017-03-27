package com.is.ggkz.entity;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.base.mybatis.AbstractBaseEntity;

/**
 *
 * @ClassName: GgkzOrgInfo
 * @Description: GgkzOrgInfo表的对应的java对象，该文件为工具自动生成，请勿手工修改!
 * @author 
 * @date 2013-02-27 14:19:34
 *
 */
public class GgkzOrgInfo extends AbstractBaseEntity{

    private static final long serialVersionUID = 1L;
    
	
	/* 机构ID */
    public static final String ALIAS_ORG_ID = "orgId";
	
	/* 机构名称 */
    public static final String ALIAS_ORG_NAME = "orgName";
	
	/* 机构ID */
		private String orgId;
	
	/* 机构名称 */
		private String orgName;

	
	
	
		public String getOrgId(){
			return this.orgId;
		}
		public void setOrgId(String orgId){
			this.orgId = orgId;
		}
	
	
		public String getOrgName(){
			return this.orgName;
		}
		public void setOrgName(String orgName){
			this.orgName = orgName;
		}
	
	/* 关联对象 */
    private List<GgkzUserInfo> ggkzUserInfos;
    
    public List<GgkzUserInfo> getGgkzUserInfos(){
        return this.ggkzUserInfos;
    }
    public void setGgkzUserInfos(List<GgkzUserInfo> ggkzUserInfos){
        this.ggkzUserInfos = ggkzUserInfos;
    }

	/**
	 * toString方法
	 */
	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("orgId",getOrgId())
            .append("orgName",getOrgName())
            .toString();
    }
}