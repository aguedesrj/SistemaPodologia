$(document).ready(function() {

	//$('.demo.menu .item').tab();
	$('.demo.menu .item').tab('change tab', 'DadosPessoais');

	$('#divBtnNovoContato').click(function() {
		$('.test.modal').modal('show');
	});
});
