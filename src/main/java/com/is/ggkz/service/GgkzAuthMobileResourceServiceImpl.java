package com.is.ggkz.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

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
public class GgkzAuthMobileResourceServiceImpl {
	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory
			.getLogger(GgkzAuthMobileResourceServiceImpl.class);

	public GgkzAuthMobileResourceServiceImpl ggkzAuthMobileResourceServiceImpl;

	public void setGgkzAuthMobileResourceServiceImpl(
			GgkzAuthMobileResourceServiceImpl ggkzAuthMobileResourceServiceImpl) {
		this.ggkzAuthMobileResourceServiceImpl = ggkzAuthMobileResourceServiceImpl;
	}



}
