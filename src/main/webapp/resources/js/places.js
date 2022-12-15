/**
 * @event ready on document ready
 */
$(document).ready(function () {

    /**
     * csrf token of page allPlaces.jsp
     */
    token = $('#csrfToken').val();

    /**
     * csrf header of page allPlaces.jsp
     */
    header = $('#csrfHeader').val();

    /**
     * @event submit sends the json with new place data to the server
     * @event success reloads page
     * @event error gets validation errors from server and puts them to the appropriate fields on the page
     */
    $('#addPlace').submit(function (event) {
        event.preventDefault();
        removeErrors();
        $.ajax({
            type: 'POST',
            url: '/api/admin/place/add',
            data: JSON.stringify({
                'city': $('#city').val(),
                'address': $('#address').val(),
                'placeName': $('#placeName').val()
            }),
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
                    $('#errorAddPlace').toggleClass('hide show');
                    var errors = data.responseJSON;
                    for (var i = 0; i < errors.length; i++) {
                        switch (errors[i].field) {
                            case 'city':
                                $('#city').addClass("has-error");
                                $("<strong class='errorValid'></strong><br class='errorValid'/>").text(JSON.stringify(errors[i].message)).appendTo('#divAddCity');
                                break;
                            case 'address':
                                $('#address').addClass("has-error");
                                $("<strong class='errorValid'></strong><br class='errorValid'/>").text(JSON.stringify(errors[i].message)).appendTo('#divAddAddress');
                                break;
                            case 'placeName':
                                $('#placeName').addClass("has-error");
                                $("<strong class='errorValid'></strong><br class='errorValid'/>").text(JSON.stringify(errors[i].message)).appendTo('#divAddPlaceName');
                                break;
                        }
                    }
                }
            }
        })
    });
});

/**
 * Sends an ajax request with place id to the server to delete the place.
 * @event success reloads page
 * @event error shows error message
 * @param {number} idPlace place id
 */
function deletePlace(idPlace) {
    $.ajax({
        type: 'DELETE',
        url: '/api/admin/place/delete/' + idPlace,
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
                $('#errorDeletePlace').toggleClass('hide show');
            }
        }
    })
}

/**
 * @event submit sends the json with the updated values of the place to the server
 * @event success reloads page
 * @event error gets validation errors from server and puts them to the appropriate fields on the page
 * @param {number} idPlace place id
 * @param {number} index the number of the current iteration
 */
function updatePlace(idPlace, index) {
    $('#city' + index).toggleClass('show hide');
    $('#address' + index).toggleClass('show hide');
    $('#placeName' + index).toggleClass('show hide');
    $('#updatePlaceBtn' + index).toggleClass('show hide');
    $('#updatePlaceClose' + index).toggleClass('hide show');
    $('#updateCity' + index).toggleClass('hide show');
    $('#updateAddress' + index).toggleClass('hide show');
    $('#updatePlaceName' + index).toggleClass('hide show');
    $('#placeUpdateOk' + index).toggleClass('hide show');
    $('#form' + index).submit(function (event) {
        event.preventDefault();
        removeErrors();
        var parameters = {
            'idPlace': idPlace, 'city': $('#updateCity' + index).val(), 'address': $('#updateAddress' + index).val(),
            'placeName': $('#updatePlaceName' + index).val()
        };
        $.ajax({
            type: 'PUT',
            url: '/api/admin/place/update',
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
                if (data.status === 405) {
                    location.replace("/403");
                } else {
                    $('#errorUpdatePlace').toggleClass('hide show');
                    var errors = data.responseJSON;
                    for (var i = 0; i < errors.length; i++) {
                        switch (errors[i].field) {
                            case 'city':
                                $('#updateCity' + index).addClass("has-error");
                                $("<strong class='errorValid'></strong><br class='errorValid'/>").text(JSON.stringify(errors[i].message)).appendTo('#tdCity' + index);
                                break;
                            case 'address':
                                $('#updateAddress' + index).addClass("has-error");
                                $("<strong class='errorValid'></strong><br class='errorValid'/>").text(JSON.stringify(errors[i].message)).appendTo('#tdAddress' + index);
                                break;
                            case 'placeName':
                                $('#updatePlaceName' + index).addClass("has-error");
                                $("<strong class='errorValid'></strong><br class='errorValid'/>").text(JSON.stringify(errors[i].message)).appendTo('#tdPlaceName' + index);
                                break;
                        }
                    }
                }
            }
        });
    });
}

/**
 * Closes updating place on the page.
 * @param index {number} the number of the current iteration
 */
function closeUpdate(index) {
    $('#city' + index).toggleClass('hide show');
    $('#address' + index).toggleClass('hide show');
    $('#placeName' + index).toggleClass('hide show');
    $('#updateCity' + index).toggleClass('show hide');
    $('#updateAddress' + index).toggleClass('show hide');
    $('#updatePlaceName' + index).toggleClass('show hide');
    $('#updatePlaceBtn' + index).toggleClass('hide show');
    $('#updatePlaceClose' + index).toggleClass('show hide');
    $('#placeUpdateOk' + index).toggleClass('show hide');
    removeErrors();
}

/**
 * Removes error styles from fields, hides error message.
 */
function removeErrors() {
    $('.errorValid').remove();
    $('.has-error').removeClass("has-error");
}