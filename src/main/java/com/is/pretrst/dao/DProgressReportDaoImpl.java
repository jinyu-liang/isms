package com.is.pretrst.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.base.mybatis.Mybatis3Dao;
import com.is.pretrst.entity.DProgressReport;

/**
 *
 * @ClassName: DProgressReportDaoImpl
 * @Description: DProgressReport表对应的数据库操作类
 * @author 
 * @date 2013-09-10 10:26:30 *
 */
@Component
public class DProgressReportDaoImpl extends Mybatis3Dao<DProgressReport>
{

    private static final Logger   LOGGER    = LoggerFactory.getLogger(DProgressReportDaoImpl.class);

    protected static final String NAMESPACE = "DProgressReport";

    @Override
    public String getIbatisMapperNamesapce()
    {
        return NAMESPACE;
    }

    /**
     * 查询有几个月份
     * @return
     */

    public List<String> selectByMonth()
    {
        return this.getSqlSessionTemplate().selectList("DProgressReport.selectByMonth");
    }

    /**
     * 根据月份查询报告
     * @param dateFlag
     * @return
     */
    public List<DProgressReport> selectReports(String dateFlag)
    {

        try
        {
            return this.getSqlSessionTemplate().selectList("DProgressReport.selectReport", dateFlag);
        }
        catch (Exception e)
        {
            // TODO Auto-generated catch block
            if (LOGGER.isErrorEnabled())
            {
                LOGGER.error("selectReports异常：{}", e);
            }
        }
        return null;
    }
}