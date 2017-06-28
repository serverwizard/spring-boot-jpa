<!DOCTYPE html>
<html lang="kr">
<head>
<#include "/include/header.ftl">
</head>	
<body>

<#include "/include/navigation.ftl">

<div class="container" id="main">
   <div class="col-md-10 col-md-offset-1">
      <div class="panel panel-default">
          <table class="table table-hover">
              <thead>
                <tr>
                    <th>#</th> <th>사용자 아이디</th> <th>이름</th> <th>이메일</th><th></th>
                </tr>
              </thead>
              <tbody>
              <#list users as user>
                <tr>
                    <th scope="row">${user.id}</th> 
                    <td>${user.userId}</td> 
                    <td>${user.name}</td> 
                    <td>${user.email}</td>
                    <td><a href="/users/${user.id}/form" class="btn btn-success" role="button">수정</a></td>
                </tr>
			 </#list>
              </tbody>
          </table>
        </div>
    </div>
</div>

<#include "/include/footer.ftl">
	</body>
</html>