<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pt" uri="/is-tags" %>
<%@ page import="java.util.Date" %>	
<s:hidden id="entity_ws_cd" name="entity.ws_cd"></s:hidden>
<table class="list" width="100%" style="border-collapse:separate; border-spacing:0px 1px;">
<tr>
	<th colspan="4" align="center" >工作日志信息</th>
</tr>
<tr>
	<td><label   style="letter-spacing: 6px;">登录名</label></td>
	<td><span>
		<s:textfield name="entity.fqusername" id="entity_fqusername" cssClass="required" maxlength="50" readonly="true"/>
	</span></td>
	
		<td><label  style="letter-spacing:1em;">工号</label></td>
	<td><span>
		<s:textfield name="entity.fquserid" id="entity_fquserid" cssClass="required" maxlength="50" readonly="true"/>
	</span></td>
</tr>
<tr>
	<td><label   style="letter-spacing: 6px;">开始时间</label></td>
	<td>
		<span>
		    <s:textfield name="entity.starttime" cssClass="required date" id="entity_starttime" dateFmt="yyyy-MM-dd hh:mm:ss" readonly="true">
			<s:param name="value">
				<s:date name="entity.starttime" format="yyyy-MM-dd hh:mm:ss" />
			</s:param>
			</s:textfield> <a class="inputDateButton" href="javascript:;">选择</a>
		</span>
	</td>
	<td><label  style="letter-spacing:1em;">结束时间</label></td>
	<td>
		<span>
		    <s:textfield name="entity.endtime" cssClass="required date" id="entity_endtime" dateFmt="yyyy-MM-dd hh:mm:ss" readonly="true">
			<s:param name="value">
				<s:date name="entity.endtime" format="yyyy-MM-dd hh:mm:ss" />
			</s:param>
			</s:textfield> <a class="inputDateButton" href="javascript:;">选择</a>
		</span>
	</td>
</tr>
 <tr>
  	<td><label   style="letter-spacing: 6px;">部门名称</label></td>
	<td><span>
		<s:select list="userDepartList" name="entity.ws_name" listKey="dictName" value="entity.ws_name" listValue="dictName" headerKey="" headerValue="请选择..." cssClass="required" readonly="true"></s:select>
	</span></td>
	
	<td><label  style="letter-spacing:1em;">天气</label></td>
	<td><span>
		<s:select list="#{'':'请选择状态','晴':'晴','阴':'阴','多云':'多云','雨':'雨' }" name="entity.wheather"
			cssClass="required" id="entity_dwheather">
		</s:select>
	</span></td>
</tr>
 <tr>
<tr>
	<td><label   style="letter-spacing: 6px;">日志内容</label></td>
	<td colspan="3"><span>
		<s:textarea name="entity.jobcontent" cols="90" rows="5" id="entity_jobcontent"  />
	</span></td>
</tr>
<tr>
	<td><label   style="letter-spacing: 6px;">当日工程形象进度</label></td>
	<td><span>
		<s:textfield name="entity.schedule" id="entity_schedule"   />
	</span></td>
	
		<td><label  style="letter-spacing:1em;">与计划对比</label></td>
	<td><span>
		<s:textfield name="entity.Isplan" id="entity_Isplan"   />
	</span></td>
</tr>
	
<tr>
	<td><label>人、材、机组织情况（白天）</label></td>
	<td colspan="3"><span>
		<s:textarea name="entity.daySituation" cols="90" rows="2" id="entity_daySituation"  />
	</span></td>
</tr>
<tr>
	<td><label>人、材、机组织情况（晚上）</label></td>
	<td colspan="3"><span>
		<s:textarea name="entity.nightSituation" cols="90" rows="2" id="entity_nightSituation"  />
	</span></td>
</tr>
<tr>
	<td><label>质量情况或问题</label></td>
	<td colspan="3"><span>
		<s:textarea name="entity.qualitySituation" cols="90" rows="2" id="entity_qualitySituation"  />
	</span></td>
</tr>
<tr>
	<td><label>质量情况或问题处理措施</label></td>
	<td colspan="3"><span>
		<s:textarea name="entity.qualityMeasures" cols="90" rows="2" id="entity_qualityMeasures"  />
	</span></td>
</tr>
<tr>
	<td><label>安全、文明施工情况或问题</label></td>
	<td colspan="3"><span>
		<s:textarea name="entity.SecuritySituation" cols="90" rows="2" id="entity_SecuritySituation"  />
	</span></td>
</tr>
<tr>
	<td><label>安全、文明施工情况或问题处理措施</label></td>
	<td colspan="3"><span>
		<s:textarea name="entity.SecurityMeasures" cols="90" rows="2" id="entity_SecurityMeasures"  />
	</span></td>
</tr>
<tr>
	<td><label>进场材料及出现的问题</label></td>
	<td colspan="3"><span>
		<s:textarea name="entity.materialSituation" cols="90" rows="2" id="entity_materialSituation"  />
	</span></td>
</tr>
<tr>
	<td><label>进场材料及出现的问题处理措施</label></td>
	<td colspan="3"><span>
		<s:textarea name="entity.materialMeasures" cols="90" rows="2" id="entity_materialMeasures"  />
	</span></td>
</tr>
<tr>
	<td><label>设计图纸中出现的问题</label></td>
	<td colspan="3"><span>
		<s:textarea name="entity.DrawingSituation" cols="90" rows="2" id="entity_DrawingSituation"  />
	</span></td>
</tr>
<tr>
	<td><label>设计图纸中出现的问题处理措施</label></td>
	<td colspan="3"><span>
		<s:textarea name="entity.DrawingMeasures" cols="90" rows="2" id="entity_DrawingMeasures"  />
	</span></td>
</tr>
<tr>
	<td><label>产生变更与签证问题</label></td>
	<td colspan="3"><span>
		<s:textarea name="entity.VisaSituation" cols="90" rows="2" id="entity_VisaSituation"  />
	</span></td>
</tr>
<tr>
	<td><label>产生变更与签证问题原因分析</label></td>
	<td colspan="3"><span>
		<s:textarea name="entity.VisaMeasures" cols="90" rows="2" id="entity_VisaMeasures"  />
	</span></td>
</tr>
<tr>
	<td><label>其他</label></td>
	<td colspan="3"><span>
		<s:textarea name="entity.OtherSituation" cols="90" rows="2" id="entity_OtherSituation"  />
	</span></td>
</tr>
<tr>
	<td><label>问题处理情况跟踪</label></td>
	<td colspan="3"><span>
		<s:textarea name="entity.problemTrack" cols="90" rows="2" id="entity_problemTrack"  />
	</span></td>
</tr>
<tr>
	<td><label>问题处理情况状态</label></td>
	<td colspan="3"><span class="labelnarrow">
		<s:radio id="entity_status" name="entity.status" cssClass="required"
			list="#{'1':'问题处理中','2':'处理完成'}" value="entity.status"></s:radio>
	</span></td>
</tr>
</table>
