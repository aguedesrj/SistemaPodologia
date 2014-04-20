<%@ taglib prefix="s" uri="/struts-tags" %>

<script src="../resources/js/pages/manutencaoCliente.js"></script>

	    	<div class="panel panel-default">
	        	<div class="panel-heading">
	            	<h3 class="panel-title" style="font-weight: bold;">Cadastro de Cliente</h3>
	            </div>

<s:form namespace="Cliente" id="formCliente" name="formCliente" theme="simple" cssStyle="margin-top: 20px;">
	<div class="container">
		<div id="content">
			<ul id="tabs" class="nav nav-tabs" data-tabs="tabs" style="width: 800px;">
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
							<s:textfield placeholder="Data de nascimento" name="clienteVO.pessoaVO.pesDtNascimento" id="pesDtNascimento" maxlength="14" theme="simple" cssClass="form-control" cssStyle="width: 150px;"/>
						</div>
						<div class="col-lg-6">
							<s:label for="catCodigo" cssClass="control-label">Sexo</s:label>
							<div class="col-lg-2">
								<div class="input-group" style="width: 100px;">
					               <span class="input-group-addon">
					                  <s:radio name="clienteVO.pessoaVO.pesSexo" id="pacTabagismo" theme="simple" list="#{'F':''}"/>
					               </span>
					               <label class="form-control">Feminino</label>						
								 </div>
					            <div class="input-group" style="width: 100px;">
					               <span class="input-group-addon">
					                  <s:radio name="clienteVO.pessoaVO.pesSexo" id="pacTabagismo" theme="simple" list="#{'M':''}"/>
					               </span>
					               <label class="form-control">Masculino</label>
					            </div>
					        </div>
						</div>
					</div>
					<div class="row" style="margin-top: 10px;">
						<div class="col-lg-6">
							<s:label for="forCodigo" cssClass="control-label">CPF</s:label>
							<s:textfield placeholder="CPF" name="clienteVO.pessoaVO.pesCpf" id="pesCpf" maxlength="14" theme="simple" cssClass="form-control" cssStyle="width: 200px;"/>
						</div>
						<div class="col-lg-6">
							<s:label for="catCodigo" cssClass="control-label">Data última consulta</s:label><br>
							<s:label cssStyle="font-weight: bold;" name="clienteVO.cliDataUltimaConsulta" id="cliDataUltimaConsulta"></s:label>
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
							<s:textfield placeholder="Bairro" name="clienteVO.enderecoVO.endBairro" id="endBairro" maxlength="60" theme="simple" cssStyle="width: 300px;" cssClass="form-control"/>
						</div>
						<div class="col-lg-6">
							<s:label for="catCodigo" cssClass="control-label">Cidade</s:label>
							<s:textfield placeholder="Cidade" name="clienteVO.enderecoVO.endCidade" id="endCidade" maxlength="60" theme="simple" cssStyle="width: 300px;" cssClass="form-control"/>
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
					<div class="row" style="margin-top: 20px; ">
						<table id="tabelaContatos" class="ui celled table segment" style="width: 780px; padding-top: 20px; margin-left: 20px;">
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
				<div class="tab-pane" id="Informacoes">
					<div class="row" style="margin-top: 20px;">
						<div class="col-lg-3">
						    <div class="input-group">
						    	<span class="input-group-addon">
						        	<s:checkbox name="clienteVO.pacLabora" id="pacLabora" theme="simple"/>
						      	</span>
						      	<label class="form-control">Labora</label>
						    </div>
						</div>
						<div class="col-lg-3">
							<div class="input-group">
						    	<span class="input-group-addon">
						        	<s:checkbox name="clienteVO.pacVisitaPedicuro" id="pacVisitaPedicuro" theme="simple"/>
						      	</span>
						      	<label class="form-control">Visita pedicuro</label>
						    </div>					
						</div>
						<div class="col-lg-3">
						    <div class="input-group">
						      	<span class="input-group-addon">
						        	<s:checkbox name="clienteVO.pacDiabetes" id="pacDiabetes" theme="simple"/>
						      	</span>
						      	<label class="form-control">Diabético</label>
						    </div>					
						</div>
					</div>
					<div class="row" style="margin-top: 20px;">
						<div class="col-lg-3">
						    <div class="input-group">
						      	<span class="input-group-addon">
						        	<s:checkbox name="clienteVO.pacAndaDescalco" id="pacAndaDescalco" theme="simple"/>
						      	</span>
						      	<label class="form-control">Anda descalço</label>
						    </div>
						</div>
						<div class="col-lg-3">
						    <div class="input-group">
						      	<span class="input-group-addon">
						        	<s:checkbox name="clienteVO.pacUnhaEngravada" id="pacUnhaEngravada" theme="simple"/>
						      	</span>
						      	<label class="form-control">Unha encravada</label>
						    </div>						
						</div>
						<div class="col-lg-3">
						    <div class="input-group">
						      	<span class="input-group-addon">
						        	<s:checkbox name="clienteVO.pacTabagismo" id="pacTabagismo" theme="simple"/>
						      	</span>
						      	<label class="form-control">Tabagismo</label>
						    </div>						
						</div>						
					</div>
					<div class="row" style="margin-top: 20px;">
						<div class="col-lg-3">
						    <div class="input-group">
						      	<span class="input-group-addon">
						        	<s:checkbox name="clienteVO.pacHipertensao" id="pacHipertensao" theme="simple"/>
						      	</span>
						      	<label class="form-control">Hipertensão</label>
						    </div>						
						</div>
						<div class="col-lg-3">
						    <div class="input-group">
						      	<span class="input-group-addon">
						        	<s:checkbox name="clienteVO.pacCirurgiaPes" id="pacCirurgiaPes" theme="simple"/>
						      	</span>
						      	<label class="form-control">Cirurgia nos pés</label>
						    </div>
						</div>
						<div class="col-lg-3">
							<s:label for="pacCirurgiaMotivo" cssClass="control-label">Motivo da cirurgia</s:label>
							<s:textfield placeholder="Motivo da cirurgia" name="clienteVO.pacCirurgiaMotivo" id="pacCirurgiaMotivo" maxlength="255" theme="simple" cssStyle="width: 220px;" cssClass="form-control"/>
						</div>											
					</div>
					<div class="row" style="margin-top: 20px;">
						<div class="col-lg-3">
						    <div class="input-group">
						      	<span class="input-group-addon">
						        	<s:checkbox name="clienteVO.pacAlergicoMedicamentos" id="pacAlergicoMedicamentos" theme="simple"/>
						      	</span>
						      	<label class="form-control">Alérgico a medicamentos</label>
						    </div>
						</div>
						<div class="col-lg-3">
							<s:label for="pacAlergicoQuais" cssClass="control-label">Quais medicamentos</s:label>
							<s:textfield placeholder="Quais medicamentos" name="clienteVO.pacAlergicoQuais" id="pacAlergicoQuais" maxlength="255" theme="simple" cssStyle="width: 220px;" cssClass="form-control"/>
						</div>
						<div class="col-lg-3">
							<s:label for="pacCalcadoUtiliza" cssClass="control-label">Calçado que utiliza</s:label>
							<s:textfield name="clienteVO.pacCalcadoUtiliza" id="pacCalcadoUtiliza" maxlength="100" theme="simple" cssStyle="width: 120px;" cssClass="form-control"/>
						</div>												
					</div>	
					<div class="row" style="margin-top: 20px;">
						<div class="col-lg-3">
							<s:label for="pacNumeroCalcado" cssClass="control-label">Número do calçado</s:label>
							<s:textfield name="clienteVO.pacNumeroCalcado" id="pacNumeroCalcado" maxlength="2" theme="simple" cssStyle="width: 220px;" cssClass="form-control"/>
						</div>
						<div class="col-lg-3">
							<s:label for="pacPeso" cssClass="control-label">Peso</s:label>
							<s:textfield name="clienteVO.pacPeso" id="pacPeso" maxlength="2" theme="simple" cssStyle="width: 50px;" cssClass="form-control"/>
						</div>	
						<div class="col-lg-3">
							<s:label for="pacAltura" cssClass="control-label">Altura</s:label>
							<s:textfield name="clienteVO.pacAltura" id="pacAltura" maxlength="2" theme="simple" cssStyle="width: 50px;" cssClass="form-control"/>
						</div>																							
					</div>																			
				</div>
			</div>
		</div>
	</div>
	<div class="panel-body" style="margin-top: 20px;">
    	<button id="btnSalvar" type="button" class="btn btn-primary">Salvar Cliente</button>
	</div>	
</s:form>
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
								<s:hidden name="clienteVO.contatoVO.tipoContatoVO.tcoDescricao" id="tcoDescricao"></s:hidden>
								<s:hidden name="clienteVO.contatoVO.conCodigo" id="conCodigo"></s:hidden>
								<s:label for="tcoCodigo" cssClass="control-label">Tipo contato</s:label>
								<div class="input-group">
									<s:select  cssClass="form-control"
										onchange="javascript:setNomeTipoContato();"
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
								  	<s:textfield name="clienteVO.contatoVO.conDescricao" id="conDescricao" maxlength="60" theme="simple" cssStyle="width: 300px;" cssClass="form-control"></s:textfield>
								</div>								
							</div>						
						</div>
						<div class="row" style="margin-top: 10px;">
							<div class="col-lg-5">
								<s:label for="conResponsavel" cssClass="control-label">Responsável</s:label>
								<div class="input-group">
								  	<s:textfield name="clienteVO.contatoVO.conResponsavel" id="conResponsavel" maxlength="80" theme="simple" cssStyle="width: 300px;" cssClass="form-control"></s:textfield>	
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
