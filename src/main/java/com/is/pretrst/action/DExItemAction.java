package com.is.pretrst.action;

import org.apache.struts2.convention.annotation.Namespace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.base.mybatis.BaseStruts2Action;
import com.is.pretrst.entity.DExItem;
import com.is.pretrst.entity.query.DExItemQuery;
import com.is.pretrst.service.DExItemServiceImpl;


/**
 *
 * @ClassName: DExItemAction
 * @Description: DExItem表对应的Action类
 * @author 
 * @date 2013-09-10 10:26:00 *
 */
@Namespace("/rst")
public class DExItemAction extends BaseStruts2Action{
    private static final long serialVersionUID = 1L;

    @SuppressWarnings("unused")
    private static final Logger LOGGER = LoggerFactory.getLogger(DExItemAction.class);

    private DExItem entity;
    private DExItemQuery queryEntity;
    @Autowired
    private DExItemServiceImpl dexItemserviceImpl;
    private String projectId;
    
    /**
     * @return Returns the projectId.
     */
    public String getProjectId()
    {
        return projectId;
    }

    public void setProjectId(String projectId)
    {
        this.projectId = projectId;
    }

    public DExItemAction() {
        super();
        if(entity==null){
            entity = new DExItem();
        }
        if(queryEntity==null){
            queryEntity = new DExItemQuery();
        }
    }

    public void setDexItemserviceImpl(DExItemServiceImpl dexItemserviceImpl)
    {
        this.dexItemserviceImpl = dexItemserviceImpl;
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
        queryEntity.setProjectId(projectId);
        page = dexItemserviceImpl.pageQuery(queryEntity);
    	return "DExItem/list";
    }
}