<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pt" uri="/is-tags"%>
<p>
    <label for="projectEntity_projectNm">工地中心名称</label> <span> <pt:dictshow name="projectEntity.wsCd" listValue="dictName" listKey="dictCode"
            list="workShopList"></pt:dictshow>
    </span>
</p>
<p>
    <label for="projectEntity_lastReportDt">最新报告日</label> <span> <s:textfield name="projectEntity.lastReportDt"  readonly="true"
            dateFmt="yyyy-MM-dd">
            <s:param name="value">
                <s:date name="projectEntity.lastReportDt" format="yyyy-MM-dd" />
            </s:param>
        </s:textfield>
    </span>
</p>
<p>
    <label for="projectEntity_projectNm">工程名称</label> <span> <s:textfield name="projectEntity.projectNm" id="projectEntity_projectNm" cssClass="required"
            maxlength="100" />
    </span>
</p>
<p>
    <label for="projectEntity_startDate">实际进驻日期</label> <span> <s:textfield name="projectEntity.startDate" cssClass="date" readonly="true"
            dateFmt="yyyy-MM-dd">
 <%--            <s:param name="value">
                <s:date name="projectEntity.startDate" format="yyyy-MM-dd" />
            </s:param> --%>
        </s:textfield><a class="inputDateButton" href="javascript:;">选择</a>
    </span>
</p>
<p>
    <label for="projectEntity_contractStartDate">合同要求进驻日期</label> <span><s:textfield name="projectEntity.contractStartDate" cssClass="date"
            readonly="true" dateFmt="yyyy-MM-dd">
           <%--  <s:param name="value">
                <s:date name="projectEntity.contractStartDate" format="yyyy-MM-dd" />
            </s:param> --%>
        </s:textfield><a class="inputDateButton" href="javascript:;">选择</a> </span>
</p>
<p>
    <label for="projectEntity_weight">净重(吨)</label> <span>
        <s:if test="projectEntity.weight!=null&&projectEntity.weight!=''">
    	    <input type="text" name="projectEntity.weight" id="projectEntity_weight" class="number" value="<s:text name='format.number'><s:param value='projectEntity.weight'/></s:text>"/>
    	</s:if>
        <s:else>
    	    <input type="text" name="projectEntity.weight" id="projectEntity_weight" class="number" />
    	</s:else>    	
    	
    </span>
</p>
<p style="width: auto;">
    <label for="projectEntity_contractOtherReq">合同其他要求</label> <span> <s:textarea name="projectEntity.contractOtherReq"
            id="projectEntity_contractOtherReq" maxlength="500" cols="45" rows="4" />
    </span>
</p>
