package com.is.pretrst.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.base.dict.PmsDictUtil;
import com.base.mybatis.BaseStruts2Action;
import com.is.ggkz.entity.GgkzPostInfo;
import com.is.pretrst.entity.MWorkshopContact;
import com.is.pretrst.service.MWorkshopContactServiceImpl;
import com.is.pretrst.entity.query.MWorkshopContactQuery;
import com.is.sys.entity.SysDict;
import com.is.utils.date.DateUtil;

/**
 * 
 * @ClassName: MWorkshopContactAction
 * @Description: MWorkshopContact表对应的Action类
 * @author
 * @date 2013-02-27 14:18:56 *
 */
@Namespace("/rst")
public class MWorkshopContactAction extends BaseStruts2Action {
	private static final long serialVersionUID = 1L;

	@SuppressWarnings("unused")
	private static final Logger LOGGER = LoggerFactory.getLogger(MWorkshopContactAction.class);

	@Autowired
	private MWorkshopContactServiceImpl MWorkshopContactServiceImpl;

	// 页面属性
	private MWorkshopContact entity; // 实体对象

	private MWorkshopContactQuery queryEntity; // query对象

	private Map<String, Integer> checkedReView;

	private Map<String, Integer> checkedMobileReView;

    private List<SysDict>           userDepartList;
    
    private List<SysDict>           userInfoList;
    
    private List<SysDict>           workList;

	private String[] checkbox;

	private String[] mobileCheckbox;
	

	public MWorkshopContactAction() {
		super();
		if (entity == null) {
			entity = new MWorkshopContact();
		}
		if (queryEntity == null) {
			queryEntity = new MWorkshopContactQuery();
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
		queryEntity.setTitle(entity.getTitle());
		queryEntity.setWs_nm(entity.getWs_nm());
		queryEntity.setWs_cd(entity.getWs_cd());

		page = MWorkshopContactServiceImpl.pageQuery(queryEntity);

		return "MWorkshopContact/list";//页面名称
	}
	
	/**
     * 转向用户输入页面
     */
    public String toAdd() throws Exception
    {
    	entity.setFquserid(sessionUser.getUserId());
    	entity.setFqusername(sessionUser.getUsername());
        initUserDepartList();
        initUserInfoList();
        initWorkInfoList();
        System.out.println("----userDepartList---"+userDepartList);
        System.out.println("----userInfoList---"+userInfoList);
        System.out.println("----workList---"+workList);
        return "MWorkshopContact/add";
    }
    

    /**
     * 保存数据并转向用户查看页面
     * 
     * @return
     * @throws Exception
     */
    public String add() throws Exception
    {
    	entity.setFquserid(sessionUser.getUserId());
    	entity.setFqusername(sessionUser.getUsername());
    	entity.setSenderdepID(sessionUser.getDepartId());
    	entity.setStatus("0");
    	MWorkshopContactServiceImpl.saveMWorkshopContact(entity);
        setMessage("添加成功");
        return JSON_DATA;
    }
    
    /**
     * 转向用户输入页面
     */
    public String toEdit() throws Exception
    {
    	initUserDepartList();
        initUserInfoList();
        initWorkInfoList();
        entity = MWorkshopContactServiceImpl.selectOneByEntity(entity);
        return "MWorkshopContact/edit";
    }
    
    /**
     * 更新信息
     */
    public String edit() throws Exception
    {
    	String status = entity.getStatus();
    	String reqmsg = "";
    	if("1".equals(status)){
    		entity.setAcceptdepmangertime(DateUtil.getCurDate());
    		reqmsg = "部门领导人审核成功";
    	}else if("2".equals(status)){
    		entity.setLeadertime(DateUtil.getCurDate());
    		reqmsg = "主管审核成功";
    	}
    	
    	if("true".equals(entity.getIsaccept())){
    		entity.setStatus("3");
    		entity.setAccepttime(DateUtil.getCurDate());
    		reqmsg = "接收部门接收！";
    	}else{
    		entity.setStatus("4");
    		entity.setAccepttime(DateUtil.getCurDate());
    		reqmsg = "接收部门拒绝！";
    	}
    	System.out.println("-------"+entity);
        MWorkshopContactServiceImpl.updateMWorkshopContact(entity);
        setMessage(reqmsg);
        return JSON_DATA;
    }
    
    /**
     * 删除
     * 
     * @return
     * @throws Exception
     */
    public String delete() throws Exception
    {
    	System.out.println("-------"+entity);
        String delFlag = MWorkshopContactServiceImpl.delete(entity);
        if (delFlag.equals("1"))
        {
            setMessage("删除成功!");
        }
        else if (delFlag.equals("0"))
        {
            setInfoMessage("该联系单已经在进行中，不可删除!");
        }else{
        	setInfoMessage("联系单非本人新建，不可删除!");
        }
        return JSON_DATA;
    }

	/**
	 * 打开查看页面
	 */
	public String view() throws Exception {
		initUserDepartList();
        entity = MWorkshopContactServiceImpl.selectOneByEntity(entity);
        return "MWorkshopContact/view";
	}

	// getter setter
	public MWorkshopContact getEntity() {
		return entity;
	}

	public void setEntity(MWorkshopContact entity) {
		this.entity = entity;
	}


	public MWorkshopContactQuery getMWorkshopContactQuery() {
		return queryEntity;
	}

	public void setMWorkshopContactQuery(MWorkshopContactQuery MWorkshopContactQuery) {
		this.queryEntity = MWorkshopContactQuery;
	}

	public void setMWorkshopContactServiceImpl(MWorkshopContactServiceImpl MWorkshopContactServiceImpl) {
		this.MWorkshopContactServiceImpl = MWorkshopContactServiceImpl;
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

    private void initUserInfoList()
    {
        userInfoList = PmsDictUtil.getDictByTypeanden("headUser","");
    }

	public List<SysDict> getUserInfoList() {
		return userInfoList;
	}


	public void setUserInfoList(List<SysDict> userInfoList) {
		this.userInfoList = userInfoList;
	}

	private void initWorkInfoList()
    {
        workList = PmsDictUtil.getDictByTypeanden("workshop","");
    }

	public List<SysDict> getWorkList() {
		return workList;
	}


	public void setWorkList(List<SysDict> workList) {
		this.workList = workList;
	}
    
    

}
