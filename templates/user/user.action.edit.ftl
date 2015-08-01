<@compress single_line=false>
    <#include "/layout/layout.ftl">
${header}
<section class="content-header">
    <h1>${rc.getMessage('user.title')} <small>${rc.getMessage('user.title.edit')}</small></h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="glyphicon glyphicon-home"></i> Home</a></li>
        <li class="active"><a href="${rc.getContextPath()}/user/list">${rc.getMessage('user.title.edit')}</a></li>
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
                <form role="form" action="${rc.getContextPath()}/user/action.edit/${RequestParameters.id!}" method="post">
                    <div class="box-body">
                        <div class="form-group">
                            <label for="username">${rc.getMessage('user.data.username')}</label>
                            <input type="text" name="username" class="form-control" id="username" value="${RequestParameters.username!}">
                        </div>
                        <div class="form-group">
                            <label for="fullname">${rc.getMessage('user.data.fullname')}</label>
                            <input type="text" name="fullname" class="form-control" id="fullname" value="${RequestParameters.fullname!}">
                        </div>
                        <div class="form-group">
                            <label for="roles">${rc.getMessage('user.data.idrole')}</label>
                            <select id="roles" name="roles" class="form-control">
                                <#list roles as row>
                                    <option value="${row.id!}" <#if row.id==RequestParameters.roles.id>selected="selected"</#if>>
                                    ${row.name!}
                                    </option>
                                </#list>
                            </select>
                        </div>

                        <div class="form-group">
                            <label for="branch">${rc.getMessage('user.data.idbranch')}</label>
                            <select id="branch" name="branch" class="form-control">
                                <#list branch as row>
                                    <option value="${row.id!}" <#if row.id==RequestParameters.branch.id>selected="selected"</#if>>${row.branchName!}</option>
                                </#list>
                            </select>
                        </div>
                        <div class="active">
                            <label for="branch">${rc.getMessage('user.data.active')}</label>
                            <input id="active" type="checkbox" name="active" style="position: absolute; opacity: 0;" <#if RequestParameters.active?? && RequestParameters.active?string == "on">checked="checked"</#if>>
                        </div>
                    </div>
                    <div class="box-footer">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <input type="hidden" name="id" value="${data.id!}"/>
                        <button type="submit" class="btn btn-primary">${rc.getMessage('general.submit')}</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>
${footer}
</@compress>