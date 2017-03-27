package com.is.ggkz.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.base.mybatis.Mybatis3Dao;
import com.is.ggkz.entity.GgkzAuthInfo;
import com.is.ggkz.entity.GgkzRoleAuth;

/**
 * 
 * @ClassName: GgkzRoleAuthDaoImpl
 * @Description: GgkzRoleAuth表对应的数据库操作类
 * @author 
 * @date 2013-02-27 14:19:29 *
 */
@Component
public class GgkzRoleAuthDaoImpl extends Mybatis3Dao<GgkzRoleAuth> {

	@SuppressWarnings("unused")
	private static final Logger LOGGER = LoggerFactory
			.getLogger(GgkzRoleAuthDaoImpl.class);

	protected static final String NAMESPACE = "GgkzRoleAuth";

	@Override
	public String getIbatisMapperNamesapce() {
		return NAMESPACE;
	}

	/**
	 * 根据角色id删除多个资源 List<String> roleIds
	 * 
	 * @return 其他:成功 ;0：失败
	 */
	public int deleteManyRoleInfo(List<String> ids) {
		return getSqlSessionTemplate().delete(
				"GgkzRoleAuth.batchRemoveRoleAuthByPks", ids);
	}

	/**
	 * 根据角色查找该角色下的权限id
	 * 
	 * @param roleIds
	 * @return
	 */
	public List<String> selectAuthIdsByRoles(List<String> roleIds) {
		return getSqlSessionTemplate().selectList(
				"GgkzRoleAuthExt.selectAuthIdsByRoles", roleIds);
	}
	
	/**
	 * 根据角色查找该角色下的权限id
	 * 
	 * @param roleIds
	 * @return
	 */
	public int roleinsert(GgkzRoleAuth entity) {
		return getSqlSessionTemplate().insert(
				"GgkzRoleAuth.insert", entity);
	}

	/**
	 * 根据角色查找该角色下的权限
	 * 
	 * @param roleIds
	 * @return
	 */
	public List<GgkzAuthInfo> selectAuthByRoles(List<String> roleIds) {
		return getSqlSessionTemplate().selectList(
				"GgkzRoleAuthExt.selectAuthByRoles", roleIds);
	}
}