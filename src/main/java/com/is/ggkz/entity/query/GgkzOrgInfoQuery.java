package com.is.ggkz.entity.query;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.is.ggkz.entity.GgkzOrgInfo;

/**
 *
 * @ClassName: GgkzOrgInfo
 * @Description: GgkzOrgInfo表的对应的查询对象，增加Date/Double/int型字段的区间查询属性字段
 * @author 
 * @date 2013-02-27 14:19:34
 *
 */
 
public class GgkzOrgInfoQuery extends GgkzOrgInfo {

    private static final long serialVersionUID = 1L;

    
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("orgId",getOrgId())
            .append("orgName",getOrgName())
            .toString();
    }
    
}