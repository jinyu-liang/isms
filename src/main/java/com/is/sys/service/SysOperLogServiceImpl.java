package com.is.sys.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.base.mybatis.Page;
import com.is.sys.dao.SysOperLogDaoImpl;
import com.is.sys.entity.SysOperLog;
import com.is.sys.entity.query.SysOperLogQuery;

/**
 *
 * @ClassName: SysOperLogServiceImpl
 * @Description: SysOperLog表对应的服务类
 * @author 
 * @date 2013-02-27 14:20:27 *
 */
//Spring Bean的标识.
@Component
// 默认将类中的所有函数纳入事务管理.
@Transactional
public class SysOperLogServiceImpl  {
	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory
			.getLogger(SysOperLogServiceImpl.class);
	
	public SysOperLogDaoImpl sysOperLogDaoImpl;

	@Autowired
	public void setSysOperLogDaoImpl(SysOperLogDaoImpl sysOperLogDaoImpl) {
		this.sysOperLogDaoImpl = sysOperLogDaoImpl;
	}

	/**
	 * 添加日志操作
	 * @author 
	 * @param operLogEntity
	 */
	public int insert(SysOperLog operLogEntity) {
		return sysOperLogDaoImpl.insert(operLogEntity);
		
	}
	/**
	 * 分页查询日志
	 * @author 
	 * @param queryObject
	 * @return
	 */
	public Page pageQuery(SysOperLogQuery queryObject){
		return sysOperLogDaoImpl.pageQuery("SysOperLog.selectByPage", queryObject);
	}

	/**
	 * 查询一个日志
	 * @author 
	 * @param log
	 * @return
	 */
	public SysOperLog selectOneByEntity(SysOperLog entity){
		return sysOperLogDaoImpl.selectOneByEntity(entity);
	}
}
