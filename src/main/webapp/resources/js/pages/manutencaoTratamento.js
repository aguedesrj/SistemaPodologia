$(document).ready(function() {
	
	$("#traDescricao").focus();
	$('#traTempo').numeric();
	$("#traPreco").maskMoney();
	
    // Botão gravar Produto.
    $("#btnSalvar").button().click(function() {
    	// Validar campos.
        if (isCamposFormularioTratamento()) {                
        	$.ajax({
        		url: 'SistemaPodologia/Tratamento/Salva',
                data: $('#formTratamento').serialize(),
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
                    	preparaCamposNovoTratamento();
                    	$("#divMensagemSucesso").css("display", "block");
                    	$("#spanMsgSuccess").show().html("Tratamento cadastrado com sucesso!");
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
});

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

function isCamposFormularioTratamento() {
	var isValidos = false;
	var traDescricao = $("#traDescricao"),
		traTempo = $("#traTempo"),
		traPreco = $("#traPreco");
	
	traDescricao.css("border", "1px solid #cccccc");
	traTempo.css("border", "1px solid #cccccc");
	traPreco.css("border", "1px solid #cccccc");
	
	// Validar campo descrição.
	if (traDescricao.val().trim() == "") {
		traDescricao.css("border", "1px solid #ff4500");
		isValidos = true;
	}
	
	// Validar campo tempo.
	if (traTempo.val().trim() == "") {
		traTempo.css("border", "1px solid #ff4500");
		isValidos = true;
	}
	
	// Validar campo preço.
	if (traPreco.val().trim() == "") {
		traPreco.css("border", "1px solid #ff4500");
		isValidos = true;
	}
	
	if (isValidos) {
		return false;		
	}
	return true;	
}

function preparaCamposNovoTratamento() {
	$("#traDescricao").val("");
	$("#traTempo").val("");
	$('#traFlag').prop('checked', false);
	$("#traPreco").val("");
	$("#traObs").val("");
}

