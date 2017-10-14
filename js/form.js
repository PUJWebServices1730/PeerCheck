let initFormById = function(formContainer, mainForm, animation) {
	var form = '#' + formContainer;
	var mainForm = form + ' #' + mainForm;
	$(mainForm).show();
	if(animation) {
		$(form + ' .fm-container_body').delay(500).slideDown(1000);
	} else {
		$(form + ' .fm-container_body').show();
	}
	$(form + ' .fm-form_link').on('click', function() {
		$(mainForm)[0].reset();
		$(mainForm).slideUp();
		mainForm = $(this).attr('target');
		$(mainForm).slideDown();
	});
}
