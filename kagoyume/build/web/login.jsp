
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String mail = (request.getAttribute("mail") != null) ? (String) request.getAttribute("mail") : "";

%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ログインページ</title>
        <jsp:include page="\WEB-INF\header.jsp"/>
    </head>
    <body>

        <form action="Logincheck">
            メールアドレス：<input type="text" name="mail" value="<%=mail%>">
            パスワード：<input type="text" name="pass">
            <input type="hidden" name="ac"  value="<%= session.getAttribute("ac")%>">
            <input type="hidden" name="url" value="<%=request.getAttribute("url")%>">
            <%if(request.getAttribute("Cart")!=null){%>
               <input type="hidden" name="Cart"  value="<%=request.getAttribute("Cart")%>">
            <%}%>
            <button type="submit">ログイン</button>
            <%
                if (request.getAttribute("loginerror")!=null&&request.getAttribute("loginerror").equals("YES")) {
                    out.print("<font color=\"red\">メールアドレスかパスワードが間違っています</font>");
                }
            %>
        </form>
        初めての方は<a href="Registration">こちら</a>
    </body>
</html>
