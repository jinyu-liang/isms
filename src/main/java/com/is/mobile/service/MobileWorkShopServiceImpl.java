package com.is.mobile.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.is.mobile.util.WsUtil;
import com.is.pretrst.dao.DReportImageDaoImpl;
import com.is.pretrst.dao.MWorkshopDaoImpl;
import com.is.pretrst.entity.MWorkshop;
import com.is.pretrst.entity.MWorkshopExt;
import com.is.pretrst.entity.query.MWorkshopExtQuery;
import com.is.utils.StringUtils;

/**
 * 
 * <p>文件名称: MobileSendMetralServiceImpl.java</p>
 * <p>文件描述: 手机登录验证服务实现</p>
 * <p>版权所有: 版权所有(C)2013-2020</p>
 * <p>公   司: IS软件事业部</p>
 * <p>内容摘要:
 * <p>其他说明:只要能够请求到本方法，即有采购的的权限</p>
 * <p>完成日期：2014年9月14日</p>
 * <p>修改记录0：无</p>
 * @version 1.0
 * @author  
 */
@Component
@Transactional
public class MobileWorkShopServiceImpl
{
    @Autowired
    private MWorkshopDaoImpl mWorkShopDaoImpl;
	//采购申请图片
    @Autowired
    private DReportImageDaoImpl dReportImageDaoImpl;
    @SuppressWarnings("unused")
    private static Logger  LOGGER = LoggerFactory.getLogger(MobileWorkShopServiceImpl.class);
    String    statusCd ="";

    
    /**
     *  采购待审批列表
     * @param username
     * @return List<DReport>
     */
    public List<MWorkshopExt> getWorkShopList(String userId) throws Exception
    {
        if(StringUtils.isEmpty(userId)){
        	return null;
        }
    	List<MWorkshopExt>  reportList = new ArrayList<MWorkshopExt>();
        reportList.addAll(this.getPurchByUserId(userId));
        return reportList;
    }
    
    /**
     * 根据当前用户id和状态码得到待审批数据列表
     * @param userId
     * @param statusCd
     * @return
     */
    public List<MWorkshopExt> getPurchByUserId(String userId){
    	List<MWorkshopExt> planList = new ArrayList<MWorkshopExt>();
        String post = WsUtil.getPostByUserId(userId);//当前用户的职务代码
        String divcd = WsUtil.getPostByDivcd(userId);
//        if ("38".equals(post))
//        {//职务为区域部长
//        	planList = verifiedSiteHead(userId);
//        }
//        else if("1".equals(post)||"3".equals(post)||"11".equals(post))
//        {//职务为总经理或部长
//        	planList = verifiedAll();//查询所有
//        }else
//        {//项目经理和其他职务只查询自己相关的数据
        	planList = verifiedSiteLevel(divcd);
//        }
        return planList;
    }
    /**
     * 项目经理级别的采购表，查询跟自己相关的数据
     * @param userId
     * @param statusCd
     * @return
     */
    public List<MWorkshopExt> verifiedSiteLevel(String userId){
        List<MWorkshopExt> levelList = new ArrayList<MWorkshopExt>();//中间list
 
        MWorkshopExtQuery entity  = new MWorkshopExtQuery();
        entity.setDivCd(userId);//报告者为当前用户
        levelList.addAll(mWorkShopDaoImpl.selectWorkshopByEntity(entity));
        return levelList;
    }

    /**
     * 区域部长的采购表，查询跟自己相关的数据和下属项目经理的数据
     * @param userId
     * @param statusCd
     * @return
     */
    public List<MWorkshopExt> verifiedSiteHead(String userId){
    	 List<MWorkshopExt>  levelList = new ArrayList<MWorkshopExt>();
        MWorkshopExtQuery entity  = new MWorkshopExtQuery();
        entity.setManagerUserId(userId);//报告者为当前用户
        levelList.addAll(mWorkShopDaoImpl.selectQyBZWorkshopByEntity(entity));
        
        return levelList;
    }
    /**
     * 总经理与部长的采购表，查询所有的数据
     * @param userId
     * @param statusCd
     * @return
     */
    public List<MWorkshopExt> verifiedAll(){
    	List<MWorkshopExt> allList = new ArrayList<MWorkshopExt>();
        allList = mWorkShopDaoImpl.selectWorkshopByEntity(new MWorkshopExtQuery());
        return allList;
    }
    public List<String> getWorkShopImg(MWorkshop entity){
    	List<String> allList = new ArrayList<String>();
    	allList = mWorkShopDaoImpl.getWorkShopImg(entity);
    	return allList;
    }

	public DReportImageDaoImpl getdReportImageDaoImpl() {
		return dReportImageDaoImpl;
	}



	public void setdReportImageDaoImpl(DReportImageDaoImpl dReportImageDaoImpl) {
		this.dReportImageDaoImpl = dReportImageDaoImpl;
	}



	public String getStatusCd() {
		return statusCd;
	}



	public void setStatusCd(String statusCd) {
		this.statusCd = statusCd;
	}


		public MWorkshopDaoImpl getmWorkShopDaoImpl() {
			return mWorkShopDaoImpl;
		}

		public void setmWorkShopDaoImpl(MWorkshopDaoImpl mWorkShopDaoImpl) {
			this.mWorkShopDaoImpl = mWorkShopDaoImpl;
		}

}



