package com.is.pretrst.action;

import java.util.Date;
import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.utils.spring.SpringContextHolder;

import com.base.dict.PmsDictInterfaceImpl;
import com.base.dict.PmsDictUtil;
import com.base.mybatis.BaseStruts2Action;
import com.is.pretrst.dao.ExPersonInfoDaoImpl;
import com.is.pretrst.dao.MTeamDaoImpl;
import com.is.pretrst.entity.ExPersonInfo;
import com.is.pretrst.entity.MTeam;
import com.is.pretrst.entity.MWorkshop;
import com.is.pretrst.entity.query.ExPersonInfoQuery;
import com.is.pretrst.entity.query.MWorkshopQuery;
import com.is.pretrst.service.ExPersonInfoServiceImpl;
import com.is.pretrst.service.MTeamServiceImpl;
import com.is.sys.entity.SysDict;
import com.is.utils.PublicDict;
import com.is.utils.keyUtils.KeyGen;


/**
 *
 * @ClassName: ExPersonInfoAction
 * @Description: ExPersonInfo表对应的Action类
 * @author 
 * @date 2013-12-13 16:28:09 *
 */
@Namespace("/rst")
public class ExPersonInfoAction extends BaseStruts2Action{
    private static final long serialVersionUID = 1L;

    @SuppressWarnings("unused")
    private static final Logger LOGGER = LoggerFactory.getLogger(ExPersonInfoAction.class);

    public List<SysDict> getUserDepartList() {
		return userDepartList;
	}

	public void setUserDepartList(List<SysDict> userDepartList) {
		this.userDepartList = userDepartList;
	}
	private ExPersonInfo entity;
    private ExPersonInfoQuery queryEntity;
    @Autowired
    private ExPersonInfoServiceImpl exPersonInfoServiceImpl;
    @Autowired
    private ExPersonInfoDaoImpl exPersonInfoDaoImpl;
    private MTeamDaoImpl mTeamDaoImpl;
    private List<SysDict>             userDepartList;
	
    public ExPersonInfoAction() {
        super();
        if(entity==null){
            entity = new ExPersonInfo();
        }
        if(queryEntity==null){
            queryEntity=new ExPersonInfoQuery();
        }
    }
    
    /**
     * 加保
     * @return
     */
    public String addInsurance(){
        if(exPersonInfoServiceImpl.perInfoDeal(entity.getIdentyCardCode())==1){
            setMessage("加保成功！");
        }else{
            setInfoMessage("加保失败！");
        }
        return JSON_DATA;
    }
    
    /**
     * 跳转到修改页面
     * @return
     * @throws Exception
     */
    public String toEdit() throws Exception {
    	userDepartList = PmsDictUtil.getDictByTypeanden("worktype",entity.getWsCd());
        entity = exPersonInfoServiceImpl.selectOneByEntity(entity);
        return "ExPersonInfo/edit";
    }
    
    /**
     * 编辑保存页面
     * 
     * @return
     * @throws Exception
     */
    public String edit() throws Exception
    {	String newWsNm=entity.getIdentyCardCode();
    	String wsCd=entity.getWsCd();
    	String i ="0";
    	ExPersonInfoQuery query=new ExPersonInfoQuery();
    	query.setIdentyCardCode(newWsNm);
    	query.setWsCd(wsCd);
    	ExPersonInfo shop=exPersonInfoServiceImpl.selectOneByEntity(query);
    	if(shop!=null){
    		if(exPersonInfoServiceImpl.updateByEntity(entity)==0)
    		{
    			i = "0";
    		}	 
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
     * 跳转到添加页面
     * @return
     * @throws Exception
     */
    public String toCreate() throws Exception {
    	//entity = exPersonInfoServiceImpl.selectOneByEntity(entity);
    	MTeam entityteam = new MTeam();
    	entityteam.setTeamCd(entity.getTeamId());
    	MTeamDaoImpl mTeamDaoImpl = (MTeamDaoImpl) SpringContextHolder.getBean("MTeamDaoImpl");
    	entityteam = mTeamDaoImpl.selectOneByEntity(entityteam);
    	entity.setTeamName(entityteam.getTeamNm());
    	entity.setWsNm(entityteam.getWsNm());
    	entity.setWsCd(entityteam.getWsCd());
    	//userDepartList = PmsDictUtil.getDictByType("worktype");
    	
    	userDepartList = PmsDictUtil.getDictByTypeanden("worktype",entityteam.getWsCd());

        return "ExPersonInfo/add";
    }
    
    /**
     * 保存页面
     */
    public String create() throws Exception
    {
    	
//    	exPersonInfoDaoImpl.insert(entity);
//    	return JSON_DATA;
        
        if(isExist(entity.getWsCd(),entity.getIdentyCardCode())){
            //存在重复的
            setInfoMessage("施工人员重复!");
            return JSON_DATA;
        }

       if(exPersonInfoServiceImpl.insert(entity)==1){
           
           setMessage("添加成功!");
       }
       else
       {
           setInfoMessage("添加失败!");
       }
       return JSON_DATA;
    }
    	
    /**
     * 判断用户是否已经存在
     * @param teamCd
     * @return boolean
     */
    public boolean isExist(String wsCd,String teamNm){
    	ExPersonInfo teamTmp  = new ExPersonInfo();
        teamTmp.setWsCd(wsCd);
        teamTmp.setIdentyCardCode(teamNm);
        teamTmp = exPersonInfoServiceImpl.selectOneByEntity(teamTmp);
         
        if(teamTmp!=null){
            return true;//存在返回true
        }return false;
    }
    
    
    public String list() throws Exception
    {
    	userDepartList = PmsDictUtil.getDictByTypeanden("worktype",entity.getWsCd());
    	queryEntity.setTeamId(entity.getTeamId());
        page = exPersonInfoDaoImpl.pageQuery("ExPersonInfo.selectByPage",queryEntity);
        return "ExPersonInfo/list";
    }
    /**
     * 撤保
     * @return
     */
    public String removeInsurance(){
        if(exPersonInfoServiceImpl.perInfoDeal(entity.getIdentyCardCode())==1){
            setMessage("撤保成功！");
        }else{
            setInfoMessage("撤保失败！");
        }
        return JSON_DATA;
    }
    /**
     * 剪出
     * @return
     */
    public String cutInsurance(){
        if(exPersonInfoServiceImpl.perInfocheckout(entity.getIdentyCardCode())==1){
            setMessage("剪出成功！");
        }else{
            setInfoMessage("剪出失败！");
        }
        return JSON_DATA;
    }


    public ExPersonInfo getEntity()
    {
        return entity;
    }

    public void setEntity(ExPersonInfo entity)
    {
        this.entity = entity;
    }

    public ExPersonInfoQuery getQueryEntity()
    {
        return queryEntity;
    }

    public void setQueryEntity(ExPersonInfoQuery queryEntity)
    {
        this.queryEntity = queryEntity;
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
