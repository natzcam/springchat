$(document).ready(function () {

    $('.account-form').submit(function (event) {
        var $form = $(this);
        var $submitBtn = $form.find('button[type=submit]');
        var $alert = $form.find('.alert');
        $submitBtn.button('loading');
        $alert.hide().html('');

        $.ajax({
            type: "POST",
            url: $form.attr("action"),
            data: $form.serialize(),
            success: function (response)
            {
                if (response.success) {
                    window.location = sc.contextPath;
                } else {
                    sc.formErrors($form, response.errors);
                }
                $submitBtn.button('reset');
            },
            error: function (xhr, status, error) {
                $alert.html(xhr.responseJSON.message).show();
                $submitBtn.button('reset');
            }
        });

        event.preventDefault();
    });
});
