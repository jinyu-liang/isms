package com.base.mybatis;

import java.util.ArrayList;
import java.util.List;

import ognl.Ognl;
import ognl.OgnlException;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @ClassName: BaseIbatis3Dao
 * @Description: @see {@link BaseDao}
 * @author 
 * @date 2010-11-8 下午03:11:11
 * 
 */
public abstract class Mybatis3Dao<Entity> extends
		SqlSessionDaoSupport implements BaseDao<Entity> {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(Mybatis3Dao.class);

	/**
	 * 方法前缀-新增
	 */
	protected final static String METHOD_PREFIX_INSERT = "insert";

	/**
	 * 方法前缀-删除
	 */
	protected final static String METHOD_PREFIX_DELETE = "delete";
	/**
	 * 方法前缀-更新
	 */
	protected final static String METHOD_PREFIX_UPDATE = "update";

	/**
	 * 方法前缀-查询
	 */
	protected final static String METHOD_PREFIX_SELECT = "select";

	public Mybatis3Dao() {
	}

	public int insert(Entity entity) {
		int affectCount = getSqlSessionTemplate().insert(getInsertStatement(),
				entity);
		return affectCount;
	}

	public int deleteByEntity(Entity entity) {
		int affectCount = getSqlSessionTemplate().delete(
				getDeleteByEntityStatement(), entity);
		return affectCount;
	}

	public int updateByEntity(Entity entity) {
		int affectCount = getSqlSessionTemplate().update(
				getUpdateByEntityStatement(), entity);
		return affectCount;
	}

	@SuppressWarnings("unchecked")
	public Entity selectOneByEntity(Entity entity) {
		Object obj = getSqlSessionTemplate().selectOne(
				getSelectOneByEntityStatement(), entity);
		if (obj != null) {
			return (Entity) obj;
		}
		return null;
	}

	public List<Entity> selectAll() {
		return selectByEntity(null);
	}
	


	public List<Entity> selectByEntity(Entity entity) {
		List<Entity> list = getSqlSessionTemplate().selectList(
				getSelectByEntityStatement(), entity);
		return list;
	}

	public Page pageQuery(String statement, Object queryObject) {
		Page page = null;
		try {
			if (queryObject == null) {
				throw new RuntimeException("[分页查询]查询对象不允许为null");
			}
			Integer pageNumber = (Integer) Ognl.getValue("pageNumber",
					queryObject);
			Integer pageSize = (Integer) Ognl.getValue("pageSize", queryObject);

			int offset = 0;
			if (pageNumber != null && pageSize != null) {
				offset = (pageNumber - 1) * pageSize;
			}
			int limit = pageSize.intValue();

			// 获取记录总数
			Integer count = (Integer) getSqlSessionTemplate().selectOne(
					statement + "Count", queryObject);
			// 计算总条目是否大于当前位移，如果小于等于，前移，如果大于，查询
			// 获取当前页数据
			List<Object> data = new ArrayList<Object>();
			if (count.intValue() == 0) {
				offset = 0;
			} else {
				if (count.intValue() <= offset) {
					pageNumber = count.intValue() / pageSize + 1;
					offset = (pageNumber - 1) * pageSize;
				}
				// 获取当前页数据
				data = getSqlSessionTemplate().selectList(statement,
						queryObject, new RowBounds(offset, limit));
			}

			page = new Page();
			page.setData(data);
			System.out.println("----pageNumber--------"+pageNumber);
			System.out.println("-------pageSize-----"+pageSize);
			System.out.println("------count.intValue()------"+count.intValue());
			System.out.println("-------offset-----"+offset);
			System.out.println("-------limit-----"+limit);
			page.setPageNumber(pageNumber);
			page.setPageSize(pageSize);
			page.setCount(count.intValue());
		} catch (OgnlException e) {
			LOGGER.error("分页查询出错!", e);
		}
		return page;
	}

	/**
	 * 获取ibatisMapper的命名空间
	 * 
	 * @return
	 */
	public abstract String getIbatisMapperNamesapce();

	private String getInsertStatement() {
		return getIbatisMapperNamesapce() + "." + METHOD_PREFIX_INSERT;
	}

	private String getDeleteByEntityStatement() {
		return getIbatisMapperNamesapce() + "." + METHOD_PREFIX_DELETE
				+ "ByEntity";
	}

	private String getUpdateByEntityStatement() {
		return getIbatisMapperNamesapce() + "." + METHOD_PREFIX_UPDATE
				+ "ByEntity";
	}

	private String getSelectOneByEntityStatement() {
		return getIbatisMapperNamesapce() + "." + METHOD_PREFIX_SELECT
				+ "OneByEntity";
	}

	private String getSelectByEntityStatement() {
		return getIbatisMapperNamesapce() + "." + METHOD_PREFIX_SELECT
				+ "ByEntity";
	}

}