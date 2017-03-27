package com.is.pretrst.entity;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import com.base.mybatis.AbstractBaseEntity;

/**
 *
 * @ClassName: MWorkshopItem
 * @Description: MWorkshopItem表的对应的java对象，该文件为工具自动生成，请勿手工修改!
 * @author 
 * @date 2013-10-31 18:27:44
 *
 */
public class MWorkshopItem extends AbstractBaseEntity{

    private static final long serialVersionUID = 1L;
    
	
	/* 发货计划货物Id */
    public static final String ALIAS_ITEM_ID = "itemId";
	
	/* 工程中心编码 */
    public static final String ALIAS_WS_CD = "wsCd";
	
	/* 分类代码 */
    public static final String ALIAS_CATEGORY_CD = "categoryCd";
	
	/* 材质代码 */
    public static final String ALIAS_MATERIAL_CD = "materialCd";
	
	/* 型号 */
    public static final String ALIAS_MODEL_NO = "modelNo";
	
	/* 总数 */
    public static final String ALIAS_TOTAL_AMOUNT = "totalAmount";
	
	/* 剩余数量 */
    public static final String ALIAS_REMAIN_NUMBER = "remainNumber";
	
	/* 单位 */
    public static final String ALIAS_UNIT = "unit";
	
	/* 使用位置 */
    public static final String ALIAS_USE_PLACE = "usePlace";
	
	/* 材质名称 */
    public static final String ALIAS_MATERIAL_NM = "materialNm";
	
	/* 分类名称 */
    public static final String ALIAS_CATEGORY_NM = "categoryNm";
	
	/* 备注 */
    public static final String ALIAS_MEMO = "memo";
	
	/* 发货计划货物Id */
		private String itemId;
	
	/* 工程中心编码 */
		private String wsCd;
	
	/* 分类代码 */
		private String categoryCd;
	
	/* 材质代码 */
		private String materialCd;
	
	/* 型号 */
		private String modelNo;
	
	/* 总数 */
		private Integer totalAmount;
	
	/* 剩余数量 */
		private Integer remainNumber;
	
	/* 单位 */
		private String unit;
	
	/* 使用位置 */
		private String usePlace;
	
	/* 材质名称 */
		private String materialNm;
	
	/* 分类名称 */
		private String categoryNm;
	
	/* 备注 */
		private String memo;

	
	
	
		public String getItemId(){
			return this.itemId;
		}
		public void setItemId(String itemId){
			this.itemId = itemId;
		}
	
	
		public String getWsCd(){
			return this.wsCd;
		}
		public void setWsCd(String wsCd){
			this.wsCd = wsCd;
		}
	
	
		public String getCategoryCd(){
			return this.categoryCd;
		}
		public void setCategoryCd(String categoryCd){
			this.categoryCd = categoryCd;
		}
	
	
		public String getMaterialCd(){
			return this.materialCd;
		}
		public void setMaterialCd(String materialCd){
			this.materialCd = materialCd;
		}
	
	
		public String getModelNo(){
			return this.modelNo;
		}
		public void setModelNo(String modelNo){
			this.modelNo = modelNo;
		}
	
	
		public Integer getTotalAmount(){
			return this.totalAmount;
		}
		public void setTotalAmount(Integer totalAmount){
			this.totalAmount = totalAmount;
		}
	
	
		public Integer getRemainNumber(){
			return this.remainNumber;
		}
		public void setRemainNumber(Integer remainNumber){
			this.remainNumber = remainNumber;
		}
	
	
		public String getUnit(){
			return this.unit;
		}
		public void setUnit(String unit){
			this.unit = unit;
		}
	
	
		public String getUsePlace(){
			return this.usePlace;
		}
		public void setUsePlace(String usePlace){
			this.usePlace = usePlace;
		}
	
	
		public String getMaterialNm(){
			return this.materialNm;
		}
		public void setMaterialNm(String materialNm){
			this.materialNm = materialNm;
		}
	
	
		public String getCategoryNm(){
			return this.categoryNm;
		}
		public void setCategoryNm(String categoryNm){
			this.categoryNm = categoryNm;
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
            .append("wsCd",getWsCd())
            .append("categoryCd",getCategoryCd())
            .append("materialCd",getMaterialCd())
            .append("modelNo",getModelNo())
            .append("totalAmount",getTotalAmount())
            .append("remainNumber",getRemainNumber())
            .append("unit",getUnit())
            .append("usePlace",getUsePlace())
            .append("materialNm",getMaterialNm())
            .append("categoryNm",getCategoryNm())
            .append("memo",getMemo())
            .toString();
    }
}