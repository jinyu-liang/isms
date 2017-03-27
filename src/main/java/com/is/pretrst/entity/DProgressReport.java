package com.is.pretrst.entity;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.base.mybatis.AbstractBaseEntity;

/**
 *
 * @ClassName: DProgressReport
 * @Description: DProgressReport表的对应的java对象，该文件为工具自动生成，请勿手工修改!
 * @author 
 * @date 2013-09-10 10:26:30
 *
 */
public class DProgressReport extends AbstractBaseEntity{

    private static final long serialVersionUID = 1L;
    
	
	/* 报告Id */
    public static final String ALIAS_REPORT_ID = "reportId";
	
	/* 工程中心编码 */
    public static final String ALIAS_WS_CD = "wsCd";
	
	/* 进度报告日 */
    public static final String ALIAS_REPORT_DT = "reportDt";
	
	/* 用户编码 */
    public static final String ALIAS_USER_CD = "userCd";
	
	/* 创建时间 */
    public static final String ALIAS_CREATE_TM = "createTm";
	
	/* 修改时间 */
    public static final String ALIAS_UPDATE_TM = "updateTm";
	
	/* 报告Id */
		private String reportId;
	
	/* 工程中心编码 */
		private String wsCd;
	
	/* 进度报告日 */
		private Date reportDt;
	
	/* 用户编码 */
		private String userCd;
	
	/* 创建时间 */
		private Date createTm;
	
	/* 修改时间 */
		private Date updateTm;

	
	
	
		public String getReportId(){
			return this.reportId;
		}
		public void setReportId(String reportId){
			this.reportId = reportId;
		}
	
	
		public String getWsCd(){
			return this.wsCd;
		}
		public void setWsCd(String wsCd){
			this.wsCd = wsCd;
		}
	
	
		public Date getReportDt(){
			return this.reportDt;
		}
		public void setReportDt(Date reportDt){
			this.reportDt = reportDt;
		}
	
	
		public String getUserCd(){
			return this.userCd;
		}
		public void setUserCd(String userCd){
			this.userCd = userCd;
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
	
	/* 关联对象 */
    private List<DProgressImage> progressImage;

    public List<DProgressImage> getProgressImage()
    {
        return progressImage;
    }
    public void setProgressImage(List<DProgressImage> progressImage)
    {
        this.progressImage = progressImage;
    }
    /**
	 * toString方法
	 */
	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("reportId",getReportId())
            .append("wsCd",getWsCd())
            .append("reportDt",getReportDt())
            .append("userCd",getUserCd())
            .append("createTm",getCreateTm())
            .append("updateTm",getUpdateTm())
            .toString();
    }

}