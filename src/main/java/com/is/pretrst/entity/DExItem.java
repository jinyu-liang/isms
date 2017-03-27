package com.is.pretrst.entity;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.base.mybatis.AbstractBaseEntity;

/**
 *
 * @ClassName: DExItem
 * @Description: DExItem表的对应的java对象，该文件为工具自动生成，请勿手工修改!
 * @author 
 * @date 2013-09-10 10:26:02
 *
 */
public class DExItem extends AbstractBaseEntity{

    private static final long serialVersionUID = 1L;
    
	
	/* 发货计划货物Id */
    public static final String ALIAS_ITEM_ID = "itemId";
	
	/* 工程ID */
    public static final String ALIAS_PROJECT_ID = "projectId";
	
	/* 设备名称 */
    public static final String ALIAS_ITEM_NM = "itemNm";
	
	/* 金额 */
    public static final String ALIAS_AMOUNT = "amount";
	
	/* 备注 */
    public static final String ALIAS_MEMO = "memo";
	
	/* 发货计划货物Id */
		private String itemId;
	
	/* 工程ID */
		private String projectId;
	
	/* 设备名称 */
		private String itemNm;
	
	/* 金额 */
		private Integer amount;
	
	/* 备注 */
		private String memo;

	
	
	
	
		public String getItemId() {
			return itemId;
		}
		public void setItemId(String itemId) {
			this.itemId = itemId;
		}
		public String getProjectId() {
			return projectId;
		}
		public void setProjectId(String projectId) {
			this.projectId = projectId;
		}
		public String getItemNm(){
			return this.itemNm;
		}
		public void setItemNm(String itemNm){
			this.itemNm = itemNm;
		}
	
	

	
		public Integer getAmount() {
			return amount;
		}
		public void setAmount(Integer amount) {
			this.amount = amount;
		}
		public String getMemo(){
			return this.memo;
		}
		public void setMemo(String memo){
			this.memo = memo;
		}

	/**
	 * toString方法
	 */
	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("itemId",getItemId())
            .append("projectId",getProjectId())
            .append("itemNm",getItemNm())
            .append("amount",getAmount())
            .append("memo",getMemo())
            .toString();
    }
}