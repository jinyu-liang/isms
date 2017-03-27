package com.is.utils;

import java.awt.Dimension;
import java.io.File;
import java.util.LinkedList;

import magick.CompressionType;
import magick.ImageInfo;
import magick.MagickImage;
import magick.PreviewType;

import com.is.utils.keyUtils.KeyGen;

public class ImgMagick
{

    public static final int          QUALITY         = 60;

    public static final int          SMALL_SIZE_W    = 800;

    public static final int          SMALL_SIZE_H    = 600;

    public static final int          MAX_SIZE        = 150 * 1024;

    public static final int          W_SCALE         = 16;

    public static final int          H_SCALE         = 9;

    public static final int          THUMBNAILS_W    = 72;

    public static final int          THUMBNAILS_H    = 72;

    public static final String       THUMBNAILS_PATH = "thumbnails";

    public static LinkedList<String> tempPathList    = new LinkedList<String>();

    static
    {
        System.setProperty("jmagick.systemclassloader", "no");
    }

    private ImgMagick()
    {

    }

    /**
     * 图片压缩
     * @param sourcePath图片源路径
     */
    public static String resizeImg(String sourcePath)
    {
        return resizeImg(sourcePath, false, false);
    }

    /**
     * 图片压缩
     * @param sourcePath图片源路径
     * @param aspectratio是否保持纵横比
     * @param thumbnail是否提供缩略图
     */
    public static String resizeImg(String sourcePath, boolean aspectratio, boolean thumbnails)
    {
        return resizeImg(sourcePath, QUALITY, aspectratio, thumbnails);
    }

    /**
     * 图片压缩
     * @param sourcePath图片源路径
     * @param qulity图片质量(0~100)
     * @param aspectratio是否保持纵横比
     * @param thumbnails是否提供缩略图
     */
    public static String resizeImg(String sourcePath, int qulity, boolean aspectratio, boolean thumbnails)
    {
        return resizeImg(sourcePath, SMALL_SIZE_W, SMALL_SIZE_H, qulity, aspectratio, thumbnails);
    }

    /**
     * 图片压缩
     * @param sourcePath图片源路径
     * @param toWidth新宽度
     * @param toHeight新高度
     * @param qulity图片质量(0~100)
     * @param aspectratio是否保持纵横比
     * @param thumbnails是否提供缩略图
     */
    public static String resizeImg(String sourcePath, int toWidth, int toHeight, int qulity, boolean aspectratio, boolean thumbnails)
    {
        return resizeImg(sourcePath, sourcePath, toWidth, toHeight, qulity, aspectratio, thumbnails);
    }

    /**
     * 图片压缩
     * @param sourcePath图片源路径
     * @param destinationPath图片目的路径
     * @param toWidth新宽度
     * @param toHeight新高度
     * @param qulity图片质量(0~100)
     * @param aspectratio是否保持纵横比
     * @param thumbnails是否提供缩略图
     */
    public static String resizeImg(String sourcePath, String destinationPath, int toWidth, int toHeight, int qulity, boolean aspectratio,
            boolean thumbnails)
    {
        int index = destinationPath.lastIndexOf("/");
        index = index == -1 ? destinationPath.lastIndexOf("\\") : index;
        index = index < 0 ? 0 : index;
        String thumbnailsPath = null;
        if (thumbnails)
        {
            thumbnailsPath = destinationPath.substring(0, index) + File.separator + THUMBNAILS_PATH + destinationPath.substring(index);
        }

        return resizeImg(sourcePath, destinationPath, toWidth, toHeight, qulity, aspectratio, thumbnails, thumbnailsPath, THUMBNAILS_W, THUMBNAILS_H);
    }

    /**
     * 图片压缩
     * @param sourcePath图片源路径
     * @param destinationPath图片目的路径
     * @param toWidth新宽度
     * @param toHeight新高度
     * @param qulity图片质量(0~100)
     * @param aspectratio是否保持纵横比
     * @param thumbnails是否提供缩略图
     * @param thumbnailsPath供缩略图路径
     * @param thumWidth缩略图宽度
     * @param thumHeight缩略图高度
     */
    public static String resizeImg(String sourcePath, String destinationPath, int toWidth, int toHeight, int qulity, boolean aspectratio,
            boolean thumbnails, String thumbnailsPath, int thumWidth, int thumHeight)
    {
        return resizeImg(sourcePath, destinationPath, toWidth, toHeight, qulity, MAX_SIZE, aspectratio, thumbnails, thumbnailsPath, thumWidth,
                thumHeight);
    }

    /**
     * 图片压缩
     * @param sourcePath图片源路径
     * @param destinationPath图片目的路径
     * @param toWidth新宽度
     * @param toHeight新高度
     * @param qulity图片质量(0~100)
     * @param maxSize图片大小上线
     * @param aspectratio是否保持纵横比
     * @param thumbnails是否提供缩略图
     * @param thumbnailsPath供缩略图路径
     * @param thumWidth缩略图宽度
     * @param thumHeight缩略图高度
     */
    public static String resizeImg(String sourcePath, String destinationPath, int toWidth, int toHeight, int qulity, int maxSize,
            boolean aspectratio, boolean thumbnails, String thumbnailsPath, int thumWidth, int thumHeight)
    {
        String tempPath = null;
        String tempFname = null;
        ImageInfo info = null;
        MagickImage image = null;
        Dimension imageDim = null;
        MagickImage scaled = null;
        MagickImage thumScaled = null;
        try
        {
            info = new ImageInfo(sourcePath);
            if (sourcePath.toUpperCase().endsWith("JPG") || sourcePath.toUpperCase().endsWith("JPEG"))
            {
                info.setCompression(CompressionType.JPEGCompression); //压缩类别为JPEG格式
                info.setPreviewType(PreviewType.JPEGPreview); //预览格式为JPEG格式
            }
            if (!sourcePath.toUpperCase().endsWith("PNG") && qulity > 0 && qulity <= 100)
            {
                info.setQuality(qulity);
            }

            image = new MagickImage(info);
            imageDim = image.getDimension();
            int ingWidth = imageDim.width;
            int ingHeight = imageDim.height;
            if (ingWidth > toWidth || ingHeight > toHeight)
            {
                if (aspectratio)
                {
                    Float reate = 1f;
                    if (ingWidth < ingHeight)
                    {
                        reate = Math.min(1F * toHeight / Math.max(ingWidth, ingHeight), 1F * toWidth / Math.min(ingWidth, ingHeight));
                    }
                    else
                    {
                        reate = Math.min(1F * toWidth / Math.max(ingWidth, ingHeight), 1F * toHeight / Math.min(ingWidth, ingHeight));
                    }
                    Float nw = ingWidth * reate;
                    Float nh = ingHeight * reate;
                    scaled = image.scaleImage(nw.intValue(), nh.intValue());//小图片文件的大小.
                    if (thumbnails)
                    {
                        reate = 1f;
                        if (ingWidth < ingHeight)
                        {
                            reate = Math.min(1F * thumHeight / Math.max(ingWidth, ingHeight), 1F * thumWidth / Math.min(ingWidth, ingHeight));
                        }
                        else
                        {
                            reate = Math.min(1F * thumWidth / Math.max(ingWidth, ingHeight), 1F * thumHeight / Math.min(ingWidth, ingHeight));
                        }
                        nw = ingWidth * reate;
                        nh = ingHeight * reate;
                        thumScaled = image.scaleImage(nw.intValue(), nh.intValue());//小图片文件的大小.
                    }
                }
                else
                {
                    scaled = image.scaleImage(toWidth, toHeight);//小图片文件的大小.
                    if (thumbnails)
                    {
                        thumScaled = image.scaleImage(thumWidth, thumHeight);//小图片文件的大小.
                    }
                }
                if (sourcePath.equalsIgnoreCase(destinationPath))
                {
                    int indexdot = destinationPath.lastIndexOf(".");
                    int indexsep = Math.max(destinationPath.lastIndexOf("/"), destinationPath.lastIndexOf("\\"));
                    indexdot = indexdot < 0 ? 0 : indexdot;
                    indexsep = indexsep < 0 ? 0 : indexsep;
                    tempFname = KeyGen.getCommonKeyGen("attach");
                    tempPath = destinationPath.substring(0, indexsep + 1) + tempFname + destinationPath.substring(indexdot);
                    scaled.setFileName(tempPath);
                    scaled.writeImage(info);
                    tempPathList.add(sourcePath);
                }
                else
                {
                    scaled.setFileName(destinationPath);
                    scaled.writeImage(info);
                }
            }
            if (maxSize > 0)
            {
                File file = new File(destinationPath);
                if (file.length() > MAX_SIZE)
                {
                    int index = destinationPath.lastIndexOf(".");
                    index = index < 0 ? 0 : index;
                    if (scaled == null)
                    {
                        scaled = image.scaleImage(ingWidth, ingHeight);//小图片文件的大小.
                    }
                    int reate = 10000;
                    while (file.length() > MAX_SIZE)
                    {
                        if (tempPath == null)
                        {
                            tempPathList.add(sourcePath);
                        }
                        else
                        {
                            tempPathList.add(tempPath);
                        }
                        if (sourcePath.toUpperCase().endsWith("PNG"))
                        {
                            reate -= reate > 1000 ? 1000 : reate > 100 ? 100 : reate > 10 ? 10 : 1;
                            int nw = ingWidth * reate / 10000;
                            int nh = ingHeight * reate / 10000;
                            scaled = image.scaleImage(nw, nh);//小图片文件的大小.
                        }
                        else
                        {
                            qulity -= 10;
                            info.setQuality(qulity);
                        }
                        int indexdot = destinationPath.lastIndexOf(".");
                        int indexsep = Math.max(destinationPath.lastIndexOf("/"), destinationPath.lastIndexOf("\\"));
                        indexdot = indexdot < 0 ? 0 : indexdot;
                        indexsep = indexsep < 0 ? 0 : indexsep;
                        tempFname = KeyGen.getCommonKeyGen("attach");
                        tempPath = destinationPath.substring(0, indexsep + 1) + tempFname + destinationPath.substring(indexdot);
                        scaled.setFileName(tempPath);
                        scaled.writeImage(info);
                        file = new File(tempPath);
                    }
                }
            }
            if (thumbnails)
            {
                thumScaled.setFileName(thumbnailsPath);
                thumScaled.writeImage(info);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if (scaled != null)
            {
                scaled.destroyImages();
            }
            if (thumScaled != null)
            {
                thumScaled.destroyImages();
            }
        }
        new Thread(new Runnable()
        {

            @Override
            public void run()
            {
                try
                {
                    Thread.sleep(1000);
                    String path = null;
                    while ((path = tempPathList.poll()) != null)
                    {
                        File file = new File(path);
                        file.delete();
                    }
                }
                catch (InterruptedException e)
                {
                }
            }
        }).start();
        return tempFname;
    }
}
