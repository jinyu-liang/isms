package com.is.ggkz.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.base.mybatis.Mybatis3Dao;
import com.is.ggkz.entity.GgkzUserRole;

/**
 * 
 * @ClassName: GgkzUserRoleDaoImpl
 * @Description: GgkzUserRole表对应的数据库操作类
 * @author 
 * @date 2013-02-27 14:19:27 *
 */
@Component
public class GgkzUserRoleDaoImpl extends Mybatis3Dao<GgkzUserRole> {

	@SuppressWarnings("unused")
	private static final Logger LOGGER = LoggerFactory
			.getLogger(GgkzUserRoleDaoImpl.class);

	protected static final String NAMESPACE = "GgkzUserRole";

	@Override
	public String getIbatisMapperNamesapce() {
		return NAMESPACE;
	}

	/**
	 * 批量增加用户和角色的关联
	 * 
	 * @param userRoleList
	 */
	public void batchInsertUserRole(List<GgkzUserRole> userRoleList) {
		if (userRoleList == null || userRoleList.size() < 1) {
			return;
		}
		getSqlSessionTemplate().insert("GgkzUserRoleExt.batchInsert", userRoleList);
	}
	public List<String> selectRolesByUserId(GgkzUserRole queryEntity) {
		return this.getSqlSessionTemplate().selectList("GgkzUserRole.selectRolesByUserId", queryEntity);
	}
	
	
}