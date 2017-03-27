package com.is.pretrst.action;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.HashMap;
import com.lowagie.text.html.HtmlEncoder;

import javax.servlet.http.HttpServletResponse;



import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableImage;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.utils.spring.SpringContextHolder;

import com.base.mybatis.BaseStruts2Action;
import com.is.pretrst.dao.ExWorkTypeDaoImpl;
import com.is.pretrst.dao.MTeamDaoImpl;
import com.is.pretrst.entity.ExPersonInfo;
import com.is.pretrst.entity.ExWorkType;
import com.is.pretrst.entity.MTeam;
import com.is.pretrst.entity.query.ExWorkTypeQuery;
import com.is.pretrst.entity.query.MTeamQuery;
import com.is.pretrst.service.ExPersonInfoServiceImpl;
import com.is.pretrst.service.ExWorkTypeServiceImpl;
import com.lowagie.text.html.HtmlEncoder;

/**
 * Excel导入导出
 * 
 * @author 林计钦
 * @version 1.0 Feb 7, 2014 4:14:51 PM
 */
@Namespace("/rst")
public class ExcelImportAction extends BaseStruts2Action{
	private static final long serialVersionUID = 1L;
	
	private ExPersonInfoServiceImpl exPersonInfoServiceImpl;
    public File doc; //得到上传的模板信息
    private String upFileFileName;
    private String upFileContentType;

	private Map em;//检查模板信息错误显示
    private String fileName;
    private String bankid;
    private String bankname;
    private String teamid;
    private String teamname;
    private String proid;
    private String proname;
    private MTeam entity;
    private ExWorkType entityworktype;
    @Autowired
    private ExWorkTypeServiceImpl exWorkTypeServiceImpl;
    
    @Autowired
    public ExcelImportAction() {
        super();
        if(entity==null){
            entity=new MTeam();
        }
        if(entityworktype==null){
        	entityworktype=new ExWorkType();
        }
    }

	/**
	    * 保存导入模板信息
	    * @return
	    */
	   public String importTemplate(){	  
		   String returns = "";
			try {
				// 导入处理模块
					File xlsFile = null;
					if (doc != null && doc.length() > 0) {
						xlsFile = doc;
					}
					
					if(em == null){
						em = new HashMap();
					}
					
					MTeamDaoImpl mTeamDaoImpl = (MTeamDaoImpl) SpringContextHolder.getBean("MTeamDaoImpl");
					entity = mTeamDaoImpl.selectOneByEntity(entity);
					
					

					String ret = importExcel(xlsFile.getAbsolutePath(),"","",entity.getTeamCd(),entity.getTeamNm(),entity.getWsCd(),entity.getWsNm(),fileName);
					HttpServletResponse response = ServletActionContext.getResponse();
					response.setHeader("Content-type", "text/html;charset=UTF-8");  
					response.setCharacterEncoding("UTF-8"); 
					try {						
						if(ret!=null && ret.length() > 1)
						{
							String t1 = HtmlEncoder.encode(ret);
							response.getOutputStream().print("<script>parent.callback('"+t1+"')</script>");
						}else
						{
							String t1 = HtmlEncoder.encode("上传成功！");
							response.getOutputStream().print("<script>parent.callback('"+t1+"')</script>");
						}				
					} catch (IOException e) {
						String t1 = HtmlEncoder.encode("上传失败！");
						response.getOutputStream().print("<script>parent.callback('"+t1+"')</script>");  	
					}
					
			}  catch (Exception e) {
				// TODO Auto-generated catch block
				
				this.addFieldError(null,"数据导入失败!");
				e.printStackTrace();
			}
			
			return null;
	   }
	   
    /**
     * 导入(导入到内存)
     */
    
    public String importExcel(String filepath,String bankcard,String bankname,String teamid,String teamname,String proid,String proname,String filename) {
    	Workbook book = null;
        StringBuffer rsp = new StringBuffer();
        try {
            book = Workbook.getWorkbook(new File(filepath));
            // 获得第一个工作表对象
            Sheet sheet = book.getSheet(0);
            int rows=sheet.getRows();
            int columns=sheet.getColumns();
            // 遍历每行每列的单元格
            for(int i=1;i<rows;i++){
            	StringBuffer rsp_sub = new StringBuffer();
            	String xm = "",kard ="",tel="",yhcard="",yhcardkhh="",starttime="";
            	ExPersonInfo entityper  = new ExPersonInfo();
            	
                for(int j=0;j<columns;j++){
                	
                    Cell cell = sheet.getCell(j, i);
                    String result = cell.getContents();      
                    if(j==0){
                        if(result==null ||"".equalsIgnoreCase(result))
                        {
                        	rsp_sub.append("第"+i+"行，第"+j+"列，数据为空;");
                        	System.out.println(rsp_sub);
                        }else
                        {
                        	entityper.setName(result);
                        }
                    }else if(j==1){
                    	if(result==null ||"".equalsIgnoreCase(result))
                        {
                    		rsp_sub.append("第"+i+"行，第"+j+"列，数据为空;");
                    		System.out.println(rsp_sub);
                        }else
                        {
                        	entityper.setIdentyCardCode(result);
                        }
                    }else  if(j==2){
                    	if(result==null ||"".equalsIgnoreCase(result))
                        {
                    		rsp_sub.append("第"+i+"行，第"+j+"列，数据为空;");
                    		System.out.println(rsp_sub);
                        }else
                        {
                        	entityper.setTelephone(result);
                        }
                    }else  if(j==3){
                    	if(result==null ||"".equalsIgnoreCase(result))
                        {
                    		rsp_sub.append("第"+i+"行，第"+j+"列，数据为空;");
                    		System.out.println(rsp_sub);
                        }else
                        {
                        	entityper.setBank_id(result);
                        }
                    }else  if(j==4){
                    	if(result==null ||"".equalsIgnoreCase(result))
                        {
                    		rsp_sub.append("第"+i+"行，第"+j+"列，数据为空;");
                    		System.out.println(rsp_sub);
                        }else
                        {
                        	entityper.setBank_name(result);
                        }
                    }else  if(j==5){
                    	if(result==null ||"".equalsIgnoreCase(result))
                        {
                    		rsp_sub.append("第"+i+"行，第"+j+"列，数据为空;");
                    		System.out.println(rsp_sub);
                        }else
                        {
                        	try{
                        		SimpleDateFormat sdf =   new SimpleDateFormat( "yyyy-MM-dd" );
                            	Date date = sdf.parse(result);
                            	entityper.setUpdateDate(date);
                        	}catch(Exception e)
                        	{
                        		rsp_sub.append("第"+i+"行，第"+j+"列，时间格式不正确;");
                        		System.out.println(rsp_sub);
                        	}
                        }
                    }else  if(j==6){
                    	if(result==null ||"".equalsIgnoreCase(result))
                        {
                    		rsp_sub.append("第"+i+"行，第"+j+"列，数据为空;");
                    		System.out.println(rsp_sub);
                        }else
                        {
                        	entityworktype=new ExWorkType();
                        	entityworktype.setWork_ws_cd(proid);
                        	entityworktype.setWorkNm(result);
                        	entityworktype = exWorkTypeServiceImpl.selectOneByEntity(entityworktype);;
        					
                        	if(entityworktype!=null && entityworktype.getWorkCd().length()>1)
                        	{
                        		entityper.setWorkType(entityworktype.getWorkCd());
                        		entityper.setWorkTypeName(entityworktype.getWorkNm());
                        	}else
                        	{
                        		rsp_sub.append("第"+i+"行，第"+j+"列，填写的工种数据不存在;");
                        	}
                        	
                        }
                    }  
                }
                if(rsp_sub.toString().length()==0)
                {
                	if(isExist("",entityper.getIdentyCardCode())){
                        //存在重复的
                		rsp_sub.append("第"+i+"行，施工人员重复;");
                    }else
                    {
                    	entityper.setTeamId(teamid);
                    	entityper.setTeamName(teamname);
                    	entityper.setWsCd(proid);
                    	entityper.setWsNm(proname);
                    	if(exPersonInfoServiceImpl.insert(entityper)!=1){
                    		rsp_sub.append("第"+i+"行，，插入失败;");
                        }
                    }
                }
                rsp.append(rsp_sub.toString());
            }
        } catch (Exception e) {
            System.out.println(e);
        }finally{
        	
            if(book!=null){
                book.close();
            }
        }
        return rsp.toString();
    }
    
    /**
     * 判断用户是否已经存在
     * @param teamCd
     * @return boolean
     */
    public boolean isExist(String wsCd,String teamNm){
    	ExPersonInfo teamTmp  = new ExPersonInfo();
        teamTmp.setWsCd(wsCd);
        teamTmp.setIdentyCardCode(teamNm);
        teamTmp = exPersonInfoServiceImpl.selectOneByEntity(teamTmp);
         
        if(teamTmp!=null){
            return true;//存在返回true
        }return false;
    }

    /**
     * 导出(导出到磁盘)
     */
    @Test
    public void exportExcel() {
        WritableWorkbook book = null;
        try {
            // 打开文件
            book = Workbook.createWorkbook(new File("D:/test/测试.xls"));
            // 生成名为"学生"的工作表，参数0表示这是第一页
            WritableSheet sheet = book.createSheet("学生", 0);
            // 指定单元格位置是第一列第一行(0, 0)以及单元格内容为张三
            Label label = new Label(0, 0, "张三");
            // 将定义好的单元格添加到工作表中
            sheet.addCell(label);
            // 保存数字的单元格必须使用Number的完整包路径
            jxl.write.Number number = new jxl.write.Number(1, 0, 30);
            sheet.addCell(number);
            // 写入数据并关闭文件
            book.write();
        } catch (Exception e) {
            System.out.println(e);
        }finally{
            if(book!=null){
                try {
                    book.close();
                } catch (Exception e) {
                    e.printStackTrace();
                } 
            }
        }
    }
    
    /**
     * 对象数据写入到Excel
     */
 //   @Test
//    public void writeExcel() {
//        WritableWorkbook book = null;
//        try {
//            // 打开文件
//            book = Workbook.createWorkbook(new File("D:/test/stu.xls"));
//            // 生成名为"学生"的工作表，参数0表示这是第一页
//            WritableSheet sheet = book.createSheet("学生", 0);
//            
//            List<Student> stuList=queryStudentList();
//            if(stuList!=null && !stuList.isEmpty()){
//                for(int i=0; i<stuList.size(); i++){
//                    sheet.addCell(new Label(0, i, stuList.get(i).getName()));
//                    sheet.addCell(new Number(1, i, stuList.get(i).getAge()));
//                }
//            }
//            
//            // 写入数据并关闭文件
//            book.write();
//        } catch (Exception e) {
//            System.out.println(e);
//        }finally{
//            if(book!=null){
//                try {
//                    book.close();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                } 
//            }
//        }
//    
//    }
    
    /**
     * 读取Excel数据到内存
     */
//    @Test
//    public void readExcel() {
//        Workbook book = null;
//        try {
//            // 打开文件
//            book = Workbook.getWorkbook(new File("D:/test/stu.xls"));
//            // 获得第一个工作表对象
//            Sheet sheet = book.getSheet(0);
//            int rows=sheet.getRows();
//            int columns=sheet.getColumns();
//            List<Student> stuList=new ArrayList<Student>();
//            // 遍历每行每列的单元格
//            for(int i=0;i<rows;i++){
//                Student stu = new Student();
//                for(int j=0;j<columns;j++){
//                    Cell cell = sheet.getCell(j, i);
//                    String result = cell.getContents();
//                    if(j==0){
//                        stu.setName(result);
//                    }
//                    if(j==1){
//                        stu.setAge(NumberUtils.toInt(result));
//                    }
//                    if((j+1)%2==0){
//                        stuList.add(stu);
//                        stu=null;
//                    }
//                }
//            }
//            
//            //遍历数据
//            for(Student stu : stuList){
//                System.out.println(String.format("姓名：%s, 年龄：%s", 
//                        stu.getName(), stu.getAge()));
//            }
//            
//        } catch (Exception e) {
//            System.out.println(e);
//        }finally{
//            if(book!=null){
//                try {
//                    book.close();
//                } catch (Exception e) {
//                    e.printStackTrace();
//                } 
//            }
//        }
//    
//    }
    
    /**
     * 图片写入Excel，只支持png图片
     */
    @Test
    public void writeImg() {
        WritableWorkbook wwb = null;
        try {
            wwb = Workbook.createWorkbook(new File("D:/test/image.xls"));
            WritableSheet ws = wwb.createSheet("图片", 0);
            File file = new File("D:\\test\\png.png");
            //前两位是起始格，后两位是图片占多少个格，并非是位置
            WritableImage image = new WritableImage(1, 4, 6, 18, file);
            ws.addImage(image);
            wwb.write();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            if(wwb!=null){
                try {
                    wwb.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }  
            }
        }
    }
    
    
    public ExPersonInfoServiceImpl getExPersonInfoServiceImpl() {
		return exPersonInfoServiceImpl;
	}

	public void setExPersonInfoServiceImpl(
			ExPersonInfoServiceImpl exPersonInfoServiceImpl) {
		this.exPersonInfoServiceImpl = exPersonInfoServiceImpl;
	}

	public File getDoc() {
		return doc;
	}

	public void setDoc(File doc) {
		this.doc = doc;
	}

	public Map getEm() {
		return em;
	}

	public void setEm(Map em) {
		this.em = em;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getBankid() {
		return bankid;
	}

	public void setBankid(String bankid) {
		this.bankid = bankid;
	}

	public String getBankname() {
		return bankname;
	}

	public void setBankname(String bankname) {
		this.bankname = bankname;
	}

	public String getTeamid() {
		return teamid;
	}

	public void setTeamid(String teamid) {
		this.teamid = teamid;
	}

	public String getTeamname() {
		return teamname;
	}

	public void setTeamname(String teamname) {
		this.teamname = teamname;
	}

	public String getProid() {
		return proid;
	}

	public void setProid(String proid) {
		this.proid = proid;
	}

	public String getProname() {
		return proname;
	}

	public void setProname(String proname) {
		this.proname = proname;
	}
	public MTeam getEntity()
    {
        return entity;
    }
    public void setEntity(MTeam entity)
    {
        this.entity = entity;
    }
    public String getUpFileFileName() {
		return upFileFileName;
	}

	public void setUpFileFileName(String upFileFileName) {
		this.upFileFileName = upFileFileName;
	}

	public String getUpFileContentType() {
		return upFileContentType;
	}

	public void setUpFileContentType(String upFileContentType) {
		this.upFileContentType = upFileContentType;
	}
}
