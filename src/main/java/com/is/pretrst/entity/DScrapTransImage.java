package com.is.pretrst.entity;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.base.mybatis.AbstractBaseEntity;

/**
 *
 * @ClassName: DScrapTransImage
 * @Description: DScrapTransImage表的对应的java对象，该文件为工具自动生成，请勿手工修改!
 * @author 
 * @date 2013-09-10 10:26:48
 *
 */
public class DScrapTransImage extends AbstractBaseEntity
{

    private static final long  serialVersionUID    = 1L;

    /* 照片Id */
    public static final String ALIAS_PHOTO_ID      = "photoId";

    /* 运输Id */
    public static final String ALIAS_TRANS_ID      = "transId";

    /* 文件名 */
    public static final String ALIAS_FILE_NAME     = "fileName";

    /* 拍照时间 */
    public static final String ALIAS_PHOTO_TM      = "photoTm";

    /* 备注 */
    public static final String ALIAS_MEMO          = "memo";

    /* 验货时间 */
    public static final String ALIAS_CHECK_TM      = "checkTm";

    /* 验货用户编码 */
    public static final String ALIAS_CHECK_USER_CD = "checkUserCd";

    /* 状态 */
    public static final String ALIAS_STATUS_CD     = "statusCd";

    /* 验货人 */
    public static final String ALIAS_CHECK_BY      = "checkBy";

    /* 照片Id */
    private String            photoId;

    /* 运输Id */
    private String             transId;

    /* 文件名 */
    private String             fileName;

    /* 拍照时间 */
    private Date               photoTm;

    /* 备注 */
    private String             memo;

    /* 验货时间 */
    private Date               checkTm;

    /* 验货用户编码 */
    private String             checkUserCd;

    /* 状态 */
    private String             statusCd;

    /* 验货人 */
    private String             checkBy;

    public String getPhotoId()
    {
        return this.photoId;
    }

    public void setPhotoId(String photoId)
    {
        this.photoId = photoId;
    }

    public String getTransId()
    {
        return this.transId;
    }

    public void setTransId(String transId)
    {
        this.transId = transId;
    }

    public String getFileName()
    {
        return this.fileName;
    }

    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    public Date getPhotoTm()
    {
        return this.photoTm;
    }

    public void setPhotoTm(Date photoTm)
    {
        this.photoTm = photoTm;
    }

    public String getMemo()
    {
        return this.memo;
    }

    public void setMemo(String memo)
    {
        this.memo = memo;
    }

    public Date getCheckTm()
    {
        return this.checkTm;
    }

    public void setCheckTm(Date checkTm)
    {
        this.checkTm = checkTm;
    }

    public String getCheckUserCd()
    {
        return this.checkUserCd;
    }

    public void setCheckUserCd(String checkUserCd)
    {
        this.checkUserCd = checkUserCd;
    }

    public String getStatusCd()
    {
        return this.statusCd;
    }

    public void setStatusCd(String statusCd)
    {
        this.statusCd = statusCd;
    }

    public String getCheckBy()
    {
        return this.checkBy;
    }

    public void setCheckBy(String checkBy)
    {
        this.checkBy = checkBy;
    }

    /**
     * toString方法
     */
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("photoId", getPhotoId()).append("transId", getTransId())
                .append("fileName", getFileName()).append("photoTm", getPhotoTm()).append("memo", getMemo()).append("checkTm", getCheckTm())
                .append("checkUserCd", getCheckUserCd()).append("statusCd", getStatusCd()).append("checkBy", getCheckBy()).toString();
    }
}