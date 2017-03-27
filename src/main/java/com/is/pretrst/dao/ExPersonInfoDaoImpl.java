package com.is.pretrst.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.base.mybatis.Mybatis3Dao;
import com.is.pretrst.entity.ExPersonInfo;
import com.is.pretrst.entity.MTransItem;
import com.is.pretrst.entity.query.ExPersonInfoQuery;
import com.is.utils.StringUtils;


/**
 *
 * @ClassName: ExPersonInfoDaoImpl
 * @Description: ExPersonInfo表对应的数据库操作类
 * @author 
 * @date 2013-12-13 16:28:09 *
 */
@Component 
public class ExPersonInfoDaoImpl extends Mybatis3Dao<ExPersonInfo>{

    @SuppressWarnings("unused")
    private static final Logger LOGGER = LoggerFactory.getLogger(ExPersonInfoDaoImpl.class);

    protected static final String NAMESPACE = "ExPersonInfo";

    @Override
    public String getIbatisMapperNamesapce() {
        return NAMESPACE;
    }
	/**
	 * 检出
	 * @param info
	 * @return
	 */
	public int perInfocheckout(ExPersonInfo info) {
		return getSqlSessionTemplate().update("ExPersonInfo.perInfocheckout", info);
	}
	/**
	 * 检出
	 * @param info
	 * @return
	 */
	public int perInfoDeal(ExPersonInfo info) {
		return getSqlSessionTemplate().update("ExPersonInfo.perInfoDeal", info);
	}
	/**
	 * 
	 * @param info
	 * @return
	 */
	public List<ExPersonInfo> getPersonInfoListByName() {
		return this.getSqlSessionTemplate().selectList("ExPersonInfo.getPersonInfoListByName", new ExPersonInfo());
	}
	/**
	 * 
	 * @param info
	 * @return
	 */
	public List<ExPersonInfo> getPersonInfoListToMobile(ExPersonInfoQuery info) {
		return this.getSqlSessionTemplate().selectList("ExPersonInfo.getPersonInfoListToMobile", info);
	}
}