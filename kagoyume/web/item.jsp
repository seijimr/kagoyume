
<%@page import="ky.ItemDataBeans"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    ItemDataBeans idb = (ItemDataBeans) request.getAttribute("productData");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=idb.getName()%></title>
        <jsp:include page="\WEB-INF\header.jsp"/>
    </head>
    <body>
        <img src="<%=idb.getMedimg()%>"> 
        <%=idb.getName()%>       
        <%=idb.getPrice()%>
        <h2>商品説明</h2>
        <%=idb.getDescription()%>
        <form action="Add" method="POST">
            <input type="hidden" name="code" value="<%=idb.getCode()%>">
            <input type="hidden" name="name" value="<%=idb.getName()%>">
            <input type="hidden" name="price" value="<%=idb.getPrice()%>">
            <input type="hidden" name="img" value="<%=idb.getSmallimg()%>">
            <button type="submit">カートに入れる</button>
        </form>
    </body>
</html>
