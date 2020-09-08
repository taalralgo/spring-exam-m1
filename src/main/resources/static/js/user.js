$(document).ready(function () {
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
});