
<#--
 * Assign the current data to the object called "paginationData" if set.
-->
<#setting url_escaping_charset='ISO-8859-1'>

<#if paginationData??>
<#assign data = paginationData />

<#--
 * Bind pagination data to the current data set these macros are using.
-->
<#macro bind paginationData>
    <#assign data = paginationData />
</#macro>

<#assign size    = "" />
<#assign sort    = "" />

<#if RequestParameters.size??>
    <#assign size    = "&size="+RequestParameters.size />
<#else>
    <#assign size    = "" />
</#if>

<#if RequestParameters.sort??>
    <#assign sort    = "&sort="+RequestParameters.sort />
<#else>
    <#assign sort    = "" />
</#if>

<#if RequestParameters.mode??>
    <#assign mode    = "&mode="+RequestParameters.mode />
<#else>
    <#assign mode    = "" />
</#if>

<#if RequestParameters.value??>
    <#assign value    = "&value="+RequestParameters.value />
<#else>
    <#assign value    = "" />
</#if>

<#assign baseUrl    = data.baseUrl+"?page=0"+size+sort+mode+value />
<#assign prevUrl    = data.baseUrl+"?page="+(data.currentIndex - 1)+size+sort+mode+value />
<#assign nextUrl    = data.baseUrl+"?page="+(data.currentIndex + 1)+size+sort+mode+value />
<#assign lastUrl    = data.baseUrl+"?page="+(data.deploymentLog.totalPages-1)+size+sort+mode+value />

<#macro paging>
    <#if (data.deploymentLog.totalPages>0) >
        <#if data.currentIndex == 0>
            <li class="disabled"><a href="#">&lt;&lt;</a></li>
            <li class="disabled"><a href="#">&lt;</a></li>
        <#else>
            <li><a href="${baseUrl}">&lt;&lt;</a></li>
            <li><a href="${prevUrl}">&lt;</a></li>
        </#if>
    </#if>

    <#if (data.deploymentLog.totalPages>0) >
    <#list (data.beginIndex-1)..(data.endIndex-1) as i>
        <#assign pageUrl    = data.baseUrl+"?page="+i+size+sort+mode+value />
        <#if i==data.currentIndex>
            <li class="active"><a href="${pageUrl}">&nbsp;${i+1}</a></li>
        <#else>
            <li><a href="${pageUrl}">&nbsp;${i+1}</a></li>
        </#if>
    </#list>
    </#if>

    <#if (data.currentIndex == (data.deploymentLog.totalPages-1))>
        <li class="disabled"><a href="#">&gt;</a></li>
        <li class="disabled"><a href="#">&gt;&gt;</a></li>
    <#else>
        <#if (data.deploymentLog.totalPages>0) >
            <li><a href="${nextUrl}">&gt;</a></li>
            <li><a href="${lastUrl}">&gt;&gt;</a></li>
        </#if>
    </#if>
</#macro>
</#if>