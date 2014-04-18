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
	<link rel="stylesheet" type="text/css" href="../resources/semantic/css/grid.min.css">
	<link rel="stylesheet" type="text/css" href="../resources/semantic/css/table.min.css">
	
	<script src="../resources/js/jquery-1.10.2.js"></script>
	
	<script src="../resources/semantic/javascript/history.js"></script>
	<script src="../resources/semantic/javascript/easing.js"></script>
	<script src="../resources/semantic/javascript/ace.js"></script>
	<script src="../resources/semantic/javascript/tablesort.js"></script>
	<script src="../resources/semantic/javascript/waypoints.js"></script>
	
	<script src="../resources/semantic/javascript/semantic.min.js"></script>
	<script src="../resources/semantic/javascript/semantic.js"></script>
	<script src="../resources/semantic/javascript/menu.js"></script>
	<script src="../resources/semantic/javascript/checkbox.js"></script>
	<script src="../resources/semantic/javascript/modal.js"></script>
	<script src="../resources/semantic/javascript/popup.js"></script>
	<script src="../resources/semantic/javascript/tab.js"></script>
</head>
<body style="width: 850px;">
	<div class="main container">
	  	<div id="divCarregando" class="ui active inverted dimmer" style="visibility: hidden;">
	    	<div class="ui text loader">carregando...</div>
	  	</div>
		<div id="tilesMenu"><tiles:insertAttribute name="menu"/></div>
		<div id="tilesMensagem"><tiles:insertAttribute name="mensagem"/></div>
		<div id="tilesConteudo"><tiles:insertAttribute name="conteudo"/></div>	
		<div id="tilesRodape"><tiles:insertAttribute name="rodape"/></div>
	</div>
</body>
</html>
