package com.is.utils.excelToJpg;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;

import org.icepdf.core.exceptions.PDFException;
import org.icepdf.core.exceptions.PDFSecurityException;
import org.icepdf.core.pobjects.Document;
import org.icepdf.core.pobjects.Page;
import org.icepdf.core.util.GraphicsRenderingHints;

import com.is.utils.keyUtils.KeyGen;

public class Pdf2Image
{
    public static final String FILETYPE_JPG = "jpg";

    public static final String SUFF_IMAGE   = "." + FILETYPE_JPG;

    /**
     * 
     * 将指定pdf文件的首页转换为指定路径的缩略图
     * 
     * @param filepath
     *            原文件路径，例如d:/test.pdf
     * 
     * @param imagepath
     *            图片生成路径，例如 d:/test-1.jpg
     * 
     * @param zoom
     *            缩略图显示倍数，1表示不缩放，0.3则缩小到30%
     * 
     * @return List<String> 生成的图片名称,如果页数超过9页，返回List<String>第0个值为1，表示超页了
     */

    public static List<String> tranfer(String filepath, String imagepath, float zoom) throws PDFException, PDFSecurityException, IOException
    {
        File file = new File(imagepath);
        if (!file.exists() && !file.isDirectory()) // 判断文件夹是否存在，不存在则创建
        {
            file.mkdir();
        }
        // ICEpdf document class
        String imageName =KeyGen.getCommonKeyGenImage();
        List<String> imageNames = new ArrayList<String>();
        Document document = null;

        float rotation = 0f;

        document = new Document();

        document.setFile(filepath);

        int maxPages = document.getPageTree().getNumberOfPages();
        if(maxPages>9){//如果页数超过9页，返回List<String>第0个值为1，表示超页了
            imageNames.add("1");
            return imageNames;
        }
        for (int i = 0; i < maxPages; i++)//转图片
        {
            BufferedImage img = null;
            try
            {
                img = (BufferedImage) document.getPageImage(i, GraphicsRenderingHints.SCREEN, Page.BOUNDARY_CROPBOX, rotation, zoom);
            }
            catch (Exception e)
            {
            }
            //document.dispose();
            Iterator<?> iter = ImageIO.getImageWritersBySuffix(FILETYPE_JPG);

            ImageWriter writer = (ImageWriter) iter.next();

            File outFile = new File(imagepath + imageName + i + SUFF_IMAGE);

            FileOutputStream out = new FileOutputStream(outFile);

            ImageOutputStream outImage = ImageIO.createImageOutputStream(out);

            imageNames.add(imageName + i + SUFF_IMAGE);

            writer.setOutput(outImage);

            writer.write(new IIOImage(img, null, null));

            img.flush();
            out.close();
        } 
        document.dispose();
        return imageNames;
    }
}
