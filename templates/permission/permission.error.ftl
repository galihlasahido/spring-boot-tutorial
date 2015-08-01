<@compress single_line=false>
    <#include "/layout/layout.ftl">
${header}
<ol class="breadcrumb">
    <li><a href="${rc.getContextPath()}">Home</a></li>
    <li><a href="${rc.getContextPath()}/user/list">${rc.getMessage('user.title.error')}</a></a></li>
</ol>
<div>&nbsp;</div>
<h1>${message!}</h1>
${footer}
</@compress>