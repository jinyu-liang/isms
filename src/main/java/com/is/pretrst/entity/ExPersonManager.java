package com.is.pretrst.entity;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.base.mybatis.AbstractBaseEntity;

/**
 *
 * @ClassName: ExPersonManager
 * @Description: ExPersonManager表的对应的java对象，该文件为工具自动生成，请勿手工修改!
 * @author 
 * @date 2013-09-11 17:24:48
 *
 */
public class ExPersonManager extends AbstractBaseEntity
{

    private static final long  serialVersionUID       = 1L;

    /* 报告Id */
    public static final String ALIAS_REPORT_ID        = "reportId";

    /* 工作中心编码 */
    public static final String ALIAS_WORK_CENTER_ID   = "workCenterId";

    /* 计划表标题 */
    public static final String ALIAS_TITLE            = "title";

    /* 班组编号 */
    public static final String ALIAS_TEAM_CD          = "teamCd";

    /* 报告用户编码 */
    public static final String ALIAS_REPORT_USER_CD   = "reportUserCd";

    /* 报告用户名称 */
    public static final String ALIAS_REPORT_USER_NAME = "reportUserName";

    /* 报告时间 */
    public static final String ALIAS_REPORT_TM        = "reportTm";

    /* 备注 */
    public static final String ALIAS_MEMO             = "memo";

    /* 处理用户编码 */
    public static final String ALIAS_PROCESS_USER_CD  = "processUserCd";

    /* 处理时间 */
    public static final String ALIAS_PROCESS_TM       = "processTm";

    /* 处理意见 */
    public static final String ALIAS_DEAL_COMMENT     = "dealComment";

    /* 状态 */
    public static final String ALIAS_STATUS_CD        = "statusCd";

    /* 报告Id */
    private String             reportId;

    /* 工作中心编码 */
    private String             workCenterId;

    /* 计划表标题 */
    private String             title;

    /* 班组编号 */
    private String             teamCd;

    /* 报告用户编码 */
    private String             reportUserCd;

    /* 报告用户名称 */
    private String             reportUserName;

    /* 报告时间 */
    private Date               reportTm;

    /* 备注 */
    private String             memo;

    /* 处理用户编码 */
    private String             processUserCd;

    /* 处理用户编码 */
    private String             processUserName;

    /* 处理时间 */
    private Date               processTm;

    /* 处理意见 */
    private String             dealComment;

    /* 状态 */
    private String             statusCd;

    /* 图片文件路径*/
    private List<String>       fileName;

    /* 报告人电话*/
    private String             reportUserTel;

    public String getReportId()
    {
        return this.reportId;
    }

    public void setReportId(String reportId)
    {
        this.reportId = reportId;
    }

    public String getWorkCenterId()
    {
        return this.workCenterId;
    }

    public void setWorkCenterId(String workCenterId)
    {
        this.workCenterId = workCenterId;
    }

    public String getTitle()
    {
        return this.title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getTeamCd()
    {
        return this.teamCd;
    }

    public void setTeamCd(String teamCd)
    {
        this.teamCd = teamCd;
    }

    public String getReportUserCd()
    {
        return this.reportUserCd;
    }

    public void setReportUserCd(String reportUserCd)
    {
        this.reportUserCd = reportUserCd;
    }

    public String getReportUserName()
    {
        return this.reportUserName;
    }

    public void setReportUserName(String reportUserName)
    {
        this.reportUserName = reportUserName;
    }

    public Date getReportTm()
    {
        return this.reportTm;
    }

    public void setReportTm(Date reportTm)
    {
        this.reportTm = reportTm;
    }

    public String getMemo()
    {
        return this.memo;
    }

    public void setMemo(String memo)
    {
        this.memo = memo;
    }

    public String getProcessUserCd()
    {
        return this.processUserCd;
    }

    public void setProcessUserCd(String processUserCd)
    {
        this.processUserCd = processUserCd;
    }

    public Date getProcessTm()
    {
        return this.processTm;
    }

    public void setProcessTm(Date processTm)
    {
        this.processTm = processTm;
    }

    public String getDealComment()
    {
        return this.dealComment;
    }

    public void setDealComment(String dealComment)
    {
        this.dealComment = dealComment;
    }

    public String getStatusCd()
    {
        return this.statusCd;
    }

    public void setStatusCd(String statusCd)
    {
        this.statusCd = statusCd;
    }

    public String getProcessUserName()
    {
        return processUserName;
    }

    public void setProcessUserName(String processUserName)
    {
        this.processUserName = processUserName;
    }

    public String getReportUserTel()
    {
        return reportUserTel;
    }

    public void setReportUserTel(String reportUserTel)
    {
        this.reportUserTel = reportUserTel;
    }

    /**
     * toString方法
     */
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("reportId", getReportId()).append("workCenterId", getWorkCenterId())
                .append("title", getTitle()).append("teamCd", getTeamCd()).append("reportUserCd", getReportUserCd())
                .append("reportUserName", getReportUserName()).append("reportTm", getReportTm()).append("memo", getMemo())
                .append("processUserCd", getProcessUserCd()).append("processTm", getProcessTm()).append("dealComment", getDealComment())
                .append("statusCd", getStatusCd()).append("reportUserTel", getReportUserTel()).toString();
    }

    public void setFileName(List<String> fileName)
    {
        this.fileName = fileName;
    }

    public List<String> getFileName()
    {
        return fileName;
    }
}