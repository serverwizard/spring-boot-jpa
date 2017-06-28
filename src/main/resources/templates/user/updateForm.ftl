<!DOCTYPE html>
<html lang="kr">
<head>
<#include "/include/header.ftl">
</head>	
<body>

<#include "/include/navigation.ftl">

<div class="container" id="main">
   <div class="col-md-6 col-md-offset-3">
      <div class="panel panel-default content-main">
          <form name="question" method="post" action="/users/${user.id}">
          	<input type="hidden" name="_method" value="put"/> 
              <div class="form-group">
                  <label for="userId">사용자 아이디</label>
                  <input class="form-control" id="userId" name="userId" placeholder="아이디" value="${user.userId}" > 
              </div>
              <div class="form-group">
                  <label for="name">이름</label>
                  <input class="form-control" id="name" name="name" placeholder="이름" value="${user.name}">
              </div>
              <div class="form-group">
                  <label for="email">이메일</label>
                  <input type="email" class="form-control" id="email" name="email" placeholder="이메일" value="${user.email}">
              </div>
              <button type="submit" class="btn btn-success clearfix pull-right">개인정보수정 </button>
              <div class="clearfix" />
          </form>
        </div>
    </div>
</div>

<#include "/include/footer.ftl">
	</body>
</html>