package com.is.utils.ztree;

import com.is.utils.StringUtils;

/**
 * ZTree树状结构的数据实体类
 * 
 * @author 
 * 
 */

public class ZTreeData {

	/**
	 * 数据的编号
	 */
	private String id;

	/**
	 * 数据的父级编号
	 */
	private String pId;

	/**
	 * 数据的名称
	 */
	private String name;

	/**
	 * 数据项指向的URL地址
	 */
	private String url;

	/**
	 * 节点说明
	 */
	private String note;

	/**
	 * 此节点以下是否展开
	 */
	private Boolean open;

	/** 公共属性（扩展属性） */
	private String commonProperty;

	private Boolean checked;

	private Boolean nocheck;

	private String target = "";

	public Boolean getChecked() {
		return checked;
	}

	public Boolean getNocheck() {
		return nocheck;
	}

	public void setNocheck(Boolean nocheck) {
		this.nocheck = nocheck;
	}

	public void setChecked(Boolean checked) {
		this.checked = checked;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public String getCommonProperty() {
		if (StringUtils.isEmpty(commonProperty)) {
			return "";
		}
		return commonProperty;
	}

	public void setCommonProperty(String commonProperty) {
		this.commonProperty = commonProperty;
	}

	public Boolean getOpen() {
		if (open == null) {
			return false;
		}
		return open;
	}

	public void setOpen(Boolean open) {
		this.open = open;
	}

	public String getNote() {
		if (StringUtils.isEmpty(note)) {
			return "";
		}
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getId() {
		if (StringUtils.isEmpty(id)) {
			return "";
		}
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getpId() {
		if (StringUtils.isEmpty(pId)) {
			return "";
		}
		return pId;
	}

	public void setpId(String pId) {
		this.pId = pId;
	}

	public String getName() {
		if (StringUtils.isEmpty(name)) {
			return "";
		}
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		if (StringUtils.isEmpty(url)) {
			return "";
		}
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String toString() {
		if (StringUtils.isEmpty(getUrl())) {
			return "{id:\"" + this.getId() + "\", pId:\"" + this.getpId()
					+ "\", name:\"" + this.getName() + "\", target:\""
					+ this.getTarget() + "\",note:\"" + this.getNote()
					+ "\", open:" + this.getOpen() + ", checked:"
					+ this.getChecked() + ", nocheck:" + this.getNocheck()
					+ "},";
		}
		return "{id:\"" + this.getId() + "\", pId:\"" + this.getpId()
				+ "\", name:\"" + this.getName() + "\", target:\""
				+ this.getTarget() + "\",note:\"" + this.getNote()
				+ "\",url:\"" + this.getUrl() + "\", open:" + this.getOpen()
				+ "},";
	}
}
