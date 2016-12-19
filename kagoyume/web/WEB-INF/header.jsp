<%@page import="userdata.UserDataDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!--<link rel="stylesheet" type="text/css" href="css/menu.css">-->
<link rel="SHORTCUT ICON" href="pic/favicon.ico"/>
<%
    String url = (request.getAttribute("url") != null) ? (String) request.getAttribute("url") : "/kagoyume";
    UserDataDTO userdata = (session.getAttribute("userdata") != null)
            ? (UserDataDTO) session.getAttribute("userdata")
            : null;
%>


<a href="/kagoyume">TOP</a>
<form action="Search" method="GET">
    <input type="text" name="search">
    <button type="submit">検索</button></form>

<%if (userdata==null) {%>
<form action="Login" name="formlog" method="POST"><input type="hidden" name="url" value="<%=url%>">
    <a href="#" onclick="document.formlog.submit();">ログイン</a></form>
<a href="Registration">新規会員登録</a>
<%} else {%>
<form action="Login" name="formlog">
    ようこそ<a href="Mydata"><%=userdata.getName()%></a>さん！
    <a href="#" onclick="document.formlog.submit();">ログアウト</a></form>
<%}%>
 <a href="Cart">カートを見る</a>
