package com.is.ggkz.entity.query;
import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import com.is.ggkz.entity.GgkzRoleAuth;

/**
 *
 * @ClassName: GgkzRoleAuth
 * @Description: GgkzRoleAuth表的对应的查询对象，增加Date/Double/int型字段的区间查询属性字段
 * @author 
 * @date 2013-02-27 14:19:29
 *
 */
 
public class GgkzRoleAuthQuery extends GgkzRoleAuth {

    private static final long serialVersionUID = 1L;

    /**
     * Date字段：operTime  的查询条件
     */
    
    /* 查询条件：OperTime  的开始区间  */
    private Date operTimeStart;
    
    public Date getOperTimeStart(){
        return operTimeStart;
    }
    public void setOperTimeStart(Date operTimeStart){
        this.operTimeStart = operTimeStart;
    }
    
    /*  查询条件：operTime  的结束区间     */
    private Date operTimeEnd;
    
    public Date getOperTimeEnd(){
        return operTimeEnd;
    }
    public void setOperTimeEnd(Date operTimeEnd){
        this.operTimeEnd = operTimeEnd;
    }
    
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("roleId",getRoleId())
            .append("authId",getAuthId())
            .append("operTime",getOperTime())
            .append("operTimeStart",getOperTimeStart())
            .append("operTimeEnd",getOperTimeEnd())
            .append("operUserId",getOperUserId())
            .toString();
    }
    
}