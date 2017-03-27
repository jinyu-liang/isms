package com.base.mybatis;

import java.io.Serializable;

/**
 * 
 * @ClassName: PageQueryCondition
 * @Description: 分页查询条件
 * @author 
 * @date 2010-11-8 下午04:17:37
 * 
 */
public class PageQuery implements Serializable {

	private static final long serialVersionUID = 1L;

	/* 分页查询条件 - 页码，从1开始 */
	public Integer pageNumber;

	/* 分页查询条件 - 每页显示记录个数 */
	public Integer pageSize;

	public PageQuery() {
	}

	public PageQuery(int pageNumber, int pageSize) {
		this.pageNumber = pageNumber;
		this.pageSize = pageSize;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(Integer pageNumber) {
		this.pageNumber = pageNumber;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

}
