<!DOCTYPE HTML>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"> 
	<title>::: Sistema Podologia :::</title>
	
	<link rel="stylesheet" type="text/css" href="../resources/css/principal.css">
	<link rel="stylesheet" type="text/css" href="../resources/semantic/css/semantic.css">
	<link rel="stylesheet" type="text/css" href="../resources/bootstrap-3.0.3-dist/dist/css/bootstrap.css" />	
	
	<script src="../resources/js/jquery-1.10.2.js"></script>
</head>
<body>
	<script src="../resources/js/pages/login.js"></script>
	<s:if test="hasActionMessages()">
		<div class="alert alert-success">
        	<s:actionmessage/>
      	</div>		
	</s:if>
	<s:if test="hasActionErrors()">
		<div class="alert alert-danger">
        	<s:actionerror/>
      	</div>		
	</s:if>	
	<div id="divMensagemSucesso" class="alert alert-success" style="display: none;">
       	<span id="spanMsgSuccess"></span>
     	</div>
	<div id="divMensagemErro" class="alert alert-danger" style="display: none;">
       	<span id="spanMsgError"></span>
	</div>
   	<div id="divCarregando" class="ui active inverted dimmer" style="visibility: hidden;">
		<div class="ui text loader">carregando...</div>
   	</div>	
	<s:form namespace="Usuario" id="formLogin" name="formLogin" theme="simple">
		<div class="container" style="width: 350px; margin-top: 50px;">
	    	<div class="panel panel-default">
	        	<div class="panel-heading">
	            	<h3 class="panel-title" style="font-weight: bold;">Efetuar Login</h3>
	            </div>
	            <div class="panel-body">
					<s:textfield name="usuarioVO.usuLogin" id="usuLogin" size="10" maxlength="10" theme="simple" required="true" placeholder="Login" cssClass="form-control"></s:textfield>
					<s:password cssStyle="margin-top: 10px;" name="usuarioVO.usuSenha" id="usuSenha" size="15" maxlength="10" theme="simple" required="true" placeholder="Senha" cssClass="form-control"></s:password>	            
	            	<div style="margin-top: 15px;">
	              		<button id="btnEntrar" type="button" class="btn btn-primary">Entrar</button>
	              	</div>
	            </div>
	         </div>
         </div>
	</s:form>
</body>
</html>