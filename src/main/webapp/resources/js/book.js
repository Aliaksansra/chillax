/**
 * @var{number} number of tickets in the cart
 */
var count;

/**
 * @var{number} remaining number of tickets
 */
var remainingTickets;

/**
 * @event ready on document ready
 */
$(function () {

    /**
     * Sends the json(event id and remaining number of tickets) to the server.
     * @event success gets from server remaining number of tickets subtracting the tickets in the cart.
     * @async
     * @type {{idEvent: (*|void|jQuery), remainderOfTickets: jQuery}}
     */
    var parameters = {'idEvent': $('#idEvent').val(), 'remainderOfTickets': $('#remainder').text()};
    $.ajax({
        type: 'GET',
        url: '/api/cart/tickets/remain',
        data: parameters,
        dataType: 'json',
        success: function (data) {
            remainingTickets = data;
            $('#remainder').html(remainingTickets);
            if (remainingTickets == 0) {
                $("#book").attr('disabled', true);
            }
        }
    });

    getCountTickets();

    count = $('#amount').val();

    /**
     * @event onclick button with value "+" or "-", counts tickets taking into account the remaining.
     * @function
     */
    $('.counter').click(function () {
        var num = $(this).attr("id");
        if ($(this).val().indexOf('-') !== -1) {
            if (count > 1) {
                count--;
            }
        } else {
            if (count < remainingTickets) {
                count++;
            }
        }
        $('#amount').val(count);
    });

    /**
     * @event onclick sends the json(event id and number of tickets in the cart) to the server for booking.
     * @event success counts remaining number of tickets and gets number of tickets in the cart from server.
     * @event error shows error message
     * @function
     */
    $("#book").click(function () {
        var params = {'idEvent': $('#idEvent').val(), 'countOfTickets': count};
        $.ajax({
            type: 'GET',
            url: '/api/cart/booking',
            contentType: 'application/json; charset = utf-8',
            data: params,
            dataType: 'json',
            success: function () {
                remainingTickets = remainingTickets - count;
                $('#remainder').html(remainingTickets);
                if (remainingTickets == 0) {
                    $("#book").attr('disabled', true);
                }
                getCountTickets();
                $('#amount').val(1);
                $('#bookSuccess').toggleClass('hide show');
            }
        });
    });
});