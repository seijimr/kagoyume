
<%@page import="userdata.UserDataDTO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="ky.ItemDataBeans"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    UserDataDTO ud = (UserDataDTO) session.getAttribute("userdata");
    ArrayList<ItemDataBeans> cart = (ArrayList<ItemDataBeans>) session.getAttribute("user" + ud.getUserID() + "cart");
    int total = 0;
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>カート</title>
        <jsp:include page="\WEB-INF\header.jsp"/>
    </head>
    <body>
        <%if (cart.size() == 0) {%>

        カートの中身がありません。
        <%} else {%>
        <table border=1>
            <%
                for (int i = 0; i < cart.size(); i++) {
                    ItemDataBeans idb = cart.get(i);
                    total += idb.getPrice();
            %>
            <tr>
                <td><a href="Item?id=<%=idb.getCode()%>"><img src="<%=idb.getSmallimg()%>" alt="画像"></a></td>
                <td><a href="Item?id=<%=idb.getCode()%>"><%=idb.getName()%></a></td>
                <td><%=idb.getPrice()%>円</td>
                <td><form action="Cart" method="POST">
                        <input type="hidden" name="delete" value="<%=i%>">
                        <button type="submit">削除</button></form></td>
            </tr>
            <%}%>
        </table>
        合計金額<%=total%>
        <button onclick="location.href='Buyconfirm'">購入する</button>
        <%}%>
    </body>
</html>
