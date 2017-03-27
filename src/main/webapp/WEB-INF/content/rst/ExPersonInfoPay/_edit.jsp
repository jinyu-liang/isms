<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<div>
	<label for="entity_name">姓名</label>
	
		<span>
		 <s:textfield name="entity.name"  id="entity_name"  validate="{maxlength:50}" readonly="true"/>
  	</span>
	
</div>
<div>
	<label for="entity_identy_card_code">身份证号</label>
	
		<span>
		<s:if test="entity.identy_card_code>4">
			<s:textfield name="entity.identy_card_code"  id="entity_identy_card_code"  readonly="true"/>
		</s:if>
		<s:else>
		<s:textfield name="entity.identy_card_code"  id="entity_identy_card_code"  validate="{maxlength:40}"/>
		</s:else> 				
  	    </span>
	
</div>
<div>
	<label for="entity_telephone">联系电话</label>
	
		<span>
				<s:textfield name="entity.telephone"  id="entity_telephone"  validate="{maxlength:20}" readonly="true"/>
  	</span>
	
</div>

<div>
	<label for="entity_work_ws_nm">工地名称</label>
	
		<span>
				<s:textfield name="entity.work_ws_nm"  id="entity_work_ws_nm"  validate="{maxlength:444}" readonly="true"/>
  	</span>
	
</div>

<div>
	<label for="entity_team_name">施工队名</label>
	
		<span>
				<s:textfield name="entity.team_name"  id="entity_team_name"  validate="{maxlength:300}" readonly="true"/>
  	</span>
	
</div>

<div>
	<label for="entity_start_time_am">上午开始日期/按量开始时间</label> <span> <s:textfield
			name="entity.start_time_am" cssClass="required date" id="entity_start_time_am"
			dateFmt="yyyy-MM-dd HH:mm:ss" >
			<s:param name="value">
				<s:date name="entity.start_time_am" format="yyyy-MM-dd HH:mm:ss" />
			</s:param>
		</s:textfield> <a class="inputDateButton" href="javascript:;">选择</a></span>
</div>

<div>
	<label for="entity_end_time_am">上午结束日期/按量结束时间</label> <span> <s:textfield
			name="entity.end_time_am" cssClass="required date" id="entity_end_time_am"
			dateFmt="yyyy-MM-dd HH:mm:ss" >
			<s:param name="value">
				<s:date name="entity.end_time_am" format="yyyy-MM-dd HH:mm:ss" />
			</s:param>
		</s:textfield> <a class="inputDateButton" href="javascript:;">选择</a></span>
</div>
<div>
	<label for="entity_work_timer">完成工作量</label>
	
		<span>
				<s:textfield name="entity.work_timer"  id="entity_work_timer"  validate="{maxlength:32}"/>
  	</span>
	
</div>

<div>
	<label for="entity_start_time_pm">下午开始日期</label> <span> <s:textfield
			name="entity.start_time_pm" cssClass="required date" id="entity_start_time_pm"
			dateFmt="yyyy-MM-dd HH:mm:ss" >
			<s:param name="value">
				<s:date name="entity.start_time_pm" format="yyyy-MM-dd HH:mm:ss" />
			</s:param>
		</s:textfield> <a class="inputDateButton" href="javascript:;">选择</a></span>
</div>

<div>
	<label for="entity_end_time_pm">下午结束日期</label> <span> <s:textfield
			name="entity.end_time_pm" cssClass="required date" id="entity_end_time_pm"
			dateFmt="yyyy-MM-dd HH:mm:ss" >
			<s:param name="value">
				<s:date name="entity.end_time_pm" format="yyyy-MM-dd HH:mm:ss" />
			</s:param>
		</s:textfield> <a class="inputDateButton" href="javascript:;">选择</a></span>
</div>

<div>
	<label for="entity_start_time_other">加班开始日期</label> <span> <s:textfield
			name="entity.start_time_other" cssClass="required date" id="entity_start_time_other"
			dateFmt="yyyy-MM-dd HH:mm:ss" >
			<s:param name="value">
				<s:date name="entity.start_time_other" format="yyyy-MM-dd HH:mm:ss" />
			</s:param>
		</s:textfield> <a class="inputDateButton" href="javascript:;">选择</a></span>
</div>

<div>
	<label for="entity_end_time_other">加班结束日期</label> <span> <s:textfield
			name="entity.end_time_other" cssClass="required date" id="entity_end_time_other"
			dateFmt="yyyy-MM-dd HH:mm:ss" >
			<s:param name="value">
				<s:date name="entity.end_time_other" format="yyyy-MM-dd HH:mm:ss" />
			</s:param>
		</s:textfield> <a class="inputDateButton" href="javascript:;">选择</a></span>
</div>
<div>
	<label for="entity_remark">备注</label>
	
		<span>
				<s:textfield name="entity.remark"  id="entity_remark" />
  	</span>
	
</div>


<script >
	function changeHidenValue(elem){
    	var optionVal= $(elem).find("option:selected").text();  // 取到选中的listValue(Option)的值
    $("#workTypeName").val(optionVal);
}
</script>
