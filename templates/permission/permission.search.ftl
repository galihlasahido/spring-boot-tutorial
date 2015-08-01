<@compress single_line=true>
    <#include "/layout/layout.ftl">
    <#import "/layout/pagination.ftl" as pagination />
${header}
<script language="JavaScript">
    $(document).ready(function(){
        $(".remove-data").each(function(){
            $(this).click(function(){
                if(confirm('Data will be deleted?')) return true;
                return false;
            });
        });
    });
</script>
<section class="content-header">
    <h1>${rc.getMessage('permission.title')} <small>${rc.getMessage('permission.title.list')}</small></h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="glyphicon glyphicon-home"></i> Home</a></li>
        <li class="active"><a href="${rc.getContextPath()}/role/permission/list">${rc.getMessage('permission.title.search')}</a></li>
    </ol>
</section>
<div>&nbsp;</div>
    <#if flashmessage??>
    <div class="alert alert-error"">
    <button type="button" class="close" data-dismiss="alert">&times;</button>
    <strong>${flashmessage!}</strong>
    </div>
    </#if>
<section class="content">
    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-header">
                    <div class="box-tools">
                        <form action="${rc.getContextPath()}/role/permission/search" method="get">
                            <div class="input-group">
                                <div class="input-group-btn">
                                    <button type="button" class="btn btn-warning dropdown-toggle" data-toggle="dropdown">Search option <span class="fa caret"></span></button>
                                    <ul class="dropdown-menu">
                                        <li><a href="#"><input type="radio" name="mode" value="label" checked="checked"> ${rc.getMessage('permission.data.label')}</a></li>
                                    </ul>
                                </div>
                                <input type="text" class="form-control" name="value" style="width: 250px">
                                <div class="input-group-btn pull-left">
                                    <button type="submit" class="btn btn-info glyphicon glyphicon-search"/>
                                </div><!-- /btn-group -->
                            </div>
                        </form>
                    </div>
                </div>
                <div class="box-body">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>&nbsp;<a href="#">${rc.getMessage('permission.data.idpermission')}</a></th>
                            <th>&nbsp;<a href="#">${rc.getMessage('permission.data.label')}</a></th>
                            <th>&nbsp;<a href="#">${rc.getMessage('permission.data.value')}</a></th>
                            <th>&nbsp;<a href="#">${rc.getMessage('general.edit')}</a></th>
                        </tr>
                        </thead>
                        <tbody>
                            <#list data as row>
                            <tr>
                                <td>&nbsp;${row.id!}</td>
                                <td>&nbsp;${row.label!}</td>
                                <td>&nbsp;${row.value!}</td>
                                <td>&nbsp;<a href="${rc.getContextPath()}/role/permission/delete/${row.id?c!}"><span class="glyphicon glyphicon-trash remove-data"></span></a>
                                    <a href="${rc.getContextPath()}/role/permission/edit/${row.id?c!}"><span class="glyphicon glyphicon-wrench"></span></a>
                                </td>
                            </tr>
                            </#list>

                        </tbody>

                    </table>
                </div><!-- /.box-body -->
                <div class="box-footer clearfix">
                    <ul class="pagination pagination-sm no-margin pull-right">
                        <@pagination.paging/>
                    </ul>
                </div>
            </div>

        </div>
    </div>
</section>
${footer}
</@compress>