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
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="js/jquery.min.js" type="text/javascript"></script>
        <script src="js/operations.js" type="text/javascript"></script>
        <script src="js/functions.js" type="text/javascript"></script>
        <title>JSP Page</title>
    </head>
    <body>

        <%@include file="navbar.jsp"%>

        <!-- Button trigger modal -->
        <div style="text-align: center">
            <button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#mapmodal">
                Publicar
            </button>           
            <br>
            <br>
            <br>
        </div>
        <div class="row">
            <div class="col col-lg-4 col-md-4">
                <c:forEach items="${sgusers}" var="sguser">                                    
                    <div class="media">
                        <div class="media-left media-middle">
                            <a href="profile?user=${sguser.email}">
                                <img width="48"class="media-object" src="${sguser.img}" alt="${sguser.name}">
                            </a>
                        </div>
                        <div class="media-body">
                            <h4 class="media-heading">${sguser.name}</h4>
                            <span>(Usuário sugerido)</span>
                        </div>
                    </div>
                </c:forEach>                       
            </div>            
            <div class="col-lg-4 col-md-4">
                <c:if test="${fn:length(feed) gt 0}">
                    <c:forEach var="post" items="${feed}">
                        <div class="post">
                            <div class="row">
                                <div class="col-sm-12 col-md-12">
                                    <div class="thumbnail">
                                        <img src="https://maps.googleapis.com/maps/api/staticmap?center=${post.location.lat},${post.location.lng}&zoom=15&size=500x300&markers=color:red%7C${post.location.lat},${post.location.lng}&key=AIzaSyDPSWd4ujOrb1Hhv0rDnKnW9oVG3zNfjWo">
                                        <div class="caption">
                                            <p id="${post.id}" style="float: right">
                                                <button id="bt" onclick="like('${post.id}')" class="btn btn-sm btn-success"></button> <span class="badge" id="likes" ></span>
                                            </p>
                                            <h3><a href="profile?user=${post.author}">${post.username}</a> <small>${post.date}</small></h3>
                                            <p>${post.text}</p>
                                            <script>
                                                isLiked("${post.id}");
                                            </script>
                                            <hr>
                                        </div>
                                        <h4>Comentários</h4>
                                        <c:forEach var="comment" items="${post.comments}">
                                            <div class="">
                                                <h4>${comment.username} <small>${comment.date}</small></h4>
                                                <c:if test="${(comment.author eq user.email) or (user.email eq post.id)}">
                                                    <a style="float: right"class="btn btn-sm btn-link" href="deleteComment?postId=${post.id}&comment=${comment.id}"><span class="glyphicon glyphicon-remove"></span></a>
                                                    </c:if>
                                                <p class="well well-lg">${comment.description}</p>
                                            </div>
                                        </c:forEach>
                                        <br>                    
                                        <form action="comment?postid=${post.id}" method="post">
                                            <textarea cols="40" placeholder="comentar" name="comment" class="form-control"></textarea>                        
                                            <input type="submit" value="comentar" class="btn btn-default" style="margin-top: 3px;">
                                        </form>                    
                                    </div>
                                </div>
                            </div>                        
                        </div>
                    </c:forEach>                                            
                </c:if>
            </div>
            <div class="col col-lg-4 col-md-4">
                <c:forEach items="${sgplaces}" var="sgplace">                                        
                    <div class="media">
                        <div class="media-left media-middle">                            
                            <img src="https://maps.googleapis.com/maps/api/staticmap?center=${sgplace.lat},${sgplace.lng}&zoom=13&size=160x100&markers=color:red%7C${sgplace.lat},${sgplace.lng}&key=AIzaSyDPSWd4ujOrb1Hhv0rDnKnW9oVG3zNfjWo">
                        </div>
                        <div class="media-body">
                            <h4 class="media-heading">${sgplace.description}</h4>
                            <span>(Locais visitados por seus amigos)</span>
                        </div>
                    </div>
                </c:forEach>                        
            </div>
        </div>       

        <!-- Modal -->
        <div class="modal fade" id="mapmodal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">                    
                    <div class="modal-body">                        
                        <div class="row">
                            <div class="col-md-12 col-sm-12">
                                <%@include file="containerPublicar.jsp"%>
                            </div>
                        </div>                        
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>                        
                    </div>
                </div>
            </div>
        </div>
        <div>            
            <c:set var="loadPosts" value="${fn:length(feed)}" />
            <script>
                var loadPosts = '${loadPosts}';
            </script>
            <button class="btn btn-info" id="updateButton" onclick="location.reload()">Atualizar</button>           
        </div>
        <script>
            getPrefer("${user.email}");
        </script>
        <script src="https://maps.googleapis.com/maps/api/js?v=3.exp&signed_in=true&libraries=places"></script>
        <script src="js/map.js" type="text/javascript"></script>
        <script src="js/bootstrap.js"></script>
    </body>
</html>
