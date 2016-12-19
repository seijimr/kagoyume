
<%@page import="ky.ItemDataBeans"%>
<%@page import="java.util.ArrayList"%>
<%@page import="userdata.UserDataDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    UserDataDTO ud = (UserDataDTO) session.getAttribute("userdata");
    ArrayList<ItemDataBeans> cart = (ArrayList<ItemDataBeans>) session.getAttribute("user" + ud.getUserID() + "cart");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>購入確認</title>
    </head>
    <body>
        <%
            for (int i = 0; i < cart.size(); i++) {
                ItemDataBeans idb = cart.get(i);
        %>
        <%=idb.getName()%><br>
        <%=idb.getPrice()%>円<br>
        <%}%>
        <form action="Buycomplete" method="POST">
        宅急便<input type="radio" name="delivery" value="1"><br>
        お急ぎ便<input type="radio" name="delivery" value="2"><br>
            <input type="hidden" name ="ac" value="<%=session.getAttribute("ac")%>">
            <button type="submit">上記の内容で購入する</button>
        </form>
        <button onclick="location.href='Cart'">カートに戻る</button>
    </body>
</html>
