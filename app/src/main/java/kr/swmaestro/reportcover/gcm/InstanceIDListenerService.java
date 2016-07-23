package kr.swmaestro.reportcover.gcm;

import android.content.Intent;

/**
 * Created by Yoon-Jisoo on 2016-07-20.
 *
 * GCM 서비스가 InstanceID를 획득할 때 실행되는 서비스.
 */
public class InstanceIDListenerService extends com.google.android.gms.iid.InstanceIDListenerService {
    private static final String TAG = "InstanceIDListenerService";

    @Override
    public void onTokenRefresh() {
        Intent intent = new Intent(this, RegistrationIntentService.class);
        startService(intent);
    }
}
