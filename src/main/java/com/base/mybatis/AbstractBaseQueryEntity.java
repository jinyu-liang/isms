package com.base.mybatis;

/**
 * 
 * @ClassName: BaseQuery
 * @Description: 查询基类（所有的查询对性均需要继承此类）
 * @author
 * @date 2010-11-8 下午05:12:54
 * 
 */
public abstract class AbstractBaseQueryEntity extends PageQuery {

	private static final long serialVersionUID = 1L;

	/* 数据库类型 */
	private static final String dbType = "mysql";

	/**
	 * 默认分页列表展示的数据个数
	 */
	public static final int DEFAULT_PAGE_SIZE = 20;

	public AbstractBaseQueryEntity() {
		super(1, DEFAULT_PAGE_SIZE);
	}
	

	/**
	 * 获取数据库类型
	 * 
	 * @return
	 */
	public String getDbType() {
		return dbType;
	}
}