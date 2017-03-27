package com.is.pretrst.action;

import org.apache.struts2.convention.annotation.Namespace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.base.mybatis.BaseStruts2Action;
import com.is.pretrst.entity.RstUploadConf;
import com.is.pretrst.entity.query.RstUploadConfQuery;
import com.is.pretrst.service.RstUploadConfServiceImpl;


/**
 *
 * @ClassName: RstUploadConfAction
 * @Description: RstUploadConf表对应的Action类
 * @author 
 * @date 2013-10-26 12:44:20 *
 */
@Namespace("/rst")
public class RstUploadConfAction extends BaseStruts2Action{
    private static final long serialVersionUID = 1L;

    @SuppressWarnings("unused")
    private static final Logger LOGGER = LoggerFactory.getLogger(RstUploadConfAction.class);
    private RstUploadConf entity;
    private RstUploadConfQuery queryEntity;
    @Autowired
    private RstUploadConfServiceImpl uploadConfServiceImpl;
    
    public RstUploadConfAction() {       
        super();
        if(entity==null){
            entity = new RstUploadConf();
        }
        if(queryEntity==null){
            queryEntity = new RstUploadConfQuery();
        }
    }

    
	
    public String toCreate() throws Exception {
        return "";
    }

	
    public String create() throws Exception {
        return "";
    }

	
    public String toEdit() throws Exception {
        entity = uploadConfServiceImpl.selectOneByEntity(entity);
        return "RstUploadConf/edit";
    }

	
    public String edit() throws Exception {
       int i = uploadConfServiceImpl.insert(entity);
       if(i>0){
           setMessage("修改成功！");
       }else setInfoMessage("修改失败！");
       // return "RstUploadConf/edit";
       return JSON_DATA;
    }

	
    public String delete() throws Exception {
        return "";
    }

	
    public String view() throws Exception {
        return "";
    }

	
    public String toList() throws Exception {
        return "";
    }

	
    public String list() throws Exception {
        return "";
    }


  //getter setter
    public RstUploadConf getEntity()
    {
        return entity;
    }

    public void setEntity(RstUploadConf entity)
    {
        this.entity = entity;
    }

    public RstUploadConfQuery getQueryEntity()
    {
        return queryEntity;
    }

    public void setQueryEntity(RstUploadConfQuery queryEntity)
    {
        this.queryEntity = queryEntity;
    }

    public void setUploadConfServiceImpl(RstUploadConfServiceImpl uploadConfServiceImpl)
    {
        this.uploadConfServiceImpl = uploadConfServiceImpl;
    }


    @Override
    public String getWarnMessage()
    {
        return super.getWarnMessage();
    }

    @Override
    public String getMessage()
    {
        return super.getMessage();
    }
    @Override
    public String getInfoMessage()
    {
        return super.getInfoMessage();
    }
    
    
    
}
