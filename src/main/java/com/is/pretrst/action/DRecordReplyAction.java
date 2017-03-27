package com.is.pretrst.action;

import org.apache.struts2.convention.annotation.Namespace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.base.mybatis.BaseStruts2Action;
import com.base.mybatis.Page;
import com.is.pretrst.entity.DRecordReply;
import com.is.pretrst.entity.query.DRecordReplyQuery;
import com.is.pretrst.service.DRecordReplyServiceImpl;


/**
 *
 * @ClassName: DRecordReplyAction
 * @Description: DRecordReply表对应的Action类
 * @author 
 * @date 2013-09-10 10:26:33 *
 */
@Namespace("/rst")
public class DRecordReplyAction extends BaseStruts2Action{
    private static final long serialVersionUID = 1L;

    @SuppressWarnings("unused")
    private static final Logger LOGGER = LoggerFactory.getLogger(DRecordReplyAction.class);
    private DRecordReply entity;
    private DRecordReplyQuery queryEntity;
    private DRecordReplyServiceImpl  drecordReplyServiceImpl;
    private DRecordReply record;
    private Page recordPage;
    public DRecordReplyAction() {
        super();
        if(entity == null){
            entity = new DRecordReply();
        }
        if(queryEntity == null){
            queryEntity = new DRecordReplyQuery();
        }
    }

    public DRecordReply getEntity()
    {
        return entity;
    }

    public void setEntity(DRecordReply entity)
    {
        this.entity = entity;
    }

    public DRecordReplyQuery getQueryEntity()
    {
        return queryEntity;
    }

    public void setQueryEntity(DRecordReplyQuery queryEntity)
    {
        this.queryEntity = queryEntity;
    }

    public DRecordReply getRecord()
    {
        return record;
    }

    public void setRecord(DRecordReply record)
    {
        this.record = record;
    }

    /**
     * @return Returns the recordPage.
     */
    public Page getRecordPage()
    {
        return recordPage;
    }

    /**
     * @param recordPage The recordPage to set.
     */
    public void setRecordPage(Page recordPage)
    {
        this.recordPage = recordPage;
    }

    @Autowired
    public void setDrecordReplyServiceImpl(DRecordReplyServiceImpl drecordReplyServiceImpl)
    {
        this.drecordReplyServiceImpl = drecordReplyServiceImpl;
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
        queryEntity.setRecordId(record.getReplyId());
        recordPage = drecordReplyServiceImpl.pageQuery(queryEntity);
    	return null;
    }
}