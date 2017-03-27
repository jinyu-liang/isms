package com.is.pretrst.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.base.mybatis.Mybatis3Dao;
import com.is.pretrst.entity.RstVerInfo;


/**
 *
 * @ClassName: RstVerInfoDaoImpl
 * @Description: RstVerInfo表对应的数据库操作类
 * @author 
 * @date 2013-10-26 12:44:17 *
 */
@Component 
public class RstVerInfoDaoImpl extends Mybatis3Dao<RstVerInfo>{

    @SuppressWarnings("unused")
    private static final Logger LOGGER = LoggerFactory.getLogger(RstVerInfoDaoImpl.class);

    protected static final String NAMESPACE = "RstVerInfo";

    @Override
    public String getIbatisMapperNamesapce() {
        return NAMESPACE;
    }

	public int getMaxVerId() {
		 return (Integer) getSqlSessionTemplate().selectOne(
					"RstVerInfo.selectMaxVerId");
	}
	public RstVerInfo getMaxVerCode() {
		 return (RstVerInfo) getSqlSessionTemplate().selectOne(
					"RstVerInfo.getMaxVerCode");
	}
}