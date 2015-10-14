<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="index.jsp" title="Perfil"><img class="logo" src="img/logo-mim.png"></a>
		</div>
		<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav navbar-right">
				<li><a href="profile?user=${user.email}"><img src="${user.img}" id="userphotonav" alt="ir para perfil" class="img-circle"><span class="names">${user.name}</span></a></li>
				<li><a href="loggout" title="Sair"><span class="glyphicon glyphicon-log-in icone"></span></a></li>
			</ul>
		</div>
	</div>
</nav>