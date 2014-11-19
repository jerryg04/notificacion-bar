package com.notificacionbar.cordova.plugins.notificacionbar;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.util.Log;

public class statusNotificacion extends CordovaPlugin {
public void makeNotification(String uriPath,String ticker,String contentTitle,String contentText,String mimeType,String summaryText){
		String notiService = Context.NOTIFICATION_SERVICE;
		Context context = cordova.getActivity().getApplicationContext();
		NotificationManager mNotificacManager = (NotificationManager) context.getSystemService(notiService);
		@SuppressWarnings("deprecation")
		Notification notification = new Notification(android.R.drawable.ic_menu_gallery,"Hola como estas jerry",System.currentTimeMillis());
		mNotificacManager.notify(1, notification);
	}
	@Override
	public boolean execute(String action,JSONArray args,CallbackContext callbackContext){
		try {
			JSONObject jsonObj = args.getJSONObject(0);
			makeNotification(jsonObj.getString("uri"),jsonObj.getString("ticker"),jsonObj.getString("contentTitle"),jsonObj.getString("contentText"),
					jsonObj.getString("mimeType"),jsonObj.getString("summaryText"));
			callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK));
			return true;
		} catch (JSONException e) {
			// TODO: handle exception
			Log.e("PhoneGapLog", "Notificacion Plugin: Error: " + PluginResult.Status.JSON_EXCEPTION);
			e.printStackTrace();
			callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.JSON_EXCEPTION));
			return false;
		}
	}
}
