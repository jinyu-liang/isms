package com.is.sys.entity.query;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.is.sys.entity.SysDict;

/**
 *
 * @ClassName: SysDict
 * @Description: SysDict表的对应的查询对象，增加Date/Double/int型字段的区间查询属性字段
 * @author 
 * @date 2013-03-29 16:45:21
 *
 */
 
public class SysDictQuery extends SysDict {

    private static final long serialVersionUID = 1L;

    /**
     * Integer字段：serialNumber  的查询条件
     */
    
    /* 查询条件：SerialNumber  的开始区间  */
    private Integer serialNumberStart;
    
    public Integer getSerialNumberStart(){
        return serialNumberStart;
    }
    public void setSerialNumberStart(Integer serialNumberStart){
        this.serialNumberStart = serialNumberStart;
    }
    
    /*  查询条件：serialNumber  的结束区间     */
    private Integer serialNumberEnd;
    
    public Integer getSerialNumberEnd(){
        return serialNumberEnd;
    }
    public void setSerialNumberEnd(Integer serialNumberEnd){
        this.serialNumberEnd = serialNumberEnd;
    }
    
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("dictCode",getDictCode())
            .append("dictName",getDictName())
            .append("dictTypeCode",getDictTypeCode())
            .append("serialNumber",getSerialNumber())
            .append("serialNumberStart",getSerialNumberStart())
            .append("serialNumberEnd",getSerialNumberEnd())
            .toString();
    }
    
}