<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pt" uri="/is-tags"%>
<p style="width: 500px;">
	<label for="entity_title" style="width: 80px;">发起人ID</label> <span>
		<s:textfield name="entity.fquserid" id="entity_fquserid" cssClass="required"
			maxlength="200"  readOnly="true" size="50"/> </span>
</p>
<p style="width: 500px;">
	<label for="entity_projName" style="width: 80px;">发起人名字</label> <span>
		<s:textfield name="entity.fqusername" id="entity_fqusername"
			maxlength="100" readOnly="true" /> </span>
</p>
<p style="width: 500px;">
	<label for="entity_fqaddtime" style="width: 80px;">发起时间</label> <span>
    <input type="text" readonly="readonly"
     value="<s:date name="entity.fqaddtime" format="yyyy-MM-dd"/>"/>
		
	</span>
</p>
<p style="width: 500px;">
	<label for="entity_memo" style="width: 80px;">备注</label> 
			<span>
			<s:textarea name="entity.fqdesc" cols="50"
			rows="3" id="entity_fqdesc" maxlength="100" readOnly="true"/>
			</span>
</p> 
<p style="width: 380px;">
</p>

<p style="width: 500px;">
	<label for="entity_fqyqfinishtime" style="width: 80px;">审核完成时间</label> 
	<s:if test="entity.fqstatus!=\"3\"">
	<span>
    	<input type="text" readonly="readonly" value="<s:date name="entity.fqyqfinishtime" format="yyyy-MM-dd"/>"/>		
	</span>
	</s:if>
	<s:if test="entity.fqstatus==\"3\"">
	<span>
	    <s:textfield name="entity.fqyqfinishtime" cssClass="required date" id="entity_fqyqfinishtime" dateFmt="yyyy-MM-dd" readonly="true">
		<s:param name="value">
			<s:date name="entity.fqyqfinishtime" format="yyyy-MM-dd" />
		</s:param>
		</s:textfield> <a class="inputDateButton" href="javascript:;">选择</a>
	</span>
	</s:if>
</p>

<p style="width: auto;">
	<label for="entity_fqrqdesc" style="width: 80px;">审核意见</label>
	<span> 
		<s:if test="entity.fqstatus==\"2\"">
		<s:textarea name="entity.fqrqdesc" cols="50" rows="3" id="entity_fqrqdesc" maxlength="100" cssClass="required"/>
		</s:if>
		<s:if test="entity.fqstatus!=\"2\" ">
		<s:textarea readonly="readonly"  name="entity.fqrqdesc" cols="50" rows="3" id="entity_fqrqdesc" maxlength="100" />
		</s:if>
	</span>
</p>
