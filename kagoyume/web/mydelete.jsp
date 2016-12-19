
<%@page import="userdata.UserDataDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    UserDataDTO ud = (UserDataDTO)session.getAttribute("userdata");
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>退会確認</title>
    </head>
    <body>
        名前：<%=ud.getName()%><br>
        メールアドレス：<%=ud.getMail()%><br>
        住所：<%=ud.getAddress()%>
        総購入額：<%=ud.getTotal()%>
        登録日：<%=ud.getNewDate()%>
        本当に退会してしまうんですか？
        <form action="Mydeleteresult" method="POST">
        <input type="hidden" name="ac"  value="<%= session.getAttribute("ac")%>">
        <button type="submit">はい</button>
        </form>
        <button onclick="location.href='/kagoyume'">いいえ</button>
    </body>
</html>
