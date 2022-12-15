/**
 * @event ready on document ready
 */
$(document).ready(function () {

    /**
     * @event hidden.bs.modal Clears order information in the model
     */
    $('.bd-example-modal-lg').on('hidden.bs.modal', function () {
        $(".orderInfo").html('');
    });

    /**
     * csrf token of page orders.jsp
     */
    token = $('#csrfToken').val();

    /**
     * csrf header of page orders.jsp
     */
    header = $('#csrfHeader').val();
});

/**
 * Sends ajax requests with order id to the server to get the order information and puts it on the page.
 * @param {boolean} paid order is paid or not
 * @error shows error message
 */
function moreAboutOrder(paid) {
    var guid = $('#orderGuid').val();
    var number = 0;
    $.ajax({
        type: 'GET',
        url: '/api/orders/more',
        contentType: 'application/json; charset = utf-8',
        dataType: 'json',
        data: {'guid': guid},
        success: function (data) {
            if (data == '') {
                $('#errorGetOrder').toggleClass('hide show');
            } else {
                $.each(data, function (key, value) {
                    number += 1;
                    var date = new Date(value.datetimeOfEvent);
                    $("<tr>").appendTo('#modaltable');
                    $("<p></p>").text(number).appendTo('#eventNumber');
                    $("<p></p>").text(value.title).appendTo('#eventTitle');
                    $("<p></p>").text(value.place.placeName).appendTo('#eventPlace');
                    $("<p></p>").text(date.toLocaleDateString() + ' ' + date.toLocaleTimeString([], {
                        hour: '2-digit',
                        minute: '2-digit'
                    })).appendTo('#eventDate');
                    var params = {'idEvent': value.idEvent, 'guid': guid};
                    $.ajax({
                        type: 'GET',
                        url: '/api/orders/ticketscount',
                        contentType: 'application/json; charset = utf-8',
                        dataType: 'json',
                        data: params,
                        success: function (data) {
                            $("<p></p>").text(data).appendTo('#countOfTickets');
                            $("<p></p>").text((value.price * data).toFixed(2) + ' BYN').appendTo('#ticketsPrice');
                        }
                    });
                    $("</tr>").appendTo('#modaltable');
                });
            }
        },
        error: function (data) {
            alert(JSON.stringify(data));
            $('#errorGetOrder').toggleClass('hide show');
        }
    });

    /**
     * @function
     * If order is unpaid on @event click replaces to the payment.jsp page
     */
    if (!paid) {
        $('#pay').toggleClass('hide show').click(function () {
            location.replace('/payment/' + guid);
        });
    }
}

/**
 * Sends an ajax request with user id to the server to get the user information and puts it on the page.
 * @param {number} idUser user id
 * @error shows error message
 */
function moreAboutUser(idUser) {
    $.ajax({
        type: 'GET',
        url: '/api/user/admin/more',
        contentType: 'application/json; charset = utf-8',
        dataType: 'json',
        data: {'idUser': idUser},
        success: function (data) {
            $("<p></p>").text(data.login).appendTo('#loginOfUser');
            $("<p></p>").text(data.name).appendTo('#nameOfUser');
            $("<p></p>").text(data.surname).appendTo('#surnameOfUser');
            $("<p></p>").text(data.email).appendTo('#emailOfUser');
            $("<p></p>").text(data.phone).appendTo('#phoneOfUser');
        },
        error: function () {
            $('#errorGetUser').toggleClass('hide show');
        }
    });
}

/**
 * Sends an ajax request with order id to the server to delete the order.
 * @event success reloads page
 * @event error shows error message
 */
function deleteOrder() {
    var orderGuid = $('#orderGuid').val();
    $.ajax({
        type: 'DELETE',
        url: '/api/orders/delete/' + orderGuid,
        dataType: 'json',
        beforeSend: function head(xhr) {
            xhr.setRequestHeader("Accept", "application/json");
            xhr.setRequestHeader("Content-Type", "application/json");
            xhr.setRequestHeader(header, token);
        },
        success: function () {
            location.reload();
        },
        error: function () {
            $('#errorDeleteOrder').toggleClass('hide show');
        }
    })
}