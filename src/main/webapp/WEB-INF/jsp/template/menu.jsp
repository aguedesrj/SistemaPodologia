<%@ taglib prefix="s"  uri="/struts-tags" %>

<div class="navbar navbar-default navbar-fixed-top" role="navigation">
	<div class="container">
		<div class="navbar-collapse collapse">
        	<ul class="nav navbar-nav">
            	<li class="active"><a href="../Usuario/Home">Home</a></li>
            	<li class="dropdown">
	              	<a href="#" class="dropdown-toggle" data-toggle="dropdown">Produto <b class="caret"></b></a>
	              	<ul class="dropdown-menu">
	                	<li><a href="../Produto/InicioManutencao">Cadastrar</a></li>
	                	<li><a href="../Produto/InicioPesquisa">Pesquisar</a></li>
	              	</ul>
            	</li>
            	<li><a href="#">Vendas</a></li>
            	<li class="dropdown">
	              	<a href="#" class="dropdown-toggle" data-toggle="dropdown">Usuários <b class="caret"></b></a>
	              	<ul class="dropdown-menu">
	                	<li><a href="../Usuario/ExibePesquisaUsuario">Pesquisar Usuário</a></li>
	                	<li><a href="../Usuario/ManutencaoUsuario">Manutenção Usuário</a></li>
	                	<li><a href="../Usuario/ManutencaoPerfil">Manutenção Perfil</a></li>
	              	</ul>
            	</li>            	
			</ul>
        </div>
	</div>
</div>
d