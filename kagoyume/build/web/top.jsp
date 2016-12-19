<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>かごゆめ</title>
        <jsp:include page="\WEB-INF\header.jsp"/>
    </head>
    <body>
        <h4>いくらでも買えるよ</h4>
        <form action="Search" method="GET">
            <input type="text" name="search">
            <button type="submit">検索</button>
        </form>
    </body>
</html>