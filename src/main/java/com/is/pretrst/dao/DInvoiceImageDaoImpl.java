package com.is.pretrst.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.base.mybatis.Mybatis3Dao;
import com.is.pretrst.entity.DInvoiceImage;


/**
 *
 * @ClassName: DInvoiceImageDaoImpl
 * @Description: DInvoiceImage表对应的数据库操作类
 * @author 
 * @date 2013-09-10 10:26:21 *
 */
@Component 
public class DInvoiceImageDaoImpl extends Mybatis3Dao<DInvoiceImage>{

    @SuppressWarnings("unused")
    private static final Logger LOGGER = LoggerFactory.getLogger(DInvoiceImageDaoImpl.class);

    protected static final String NAMESPACE = "DInvoiceImage";

    @Override
    public String getIbatisMapperNamesapce() {
        return NAMESPACE;
    }
}