/**
 * @event ready on document ready
 */
$(document).ready(function () {

    /**
     * csrf token of page event.jsp
     */
    token = $('#csrfToken').val();

    /**
     * csrf token of page event.jsp
     */
    header = $('#csrfHeader').val();

    /**
     * @event onkeypress sends the json with new comment to the server.
     * @event success reloads page
     * @event error shows error message
     */
    $('#newComment').keypress(function (event) {
        var key = event.which;
        if (key == 13)  // the enter key code
        {
            $.ajax({
                type: 'POST',
                url: '/api/comments/add',
                data: JSON.stringify({
                    'comment': $('#newComment').val(), 'dateOfComment': new Date(), 'idEvent': $('#idEvent').val()
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
                error: function () {
                    $('#errorAddComment').toggleClass('hide show');
                }
            });
        }
    });
});

/**
 * Sends the json with comment id and comment text to the server fot updating.
 * @param {number} idComments comment id
 * @param {number} index the number of the current iteration
 * @event success reloads page
 * @event error shows error message
 */
function updateComment(idComments, index) {
    $('#current' + index).toggleClass('inline hide');
    $('#updateButton' + index).toggleClass('inline hide');
    $('#closeUpdate' + index).toggleClass('hide inline');
    $('#update' + index).toggleClass('hide inline').keypress(function (event) {
        var key = event.which;
        if (key == 13)  // the enter key code
        {
            $.ajax({
                type: 'PUT',
                url: '/api/comments/update',
                data: JSON.stringify({'idComments': idComments, 'comment': $('#update' + index).val()}),
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
                    $('#errorUpdateComment').toggleClass('hide show');
                }
            });
        }
    });
}

/**
 * Sends an ajax request with comment id to the server to delete the comment.
 * @param {number} idComments comment id
 * @event success reloads page
 * @event error shows error message
 */
function deleteComment(idComments) {
    $.ajax({
        type: 'DELETE',
        url: '/api/comments/delete/' + idComments,
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
            $('#errorDeleteComment').toggleClass('hide show');
        }
    });
}

/**
 * Closes updating comment on the page.
 * @param index {number} the number of the current iteration
 */
function closeUpdate(index) {
    $('#current' + index).toggleClass('hide inline');
    $('#update' + index).toggleClass('inline hide');
    $('#updateButton' + index).toggleClass('hide inline');
    $('#closeUpdate' + index).toggleClass('inline hide');
}