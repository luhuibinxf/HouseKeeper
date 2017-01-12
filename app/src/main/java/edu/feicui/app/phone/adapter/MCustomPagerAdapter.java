package edu.feicui.app.phone.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

import edu.feicui.app.phone.R;

/**
 * Created by Administrator on 2017/1/11.
 */

public class MCustomPagerAdapter extends PagerAdapter {
    Context context;
    ArrayList<View> views = new ArrayList<View>();
    ArrayList<String> Title = new ArrayList<String>();

    public MCustomPagerAdapter(Context mCt) {
        context = mCt;
    }

    public ArrayList<View> initViews() {
        ArrayList<View> views = new ArrayList<View>();
        ImageView imageView = new ImageView(context);//每次都的从新new一下
        imageView.setLayoutParams(new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        imageView.setImageResource(R.mipmap.adware_style_applist);
        views.add(imageView);

        imageView = new ImageView(context);//每次都的从新new一下
        imageView.setLayoutParams(new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        imageView.setImageResource(R.mipmap.adware_style_banner);
        views.add(imageView);

        imageView = new ImageView(context);//每次都的从新new一下
        imageView.setLayoutParams(new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        imageView.setImageResource(R.mipmap.adware_style_creditswall);
        views.add(imageView);

        return views;
    }

    public ArrayList<String> initTitles() {
        Title.add("一键加速");
        Title.add("垃圾清理");
        Title.add("软件管理");
        return null;
    }

    public void setViews(ArrayList<View> views) {
        this.views = views;
    }


    @Override
    public int getCount() {
        return views.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(views.get(position));
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = views.get(position);
        container.addView(view);
        return view;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return Title.get(position);
    }

}
