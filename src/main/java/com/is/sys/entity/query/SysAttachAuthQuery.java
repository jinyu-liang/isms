package com.is.sys.entity.query;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.is.sys.entity.SysAttachAuth;

/**
 *
 * @ClassName: BzzcAttachAuth
 * @Description: BzzcAttachAuth表的对应的查询对象，增加Date/Double/int型字段的区间查询属性字段
 * @author 
 * @date 2011-12-21 09:30:05
 *
 */
 
public class SysAttachAuthQuery extends SysAttachAuth {

    private static final long serialVersionUID = 1L;

    
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("attachId",getAttachId())
            .append("userId",getUserId())
            .append("busiId",getBusiId())
            .toString();
    }
    
}