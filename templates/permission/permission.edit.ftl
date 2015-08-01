<@compress single_line=false>
    <#include "/layout/layout.ftl">
${header}
<section class="content-header">
    <h1>${rc.getMessage('permission.title')} <small>${rc.getMessage('permission.title.insert')}</small></h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="glyphicon glyphicon-home"></i> Home</a></li>
        <li class="active"><a href="${rc.getContextPath()}/role/permission/list">${rc.getMessage('permission.title.insert')}</a></li>
    </ol>
</section>
<section class="content">
    <div class="row">
        <div class="col-xs-12">
            <div class="box box-primary">
                <div class="box-header">
                    <div>&nbsp;</div>
                    <#if message??>
                        <div class="alert alert-danger" role="alert">${message}</div>
                    </#if>
                    <#if RequestParameters.status??>
                        <div class="alert alert-danger" role="alert">${rc.getMessage('general.edited')}</div>
                    </#if>
                </div><!-- /.box-header -->
                <!-- form start -->
                <form role="form" action="${rc.getContextPath()}/role/permission/action.edit/${data.id!}" method="post">
                    <div class="box-body">
                        <div class="form-group">
                            <label for="label">${rc.getMessage('permission.data.label')}</label>
                            <input type="text" name="label" class="form-control" id="label" value="${data.label!}">
                        </div>
                        <div class="form-group">
                            <label for="value">${rc.getMessage('permission.data.value')}</label>
                            <input type="text" name="value" class="form-control" id="value" value="${data.value!}">
                        </div>
                        <div class="box-footer">
                            <input type="hidden" name="id" id="id" value="${data.id!}">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <button type="submit" class="btn btn-primary">${rc.getMessage('general.submit')}</button>
                        </div>
                </form>
            </div>
        </div>
    </div>
</section>
${footer}
</@compress>