package com.is.ggkz.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.base.mybatis.Mybatis3Dao;
import com.is.ggkz.entity.GgkzResourceInfo;

/**
 * 
 * @ClassName: GgkzResourceInfoDaoImpl
 * @Description: GgkzResourceInfo表对应的数据库操作类
 * @author 
 * @date 2013-02-27 14:19:24 *
 */
@Component
public class GgkzResourceInfoDaoImpl extends Mybatis3Dao<GgkzResourceInfo> {

	@SuppressWarnings("unused")
	private static final Logger LOGGER = LoggerFactory
			.getLogger(GgkzResourceInfoDaoImpl.class);

	protected static final String NAMESPACE = "GgkzResourceInfo";

	@Override
	public String getIbatisMapperNamesapce() {
		return NAMESPACE;
	}

	/**
	 * 删除多个资源
	 * 
	 * @author 
	 * @return 1:成功 0：失败
	 */
	public int deleteManyResource(List<String> ids) {
		return getSqlSessionTemplate().delete(
				"GgkzResourceInfo.batchRemoveResourceByPks", ids);
	}

	/**
	 * 根据权限id，查找权限id对应的菜单id
	 * 
	 * @param authIds
	 * @return
	 */
	public List<String> selectResourceIdsByAuthIds(List<String> authIds) {
		return getSqlSessionTemplate().selectList(
				"GgkzResourceInfoExt.selectResourceIdsByAuthIds", authIds);
	}

	/**
	 * 根据权限id，查找相应的资源
	 * 
	 * @param authIds
	 * @return
	 */
	public List<GgkzResourceInfo> selectResourceByAuthIds(List<String> authIds) {
		return getSqlSessionTemplate().selectList(
				"GgkzResourceInfoExt.selectResourceByAuthIds", authIds);
	}
}