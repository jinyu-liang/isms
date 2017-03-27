package com.is.pretrst.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.hibernate.tool.hbm2x.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.base.dict.PmsDictUtil;
import com.base.mybatis.BaseStruts2Action;
import com.is.pretrst.dao.DExProjectDaoImpl;
import com.is.pretrst.dao.ExPersonManagerDaoImpl;
import com.is.pretrst.entity.DContractImage;
import com.is.pretrst.entity.DExItem;
import com.is.pretrst.entity.DExProject;
import com.is.pretrst.entity.ExPersonManager;
import com.is.pretrst.entity.MTeam;
import com.is.pretrst.entity.MWorkshop;
import com.is.pretrst.entity.query.ExPersonManagerQuery;
import com.is.pretrst.entity.query.MWorkshopQuery;
import com.is.pretrst.service.DContractImageServiceImpl;
import com.is.pretrst.service.MTeamServiceImpl;
import com.is.pretrst.service.MWorkshopServiceImpl;
import com.is.sys.entity.SysDict;

/**
 * 
 * @ClassName: MWorkshopAction
 * @Description: MWorkshop表对应的Action类
 * @author 
 * @date 2013-09-10 10:26:58 *
 */
@Namespace("/rst")
public class MWorkshopAction extends BaseStruts2Action
{
    private static final long         serialVersionUID = 1L;

    @SuppressWarnings("unused")
    private static final Logger       LOGGER           = LoggerFactory.getLogger(MWorkshopAction.class);

    private MWorkshop                 entity;

    private MWorkshopQuery            queryEntity;

    private MWorkshopServiceImpl      mWorkShopServiceImpl;

    private List<SysDict>             userDepartList;

    private DExProject                projectEntity;

    private DExProjectDaoImpl         projectDaoImpl;

    private DContractImageServiceImpl contractImageServiceImpl;

    private List<DExItem>             itemList;

    private List<String>              imageList;
    private String              orderNo;

    private List<SysDict>             workShopList;
    
    private List<MTeam> expersonList;
    @Autowired
    private MTeamServiceImpl  mTeamServiceImpl;
    @Autowired
    private ExPersonManagerDaoImpl  exPersonManagerImpl;

    public MWorkshopAction()
    {
        super();
        if (entity == null)
        {
            entity = new MWorkshop();
        }
        if (queryEntity == null)
        {
            queryEntity = new MWorkshopQuery();
        }
    }

    public String toList() throws Exception
    {
        return null;
    }

    /**
     * 列表页面
     * 
     * @return
     * @throws Exception
     */
    public String list() throws Exception
    {
        userDepartList = PmsDictUtil.getDictByType("depart");
      //  dictCode=2.1;
        queryEntity.setDivCd(sessionUser.getDepartId());
        page = mWorkShopServiceImpl.selectMWorkShopPage(queryEntity);
        return "MWorkshop/list";
    }

    /**
     * 查看合同图片
     * 
     * @return
     * @throws Exception
     */
    public String contractImage() throws Exception
    {
        DContractImage dcontractImage = new DContractImage();
        dcontractImage.setWsCd(queryEntity.getWsCd());
        imageList = contractImageServiceImpl.getImages(dcontractImage);
        return "MWorkshop/contract_pic";
    }

    /**
     * 工地处理转向页面
     * 
     * @return
     * @throws Exception
     */
    public String toWorkDeal() throws Exception
    {
        workShopList = PmsDictUtil.getDictByType("workshop");
        entity = mWorkShopServiceImpl.getOneWorkshop(queryEntity);
        if (entity.getProject() != null)
        {
            projectEntity = entity.getProject();
            projectEntity.setWsCd(entity.getWsCd());
            itemList = projectEntity.getdExItems();
        }
        else
        { projectEntity.setWsCd(entity.getWsCd());
        projectEntity.setProjectNm(null);
        }
        return "MWorkshop/workDeal";
    }

    /**
     * 工地处理
     * 
     * @return
     * @throws Exception
     */
    public String workDeal() throws Exception
    {
        entity = mWorkShopServiceImpl.getOneWorkshop(queryEntity);
        projectEntity.setWsCd(entity.getWsCd());
        mWorkShopServiceImpl.workDeal(projectEntity, itemList);
        setMessage("处理成功!");
        return JSON_DATA;
        //测试取得list数据
        //		for(int i=1;i<=itemList.size();i++){
        //			
        //			return null;
        //		}
        //		return null;
    }

    /**
     * 新增保存
     * 
     * @return
     * @throws Exception
     */
    public String create() throws Exception
    {	MWorkshopQuery query=new MWorkshopQuery();
    	query.setWsNm(entity.getWsNm());
    	List<MWorkshop> shopList=mWorkShopServiceImpl.selectByEntity(query);
    	if(shopList!=null&&shopList.size()>0){
    		setInfoMessage("您输入的工地名称已存在!");
    	}else{
    		mWorkShopServiceImpl.workSave(entity);
    	}
        
        setMessage("添加成功!");
        return JSON_DATA;
    }

    /**
     * 编辑转向页面
     * 
     * @return
     * @throws Exception
     */
    public String toEdit() throws Exception
    {
    	userDepartList = PmsDictUtil.getDictByType("depart");
        entity = mWorkShopServiceImpl.getOneWorkshop(entity);
        return "MWorkshop/edit";
    }

    /**
     * 编辑保存页面
     * 
     * @return
     * @throws Exception
     */
    public String edit() throws Exception
    {	String newWsNm=entity.getWsNm();
    	String wsCd=entity.getWsCd();
    	String i ="0";
    	MWorkshopQuery query=new MWorkshopQuery();
    	query.setWsNm(newWsNm);
    	MWorkshop shop=mWorkShopServiceImpl.selectOneByEntity(query);
    	if(shop!=null){
    		String oldWscd=shop.getWsCd();
    		if(!oldWscd.equals(wsCd)){
    			setInfoMessage("您输入的工地名称已存在!");
    		}else{
    			i = mWorkShopServiceImpl.workUpdate(entity);
    		}
    	}else{
    		i = mWorkShopServiceImpl.workUpdate(entity);
    	}
    	
        if (i.equals("0"))
        {
            setMessage("修改成功!");
        }
        else
        {
            setMessage("修改失败!");
        }
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
        String delFlag = mWorkShopServiceImpl.delete(queryEntity);
        if (delFlag.equals("1"))
        {
            setMessage("删除成功!");
        }
        else
        {
            setInfoMessage("该工地已经在进行中，不可删除!");
        }
        return JSON_DATA;
    }

    /**
     * 查看页面
     * 
     * @return
     * @throws Exception
     */
    public String view() throws Exception
    {userDepartList = PmsDictUtil.getDictByType("depart");
        entity = mWorkShopServiceImpl.getOneWorkshop(queryEntity);
        if (entity.getProject() != null)
        {
            projectEntity = entity.getProject();
            projectEntity.setWsCd(entity.getWsCd());
        }
        else
        {
            projectEntity = new DExProject();
        }
        return "MWorkshop/view";
    }
    /**
     * 查看外线人员明细页面
     * 
     * @return
     * @throws Exception
     */
    public String perinfoListLiew() throws Exception
    {
    entity = mWorkShopServiceImpl.getOneWorkshop(queryEntity);
    ExPersonManagerQuery exPersonManagerQuery=new ExPersonManagerQuery(); 
    exPersonManagerQuery.setWorkCenterId(entity.getWsNm());
    List <ExPersonManager> personList=new ArrayList<ExPersonManager>();
    personList= exPersonManagerImpl.selectByEntity(exPersonManagerQuery);
    if(personList!=null&&personList.size()>0){
    	orderNo=personList.get(0).getTeamCd();
    }else{
    	orderNo=null;
    }
    expersonList = mTeamServiceImpl.selectPersonInfoByTeamCd(entity.getWsNm(),"");
    return "MWorkshop/person_list_view_info";
    }

    //校验如果是仓库则查询负责人是否负责了其他仓库
    public void checkStore() throws IOException{
        if("3".equals(entity.getTypeCd())){
            MWorkshop shop = new MWorkshop();
            shop.setTypeCd("3");//工地类型为仓库
            shop.setManagerUserId(entity.getManagerUserId());//负责人为当前选中的人
            List<MWorkshop> shopList = mWorkShopServiceImpl.selectByEntity(shop);
            	if(shopList!=null && shopList.size()>0&&StringUtils.isEmpty(entity.getWsCd())){
                    ServletActionContext.getResponse().getWriter().print(1);//有重复的
                }
            	if(shopList!=null && shopList.size()>0&&StringUtils.isNotEmpty(entity.getWsCd())){
            		int j=0;
            		for(int i=0;i<shopList.size();i++){
            			if(!shopList.get(i).getWsCd().equals(entity.getWsCd())){
            				j=j+1;
            			}
            		}
            		if(j>0){
            			ServletActionContext.getResponse().getWriter().print(1);//有重复的
            		}
            			
            	}
        }
    }
    public void setUserDepartList(List<SysDict> userDepartList)
    {
        this.userDepartList = userDepartList;
    }

    public List<SysDict> getUserDepartList()
    {
        return userDepartList;
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

    public void setProjectDaoImpl(DExProjectDaoImpl projectDaoImpl)
    {
        this.projectDaoImpl = projectDaoImpl;
    }

    public DExProjectDaoImpl getProjectDaoImpl()
    {
        return projectDaoImpl;
    }

    public void setWorkShopList(List<SysDict> workShopList)
    {
        this.workShopList = workShopList;
    }

    public List<SysDict> getWorkShopList()
    {
        return workShopList;
    }

    public MWorkshop getEntity()
    {
        return entity;
    }

    public DExProject getProjectEntity()
    {
        return projectEntity;
    }

    public void setProjectEntity(DExProject projectEntity)
    {
        this.projectEntity = projectEntity;
    }

    public List<DExItem> getItemList()
    {
        return itemList;
    }

    public void setItemList(List<DExItem> itemList)
    {
        this.itemList = itemList;
    }

    public void setEntity(MWorkshop entity)
    {
        this.entity = entity;
    }

    public MWorkshopQuery getQueryEntity()
    {
        return queryEntity;
    }

    public void setQueryEntity(MWorkshopQuery queryEntity)
    {
        this.queryEntity = queryEntity;
    }

    public MWorkshopServiceImpl getmWorkShopServiceImpl()
    {
        return mWorkShopServiceImpl;
    }

    public List<String> getImageList()
    {
        return imageList;
    }

    public void setImageList(List<String> imageList)
    {
        this.imageList = imageList;
    }

    @Autowired
    public void setmWorkShopServiceImpl(MWorkshopServiceImpl mWorkShopServiceImpl)
    {
        this.mWorkShopServiceImpl = mWorkShopServiceImpl;
    }

    public String toCreate() throws Exception
    {    	
    	userDepartList = PmsDictUtil.getDictByTypeanden("depart",sessionUser.getDepartId());
        return "MWorkshop/add";
    }

    @Autowired
    public void setContractImageServiceImpl(DContractImageServiceImpl contractImageServiceImpl)
    {
        this.contractImageServiceImpl = contractImageServiceImpl;
    }

	public List<MTeam> getExpersonList() {
		return expersonList;
	}

	public void setExpersonList(List<MTeam> expersonList) {
		this.expersonList = expersonList;
	}

	public MTeamServiceImpl getmTeamServiceImpl() {
		return mTeamServiceImpl;
	}

	public void setmTeamServiceImpl(MTeamServiceImpl mTeamServiceImpl) {
		this.mTeamServiceImpl = mTeamServiceImpl;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

}