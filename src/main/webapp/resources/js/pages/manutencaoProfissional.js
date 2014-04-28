$(document).ready(function() {
	
	$("#pesNome").focus();
	
	// Botão exibir modal de contatos..
    $("#btnNovoContato").button().click(function() {
    	exibeModalContato(true, null);
    });	
    
    // Inserir contato.
	$("#btnInserir").button().click(function() {
		if (isCamposFormularioContato()) {
			// ir no servidor...
			$.ajax({
				url: 'SistemaPodologia/Profissional/IncluiContato',
				data: $('#formModalContato').serialize(),
				type: 'POST',
				cache: false,
				dataType: "json",
				beforeSend: function(){
					$("#divCarregando").css("visibility", "visible");
					$("#divMensagemErro").css("display", "none");
					$("#divMensagemSucesso").css("display", "none");
				},
				success: function(data, status, request){
					$("#divCarregando").css("visibility", "hidden");
					if (status == "success" && data.mensagemUsuario == null) {
						atualizaTabelaContato(data.profissionalVO.listaContatos);
						$("#modalContato").modal('hide');
					} else {
						$("#divCarregando").css("visibility", "hidden");
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
    });    
    
    // Fechar modal do contato.
	$("#btnCancelar").button().click(function() {	
		$("#modalContato").modal('hide');
	});	
	
    // Botão gravar.
    $("#btnSalvar").button().click(function() {
    	// Validar campos.
        if (isCamposFormularioProfissional()) {                
        	$.ajax({
        		url: 'SistemaPodologia/Profissional/Salva',
                data: $('#formProfissional').serialize(),
                type: 'POST',
                cache: false,
                dataType: "json",
                beforeSend: function(){
    				$("#divCarregando").css("visibility", "visible");
    				$("#divMensagemErro").css("display", "none");
    				$("#divMensagemSucesso").css("display", "none");
                },
                success: function(data, status, request){
                    if (status == "success" && data.mensagemUsuario == null) {
                    	preparaCamposNovoProfissional();
                    	$("#divMensagemSucesso").css("display", "block");
                    	$("#spanMsgSuccess").show().html("Profissional cadastrado com sucesso!");
                        $("#pesNome").focus();
                        // limpa tabela de contatos.
                        $(".tbodyTabelaContatos").html("");
                    } else {
    					$("#divCarregando").css("visibility", "hidden");
    					$("#divMensagemErro").css("display", "block");
    					$("#spanMsgError").show().html(data.mensagemUsuario); 	                                              
                    }
                },
    			complete : function () {
    				$("#divCarregando").css("visibility", "hidden");
    			},                
                error: function (request, error) {
    				$("#divCarregando").css("visibility", "hidden");
    				$("#divMensagemErro").css("display", "block");
    				$("#spanMsgError").show().html("Sistema indisponível no momento.");                                       
                }
        	});                        
        }
    });
    
	// verifica se está em alteração.
	if ($("#pesCodigo").val() != null && $("#pesCodigo").val() > 0) {
	    $.ajax({
	    	url: 'SistemaPodologia/Profissional/BuscaContatos',
	        type: 'POST',
	        cache: false,
	        dataType: "json",
	        beforeSend: function(){
				$("#divCarregando").css("visibility", "visible");
				$("#divMensagemErro").css("display", "none");
				$("#divMensagemSucesso").css("display", "none");
	        },
	        success: function(data, status, request){
	        	$("#divCarregando").css("visibility", "hidden");
	            if (status == "success" && data.mensagemUsuario == undefined) {
	            	atualizaTabelaContato(data.profissionalVO.listaContatos);
	            }
	        },
	        error: function (request, error) {
				$("#divCarregando").css("visibility", "hidden");
				$("#divMensagemErro").css("display", "block");
				$("#spanMsgError").show().html("Sistema indisponível no momento."); 	                                      
	        }
	    });		
	}    
});

function atualizaTabelaContato(listaContatos) {
	$(".tbodyTabelaContatos").html("");
	for (var i = 0; listaContatos.length > i; i++) {
		atualizaIncluiTabelaContato(listaContatos[i]);
	}
}

function atualizaIncluiTabelaContato(contatoVO) {
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
		url: 'SistemaPodologia/Profissional/AlteraContato',
		data: $('#formModalContato').serialize(),
		type: 'POST',
		cache: false,
		dataType: "json",
		beforeSend: function(){
			$("#divCarregando").css("visibility", "visible");
			$("#divMensagemErro").css("display", "none");
			$("#divMensagemSucesso").css("display", "none");
		},
		success: function(data, status, request){
			$("#divCarregando").css("visibility", "hidden");
			if (status == "success" && data.mensagemUsuario == null) {
				// abrir modal.
				exibeModalContato(false, data.profissionalVO.contatoVO);
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
    				url: 'SistemaPodologia/Profissional/ExcluiContato',
    				data: $("#conCodigo").val(conCodigo),
    				type: 'POST',
    				cache: false,
    				dataType: "json",
    				beforeSend: function(){
    					$("#divCarregando").css("visibility", "visible");
    					$("#divMensagemErro").css("display", "none");
    					$("#divMensagemSucesso").css("display", "none");
    				},
    				success: function(data, status, request){
    					$("#divCarregando").css("visibility", "hidden");
    					if (status == "success" && data.mensagemUsuario == null) {
    						atualizaTabelaContato(data.profissionalVO.listaContatos);
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

function isCamposFormularioContato() {
	var isValidos = false;
	
	var tcoCodigo    = $("#tcoCodigo"), 
	    conDescricao = $("#conDescricao");	

	tcoCodigo.css("border", "1px solid #cccccc");
	conDescricao.css("border", "1px solid #cccccc");
	
	// Validar campo tipo de contato.
	if (tcoCodigo.val().trim() == "-1") {
		tcoCodigo.css("border", "1px solid #ff4500");
		isValidos = true;
	}
	
	// Validar campo descrição do contato.
	if (conDescricao.val().trim() == "") {
		conDescricao.css("border", "1px solid #ff4500");
		isValidos = true;
	}
	
	if (isValidos) {
		return false;		
	}
	
	return true;	
}

function isCamposFormularioProfissional() {
	var pesNome = $("#pesNome");
	
	pesNome.css("border", "1px solid #cccccc");
	
	// Validar campo nome do Profissional.
	if (pesNome.val().trim() == "") {
		pesNome.css("border", "1px solid #ff4500");

		$("#divMensagemErro").css("display", "block");
		$("#spanMsgError").show().html("O(s) campo(s) em vermelho(s) é obrigatório.");	
		return false;
	}
	
	return true;	
}

function preparaCamposNovoProfissional() {
	// dados pessoais
	$("#pesNome").val("");
	$("#pesDtNascimento").val("");
	$('#pesSexo').prop('checked', false);
	$("#pesCpf").val("");
	$("#pesObs").val("");
	$("#cliDataUltimaConsulta").val("Primeira visita.");
	// endereço
	$("#endLogadouro").val("");
	$("#endNumero").val("");
	$("#endBairro").val("");
	$("#endCidade").val("");
	$("#estCodigo").val("-1");
	$("#endCep").val("");
}

