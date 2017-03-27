package com.is.pretrst.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.base.mybatis.Mybatis3Dao;
import com.is.pretrst.entity.MWorkshop;
import com.is.pretrst.entity.MWorkshopItem;


/**
 *
 * @ClassName: MWorkshopItemDaoImpl
 * @Description: MWorkshopItem表对应的数据库操作类
 * @author 
 * @date 2013-10-31 18:27:43 *
 */
@Component 
public class MWorkshopItemDaoImpl extends Mybatis3Dao<MWorkshopItem>{

    @SuppressWarnings("unused")
    private static final Logger LOGGER = LoggerFactory.getLogger(MWorkshopItemDaoImpl.class);

    protected static final String NAMESPACE = "MWorkshopItem";

    @Override
    public String getIbatisMapperNamesapce() {
        return NAMESPACE;
    }
}