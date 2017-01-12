package edu.feicui.app.phone.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import edu.feicui.app.phone.R;
import edu.feicui.app.phone.Util.DialogUtil;
import edu.feicui.app.phone.biz.PhoneDatabase;

public class PhoneMobileActivity extends AppCompatActivity {
    Context context;
    ListView mLv;
    TextView mTv;
    List<Map<String, String>> list;
    PhoneDatabase pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_mobile);
        mLv = (ListView) findViewById(R.id.phone_lv);
        pd = new PhoneDatabase(context);
        Intent intent = getIntent();
        String str = intent.getStringExtra("ok");
        list = pd.queryList(Integer.parseInt(str));
        String[] from = {"name", "number"};
        int[] to = new int[]{R.id.phone_tv_lv1, R.id.phone_tv_lv2};
        SimpleAdapter adapter = new SimpleAdapter(this, list, R.layout.activity_phone_mobile_lv, from, to);
        mLv.setAdapter(adapter);
        mTv = (TextView) findViewById(R.id.phone_tv_lv2);
        mLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
                DialogUtil.alertDialog(context, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_CALL);
                        intent.setData(Uri.parse("tel:" + list.get(position).get("number")));
                        startActivity(intent);
                    }
                }, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        setResult(RESULT_OK, getIntent());//不要返回，取消对话框就好了
                        finish();
                    }
                });

            }
        });
    }
}
