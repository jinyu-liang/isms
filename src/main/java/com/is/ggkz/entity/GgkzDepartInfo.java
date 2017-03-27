package com.is.ggkz.entity;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.base.mybatis.AbstractBaseEntity;

/**
 *
 * @ClassName: GgkzDepartInfo
 * @Description: GgkzDepartInfo表的对应的java对象，该文件为工具自动生成，请勿手工修改!
 * @author 
 * @date 2013-02-27 14:19:37
 *
 */
public class GgkzDepartInfo extends AbstractBaseEntity
{

    private static final long  serialVersionUID       = 1L;

    /* 部门代码 */
    public static final String ALIAS_DEPART_ID        = "departId";

    /* 上级部门代码 */
    public static final String ALIAS_HIGHER_DEPART_ID = "higherDepartId";

    /* 部门名称 */
    public static final String ALIAS_DEPART_NAME      = "departName";

    /* 备注 */
    public static final String ALIAS_NOTE             = "note";
    
    /* 备注 */
    public static final String ALIAS_DEPARTFULLNAME   = "departfullname";

    /* 部门代码 */
    private String             departId;

    /* 上级部门代码 */
    private String             higherDepartId;

    /* 部门名称 */
    private String             departName;

    /* 备注 */
    private String             note;
    
   

	/* 部门全名 */
    public String             departfullname;

    /* 部门dns*/
    public String             depdns;

    /* 部门dn*/
    public String             depdn;

    /* 部门排序*/
    public int             ordernum;
    
    /* 部门排序*/
    public Date             addtime;
    
    /* 部门dn*/
    public String             basestatus;
    
    /* 部门dn*/
    public String             departtype;



	/**
     * 执行存储过程返回结果
     */
    private Integer            result;
    
    public String getDepartId()
    {
        return this.departId;
    }

    public void setDepartId(String departId)
    {
        this.departId = departId;
    }

    public String getHigherDepartId()
    {
        return this.higherDepartId;
    }

    public void setHigherDepartId(String higherDepartId)
    {
        this.higherDepartId = higherDepartId;
    }

    public String getDepartName()
    {
        return this.departName;
    }

    public void setDepartName(String departName)
    {
        this.departName = departName;
    }

    public String getNote()
    {
        return this.note;
    }

    public void setNote(String note)
    {
        this.note = note;
    }

    public Date getAddtime() {
		return addtime;
	}

	public void setAddtime(Date addtime) {
		this.addtime = addtime;
	}

	public Integer getResult()
    {
        return result;
    }

    public void setResult(Integer result)
    {
        this.result = result;
    }

    /* 关联对象 */
    private List<GgkzUserInfo> ggkzUserInfos;

    public List<GgkzUserInfo> getGgkzUserInfos()
    {
        return this.ggkzUserInfos;
    }

    public void setGgkzUserInfos(List<GgkzUserInfo> ggkzUserInfos)
    {
        this.ggkzUserInfos = ggkzUserInfos;
    }
    
    public String getDepart_fullname() {
		return departfullname;
	}

	public void setDepart_fullname(String depart_fullname) {
		this.departfullname = depart_fullname;
	}
	
    public String getBasestatus() {
		return basestatus;
	}

	public void setBasestatus(String basestatus) {
		this.basestatus = basestatus;
	}



	public String getDeparttype() {
		return departtype;
	}

	public void setDeparttype(String departtype) {
		this.departtype = departtype;
	}

	public String getDepdns() {
		return depdns;
	}

	public void setDepdns(String depdns) {
		this.depdns = depdns;
	}

	public String getDepdn() {
		return depdn;
	}

	public void setDepdn(String depdn) {
		this.depdn = depdn;
	}

	public int getOrdernum() {
		return ordernum;
	}

	public void setOrdernum(int ordernum) {
		this.ordernum = ordernum;
	}

    /**
     * toString方法
     */
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("departId", getDepartId())
                .append("higherDepartId", getHigherDepartId()).append("departName", getDepartName()).append("departfullname",getDepart_fullname())
                .append("departtype",getDeparttype()).append("basestatus",getBasestatus())
                .append("depdns",getDepdns()).append("depdn",getDepdn()).append("ordernum",getOrdernum()).append("note", getNote()).toString();
    }
}