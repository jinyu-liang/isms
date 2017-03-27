package com.is.pretrst.action;

import java.util.Date;

import org.apache.struts2.convention.annotation.Namespace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.base.mybatis.BaseStruts2Action;
import com.is.pretrst.entity.DScrapTrans;
import com.is.pretrst.entity.query.DScrapTransQuery;
import com.is.pretrst.service.DScrapTransServiceImpl;
import com.is.utils.StringUtils;

/**
 *
 * @ClassName: DScrapTransAction
 * @Description: DScrapTrans表对应的Action类
 * @author 
 * @date 2013-09-10 10:26:53 *
 */
@Namespace("/rst")
public class DScrapTransAction extends BaseStruts2Action
{
    private static final long      serialVersionUID = 1L;

    @SuppressWarnings("unused")
    private static final Logger    LOGGER           = LoggerFactory.getLogger(DScrapTransAction.class);

    private DScrapTrans            entity;

    private DScrapTransQuery       queryEntity;

    @Autowired
    private DScrapTransServiceImpl dscrapTransServiceImpl;

    public DScrapTransAction()
    {
        super();
        if (entity == null)
        {
            entity = new DScrapTrans();
        }
        if (queryEntity == null)
        {
            queryEntity = new DScrapTransQuery();
        }
    }

    public String toCreate() throws Exception
    {
        return null;
    }

    public String create() throws Exception
    {
        return null;
    }

    public String toEdit() throws Exception
    {
        return null;
    }

    public String edit() throws Exception
    {
        return null;
    }

    public String delete() throws Exception
    {
        return null;
    }

    public String view() throws Exception
    {

        entity = dscrapTransServiceImpl.selectOneByEntity(entity);
        return "DScrapTrans/view";
    }

    public String toList() throws Exception
    {
        return null;
    }

    public String list() throws Exception
    {
        page = dscrapTransServiceImpl.pageQuery(queryEntity);
        return "DScrapTrans/list";
    }

    /**
     * 收货员确认收货
     * @return
     * @throws Exception
     */
    public String affirmDelivery() throws Exception
    {
        int i = 0;
        if (StringUtils.isNotEmpty(entity.getTransId()))
        {
            DScrapTrans dScrapTrans = new DScrapTrans();
            dScrapTrans.setTransId(entity.getTransId());
            dScrapTrans.setMemo(entity.getMemo());
            dScrapTrans.setStatusCd(entity.getStatusCd());
            dScrapTrans.setArrivalTm(new Date());
            i = dscrapTransServiceImpl.scrapTransReceive(dScrapTrans);
        }
        if (i != 0)
        {
            setMessage("收货成功");
        }
        else
        {
            setInfoMessage("收货失败");
        }
        return JSON_DATA;
    }

    /**
     * 删除剩料信息与剩料图片
     * @return
     */
    public String deleteScrapTrans(){
        if(dscrapTransServiceImpl.deleteByEntity(entity)!=0){
            setMessage("删除成功");
        }else{
            setInfoMessage("删除失败");
        }
        return JSON_DATA;
    }
    /**
     * @return Returns the entity.
     */
    public DScrapTrans getEntity()
    {
        return entity;
    }

    public void setEntity(DScrapTrans entity)
    {
        this.entity = entity;
    }

    public DScrapTransQuery getQueryEntity()
    {
        return queryEntity;
    }

    public void setQueryEntity(DScrapTransQuery queryEntity)
    {
        this.queryEntity = queryEntity;
    }

    public void setDscrapTransServiceImpl(DScrapTransServiceImpl dscrapTransServiceImpl)
    {
        this.dscrapTransServiceImpl = dscrapTransServiceImpl;
    }

    /* (non-Javadoc)
     * @see com.base.mybatis.BaseStruts2Action#getWarnMessage()
     */
    @Override
    public String getWarnMessage()
    {
        // TODO Auto-generated method stub
        return super.getWarnMessage();
    }

    /* (non-Javadoc)
     * @see com.base.mybatis.BaseStruts2Action#getMessage()
     */
    @Override
    public String getMessage()
    {
        // TODO Auto-generated method stub
        return super.getMessage();
    }

    /* (non-Javadoc)
     * @see com.base.mybatis.BaseStruts2Action#getInfoMessage()
     */
    @Override
    public String getInfoMessage()
    {
        // TODO Auto-generated method stub
        return super.getInfoMessage();
    }

}