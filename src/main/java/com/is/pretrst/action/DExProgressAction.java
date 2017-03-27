package com.is.pretrst.action;

import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.base.mybatis.BaseStruts2Action;
import com.is.ggkz.entity.GgkzUserInfo;
import com.is.ggkz.service.GgkzUserInfoServiceImpl;
import com.is.pretrst.entity.DExProgress;
import com.is.pretrst.entity.DExProject;
import com.is.pretrst.entity.DProgressImage;
import com.is.pretrst.entity.DProgressReport;
import com.is.pretrst.entity.query.DExProgressQuery;
import com.is.pretrst.entity.query.DProgressReportQuery;
import com.is.pretrst.service.DExProgressServiceImpl;
import com.is.pretrst.service.DExProjectServiceImpl;
import com.is.pretrst.service.DProgressImageServiceImpl;
import com.is.pretrst.service.DProgressReportServiceImpl;

/**
 *
 * @ClassName: DExProgressAction
 * @Description: DExProgress表对应的Action类
 * @author 
 * @date 2013-09-10 10:25:53 *
 */
@Namespace("/rst")
public class DExProgressAction extends BaseStruts2Action
{
    private static final long       serialVersionUID = 1L;

    @SuppressWarnings("unused")
    private static final Logger     LOGGER           = LoggerFactory.getLogger(DExProgressAction.class);

    private DExProgress             entity;

    private DExProgressQuery        queryEntity;

    private DExProject              project;
    
    private List<String>            imageList;
    
    private Map<String,List<DProgressReport>> reportMap ;
    @Autowired
    private DExProgressServiceImpl  dexProgressServiceImpl;

    @Autowired
    private DExProjectServiceImpl   dexProjectServiceImpl;

    @Autowired
    private GgkzUserInfoServiceImpl ggkzUserInfoServiceImpl;
    
    @Autowired
    private DProgressReportServiceImpl dprogressReportServiceImpl;
    
    @Autowired
    private DProgressImageServiceImpl dprogressImageServiceImpl;
    
    private DProgressReportQuery dProgressReportQuery;//报告查询对象
    private String reportDt;//报告日期
    private String userCd;//报告人id
    
    public DExProgressAction()
    {
        super();
        if (entity == null)
        {
            entity = new DExProgress();
        }
        if (project == null)
        {
            project = new DExProject();
        }
        if (queryEntity == null)
        {
            queryEntity = new DExProgressQuery();
        }
        if (dProgressReportQuery == null)
        {
            dProgressReportQuery = new DProgressReportQuery();
        }
    }

    public String toCreate() throws Exception
    {
        return null;
    }

    public String create() throws Exception
    {
        return null;
    }

    public String toEdit() throws Exception
    {
        project.setProjectId(entity.getProjectId());
        project = dexProjectServiceImpl.selectOneByEntity(project);
        entity = dexProgressServiceImpl.selectOneByEntity(entity);
        return "DExProject/edit";
    }

    public String edit() throws Exception
    {
        project.setProjectId(entity.getProjectId());
        int i = dexProjectServiceImpl.updateByEntity(project);
        i += dexProgressServiceImpl.updateByEntity(entity);
        if (i != 0)
        {
            setMessage("修改成功");
        }
        else
        {
            setInfoMessage("修改失败");
        }
        return JSON_DATA;
    }

    public String delete() throws Exception
    {
        return null;
    }

    public String view() throws Exception
    {
        return null;
    }

    public String toList() throws Exception
    {
        return null;
    }

    public String list() throws Exception
    {
        page = dexProgressServiceImpl.pageQuery(queryEntity);
        return "DExProgress/list";
    }
    public String treeList() throws Exception
    {
        reportDt = reportDt.trim();
        String mon = reportDt.substring(reportDt.indexOf("-") + 1).trim();
        if(mon.length()<2){//处理月份，一位数的在前面加0
            String year = reportDt.substring(0,reportDt.indexOf("-"));
            reportDt = year+"-0"+mon;
        }
        queryEntity.setFbManner(reportDt);//把报告的reportDt放进进度对象
        queryEntity.setFbMmaterial(userCd);//把报告的提交者放到进度对象
        page = dexProgressServiceImpl.pageQueryTree(queryEntity);
        return "DExProgress/list";
    }
    /**
     * 校验是否是部长权限（AJAX）
     * @throws IOException 
     */
    public String isAuth() throws IOException
    {
        GgkzUserInfo user = new GgkzUserInfo();
        user.setUserId(sessionUser.getUserId());
        user = ggkzUserInfoServiceImpl.selectUserByEntity(user);
        if (user != null)
        {
            if (user.getPost().equals("3") || user.getPost().equals("7") || user.getPost().equals("13") || user.getPost().equals("14")
                    || user.getPost().equals("16") || user.getPost().equals("17") || user.getPost().equals("18") || user.getPost().equals("24"))
            {
                ServletActionContext.getResponse().getWriter().print(1);
            }
            else
            {
                ServletActionContext.getResponse().getWriter().print(0);
            }
        }
        return null;

    }
    /**
     * 查询进度报告列表
     * @return
     */
    public String progressIndex(){
       // Map<String,List<DProgressReport>> reportMap1 =  dprogressReportServiceImpl.selectProgressIndex();
        reportMap =  dprogressReportServiceImpl.selectProgressIndex();
         
          /*reportMap =  this.getSortedHashtableByKey(reportMap1);
          Map s = new HashMap();*/
       return "DExProgress/progressIndex";
        
    }
    /** 
     * @param h 
     * @return 
     * 实现对map按照key排序 
     */  
    @SuppressWarnings("unchecked")  
    public static Map.Entry[] getSortedHashtableByKey(Map h) {  
  
        Set set = h.entrySet();  
  
        Map.Entry[] entries = (Map.Entry[]) set.toArray(new Map.Entry[set  
                .size()]);  
  
        Arrays.sort(entries, new Comparator() {  
            public int compare(Object arg0, Object arg1) {  
                Object key1 = ((Map.Entry) arg0).getKey();  
                Object key2 = ((Map.Entry) arg1).getKey();  
                return ((Comparable) key1).compareTo(key2);  
            }  
  
        });  
  
        return entries;  
    }  
    
    /**
     * 查询列表上的图片
     * @return
     */
    public String listPic(){
        DProgressImage image = new DProgressImage();
        image.setReportId(entity.getReportId());
        imageList = dprogressImageServiceImpl.selectImagebyEntity(image);
        return  "DExProgress/progress_pic";
    }
    //getter sertter
    public DExProgress getEntity()
    {
        return entity;
    }

    public void setEntity(DExProgress entity)
    {
        this.entity = entity;
    }

    public DExProgressQuery getQueryEntity()
    {
        return queryEntity;
    }

    public void setQueryEntity(DExProgressQuery queryEntity)
    {
        this.queryEntity = queryEntity;
    }

    public DExProject getProject()
    {
        return project;
    }

    public void setProject(DExProject project)
    {
        this.project = project;
    }

    public List<String> getImageList()
    {
        return imageList;
    }

    public void setImageList(List<String> imageList)
    {
        this.imageList = imageList;
    }

    public void setDexProgressServiceImpl(DExProgressServiceImpl dexProgressServiceImpl)
    {
        this.dexProgressServiceImpl = dexProgressServiceImpl;
    }

    public void setDexProjectServiceImpl(DExProjectServiceImpl dexProjectServiceImpl)
    {
        this.dexProjectServiceImpl = dexProjectServiceImpl;
    }

    public void setGgkzUserInfoServiceImpl(GgkzUserInfoServiceImpl ggkzUserInfoServiceImpl)
    {
        this.ggkzUserInfoServiceImpl = ggkzUserInfoServiceImpl;
    }

    public void setDprogressReportServiceImpl(DProgressReportServiceImpl dprogressReportServiceImpl)
    {
        this.dprogressReportServiceImpl = dprogressReportServiceImpl;
    }

    
    public Map<String, List<DProgressReport>> getReportMap()
    {
        return reportMap;
    }

    public void setReportMap(Map<String, List<DProgressReport>> reportMap)
    {
        this.reportMap = reportMap;
    }

    public void setDprogressImageServiceImpl(DProgressImageServiceImpl dprogressImageServiceImpl)
    {
        this.dprogressImageServiceImpl = dprogressImageServiceImpl;
    }

  
    public String getReportDt()
    {
        return reportDt;
    }

    public void setReportDt(String reportDt)
    {
        this.reportDt = reportDt;
    }

    public String getUserCd()
    {
        return userCd;
    }

    public void setUserCd(String userCd)
    {
        this.userCd = userCd;
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
}