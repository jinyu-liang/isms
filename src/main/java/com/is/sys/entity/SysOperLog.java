package com.is.sys.entity;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.base.mybatis.AbstractBaseEntity;

/**
 *
 * @ClassName: SysOperLog
 * @Description: SysOperLog表的对应的java对象，该文件为工具自动生成，请勿手工修改!
 * @author 
 * @date 2013-02-27 14:20:26
 *
 */
public class SysOperLog extends AbstractBaseEntity{

    private static final long serialVersionUID = 1L;
    
	
	/* 日志id */
    public static final String ALIAS_LOG_ID = "logId";
	
	/* 业务Id */
    public static final String ALIAS_BUSI_ID = "busiId";
	
	/* 模块编码 */
    public static final String ALIAS_MODEL_ID = "modelId";
	
	/* 模块名称 */
    public static final String ALIAS_MODEL_NAME = "modelName";
	
	/* 操作功能代码 */
    public static final String ALIAS_OPER_FUNC_CODE = "operFuncCode";
	
	/* 操作功能名称 */
    public static final String ALIAS_OPER_FUNC_NAME = "operFuncName";

    /* 操作功能事件 */
    public static final String ALIAS_OPER_EVENT = "operEvent";
	
	/* 操作结果 */
    public static final String ALIAS_OPER_RESULT = "operResult";
	
	/* 操作时间 */
    public static final String ALIAS_OPERATE_TIME = "operateTime";
	
	/* 用户Id */
    public static final String ALIAS_USER_ID = "userId";
	
	/* 姓名 */
    public static final String ALIAS_NAME = "name";
	
	/* 操作人Ip */
    public static final String ALIAS_OPER_IP = "operIp";
	
	/* 备注 */
    public static final String ALIAS_NOTE = "note";
	
	/* 日志id */
		private String logId;
	
	/* 业务Id */
		private String busiId;
	
	/* 模块编码 */
		private String modelId;
	
	/* 模块名称 */
		private String modelName;
	
	/* 操作功能代码 */
		private String operFuncCode;
	
	/* 操作功能名称 */
		private String operFuncName;

	/* 操作功能事件 */
		private String operEvent;
	
	/* 操作结果 */
		private String operResult;
	
	/* 操作时间 */
		private Date operateTime;
	
	/* 用户Id */
		private String userId;
	
	/* 姓名 */
		private String name;
	
	/* 操作人Ip */
		private String operIp;
	
	/* 备注 */
		private String note;

	
	
	
		public String getLogId(){
			return this.logId;
		}
		public void setLogId(String logId){
			this.logId = logId;
		}
	
	
		public String getBusiId(){
			return this.busiId;
		}
		public void setBusiId(String busiId){
			this.busiId = busiId;
		}
	
	
		public String getModelId(){
			return this.modelId;
		}
		public void setModelId(String modelId){
			this.modelId = modelId;
		}
	
	
		public String getModelName(){
			return this.modelName;
		}
		public void setModelName(String modelName){
			this.modelName = modelName;
		}
	
	
		public String getOperFuncCode(){
			return this.operFuncCode;
		}
		public void setOperFuncCode(String operFuncCode){
			this.operFuncCode = operFuncCode;
		}
	
	
		public String getOperFuncName(){
			return this.operFuncName;
		}
		public void setOperFuncName(String operFuncName){
			this.operFuncName = operFuncName;
		}
	
	
		public String getOperResult(){
			return this.operResult;
		}
		public void setOperResult(String operResult){
			this.operResult = operResult;
		}
	
	
		public Date getOperateTime(){
			return this.operateTime;
		}
		public void setOperateTime(Date operateTime){
			this.operateTime = operateTime;
		}
	
	
		public String getUserId(){
			return this.userId;
		}
		public void setUserId(String userId){
			this.userId = userId;
		}
	
	
		public String getName(){
			return this.name;
		}
		public void setName(String name){
			this.name = name;
		}
	
	
		public String getOperIp(){
			return this.operIp;
		}
		public void setOperIp(String operIp){
			this.operIp = operIp;
		}
	
	
		public String getNote(){
			return this.note;
		}
		public void setNote(String note){
			this.note = note;
		}

        public String getOperEvent()
        {
            return operEvent;
        }
        public void setOperEvent(String operEvent)
        {
            this.operEvent = operEvent;
        }
    /**
	 * toString方法
	 */
	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("logId",getLogId())
            .append("busiId",getBusiId())
            .append("modelId",getModelId())
            .append("modelName",getModelName())
            .append("operFuncCode",getOperFuncCode())
            .append("operFuncName",getOperFuncName())
            .append("operResult",getOperResult())
            .append("operateTime",getOperateTime())
            .append("userId",getUserId())
            .append("name",getName())
            .append("operIp",getOperIp())
            .append("note",getNote())
            .toString();
    }
}