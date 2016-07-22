package kr.swmaestro.reportcover;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by JiSoo on 2016-07-18.
 *
 *
 */
public class ProxyUp {
    private final static String TAG = "ProxyUp";

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void signup(String email, AsyncHttpResponseHandler responseHandler) {
        RequestParams params = new RequestParams();
        params.put("email", email);

        client.post("http://reportcover.pythonanywhere.com/account", params, responseHandler);
    }

    public static void registration(String email, String gcmId, AsyncHttpResponseHandler responseHandler) {
        RequestParams params = new RequestParams();
        params.put("email", email);
        params.put("gcmId", gcmId);

        client.post("http://reportcover.pythonanywhere.com/gcm", params, responseHandler);
    }

    public static void selectUniv(int univNumber, String email,
                                     AsyncHttpResponseHandler responseHandler) {
        RequestParams params = new RequestParams();
        params.put("univNumber", univNumber);
        params.put("email", email);

        Log.d(TAG, "UnivNumber: " + univNumber + ", Email: " + email);

        client.post("http://reportcover.pythonanywhere.com/university", params, responseHandler);
    }

    public static void putDate(Context context, String email, String lastConnectDate,
                               AsyncHttpResponseHandler responseHandler) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo packageInfo = pm.getPackageInfo("kr.swmaestro.reportcover", PackageManager.GET_PERMISSIONS);

            String installedDate = dateFormat.format(new Date(packageInfo.firstInstallTime));
            String disconnectDate = dateFormat.format(Calendar.getInstance().getTime());

            Log.i(TAG, "Installed: " + installedDate + ", LastConnect: " + lastConnectDate + ", DisConnect: " + disconnectDate);

            RequestParams params = new RequestParams();
            params.put("email", email);
            params.put("insDate", installedDate);
            params.put("lastDate", lastConnectDate);
            params.put("disDate", disconnectDate);

            client.put("http://reportcover.pythonanywhere.com/account", params, responseHandler);
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "Error: " + e);
        }
    }
}