$(document).ready(function () {

    $('.form-add-user').on('submit', function (e) {
        const confirmer = $('#confirmer').val();
        const pwd = $('#password').val();
        if(confirmer != pwd || confirmer == '' || pwd == '') {
            e.preventDefault();
            alert("La confirmation de mot de passe n'est pas correcte");
            return false;
        }
    });
    var firstRole = $.trim($( "select#role option:selected" ).text());
    switch (firstRole) {
        case 'ROLE_ADMIN':
            $('.caissier').hide();
            $('.admin').show();
            $('.user-password').show();
            break;
        case 'ROLE_CAISSIER':
            $('.caissier').show();
            $('.user-password').show();
            $('.admin').hide();
            break;
        case 'ROLE_CLIENT':
            $('.caissier').hide();
            $('.admin').hide();
            $('.user-password').hide();
            break;
    }
    $('select#role').on('change', function() {
        var roleId = this.value;
        var roleText = $.trim($( "select#role option:selected" ).text());
        switch (roleText) {
            case 'ROLE_ADMIN':
                $('.caissier').hide();
                $('.admin').show();
                $('.user-password').show();
                break;
            case 'ROLE_CAISSIER':
                $('.caissier').show();
                $('.user-password').show();
                $('.admin').hide();
                break;
            case 'ROLE_CLIENT':
                $('.caissier').hide();
                $('.admin').hide();
                $('.user-password').hide();
                break;
        }
    });

    $('.btnRemove').click(function () {
        //
        const swalWithBootstrapButtons = Swal.mixin({
            customClass: {
                confirmButton: 'btn btn-success',
                cancelButton: 'btn btn-danger'
            },
            buttonsStyling: false
        })

        swalWithBootstrapButtons.fire({
            title: 'Êtes vous sûr de vouloir supprimer la ligne?',
            text: "Cette action est irreversible!",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonText: 'Supprimer',
            cancelButtonText: 'Annuler!',
            // reverseButtons: true
        }).then((result) => {
            if (result.value)
            {
                var id = $(this).attr('data')
                $.ajax({
                    url: '/admin/user/delete/' + id,
                    type: 'get',
                    success: function (data) {
                        if(data.status === "success") {
                            window.location.reload()
                        }

                    },
                    error: function (error) {
                        console.error(error);
                    }
                })
            }
            else if
            (
                result.dismiss === Swal.DismissReason.cancel
            )
            {
                // swalWithBootstrapButtons.fire(
                //     'Cancelled',
                //     'Your imaginary file is safe :)',
                //     'error'
                // )
            }
        })
        //
    });
});