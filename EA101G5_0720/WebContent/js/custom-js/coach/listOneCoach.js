function goBack() {
	window.history.back();
}

$(document).ready(function() {

	$('.pop').on('click', function() {
		$('.imagepreview').attr('src', $(this).find('img').attr('src'));
		$('#imagemodal').modal('show');
	});
	
});