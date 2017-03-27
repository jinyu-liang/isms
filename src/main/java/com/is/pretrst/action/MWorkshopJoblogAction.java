package com.is.pretrst.action;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.base.dict.PmsDictUtil;
import com.base.mybatis.BaseStruts2Action;
import com.is.ggkz.entity.GgkzUserInfo;
import com.is.ggkz.entity.GgkzUserRole;
import com.is.ggkz.entity.query.GgkzUserInfoQuery;
import com.is.pretrst.entity.MWorkshopJoblog;
import com.is.pretrst.service.MWorkshopJoblogServiceImpl;
import com.is.pretrst.entity.query.MWorkshopJoblogQuery;
import com.is.sys.entity.SysDict;
import com.is.utils.PublicDict;
import com.is.utils.StringUtils;
import com.is.utils.keyUtils.KeyGen;

/**
 * 
 * @ClassName: MWorkshopJoblogAction
 * @Description: MWorkshopJoblog表对应的Action类
 * @author
 * @date 2013-02-27 14:18:56 *
 */
@Namespace("/rst")
public class MWorkshopJoblogAction extends BaseStruts2Action {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private static final Logger LOGGER = LoggerFactory.getLogger(MWorkshopJoblogAction.class);

	@Autowired
	private MWorkshopJoblogServiceImpl MWorkshopJoblogServiceImpl;

	// 页面属性
	private MWorkshopJoblog entity; // 实体对象

	private MWorkshopJoblogQuery queryEntity; // query对象

	private Map<String, Integer> checkedReView;

	private Map<String, Integer> checkedMobileReView;

    private List<SysDict>           userDepartList;

	private String[] checkbox;

	private String[] mobileCheckbox;
	

	public MWorkshopJoblogAction() {
		super();
		if (entity == null) {
			entity = new MWorkshopJoblog();
		}
		if (queryEntity == null) {
			queryEntity = new MWorkshopJoblogQuery();
		}
	}


	/**
	 * 跳转到list页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		initUserDepartList();
		
		String pageSize = ServletActionContext.getRequest().getParameter("queryEntity.pageSize");
		String pageNumber = ServletActionContext.getRequest().getParameter("queryEntity.pageNumber");
		int pageNumber1 = (null != pageNumber && !"".equals(pageNumber) && !"null".equals(pageNumber)) ? Integer.valueOf(pageNumber):1;
		int pageSize1 = (null != pageSize && !"".equals(pageSize) && !"null".equals(pageSize)) ? Integer.valueOf(pageSize):20;

		queryEntity.setPageNumber(pageNumber1);
		queryEntity.setPageSize(pageSize1);
		queryEntity.setFquserid(entity.getFquserid());
		queryEntity.setFqusername(entity.getFqusername());
		queryEntity.setStart_date(entity.getStart_date());
		queryEntity.setEnd_date(entity.getEnd_date());
		queryEntity.setWs_cd(sessionUser.getDepartId());
		page = MWorkshopJoblogServiceImpl.pageQuery(queryEntity);

		return "MWorkshopJoblog/list";//页面名称
	}
	
	/**
     * 转向用户输入页面
     */
    public String toAdd() throws Exception
    {
    	entity.setFquserid(sessionUser.getUserId());
    	entity.setFqusername(sessionUser.getUsername());
    	entity.setWs_cd(sessionUser.getDepartId());
        initUserDepartList();
        return "MWorkshopJoblog/add";
    }
    

    /**
     * 保存数据并转向用户查看页面
     * 
     * @return
     * @throws Exception
     */
    public String add() throws Exception
    {
    	MWorkshopJoblogServiceImpl.saveMWorkshopJoblog(entity);
        setMessage("添加成功");
        return JSON_DATA;
    }
    
    /**
     * 转向用户输入页面
     */
    public String toEdit() throws Exception
    {
    	initUserDepartList();
        entity = MWorkshopJoblogServiceImpl.selectOneByEntity(entity);
        return "MWorkshopJoblog/edit";
    }
    
    /**
     * 更新信息
     */
    public String edit() throws Exception
    {
        MWorkshopJoblogServiceImpl.updateMWorkshopJoblog(entity);
        setMessage("修改成功");
        return JSON_DATA;
    }
    

	/**
	 * 打开查看页面
	 */
	public String view() throws Exception {
		initUserDepartList();
        entity = MWorkshopJoblogServiceImpl.selectOneByEntity(entity);
        return "MWorkshopJoblog/view";
	}

	// getter setter
	public MWorkshopJoblog getEntity() {
		return entity;
	}

	public void setEntity(MWorkshopJoblog entity) {
		this.entity = entity;
	}


	public MWorkshopJoblogQuery getMWorkshopJoblogQuery() {
		return queryEntity;
	}

	public void setMWorkshopJoblogQuery(MWorkshopJoblogQuery MWorkshopJoblogQuery) {
		this.queryEntity = MWorkshopJoblogQuery;
	}

	public void setMWorkshopJoblogServiceImpl(MWorkshopJoblogServiceImpl MWorkshopJoblogServiceImpl) {
		this.MWorkshopJoblogServiceImpl = MWorkshopJoblogServiceImpl;
	}

	public String[] getCheckbox() {
		return checkbox;
	}

	public void setCheckbox(String[] checkbox) {
		this.checkbox = checkbox;
	}

	public String[] getMobileCheckbox() {
		return mobileCheckbox;
	}

	public void setMobileCheckbox(String[] mobileCheckbox) {
		this.mobileCheckbox = mobileCheckbox;
	}

	@Override
	public String getWarnMessage() {
		// TODO Auto-generated method stub
		return super.getWarnMessage();
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return super.getMessage();
	}

	@Override
	public String getInfoMessage() {
		// TODO Auto-generated method stub
		return super.getInfoMessage();
	}

	public Map<String, Integer> getCheckedReView() {
		return checkedReView;
	}

	public void setCheckedReView(Map<String, Integer> checkedReView) {
		this.checkedReView = checkedReView;
	}

	public Map<String, Integer> getCheckedMobileReView() {
		return checkedMobileReView;
	}

	public void setCheckedMobileReView(Map<String, Integer> checkedMobileReView) {
		this.checkedMobileReView = checkedMobileReView;
	}
	
    private void initUserDepartList()
    {
        userDepartList = PmsDictUtil.getDictByTypeanden("depart",sessionUser.getDepartId());
    }

    public List<SysDict> getUserDepartList()
    {
        return userDepartList;
    }

    public void setUserDepartList(List<SysDict> userDepartList)
    {
        this.userDepartList = userDepartList;
    }
    
    

}
