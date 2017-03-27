package com.is.pretrst.entity;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.base.mybatis.AbstractBaseEntity;

/**
 *
 * @ClassName: ExWorkType
 * @Description: MWork表的对应的java对象，该文件为工具自动生成，请勿手工修改!
 * @author 
 * @date 2013-12-18 15:07:37
 *
 */
public class ExWorkType extends AbstractBaseEntity{

    private static final long serialVersionUID = 1L;
    
	
	/* 班组编号 */
    public static final String ALIAS_TEAM_CD = "workCd";
	
	/* 工程队名称 */
    public static final String ALIAS_TEAM_NM = "workNm";
	
	/* 操作用户Id */
    public static final String ALIAS_OPER_USER_ID = "operUserId";
	
	/* 操作用户名称 */
    public static final String ALIAS_OPER_USER_NAME = "operUserName";
	
	/* 操作时间 */
    public static final String ALIAS_OPER_TIME = "operTime";
	
    
    
    
    
    
    
    
	/* 班组编号 */
		private String workCd;
	
	/* 工程队名称 */
		private String workNm;
	

	/* 操作用户Id */
		private String operUserId;
	
	/* 操作用户名称 */
		private String operUserName;
	
	/* 操作时间 */
		private Date operTime;
		
	/* 本组人员最近更新时间 */
		private Date updateDate;
		
		/* 项目ID */
		public String work_ws_cd;
		
		/* 项目名称 */
		public String work_ws_nm;
		
		/* 工种计价方式，按天，按量，其它 */
		public String work_count_type;
		
		/* 工资待遇*/
		public int work_pay;
	
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
        public String getWorkCd(){
			return this.workCd;
		}
		public void setWorkCd(String workCd){
			this.workCd = workCd;
		}
	
	
		public String getWorkNm(){
			return this.workNm;
		}
		public void setWorkNm(String workNm){
			this.workNm = workNm;
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

       
        public String getWork_ws_cd() {
			return work_ws_cd;
		}
		public void setWork_ws_cd(String work_ws_cd) {
			this.work_ws_cd = work_ws_cd;
		}
		public String getWork_ws_nm() {
			return work_ws_nm;
		}
		public void setWork_ws_nm(String work_ws_nm) {
			this.work_ws_nm = work_ws_nm;
		}
		public String getWork_count_type() {
			return work_count_type;
		}
		public void setWork_count_type(String work_count_type) {
			this.work_count_type = work_count_type;
		}
		public int getWork_pay() {
			return work_pay;
		}
		public void setWork_pay(int work_pay) {
			this.work_pay = work_pay;
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
            .append("workCd",getWorkCd())
            .append("workNm",getWorkNm())
            .append("operUserId",getOperUserId())
            .append("operUserName",getOperUserName())
            .append("operTime",getOperTime())
            .append("work_ws_cd",getWork_ws_cd())
            .append("work_ws_nm",getWork_ws_nm())
            .append("work_count_type",getWork_count_type())
            .append("work_pay",getWork_pay())
            .toString();
    }
}