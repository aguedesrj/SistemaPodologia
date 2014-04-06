$(document).ready(function() {
	$("#usuLogin").focus();
	
	$('.ui.form').form({
		usuLogin: {
			identifier  : 'usuLogin',
		    rules: [{
		    	type   : 'empty',
		        prompt : 'Informe o login.'
		    }]
		},
		usuSenha: {
			identifier  : 'usuSenha',
			rules: [{
				type   : 'empty',
			    prompt : 'Informe o login.'
			}]
		}		    
	});	
	
	$('#divBtnEfetuarLogin').click(function() {
		if (isCamposValidos()) {
			$.ajax({
				url: 'SistemaPodologia/Usuario/Login',
				data: $('#formLogin').serialize(),
				type: 'POST',
				cache: false,
				dataType: "json",
				beforeSend: function(){
					$("#divCarregando").css("visibility", "visible");
					$("#divMessageRed").css("visibility", "hidden");
				},
				success: function(data, status, request){ 
					if (status == "success" && data.mensagemUsuario == null) {
						$("#formLogin").attr("action", "Home");
						$("#formLogin").submit();
					} else {
						$("#divCarregando").css("visibility", "hidden");
						$("#divMessageRed").css("visibility", "visible");
						$("#spanMessage").show().html(data.mensagemUsuario);					
					}
				},
				error: function (request, error) {
					$("#divCarregando").css("visibility", "hidden");
					$("#divMessageRed").css("visibility", "visible");
					$("#spanMessage").show().html("Sistema indisponível no momento.");
				}
			});			
		}
	});
});

/*
 * Validar os campos.
 */
function isCamposValidos() {
	var usuLogin = $("#usuLogin"), 
		usuSenha = $("#usuSenha");	

	// Validar campo login.
	if (usuLogin.val().trim() == "") {
		return false;
	}
	
	// Validar campo senha.
	if (usuSenha.val().trim() == "") {
		return false;
	}
	
	return true;
}

