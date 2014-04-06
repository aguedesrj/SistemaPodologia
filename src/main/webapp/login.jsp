<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML>
<html>
<head>
	<title>::: Sistema Podologia :::</title>
	<link rel="stylesheet" type="text/css" href="../resources/css/principal.css">
	<link rel="stylesheet" type="text/css" href="../resources/semantic-0.15.4/css/semantic.css">
	
	<script src="../resources/js/jquery-1.10.2.js"></script>
	<script src="../resources/semantic-0.15.4/javascript/semantic.min.js"></script>
	<script src="../resources/semantic-0.15.4/javascript/semantic.js"></script>
</head>
<body>
	<script src="../resources/js/pages/login.js"></script>
	<div id="divMessageRed" class="ui small red message" style="visibility: hidden;"><span id="spanMessage"></span></div>
  	<div id="divCarregando" class="ui active inverted dimmer" style="visibility: hidden;">
    	<div class="ui text loader">carregando...</div>
  	</div>
  	<s:form namespace="Usuario" id="formLogin" name="formLogin" theme="simple">
	  	<div align="center" style="padding-top: 50px;">
			<div align="left" class="ui form segment" style="width: 300px;">
		  		<div class="field">
		    		<label>Login</label>
		    		<div class="ui mini left labeled icon input">
		      			<s:textfield name="usuario.usuLogin" id="usuLogin" maxlength="10" theme="simple" placeholder="Login"></s:textfield>
		      			<i class="user icon"></i>
		      			<div class="ui corner label">
		       				<i class="icon asterisk"></i>
		      			</div>
		    		</div>
		  		</div>
		  		<div class="field">
		    		<label>Senha</label>
		    		<div class="ui mini left labeled icon input">
		      			<s:password name="usuario.usuSenha" id="usuSenha" maxlength="10" theme="simple" placeholder="Senha"></s:password>
		      			<i class="lock icon"></i>
		      			<div class="ui corner label">
		        			<i class="icon asterisk"></i>
		      			</div>
		    		</div>
		  		</div>
		  		<div id="divBtnEfetuarLogin" class="mini circular ui blue submit button">Efetuar login</div>
			</div>
		</div>
	</s:form>
</body>
</html>