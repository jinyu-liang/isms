<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<table class="list" width="100%">
    <tr >
        <th colspan="4" align="center" bgcolor="#EDEDED" ><s:date name="entity.progressReport.updateTm" format="yyyy年MM月dd日" />&nbsp;&nbsp;&nbsp;&nbsp;<s:property value="entity.wsLeader" />队
            &nbsp;&nbsp;&nbsp;&nbsp;工程 <font style="font-size:1.3em;color: red; "><s:property value="project.projectNm"/></font>进度编辑
        </th>
    </tr>
    <s:hidden name="project.projectId" ></s:hidden>
    <tr>
        <th colspan="4" align="center"  >设备</th>
    </tr>
    <tr>
        <td><label for="project.weight"  >设备净重(吨):</label></td>
        <td colspan="3"><span>
    	       <s:textfield name="project.weight"  id="project_weight" cssClass="number" />
            </span>
        </td>
    </tr>   
    <tr>
    	<th colspan="4" align="center" >进驻日期及合同约定</th>
    </tr>
    <tr>
        <td> <label for="project_startDate">实  际  进 驻  日  期:</label></td>
        <td><span><s:textfield name="project.startDate" cssClass="date" dateFmt="yyyy-MM-dd" id="project_startDate" />
             <a class="inputDateButton" href="javascript:;">选择</a>  
	       </span>
        </td>
        <td><label for="project_contractStartDate" >合同要求进驻日期:</label></td>
        <td><span>
                <s:textfield name="project.contractStartDate" cssClass="date" dateFmt="yyyy-MM-dd" id="project_contractStartDate" />  <a class="inputDateButton" href="javascript:;">选择</a>  
            </span>
        </td>
    </tr>
    <tr>
        <td> <label for="project_contractOtherReq" >合  同  其  他  要  求:</label></td>
        <td colspan="3"> <span>
                   <s:textarea rows="2" cols="45" name="project.contractOtherReq"  id="project_contractOtherReq"  maxlength="100"/>
             </span>
        </td>
    </tr>
    <tr>
        <th colspan="4" align="center" >现场情况反馈</th>
    </tr>
    <tr>
        <td> <label for="project_contractOtherReq" >厂内发货 问题:</label></td>
    	<td><span>
    	       <s:textfield name="entity.fbDelivery"  id="entity_fbDelivery"  maxlength="100"/>
             </span>
    	</td>
    	<td><label for="entity_fbQuality"  >质量情况:</label></td>
    	<td><span>
    	       <s:textfield name="entity.fbQuality"  id="entity_fbQuality"  maxlength="100"/>
    	   </span>
    	</td>
    </tr>
    <tr>
        <td><label for="entity_fbSecurity" >安全情况: </label></td>
        <td><span>
    	       <s:textfield name="entity.fbSecurity"  id="entity_fbSecurity"  maxlength="100"/>
    	   </span>
        </td>
        <td><label for="entity_fbManner" >文 明 施 工 情 况:</label></td>
        <td><span>
    	       <s:textfield name="entity.fbManner"  id="entity_fbManner"  maxlength="100"/>
    	   </span>
        </td> 
    </tr>
    <tr>   
        <td><label for="entity_fbMmaterial" >主材情况:</label>
        <td><span>
    	       <s:textfield name="entity.fbMmaterial"  id="entity_fbMmaterial"  maxlength="100"/>
    	   </span>
        </td>
        <td><label for="entity_fbSmaterial" >配件情况:</label></td>
        <td><span>
    	       <s:textfield name="entity.fbSmaterial"  id="entity_fbSmaterial"  maxlength="100"/>
    	   </span>
        </td>       
    </tr>
    <tr>
        <th colspan="4" align="center" >现场费用</th>
    </tr>
    <tr>
        <td><label for="entity_totalCost" >预 计     定     额:</label></td>
        <td colspan="3"><span>
	           <s:textfield name="entity.totalCost"  id="entity_totalCost"   cssClass="number"/>
            </span>
        </td> 
    </tr>
    <tr>  
    	<th colspan="4" align="center" >现场易耗费用</th>
    </tr>
    <tr>    
        <td><label for="entity_totalExpense" >总定额金额:</label></td>
        <td><span>
    	       <s:textfield name="entity.totalExpense"  id="entity_totalExpense"  cssClass="number" />
            </span>
         </td>   
        <td><label for="entity_currentExpense" >已发金额:</label></td>
        <td><span>
    	       <s:textfield name="entity.currentExpense"  id="entity_currentExpense"  cssClass="number" />
    	   </span>
    	</td>       
    </tr>
    <tr>
        <th colspan="4" align="center" >备注</th>
    </tr>
    <tr>
        <td><label for="entity_memo" >备注:</label></td>
        <td colspan="3"><span>
    	       <s:textarea rows="2" cols="45"  name="entity.memo"  id="entity_memo" maxlength="200"/>
    	   </span>
        </td>    
    </tr>
</table>
