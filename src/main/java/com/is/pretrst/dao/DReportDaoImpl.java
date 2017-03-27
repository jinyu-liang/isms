package com.is.pretrst.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.base.mybatis.Mybatis3Dao;
import com.base.mybatis.Page;
import com.is.ggkz.entity.query.GgkzUserInfoQuery;
import com.is.pretrst.entity.DReport;
import com.is.pretrst.entity.query.DReportQuery;


/**
 *
 * @ClassName: DReportDaoImpl
 * @Description: DReport表对应的数据库操作类
 * @author 
 * @date 2013-09-11 17:24:27 *
 */
@Component 
public class DReportDaoImpl extends Mybatis3Dao<DReport>{

    @SuppressWarnings("unused")
    private static final Logger LOGGER = LoggerFactory.getLogger(DReportDaoImpl.class);

    protected static final String NAMESPACE = "DReport";

    @Override
    public String getIbatisMapperNamesapce() {
        return NAMESPACE;
    }

	
	/**
	 * 根据查询条件获取分页列表
	 * 
	 * @param DReportQuery
	 * @return
	 */
	public Page selectReportPage(DReportQuery queryEntity) {
		return this.pageQuery("DReport.selectByPage", queryEntity);
	}
}