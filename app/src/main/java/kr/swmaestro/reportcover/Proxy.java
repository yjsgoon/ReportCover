package kr.swmaestro.reportcover;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by JiSoo on 2016-07-18.
 *
 *
 */
public class Proxy {
    private static final String TAG = "Proxy";

    public String getJSON() {

        try {
            URL url = new URL("http://reportcover.pythonanywhere.com/loadUniv");

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // 서버 접속시의 Time out(ms)
            conn.setConnectTimeout(10 * 1000);
            // Read시의 Time out(ms)
            conn.setConnectTimeout(10 * 1000);

            conn.setRequestMethod("GET");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Accept-Charset", "UTF-8");
            conn.setRequestProperty("Cache-Control", "no-cache");
            conn.setRequestProperty("Accept-Charset", "application/json");
            conn.setDoInput(true);

            conn.connect();

            int status = conn.getResponseCode();
            Log.i(TAG, "ProxyResponseCode: " + status);

            switch(status) {
                case 200:
                case 201:
                    BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    StringBuilder sb = new StringBuilder();
                    String line;
                    while((line = br.readLine()) != null) {
                        sb.append(line + "\n");
                    }
                    br.close();
                    return sb.toString();
            }
        } catch(Exception e) {
            e.printStackTrace();
            Log.i(TAG, "NETWORK ERROR : " + e);
        }
        return null;
    }
}

