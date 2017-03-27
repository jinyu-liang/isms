package com.is.pretrst.action;

import org.apache.struts2.convention.annotation.Namespace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.base.mybatis.BaseStruts2Action;
import com.is.pretrst.entity.MWorkshopTrans;
import com.is.pretrst.entity.query.MWorkshopTransQuery;
import com.is.pretrst.service.MWorkshopTransServiceImpl;
import com.is.utils.StringUtils;

/**
 *
 * @ClassName: MWorkshopTransAction
 * @Description: MWorkshopTrans表对应的Action类
 * @author 
 * @date 2013-09-10 10:26:53 *
 */
@Namespace("/rst")
public class MWorkshopTransAction extends BaseStruts2Action
{
    private static final long      serialVersionUID = 1L;

    @SuppressWarnings("unused")
    private static final Logger    LOGGER           = LoggerFactory.getLogger(MWorkshopTransAction.class);

    private MWorkshopTrans            entity;

    private MWorkshopTransQuery       queryEntity;

    @Autowired
    private MWorkshopTransServiceImpl mWorkTransServiceImpl;

    public MWorkshopTransAction()
    {
        super();
        if (entity == null)
        {
            entity = new MWorkshopTrans();
        }
        if (queryEntity == null)
        {
            queryEntity = new MWorkshopTransQuery();
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
	/**
	 * 查看
	 * @return
	 * @throws Exception
	 */
    public String view() throws Exception
    {

        entity = mWorkTransServiceImpl.selectOneByEntity(entity);
        return "MWorkshopTrans/view";
    }

    public String toList() throws Exception
    {
        return null;
    }
	/**
	 * 列表
	 * @return
	 * @throws Exception
	 */

    public String list() throws Exception
    {
        page = mWorkTransServiceImpl.pageQuery(queryEntity);
        return "MWorkshopTrans/list";
    }

    /**
     * 收货员确认收货
     * @return
     * @throws Exception
     */
    public String affirmDelivery() throws Exception
    {
        String i = "0";
        if (StringUtils.isNotEmpty(entity.getTransId()))
        {
            i = mWorkTransServiceImpl.MWorkTransReceive(entity.getTransId(), entity.getTransUserCd(), entity.getStatusCd(), entity.getDealComment());
        }
        if (i.equals("0"))
        {
            setMessage("操作成功");
        }
        else
        {
            setInfoMessage("操作失败");
        }
        return JSON_DATA;
    }

    /**
     * 删除工地转换信息.
     */
    public String deleteWorkTrans(){
        if(mWorkTransServiceImpl.deleteByEntity(entity)!=0){
            setMessage("删除成功");
        }else{
            setInfoMessage("删除失败");
        }
        return JSON_DATA;
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

    public MWorkshopTrans getEntity() {
		return entity;
	}

	public void setEntity(MWorkshopTrans entity) {
		this.entity = entity;
	}

	public MWorkshopTransQuery getQueryEntity() {
		return queryEntity;
	}

	public void setQueryEntity(MWorkshopTransQuery queryEntity) {
		this.queryEntity = queryEntity;
	}

	public MWorkshopTransServiceImpl getmWorkTransServiceImpl() {
		return mWorkTransServiceImpl;
	}

	public void setmWorkTransServiceImpl(
			MWorkshopTransServiceImpl mWorkTransServiceImpl) {
		this.mWorkTransServiceImpl = mWorkTransServiceImpl;
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