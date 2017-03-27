package com.is.ggkz.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.is.ggkz.dao.GgkzOrgInfoDaoImpl;

/**
 *
 * @ClassName: GgkzOrgInfoServiceImpl
 * @Description: GgkzOrgInfo表对应的服务类
 * @author 
 * @date 2013-02-27 14:19:35 *
 */
//Spring Bean的标识.
@Component
// 默认将类中的所有函数纳入事务管理.
@Transactional
public class GgkzOrgInfoServiceImpl {
	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory
			.getLogger(GgkzOrgInfoServiceImpl.class);
	
	public GgkzOrgInfoDaoImpl ggkzOrgInfoDaoImpl;

	@Autowired
	public void setGgkzOrgInfoDaoImpl(GgkzOrgInfoDaoImpl ggkzOrgInfoDaoImpl) {
		this.ggkzOrgInfoDaoImpl = ggkzOrgInfoDaoImpl;
	}
}
