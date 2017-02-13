package edu.feicui.app.phone.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import edu.feicui.app.phone.R;
import edu.feicui.app.phone.base.util.BaseActivity;

public class SettingActivity extends BaseActivity {
    ImageView mIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        bar();

    }

    /**
     * 导航栏
     */
    void bar() {
        initActionBar();
        setmTvL("                                    ");
        setmTvM("设置");
        setmTvR("                                    ");
        setmIvL(R.mipmap.btn_homeasup_default);
        mIvL = (ImageView) findViewById(R.id.m_bar_left_iv);
        mIvL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                onBackPressed();
                setResult(RESULT_OK, getIntent());
                finish();
            }
        });
    }
}
