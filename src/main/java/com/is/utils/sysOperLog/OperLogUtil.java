package com.is.utils.sysOperLog;

import java.util.Date;

import org.apache.commons.lang.xwork.StringUtils;
import org.springside.modules.utils.spring.SpringContextHolder;

import com.base.security.SpringSecurityUtils;
import com.opensymphony.xwork2.util.logging.Logger;
import com.is.ggkz.entity.ext.UserDetail;
import com.is.sys.entity.SysOperLog;
import com.is.sys.service.SysOperLogServiceImpl;
import com.is.utils.PublicDict;
import com.is.utils.ipUtil.IpUtil;
import com.is.utils.keyUtils.KeyGen;

public class OperLogUtil {
    
	/**
	 * 对模块进行操作 ，记录的系统日志
	 * 
	 * @param busiId
	 *            业务Id 非空
	 * @param modelId
	 *            模块id 非空
	 * @param modelName 非空
	 *            功能名称
	 * @param operFuncCode 非空 操作事项 存入对应编号 字典对应为｛增加1，删除2， 查询3， 修改4，导入5 ，导出6｝｝ 对应如下： PublicDict.OPER_LOG_EVENT_ADD =
	 *            "1"; PublicDict.OPER_LOG_EVENT_DEL = "2";
	 *            PublicDict.OPER_LOG_EVENT_SELECT = "3";
	 *            PublicDict.OPER_LOG_EVENT_UPDATE = "4";
	 *            PublicDict.OPER_LOG_EVENT_IN = "5";
	 *            PublicDict.OPER_LOG_EVENT_OUT = "6";
	 *            操作功能
	 * @param operFuncName 非空 [增加，删除， 查询， 修改，导入 ，导出 ]
	 *            
	 * @param operResult 非空
	 *            操作结果 存入对应编号 ｛成功 1 失败0｝ 对应如下：
	 *            PublicDict.OPER_LOG_RESULT_SUCCESS = "1";
	 *            PublicDict.OPER_LOG_RESULT_ERROR = "0";
	 * @param operResultNote
	 *            操作结果说明
	 * @param operTableName
	 *            操作的表名
	 *            @author 
	 */
	public static void insertOperLog(String busiId, String modelId,
			String modelName, String operFuncCode, String operFuncName,
			String operEvent, String operResult, String operResultNote,
			String operTableName) {
		if (StringUtils.isEmpty(busiId) || StringUtils.isEmpty(modelId)
				|| StringUtils.isEmpty(operFuncCode)
				|| StringUtils.isEmpty(operResult)
				) {
			return;
		}
		// 有了登录信息后再取信息
		// LoginDomain domain = (LoginDomain) ActionContext.getContext()
		// .getSession().get(PublicDict.USERSESSION);
		// if(domain == null){
		// domain = new LoginDomain();
		// domain.setLoginIp(IpUtil.getIpAttr());
		// }

		SysOperLog operLogEntity = new SysOperLog();
		operLogEntity.setLogId(KeyGen.getCommonKeyGen(PublicDict.SYS_OPER_LOG_PREFIX));//通过获取主键方法得到。
		operLogEntity.setBusiId(busiId);
		operLogEntity.setModelId(modelId);
		operLogEntity.setModelName(modelName);
		operLogEntity.setOperFuncCode(operFuncCode);// 增加1，删除2， 查询3， 修改4， 
		operLogEntity.setOperFuncName(operFuncName);
		operLogEntity.setOperEvent(operEvent);
		operLogEntity.setOperResult(operResult);// 成功 1 失败 0
		operLogEntity.setOperateTime(new Date());
		UserDetail user = SpringSecurityUtils.getCurrentUser();
		if(user!=null){
			operLogEntity.setUserId(user.getUserId());
			operLogEntity.setName(user.getUsername());
		}
		operLogEntity.setOperIp("pc机:"+IpUtil.getIpAttr());
		//operLogEntity.setOperIp(domain.getLoginIp());
		//operLogEntity.setEmpId(domain.getEmpId());
		//operLogEntity.setEmpName(domain.getOperName());
		operLogEntity.setNote(operResultNote);
		//operLogEntity.setOperTableName(operTableName);
		SysOperLogServiceImpl operLogService =(SysOperLogServiceImpl) SpringContextHolder.getBean("sysOperLogServiceImpl");
		operLogService.insert(operLogEntity);
	}
	/**
     * 对模块进行操作 ，记录的系统日志
     * 
     * @param busiId
     *            业务Id 非空
     * @param modelId
     *            模块id 非空
     * @param modelName 非空
     *            功能名称
     * @param operFuncCode 非空 操作事项 存入对应编号 字典对应为｛增加1，删除2， 查询3， 修改4，导入5 ，导出6｝｝ 对应如下： PublicDict.OPER_LOG_EVENT_ADD =
     *            "1"; PublicDict.OPER_LOG_EVENT_DEL = "2";
     *            PublicDict.OPER_LOG_EVENT_SELECT = "3";
     *            PublicDict.OPER_LOG_EVENT_UPDATE = "4";
     *            PublicDict.OPER_LOG_EVENT_IN = "5";
     *            PublicDict.OPER_LOG_EVENT_OUT = "6";
     *            操作功能
     * @param operFuncName 非空 [增加，删除， 查询， 修改，导入 ，导出 ]
     *            
     * @param operResult 非空
     *            操作结果 存入对应编号 ｛成功 1 失败0｝ 对应如下：
     *            PublicDict.OPER_LOG_RESULT_SUCCESS = "1";
     *            PublicDict.OPER_LOG_RESULT_ERROR = "0";
     * @param userId 操作的用户id           
     * @param operResultNote
     *            操作结果说明
     * @param operTableName
     *            操作的表名
     *            @author 
     */
	public static void insertMobileOperLog(String busiId,String userId,String modelId,
	        String modelName, String operFuncCode, String operFuncName,
	        String operEvent, String operResult, String operResultNote,
	        String operTableName) {
	    if (StringUtils.isEmpty(busiId) || StringUtils.isEmpty(modelId)
	            || StringUtils.isEmpty(operFuncCode)
	            || StringUtils.isEmpty(operResult)
	    ) {
	        return;
	    }
	    
	    SysOperLog operLogEntity = new SysOperLog();
	    operLogEntity.setLogId(KeyGen.getCommonKeyGen(PublicDict.SYS_OPER_LOG_PREFIX));//通过获取主键方法得到。
	    operLogEntity.setBusiId(busiId);
	    operLogEntity.setModelId(modelId);
	    operLogEntity.setModelName(modelName);
	    operLogEntity.setOperFuncCode(operFuncCode);// 增加1，删除2， 查询3， 修改4， 
	    operLogEntity.setOperFuncName(operFuncName);
	    operLogEntity.setOperEvent(operEvent);
	    operLogEntity.setOperResult(operResult);// 成功 1 失败 0
	    operLogEntity.setOperateTime(new Date());
	    //UserDetail user = SpringSecurityUtils.getCurrentUser();
        operLogEntity.setUserId(userId);
        operLogEntity.setName(userId);
	    operLogEntity.setOperIp("手持终端:"+IpUtil.getIpAttr());
	    operLogEntity.setNote(operResultNote);
	    SysOperLogServiceImpl operLogService =(SysOperLogServiceImpl) SpringContextHolder.getBean("sysOperLogServiceImpl");
	    operLogService.insert(operLogEntity);
	}
}
