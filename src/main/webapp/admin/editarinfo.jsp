<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html> 
<html> 
	<head> 
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>Editar Perfil</title>
		<link rel="shortcut icon" href="../logo.ico" >
                <link rel="stylesheet" href="css/theme-green.css">
		<link rel="stylesheet" href="css/bootstrap.min.css">
		<link href='http://fonts.googleapis.com/css?family=Slabo+27px' rel='stylesheet' type='text/css'>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
	</head> 
	<body>
		<%@ include file="navbar.jsp"%>
		
		<div class="container">
			<div class="row">
				<div class="col-md-6 col-md-push-3">
					
					<form action="updateInfo" method="POST" class="form-horizontal form" id="ed" enctype="multipart/form-data">
						
						<fieldset>
							<legend>Informações Pessoais </legend>
						</fieldset>
							
						<div class="form-group">
							<label for="nome" class="control-label col-sm-3">
								<img src="${user.img}" id="userphoto" alt="userphoto" class="img-circle">
							</label>
							<div class="col-sm-9">
								<input type="file" name="image">
							</div>
						</div>			
						
						<div class="form-group">
							<label for="nome" class="control-label col-sm-2">Nome </label>
								<div class="col-sm-10">
									<input type="text" class="form-control" id="name" name="name" value="${user.name}">
								</div>
						</div>						
						
						<div class="form-group">
							<label for="nome" class="control-label col-sm-2">Idade</label>
								<div class="col-sm-6">
									<input type="number" class="form-control" id="age" name="age" value="${user.age}">
								</div>
						</div>
						
						<div class="form-group">
							<label for="status" class="control-label col-sm-2">Status</label>
									<div class="col-sm-8">
										<textarea type="text" class="form-control" id="bio" name="bio">${user.bio}</textarea>
									</div>	
						</div>
						
						<div class="form-group">
							<label for="cidade" class="control-label col-sm-2">Cidade</label>
								<div class="col-sm-6">
									<input type="text" class="form-control" id="address" name="city" value="${user.address.city}">
								</div>
						</div>
						<div class="form-group">
							<label for="cidade" class="control-label col-sm-2">Estado</label>
								<div class="col-sm-6">
									<input type="text" class="form-control" id="address" name="state" value="${user.address.state}">
								</div>
						</div>
						<div class="form-group">
							<label for="cidade" class="control-label col-sm-2">País</label>
								<div class="col-sm-6">
									<input type="text" class="form-control" id="address" name="country" value="${user.address.country}">
								</div>
						</div>
						
						<fieldset>
							<legend>Informações de Conta </legend>
						</fieldset>
						
						<div class="form-group">
							<label for="login" class="control-label col-sm-2">Email</label>
								<div class="col-sm-6">
								<input type="text" class="form-control" id="login" name="login" value="${user.email}" readonly>
								</div>
						</div>
						
						
						<div class="form-group">
							<label for="login" class="control-label col-sm-2">Senha</label>
							<div class="col-sm-6">
								<input type="password" class="form-control" id="senha" name="senha" value="${user.password}" readonly>
								</div>
						</div>
						
						
						<button type="submit"  id="alterar" value= "alterar" class="btn btn-primary pull-right">
							Salvar Altera&ccedil;&otilde;es
						</button>
					</form>
					<form action="Updatetheme" method="POST" class="form-horizontal form" id="ed">
						
						<fieldset>
							<legend>Personalização</legend>
						</fieldset>
						
						<div class="form-group">
							<label for="theme" class="control-label col-sm-4">Escolha um tema</label>
								<div class="col-sm-6">
									<select class="form-control" id="theme">
									    <option value="green">Green (Default)</option>
									    <option value="blue">Blue</option>
									    <option value="black">Black</option>
									</select>
								</div>
						</div>
						
						<button type="submit"  id="changeTheme" value= "changeTheme" class="btn btn-primary pull-right">
							Salvar
						</button>

					</form>
			</div>
		</div>
	</div>
		<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
		<script src="js/bootstrap.js"></script>
	</body>
</html>