package com.is.pretrst.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.base.mybatis.Page;
import com.is.mq.producer.PmsMessageTopicProducer;
import com.is.pretrst.dao.DScrapTransDaoImpl;
import com.is.pretrst.entity.DScrapTrans;
import com.is.pretrst.entity.query.DScrapTransImageQuery;
import com.is.pretrst.entity.query.DScrapTransQuery;
import com.is.utils.PublicDict;
import com.is.utils.sysOperLog.OperLogUtil;

/**
 *
 * @ClassName: DScrapTransServiceImpl
 * @Description: DScrapTrans表对应的服务类
 * @author 
 * @date 2013-09-10 10:26:55 *
 */
@Component
@Transactional 
public class DScrapTransServiceImpl {
	@SuppressWarnings("unused")
	private static Logger logger = LoggerFactory
			.getLogger(DScrapTrans.class);
	@Autowired
	private DScrapTransDaoImpl dscrapTransDaoImpl;
	@Autowired
	private DScrapTransImageServiceImpl dScrapTransImageServiceImpl;
	@Autowired
	private PmsMessageTopicProducer pmsMessageTopicProducer;
    public void setDscrapTransDaoImpl(DScrapTransDaoImpl dscrapTransDaoImpl)
    {
        this.dscrapTransDaoImpl = dscrapTransDaoImpl;
    }

    /**
     * 分页查询 剩料列表
     * @param queryEntity
     * @return page
     */
    @Transactional(readOnly=true)
    public Page pageQuery (DScrapTransQuery queryEntity){
        return dscrapTransDaoImpl.pageQuery("DScrapTrans.selectByPage",queryEntity);
    }
    /**
     * 查找一个 剩料对象
     * @param entity
     * @return DDeliveryPlan 
     */
    public DScrapTrans selectOneByEntity(DScrapTrans entity){
        entity =  dscrapTransDaoImpl.selectOneByEntity(entity);
        return entity;
    }
    
    /**
     * 根据条件更新对象
     * @param entity
     * @return
     */
    public int updateByEntity(DScrapTrans entity){
        int i = dscrapTransDaoImpl.updateByEntity(entity);
        if(i>0){
         // 添加日志
            OperLogUtil.insertOperLog(entity.getTransId(), PublicDict.MODEL_SCRAPTRANS,
                    "剩料管理", PublicDict.OPER_LOG_EVENT_UPDATE, "更新", "更新剩料信息", "更新成功",
                    "更新剩料信息", "d_scrap_trans"); 
        }
         return i;
    }

    /**
     * 剩料收货
     * @param transID   剩料运输主键Id
     * @param memo         
     * @param statecd 
     * @param transid
     * @return
     */
    public int  scrapTransReceive(DScrapTrans entity) {
        int i =  dscrapTransDaoImpl.updateByEntity(entity);
        entity=dscrapTransDaoImpl.selectOneByEntity(entity);
        if(i>0){
           // 添加日志
            OperLogUtil.insertOperLog(entity.getTransId(), PublicDict.MODEL_SCRAPTRANS,
                    "剩料管理", PublicDict.OPER_LOG_EVENT_UPDATE, "更新", "更新剩料信息", "更新成功",
                    "更新剩料信息", "d_scrap_trans"); 
        	if (entity.getStatusCd().equals("Yes")) {
				// 发消息接口
				pmsMessageTopicProducer.sendTopic(
						entity.getTransUserCd(),
						"【" + entity.getReceiver() + "】已经同意收货",
						"您从【" + entity.getFromWsCd() + "】发往【"
								+ entity.getToWsCd() + "】的剩料发货",entity.getReceiverId());
			} else {
				// 发消息接口
				pmsMessageTopicProducer.sendTopic(
						entity.getTransUserCd(),
						"【" + entity.getReceiver() + "】收货时发现有问题",
						"您从【" + entity.getFromWsCd() + "】发往【"
								+ entity.getToWsCd() + "】的剩料发货",entity.getReceiverId());

			}
            
        }
        return i;
    }
    
    /**
     * 删除剩料信息
     * @param entity
     * @return
     */
    public int deleteByEntity(DScrapTrans entity){
        int  i = 0;
        i=dscrapTransDaoImpl.deleteByEntity(entity);//根据transId删除剩料信息
        DScrapTransImageQuery image = new DScrapTransImageQuery();
        image.setTransId(entity.getTransId());
        i+=dScrapTransImageServiceImpl.deleteByEntity(image);//根据transId删除剩料图片信息与目录下图片
        return i;
    }
}
