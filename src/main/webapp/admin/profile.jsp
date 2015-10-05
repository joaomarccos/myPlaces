<%-- 
    Document   : profile
    Author     : joaomarcos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="js/jquery.min.js" type="text/javascript"></script>
        <script src="js/profileOperations.js" type="text/javascript"></script>
        <title>JSP Page</title>
    </head>
    <body>
        <a href="index.jsp">Inicio</a>
        <h1>${oUser.name}</h1>
        <c:if test="${user.email != oUser.email}">
            <script>
                isFollowing("${oUser.email}");
            </script>            
            <button id="bt" onclick="follow('${oUser.email}')"></button>
        </c:if>
        <script>
            getNfollowers("${oUser.email}");
        </script>
        <p><b id="qtde"></b>seguidores</p>

        <div>

        </div>
    </body>
</html>
