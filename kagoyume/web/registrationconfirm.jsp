
<%@page import="userdata.UserDataBeans"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
        UserDataBeans udb = new UserDataBeans();
    if((UserDataBeans)request.getAttribute("forminput")!=null){
        udb = (UserDataBeans)request.getAttribute("forminput");
    }
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>登録確認</title>
        <jsp:include page="\WEB-INF\header.jsp"/>
    </head>
    <body>
        名前：<%=udb.getName()%><br>
        パスワード：<%=udb.getPass()%><br>
        メールアドレス：<%=udb.getMail()%><br>
        住所：<%=udb.getAddress()%><br>
        上記内容で登録致します。よろしいですか？<br>
        <form action="Registrationcomplete" method="POST">
            <input type="hidden" name="name" value="<%=udb.getName()%>">
            <input type="hidden" name="pass" value="<%=udb.getPass()%>">
            <input type="hidden" name="mail" value="<%=udb.getMail()%>">
            <input type="hidden" name="address" value="<%=udb.getAddress()%>">
            <input type="hidden" name="ac"  value="<%= session.getAttribute("ac")%>">
            <button type="submit">登録する</button>
        </form>
        <form action="Registration" method="POST">
            <input type="hidden" name="name" value="<%=udb.getName()%>">
            <input type="hidden" name="mail" value="<%=udb.getMail()%>">
            <input type="hidden" name="address" value="<%=udb.getAddress()%>">
            <button type="submit">登録画面に戻る</button>
        </form>
    </body>
</html>
