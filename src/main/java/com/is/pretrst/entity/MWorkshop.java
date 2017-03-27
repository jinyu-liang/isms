package com.is.pretrst.entity;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.base.mybatis.AbstractBaseEntity;

/**
 *
 * @ClassName: MWorkshop
 * @Description: MWorkshop表的对应的java对象，该文件为工具自动生成，请勿手工修改!
 * @author 
 * @date 2013-09-10 10:26:59
 *
 */
public class MWorkshop extends AbstractBaseEntity
{

    private static final long    serialVersionUID      = 1L;

    /* 工程中心编码 */
    public static final String   ALIAS_WS_CD           = "wsCd";

    /* 工地中心名称 */
    public static final String   ALIAS_WS_NM           = "wsNm";

    /* 部门编码 */
    public static final String   ALIAS_DIV_CD          = "divCd";

    /* 区分编码 */
    public static final String   ALIAS_TYPE_CD         = "typeCd";

    /* 负责人用户编码 */
    public static final String   ALIAS_MANAGER_USER_ID = "managerUserId";

    /* 开始时间 */
    public static final String   ALIAS_BEGIN_TIME      = "beginTime";

    /* 结束时间 */
    public static final String   ALIAS_END_TIME        = "endTime";

    /* 删除区分 */
    public static final String   ALIAS_DELETE_CD       = "deleteCd";

    /* 工程中心编码 */
    private String               wsCd;

    /* 工地中心名称 */
    private String               wsNm;

    /* 部门编码 */
    private String               divCd;

    /* 区分编码 */
    private String               typeCd;

    /* 负责人用户编码 */
    private String               managerUserId;

    /* 开始时间 */
    private Date                 beginTime;

    /* 结束时间 */
    private Date                 endTime;

    /* 删除区分 */
    private String               deleteCd;

    /* 附件 */
    private String               filenames;

    private List<MWorkshopItem>  itemList;

    private List<DContractImage> contractImage;

    private DExProject           project;
    
    /* 工地负责人电话*/
    private String mobileTel;

    
     

    /**
     * @return Returns the mobileTel.
     */
    public String getMobileTel()
    {
        return mobileTel;
    }

    /**
     * @param mobileTel The mobileTel to set.
     */
    public void setMobileTel(String mobileTel)
    {
        this.mobileTel = mobileTel;
    }

    public String getWsCd()
    {
        return this.wsCd;
    }

    public void setWsCd(String wsCd)
    {
        this.wsCd = wsCd;
    }

    public String getWsNm()
    {
        return this.wsNm;
    }

    public void setWsNm(String wsNm)
    {
        this.wsNm = wsNm;
    }

    public String getDivCd()
    {
        return this.divCd;
    }

    public void setDivCd(String divCd)
    {
        this.divCd = divCd;
    }

    public String getTypeCd()
    {
        return this.typeCd;
    }

    public void setTypeCd(String typeCd)
    {
        this.typeCd = typeCd;
    }

    public String getManagerUserId()
    {
        return this.managerUserId;
    }

    public void setManagerUserId(String managerUserId)
    {
        this.managerUserId = managerUserId;
    }

    public Date getBeginTime()
    {
        return this.beginTime;
    }

    public void setBeginTime(Date beginTime)
    {
        this.beginTime = beginTime;
    }

    public Date getEndTime()
    {
        return this.endTime;
    }

    public void setEndTime(Date endTime)
    {
        this.endTime = endTime;
    }

    public String getDeleteCd()
    {
        return this.deleteCd;
    }

    public void setDeleteCd(String deleteCd)
    {
        this.deleteCd = deleteCd;
    }

    /**
     * toString方法
     */
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("wsCd", getWsCd()).append("wsNm", getWsNm())
                .append("divCd", getDivCd()).append("typeCd", getTypeCd()).append("managerUserId", getManagerUserId())
                .append("mobileTel",getMobileTel()).append("beginTime", getBeginTime()).append("endTime", getEndTime()).append("deleteCd", getDeleteCd()).toString();
    }

    public void setContractImage(List<DContractImage> contractImage)
    {
        this.contractImage = contractImage;
    }

    public List<DContractImage> getContractImage()
    {
        return contractImage;
    }

    public void setProject(DExProject project)
    {
        this.project = project;
    }

    public DExProject getProject()
    {
        return project;
    }

    public void setFilenames(String filenames)
    {
        this.filenames = filenames;
    }

    public String getFilenames()
    {
        return filenames;
    }

    public List<MWorkshopItem> getItemList()
    {
        return itemList;
    }

    public void setItemList(List<MWorkshopItem> itemList)
    {
        this.itemList = itemList;
    }

}