<#import "/spring.ftl" as spring/>
<#assign sec=JspTaglibs["http://www.springframework.org/security/tags"] />
<#assign header>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${rc.getMessage('app.name')} | ${rc.getMessage('app.version')}</title>
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
    <meta name="_csrf" content="${_csrf.token}"/>
    <!-- default header name is X-CSRF-TOKEN -->
    <meta name="_csrf_header" content="${_csrf.headerName}"/>

    <link href="${rc.getContextPath()}/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <link href="${rc.getContextPath()}/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
    <!-- Theme style -->
    <link href="${rc.getContextPath()}/css/AdminLTE.css" rel="stylesheet" type="text/css" />

    <!-- ./wrapper -->
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="${rc.getContextPath()}/js/jquery.min.js"></script>
    <script src="${rc.getContextPath()}/js/bootstrap.min.js" type="text/javascript"></script>

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="${rc.getContextPath()}/js/html5shiv.js"></script>
    <script src="${rc.getContextPath()}/js/respond.min.js"></script>
    <![endif]-->
    <script language="JavaScript">
        $(document).ready(function(){
            var list = window.location.href.toString().split(window.location.host)[1];
            var expList = list.split("/");
            $("#"+expList[1]).addClass("active");

            var header = $("meta[name='_csrf_header']").attr("content");
            var token = $("meta[name='_csrf']").attr("content");

            $(document).ajaxSend(function(e, xhr, options) {
                xhr.setRequestHeader(header, token);
            });
        });
    </script>
</head>
<body class="skin-blue">
<!-- header logo: style can be found in header.less -->
<header class="header">
<a href="index.html" class="logo">
    <!-- Add the class icon to your logo image or logo icon to add the margining -->
    Simulator
</a>
<!-- Header Navbar: style can be found in header.less -->
<nav class="navbar navbar-static-top" role="navigation">
<!-- Sidebar toggle button-->
<a href="#" class="navbar-btn sidebar-toggle" data-toggle="offcanvas" role="button">
    <span class="sr-only">Toggle navigation</span>
    <span class="icon-bar"></span>
    <span class="icon-bar"></span>
    <span class="icon-bar"></span>
</a>
<div class="navbar-right">
<ul class="nav navbar-nav">
<!-- User Account: style can be found in dropdown.less -->
<li class="dropdown user user-menu">
    <a href="#" class="dropdown-toggle" data-toggle="dropdown">
        <i class="glyphicon glyphicon-user"></i>
        <span><@security.authentication property="name" ></@security.authentication> <i class="caret"></i></span>
    </a>
    <ul class="dropdown-menu">
        <!-- User image -->
        <li class="user-header bg-light-blue">
            <p>
                ${Session["SPRING_SECURITY_CONTEXT"].authentication.name}
            </p>
        </li>
        <!-- Menu Body -->
        <!-- Menu Footer-->
        <li class="user-footer">
            <div class="pull-left">
                <a href="${rc.getContextPath()}/user/profile/${Session["SPRING_SECURITY_CONTEXT"].authentication.name}" class="btn btn-default btn-flat">Profile</a>
            </div>
            <div class="pull-right">
                <a href="${rc.getContextPath()}/logout" class="btn btn-default btn-flat">Sign out</a>
            </div>
        </li>
    </ul>
</li>
</ul>
</div>
</nav>
</header>
<div class="wrapper row-offcanvas row-offcanvas-left">
    <!-- Left side column. contains the logo and sidebar -->
    <aside class="left-side sidebar-offcanvas">
        <!-- sidebar: style can be found in sidebar.less -->
        <section class="sidebar">
            <!-- Sidebar user panel -->
            <div class="user-panel">
                <div class="pull-left image">
                    <img src="${rc.getContextPath()}/img/avatars.png" class="img-circle" alt="User Image" />
                </div>
                <div class="pull-left info">
                    <p>Hello, ${Session["SPRING_SECURITY_CONTEXT"].authentication.name}</p>
                </div>
            </div>
            <!-- sidebar menu: : style can be found in sidebar.less -->
            <ul class="sidebar-menu">
                <li id="dashboard">
                    <a href="/">
                        <i class="glyphicon glyphicon-home"></i> <span>Dashboard</span>
                    </a>
                </li>
                <li class="treeview" id="user">
                    <a href="#">
                        <i class="glyphicon glyphicon-user"></i> <span>${rc.getMessage('general.menu.user')}</span>
                        <i class="fa"></i>
                    </a>
                    <ul class="treeview-menu">
                        <li><a href="${rc.getContextPath()}/user/list"><i class="fa"></i> ${rc.getMessage('user.title.list')}</a></li>
                        <li><a href="${rc.getContextPath()}/user/insert"><i class="fa"></i> ${rc.getMessage('user.title.insert')}</a></li>
                    </ul>
                </li>
                <li class="treeview" id="role">
                    <a href="#">
                        <i class="glyphicon glyphicon-wrench"></i> <span>${rc.getMessage('general.menu.access.control')}</span>
                        <i class="fa"></i>
                    </a>
                    <ul class="treeview-menu">
                        <li><a href="${rc.getContextPath()}/role/list"><i class="fa"></i>${rc.getMessage('role.title.list')}</a></li>
                        <li><a href="${rc.getContextPath()}/role/insert"><i class="fa"></i>${rc.getMessage('role.title.insert')}</a></li>
                        <li><a href="${rc.getContextPath()}/role/permission/list"><i class="fa"></i>${rc.getMessage('permission.title.list')}</a></li>
                        <li><a href="${rc.getContextPath()}/role/permission/insert"><i class="fa"></i>${rc.getMessage('permission.title.insert')}</a></li>
                        <li><a href="${rc.getContextPath()}/role/rolepermission/list"><i class="fa"></i>${rc.getMessage('rolepermission.title.list')}</a></li>
                    </ul>
                </li>
                <li class="treeview" id="pembelian">
                    <a href="#">
                        <i class="glyphicon glyphicon-shopping-cart"></i> <span>${rc.getMessage('pembelian.title')}</span>
                        <i class="fa"></i>
                    </a>
                    <ul class="treeview-menu">
                        <li><a href="${rc.getContextPath()}/pembelian/list"><i class="fa"></i>${rc.getMessage('pembelian.title.list')}</a></li>
                        <li><a href="${rc.getContextPath()}/pembelian/insert"><i class="fa"></i>${rc.getMessage('pembelian.title.insert')}</a></li>
                        <li><a href="${rc.getContextPath()}/pembelian/pembayaran.search"><i class="fa"></i>${rc.getMessage('pembelian.title.pembelian.barang')}</a></li>
                    </ul>
                </li>
                <li class="treeview" id="branch">
                    <a href="#">
                        <i class="glyphicon glyphicon glyphicon-tree-conifer"></i> <span>${rc.getMessage('branch.title')}</span>
                        <i class="fa"></i>
                    </a>
                    <ul class="treeview-menu">
                        <li><a href="${rc.getContextPath()}/branch/list"><i class="fa"></i>${rc.getMessage('branch.title.list')}</a></li>
                        <li><a href="${rc.getContextPath()}/branch/insert"><i class="fa"></i>${rc.getMessage('branch.title.insert')}</a></li>
                    </ul>
                </li>
                <li class="treeview" id="supplier">
                    <a href="#">
                        <i class="glyphicon glyphicon glyphicon-bookmark"></i> <span>${rc.getMessage('supplier.title')}</span>
                        <i class="fa"></i>
                    </a>
                    <ul class="treeview-menu">
                        <li><a href="${rc.getContextPath()}/supplier/list"><i class="fa"></i>${rc.getMessage('supplier.title.list')}</a></li>
                        <li><a href="${rc.getContextPath()}/supplier/insert"><i class="fa"></i>${rc.getMessage('supplier.title.insert')}</a></li>
                    </ul>
                </li>
            </ul>
        </section>
        <!-- /.sidebar -->
    </aside>
    <aside class="right-side">
    </#assign>

    <#assign footer>
    </aside>
</div>
<!-- AdminLTE App -->
<script src="${rc.getContextPath()}/js/AdminLTE/app.js" type="text/javascript"></script>
</body>
</html>
</#assign>