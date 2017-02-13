package edu.feicui.app.phone.Util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;

import edu.feicui.app.phone.R;

/**
 * Created by Administrator on 2017/1/5.
 */

public class DialogUtil {
    static Context mCt;

    public static void alertDialog(Context context, DialogInterface.OnClickListener okListener, DialogInterface.OnClickListener cancelListener) {
        mCt = context;
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("是否拨打电话")
                .setIcon(R.mipmap.ic_launcher)
                .setPositiveButton("确定", okListener)
                .setNegativeButton("取消", cancelListener)
                .show();
    }
    public static void customAlertDialog(Context context, View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(view)
                .show();
    }
}
