package com.is.pretrst.entity;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.base.mybatis.AbstractBaseEntity;

/**
 *
 * @ClassName: DProgressImage
 * @Description: DProgressImage表的对应的java对象，该文件为工具自动生成，请勿手工修改!
 * @author 
 * @date 2013-09-10 10:26:26
 *
 */
public class DProgressImage extends AbstractBaseEntity
{

    private static final long  serialVersionUID    = 1L;

    /* 照片Id */
    public static final String ALIAS_PHOTO_ID      = "imageId";

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

    /* 上传时间 */
    public static final String ALIAS_WS_CD     = "wsCd";
    
    
    /* 照片Id */
    private String             imageId;

    /*进度报告ID*/
    private String             reportId;

    /* 工作中心编码*/
    private String             wsCd;

    /**照片文件名路径**/
    private String              filename;
    
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
    


    /**
     * @return Returns the imageId.
     */
    public String getImageId()
    {
        return imageId;
    }

    /**
     * @param imageId The imageId to set.
     */
    public void setImageId(String imageId)
    {
        this.imageId = imageId;
    }

    /**
     * @return Returns the reportId.
     */
    public String getReportId()
    {
        return reportId;
    }

    /**
     * @param reportId The reportId to set.
     */
    public void setReportId(String reportId)
    {
        this.reportId = reportId;
    }

    /**
     * @return Returns the wsCd.
     */
    public String getWsCd()
    {
        return wsCd;
    }

    /**
     * @param wsCd The wsCd to set.
     */
    public void setWsCd(String wsCd)
    {
        this.wsCd = wsCd;
    }

    /**
     * @return Returns the posLongitude.
     */
    public Float getPosLongitude()
    {
        return posLongitude;
    }

    /**
     * @param posLongitude The posLongitude to set.
     */
    public void setPosLongitude(Float posLongitude)
    {
        this.posLongitude = posLongitude;
    }

    /**
     * @return Returns the filename.
     */
    public String getFilename()
    {
        return filename;
    }

    /**
     * @param filename The filename to set.
     */
    public void setFilename(String filename)
    {
        this.filename = filename;
    }


    /**
     * toString方法
     */
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("posLatitude", getPosLatitude()).append("posHeight", getPosHeight())
                .append("photoUserCd", getPhotoUserCd()).append("photoTm", getPhotoTm()).append("uploadTm", getUploadTm())
                .toString();
    }
}