package com.is.sys.entity;

import java.util.List;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import com.base.mybatis.AbstractBaseEntity;

/**
 *
 * @ClassName: SysDictType
 * @Description: SysDictType表的对应的java对象，该文件为工具自动生成，请勿手工修改!
 * @author 
 * @date 2013-03-29 16:45:28
 *
 */
public class SysDictType extends AbstractBaseEntity{

    private static final long serialVersionUID = 1L;
    
	
	/* 字典类型代码 */
    public static final String ALIAS_DICT_TYPE_CODE = "dictTypeCode";
	
	/* 字典类型名称 */
    public static final String ALIAS_DICT_TYPE_NAME = "dictTypeName";
	
	/* 序号 */
    public static final String ALIAS_SERIAL_NUMBER = "serialNumber";
	
	/* 字典类型代码 */
		private String dictTypeCode;
	
	/* 字典类型名称 */
		private String dictTypeName;
	
	/* 序号 */
		private Integer serialNumber;

	
	
	
		public String getDictTypeCode(){
			return this.dictTypeCode;
		}
		public void setDictTypeCode(String dictTypeCode){
			this.dictTypeCode = dictTypeCode;
		}
	
	
		public String getDictTypeName(){
			return this.dictTypeName;
		}
		public void setDictTypeName(String dictTypeName){
			this.dictTypeName = dictTypeName;
		}
	
	
		public Integer getSerialNumber(){
			return this.serialNumber;
		}
		public void setSerialNumber(Integer serialNumber){
			this.serialNumber = serialNumber;
		}
	
	/* 关联对象 */
    private List<SysDict> sysDicts;
    
    public List<SysDict> getSysDicts(){
        return this.sysDicts;
    }
    public void setSysDicts(List<SysDict> sysDicts){
        this.sysDicts = sysDicts;
    }

	/**
	 * toString方法
	 */
	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("dictTypeCode",getDictTypeCode())
            .append("dictTypeName",getDictTypeName())
            .append("serialNumber",getSerialNumber())
            .toString();
    }
}