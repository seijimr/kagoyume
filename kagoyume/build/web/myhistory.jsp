
<%@page import="ky.ItemDataBeans"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
  ArrayList<ItemDataBeans> list = (ArrayList<ItemDataBeans>)request.getAttribute("buyhistory");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>購入履歴</title>
    </head>
    <body>
        <%if(list.size()!=0){%>
            <table border=1>
            <%for(int i = 0; i<list.size();i++){%>
            <tr>
                <td><a href="Item?id=<%=list.get(i).getCode()%>"><img src="<%=list.get(i).getSmallimg()%>" alt="画像"></a></td>
                <td><a href="Item?id=<%=list.get(i).getCode()%>"><%=list.get(i).getName()%></a></td>
                <td><%=list.get(i).getPrice()%>円</td>
                <td><%=list.get(i).getBuyDate()%></td>
            </tr>
            <%}%></table>
       <%}else{%>
       購入履歴がありません
       <%}%>
       <a href="Mydata">ユーザー情報に戻る</a>
    </body>
</html>
