package edu.feicui.app.phone.activity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;

import edu.feicui.app.phone.R;
import edu.feicui.app.phone.Util.MemoryManager;
import edu.feicui.app.phone.view.DrawView;
import edu.feicui.app.phone.base.util.BaseActivity;
import edu.feicui.app.phone.base.util.ReadDatabase;


public class HomeActivity extends BaseActivity {
    Context mCtx;
    TextView mTv;//方便在那里
    DrawView mDv;
    ImageView mIv;

    MemoryManager mm;

    int number=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCtx = this;
        setContentView(R.layout.activity_home);
        mTv = (TextView) findViewById(R.id.phone_telmgr);
        ReadDatabase rd = new ReadDatabase(mCtx);
        rd.write();
        mTv.setOnClickListener(new View.OnClickListener() {//通讯录
            @Override
            public void onClick(View v) {
                startActivity(MobileActivity.class);
            }
        });

        mIv = (ImageView) findViewById(R.id.home_set);
        mIv.setOnClickListener(new View.OnClickListener() {//设置
            @Override
            public void onClick(View v) {
                startActivity(SettingActivity.class);
            }
        });

        mm = new MemoryManager(mCtx);
        long AvailMem = mm.getPhoneFreeRamMemory(mCtx);
        long TotalMem = mm.getPhoneTotalramMemory();

        float Percentage = (float) (TotalMem - AvailMem) / (float) TotalMem * 100;
        DecimalFormat df = new DecimalFormat("0");
        String percentage = df.format(Percentage) + "%";

        float angle = Percentage * 3.6f;
        final float Angle = Float.parseFloat(df.format(angle));

        mTv = (TextView) findViewById(R.id.m_home_dv);//只能靠id区别
        mTv.setText(percentage);
        mDv = (DrawView) findViewById(R.id.home_dv);

        mDv.setParamsWithAnim(Angle);
        mIv = (ImageView) findViewById(R.id.m_home_dv_iv);
        mIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                number = mDv.count;
                if (number == 0) {
                    mm.killALLProcesses();
                    mDv.setParamsWithAnim(Angle);
                    number = 1;
                }
            }
        });

        mTv = (TextView) findViewById(R.id.m_home_softmgr);
        mTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(SoftwareActivity.class);
            }
        });

    }


}
