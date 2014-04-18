<%@ taglib prefix="s" uri="/struts-tags" %>

<script src="../resources/js/pages/manutencaoCliente.js"></script>

	    	<div class="panel panel-default">
	        	<div class="panel-heading">
	            	<h3 class="panel-title" style="font-weight: bold;">Cadastro de Cliente</h3>
	            </div>

<s:form namespace="Cliente" id="formCliente" name="formCliente" theme="simple" cssStyle="margin-top: 20px;">
	<div class="container">
		<div id="content">
			<ul id="tabs" class="nav nav-tabs" data-tabs="tabs">
				<li class="active"><a href="#DadosPessoais" data-toggle="tab">Dados pessoais</a></li>
				<li><a href="#Endereco" data-toggle="tab">Endereço</a></li>
				<li><a href="#Contatos" data-toggle="tab">Contatos</a></li>
				<li><a href="#Informacoes" data-toggle="tab">Informações</a></li>
			</ul>
			<div id="my-tab-content" class="tab-content">
				<div class="tab-pane active" id="DadosPessoais">
					<div class="row" style="margin-top: 20px;">
						<div class="col-lg-6">
							<s:label for="proNome" cssClass="control-label">Nome</s:label>
							<s:textfield placeholder="Nome" name="clienteVO.pessoaVO.proNome" id="proNome" maxlength="80" theme="simple" required="true" cssClass="form-control" cssStyle="width: 500px;"/>
						</div>
					</div>
					<div class="row" style="margin-top: 10px;">
						<div class="col-lg-6">
							<s:label for="forCodigo" cssClass="control-label">Data de nascimento</s:label>
							
							<div class="well">
  <div id="datetimepicker1" class="input-append date">
    <input data-format="dd/MM/yyyy hh:mm:ss" type="text"></input>
    <span class="add-on">
      <i data-time-icon="icon-time" data-date-icon="icon-calendar">
      </i>
    </span>
  </div>
</div>
							
							<s:textfield placeholder="Data de nascimento" name="clienteVO.pessoaVO.pesDtNascimento" id="pesDtNascimento" maxlength="14" theme="simple" cssClass="form-control" cssStyle="width: 150px;"/>
						</div>
						<div class="col-lg-6">
							<s:label for="catCodigo" cssClass="control-label">Sexo</s:label>
						</div>
					</div>
					<div class="row" style="margin-top: 10px;">
						<div class="col-lg-6">
							<s:label for="forCodigo" cssClass="control-label">CPF</s:label>
							<s:textfield placeholder="CPF" name="clienteVO.pessoaVO.pesCpf" id="pesCpf" maxlength="14" theme="simple" cssClass="form-control" cssStyle="width: 200px;"/>
						</div>
						<div class="col-lg-6">
							<s:label for="catCodigo" cssClass="control-label">Data última consulta</s:label>
							<s:label name="clienteVO.cliDataUltimaConsulta" id="cliDataUltimaConsulta"></s:label>
						</div>
					</div>
					<div class="row" style="margin-top: 10px;">
						<div class="col-lg-6">
							<s:label for="proNome" cssClass="control-label">Observação</s:label>
							<s:textarea placeholder="Observação" name="clienteVO.pessoaVO.pesObs" id="pesObs" cols="90" theme="simple" cssClass="form-control"></s:textarea>
						</div>
					</div>															
				</div>
				<div class="tab-pane" id="Endereco">
					<div class="row" style="margin-top: 20px;">
						<div class="col-lg-6">
							<s:label for="forCodigo" cssClass="control-label">Logradouro</s:label>
							<s:textfield placeholder="Logradouro" name="clienteVO.enderecoVO.endLogadouro" id="endLogadouro" maxlength="100" theme="simple" cssStyle="width: 350px;" cssClass="form-control"/>
						</div>
						<div class="col-lg-6">
							<s:label for="catCodigo" cssClass="control-label">Número</s:label>
							<s:textfield placeholder="Número" name="clienteVO.enderecoVO.endNumero" id="endNumero" maxlength="8" theme="simple" cssStyle="width: 100px;" cssClass="form-control"/>
						</div>
					</div>
					<div class="row" style="margin-top: 10px;">
						<div class="col-lg-6">
							<s:label for="forCodigo" cssClass="control-label">Bairro</s:label>
							<s:textfield placeholder="Bairro" name="clienteVO.enderecoVO.endBairro" id="endBairro" maxlength="60" theme="simple" cssStyle="width: 350px;" cssClass="form-control"/>
						</div>
						<div class="col-lg-6">
							<s:label for="catCodigo" cssClass="control-label">Cidade</s:label>
							<s:textfield placeholder="Cidade" name="clienteVO.enderecoVO.endCidade" id="endCidade" maxlength="60" theme="simple" cssStyle="width: 350px;" cssClass="form-control"/>
						</div>
					</div>
					<div class="row" style="margin-top: 10px;">
						<div class="col-lg-6">
							<s:label for="forCodigo" cssClass="control-label">Estado</s:label>
							<s:select  cssClass="form-control"
								theme="simple"
								headerKey="-1" 
								headerValue="::: Selecione :::"
								list="clienteVO.enderecoVO.listaEstadosVO" 
								name="clienteVO.enderecoVO.estadoVO.estCodigo"
								listKey="estCodigo"
								listValue="estNome"
								id="estCodigo">
							</s:select>
						</div>
						<div class="col-lg-6">
							<s:label for="catCodigo" cssClass="control-label">CEP</s:label>
							<s:textfield placeholder="CEP" name="clienteVO.enderecoVO.endCep" id="endCep" maxlength="12" theme="simple" cssStyle="width: 150px;" cssClass="form-control"/>
						</div>
					</div>															
				</div>
				<div class="tab-pane" id="Contatos">
					<div class="row" style="margin-top: 20px;">
						<table class="ui celled table segment" style="padding-top: 20px;">
				  			<thead>
				    			<tr>
				    				<th width="150px">Tipo Contato</th>
				    				<th>Descrição</th>
				    				<th>Responsável</th>
				    				<th>Ação</th>
				  				</tr>
				  			</thead>
						</table>
						<div class="panel-body">
					    	<button id="btnNovoContato" type="button" class="btn btn-primary">Novo contato</button>
						</div>
					</div>
				</div>
				<div class="tab-pane" id="Informacoes">
					<div class="row" style="margin-top: 20px;">
						<div class="col-lg-6">
							<s:label for="pacLabora" cssClass="control-label">Labora</s:label>
							<s:checkbox name="clienteVO.pacLabora" id="pacLabora" theme="simple" value="true" cssClass="form-control"/>
						</div>
						<div class="col-lg-6">
							<s:label for="pacVisitaPedicuro" cssClass="control-label">Visita pedicuro</s:label>
							<s:checkbox name="clienteVO.pacVisitaPedicuro" id="pacVisitaPedicuro" theme="simple" value="true" cssClass="form-control"/>
						</div>
						<div class="col-lg-6">
							<s:label for="pacHipertensao" cssClass="control-label">Hipertensão</s:label>
							<s:checkbox name="clienteVO.pacHipertensao" id="pacHipertensao" theme="simple" value="true" cssClass="form-control"/>
						</div>
						<div class="col-lg-6">
							<s:label for="pacTabagismo" cssClass="control-label">Tabagismo</s:label>
							<s:checkbox name="clienteVO.pacTabagismo" id="pacTabagismo" theme="simple" value="true" cssClass="form-control"/>
						</div>						
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="panel-body">
    	<button id="btnSalvar" type="button" class="btn btn-primary">Salvar</button>
	</div>	
</s:form>
</div>

<!-- DIV do formulário contato -->
<div id="modalContato" class="modal fade">
	<div class="modal-dialog" style="width: 500px;">
		<div class="modal-content">
			<!-- dialog body -->
			<div class="modal-body">
				<button type="button" class="close" data-dismiss="modal">&times;</button>
				<div class="panel panel-default" style="margin-top: 30px;">
			    	<div class="panel-heading">
			        	<h3 class="panel-title">Contato</h3>
			        </div>
   					<form id="formModalContato" style="margin-left: 15px;">
						<div class="row" style="margin-top: 10px;">
							<div class="col-lg-5">
								<s:label for="tcoCodigo" cssClass="control-label">Tipo contato</s:label>
								<div class="input-group">
									<s:select  cssClass="form-control"
										theme="simple"
										headerKey="-1" 
										headerValue="::: Selecione :::"
										list="clienteVO.contatoVO.listaTipoContatosVO" 
										name="clienteVO.contatoVO.tipoContatoVO.tcoCodigo"
										listKey="tcoCodigo"
										listValue="tcoDescricao"
										id="tcoCodigo">
									</s:select>
								</div>								
							</div>
							<div class="col-lg-5">
								<s:label for="conDescricao" cssClass="control-label">Descrição</s:label>
								<div class="input-group">
								  	<s:textfield name="clienteVO.contatoVO.conDescricao" id="conDescricao" maxlength="60" theme="simple" required="true" cssStyle="width: 350px;" cssClass="form-control"></s:textfield>
								</div>								
							</div>						
						</div>
						<div class="row" style="margin-top: 10px;">
							<div class="col-lg-5">
								<s:label for="conResponsavel" cssClass="control-label">Responsável</s:label>
								<div class="input-group">
								  	<s:textfield name="clienteVO.contatoVO.conResponsavel" id="conResponsavel" maxlength="80" theme="simple" required="true" cssStyle="width: 350px;" cssClass="form-control"></s:textfield>	
								</div>								
							</div>						
						</div>
					</form>
					<div class="panel-body" style="margin-top: 10px;">
				    	<button id="btnInserir" type="button" class="btn btn-primary">Inserir</button>
				    	<button id="btnCancelar" type="button" class="btn btn-primary" style="margin-left: 10px;">Cancelar</button>	    	
					</div>									        
			    </div>				
			</div>
		</div>
	</div>	
</div>
