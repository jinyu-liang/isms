<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pt" uri="/is-tags"%>
<p style="width: 500px;">
	<label for="entity_title" style="width: 160px;">整改人ID</label> <span>
		<s:textfield name="entity.zguserid" id="entity_zguserid" cssClass="required"
			maxlength="200"  readOnly="true" size="50"/> </span>
</p>
<p style="width: 500px;">
	<label for="entity_projName" style="width: 160px;">整改人名字</label> <span>
		<s:textfield name="entity.zgusername" id="entity_zgusername"
			maxlength="100" readOnly="true" /> </span>
</p>
<p style="width: 500px;">
	<label for="entity_zgaddtime" style="width: 160px;">整改时间</label> <span>
    <input type="text" readonly="readonly"
     value="<s:date name="entity.zgaddtime" format="yyyy-MM-dd"/>"/>
		
	</span>
</p>
<s:if test="entity.fqstatus!=\"1\"">
<p style="width: 500px;">
	<label for="entity_zgjhfinishtime" style="width: 160px;">整改计划完成时间</label> <span>
    <input type="text" readonly="readonly"
     value="<s:date name="entity.zgjhfinishtime" format="yyyy-MM-dd"/>"/>		
	</span>
</p>
</s:if>
<s:if test="entity.fqstatus==\"1\"">
<p style="width: 500px;">
	<label for="entity_zgjhfinishtime" style="width: 160px;">整改计划完成时间</label> <span>
    <span>
    <s:textfield name="entity.zgjhfinishtime" cssClass="required date" id="entity_zgjhfinishtime" dateFmt="yyyy-MM-dd" readonly="true">
	<s:param name="value">
		<s:date name="entity.zgjhfinishtime" format="yyyy-MM-dd" />
	</s:param>
	</s:textfield> <a class="inputDateButton" href="javascript:;">选择</a>
	</span>
</p>
</s:if>

<p style="width: auto;">
	<label for="entity_zgdesc" style="width: 160px;">整改计划</label>
	<span> 
		<s:if test="entity.fqstatus==\"1\"">
		<s:textarea name="entity.zgdesc" cols="50" rows="3" id="entity_zgdesc" maxlength="100" cssClass="required"/>
		</s:if>
		<s:if test="entity.fqstatus!=\"1\"">
		<s:textarea readonly="readonly"  name="entity.zgdesc" cols="50" rows="3" id="entity_zgdesc" maxlength="100" />
		</s:if>
	</span>
</p>


<p style="width: 380px;">

<p style="width: 500px;">
	<label for="entity_zgfinishtime" style="width: 160px;">整改计划完成时间</label> 
	<s:if test="entity.fqstatus!=\"2\"">
	<span>
    	<input type="text" readonly="readonly" value="<s:date name="entity.zgfinishtime" format="yyyy-MM-dd"/>"/>		
	</span>
	</s:if>
	<s:if test="entity.fqstatus==\"2\"">
	<span>
	    <s:textfield name="entity.zgfinishtime" cssClass="required date" id="entity_zgfinishtime" dateFmt="yyyy-MM-dd" readonly="true">
		<s:param name="value">
			<s:date name="entity.zgfinishtime" format="yyyy-MM-dd" />
		</s:param>
		</s:textfield> <a class="inputDateButton" href="javascript:;">选择</a>
	</span>
	</s:if>
</p>

<p style="width: 500px;">
	<label for="entity_isovertime" style="width: 160px;">整改是否超过计划完成时间</label>
	<span class="labelnarrow"><s:radio
			id="entity_isovertime" name="entity.isovertime"
			list="#{'0':'否','1':'是'}" value="entity.isovertime" cssClass="required">
		</s:radio> </span>
</p>


<p style="width: auto;">
	<label for="entity_zgfinishdesc" style="width: 160px;">整改人完成处理说明</label>
	<span> 
		<s:if test="entity.fqstatus==\"2\"">
		<s:textarea name="entity.zgfinishdesc" cols="50" rows="3" id="entity_zgfinishdesc" maxlength="100" cssClass="required"/>
		</s:if>
		<s:if test="entity.fqstatus!=\"2\" ">
		<s:textarea readonly="readonly"  name="entity.zgfinishdesc" cols="50" rows="3" id="entity_zgfinishdesc" maxlength="100" />
		</s:if>
	</span>
</p>

