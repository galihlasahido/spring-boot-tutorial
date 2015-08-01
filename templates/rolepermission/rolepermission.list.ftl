<@compress single_line=false>
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

    $.ajax({
        async:false,
        type: "GET",
        url: "${rc.getContextPath()}/role/rolepermission/rolePermissionJSON"
    }).done(function( data ) {
        var prePopulate = $.parseJSON(data);
        $.each( prePopulate, function( key, val ) {
            var simple = $("#tag-"+val[0]+" ~ td.role-"+val[1]).find(".simple");
            var attrsimple = simple.attr('alt');

            simple.attr("checked", true);
            simple.prop('alt', attrsimple+"#"+val[2]);
        });
    });

    $(".status-tag").each(function() {
        var tag = $(this);
        var id = "";
        tag.click(function(){
            var checked = tag.prop('checked');
            var status = false;

            console.log("checked >>>"+checked);

            if(checked==true) {
                status = true;
            }

            $.ajax({
                async:false,
                type: "POST",
                data: {'data':tag.attr('alt'), 'status':status},
                url: "${rc.getContextPath()}/role/rolepermission/updateRolePermissionJSON"
            }).done(function( data ) {
                id = data;
            });

            var attrsimple = tag.attr('alt').split("#");
            tag.prop('alt', attrsimple[0]+"#"+attrsimple[1]+"#"+id);

        });
    });


});
</script>
<section class="content-header">
    <h1>${rc.getMessage('rolepermission.title')} <small>${rc.getMessage('rolepermission.title.list')}</small></h1>
    <ol class="breadcrumb">
        <li><a href="#"><i class="glyphicon glyphicon-home"></i> Home</a></li>
        <li class="active"><a href="${rc.getContextPath()}/role/rolepermission/list">${rc.getMessage('rolepermission.title.list')}</a></li>
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
                    <div class="box-tools">&nbsp;</div>
                </div>
                <div class="box-body">
                    <table id="reservations" class="table table-bordered">
                        <thead>
                            <tr>
                                <th>
                                    <span class="pull-left">${rc.getMessage('rolepermission.title')}</span>
                                        <br/>
                                    <span class="pull-right">${rc.getMessage('role.title')}</span>
                                </th>
                                <#list role as row>
                                    <th>${row.id!}</th>
                                </#list>
                            </tr>
                        </thead>
                        <tbody>
                            <#list permission as prow>
                            <tr>
                                <td id="tag-${prow.id!}">${prow.label!}</td>
                                <#list role as rrow>
                                    <td class="role-${rrow.id!}">&nbsp;
                                        <input type="checkbox" class="status-tag simple" name="status-tag" alt="${prow.id!}#${rrow.id!}"/>
                                    </td>
                                </#list>
                            </tr>
                            </#list>
                        </tbody>
                    </table>
                </div><!-- /.box-body -->
                <div class="box-footer clearfix">
                    <ul class="pagination pagination-sm no-margin pull-right">
                        <#--<@pagination.paging/>-->
                    </ul>
                </div>
            </div>

        </div>
    </div>
</section>
${footer}
</@compress>