package com.is.pretrst.action;

import java.util.Date;

import org.apache.struts2.convention.annotation.Namespace;
import org.hibernate.tool.hbm2x.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.base.mybatis.BaseStruts2Action;
import com.is.pretrst.entity.ExWorkType;
import com.is.pretrst.entity.query.ExWorkTypeQuery;
import com.is.pretrst.service.ExWorkTypeServiceImpl;
import com.is.utils.PublicDict;
import com.is.utils.keyUtils.KeyGen;


/**
 *
 * @ClassName: ExWorkTypeAction
 * @Description: ExWorkType表对应的Action类
 * @author 
 * @date 2013-12-18 15:07:36 *
 */
@Namespace("/rst")
public class ExWorkTypeAction extends BaseStruts2Action{
    private static final long serialVersionUID = 1L;

    @SuppressWarnings("unused")
    private static final Logger LOGGER = LoggerFactory.getLogger(ExWorkTypeAction.class);
    private ExWorkType entity;
    private ExWorkTypeQuery queryEntity;
    @Autowired
    private ExWorkTypeServiceImpl exWorkTypeServiceImpl;
    @Autowired
    public ExWorkTypeAction() {
        super();
        if(entity==null){
            entity=new ExWorkType();
        }
        if(queryEntity==null){
            queryEntity =new ExWorkTypeQuery();
        }
    }
    /**
     * 跳转到添加页面
     * @return
     * @throws Exception
     */
    public String toCreate() throws Exception {
        //shopList =  mWorkshopServiceImpl.selectAll();
    	System.out.print("111111111111111");
        return "ExWorkType/add";
    }
    /**
     * 添加
     * @return
     * @throws Exception
     */
    public String create() throws Exception {
        if(isExist(entity.getWorkCd(),entity.getWorkNm(),entity.getWork_ws_cd())){
            //存在重复的
            setInfoMessage("工种名称重复!");
            return JSON_DATA;
        }
        entity.setWorkCd(KeyGen.getCommonKeyGen(PublicDict.M_TEAM));
        entity.setOperUserId(sessionUser.getUserId());
        entity.setOperUserName(sessionUser.getUsername());
        entity.setOperTime(new Date());
       if(exWorkTypeServiceImpl.insert(entity)==1){
           
           setMessage("添加成功!");
       }
       else
       {
           setInfoMessage("添加失败!");
       }
       return JSON_DATA;
    }

    /**
     * 跳转到修改页面
     * @return
     * @throws Exception
     */
    public String toEdit() throws Exception {
        entity = exWorkTypeServiceImpl.selectOneByEntity(entity);
        return "ExWorkType/edit";
    }

	/**
	 * 更新
	 * @return
	 * @throws Exception
	 */
    public String edit() throws Exception {
        ExWorkType teamOld = new ExWorkType();
        teamOld.setWorkCd(entity.getWorkCd());
        teamOld.setWorkNm(entity.getWorkNm());
        teamOld = exWorkTypeServiceImpl.selectOneByEntity(teamOld);
        if(teamOld!=null && StringUtils.isNotEmpty(teamOld.getWorkCd())){
            if(!teamOld.getWorkCd().equals(entity.getWorkCd())){
                if(isExist(entity.getWorkCd(),entity.getWorkNm(),entity.getWork_ws_cd())){
                  //存在重复的
                    setInfoMessage("工种名称重复!");
                    return JSON_DATA;
                }
            }
        }
        entity.setOperUserId(sessionUser.getUserId());
        entity.setOperUserName(sessionUser.getUsername());
        entity.setOperTime(new Date());
        if(exWorkTypeServiceImpl.updateByEntity(entity)==1){
            setMessage("更新成功！");
        }else{
            setInfoMessage("更新失败！");
        }
        return JSON_DATA;
    }

	/**
	 * 删除
	 * @return
	 * @throws Exception
	 */
    public String delete() throws Exception {
        if(exWorkTypeServiceImpl.deleteByEntity(entity)==1){
            setMessage("删除成功！");
        }else{
            setInfoMessage("删除失败！");
        }
        return JSON_DATA;
    }

	
    public String view() throws Exception {
        entity = exWorkTypeServiceImpl.selectOneByEntity(entity);
        return "ExWorkType/view";
    }
	
    /**
     * 跳转到列表页面
     * @return
     * @throws Exception
     */
    public String list() throws Exception {
        queryEntity.setWork_ws_cd(entity.getWork_ws_cd());
        page=exWorkTypeServiceImpl.pageQuery(queryEntity);
        return "ExWorkType/list";
    }
    /**
     * 判断施工队编号是否已经存在
     * @param teamCd
     * @return boolean
     */
    public boolean isExist(String wsCd,String teamNm,String work_ws_cd){
        ExWorkType teamTmp  = new ExWorkType();
        teamTmp.setWorkCd(wsCd);
        teamTmp.setWorkNm(teamNm);
        teamTmp.setWork_ws_cd(work_ws_cd);
        teamTmp = exWorkTypeServiceImpl.selectOneByEntity(teamTmp);
         
        if(teamTmp!=null){
            return true;//存在返回true
        }return false;
        
    }
    
    @Override
    public String getWarnMessage()
    {
        // TODO Auto-generated method stub
        return super.getWarnMessage();
    }

    @Override
    public String getMessage()
    {
        // TODO Auto-generated method stub
        return super.getMessage();
    }

    @Override
    public String getInfoMessage()
    {
        // TODO Auto-generated method stub
        return super.getInfoMessage();
    }

    public ExWorkType getEntity()
    {
        return entity;
    }
    public void setEntity(ExWorkType entity)
    {
        this.entity = entity;
    }
    public ExWorkTypeQuery getQueryEntity()
    {
        return queryEntity;
    }
    public void setQueryEntity(ExWorkTypeQuery queryEntity)
    {
        this.queryEntity = queryEntity;
    }
    public void setExWorkTypeServiceImpl(ExWorkTypeServiceImpl ExWorkTypeServiceImpl)
    {
        this.exWorkTypeServiceImpl = ExWorkTypeServiceImpl;
    }
    
}
