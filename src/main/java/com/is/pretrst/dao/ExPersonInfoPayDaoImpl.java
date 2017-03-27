package com.is.pretrst.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.base.mybatis.Mybatis3Dao;

import com.is.pretrst.entity.ExPersonInfo;
import com.is.pretrst.entity.ExPersonInfoPay;

@Component
public class ExPersonInfoPayDaoImpl extends Mybatis3Dao<ExPersonInfoPay>{

    @SuppressWarnings("unused")
    private static final Logger LOGGER = LoggerFactory.getLogger(ExPersonInfoPayDaoImpl.class);

    protected static final String NAMESPACE = "ExPersonInfoPay";

    @Override
    public String getIbatisMapperNamesapce() {
        return NAMESPACE;
    }

	/**
	 * 确认完成
	 * @param info
	 * @return
	 */
	public int perInfoDeal(ExPersonInfoPay info) {
		return getSqlSessionTemplate().update("ExPersonInfoPay.perInfoDeal", info);
	}
	
	public ExPersonInfoPay getcuizhanginfo(ExPersonInfoPay entity){
			return (ExPersonInfoPay) getSqlSessionTemplate().selectOne("ExPersonInfoPay.getcuizhang", entity);
	}

//	/**
//	 * 
//	 * @param info
//	 * @return
//	 */
//	public List<ExPersonInfoPay> getPersonInfoListByName() {
//		return this.getSqlSessionTemplate().selectList("ExPersonInfoPay.getPersonInfoListByName", new ExPersonInfoPay());
//	}
//	/**
//	 * 
//	 * @param info
//	 * @return
//	 */
//	public List<ExPersonInfoPay> getPersonInfoListToMobile(ExPersonInfoPay info) {
//		return this.getSqlSessionTemplate().selectList("ExPersonInfoPay.getPersonInfoListToMobile", info);
//	}
}
