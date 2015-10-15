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
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
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
                    <c:if test="${user.email != oUser.email}">
                        <script>
                            isFollowing("${oUser.email}");
                        </script>            
                        <button id="bt" class="btn btn-default text-capitalize" onclick="follow('${oUser.email}')"></button><br>
                    </c:if>
                    <c:if test="${oUser.address != null}">
                        <h5>${oUser.address.city} - ${oUser.address.state}, ${oUser.address.country}</h5>
                    </c:if>                    
                </div>
                <div class="col-md-4 col-md-push-2 more">       
                    <p><b id="qtde"></b> seguidores</p>
                    <c:if test="${user.email == oUser.email}">
                        <a href="editarinfo.jsp" class="edit"><span class="glyphicon glyphicon-pencil"></span> Editar Perfil</a>
                    </c:if>                    
                </div>
            </div>
        </div>

        <div class="container content_p">
            <div class="row">
                <div class="col-md-12">
                    <div class="panel panel-default">
                        <div class="panel-body">
                            <p class="text-capitalize" id="status" style="width: 100%">
                                <img src="img/aspas1.png" class="aspas_p" alt="aspas">
                                ${oUser.bio}
                                <img src="img/aspas2.png" class="aspas_p" alt="aspas">
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </div>                      
        <div class="row">
            <div class="col col-lg-3 col-md-3">
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
            <div class="col-lg-5 col-md-5">
                <c:if test="${fn:length(oPosts) gt 0}">
                    <c:forEach var="post" items="${oPosts}">
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
            
        <script>
            getPrefer("${user.email}");
            getNfollowers("${oUser.email}");
        </script>
    </body>

</html>
