$(document).ready(function() {
	
	$("#pesNome").focus();

    // Fechar modal do detalhe.
	$("#btnFechar").button().click(function() {	
		$("#modalDetalhe").modal('hide');
	});	
	
    // Botão novo produto.
    $("#btnNovo").button().click(function() {
		$("#formCliente").attr("action", "Novo");
		$("#formCliente").submit();    	
    });
    
	// Botão pesquisa Produto.
	$("#btnPesquisar").button().click(function() {
		$.ajax({
			url: 'SistemaPodologia/Cliente/ExecutaPesquisa',
			data: $('#formCliente').serialize(),
			type: 'POST',
			cache: false,
			dataType: "json",
			beforeSend: function(){
//				$("#loading").css("display", "block");
//				$("#divMensagemErro").css("display", "none");
//				$("#divMensagemSucesso").css("display", "none");
			},
			success: function(data, status, request){ 
//				$("#loading").css("display", "none");
				if (status == "success" && data.mensagemUsuario == null) {
					// atualiza lista na tabela.
					atualizaTabelaCliente(data.listaPessoaVO);
				} else {
//					$("#divMensagemErro").css("display", "block");
//					$("#spanMsgError").show().html(data.mensagemUsuario);  					
				}
			},
			error: function (request, error) {
//				$("#loading").css("display", "none");
//				$("#divMensagemErro").css("display", "block");
//				$("#spanMsgError").show().html("Sistema indisponível no momento.");  
			}
		});			
	});	    
});

function onChange() {
	if ($("#pesNome").val().length >= 3) {
		$.ajax({
			url: 'SistemaPodologia/Cliente/ExecutaPesquisa',
			data: $('#formCliente').serialize(),
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
					// atualiza lista na tabela.
					atualizaTabelaCliente(data.listaPessoaVO);
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
		$(".tbodyTabelaCliente").html("");
	}
}

function atualizaTabelaCliente(listaPessoaVO) {
	$(".tbodyTabelaCliente").html("");
	for (var i = 0; listaPessoaVO.length > i; i++) {
		atualizaIncluiTabelaCliente(listaPessoaVO[i]);
	}
}

function atualizaIncluiTabelaCliente(pessoaVO) {
	var html = 
		"<tr><td>" +pessoaVO.pesNome+
		"</td><td>"+
		"<span title='Clique aqui para detalhar o Cliente.' class='large glyphicon glyphicon-pencil' onclick='javascript:detalhar("+pessoaVO.pesCodigo+");' style='cursor:pointer;'></span>"+
		"<span title='Clique aqui para alterar o Cliente.' class='large glyphicon glyphicon-remove-sign' onclick='javascript:alterar("+pessoaVO.pesCodigo+");' style='cursor:pointer; margin-left: 20px;'></span>"+
		"</td></tr>";
	$(".tbodyTabelaCliente").append(html);	
}

function detalhar(pesCodigo) {
	$("#pesCodigo").val(pesCodigo);
	$.ajax({
		url: 'SistemaPodologia/Cliente/Detalha',
		data: $('#formCliente').serialize(),
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
				exibirModalDetalhe(data.clienteVO);
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

function exibirModalDetalhe(clienteVO) {
	$("#spanPerNome").html(clienteVO.pessoaVO.pesNome);
	$("#spanPesDtNascimento").html(clienteVO.pessoaVO.pesDtNascimento);
	$("#spanPesSexo").html(clienteVO.pessoaVO.pesSexo);
	$("#spanPesCPF").html(clienteVO.pessoaVO.pesCpf);
	$("#spanCliDataUltimaConsulta").html(clienteVO.cliDataUltimaConsulta);
	
//	if (data.produto.fornecedor != undefined) {
//		$("#spanForNome").html(data.produto.fornecedor.genDescricao);
//	}	
//	if (data.produto.categoria != undefined) {
//		$("#spanCatDescricao").html(data.produto.categoria.genDescricao);
//	}
//	$("#spanProQuantidadeMinima").html(data.produto.proQuantidadeMinima);
//	$("#spanProQuantidadeMaxima").html(data.produto.proQuantidadeMaxima);
//	$("#spanProObs").html(data.produto.proObs);
//
//	
//	// exibir os valores.
//	$("#spanValoresProduto").html("");
//	for (var i = 0; data.produto.listaValoresProduto.length > i; i++) {
//		$("#spanValoresProduto").append(data.produto.listaValoresProduto[i].vvpValorProduto+"<br>");
//	}
	
    // exibir modal.
	$("#modalDetalhe").modal({ // wire up the actual modal functionality and show the dialog
   		 "backdrop" : "static",
   		 "keyboard" : true,
   		 "show" : true // ensure the modal is shown immediately
	});		
}

function alterar(pesCodigo) {
	
}
