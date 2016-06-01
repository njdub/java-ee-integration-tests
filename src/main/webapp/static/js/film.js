"use strict";

var currentFlip = null;

function updateFilm(id) {
    if (currentFlip != null)
        currentFlip.close();
    var front = document.getElementById('film-view-' + id);
    var back_content = '<form method="post" action="/page/films.jsf" enctype="application/x-www-form-urlencoded">' +
        document.getElementById('update-film-form-' + id).innerHTML +
        '</form>';
    currentFlip = flippant.flip(front, back_content);
}

$(document).ready(function(){
    //$('.notifications').after('<form action="/about" method="post"><button type="submit">Ivab</button></form>');
    //$('.notifications').after('<form action="/about" method="post"><button type="submit">Ivab</button></form>');
});