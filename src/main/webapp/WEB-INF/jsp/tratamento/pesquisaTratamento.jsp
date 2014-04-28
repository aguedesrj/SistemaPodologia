<%@ taglib prefix="s"  uri="/struts-tags" %>

<script src="../resources/js/pages/pesquisaTratamento.js"></script>

<div class="container">
	<div class="panel panel-default">
    	<div class="panel-heading">
        	<h3 class="panel-title" style="font-weight: bold;">Pesquisar Tratamentos</h3>
        </div>
	    <s:form namespace="Tratamento" id="formTratamento" name="formTratamento" theme="simple" cssStyle="margin-left: 15px; margin-top: 15px;">
	    	<s:hidden name="tratamentoVO.traCodigo" id="traCodigo"/>
	    	<div class="row">
				<div class="col-lg-5">
					<s:label for="traDescricao" cssClass="control-label">Descrição do Tratamento</s:label>
					<s:textfield name="tratamentoVO.traDescricao" id="traDescricao" maxlength="120" theme="simple" cssClass="form-control" cssStyle="width: 350px;" onkeypress="javascript:onChange();"/>
				</div>
			</div>	    	
		</s:form>
		<div class="panel-body">
	    	<button id="btnPesquisar" type="button" class="btn btn-primary">Pesquisar</button>
	    	<button style="margin-left: 10px;" id="btnNovo" type="button" class="btn btn-primary">Novo Tratamento</button>
		</div>
	</div>
	<div class="panel panel-default">
    	<div class="panel-heading">
        	<h3 class="panel-title" style="font-weight: bold;">Lista de Tratamentos</h3>
        </div>
		<div class="panel-body">
			<table id="tabelaTratamento" class="ui celled table segment" style="width: 780px; padding-top: 20px;">
	  			<thead>
	    			<tr>
	    				<th width="350px;">Descrição</th>
	    				<th width="50px">Ação</th>
	  				</tr>
	  			</thead>
	  			<tbody class="tbodyTabelaTratamento"></tbody>
			</table>		
		</div>
	</div>   	
</div>

<!-- DIV do detalhamento -->
<div id="modalDetalhe" class="modal fade">
	<div class="modal-dialog" style="width: 550px;">
		<div class="modal-content">
			<div class="modal-body">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<div class="panel panel-default" style="margin-top: 30px;">
			    	<div class="panel-heading">
			        	<h3 class="panel-title">Detalhe</h3>
			        </div>
			        <s:form namespace="Tratamento" id="formModalTratamento" name="formModalTratamento" theme="simple">
			        	<div class="panel-body">
							<div class="row">
								<div class="col-lg-6">
									<label for="spanTraDescricao" class="control-label">Descrição</label><br>
									<span id="spanTraDescricao"></span>								
								</div>
							</div>
							<div class="row" style="margin-top: 10px;">
								<div class="col-lg-6">
									<label for="spanTraTempo" class="control-label">Tempo (em minutos)</label><br>
									<span id="spanTraTempo"></span>							
								</div>
								<div class="col-lg-6">
									<label for="spanTraPreco" class="control-label">Preço</label><br>
									<span id="spanTraPreco"></span>	
								</div>								
							</div>	
							<div class="row" style="margin-top: 10px;">
								<div class="col-lg-6" style="width: 500px;">
								    <div class="input-group">
								      	<span class="input-group-addon">
								      		<input type="checkbox" id="traFlag" disabled="disabled"/>
								      	</span>
								      	<label class="form-control">Tratamento possa ser feito 2 vezes no mesmo horário.</label>
								    </div>																
								</div>								
							</div>
							<div class="row" style="margin-top: 10px;">
								<div class="col-lg-6" style="width: 500px;">
									<label for="spanTraObs" class="control-label">Observação</label><br>
									<span id="spanTraObs"></span>							
								</div>								
							</div>
						</div>							        
			        </s:form>
					<div class="panel-body">
				    	<button id="btnFechar" type="button" class="btn btn-primary">Fechar</button>	    	
					</div>								        
			    </div>				
			</div>
		</div>
	</div>
</div>
