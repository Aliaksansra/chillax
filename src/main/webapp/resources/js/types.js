/**
 * @event ready on document ready
 */
$(document).ready(function () {

    /**
     * csrf token of page allTypes.jsp
     */
    token = $('#csrfToken').val();

    /**
     * csrf header of page allTypes.jsp
     */
    header = $('#csrfHeader').val();

    /**
     * @event submit sends the json with new type data to the server
     * @event success reloads page
     * @event error gets validation errors from server and puts them to the appropriate fields on the page
     */
    $('#addType').submit(function (event) {
        event.preventDefault();
        removeErrors();
        $.ajax({
            type: 'POST',
            url: '/api/admin/type/add',
            data: JSON.stringify({'nameOfType': $('#nameOfType').val()}),
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
                if (data.status === 200) {
                    location.replace("/403");
                } else {
                    $('#nameOfType').addClass("has-error");
                    $('#errorAddType').toggleClass('hide show');
                    var errors = data.responseJSON;
                    for (var i = 0; i < errors.length; i++) {
                        $("<strong class='errorValid'></strong><br class='errorValid'/>").text(JSON.stringify(errors[i].message)).appendTo('#divAddType');
                    }
                }
            }
        });
    });
});

/**
 * Sends an ajax request with type id to the server to delete the type.
 * @event success reloads page
 * @event error shows error message
 * @param {number} idType type id
 */
function deleteType(idType) {
    $.ajax({
        type: 'DELETE',
        url: '/api/admin/type/delete/' + idType,
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
                $('#errorDeleteType').toggleClass('hide show');
            }
        }
    })
}

/**
 * @event submit sends the json with the updated values of the type to the server
 * @event success reloads page
 * @event error gets validation errors from server and puts them to the appropriate fields on the page
 * @param {number} idType type id
 * @param {number} index the number of the current iteration
 */
function updateType(idType, index) {
    $('#typeName' + index).toggleClass('show hide');
    $('#updateTypeBtn' + index).toggleClass('show hide');
    $('#updateTypeClose' + index).toggleClass('hide inline');
    $('#updateTypeName' + index).removeClass("has-error").toggleClass('hide show');
    $('#typeUpdateOk' + index).toggleClass('hide inline');
    $('#updateType' + index).submit(function (event) {
        event.preventDefault();
        removeErrors();
        var parameters = {'idType': idType, 'nameOfType': $('#updateTypeName' + index).val()};
        $.ajax({
            type: 'PUT',
            url: '/api/admin/type/update',
            data: JSON.stringify(parameters),
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
                    $('#updateTypeName' + index).addClass("has-error");
                    $('#errorUpdateType').toggleClass('hide show');
                    var errors = data.responseJSON;
                    for (var i = 0; i < errors.length; i++) {
                        $("<strong class='errorValid'></strong><br class='errorValid'/>").text(JSON.stringify(errors[i].message)).appendTo('#tdUpdateType' + index);
                    }
                }
            }
        });
    });
}

/**
 * Closes updating type on the page.
 * @param index {number} the number of the current iteration
 */
function closeUpdate(index) {
    $('#typeName' + index).toggleClass('hide show');
    $('#updateTypeBtn' + index).toggleClass('hide show');
    $('#updateTypeName' + index).toggleClass('show hide');
    $('#updateTypeClose' + index).toggleClass('inline hide');
    $('#typeUpdateOk' + index).toggleClass('inline hide');
    removeErrors();
}

/**
 * Removes error styles from fields, hides error message.
 */
function removeErrors() {
    $('.errorValid').remove();
}