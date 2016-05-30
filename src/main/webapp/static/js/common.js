"use strict";

var alert_item = $('.bottom-right');
var alert_text = alert_item.attr('alert-message');
if (alert_text && alert_text !== '') {
    alert_item.notify({
        message: {
            text: alert_text
        },
        type: 'blackgloss',
        closable: false
    }).show();
}