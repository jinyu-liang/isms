package com.is.ggkz.action;
import org.apache.struts2.convention.annotation.Namespace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.base.mybatis.BaseStruts2Action;
import com.is.ggkz.dao.GgkzUserRoleDaoImpl;
import com.is.ggkz.entity.query.GgkzUserRoleQuery;

/**
 *
 * @ClassName: GgkzUserRoleAction
 * @Description: GgkzUserRole表对应的Action类
 * @author 
 * @date 2013-02-27 14:19:26 *
 */
@Namespace("/ggkz")
public class GgkzUserRoleAction extends BaseStruts2Action {
    private static final long serialVersionUID = 1L;

    @SuppressWarnings("unused")
    private static final Logger LOGGER = LoggerFactory.getLogger(GgkzUserRoleAction.class);

    public GgkzUserRoleAction() {
        super();
    }
    /** test */
    private GgkzUserRoleDaoImpl ggkzUserRoleDaoImpl;

	public String list() throws Exception {
		// TODO Auto-generated method stub
		ggkzUserRoleDaoImpl.selectByEntity(new GgkzUserRoleQuery());
		return null;
	}

	public String input() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String save() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String delete() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Autowired
	public void setGgkzUserRoleDaoImpl(GgkzUserRoleDaoImpl ggkzUserRoleDaoImpl) {
		this.ggkzUserRoleDaoImpl = ggkzUserRoleDaoImpl;
	}
	
}
