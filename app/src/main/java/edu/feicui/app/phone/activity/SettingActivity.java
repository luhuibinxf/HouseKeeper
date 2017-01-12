package edu.feicui.app.phone.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import edu.feicui.app.phone.R;

public class SettingActivity extends AppCompatActivity {
    ImageView mIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        mIv = (ImageView) findViewById(R.id.m_right);
        mIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK, getIntent());
                finish();
            }
        });
    }
}
