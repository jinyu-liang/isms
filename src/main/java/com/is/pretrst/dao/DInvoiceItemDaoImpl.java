package com.is.pretrst.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.base.mybatis.Mybatis3Dao;
import com.is.pretrst.entity.DInvoiceItem;


/**
 *
 * @ClassName: DInvoiceItemDaoImpl
 * @Description: DInvoiceItem表对应的数据库操作类
 * @author 
 * @date 2013-09-10 10:26:16 *
 */
@Component 
public class DInvoiceItemDaoImpl extends Mybatis3Dao<DInvoiceItem>{

    @SuppressWarnings("unused")
    private static final Logger LOGGER = LoggerFactory.getLogger(DInvoiceItemDaoImpl.class);

    protected static final String NAMESPACE = "DInvoiceItem";

    @Override
    public String getIbatisMapperNamesapce() {
        return NAMESPACE;
    }
    
    /**
     * 批量添加出门单产品信息
     * 
     * @param List<DInvoiceItem> dinvoiceItemList
     * @return 操作的行数
     */
    public int insertManyItemInfo(List<DInvoiceItem> dinvoiceItemList) {
        return getSqlSessionTemplate().insert("DInvoiceItem.batchInsertItemInfo",
                dinvoiceItemList);
    }
}