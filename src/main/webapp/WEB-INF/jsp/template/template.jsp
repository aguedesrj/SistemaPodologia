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

	<link rel="stylesheet" type="text/css" href="/resources/css/principal.css">
	<link rel="stylesheet" type="text/css" href="/resources/semantic-0.15.4/css/semantic.min.css" class="ui">
	<link rel="stylesheet" type="text/css" href="/resources/semantic-0.15.4/css/semantic.css">
	
	<script src="resources/js/jquery-1.10.2.js"></script>
	<script src="resources/semantic-0.15.4/javascript/semantic.min.js"></script>
	<script src="resources/semantic-0.15.4/javascript/semantic.js"></script>
</head>
<body>
  	<div id="divCarregando" class="ui active inverted dimmer" style="visibility: visible;">
    	<div class="ui text loader">carregando...</div>
  	</div>
	<div id="tilesMenu"><tiles:insertAttribute name="menu"/></div>
	<div id="tilesMensagem" style="padding-top: 25px;"><tiles:insertAttribute name="mensagem"/></div>
	<div id="tilesConteudo"><tiles:insertAttribute name="conteudo"/></div>	
	<div id="tilesRodape"><tiles:insertAttribute name="rodape"/></div>
</body>
</html>
