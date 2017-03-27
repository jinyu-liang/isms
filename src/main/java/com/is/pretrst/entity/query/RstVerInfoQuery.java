package com.is.pretrst.entity.query;
import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.is.pretrst.entity.RstVerInfo;

/**
 *
 * @ClassName: RstVerInfo
 * @Description: RstVerInfo表的对应的查询对象，增加Date/Double/int型字段的区间查询属性字段
 * @author 
 * @date 2013-10-26 12:44:17
 *
 */
 
public class RstVerInfoQuery extends RstVerInfo {

    private static final long serialVersionUID = 1L;

    /**
     * Integer字段：verId  的查询条件
     */
    
    /* 查询条件：VerId  的开始区间  */
    private Integer verIdStart;
    
    public Integer getVerIdStart(){
        return verIdStart;
    }
    public void setVerIdStart(Integer verIdStart){
        this.verIdStart = verIdStart;
    }
    
    /*  查询条件：verId  的结束区间     */
    private Integer verIdEnd;
    
    public Integer getVerIdEnd(){
        return verIdEnd;
    }
    public void setVerIdEnd(Integer verIdEnd){
        this.verIdEnd = verIdEnd;
    }
    /**
     * Date字段：prodDate  的查询条件
     */
    
    /* 查询条件：ProdDate  的开始区间  */
    private Date prodDateStart;
    
    public Date getProdDateStart(){
        return prodDateStart;
    }
    public void setProdDateStart(Date prodDateStart){
        this.prodDateStart = prodDateStart;
    }
    
    /*  查询条件：prodDate  的结束区间     */
    private Date prodDateEnd;
    
    public Date getProdDateEnd(){
        return prodDateEnd;
    }
    public void setProdDateEnd(Date prodDateEnd){
        this.prodDateEnd = prodDateEnd;
    }
    /**
     * Date字段：finishDate  的查询条件
     */
    
    /* 查询条件：FinishDate  的开始区间  */
    private Date finishDateStart;
    
    public Date getFinishDateStart(){
        return finishDateStart;
    }
    public void setFinishDateStart(Date finishDateStart){
        this.finishDateStart = finishDateStart;
    }
    
    /*  查询条件：finishDate  的结束区间     */
    private Date finishDateEnd;
    
    public Date getFinishDateEnd(){
        return finishDateEnd;
    }
    public void setFinishDateEnd(Date finishDateEnd){
        this.finishDateEnd = finishDateEnd;
    }
    
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("verId",getVerId())
            .append("verIdStart",getVerIdStart())
            .append("verIdEnd",getVerIdEnd())
            .append("verCode",getVerCode())
            .append("prodDate",getProdDate())
            .append("prodDateStart",getProdDateStart())
            .append("prodDateEnd",getProdDateEnd())
            .append("finishDate",getFinishDate())
            .append("finishDateStart",getFinishDateStart())
            .append("finishDateEnd",getFinishDateEnd())
            .append("statusCd",getStatusCd())
            .append("memo",getMemo())
            .append("fileName",getFileName())
            .append("updateMode",getUpdateMode())
            .toString();
    }
    
}