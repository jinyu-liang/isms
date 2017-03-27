package com.base.mybatis;

import java.util.List;

/**
 * 
 * @ClassName: BaseIbatis3Dao
 * @Description: 基础DAO接口定义
 * @author
 * @date 2010-11-8 下午03:11:11
 * 
 */
public interface BaseDao<Entity> {

	/**
	 * 新增数据
	 * 
	 * @param entity
	 * @return
	 */
	public int insert(Entity entity);

	/**
	 * 根据主键删除数据
	 * 
	 * @param primaryKey
	 * @return
	 */
	public int deleteByEntity(Entity entity);

	/**
	 * 根据主键更新数据
	 * 
	 * @param entity
	 * @return
	 */
	public int updateByEntity(Entity entity);

	/**
	 * 根据主键查询数据
	 * 
	 * @param primaryKey
	 * @return
	 */
	public Entity selectOneByEntity(Entity entity);

	/**
	 * 查询所有结果集
	 * 
	 * @return
	 */
	public List<Entity> selectAll();

	/**
	 * 根据实体对象查询数据结果集，实体对象中不为空的属性作为查询条件，多个查询条件为与(and)的关系
	 * 
	 * @param entity
	 * @return
	 */
	public List<Entity> selectByEntity(Entity entity);


}