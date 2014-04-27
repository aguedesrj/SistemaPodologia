<%@ taglib prefix="s"  uri="/struts-tags" %>

		<link rel="stylesheet" type="text/css" href="../resources/DataTables example_files/bootstrap.css">
		<link rel="stylesheet" type="text/css" href="../resources/DataTables example_files/DT_bootstrap.css">

		<script type="text/javascript" charset="utf-8" language="javascript" src="../resources/DataTables example_files/jquery.dataTables.js"></script>
		<script type="text/javascript" charset="utf-8" language="javascript" src="../resources/DataTables example_files/DT_bootstrap.js"></script>

<script src="../resources/js/pages/pesquisaCliente.js"></script>

						<script type="text/javascript">
							$("#divCarregando").css("visibility", "visible");
						</script>

<div class="container">
	<div class="panel panel-default">
    	<div class="panel-heading">
        	<h3 class="panel-title" style="font-weight: bold;">Lista de Clientes</h3>
        </div>
		<div class="panel-body">
			<div class="container">

				<table class="table table-striped table-bordered dataTable" id="example" aria-describedby="example_info">
					<thead>
						<tr role="row">
							<th class="sorting_asc" role="columnheader" tabindex="0" aria-controls="example" rowspan="1" colspan="1" aria-sort="ascending" aria-label="Rendering engine: activate to sort column descending" style="width: 350px;">Nome</th>
							<th class="sorting" role="columnheader" tabindex="0" aria-controls="example" rowspan="1" colspan="1" aria-label="Browser: activate to sort column ascending" style="width: 50px;">Ação</th>
						</tr>
					</thead>
					<tbody role="alert" aria-live="polite" aria-relevant="all">
						<s:iterator value="listaPessoaVO" status="stat">
							<tr>  
							    <td>  
							        <s:property value="listaPessoaVO[#stat.index].pesNome"/>  
							    </td>  
							    <td>  
									<span title='Clique aqui para detalhar o Cliente.' class='large glyphicon glyphicon-zoom-in' onclick='javascript:detalhar("<s:property value="listaPessoaVO[#stat.index].pesCodigo"/>");' style='cursor:pointer;'></span>
									<span title='Clique aqui para alterar o Cliente.' class='large glyphicon glyphicon-cog' onclick='javascript:alterar("<s:property value="listaPessoaVO[#stat.index].pesCodigo"/>");' style='cursor:pointer; margin-left: 20px;'></span>
							    </td>    
							</tr>  						
						</s:iterator>
					</tbody>
				</table>
			</div>
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
					<div class="container">
						<div id="content">
							<ul id="tabs" class="nav nav-tabs" data-tabs="tabs" style="width: 800px; margin-left: 15px; margin-top: 15px;">
								<li class="active"><a href="#DadosPessoais" data-toggle="tab">Dados pessoais</a></li>
								<li><a href="#Endereco" data-toggle="tab">Endereço</a></li>
								<li><a href="#Contatos" data-toggle="tab">Contatos</a></li>
								<li><a href="#Informacoes" data-toggle="tab">Informações</a></li>
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
											<label for="spanCliDataUltimaConsulta" class="control-label">Data última consulta</label><br>
											<span id="spanCliDataUltimaConsulta"></span>
										</div>
									</div>
									<div class="row" style="margin-top: 10px;">
										<div class="col-lg-6">
											<label for="spanPesObs" class="control-label">Observação</label><br>
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
											<label for="spanEndNumero" class="control-label">Número</label><br>
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
								    				<th width="200px">Descrição</th>
								    				<th width="200px">Responsável</th>
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
										        	<s:checkbox name="clienteVO.pacLabora" id="pacLabora" theme="simple" disabled="disabled"/>
										      	</span>
										      	<label class="form-control">Labora</label>
										    </div>
										</div>
										<div class="col-lg-3">
											<div class="input-group">
										    	<span class="input-group-addon">
										        	<s:checkbox name="clienteVO.pacVisitaPedicuro" id="pacVisitaPedicuro" theme="simple" disabled="disabled"/>
										      	</span>
										      	<label class="form-control">Visita pedicuro</label>
										    </div>					
										</div>
										<div class="col-lg-3">
										    <div class="input-group">
										      	<span class="input-group-addon">
										        	<s:checkbox name="clienteVO.pacDiabetes" id="pacDiabetes" theme="simple" disabled="disabled"/>
										      	</span>
										      	<label class="form-control">Diabético</label>
										    </div>					
										</div>
									</div>
									<div class="row" style="margin-top: 20px;">
										<div class="col-lg-3">
										    <div class="input-group">
										      	<span class="input-group-addon">
										        	<s:checkbox name="clienteVO.pacAndaDescalco" id="pacAndaDescalco" theme="simple" disabled="disabled"/>
										      	</span>
										      	<label class="form-control">Anda descalço</label>
										    </div>
										</div>
										<div class="col-lg-3">
										    <div class="input-group">
										      	<span class="input-group-addon">
										        	<s:checkbox name="clienteVO.pacUnhaEngravada" id="pacUnhaEngravada" theme="simple" disabled="disabled"/>
										      	</span>
										      	<label class="form-control">Unha encravada</label>
										    </div>						
										</div>
										<div class="col-lg-3">
										    <div class="input-group">
										      	<span class="input-group-addon">
										        	<s:checkbox name="clienteVO.pacTabagismo" id="pacTabagismo" theme="simple" disabled="disabled"/>
										      	</span>
										      	<label class="form-control">Tabagismo</label>
										    </div>						
										</div>						
									</div>
									<div class="row" style="margin-top: 20px;">
										<div class="col-lg-3">
										    <div class="input-group">
										      	<span class="input-group-addon">
										        	<s:checkbox name="clienteVO.pacHipertensao" id="pacHipertensao" theme="simple" disabled="disabled"/>
										      	</span>
										      	<label class="form-control">Hipertensão</label>
										    </div>						
										</div>
										<div class="col-lg-3">
										    <div class="input-group">
										      	<span class="input-group-addon">
										        	<s:checkbox name="clienteVO.pacCirurgiaPes" id="pacCirurgiaPes" theme="simple" disabled="disabled"/>
										      	</span>
										      	<label class="form-control">Cirurgia nos pés</label>
										    </div>
										</div>
										<div class="col-lg-3">
											<label for="pacCirurgiaMotivo" class="control-label">Motivo da cirurgia</label><br>
											<s:textfield placeholder="Motivo da cirurgia" name="clienteVO.pacCirurgiaMotivo" id="pacCirurgiaMotivo" maxlength="255" theme="simple" cssStyle="width: 220px;" cssClass="form-control"/>
										</div>											
									</div>
									<div class="row" style="margin-top: 20px;">
										<div class="col-lg-3">
										    <div class="input-group">
										      	<span class="input-group-addon">
										        	<s:checkbox name="clienteVO.pacAlergicoMedicamentos" id="pacAlergicoMedicamentos" theme="simple" disabled="disabled"/>
										      	</span>
										      	<label class="form-control">Alérgico a medicamentos</label>
										    </div>
										</div>
										<div class="col-lg-3">
											<label for="pacAlergicoQuais" class="control-label">Quais medicamentos</label><br>
											<span id="spanPacAlergicoQuais"></span>	
										</div>
										<div class="col-lg-3">
											<label for="pacCalcadoUtiliza" class="control-label">Calçado que utiliza</label><br>
											<span id="spanPacCalcadoUtiliza"></span>	
										</div>												
									</div>	
									<div class="row" style="margin-top: 20px;">
										<div class="col-lg-3">
											<label for="pacNumeroCalcado" class="control-label">Número do calçado</label><br>
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
					<div class="panel-body" style="margin-top: 10px;">
				    	<button id="btnFechar" type="button" class="btn btn-primary">Fechar</button>	    	
					</div>									        
			    </div>				
			</div>
		</div>
	</div>
</div>

						<script type="text/javascript">
						$("#divCarregando").css("visibility", "hidden");
						</script>	
