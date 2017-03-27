package com.is.pretrst.dao;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.base.mybatis.Mybatis3Dao;
import com.is.pretrst.entity.DDeliveryItem;

@Component
public class DDeliveryItemDaoImpl extends Mybatis3Dao<DDeliveryItem>
{
    @SuppressWarnings("unused")
    private static final Logger   LOGGER    = LoggerFactory.getLogger(DDeliveryItemDaoImpl.class);

    protected static final String NAMESPACE = "DDeliveryItem";

    @Override
    public String getIbatisMapperNamesapce()
    {
        return NAMESPACE;
    }
    
    
    
    
    /**
     * 批量添加发料产品信息
     * 
     * @param List<DDeliveryItem> ddeliveryItemList
     * @return 操作的行数
     */
    public int insertManyItemInfo(List<DDeliveryItem> ddeliveryItemList) {
        return getSqlSessionTemplate().insert("DDeliverItem.batchInsertItemInfo",
                ddeliveryItemList);
    }
}