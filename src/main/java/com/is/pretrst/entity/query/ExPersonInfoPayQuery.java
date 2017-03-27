package com.is.pretrst.entity.query;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.is.pretrst.entity.ExPersonInfoPay;



public class ExPersonInfoPayQuery extends ExPersonInfoPay {

    private static final long serialVersionUID = 1L;

    /**
     * Date字段：updateDate  的查询条件
     */
    
    /* 查询条件：UpdateDate  的开始区间  */
    private Date updateDateStart;
    
    public Date getUpdateDateStart(){
        return updateDateStart;
    }
    public void setUpdateDateStart(Date updateDateStart){
        this.updateDateStart = updateDateStart;
    }
    
    /*  查询条件：updateDate  的结束区间     */
    private Date updateDateEnd;
    
    public Date getUpdateDateEnd(){
        return updateDateEnd;
    }
    public void setUpdateDateEnd(Date updateDateEnd){
        this.updateDateEnd = updateDateEnd;
    }
    
//    "ExPersonInfoPay [identyCardCode=" + identyCardCode + ", name="
//	+ name + ", telephone=" + telephone + ", work_type="
//	+ work_type + ", work_type_name=" + work_type_name
//	+ ", work_count_type=" + work_count_type + ", work_pay="
//	+ work_pay + ", work_ws_cd=" + work_ws_cd + ", work_ws_nm="
//	+ work_ws_nm + ", team_name=" + team_name + ", team_id="
//	+ team_id + ", addtime=" + addtime + ", start_time_am="
//	+ start_time_am + ", end_time_am=" + end_time_am
//	+ ", start_time_pm=" + start_time_pm + ", end_time_pm="
//	+ end_time_pm + ", start_time_other=" + start_time_other
//	+ ", end_time_other=" + end_time_other + ", work_timer="
//	+ work_timer + ", work_status=" + work_status + ", remark="
//	+ remark + ", dayormonth=" + dayormonth + ", bank_card="
//	+ bank_card + ", bank_card_name=" + bank_card_name + "]";
    
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
        	.append("Pid",getPid())
            .append("identy_card_code",getIdenty_card_code())
            .append("addtime",getAddtime())
            .append("name",getName())
            .append("telephone",getTelephone())
            .append("work_type",getWork_type())
            .append("work_type_name",getWork_type_name())
            .append("work_count_type",getWork_count_type())
            .append("work_pay",getWork_pay())
            .append("work_ws_cd",getWork_ws_cd())
            .append("work_ws_nm",getWork_ws_nm())
            .append("team_name",getTeam_name())
            .append("team_id",getTeam_id())
            .append("start_time_am",getStart_time_am())
            .append("end_time_am",getEnd_time_am())
            .append("start_time_pm",getStart_time_pm())
            .append("end_time_pm",getEnd_time_pm())
            .append("start_time_other",getStart_time_other())
            .append("end_time_other",getEnd_time_other())
            .append("work_timer",getWork_timer())
            .append("work_status",getWork_status())
            .append("dayormonth",getDayormonth())
            .append("bank_card",getBank_card())
            .append("bank_card_name",getBank_card_name())
            .append("remark",getRemark())
            .toString();
    }
}
