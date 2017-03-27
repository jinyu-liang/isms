package com.is.ggkz.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.is.ggkz.dao.GgkzAuthMobileInfoDaoImpl;
import com.is.ggkz.dao.GgkzAuthMobileResourceDaoImpl;

/**
 * 
 * @ClassName: GgkzAuthMobileInfoServiceImpl
 * @Description: GgkzAuthMobileInfo表对应的服务类
 * @author 
 * @date 2013-02-27 14:19:23 *
 */
// Spring Bean的标识.
@Component
// 默认将类中的所有函数纳入事务管理.
@Transactional
public class GgkzAuthMobileInfoServiceImpl {
	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory
			.getLogger(GgkzAuthInfoServiceImpl.class);

	public GgkzAuthMobileInfoDaoImpl ggkzAuthMobileInfoDaoImpl;

	// 权限分配资源Dao
	public GgkzAuthMobileResourceDaoImpl ggkzAuthMobileResourceDaoImpl;

	public void setGgkzAuthMobileInfoDaoImpl(
			GgkzAuthMobileInfoDaoImpl ggkzAuthMobileInfoDaoImpl) {
		this.ggkzAuthMobileInfoDaoImpl = ggkzAuthMobileInfoDaoImpl;
	}

	public void setGgkzAuthMobileResourceDaoImpl(
			GgkzAuthMobileResourceDaoImpl ggkzAuthMobileResourceDaoImpl) {
		this.ggkzAuthMobileResourceDaoImpl = ggkzAuthMobileResourceDaoImpl;
	}

}
