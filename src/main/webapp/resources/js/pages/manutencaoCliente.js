$(document).ready(function() {
	
	$("#pesNome").focus();
	
	$('#datetimepicker1').datetimepicker({
		language: 'pt-BR'
	});	

	// Botão exibir modal de contatos..
    $("#btnNovoContato").button().click(function() {
    	exibeModalContato(true, null);
    });	
    
    // Inserir contato.
	$("#btnInserir").button().click(function() {
//		if (isCamposValoresValidos()) {
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
						atualizaTabelaContato(data.clienteVO.listaContatos);
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
//		}		
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

function atualizaTabelaContato(listaContatos) {
	$(".tbodyTabelaContatos").html("");
	for (var i = 0; listaContatos.length > i; i++) {
		atualizaIncluiTabelaContato(listaContatos[i]);
	}
}

function atualizaIncluiTabelaContato() {
	var html = "<tr><td>"+
		contatoVO.tipoContatoVO.tcoDescricao+
		"</td><td>"+contatoVO.conDescricao+
		"</td><td>"+contatoVO.conResponsavel+
		"</td><td>"+
		"<span title='Clique aqui para alterar este contato.' class='large glyphicon glyphicon-pencil' onclick='javascript:alterarContato("+contatoVO.conCodigo+");' style='cursor:pointer;'></span>"+
		"<span title='Clique aqui para excluir este contato.' class='large glyphicon glyphicon-remove-sign' onclick='javascript:removerContato("+contatoVO.conCodigo+");' style='cursor:pointer; margin-left: 20px;'></span>"+
		"</td></tr>";
	$(".tbodyTabelaContatos").append(html);	
}

/**
 * Set o nome do tipo de contato neste campo(hidden) no momento que alterar na combobox de lista de tipo de contato.
 */
function setNomeTipoContato() {
	$("#tcoDescricao").val($('#tcoCodigo :selected').text());
}

function exibeModalContato(isLimpaCampo, contatoVO) {
	$("#tcoCodigo").css("border", "1px solid #cccccc");
	$("#conDescricao").css("border", "1px solid #cccccc");
	if (isLimpaCampo) {
	    // Limpar campos.
		$("#tcoCodigo").val("-1");
		$("#tcoDescricao").val("");
		$("#conCodigo").val("");
		$("#conDescricao").val("");
	    $("#conResponsavel").val("");		
	} else {
		$("#tcoCodigo").val(contatoVO.tipoContatoVO.tcoCodigo);
		$("#tcoDescricao").val(contatoVO.tipoContatoVO.tcoDescricao);
		$("#conCodigo").val(contatoVO.conCodigo);
		$("#conDescricao").val(contatoVO.conDescricao);
	    $("#conResponsavel").val(contatoVO.conResponsavel);
	}
    // exibir modal.
	$("#modalContato").modal({
   		 "backdrop" : "static",
   		 "keyboard" : true,
   		 "show" : true
	});
}

function alterarContato(conCodigo) {
	$("#conCodigo").val(conCodigo);
	$.ajax({
		url: 'SistemaPodologia/Cliente/AlteraContato',
		data: $('#formModalContato').serialize(),
		type: 'POST',
		cache: false,
		dataType: "json",
		beforeSend: function(){
			$("#divCarregando").css("visibility", "visible");
			$("#divMensagemErro").css("display", "none");
		},
		success: function(data, status, request){
			$("#divCarregando").css("visibility", "hidden");
			if (status == "success" && data.mensagemUsuario == null) {
				// abrir modal.
				exibeModalContato(false, data.clienteVO.contatoVO);
			} else {
				$("#divMensagemErro").css("display", "block");
				$("#spanMsgError").show().html(data.mensagemUsuario);						
			}
		},
		error: function (request, error) {
			$("#divCarregando").css("visibility", "hidden");
			$("#divMensagemErro").css("display", "block");
			$("#spanMsgError").show().html("Sistema indisponível no momento.");
		}        
	});	
}

function removerContato(conCodigo) {
    BootstrapDialog.show({
        title: '::: Sistema Podologia :::',
        message: 'Deseja excluir o contato selecinado?',
        buttons: [{
            label: 'Sim',
            action: function(dialogItself) {
            	dialogItself.close();
    			$.ajax({
    				url: 'SistemaPodologia/Cliente/ExcluiContato',
    				data: $("#conCodigo").val(conCodigo),
    				type: 'POST',
    				cache: false,
    				dataType: "json",
    				beforeSend: function(){
    					$("#divCarregando").css("visibility", "visible");
    					$("#divMensagemErro").css("display", "none");
    				},
    				success: function(data, status, request){
    					$("#divCarregando").css("visibility", "hidden");
    					if (status == "success" && data.mensagemUsuario == null) {
    						atualizaTabelaContato(data.clienteVO.listaContatos);
    						$("#modalContato").modal('hide');
    						BootstrapDialog.show({
    					        title: '::: Sistema Podologia :::',
    					        message: 'Contato excluído.'
    					    });
    					} else {
    						$("#divMensagemErro").css("display", "block");
    						$("#spanMsgError").show().html(data.mensagemUsuario);						
    					}
    				},
    				error: function (request, error) {
    					$("#divCarregando").css("visibility", "hidden");
    					$("#divMensagemErro").css("display", "block");
    					$("#spanMsgError").show().html("Sistema indisponível no momento.");
    				}        
    			});
            }
        }, {
            label: 'Não',
            action: function(dialogItself) {
            	dialogItself.close();
            }
        }]
    });	
}

