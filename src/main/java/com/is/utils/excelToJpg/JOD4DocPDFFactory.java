package com.is.utils.excelToJpg;

import java.io.File;

import com.is.utils.excelToJpg.JOD4DocToPDF;

public class JOD4DocPDFFactory
{
    //    private static JOD4DocPDFFactory jod4DocPDFFactory;

    private static JOD4DocToPDF t = new JOD4DocToPDF();

    //    static{
    //        t = new JOD4DocToPDF();
    //    }

    private JOD4DocPDFFactory()
    {
        //        t = new JOD4DocToPDF();
    }

//    public static JOD4DocPDFFactory getJOD4DocPDFFactory()
//    {
//        if (jod4DocPDFFactory == null)
//        {
//            synchronized (JOD4DocPDFFactory.class)
//            {
//                if (jod4DocPDFFactory == null)
//                {
//                    jod4DocPDFFactory = new JOD4DocPDFFactory();
//                }
//            }
//        }
//        return jod4DocPDFFactory;
//    }

    public static void docToPdfFactory(File inputFile, File outputFile, String pdfPath)
    {
        t.docToPdf(inputFile, outputFile, pdfPath);
    }
}
