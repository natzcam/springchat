var sc = {};

//populate form with constraint errors map
sc.formErrors = function (form, errors) {
    for (var id in errors) {
        form.find('[name=' + id + ']').closest('.form-group')
                .addClass('has-error').find('.help-block').html(errors[id]);
    }
    var genError = errors[form.attr('id')];
    if(genError){
        form.find('.alert').html(genError).show();
    }
};

//clear form
sc.clearForm = function (form) {
    $(':input', $form).not(':button, :submit, :reset, :hidden, .static-control').val('')
            .removeAttr('checked').removeAttr('selected');
    //clear validations
    form.find('.form-group').removeClass('has-error').find('.help-block').html('');
    form.find('.alert').hide().html('');
};

$(document).ready(function () {
    //when input comes to focus clear validation error
    $('.form-control').on('focus', function () {
        $(this).closest('.form-group').removeClass('has-error').find('.help-block').html('');
        $(this).closest('form').find('.alert').hide().html('');
    });
});


