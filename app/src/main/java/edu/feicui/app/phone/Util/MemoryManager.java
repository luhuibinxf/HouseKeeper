package edu.feicui.app.phone.Util;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Environment;
import android.os.StatFs;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/1/8.
 */

public class MemoryManager {

    private long BlockSize;//模块大小
    private long BlockCount;//模块数量
    private long AvailCount;//使用数量

    private float Total;//总共的
    private float Used;//可用的
    private float Avail;//用了的

    Context mct;
    File file;
    StatFs sfs;

    DecimalFormat df = new DecimalFormat("0");
    List list = new ArrayList();

    private PackageManager packageManager;
    private ActivityManager activityManager;//没注意到这个

    public MemoryManager(Context context) {
        this.mct = context;
        packageManager = context.getPackageManager();
        activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);//这个的作用
    }

    /**
     * 可以用的内存
     *
     * @param context
     * @return
     */
    public long getPhoneFreeRamMemory(Context context) {//可以用的内存
        ActivityManager.MemoryInfo info = new ActivityManager.MemoryInfo();//获得MemoryInfo对象
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        am.getMemoryInfo(info);
        return info.availMem;
    }

    /**
     * 得到完整的内存
     *
     * @return
     */
    public long getPhoneTotalramMemory() {//得到完整的内存
        try {
            FileReader fr = new FileReader("/proc/meminfo");
            BufferedReader br = new BufferedReader(fr);
            String text = br.readLine();
            String[] array = text.split("\\s+");
            return Long.valueOf(array[1]) * 1024;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    private static MemoryManager memoryManager = null;

    /**
     * 得到手机App管理信息
     *
     * @param context
     * @return
     */
    public static MemoryManager getAppInfoManager(Context context) {
        if (memoryManager == null) {
            synchronized (context) {
                if (memoryManager == null) {
                    memoryManager = new MemoryManager(context);
                }
            }
        }
        return memoryManager;
    }

    /**
     * 清理所有正在运行的程序（级别为服务以上的非系统进程）
     */
    public void killALLProcesses() {//清理所有正在运行的程序（级别为服务以上的非系统进程）
        List<ActivityManager.RunningAppProcessInfo> appProcessInfos = activityManager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo appProcessInfo : appProcessInfos) {
            String packageName = appProcessInfo.processName;
            try {
                ApplicationInfo applicationInfo = packageManager.getApplicationInfo(packageName, PackageManager.GET_META_DATA | PackageManager.GET_SHARED_LIBRARY_FILES | PackageManager.GET_SHARED_LIBRARY_FILES);
                if ((applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0) {
                    activityManager.killBackgroundProcesses(packageName);
                }
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 运行
     */
    public List getRootDirectory() {
        file = Environment.getRootDirectory();
        sfs = new StatFs(file.getPath());//俩个路径有区别吗

        BlockSize = sfs.getBlockSize();
        BlockCount = sfs.getBlockCount();
        AvailCount = sfs.getAvailableBlocks();

        Total = BlockSize * BlockCount / (1024 * 1024);

        Used = BlockSize * AvailCount / (1024 * 1024);

        Avail = Total - Used;

        float size = (float) (Total - Used) / (float) Total * 100;//注意这是要显示的
        String filesize = df.format(size) + "%";//返回的是String类型的
        list.add(filesize);

        float angle = size * 3.6f;
        float Angle = Float.parseFloat(df.format(angle));
        list.add(Angle);

        return list;
    }

    /**
     * 内
     */
    public List getDataDirectory() {
        file = Environment.getDataDirectory();
        sfs = new StatFs(file.getAbsolutePath());
        BlockSize = sfs.getBlockSize();
        BlockCount = sfs.getBlockCount();
        AvailCount = sfs.getAvailableBlocks();

        Total = BlockSize * BlockCount / (1024 * 1024);
        Used = BlockSize * AvailCount / (1024 * 1024);

        Total = BlockSize * BlockCount / (1024 * 1024);
        int TotalSize = Integer.parseInt(df.format(Total));
        list.add(TotalSize);

        Used = BlockSize * AvailCount / (1024 * 1024);
        int UsedSize = Integer.parseInt(df.format(Used));
        list.add(UsedSize);

        Avail = Total - Used;
        int AvailSize = Integer.parseInt(df.format(Avail));
        list.add(AvailSize);
        return list;
    }

    /**
     * 外
     */
    public List getExternalStorageDirectory() {
        file = Environment.getExternalStorageDirectory();
        sfs = new StatFs(file.getAbsolutePath());
        BlockSize = sfs.getBlockSize();
        BlockCount = sfs.getBlockCount();
        AvailCount = sfs.getAvailableBlocks();

        Total = BlockSize * BlockCount / (1024 * 1024);

        Used = BlockSize * AvailCount / (1024 * 1024);

        Total = BlockSize * BlockCount / (1024 * 1024);
        int TotalSize = Integer.parseInt(df.format(Total));
        list.add(TotalSize);

        Used = BlockSize * AvailCount / (1024 * 1024);
        int UsedSize = Integer.parseInt(df.format(Used));
        list.add(UsedSize);

        Avail = Total - Used;
        int AvailSize = Integer.parseInt(df.format(Avail));
        list.add(AvailSize);
        return list;
    }

    /**
     * @return
     */
    public List TotalAllStorage() {
        file = Environment.getDataDirectory();
        sfs = new StatFs(file.getAbsolutePath());
        BlockSize = sfs.getBlockSize();
        BlockCount = sfs.getBlockCount();
        Total = BlockSize * BlockCount / (1024 * 1024);

        file = Environment.getExternalStorageDirectory();
        sfs = new StatFs(file.getAbsolutePath());
        long BlockSize1 = sfs.getBlockSize();
        long BlockCount1 = sfs.getBlockCount();
        float Total1 = BlockSize1 * BlockCount1 / (1024 * 1024);
        float TotaAll = Total + Total1;

        float size = (float) Total1 / (float) TotaAll * 360;
        float Angle = Float.parseFloat(df.format(size));//返回的是String类型的
        list.add(Angle);

        return list;

    }
}
