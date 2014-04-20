<%@ taglib prefix="s"  uri="/struts-tags" %>

<script src="../resources/js/pages/pesquisaCliente.js"></script>

<div class="container">
	<div class="panel panel-default">
    	<div class="panel-heading">
        	<h3 class="panel-title">Pesquisar Clientes</h3>
        </div>
	    <s:form namespace="Cliente" id="formCliente" name="formCliente" theme="simple" cssStyle="margin-left: 15px; margin-top: 15px;">
	    	<s:hidden name="clienteVO.pessoaVO.pesCodigo" id="pesCodigo"/>
	    	<div class="row">
				<div class="col-lg-5">
					<s:label for="proNome" cssClass="control-label">Nome do Cliente</s:label>
					<s:textfield name="clienteVO.pessoaVO.pesNome" id="pesNome" maxlength="120" theme="simple" cssClass="form-control" cssStyle="width: 250px;" onkeypress="javascript:onChange();"/>
				</div>
			</div>	    	
		</s:form>
		<div class="panel-body">
	    	<button id="btnPesquisar" type="button" class="btn btn-primary">Pesquisar</button>
	    	<button style="margin-left: 10px;" id="btnNovo" type="button" class="btn btn-primary">Novo</button>
		</div>
	</div>
	<div class="panel panel-default">
    	<div class="panel-heading">
        	<h3 class="panel-title">Listagem</h3>
        </div>
		<div class="panel-body">
			<table id="tabelaCliente" class="ui celled table segment" style="width: 780px; padding-top: 20px; margin-left: 20px;">
	  			<thead>
	    			<tr>
	    				<th width="350px;">Nome</th>
	    				<th width="50px">Ação</th>
	  				</tr>
	  			</thead>
	  			<tbody class="tbodyTabelaCliente"></tbody>
			</table>		
		</div>
	</div>   	
</div>

<!-- DIV do detalhamento -->
<div id="modalDetalhe" class="modal fade">
	<div class="modal-dialog" style="width: 400px;">
		<div class="modal-content">
			<div class="modal-body">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<div class="panel panel-default" style="margin-top: 30px;">
			    	<div class="panel-heading">
			        	<h3 class="panel-title">Detalhe</h3>
			        </div>
   					<form id="formDialogCliente" style="margin-left: 15px;">
						<div class="row" style="margin-top: 10px;">
							<div class="col-lg-6">
								<label for="spanPerNome" class="control-label">Nome</label><br>
								<span id="spanPerNome"></span>									
							</div>
							<div class="col-lg-6">
								<label for="spanPesDtNascimento" class="control-label">Data de nascimento</label><br>
								<span id="spanPesDtNascimento"></span>									
							</div>						
						</div>
						<div class="row" style="margin-top: 10px;">
							<div class="col-lg-6">
								<label for="spanPesSexo" class="control-label">Sexo</label><br>
								<span id="spanPesSexo"></span>								
							</div>
							<div class="col-lg-6">
								<label for="spanPesCPF" class="control-label">CPF</label><br>
								<span id="spanPesCPF"></span>								
							</div>						
						</div>
						<div class="row" style="margin-top: 10px;">
							<div class="col-lg-6">
								<label for="spanCliDataUltimaConsulta" class="control-label">Data última consulta</label><br>
								<span id="spanCliDataUltimaConsulta"></span>							
							</div>					
						</div>						
					</form>
					<div class="panel-body" style="margin-top: 10px;">
				    	<button id="btnFechar" type="button" class="btn btn-primary">Fechar</button>	    	
					</div>									        
			    </div>				
			</div>
		</div>
	</div>
</div>
