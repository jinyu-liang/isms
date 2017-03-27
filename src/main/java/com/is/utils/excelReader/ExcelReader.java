package com.is.utils.excelReader;

	import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

	/**
	 * 操作Excel表格的功能类
	 */
	@SuppressWarnings({"unused","deprecation"})
	public class ExcelReader {
	    private POIFSFileSystem fs;
	    private HSSFWorkbook wb;
	    private HSSFSheet sheet;
	    private HSSFRow row;

	    /**
	     * 读取Excel表格表头的内容
	     * @param InputStream
	     * @return String 表头内容的数组
	     */
		public String[] readExcelTitle(InputStream is) {
	        try {
	            fs = new POIFSFileSystem(is);
	            wb = new HSSFWorkbook(fs);
	        } catch (IOException e) {
	        }
	        sheet = wb.getSheetAt(0);
	        row = sheet.getRow(0);
	        // 标题总列数
	        int colNum = row.getPhysicalNumberOfCells();
	        String[] title = new String[colNum];
	        for (int i = 0; i < colNum; i++) {
	            //title[i] = getStringCellValue(row.getCell((short) i));
	            title[i] = getCellFormatValue(row.getCell((short) i));
	        }
	        return title;
	    }

	    /**
	     * 读取Excel数据内容
	     * @param InputStream
	     * @return Map 包含单元格数据内容的Map对象
	     */
		/*public Map<Integer, String> readExcelContent(InputStream is) {
	        Map<Integer, String> content = new HashMap<Integer, String>();
	        String str = "";
	        try {
	            fs = new POIFSFileSystem(is);
	            wb = new HSSFWorkbook(fs);
	        } catch (IOException e) {
	        }
	        sheet = wb.getSheetAt(0);
	        // 得到总行数
	        int rowNum = sheet.getLastRowNum();
	        row = sheet.getRow(0);
	        int colNum = row.getPhysicalNumberOfCells();
	        // 正文内容应该从第二行开始,第一行为表头的标题
	        for (int i = 1; i <= rowNum; i++) {
	            row = sheet.getRow(i);
	            int j = 0;
	            while (j < colNum) {
	                // 每个单元格的数据内容用"-"分割开，以后需要时用String类的replace()方法还原数据
	                // 也可以将每个单元格的数据设置到一个javabean的属性中，此时需要新建一个javabean
	                // str += getStringCellValue(row.getCell((short) j)).trim() +
	                // "-";
	                str += getCellFormatValue(row.getCell((short) j)).trim() + "    ";
	                j++;
	            }
	            content.put(i, str);
	            str = "";
	        }
	        return content;
	    }*/

	    /**
	     * 获取单元格数据内容为字符串类型的数据
	     * 
	     * @param cell Excel单元格
	     * @return String 单元格数据内容
	     */
	    private String getStringCellValue(HSSFCell cell) {
	        String strCell = "";
	        switch (cell.getCellType()) {
	        case HSSFCell.CELL_TYPE_STRING:
	            strCell = cell.getStringCellValue();
	            break;
	        case HSSFCell.CELL_TYPE_NUMERIC:
	            strCell = String.valueOf(cell.getNumericCellValue());
	            break;
	        case HSSFCell.CELL_TYPE_BOOLEAN:
	            strCell = String.valueOf(cell.getBooleanCellValue());
	            break;
	        case HSSFCell.CELL_TYPE_BLANK:
	            strCell = "";
	            break;
	        default:
	            strCell = "";
	            break;
	        }
	        if (strCell.equals("") || strCell == null) {
	            return "";
	        }
	        if (cell == null) {
	            return "";
	        }
	        return strCell;
	    }

	    /**
	     * 获取单元格数据内容为日期类型的数据
	     * 
	     * @param cell
	     *            Excel单元格
	     * @return String 单元格数据内容
	     */
	   
		private String getDateCellValue(HSSFCell cell) {
	        String result = "";
	        try {
	            int cellType = cell.getCellType();
	            if (cellType == HSSFCell.CELL_TYPE_NUMERIC) {
	                Date date = cell.getDateCellValue();
	                result = (date.getYear() + 1900) + "-" + (date.getMonth() + 1)
	                        + "-" + date.getDate();
	            } else if (cellType == HSSFCell.CELL_TYPE_STRING) {
	                String date = getStringCellValue(cell);
	                result = date.replaceAll("[年月]", "-").replace("日", "").trim();
	            } else if (cellType == HSSFCell.CELL_TYPE_BLANK) {
	                result = "";
	            }
	        } catch (Exception e) {
	        }
	        return result;
	    }

	    /**
	     * 根据Cell类型设置数据
	     * @param cell
	     * @return
	     */
	    private String getCellFormatValue(Cell cell) {
	        String cellvalue = "";
	        if (cell != null) {
	            // 判断当前Cell的Type
	            switch (cell.getCellType()) {
	            // 如果当前Cell的Type为NUMERIC
	            case Cell.CELL_TYPE_NUMERIC:
	            case Cell.CELL_TYPE_FORMULA: {
	                // 判断当前的cell是否为Date
	                if (HSSFDateUtil.isCellDateFormatted(cell)) {
	                    // 如果是Date类型则，转化为Data格式
	                    
	                    //方法1：这样子的data格式是带时分秒的：2011-10-12 0:00:00
	                    //cellvalue = cell.getDateCellValue().toLocaleString();
	                    
	                    //方法2：这样子的data格式是不带带时分秒的：2011-10-12
	                    Date date = cell.getDateCellValue();
	                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	                    cellvalue = sdf.format(date);
	                    
	                }
	                // 如果是纯数字
	                else {
	                    // 取得当前Cell的数值
	                    cellvalue = String.valueOf(cell.getNumericCellValue());
	                }
	                break;
	            }
	            // 如果当前Cell的Type为STRIN
	            case Cell.CELL_TYPE_STRING:
	                // 取得当前的Cell字符串
	                cellvalue = cell.getRichStringCellValue().getString();
	                break;
	            // 默认的Cell值
	            default:
	                cellvalue = " ";
	            }
	        } else {
	            cellvalue = "";
	        }
	        return cellvalue;

	    }
        /**
         * 
         * @param filePath 源文件路径
         * @param fileType 源文件类型
         * @return
         */
		public static  List<String[]> getFileValue (InputStream is,String fileType) {
	    	ExcelReader er = new ExcelReader();
	    	List<String[]> retList = new ArrayList<String[]>(); //保存所有列
	        try {
	            // 对读取Excel表格标题测试
	           /* InputStream fis = new FileInputStream("d:\\test\\test.xls");
	            ExcelReader excelReader = new ExcelReader();
	            String[] title = excelReader.readExcelTitle(fis);
	            for (String s : title) {
	            }*/
	           // File file = new File(filePath);
	          //  String fileName = file.getName();
	          //  String prefix=fileName.substring(fileName.lastIndexOf(".")+1);//获得文件后缀
	        	//InputStream is = new FileInputStream(file);
	        	//Workbook对象，是我们最想得到的对象。
	        	 Workbook wb = null;
	        	  if("xls".equals(fileType))
	        	  {
	        	       wb = new HSSFWorkbook(is);
	        	  }
	        	  else if("xlsx".equals(fileType))
	        	  {
	        	      wb = new XSSFWorkbook(is); 
	        	  }else {
					throw new Exception("请上传正确的格式");
	        	  }
		        	Sheet sheet = wb.getSheetAt(0); 
		        	if(sheet.getLastRowNum()==0){
		                throw new Exception("上传的文件为空");
		            }
		        	int rowcount = sheet.getLastRowNum(); 
		        	int col =  0;
		        	
		        	for(short r=0;r<sheet.getLastRowNum();r++){
		        		Row row = sheet.getRow(r);
		        		if(row!=null){
		        		col = row.getLastCellNum();//获得列数
		        		String[]fileValue  = new String[col];
		        		for(int i=0;i<col;i++){
		        			fileValue[i] = er.getCellFormatValue(row.getCell(i)).trim();
		        		}
		        		retList.add(fileValue);
		        	}
		        	}
		        	
		        	/*test system.out
		        	for(String [] ss:retList){
		        		for(int i=0;i<9;i++){
		        		}
		        	}*/
	        } catch (Exception e) {
	        }
	        return retList;
	    }
		
		public static void main(String[] args) {
			ExcelReader er = new ExcelReader();
	    	List<String[]> retList = new ArrayList<String[]>(); //保存所有列
	        try {
	            // 对读取Excel表格标题测试
	           /* InputStream fis = new FileInputStream("d:\\test\\test.xls");
	            ExcelReader excelReader = new ExcelReader();
	            String[] title = excelReader.readExcelTitle(fis);
	            for (String s : title) {
	            }*/

	            /*  // 对读取Excel表格内容测试
	            InputStream is2 = new FileInputStream("d:\\test\\test.xls");
	            Map<Integer, String> map = excelReader.readExcelContent(is2);
	            for (int i = 1; i <= map.size(); i++) {
	            }*/
	            File file = new File("d:\\test\\a.xls");
	            String fileName = file.getName();
	            String prefix=fileName.substring(fileName.lastIndexOf(".")+1);//获得文件后缀
	        	InputStream is = new FileInputStream(file);
	        	//Workbook对象，是我们最想得到的对象。
	        	 Workbook wb = null;
	        	  if("xls".equals(prefix))
	        	  {
	        	       wb = new HSSFWorkbook(is);
	        	  }
	        	  else if("xlsx".equals(prefix))
	        	  {
	        	      wb = new XSSFWorkbook(is); 
	        	  }else {
					throw new Exception("请上传正确的格式");
	        	  }
		        	Sheet sheet = wb.getSheetAt(0); 
		        	if(sheet.getLastRowNum()==0){
		                throw new Exception("上传的文件为空");
		            }
		        	int rowcount = sheet.getLastRowNum(); 
		        	int col =  0;
		        	
		        	for(short r=0;r<sheet.getLastRowNum();r++){
		        		Row row = sheet.getRow(r);
		        		if(row!=null){
		        		col = row.getLastCellNum();//获得列数
		        		String[]fileValue  = new String[col];
		        		for(int i=0;i<col;i++){
		        			fileValue[i] = er.getCellFormatValue(row.getCell(i)).trim();
		        		}
		        		retList.add(fileValue);
		        	}
		        	}
		        	String title = retList.get(0)[0];//标题
		    		String deliveryPlanTm = retList.get(1)[3];//发货时间
		    		deliveryPlanTm = deliveryPlanTm.substring(deliveryPlanTm.indexOf("：")+1).replace(" ", "");
		    		String sellorderCode = retList.get(1)[0];//销售订单号
		    		sellorderCode = sellorderCode.substring(sellorderCode.indexOf("：")+1).trim();
		    		String planId = retList.get(1)[6];//发运计划编号
		    		planId = planId.substring(planId.indexOf("：")+1).trim();
		    		String toWs = retList.get(2)[0];//收货中心代码
		    		toWs = toWs.substring(toWs.indexOf("：")+1).trim();
		    		String unloadPlaceNm = retList.get(2)[4];//卸货地点
		    		unloadPlaceNm = unloadPlaceNm.substring(unloadPlaceNm.indexOf("：")+1).trim();
		    		String createUserNm = retList.get(30)[5];//制表人
		    		createUserNm = createUserNm.substring(createUserNm.indexOf("：")+1).trim();
		    		String createDate = retList.get(30)[7];
		    		createDate = createDate.substring(createDate.indexOf("：")+1).replace(" ", "");
		        	//test system.out
		        	for(int j=6;j<rowcount;j++){
		        	    String[] ss = retList.get(j);
		        		for(int i=0;i<9;i++){
		        		    System.out.println("第"+(j+1)+"行，第"+i+"列:"+ss[i]);
		        		    System.out.println(createUserNm);
		        		}
		        	} 
	        } catch (Exception e) {
	        }
		}
		
		
}
