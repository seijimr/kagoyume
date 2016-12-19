
<%@page import="base.KagoyumeHelper"%>
<%@page import="userdata.UserDataDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    UserDataDTO ud = (UserDataDTO)session.getAttribute("userdata");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>更新完了</title>
    </head>
    <body>
        <%=ud.getName()%><br>
        <%=ud.getMail()%><br>
        <%=ud.getAddress()%><br>
        以上の内容で更新しました。
        <h1><%=new KagoyumeHelper().home()%></h1>
    </body>
</html>
