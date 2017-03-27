package com.is.pretrst.action;

import org.apache.struts2.convention.annotation.Namespace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.base.mybatis.BaseStruts2Action;
import com.is.pretrst.entity.DDeliveryItem;
import com.is.pretrst.entity.query.DDeliveryItemQuery;
import com.is.pretrst.service.DDeliveryItemServiceImpl;


/**
 *
 * @ClassName: DDeliveryItemAction
 * @Description: DDeliveryItem表对应的Action类
 * @author 
 * @date 2013-09-10 10:26:50 *
 */
@Namespace("/rst")
public class DDeliveryItemAction extends BaseStruts2Action{
    private static final long serialVersionUID = 1L;

    @SuppressWarnings("unused")
    private static final Logger LOGGER = LoggerFactory.getLogger(DDeliveryItemAction.class);
    private DDeliveryItem entity;
    private DDeliveryItemQuery queryEntity;
    private DDeliveryItemServiceImpl  ddeliveryItemServiceImpl;
    public DDeliveryItemAction() {
        super();
        if(entity==null){
            entity = new DDeliveryItem();
        }
        if(queryEntity==null){
            queryEntity = new DDeliveryItemQuery();
        }
    }

    public DDeliveryItem getEntity()
    {
        return entity;
    }

    public void setEntity(DDeliveryItem entity)
    {
        this.entity = entity;
    }

    public DDeliveryItemQuery getQueryEntity()
    {
        return queryEntity;
    }

    public void setQueryEntity(DDeliveryItemQuery queryEntity)
    {
        this.queryEntity = queryEntity;
    }
    @Autowired
    public void setDeliveryItemServiceImpl(DDeliveryItemServiceImpl ddeliveryItemServiceImpl)
    {
        this.ddeliveryItemServiceImpl = ddeliveryItemServiceImpl;
    }



    public String toCreate() throws Exception {
        return null;
    }
    

	
    public String create() throws Exception {
    	return null;
    }

	
    public String toEdit() throws Exception {
    	return null;
    }

	
    public String edit() throws Exception {
    	return null;
    }

	
    public String delete() throws Exception {
    	return null;
    }

	
    public String view() throws Exception {
    	return null;
    }

	
    public String toList() throws Exception {
    	return null;
    }

	
    public String list() throws Exception {
      page = ddeliveryItemServiceImpl.queryPage(queryEntity);
    	return "DDeliveryItem/list";
    }
}
