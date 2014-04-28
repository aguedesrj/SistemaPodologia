<%@ taglib prefix="s" uri="/struts-tags" %>

<script src="../resources/js/pages/manutencaoTratamento.js"></script>

<div class="panel panel-default">
	<div class="panel-heading">
    	<h3 class="panel-title" style="font-weight: bold;">Cadastro de Tratamento</h3>
    </div>
	<s:form namespace="Tratamento" id="formTratamento" name="formTratamento" theme="simple" cssStyle="margin-left: 10px;">
       	<s:hidden name="tratamentoVO.traCodigo" id="traCodigo"></s:hidden>
       	<div class="panel-body">
			<div class="row">
				<div class="col-lg-6">
					<s:label for="traDescricao" cssClass="control-label">Nome</s:label>
					<s:textfield name="tratamentoVO.traDescricao" id="traDescricao" maxlength="80" theme="simple" required="true" cssClass="form-control" cssStyle="width: 500px;"/>								
				</div>
			</div>
			<div class="row" style="margin-top: 10px;">
				<div class="col-lg-6">
					<s:label for="traTempo" cssClass="control-label">Tempo estimado de duração(em minutos)</s:label>
					<s:textfield name="tratamentoVO.traTempo" id="traTempo" maxlength="10" theme="simple" required="true" cssClass="form-control" cssStyle="width: 100px;"/>							
				</div>
				<div class="col-lg-6">
					<s:label for="traPreco" cssClass="control-label">Preço do tratamento</s:label>
					<div class="input-group">
						<span class="input-group-addon">R$</span>
					  	<s:textfield name="tratamentoVO.traPreco" id="traPreco" maxlength="10" theme="simple" required="true" cssStyle="width: 100px;" cssClass="form-control"></s:textfield>
					</div>				
				</div>								
			</div>	
			<div class="row" style="margin-top: 10px;">
				<div class="col-lg-6">
					<div class="input-group">
				    	<span class="input-group-addon">
				        	<s:checkbox name="tratamentoVO.traFlag" id="traFlag" theme="simple"/>
				      	</span>
				      	<label class="form-control">Marque caso o tratamento possa ser feito 2 vezes no mesmo horário.</label>
				    </div>				
				</div>								
			</div>
			<div class="row" style="margin-top: 10px;">
				<div class="col-lg-6">
					<s:label for="traObs" cssClass="control-label">Observação</s:label>
					<s:textarea name="tratamentoVO.traObs" id="traObs" cols="90" theme="simple" cssClass="form-control"></s:textarea>							
				</div>								
			</div>
		</div>
	</s:form>
	<div class="panel-body" style="margin-top: 10px; margin-left: 10px;">
    	<button id="btnSalvar" type="button" class="btn btn-primary">Salvar Tratamento</button>
	</div>	
</div>
