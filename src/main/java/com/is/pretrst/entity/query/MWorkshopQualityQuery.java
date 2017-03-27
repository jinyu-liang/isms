package com.is.pretrst.entity.query;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.is.pretrst.entity.MWorkshopQuality;


public class MWorkshopQualityQuery extends MWorkshopQuality {

    private static final long serialVersionUID = 1L;

    /**
     * Integer字段：number  的查询条件
     */
    
    /* 查询条件：Number  的开始区间  */
    private Integer numberStart;
    
    public Integer getNumberStart(){
        return numberStart;
    }
    public void setNumberStart(Integer numberStart){
        this.numberStart = numberStart;
    }
    
    /*  查询条件：number  的结束区间     */
    private Integer numberEnd;
    
    public Integer getNumberEnd(){
        return numberEnd;
    }
    public void setNumberEnd(Integer numberEnd){
        this.numberEnd = numberEnd;
    }

	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("pid",getPid())
            .append("fquserid",getFquserid())
            .append("fqusername",getFqusername())
            .append("ws_cd",getWs_cd())
            .append("ws_nm",getWs_nm())
            .append("div_cd",getDiv_cd())
            .append("fqattchid",getFqattchid())
            .append("zgattchid",getZgattchid())
            .append("fqdesc",getFqdesc())
            .append("zgdesc",getZgdesc())
            .append("zgusername",getZgusername())
            .append("zguserid",getZguserid())
            .append("zgaddtime",getZgaddtime())
            .append("fqaddtime",getFqaddtime())
            .append("fqstatus",getFqstatus())
            .append("remak",getRemak())
            .append("zgjhfinishtime",this.getZgjhfinishtime())
            .append("zgfinishtime",this.getZgfinishtime())
            .append("isovertime",this.getIsovertime())
            .append("jlqrfinishtime",this.getJlqrfinishtime())
            .append("fqrqrfinishtime",this.getFqrqrfinishtime())
            .append("fqyqfinishtime",this.getFqyqfinishtime())
            .append("zgfinishuserid",this.getZgfinishuserid())
    		.append("zgfinishusername",this.getZgfinishusername())
			.append("zgfinishdesc",this.getZgfinishdesc())
			.append("jluserid",this.getJluserid())
			.append("jlusername",this.getJlusername())
			.append("jlfinishuerid",this.getJlfinishuerid())
			.append("jlfinishusername",this.getJlfinishusername())
			.append("jlDesc",this.getJlDesc())
			.append("fqrqdesc",this.getFqrqdesc())
            .toString();
    }
}
