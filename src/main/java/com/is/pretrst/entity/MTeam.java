package com.is.pretrst.entity;

import java.util.Date;
import java.util.List;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import com.base.mybatis.AbstractBaseEntity;

/**
 *
 * @ClassName: MTeam
 * @Description: MTeam表的对应的java对象，该文件为工具自动生成，请勿手工修改!
 * @author 
 * @date 2013-12-18 15:07:37
 *
 */
public class MTeam extends AbstractBaseEntity{

    private static final long serialVersionUID = 1L;
    
	
	/* 班组编号 */
    public static final String ALIAS_TEAM_CD = "teamCd";
	
	/* 工程队名称 */
    public static final String ALIAS_TEAM_NM = "teamNm";
	
	/* 工程中心编码 */
    public static final String ALIAS_WS_CD = "wsCd";
	
	/* 工地中心名称 */
    public static final String ALIAS_WS_NM = "wsNm";
	
	/* 施工总人数 */
    public static final String ALIAS_WORKER_COUNT = "workerCount";

    /* 队长联系电话 */
    public static final String ALIAS_MOBILE_TEL = "mobileTel";

    /* 队长身份证号*/
    public static final String ALIAS_IDENTY_CARD_CODE = "identyCardCode";
	
	/* 操作用户Id */
    public static final String ALIAS_OPER_USER_ID = "operUserId";
	
	/* 操作用户名称 */
    public static final String ALIAS_OPER_USER_NAME = "operUserName";
	
	/* 操作时间 */
    public static final String ALIAS_OPER_TIME = "operTime";
	
	/* 班组编号 */
		private String teamCd;
	
	/* 工程队名称 */
		private String teamNm;
	
	/* 工程中心编码 */
		private String wsCd;
	
	/* 工地中心名称 */
		private String wsNm;
	
	/* 队长身份证号 */
		private String identyCardCode;
		
	/* 队长电话 */
		private String mobileTel;
	
	/* 施工总人数 */
		private Integer workerCount;

	/* 操作用户Id */
		private String operUserId;
	
	/* 操作用户名称 */
		private String operUserName;
	
	/* 操作时间 */
		private Date operTime;
		
	/* 本组人员最近更新时间 */
		private Date updateDate;
	
	/* 关联对象 外线人员信息 */
		private List<ExPersonInfo> exPersonList;
	
		
        public List<ExPersonInfo> getExPersonList()
        {
            return exPersonList;
        }
        public void setExPersonList(List<ExPersonInfo> exPersonList)
        {
            this.exPersonList = exPersonList;
        }
        public String getTeamCd(){
			return this.teamCd;
		}
		public void setTeamCd(String teamCd){
			this.teamCd = teamCd;
		}
	
	
		public String getTeamNm(){
			return this.teamNm;
		}
		public void setTeamNm(String teamNm){
			this.teamNm = teamNm;
		}
	
	
		public String getWsCd(){
			return this.wsCd;
		}
		public void setWsCd(String wsCd){
			this.wsCd = wsCd;
		}
	
	
		public String getWsNm(){
			return this.wsNm;
		}
		public void setWsNm(String wsNm){
			this.wsNm = wsNm;
		}
	
	
		public Integer getWorkerCount(){
			return this.workerCount;
		}
		public void setWorkerCount(Integer workerCount){
			this.workerCount = workerCount;
		}
	
	
		public String getOperUserId(){
			return this.operUserId;
		}
		public void setOperUserId(String operUserId){
			this.operUserId = operUserId;
		}
	
	
		public String getOperUserName(){
			return this.operUserName;
		}
		public void setOperUserName(String operUserName){
			this.operUserName = operUserName;
		}
	
	
		public Date getOperTime(){
			return this.operTime;
		}
		public void setOperTime(Date operTime){
			this.operTime = operTime;
		}

        public String getIdentyCardCode()
        {
            return identyCardCode;
        }
        public void setIdentyCardCode(String identyCardCode)
        {
            this.identyCardCode = identyCardCode;
        }
        public String getMobileTel()
        {
            return mobileTel;
        }
        public void setMobileTel(String mobileTel)
        {
            this.mobileTel = mobileTel;
        }
       
        public Date getUpdateDate()
        {
            return updateDate;
        }
        public void setUpdateDate(Date updateDate)
        {
            this.updateDate = updateDate;
        }
    /**
	 * toString方法
	 */
	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("teamCd",getTeamCd())
            .append("teamNm",getTeamNm())
            .append("wsCd",getWsCd())
            .append("wsNm",getWsNm())
            .append("workerCount",getWorkerCount())
            .append("operUserId",getOperUserId())
            .append("operUserName",getOperUserName())
            .append("operTime",getOperTime())
            .append("mobileTel",getMobileTel())
            .append("identyCardCode",getIdentyCardCode())
            .toString();
    }
}