<%@ taglib prefix="s" uri="/struts-tags" %>

<link rel="stylesheet" type="text/css" href="../resources/css/bootstrap_calendar.css">
<script src="../resources/js/bootstrap_calendar.js"></script>

<script src="../resources/js/pages/manutencaoConsulta.js"></script>

<div class="panel panel-default">
	<div class="panel-heading">
    	<h3 class="panel-title" style="font-weight: bold;">Marcação de Consulta</h3>
    </div>
	<s:form namespace="Consulta" id="formConsulta" name="formConsulta" theme="simple" cssStyle="margin-left: 20px; margin-top: 15px;">
    	<div class="container">
			<div class="row">
				<div class="col-lg-6" style="width: 300px; margin-top: 5px;">
			    	<div class="calendar_test"></div>
			    	<br/><br/>
					<div class="btn-group">
						<ul class="dropdown-menu">
							<li class="noborder calendar_test"></li>
						</ul>
					</div>
				</div>
				<div class="row">
					<div class="col-lg-6">
						<s:label for="prfCodigo" cssClass="control-label" cssStyle="font-weight: bold;">Profissional</s:label>
						<s:select  cssClass="form-control"
							cssStyle="width: 300px;"
							theme="simple"
							headerKey="-1" 
							headerValue="::: Selecione :::"
							list="consultaVO.listaProfissionalVO" 
							name="clienteVO.profissionalVO.prfCodigo"
							listKey="prfCodigo"
							listValue="pessoaVO.pesNome"
							id="prfCodigo">
						</s:select>					
					</div>
					<div class="col-lg-6" style="margin-top: 10px;">
						<s:label for="pacCodigo" cssClass="control-label" cssStyle="font-weight: bold;">Cliente</s:label>
						<s:select  cssClass="form-control"
							cssStyle="width: 300px;"
							theme="simple"
							headerKey="-1" 
							headerValue="::: Selecione :::"
							list="consultaVO.listaClienteVO" 
							name="clienteVO.clienteVO.pacCodigo"
							listKey="pacCodigo"
							listValue="pessoaVO.pesNome"
							id="pacCodigo">
						</s:select>
					</div>	
					<div class="col-lg-6" style="margin-top: 10px;">
						<s:label for="traCodigo" cssClass="control-label" cssStyle="font-weight: bold;">Tratamento</s:label>
						<s:select  cssClass="form-control"
							cssStyle="width: 300px;"
							theme="simple"
							headerKey="-1" 
							headerValue="::: Selecione :::"
							list="consultaVO.listaTratamentoVO" 
							name="clienteVO.tratamentoVO.traCodigo"
							listKey="traCodigo"
							listValue="traDescricao"
							id="traCodigo">
						</s:select>
					</div>
					<div class="col-lg-6" style="margin-top: 10px;">
						<label for="spanDataMarcacaoConsulta" class="control-label" style="font-weight: bold;">Data da selecionada</label><br>
						<span id="spanDataMarcacaoConsulta"></span>					
					</div>									
				</div>				
			</div>
			<div class="row">
				<table id="tabelaConsulta" class="ui celled table segment" style="width: 780px; padding-left: 20px;">
		  			<thead>
		    			<tr>
		    				<th width="50px">Horário</th>
		    				<th width="350px;">Paciente</th>
		    				<th width="350px;">Tratamento</th>
		    				<th width="100px;">Preço</th>
		  				</tr>
		  			</thead>
		  			<tbody class="tbodyTabelaConsulta"></tbody>
				</table>			
			</div>
		</div>	
	</s:form>
	<div class="panel-body" style="margin-top: 20px;">
    	<button id="btnSalvar" type="button" class="btn btn-primary">Salvar Consulta</button>
	</div>	
</div>