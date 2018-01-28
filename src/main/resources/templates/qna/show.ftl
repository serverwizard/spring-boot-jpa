<!DOCTYPE html>
<html lang="kr">
<head>
<#include "/include/header.ftl">
    <script></script>
</head>

<body>
<#include "/include/navigation.ftl">

<div class="container" id="main">
    <div class="col-md-12 col-sm-12 col-lg-12">
        <div class="panel panel-default">
            <header class="qna-header">
                <h2 class="qna-title">${question.title}</h2>
            </header>
            <div class="content-main">
                <article class="article">
                    <div class="article-header">
                        <div class="article-header-thumb">
                            <img src="https://graph.facebook.com/v2.3/100000059371774/picture"
                                 class="article-author-thumb" alt="">
                        </div>
                        <div class="article-header-text">
                        ${question.writer.userId}
                            <a href="/questions/413" class="article-header-time" title="퍼머링크">
                            ${question.createDate}
                                <i class="icon-link"></i>
                            </a>
                        </div>
                    </div>
                    <div class="article-doc">
                    ${question.contents}
                    </div>
                    <div class="article-util">
                        <ul class="article-util-list">
                            <li>
                                <a class="link-modify-article" href="/questions/${question.id}/form">수정</a>
                            </li>
                            <li>
                                <form class="form-delete" action="/questions/${question.id}" method="POST">
                                    <input type="hidden" name="_method" value="DELETE">
                                    <button class="link-delete-article" type="submit">삭제</button>
                                </form>
                            </li>
                            <li>
                                <a class="link-modify-article" href="/">목록</a>
                            </li>
                        </ul>
                    </div>
                </article>

            <#if question.answers??>
                <div class="qna-comment">
                    <div class="qna-comment-slipp">
                        <p class="qna-comment-count"><strong>${question.answers?size}</strong>개의 의견</p>
                        <div class="qna-comment-slipp-articles">

                            <#list question.answers as answer>
                                <article class="article" id="answer-1405">
                                    <div class="article-header">
                                        <div class="article-header-thumb">
                                            <img src="https://graph.facebook.com/v2.3/100000059371774/picture"
                                                 class="article-author-thumb" alt="">
                                        </div>
                                        <div class="article-header-text">
                                        ${answer.writer.userId}
                                            <a href="#answer-1434" class="article-header-time" title="퍼머링크">
                                            ${answer.createDate}
                                            </a>
                                        </div>
                                    </div>
                                    <div class="article-doc comment-doc">
                                        <p>${answer.contents}</p>
                                    </div>
                                    <div class="article-util">
                                        <ul class="article-util-list">
                                        <#--<li>-->
                                        <#--<a class="link-modify-article"-->
                                        <#--href="/questions/${question.id}/answers/1405/form">수정</a>-->
                                        <#--</li>-->
                                            <li>
                                                <form class="delete-answer-form"
                                                      action="/questions/${question.id}/answers/${answer.id}"
                                                      method="POST">
                                                    <input type="hidden" name="_method" value="DELETE">
                                                    <button type="submit" class="delete-answer-button">삭제</button>
                                                </form>
                                            </li>
                                        </ul>
                                    </div>
                                </article>
                            </#list>


                            <form class="submit-write" method="POST" action="/questions/${question.id}/answers">
                                <div class="form-group" style="padding:14px;">
                                    <textarea class="form-control" placeholder="Update your status"
                                              name="contents"></textarea>
                                </div>
                                <input type="submit" class="btn btn-success pull-right" value="답변하기"/>
                                <div class="clearfix"/>

                            </form>
                        </div>
                    </div>
                </div>
                </#if>
            </div>
        </div>
    </div>
</div>

<script type="text/template" id="answerTemplate">
    <article class="article">
        <div class="article-header">
            <div class="article-header-thumb">
                <img src="https://graph.facebook.com/v2.3/1324855987/picture" class="article-author-thumb" alt="">
            </div>
            <div class="article-header-text">
                <a href="#" class="article-author-name">{0}</a>
                <div class="article-header-time">{1}</div>
            </div>
        </div>
        <div class="article-doc comment-doc">
            {2}
        </div>
        <div class="article-util">
            <ul class="article-util-list">
                <li>
                    <a class="link-modify-article" href="/api/qna/updateAnswer/{3}">수정</a>
                </li>
                <li>
                    <form class="delete-answer-form" action="/api/questions/{3}/answers/{4}" method="POST">
                        <input type="hidden" name="_method" value="DELETE">
                        <button type="submit" class="delete-answer-button">삭제</button>
                    </form>
                </li>
            </ul>
        </div>
    </article>
</script>

<#include "/include/footer.ftl">
</body>
</html>