package com.is.pretrst.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.base.dict.PmsDictUtil;
import com.base.mybatis.BaseStruts2Action;
import com.base.security.SpringSecurityUtils;
import com.is.ggkz.entity.ext.UserDetail;
import com.is.pretrst.entity.ExPersonManager;
import com.is.pretrst.entity.MTeam;
import com.is.pretrst.entity.query.ExPersonManagerQuery;
import com.is.pretrst.service.ExPersonManagerServiceImpl;
import com.is.pretrst.service.MTeamServiceImpl;
import com.is.sys.entity.SysDict;

/**
 *
 * @ClassName: ExPersonManagerAction
 * @Description: ExPersonManager表对应的Action类
 * @author 
 * @date 2013-09-11 17:24:47 *
 */
@Namespace("/rst")
public class ExPersonManagerAction extends BaseStruts2Action
{
    private static final long   serialVersionUID = 1L;

    private static final Logger LOGGER           = LoggerFactory.getLogger(ExPersonManagerAction.class);

    //action字典属性：
    private List<SysDict>       workShopList;
    private ExPersonManagerServiceImpl exPersonManagerImpl;

    private ExPersonManagerQuery       queryEntity;

    private ExPersonManager            entity;
    private String orderNo;
    private List<MTeam> expersonList;
    
    @Autowired
    private MTeamServiceImpl  mTeamServiceImpl;
    public ExPersonManagerAction()
    {
        super();
        if (entity == null)
        {
            entity = new ExPersonManager();
        }
        if (queryEntity == null)
        {
            queryEntity = new ExPersonManagerQuery();
        }
    }

    /**
     * 外线人员列表列表
     * 
     * @return
     * @throws Exception
     */
    public String list() throws Exception
    {
        //获取数据字典：
        workShopList = PmsDictUtil.getDictByType("workshop");
        page = exPersonManagerImpl.selectPersonManagerPage(queryEntity);
        return "ExPersonManager/list";
    }

    /**
     * 转向输入页面
     */
    public String toCreate() throws Exception
    {
        return "ExPersonManager/add";
    }

    /**
     * 转向用户输入页面
     */
    public String toEdit() throws Exception
    {
        entity = exPersonManagerImpl.getPersonManager(entity);
        return "ExPersonManager/edit";
    }

    /**
     * 打开查看页面
     */
    public String view() throws Exception
    {       //获取数据字典：
        workShopList = PmsDictUtil.getDictByType("workshop");
        entity = exPersonManagerImpl.getPersonManager(queryEntity);
        expersonList = mTeamServiceImpl.selectPersonInfoByTeamCd(entity.getWorkCenterId(),entity.getReportId());
        return "ExPersonManager/view";
    }
    
    /**
     * 打开查看页面
     */
    public String imgView() throws Exception
    {
        entity = exPersonManagerImpl.getPersonManager(queryEntity);
        return "ExPersonManager/imgView";
    }

    /**
     * 保存页面
     */
    public String create() throws Exception
    {
        exPersonManagerImpl.savePersonManager(entity);
        return SUCCESS;
    }
    /**
     * 保存页面
     */
    public String delete() throws Exception
    {
    	exPersonManagerImpl.delete(entity);
    	
        setMessage("删除成功");
    return JSON_DATA;
    	
    }

    /**
     * 处理页面
     */
    public String toDeal() throws Exception
    {
        entity = exPersonManagerImpl.getPersonManager(queryEntity);
        expersonList = mTeamServiceImpl.selectPersonInfoByTeamCd(entity.getWorkCenterId(),entity.getReportId());
        return "ExPersonManager/deal";

    }

    /**
     * 外线人员处理页面
     */
    public String deal() throws Exception
    {
        ExPersonManager dreport = exPersonManagerImpl.getPersonManager(queryEntity);

        String verfieder = dreport.getProcessUserCd();
        UserDetail user = SpringSecurityUtils.getCurrentUser();
        LOGGER.debug("========【{}】=======【{}】", verfieder, user.getUserId());
            dreport.setDealComment(entity.getDealComment());
            dreport.setStatusCd(entity.getStatusCd());
            exPersonManagerImpl.deal(dreport);
            setMessage("处理完成!");
            return JSON_DATA;
    }

    public void setWorkShopList(List<SysDict> workShopList)
    {
        this.workShopList = workShopList;
    }

    public List<SysDict> getWorkShopList()
    {
        return workShopList;
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
    public ExPersonManagerServiceImpl getExPersonManagerImpl()
    {
        return exPersonManagerImpl;
    }

    @Autowired
    public void setExPersonManagerImpl(ExPersonManagerServiceImpl exPersonManagerImpl)
    {
        this.exPersonManagerImpl = exPersonManagerImpl;
    }

    public ExPersonManagerQuery getQueryEntity()
    {
        return queryEntity;
    }

    public void setQueryEntity(ExPersonManagerQuery queryEntity)
    {
        this.queryEntity = queryEntity;
    }

    public ExPersonManager getEntity()
    {
        return entity;
    }

    public void setEntity(ExPersonManager entity)
    {
        this.entity = entity;
    }

    public List<MTeam> getExpersonList()
    {
        return expersonList;
    }

    public void setExpersonList(List<MTeam> expersonList)
    {
        this.expersonList = expersonList;
    }

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

  

}
