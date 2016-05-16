"use strict";

$('#add-film-modal').on('show.bs.modal', function (e) {
    $.get(
        "/rest/director/all",
        {},
        function (response) {
            var director_select_item = $('#add-film-director-id');
            director_select_item.html('');
            response.forEach(function (item) {
                var id = item.id;
                var full_name = item.firstName + " " + item.lastName;
                director_select_item.append('<option value="' + id + '">' + full_name + '</option>');
            });
        }
    )
});
