package edu.feicui.app.phone.biz;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 2017/1/11.
 */

public class SaveInstance {
    static Context mCt;
    private static SaveInstance saveInstance = null;
    SharedPreferences sp = null;
    SharedPreferences.Editor et;

    SaveInstance() {
        sp = mCt.getSharedPreferences("test", Context.MODE_APPEND);
        et = sp.edit();
    }

    public synchronized static SaveInstance getSaveInstance(Context context) {
        mCt = context;
        if (saveInstance == null) {
            return new SaveInstance();
        }
        return saveInstance;
    }

    public void putString(String key, String value) {
        et.putString("isSaved", "ok");
        et.commit();//承诺???
    }

    public String getStringValue(String key) {
        return sp.getString(key, "unsaved");
    }
}
