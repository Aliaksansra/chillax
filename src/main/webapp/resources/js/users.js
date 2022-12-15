/**
 * @event ready on document ready
 */
$(document).ready(function () {

    /**
     * csrf token of page allUsers.jsp
     */
    token = $('#csrfToken').val();

    /**
     * csrf header of page allUsers.jsp
     */
    header = $('#csrfHeader').val();

    /**
     * @event hidden.bs.modal replaces to the main page
     */
    $('#registerModal').on('hidden.bs.modal', function (e) {
        location.replace('/');
    });

    /**
     * @event submit sends the json with new user data to the server, validates field with roles before it.
     * @event success reloads page
     * @event error gets validation errors from server and puts them to the appropriate fields on the page
     */
    $('#addUser').submit(function (event) {
        event.preventDefault();
        removeErrors();
        validate($('#userRole').val());
        var parameters = {
            'login': $('#addLogin').val(),
            'password': $('#addPassword').val(),
            'name': $('#username').val(),
            'surname': $('#surname').val(),
            'email': $('#email').val(),
            'phone': $('#phone').val(),
            'role': {'idRole': $('#userRole').val()[0]}
        };
        $.ajax({
            type: 'POST',
            url: '/api/user/admin/add',
            data: JSON.stringify(parameters),
            dataType: 'json',
            beforeSend: function head(xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-Type", "application/json");
                xhr.setRequestHeader(header, token);
            },
            success: function () {
                location.reload();
            },
            error: function (data) {
                if (data.status === 200) {
                    location.replace("/403");
                } else {
                    $('#errorAddUser').toggleClass('hide show');
                    var errors = data.responseJSON;
                    for (var i = 0; i < errors.length; i++) {
                        switch (errors[i].field) {
                            case 'name':
                                $('#username').addClass("has-error");
                                $("<strong class='errorValid'></strong><br class='errorValid'/>").text(JSON.stringify(errors[i].message)).appendTo('#divAddUsername');
                                break;
                            case 'surname':
                                $('#surname').addClass("has-error");
                                $("<strong class='errorValid'></strong><br class='errorValid'/>").text(JSON.stringify(errors[i].message)).appendTo('#divAddSurname');
                                break;
                            case 'email':
                                $('#email').addClass("has-error");
                                $("<strong class='errorValid'></strong><br class='errorValid'/>").text(JSON.stringify(errors[i].message)).appendTo('#divAddEmail');
                                break;
                            case 'password':
                                $('#addPassword').addClass("has-error");
                                $("<strong class='errorValid'></strong><br class='errorValid'/>").text(JSON.stringify(errors[i].message)).appendTo('#divAddPassword');
                                break;
                            case 'login':
                                $('#addLogin').addClass("has-error");
                                $("<strong class='errorValid'></strong><br class='errorValid'/>").text(JSON.stringify(errors[i].message)).appendTo('#divAddLogin');
                                break;
                            case 'phone':
                                $('#phone').addClass("has-error");
                                $("<strong class='errorValid'></strong><br class='errorValid'/>").text(JSON.stringify(errors[i].message)).appendTo('#divAddPhone');
                                break;
                        }
                    }
                }
            }
        });
    });

    /**
     * @event submit sends the json with user data to the server for registration with role USER
     * @event success reloads page
     * @event error gets validation errors from server and puts them to the appropriate fields on the page
     */
    $('#register').submit(function (event) {
        event.preventDefault();
        removeErrors();
        var parameters = {
            'login': $('#yourLogin').val(),
            'password': $('#yourPassword').val(),
            'name': $('#yourName').val(),
            'surname': $('#yourSurname').val(),
            'email': $('#yourEmail').val(),
            'phone': $('#yourPhone').val()
        };
        $.ajax({
            type: 'POST',
            url: '/api/user/register',
            data: JSON.stringify(parameters),
            dataType: 'json',
            beforeSend: function head(xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-Type", "application/json");
                xhr.setRequestHeader($('#csrfHeaderRegistration').val(), $('#csrfTokenRegistration').val());
            },
            success: function () {
                $('#registerModal').modal('show');
            },
            error: function (data) {
                $('#errorRegister').toggleClass('hide show');
                var errors = data.responseJSON;
                for (var i = 0; i < errors.length; i++) {
                    switch (errors[i].field) {
                        case 'name':
                            $('#yourName').addClass("has-error");
                            $("<strong class='errorValid'></strong><br class='errorValid'/>").text(JSON.stringify(errors[i].message)).appendTo('#divInputUsername');
                            break;
                        case 'surname':
                            $('#yourSurname').addClass("has-error");
                            $("<strong class='errorValid'></strong><br class='errorValid'/>").text(JSON.stringify(errors[i].message)).appendTo('#divInputSurname');
                            break;
                        case 'email':
                            $('#yourEmail').addClass("has-error");
                            $("<strong class='errorValid'></strong><br class='errorValid'/>").text(JSON.stringify(errors[i].message)).appendTo('#divInputEmail');
                            break;
                        case 'password':
                            $('#yourPassword').addClass("has-error");
                            $("<strong class='errorValid'></strong><br class='errorValid'/>").text(JSON.stringify(errors[i].message)).appendTo('#divInputPassword');
                            break;
                        case 'login':
                            $('#yourLogin').addClass("has-error");
                            $("<strong class='errorValid'></strong><br class='errorValid'/>").text(JSON.stringify(errors[i].message)).appendTo('#divInputLogin');
                            break;
                        case 'phone':
                            $('#yourPhone').addClass("has-error");
                            $("<strong class='errorValid'></strong><br class='errorValid'/>").text(JSON.stringify(errors[i].message)).appendTo('#divInputPhone');
                            break;
                    }
                }
            }
        })
    })
});

/**
 * Sends an ajax request with user id to the server to delete the user.
 * @event success reloads page
 * @event error shows error message
 * @param {number} idUser user id
 */
function deleteUser(idUser) {
    $.ajax({
        type: 'DELETE',
        url: '/api/user/admin/delete/' + idUser,
        dataType: 'json',
        beforeSend: function head(xhr) {
            xhr.setRequestHeader("Accept", "application/json");
            xhr.setRequestHeader("Content-Type", "application/json");
            xhr.setRequestHeader(header, token);
        },
        success: function () {
            location.reload();
        },
        error: function (data) {
            if (data.status === 405) {
                location.replace("/403");
            } else {
                $('#errorDeleteUser').toggleClass('hide show');
            }
        }
    })
}

/**
 * Removes error styles from fields, hides error message.
 */
function removeErrors() {
    $('.errorValid').remove();
    $('.has-error').removeClass("has-error");
}

/**
 * Validates roles fields.
 * @param {number} idRole role id
 * @returns {boolean} false - if there are validation errors, true - if not
 */
function validate(idRole) {
    if (idRole == 0) {
        $('#userRole').addClass("has-error");
        $("<strong class='errorValid'></strong><br class='errorValid'/>").text('The role is required!').appendTo('#divAddUserRole');
        return false;
    }
}