$(document).ready(function() {
	
	// Fechar modal do detalhe.
	$("#btnFechar").button().click(function() {	
		$("#modalDetalhe").modal('hide');
	});	
	
    // Botão novo produto.
    $("#btnNovo").button().click(function() {
    	$("#divCarregando").css("visibility", "visible");
		$("#formProfissional").attr("action", "Novo");
		$("#formProfissional").submit();    	
    });
    
    // executar pesquisa.
	$.ajax({
		url: 'SistemaPodologia/Profissional/ExecutaPesquisa',
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
				atualizaTabelaProfissional(data.listaProfissionalVO);
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

function atualizaTabelaProfissional(listaProfissionalVO) {
	$(".tbodyTabelaProfissional").html("");
	for (var i = 0; listaProfissionalVO.length > i; i++) {
		atualizaIncluiTabelaProfissional(listaProfissionalVO[i]);
	}
}

function atualizaIncluiTabelaProfissional(profissionalVO) {
	var html = 
		"<tr><td>" +profissionalVO.pessoaVO.pesNome+
		"</td><td>"+
		"<span title='Clique aqui para detalhar o Profissional.' class='large glyphicon glyphicon-zoom-in' onclick='javascript:detalhar("+profissionalVO.prfCodigo+");' style='cursor:pointer;'></span>"+
		"<span title='Clique aqui para alterar o Profissional.' class='large glyphicon glyphicon-cog' onclick='javascript:alterar("+profissionalVO.prfCodigo+");' style='cursor:pointer; margin-left: 20px;'></span>"+
		"</td></tr>";
	$(".tbodyTabelaProfissional").append(html);	
}

function detalhar(prfCodigo) {
	$.ajax({
		url: 'SistemaPodologia/Profissional/Detalha?profissionalVO.prfCodigo='+prfCodigo,
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
				exibirModalDetalhe(data.profissionalVO);
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

function exibirModalDetalhe(profissionalVO) {
	// dados pessoais
	$("#spanPrfDtFormacao").html(profissionalVO.prfDtFormacao);
	$("#spanPrfDescricaoFormacao").html(profissionalVO.prfDescricaoFormacao);
	$("#spanPerNome").html(profissionalVO.pessoaVO.pesNome);
	$("#spanPesDtNascimento").html(profissionalVO.pessoaVO.pesDtNascimento);
	$("#spanPesSexo").html(profissionalVO.pessoaVO.pesSexoFormat);
	$("#spanPesCPF").html(profissionalVO.pessoaVO.pesCpf);
	$("#spanPesObs").html(profissionalVO.pessoaVO.pesObs);
	// endereço
	$("#spanEndLogadouro").html(profissionalVO.enderecoVO.endLogadouro);
	$("#spanEndNumero").html(profissionalVO.enderecoVO.endNumero);
	$("#spanEndBairro").html(profissionalVO.enderecoVO.endBairro);
	$("#spanEndCidade").html(profissionalVO.enderecoVO.endCidade);
	$("#spanEstNome").html(profissionalVO.enderecoVO.estadoVO.estNome);
	$("#spanEndCep").html(profissionalVO.enderecoVO.endCep);
	// contatos
	atualizaTabelaContato(profissionalVO.listaContatos);
	// exibir modal.
	$("#modalDetalhe").modal({ 
   		 "backdrop" : "static",
   		 "keyboard" : true,
   		 "show" : true
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

function alterar(prfCodigo) {
	$("#divCarregando").css("visibility", "visible");
	$("#prfCodigo").val(prfCodigo);
	$("#formProfissional").attr("action", "InicioAlteracao");
	$("#formProfissional").submit();	
}
