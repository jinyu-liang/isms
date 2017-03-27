<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<s:hidden name="project.projectId" ></s:hidden>
<div>
    <label for="project_weight">设   备   净   重   (吨):</label>
    
        <span>
                <s:textfield name="project.weight"  id="project_weight" cssClass="number" />
    </span>
    
</div>
<div>
    <label for="project_startDate">实  际  进 驻  日  期</label>
    
        <span>
            <s:textfield name="project.startDate" cssClass="date" dateFmt="yyyy-MM-dd" id="project_startDate" /><a class="inputDateButton" href="javascript:;">选择</a>
        </span>
	    <label for="project_contractStartDate">合同要求 进驻日期</label>
    
        <span>
            <s:textfield name="project.contractStartDate" cssClass="date" dateFmt="yyyy-MM-dd" id="project_contractStartDate" /><a class="inputDateButton" href="javascript:;">选择</a>
        </span>
</div>
<div>
    <label for="project_contractOtherReq">合  同  其  他  要  求:</label>
    
        <span>
                <s:textarea cols="17" rows="3" name="project.contractOtherReq"  id="project_contractOtherReq"  maxlength="100"/>
        </span>
    
</div>

<div>
    <label for="project_contractOtherReq">厂  内  发 货  问  题:</label>
    
        <span>
                <s:textfield name="entity.fbDelivery"  id="entity_fbDelivery"  maxlength="100"/>
        </span>
    <label for="entity_fbQuality">质     量     情     况:</label>
    
        <span>
                <s:textfield name="entity.fbQuality"  id="entity_fbQuality"  maxlength="100"/>
    </span>
</div>
<div>
    <label for="entity_fbSecurity"> 安     全     情     况: </label>
    
        <span>
                <s:textfield name="entity.fbSecurity"  id="entity_fbSecurity"  maxlength="100"/>
    </span>
    
    <label for="entity_fbManner">文  明  施 工  情  况:</label>
    
        <span>
                <s:textfield name="entity.fbManner"  id="entity_fbManner"  maxlength="100"/>
    </span>
    
</div>
<div>
    <label for="entity_fbMmaterial">主材情况</label>
    
        <span>
                <s:textfield name="entity.fbMmaterial"  id="entity_fbMmaterial"  maxlength="100"/>
    </span>
    <label for="entity_fbSmaterial">配件情况</label>
    
        <span>
                <s:textfield name="entity.fbSmaterial"  id="entity_fbSmaterial"  maxlength="100"/>
    </span>
    
</div>
<div>
    <label for="entity_totalCost">预     计     定     额:</label>
    
        <span>
                <s:textfield name="entity.totalCost"  id="entity_totalCost"   cssClass="number"/>
    </span>
    
</div>

<div>
    <label for="entity_totalExpense">总费用</label>
    
        <span>
                <s:textfield name="entity.totalExpense"  id="entity_totalExpense"  cssClass="number" />
    </span>
    
    <label for="entity_currentExpense">易耗费用已发额</label>
    
        <span>
                <s:textfield name="entity.currentExpense"  id="entity_currentExpense"  cssClass="number" />
    </span>
    
</div>
<div>
    <label for="entity_memo">备注</label>
    
        <span>
                <s:textfield name="entity.memo"  id="entity_memo" maxlength="200"/>
    </span>
    
</div>

<%-- <div>
	<label for="project_wsCd">工程中心编码</label>
	
		<span>
				<s:textfield name="project.wsCd"  id="project_wsCd"  maxlength="4"/>
  	</span>
	
</div>
<div>
	<label for="project_projectNm">工程名称</label>
	
		<span>
				<s:textfield name="project.projectNm"  id="project_projectNm"  maxlength="100"/>
  	</span>
	
</div>


<div>
	<label for="project_startDate">起始日期</label>
	
		<span>
			<s:textfield name="project.startDate" id="project_startDate" cssClass="date" >
				<s:param name="value">
					<s:date name="project.startDate" format="yyyy-MM-dd" />
				</s:param>
			</s:textfield>
		</span>
		
	
</div>

<div>
	<label for="project_lastReportDt">最新报告日</label>
	
		<span>
			<s:textfield name="project.lastReportDt" id="project_lastReportDt" cssClass="date" >
				<s:param name="value">
					<s:date name="project.lastReportDt" format="yyyy-MM-dd" />
				</s:param>
			</s:textfield>
		</span>
		
	
</div>
<div>
	<label for="project_lastReportId">最新报告ID</label>
	
		<span>
				<s:textfield name="project.lastReportId"  id="project_lastReportId" />
  	</span>
	
</div> --%>
