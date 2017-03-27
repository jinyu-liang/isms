package com.is.pretrst.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.hibernate.tool.hbm2x.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.base.mybatis.BaseStruts2Action;
import com.base.mybatis.Page;
import com.is.pretrst.entity.DInvoice;
import com.is.pretrst.entity.DInvoiceImage;
import com.is.pretrst.entity.DInvoiceItem;
import com.is.pretrst.entity.query.DInvoiceQuery;
import com.is.pretrst.service.DInvoiceImageServiceImpl;
import com.is.pretrst.service.DInvoiceServiceImpl;
import com.is.sys.entity.SysAttach;
import com.is.sys.service.SysAttachServiceImpl;

/**
 *
 * @ClassName: DInvoiceAction
 * @Description: DInvoice表对应的Action类
 * @author 
 * @date 2013-09-10 10:26:11 *
 */
@Namespace("/rst")
@SuppressWarnings("unchecked")
public class DInvoiceAction extends BaseStruts2Action
{
    private static final long        serialVersionUID = 1L;

    private static final Logger      LOGGER           = LoggerFactory.getLogger(DInvoiceAction.class);

    private DInvoice                 entity;

    public DInvoiceQuery             queryEntity;

    private DInvoiceServiceImpl      dinvoiceServiceImpl;

    private DInvoiceImageServiceImpl dinvoiceImageServiceImpl;

    private Page                     invoicePage;

    private String                   fileIds;                                                          //附件id

    private String                   planWsCd;                                                          //计划表中收货工地CD

    private String                   status;                                                            //上传出门单的时候看计划表是否是已下发

    private List<String>             imageList;

    @Autowired
    private SysAttachServiceImpl     sysAttachServiceImpl;

    HttpSession                      session          = ServletActionContext.getRequest().getSession();

    public DInvoiceAction()
    {
        super();
        if (entity == null)
        {
            entity = new DInvoice();
        }
        if (queryEntity == null)
        {
            queryEntity = new DInvoiceQuery();
        }
    }

    public DInvoice getEntity()
    {
        return entity;
    }

    public void setEntity(DInvoice entity)
    {
        this.entity = entity;
    }

    public DInvoiceQuery getQueryEntity()
    {
        return queryEntity;
    }

    public void setQueryEntity(DInvoiceQuery queryEntity)
    {
        this.queryEntity = queryEntity;
    }

    public Page getInvoicePage()
    {
        return invoicePage;
    }

    public void setInvoicePage(Page invoicePage)
    {
        this.invoicePage = invoicePage;
    }

    public String getPlanWsCd()
    {
        return planWsCd;
    }

    public void setPlanWsCd(String planWsCd)
    {
        this.planWsCd = planWsCd;
    }

    public String getFileIds()
    {
        return fileIds;
    }

    public void setFileIds(String fileIds)
    {
        this.fileIds = fileIds;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public List<String> getImageList()
    {
        return imageList;
    }

    public void setImageList(List<String> imageList)
    {
        this.imageList = imageList;
    }

    @Override
    public String getWarnMessage()
    {
        return super.getWarnMessage();
    }

    @Override
    public String getMessage()
    {
        return super.getMessage();
    }

    @Override
    public String getInfoMessage()
    {
        return super.getInfoMessage();
    }

    @Autowired
    public void setDinvoiceImageServiceImpl(DInvoiceImageServiceImpl dinvoiceImageServiceImpl)
    {
        this.dinvoiceImageServiceImpl = dinvoiceImageServiceImpl;
    }

    @Autowired
    public void setDinvoiceServiceImpl(DInvoiceServiceImpl dinvoiceServiceImpl)
    {
        this.dinvoiceServiceImpl = dinvoiceServiceImpl;
    }

    public String list() throws Exception
    {
        page = dinvoiceServiceImpl.pageQuery(queryEntity);
        return "DInvoice/list";
    }

    /**
     * 预览出门单
     */
    public String preViewInvoice()
    {

        SysAttach attach = sysAttachServiceImpl.getAttachById(fileIds);
        String inputFilePath = attach.getAttachPath() + "/" + attach.getAttachId();// 要下载的文件名称
        String filetype = attach.getFileType();

        String path = ServletActionContext.getServletContext().getRealPath("/");//系统根目录，用于存入生成的图片用
        String fullInputFilePath = (path + inputFilePath).replace("/", "\\");//完整的附件存放路径
        InputStream is = null;
        try
        {
            File file = new File(fullInputFilePath);
            is = new FileInputStream(file);
        }
        catch (Exception e)
        {
            setInfoMessage("请重新上传附件");
            return "DInvoice/invoice_preView_err";
        }
        try
        {
            if (("xls").equals(filetype) || "xlsx".equals(filetype))
            {

                if (!"3".equals(status))
                {//判断所选计划是否已下发
                    setInfoMessage("请选中已下发的计划再上传出门单。");
                    return "DInvoice/invoice_preView_err";
                }
                List<String[]> retList = dinvoiceServiceImpl.analyseExcel(is, filetype);
                if (retList != null && retList.size() != 0)
                {
                    String toWs = retList.get(2)[0];//收货中心
                    String planId = retList.get(1)[6];//发运计划编号
                    String tcompanyNm = retList.get(3)[0];//运输公司名称
                    String tcompanyTel = retList.get(3)[2];//运输公司电话
                    String driver = retList.get(3)[4];//司机
                    String driverTel = retList.get(3)[7];//司机电话
                    String truckNum = retList.get(3)[6];//车牌号
                    String unloadPlaceNm = retList.get(2)[4];//卸货地点
                    String sellOrderCode = retList.get(1)[0];//销售订单号
                    String invoiceUserNm = "";//制表人名称
                    for (int i = 3; i < retList.size(); i++)//获得制表人名称
                    {//从第四行开始
                        String[] row = retList.get(i);
                        if(row[5].contains("制表人")){//如果这一行的第6列包含制表人这三个字
                            invoiceUserNm = row[5];//制表人名称
                            break;
                        }
                    }

                    //收货中心
                    if (StringUtils.isNotEmpty(toWs) && StringUtils.isNotEmpty(toWs.substring(toWs.indexOf("：") + 1).trim()))
                    {//收货中心存在
                        toWs = toWs.substring(toWs.indexOf("：") + 1).trim();
                        if (dinvoiceServiceImpl.shopIsEx(toWs) == 0)
                        {//收货中心是否写错了
                            setInfoMessage("收货中心'" + toWs + "'不存在。");
                            return "DInvoice/invoice_preView_err";
                        }
                    }
                    else
                    {//收货中心不存在 
                        setInfoMessage("收货中心不存在。");
                        return "DInvoice/invoice_preView_err";
                    }

                    //发运计划编号 
                    if (StringUtils.isNotEmpty(planId) && StringUtils.isNotEmpty(planId.substring(planId.indexOf("：") + 1).trim()))
                    {//发运计划编号存在
                        planId = planId.substring(planId.indexOf("：") + 1).trim();
                        if (!entity.getPlanId().equals(planId))
                        {
                            setInfoMessage("所上传出门单的发运计划编号与计划表中的发运计划编号不一致。");
                            return "DInvoice/invoice_preView_err";
                        }
                    }
                    else
                    {//发运计划编号不存在
                        setInfoMessage("发运计划编号不存在。");
                        return "DInvoice/invoice_preView_err";
                    }
                    //运输公司名称
                    if (StringUtils.isNotEmpty(tcompanyNm) && StringUtils.isNotEmpty(tcompanyNm.substring(tcompanyNm.indexOf("：") + 1).trim()))
                    {
                        tcompanyNm = tcompanyNm.substring(tcompanyNm.indexOf("：") + 1).trim();
                    }
                    else
                    {//运输公司名称不存在
                        setInfoMessage("运输公司名称不存在。");
                        return "DInvoice/invoice_preView_err";
                    }
                    //运输公司电话
                    if (StringUtils.isNotEmpty(tcompanyTel) && StringUtils.isNotEmpty(tcompanyTel.substring(tcompanyTel.indexOf("：") + 1).trim()))
                    {
                        tcompanyTel = tcompanyTel.substring(tcompanyTel.indexOf("：") + 1).trim();
                    }
                    else
                    {//运输公司电话不存在
                        setInfoMessage("运输公司电话不存在。");
                        return "DInvoice/invoice_preView_err";
                    }
                    //司机
                    if (StringUtils.isNotEmpty(driver) && StringUtils.isNotEmpty(driver.substring(driver.indexOf("：") + 1).trim()))
                    {
                        driver = driver.substring(driver.indexOf("：") + 1).trim();
                    }
                    else
                    {
                        setInfoMessage("司机不存在。");
                        return "DInvoice/invoice_preView_err";
                    }
                    //司机电话  
                    if (StringUtils.isNotEmpty(driverTel) && StringUtils.isNotEmpty(driverTel.substring(driverTel.indexOf("：") + 1).trim()))
                    {
                        driverTel = driverTel.substring(driverTel.indexOf("：") + 1).trim();
                    }
                    else
                    {
                        setInfoMessage("司机电话不存在。");
                        return "DInvoice/invoice_preView_err";
                    }
                    //车牌号
                    if (StringUtils.isNotEmpty(truckNum) && StringUtils.isNotEmpty(truckNum.substring(truckNum.indexOf("：") + 1).trim()))
                    {
                        truckNum = truckNum.substring(truckNum.indexOf("：") + 1).trim();
                    }
                    else
                    {
                        setInfoMessage("车牌号不存在。");
                        return "DInvoice/invoice_preView_err";
                    }
                    //卸货地点  
                    if (StringUtils.isNotEmpty(unloadPlaceNm)
                            && StringUtils.isNotEmpty(unloadPlaceNm.substring(unloadPlaceNm.indexOf("：") + 1).trim()))
                    {
                        unloadPlaceNm = unloadPlaceNm.substring(unloadPlaceNm.indexOf("：") + 1).trim();
                    }
                    else
                    {
                        setInfoMessage("卸货地点不存在。");
                        return "DInvoice/invoice_preView_err";
                    }
                    //销售订单号 
                    if (StringUtils.isNotEmpty(sellOrderCode)
                            && StringUtils.isNotEmpty(sellOrderCode.substring(sellOrderCode.indexOf("：") + 1).trim()))
                    {
                        sellOrderCode = sellOrderCode.substring(sellOrderCode.indexOf("：") + 1).trim();
                    }
                    else
                    {
                        setInfoMessage("销售订单号不存在。");
                        return "DInvoice/invoice_preView_err";
                    }
                    //制表人 
                    if (StringUtils.isNotEmpty(invoiceUserNm)
                            && StringUtils.isNotEmpty(invoiceUserNm.substring(invoiceUserNm.indexOf("：") + 1).trim()))
                    {
                        invoiceUserNm = invoiceUserNm.substring(invoiceUserNm.indexOf("：") + 1).trim();
                        if (!sessionUser.getUsername().equals(invoiceUserNm))
                        {
                            setInfoMessage("请在制表人一栏填写自己的名字。");
                            return "DDeliveryPlan/plan_preView_err";
                        }
                        if (dinvoiceServiceImpl.isInvoiceUser(invoiceUserNm) == 1)
                        {
                            setInfoMessage("制表人'" + invoiceUserNm + "'不具有上传出门单的权限");
                            return "DInvoice/invoice_preView_err";
                        }
                    }
                    else
                    {
                        setInfoMessage("制表人名称不存在。");
                        return "DInvoice/invoice_preView_err";
                    }

                    entity = dinvoiceServiceImpl.excelToInvoice(fullInputFilePath, path, retList,invoiceUserNm);
                    if ("1".equals(entity.getTitle()))
                    {
                        setInfoMessage("您上传的出门单页数太多，请在打印预览-设置-页面-缩放-调整为(F): 1页宽   9页高");
                        return "DInvoice/invoice_preView_err";
                    }
                    session.setAttribute(entity.getInvoiceId() + "dinvoiceItemList", entity.getDInvoiceItems());//把产品集合放在session中
                }
                else
                {
                    setMessage("您上传的出门单格式不正确.");
                    return "DInvoice/invoice_preView_err";
                }
            }
            else
            {
                setMessage("请选用出门单模板.");
                return "DInvoice/invoice_preView_err";
            }
        }
        catch (Exception e)
        {
            LOGGER.debug("解析文件错误：[{}]", e);
            setInfoMessage("您上传的出门单格式不正确.");
            return "DInvoice/invoice_preView_err";
        }
        return "DInvoice/invoice_preView";
    }

    /**
     * 校验是否有重复的出门单（AJAX）（根据发货日期与收货工地来确定）
     * @throws IOException 
     */
    public String isExist() throws IOException
    {
        DInvoice invoice = new DInvoice();
        invoice.setToWsCd(entity.getToWsCd());
        invoice.setDeliveryTm(entity.getDeliveryTm());
        invoice.setInvoiceUserId(entity.getInvoiceUserId());
        invoice.setPlanId(entity.getPlanId());
        invoice = dinvoiceServiceImpl.selectOneByEntity(invoice);
        if (invoice != null && StringUtils.isNotEmpty(invoice.getStatusCd()))
        {
            if ("6".equals(invoice.getStatusCd()) || "0".equals(invoice.getStatusCd()) || "2".equals(invoice.getStatusCd()))
            {
                ServletActionContext.getResponse().getWriter().print(1);//有重复的
            }
            else
            {
                ServletActionContext.getResponse().getWriter().print(2);//有重复的并且是已经审批了的
            }

        }
        else
        {
            ServletActionContext.getResponse().getWriter().print(0);
        }
        return null;

    }

    /**
     * 覆盖上传操作
     * @return
     */
    public String reUpload()
    {
        entity.setDInvoiceItems((List<DInvoiceItem>) session.getAttribute(entity.getInvoiceId() + "dinvoiceItemList"));
        session.removeAttribute(entity.getInvoiceId() + "dinvoiceItemList");
        entity.setDeleteCd("0");//删除标识
        entity.setStatusCd("6");//刚上传
        entity.setUpdateTm(new Date());
        if (dinvoiceServiceImpl.reUpload(entity) != 0)
        {
            setMessage("上传成功");
        }
        else
        {
            setInfoMessage("上传失败");
        }
        return JSON_DATA;
    }

    /**
     * 增加
     * @return
     */
    public String uploadInvoice()
    {
        entity.setDInvoiceItems((List<DInvoiceItem>) session.getAttribute(entity.getInvoiceId() + "dinvoiceItemList"));
        session.removeAttribute(entity.getInvoiceId() + "dinvoiceItemList");
        entity.setDeleteCd("0");//删除标识
        entity.setStatusCd("6");//刚上传
        if (dinvoiceServiceImpl.insert(entity) != 0)
        {
            setMessage("上传成功");
        }
        else
        {
            setInfoMessage("上传失败");
        }
        return JSON_DATA;
    }

    /**
     * 查询出门单包含的图片
     */
    public String getInvoiceImage()
    {
        imageList = new ArrayList<String>();
        DInvoice invoice = new DInvoice();
        DInvoiceImage image = new DInvoiceImage();
        invoice.setInvoiceId(entity.getInvoiceId());
        image.setInvoiceId(entity.getInvoiceId());
        entity = dinvoiceServiceImpl.selectOneByEntity(invoice);
       // imageList.add(entity.getDinvoiceImagePath());//出门单缩略图
        imageList.addAll(dinvoiceImageServiceImpl.getImages(image));//出门单包含的图片
        return "DInvoice/invoice_pic";
    }

    /**
     * 预览后不上传点击关闭，清除session中的产品集合
     * @throws IOException 
     */
    public String removeSession() throws IOException
    {
        if (entity.getInvoiceId() != null)
        {
            session.removeAttribute(entity.getInvoiceId() + "dinvoiceItemList");
        }

        return null;

    }
    
    /**
     * 删除出门单
     */
    public String deleteInvoice(){
        if(dinvoiceServiceImpl.deleteDinvoiceByInvoiceId(entity.getInvoiceId())!=0){
            setMessage("删除成功");
        }
        else
        {
            setInfoMessage("删除失败");
        }
        return JSON_DATA;
    }
}