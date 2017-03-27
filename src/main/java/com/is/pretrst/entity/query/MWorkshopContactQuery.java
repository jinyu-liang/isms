package com.is.pretrst.entity.query;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.is.pretrst.entity.MWorkshopContact;

public class MWorkshopContactQuery extends MWorkshopContact {

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
        .append("ID",this.getID())
        .append("ws_cd",this.getWs_cd())
        .append("ws_nm",this.getWs_nm())
        .append("fquserid",this.getFquserid())
        .append("fqusername",this.getFqusername())
        .append("title",this.getTitle())
        .append("detail",this.getDetail())
        .append("addtime",this.getAddtime())
        .append("acceptdepname",this.getAcceptdepname())
        .append("acceptdepID",this.getAcceptdepID())
        .append("senderdepname",this.getSenderdepname())
        .append("senderdepID",this.getSenderdepID())
        .append("acceptdepmangername",this.getAcceptdepmangername())
        .append("acceptdepmangerID",this.getAcceptdepmangerID())
        .append("acceptdepmangerdesc",this.getAcceptdepmangerdesc())
        .append("acceptdepmangertime",this.getAcceptdepmangertime())
        .append("leadername",this.getLeadername())
        .append("leaderID",this.getLeaderID())
        .append("leaderdesc",this.getLeaderdesc())
        .append("leadertime",this.getLeadertime())
        .append("Isaccept",this.getIsaccept())
        .append("accepttime",this.getAccepttime())
        .append("acceptdesc",this.getAcceptdesc())
        .append("status",this.getStatus())
        .append("remark",this.getRemark())
        .append("pageNumber",this.getPageNumber())
        .append("pageSize",this.getPageSize())
        .append("numberStart",this.getNumberStart())
        .append("numberEnd",this.getNumberEnd())
        .append("acceptuserID",this.getAcceptuserID())
        .append("acceptusername",this.getAcceptusername())
        .toString();
    }
}
