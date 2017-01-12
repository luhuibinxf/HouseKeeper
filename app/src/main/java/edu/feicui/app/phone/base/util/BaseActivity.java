package edu.feicui.app.phone.base.util;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Administrator on 2016/12/28.
 */

public class BaseActivity extends AppCompatActivity {
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
