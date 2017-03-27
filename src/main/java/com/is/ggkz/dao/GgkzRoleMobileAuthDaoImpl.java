package com.is.ggkz.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.base.mybatis.Mybatis3Dao;
import com.is.ggkz.entity.GgkzAuthMobileInfo;
import com.is.ggkz.entity.GgkzRoleMobileAuth;

/**
 * 
 * @ClassName: GgkzRoleMobileAuthDaoImpl
 * @Description: GgkzRoleAuth表对应的数据库操作类
 * @author 
 * @date 2013-02-27 14:19:29 *
 */
@Component
public class GgkzRoleMobileAuthDaoImpl extends Mybatis3Dao<GgkzRoleMobileAuth> {

	@SuppressWarnings("unused")
	private static final Logger LOGGER = LoggerFactory
			.getLogger(GgkzRoleMobileAuthDaoImpl.class);

	protected static final String NAMESPACE = "GgkzRoleMobileAuth";

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
				"GgkzRoleMobileAuth.batchRemoveRoleAuthByPks", ids);
	}

	/**
	 * 根据角色查找该角色下的权限id
	 * 
	 * @param roleIds
	 * @return
	 */
	public List<String> selectAuthIdsByRoles(List<String> roleIds) {
		return getSqlSessionTemplate().selectList(
				"GgkzRoleMobileAuth.selectAuthIdsByRoles", roleIds);
	}
	
	public List<GgkzAuthMobileInfo> selectAuthByRoles(List<String> roleIds){
		return getSqlSessionTemplate().selectList(
				"GgkzRoleMobileAuth.selectAuthByRoles", roleIds);
	}
}