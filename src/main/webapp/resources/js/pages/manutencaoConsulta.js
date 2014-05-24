$(document).ready(function() {
	$(document).ready( function(){
		theMonths = ["Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"];
		theDays = ["Dom", "Seg", "Ter", "Qua", "Qui", "Sex", "Sab"];

		$('.calendar_test').calendar({
			months: theMonths,
			days: theDays
		});
		
	});
	
	// montar tabela consulta
    $.ajax({
    	url: 'SistemaPodologia/Consulta/ExibeTabelaConsulta',
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
            	atualizaTabelaConsulta(data.listaHoraMarcarConsultaVO);
            }
        },
        error: function (request, error) {
			$("#divCarregando").css("visibility", "hidden");
			$("#divMensagemErro").css("display", "block");
			$("#spanMsgError").show().html("Sistema indisponível no momento."); 	                                      
        }
    });	
});

function atualizaTabelaConsulta(listaHoraMarcarConsultaVO) {
	$(".tbodyTabelaConsulta").html("");
	for (var i = 0; listaHoraMarcarConsultaVO.length > i; i++) {
		inserirLinhaTabelaConsulta(listaHoraMarcarConsultaVO[i]);
	}
}

function inserirLinhaTabelaConsulta(horaMarcarConsultaVO) {
	var html = 
		"<tr>"+
			"<td>"+horaMarcarConsultaVO.hmlDescricao+"</td>"+
			"<td></td>"+
			"<td></td>"+
			"<td></td>"+
		"</tr>";
	$(".tbodyTabelaConsulta").append(html);	
}

function dataRetorno(dia, mes, ano) {
	$("#spanDataMarcacaoConsulta").html('Data: '+dia+'/'+mes+'/'+ano);
}	