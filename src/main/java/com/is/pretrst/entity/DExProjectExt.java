package com.is.pretrst.entity;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.base.mybatis.AbstractBaseEntity;

/**
 *
 * @ClassName: DExProject
 * @Description: DExProject表的对应的java对象，该文件为工具自动生成，请勿手工修改!
 * @author 
 * @date 2013-09-10 10:26:07
 *
 */
public class DExProjectExt extends AbstractBaseEntity
{

    private static final long  serialVersionUID     = 1L;

    
    /* 工程id */
    public static final String ALIAS_PROJECT_ID     = "projectId";
    /* 工程名称 */
    public static final String ALIAS_PROJECT_NM     = "projectNm";

  
    /* 工地代码 */
    public static final String ALIAS_WS_CD     = "WsCd";

    /* 权重 */
    public static final String ALIAS_WEIGHT         = "weight";

    /* 起始日期 */
    public static final String ALIAS_START_DATE     = "startDate";

    /* 最新报告日 */
    public static final String ALIAS_LAST_REPORT_DT = "lastReportDt";

    /* 最新报告ID */
    public static final String ALIAS_LAST_REPORT_ID = "lastReportId";
    
    /*进度状况*/
    public static final String ALIAS_PROGRESS_STATUS = "progressStatus";

    
    /* 工程id */
    private String             projectId;
    /* 工程名称 */
    private String             projectNm;
    
    /*工地id*/
    private String             wsCd;
    /* 净重 */
    private Double             weight;

    /* 起始日期 */
    private String             startDate;

    /* 最新报告日 */
    private Date               lastReportDt;

    /* 最新报告ID */
    private String             lastReportId;

    /*进度状况*/
    private String             progressStatus;



    /**
     * @return Returns the projectNm.
     */
    public String getProjectNm()
    {
        return projectNm;
    }



    /**
     * @param projectNm The projectNm to set.
     */
    public void setProjectNm(String projectNm)
    {
        this.projectNm = projectNm;
    }



    /**
     * @return Returns the weight.
     */
    public Double getWeight()
    {
        return weight;
    }



    /**
     * @param weight The weight to set.
     */
    public void setWeight(Double weight)
    {
        this.weight = weight;
    }



    /**
     * @return Returns the startDate.
     */
    public String getStartDate()
    {
        return startDate;
    }



    /**
     * @param startDate The startDate to set.
     */
    public void setStartDate(String startDate)
    {
        this.startDate = startDate;
    }



    /**
     * @return Returns the lastReportDt.
     */
    public Date getLastReportDt()
    {
        return lastReportDt;
    }



    /**
     * @param lastReportDt The lastReportDt to set.
     */
    public void setLastReportDt(Date lastReportDt)
    {
        this.lastReportDt = lastReportDt;
    }



    /**
     * @return Returns the lastReportId.
     */
    public String getLastReportId()
    {
        return lastReportId;
    }



    /**
     * @param lastReportId The lastReportId to set.
     */
    public void setLastReportId(String lastReportId)
    {
        this.lastReportId = lastReportId;
    }



    /**
     * @return Returns the progressStatus.
     */
    public String getProgressStatus()
    {
        return progressStatus;
    }



    /**
     * @param progressStatus The progressStatus to set.
     */
    public void setProgressStatus(String progressStatus)
    {
        this.progressStatus = progressStatus;
    }

    /**
     * @return Returns the wsCd.
     */
    public String getWsCd()
    {
        return wsCd;
    }

    /**
     * @param wsCd The wsCd to set.
     */
    public void setWsCd(String wsCd)
    {
        this.wsCd = wsCd;
    }



    public String getProjectId() {
		return projectId;
	}



	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}



	/**
     * toString方法
     */
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
		        .append("projectId",getProjectId())
                .append("projectNm",getProjectNm())
                .append("weight",getWeight())
                .append("startDate",getStartDate())
                .append("lastReportDt",getLastReportDt())
                .append("lastReportId",getLastReportId())
                .append("progressStatus",getProgressStatus())
                .append("wsCd",getWsCd())
               .toString();
    }
}