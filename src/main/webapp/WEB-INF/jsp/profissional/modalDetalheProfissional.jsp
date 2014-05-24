<%@ taglib prefix="s"  uri="/struts-tags" %>

    	<div class="panel-heading">
        	<h3 class="panel-title">Detalhe</h3>
        </div>
        <s:form namespace="Profissional" id="formModalProfissional" name="formModalProfissional" theme="simple" cssStyle="margin-left: 10px; margin-top: 15px;">
			<div class="container">
				<div id="content">
					<ul id="tabs" class="nav nav-tabs" data-tabs="tabs" style="width: 800px;">
						<li class="active"><a href="#DadosPessoais" data-toggle="tab">Dados pessoais</a></li>
						<li><a href="#Endereco" data-toggle="tab">Endereço</a></li>
						<li><a href="#Contatos" data-toggle="tab">Contatos</a></li>
					</ul>
					<div id="my-tab-content" class="tab-content" style="margin-left: 10px;">
						<div class="tab-pane active" id="DadosPessoais">
							<div class="row" style="margin-top: 10px;">
								<div class="col-lg-6">
									<label for="spanPerNome" class="control-label">Nome</label><br>
									<span id="spanPerNome"></span>								
								</div>
							</div>
							<div class="row" style="margin-top: 10px;">
								<div class="col-lg-6">
									<label for="spanPrfDtFormacao" class="control-label">Data de formação</label><br>
									<span id="spanPrfDtFormacao"></span>							
								</div>
								<div class="col-lg-6">
									<label for="spanPrfDescricaoFormacao" class="control-label">Formação</label><br>
									<span id="spanPrfDescricaoFormacao"></span>	
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
							</div>
							<div class="row" style="margin-top: 10px;">
								<div class="col-lg-6">
									<label for="spanPesObs" class="control-label">Observação</label><br>
									<span id="spanPesObs"></span>							
								</div>
							</div>															
						</div>
						<div class="tab-pane" id="Endereco">
							<div class="row" style="margin-top: 10px;">
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
							<div class="row" style="margin-top: 10px; ">
								<table id="tabelaContatos" class="ui celled table segment" style="width: 780px; padding-top: 30px; margin-left: 15px;">
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
					</div>
				</div>
			</div>
		</s:form>
