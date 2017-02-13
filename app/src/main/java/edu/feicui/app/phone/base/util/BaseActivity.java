package edu.feicui.app.phone.base.util;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import edu.feicui.app.phone.R;

/**
 * Created by Administrator on 2016/12/28.
 */

public class BaseActivity extends AppCompatActivity {
    public ImageView mIvL, mIvR;
    public TextView mTvL, mTvM, mTvR;

    View mView;
    ActionBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar);
        mView = getLayoutInflater().inflate(R.layout.activity_bar, null);//要给谁的，谁的布局

        mIvL = (ImageView) mView.findViewById(R.id.m_bar_left_iv);//谁的布局给谁

        mIvR = (ImageView) mView.findViewById(R.id.m_bar_reight_iv);

        mTvL = (TextView) mView.findViewById(R.id.m_bar_left_tv);

        mTvM = (TextView) mView.findViewById(R.id.m_bar_middle_tv);

//        mTvR = (TextView) findViewById(R.id.m_bar_reight_tv);//上下的区别  下面这样写没使用过

        mTvR = (TextView) mView.findViewById(R.id.m_bar_reight_tv);
    }

    public void initActionBar() {
        bar = getSupportActionBar();
        bar.setDisplayShowCustomEnabled(true);
        bar.setCustomView(mView);
    }

    public void setmIvL(int drawable) {
        mIvL.setImageResource(drawable);
    }

    public void setmIvR(int drawable) {
        mIvR.setImageResource(drawable);
    }

    public void setmTvL(String LTitle) {
        mTvL.setText(LTitle);
    }

    public void setmTvM(String MTitle) {
        mTvM.setText(MTitle);
    }

    public void setmTvR(String RTitle) {
        mTvR.setText(RTitle);
    }

    protected void startActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }

    protected void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent(this, cls);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    protected void startActivity(Class<?> cls, int inAnimID, int outAnimID) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
        overridePendingTransition(inAnimID, outAnimID);
    }

    protected void startActivity(Class<?> cls, int inAnimID, int outAnimID, Bundle bundle) {
        Intent intent = new Intent(this, cls);
        intent.putExtras(bundle);
        startActivity(intent);
        overridePendingTransition(inAnimID, outAnimID);
    }

    public void finish() {
        super.finish();
    }
}
