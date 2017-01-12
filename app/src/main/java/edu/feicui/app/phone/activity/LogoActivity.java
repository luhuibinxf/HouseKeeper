package edu.feicui.app.phone.activity;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageView;

import edu.feicui.app.phone.R;
import edu.feicui.app.phone.base.util.BaseActivity;

public class LogoActivity extends BaseActivity {
    private ImageView mImvLog, mImgApha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);
        mImgApha = (ImageView) findViewById(R.id.logo);
        mImvLog = (ImageView) findViewById(R.id.logo);
        mImvLog.setImageResource(R.drawable.logo);//帧动画
        AnimationDrawable animDraw = (AnimationDrawable) mImvLog.getDrawable();
        animDraw.start();
        Animation anim = new AlphaAnimation(0, 1);
        anim.setDuration(5000);
        anim.setRepeatCount(2);
        mImgApha.startAnimation(anim);//监听
        anim.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                startActivity(HomeActivity.class);
            }
        });
    }
}
