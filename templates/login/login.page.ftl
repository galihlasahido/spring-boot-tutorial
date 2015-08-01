<@compress single_line=false>
<#import "/spring.ftl" as spring/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${rc.getMessage('app.name')} | ${rc.getMessage('app.version')}</title>
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
    <link href="${rc.getContextPath()}/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
    <!-- Theme style -->
    <link href="${rc.getContextPath()}/css/AdminLTE.css" rel="stylesheet" type="text/css" />

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="${rc.getContextPath()}/js/html5shiv.js"></script>
    <script src="${rc.getContextPath()}/js/respond.min.js"></script>
    <![endif]-->
    <style>
        .bg-olive { background-color: #3D8499!important; }
        .form-box .header {background: #3D8499}
    </style>
</head>
<body>

<div class="form-box" id="login-box">
    <div class="header">Sign In</div>
    <form action="${rc.getContextPath()}/user-login" method="post">
        <div class="body bg-gray">
            <#if error??>
                <div class="alert alert-danger" role="alert">
                    <@spring.message "login.error"/>
                </div>
                <div>&nbsp;</div>
            </#if>
            <div class="form-group">
                <input type="text" name="username" class="form-control" placeholder="User ID"/>
            </div>
            <div class="form-group">
                <input type="password" name="password" class="form-control" placeholder="Password"/>
            </div>
        </div>
        <div class="footer">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <button type="submit" class="btn bg-olive btn-block">Sign me in</button>
        </div>
    </form>
</div>
</body>
</html>
</@compress>