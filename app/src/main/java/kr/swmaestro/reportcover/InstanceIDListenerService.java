package kr.swmaestro.reportcover;

import android.content.Intent;

/**
 * Created by JiSoo on 2016-07-20.
 *
 */
public class InstanceIDListenerService extends com.google.android.gms.iid.InstanceIDListenerService {
    private static final String TAG = "InstanceIDListenerService";

    @Override
    public void onTokenRefresh() {
        Intent intent = new Intent(this, RegistrationIntentService.class);
        startService(intent);
    }
}
