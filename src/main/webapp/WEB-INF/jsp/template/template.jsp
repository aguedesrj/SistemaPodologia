<!DOCTYPE HTML>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<html>
<head>
	<s:head></s:head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"> 
	<title><tiles:insertAttribute name="title"/></title>

	<link rel="stylesheet" type="text/css" href="../resources/css/principal.css">
	<link rel="stylesheet" type="text/css" href="../resources/semantic/css/semantic.css">
	<link rel="stylesheet" type="text/css" href="../resources/bootstrap-3.0.3-dist/dist/css/bootstrap.css" />
	<link rel="stylesheet" type="text/css" href="../resources/css/datepicker.css" />
	<link rel="stylesheet" type="text/css" href="../resources/css/bootstrap-combobox.css" />
	<link rel="stylesheet" type="text/css" href="../resources/css/bootstrap-dialog.min.css" />	
	
	<script src="../resources/js/jquery-1.10.2.js"></script>
	<script src="../resources/js/run_prettify.js"></script>	
	<script src="../resources/js/util.js"></script>	
	<script src="../resources/bootstrap-3.0.3-dist/dist/js/bootstrap.min.js"></script>	
	<script src="../resources/js/bootbox.js"></script>
	<script src="../resources/js/bootstrap-datepicker.js"></script>
	<script src="../resources/js/bootstrap-combobox.js"></script>
	<script src="../resources/js/bootstrap-dialog.min.js"></script>	
</head>
<body>
	<div id="page-wrap">
		<div id="divCarregando" class="ui active inverted dimmer" style="visibility: hidden;">
			<div class="ui text loader">carregando...</div>
		</div>
		<div id="tilesMenu"><tiles:insertAttribute name="menu"/></div>
		<div id="tilesMensagem" style="padding-top: 100px;"><tiles:insertAttribute name="mensagem"/></div>
		<div id="tilesConteudo"><tiles:insertAttribute name="conteudo"/></div>	
		<div id="tilesRodape"><tiles:insertAttribute name="rodape"/></div>
	</div>
</body>
</html>
