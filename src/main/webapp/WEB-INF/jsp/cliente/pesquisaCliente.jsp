<%@ taglib prefix="s"  uri="/struts-tags" %>

<script src="../resources/js/pages/pesquisaCliente.js"></script>

<div class="container">
	<div class="panel panel-default">
    	<div class="panel-heading">
        	<h3 class="panel-title" style="font-weight: bold;">Pesquisar Clientes</h3>
        </div>
	    <s:form namespace="Cliente" id="formCliente" name="formCliente" theme="simple" cssStyle="margin-left: 15px; margin-top: 15px;">
	    	<s:hidden name="clienteVO.pacCodigo" id="pacCodigo"/>
	    	<s:hidden name="clienteVO.pessoaVO.pesCodigo" id="pesCodigo"/>
	    	<div class="row">
				<div class="col-lg-5">
					<s:label for="proNome" cssClass="control-label">Nome do Cliente</s:label>
					<s:textfield name="clienteVO.pessoaVO.pesNome" id="pesNome" maxlength="120" theme="simple" cssClass="form-control" cssStyle="width: 350px;" onkeypress="javascript:onChange();"/>
				</div>
			</div>	    	
		</s:form>
		<div class="panel-body">
	    	<button id="btnPesquisar" type="button" class="btn btn-primary">Pesquisar</button>
	    	<button style="margin-left: 10px;" id="btnNovo" type="button" class="btn btn-primary">Novo Cliente</button>
		</div>
	</div>
	<div class="panel panel-default">
    	<div class="panel-heading">
        	<h3 class="panel-title" style="font-weight: bold;">Lista de Clientes</h3>
        </div>
		<div class="panel-body">
			<table id="tabelaCliente" class="ui celled table segment" style="width: 780px; padding-top: 20px;">
	  			<thead>
	    			<tr>
	    				<th width="350px;">Nome</th>
	    				<th width="50px">A��o</th>
	  				</tr>
	  			</thead>
	  			<tbody class="tbodyTabelaCliente"></tbody>
			</table>		
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
			    	<div class="panel-heading">
			        	<h3 class="panel-title">Detalhe</h3>
			        </div>
			        <s:form namespace="Cliente" id="formModalCliente" name="formModalCliente" theme="simple" cssStyle="margin-left: 20px; margin-top: 15px;">
					<div class="container">
						<div id="content">
							<ul id="tabs" class="nav nav-tabs" data-tabs="tabs" style="width: 800px;">
								<li class="active"><a href="#DadosPessoais" data-toggle="tab">Dados pessoais</a></li>
								<li><a href="#Endereco" data-toggle="tab">Endere�o</a></li>
								<li><a href="#Contatos" data-toggle="tab">Contatos</a></li>
								<li><a href="#Informacoes" data-toggle="tab">Informa��es</a></li>
							</ul>
							<div id="my-tab-content" class="tab-content">
								<div class="tab-pane active" id="DadosPessoais">
									<div class="row" style="margin-top: 20px;">
										<div class="col-lg-6">
											<label for="spanPerNome" class="control-label">Nome</label><br>
											<span id="spanPerNome"></span>								
										</div>
									</div>
									<div class="row" style="margin-top: 10px;">
										<div class="col-lg-6">
											<label for="spanPesDtNascimento" class="control-label">Data de nascimento</label><br>
											<span id="spanPesDtNascimento"></span>							
										</div>
										<div class="col-lg-6">
											<label for="spanPesSexo" class="control-label">Sexo</label><br>
											<span id="spanPesSexo"></span>	
										</div>
									</div>
									<div class="row" style="margin-top: 10px;">
										<div class="col-lg-6">
											<label for="spanPesCPF" class="control-label">CPF</label><br>
											<span id="spanPesCPF"></span>
										</div>
										<div class="col-lg-6">
											<label for="spanCliDataUltimaConsulta" class="control-label">Data �ltima consulta</label><br>
											<span id="spanCliDataUltimaConsulta"></span>
										</div>
									</div>
									<div class="row" style="margin-top: 10px;">
										<div class="col-lg-6">
											<label for="spanPesObs" class="control-label">Observa��o</label><br>
											<span id="spanPesObs"></span>							
										</div>
									</div>															
								</div>
								<div class="tab-pane" id="Endereco">
									<div class="row" style="margin-top: 20px;">
										<div class="col-lg-6">
											<label for="spanEndLogadouro" class="control-label">Logradouro</label><br>
											<span id="spanEndLogadouro"></span>							
										</div>
										<div class="col-lg-6">
											<label for="spanEndNumero" class="control-label">N�mero</label><br>
											<span id="spanEndNumero"></span>							
										</div>
									</div>
									<div class="row" style="margin-top: 10px;">
										<div class="col-lg-6">
											<label for="spanEndBairro" class="control-label">Bairro</label><br>
											<span id="spanEndBairro"></span>							
										</div>
										<div class="col-lg-6">
											<label for="spanEndCidade" class="control-label">Cidade</label><br>
											<span id="spanEndCidade"></span>							
										</div>
									</div>
									<div class="row" style="margin-top: 10px;">
										<div class="col-lg-6">
											<label for="spanEstNome" class="control-label">Estado</label><br>
											<span id="spanEstNome"></span>
										</div>
										<div class="col-lg-6">
											<label for="spanEndCep" class="control-label">CEP</label><br>
											<span id="spanEndCep"></span>							
										</div>
									</div>															
								</div>
								<div class="tab-pane" id="Contatos">
									<div class="row" style="margin-top: 20px; ">
										<table id="tabelaContatos" class="ui celled table segment" style="width: 780px; padding-top: 20px; margin-left: 20px;">
								  			<thead>
								    			<tr>
								    				<th width="150px">Tipo Contato</th>
								    				<th width="200px">Descri��o</th>
								    				<th width="200px">Respons�vel</th>
								  				</tr>
								  			</thead>
								  			<tbody class="tbodyTabelaContatos"></tbody>
										</table>
									</div>
								</div>
								<div class="tab-pane" id="Informacoes">
									<div class="row" style="margin-top: 20px;">
										<div class="col-lg-3">
										    <div class="input-group">
										    	<span class="input-group-addon">
										    		<input type="checkbox" name="clienteVO.pacLabora" id="pacLabora" disabled="disabled"/>
										      	</span>
										      	<label class="form-control">Labora</label>
										    </div>
										</div>
										<div class="col-lg-3">
											<div class="input-group">
										    	<span class="input-group-addon">
										    		<input type="checkbox" name="clienteVO.pacVisitaPedicuro" id="pacVisitaPedicuro" disabled="disabled"/>
										      	</span>
										      	<label class="form-control">Visita pedicuro</label>
										    </div>					
										</div>
										<div class="col-lg-3">
										    <div class="input-group">
										      	<span class="input-group-addon">
										      		<input type="checkbox" name="clienteVO.pacDiabetes" id="pacDiabetes" disabled="disabled"/>
										      	</span>
										      	<label class="form-control">Diab�tico</label>
										    </div>					
										</div>
									</div>
									<div class="row" style="margin-top: 20px;">
										<div class="col-lg-3">
										    <div class="input-group">
										      	<span class="input-group-addon">
										      		<input type="checkbox" name="clienteVO.pacAndaDescalco" id="pacAndaDescalco" disabled="disabled"/>
										      	</span>
										      	<label class="form-control">Anda descal�o</label>
										    </div>
										</div>
										<div class="col-lg-3">
										    <div class="input-group">
										      	<span class="input-group-addon">
										      		<input type="checkbox" name="clienteVO.pacUnhaEngravada" id="pacUnhaEngravada" disabled="disabled"/>
										      	</span>
										      	<label class="form-control">Unha encravada</label>
										    </div>						
										</div>
										<div class="col-lg-3">
										    <div class="input-group">
										      	<span class="input-group-addon">
										      		<input type="checkbox" name="clienteVO.pacTabagismo" id="pacTabagismo" disabled="disabled"/>
										      	</span>
										      	<label class="form-control">Tabagismo</label>
										    </div>						
										</div>						
									</div>
									<div class="row" style="margin-top: 20px;">
										<div class="col-lg-3">
										    <div class="input-group">
										      	<span class="input-group-addon">
										      		<input type="checkbox" name="clienteVO.pacHipertensao" id="pacHipertensao" disabled="disabled"/>
										      	</span>
										      	<label class="form-control">Hipertens�o</label>
										    </div>						
										</div>
										<div class="col-lg-3">
										    <div class="input-group">
										      	<span class="input-group-addon">
										      		<input type="checkbox" name="clienteVO.pacCirurgiaPes" id="pacCirurgiaPes" disabled="disabled"/>
										      	</span>
										      	<label class="form-control">Cirurgia nos p�s</label>
										    </div>
										</div>
										<div class="col-lg-3">
											<label for="pacCirurgiaMotivo" class="control-label">Motivo da cirurgia</label><br>
											<span id="spanPacCirurgiaMotivo"></span>
										</div>											
									</div>
									<div class="row" style="margin-top: 20px;">
										<div class="col-lg-3">
										    <div class="input-group">
										      	<span class="input-group-addon">
										      		<input type="checkbox" name="clienteVO.pacAlergicoMedicamentos" id="pacAlergicoMedicamentos" disabled="disabled"/>
										      	</span>
										      	<label class="form-control">Al�rgico a medicamentos</label>
										    </div>
										</div>
										<div class="col-lg-3">
											<label for="pacAlergicoQuais" class="control-label">Quais medicamentos</label><br>
											<span id="spanPacAlergicoQuais"></span>	
										</div>
										<div class="col-lg-3">
											<label for="pacCalcadoUtiliza" class="control-label">Cal�ado que utiliza</label><br>
											<span id="spanPacCalcadoUtiliza"></span>	
										</div>												
									</div>	
									<div class="row" style="margin-top: 20px;">
										<div class="col-lg-3">
											<label for="pacNumeroCalcado" class="control-label">N�mero do cal�ado</label><br>
											<span id="spanPacNumeroCalcado"></span>	
										</div>
										<div class="col-lg-3">
											<label for="spanPacPeso" class="control-label">Peso</label><br>
											<span id="spanPacPeso"></span>
										</div>	
										<div class="col-lg-3">
											<label for="pacAltura" class="control-label">Altura</label><br>
											<span id="spanPacAltura"></span>
										</div>																							
									</div>																			
								</div>
							</div>
						</div>
					</div>
					</s:form>			        
					<div class="panel-body" style="margin-top: 10px;">
				    	<button id="btnFechar" type="button" class="btn btn-primary">Fechar</button>	    	
					</div>									        
			    </div>				
			</div>
		</div>
	</div>
</div>
