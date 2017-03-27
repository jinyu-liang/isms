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
import net.sf.json.JSONObject;

import org.apache.struts2.convention.annotation.Namespace;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.utils.spring.SpringContextHolder;

import com.base.mybatis.BaseStruts2Action;
import com.is.ggkz.dao.GgkzRoleAuthDaoImpl;
import com.is.ggkz.entity.GgkzDepartInfo;
import com.is.ggkz.entity.GgkzResourceInfo;
import com.is.ggkz.entity.GgkzRoleAuth;
import com.is.ggkz.entity.GgkzRoleInfo;
import com.is.ggkz.entity.query.GgkzDepartInfoQuery;
import com.is.ggkz.entity.query.GgkzRoleInfoQuery;
import com.is.ggkz.service.GgkzDepartInfoServiceImpl;
import com.is.ggkz.service.GgkzRoleAuthServiceImpl;
import com.is.ggkz.tool.JqOrgChart;
import com.is.pretrst.dao.MTeamDaoImpl;
import com.is.pretrst.entity.ExPersonInfoPay;
import com.is.pretrst.service.ExPersonInfoPayServiceImpl;
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
public class GgkzDepartInfoAction extends BaseStruts2Action
{
    private static final long         serialVersionUID = 1L;

    @SuppressWarnings("unused")
    private static final Logger       LOGGER           = LoggerFactory.getLogger(GgkzDepartInfoAction.class);

    // -- 页面属性 --//
    private GgkzDepartInfo            entity;

    private GgkzDepartInfoQuery       queryEntity;

    private String                    orgChart;

    @Autowired
    private GgkzDepartInfoServiceImpl ggkzDepartInfoServiceImpl;
    
    @Autowired
    private GgkzRoleAuthServiceImpl ggkzRoleInfoServiceImpl;
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

	public GgkzDepartInfoAction()
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
    	queryEntity.setDepdns(sessionUser.getDepartId());
    	page = ggkzDepartInfoServiceImpl.pageQuery(queryEntity);
        return "ggkz-depart-info/list";
    }
    

    
    public String roleupdate() throws Exception
    {
    	 String deptId = entity.getHigherDepartId();
    	 JSONArray ja=JSONArray.fromObject(deptId);
    	 for(int i=0;i<ja.size();i++)
    	 {
		 	GgkzRoleAuthDaoImpl gRoleAuthDaoImpl =new GgkzRoleAuthDaoImpl() ;//SpringContextHolder.getBean("GgkzRoleAuthDaoImpl");
		 	GgkzRoleAuth roleentity = new GgkzRoleAuth();
    		 JSONObject o=ja.getJSONObject(i);
    		 if(o.get("id")!=null && "true".equalsIgnoreCase(o.get("checked").toString())){
    			 	roleentity.setRoleId(sessionUser.getUserId());
 		 			roleentity.setAuthId(o.get("id").toString());

 		 			SimpleDateFormat ymd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
 		    		try {
 		    			roleentity.setOperTime(ymd.parse(ymd.format(new Date())));
 		    			ggkzRoleInfoServiceImpl.insertGgkzRoleAuth(roleentity);
 		    		} catch (Exception e1) {
 		    			// TODO Auto-generated catch block
 		    			e1.printStackTrace();
 		    		}
    		 	}else if(o.get("id")!=null && "false".equalsIgnoreCase(o.get("checked").toString()))
    		 	{
    		 		roleentity.setRoleId(sessionUser.getUserId());
    		 		roleentity.setAuthId(o.get("id").toString());
    		 		gRoleAuthDaoImpl.deleteByEntity(roleentity);
    		 	}
    	 }
    	 setInfoMessage("设置成功！");
        return JSON_DATA;
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
            entity.setDepartId("part");
        	entity.setHigherDepartId("");
        	GgkzDepartInfo  resourceList = ggkzDepartInfoServiceImpl.selectMaxEntity(entity);
        	entity.setDepartId(resourceList.getHigherDepartId());
        	entity.setDepdn(resourceList.getDepdn());
        	entity.setDepdns(resourceList.getDepdn());
        	entity.setDepartId("");
        }
        return "ggkz-depart-info/add";
    }

    public String create()
    {
    	if(entity.getDepart_fullname()==null ||"".equalsIgnoreCase(entity.getDepart_fullname()))
    	{
    		entity.setDepart_fullname(entity.getDepartName());
    	}else
    	{
    		entity.setDepart_fullname(entity.getDepart_fullname()+"."+entity.getDepartName());
    	}
    	
    	if(entity.getAddtime()==null)
    	{
    		SimpleDateFormat ymd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    		try {
    			entity.setAddtime(ymd.parse(ymd.format(new Date())));
    		} catch (Exception e1) {
    			// TODO Auto-generated catch block
    			e1.printStackTrace();
    		}
    	}
        int rst = ggkzDepartInfoServiceImpl.insertEntity(entity);
        if (rst == 1)
        {
        	setMessage("添加成功1111111111111111111！");  
           // return SUCCESS;
        }
        else
        {
            setWarnMessage("添加失败！");
           // return SUCCESS;
        }
    	//setMessage("角色名称已经存在");
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
                data.setNote(resource.getDepart_fullname());
                data.setOpen(false);
                treeData.add(data);
            }
        }

        jsondata = ZtreeHelper.ztreeData(treeData);
        return JSON_DATA;
    }
    
    /**
     * 获取部门信息
     * 
     * @return
     * @throws Exception
     */
    public String getroletree() throws Exception
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
                data.setNote(resource.getDepart_fullname());
                data.setTarget(resource.getDepdns());
                data.setOpen(false);
                GgkzRoleAuth gra = new GgkzRoleAuth();
                gra = ggkzRoleInfoServiceImpl.selectByIdGgkzRoleAuth(resource.getDepartId(), sessionUser.getUserId());
                if(gra!=null && gra.getRoleId()!=null)
                {
                	data.setChecked(true);
                }
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
        	map.put("depdn", resourceList.getDepdn());
        	map.put("depdns", resourceList.getDepdns()+"."+resourceList.getDepdn());
        	map.put("note", resourceList.getNote());
        	map.put("ordernum", resourceList.getOrdernum());
        	map.put("higherDepartId", resourceList.getHigherDepartId());
        }
        else
        {
//        	entity.setDepartId("part");
//        	entity.setHigherDepartId("");
//        	resourceList = ggkzDepartInfoServiceImpl.selectMaxEntity(entity);
        	entity.setDepartId(entity.getHigherDepartId());
        	entity.setHigherDepartId("");
        	resourceList = ggkzDepartInfoServiceImpl.selectEntity(entity);
        	System.out.println("111");
        	map.put("departfullname", resourceList.getDepart_fullname());
        	map.put("departId", resourceList.getDepartId());
        	map.put("departName", resourceList.getDepartName());
        	map.put("depdn","1");
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
