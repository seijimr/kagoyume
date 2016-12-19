
<%@page import="userdata.UserDataDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    UserDataDTO ud = (UserDataDTO) session.getAttribute("userdata");

    String notmatch = (request.getAttribute("passnotmatch") != null)
            ? (String) request.getAttribute("passnotmatch") : "";
    notmatch = (request.getAttribute("passcurnotmatch") != null)
            ? (String) request.getAttribute("passcurnotmatch") : notmatch;
    String omission = (request.getAttribute("omission") != null)
            ? (String) request.getAttribute("omission") : "";
    String registered = (request.getAttribute("registered") != null)
            ? (String) request.getAttribute("registered") : "";
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ユーザー情報更新</title>
        <jsp:include page="\WEB-INF\header.jsp"/>
    </head>
    <body>
        <form action="Myupdateresult" method="POST">
            ユーザー名：<input type="text" name="name" value="<%=ud.getName()%>"><br>
            現在のパスワード<input type="password" name="passcur"><br>
            新しいパスワード：<input type="password" name="passnew"><br>
            パスワード確認：<input type="password" name="repass"><br>
            メールアドレス：<input type="text" name="mail" value="<%=ud.getMail()%>"><br>
            住所：<input type="text" name="address" value="<%=ud.getAddress()%>"><br>
            <input type="hidden" name="ac"  value="<%= session.getAttribute("ac")%>">
            <button type="submit">更新</button>
            <%
                if (!notmatch.equals("")) {
                    out.print(notmatch);
                }
                if (!omission.equals("")) {
                    out.print(omission);
                }
                if (!registered.equals("")) {
                    out.print(registered);
                }
            %>
        </form>
    </body>
</html>
