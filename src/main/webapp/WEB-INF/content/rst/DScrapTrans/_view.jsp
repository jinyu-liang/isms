<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="pt" uri="/is-tags"%>
<div>
    <label for="entity_fromWsCd">工作中心</label> <span>   <s:textfield name="entity.fromWsCd" readonly="true"/>  </span>
     <label for="entity_sender">发货人</label> <span> <s:textfield name="entity.sender" readonly="true"/> </span>
    
</div>


<div>
     <label for="entity_toWsCd">收货单位</label> <span><s:textfield name="entity.toWsCd" readonly="true"/> </span>
   <label for="entity_receiver">收货人</label> <span> <s:textfield name="entity.receiver" readonly="true"/> </span>
</div>




<div>
    
     <label for="entity_tCompanyNm">运输公司</label> <span> <s:textfield name="entity.tCompanyNm" readonly="true"/> </span>
     <label for="entity_driver">司机</label> <span> <s:textfield name="entity.driver" readonly="true"/> </span>
</div>





<div>
     <label for="entity_deliveryTm">发货时间</label> <span><input type="text" value="<s:date name='entity.deliveryTm'  format='yyyy-MM-dd HH:mm:ss'/>" readonly="readonly"/>  </span>
     <label for="entity_weight">净重(吨)</label> <span> <s:textfield name="entity.weight" readonly="true" /> </span>
</div>






<div>
     <label for="entity_arrivalTm">到货时间</label> <span>
        <s:textfield
            name="entity.arrivalTm" cssClass="required date" id="entity_arrivalTm"
            dateFmt="yyyy-MM-dd HH:mm:ss" readonly="true">
            <s:param name="value">
                <s:date name="entity.arrivalTm" format="yyyy-MM-dd HH:mm:ss" />
            </s:param>
        </s:textfield> <a class="inputDateButton" href="javascript:;">选择</a>
        
        </span>
</div>
<s:if test="sessionUser.username!=entity.receiver">
    <div>
      <label for="entity_statusCd">状态</label> <span>
        <s:if test="entity.statusCd==0"><font style="color:#6495ED;font-size: 15px">运输中</font></s:if>
        <s:elseif test="entity.statusCd==\"Yes\""><font style="color:green;font-size: 15px"">已收货</font></s:elseif>
        <s:elseif test="entity.statusCd==\"No\""><font style="color: red;font-size: 15px"">有问题</font></s:elseif>
     </span>
    </div>
    <div>
        <label for="entity_memo">备注</label> 
        <span> 
            <s:textarea id="entity_memo" cols="61" rows="4" name="entity.memo" readonly="true"/>  
        </span>
    </div>
</s:if>
<s:else>
    <div>
    	<label for="entity_statusCd" style="width: 120px;">是否同意收货</label> <span class="labelnarrow"> <s:radio
			id="entity.statusCd" name="entity.statusCd" cssClass="required"
			list="#{'Yes':'同意','No':'有问题'}"> 
		</s:radio> </span>
    </div>
    <div>
        <label for="entity_memo">备注</label> 
        <span> 
            <s:textarea id="entity_memo" cols="61" rows="4" name="entity.memo" /> 
        </span>
    </div>
</s:else>
