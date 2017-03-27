package com.is.pretrst.entity;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import com.base.mybatis.AbstractBaseEntity;

/**
 *
 * @ClassName: DScrapTrans
 * @Description: DScrapTrans表的对应的java对象，该文件为工具自动生成，请勿手工修改!
 * @author 
 * @date 2013-09-10 10:26:54
 *
 */
public class DScrapTrans extends AbstractBaseEntity
{

    private static final long  serialVersionUID    = 1L;

    /* 运输Id */
    public static final String ALIAS_TRANS_ID      = "transId";

    /* 发货仓库编码 */
    public static final String ALIAS_FROM_WS_CD    = "fromWsCd";

    /* 起单用户ID */
    public static final String ALIAS_TRANS_USER_CD = "transUserCd";

    /* 发货人 */
    public static final String ALIAS_SENDER        = "sender";

    /* 收货工作中心编码 */
    public static final String ALIAS_TO_WS_CD      = "toWsCd";

    /* 接收人 */
    public static final String ALIAS_RECEIVER      = "receiver";
    
    /* 接收id */
    public static final String ALIAS_RECEIVERID      = "receiverId";

    /* 运输公司名称 */
    public static final String ALIAS_T_COMPANY_NM  = "tCompanyNm";

    /* 司机 */
    public static final String ALIAS_DRIVER        = "driver";

    /* 权重 */
    public static final String ALIAS_WEIGHT        = "weight";

    /* 发货时间 */
    public static final String ALIAS_DELIVERY_TM   = "deliveryTm";

    /* 到货时间 */
    public static final String ALIAS_ARRIVAL_TM    = "arrivalTm";

    /* 备注 */
    public static final String ALIAS_MEMO          = "memo";

    /* 状态 */
    public static final String ALIAS_STATUS_CD     = "statusCd";

    /* 运输Id */
    private String             transId;

    /* 发货仓库编码 */
    private String             fromWsCd;

    /* 运输用户ID */
    private String             transUserCd;

    /* 运输用户 */
    private String             transUserName;
    /* 发货人 */
    private String             sender;

    /* 收货工作中心编码 */
    private String             toWsCd;

    /* 接收人 */
    private String             receiver;
    /* 接收人id */
    private String             receiverId;

    /* 运输公司名称 */
    private String             tCompanyNm;

    /* 司机 */
    private String             driver;

    /* 权重 */
    private Float              weight;

    /* 发货时间 */
    private Date               deliveryTm;

    /* 到货时间 */
    private Date               arrivalTm;

    /* 备注 */
    private String             memo;

    /* 状态 */
    private String             statusCd;
    
    private List<String> fileName;

    public String getTransId()
    {
        return this.transId;
    }

    public void setTransId(String transId)
    {
        this.transId = transId;
    }

    public String getFromWsCd()
    {
        return this.fromWsCd;
    }

    public void setFromWsCd(String fromWsCd)
    {
        this.fromWsCd = fromWsCd;
    }

    public String getTransUserCd()
    {
        return this.transUserCd;
    }

    public void setTransUserCd(String transUserCd)
    {
        this.transUserCd = transUserCd;
    }

    public String getSender()
    {
        return this.sender;
    }

    public void setSender(String sender)
    {
        this.sender = sender;
    }

    public String getToWsCd()
    {
        return this.toWsCd;
    }

    public void setToWsCd(String toWsCd)
    {
        this.toWsCd = toWsCd;
    }

    public String getReceiver()
    {
        return this.receiver;
    }

    public void setReceiver(String receiver)
    {
        this.receiver = receiver;
    }

    public String getTCompanyNm()
    {
        return this.tCompanyNm;
    }

    public void setTCompanyNm(String tCompanyNm)
    {
        this.tCompanyNm = tCompanyNm;
    }

    public String getDriver()
    {
        return this.driver;
    }

    public void setDriver(String driver)
    {
        this.driver = driver;
    }

    public Float getWeight()
    {
        return this.weight;
    }

    public void setWeight(Float weight)
    {
        this.weight = weight;
    }

    public Date getDeliveryTm()
    {
        return this.deliveryTm;
    }

    public void setDeliveryTm(Date deliveryTm)
    {
        this.deliveryTm = deliveryTm;
    }

    public Date getArrivalTm()
    {
        return this.arrivalTm;
    }

    public void setArrivalTm(Date arrivalTm)
    {
        this.arrivalTm = arrivalTm;
    }

    public String getMemo()
    {
        return this.memo;
    }

    public void setMemo(String memo)
    {
        this.memo = memo;
    }

    public String getStatusCd()
    {
        return this.statusCd;
    }

    public void setStatusCd(String statusCd)
    {
        this.statusCd = statusCd;
    }


    public String getReceiverId()
    {
        return receiverId;
    }

    public void setReceiverId(String receiverId)
    {
        this.receiverId = receiverId;
    }
    

    public String getTransUserName() {
		return transUserName;
	}

	public void setTransUserName(String transUserName) {
		this.transUserName = transUserName;
	}

	/**
     * toString方法
     */
    public String toString()
    {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE).append("transId", getTransId()).append("fromWsCd", getFromWsCd())
                .append("transUserCd", getTransUserCd()).append("sender", getSender()).append("toWsCd", getToWsCd())
                .append("receiver", getReceiver()).append("tCompanyNm", getTCompanyNm()).append("driver", getDriver()).append("weight", getWeight())
                .append("deliveryTm", getDeliveryTm()).append("arrivalTm", getArrivalTm()).append("memo", getMemo())
                .append("statusCd", getStatusCd()).append("receiverId",getReceiverId()).toString();
    }

	public void setFileName(List<String> fileName) {
		this.fileName = fileName;
	}

	public List<String> getFileName() {
		return fileName;
	}

	public String gettCompanyNm() {
		return tCompanyNm;
	}

	public void settCompanyNm(String tCompanyNm) {
		this.tCompanyNm = tCompanyNm;
	}

	
}