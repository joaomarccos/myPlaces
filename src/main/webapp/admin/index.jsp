<%-- 
    Document   : index.jsp
    Author     : joaomarcos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
    <head>
        <link href="css/map-style.css" rel="stylesheet" type="text/css"/>
        <link href="css/theme-green.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <title>Feed</title>
        <link rel="shortcut icon" href="../logo.ico"> 
        <link href='http://fonts.googleapis.com/css?family=Slabo+27px' rel='stylesheet' type='text/css'>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="js/jquery.min.js" type="text/javascript"></script>
        <script src="js/operations.js" type="text/javascript"></script>
        <title>JSP Page</title>
    </head>
    <body>
        
        <%@include file="navbar.jsp"%>
        
        <div class="container">
            <div class="row">
            	<div class="col-md-8 col-md-push-2">
                    <%@include file="containerPublicar.jsp"%>
		</div>
            </div>
        </div>
                
        <h1>Main page <small>(fique a vontade de mudar o que quiser, mas não mude os IDs)</small></h1>
        <a href="profile?user=${user.email}">Perfil</a>
        <a href="loggout">Sair</a>
        <br>

        <div>
            Busca
            <input id="search" type="text" placeholder="buscar usuários">
            <div id="resultado-busca">

            </div>
        </div>

        <div>            
            Novo post <small id="post-result"></small>
            <br>
            <div id="map-canvas"></div>        
            <input id="pac-input" class="controls" type="text" name="place-description" placeholder="Informe um local">        
            <form id="newPost" method="post" action="newPost">                    
                <input  hidden id="place" name="place" type="text">
                <input  hidden id="lat" name="lat" type="text">
                <input  hidden id="lng" name="lng" type="text">            
                <textarea name="post" cols="40" placeholder="Diga algo sobre isso"></textarea>            
            </form>
            <button onclick="submitPost()">Postar</button>
        </div>

        <div>
            Usuários sugeridos
            <c:if test="${sgusers != null}}">
                <c:forEach items="${sgusers}" var="sguser">
                    <a href="profile?user=${sguser.email}">${sguser.name}</a>
                </c:forEach>
            </c:if>
        </div>

        <div>
            Locais recentemente visitados
            <c:if test="${sgplaces != null}">
                <c:forEach items="${sgplaces}" var="sgplace">
                    <p>
                        <em>${sgplace.description}</em><br>
                        <img src="https://maps.googleapis.com/maps/api/staticmap?center=${sgplace.lat},${sgplace.lng}&zoom=13&size=150x150&markers=color:red%7C${sgplace.lat},${sgplace.lng}&key=AIzaSyDPSWd4ujOrb1Hhv0rDnKnW9oVG3zNfjWo">                    
                    </p>
                </c:forEach>
            </c:if>
        </div>
        <br>

        <div>
            Feed
            <hr>

            <c:set var="loadPosts" value="${fn:length(feed)}" />
            <script>
                var loadPosts = '${loadPosts}';
            </script>

            <button id="updateButton" onclick="location.reload()">Atualizar</button>
            <c:if test="${fn:length(feed) gt 0}">
                <c:forEach var="post" items="${feed}">
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
        </div>
        <script>
            getPrefer("${user.email}");
        </script>
        <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&signed_in=true&libraries=places"></script>
        <script src="js/map.js" type="text/javascript"></script>
        <script src="js/bootstrap.js"></script>
    </body>
</html>
