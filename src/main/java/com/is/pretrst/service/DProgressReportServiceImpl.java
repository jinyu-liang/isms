package com.is.pretrst.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.is.pretrst.dao.DProgressReportDaoImpl;
import com.is.pretrst.entity.DProgressReport;

/**
 *
 * @ClassName: DProgressReportServiceImpl
 * @Description: DProgressReport表对应的服务类
 * @author 
 * @date 2013-09-10 10:26:31 *
 */
@Component
@Transactional
public class DProgressReportServiceImpl
{
    @SuppressWarnings("unused")
    private static Logger          logger = LoggerFactory.getLogger(DProgressReport.class);

    @Autowired
    private DProgressReportDaoImpl dprogressReportDaoImpl;

    /**
     * 查询dprogressReport对象list集合
     * @param entity
     * @return
     */
    public Map<String, List<DProgressReport>> selectProgressIndex()
    {
        Map<String, List<DProgressReport>> reportMap = new TreeMap<String, List<DProgressReport>>();
        List<DProgressReport> reportList = new ArrayList<DProgressReport>();

        List<String> mouth = new ArrayList<String>();
        mouth = dprogressReportDaoImpl.selectByMonth();
        //mouth  = this.Sort(mouth);
        for (String m : mouth)
        {
            reportList = dprogressReportDaoImpl.selectReports(m);
            int i =Integer.parseInt( m.substring(5, m.length()-1));
            if(i<10){
                reportMap.put(m.replace("y", "年0").replace("m", "月"), reportList);
            }else{
            reportMap.put(m.replace("y", "年").replace("m", "月"), reportList);
            }
        }
        return reportMap;
    }
    
    public Map<String, String> defaultSelect(Map<String, List<DProgressReport>> map)
    {
        Map<String, String> reportMap = new HashMap<String, String>();
         //reportMap.greportMap.size()-1
        return reportMap;
    }
   
}
