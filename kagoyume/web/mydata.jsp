
<%@page import="userdata.UserDataDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
  UserDataDTO ud = (UserDataDTO)session.getAttribute("userdata");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ユーザー情報</title>
    </head>
    <body>
       名前：<%=ud.getName()%>
       メールアドレス：<%=ud.getMail()%>
       住所：<%=ud.getAddress()%>
       総購入金額：<%=ud.getTotal()%>
       登録日時：<%=ud.getNewDate()%>
       <a href="Myhistory">購入履歴</a>
       <button onclick="location.href='Myupdate'">更新</button>
       <button onclick="location.href='Mydelete'">退会</button>
    </body>
</html>
