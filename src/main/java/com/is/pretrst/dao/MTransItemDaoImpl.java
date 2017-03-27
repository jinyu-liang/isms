package com.is.pretrst.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.base.mybatis.Mybatis3Dao;
import com.is.pretrst.entity.MTransItem;
import com.is.pretrst.entity.MWorkshopItem;


/**
 *
 * @ClassName: MTransItemDaoImpl
 * @Description: MTransItem表对应的数据库操作类
 * @author 
 * @date 2013-10-31 18:27:50 *
 */
@Component 
public class MTransItemDaoImpl extends Mybatis3Dao<MTransItem>{

    @SuppressWarnings("unused")
    private static final Logger LOGGER = LoggerFactory.getLogger(MTransItemDaoImpl.class);

    protected static final String NAMESPACE = "MTransItem";

    @Override
    public String getIbatisMapperNamesapce() {
        return NAMESPACE;
    }
	/**
	 * 
	 * @param shop
	 * @return
	 */
	public List<MTransItem> getTransItemListByWscd(MTransItem shop) {
		return this.getSqlSessionTemplate().selectList("MTransItem.getTransItemListByWscd", shop);
	}
    
}