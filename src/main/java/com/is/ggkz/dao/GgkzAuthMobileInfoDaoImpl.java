package com.is.ggkz.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.base.mybatis.Mybatis3Dao;
import com.is.ggkz.entity.GgkzAuthMobileInfo;

/**
 * 
 * @ClassName: GgkzAuthMobileInfoDaoImpl
 * @Description: GgkzAuthMobileInfo表对应的数据库操作类
 * @author 
 * @date 2013-02-27 14:19:22 *
 */
@Component
public class GgkzAuthMobileInfoDaoImpl extends Mybatis3Dao<GgkzAuthMobileInfo> {

	@SuppressWarnings("unused")
	private static final Logger LOGGER = LoggerFactory
			.getLogger(GgkzAuthMobileInfoDaoImpl.class);

	protected static final String NAMESPACE = "GgkzAuthMobileInfo";

	@Override
	public String getIbatisMapperNamesapce() {
		return NAMESPACE;
	}

}