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
public class DContractImage extends AbstractBaseEntity
{

    private static final long  serialVersionUID    = 1L;

    /* 照片Id */
    public static final String ALIAS_PHOTO_ID      = "photoId";

    /* 工地中心编码	 */
    public static final String ALIAS_INVOICE_ID    = "wsCd";

    /* 文件名 */
    public static final String ALIAS_FILE_NAME     = "filename";


    /* 拍照时间 */
    public static final String ALIAS_PHOTO_TM      = "photoTm";


    /* 照片Id */
    private String            photoId;

    /* 工地中心编码 */
    private String             wsCd;

    /* 文件名 */
    private String             filename;


    /* 拍照时间 */
    private Date               photoTm;


    public String getPhotoId()
    {
        return this.photoId;
    }

    public void setPhotoId(String photoId)
    {
        this.photoId = photoId;
    }


    public String getWsCd() {
		return wsCd;
	}

	public void setWsCd(String wsCd) {
		this.wsCd = wsCd;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public Date getPhotoTm()
    {
        return this.photoTm;
    }

    public void setPhotoTm(Date photoTm)
    {
        this.photoTm = photoTm;
    }


    /**
     * toString方法
     */
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("photoId", getPhotoId()).append("wsCd", getWsCd())
                .append("filename", getFilename()).append("photoTm", getPhotoTm()).toString();
    }
}