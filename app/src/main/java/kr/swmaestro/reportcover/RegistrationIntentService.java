package kr.swmaestro.reportcover;

import android.annotation.SuppressLint;
import android.app.IntentService;
import android.app.ProgressDialog;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.io.IOException;

import cz.msebera.android.httpclient.Header;

/**
 * Created by JiSoo on 2016-07-20.
 *
 */
public class RegistrationIntentService extends IntentService {
    private static final String TAG = "RegistrationIntentService";

    public RegistrationIntentService() {
        super(TAG);
    }

    @SuppressLint("LongLogTag")
    @Override
    protected void onHandleIntent(Intent intent) {
        InstanceID instanceID = InstanceID.getInstance(this);
        String token = null;
        try {
            synchronized (TAG) {
                String default_senderId = getString(R.string.gcm_defaultSenderId);
                String scope = GoogleCloudMessaging.INSTANCE_ID_SCOPE;
                token = instanceID.getToken(default_senderId, scope, null);

                Log.i(TAG, "GCM Registration Token: " + token);

                registrationServer(intent.getStringExtra("email"), token);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void registrationServer(String email, String gcmId) {

        ProxyUp.registration(email, gcmId,
                new AsyncHttpResponseHandler() {
                    @SuppressLint("LongLogTag")
                    @Override
                    public void onSuccess(int i, Header[] headers, byte[] bytes) {
                        Log.e(TAG, "up onSuccess:" + i);
                        Toast.makeText(getApplicationContext(), "Connection Success", Toast.LENGTH_SHORT).show();
                    }

                    @SuppressLint("LongLogTag")
                    @Override
                    public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                        Log.e(TAG, "up onFailure:" + i);
                        Toast.makeText(getApplicationContext(), "Connection Failure", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
