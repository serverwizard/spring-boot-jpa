<nav class="navbar navbar-fixed-top header">
    <div class="col-md-12">
        <div class="navbar-header">

            <a href="/" class="navbar-brand">know-study</a>
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-collapse1">
                <i class="glyphicon glyphicon-search"></i>
            </button>

        </div>
        <div class="collapse navbar-collapse" id="navbar-collapse1">
            <form class="navbar-form pull-left" method="GET" action="/">
                <div class="input-group" style="max-width:470px;">
                    <input type="text" class="form-control" placeholder="Search" name="keyword" id="keyword">
                    <div class="input-group-btn">
                        <button class="btn btn-default btn-primary" type="submit"><i
                                class="glyphicon glyphicon-search"></i></button>
                    </div>
                </div>
            </form>
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="glyphicon glyphicon-bell"></i></a>
                    <ul class="dropdown-menu">
                        <li><a href="http://www.daou.co.kr" target="_blank">Daou</a></li>
                        <li><a href="https://facebook.com" target="_blank">Facebook</a></li>
                    </ul>
                </li>
            <#if loginUser?exists>
                <li><a href="/users"><i class="glyphicon glyphicon-user"></i></a></li></#if>
            </ul>
        </div>
    </div>
</nav>
<div class="navbar navbar-default" id="subnav">
    <div class="col-md-12">
        <#--<div class="navbar-header">-->
            <#--<a href="#" style="margin-left:15px;" class="navbar-btn btn btn-default btn-plus dropdown-toggle"-->
               <#--data-toggle="dropdown"><i class="glyphicon glyphicon-home" style="color:#dd1111;"></i> Home-->
                <#--<small><i class="glyphicon glyphicon-chevron-down"></i></small>-->
            <#--</a>-->
            <#--<ul class="nav dropdown-menu">-->
                <#--<li><a href="user/profile.html"><i class="glyphicon glyphicon-user" style="color:#1111dd;"></i> Profile</a>-->
                <#--</li>-->
                <#--<li class="nav-divider"></li>-->
                <#--<li><a href="#"><i class="glyphicon glyphicon-cog" style="color:#dd1111;"></i> Settings</a></li>-->
            <#--</ul>-->

            <#--<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navbar-collapse2">-->
                <#--<span class="sr-only">Toggle navigation</span>-->
                <#--<span class="icon-bar"></span>-->
                <#--<span class="icon-bar"></span>-->
                <#--<span class="icon-bar"></span>-->
            <#--</button>-->
        <#--</div>-->
        <div class="collapse navbar-collapse" id="navbar-collapse2">
            <ul class="nav navbar-nav navbar-right">
                <li class="active"><a href="/">Posts</a></li>
            <#if !loginUser?exists>
                <li><a href="/users/loginForm" role="button">로그인</a></li>
                <li><a href="/users/enrollForm" role="button">회원가입</a></li>
                <!--
            <li><a href="#loginModal" role="button" data-toggle="modal">로그인</a></li>
            <li><a href="#registerModal" role="button" data-toggle="modal">회원가입</a></li>
            -->
            <#else>
                <li><a href="/users/logout" role="button">로그아웃</a></li>
                <li><a href="/users/#{loginUser.id}/form" role="button">개인정보수정</a></li>
            </#if>
            </ul>
        </div>
    </div>
</div>