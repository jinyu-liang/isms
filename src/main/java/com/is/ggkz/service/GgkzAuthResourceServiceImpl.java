package com.is.ggkz.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.is.ggkz.dao.GgkzAuthResourceDaoImpl;

/**
 * 
 * @ClassName: GgkzAuthResourceServiceImpl
 * @Description: GgkzAuthResource表对应的服务类
 * @author 
 * @date 2013-02-27 14:19:32 *
 */
// Spring Bean的标识.
@Component
// 默认将类中的所有函数纳入事务管理.
@Transactional
public class GgkzAuthResourceServiceImpl {
	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory
			.getLogger(GgkzAuthResourceServiceImpl.class);

	public GgkzAuthResourceDaoImpl ggkzAuthResourceDaoImpl;

	@Autowired
	public void setGgkzAuthInfoDaoImpl(
			GgkzAuthResourceDaoImpl ggkzAuthResourceDaoImpl) {
		this.ggkzAuthResourceDaoImpl = ggkzAuthResourceDaoImpl;
	}

}
