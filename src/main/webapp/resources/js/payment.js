/**
 * @event ready on document ready
 */
$(document).ready(function () {

    getCountTickets();

    /**
     * Validates credit card fields
     * @type {{validate}}
     */
    var blueThemeCreditly = Creditly.initialize(
        '.creditly-wrapper.blue-theme .expiration-month-and-year',
        '.creditly-wrapper.blue-theme .credit-card-number',
        '.creditly-wrapper.blue-theme .security-code',
        '.creditly-wrapper.blue-theme .card-type'
    );

    /**
     * @event click if there are no validation errors in the credit card fields
     * sends an ajax request to the server with order id
     * to confirm payment in the database
     * @event success replaces to the page tickets.jsp with paid tickets
     * @event error shows error message
     */
    $(".creditly-blue-theme-submit").click(function (event) {
        event.preventDefault();
        var output = blueThemeCreditly.validate(); // Your validated credit card output
        if (output) {
            var param = {'guid': $('#orderGuid').val()};
            $.ajax({
                type: 'GET',
                url: '/api/cart/pay',
                data: param,
                dataType: 'json',
                success: function () {
                    $(".creditly-blue-theme-submit").attr('disabled', true);
                    location.replace('/orderedTickets/' + $('#orderGuid').val());
                },
                error: function () {
                    $('#failMessage').toggleClass('hide show');
                }
            });
        }
    });
})
