package com.is.pretrst.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.base.mybatis.BaseStruts2Action;
import com.is.pretrst.entity.RstUploadConf;
import com.is.pretrst.entity.RstVerInfo;
import com.is.pretrst.service.RstUploadConfServiceImpl;
import com.is.pretrst.service.RstVerInfoServiceImpl;
import com.is.sys.entity.SysDict;

/**
 * 
 * @ClassName: ExPersonManagerAction
 * @Description: ExPersonManager表对应的Action类
 * @author 
 * @date 2013-09-11 17:24:47 *
 */
@Namespace("/rst")
public class SysConfAction extends BaseStruts2Action {
	private static final long serialVersionUID = 1L;

	private static final Logger LOGGER = LoggerFactory
			.getLogger(SysConfAction.class);
	private RstVerInfo nowVerInfo;
	private RstUploadConf imgInfo;
	// action字典属性：
	private List<SysDict> workShopList;

	private RstVerInfoServiceImpl rstVerInfoServiceImpl;
	@Autowired
	private RstUploadConfServiceImpl uploadConfServiceImpl;
	public SysConfAction() {
		super();
	}
	
    /**
     * 外线人员列表列表
     * 
     * @return
     * @throws Exception
     */
    public String list() throws Exception
    {
		nowVerInfo=rstVerInfoServiceImpl.getMaxVerCode();
		imgInfo=uploadConfServiceImpl.selectPicParam();
        return "SysConf/list";
    }

	/**
	 * 转向用户输入页面
	 */
	public String toVerConf() throws Exception {
		nowVerInfo=rstVerInfoServiceImpl.getMaxVerCode();
		return "SysConf/verConf";
	}

	public void setWorkShopList(List<SysDict> workShopList) {
		this.workShopList = workShopList;
	}

	public List<SysDict> getWorkShopList() {
		return workShopList;
	}

	@Override
	public String getWarnMessage() {
		// TODO Auto-generated method stub
		return super.getWarnMessage();
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return super.getMessage();
	}

	@Override
	public String getInfoMessage() {
		// TODO Auto-generated method stub
		return super.getInfoMessage();
	}

	public RstVerInfo getNowVerInfo() {
		return nowVerInfo;
	}

	public void setNowVerInfo(RstVerInfo nowVerInfo) {
		this.nowVerInfo = nowVerInfo;
	}




	public RstUploadConf getImgInfo() {
		return imgInfo;
	}

	public void setImgInfo(RstUploadConf imgInfo) {
		this.imgInfo = imgInfo;
	}

	public RstVerInfoServiceImpl getRstVerInfoServiceImpl() {
		return rstVerInfoServiceImpl;
	}

	@Autowired
	public void setRstVerInfoServiceImpl(
			RstVerInfoServiceImpl rstVerInfoServiceImpl) {
		this.rstVerInfoServiceImpl = rstVerInfoServiceImpl;
	}

    public void setUploadConfServiceImpl(RstUploadConfServiceImpl uploadConfServiceImpl)
    {
        this.uploadConfServiceImpl = uploadConfServiceImpl;
    }

}
