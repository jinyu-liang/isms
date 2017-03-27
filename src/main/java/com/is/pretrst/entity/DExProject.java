package com.is.pretrst.entity;

import java.util.Date;
import java.util.List;
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
public class DExProject extends AbstractBaseEntity{

    private static final long serialVersionUID = 1L;
    
	
	/* 工程ID */
    public static final String ALIAS_PROJECT_ID = "projectId";
	
	/* 工程中心编码 */
    public static final String ALIAS_WS_CD = "wsCd";
	
	/* 工程名称 */
    public static final String ALIAS_PROJECT_NM = "projectNm";
	
	/* 权重 */
    public static final String ALIAS_WEIGHT = "weight";
	
	/* 合同要求进驻日期 */
    public static final String ALIAS_CONTRACT_START_DATE = "contractStartDate";
	
	/* 起始日期 */
    public static final String ALIAS_START_DATE = "startDate";
	
	/* 合同其他要求 */
    public static final String ALIAS_CONTRACT_OTHER_REQ = "contractOtherReq";
	
	/* 最新报告日 */
    public static final String ALIAS_LAST_REPORT_DT = "lastReportDt";
	
	/* 最新报告ID */
    public static final String ALIAS_LAST_REPORT_ID = "lastReportId";
	
	/* 工程ID */
		private String projectId;
	
	/* 工程中心编码 */
		private String wsCd;
	
    
	/* 工程名称 */
		private String projectNm;
	
	/* 权重 */
		private Double weight;
	
	/* 合同要求进驻日期 */
		private String contractStartDate;
	
	/* 起始日期 */
		private String startDate;
	



		/* 合同其他要求 */
		private String contractOtherReq;
	
	/* 最新报告日 */
		private Date lastReportDt;
	
	/* 最新报告ID */
		private String lastReportId;
		
		private String isUpdate;//是否有修改的权限

	
        public String getProjectId()
        {
            return projectId;
        }
        public void setProjectId(String projectId)
        {
            this.projectId = projectId;
        }
        public String getWsCd(){
			return this.wsCd;
		}
		public void setWsCd(String wsCd){
			this.wsCd = wsCd;
		}
	
	
		public String getProjectNm(){
			return this.projectNm;
		}
		public void setProjectNm(String projectNm){
			this.projectNm = projectNm;
		}
	
	
        public Double getWeight() {
			return weight;
		}
		public void setWeight(Double weight) {
			this.weight = weight;
		}
		public String getContractOtherReq(){
			return this.contractOtherReq;
		}
		public void setContractOtherReq(String contractOtherReq){
			this.contractOtherReq = contractOtherReq;
		}
	
	
		public Date getLastReportDt(){
			return this.lastReportDt;
		}
		public void setLastReportDt(Date lastReportDt){
			this.lastReportDt = lastReportDt;
		}
        public String getLastReportId()
        {
            return lastReportId;
        }
        public void setLastReportId(String lastReportId)
        {
            this.lastReportId = lastReportId;
        }
        public List<DExItem> getdExItems() {
            return dExItems;
        }
        public void setdExItems(List<DExItem> dExItems) {
            this.dExItems = dExItems;
        }

        public String getContractStartDate()
        {
            return contractStartDate;
        }
        public void setContractStartDate(String contractStartDate)
        {
            this.contractStartDate = contractStartDate;
        }
        public String getStartDate()
        {
            return startDate;
        }
        public void setStartDate(String startDate)
        {
            this.startDate = startDate;
        }

        public String getIsUpdate() {
			return isUpdate;
		}
		public void setIsUpdate(String isUpdate) {
			this.isUpdate = isUpdate;
		}

	/* 关联对象 */
    private List<DExItem> dExItems;
    private DExProgress  dExProgresss;
    public DExProgress getdExProgresss()
    {
        return dExProgresss;
    }
    public void setdExProgresss(DExProgress dExProgresss)
    {
        this.dExProgresss = dExProgresss;
    }
    /**
	 * toString方法
	 */
	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("projectId",getProjectId())
            .append("wsCd",getWsCd())
            .append("projectNm",getProjectNm())
            .append("weight",getWeight())
            .append("contractStartDate",getContractStartDate())
            .append("startDate",getStartDate())
            .append("contractOtherReq",getContractOtherReq())
            .append("lastReportDt",getLastReportDt())
            .append("lastReportId",getLastReportId())
            .append("isUpdate",getIsUpdate())
            .toString();
    }
}