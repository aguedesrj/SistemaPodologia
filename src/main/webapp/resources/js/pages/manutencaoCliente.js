$(document).ready(function() {
	
	$('#datetimepicker1').datetimepicker({
		language: 'pt-BR'
	});	

	// Botão exibir modal de contatos..
    $("#btnNovoContato").button().click(function() {
    	$("#tcoCodigo").css("border", "1px solid #cccccc");
    	$("#conDescricao").css("border", "1px solid #cccccc");
        // Limpar campos.
        $("#conDescricao").val("");
        $("#conResponsavel").val("");
        // exibir modal.
    	$("#modalContato").modal({
	   		 "backdrop" : "static",
	   		 "keyboard" : true,
	   		 "show" : true
    	});
    });	
    
    // Inserir contato.
	$("#btnInserir").button().click(function() {
		if (isCamposValoresValidos()) {
			// ir no servidor...
			$.ajax({
				url: 'SistemaPodologia/Cliente/IncluiContato',
				data: $('#formModalContato').serialize(),
				type: 'POST',
				cache: false,
				dataType: "json",
				beforeSend: function(){
					$("#loading").css("display", "block");
					$("#divMensagemErro").css("display", "none");
				},
				success: function(data, status, request){
					$("#loading").css("display", "none");
					if (status == "success" && data.mensagemUsuario == null) {
						$("#tabelaValores").addRowData(0, {
							vvpDataCadastro : data.valoresProdutoVO.vvpDataCadastro,
							vrpImpostoICMS  : data.valoresProdutoVO.vrpImpostoICMS,
							vrpImpostoIPI   : data.valoresProdutoVO.vrpImpostoIPI,
							vrpImpostoISS   : data.valoresProdutoVO.vrpImpostoISS,
							vvpValorProduto : data.valoresProdutoVO.vvpValorProduto
						});
						$("#modalContato").modal('hide'); 
					} else {
						$("#divMensagemErro").css("display", "block");
						$("#spanMsgError").show().html(data.mensagemUsuario);						
					}
				},
				error: function (request, error) {
					$("#loading").css("display", "none");
					$("#divMensagemErro").css("display", "block");
					$("#spanMsgError").show().html("Sistema indisponível no momento.");
				}        
			});                                
		}		
    });    
    
    // Fechar modal do contato.
	$("#btnCancelar").button().click(function() {	
		$("#modalContato").modal('hide');
	});	
	
    // Botão gravar Produto.
    $("#btnSalvar").button().click(function() {
    	// Validar campos.
        if (isCamposFormularioValidos()) {                
        	$.ajax({
        		url: 'SistemaComercialGuedes/Produto/Salva',
                data: $('#formProduto').serialize(),
                type: 'POST',
                cache: false,
                dataType: "json",
                beforeSend: function(){
					$("#loading").css("display", "block");
					$("#divMensagemErro").css("display", "none");
					$("#divMensagemSucesso").css("display", "none");
                },
                success: function(data, status, request){
                    if (status == "success" && data.mensagemUsuario == null) {
                    	limparCampos();
                    	$("#divMensagemSucesso").css("display", "block");
                    	$("#spanMsgSuccess").show().html("Produto cadastrado com sucesso!");
                        $("#proNome").focus();
                        $("#tabelaValores").jqGrid("clearGridData", true);
                    } else {
						$("#loading").css("display", "none");
						$("#divMensagemErro").css("display", "block");
						$("#spanMsgError").show().html(data.mensagemUsuario);                                               
                    }
                },
    			complete : function () {
    				$("#loading").css("display", "none");
    			},                
                error: function (request, error) {
					$("#loading").css("display", "none");
					$("#divMensagemErro").css("display", "block");
					$("#spanMsgError").show().html("Sistema indisponível no momento.");                                       
                }
        	});                        
        }
    });	
});

function montarTabelaContatos() {
	
}

/*
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
*/
