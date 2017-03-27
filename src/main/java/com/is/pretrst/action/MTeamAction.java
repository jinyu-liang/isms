package com.is.pretrst.action;

import java.util.Date;

import org.apache.struts2.convention.annotation.Namespace;
import org.hibernate.tool.hbm2x.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.base.mybatis.BaseStruts2Action;
import com.is.pretrst.entity.MTeam;
import com.is.pretrst.entity.query.MTeamQuery;
import com.is.pretrst.service.MTeamServiceImpl;
import com.is.utils.PublicDict;
import com.is.utils.keyUtils.KeyGen;


/**
 *
 * @ClassName: MTeamAction
 * @Description: MTeam表对应的Action类
 * @author 
 * @date 2013-12-18 15:07:36 *
 */
@Namespace("/rst")
public class MTeamAction extends BaseStruts2Action{
    private static final long serialVersionUID = 1L;

    @SuppressWarnings("unused")
    private static final Logger LOGGER = LoggerFactory.getLogger(MTeamAction.class);
    private MTeam entity;
    private MTeamQuery queryEntity;
    @Autowired
    private MTeamServiceImpl mTeamServiceImpl;
    @Autowired
    public MTeamAction() {
        super();
        if(entity==null){
            entity=new MTeam();
        }
        if(queryEntity==null){
            queryEntity =new MTeamQuery();
        }
    }
    /**
     * 跳转到添加页面
     * @return
     * @throws Exception
     */
    public String toCreate() throws Exception {
        //shopList =  mWorkshopServiceImpl.selectAll();
        return "MTeam/add";
    }
    /**
     * 添加
     * @return
     * @throws Exception
     */
    public String create() throws Exception {
        if(isExist(entity.getWsCd(),entity.getTeamNm())){
            //存在重复的
            setInfoMessage("施工队名称重复!");
            return JSON_DATA;
        }
        entity.setTeamCd(KeyGen.getCommonKeyGen(PublicDict.M_TEAM));
        entity.setOperUserId(sessionUser.getUserId());
        entity.setOperUserName(sessionUser.getUsername());
        entity.setOperTime(new Date());
       if(mTeamServiceImpl.insert(entity)==1){
           
           setMessage("添加成功!");
       }
       else
       {
           setInfoMessage("添加失败!");
       }
       return JSON_DATA;
    }
    
    /**
     * 跳转到添加页面
     * @return
     * @throws Exception
     */
    public String upload() throws Exception {
        //shopList =  mWorkshopServiceImpl.selectAll();
        return "MTeam/loadImportExec";
    }

    /**
     * 跳转到修改页面
     * @return
     * @throws Exception
     */
    public String toEdit() throws Exception {
        entity = mTeamServiceImpl.selectOneByEntity(entity);
        return "MTeam/edit";
    }

	/**
	 * 更新
	 * @return
	 * @throws Exception
	 */
    public String edit() throws Exception {
        MTeam teamOld = new MTeam();
        teamOld.setWsCd(entity.getWsCd());
        teamOld.setTeamNm(entity.getTeamNm());
        teamOld = mTeamServiceImpl.selectOneByEntity(teamOld);
        if(teamOld!=null && StringUtils.isNotEmpty(teamOld.getTeamCd())){
            if(!teamOld.getTeamCd().equals(entity.getTeamCd())){
                if(isExist(entity.getWsCd(),entity.getTeamNm())){
                  //存在重复的
                    setInfoMessage("施工队编号重复!");
                    return JSON_DATA;
                }
            }
        }
        entity.setOperUserId(sessionUser.getUserId());
        entity.setOperUserName(sessionUser.getUsername());
        entity.setOperTime(new Date());
        if(mTeamServiceImpl.updateByEntity(entity)==1){
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
        if(mTeamServiceImpl.deleteByEntity(entity)==1){
            setMessage("删除成功！");
        }else{
            setInfoMessage("删除失败！");
        }
        return JSON_DATA;
    }

	
    public String view() throws Exception {
        entity = mTeamServiceImpl.selectOneByEntity(entity);
        return "MTeam/view";
    }
	
    /**
     * 跳转到列表页面
     * @return
     * @throws Exception
     */
    public String list() throws Exception {
        queryEntity.setWsCd(entity.getWsCd());
        page=mTeamServiceImpl.pageQuery(queryEntity);
        return "MTeam/list";
    }
    /**
     * 判断施工队编号是否已经存在
     * @param teamCd
     * @return boolean
     */
    public boolean isExist(String wsCd,String teamNm){
        MTeam teamTmp  = new MTeam();
        teamTmp.setWsCd(wsCd);
        teamTmp.setTeamNm(teamNm);
        teamTmp = mTeamServiceImpl.selectOneByEntity(teamTmp);
         
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

    public MTeam getEntity()
    {
        return entity;
    }
    public void setEntity(MTeam entity)
    {
        this.entity = entity;
    }
    public MTeamQuery getQueryEntity()
    {
        return queryEntity;
    }
    public void setQueryEntity(MTeamQuery queryEntity)
    {
        this.queryEntity = queryEntity;
    }
    public void setmTeamServiceImpl(MTeamServiceImpl mTeamServiceImpl)
    {
        this.mTeamServiceImpl = mTeamServiceImpl;
    }
    
}
