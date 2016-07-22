package kr.swmaestro.reportcover;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

/**
 * Created by JiSoo on 2016-07-18.
 *
 */

public class SelectUnivActivity extends AppCompatActivity implements
        AdapterView.OnItemClickListener {
    private static final String TAG = "SelectUnivActivity";

    private ArrayList<UnivInformation> listDataArray = new ArrayList<UnivInformation>();
    private Dao dao;
    private ListView listView;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_univ);

        listView = (ListView) findViewById(R.id.select_univ_listView);
    }

    @Override
    public void onResume() {
        super.onResume();

        refreshData();
    }

    private static AsyncHttpClient client = new AsyncHttpClient();

    public void refreshData() {
        try {
            client.get("http://reportcover.pythonanywhere.com/university", new AsyncHttpResponseHandler() {
                @Override
                public void onSuccess(int i, Header[] headers, byte[] bytes) {
                    String jsonData = new String(bytes);
                    Log.i(TAG, "jsonData: " + jsonData);

                    dao = new Dao(getApplicationContext());
                    dao.insertJsonData(jsonData);

                    listView();
                }

                @Override
                public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Log.i(TAG, "NETWORK ERROR : " + e);
        }
    }

    public void listView() {
        listDataArray = dao.getUnivInformationList();

        UnivListAdapter univListAdapter = new UnivListAdapter(this, R.layout.univ_list_row, listDataArray);
        listView.setAdapter(univListAdapter);
        listView.setOnItemClickListener(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        ProxyUp.putDate(getApplicationContext(), getIntent().getStringExtra("email"),
                getIntent().getStringExtra("lastConnectDate"), new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int i, Header[] headers, byte[] bytes) {
                        Log.e(TAG, "up onSuccess:" + i);
                        Toast.makeText(getApplicationContext(), "Connection Success", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                        Log.e(TAG, "up onFailure:" + i);
                        Toast.makeText(getApplicationContext(), "Connection Failure", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        progressDialog = ProgressDialog.show(SelectUnivActivity.this, "", "connecting...");

        ProxyUp.selectUniv(listDataArray.get(position).getUnivNumber(), getIntent().getStringExtra("email"),
                new AsyncHttpResponseHandler() {
                    @Override
                    public void onSuccess(int i, Header[] headers, byte[] bytes) {
                        Log.e(TAG, "up onSuccess:" + i);
                        progressDialog.cancel();
                        Toast.makeText(getApplicationContext(), "Connection Success", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(int i, Header[] headers, byte[] bytes, Throwable throwable) {
                        Log.e(TAG, "up onFailure:" + i);
                        progressDialog.cancel();
                        Toast.makeText(getApplicationContext(), "Connection Failure", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
