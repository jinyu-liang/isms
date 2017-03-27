package com.is.pretrst.entity;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import com.base.mybatis.AbstractBaseEntity;

/**
 *
 * @ClassName: DDeliverItem
 * @Description: DDeliverItem表的对应的java对象，该文件为工具自动生成，请勿手工修改!
 * @author 
 * @date 2013-09-09 09:25:33
 *
 */
public class DDeliveryItem extends AbstractBaseEntity{

    private static final long serialVersionUID = 1L;
    
	
	/* 发货计划货物Id */
    public static final String ALIAS_ITEM_ID = "itemId";
	
	/* 发货计划ID */
    public static final String ALIAS_PLAN_ID = "planId";
	
	/* 序号 */
    public static final String ALIAS_ITEM_NO = "itemNo";
	
	/* 货物类别代码-备用 */
    public static final String ALIAS_CLASS_CD = "classCd";
	
	/* 货物类别名称 */
    public static final String ALIAS_CLASS_NM = "classNm";
	
	/* 分类代码 */
    public static final String ALIAS_CATEGORY_CD = "categoryCd";
	
	/* 分类名称 */
    public static final String ALIAS_CATEGORY_NM = "categoryNm";
	
	/* 材质代码 */
    public static final String ALIAS_MATERIAL_CD = "materialCd";
	
	/* 材质名称 */
    public static final String ALIAS_MATERIAL_NM = "materialNm";
	
	/* 型号 */
    public static final String ALIAS_MODEL_NO = "modelNo";
	
	/* 标识编码 */
    public static final String ALIAS_IDENTIFICATION_CD = "identificationCd";
	
	/* 标识名称 */
    public static final String ALIAS_IDENTIFICATION_NM = "identificationNm";
	
	/* 发货人 */
    public static final String ALIAS_SENDER = "sender";
	
	/* 接收人 */
    public static final String ALIAS_RECEIVER = "receiver";
	
	/* 发货数 */
    public static final String ALIAS_SEND_AMOUNT = "sendAmount";
	
	/* 实收数 */
    public static final String ALIAS_RECEIVED_AMOUNT = "receivedAmount";
	
	/* 单位 */
    public static final String ALIAS_UNIT = "unit";
	
    /* 使用位置 */
    public static final String ALIAS_USE_PLACE = "usePlace";
    
	/* 备注 */
    public static final String ALIAS_MEMO = "memo";
	
	/* 状态 */
    public static final String ALIAS_STATUS_CD = "statusCd";
	
	/* 发货计划货物Id */
		private String itemId;
	
	/* 发货计划ID */
		private String planId;
	
	/* 序号 */
		private Integer itemNo;
	
	/* 货物类别代码-备用 */
		private String classCd;
	
	/* 货物类别名称 */
		private String classNm;
	
	/* 分类代码 */
		private String categoryCd;
	
	/* 分类名称 */
		private String categoryNm;
	
	/* 材质代码 */
		private String materialCd;
	
	/* 材质名称 */
		private String materialNm;
	
	/* 型号 */
		private String modelNo;
	
	/* 标识编码 */
		private String identificationCd;
	
	/* 标识名称 */
		private String identificationNm;
	
	/* 发货人 */
		private String sender;
	
	/* 接收人 */
		private String receiver;
	
	/* 发货数 */
		private Integer sendAmount;
	
	/* 实收数 */
		private Integer receivedAmount;
	
	/* 单位 */
		private String unit;
	/* 使用位置 */
	    private String usePlace;
	
	/* 备注 */
		private String memo;
	
	/* 状态 */
		private String statusCd;

	
	
	
		public String getItemId(){
			return this.itemId;
		}
		public void setItemId(String itemId){
			this.itemId = itemId;
		}
	
	
		public String getPlanId(){
			return this.planId;
		}
		public void setPlanId(String planId){
			this.planId = planId;
		}
	
	
		public Integer getItemNo(){
			return this.itemNo;
		}
		public void setItemNo(Integer itemNo){
			this.itemNo = itemNo;
		}
	
	
		public String getClassCd(){
			return this.classCd;
		}
		public void setClassCd(String classCd){
			this.classCd = classCd;
		}
	
	
		public String getClassNm(){
			return this.classNm;
		}
		public void setClassNm(String classNm){
			this.classNm = classNm;
		}
	
	
		public String getCategoryCd(){
			return this.categoryCd;
		}
		public void setCategoryCd(String categoryCd){
			this.categoryCd = categoryCd;
		}
	
	
		public String getCategoryNm(){
			return this.categoryNm;
		}
		public void setCategoryNm(String categoryNm){
			this.categoryNm = categoryNm;
		}
	
	
		public String getMaterialCd(){
			return this.materialCd;
		}
		public void setMaterialCd(String materialCd){
			this.materialCd = materialCd;
		}
	
	
		public String getMaterialNm(){
			return this.materialNm;
		}
		public void setMaterialNm(String materialNm){
			this.materialNm = materialNm;
		}
	
	
		public String getModelNo(){
			return this.modelNo;
		}
		public void setModelNo(String modelNo){
			this.modelNo = modelNo;
		}
	
	
		public String getIdentificationCd(){
			return this.identificationCd;
		}
		public void setIdentificationCd(String identificationCd){
			this.identificationCd = identificationCd;
		}
	
	
		public String getIdentificationNm(){
			return this.identificationNm;
		}
		public void setIdentificationNm(String identificationNm){
			this.identificationNm = identificationNm;
		}
	
	
		public String getSender(){
			return this.sender;
		}
		public void setSender(String sender){
			this.sender = sender;
		}
	
        public String getUsePlace()
        {
            return usePlace;
        }

        public void setUsePlace(String usePlace)
        {
            this.usePlace = usePlace;
        }
        public String getReceiver(){
			return this.receiver;
		}
		public void setReceiver(String receiver){
			this.receiver = receiver;
		}
	
	
		public Integer getSendAmount(){
			return this.sendAmount;
		}
		public void setSendAmount(Integer sendAmount){
			this.sendAmount = sendAmount;
		}
	
	
		public Integer getReceivedAmount(){
			return this.receivedAmount;
		}
		public void setReceivedAmount(Integer receivedAmount){
			this.receivedAmount = receivedAmount;
		}
	
	
		public String getUnit(){
			return this.unit;
		}
		public void setUnit(String unit){
			this.unit = unit;
		}

        public String getMemo(){
			return this.memo;
		}
		public void setMemo(String memo){
			this.memo = memo;
		}
	
	
		public String getStatusCd(){
			return this.statusCd;
		}
		public void setStatusCd(String statusCd){
			this.statusCd = statusCd;
		}

	/**
	 * toString方法
	 */
	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("itemId",getItemId())
            .append("planId",getPlanId())
            .append("itemNo",getItemNo())
            .append("classCd",getClassCd())
            .append("classNm",getClassNm())
            .append("categoryCd",getCategoryCd())
            .append("categoryNm",getCategoryNm())
            .append("materialCd",getMaterialCd())
            .append("materialNm",getMaterialNm())
            .append("modelNo",getModelNo())
            .append("identificationCd",getIdentificationCd())
            .append("identificationNm",getIdentificationNm())
            .append("sender",getSender())
            .append("receiver",getReceiver())
            .append("sendAmount",getSendAmount())
            .append("receivedAmount",getReceivedAmount())
            .append("unit",getUnit())
            .append("usePlace",getUsePlace())
            .append("memo",getMemo())
            .append("statusCd",getStatusCd())
            .toString();
    }
}