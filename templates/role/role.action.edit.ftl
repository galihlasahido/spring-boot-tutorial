<@compress single_line=false>
    <#include "/layout/layout.ftl">
${header}
<section class="content-header">
    <h1>${rc.getMessage('role.title')} <small>${rc.getMessage('role.title.edit')}</small></h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="glyphicon glyphicon-home"></i> Home</a></li>
        <li class="active"><a href="${rc.getContextPath()}/role/list">${rc.getMessage('role.title.insert')}</a></li>
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
                <form role="form" action="${rc.getContextPath()}/role/action.edit/${RequestParameters.id!}" method="post">
                    <div class="box-body">
                        <div class="form-group">
                            <label for="description">${rc.getMessage('role.data.description')}</label>
                            <input type="text" name="description" class="form-control" id="description" value="<#if RequestParameters.id??>${RequestParameters.id!}</#if>">
                        </div>

                        <div class="form-group">
                            <label for="name">${rc.getMessage('role.data.name')}</label>
                            <input type="text" name="name" class="form-control" id="name" value="<#if RequestParameters.name??>${RequestParameters.name!}</#if>">
                        </div>

                        <div class="box-footer">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <input type="hidden" name="id" value="${RequestParameters.id!}"/>
                            <button type="submit" class="btn btn-primary">${rc.getMessage('general.submit')}</button>
                        </div>
                </form>
            </div>
        </div>
    </div>
</section>
${footer}
</@compress>