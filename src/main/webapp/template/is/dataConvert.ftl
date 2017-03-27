<#-- 设置default值-->
<#assign defalutValue = parameters.defaultValue!""/>
<#-- 设置withCode值-->
<#assign withCode = parameters.withCode!"false"/>
<#-- 设置separator值-->
<#assign separator = parameters.separator!"|"/>
<#if parameters.nameValue?exists>
	<#list parameters.list as item>
		<#assign itemKey = item["${parameters.listKey}"]/>
		<#assign itemValue = item["${parameters.listValue}"]/>
		<#if tag.contains(parameters.nameValue, itemKey) == true>
			<#if withCode == "true">
				${itemKey}${separator}${itemValue}<#t/>
			<#else>
				${itemValue}<#t/>
			</#if>
			<#assign success = "true"/>
			<#break>
		</#if>
	</#list>
	<#-- 转换失败-->
	<#if !success?exists>
		${defalutValue}<#t/>
	</#if>
</#if>