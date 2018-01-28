<!DOCTYPE html>
<html lang="kr">
<head>
<#include "/include/header.ftl">
</head>
<body>
<#include "/include/navigation.ftl">
<div class="container" id="main">
    <div class="col-md-12 col-sm-12 col-lg-10 col-lg-offset-1">
        <div class="panel panel-default content-main">
            <form name="question" method="post" action="/questions/${question.id}">
                <input type="hidden" name="_method" value="put" />
                <div class="form-group">
                    <label for="title">제목</label>
                    <input type="text" class="form-control" id="title" name="title" value="${question.title}"/>
                </div>
                <div class="form-group">
                    <label for="contents">내용</label>
                    <textarea name="contents" id="contents" rows="5" class="form-control">${question.contents}</textarea>
                </div>
                <button type="submit" class="btn btn-success clearfix pull-right">수정하기</button>
                <div class="clearfix" />
            </form>
        </div>
    </div>
</div>

<#include "/include/footer.ftl">
</body>
</html>