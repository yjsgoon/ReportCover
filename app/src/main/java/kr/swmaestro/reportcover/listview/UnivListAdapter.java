package kr.swmaestro.reportcover.listview;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

import kr.swmaestro.reportcover.R;

/**
 * Created by Yoon-Jisoo on 2016-07-18.
 *
 * 대학 관련 자료를 위한 Custom List View Adapter.
 */
public class UnivListAdapter extends ArrayAdapter<UnivInformation> {
    private static final String TAG = "UnivListAdapter";

    private Context context;
    private int layoutResource;
    private ArrayList<UnivInformation> univData;

    public UnivListAdapter(Context context, int layoutResource,
                           ArrayList<UnivInformation> univListData) {
        super(context, layoutResource, univListData);
        this.context = context;
        this.layoutResource = layoutResource;
        this.univData = univListData;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResource, parent, false);
        }

        TextView textView = (TextView) row.findViewById(R.id.univ_row_textView);
        textView.setText(univData.get(position).getUnivName());

        TextView textView2 = (TextView) row.findViewById(R.id.univ_row_ref);
        textView2.setText("Ref: " + String.valueOf(univData.get(position).getRefCount()));

        ImageView imageView = (ImageView) row.findViewById(R.id.univ_row_imageView);

        String img_path = context.getFilesDir().getPath() + "/" + univData.get(position).getImgName();
        Log.i(TAG, "Image Path: " + img_path);

        File img_load_path = new File(img_path);
        Log.i(TAG, "Image Load Path: " + img_load_path);

        if (img_load_path.exists()) {
            Bitmap bitmap = BitmapFactory.decodeFile(img_path);
            imageView.setImageBitmap(bitmap);
        }

        return row;
    }
}
