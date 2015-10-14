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
        <script src="js/functions.js" type="text/javascript"></script>
        <script src="js/profileOperations.js" type="text/javascript"></script>
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="navbar.jsp"%>

        <div class="container header_p">
            <div class="row rowheader">
                <div class="col-md-2 col-md-pull foto_p">
                    <a href="#"><img src="${oUser.img}" id="userphoto_p" alt="userphoto" class="img-circle"></a>
                </div>
                <div class="col-md-6 col-md-push-2 info">
                    <h3>${oUser.name}</h3>
                    <h5>${oUser.age}</h5>
                    <h5>${oUser.address.city}, ${oUser.address.state}, ${oUser.address.country}</h5>
                </div>
                <div class="col-md-4 col-md-push-2 more">       
                    <p><b id="qtde"></b> seguidores</p>
                    <c:if test="${user.email == oUser.email}">
                        <a href="editarinfo.jsp" class="edit"><span class="glyphicon glyphicon-pencil"></span> Editar Perfil</a>
                    </c:if>
                    <c:if test="${user.email != oUser.email}">
                        <script>
                            isFollowing("${oUser.email}");
                        </script>            
                        <button id="bt" class="btn btn-primary pull-right" onclick="follow('${oUser.email}')"></button>
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
                                    ${oUser.bio}
                                <img src="img/aspas2.png" class="aspas_p" alt="aspas">
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>                      
        <c:if test="${fn:length(oPosts) gt 0}">
            <c:forEach var="post" items="${oPosts}">
                <p><a href="profile?user=${post.author}">${post.username}</a> <small>${post.date}</small></p>
                <p>${post.text}</p>
                <img src="https://maps.googleapis.com/maps/api/staticmap?center=${post.location.lat},${post.location.lng}&zoom=13&size=300x200&markers=color:red%7C${post.location.lat},${post.location.lng}&key=AIzaSyDPSWd4ujOrb1Hhv0rDnKnW9oVG3zNfjWo">                    
                <div id="${post.id}">
                    <a id="likes"></a>
                    <button Id="bt" onclick="like('${post.id}')"></button>
                </div>
                <script>
                        isLiked("${post.id}");
                </script>
                <c:forEach var="comment" items="${post.comments}">
                    <p>${comment.username} <small>${comment.date}</small></p>
                    <p>${comment.description}</p>
                    <c:if test="${(comment.author eq user.email) or (user.email eq post.id)}">
                        <a href="deleteComment?postId=${post.id}&comment=${comment.id}">Excluir</a>
                    </c:if>
                </c:forEach>
                <br>                    
                <form action="comment?postid=${post.id}" method="post">
                    <textarea cols="40" placeholder="comentar" name="comment"></textarea>                        
                    <input type="submit" value="comentar">
                </form>                    
            </c:forEach>                                            
        </c:if>
        <script>
            getNfollowers("${oUser.email}");
        </script>
    </body>

</html>
