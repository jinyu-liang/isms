package com.is.pretrst.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.base.mybatis.Mybatis3Dao;
import com.base.mybatis.Page;
import com.is.pretrst.entity.MWorkshopQuality;
import com.is.pretrst.entity.query.MWorkshopQualityQuery;


/**
 *
 * @ClassName: MWorkshopQualityDaoImpl
 * @Description: MWorkshopQuality表对应的数据库操作类
 * @author 
 * @date 2013-09-11 17:24:27 *
 */
@Component 
public class MWorkshopQualityDaoImpl extends Mybatis3Dao<MWorkshopQuality>{

    @SuppressWarnings("unused")
    private static final Logger LOGGER = LoggerFactory.getLogger(MWorkshopQualityDaoImpl.class);

    protected static final String NAMESPACE = "MWorkshopQuality";

    @Override
    public String getIbatisMapperNamesapce() {
        return NAMESPACE;
    }

	
	/**
	 * 根据查询条件获取分页列表
	 * 
	 * @param MWorkshopQualityQuery
	 * @return
	 */
	public Page selectReportPage(MWorkshopQualityQuery queryEntity) {
		return this.pageQuery("MWorkshopQuality.selectByPage", queryEntity);
	}
}