$(document).ready(function () {

    $('.form-add-user').on('submit', function (e) {
        const confirmer = $('#confirmer').val();
        const pwd = $('#password').val();
        if(confirmer != pwd || confirmer == '' || pwd == '') {
            e.preventDefault();
            alert("La confirmation de mot de passe n'est pas correcte");
            return false;
        }
        const role = $('#role').find(":selected").text();
        const numpiece = $('#numpiece').val();
        const prenom = $('#prenom').val();
        const nom = $('#nom').val();
        const telephone = $('#telephone').val();
        if(telephone.length != 9) {
            alert("Le numero de telephone doit etre 9 caracteres.");
            return false;
        }
        switch (role) {
            case 'ROLE_SUPER':
                var login = $('#login').val();
                if(numpiece === '' || prenom === '' || nom === '' || telephone === '' || login === '' ) {
                    alert("Veuillez renseigner tous les champs!");
                    return false;
                }
                break;
            case 'ROLE_ADMIN':
                var login = $('#login').val();
                if(numpiece === '' || prenom === '' || nom === '' || telephone === '' || login === '' ) {
                    alert("Veuillez renseigner tous les champs!");
                    return false;
                }
                break;
            case 'ROLE_CAISSIER':
                var avatar = $('#avatar').val();
                var iv = $('#iv').val();
                if(numpiece === '' || prenom === '' || nom === '' || telephone === '' || avatar === '' || iv === '' ) {
                    alert("Veuillez renseigner tous les champs!");
                    return false;
                }
                if(iv < 100000) {
                    alert("La saomme de l'IV n'est pas valide");
                    return false;
                }
                break;
            case 'ROLE_CLIENT':
                if(numpiece === '' || prenom === '' || nom === '' || telephone === '') {
                    alert("Veuillez renseigner tous les champs!");
                    return false;
                }
                break;
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


    $('.edit-user').click(function () {
        var userId = $(this).attr('user-id')
        $.ajax({
            url: '/admin/user/edit/' + userId,
            type: 'get',
            dataType: 'json',
            success: function (data) {
                console.log(data.id);
                // $('#id').val(data.id)
                // $('#matricule').val(data.matricule)
                // $('#nomComplet').val(data.nomComplet)
                // $('#poste').val(data.poste)
                // $('#salaire').val(data.salaire)
                // $('#service').val(data.service.id)
            },
            error: function (error) {
                console.error(error);
            }
        })
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