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
        <title>Perfil</title>
        <link rel="shortcut icon" href="../logo.ico">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/theme-green.css">
        <link href='http://fonts.googleapis.com/css?family=Slabo+27px' rel='stylesheet' type='text/css'>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <script src="js/jquery.min.js" type="text/javascript"></script>
        <script src="js/profileOperations.js" type="text/javascript"></script>
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="navbar.jsp"%>

        <div class="container header_p">
            <div class="row rowheader">
                <div class="col-md-2 col-md-pull foto_p">
                    <a href="#"><img src="img/default_user.png" id="userphoto_p" alt="userphoto" class="img-circle"></a>
                </div>
                <div class="col-md-6 col-md-push-2 info">
                    <h3>${oUser.name}</h3>
                    <h5>idade</h5>
                    <h5>Cidede, estafo, país</h5>
                </div>
                <div class="col-md-4 col-md-push-2 more">       
                    <p><b id="qtde"></b>seguidores</p>
                    <c:if test="${user.email == oUser.email}">
                        <a href="editarinfo.jsp" class="edit"><span class="glyphicon glyphicon-pencil"></span> Editar Perfil</a>
                    </c:if>
                    <c:if test="${user.email != oUser.email}">
                        <script>
                isFollowing("${oUser.email}");
                        </script>            
                        <button id="bt" onclick="follow('${oUser.email}')"></button>
                    </c:if>
                </div>
            </div>
        </div>

        <div class="container content_p">
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <p class="text-justify" id="status">
                                <img src="img/aspas1.png" class="aspas_p" alt="aspas">
                                Status
                                <img src="img/aspas2.png" class="aspas_p" alt="aspas">
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>                      

        <script>
            getNfollowers("${oUser.email}");
        </script>
    </body>

</html>
