/**
 * @event ready on document ready
 */
$(document).ready(function () {
    /**
     * csrf token of page event.jsp
     */
    token = $('#csrfToken').val();

    /**
     * csrf header of page event.jsp
     */
    header = $('#csrfHeader').val();

    /**
     * @event hidden.bs.modal replaces to the main page
     */
    $('#successMsgModal').on('hidden.bs.modal', function () {
        location.replace('/');
    });
    /**
     * @event submit sends the json with new event data to the server, validates fields with places and types before it.
     * @event success resets form field values
     * @event error gets validation errors from server and puts them to the appropriate fields on the page
     */
    $('#addEvent').submit(function (event) {
        event.preventDefault();
        removeErrors();
        validate($('#place').val(), $('#type').val(), 'place', 'type', 'divAddEventPlace', 'divAddEventType');
        var addForm = new FormData();
        addForm.append("file", file.files[0]);
        var filename = null;
        if (file.files[0] != null) {
            loadImage(addForm);
            filename = file.files[0].name
        }
        $.ajax({
            type: 'POST',
            url: '/api/admin/event/add',
            data: JSON.stringify({
                'title': $('#title').val(),
                'description': $('#description').val(),
                'datetimeOfEvent': $('#datetimeOfEvent').val(),
                'price': $('#price').val(),
                'allTickets': $('#allTickets').val(),
                'poster': filename,
                'type': {"idType": $('#type').val()[0]},
                "place": {"idPlace": $('#place').val()[0]}
            }),
            dataType: 'json',
            beforeSend: function head(xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-Type", "application/json");
                xhr.setRequestHeader(header, token);
            },
            success: function () {
                if ($('#errorAddEvent').hasClass("show")) {
                    $('#errorAddEvent').toggleClass('show hide');
                }
                $('#successAddEvent').toggleClass('hide show');
                $('#title').val('');
                $('#description').val('');
                $('#datetimeOfEvent').val('');
                $('#price').val('');
                $('#allTickets').val('');
            },
            error: function (data) {
                if (data.status === 200) {
                    location.replace("/403");
                } else {
                    if ($('#successAddEvent').hasClass("show")) {
                        $('#successAddEvent').toggleClass('show hide');
                    }
                    $('#errorAddEvent').toggleClass('hide show');
                    var errors = data.responseJSON;
                    for (var i = 0; i < errors.length; i++) {
                        switch (errors[i].field) {
                            case 'title':
                                $('#title').addClass("has-error");
                                $("<strong class='errorValid'></strong><br class='errorValid'/>").text(JSON.stringify(errors[i].message)).appendTo('#divAddTitle');
                                break;
                            case 'datetimeOfEvent':
                                $('#datetimeOfEvent').addClass("has-error");
                                $("<strong class='errorValid'></strong><br class='errorValid'/>").text(JSON.stringify(errors[i].message)).appendTo('#divAddEventDate');
                                break;
                            case 'price':
                                $('#price').addClass("has-error");
                                $("<strong class='errorValid'></strong><br class='errorValid'/>").text(JSON.stringify(errors[i].message)).appendTo('#divAddPrice');
                                break;
                            case 'allTickets':
                                $('#allTickets').addClass("has-error");
                                $("<strong class='errorValid'></strong><br class='errorValid'/>").text(JSON.stringify(errors[i].message)).appendTo('#divAddAllTickets');
                                break;
                        }
                    }
                }
            }
        })
    });

    /**
     * @event submit sends the json with the updated values of the event to the server,
     * validates fields with places and types before it.
     * @event success shows modal with success message
     * @event error gets validation errors from server and puts them to the appropriate fields on the page
     */
    $('#updateEvent').submit(function (event) {
        event.preventDefault();
        removeErrors();
        var updateFileForm = new FormData();
        updateFileForm.append("file", file.files[0]);
        var filename = null;
        if (file.files[0] != null) {
            loadImage(updateFileForm);
            filename = file.files[0].name
        }
        validate($('#updatePlace').val(), $('#updateType').val(), 'updatePlace', 'updateType', 'divUpdateEventPlace', 'divUpdateEventType');
        var parameters = {
            'idEvent': $('#updateIdEvent').val(),
            'title': $('#updateTitle').val(),
            'description': $('#updateDescription').val(),
            'datetimeOfEvent': $('#updateDatetimeOfEvent').val(),
            'price': $('#updatePrice').val(),
            'allTickets': $('#updateAllTickets').val(),
            'poster': filename,
            'type': {"idType": $('#updateType').val()[0]},
            "place": {"idPlace": $('#updatePlace').val()[0]}
        };
        $.ajax({
            type: 'PUT',
            url: '/api/admin/event/update',
            data: JSON.stringify(parameters),
            dataType: 'json',
            beforeSend: function head(xhr) {
                xhr.setRequestHeader("Accept", "application/json");
                xhr.setRequestHeader("Content-Type", "application/json");
                xhr.setRequestHeader(header, token);
            },
            success: function () {
                $('#successMsgModal').modal('show');
            },
            error: function (data) {
                if (data.status === 405) {
                    location.replace("/403");
                } else {
                    $('#errorUpdateEvent').toggleClass('hide show');
                    var errors = data.responseJSON;
                    for (var i = 0; i < errors.length; i++) {
                        switch (errors[i].field) {
                            case 'title':
                                $('#updateTitle').addClass("has-error");
                                $("<strong class='errorValid'></strong><br class='errorValid'/>").text(JSON.stringify(errors[i].message)).appendTo('#divUpdateTitle');
                                break;
                            case 'datetimeOfEvent':
                                $('#updateDatetimeOfEvent').addClass("has-error");
                                $("<strong class='errorValid'></strong><br class='errorValid'/>").text(JSON.stringify(errors[i].message)).appendTo('#divUpdateEventDate');
                                break;
                            case 'price':
                                $('#updatePrice').addClass("has-error");
                                $("<strong class='errorValid'></strong><br class='errorValid'/>").text(JSON.stringify(errors[i].message)).appendTo('#divUpdatePrice');
                                break;
                            case 'allTickets':
                                $('#updateAllTickets').addClass("has-error");
                                $("<strong class='errorValid'></strong><br class='errorValid'/>").text(JSON.stringify(errors[i].message)).appendTo('#divUpdateAllTickets');
                                break;
                        }
                    }
                }
            }
        })
    });
});

/**
 * Sends an ajax request with event id to the server to delete the event.
 * @param {number} idEvent event id
 * @event success reloads page
 * @event error shows error message or if access is denied, will replace to url "/403"
 */
function deleteEvent(idEvent) {
    $.ajax({
        type: 'DELETE',
        url: '/api/admin/event/delete/' + idEvent,
        dataType: 'json',
        beforeSend: function head(xhr) {
            xhr.setRequestHeader("Accept", "application/json");
            xhr.setRequestHeader("Content-Type", "application/json");
            xhr.setRequestHeader($('#csrfHeaderIndex').val(), $('#csrfTokenIndex').val());
        },
        success: function () {
            location.reload();
        },
        error: function (data) {
            if (data.status === 405) {
                location.replace("/403");
            } else {
                $('#errorDeleteEvent').toggleClass('hide show');
            }
        }
    })
}

/**
 * Removes error styles from fields, hides error message.
 */
function removeErrors() {
    $('#errorAddEvent').toggleClass('show hide');
    $('.errorValid').remove();
    $('.has-error').removeClass("has-error");

}

/**
 * Validates places and types fields.
 * @param {number} idPlace place id
 * @param {number} idType type id
 * @param {String} fieldPlace place selector
 * @param {String} fieldType type selector
 * @param {String} divPlace div place selector
 * @param {String} divType div type selector
 * @returns {boolean} false - if there are validation errors, true - if not
 */
function validate(idPlace, idType, fieldPlace, fieldType, divPlace, divType) {
    if (idPlace == 0) {
        $('#' + fieldPlace).addClass("has-error");
        $("<strong class='errorValid'></strong><br class='errorValid'/>").text('The place is required!').appendTo('#' + divPlace);
        return false;
    }
    if (idType == 0) {
        $('#' + fieldType).addClass("has-error");
        $("<strong class='errorValid'></strong><br class='errorValid'/>").text('The type is required!').appendTo('#' + divType);
        return false;
    }
}

/**
 * Sends image to the server from form.
 * @param addForm FormData()
 */
function loadImage(addForm) {
    $.ajax({
        type: 'POST',
        url: '/api/admin/event/loadimage',
        data: addForm,
        enctype: 'multipart/form-data',
        processData: false,
        contentType: false,
        beforeSend: function head(xhr) {
            xhr.setRequestHeader(header, token);
        },
        success: function (data) {
            if (data === 'ALREADY_REPORTED') {
                $('#messageNotUniquePoster').toggleClass('hide show');
            }
        }
    });
}