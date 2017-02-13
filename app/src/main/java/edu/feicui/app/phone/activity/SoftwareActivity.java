package edu.feicui.app.phone.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import edu.feicui.app.phone.R;
import edu.feicui.app.phone.Util.MemoryManager;
import edu.feicui.app.phone.base.util.BaseActivity;
import edu.feicui.app.phone.view.DrawView;

public class SoftwareActivity extends BaseActivity {

    Context mCtt;

    ProgressBar mPb;
    TextView mTv;
    DrawView mDv;
    RelativeLayout mRl;

    List list;


    MemoryManager mm;

    int Max = 0;
    int number = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mCtt = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_software);

        mRl = (RelativeLayout) findViewById(R.id.all_app);
        mRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SoftwareActivity.this, SoftmgrAppshowActivity.class);
                intent.putExtra("ok", "所有应用");
                startActivity(intent);
            }
        });

        mRl = (RelativeLayout) findViewById(R.id.user_app);
        mRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SoftwareActivity.this, SoftmgrAppshowActivity.class);
                intent.putExtra("ok", "用户应用");
                startActivity(intent);
            }
        });

        mRl = (RelativeLayout) findViewById(R.id.system_app);
        mRl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SoftwareActivity.this, SoftmgrAppshowActivity.class);
                intent.putExtra("ok", "系统应用");
                startActivity(intent);
            }
        });

        Bar();

        Draw();

        DataDirectory();

        ExternalStorageDirectory();

    }

    void Bar() {
        initActionBar();
        setmTvL("                                    ");
        setmTvM("软件管理");
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

    public void Draw() {

        mm = new MemoryManager(mCtt);
        list = mm.TotalAllStorage();
        float Angle = (float) list.get(0);
        mDv = (DrawView) findViewById(R.id.m_dv1_s);
        mDv.getParamsWithAnim(Angle, 360 - Angle);
    }

    public void DataDirectory() {
        mm = new MemoryManager(mCtt);
        list = mm.getDataDirectory();

        mPb = (ProgressBar) findViewById(R.id.app_storage_pb);
        Max = (int) list.get(0);
        mPb.setMax(Max);

        number = (int) list.get(2);
        mPb.setProgress(number);

        mTv = (TextView) findViewById(R.id.app_storage_tv);
        mTv.setText("手机内置空间");

        mTv = (TextView) findViewById(R.id.app_storage_tv1);
        mTv.setText("可用" + ((Integer) list.get(1) + "MB/总共" + (Integer) list.get(0)) + "MB");

    }

    public void ExternalStorageDirectory() {
        mm = new MemoryManager(mCtt);
        list = mm.getExternalStorageDirectory();

        mPb = (ProgressBar) findViewById(R.id.storage_pb);
        Max = (int) list.get(0);
        mPb.setMax(Max);

        number = (int) list.get(2);
        mPb.setProgress(number);

        mTv = (TextView) findViewById(R.id.storage_tv);
        mTv.setText("外置存储空间");

        mTv = (TextView) findViewById(R.id.storage_tv1);
        mTv.setText("可用" + ((Integer) list.get(1) + "MB/总共" + (Integer) list.get(0)) + "MB");

    }
}
