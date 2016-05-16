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

var alert_item = $('.bottom-right');
var alert_text = alert_item.attr('alert-message');
if (alert_text !== '') {
    alert_item.notify({
        message: {
            text: alert_text
        },
        type: 'blackgloss',
        closable: false
    }).show();
}

