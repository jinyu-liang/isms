package com.is.ggkz.action;
import java.util.List;

import org.apache.struts2.convention.annotation.Namespace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.base.mybatis.BaseStruts2Action;

/**
 *
 * @ClassName: GgkzResourceInfoAction
 * @Description: GgkzResourceInfo表对应的Action类
 * @author 
 * @date 2013-02-27 14:19:24 *
 */
@Namespace("/ggkz")
public class GgkzResourceInfoAction extends BaseStruts2Action {
    private static final long serialVersionUID = 1L;

    @SuppressWarnings("unused")
    private static final Logger LOGGER = LoggerFactory.getLogger(GgkzResourceInfoAction.class);

    public GgkzResourceInfoAction() {
        super();
    }
    //页面属性
	//选中资源的ID
	public List<String> resourceids;

	public List<String> getResourceids() {
		return resourceids;
	}

	public void setResourceids(List<String> resourceids) {
		this.resourceids = resourceids;
	}

	public String list() throws Exception {
		// TODO Auto-generated method stub
		return "ggkz-resource-info/list";
	}

	public String input() throws Exception {
		return "ggkz-resource-info/input";
	}

	public String save() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public String delete() throws Exception {
		return null;
	}

}
