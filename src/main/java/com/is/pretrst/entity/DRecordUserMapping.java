package com.is.pretrst.entity;

import java.util.Date;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import com.base.mybatis.AbstractBaseEntity;

/**
 *
 * @ClassName: DRecordUserMapping
 * @Description: DRecordUserMapping表的对应的java对象，该文件为工具自动生成，请勿手工修改!
 * @author 
 * @date 2013-09-10 10:26:39
 *
 */
public class DRecordUserMapping extends AbstractBaseEntity
{

    private static final long  serialVersionUID      = 1L;

    /* 映射Id */
    public static final String ALIAS_MAPPING_ID      = "mappingId";

    /* 流水号 */
    public static final String ALIAS_RECORD_ID       = "recordId";

    /* 记录种类 */
    public static final String ALIAS_RECORD_TYPE     = "recordType";

    /* 对应用户CD */
    public static final String ALIAS_MAPPING_USER_CD = "mappingUserCd";

    /* 对应用户Name */
    public static final String ALIAS_MAPPING_USER_NM = "mappingUserNm";

    /* 权限区分 */
    public static final String ALIAS_LEVEL_FLG       = "levelFlg";

    /* 创建时间 */
    public static final String ALIAS_CREATE_TM       = "createTm";

    /* 修改时间 */
    public static final String ALIAS_UPDATE_TM       = "updateTm";

    /* 删除区分 */
    public static final String ALIAS_DELETE_CD       = "deleteCd";

    /* 映射Id */
    private String             mappingId;

    /* 流水号 */
    private String             recordId;

    /* 记录种类 */
    private String             recordType;

    /* 对应用户CD */
    private String             mappingUserCd;

    /* 对应用户NM */
    private String             mappingUserNm;

    /* 权限区分 */
    private String             levelFlg;

    /* 创建时间 */
    private Date               createTm;

    /* 修改时间 */
    private Date               updateTm;

    /* 删除区分 */
    private String             deleteCd;
    
    private int dateAction;

    public String getMappingId()
    {
        return this.mappingId;
    }

    public void setMappingId(String mappingId)
    {
        this.mappingId = mappingId;
    }

    public String getRecordId()
    {
        return this.recordId;
    }

    public void setRecordId(String recordId)
    {
        this.recordId = recordId;
    }

    public String getRecordType()
    {
        return this.recordType;
    }

    public void setRecordType(String recordType)
    {
        this.recordType = recordType;
    }

    public String getMappingUserCd()
    {
        return this.mappingUserCd;
    }

    public void setMappingUserCd(String mappingUserCd)
    {
        this.mappingUserCd = mappingUserCd;
    }

    public String getLevelFlg()
    {
        return this.levelFlg;
    }

    public void setLevelFlg(String levelFlg)
    {
        this.levelFlg = levelFlg;
    }

    public Date getCreateTm()
    {
        return this.createTm;
    }

    public void setCreateTm(Date createTm)
    {
        this.createTm = createTm;
    }

    public Date getUpdateTm()
    {
        return this.updateTm;
    }

    public void setUpdateTm(Date updateTm)
    {
        this.updateTm = updateTm;
    }

    public String getDeleteCd()
    {
        return this.deleteCd;
    }

    public void setDeleteCd(String deleteCd)
    {
        this.deleteCd = deleteCd;
    }

    public String getMappingUserNm()
    {
        return mappingUserNm;
    }

    public void setMappingUserNm(String mappingUserNm)
    {
        this.mappingUserNm = mappingUserNm;
    }

    public int getDateAction()
    {
        return dateAction;
    }

    public void setDateAction(int dateAction)
    {
        this.dateAction = dateAction;
    }

    /**
     * toString方法
     */
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("mappingId", getMappingId()).append("recordId", getRecordId())
                .append("recordType", getRecordType()).append("mappingUserCd", getMappingUserCd()).append("levelFlg", getLevelFlg())
                .append("createTm", getCreateTm()).append("updateTm", getUpdateTm()).append("deleteCd", getDeleteCd()).toString();
    }
}