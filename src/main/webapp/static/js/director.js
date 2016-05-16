"use strict";
/**
 * Created on 12-May-16.
 *
 * @author Nazar Dub
 */

$('#add-film-birth-day').datepicker({"viewMode": "years", "format": "yyyy-mm-dd"});

//$("#add-director-modal").modal();


$('#add-director-modal').on('hidden.bs.modal', function (e) {
    $('#add-film-birth-day').datepicker('hide');
});


