<%@ taglib prefix="s"  uri="/struts-tags" %>

<script src="../resources/js/pages/pesquisaProfissional.js"></script>

<div class="container">
	<div class="panel panel-default">
    	<div class="panel-heading">
        	<h3 class="panel-title" style="font-weight: bold;">Lista de Profissionais</h3>
        </div>
		<div class="panel-body">
			<s:form namespace="Profissional" id="formProfissional" name="formProfissional" theme="simple">
				<s:hidden name="profissionalVO.prfCodigo" id="prfCodigo"></s:hidden>
				<table id="tabelaProfissional" class="ui celled table segment" style="width: 780px; padding-top: 20px;">
		  			<thead>
		    			<tr>
		    				<th width="350px;">Nome</th>
		    				<th width="50px">Ação</th>
		  				</tr>
		  			</thead>
		  			<tbody class="tbodyTabelaProfissional"></tbody>
				</table>
			</s:form>		
		</div>
	</div>   	
</div>

<!-- DIV do detalhamento -->
<div id="modalDetalhe" class="modal fade">
	<div class="modal-dialog" style="width: 880px;">
		<div class="modal-content">
			<div class="modal-body">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<div class="panel panel-default" style="margin-top: 30px;">
					<jsp:include page="/WEB-INF/jsp/profissional/modalDetalheProfissional.jsp"></jsp:include>
					<div class="panel-body" style="margin-top: 10px;">
				    	<button id="btnFechar" type="button" class="btn btn-primary">Fechar</button>	    	
					</div>									        
			    </div>				
			</div>
		</div>
	</div>
</div>
