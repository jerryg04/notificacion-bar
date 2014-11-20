package com.notificacionbar.cordova.plugins.notificacionbar;

import java.io.File;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.azabache.movil.R;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;


public class statusNotificacion extends CordovaPlugin {
@SuppressWarnings("deprecation")
public void makeNotification(String uriPath,String ticker,String contentTitle,String contentText,String mimeType,String summaryText){
		String notiService = Context.NOTIFICATION_SERVICE;
		Context context = cordova.getActivity().getApplicationContext();
		NotificationManager mNotificacManager = (NotificationManager) context.getSystemService(notiService);
		Notification notification = new Notification(android.R.drawable.ic_menu_gallery,"Imagen descargada",System.currentTimeMillis());
		notification.flags = Notification.FLAG_AUTO_CANCEL;
		notification.defaults = Notification.DEFAULT_VIBRATE;
		notification.sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
		Intent intent = new Intent(Intent.ACTION_VIEW);
		Uri data = Uri.fromFile( new File(uriPath));
		intent.setDataAndType(data,mimeType);
		PendingIntent contentIntent = PendingIntent.getActivity(context, 0, intent,  PendingIntent.FLAG_UPDATE_CURRENT);
		notification.setLatestEventInfo(context, contentTitle, contentText, contentIntent);
		mNotificacManager.notify(1, notification);
	}
	@SuppressWarnings("deprecation")
	public void pdfNotificacion(String uriPath,String ticker,String contentTitle,String contentText,String mimeType,String summaryText){
		String notiService = Context.NOTIFICATION_SERVICE;
		Context context = cordova.getActivity().getApplicationContext();
		NotificationManager mNotificacManager = (NotificationManager) context.getSystemService(notiService);
		Notification notification = new Notification(R.drawable.ic_pdf,"Cotizacion",System.currentTimeMillis());
		notification.flags = Notification.FLAG_AUTO_CANCEL;
		notification.defaults = Notification.DEFAULT_VIBRATE;
		notification.sound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
		Intent intent = new Intent(Intent.ACTION_VIEW);
		Uri data = Uri.fromFile( new File(uriPath));
		intent.setDataAndType(data,mimeType);
		PendingIntent contentIntent = PendingIntent.getActivity(context, 0, intent,  PendingIntent.FLAG_UPDATE_CURRENT);
		notification.setLatestEventInfo(context, contentTitle, contentText, contentIntent);
		mNotificacManager.notify(2, notification);
	}
	@Override
	public boolean execute(String action,JSONArray args,CallbackContext callbackContext){
		try {
			if(action.equals("imagen")){
				JSONObject jsonObj = args.getJSONObject(0);
				makeNotification(jsonObj.getString("uri"),jsonObj.getString("ticker"),jsonObj.getString("contentTitle"),jsonObj.getString("contentText"),
						jsonObj.getString("mimeType"),jsonObj.getString("summaryText"));
				callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK));
				return true;
			}
			if(action.equals("pdf")){
				JSONObject jsonObj = args.getJSONObject(0);
				pdfNotificacion(jsonObj.getString("uri"),jsonObj.getString("ticker"),jsonObj.getString("contentTitle"),jsonObj.getString("contentText"),
						jsonObj.getString("mimeType"),jsonObj.getString("summaryText"));
				callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK));
				return true;
			}
			
		} catch (JSONException e) {
			// TODO: handle exception
			Log.e("PhoneGapLog", "Notificacion Plugin: Error: " + PluginResult.Status.JSON_EXCEPTION);
			e.printStackTrace();
			callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.JSON_EXCEPTION));
			return false;
		}
		return false;
	}
}
