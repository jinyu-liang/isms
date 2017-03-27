package com.is.pretrst.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.base.mybatis.Mybatis3Dao;
import com.is.pretrst.entity.DInvoice;


/**
 *
 * @ClassName: DInvoiceDaoImpl
 * @Description: DInvoice表对应的数据库操作类
 * @author 
 * @date 2013-09-10 10:26:11 *
 */
@Component 
public class DInvoiceDaoImpl extends Mybatis3Dao<DInvoice>{

    @SuppressWarnings("unused")
    private static final Logger LOGGER = LoggerFactory.getLogger(DInvoiceDaoImpl.class);

    protected static final String NAMESPACE = "DInvoice";

    @Override
    public String getIbatisMapperNamesapce() {
        return NAMESPACE;
    }
    
    /**
     * 根据查询条件查询出门单对象 包含对应的出门单详情图片
     * @param entity
     */
    public List<DInvoice> selectByEntity(DInvoice entity ){
        List<DInvoice> list = getSqlSessionTemplate().selectList(
                NAMESPACE+".selectByEntity", entity);
        return list;
    }
}