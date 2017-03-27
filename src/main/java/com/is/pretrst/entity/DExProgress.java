package com.is.pretrst.entity;

import java.util.List;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import com.base.mybatis.AbstractBaseEntity;

/**
 *
 * @ClassName: DExProgress
 * @Description: DExProgress表的对应的java对象，该文件为工具自动生成，请勿手工修改!
 * @author 
 * @date 2013-09-10 10:25:54
 *
 */
public class DExProgress extends AbstractBaseEntity{

    private static final long serialVersionUID = 1L;
    
	
	/* 报告Id */
    public static final String ALIAS_REPORT_ID = "reportId";
	
	/* 工程ID */
    public static final String ALIAS_PROJECT_ID = "projectId";
	
	/* 进度状况 */
    public static final String ALIAS_PROGRESS_STATUS = "progressStatus";
	
	/* 现场反馈问题 */
    public static final String ALIAS_FB_WORKSHOP = "fbWorkshop";
	
	/* 厂内发货问题 */
    public static final String ALIAS_FB_DELIVERY = "fbDelivery";
	
	/* 质量问题反馈 */
    public static final String ALIAS_FB_QUALITY = "fbQuality";
	
	/* 安全问题反馈 */
    public static final String ALIAS_FB_SECURITY = "fbSecurity";
	
	/* 文明施工问题 */
    public static final String ALIAS_FB_MANNER = "fbManner";
	
	/* 主材情况 */
    public static final String ALIAS_FB_MMATERIAL = "fbMmaterial";
	
	/* 配件情况 */
    public static final String ALIAS_FB_SMATERIAL = "fbSmaterial";
	
	/* 设备情况 */
    public static final String ALIAS_FB_EQUIPMENT = "fbEquipment";
	
	/* 计划定额费用 */
    public static final String ALIAS_TOTAL_COST = "totalCost";
	
	/* 实际费用 */
    public static final String ALIAS_CURRENT_COST = "currentCost";
	
	/* 总费用 */
    public static final String ALIAS_TOTAL_EXPENSE = "totalExpense";
	
	/* 易耗费用已发额 */
    public static final String ALIAS_CURRENT_EXPENSE = "currentExpense";
	
	/* 施工队长 */
    public static final String ALIAS_TEAM_LEADER = "teamLeader";
	
	/* 主要焊工 */
    public static final String ALIAS_WELDER = "welder";
	
	/* 主要铆工 */
    public static final String ALIAS_RIVETER = "riveter";
	
	/* 施工总人数 */
    public static final String ALIAS_WORKER_COUNT = "workerCount";
	
	/* 分管副部长 */
    public static final String ALIAS_VICE_MANAGER = "viceManager";
	
	/* 现场带队人员 */
    public static final String ALIAS_WS_LEADER = "wsLeader";
	
	/* 备注 */
    public static final String ALIAS_MEMO = "memo";
	
	/* 报告Id */
		private String reportId;
	
	/* 工程ID */
		private String projectId;
	
	/* 进度状况 */
		private String progressStatus;
	
	/* 现场反馈问题 */
		private String fbWorkshop;
	
	/* 厂内发货问题 */
		private String fbDelivery;
	
	/* 质量问题反馈 */
		private String fbQuality;
	
	/* 安全问题反馈 */
		private String fbSecurity;
	
	/* 文明施工问题 */
		private String fbManner;
	
	/* 主材情况 */
		private String fbMmaterial;
	
	/* 配件情况 */
		private String fbSmaterial;
	
	/* 设备情况 */
		private String fbEquipment;
	
	/* 计划定额费用 */
		private Float totalCost;
	
	/* 实际费用 */
		private Float currentCost;
	
	/* 总费用 */
		private Float totalExpense;
	
	/* 易耗费用已发额 */
		private Float currentExpense;
	
	/* 施工队长 */
		private String teamLeader;
	
	/* 主要焊工 */
		private String welder;
	
	/* 主要铆工 */
		private String riveter;
	
	/* 施工总人数 */
		private Integer workerCount;
	
	/* 分管副部长 */
		private String viceManager;
	
	/* 现场带队人员 */
		private String wsLeader;
	
	
	/* 备注 */
		private String memo;
		
	/*是否有更新的权限*/
		private String isUpdate;
		
	/**dexproject 对象的属性
		private String pProjectId;
        private String pWsCd;
        private String pProjectNm;
        private String pWeight;
        private String pContractStartDate;
        private String pStartDate;
        private String pContractOtherReq;
        private String pLastReportDt;
        private String pLastReportId;
	*/
	
		
        public String getReportId(){
			return this.reportId;
		}
		public void setReportId(String reportId){
			this.reportId = reportId;
		}
	
        public String getProjectId()
        {
            return projectId;
        }
        public void setProjectId(String projectId)
        {
            this.projectId = projectId;
        }
        public String getProgressStatus(){
			return this.progressStatus;
		}
		public void setProgressStatus(String progressStatus){
			this.progressStatus = progressStatus;
		}
	
	
		public String getFbWorkshop(){
			return this.fbWorkshop;
		}
		public void setFbWorkshop(String fbWorkshop){
			this.fbWorkshop = fbWorkshop;
		}
	
	
		public String getFbDelivery(){
			return this.fbDelivery;
		}
		public void setFbDelivery(String fbDelivery){
			this.fbDelivery = fbDelivery;
		}
	
	
		public String getFbQuality(){
			return this.fbQuality;
		}
		public void setFbQuality(String fbQuality){
			this.fbQuality = fbQuality;
		}
	
	
		public String getFbSecurity(){
			return this.fbSecurity;
		}
		public void setFbSecurity(String fbSecurity){
			this.fbSecurity = fbSecurity;
		}
	
	
		public String getFbManner(){
			return this.fbManner;
		}
		public void setFbManner(String fbManner){
			this.fbManner = fbManner;
		}
	
	
		public String getFbMmaterial(){
			return this.fbMmaterial;
		}
		public void setFbMmaterial(String fbMmaterial){
			this.fbMmaterial = fbMmaterial;
		}
	
	
		public String getFbSmaterial(){
			return this.fbSmaterial;
		}
		public void setFbSmaterial(String fbSmaterial){
			this.fbSmaterial = fbSmaterial;
		}
	
	
		public String getFbEquipment(){
			return this.fbEquipment;
		}
		public void setFbEquipment(String fbEquipment){
			this.fbEquipment = fbEquipment;
		}
	
	
		public Float getTotalCost(){
			return this.totalCost;
		}
		public void setTotalCost(Float totalCost){
			this.totalCost = totalCost;
		}
	
	
		public Float getCurrentCost(){
			return this.currentCost;
		}
		public void setCurrentCost(Float currentCost){
			this.currentCost = currentCost;
		}
	
	
		public Float getTotalExpense(){
			return this.totalExpense;
		}
		public void setTotalExpense(Float totalExpense){
			this.totalExpense = totalExpense;
		}
	
	
		public Float getCurrentExpense(){
			return this.currentExpense;
		}
		public void setCurrentExpense(Float currentExpense){
			this.currentExpense = currentExpense;
		}
	
	
		public String getTeamLeader(){
			return this.teamLeader;
		}
		public void setTeamLeader(String teamLeader){
			this.teamLeader = teamLeader;
		}
	
	
		public String getWelder(){
			return this.welder;
		}
		public void setWelder(String welder){
			this.welder = welder;
		}
	
	
		public String getRiveter(){
			return this.riveter;
		}
		public void setRiveter(String riveter){
			this.riveter = riveter;
		}
	
	
		public Integer getWorkerCount(){
			return this.workerCount;
		}
		public void setWorkerCount(Integer workerCount){
			this.workerCount = workerCount;
		}
	
	
		public String getViceManager(){
			return this.viceManager;
		}
		public void setViceManager(String viceManager){
			this.viceManager = viceManager;
		}
	
	
		public String getWsLeader(){
			return this.wsLeader;
		}
		public void setWsLeader(String wsLeader){
			this.wsLeader = wsLeader;
		}
	
	
		public String getMemo(){
			return this.memo;
		}
		public void setMemo(String memo){
			this.memo = memo;
		}

	/**
         * @return Returns the pProjectId.
         
        public String getpProjectId()
        {
            return pProjectId;
        }
        public void setpProjectId(String pProjectId)
        {
            this.pProjectId = pProjectId;
        }
        public String getpWsCd()
        {
            return pWsCd;
        }
        public void setpWsCd(String pWsCd)
        {
            this.pWsCd = pWsCd;
        }
        public String getpProjectNm()
        {
            return pProjectNm;
        }
        public void setpProjectNm(String pProjectNm)
        {
            this.pProjectNm = pProjectNm;
        }
        public String getpWeight()
        {
            return pWeight;
        }
        public void setpWeight(String pWeight)
        {
            this.pWeight = pWeight;
        }
        public String getpContractStartDate()
        {
            return pContractStartDate;
        }
        public void setpContractStartDate(String pContractStartDate)
        {
            this.pContractStartDate = pContractStartDate;
        }
        public String getpStartDate()
        {
            return pStartDate;
        }
        public void setpStartDate(String pStartDate)
        {
            this.pStartDate = pStartDate;
        }
        public String getpContractOtherReq()
        {
            return pContractOtherReq;
        }
        public void setpContractOtherReq(String pContractOtherReq)
        {
            this.pContractOtherReq = pContractOtherReq;
        }
        public String getpLastReportDt()
        {
            return pLastReportDt;
        }
        public void setpLastReportDt(String pLastReportDt)
        {
            this.pLastReportDt = pLastReportDt;
        }
        public String getpLastReportId()
        {
            return pLastReportId;
        }
        public void setpLastReportId(String pLastReportId)
        {
            this.pLastReportId = pLastReportId;
        }*/

    /* 关联对象 */
    private List<DExItem> exItems;
    
    private DProgressReport progressReport;
    
    private DExProject  dexProject;
    
    private List<String> dprogressImage;
   
    public DProgressReport getProgressReport()
    {
        return progressReport;
    }
    public void setProgressReport(DProgressReport progressReport)
    {
        this.progressReport = progressReport;
    }
    public void setExItems(List<DExItem> exItems)
    {
        this.exItems = exItems;
    }
    public List<DExItem> getExItems()
    {
        return exItems;
    }
    
    public DExProject getDexProject()
    {
        return dexProject;
    }
    public void setDexProject(DExProject dexProject)
    {
        this.dexProject = dexProject;
    }

    public List<String> getDprogressImage()
    {
        return dprogressImage;
    }
    public void setDprogressImage(List<String> dprogressImage)
    {
        this.dprogressImage = dprogressImage;
    }
    public String getIsUpdate()
    {
        return isUpdate;
    }
    public void setIsUpdate(String isUpdate)
    {
        this.isUpdate = isUpdate;
    }
    /**
	 * toString方法
	 */
	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("reportId",getReportId())
            .append("projectId",getProjectId())
            .append("progressStatus",getProgressStatus())
            .append("fbWorkshop",getFbWorkshop())
            .append("fbDelivery",getFbDelivery())
            .append("fbQuality",getFbQuality())
            .append("fbSecurity",getFbSecurity())
            .append("fbManner",getFbManner())
            .append("fbMmaterial",getFbMmaterial())
            .append("fbSmaterial",getFbSmaterial())
            .append("fbEquipment",getFbEquipment())
            .append("totalCost",getTotalCost())
            .append("currentCost",getCurrentCost())
            .append("totalExpense",getTotalExpense())
            .append("currentExpense",getCurrentExpense())
            .append("teamLeader",getTeamLeader())
            .append("welder",getWelder())
            .append("riveter",getRiveter())
            .append("workerCount",getWorkerCount())
            .append("viceManager",getViceManager())
            .append("wsLeader",getWsLeader())
            .append("memo",getMemo())
            .toString();
    }

}