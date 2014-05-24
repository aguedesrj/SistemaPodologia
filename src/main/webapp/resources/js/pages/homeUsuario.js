$(document).ready(function() {
	
	// efetuar backup.
	$("#btnBackup").button().click(function() {
		$.ajax({
			url: '/SistemaPodologia/Usuario/Backup',
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
				if (status == "success") {
                	$("#divMensagemSucesso").css("display", "block");
                	$("#spanMsgSuccess").show().html(data.mensagemUsuario);
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
	});	
	
	$("#btnCadastrarCliente").button().click(function() {
		$("#formUsuario").attr("action", "/SistemaPodologia/Cliente/Novo");
		$("#formUsuario").submit();		
	});
	
	$("#btnPesquisarCliente").button().click(function() {
		$("#formUsuario").attr("action", "/SistemaPodologia/Cliente/Pesquisa");
		$("#formUsuario").submit();		
	});	
});
