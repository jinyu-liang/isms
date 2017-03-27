package com.is.pretrst.entity;

import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import com.base.mybatis.AbstractBaseEntity;

/**
 *
 * @ClassName: DRecordReply
 * @Description: DRecordReply表的对应的java对象，该文件为工具自动生成，请勿手工修改!
 * @author 
 * @date 2013-09-10 10:26:34
 *
 */
public class DRecordReply extends AbstractBaseEntity{

    private static final long serialVersionUID = 1L;
    
	
	/* 回复Id */
    public static final String ALIAS_REPLY_ID = "replyId";
	
	/* 流水号 */
    public static final String ALIAS_RECORD_ID = "recordId";
	
	/* 回复类别 */
    public static final String ALIAS_REPLY_TYPE = "recordType";
	
	/* 根回复项目ID */
    public static final String ALIAS_ROOT_REPLY_ID = "rootReplyId";
	
	/* 父回复项目Id */
    public static final String ALIAS_PARENT_REPLY_ID = "parentReplyId";
	
	/* 计划表标题 */
    public static final String ALIAS_TITLE = "title";
	
	/* 内容 */
    public static final String ALIAS_CONTENT = "content";
	
	/* 回复者用户Cd */
    public static final String ALIAS_REPLY_USER_CD = "replyUserCd";
    
    /* 回复者用户NM */
    public static final String ALIAS_REPLY_USER_NM = "replyUserNm";
	
	/* 创建时间 */
    public static final String ALIAS_CREATE_TM = "createTm";
	
	/* 修改时间 */
    public static final String ALIAS_UPDATE_TM = "updateTm";
	
	/* 额外区分 */
    public static final String ALIAS_EXTRA_FLG = "extraFlg";
	
	/* 删除区分 */
    public static final String ALIAS_DELETE_CD = "deleteCd";
	
	/* 回复Id */
		private String replyId;
	
	/* 流水号 */
		private String recordId;
	
	/* 回复类别 */
		private String recordType;
	
	/* 根回复项目ID */
		private String rootReplyId;
	
	/* 父回复项目Id */
		private String parentReplyId;
	
	/* 计划表标题 */
		private String title;
	
	/* 内容 */
		private String content;
	
	/* 回复者用户Cd */
		private String replyUserCd;
		
	/* 回复者用户NM */
		private String replyUserNm;
	
	/* 创建时间 */
		private Date createTm;
	
	/* 修改时间 */
		private Date updateTm;
	
	/* 额外区分 */
		private String extraFlg;
	
	/* 删除区分 */
		private String deleteCd;

	
	
	
		public String getReplyId(){
			return this.replyId;
		}
		public void setReplyId(String replyId){
			this.replyId = replyId;
		}
	
	
		public String getRecordId(){
			return this.recordId;
		}
		public void setRecordId(String recordId){
			this.recordId = recordId;
		}
	
        public String getRecordType()
        {
            return recordType;
        }
        public void setRecordType(String recordType)
        {
            this.recordType = recordType;
        }
        public String getRootReplyId(){
			return this.rootReplyId;
		}
		public void setRootReplyId(String rootReplyId){
			this.rootReplyId = rootReplyId;
		}
	
	
		public String getParentReplyId(){
			return this.parentReplyId;
		}
		public void setParentReplyId(String parentReplyId){
			this.parentReplyId = parentReplyId;
		}
	
	
		public String getTitle(){
			return this.title;
		}
		public void setTitle(String title){
			this.title = title;
		}
	
	
		public String getContent(){
			return this.content;
		}
		public void setContent(String content){
			this.content = content;
		}
	
	
		public String getReplyUserCd(){
			return this.replyUserCd;
		}
		public void setReplyUserCd(String replyUserCd){
			this.replyUserCd = replyUserCd;
		}
	
	
		public Date getCreateTm(){
			return this.createTm;
		}
		public void setCreateTm(Date createTm){
			this.createTm = createTm;
		}
	
	
		public Date getUpdateTm(){
			return this.updateTm;
		}
		public void setUpdateTm(Date updateTm){
			this.updateTm = updateTm;
		}
	
	
		public String getExtraFlg(){
			return this.extraFlg;
		}
		public void setExtraFlg(String extraFlg){
			this.extraFlg = extraFlg;
		}
	
	
		public String getDeleteCd(){
			return this.deleteCd;
		}
		public void setDeleteCd(String deleteCd){
			this.deleteCd = deleteCd;
		}

        public String getReplyUserNm()
        {
            return replyUserNm;
        }
        public void setReplyUserNm(String replyUserNm)
        {
            this.replyUserNm = replyUserNm;
        }
    /**
	 * toString方法
	 */
	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("replyId",getReplyId())
            .append("recordId",getRecordId())
            .append("replyType",getRecordType())
            .append("rootReplyId",getRootReplyId())
            .append("parentReplyId",getParentReplyId())
            .append("title",getTitle())
            .append("content",getContent())
            .append("replyUserCd",getReplyUserCd())
            .append("createTm",getCreateTm())
            .append("updateTm",getUpdateTm())
            .append("extraFlg",getExtraFlg())
            .append("deleteCd",getDeleteCd())
            .append("replyUserNm",getReplyUserNm())
            .toString();
    }
}