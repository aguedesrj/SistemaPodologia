<%@ taglib prefix="s" uri="/struts-tags" %>

<script src="../resources/js/pages/manutencaoProfissional.js"></script>

<div class="panel panel-default">
	<div class="panel-heading">
    	<h3 class="panel-title" style="font-weight: bold;">Cadastro de Profissional</h3>
    </div>
	<s:form namespace="Profissional" id="formProfissional" name="formProfissional" theme="simple" cssStyle="margin-left: 20px; margin-top: 15px;">
		<s:hidden name="profissionalVO.pessoaVO.pesCodigo" id="pesCodigo"></s:hidden>
		<s:hidden name="profissionalVO.prfCodigo" id="prfCodigo"></s:hidden>
		<s:hidden name="profissionalVO.enderecoVO.endCodigo" id="endCodigo"></s:hidden>
		<div class="container">
			<div id="content">
				<ul id="tabs" class="nav nav-tabs" data-tabs="tabs" style="width: 800px;">
					<li class="active"><a href="#DadosPessoais" data-toggle="tab">Dados pessoais</a></li>
					<li><a href="#Endereco" data-toggle="tab">Endereço</a></li>
					<li><a href="#Contatos" data-toggle="tab">Contatos</a></li>
				</ul>
				<div id="my-tab-content" class="tab-content">
					<div class="tab-pane active" id="DadosPessoais">
						<div class="row" style="margin-top: 20px;">
							<div class="col-lg-6">
								<s:label for="pesNome" cssClass="control-label">Nome</s:label>
								<s:textfield name="profissionalVO.pessoaVO.pesNome" id="pesNome" maxlength="80" theme="simple" required="true" cssClass="form-control" cssStyle="width: 500px;"/>
							</div>
						</div>
						<div class="row" style="margin-top: 10px;">
							<div class="col-lg-6">
								<s:label for="prfDtFormacao" cssClass="control-label">Data da formação</s:label>
								<s:textfield name="profissionalVO.prfDtFormacao" id="prfDtFormacao" maxlength="10" theme="simple" cssClass="form-control" cssStyle="width: 200px;"/>
							</div>
							<div class="col-lg-6">
								<s:label for="prfDescricaoFormacao" cssClass="control-label">Formação</s:label><br>
								<s:textfield name="profissionalVO.prfDescricaoFormacao" id="prfDescricaoFormacao" maxlength="255" theme="simple" cssClass="form-control" cssStyle="width: 200px;"/>
							</div>
						</div>
						<div class="row" style="margin-top: 10px;">
							<div class="col-lg-6">
								<s:label for="forCodigo" cssClass="control-label">Data de nascimento</s:label>
								<s:textfield name="profissionalVO.pessoaVO.pesDtNascimento" id="pesDtNascimento" maxlength="14" theme="simple" cssClass="form-control" cssStyle="width: 130px;"/>
							</div>
							<div class="col-lg-6">
								<s:label for="catCodigo" cssClass="control-label">Sexo</s:label>
								<br>
								<s:if test='profissionalVO.pessoaVO.pesSexo == "F"'>
									<input type="radio" name="profissionalVO.pessoaVO.pesSexo" id="pesSexo" value="F" checked="checked"/><span style="margin-left: 5px;">Feminino</span>
									<input style="margin-left: 20px;" type="radio" name="profissionalVO.pessoaVO.pesSexo" id="pesSexo" value="M"/><span style="margin-left: 5px;">Masculino</span>	
								</s:if>
								<s:elseif test='profissionalVO.pessoaVO.pesSexo == "M"'>
									<input type="radio" name="profissionalVO.pessoaVO.pesSexo" id="pesSexo" value="F"/><span style="margin-left: 5px;">Feminino</span>
									<input style="margin-left: 20px;" type="radio" name="profissionalVO.pessoaVO.pesSexo" id="pesSexo" value="M" checked="checked"/><span style="margin-left: 5px;">Masculino</span>								
								</s:elseif>
							</div>
						</div>
						<div class="row" style="margin-top: 10px;">
							<div class="col-lg-6">
								<s:label for="forCodigo" cssClass="control-label">CPF</s:label>
								<s:textfield name="profissionalVO.pessoaVO.pesCpf" id="pesCpf" maxlength="14" theme="simple" cssClass="form-control" cssStyle="width: 200px;"/>
							</div>
						</div>
						<div class="row" style="margin-top: 10px;">
							<div class="col-lg-6">
								<s:label for="pesObs" cssClass="control-label">Observação</s:label>
								<s:textarea name="profissionalVO.pessoaVO.pesObs" id="pesObs" cols="90" theme="simple" cssClass="form-control"></s:textarea>
							</div>
						</div>															
					</div>
					<div class="tab-pane" id="Endereco">
						<div class="row" style="margin-top: 20px;">
							<div class="col-lg-6">
								<s:label for="forCodigo" cssClass="control-label">Logradouro</s:label>
								<s:textfield name="profissionalVO.enderecoVO.endLogadouro" id="endLogadouro" maxlength="100" theme="simple" cssStyle="width: 350px;" cssClass="form-control"/>
							</div>
							<div class="col-lg-6">
								<s:label for="catCodigo" cssClass="control-label">Número</s:label>
								<s:textfield name="profissionalVO.enderecoVO.endNumero" id="endNumero" maxlength="8" theme="simple" cssStyle="width: 100px;" cssClass="form-control"/>
							</div>
						</div>
						<div class="row" style="margin-top: 10px;">
							<div class="col-lg-6">
								<s:label for="forCodigo" cssClass="control-label">Bairro</s:label>
								<s:textfield name="profissionalVO.enderecoVO.endBairro" id="endBairro" maxlength="60" theme="simple" cssStyle="width: 300px;" cssClass="form-control"/>
							</div>
							<div class="col-lg-6">
								<s:label for="catCodigo" cssClass="control-label">Cidade</s:label>
								<s:textfield name="profissionalVO.enderecoVO.endCidade" id="endCidade" maxlength="60" theme="simple" cssStyle="width: 300px;" cssClass="form-control"/>
							</div>
						</div>
						<div class="row" style="margin-top: 10px;">
							<div class="col-lg-6">
								<s:label for="forCodigo" cssClass="control-label">Estado</s:label>
								<s:select  cssClass="form-control"
									cssStyle="width: 300px;"
									theme="simple"
									headerKey="-1" 
									headerValue="::: Selecione :::"
									list="profissionalVO.enderecoVO.listaEstadosVO" 
									name="profissionalVO.enderecoVO.estadoVO.estCodigo"
									listKey="estCodigo"
									listValue="estNome"
									id="estCodigo">
								</s:select>
							</div>
							<div class="col-lg-6">
								<s:label for="catCodigo" cssClass="control-label">CEP</s:label>
								<s:textfield name="profissionalVO.enderecoVO.endCep" id="endCep" maxlength="12" theme="simple" cssStyle="width: 150px;" cssClass="form-control"/>
							</div>
						</div>															
					</div>
					<div class="tab-pane" id="Contatos">
						<div class="row" style="margin-top: 20px; ">
							<table id="tabelaContatos" class="ui celled table segment" style="width: 780px; padding-top: 20px; margin-left: 10px;">
					  			<thead>
					    			<tr>
					    				<th width="150px">Tipo Contato</th>
					    				<th width="200px">Descrição</th>
					    				<th width="200px">Responsável</th>
					    				<th width="50px">Ação</th>
					  				</tr>
					  			</thead>
					  			<tbody class="tbodyTabelaContatos"></tbody>
							</table>
							<div class="panel-body">
						    	<button id="btnNovoContato" type="button" class="btn btn-primary">Novo contato</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</s:form>
	<div class="panel-body" style="margin-top: 20px;">
    	<button id="btnSalvar" type="button" class="btn btn-primary">Salvar Profissional</button>
	</div>	
</div>

<!-- DIV do formulário contato -->
<div id="modalContato" class="modal fade">
	<div class="modal-dialog" style="width: 650px;">
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
								<s:hidden name="profissionalVO.contatoVO.tipoContatoVO.tcoDescricao" id="tcoDescricao"></s:hidden>
								<s:hidden name="profissionalVO.contatoVO.conCodigo" id="conCodigo"></s:hidden>
								<s:label for="tcoCodigo" cssClass="control-label">Tipo contato</s:label>
								<div class="input-group">
									<s:select  cssClass="form-control"
										onchange="javascript:setNomeTipoContato();"
										theme="simple"
										headerKey="-1" 
										headerValue="::: Selecione :::"
										list="profissionalVO.contatoVO.listaTipoContatosVO" 
										name="profissionalVO.contatoVO.tipoContatoVO.tcoCodigo"
										listKey="tcoCodigo"
										listValue="tcoDescricao"
										id="tcoCodigo">
									</s:select>
								</div>								
							</div>
							<div class="col-lg-5">
								<s:label for="conDescricao" cssClass="control-label">Descrição</s:label>
								<div class="input-group">
								  	<s:textfield name="profissionalVO.contatoVO.conDescricao" id="conDescricao" maxlength="60" theme="simple" cssStyle="width: 300px;" cssClass="form-control"></s:textfield>
								</div>								
							</div>						
						</div>
						<div class="row" style="margin-top: 10px;">
							<div class="col-lg-5">
								<s:label for="conResponsavel" cssClass="control-label">Responsável</s:label>
								<div class="input-group">
								  	<s:textfield name="profissionalVO.contatoVO.conResponsavel" id="conResponsavel" maxlength="80" theme="simple" cssStyle="width: 300px;" cssClass="form-control"></s:textfield>	
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
