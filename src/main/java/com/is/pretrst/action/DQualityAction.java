package com.is.pretrst.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.base.dict.PmsDictUtil;
import com.base.mybatis.BaseStruts2Action;
import com.is.pretrst.entity.DReportImage;
import com.is.pretrst.entity.MWorkshopQuality;
import com.is.pretrst.entity.query.MWorkshopQualityQuery;
import com.is.pretrst.service.DReportImageServiceImpl;
import com.is.pretrst.service.MWorkshopQualityServiceImpl;
import com.is.sys.entity.SysDict;

/**
 * 
 * @ClassName: DQualityAction
 * @Description: MWorkshopQuality表对应的Action类
 * @author liangjy
 * @date 2016-07-01
 */
@Namespace("/rst")
public class DQualityAction extends BaseStruts2Action {
	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = LoggerFactory.getLogger(DQualityAction.class);

	private MWorkshopQualityServiceImpl mworkshopqualityserviceimpl;
	
	private DReportImageServiceImpl dreportimageserviceimpl;

	private MWorkshopQualityQuery queryEntity;

	private MWorkshopQuality entity;

	private List<String> imageList;

	private int containSelf = 0;
	
	 /** 用户部门 */
    private List<SysDict> userDepartList;


	public DQualityAction() {
		super();
		if (entity == null) {
			entity = new MWorkshopQuality();
		}
		if (queryEntity == null) {
			queryEntity = new MWorkshopQualityQuery();
		}
	}

	/**
	 * 质量管理列表
	 * 
	 * @return
	 * @throws Exception
	 */
	public String list() throws Exception {
		initUserDepartList();
//        if (containSelf == 0)
//        {
//            
//        }
//		queryEntity.setDiv_cd(sessionUser.getDepartId());
		
		System.out.println("-----------"+entity.getWs_nm());
		
		
		queryEntity.setWs_nm(entity.getWs_nm());
		queryEntity.setDiv_cd(entity.getDiv_cd());
		page = mworkshopqualityserviceimpl.selectReportPage(queryEntity);
		return "DQuality/list";
	}

	/**
	 * 转向用户输入页面
	 */
	public String toCreate() throws Exception {
		return "DQuality/add";
	}

	/**
	 * 转向用户输入页面
	 */
	public String toEdit() throws Exception {
		entity = mworkshopqualityserviceimpl.getDQuality(entity);
		return "DQuality/edit";
	}

	/**
	 * 打开查看页面
	 */
	public String view() throws Exception {
		entity = mworkshopqualityserviceimpl.getDQuality(queryEntity);
		return "DQuality/view";
	}
	/**
	 * 打开查看页面
	 */
	public String imgView() throws Exception {
		DReportImage dreportimage = new DReportImage();
		dreportimage.setReportId(queryEntity.getFqattchid());
        imageList = dreportimageserviceimpl.getImages(dreportimage);
		return "DQuality/imgView";
	}
	/**
	 * 保存页面
	 */
	public String create() throws Exception {
		mworkshopqualityserviceimpl.saveDQuality(entity);
		return SUCCESS;
	}

	/**
	 * 审核页面
	 */
	public String toVerfied() throws Exception {
		entity = mworkshopqualityserviceimpl.getDQuality(queryEntity);
		return "DQuality/verified";
	}

	/**
	 * 发票处理页面
	 */
	public String toDeal() throws Exception {
		entity = mworkshopqualityserviceimpl.getDQuality(queryEntity);
		return "DQuality/deal";
	}
	
	/**
	 * 发起处理页面
	 */
	public String toDealfq() throws Exception {
		entity = mworkshopqualityserviceimpl.getDQuality(queryEntity);
		DReportImage dreportimage = new DReportImage();
		dreportimage.setReportId(queryEntity.getFqattchid());
        imageList = dreportimageserviceimpl.getImages(dreportimage);
		return "DQuality/dealfq";
	}
	
	/**
	 * 发起处理页面
	 */
	public String toDealzq() throws Exception {
		entity = mworkshopqualityserviceimpl.getDQuality(queryEntity);
		DReportImage dreportimage = new DReportImage();
		dreportimage.setReportId(queryEntity.getFqattchid());
        imageList = dreportimageserviceimpl.getImages(dreportimage);
        
		return "DQuality/dealzq";
	}
	
	/**
	 * 发起处理页面
	 */
	public String toDealjl() throws Exception {
		entity = mworkshopqualityserviceimpl.getDQuality(queryEntity);
		DReportImage dreportimage = new DReportImage();
		dreportimage.setReportId(queryEntity.getFqattchid());
        imageList = dreportimageserviceimpl.getImages(dreportimage);
        
		return "DQuality/dealjl";
	}

	/**
	 * 整改处理页面
	 */
	public String billDealzq() throws Exception {
		System.out.println("-------entity1------------"+entity);
		//整改方确认
		if("1".equals(entity.getFqstatus()))
		{
			entity.setZgjhfinishtime(entity.getZgjhfinishtime());
			entity.setZgdesc(entity.getZgdesc());
		}else if("2".equals(entity.getFqstatus())){
			entity.setZgfinishtime(entity.getZgfinishtime());
			entity.setIsovertime(entity.getIsovertime());
			entity.setZgfinishdesc(entity.getZgfinishdesc());
		}
		
		entity.setDealType("zq");
		String flag=mworkshopqualityserviceimpl.billDeal(entity);
		if(flag.equals("1")){
			setMessage("操作成功!");	
		}else{
			setInfoMessage("操作失败!");
		}
		return JSON_DATA;

	}

	/**
	 * 发起处理界面
	 */
	public String billDealfq() throws Exception {
		
		
		//整改方确认
		if("3".equals(entity.getFqstatus()))
		{
			entity.setFqyqfinishtime(entity.getFqyqfinishtime());
			entity.setFqrqdesc(entity.getFqrqdesc());
			entity.setDealType("fq");
		}
		
		String flag=mworkshopqualityserviceimpl.billDeal(entity);
		if(flag.equals("1")){
			setMessage("操作成功!");	
		}else{
			setInfoMessage("操作失败!");
		}
		return JSON_DATA;
	}
	
	/**
	 * 监理处理界面
	 */
	public String billDeal() throws Exception {
		
		//整改方确认
		if("4".equals(entity.getFqstatus()))
		{
			entity.setJlfinishuerid(entity.getJlfinishuerid());
			entity.setJlfinishusername(entity.getJlfinishusername());
			entity.setJlqrfinishtime(entity.getJlqrfinishtime());
			entity.setJlDesc(entity.getJlDesc());
			entity.setDealType("jl");
		}
		
		String flag=mworkshopqualityserviceimpl.billDeal(entity);
		if(flag.equals("1")){
			setMessage("操作成功!");	
		}else{
			setInfoMessage("操作失败!");
		}
		return JSON_DATA;
	}
	/**
	 * 审核保存页面
	 */
	public String verified() throws Exception {
//		DQuality DQuality = mworkshopqualityserviceimpl.getDQuality(queryEntity);
//		LOGGER.debug("打印==【{}】+++【{}】", entity, DQuality.getVerifiedHeadStatus());
//
//		String verfieder = DQuality.getVerifiedUserCd();
//		UserDetail user = SpringSecurityUtils.getCurrentUser();
//		LOGGER.debug("========【{}】=======【{}】", verfieder, user.getUserId());
//		DQuality.setVerifiedHeadMemo(entity.getVerifiedHeadMemo());
//		DQuality.setVerifiedHeadStatus(entity.getVerifiedHeadStatus());
//		String flag=mworkshopqualityserviceimpl.verfied(DQuality);
//		if(flag.equals("1")){
//			setMessage("操作成功!");	
//		}else{
//			setInfoMessage("操作失败");
//		}
		return JSON_DATA;
	}

	/**
	 * 删除采购信息
	 * @return
	 */
	public String deleteReport(){
	    if(mworkshopqualityserviceimpl.deleteByEntity(entity)!=0){
	        setMessage("删除成功");
	    }else{
	        setInfoMessage("删除失败");
	    }
	    return JSON_DATA;
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
	public MWorkshopQualityServiceImpl getmworkshopqualityserviceimpl() {
		return mworkshopqualityserviceimpl;
	}

	@Autowired
	public void setmworkshopqualityserviceimpl(MWorkshopQualityServiceImpl mworkshopqualityserviceimpl) {
		this.mworkshopqualityserviceimpl = mworkshopqualityserviceimpl;
	}

	public MWorkshopQualityQuery getQueryEntity() {
		return queryEntity;
	}

	public void setQueryEntity(MWorkshopQualityQuery queryEntity) {
		this.queryEntity = queryEntity;
	}

	public MWorkshopQuality getEntity() {
		return entity;
	}

	public void setEntity(MWorkshopQuality entity) {
		this.entity = entity;
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
    
    public List<String> getImageList()
    {
        return imageList;
    }

    public void setImageList(List<String> imageList)
    {
        this.imageList = imageList;
    }
    
    @Autowired
	public void setDreportimageserviceimpl(DReportImageServiceImpl dreportimageserviceimpl) {
		this.dreportimageserviceimpl = dreportimageserviceimpl;
	}
}
