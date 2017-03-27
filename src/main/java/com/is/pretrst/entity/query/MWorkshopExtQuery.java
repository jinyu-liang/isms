package com.is.pretrst.entity.query;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.is.pretrst.entity.MWorkshopExt;

/**
 *
 * @ClassName: MWorkshop
 * @Description: MWorkshop表的对应的查询对象，增加Date/Double/int型字段的区间查询属性字段
 * @author 
 * @date 2013-09-10 10:26:58
 *
 */
 
public class MWorkshopExtQuery extends MWorkshopExt {

    private static final long serialVersionUID = 1L;

    
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("wsCd",getWsCd())
            .append("wsNm",getWsNm())
            .append("beginTime",getBeginTime())
            .append("endTime",getEndTime())
            .toString();
    }
    
}