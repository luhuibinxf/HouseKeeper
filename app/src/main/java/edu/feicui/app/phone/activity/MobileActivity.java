package edu.feicui.app.phone.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import edu.feicui.app.phone.R;
import edu.feicui.app.phone.base.util.BaseActivity;
import edu.feicui.app.phone.biz.PhoneDatabase;

public class MobileActivity extends BaseActivity {
    Context context;
    GridView mGv;
    List<Map<String, String>> list;
    PhoneDatabase pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_mobile);

        Bar();

        mGv = (GridView) findViewById(R.id.phone_gv);
        pd = new PhoneDatabase(context);
        list = pd.queryTable();

        String[] from = {"name"};

        int[] to = new int[]{R.id.phone_gv_tv};
        SimpleAdapter adapter = new SimpleAdapter(this, list, R.layout.activity_mobile_grid, from, to);
        mGv.setAdapter(adapter);
        mGv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent();
                intent.putExtra("ok", "" + position);
//                Log.i("@@@@@", position + "");
                intent.setClass(MobileActivity.this, PhoneMobileActivity.class);
                startActivity(intent);
            }
        });
    }

    public void Bar() {

        initActionBar();
        setmTvL("                                    ");
        setmTvM("  通讯录");
        setmTvR("                                    ");
        setmIvL(R.mipmap.btn_homeasup_default);
        mIvL = (ImageView) findViewById(R.id.m_bar_left_iv);
        mIvL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
//                setResult(RESULT_OK, getIntent());
                finish();
            }
        });
    }
}
