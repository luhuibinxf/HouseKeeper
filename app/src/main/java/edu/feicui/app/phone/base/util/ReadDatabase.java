package edu.feicui.app.phone.base.util;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by Administrator on 2017/1/3.
 */

public class ReadDatabase {
    Context context;

    public ReadDatabase(Context ctx) {
        context = ctx;
    }

    public void write() {
        InputStream in = null;
        OutputStream out = null;
        try {
            AssetManager assets = context.getAssets();
            in = assets.open("commonnum.db");
            out = context.openFileOutput("com.db", Context.MODE_PRIVATE);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedInputStream bis = new BufferedInputStream(in);
        BufferedOutputStream bos = new BufferedOutputStream(out);
        byte[] b = new byte[1024];
        try {
            int len = 0;
            while (len != -1) {
                len = bis.read(b);
                if (len != -1) {
                    bos.write(b, 0, len);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                bis.close();
                bos.flush();
                bos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
