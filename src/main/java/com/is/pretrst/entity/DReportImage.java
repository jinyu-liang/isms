package com.is.pretrst.entity;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.base.mybatis.AbstractBaseEntity;

/**
 *
 * @ClassName: DReportImage
 * @Description: DReportImage表的对应的java对象，该文件为工具自动生成，请勿手工修改!
 * @author 
 * @date 2013-09-10 10:26:45
 *
 */
public class DReportImage extends AbstractBaseEntity
{

    private static final long  serialVersionUID    = 1L;

    /* 照片Id */
    public static final String ALIAS_PHOTO_ID      = "photoId";

    /* 发货单ID */
    public static final String ALIAS_INVOICE_ID    = "reportId";

    /* 文件名 */
    public static final String ALIAS_FILE_NAME     = "fileName";

    /* 经纬位置 */
    public static final String ALIAS_POS_LONGITUDE = "posLongitude";

    /* 维度位置 */
    public static final String ALIAS_POS_LATITUDE  = "posLatitude";

    /* 位置高度 */
    public static final String ALIAS_POS_HEIGHT    = "posHeight";

    /* 拍摄用户编码 */
    public static final String ALIAS_PHOTO_USER_CD = "photoUserCd";

    /* 拍照时间 */
    public static final String ALIAS_PHOTO_TM      = "photoTm";

    /* 上传时间 */
    public static final String ALIAS_UPLOAD_TM     = "uploadTm";

    /* 备注 */
    public static final String ALIAS_MEMO          = "memo";

    /* 验货时间 */
    public static final String ALIAS_CHECK_TM      = "checkTm";

    /* 验货用户编码 */
    public static final String ALIAS_CHECK_USER_CD = "checkUserCd";

    /* 状态 */
    public static final String ALIAS_STATUS_CD     = "statusCd";

    /* 照片Id */
    private String            photoId;

    /* 发货单ID */
    private String             reportId;

    /* 文件名 */
    private String             fileName;

    /* 经纬位置 */
    private Float              posLongitude;

    /* 维度位置 */
    private Float              posLatitude;

    /* 位置高度 */
    private Float              posHeight;

    /* 拍摄用户编码 */
    private String             photoUserCd;

    /* 拍照时间 */
    private Date               photoTm;

    /* 上传时间 */
    private Date               uploadTm;

    /* 备注 */
    private String             memo;

    /* 验货时间 */
    private Date               checkTm;

    /* 验货用户编码 */
    private String             checkUserCd;

    /* 状态 */
    private String             statusCd;

    public String getPhotoId()
    {
        return this.photoId;
    }

    public void setPhotoId(String photoId)
    {
        this.photoId = photoId;
    }

    public String getReportId()
    {
        return this.reportId;
    }

    public void setReportId(String ReportId)
    {
        this.reportId = ReportId;
    }

    public String getFileName()
    {
        return this.fileName;
    }

    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    public Float getPosLongitude()
    {
        return this.posLongitude;
    }

    public void setPosLongitude(Float posLongitude)
    {
        this.posLongitude = posLongitude;
    }

    public Float getPosLatitude()
    {
        return this.posLatitude;
    }

    public void setPosLatitude(Float posLatitude)
    {
        this.posLatitude = posLatitude;
    }

    public Float getPosHeight()
    {
        return this.posHeight;
    }

    public void setPosHeight(Float posHeight)
    {
        this.posHeight = posHeight;
    }

    public String getPhotoUserCd()
    {
        return this.photoUserCd;
    }

    public void setPhotoUserCd(String photoUserCd)
    {
        this.photoUserCd = photoUserCd;
    }

    public Date getPhotoTm()
    {
        return this.photoTm;
    }

    public void setPhotoTm(Date photoTm)
    {
        this.photoTm = photoTm;
    }

    public Date getUploadTm()
    {
        return this.uploadTm;
    }

    public void setUploadTm(Date uploadTm)
    {
        this.uploadTm = uploadTm;
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

    /**
     * toString方法
     */
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("photoId", getPhotoId()).append("reportId", getReportId())
                .append("fileName", getFileName()).append("posLongitude", getPosLongitude()).append("posLatitude", getPosLatitude())
                .append("posHeight", getPosHeight()).append("photoUserCd", getPhotoUserCd()).append("photoTm", getPhotoTm())
                .append("uploadTm", getUploadTm()).append("memo", getMemo()).append("checkTm", getCheckTm()).append("checkUserCd", getCheckUserCd())
                .append("statusCd", getStatusCd()).toString();
    }
}