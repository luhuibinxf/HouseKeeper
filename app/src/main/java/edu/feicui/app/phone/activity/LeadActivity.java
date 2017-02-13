package edu.feicui.app.phone.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import edu.feicui.app.phone.R;
import edu.feicui.app.phone.adapter.MCustomPagerAdapter;
import edu.feicui.app.phone.biz.SaveInstance;
import edu.feicui.app.phone.service.LeadService;

public class LeadActivity extends AppCompatActivity {

    Context context;

    ViewPager mVp;
    ImageView mIv;
    TextView mTv;
    PagerTabStrip mPts;

    ArrayList<View> views;
    ArrayList<String> Title;
    MCustomPagerAdapter adapter;
    int[] to = {R.id.lead_IV_1, R.id.lead_IV_2, R.id.lead_IV_3};

    SaveInstance saveInstance;

    Intent service = new Intent();
    LeadService Lservice;
    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            Lservice = ((LeadService.MyBinder) service).getService();
            Lservice.play();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Save();


        context = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lead);

        mTv = (TextView) findViewById(R.id.lead_VP_tv);
        mTv.setVisibility(View.INVISIBLE);

        mVp = (ViewPager) findViewById(R.id.lead_Vp);
        mVp.setVisibility(View.VISIBLE);

        adapter = new MCustomPagerAdapter(context);
        views = adapter.initViews();
        adapter.setViews(views);
        mVp.setAdapter(adapter);//为什么每次都少这句化呢


        mVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                for (int object : to) {
                    if (object == to[position]) {
                        mIv = (ImageView) findViewById(object);
                        mIv.setImageResource(R.mipmap.adware_style_selected);
                    } else {
                        mIv = (ImageView) findViewById(object);
                        mIv.setImageResource(R.mipmap.adware_style_default);
                    }
                }
            }

            @Override
            public void onPageSelected(int position) {
                if (position == 2) {
                    mTv.setVisibility(View.VISIBLE);
                    mTv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            unbindService(conn);

                            SaveInstance.getSaveInstance(context).putString("isSaved", "ok");//没有这句话的区别
                            Intent intent = new Intent(LeadActivity.this, LogoActivity.class);
                            startActivity(intent);
                            finish();//注意这句话的作用
                        }
                    });
                } else {
                    mTv.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        mPts = (PagerTabStrip) findViewById(R.id.lead_pts);
        Title = adapter.initTitles();
        mPts.setTextColor(getResources().getColor(R.color.black));
        mPts.setTextSize(TypedValue.COMPLEX_UNIT_SP, 15);
    }


    void Save() {
        saveInstance = SaveInstance.getSaveInstance(this);//需注意这？？？？？
        String isSaved = saveInstance.getStringValue("isSaved");
        if (isSaved.equals("ok")) {
            Intent intent = new Intent(LeadActivity.this, LogoActivity.class);
            startActivity(intent);
            finish();//注意这句话的作用

        } else {

            service.setClass(LeadActivity.this, LeadService.class);
            bindService(service, conn, BIND_AUTO_CREATE);
        }
    }
}
