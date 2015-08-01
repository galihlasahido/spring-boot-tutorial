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
    <h1>${rc.getMessage('role.title')} <small>${rc.getMessage('role.title.list')}</small></h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="glyphicon glyphicon-home"></i> Home</a></li>
        <li class="active"><a href="${rc.getContextPath()}/role/list">${rc.getMessage('role.title.search')}</a></li>
    </ol>
</section>
<div>&nbsp;</div>
<section class="content">
    <div class="row">
        <div class="col-xs-12">
            <div class="box">
                <div class="box-header">
                    <div class="box-tools">
                        <form action="${rc.getContextPath()}/role/search" method="get">
                            <div class="input-group">
                                <div class="input-group-btn">
                                    <button type="button" class="btn btn-warning dropdown-toggle" data-toggle="dropdown">Search option <span class="fa caret"></span></button>
                                    <ul class="dropdown-menu">
                                        <li><a href="#"><input type="radio" name="mode" value="idrole" <#if RequestParameters.mode?? && RequestParameters.mode=="idrole">checked="checked"</#if>> ${rc.getMessage('role.data.idrole')}</a></li>
                                        <li><a href="#"><input type="radio" name="mode" value="name" <#if RequestParameters.mode?? && RequestParameters.mode=="name">checked="checked"</#if>> ${rc.getMessage('role.data.name')}</a></li>
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
                            <th>&nbsp;<a href="#">${rc.getMessage('role.data.idrole')}</a></th>
                            <th>&nbsp;<a href="#">${rc.getMessage('role.data.description')}</a></th>
                            <th>&nbsp;<a href="#">${rc.getMessage('role.data.name')}</a></th>
                            <th>&nbsp;<a href="#">${rc.getMessage('general.edit')}</a></th>
                        </tr>
                        </thead>
                        <tbody>
                            <#list data as row>
                            <tr>
                                <td>&nbsp;${row.id!}</td>
                                <td>&nbsp;${row.description!}</td>
                                <td>&nbsp;${row.name!}</td>
                                <td>&nbsp;<a href="${rc.getContextPath()}/role/delete/${row.id!}"><span class="glyphicon glyphicon-trash remove-data"></span></a>
                                    <a href="${rc.getContextPath()}/role/edit/${row.id!}"><span class="glyphicon glyphicon-wrench"></span></a>
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