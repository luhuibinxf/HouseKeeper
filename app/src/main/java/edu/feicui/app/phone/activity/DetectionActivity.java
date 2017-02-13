package edu.feicui.app.phone.activity;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

import edu.feicui.app.phone.R;
import edu.feicui.app.phone.Util.DialogUtil;
import edu.feicui.app.phone.Util.SystemManager;
import edu.feicui.app.phone.base.util.BaseActivity;

public class DetectionActivity extends BaseActivity {
    TextView mTv;
    ProgressBar mPb;

    SystemManager systemManager;

    Changed changed;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detection);

        bar();
        mPb = (ProgressBar) findViewById(R.id.m_detection_pb);
        mTv = (TextView) findViewById(R.id.m_detection_pb_tv);

        Systeminfo();
        /**
         * 动态注册不好（为什么）
         */
        changed = new Changed();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
//        intentFilter.addAction(Intent.ACTION_POWER_CONNECTED);
//        intentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED);
        registerReceiver(changed, intentFilter);

    }

    /**
     * 导航栏
     */
    void bar() {
        initActionBar();
        setmIvL(R.mipmap.btn_homeasup_default);
        setmTvL("                                ");
        setmTvM("手机检测");
        setmTvR("                                ");
        mIvL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
//                setResult(RESULT_OK, getIntent());
                finish();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    void Systeminfo() {
        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo info = new ActivityManager.MemoryInfo();
        activityManager.getMemoryInfo(info);
        systemManager = new SystemManager(this);


        mTv = (TextView) findViewById(R.id.m_detection_tv_a_0);
        mTv.setText("设备名称：" + systemManager.getPhoneModelName());
        mTv = (TextView) findViewById(R.id.m_detection_tv_b_0);
        mTv.setText("系统版本：" + systemManager.getPhoneSystemVersion());

        mTv = (TextView) findViewById(R.id.m_detection_tv_a_1);
        mTv.setText("系统总运行：" + (info.totalMem >> 20) + "M");
        mTv = (TextView) findViewById(R.id.m_detection_tv_b_1);
        mTv.setText("系统剩余内存:" + (info.availMem >> 20) + "M");

        mTv = (TextView) findViewById(R.id.m_detection_tv_a_2);
        mTv.setText("CPU名称：" + systemManager.getPhoneCPUName());
        mTv = (TextView) findViewById(R.id.m_detection_tv_b_2);
        mTv.setText("COU数量：" + systemManager.getPhoneCPUNumber());

        mTv = (TextView) findViewById(R.id.m_detection_tv_a_3);
        mTv.setText("手机分辨率：" + systemManager.getResolution());
        mTv = (TextView) findViewById(R.id.m_detection_tv_b_3);
//        mTv.setText("照相机分辨率" + systemManager.getMaxPhoneSize());

        mTv = (TextView) findViewById(R.id.m_detection_tv_a_4);
        mTv.setText("基带版本：" + systemManager.getBaseband_Ver());
        mTv = (TextView) findViewById(R.id.m_detection_tv_b_4);
        if (systemManager.isRoot()) {
            mTv.setText("是否获取Root权限:是");
        } else {
            mTv.setText("是否获取Root权限:否");
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(changed);
    }

    /**
     * 没完善
     */
    public class Changed extends BroadcastReceiver {

        @Override
        public void onReceive(final Context context, Intent intent) {
            final int level = intent.getIntExtra("level", 0);
            final double BatteryT = intent.getIntExtra("temperature", 0);
            final int BatteryV = intent.getIntExtra("voltage", 0);

            int status = intent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);

            boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING;
            boolean isFull = status == BatteryManager.BATTERY_STATUS_FULL;
//            int chargePlug = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, -1);
//            boolean usbCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_USB;
//            boolean acCharge = chargePlug == BatteryManager.BATTERY_PLUGGED_AC;

            if (isCharging) {
                final Timer timer = new Timer();
                final TimerTask timerTask = new TimerTask() {
                    int prolength = level;

                    @Override
                    public void run() {
                        if (prolength <= 100) {
                            prolength = mPb.getProgress() + 1;
                            mPb.setProgress(prolength);
                        }
                        if (prolength == 100) {
                            prolength = level;
                            mPb.setProgress(prolength);
                        }
                    }
                };
                timer.schedule(timerTask, 10, 500);
            } else if (isFull) {
                mPb.setProgress(level);
            }

            mTv = (TextView) findViewById(R.id.m_detection_pb_tv);
            mTv.setText(level + "%");
//
            mPb.setProgress(level);
            mPb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    v = LayoutInflater.from(context).inflate(R.layout.activity_cus_dialog, null);
                    DialogUtil.customAlertDialog(context, v);

                    mTv = (TextView) v.findViewById(R.id.m_detection_dialog_r);
                    mTv.setText(BatteryV + "mV");
                    mTv = (TextView) v.findViewById(R.id.m_detection_dialog_1_r);
                    mTv.setText((BatteryT * 0.1) + "℃");
                }
            });
        }
    }
}
