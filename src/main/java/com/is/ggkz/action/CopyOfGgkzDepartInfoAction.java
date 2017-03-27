package com.is.ggkz.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import net.sf.json.JSONArray;

import org.apache.struts2.convention.annotation.Namespace;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.base.mybatis.BaseStruts2Action;
import com.is.ggkz.entity.GgkzDepartInfo;
import com.is.ggkz.entity.GgkzResourceInfo;
import com.is.ggkz.entity.GgkzRoleInfo;
import com.is.ggkz.entity.query.GgkzDepartInfoQuery;
import com.is.ggkz.entity.query.GgkzRoleInfoQuery;
import com.is.ggkz.service.GgkzDepartInfoServiceImpl;
import com.is.ggkz.tool.JqOrgChart;
import com.is.utils.ztree.ZTreeData;
import com.is.utils.ztree.ZtreeHelper;

/**
 *
 * @ClassName: GgkzDepartInfoAction
 * @Description: GgkzDepartInfo表对应的Action类
 * @author 
 * @date 2013-02-27 14:19:37 *
 */
@Namespace("/ggkz")
public class CopyOfGgkzDepartInfoAction extends BaseStruts2Action
{
    private static final long         serialVersionUID = 1L;

    @SuppressWarnings("unused")
    private static final Logger       LOGGER           = LoggerFactory.getLogger(CopyOfGgkzDepartInfoAction.class);

    // -- 页面属性 --//
    private GgkzDepartInfo            entity;

    private GgkzDepartInfoQuery       queryEntity;

    private String                    orgChart;

    @Autowired
    private GgkzDepartInfoServiceImpl ggkzDepartInfoServiceImpl;
    
    /**
     * json格式数据的字符串形式
     */
    public String jsondata;
    
    public String getJsondata() {
		return jsondata;
	}

	public void setJsondata(String jsondata) {
		this.jsondata = jsondata;
	}

	public CopyOfGgkzDepartInfoAction()
    {
        super();
        if (entity == null)
        {
            entity = new GgkzDepartInfo();
        }
        if (queryEntity == null)
        {
        	queryEntity = new GgkzDepartInfoQuery();
        }
    }

    public String list() throws Exception
    {
//        orgChart = JqOrgChart.collection2jOrgChartHtml(ggkzDepartInfoServiceImpl.selectDepartAll(entity));
//        return "ggkz-depart-info/list";
    	
    	page = ggkzDepartInfoServiceImpl.pageQuery(queryEntity);
        return "ggkz-depart-info/list";
    }

    public String toAdd()
    {
       String deptId = entity.getDepartId();
        if (deptId == null || "".equals(deptId))
        {
            String rst = ggkzDepartInfoServiceImpl.selectbaseid();
            if (rst.length()>0)
            {
                entity.setDepartId(rst);
            }
            else
            {
                setInfoMessage("生成部门ID失败！");
                return SUCCESS;
            }
        }
        return "ggkz-depart-info/add";
    }

    public String create()
    {
//    	if(entity.getDepart_fullname()==null ||"".equalsIgnoreCase(entity.getDepart_fullname()))
//    	{
//    		entity.setDepart_fullname(entity.getDepartName());
//    	}
//    	
//    	if(entity.getAddtime()==null)
//    	{
//    		SimpleDateFormat ymd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//    		try {
//    			entity.setAddtime(ymd.parse(ymd.format(new Date())));
//    		} catch (Exception e1) {
//    			// TODO Auto-generated catch block
//    			e1.printStackTrace();
//    		}
//    	}
//        int rst = ggkzDepartInfoServiceImpl.insertEntity(entity);
//        if (rst == 1)
//        {
//        	setMessage("添加成功1111111111111111111！");  
//           // return SUCCESS;
//        }
//        else
//        {
//            setWarnMessage("添加失败！");
//           // return SUCCESS;
//        }
    	setMessage("角色名称已经存在");
    	//jsondata = "{\"statusCode\":\"200\",\"message\":\"保存成功！\",\"navTabId\":\"navTabId\",\"rel\":\"\",\"callbackType\":\"closeCurrent\",\"forwardUrl\":\"\",\"confirmMsg\":\"\"}";;
        return JSON_DATA;
    }
    
    /**
     * 保存角色对象
     */
    public String create1() throws Exception
    {
        setMessage("添加成功");
        return JSON_DATA;
    }

    public String delete()
    {
        entity.setDepartId(entity.getDepartId() + "%");
        int rst = ggkzDepartInfoServiceImpl.deleteByEntityCascade(entity);
        if (rst == 1)
        {
            setInfoMessage("删除成功！");
            return SUCCESS;
        }
        else
        {
            setWarnMessage("删除失败！");
            return SUCCESS;
        }
    }
    
    /**
     * 获取部门信息
     * 
     * @return
     * @throws Exception
     */
    public String gettree() throws Exception
    {
        List<GgkzDepartInfo> resourceList = ggkzDepartInfoServiceImpl.selectDepartAll(entity);
        
        List<ZTreeData> treeData = new ArrayList<ZTreeData>();
        if (resourceList != null && resourceList.size() > 0)
        {
            for (int i = 0; i < resourceList.size(); i++)
            {
            	GgkzDepartInfo resource = resourceList.get(i);
                ZTreeData data = new ZTreeData();
                data.setId(resource.getDepartId());
                data.setpId(resource.getHigherDepartId());
                data.setName(resource.getDepartName());
               // data.setUrl(resource.getResourceUrl());
                data.setOpen(false);
                treeData.add(data);
            }
        }

        jsondata = ZtreeHelper.ztreeData(treeData);
        return JSON_DATA;
    }
    
    /**
     * 获取资源菜单
     * 
     * @return
     * @throws Exception
     */
    public String getpartinfo( ) throws Exception
    {
        GgkzDepartInfo resourceList = ggkzDepartInfoServiceImpl.selectMaxEntity(entity);
        
        Map<String, Object> map = new TreeMap<String, Object>();
        
        if (resourceList != null)
        {
        	map.put("departfullname", resourceList.getDepart_fullname());
        	map.put("departId", resourceList.getDepartId());
        	map.put("departName", resourceList.getDepartName());
        	map.put("depdn", resourceList.getDepdn()+1);
        	map.put("depdns", resourceList.getDepdns()+"."+resourceList.getDepdn()+1);
        	map.put("note", resourceList.getNote());
        	map.put("ordernum", resourceList.getOrdernum());
        	map.put("higherDepartId", resourceList.getHigherDepartId());
        }
        else
        {
        	entity.setDepartId(entity.getHigherDepartId());
        	entity.setHigherDepartId("");
        	resourceList = ggkzDepartInfoServiceImpl.selectEntity(entity);
        	System.out.println("111");
        	map.put("departfullname", resourceList.getDepart_fullname());
        	map.put("departId", resourceList.getDepartId());
        	map.put("departName", resourceList.getDepartName());
        	map.put("depdn", "1");
        	map.put("depdns", resourceList.getDepdns()+".1");
        	map.put("note", resourceList.getNote());
        	map.put("ordernum", resourceList.getOrdernum());
        	map.put("higherDepartId", resourceList.getHigherDepartId());
        }
      //  JSONObject jsonObject = JSONObject.fromMap(productMap); 
        ObjectMapper mapper = new ObjectMapper();
        jsondata =  mapper.writeValueAsString(map);
       // jsondata = map.toString();        
        return JSON_DATA;
    }
    
    public String gettreePostFilter() throws Exception
    {
    	//page = ggkzDepartInfoServiceImpl.pageQuery(queryEntity);
       // return "ggkz-depart-info/searchDepart";
    	return "ggkz-depart-info/searchDepart";
    }

    public GgkzDepartInfo getEntity()
    {
        return entity;
    }

    public void setEntity(GgkzDepartInfo entity)
    {
        this.entity = entity;
    }

    public GgkzDepartInfoQuery getQueryEntity()
    {
        return queryEntity;
    }

    public void setQueryEntity(GgkzDepartInfoQuery queryEntity)
    {
        this.queryEntity = queryEntity;
    }

    public String getOrgChart()
    {
        return orgChart;
    }

    public void setOrgChart(String orgChart)
    {
        this.orgChart = orgChart;
    }

}
