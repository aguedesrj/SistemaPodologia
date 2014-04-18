$(document).ready(function() {
	$("#usuLogin").focus();
	
	$('#btnEntrar').click(function() {
		if (isCamposValidos()) {
			$.ajax({
				url: 'SistemaPodologia/Usuario/Login',
				data: $('#formLogin').serialize(),
				type: 'POST',
				cache: false,
				dataType: "json",
				beforeSend: function() {
					$("#divCarregando").css("visibility", "visible");
					$("#divMensagemErro").css("display", "none");
				},
				success: function(data, status, request){ 
					if (status == "success" && data.mensagemUsuario == null) {
						$("#formLogin").attr("action", "Home");
						$("#formLogin").submit();
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
});

/*
 * Validar os campos.
 */
function isCamposValidos() {
	var isValidos = false;
	var usuLogin = $("#usuLogin"), 
		usuSenha = $("#usuSenha");	

	usuLogin.css("border", "1px solid #cccccc");
	usuSenha.css("border", "1px solid #cccccc");
	
	// Validar campo login.
	if (usuLogin.val().trim() == "") {
		usuLogin.css("border", "1px solid #ff4500");
		isValidos = true;
	}
	
	// Validar campo senha.
	if (usuSenha.val().trim() == "") {
		usuSenha.css("border", "1px solid #ff4500");
		isValidos = true;
	}
	
	if (isValidos) {
		$("#divMensagemErro").css("display", "block");
		$("#spanMsgError").show().html("O(s) campo(s) em vermelho(s) é obrigatório.");			
		return false;		
	}
	
	return true;
}

