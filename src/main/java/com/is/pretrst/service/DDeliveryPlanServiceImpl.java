package com.is.pretrst.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.struts2.ServletActionContext;
import org.icepdf.core.exceptions.PDFException;
import org.icepdf.core.exceptions.PDFSecurityException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.base.mybatis.Page;
import com.is.ggkz.dao.GgkzUserInfoDaoImpl;
import com.is.ggkz.entity.GgkzUserInfo;
import com.is.ggkz.entity.query.GgkzUserInfoQuery;
import com.is.mq.producer.PmsMessageTopicProducer;
import com.is.pretrst.dao.DDeliveryItemDaoImpl;
import com.is.pretrst.dao.DDeliveryPlanDaoImpl;
import com.is.pretrst.dao.DRecordReplyDaoImpl;
import com.is.pretrst.dao.DRecordUserMappingDaoImpl;
import com.is.pretrst.dao.MWorkshopDaoImpl;
import com.is.pretrst.entity.DDeliveryItem;
import com.is.pretrst.entity.DDeliveryPlan;
import com.is.pretrst.entity.DRecordReply;
import com.is.pretrst.entity.DRecordUserMapping;
import com.is.pretrst.entity.MWorkshop;
import com.is.pretrst.entity.query.DDeliveryPlanQuery;
import com.is.pretrst.entity.query.DRecordUserMappingQuery;
import com.is.utils.DateUtils;
import com.is.utils.PublicDict;
import com.is.utils.StringUtils;
import com.is.utils.date.DateUtil;
import com.is.utils.excelReader.ExcelReader;
import com.is.utils.excelToJpg.JOD4DocPDFFactory;
import com.is.utils.excelToJpg.Pdf2Image;
import com.is.utils.keyUtils.KeyGen;
import com.is.utils.sysOperLog.OperLogUtil;

/**
 * 
 * <p>文件名称: DDeliveryPlanService.java</p>
 * <p>文件描述: 发料计划信息和发料产品信息的保存及把上传的excel转换在jpg的类</p>
 * <p>版权所有: 版权所有(C)2013-2020</p>
 * <p>公   司: IS软件事业部</p>
 * <p>内容摘要: </p>
 * <p>其他说明: </p>
 * <p>完成日期：2013-9-9</p>
 * <p>修改记录0：无</p>
 * @version 1.0
 * @author  
 */
@Component
@Transactional
public class DDeliveryPlanServiceImpl
{
    @SuppressWarnings("unused")
    private static Logger             logger = LoggerFactory.getLogger(DDeliveryPlan.class);

    private DDeliveryPlanDaoImpl      ddeliveryPlanDaoImpl;

    private DDeliveryItemDaoImpl      ddeliveryItemDaoImpl;

    private MWorkshopDaoImpl          mworkshopDaoImpl;

    private GgkzUserInfoDaoImpl       ggkzUserInfoDaoImpl;

    private DRecordUserMappingDaoImpl drecordUserMappingDaoImpl;
    @Autowired
    private DInvoiceServiceImpl       dinvoiceServiceImpl;
    
    private DRecordReplyDaoImpl       drecordReplyDaoImpl;

    @Autowired
    private PmsMessageTopicProducer   pmsMessageTopicProducer;

    @Autowired
    public void setdDeliveryPlanDao(DDeliveryPlanDaoImpl ddeliveryPlanDaoImpl)
    {
        this.ddeliveryPlanDaoImpl = ddeliveryPlanDaoImpl;
    }

    @Autowired
    public void setMworkshopDao(MWorkshopDaoImpl mworkshopDaoImpl)
    {
        this.mworkshopDaoImpl = mworkshopDaoImpl;
    }

    @Autowired
    public void setDdeliveryItemDaoImpl(DDeliveryItemDaoImpl ddeliveryItemDaoImpl)
    {
        this.ddeliveryItemDaoImpl = ddeliveryItemDaoImpl;
    }

    @Autowired
    public void setGgkzUserInfoDaoImpl(GgkzUserInfoDaoImpl ggkzUserInfoDaoImpl)
    {
        this.ggkzUserInfoDaoImpl = ggkzUserInfoDaoImpl;
    }

    @Autowired
    public void setDrecordUserMappingDaoImpl(DRecordUserMappingDaoImpl drecordUserMappingDaoImpl)
    {
        this.drecordUserMappingDaoImpl = drecordUserMappingDaoImpl;
    }

    @Autowired
    public void setDrecordReplyDaoImpl(DRecordReplyDaoImpl drecordReplyDaoImpl)
    {
        this.drecordReplyDaoImpl = drecordReplyDaoImpl;
    }

    /**
     * 根据工作地点名称查找工作地点cd与项目经理名称
     * @param wsNm
     * @return [0]工作地点cd [1]项目经理名称
     */
    public String[] getWsCdByWsNm(String wsNm)
    {
        MWorkshop entity = new MWorkshop();
        String[] shopMsg = new String[3];
        entity.setWsNm(wsNm);
        entity = mworkshopDaoImpl.selectOneByEntity(entity);
        shopMsg[0] = entity.getWsCd();//工地CD
        shopMsg[2] = entity.getManagerUserId();//工地负责人CD
        shopMsg[1] = this.getWorkShopName(wsNm);//工地负责人Name
        return shopMsg;

    }

    /**
     * 根据工地名称查询负责人名称
     * @param wsCd
     * @return
     */
    public String getWorkShopName(String wsCd)
    {
        return mworkshopDaoImpl.getShopMangeUserName(wsCd);
    }

    /**
     * 工地是否存在
     * @param shop
     * @return
     */
    public int shopIsEx(String wsNm)
    {
        MWorkshop shop = new MWorkshop();
        shop.setWsNm(wsNm);
        shop = mworkshopDaoImpl.selectOneByEntity(shop);
        if (shop == null)
        {
            return 0;//不存在
        }
        if (shop != null && StringUtils.isNotEmpty(shop.getEndTime().toString()))
        {
            Calendar c = Calendar.getInstance();
            Calendar c2 = Calendar.getInstance();
            c2.setTime(shop.getEndTime());
            if (c.getTimeInMillis() > c2.getTimeInMillis())
            {
                return 2;//过期了
            }
        }
        return 1;

    }

    /**
     * 根据userName 查询userId
     * @param userName
     * @return
     */
    public String getUserCd(String userName)
    {
        GgkzUserInfoQuery entityQuery = new GgkzUserInfoQuery();
        GgkzUserInfo entity = new GgkzUserInfo();
        entityQuery.setName(userName);
        entity = ggkzUserInfoDaoImpl.selectByEntity(entityQuery).get(0);
        if (entity != null && StringUtils.isNotEmpty(entity.getUserId()))
        {
            return entity.getUserId();
        }
        return "";
    }

    /**
     * 根据userid 查询userName
     * @param userName
     * @return
     */
    public String getUserName(String userId)
    {
        GgkzUserInfoQuery entityQuery = new GgkzUserInfoQuery();
        GgkzUserInfo entity = new GgkzUserInfo();
        entityQuery.setUserId(userId);
        entity = ggkzUserInfoDaoImpl.selectByEntity(entityQuery).get(0);
        if (entity != null && StringUtils.isNotEmpty(entity.getName()))
        {
            return entity.getName();
        }
        return "";
    }

    /**
     * 保存发料计划信息与产品集合
     * @param DDeliveryPlan 发料计划对象 
     * @return int 成功返回1
     */
    public int insert(DDeliveryPlan entity)
    {
        int i = 0;
        i = ddeliveryPlanDaoImpl.insert(entity);
        i += this.insertManyItemInfo(entity.getDdeliveryItemList());
        if (i > 0)
        {
            // 添加日志
            OperLogUtil.insertOperLog(entity.getPlanId(), PublicDict.MODEL_SENDMETRAL, "发料管理", PublicDict.OPER_LOG_EVENT_ADD, "添加", "添加发料计划信息",
                    "添加成功", "添加发料计划", "d_delivery_plan");
            //发送通知消息
            pmsMessageTopicProducer.sendTopic(entity.getVerifiedHeadUserCd(), "发料计划待审批通知", getUserName(entity.getCreateUserCd())
                    + "上传了一条出门单数据，请您及时审批", entity.getCreateUserCd());
            pmsMessageTopicProducer.sendTopic(entity.getVerifiedSiteUserCd(), "发料计划待审批通知", getUserName(entity.getCreateUserCd())
                    + "上传了一条出门单数据，请您及时审批", entity.getCreateUserCd());

        }
        return i;
    }

    /**
     * 保存发料产品信息
     * @param DDeliveryItem 发料计划对象 
     * @return int 成功返回相应的操作行数
     */
    public int insertManyItemInfo(List<DDeliveryItem> ddeliveryItemList)
    {
        //return  ddeliveryItemDaoImpl.insertManyItemInfo(ddeliveryItemList);
        int i = 0;
        for (DDeliveryItem item : ddeliveryItemList)
        {
            i += ddeliveryItemDaoImpl.insert(item);

        }
        if (i > 0)
        {
            // 添加日志
            OperLogUtil.insertOperLog(ddeliveryItemList.get(0).getItemId(), PublicDict.MODEL_SENDMETRAL, "发料管理", PublicDict.OPER_LOG_EVENT_ADD, "添加",
                    "添加发料计划产品明细", "添加成功", "添加发料计划产品明细", "d_delivery_item");
        }
        return i;
    }

    /**
     * 分页查询 发料计划列表
     * @param queryEntity
     * @return page
     */
    @Transactional(readOnly = true)
    public Page pageQuery(DDeliveryPlanQuery queryEntity)
    {
        return ddeliveryPlanDaoImpl.pageQuery("DDeliveryPlan.selectByPage", queryEntity);
    }

    /**
     * 查找一个发料对象
     * @param entity
     * @return DDeliveryPlan 
     */
    public DDeliveryPlan selectOneByEntity(DDeliveryPlan entity)
    {
        entity.setDeleteCd("0");
        return ddeliveryPlanDaoImpl.selectOneByEntity(entity);
    }

    /**
     * 根据条件更新对象
     * @param entity
     * @return
     */
    public int updateByEntity(DDeliveryPlan entity)
    {
        entity.setUpdateTm(new Date());
        return ddeliveryPlanDaoImpl.updateByEntity(entity);
    }

    /**
     * 处理可下发发料计划
     */
    public int waitSendEdit(DDeliveryPlan entity, String mappingUserIds)
    {
        int i = 0;
        entity.setDownSendTm(new Date());//下发时间
        i = this.updateByEntity(entity);
        DRecordUserMapping drecordUserMapping = new DRecordUserMapping();
        drecordUserMapping.setRecordId(entity.getPlanId());
        i += drecordUserMappingDaoImpl.deleteByEntity(drecordUserMapping);
        if (!"".equals(mappingUserIds))
        {
            String[] mappingids = mappingUserIds.split(",");//其他领导id
            if (mappingids.length != 0)
            {
                for (int j = 0; j < mappingids.length; j++)
                {
                    drecordUserMapping.setRecordType("0");//发料计划
                    drecordUserMapping.setUpdateTm(new Date());
                    drecordUserMapping.setDeleteCd("0");
                    drecordUserMapping.setCreateTm(new Date());
                    drecordUserMapping.setLevelFlg("0");//备用
                    drecordUserMapping.setMappingUserCd(mappingids[j]);
                    drecordUserMapping.setMappingId(KeyGen.getCommonKeyGen(PublicDict.D_RECORD_USER_MAPPING));
                    i += drecordUserMappingDaoImpl.insert(drecordUserMapping);
                }
            }
        }
        if (i > 0)
        {
            // 添加日志
            OperLogUtil.insertOperLog(entity.getPlanId(), PublicDict.MODEL_SENDMETRAL, "发料管理", PublicDict.OPER_LOG_EVENT_UPDATE, "修改", "处理可下发发料计划",
                    "处理成功", "处理可下发发料计划", "d_delivery_plan");
            //发送通知消息
            pmsMessageTopicProducer.sendTopic("post9", "发料管理下发通知", getUserName(entity.getCreateUserCd()) + "下发了一条出门单数据，请及时查看",
                    entity.getCreateUserCd());
            pmsMessageTopicProducer.sendTopic("post20", "发料管理下发通知", getUserName(entity.getCreateUserCd()) + "下发了一条出门单数据，请及时查看",
                    entity.getCreateUserCd());
            pmsMessageTopicProducer.sendTopic("post30", "发料管理下发通知", getUserName(entity.getCreateUserCd()) + "下发了一条出门单数据，请及时查看",
                    entity.getCreateUserCd());
            if (!"".equals(mappingUserIds))
            {
                String[] mappingids = mappingUserIds.split(",");//其他领导id
                for (int j = 0; j < mappingids.length; j++)
                {//给其他领导发通知消息
                    pmsMessageTopicProducer.sendTopic(mappingids[j], "发料管理下发通知", getUserName(entity.getCreateUserCd()) + "下发了一条出门单数据，请及时查看",
                            entity.getCreateUserCd());
                }
            }
        }
        return i;
    }

    /**
     * 覆盖发料计划,需要把原来的全部删除掉，重新添加
     * @param entity 
     * @return 操作的行数
     */
    public int reUpload(DDeliveryPlan entity)
    {
        int i = 0;
        if (this.deleteByEntity(entity) != 0)
        {//如果删除成功
            entity.setUpdateTm(new Date());
            i = ddeliveryPlanDaoImpl.insert(entity);
            i += this.insertManyItemInfo(entity.getDdeliveryItemList());
        }
        if (i > 0)
        {
            // 添加日志
            OperLogUtil.insertOperLog(entity.getPlanId(), PublicDict.MODEL_SENDMETRAL, "发料管理", PublicDict.OPER_LOG_EVENT_UPDATE, "修改", "重新上传发料计划",
                    "处理成功", "重新上传发料计划", "d_delivery_plan");
            //发送通知消息
            pmsMessageTopicProducer.sendTopic(entity.getVerifiedHeadUserCd(), "发料计划待审批通知", getUserName(entity.getCreateUserCd())
                    + "重新上传了一条出门单数据，请您及时审批", entity.getCreateUserCd());
            pmsMessageTopicProducer.sendTopic(entity.getVerifiedSiteUserCd(), "发料计划待审批通知", getUserName(entity.getCreateUserCd())
                    + "重新上传了一条出门单数据，请您及时审批", entity.getCreateUserCd());

        }
        return i;
    }

    /**
     * 根据planId删除发料计划与产品详情
     * @param entity
     * @return 操作的行数
     */
    public int deleteByEntity(DDeliveryPlan entity)
    {
        int i=0;
        DDeliveryPlan plan = new DDeliveryPlan();
        DDeliveryItem item = new DDeliveryItem();
        if(StringUtils.isNotEmpty(entity.getPlanId())){
            plan.setPlanId(entity.getPlanId());
            item.setPlanId(entity.getPlanId());
            plan =  ddeliveryPlanDaoImpl.selectOneByEntity(plan);
            if(plan!=null&&StringUtils.isNotEmpty(plan.getPlanImagePath())){
                String[]paths = plan.getPlanImagePath().split(",");
                for(String path:paths){
                    this.deleteFile(path);//删除系统目录下的图片 
                }
            }
            i = ddeliveryPlanDaoImpl.deleteByEntity(plan);//删除发料计划
            i += ddeliveryItemDaoImpl.deleteByEntity(item);//删除发料产品详情
            
        }
        if (i > 0)
        {
            // 添加日志
            OperLogUtil.insertOperLog(entity.getPlanId(), PublicDict.MODEL_SENDMETRAL, "发料管理", PublicDict.OPER_LOG_EVENT_DEL, "删除", "发料计划与产品详情",
                    "处理成功", "发料计划与产品详情", "d_delivery_plan,d_delivery_item");
        }
        return i;
    }

    /**
     * excel转图片 发料计划预览功能
     * @param filePath 源文件路径
     * @param path 系统根目录
     * @param fileType 文件类型
     * @return 
     * @throws PDFException
     * @throws PDFSecurityException
     * @throws IOException
     */
    public DDeliveryPlan excelToPlan(String filePath, String path, List<String[]> retList,String createUserNm) throws PDFException, PDFSecurityException, IOException
    {
        DDeliveryPlan ddeliveryPlan = new DDeliveryPlan();//计划表对象
        List<DDeliveryItem> deliveryItemList = new ArrayList<DDeliveryItem>();//计划表产品List
        String pdfPath = ServletActionContext.getServletContext().getRealPath("/") + "temp" + File.separator + DateUtil.getCurDateTimeMil() + ".pdf";//PDF的生成路径
        File inputFile = new File(filePath);
        File outputFile = new File(pdfPath);
        JOD4DocPDFFactory.docToPdfFactory(inputFile, outputFile, pdfPath);//※※调用工厂类的静态方法来执行转换//转成PDF
        List<String> imageNames = Pdf2Image.tranfer(pdfPath, path + PublicDict.JPGPATH, PublicDict.ZOOM);//把PDF转换成图片
        if(imageNames!=null && StringUtils.isNotEmpty(imageNames.get(0)) && "1".equals(imageNames.get(0))){
            ddeliveryPlan.setTitle("1");
            return ddeliveryPlan;
        }
        StringBuffer sb = new StringBuffer();//多个图片地址拼接上upload
        for(int i=0;i<imageNames.size();i++){
            if(i>0){
                sb.append(","+PublicDict.JPGPATH +imageNames.get(i)); 
            }else
                sb.append(PublicDict.JPGPATH +imageNames.get(i));
        }
        
        //----------解析文档-----------------
        if (retList != null && retList.size() != 0)
        {
            String toWs = retList.get(2)[0];//收货中心
            toWs = toWs.substring(toWs.indexOf("：") + 1).trim();
            String title = retList.get(0)[0];//标题
            String deliveryPlanTm = retList.get(1)[3];//发货时间
            deliveryPlanTm = deliveryPlanTm.substring(deliveryPlanTm.indexOf("：") + 1).replace(" ", "");
            String planId = retList.get(1)[6];//发运计划编号
            planId = planId.substring(planId.indexOf("：") + 1).trim();
            String unloadPlaceNm = retList.get(2)[4];//卸货地点
            String sellOrderCode = retList.get(1)[0];//销售订单号
            sellOrderCode = sellOrderCode.substring(sellOrderCode.indexOf("：") + 1).trim();
            unloadPlaceNm = unloadPlaceNm.substring(unloadPlaceNm.indexOf("：") + 1).trim();
            ddeliveryPlan.setCreateUserCd(this.getUserCd(createUserNm));
            String[] wsMsg = this.getWsCdByWsNm(toWs);
            ddeliveryPlan.setToWsNm(toWs);//工地名称
            ddeliveryPlan.setToWsCd(wsMsg[0]);//工地代码
            ddeliveryPlan.setTitle(title);
            ddeliveryPlan.setVerifiedSiteUserCd(wsMsg[2]);//工地项目经理CD
            ddeliveryPlan.setVerifiedSiteUserNm(wsMsg[1]);//工地项目经理名称
            ddeliveryPlan.setPlanImagePath(sb.toString());//计划表图片位置
            ddeliveryPlan.setDeliveryPlanTm(DateUtils.stringToDate(deliveryPlanTm, "yyyy年mm月dd日"));
            ddeliveryPlan.setSellOrderCode(sellOrderCode);
            ddeliveryPlan.setPlanId(planId);
            ddeliveryPlan.setUnloadPlaceNm(unloadPlaceNm);

            //封装产品信息
            for (int i = 3; i < retList.size(); i++)
            {//从第四行开始
                String[] row = retList.get(i);
                if (StringUtils.isNum(row[0].trim()))
                {//如果第一列编号为数字的，则为产品

                    DDeliveryItem ddeliveryItem = new DDeliveryItem();
                    //for(int j=0;j<9;j++){
                    if (!"".equals(row[1]) && row[1] != null)
                    {
                        ddeliveryItem.setPlanId(ddeliveryPlan.getPlanId());
                        ddeliveryItem.setItemId(KeyGen.getCommonKeyGen(PublicDict.DELIVERY_ITEM));//物品主键
                        ddeliveryItem.setItemNo((int) Float.parseFloat(row[0].trim()));//序号
                        ddeliveryItem.setCategoryNm(row[1]);//货物名称
                        ddeliveryItem.setMaterialNm(row[2]);//材质
                        ddeliveryItem.setModelNo(row[3]);//规格型号
                        ddeliveryItem.setUnit(row[4]);//单位
                        ddeliveryItem.setSendAmount(new BigDecimal(row[5]).intValue());//发货数量
                        ddeliveryItem.setUsePlace(row[6]);//使用位置 
                        ddeliveryItem.setMemo(row[7]);//备注
                        ddeliveryItem.setStatusCd("0");
                        //  }
                        deliveryItemList.add(ddeliveryItem);
                    }
                }
            }
            ddeliveryPlan.setDdeliveryItemList(deliveryItemList);
        }
        return ddeliveryPlan;
    }

    /**
     * 获得excel中的数据
     * @param filePath
     * @param fileType
     * @return
     */
    public List<String[]> analyseExcel(InputStream is, String fileType)
    {
        List<String[]> retList = new ArrayList<String[]>();//存放excel内容
        retList = ExcelReader.getFileValue(is, fileType);//获得excel文件中的内容
        return retList;
    }

    /**
     * 获得所有发货员的id
     * @return List<String>
     */
    public List<String> getAllDeliveryStaffCd()
    {
        List<String> userIdList = new ArrayList<String>();
        List<GgkzUserInfo> infoList = new ArrayList<GgkzUserInfo>();
        GgkzUserInfoQuery user = new GgkzUserInfoQuery();
        user.setPost("9");
        infoList = ggkzUserInfoDaoImpl.selectByEntity(user);
        user = new GgkzUserInfoQuery();
        user.setPost("20");
        infoList.addAll(ggkzUserInfoDaoImpl.selectByEntity(user));
        user = new GgkzUserInfoQuery();
        user.setPost("30");
        infoList.addAll(ggkzUserInfoDaoImpl.selectByEntity(user));
        user = new GgkzUserInfoQuery();
        for (GgkzUserInfo info : infoList)
        {
            if (info != null && StringUtils.isNotEmpty(info.getUserId()))
            {
                userIdList.add(info.getUserId());
            }
        }
        return userIdList;
    }

    /**
     * 根据查询条件查询计划
     * @param entity
     * @return
     */
    public List<DDeliveryPlan> selectByEntity(DDeliveryPlan entity)
    {
        return ddeliveryPlanDaoImpl.selectByEntity(entity);
    }

    /**
     * 判断当前登陆人是否有已下发计划表的回复权限
     * @param plan 计划表对象
     * @param userCd 当前登陆人id
     * @return 1:可以回复  0:不可以回复
     */
    public String isReply(DDeliveryPlan plan, String userCd)
    {
        //查询是否是其它领导
        DRecordUserMappingQuery mapp = new DRecordUserMappingQuery();
        mapp.setRecordType("0");//类型为计划表
        mapp.setMappingUserCd(userCd);
        mapp.setRecordId(plan.getPlanId());
        List<DRecordUserMapping> mapplist = drecordUserMappingDaoImpl.selectByEntity(mapp);
        if (mapplist != null && mapplist.size() > 0)
        {
            return "1";
        }

        if (userCd.equals(plan.getCreateUserCd()))
        {//如果是本条数据的创建者
            return "1";
        }

        if (userCd.equals(plan.getVerifiedSiteUserCd()))
        {//如果是本条数据的项目经理
            return "1";
        }

        if (userCd.equals(plan.getVerifiedHeadUserCd()))
        {//如果是本条数据的审批部长
            return "1";
        }

        GgkzUserInfo info = new GgkzUserInfo();

        info.setUserId(userCd);
        info = ggkzUserInfoDaoImpl.selectOneByEntity(info);
        if (info != null && StringUtils.isNotEmpty(info.getPost()))
        {//如果当前登陆人就发货员
            if ("9".equals(info.getPost()))
            {
                return "1";
            }

            if ("20".equals(info.getPost()))
            {
                return "1";
            }

            if ("30".equals(info.getPost()))
            {
                return "1";
            }

        }

        return "0";

    }
    /**
     * 删除计划表信息
     * @param planId
     * @return
     */
    public int deletePlan(DDeliveryPlan p){
        int i=0;
        DRecordReply reply = new DRecordReply();
        DRecordUserMapping ping = new DRecordUserMapping();
        reply.setRecordId(p.getPlanId());
        ping.setRecordId(p.getPlanId());
        i+=this.deleteByEntity(p);//删除计划表与计划产品详情
        i+=dinvoiceServiceImpl.deleteDinvoiceByPlanId(p.getPlanId());//删除出门单与出门单产品详情和图片
        i+=drecordReplyDaoImpl.deleteByEntity(reply);//删除计划表回复内容
        i+=drecordUserMappingDaoImpl.deleteByEntity(ping);//删除计划表下发时关联的其他领导
        
        return i;
    }
    
    
    /**
     * 删除图片文件
     * @param filePath 图片名称
     */
    public void  deleteFile(String filePath){
        String path = ServletActionContext.getServletContext().getRealPath("/");// 系统根目录
        File file = new File(path + filePath);//删除图片
        // 路径为文件且不为空则进行删除
        if (file.isFile() && file.exists())
        {
            file.delete();
        }
    }
}
