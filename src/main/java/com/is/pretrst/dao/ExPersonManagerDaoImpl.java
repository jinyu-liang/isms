package com.is.pretrst.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.base.mybatis.Mybatis3Dao;
import com.base.mybatis.Page;
import com.is.pretrst.entity.ExPersonManager;
import com.is.pretrst.entity.query.ExPersonManagerQuery;


/**
 *
 * @ClassName: ExPersonManagerDaoImpl
 * @Description: ExPersonManager表对应的数据库操作类
 * @author 
 * @date 2013-09-11 17:24:48 *
 */
@Component 
public class ExPersonManagerDaoImpl extends Mybatis3Dao<ExPersonManager>{

    @SuppressWarnings("unused")
    private static final Logger LOGGER = LoggerFactory.getLogger(ExPersonManagerDaoImpl.class);

    protected static final String NAMESPACE = "ExPersonManager";

    @Override
    public String getIbatisMapperNamesapce() {
        return NAMESPACE;
    }

	public Page selectExpersonManagerPage(ExPersonManagerQuery queryEntity) {
		return this.pageQuery("ExPersonManager.selectByPage", queryEntity);
	}
}