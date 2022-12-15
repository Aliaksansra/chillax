/**
 * @event ready on document ready
 */
$(document).ready(function () {

    getCountTickets();

    /**
     * Gets an authenticated user from server.
     * @async
     * @event success puts user login on the page
     */
    if ($('#currentUser').html() === '') {
        $.ajax({
            type: 'GET',
            url: '/api/user/get',
            contentType: 'application/json; charset = utf-8',
            dataType: 'json',
            success: function (data) {
                if (data != 0) {
                    $('#currentUser').html(data.login);
                }
            }
        })
    }

    activateLinks($(".nav-link"));

    activateLinks($(".pagin"));
});

/**
 * Gets the number of tickets in the cart from server.
 * @event success puts it on the page
 * @async
 */
function getCountTickets() {
    $.ajax({
        type: 'GET',
        url: '/api/cart/count',
        contentType: 'application/json; charset = utf-8',
        dataType: 'json',
        success: function (data) {
            if (data != 0) {
                $('#count').html(data);
            }
        }
    })
}

/**
 * Activates menu links when it`s clicked.
 * @function
 */
function activateLinks(selector) {
    $.each(selector, function () {
        if (this.href == document.location.href) {
            $(this).addClass('active');
        }
    })
}

/**
 * Prints page
 */
function printDiv() {
    var divToPrint = document.getElementById('DivIdToPrint');
    var newWin = window.open('', 'Print-Window');
    newWin.document.open().write('<html><body onload="window.print()">' + divToPrint.innerHTML + '</body></html>');
    newWin.document.close();
    setTimeout(function () {
        newWin.close();
    }, 150);
}