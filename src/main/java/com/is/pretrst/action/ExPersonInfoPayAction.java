package com.is.pretrst.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.base.dict.PmsDictUtil;
import com.base.mybatis.BaseStruts2Action;
import com.is.pretrst.dao.ExPersonInfoPayDaoImpl;
import com.is.pretrst.dao.MTeamDaoImpl;
import com.is.pretrst.entity.ExPersonInfo;
import com.is.pretrst.entity.ExPersonInfoPay;
import com.is.pretrst.entity.query.ExPersonInfoPayQuery;
import com.is.pretrst.entity.query.ExPersonInfoQuery;
import com.is.pretrst.service.ExPersonInfoPayServiceImpl;
import com.is.sys.entity.SysDict;
import com.is.utils.webServices.send;

@Namespace("/rst")
public class ExPersonInfoPayAction extends BaseStruts2Action{
    private static final long serialVersionUID = 1L;

    @SuppressWarnings("unused")
    private static final Logger LOGGER = LoggerFactory.getLogger(ExPersonInfoPayAction.class);

    public List<SysDict> getUserDepartList() {
		return userDepartList;
	}

	public void setUserDepartList(List<SysDict> userDepartList) {
		this.userDepartList = userDepartList;
	}
	private ExPersonInfoPay entity;
    private ExPersonInfoPayQuery queryEntity;
    @Autowired
    private ExPersonInfoPayServiceImpl ExPersonInfoPayServiceImpl;
    @Autowired
    private ExPersonInfoPayDaoImpl ExPersonInfoPayDaoImpl;
    private MTeamDaoImpl mTeamDaoImpl;
    private List<SysDict>             userDepartList;
	
    public ExPersonInfoPayAction() {
        super();
        if(entity==null){
            entity = new ExPersonInfoPay();
        }
        if(queryEntity==null){
            queryEntity=new ExPersonInfoPayQuery();
        }
    }

    /**
     * 跳转到修改页面
     * @return
     * @throws Exception
     */
    public String toEdit() throws Exception {
    	queryEntity.setIdenty_card_code(entity.getIdenty_card_code());
    	queryEntity.setDayormonth("1");
    	SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM" );
    	if(entity.getWork_status()!=null && entity.getWork_status()!="")
    	{
    		Date date = sdf.parse(entity.getWork_status());
    		queryEntity.setAddtime(date);
    		queryEntity.setWork_status("");
    	}
    	
        page = ExPersonInfoPayDaoImpl.pageQuery("ExPersonInfoPay.selectByPage",queryEntity);
        return "ExPersonInfoPay/listperson";
    }
    
    /**
     * 跳转到修改页面
     * @return
     * @throws Exception
     */
    public String toUpdate() throws Exception {
        entity = ExPersonInfoPayServiceImpl.selectOneByEntity(entity);
        return "ExPersonInfoPay/edit";
    }
    
    /**
     * 跳转到修改页面
     * @return
     * @throws Exception
     */
    public String Update() throws Exception {
    	if(ExPersonInfoPayServiceImpl.updateByEntity(entity)==1)
		{
    		setMessage("修改成功!");
		}
    	return JSON_DATA;
    }
    
    public String rolesearch() throws Exception
    {
        return "ExPersonInfoPay/roleDepart";
    }
    
    public String cuizhang() 
    {
    	String div_cd = entity.getName();
    	queryEntity.setName(div_cd);
        page = ExPersonInfoPayDaoImpl.pageQuery("ExPersonInfoPay.getcuizhang",queryEntity);
    	return "ExPersonInfoPay/listcuiban";
    }
    
    public String createduban() 
    {
    	String div_cd = entity.getName();
    	queryEntity.setName(div_cd);
        page = ExPersonInfoPayDaoImpl.pageQuery("ExPersonInfoPay.getcuizhang",queryEntity);
        send ws = new send();
        try {
			ws.Sendtoliucheng(page);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return "ExPersonInfoPay/listcuiban";
    }
    
    
    /**
     * 编辑保存页面
     * 
     * @return
     * @throws Exception
     */
//    public String edit() throws Exception
//    {	String newWsNm=entity.getIdentyCardCode();
//    	String wsCd=entity.getWsCd();
//    	String i ="0";
//    	ExPersonInfoPayQuery query=new ExPersonInfoPayQuery();
//    	query.setIdentyCardCode(newWsNm);
//    	query.setWsCd(wsCd);
//    	ExPersonInfoPay shop=ExPersonInfoPayServiceImpl.selectOneByEntity(query);
//    	if(shop!=null){
//    		if(ExPersonInfoPayServiceImpl.updateByEntity(entity)==0)
//    		{
//    			i = "0";
//    		}	 
//    	}
//
//        if (i.equals("0"))
//        {
//            setMessage("修改成功!");
//        }
//        else
//        {
//            setMessage("修改失败!");
//        }
//        return JSON_DATA;
//    }
    
    /**
     * 跳转到添加页面
     * @return
     * @throws Exception
     */
//    public String toCreate() throws Exception {
//    	//entity = ExPersonInfoPayServiceImpl.selectOneByEntity(entity);
//    	MTeam entityteam = new MTeam();
//    	entityteam.setTeamCd(entity.getTeamId());
//    	MTeamDaoImpl mTeamDaoImpl = (MTeamDaoImpl) SpringContextHolder.getBean("MTeamDaoImpl");
//    	entityteam = mTeamDaoImpl.selectOneByEntity(entityteam);
//    	entity.setTeamName(entityteam.getTeamNm());
//    	entity.setWsNm(entityteam.getWsNm());
//    	entity.setWsCd(entityteam.getWsCd());
//    	//userDepartList = PmsDictUtil.getDictByType("worktype");
//    	
//    	userDepartList = PmsDictUtil.getDictByTypeanden("worktype",entityteam.getWsCd());
//
//        return "ExPersonInfoPay/add";
//    }
    
    /**
     * 保存页面
     */
//    public String create() throws Exception
//    {
//    	
////    	ExPersonInfoPayDaoImpl.insert(entity);
////    	return JSON_DATA;
//        
//        if(isExist(entity.getWsCd(),entity.getIdentyCardCode())){
//            //存在重复的
//            setInfoMessage("施工人员重复!");
//            return JSON_DATA;
//        }
//
//       if(ExPersonInfoPayServiceImpl.insert(entity)==1){
//           
//           setMessage("添加成功!");
//       }
//       else
//       {
//           setInfoMessage("添加失败!");
//       }
//       return JSON_DATA;
//    }
    	
    /**
     * 判断用户是否已经存在
     * @param teamCd
     * @return boolean
     */
//    public boolean isExist(String wsCd,String teamNm){
//    	ExPersonInfoPay teamTmp  = new ExPersonInfoPay();
//        teamTmp.setWsCd(wsCd);
//        teamTmp.setIdentyCardCode(teamNm);
//        teamTmp = ExPersonInfoPayServiceImpl.selectOneByEntity(teamTmp);
//         
//        if(teamTmp!=null){
//            return true;//存在返回true
//        }return false;
//    }
    
    
    public String list() throws Exception
    {
    	userDepartList = PmsDictUtil.getDictByTypeanden("worktype",entity.getWork_ws_cd());
    	queryEntity.setTeam_id(entity.getTeam_id());
    	queryEntity.setDayormonth("2");
        page = ExPersonInfoPayDaoImpl.pageQuery("ExPersonInfoPay.selectByPage",queryEntity);
        return "ExPersonInfoPay/list";
    }

    /**
     * 与员工确认wan
     * @return
     */
    public String addInsurance(){
        if(ExPersonInfoPayServiceImpl.perInfoDeal(entity.getPid())==1){
            setMessage("确认完成！");
        }else{
            setInfoMessage("确认失败！");
        }
        return JSON_DATA;
    }
    
    /**
     * 完成
     * @return
     */
    public String fianshInsurance(){
        if(ExPersonInfoPayServiceImpl.finishInfoDeal(entity.getPid())==1){
            setMessage("确认发放成功！");
        }else{
            setInfoMessage("确认发放失败！");
        }
        return JSON_DATA;
    }
    

    
//    /**
//     * 编辑保存页面
//     * 
//     * @return
//     * @throws Exception
//     */
//    public String edit() throws Exception
//    {	String newWsNm=entity.getIdentyCardCode();
//    	String wsCd=entity.getWsCd();
//    	String i ="0";
//    	ExPersonInfoQuery query=new ExPersonInfoQuery();
//    	query.setIdentyCardCode(newWsNm);
//    	query.setWsCd(wsCd);
//    	ExPersonInfo shop=exPersonInfoServiceImpl.selectOneByEntity(query);
//    	if(shop!=null){
//    		if(exPersonInfoServiceImpl.updateByEntity(entity)==0)
//    		{
//    			i = "0";
//    		}	 
//    	}
//
//        if (i.equals("0"))
//        {
//            setMessage("修改成功!");
//        }
//        else
//        {
//            setMessage("修改失败!");
//        }
//        return JSON_DATA;
//    }

    public ExPersonInfoPay getEntity()
    {
        return entity;
    }

    public void setEntity(ExPersonInfoPay entity)
    {
        this.entity = entity;
    }

    public ExPersonInfoPayQuery getQueryEntity()
    {
        return queryEntity;
    }

    public void setQueryEntity(ExPersonInfoPayQuery queryEntity)
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
