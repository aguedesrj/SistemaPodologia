$(document).ready(function() {
	
	$("#traDescricao").focus();

    // Fechar modal do detalhe.
	$("#btnFechar").button().click(function() {	
		$("#modalDetalhe").modal('hide');
	});	
	
    // Botão novo.
    $("#btnNovo").button().click(function() {
    	$("#divCarregando").css("visibility", "visible");
		$("#formTratamento").attr("action", "Novo");
		$("#formTratamento").submit();    	
    });
    
	// Botão pesquisa Produto.
	$("#btnPesquisar").button().click(function() {
		$.ajax({
			url: 'SistemaPodologia/Tratamento/ExecutaPesquisa',
			data: $('#formTratamento').serialize(),
			type: 'POST',
			cache: false,
			dataType: "json",
			beforeSend: function(){
				$("#divCarregando").css("visibility", "visible");
				$("#divMensagemSucesso").css("display", "none");
				$("#divMensagemErro").css("display", "none");
			},
			success: function(data, status, request){ 
				$("#divCarregando").css("visibility", "hidden");
				if (status == "success" && data.mensagemUsuario == null) {
					// atualiza lista na tabela.
					atualizaTabelaTratamento(data.listaTratamentoVO);
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
	});	    
});

function onChange() {
	if ($("#traDescricao").val().length >= 3) {
		$.ajax({
			url: 'SistemaPodologia/Tratamento/ExecutaPesquisa',
			data: $('#formTratamento').serialize(),
			type: 'POST',
			cache: false,
			dataType: "json",
			beforeSend: function(){
				$("#divCarregando").css("visibility", "visible");
				$("#divMensagemSucesso").css("display", "none");
				$("#divMensagemErro").css("display", "none");
			},
			success: function(data, status, request){ 
				$("#divCarregando").css("visibility", "hidden");
				if (status == "success" && data.mensagemUsuario == null) {
					// atualiza lista na tabela.
					atualizaTabelaTratamento(data.listaTratamentoVO);
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
	} else {
		$(".tbodyTabelaTratamento").html("");
	}
}

function atualizaTabelaTratamento(listaTratamentoVO) {
	$(".tbodyTabelaTratamento").html("");
	for (var i = 0; listaTratamentoVO.length > i; i++) {
		atualizaIncluiTabelaTratamento(listaTratamentoVO[i]);
	}
}

function atualizaIncluiTabelaTratamento(tratamentoVO) {
	var html = 
		"<tr><td>" +tratamentoVO.traDescricao+
		"</td><td>"+
		"<span title='Clique aqui para detalhar o Tratamento.' class='large glyphicon glyphicon-zoom-in' onclick='javascript:detalhar("+tratamentoVO.traCodigo+");' style='cursor:pointer;'></span>"+
		"<span title='Clique aqui para alterar o Tratamento.' class='large glyphicon glyphicon-cog' onclick='javascript:alterar("+tratamentoVO.traCodigo+");' style='cursor:pointer; margin-left: 20px;'></span>"+
		"</td></tr>";
	$(".tbodyTabelaTratamento").append(html);	
}

function detalhar(traCodigo) {
	$("#traCodigo").val(traCodigo);
	$.ajax({
		url: 'SistemaPodologia/Tratamento/Detalha',
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
			$("#divCarregando").css("visibility", "hidden");
			if (status == "success" && data.mensagemUsuario == null) {
				exibirModalDetalhe(data.tratamentoVO);
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

function exibirModalDetalhe(tratamentoVO) {
	// limpar campos.
	$("#spanTraDescricao").html("");
	$("#spanTraTempo").html("");
	$("#spanTraPreco").html("");
	$('#traFlag').prop('checked', false);
	$("#spanTraObs").html("");	
	// dados pessoais
	$("#spanTraDescricao").html(tratamentoVO.traDescricao);
	$("#spanTraTempo").html(tratamentoVO.traTempo);
	$("#spanTraPreco").html(tratamentoVO.traPreco);
	if (tratamentoVO.traFlag) {
		$('#traFlag').prop('checked', true);
	}
	$("#spanTraObs").html(tratamentoVO.traObs);
	// exibir modal.
	$("#modalDetalhe").modal({ 
   		 "backdrop" : "static",
   		 "keyboard" : true,
   		 "show" : true
	});		
}

function alterar(traCodigo) {
	$("#divCarregando").css("visibility", "visible");
	$("#traCodigo").val(traCodigo);
	$("#formTratamento").attr("action", "InicioAlteracao");
	$("#formTratamento").submit();	
}
