package com.jguix.cordova;

import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaInterface;
import android.util.Log;
import android.provider.Settings;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
* SimpleToast Plugin
* Based on tutorial at http://www.mat-d.com/site/tutorial-creating-a-cordova-phonegap-plugin-for-android-app/
*/
public class SimpleToast extends CordovaPlugin {

	public static final String TAG = "SimpleToast";
	private static final String ACTION_SHOW_EVENT = "show";

	/**
	* Constructor.
	*/
	public SimpleToast() {
	}

	/**
	* Sets the context of the Command. This can then be used to do things like
	* get file paths associated with the Activity.
	*
	* @param cordova The context of the main Activity.
	* @param webView The CordovaWebView Cordova is running in.
	*/
	public void initialize(CordovaInterface cordova, CordovaWebView webView) {
		super.initialize(cordova, webView);
		Log.v(TAG,"Init SimpleToast");
	}

	public boolean execute(final String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		if (ACTION_SHOW_EVENT.equals(action)) {
			final int duration = Toast.LENGTH_SHORT;
			final JSONObject options = args.getJSONObject(0);
			final String message = options.getString("message");

			// Shows a toast
			Log.v(TAG,"SimpleToast received: " + action);

			cordova.getActivity().runOnUiThread(new Runnable() {
				public void run() {
					Toast toast = Toast.makeText(cordova.getActivity().getApplicationContext(), message, duration);
					toast.show();
				}
			});

			return true;
		} else {
			callbackContext.error("toast." + action + " is not a supported function. Did you mean '" + ACTION_SHOW_EVENT + "'?");
			return false;
		}
	}

}