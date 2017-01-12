package edu.feicui.app.phone.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

import edu.feicui.app.phone.R;
import edu.feicui.app.phone.Util.MemoryManager;
import edu.feicui.app.phone.view.DrawView;

public class SoftwareActivity extends AppCompatActivity {
    Context mCtt;
    DrawView mDv;
    List list;
    MemoryManager mm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mCtt = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_software);
        mDv = (DrawView) findViewById(R.id.m_dv1_s);
        mm = new MemoryManager(mCtt);
        list = mm.TotalAllStorage();
        float Angle = (float) list.get(0);
        mDv.getParamsWithAnim(Angle, 360 - Angle);
    }
}
