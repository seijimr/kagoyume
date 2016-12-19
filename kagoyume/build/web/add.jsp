
<%@page import="ky.ItemDataBeans"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    ItemDataBeans idb = (ItemDataBeans)request.getAttribute("idb");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>カートに追加しました</title>
         <jsp:include page="\WEB-INF\header.jsp"/>
    </head>
    <body>
        <img src="<%=idb.getSmallimg()%>">
        <%=idb.getName()%>
        <%=idb.getPrice()%>円
        
       カートに追加しました。まだ買い物を続けますか？
       <button onclick="location.href='Item?id=<%=idb.getCode()%>'">買い物を続ける</button>
       <button onclick="location.href='Cart'">カートを見る</button>
    </body>
</html>
