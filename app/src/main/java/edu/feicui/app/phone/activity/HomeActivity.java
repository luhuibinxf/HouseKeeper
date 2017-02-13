package edu.feicui.app.phone.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.Date;

import edu.feicui.app.phone.R;
import edu.feicui.app.phone.Util.MemoryManager;
import edu.feicui.app.phone.view.DrawView;
import edu.feicui.app.phone.base.util.BaseActivity;
import edu.feicui.app.phone.base.util.ReadDatabase;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;


public class HomeActivity extends BaseActivity {
    Context mCtx;
    TextView mTv;//方便在那里
    DrawView mDv;
    ImageView mIv;

    MemoryManager mm;


    int number = 0;

    public static final long TWO_SECOND = 2 * 1000;
    long mExitTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mCtx = this;
        setContentView(R.layout.activity_home);
        bar();
        /**
         * 通讯录
         */
        Mobile();
        /**
         * 设置界面
         */
        Setting();
        /**
         * 手机检测
         */
        Detection();
        /**
         * 手机加速
         */
        Acceleration();

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

    /**
     * 通讯录
     */
    void Mobile() {
        mTv = (TextView) findViewById(R.id.phone_telmgr);
        ReadDatabase rd = new ReadDatabase(mCtx);
        rd.write();
        mTv.setOnClickListener(new View.OnClickListener() {//通讯录
            @Override
            public void onClick(View v) {
                startActivity(MobileActivity.class, R.anim.in_down, R.anim.out_down);
            }
        });
    }

    /**
     * 设置界面
     */
    void Setting() {
        mIvR.setOnClickListener(new View.OnClickListener() {//设置
            @Override
            public void onClick(View v) {
                startActivity(SettingActivity.class, R.anim.in_down, R.anim.out_down);
            }
        });
    }

    /**
     * 手机检测
     */
    void Detection() {
        mTv = (TextView) findViewById(R.id.m_phone_detection);
        mTv.setOnClickListener(new View.OnClickListener() {//通讯录
            @Override
            public void onClick(View v) {
                startActivity(DetectionActivity.class, R.anim.in_down, R.anim.out_down);
            }
        });
    }

    /**
     * 手机加速
     */
    void Acceleration() {
        mTv = (TextView) findViewById(R.id.m_home_acceleration);
        mTv.setOnClickListener(new View.OnClickListener() {//通讯录
            @Override
            public void onClick(View v) {
                startActivity(AccelerationActivity.class, R.anim.in_down, R.anim.out_down);
            }
        });
    }

    /**
     * 导航栏
     */
    void bar() {
        initActionBar();
        setmIvL(R.mipmap.ic_launcher1);
        setmTvL("                             ");
        setmTvM("手机管家");
        setmTvR("                             ");
        setmIvR(R.mipmap.ic_child_configs);
    }

    /**
     * 从写系统返回键
     *
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTime) > TWO_SECOND) {
                Toast.makeText(this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
