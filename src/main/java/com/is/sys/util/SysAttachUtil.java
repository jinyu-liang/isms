package com.is.sys.util;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springside.modules.utils.spring.SpringContextHolder;

import com.is.sys.entity.SysAttach;
import com.is.sys.service.SysAttachServiceImpl;

public class SysAttachUtil
{
    @SuppressWarnings("unused")
    private static final Logger LOGGER = LoggerFactory.getLogger(SysAttachUtil.class);

    private SysAttachUtil()
    {

    }

    public static List<SysAttach> getSysAttachById(String id)
    {
        List<SysAttach> resultList = new ArrayList<SysAttach>();
        if (id != null && !"".equals(id.trim()))
        {
            SysAttachServiceImpl sysAttachServiceImpl = (SysAttachServiceImpl) SpringContextHolder.getBean("sysAttachServiceImpl");

            String[] idarray = id.split(";");

            for (int i = 0; i < idarray.length; i++)
            {
                String attachId = idarray[i];

                SysAttach attach = sysAttachServiceImpl.getAttachById(attachId);
                if (attach != null)
                {
                    String inputFilePath = attach.getAttachPath() + "/" + attach.getAttachId();// 要下载的文件名称
                    attach.setAttachPath(inputFilePath);

                    resultList.add(attach);

                    //                  @SuppressWarnings("deprecation")
                    //                    String filepath = ServletActionContext.getRequest().getRealPath("/" + inputFilePath);
                    //                    File updownloadfile = new File(filepath);
                    //                    try
                    //                    {
                    //                        if (!updownloadfile.exists())
                    //                        {
                    //                            //"文件损坏
                    //                        }
                    //                        else
                    //                        {
                    //                            //"文件存在
                    //                        }
                    //                    }
                    //                    catch (Exception e)
                    //                    {
                    //                    }
                }
            }
        }
        return resultList;
    }
}
