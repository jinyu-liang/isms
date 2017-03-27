package com.is.utils.excelToJpg;

import java.io.File;
import java.net.ConnectException;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.artofsolving.jodconverter.DocumentConverter;
import com.artofsolving.jodconverter.openoffice.connection.OpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.connection.SocketOpenOfficeConnection;
import com.artofsolving.jodconverter.openoffice.converter.OpenOfficeDocumentConverter;
import com.is.pretrst.dao.DDeliveryPlanDaoImpl;
import com.is.pretrst.dao.DInvoiceImageDaoImpl;
import com.is.pretrst.entity.DDeliveryPlan;
import com.is.pretrst.entity.DInvoiceImage;
import com.is.utils.PublicDict;
import com.is.utils.date.DateUtil;

public class JOD4DocToPDF
{
    private DDeliveryPlanDaoImpl ddeliveryPlanDaoImpl;

    private DInvoiceImageDaoImpl dinvoiceImageDaoImpl;

    @Autowired
    public void setDdeliveryPlanDao(DDeliveryPlanDaoImpl ddeliveryPlanDaoImpl)
    {
        this.ddeliveryPlanDaoImpl = ddeliveryPlanDaoImpl;
    }

    @Autowired
    public void setDinvoiceImageDao(DInvoiceImageDaoImpl dinvoiceImageDaoImpl)
    {
        this.dinvoiceImageDaoImpl = dinvoiceImageDaoImpl;
    }

    /**
     * office转pdf文件
     * @param inputFile
     * @param outputFile
     * @param pdfPath
     */
    public void docToPdf(File inputFile, File outputFile, String pdfPath)
    {
        File file = new File(outputFile.toString().replace(outputFile.getName(), ""));
        if (!file.exists() && !file.isDirectory()) //判断文件存入路径是否存在，不存在则创建   
        {
            file.mkdir();
        }
        Date start = new Date();
        // connect to an OpenOffice.org instance running on port 8100
        OpenOfficeConnection connection = new SocketOpenOfficeConnection(8100);
        try
        {
            connection.connect();

            // convert
            DocumentConverter converter = new OpenOfficeDocumentConverter(connection);

            converter.convert(inputFile, outputFile);
        }
        catch (ConnectException cex)
        {
        }
        finally
        {
            // close the connection
            if (connection != null)
            {
                connection.disconnect();
                connection = null;
            }
        }
        long l = (start.getTime() - new Date().getTime());
        long day = l / (24 * 60 * 60 * 1000);
        long hour = (l / (60 * 60 * 1000) - day * 24);
        long min = ((l / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (l / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
    }

    /**
     * 
     * <p>文件名称: JOD4DocToPDF.java</p>
     * <p>文件描述: excel文件转pdf的线程</p>
     * <p>版权所有: 版权所有(C)2013-2020</p>
     * <p>公   司: IS软件事业部</p>
     * <p>内容摘要:线程内调用pdf转jpg的线程 </p>
     * <p>其他说明:excel多页转pdf也只有一个文件，所以pdf的名称提前定义好，供pdf转jpg使用</p>
     * <p>完成日期：2013-9-9</p>
     * <p>修改记录0：无</p>
     * @version 1.0
     * @author  
     */
    class excel2PdfThread extends java.lang.Thread
    {
        public File   inputFile;

        public File   outputFile;

        public String pdfPath;

        public String id;

        public int    flag;

        public void run()
        {
            JOD4DocPDFFactory.docToPdfFactory(inputFile, outputFile, pdfPath);//※※调用工厂类的静态方法来执行转换//转成PDF
            Pdf2ImageThread pdf2image = new Pdf2ImageThread();
            pdf2image.pdfPath = pdfPath;
            pdf2image.id = id;
            pdf2image.flag = flag;
            pdf2image.start();
        }

    }

    /**
     * 
     * <p>文件名称: JOD4DocToPDF.java</p>
     * <p>文件描述: pdf文件转换成jpg</p>
     * <p>版权所有: 版权所有(C)2013-2020</p>
     * <p>公   司: IS软件事业部</p>
     * <p>内容摘要: 把转换成功的图片名称存入到数据库</p>
     * <p>其他说明: </p>
     * <p>完成日期：2013-9-9</p>
     * <p>修改记录0：无</p>
     * @version 1.0
     * @author  
     */
    class Pdf2ImageThread extends java.lang.Thread
    {

        public String pdfPath;

        public String id;

        public int    flag;

        public void run()
        {
            try
            {
                List<String> imageNames = Pdf2Image.tranfer(pdfPath, PublicDict.JPGPATH, PublicDict.ZOOM);//把PDF转换成图片

                if (imageNames.size() > 0)
                {//保存把图片路径更新到到数据库
                    if (flag == 1)
                    {//发运计划使用
                        DDeliveryPlan entity = new DDeliveryPlan();
                        entity.setPlanId(id);
                        entity.setPlanImagePath(PublicDict.JPGPATH + imageNames.get(0));
                        ddeliveryPlanDaoImpl.updateByEntity(entity);
                    }
                    else if (flag == 0)
                    {//发运清单使用 
                        DInvoiceImage entity = new DInvoiceImage();
                        entity.setInvoiceId(id.trim());
                        entity.setFileName(imageNames.get(0));
                        dinvoiceImageDaoImpl.updateByEntity(entity);
                    }
                }
            }
            catch (Exception e)
            {
            }
        }

    }

    /**
     * excel转图片的入口方法(多线程 -暂不用)
     * @param filePath excel源文件路径
     * @param id 对象主键id
     * @param 发运计划使用 （1） 还是 发运清单使用 （0）
     */
    public void excelToJpg(String filePath, String id, int flag)
    {
        String pdfPath = ServletActionContext.getServletContext().getRealPath("/") + "temp" + File.separator + DateUtil.getCurDateTimeMil() + ".pdf";
        excel2PdfThread excel2PdfT = new excel2PdfThread();
        //  File file = new File("d:\\test\\test");

        // excel2PdfT.inputFile = new File(file.getName() + ".xls");
        // file.renameTo(excel2PdfT.inputFile);

        excel2PdfT.inputFile = new File("d:\\test\\test.xls");//"d:\\test\\test.xls"

        excel2PdfT.outputFile = new File(pdfPath);
        excel2PdfT.pdfPath = pdfPath;
        excel2PdfT.id = id;
        excel2PdfT.flag = flag;
        excel2PdfT.start();
    }

    public static void main(String[] args)
    {
        JOD4DocToPDF jp = new JOD4DocToPDF();
        jp.excelToJpg("", "137", 1);
    }
}
