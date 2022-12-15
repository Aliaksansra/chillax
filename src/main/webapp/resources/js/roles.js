/**
 * @event ready on document ready
 */
$(document).ready(function () {

    /**
     * csrf token of page allRoles.jsp
     */
    token = $('#csrfToken').val();

    /**
     * csrf header of page allRoles.jsp
     */
    header = $('#csrfHeader').val();

    /**
     * @event submit sends the json with new role data to the server
     * @event success reloads page
     * @event error gets validation errors from server and puts them to the appropriate fields on the page
     */
    $('#addRole').submit(function (event) {
        event.preventDefault();
        removeErrors();
        $.ajax({
            type: 'POST',
            url: '/api/admin/role/add',
            data: JSON.stringify({'role': $('#role').val()}),
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
                if(data.status === 200){
                   location.replace("/403");
               } else {
                    $('#role').addClass("has-error");
                    $('#errorAddRole').toggleClass('hide show');
                    var errors = data.responseJSON;
                    for (var i = 0; i < errors.length; i++) {
                        $("<strong class='errorValid'></strong><br class='errorValid'/>").text(JSON.stringify(errors[i].message)).appendTo('#divAddRole');
                    }
                }
            }
        });
    });
});

/**
 * Sends an ajax request with role id to the server to delete the role.
 * @event success reloads page
 * @event error shows error message
 * @param {number} idRole role id
 */
function deleteRole(idRole) {
    $.ajax({
        type: 'DELETE',
        url: '/api/admin/role/delete/' + idRole,
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
            }else {
                $('#errorDeleteRole').toggleClass('hide show');
            }
        }
    })
}

/**
 * @event submit sends the json with the updated values of the role to the server
 * @event success reloads page
 * @event error gets validation errors from server and puts them to the appropriate fields on the page
 * @param {number} idRole role id
 * @param {number} index the number of the current iteration
 */
function updateRole(idRole, index) {
    $('#role' + index).toggleClass('show hide');
    $('#updateRoleBtn' + index).toggleClass('show hide');
    $('#updateRoleClose' + index).toggleClass('hide inline');
    $('#updateRoleName' + index).removeClass("has-error").toggleClass('hide show');
    $('#updateRoleOk' + index).toggleClass('hide inline');
    $('#updateRole' + index).submit(function (event) {
        event.preventDefault();
        removeErrors();
        $.ajax({
            type: 'PUT',
            url: '/api/admin/role/update',
            data: JSON.stringify({'idRole': idRole, 'role': $('#updateRoleName' + index).val()}),
            dataType: 'json',
            beforeSend: function head(xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-Type", "application/json", "charset=utf-8");
                xhr.setRequestHeader(header, token);
            },
            success: function () {
                location.reload();
            },
            error: function (data) {
                if (data.status === 405) {
                    location.replace("/403");
                } else {
                    $('#updateRoleName' + index).addClass("has-error");
                    $('#errorUpdateRole').toggleClass('hide show');
                    var errors = data.responseJSON;
                    for (var i = 0; i < errors.length; i++) {
                        $("<strong class='errorValid'></strong><br class='errorValid'/>").text(JSON.stringify(errors[i].message)).appendTo('#tdUpdateRole' + index);
                    }
                }
            }
        });
    });
}

/**
 * Closes updating role on the page.
 * @param index {number} the number of the current iteration
 */
function closeRoleUpdate(index) {
    $('#role' + index).toggleClass('hide show');
    $('#updateRoleBtn' + index).toggleClass('hide show');
    $('#updateRoleName' + index).toggleClass('show hide');
    $('#updateRoleClose' + index).toggleClass('inline hide');
    $('#updateRoleOk' + index).toggleClass('inline hide');
    removeErrors();
}

/**
 * Removes error styles from fields, hides error message.
 */
function removeErrors() {
    $('.errorValid').remove();
}
