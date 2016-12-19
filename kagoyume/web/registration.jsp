
<%@page import="userdata.UserDataBeans"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    UserDataBeans udb = new UserDataBeans();
    if((UserDataBeans)request.getAttribute("forminput")!=null){
        udb = (UserDataBeans)request.getAttribute("forminput");
    }
    String notmatch = (request.getAttribute("passnotmatch") != null)
            ? (String) request.getAttribute("passnotmatch") : "";
    String omission = (request.getAttribute("omission") != null)
            ? (String) request.getAttribute("omission") : "";
    String registered = (request.getAttribute("registered") != null)
            ? (String) request.getAttribute("registered") : "";
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>新規会員登録</title>
        <jsp:include page="\WEB-INF\header.jsp"/>
    </head>
    <body>
        <form action="Registrationconfirm" method="POST">
            ユーザー名：<input type="text" name="name" value="<%=udb.getName()%>"><br>
            パスワード：<input type="password" name="pass"><br>
            パスワード確認：<input type="password" name="repass"><br>
            メールアドレス：<input type="text" name="mail" value="<%=udb.getMail()%>"><br>
            住所：<input type="text" name="address" value="<%=udb.getAddress()%>"><br>
            <input type="hidden" name="ac"  value="<%= session.getAttribute("ac")%>">
            <button type="submit">登録確認</button>
            <%
                if (!notmatch.equals("")) out.print(notmatch);
                if (!omission.equals("")) out.print(omission);
                if (!registered.equals("")) out.print(registered);
            %>
        </form>
    </body>
</html>
