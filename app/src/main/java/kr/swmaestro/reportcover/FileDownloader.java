package kr.swmaestro.reportcover;

import android.content.Context;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.FileAsyncHttpResponseHandler;

import java.io.File;

import cz.msebera.android.httpclient.Header;

/**
 * Created by JiSoo on 2016-07-18.
 *
 */
public class FileDownloader {
    private static final String TAG = "FileDownloader";

    private final Context context;

    public FileDownloader(Context context) {
        this.context = context;
    }

    private static AsyncHttpClient client = new AsyncHttpClient();

    public void downFile(String fileUrl, String fileName) {
        final File filePath = new File(context.getFilesDir().getPath() + "/" + fileName);

        if(!filePath.exists()) {
            client.get(fileUrl, new FileAsyncHttpResponseHandler(context) {
                @Override
                public void onFailure(int statusCode, Header[] headers, Throwable throwable, File file) {

                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, File file) {
                    Log.i(TAG, "responsePath: " + file.getAbsolutePath());
                    Log.i(TAG, "originalPath: " + filePath.getAbsolutePath());
                    file.renameTo(filePath);
                }
            });
        }
    }
}
