package kr.swmaestro.reportcover;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

/**
 * Created by JiSoo on 2016-07-18.
 *
 *
 */
public class ProxyUp {
    private final static String TAG = "ProxyUp";

    private static AsyncHttpClient client = new AsyncHttpClient();

    public static void registration(String email, String gcmId, AsyncHttpResponseHandler responseHandler) {
        RequestParams params = new RequestParams();
        params.put("email", email);
        params.put("gcmId", gcmId);

        client.post("http://reportcover.pythonanywhere.com/registration", params, responseHandler);
    }

    public static void selectUniv(int univNumber, String email,
                                     AsyncHttpResponseHandler responseHandler) {
        RequestParams params = new RequestParams();
        params.put("univNumber", univNumber);
        params.put("email", email);

        Log.d(TAG, "UnivNumber: " + univNumber + ", Email: " + email);

        client.post("http://reportcover.pythonanywhere.com/selectUniv", params, responseHandler);
    }
}