<%@ taglib prefix="s"  uri="/struts-tags" %>

<script src="../resources/js/pages/usuario/manutencaoUsuario.js"></script>

<div class="container">
	<div class="panel panel-default">
    	<div class="panel-heading">
        	<h3 class="panel-title">Manuten��o Usu�rio</h3>
        </div>
        <s:form namespace="Usuario" id="formUsuario" name="formUsuario" theme="simple" cssStyle="margin-left: 15px;">
        	<span style="margin-top: 15px; font-size: 12px; font-style: italic;">* campos obrigat�rios</span>
			<s:hidden id="usuCodigo" name="usuario.usuCodigo"></s:hidden>
			<s:hidden id="pesCodigo" name="usuario.pesCodigo"></s:hidden>
			<s:hidden id="itensSelecionados" name="itensSelecionados"></s:hidden> 
			<div class="row">
				<div class="col-lg-6">
					<s:label for="proNome" cssClass="control-label">Nome do Usu�rio *</s:label>
					<s:textfield name="usuario.pesNome" id="pesNome" maxlength="120" theme="simple" required="true" cssClass="form-control" cssStyle="width: 400px;"/>
				</div>
				<div class="col-lg-6">
					<s:label for="proNome" cssClass="control-label">Login *</s:label>
					<s:textfield name="usuario.usuLogin" id="usuLogin" maxlength="50" theme="simple" required="true" cssClass="form-control" cssStyle="width: 250px;"/>
				</div>
			</div>
			<div class="row" style="margin-top: 15px;">
				<div class="col-lg-6">
					<s:label for="proNome" cssClass="control-label">Senha *</s:label>
					<s:password name="usuario.usuSenha" id="usuSenha" maxlength="20" theme="simple" required="true" cssClass="form-control" cssStyle="width: 150px;"/>
				</div>
				<div class="col-lg-6">
					<s:label for="proNome" cssClass="control-label">Confirma senha *</s:label>
					<s:password name="usuario.usuConfirmaSenha" id="usuConfirmaSenha" maxlength="20" theme="simple" required="true" cssClass="form-control" cssStyle="width: 150px;"/>
				</div>
			</div>			
			<div class="row" style="margin-top: 15px;">
				<div class="col-lg-5">
					<s:label for="proNome" cssClass="control-label">Perfis</s:label><br>
					<a href='#' id='select-all'>Selecionar todos</a>
					<a href='#' id='deselect-all'>Desmarcar todos</a>
					<select id='selectPerfis' multiple='multiple' name="selectPerfis[]">
					</select>							
				</div>						
			</div>        
		</s:form>
		<div class="panel-body">
	    	<button id="btnSalvar" type="button" class="btn btn-primary">Salvar</button>
		</div>		
	</div>
</div>
