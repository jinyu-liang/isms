package com.is.ggkz.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.is.ggkz.dao.GgkzUserRoleDaoImpl;

/**
 *
 * @ClassName: GgkzUserRoleServiceImpl
 * @Description: GgkzUserRole表对应的服务类
 * @author 
 * @date 2013-02-27 14:19:28 *
 */
//Spring Bean的标识.
@Component
// 默认将类中的所有函数纳入事务管理.
@Transactional
public class GgkzUserRoleServiceImpl{
	
	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory
			.getLogger(GgkzUserRoleServiceImpl.class);
	
	public GgkzUserRoleDaoImpl ggkzUserRoleDaoImpl;

	@Autowired
	public void setGgkzUserRoleDaoImpl(GgkzUserRoleDaoImpl ggkzUserRoleDaoImpl) {
		this.ggkzUserRoleDaoImpl = ggkzUserRoleDaoImpl;
	}
}
