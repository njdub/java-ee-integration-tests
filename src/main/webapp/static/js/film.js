"use strict";

var currentFlip = null;

function updateFilm(id) {
    if (currentFlip != null) {
        currentFlip.close();
        //$('.fom-copy').remove();
    }
    var front = document.getElementById('film-view-' + id);

    var source = $('#film-view-' + id);
    var sourceSpan = source.find('span');

    var title = source.find('h2').text();
    var duration = sourceSpan.eq(0).text();
    var year = sourceSpan.eq(1).text();

    var directorName = sourceSpan.eq(2).text();

    var description = source.find('p:last').text();


    //var formData = $('#update-film-form').clone();
    var formData = $('#update-film-form');

    formData.find('#update-film-form\\:update-film-id').attr('value', id);
    formData.find('#update-film-form\\:update-film-title').attr('value', title);
    formData.find('#update-film-form\\:update-film-year').attr('value', year);
    formData.find('#update-film-form\\:update-film-duration').attr('value', duration);

    formData.find('#update-film-form\\:update-film-director-id option:contains("' + directorName + '")')
        .attr('selected', "selected");

    formData.find('#update-film-form\\:update-film-description').text(description);


    //$('#update-film-form textarea').text('text text');


    var back_content = '<form method="post" style="form-copy" action="/page/films.jsf" enctype="application/x-www-form-urlencoded">' +
        formData.html() +
        '</form>';
    currentFlip = flippant.flip(front, back_content);
}

$(document).ready(function () {
    //NOP
});