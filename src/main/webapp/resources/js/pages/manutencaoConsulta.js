$(document).ready(function() {
	$(document).ready( function(){
		theMonths = ["Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"];
		theDays = ["Dom", "Seg", "Ter", "Qua", "Qui", "Sex", "Sab"];

		$('.calendar_test').calendar({
			months: theMonths,
			days: theDays
		});
		
	});
});

function dataRetorno(dia, mes, ano) {
	alert('Data: '+dia+'/'+mes+'/'+ano);
	return valor;
}	