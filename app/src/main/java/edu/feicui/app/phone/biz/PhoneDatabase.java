package edu.feicui.app.phone.biz;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/1/3.
 */

public class PhoneDatabase {
    Context context;
    SQLiteDatabase sd;
    String str;

    public PhoneDatabase(Context mCt) {
        context = mCt;
    }

    public List<Map<String, String>> queryTable() {
        List<Map<String, String>> table = new ArrayList<Map<String, String>>();
        sd = SQLiteDatabase.openOrCreateDatabase(context.getFilesDir() + File.separator + "com.db", null);
        str = "select * from classlist;";
        Cursor cursor = sd.rawQuery(str, null);//null是一个数组型的参数
        while (cursor.moveToNext()) {
            Map<String, String> tables = new HashMap<String, String>();
            String name = cursor.getString(0);
            tables.put("name", name);
//            Log.i("@@@@", name + "");
            table.add(tables);
        }
        return table;
    }

    public List<Map<String, String>> queryList(int position) {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        sd = SQLiteDatabase.openOrCreateDatabase(context.getFilesDir() + File.separator + "com.db", null);
        str = "select * from table" + (position + 1) + ";";
        Cursor cursor = sd.rawQuery(str, null);//null是一个数组型的参数
        while (cursor.moveToNext()) {
            Map<String, String> lists = new HashMap<String, String>();
            String number = cursor.getString(1);
            String name = cursor.getString(2);
            lists.put("number", number);
            lists.put("name", name);
            list.add(lists);
        }
        return list;
    }
}
