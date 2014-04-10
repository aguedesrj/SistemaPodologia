<%@ taglib prefix="s" uri="/struts-tags" %>

<script src="../resources/js/pages/manutencaoCliente.js"></script>

<s:form namespace="Cliente" id="formCliente" name="formCliente" theme="simple">
	<div class="ui pointing secondary demo menu">
		<a class="active red item" data-tab="DadosPessoais">Dados pessoais</a>
	  	<a class="blue item" data-tab="Endereco">Endereço</a>
	  	<a class="green item" data-tab="Contatos">Contatos</a>
	  	<a class="black item" data-tab="Informacoes">Informações</a>
	</div>
	<!-- Dados pessoais -->
	<div class="ui active tab segment" data-tab="DadosPessoais">
		<div class="ui form">
	  		<div class="field">
	    		<label>Nome</label>
	    		<s:textfield placeholder="Nome" name="clienteVO.pessoaVO.proNome" id="proNome" maxlength="80" theme="simple" required="true" cssClass="form-control" cssStyle="width: 500px;"/>
	  		</div>
		  	<div class="two fields">
		    	<div class="field">
		      		<label>Data de nascimento</label>
		      		<s:textfield placeholder="Data de nascimento" name="clienteVO.pessoaVO.pesDtNascimento" id="pesDtNascimento" maxlength="14" theme="simple" required="true" cssClass="form-control" cssStyle="width: 150px;"/>
		    	</div>
			    <div class="field">
			    	<label>Sexo</label>
			    	<div class="ui radio checkbox" style="padding-right: 20px;">
			        	<input id="choice-1" name="fruit" type="radio">
			        	<label for="choice-1">Feminino</label>
			      	</div>
			      	<div class="ui radio checkbox">
			        	<input id="choice-2" name="fruit" type="radio">
			        	<label for="choice-2">Masculino</label>
			      	</div>      
			    </div>
		  	</div>
		  	<div class="two fields">
		    	<div class="field">
		      		<label>CPF</label>
		      		<s:textfield placeholder="CPF" name="clienteVO.pessoaVO.pesCpf" id="pesCpf" maxlength="14" theme="simple" required="true" cssClass="form-control" cssStyle="width: 200px;"/>
		    	</div>
		    	<div class="field">
		      		<label>Data última consulta</label>
		      		<s:label name="clienteVO.cliDataUltimaConsulta" id="cliDataUltimaConsulta"></s:label>
		    	</div>
		  	</div>
	  		<div class="field">
	    		<label>Observação</label>
	    		<s:textarea placeholder="Observação" name="clienteVO.pessoaVO.pesObs" id="pesObs" cols="90" theme="simple" cssClass="form-control"></s:textarea>
	  		</div>
		</div>
	</div>
	
	<!-- Endereço -->
	<div class="ui tab segment" data-tab="Endereco">
		<div class="ui form">
	  		<div class="two fields">
		    	<div class="field">
		      		<label>Logradouro</label>
		      		<s:textfield placeholder="Logradouro" name="clienteVO.enderecoVO.endLogadouro" id="endLogadouro" maxlength="100" theme="simple" cssStyle="width: 350px;"/>
		    	</div>
		    	<div class="field">
		      		<label>Número</label>
		      		<s:textfield placeholder="Número" name="clienteVO.enderecoVO.endNumero" id="endNumero" maxlength="8" theme="simple" cssStyle="width: 100px;"/>
		    	</div>
		  	</div>
	  		<div class="two fields">
		    	<div class="field">
		      		<label>Bairro</label>
		      		<s:textfield placeholder="Bairro" name="clienteVO.enderecoVO.endBairro" id="endBairro" maxlength="60" theme="simple" cssStyle="width: 350px;"/>
		    	</div>
		    	<div class="field">
		      		<label>Cidade</label>
		      		<s:textfield placeholder="Cidade" name="clienteVO.enderecoVO.endCidade" id="endCidade" maxlength="60" theme="simple" cssStyle="width: 350px;"/>
		    	</div>
		  	</div>
	  		<div class="two fields">
		    	<div class="field">
		      		<label>Estado</label>
		      		<s:textfield placeholder="Estado" name="clienteVO.enderecoVO.endCep" id="endCep" maxlength="12" theme="simple" cssStyle="width: 150px;"/>
		    	</div>
		    	<div class="field">
		      		<label>CEP</label>
		      		<s:textfield placeholder="CEP" name="clienteVO.enderecoVO.endCep" id="endCep" maxlength="12" theme="simple" cssStyle="width: 150px;"/>
		    	</div>
		  	</div>		  			  	
		</div>
	</div>
	
	<!-- Contatos -->
	<div class="ui tab segment" data-tab="Contatos">
		<table class="ui celled table segment">
  			<thead>
    			<tr>
    				<th width="150px">Tipo Contato</th>
    				<th>Descrição</th>
    				<th>Responsável</th>
    				<th>Ação</th>
  				</tr>
  			</thead>
  			<tbody>
    		<tr>
      			<td>John</td>
      			<td>Approved</td>
      			<td>None</td>
      			<td></td>
    		</tr>
    		<tr>
      			<td>Jamie</td>
      			<td>Approved</td>
      			<td>Requires call</td>
      			<td></td>
    		</tr>
    		<tr>
      			<td>Jill</td>
      			<td>Denied</td>
      			<td>None</td>
      			<td></td>
    		</tr>
  			</tbody>
		</table>
		<div class="mini ui button" id="divBtnNovoContato">
			<i class="add icon link" data-content="Hello, I am a pop-up."></i>
			Novo Contato
		</div>			
	</div>
	
	<!-- Informações -->
	<div class="ui tab segment" data-tab="Informacoes">
		<div class="ui form">
	  		<div class="four fields">
	  			<div class="field">
	  				<div class="ui checkbox">
	  					<s:checkbox name="clienteVO.pacLabora" id="pacLabora" theme="simple" fieldValue="true" value="true"/>
	  					<label>Labora</label>
	  				</div>
	  			</div>
	  			<div class="field">
	  				<div class="ui checkbox">
	  					<s:checkbox name="clienteVO.pacVisitaPedicuro" id="">Visita pedicuro</s:checkbox>
	  				</div>	  			
	  			</div>
	  			<div class="field">
					<div class="ui checkbox">
					  <input name="fun" type="checkbox">
					  <label>I enjoy having fun</label>
					</div>	  			
	  			</div>
	  			<div class="field">
		  			<s:checkbox name="clienteVO.pacHipertensao" id="">Hipertensão</s:checkbox>	  			
	  			</div>	  				  				  			
	  		</div>
	  		<div class="four fields">
	  			<div class="field">
	  				<div class="ui checkbox">
	  					<s:checkbox name="clienteVO.pacTabagismo" id="pacTabagismo" theme="simple" fieldValue="true" value="true"/>
	  					<label>Tabagismo</label>
	  				</div>
	  			</div>
	  			<div class="field">
	  				<div class="ui checkbox">
	  					<s:checkbox name="clienteVO.pacAndaDescalco" id="pacAndaDescalco" theme="simple" fieldValue="true" value="true"/>
	  					<label>Anda descalço</label>
	  				</div>	  			
	  			</div>
	  			<div class="field">
	  				<div class="ui checkbox">
	  					<s:checkbox name="clienteVO.pacUnhaEngravada" id="pacUnhaEngravada" theme="simple" fieldValue="true" value="true"/>
	  					<label>Unha encravada</label>
	  				</div>  			
	  			</div>	  				  				  			
	  		</div>	
	  		<div class="four fields">
	  			<div class="field">
	  				<div class="ui checkbox">
	  					<s:checkbox name="clienteVO.pacAlergicoMedicamentos" id="pacAlergicoMedicamentos" theme="simple" fieldValue="true" value="true"/>
	  					<label>Alergico a medicamentos</label>
	  				</div>
	  			</div>
	  			<div class="field">
		      		<label>Quais medicamentos</label>
		      		<s:textfield placeholder="Quais medicamentos" name="clienteVO.pacAlergicoQuais" id="pacAlergicoQuais" maxlength="255" theme="simple" cssStyle="width: 350px;"/>	  					  			
	  			</div>	  				  				  			
	  		</div>	
	  		<div class="four fields">
	  			<div class="field">
	  				<div class="ui checkbox">
	  					<s:checkbox name="clienteVO.pacCirurgiaPes" id="pacCirurgiaPes" theme="simple" fieldValue="true" value="true"/>
	  					<label>Cirurgia nos pés</label>
	  				</div>
	  			</div>
	  			<div class="field">
		      		<label>Motivo da cirurgia</label>
		      		<s:textfield placeholder="Motivo da cirurgia" name="clienteVO.pacCirurgiaMotivo" id="pacCirurgiaMotivo" maxlength="255" theme="simple" cssStyle="width: 350px;"/>	  					  			
	  			</div>	  				  				  			
	  		</div>
	  		<div class="four fields">
	  			<div class="field">
		      		<label>Calçado que utiliza</label>
		      		<s:textfield placeholder="Medicamentos" name="clienteVO.pacCalcadoUtiliza" id="pacCalcadoUtiliza" maxlength="100" theme="simple" cssStyle="width: 200px;"/>	  					  			
	  			</div>
	  			<div class="field">
		      		<label>Número do calçado</label>
		      		<s:textfield placeholder="Calçado" name="clienteVO.pacNumeroCalcado" id="pacNumeroCalcado" maxlength="2" theme="simple" cssStyle="width: 100px;"/>	  					  			
	  			</div>
	  			<div class="field">
		      		<label>Peso</label>
		      		<s:textfield placeholder="Peso" name="clienteVO.pacPeso" id="pacPeso" maxlength="5" theme="simple" cssStyle="width: 100px;"/>	  					  			
	  			</div>
	  			<div class="field">
		      		<label>Altura</label>
		      		<s:textfield placeholder="Altura" name="clienteVO.pacAltura" id="pacAltura" maxlength="4" theme="simple" cssStyle="width: 100px;"/>	  					  			
	  			</div>	  				  				  				  				  				  			
	  		</div>	  			  		  		  		
	  	</div>
	</div>
</s:form>

<!-- div do modal de contato -->
<div class="ui test modal">
	<i class="close icon"></i>
    <div class="header">
      Contato
    </div>
    <div class="content">
		<div class="ui form">
	  		<div class="two fields">
		    	<div class="field">
		      		<label>Tipo Contato</label>
		      		<s:textfield placeholder="Tipo Contato" name="clienteVO.contatoVO.tcoCodigo" id="tcoCodigo" maxlength="100" theme="simple" cssStyle="width: 350px;"/>
		    	</div>
		    	<div class="field">
		      		<label>Descrição</label>
		      		<s:textfield placeholder="Descrição" name="clienteVO.contatoVO.conDescricao" id="conDescricao" maxlength="60" theme="simple" cssStyle="width: 350px;"/>
		    	</div>
		  	</div>
	  		<div class="one fields">
		    	<div class="field">
		      		<label>Responsável</label>
		      		<s:textfield placeholder="Responsável" name="clienteVO.contatoVO.conResponsavel" id="conResponsavel" maxlength="80" theme="simple" cssStyle="width: 350px;"/>
		    	</div>
		  	</div>		  			  	
		</div>
    </div>
	<div class="actions">
    	<div class="mini ui black button">
        	Cancelar
      	</div>
      	<div class="mini ui positive right labeled icon button" id="divBtnInserirContato">
        	Inserir
     	</div>
	</div>
</div>