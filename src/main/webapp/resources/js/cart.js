/**
 * @var{number} remaining number of tickets
 */
var remainingTickets;

/**
 * @event ready on document ready
 */
$(document).ready(function () {

    /**
     * loop counter
     * @type {number}
     */
    var number = 0;

    /**
     * csrf token of page cart.jsp
     */
    var token;

    /**
     * csrf header of page cart.jsp
     */
    var header;

    /**
     * total order price
     * @type {number}
     */
    var totalPrice = 0;

    getCountTickets();

    /**
     * Gets cart information from server.
     * @event success puts data on the page "cart"
     * @async
     */
    $.ajax({
        type: 'GET',
        url: '/api/cart',
        contentType: 'application/json; charset = utf-8',
        dataType: 'json',
        success: function (data) {
            $.each(data, function (key, value) {
                number += 1;
                var date = new Date(value.datetimeOfEvent);
                $("<tr>").appendTo('.table');
                $("<p class='margin-20'></p>").text(number).appendTo('#number');
                $("<p class='margin-20'></p>").text(value.title).appendTo('#title');
                $("<p class='margin-20'></p>").text(value.place.placeName).appendTo('#place');
                $("<p class='margin-20'></p>").text(date.toLocaleDateString() + ' ' + date.toLocaleTimeString([], {
                    hour: '2-digit',
                    minute: '2-digit'
                })).appendTo('#date');
                $("<div class='padding'>").appendTo('#tickets');
                $("<input type='hidden'/>").val(value.idEvent).attr('id', 'idEvent' + number).appendTo('#tickets');
                $("<input class='change' type='button' value='-'/>").attr('id', number).appendTo('#tickets');
                $("<input type='number' min='1' class='number'/>").attr('id', 'sum' + number).val(value.countOfTickets).appendTo('#tickets');
                $("<input class='change' type='button' value='+'/>").attr('id', number).appendTo('#tickets');
                $("</div>").appendTo('#tickets');
                $("<p class='margin-20'></p>").text(value.price * value.countOfTickets).attr('id', 'total' + number).appendTo('#price');
                $("<div class='padding'>").appendTo('#delete');
                $("<button class='del btn btn-warning'>X</button>").attr('id', number).appendTo('#delete');
                $("</div>").appendTo('#delete');
                $("</tr>").appendTo('.table');
                $("<hr>").appendTo('padding');
                totalPrice = totalPrice + value.price * value.countOfTickets;
            });
            $("<span id='total'></span>").text(totalPrice + ' BYN').appendTo('#cost');
            token = $('#csrfToken').val();
            header = $('#csrfHeader').val();

            /**
             * @event onclick button with value "+" or "-" gets remaining number of tickets from server
             * and counts tickets in the cart taking into account the remaining.
             */
            $('.change').click(function () {
                var num = $(this).attr("id");
                var count = $('#sum' + num).val();

                var param = {'idEvent': $('#idEvent' + num).val()};
                $.ajax({
                    type: 'GET',
                    url: '/api/cart/tickets/free',
                    data: param,
                    async: false,
                    dataType: 'json',
                    success: function (data) {
                        remainingTickets = data;
                    }
                });
                if ($(this).val().indexOf('-') != -1) {
                    if (count > 1) {
                        count--;
                    }
                } else if (remainingTickets > count) {
                    count++;
                }
                $('#sum' + num).val(count);

                /**
                 * Sends the json (event id, number of booked tickets) to the server.
                 * @event success gets total price of the current order and puts it on the page.
                 * @async
                 */
                $.ajax({
                    type: 'PUT',
                    url: '/api/cart',
                    data: JSON.stringify({'idEvent': $('#idEvent' + num).val(), 'countOfTickets': count}),
                    dataType: 'json',
                    beforeSend: function head(xhr) {
                        xhr.setRequestHeader("Accept", "application/json");
                        xhr.setRequestHeader("Content-Type", "application/json");
                        xhr.setRequestHeader(header, token);
                    },
                    success: function (data) {
                        var priceChanges = data - parseInt($('#total' + num).text());
                        var totalPriceBefore = parseInt($('#total').text());
                        $('#total').text((totalPriceBefore + priceChanges) + ' BYN');
                        $('#total' + num).text(data);
                    }
                });
            });

            /**
             * @event onclick sends event id to the server to remove order information for this event.
             * @event success reloads page
             * @event error means that cart is empty, shows message about it
             */
            $(".del").click(function () {
                $.ajax({
                    type: 'DELETE',
                    url: '/api/cart/' + $('#idEvent' + $(this).attr("id")).val(),
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
                        $('#emptyCartMessage').toggleClass('hide show');
                        $('#confirm').toggleClass('show hide');
                        $('#anonym').toggleClass('show hide');
                        $('#cost').toggleClass('show hide');
                    }
                });
            });

        },
        error: function () {
            $('#emptyCartMessage').toggleClass('hide show');
            $('#confirm').toggleClass('show hide');
            $('#anonym').toggleClass('show hide');
            $('#cost').toggleClass('show hide');

        }
    });

    /**
     * @event onclick confirms order on the server
     * @event success replaces to the payment page
     * @event error shows error message
     */
    $("#confirm").click(function () {
        $.ajax({
            type: 'GET',
            url: '/api/cart/order',
            dataType: 'json',
            success: function (data) {
                $("#confirm").attr('disabled', true);
                location.replace('/payment/' + data);
            },
            error: function () {
                $('#errorConfirmOrder').toggleClass('hide show');
            }
        });
    });

    /**
     * @event onclick for unauthorized users,
     * shows a message about the need to authorize before confirming the order.
     */
    $("#anonym").click(function () {
        $('#links').toggleClass('hide show');
    });
});