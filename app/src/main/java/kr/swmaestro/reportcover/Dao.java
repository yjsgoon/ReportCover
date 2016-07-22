package kr.swmaestro.reportcover;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by JiSoo on 2016-07-18.
 *
 */
public class Dao {
    private static final String TAG = "Dao";

    private Context context;
    private SQLiteDatabase database;

    public Dao(Context context) {
        this.context = context;

        database = context.openOrCreateDatabase("LocalDATA.db", SQLiteDatabase.CREATE_IF_NECESSARY, null);

        try {
            String sql = "CREATE TABLE IF NOT EXISTS UnivInformations (ID integer primary key autoincrement,"
                    + "                                        UnivNumber integer UNIQUE not null,"
                    + "                                        UnivName text not null,"
                    + "                                        FileName text not null,"
                    + "                                        ImgName text not null,"
                    + "                                        RefCount integer not null);";
            database.execSQL(sql);
        } catch (Exception e) {
            Log.e(TAG, "CREATE TABLE FAILED! - " + e);
            e.printStackTrace();
        }
    }

    public void insertJsonData(String jsonData) {
        int univNumber;
        String univName;
        String fileName;
        String imgName;
        int refCount;

        FileDownloader fileDownloader = new FileDownloader(context);

        try {
            JSONArray jArr = new JSONArray(jsonData);

            for (int i = 0; i < jArr.length(); i++) {
                JSONObject jObj = jArr.getJSONObject(i);

                univNumber = jObj.getInt("UnivNumber");
                univName = jObj.getString("UnivName");
                fileName = jObj.getString("FileName");
                imgName = jObj.getString("ImgName");
                refCount = jObj.getInt("RefCount");

                Log.i(TAG, "UnivNumber: " + univNumber + " UnivName: " + univName);

                String sql = "INSERT INTO UnivInformations (UnivNumber, UnivName, FileName, ImgName, RefCount)"
                        + " VALUES(" + univNumber + ",'" + univName + "','" + fileName +"','" + imgName + "', " + refCount + ");";

                Log.d(TAG, "Query: " + sql);

                try {
                    database.execSQL(sql);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                fileDownloader.downFile("http://reportcover.pythonanywhere.com/image/" + imgName, imgName);
            }
        } catch (JSONException e) {
            Log.e(TAG, "JSON ERROR! - " + e);
            e.printStackTrace();
        }
    }

    public ArrayList<UnivInformation> getUnivInformationList() {
        ArrayList<UnivInformation> univInformationList = new ArrayList<UnivInformation>();

        int univNumber;
        String univName;
        String fileName;
        String imgName;
        int refCount;

        String sql = "SELECT * From UnivInformations;";
        Cursor cursor = database.rawQuery(sql, null);

        while (cursor.moveToNext()) {
            univNumber = cursor.getInt(1);
            univName = cursor.getString(2);
            fileName = cursor.getString(3);
            imgName = cursor.getString(4);
            refCount = cursor.getInt(5);

            Log.d(TAG, "UnivName: " + univName + ", FileName: " + fileName +
                    ", ImgName: " + imgName + ", RefCount: " + refCount);

            univInformationList.add(new UnivInformation(univNumber, univName, fileName, imgName, refCount));
        }
        cursor.close();

        return univInformationList;
    }
}

