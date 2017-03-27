package com.is.sys.entity;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.base.mybatis.AbstractBaseEntity;

/**
 * 
 * @ClassName: SysDict
 * @Description: SysDict表的对应的java对象，该文件为工具自动生成，请勿手工修改!
 * @author 
 * @date 2013-02-27 14:20:15
 * 
 */
public class SysDict extends AbstractBaseEntity {

	private static final long serialVersionUID = 1L;

	/* 字典代码 */
	public static final String ALIAS_DICT_CODE = "dictCode";

	/* 字典项名称 */
	public static final String ALIAS_DICT_NAME = "dictName";

	/* 字典类型代码 */
	public static final String ALIAS_DICT_TYPE_CODE = "dictTypeCode";

	/* 序号 */
	public static final String ALIAS_SERIAL_NUMBER = "serialNumber";

	/* 字典代码 */
	private String dictCode;

	/* 字典项名称 */
	private String dictName;

	/* 字典类型代码 */
	private String dictTypeCode;

	/* 序号 */
	private Integer serialNumber;

	public String getDictCode() {
		return this.dictCode;
	}

	public void setDictCode(String dictCode) {
		this.dictCode = dictCode;
	}

	public String getDictName() {
		return this.dictName;
	}

	public void setDictName(String dictName) {
		this.dictName = dictName;
	}

	public String getDictTypeCode() {
		return this.dictTypeCode;
	}

	public void setDictTypeCode(String dictTypeCode) {
		this.dictTypeCode = dictTypeCode;
	}

	public Integer getSerialNumber() {
		return this.serialNumber;
	}

	public void setSerialNumber(Integer serialNumber) {
		this.serialNumber = serialNumber;
	}

	/* 关联对象 */
    private SysDictType sysDictTypes;
   

	/**
	 * @return the sysDictTypes
	 */
	public SysDictType getSysDictTypes() {
		return sysDictTypes;
	}

	/**
	 * @param sysDictTypes the sysDictTypes to set
	 */
	public void setSysDictTypes(SysDictType sysDictTypes) {
		this.sysDictTypes = sysDictTypes;
	}

	/**
	 * toString方法
	 */
	public String toString() {
		return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
				.append("dictCode", getDictCode())
				.append("dictName", getDictName())
				.append("dictTypeCode", getDictTypeCode())
				.append("serialNumber", getSerialNumber()).toString();
	}
}