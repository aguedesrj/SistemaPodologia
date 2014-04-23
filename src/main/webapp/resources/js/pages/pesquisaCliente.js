$(document).ready(function() {
	
	$("#pesNome").focus();

    // Fechar modal do detalhe.
	$("#btnFechar").button().click(function() {	
		$("#modalDetalhe").modal('hide');
	});	
	
    // Botão novo produto.
    $("#btnNovo").button().click(function() {
    	$("#divCarregando").css("visibility", "visible");
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
				$("#divCarregando").css("visibility", "visible");
				$("#divMensagemSucesso").css("display", "none");
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
				$("#divMensagemSucesso").css("display", "none");
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
		"<span title='Clique aqui para detalhar o Cliente.' class='large glyphicon glyphicon-zoom-in' onclick='javascript:detalhar("+pessoaVO.pesCodigo+");' style='cursor:pointer;'></span>"+
		"<span title='Clique aqui para alterar o Cliente.' class='large glyphicon glyphicon-cog' onclick='javascript:alterar("+pessoaVO.pesCodigo+");' style='cursor:pointer; margin-left: 20px;'></span>"+
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
			$("#divMensagemSucesso").css("display", "none");
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
	// dados pessoais
	$("#spanPerNome").html(clienteVO.pessoaVO.pesNome);
	$("#spanPesDtNascimento").html(clienteVO.pessoaVO.pesDtNascimento);
	$("#spanPesSexo").html(clienteVO.pessoaVO.pesSexoFormat);
	$("#spanPesCPF").html(clienteVO.pessoaVO.pesCpf);
	if (clienteVO.cliUltimoTratamento != null) {
		$("#spanCliDataUltimaConsulta").html(clienteVO.cliDataUltimaConsulta + " (Tratamento: " + clienteVO.cliUltimoTratamento + ")");
	} else {
		$("#spanCliDataUltimaConsulta").html(clienteVO.cliDataUltimaConsulta);
	}
	$("#spanPesObs").html(clienteVO.pessoaVO.pesObs);
	// endereço
	$("#spanEndLogadouro").html(clienteVO.enderecoVO.endLogadouro);
	$("#spanEndNumero").html(clienteVO.enderecoVO.endNumero);
	$("#spanEndBairro").html(clienteVO.enderecoVO.endBairro);
	$("#spanEndCidade").html(clienteVO.enderecoVO.endCidade);
	$("#spanEstNome").html(clienteVO.enderecoVO.estadoVO.estNome);
	$("#spanEndCep").html(clienteVO.enderecoVO.endCep);
	// contatos
	atualizaTabelaContato(clienteVO.listaContatos);
	// paciente
	$("#spanPacAlergicoQuais").html(clienteVO.pacAlergicoQuais);
	$("#spanPacCalcadoUtiliza").html(clienteVO.pacCalcadoUtiliza);
	$("#spanPacNumeroCalcado").html(clienteVO.pacNumeroCalcado);
	$("#spanPacPeso").html(clienteVO.pacPeso);
	$("#spanPacAltura").html(clienteVO.pacAltura);
	// exibir modal.
	$("#modalDetalhe").modal({ // wire up the actual modal functionality and show the dialog
   		 "backdrop" : "static",
   		 "keyboard" : true,
   		 "show" : true // ensure the modal is shown immediately
	});		
}

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
		"</td><tr>";
	$(".tbodyTabelaContatos").append(html);	
}

function alterar(pesCodigo) {
	$("#divCarregando").css("visibility", "visible");
	$("#pesCodigo").val(pesCodigo);
	$("#formCliente").attr("action", "InicioAlteracao");
	$("#formCliente").submit();	
}
