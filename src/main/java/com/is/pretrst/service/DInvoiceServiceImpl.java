package com.is.pretrst.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.impl.cookie.DateParseException;
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
import com.is.pretrst.dao.DInvoiceDaoImpl;
import com.is.pretrst.dao.DInvoiceItemDaoImpl;
import com.is.pretrst.dao.MWorkshopDaoImpl;
import com.is.pretrst.entity.DInvoice;
import com.is.pretrst.entity.DInvoiceImage;
import com.is.pretrst.entity.DInvoiceItem;
import com.is.pretrst.entity.MWorkshop;
import com.is.pretrst.entity.query.DInvoiceQuery;
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
 * @ClassName: DInvoiceServiceImpl
 * @Description: DInvoice表对应的服务类
 * @author 
 * @date 2013-09-10 10:26:13 *
 */
@Component
@Transactional
public class DInvoiceServiceImpl
{
    @SuppressWarnings("unused")
    private static Logger       logger = LoggerFactory.getLogger(DInvoice.class);

    private DInvoiceDaoImpl     dinvoiceDaoImpl;

    private MWorkshopDaoImpl    mworkshopDaoImpl;

    private GgkzUserInfoDaoImpl ggkzUserInfoDaoImpl;

    private DInvoiceItemDaoImpl dInvoiceItemDaoImpl;

    private DInvoiceImageServiceImpl dInvoiceImageServiceImpl;

    @Autowired
    public void setMworkshopDao(MWorkshopDaoImpl mworkshopDaoImpl)
    {
        this.mworkshopDaoImpl = mworkshopDaoImpl;
    }

    @Autowired
    public void setGgkzUserInfoDaoImpl(GgkzUserInfoDaoImpl ggkzUserInfoDaoImpl)
    {
        this.ggkzUserInfoDaoImpl = ggkzUserInfoDaoImpl;
    }

    @Autowired
    public void setDinvoiceDaoImpl(DInvoiceDaoImpl dinvoiceDaoImpl)
    {
        this.dinvoiceDaoImpl = dinvoiceDaoImpl;
    }

    @Autowired
    public void setdInvoiceItemDaoImpl(DInvoiceItemDaoImpl dInvoiceItemDaoImpl)
    {
        this.dInvoiceItemDaoImpl = dInvoiceItemDaoImpl;
    }
    
    @Autowired
    public void setdInvoiceImageServiceImpl(DInvoiceImageServiceImpl dInvoiceImageServiceImpl)
    {
        this.dInvoiceImageServiceImpl = dInvoiceImageServiceImpl;
    }

    /**
     * 分页查询出门单列表
     * @param entityQuery
     * @return Page
     */
    public Page pageQuery(DInvoiceQuery queryEntity)
    {
        return dinvoiceDaoImpl.pageQuery("DInvoice.selectByPage", queryEntity);
    }

    /**
     * excel转图片 出门单预览功能
     * @param filePath 源文件路径
     * @param path 系统根目录
     * @param fileType 文件类型
     * @return
     * @throws DateParseException 
     * @throws IOException 
     * @throws PDFSecurityException 
     * @throws PDFException 
     */
    public DInvoice excelToInvoice(String filePath, String path, List<String[]> retList,String invoiceUserNm) throws PDFException, PDFSecurityException, IOException
    {
        DInvoice dinvoice = new DInvoice();//出门单对象
        List<DInvoiceItem> dinvoiceItemList = new ArrayList<DInvoiceItem>();//出门单产品List
        /*转换成图片 */
        String pdfPath = ServletActionContext.getServletContext().getRealPath("/") + "temp" + File.separator + DateUtil.getCurDateTimeMil() + ".pdf";//PDF的生成路径
        File inputFile = new File(filePath);
        File outputFile = new File(pdfPath);
        JOD4DocPDFFactory.docToPdfFactory(inputFile, outputFile, pdfPath);//※※调用工厂类的静态方法来执行转换//转成PDF
        List<String> imageNames = Pdf2Image.tranfer(pdfPath, path + PublicDict.JPGPATH, PublicDict.ZOOM);//把PDF转换成图片
        if(imageNames!=null && StringUtils.isNotEmpty(imageNames.get(0)) && "1".equals(imageNames.get(0))){
            dinvoice.setTitle("1");
            return dinvoice;
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
            String tcompanyNm = retList.get(3)[0];//运输公司名称
            tcompanyNm = tcompanyNm.substring(tcompanyNm.indexOf("：") + 1).trim();
            String tcompanyTel = retList.get(3)[2];//运输公司电话
            tcompanyTel = tcompanyTel.substring(tcompanyTel.indexOf("：") + 1).trim();
            String driver = retList.get(3)[4];//司机
            driver = driver.substring(driver.indexOf("：") + 1).trim();
            String driverTel = retList.get(3)[7];//司机电话
            driverTel = driverTel.substring(driverTel.indexOf("：") + 1).trim();
            String truckNum = retList.get(3)[6];//车牌号
            truckNum = truckNum.substring(truckNum.indexOf("：") + 1).trim();

            String unloadPlaceNm = retList.get(2)[4];//卸货地点
            String sellOrderCode = retList.get(1)[0];//销售订单号
            sellOrderCode = sellOrderCode.substring(sellOrderCode.indexOf("：") + 1).trim();
            unloadPlaceNm = unloadPlaceNm.substring(unloadPlaceNm.indexOf("：") + 1).trim();
            String[] wsMsg = this.getWsCdAnduserNm(toWs);//根据工地名称得到工地cd与负责人名称
            String[] wsCdAndwsNm = this.getWorkShopCdAndNm(invoiceUserNm);//根据制表人得到仓库cd与仓库名称
            dinvoice.setInvoiceId(KeyGen.getCommonKeyGen(PublicDict.DINVOICE_ID));
            dinvoice.setTitle(title);
            dinvoice.setSellOrderCode(sellOrderCode);
            dinvoice.setPlanId(planId);//
            dinvoice.setDeliveryTm(DateUtils.stringToDate(deliveryPlanTm, "yyyy年mm月dd日"));
            dinvoice.setTcompanyNm(tcompanyNm);
            dinvoice.setTcompanyTel(tcompanyTel);
            dinvoice.setDriver(driver);
            dinvoice.setDriverTel(driverTel);
            dinvoice.setTruckNum(truckNum);
            dinvoice.setToWsNm(toWs);//收货工地名称
            dinvoice.setToWsCd(wsMsg[0]);//收货工地代码
            dinvoice.setApprovalUserCd(wsMsg[2]);
            dinvoice.setInvoiceUserId(this.getUserCd(invoiceUserNm));//制表人id
            dinvoice.setDinvoiceImagePath(sb.toString());//出门单图片位置
            dinvoice.setFromWsCd(wsCdAndwsNm[0]);//制表人所在仓库id//或当前操作人？？
            dinvoice.setApprovalReqTm(new Date());

            //封装产品信息
            for (int i = 3; i < retList.size(); i++)
            {//从第四行开始
                String[] row = retList.get(i);
                if (StringUtils.isNum(row[0].trim()))
                {//如果第一列编号为数字的，则为产品

                    DInvoiceItem dInvoiceItem = new DInvoiceItem();
                    if (!"".equals(row[1]) && row[1] != null)
                    {
                        dInvoiceItem.setInvoiceId(dinvoice.getInvoiceId());
                        dInvoiceItem.setItemId(KeyGen.getCommonKeyGen(PublicDict.DINVOICE_ITEM_ID));//物品主键
                        dInvoiceItem.setItemNo((int) Float.parseFloat(row[0].trim()));//序号
                        dInvoiceItem.setCategoryNm(row[1]);//货物名称
                        dInvoiceItem.setMaterialNm(row[2]);//材质
                        dInvoiceItem.setModelNo(row[3]);//规格型号
                        dInvoiceItem.setUnit(row[4]);//单位
                        dInvoiceItem.setSendAmount(new BigDecimal(row[5]).intValue());//发货数量
                        dInvoiceItem.setUsePlace(row[6]);//使用位置 
                        dInvoiceItem.setMemo(row[7]);//备注
                        dInvoiceItem.setStatusCd("0");
                        dinvoiceItemList.add(dInvoiceItem);
                    }
                }
            }
            dinvoice.setDInvoiceItems(dinvoiceItemList);
        }
        return dinvoice;
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
     * 工地是否存在
     * @param shop
     * @return
     */
    public int shopIsEx(String wsNm)
    {
        MWorkshop shop = new MWorkshop();
        shop.setWsNm(wsNm);
        if (mworkshopDaoImpl.selectOneByEntity(shop) == null)
        {
            return 0;
        }
        else
        {
            return 1;
        }
    }

    /**
     * 根据工地名称查找工作cd与项目经理名称
     * @param wsNm
     * @return [0]工作地点cd [1]工地负责人名称  [2]工地负责人id
     */
    public String[] getWsCdAnduserNm(String wsNm)
    {
        MWorkshop entity = new MWorkshop();
        String[] shopMsg = new String[3];
        entity.setWsNm(wsNm);
        entity = mworkshopDaoImpl.selectOneByEntity(entity);
        if (entity != null)
        {
            shopMsg[0] = entity.getWsCd();//工地CD
            shopMsg[2] = entity.getManagerUserId();//工地负责人CD
            shopMsg[1] = this.getWorkShopName(wsNm);//工地负责人Name
        }
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
     * 根据负责人名称查询所在仓库编码及所在仓库名称
     * @param userName
     * @return 【0】：仓库CD;【1】：仓库名称
     */
    public String[] getWorkShopCdAndNm(String userName)
    {
        String[] wsCdAndwsNm = new String[2];
        String userCD = this.getUserCd(userName);
        MWorkshop shop = new MWorkshop();
        shop.setManagerUserId(userCD);
        shop = mworkshopDaoImpl.selectOneByEntity(shop);
        if (shop != null && StringUtils.isNotEmpty(shop.getWsCd()))
        {
            wsCdAndwsNm[0] = shop.getWsCd();
        }
        if (shop != null && StringUtils.isNotEmpty(shop.getWsNm()))
        {
            wsCdAndwsNm[1] = shop.getWsNm();
        }
        return wsCdAndwsNm;
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
     * 判断制表人是不是发货员或者发料员
     * @param userName
     * @return
     */
    public int isInvoiceUser(String userName)
    {
        GgkzUserInfoQuery entityQuery = new GgkzUserInfoQuery();
        GgkzUserInfo entity = new GgkzUserInfo();
        entityQuery.setName(userName);
        entity = ggkzUserInfoDaoImpl.selectByEntity(entityQuery).get(0);
        if (entity != null && StringUtils.isNotEmpty(entity.getPost()))
        {
            if ("9".equals(entity.getPost()) || "20".equals(entity.getPost()) || "30".equals(entity.getPost()) || "25".equals(entity.getPost()))
            {
                return 0;
            }
        }
        return 1;
    }

    /**
     * 根据条件查询一个出门单对象
     * @param entity
     * @return
     */
    public DInvoice selectOneByEntity(DInvoice entity)
    {
        return dinvoiceDaoImpl.selectOneByEntity(entity);
    }

    /**
     * 插入出门单对象
     * @param entity
     * @return
     */
    public int insert(DInvoice entity)
    {
        int i = 0;
        entity.setUpdateTm(new Date());
        i = dinvoiceDaoImpl.insert(entity);
        i += this.insertManyItemInfo(entity.getDInvoiceItems());
        if (i > 0)
        {
            // 添加日志
            OperLogUtil.insertOperLog(entity.getInvoiceId(), PublicDict.MODEL_SENDMETRAL, "发料管理", PublicDict.OPER_LOG_EVENT_ADD, "添加", "添加出门单信息",
                    "添加成功", "添加出门单信息", "d_invoice");
        }
        return i;
    }

    /**
     * 覆盖出门单与出门单产品集合
     * @param entity
     * @return
     */
    public int reUpload(DInvoice entity)
    {
        int i = 0;
        i = this.deleteByEntity(entity);//先删除
        if (i != 0)
        {
            i += dinvoiceDaoImpl.insert(entity);
            for (DInvoiceItem item : entity.getDInvoiceItems())
            {
                i += dInvoiceItemDaoImpl.insert(item);
            }
        }
        if (i > 0)
        {
            // 添加日志
            OperLogUtil.insertOperLog(entity.getInvoiceId(), PublicDict.MODEL_SENDMETRAL, "发料管理", PublicDict.OPER_LOG_EVENT_ADD, "添加", "添加出门单信息",
                    "添加成功", "添加出门单信息", "d_invoice");
        }
        return i;

    }

    /**
     * 重复上传时删除出门单对象与产品集合
     * @param entity
     * @return
     */
    public int deleteByEntity(DInvoice entity)
    {
        int i = 0;
        DInvoice invoice = new DInvoice();
        DInvoiceItem item = new DInvoiceItem();
        DInvoiceImage image = new DInvoiceImage();
        invoice.setPlanId(entity.getPlanId());
        invoice.setDeliveryTm(entity.getDeliveryTm());
        invoice.setToWsCd(entity.getToWsCd());
        invoice.setInvoiceUserId(entity.getInvoiceUserId());
        invoice = dinvoiceDaoImpl.selectOneByEntity(invoice);//根据发货时间与收货工地查询出门单
        if (invoice != null)
        {//如果出门单对象存在
            item.setInvoiceId(invoice.getInvoiceId());
            image.setInvoiceId(invoice.getInvoiceId());
            i = dinvoiceDaoImpl.deleteByEntity(invoice);
            i += dInvoiceItemDaoImpl.deleteByEntity(item);//删除产品集合
            i += dInvoiceImageServiceImpl.deleteByEntity(image);//删除出门单图片
            if(StringUtils.isNotEmpty(invoice.getDinvoiceImagePath())){
                String [] paths = invoice.getDinvoiceImagePath().split(",");
                for(String path:paths){
                    this.deleteFile(path);//删除出门单缩略图
                }
            }
         }
        if (i > 0)
        {
            // 添加日志
            OperLogUtil.insertOperLog(entity.getInvoiceId(), PublicDict.MODEL_SENDMETRAL, "发料管理", PublicDict.OPER_LOG_EVENT_DEL, "删除", "删除出门单信息",
                    "删除成功", "删除出门单信息", "d_invoice");
        }
        return i;
    }

    /**
     * 批量增加出门单产品集合
     * @param items
     * @return
     */
    public int insertManyItemInfo(List<DInvoiceItem> items)
    {
        int i = dInvoiceItemDaoImpl.insertManyItemInfo(items);
        if (i > 0)
        {
            // 添加日志
            OperLogUtil.insertOperLog(items.get(0).getInvoiceId(), PublicDict.MODEL_SENDMETRAL, "发料管理", PublicDict.OPER_LOG_EVENT_ADD, "添加",
                    "添加出门单产品详情", "添加成功", "添加出门单产品详情", "d_invoice_item");
        }
        return i;
    }
    /**
     * 根据计划表id删除所有出门单相关数据，包括出门单，出门单产品 出门单图片
     * @param planId 计划表id
     * @return  INT 操作影响的条数
     */
    public int deleteDinvoiceByPlanId(String planId){
        int i=0;
        DInvoice invoice = new DInvoice();
        DInvoiceItem item = new DInvoiceItem();
        DInvoiceImage image = new DInvoiceImage();
        invoice.setPlanId(planId);
        List<DInvoice> inList = dinvoiceDaoImpl.selectByEntity(invoice);
        if(inList!=null){
            for(DInvoice vce:inList){
                item.setInvoiceId(vce.getInvoiceId());
                image.setInvoiceId(vce.getInvoiceId());
                i = dinvoiceDaoImpl.deleteByEntity(vce);//删除出门单数据
                if(StringUtils.isNotEmpty(vce.getDinvoiceImagePath())){
                    String []pahts = vce.getDinvoiceImagePath().split(",");
                    for(String path:pahts){
                        this.deleteFile(path);//删除出门单缩略图
                    }
                }
                i += dInvoiceItemDaoImpl.deleteByEntity(item);//删除出门单产品集合
                i += dInvoiceImageServiceImpl.deleteByEntity(image);//删除出门单图片
            }
        }
        return i;
    }
    /**
     * 根据出门单id删除所有出门单相关数据，包括出门单，出门单产品 出门单图片
     * @param invoiceId  出门单id
     * @return  INT 操作影响的条数
     */
    public int deleteDinvoiceByInvoiceId(String invoiceId){
        int i=0;
        DInvoice invoice = new DInvoice();
        DInvoiceItem item = new DInvoiceItem();
        DInvoiceImage image = new DInvoiceImage();
        invoice.setInvoiceId(invoiceId);
        item.setInvoiceId(invoiceId);
        image.setInvoiceId(invoiceId);
        invoice = this.selectOneByEntity(invoice);
        if(invoice!=null){
            if(StringUtils.isNotEmpty(invoice.getDinvoiceImagePath())){
                String[]paths = invoice.getDinvoiceImagePath().split(",");
                for(String path:paths){
                    this.deleteFile(path);//删除出门单缩略图
                }
            }
            i = dinvoiceDaoImpl.deleteByEntity(invoice);//删除出门单数据
        }
        i += dInvoiceItemDaoImpl.deleteByEntity(item);//删除出门单产品集合
        i += dInvoiceImageServiceImpl.deleteByEntity(image);//删除出门单图片
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
