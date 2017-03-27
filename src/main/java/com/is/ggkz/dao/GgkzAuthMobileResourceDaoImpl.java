package com.is.ggkz.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.base.mybatis.Mybatis3Dao;
import com.is.ggkz.entity.GgkzAuthMobileResource;

/**
 * 
 * @ClassName: GgkzAuthMobileResourceDaoImpl
 * @Description: GgkzAuthMobileResource表对应的数据库操作类
 * @author 
 * @date 2013-02-27 14:19:31 *
 */
@Component
public class GgkzAuthMobileResourceDaoImpl extends
		Mybatis3Dao<GgkzAuthMobileResource> {

	@SuppressWarnings("unused")
	private static final Logger LOGGER = LoggerFactory
			.getLogger(GgkzAuthMobileResourceDaoImpl.class);

	protected static final String NAMESPACE = "GgkzAuthMobileResource";

	@Override
	public String getIbatisMapperNamesapce() {
		return NAMESPACE;
	}

	/**
	 * 批量插入权限与资源关联。
	 * 
	 * @author 
	 * @param resources
	 *            权限资源列表
	 */
	public int batchInsert(List<GgkzAuthMobileResource> resources) {
		return getSqlSessionTemplate().insert(
				"GgkzAuthMobileResource.batchInsert", resources);
	}

	/**
	 * 根据权限Id批量删除权限资源
	 * 
	 * @author 
	 * @param resources
	 * @return
	 */
	public int batchDelete(GgkzAuthMobileResource resource) {
		return deleteByEntity(resource);
	}

	public List<String> selectAuthResourceByRoles(List<String> roles) {
		if (roles == null || roles.size() < 1) {
			return null;
		}
		return getSqlSessionTemplate().selectList(
				"GgkzAuthMobileResource.selectAuthResourceByRoles", roles);
	}
}