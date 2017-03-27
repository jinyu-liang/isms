package com.is.mobile.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.is.ggkz.dao.GgkzUserInfoDaoImpl;
import com.is.ggkz.entity.GgkzUserInfo;
import com.is.mobile.util.WsUtil;
import com.is.mq.producer.PmsMessageTopicProducer;
import com.is.pretrst.dao.DDeliveryPlanDaoImpl;
import com.is.pretrst.dao.DInvoiceDaoImpl;
import com.is.pretrst.dao.DInvoiceImageDaoImpl;
import com.is.pretrst.dao.DInvoiceItemDaoImpl;
import com.is.pretrst.dao.DRecordReplyDaoImpl;
import com.is.pretrst.dao.DRecordUserMappingDaoImpl;
import com.is.pretrst.dao.MWorkshopDaoImpl;
import com.is.pretrst.entity.DDeliveryPlan;
import com.is.pretrst.entity.DInvoice;
import com.is.pretrst.entity.DInvoiceImage;
import com.is.pretrst.entity.DInvoiceItem;
import com.is.pretrst.entity.DRecordReply;
import com.is.pretrst.entity.DRecordUserMapping;
import com.is.pretrst.entity.MWorkshop;
import com.is.pretrst.entity.query.DRecordUserMappingQuery;
import com.is.pretrst.service.MWorkshopTransServiceImpl;
import com.is.utils.PublicDict;
import com.is.utils.StringUtils;
import com.is.utils.keyUtils.KeyGen;
import com.is.utils.sysOperLog.OperLogUtil;

/**
 * 
 * <p>文件名称: MobileSendMetralServiceImpl.java</p>
 * <p>文件描述: 手机登录验证服务实现</p>
 * <p>版权所有: 版权所有(C)2013-2020</p>
 * <p>公   司: IS软件事业部</p>
 * <p>内容摘要:在查询发料计划表时，分三个级别查询，一级：总经理、部长，查询所有发料计划表数据。二级：区域部长，查询自己相关的发料计划表数据与下属项目经理的发料计划表数据，三级：项目经理或其他职务的人，查找与自己相关的发料计划表数据 </p>
 * <p>其他说明:只要能够请求到本方法，即有发料的权限</p>
 * <p>完成日期：2014年9月14日</p>
 * <p>修改记录0：无</p>
 * @version 1.0
 * @author  
 */
@Component
@Transactional
public class MobileSendMetralServiceImpl
{
    @Autowired
    private DDeliveryPlanDaoImpl ddeliveryPlanDaoImpl;
    @Autowired
    private DInvoiceDaoImpl dinvoiceDaoImpl;
    @Autowired
    private DRecordReplyDaoImpl drecordReplyDaoImpl;
    @Autowired
    private DInvoiceImageDaoImpl dinvoiceImageDaoImpl;
    @Autowired
    private GgkzUserInfoDaoImpl ggkzUserInfoDaoImpl;
    @Autowired
    private MWorkshopTransServiceImpl mworkshopTransServiceImpl;
    @Autowired
    private DInvoiceItemDaoImpl dinvoiceItemDaoImpl;
    @Autowired
    private PmsMessageTopicProducer pmsMessageTopicProducer;
    @Autowired
    private MWorkshopDaoImpl mworkshopDaoImpl;
    @Autowired
    private DRecordUserMappingDaoImpl drecordUserMappingDaoImpl;
    String                       statusCd ="";
    /**
     *  发料管理待审批计划表
     * @param username
     * @return List<DDeliveryPlan>
     */
    public List<DDeliveryPlan> getSendMetralApproval(String userId) throws Exception
    {
    	List<DDeliveryPlan>  valList = new ArrayList<DDeliveryPlan>();
        statusCd = "0";//状态为待审批
        valList.addAll(this.getPlanByUserIdAndStatus(userId, statusCd));
        return valList;
    }

    /**
     * 发料管理已审批计划表
     * @param username
     * @return List<DDeliveryPlan>
     */
    public List<DDeliveryPlan> getSendMetralApprovaled(String userId)
    {
    	List<DDeliveryPlan>  valedList = new ArrayList<DDeliveryPlan>();
        //statusCd = "1";//状态为已审批
        valedList.addAll( this.getPlanByUserIdAndStatus(userId, "1"));//状态为已审批
        valedList.addAll( this.getPlanByUserIdAndStatus(userId, "2"));//状态为已审批有问题
       // List<DDeliveryPlan> warnList = getWarndb(userId,statusCd);
       // valedList.addAll(warnList);//已审批中有问题的数据
        return valedList;
    }

    /**
     * 发料管理已下发计划表
     * @param username
     * @return List<DInvoice>
     */
    public List<DDeliveryPlan> getSendMetralIssueed(String userId)
    {
        statusCd = "3";//状态为已下发
        return this.getPlanByUserIdAndStatus(userId, statusCd);
    }

    /**
     * 发料管理待审批出门单
     * @param username
     * @return List<DInvoice>
     */
    public List<DInvoice> getSendMetralOutApproval(String userId)
    {
    	statusCd = "0";
        return this.getInvoiceByUserIdAndStatus(userId, statusCd);
    }

    /**
     * 发料管理已审批出门单
     * @param username
     * @return List<DInvoice>
     */
    public List<DInvoice> getSendMetralOutApprovaled(String userId)
    {
        List<DInvoice> valedList = new ArrayList<DInvoice>();
        valedList.addAll(this.getInvoiceByUserIdAndStatus(userId, "2"));//查询已审批有问题的数据
        valedList.addAll(this.getInvoiceByUserIdAndStatus(userId, "1"));//查询已审批的数据
        return valedList;
    }

    /**
     * 发料管理送货中出门单
     * @param username
     * @return List<DInvoice>
     */
    public List<DInvoice> getSendMetralTrans(String userId)
    {
    	statusCd = "3";
    	return this.getInvoiceByUserIdAndStatus(userId, statusCd);
    }

    /**
     * 发料管理已送达出门单
     * @param username
     * @return List<DInvoice>
     */
    public List<DInvoice> getSendMetralArrived(String userId)
    {
        List<DInvoice> vedList = new ArrayList<DInvoice>();
        vedList.addAll(this.getInvoiceByUserIdAndStatus(userId, "5"));//收货有问题
        vedList.addAll(this.getInvoiceByUserIdAndStatus(userId, "7"));//收货问题已解决
        vedList.addAll(this.getInvoiceByUserIdAndStatus(userId, "4"));//已经收货
        return vedList;
    }
    
    /**
     * 项目经理级别的发料计划表，查询跟自己相关的数据
     * @param userId
     * @param statusCd
     * @return
     */
    public List<DDeliveryPlan> verifiedSiteLevel(String userId,String statusCd){
        List<DDeliveryPlan> levelList = new ArrayList<DDeliveryPlan>();//中间list
        List<DDeliveryPlan> levelImageList = new ArrayList<DDeliveryPlan>();
        levelList = new ArrayList<DDeliveryPlan>();
        DDeliveryPlan entity  = new DDeliveryPlan();
        entity.setStatusCd(statusCd);
        entity.setDeleteCd("0");
        entity.setVerifiedSiteUserCd(userId);//审批项目经理为当前用户
        levelList = ddeliveryPlanDaoImpl.selectByEntityWs(entity);
        entity.setVerifiedSiteUserCd(null);
        entity.setCreateUserCd(userId);//创建者为当前用户
        levelList.addAll( ddeliveryPlanDaoImpl.selectByEntityWs(entity));
        entity.setCreateUserCd(null);
        entity.setVerifiedHeadUserCd(userId);//审批部长为当前用户
        levelList.addAll(  ddeliveryPlanDaoImpl.selectByEntityWs(entity));
        if("3".equals(statusCd)){
            /*entity.setVerifiedHeadUserCd(null);
            entity.setCreateUserCd(userId);
            levelImageList = ddeliveryPlanDaoImpl.selectPlanAndInvoiceImageByUserIdStatus(entity);//查询 的是回复人所回复的发料计划信息与发料计划对应的出门单图片*/
            entity.setVerifiedHeadUserCd(null);
            entity.setCreateUserCd(userId);
            levelList.addAll(this.selectPlanFromMapping(entity));//根据userId查找回复表里对应已经完成的发料计划
        }
        Set<String> set = new HashSet<String>();
        for (DDeliveryPlan plan:levelList) {
            if (set.add(plan.getPlanId())){//根据planId去重复
                levelImageList.add(plan);
             } 
        }
        levelList.clear();
        levelList.addAll(levelImageList);
        
        return levelList;
    }
    /**
     * 区域部长的发料计划表，查询跟自己相关的数据和下属项目经理的数据
     * @param userId
     * @param statusCd
     * @return
     */
    public List<DDeliveryPlan> verifiedSiteHead(String userId,String statusCd){
    	 List<DDeliveryPlan>  siteHeadList = new ArrayList<DDeliveryPlan>();
    	 DDeliveryPlan entity  = new DDeliveryPlan();
        entity.setStatusCd(statusCd);
        entity.setDeleteCd("0");
        siteHeadList.addAll(this.verifiedSiteLevel(userId,statusCd));//查询跟自己相关的数据
        List<String> strList = WsUtil.getSiteUserIdByEareaheadUserId(userId);//查询区域部长所有下属项目经理的id
        for (String siteId : strList)
        {
        	siteHeadList.addAll(this.verifiedSiteLevel(siteId,statusCd));//查询区域部长所有下属项目经理的计划
        }
        return siteHeadList;
    }
    /**
     * 总经理与部长的发料计划表，查询所有的数据
     * @param userId
     * @param statusCd
     * @return
     */
    public List<DDeliveryPlan> verifiedAll(String statusCd){
        List<DDeliveryPlan> allList = new ArrayList<DDeliveryPlan>();
        
    	DDeliveryPlan entity  = new DDeliveryPlan();
        entity.setDeleteCd("0");
        entity.setStatusCd(statusCd);
        allList = ddeliveryPlanDaoImpl.selectByEntityWs(entity);
        return allList;
    }
    /**
     * 查询有问题的数据（已弃）
     * @param userId
     * @param statusCd
     * @return
     */
    public List<DDeliveryPlan> getWarndb(String userId,String statusCd){
    	//List<DDeliveryPlan> warnList = new ArrayList<DDeliveryPlan>();
    	List<DDeliveryPlan>  pList = new ArrayList<DDeliveryPlan>();//存放有问题的数据
    	pList = getPlanByUserIdAndStatus(userId, "2");
    	
       /* if("0".equals(statusCd)){//查询在待审批状态中的有问题数据
        	for(DDeliveryPlan plan:pList){
        		if(plan.getVerifiedHeadStatus()==null||plan.getVerifiedSiteStatus()==null){
        			warnList.add(plan);
        		}
        	}
        }if("1".equals(statusCd)){//查询在已审批状态中的有问题数据
        	for(DDeliveryPlan plan:pList){
        		if(plan.getVerifiedHeadStatus()!=null&&plan.getVerifiedSiteStatus()!=null){
        			warnList.add(plan);
        		}
        	}
        }
        return warnList;*/
    	return pList;
    }
    
    /**
     * 根据当前用户id和各环节状态码得到发料计划表数据
     * @param userId
     * @param statusCd
     * @return
     */
    public List<DDeliveryPlan> getPlanByUserIdAndStatus(String userId,String statusCd){
    	List<DDeliveryPlan> planList = new ArrayList<DDeliveryPlan>();
        String post = WsUtil.getPostByUserId(userId);//当前用户的职务代码
        
        if ("38".equals(post))
        {//职务为区域部长
        	planList = verifiedSiteHead(userId,statusCd);
        }
        else if("1".equals(post)||"3".equals(post)||"11".equals(post)||"2".equals(post)||"6".equals(post))
        {//职务为总经理或部长或信息主管和部门领导信息主管和董事长
        	planList = verifiedAll(statusCd);//查询所有
        }else if(("9".equals(post)||"20".equals(post)||"30".equals(post))&&"3".equals(statusCd))
        {//职务为发货员并且查询的是已下发的数据
            planList = verifiedAll(statusCd);//查询所有已下发的
        }else
        {//项目经理和其他职务只查询自己相关的数据
        	planList = verifiedSiteLevel(userId,statusCd);
        }
        if("3".equals(statusCd)){//如果是查看的已下发，则去封装回复信息,出门单图片
            
            List<DDeliveryPlan> plan_Rep_Img_List = new ArrayList<DDeliveryPlan>();
            for(DDeliveryPlan p:planList){
                DDeliveryPlan newPlan = new DDeliveryPlan();
                newPlan = this.getPlanAndR(p, userId);//得到封装了回复消息的对象
                newPlan.setIsReple(this.isReply(p, userId));//是否对本条数据有回复的权限
                /*   封装计划下的出门单图片              */
                String planId = newPlan.getPlanId();
                DInvoice invoice = new DInvoice();
                invoice.setPlanId(planId);
                if(("9".equals(post)||"20".equals(post)||"30".equals(post))){//如果当前人是发料员，在每个计划表中只查询自己上传的出门单
                    invoice.setInvoiceUserId(userId);
                }
                List<DInvoice> invoiceList = new ArrayList<DInvoice>();
                invoiceList = dinvoiceDaoImpl.selectByEntity(invoice);
                List<String> dinvoicePaths = new ArrayList<String>();
                List<String> dinvoiceImages = new ArrayList<String>();
                if(invoiceList!=null&&invoiceList.size()!=0){
                    if(StringUtils.isNotEmpty(invoiceList.get(0).getStatusCd())&& "6".equals(invoiceList.get(0).getStatusCd())){
                        newPlan.setIsUploadInvoice("1");//本条计划下有出门单，并且是未申请 发车的
                    }else {
                        newPlan.setIsUploadInvoice("2");//本条计划下有出门单，并且是已经申请 发车的
                    }
                    newPlan.setInvoiceId(invoiceList.get(0).getInvoiceId());//出门单id
                }else{
                    newPlan.setIsUploadInvoice("0");//本条计划下没有出门单
                }
                for(DInvoice in:invoiceList){//循环出门单，取出每个门单缩略图,然后再查询每个出门单对应着的详情图片
                    dinvoicePaths.add(in.getDinvoiceImagePath());
                    String invoiceId = in.getInvoiceId();
                    DInvoiceImage image = new DInvoiceImage();
                    image.setInvoiceId(invoiceId);
                    List<DInvoiceImage> imageList =  new ArrayList<DInvoiceImage>(); 
                    imageList = dinvoiceImageDaoImpl.selectByEntity(image);
                    for(DInvoiceImage im:imageList){//循环出门单详情 ，取出门单详情图片
                        dinvoiceImages.add(im.getFileName());
                    }
                }
                newPlan.setDinvoiceImages(dinvoiceImages);
                newPlan.setDinvoicePaths(dinvoicePaths);
                plan_Rep_Img_List.add(newPlan);
            }
            planList.clear();
            planList.addAll(plan_Rep_Img_List);
            
        }
        return planList;
    }
    
    /**
     * 根据发料信息对象与当前用户id封装当前用户在本条发料信息中的回复
     * @param plan 发料信息对象
     * @param userId 当前用户id
     * @return 封装了回复消息的发料对象
     */
    public DDeliveryPlan getPlanAndR(DDeliveryPlan plan,String userId){
        DRecordReply  reply  = new DRecordReply();
        reply.setReplyUserCd(userId);
        reply.setRecordId(plan.getPlanId());
        reply = drecordReplyDaoImpl.selectOneByEntity(reply);
        if(reply!=null){
            plan.setRecordReplyId(reply.getReplyId());
            plan.setRecordTitle(reply.getTitle());
            plan.setRecordContent(reply.getContent());
            plan.setRecordUpdateTm(reply.getUpdateTm());
        }
        return plan;
    }
    
    /**
     * 根据出门单状态与当前人的职务查询相关的出门单数据
     * @param userId 用户id
     * @param statusCd 出门单状态
     * @return  List<DInvoice> 出门单集合
     */
    public List<DInvoice> getInvoiceByUserIdAndStatus(String userId,String statusCd){
        List<DInvoice> invoiceList = new ArrayList<DInvoice>();
        String post = WsUtil.getPostByUserId(userId);//当前用户的职务代码
        
        if ("38".equals(post))
        {//职务为区域部长
            invoiceList = getSiteHeadInvoie(userId,statusCd);
        }
        else if("1".equals(post)||"3".equals(post))
        {//职务为总经理或部长
            invoiceList = getAllInvoice(statusCd);//查询所有
        }else
        {//项目经理和其他职务只查询自己相关的数据
            invoiceList = getSiteByUserIdAndStatus(userId,statusCd);
        }
        return invoiceList;
    }
    
    /**
     * 根据当前用户与状态码查询出门单数据
     * @param userId
     * @param statusCd
     * @return List<DInvoice>
     */
    public List<DInvoice> getSiteByUserIdAndStatus(String userId,String statusCd){
    	List<DInvoice> invoiceList = new ArrayList<DInvoice>();
    	
    	DInvoice entity = new DInvoice();
    	entity.setDeleteCd("0");
    	entity.setStatusCd(statusCd);
    	entity.setInvoiceUserId(userId);//起单用户为当前用户
    	invoiceList .addAll( dinvoiceDaoImpl.selectByEntity(entity));
    	//if(invoiceList==null||invoiceList.size()==0){//如果不是起单人，那就是批准人，业务上这两个人不可能会转换
    		entity.setInvoiceUserId(null);
        	entity.setApprovalUserCd(userId);//批准发货人为当前用户
        	invoiceList .addAll( dinvoiceDaoImpl.selectByEntity(entity));
    //	}
    	return invoiceList;
    }
    
    /**
     * 根据状态码查询所有的出门单 经理或者部长
     * @param status
     * @return
     */
    public List<DInvoice> getAllInvoice(String status){
        DInvoice invoice = new DInvoice();
        invoice.setStatusCd(status);
        invoice.setDeleteCd("0");
       return dinvoiceDaoImpl.selectByEntity(invoice);
    }
    
    /**
     * 根据出门单状态查询自己的和自己下属的出门单 区域部长
     * @param userId
     * @param status
     * @return
     */
    public List<DInvoice> getSiteHeadInvoie(String userId,String status){
        List<DInvoice> invoiceList = new ArrayList<DInvoice>();
        invoiceList.addAll(this.getSiteByUserIdAndStatus(userId, status));
        List<String> strList = WsUtil.getSiteUserIdByEareaheadUserId(userId);//查询区域部长所有下属项目经理的id
        for (String siteId : strList)
        {
            invoiceList.addAll(this.getSiteByUserIdAndStatus(siteId,status));//查询区域部长所有下属项目经理的出门单
        }
        return invoiceList;
    }
    
    /**
     * 判断当前登陆人是否有已下发计划表的回复权限
     * @param plan 计划表对象
     * @param userCd 当前登陆人id
     * @return 1:可以回复  0:不可以回复
     */
    public String isReply(DDeliveryPlan plan,String userCd){
        //查询是否是其它领导
        DRecordUserMappingQuery mapp = new DRecordUserMappingQuery();
        mapp.setRecordType("0");//类型为计划表
        mapp.setMappingUserCd(userCd);
        mapp.setRecordId(plan.getPlanId());
        List<DRecordUserMapping> mapplist = drecordUserMappingDaoImpl.selectByEntity(mapp);
        if(mapplist!=null&&mapplist.size()>0){
            return "1";
        }
        if(userCd.equals(plan.getCreateUserCd())){//如果是本条数据的创建者
            return "1";
        }
        
        if(userCd.equals(plan.getVerifiedSiteUserCd())){//如果是本条数据的项目经理
            return "1";
        }

        if(userCd.equals(plan.getVerifiedHeadUserCd())){//如果是本条数据的审批部长
            return "1";
        }
        GgkzUserInfo info = new GgkzUserInfo();
        info.setUserId(userCd);
        info = ggkzUserInfoDaoImpl.selectOneByEntity(info);
        if(info!=null && StringUtils.isNotEmpty(info.getPost())){//如果当前登陆人就发货员
            if("9".equals(info.getPost())){
                return "1";
            }
            if("20".equals(info.getPost())){
                return "1";
            }
            if("30".equals(info.getPost())){
                return "1";
            }
        }
        return "0";
    }
    /**
     * 更新回复对象
     * @param userId 回复人id
     * @param planId 计划id
     * @return  String 0:成功 ;1：失败
     */
    public String updateRecordReply(String userId,String planId,String title,String memo){
        int i = 0;
        //新建一个查询对象
        DRecordReply  entityTmp = new DRecordReply();
        entityTmp.setRecordId(planId);
        entityTmp.setReplyUserCd(userId);
        
         entityTmp = drecordReplyDaoImpl.selectOneByEntity(entityTmp);
        if(entityTmp!=null){
            entityTmp.setUpdateTm(new Date());
            entityTmp.setContent(memo);
            entityTmp.setTitle(title);
            i = drecordReplyDaoImpl.updateByEntity(entityTmp);//已存在当前用户的回复，更新
        }else{
            DRecordReply  entity = new DRecordReply();
            entity.setCreateTm(new Date());//还没有回复过，添加
            entity.setUpdateTm(new Date());
            entity.setReplyId(KeyGen.getCommonKeyGen(PublicDict.DRECORD_REPLY));
            entity.setRecordType("0");//0为发料信息回复
            entity.setDeleteCd("0");//删除区分
            entity.setReplyUserCd(userId);
            entity.setRecordId(planId);
            entity.setTitle(title);
            entity.setContent(memo);//回复内容
            entity.setExtraFlg("0");//回复额外区分 重要，星标等-备用
            entity.setRootReplyId("0");//根回复项目ID-备用
            entity.setParentReplyId("0");//父回复项目ID-备用
            i = drecordReplyDaoImpl.insert(entity);
        }
        if(i!=0){
            // 添加日志
            OperLogUtil.insertMobileOperLog(planId, userId,PublicDict.MODEL_SENDMETRAL,
            "发料管理", PublicDict.OPER_LOG_EVENT_ADD, "添加", "手机端回复发料计划", "添加成功",
            "手机端回复发料计划", "d_record_reply");
            return "0";//成功
        }else return "1";
    }
    
    /**
     * 根据计划id查询所有回复
     * @param planId
     * @return
     */
    public List<DRecordReply> recordView(String planId){
        DRecordReply reply = new DRecordReply();
        reply.setRecordId(planId);
        return drecordReplyDaoImpl.selectByEntity(reply);
        
        
    }
    /**
     * 根据planId查询plan对象
     * @param planId
     * @return DDeliveryPlan
     */
    public DDeliveryPlan selectPlanById(String planId){
        DDeliveryPlan entity = new DDeliveryPlan();
        entity.setPlanId(planId);
        entity.setDeleteCd("0");
        return ddeliveryPlanDaoImpl.selectOneByEntity(entity);
    }
    
    /**
     * 根据invoiceId查询出门单对象
     * @param invoiceId
     * @return DInvoice
     */
    public DInvoice selectInvoiceById(String invoiceId)throws Exception{
        DInvoice entity = new DInvoice();
        entity.setInvoiceId(invoiceId);
        entity.setDeleteCd("0");
        return dinvoiceDaoImpl.selectOneByEntity(entity);
    }
    
    /**
     * 发货员申请 发车
     * @param userId 当前用户id
     * @param invoiceId 出门单id
     * @param List<inStream 图片流
     * @return 成功：0;不成功：1;
     * @throws IOException
     */
    public String applyStartOff (String userId,String invoiceId,List<File> userfile) throws IOException{
        int i = 0;                                                          
        DInvoice invoice = new DInvoice();
        invoice.setInvoiceId(invoiceId);
        invoice.setUpdateTm(new Date());
        invoice.setApprovalReqTm(new Date());//申请发车时间
        invoice.setStatusCd("0");
        i += dinvoiceDaoImpl.updateByEntity(invoice);
        DDeliveryPlan plan = new DDeliveryPlan();
        DInvoice invo = new DInvoice();
        invo =dinvoiceDaoImpl.selectOneByEntity(invoice); 
        plan.setPlanId(invo.getPlanId());
        plan.setDeliveryReqTm(new Date());//对应的出门单申请发车时间
        i += ddeliveryPlanDaoImpl.updateByEntity(plan);
        //图片处理方法
        i+=imageDeal(invoiceId, userId, userfile);
        if(i!=0){
         // 添加日志
            OperLogUtil.insertMobileOperLog(invoiceId, userId,PublicDict.MODEL_SENDMETRAL,
            "发料管理", PublicDict.OPER_LOG_EVENT_UPDATE, "更新", "手机端发货员申请发车", "更新成功",
            "手机端发货员申请发车", "d_invoice");
          //发送通知消息
            DInvoice msginvoice = new DInvoice();
            msginvoice.setInvoiceId(invoiceId);
            msginvoice = dinvoiceDaoImpl.selectOneByEntity(msginvoice);
            pmsMessageTopicProducer.sendTopic(msginvoice.getApprovalUserCd(),"待审批出门单通知", getNameById(msginvoice.getInvoiceUserId())+"上传的出门单申请发车，请及时审批", msginvoice.getInvoiceUserId());
            return "0";
        }else return "1";
    }

/**图片处理方法
 * 
 * @param reportId
 * @param userId
 * @param userfile
 * @return
 */
  
  public int imageDeal(String invoiceId,String userId,List<File> userfile){
      int j=0;
      if(userfile!=null){
	      try {
	          for(int i=0;i<userfile.size();i++)
	          {
	
	  			String photoId=KeyGen.getCommonKeyGen(PublicDict.USERPHOTO);
	  			String rootPath=Thread.currentThread().getContextClassLoader().getResource("/").getPath();
	  			rootPath = rootPath.substring(0,rootPath.length()-16)+"upload/";
	  			String filename = photoId+".jpg";
	              FileInputStream in = new FileInputStream(userfile.get(i));
	              FileOutputStream out = new FileOutputStream(rootPath+filename);
	              byte[] b = new byte[10240];
	              int len = 0;
	              while ((len = in.read(b)) > 0) {
	                  out.write(b, 0, len);
	              }
	              in.close();
	              out.close();
	            DInvoiceImage image = new DInvoiceImage();
	            image.setInvoiceId(invoiceId);
	            filename="/upload/"+filename;
	            image.setFileName(filename);
	            image.setPhotoUserCd(userId);
	            image.setUploadTm(new Date());
	            image.setStatusCd("0");
	            image.setPhotoId(photoId);//主键
	              j += dinvoiceImageDaoImpl.insert(image);
	              if(j<0){
	               // 添加日志
	                  OperLogUtil.insertMobileOperLog(invoiceId, userId,PublicDict.MODEL_SENDMETRAL,
	                  "发料管理", PublicDict.OPER_LOG_EVENT_ADD, "添加", "手机端上传出门单图片", "添加成功",
	                  "手机端上传出门单图片", "d_invoice_image");
	              }
	          }
	      } catch (Exception e) {
	      }
      }
      return j;
  }


    /**
     * 项目经理审批发车
     * @param userId
     * @param invoiceId
     * @param status 0：同意;1:有问题
     * @param memo
     * @return 成功：0;不成功：1;
     */
    public String siteDisposeSender(String userId,String invoiceId,String status ,String memo){
        DInvoice invoice = new DInvoice();
        invoice.setInvoiceId(invoiceId);//出门单id
        invoice.setApprovalTm(new Date());//审批时间
       // invoice.setApprovalUserCd(userId);//审批用户id
        invoice.setApprovalUserNm(this.getNameById(userId));//审批用户名称
        invoice.setUpdateTm(new Date());//最后更新时间
        invoice.setMemo(memo);//备注
      
        DInvoice msginvoice = new DInvoice();
        msginvoice.setInvoiceId(invoiceId);
        msginvoice = dinvoiceDaoImpl.selectOneByEntity(msginvoice);
        if("0".equals(status)){
            invoice.setStatusCd("1");//审批状态可发车
            //通知
            pmsMessageTopicProducer.sendTopic(msginvoice.getInvoiceUserId(),"已审批出门单通知", "您上传的发往"+selectShopNameByCd(msginvoice.getToWsCd())+"工地的出门单申请发车已经审批，请及时发车", userId);
        }
        else
        {
            invoice.setStatusCd("2");//审批状态发车有问题
          //通知
            pmsMessageTopicProducer.sendTopic(msginvoice.getInvoiceUserId(),"已审批出门单通知", "您上传的发往"+selectShopNameByCd(msginvoice.getToWsCd())+"工地的出门单申请发车有问题，请修改后重新申请", userId);
        }
        
        int i = dinvoiceDaoImpl.updateByEntity(invoice);
        if(i!=0){
         // 添加日志
            OperLogUtil.insertMobileOperLog(invoiceId, userId,PublicDict.MODEL_SENDMETRAL,
            "发料管理", PublicDict.OPER_LOG_EVENT_UPDATE, "更新", "手机端审批发车", "更新成功",
            "手机端审批发车", "d_invoice");
            return "0";
        }else return "1";
    }
    /**
     * 根据用户id查询用户名
     * @param userId
     * @return  用户名（String）
     */
    public String getNameById(String userId){
        GgkzUserInfo entity = new GgkzUserInfo();
        entity.setUserId(userId);
        entity = ggkzUserInfoDaoImpl.selectOneByEntity(entity);
        if(entity!=null&&StringUtils.isNotEmpty(entity.getName())){
            return entity.getName();
        }return "";
    }
    /**
     * 发货员确认发车
     * @param invoiceId
     * @return
     */
    public String senderAffirm(String invoiceId){
        DInvoice invoice = new DInvoice();
        invoice.setInvoiceId(invoiceId);
        invoice.setStatusCd("3");//送货中
        invoice.setSureDeliveryTm(new Date());//确认发车时间
        invoice.setUpdateTm(new Date());
        int i = dinvoiceDaoImpl.updateByEntity(invoice);
        if(i!=0){
         // 添加日志
            OperLogUtil.insertMobileOperLog(invoiceId, "",PublicDict.MODEL_SENDMETRAL,
            "发料管理", PublicDict.OPER_LOG_EVENT_UPDATE, "更新", "手机端确认发车", "更新成功",
            "手机端确认发车", "d_invoice");
            DInvoice msginvoice = new DInvoice();
            msginvoice.setInvoiceId(invoiceId);
            msginvoice = dinvoiceDaoImpl.selectOneByEntity(msginvoice);
            pmsMessageTopicProducer.sendTopic(msginvoice.getApprovalUserCd(),"送货中出门单通知", this.getNameById(msginvoice.getInvoiceUserId())+"上传的出门单已确认发车，请注意收货", msginvoice.getInvoiceUserId());
            return "0";
        }else return "1";
    }
    /**
     * 项目经理确认收货 ★★与确认问题解决合并
     * @param invoiceId
     * @param status
     * @param memo
     * @return
     */
    public String siteAffirmReceive(String invoiceId,String status,String memo ){
        int i = 0;
        DInvoice invoice = new DInvoice();
        DDeliveryPlan plan = new DDeliveryPlan();//用于更新本条出门单对应的计划表的完成时间
        invoice.setInvoiceId(invoiceId);
        invoice = dinvoiceDaoImpl.selectOneByEntity(invoice);
        plan.setPlanId(invoice.getPlanId());
        plan.setDeliveryDoneTm(new Date());//对应的出门单收货时间
        if(invoice!=null){
            invoice.setUpdateTm(new Date());
            invoice.setMemo(memo);
            invoice.setArrivalTm(new Date());//确认时间
            if(StringUtils.isNotEmpty(invoice.getStatusCd())&&"5".equals(invoice.getStatusCd())){//如果原来这条数据是项目经理收货有问题，
                if("0".equals(status)){//本次请求如果是同意，就把本条出门单置为问题解决
                    invoice.setStatusCd("7");
                    i += ddeliveryPlanDaoImpl.updateByEntity(plan);//更新计划表的完成时间
                    i += workItemDeal(invoiceId);//把出门单中产品详情集合添加到收货工地
                    //通知
                    pmsMessageTopicProducer.sendTopic(invoice.getInvoiceUserId(),"已送达出门单通知", "您发往"+selectShopNameByCd(invoice.getToWsCd())+"的出门单问题已解决", invoice.getApprovalUserCd());
                }
            }else{
                if("0".equals(status)){//正常情况下
                    invoice.setStatusCd("4");
                    i += ddeliveryPlanDaoImpl.updateByEntity(plan);//更新计划表的完成时间
                    i += workItemDeal(invoiceId);//把出门单中产品详情集合添加到收货工地
                    //通知
                    pmsMessageTopicProducer.sendTopic(invoice.getInvoiceUserId(),"已送达出门单通知", "您发往"+selectShopNameByCd(invoice.getToWsCd())+"的出门单已确认收货", invoice.getApprovalUserCd());
                }
                else
                {
                    invoice.setStatusCd("5");//收货有问题
                    //通知
                    pmsMessageTopicProducer.sendTopic(invoice.getInvoiceUserId(),"已送达出门单通知", "您发往"+selectShopNameByCd(invoice.getToWsCd())+"的出门单收货有问题，请联系该工地的项目经理", invoice.getApprovalUserCd());
                }
            }
            i += dinvoiceDaoImpl.updateByEntity(invoice);
        }
        
        if(i!=0){
         // 添加日志
            OperLogUtil.insertMobileOperLog(invoiceId, "",PublicDict.MODEL_SENDMETRAL,
            "发料管理", PublicDict.OPER_LOG_EVENT_UPDATE, "更新", "手机端修改收货状态", "更新成功",
            "手机端确认发车", "d_invoice");
            return "0";
        }else return "1";
    }
    
    /**
     * 在确认收货的时候把出门单中产品详情集合添加到收货工地
     * @param flag
     * @param wsCd
     * @param materialNm
     * @param totalAmount
     * @param modelNo
     * @return INT  数据库影响的条数
     */
    public int workItemDeal(String invoiceId){
        int i=0;
        DInvoiceItem item = new DInvoiceItem();
        DInvoice invoice = new DInvoice();
        item.setInvoiceId(invoiceId);
        invoice.setInvoiceId(invoiceId);
       List<DInvoiceItem> itemList = dinvoiceItemDaoImpl.selectByEntity(item);//根据出门单id得到出门单产品详情集合
       String wsCd =  dinvoiceDaoImpl.selectOneByEntity(invoice).getToWsCd();//根据出门单id得到出门单收货工地id
       MWorkshop op = new MWorkshop();
       op.setWsCd(wsCd);
       String wsNm = mworkshopDaoImpl.selectOneByEntity(op).getWsNm();//根据收货工地id得到收货工地名称
       
       for(DInvoiceItem it:itemList ){
          i+=mworkshopTransServiceImpl.workItemDeal("to", wsNm, it.getCategoryNm(), it.getSendAmount(), it.getModelNo());
       }
        return i;
    }
    
    /**
     * 发货员更新备注
     * @param planId
     * @param memo
     * @return
     */
    public String updateMemo(String planId,String memo){
        DDeliveryPlan entity = new DDeliveryPlan();
        entity.setPlanId(planId);
        entity.setMemo(memo);
        int i = ddeliveryPlanDaoImpl.updateByEntity(entity);
        if(i!=0){
         // 添加日志
            OperLogUtil.insertMobileOperLog(planId, "",PublicDict.MODEL_SENDMETRAL,
            "发料管理", PublicDict.OPER_LOG_EVENT_UPDATE, "更新", "手机端更新出门单备注", "更新成功",
            "手机端更新出门单备注", "d_invoice");
            return "0";
        }else return "1";
    }
    /**
     * 审批项目经理或者审批部长处理待审批发料计划
     * @param userId 用户id
     * @param planId 计划id
     * @param status
     * @param memo 备注
     * @return
     */
    public String disposePlan(String userId,String planId,String status,String memo){
        int i = 0;
       DDeliveryPlan entity = new DDeliveryPlan();
       entity.setPlanId(planId);
       entity = ddeliveryPlanDaoImpl.selectOneByEntity(entity);
       if(entity!=null){
           String msgUserId="";
           if(StringUtils.isNotEmpty(entity.getVerifiedSiteUserCd())&&userId.equals(entity.getVerifiedSiteUserCd())){//如果当前用户是项目经理
               entity.setVerifiedSiteStatus(status);
               entity.setVerifiedSiteMemo(memo);
               entity.setVerifiedSiteTm(new Date());
               msgUserId=entity.getVerifiedSiteUserCd();
               
           }else if(StringUtils.isNotEmpty(entity.getVerifiedHeadUserCd())&&userId.equals(entity.getVerifiedHeadUserCd())){//如果当前用户是审批部长
               entity.setVerifiedHeadStatus(status);
               entity.setVerifiedHeadMemo(memo);
               entity.setVerifiedHeadTm(new Date());
               msgUserId=entity.getVerifiedHeadUserCd();
           }
           if("4".equals(status)){//当前审批意见为不同意
               entity.setStatusCd("2");
             //发送通知消息
               pmsMessageTopicProducer.sendTopic(entity.getCreateUserCd(),"已审批发料计划通知", "您上传的发往"+selectShopNameByCd(entity.getToWsCd())+"工地的计划表有问题，请修改后重新上传。", msgUserId);
           }else if("3".equals(entity.getVerifiedSiteStatus())&&"3".equals(entity.getVerifiedHeadStatus())){//如果本条数据中审批项目经理与审批部长都同意了则把本
               entity.setStatusCd("1");        
             //发送通知消息
               pmsMessageTopicProducer.sendTopic(entity.getCreateUserCd(),"已审批发料计划通知", "您上传的发往"+selectShopNameByCd(entity.getToWsCd())+"工地的计划表已审批，请及时下发。", msgUserId);//本条数据置为可下发
           }
           entity.setUpdateTm(new Date());
          i = ddeliveryPlanDaoImpl.updateByEntity(entity);
       }
       if(i!=0){
        // 添加日志
           OperLogUtil.insertMobileOperLog(planId, userId,PublicDict.MODEL_SENDMETRAL,
           "发料管理", PublicDict.OPER_LOG_EVENT_UPDATE, "更新", "手机端审批发料计划", "更新成功",
           "手机端审批发料计划", "d_delivery_plan");
           return "0";
       }else return "1";
    }
    
    /**
     * 根据userId查找回复表里对应已经完成的发料计划
     */
    public List<DDeliveryPlan> selectPlanFromMapping(DDeliveryPlan entity){
       return ddeliveryPlanDaoImpl.selectPlanFromMapp(entity);
    }
   /**
    * 根据工地cd查询工地名称
    */
    public String selectShopNameByCd(String shopCd){
    MWorkshop shop = new MWorkshop();
    shop.setWsCd(shopCd);
    shop = mworkshopDaoImpl.selectOneByEntity(shop);
    if(shop!=null){
        return shop.getWsNm();
    }return "";
    }
    
}
