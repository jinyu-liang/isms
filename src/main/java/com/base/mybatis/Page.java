package com.base.mybatis;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @ClassName: Page
 * @Description: 分页对象
 * @author 
 * @date 2010-11-8 下午05:12:11
 * 
 */
public class Page implements Serializable {

	private static final long serialVersionUID = 1L;

	/* 分页数据对象列表 */
	private List<?> data;

	/* 记录总数 */
	private int count;

	/* 每页显示记录个数 */
	private int pageSize;

	/* 每页显示记录个数列表 */
	private List<String> pageSizeList;

	/* 总页数 */
	private int pageCount;

	/* 当前页数 */
	private int pageNumber;

	/* 页面上显示的页码个数 */
	private int pageNumShown;

	private void init() {
		count = 0;
		pageSize = 20;
		pageSizeList = new ArrayList<String>();
		pageSizeList.add("20");
		pageSizeList.add("50");
		pageSizeList.add("100");
		pageSizeList.add("300");
		pageSizeList.add("1000");
		pageCount = 1;
		pageNumber = 1;
		pageNumShown = 10;
	}

	public Page() {
		init();
	}

	/**
	 * 获取当前页中的数据记录
	 * 
	 * @return
	 */
	public List<?> getData() {
		return data;
	}

	public void setData(List<?> data) {
		this.data = data;
	}

	/**
	 * 获取记录总数
	 * 
	 * @return
	 */
	public int getCount() {
		return count;
	}

	/**
	 * 设置总记录数，同时计算出总页数
	 * 
	 * @param count
	 */
	public void setCount(int count) {
		if (pageSize != 0) {
			pageCount = count / pageSize;
			if (count % pageSize != 0) {
				pageCount++;
			}
		}
		this.count = count;
	}

	/**
	 * 获取每页显示记录个数
	 * 
	 * @return
	 */
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List<String> getPageSizeList() {
		return pageSizeList;
	}

	/**
	 * 获取总页数
	 * 
	 * @return
	 */
	public int getPageCount() {
		return pageCount;
	}

	/**
	 * 获取当前页数
	 * 
	 * @return
	 */
	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	/**
	 * 该页是否有下一页.
	 */
	public boolean hasNextPage() {
		return getPageNumber() < getPageCount();
	}

	/**
	 * 该页是否有上一页.
	 */
	public boolean hasPreviousPage() {
		return getPageNumber() > 1;
	}

	public int getPageNumShown() {
		return pageNumShown;
	}

	public void setPageNumShown(int pageNumShown) {
		this.pageNumShown = pageNumShown;
	}
}