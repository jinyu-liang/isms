package com.is.pretrst.action;

import org.apache.struts2.convention.annotation.Namespace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.base.mybatis.BaseStruts2Action;
import com.is.pretrst.entity.DExProject;
import com.is.pretrst.entity.query.DExProjectQuery;
import com.is.pretrst.service.DExProjectServiceImpl;


/**
 *
 * @ClassName: DExProjectAction
 * @Description: DExProject表对应的Action类
 * @author 
 * @date 2013-09-10 10:26:06 *
 */
@Namespace("/rst")
public class DExProjectAction extends BaseStruts2Action{
    private static final long serialVersionUID = 1L;

    @SuppressWarnings("unused")
    private static final Logger LOGGER = LoggerFactory.getLogger(DExProjectAction.class);
    private DExProject entity;
    private DExProjectQuery queryEntity;
    private DExProjectServiceImpl dExProjectServiceImpl;
    public DExProjectAction() {
        super();
        if (entity == null)
        {
            entity = new DExProject();
        }
        if (queryEntity == null)
        {
            queryEntity = new DExProjectQuery();
        }
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
        //获取数据字典：
//        workShopList = PmsDictUtil.getDictByType("workshop");
        page = dExProjectServiceImpl.selectDExProjectPage(queryEntity);
        return "DExProject/list";
    }

@Autowired
	public void setdExProjectServiceImpl(DExProjectServiceImpl dExProjectServiceImpl) {
		this.dExProjectServiceImpl = dExProjectServiceImpl;
	}


	public DExProjectServiceImpl getdExProjectServiceImpl() {
		return dExProjectServiceImpl;
	}
}