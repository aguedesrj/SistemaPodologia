<%@ taglib prefix="s"  uri="/struts-tags" %>
<div class="navbar navbar-default navbar-fixed-top" role="navigation">
	<div class="container">
		<div class="navbar-collapse collapse">
        	<ul class="nav navbar-nav">
            	<li class="active"><a href="/SistemaPodologia/Usuario/Home">Home</a></li>
            	<li class="dropdown">
	              	<a href="#" class="dropdown-toggle" data-toggle="dropdown">Cliente <b class="caret"></b></a>
	              	<ul class="dropdown-menu">
	                	<li><a href="/SistemaPodologia/Cliente/Novo">Cadastrar</a></li>
	                	<li><a href="/SistemaPodologia/Cliente/Pesquisa">Pesquisar</a></li>
	              	</ul>	              	
            	</li>
            	<li class="dropdown">
	              	<a href="#" class="dropdown-toggle" data-toggle="dropdown">Tratamento <b class="caret"></b></a>
	              	<ul class="dropdown-menu">
	                	<li><a href="/SistemaPodologia/Tratamento/Novo">Cadastrar</a></li>
	                	<li><a href="/SistemaPodologia/Tratamento/Pesquisa">Pesquisar</a></li>
	              	</ul>  
	            </li>          	           	
			</ul>
        </div>
	</div>
</div>

