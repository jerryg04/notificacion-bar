var Notification = function () {

};

Notification.prototype = {
    /**
     * Open the Android share dialog.
     */
	mostrar: function (message, win, fail) {
		cordova.exec(win, fail, 'statusNotificacion', 'mostrar', [{uri: message.uri,ticker: message.ticker, contentTitle:message.contentTitle,contentText:message.contentText,mimeType:message.mimeType,summaryText:message.summaryText}]);
	}
		   
};

var notificacion = new Notification();

module.exports = notificacion;
