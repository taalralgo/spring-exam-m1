$(document).ready(function () {

    $('.transaction-add').on('submit', function (e) {
        const numpiece = $('#numpiece').val();
        const prenom = $('#prenom').val();
        const nom = $('#nom').val();
        const telephone = $('#telephone').val();
        const numpieceemet = $('#numpieceemet').val();
        const prenomemet = $('#prenomemet').val();
        const nomemet = $('#nomemet').val();
        const telephoneemet = $('#telephoneemet').val();
        const montant = $('#montant').val();
        if(numpiece === '' || prenom === '' || nom === '' || telephone === '' || numpieceemet === ''
            || prenomemet === '' || nomemet === '' || telephoneemet === '' || montant === '') {
            e.preventDefault();
            alert("Tous les champs ne sont pas renseignés!");
            return false;
        }
        if(montant < 5) {
            e.preventDefault();
            alert("Le montant n'est pas valide!");
            return false;
        }
        if(telephone.length != 9 || telephoneemet.length != 9) {
            e.preventDefault();
            alert("Le numero de telephone n'est pas valide!");
            return false;
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