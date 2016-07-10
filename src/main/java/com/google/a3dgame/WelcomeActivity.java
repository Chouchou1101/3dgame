package com.google.a3dgame;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Toast;

import com.google.a3dgame.service.DownLoadService;
import com.google.a3dgame.utils.NetOpenUtils;

import pl.droidsonroids.gif.GifImageView;

public class WelcomeActivity extends AppCompatActivity {

    private boolean isNetOpen;
    private boolean isFristLogin;
    private GifImageView giv;
    private Animation animation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        giv= (GifImageView) findViewById(R.id.welcome_gif);
        animation=new AlphaAnimation(0,1.0f);
        animation.setDuration(3000);
        giv.startAnimation(animation);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                isNetOpen=NetOpenUtils.isNetConnect(WelcomeActivity.this);
                if (isNetOpen){
                    Intent downloadIntent=new Intent(WelcomeActivity.this, DownLoadService.class);
                    startService(downloadIntent);
                }
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if(!isNetOpen){
                    Toast.makeText(WelcomeActivity.this,"网络异常", Toast.LENGTH_LONG).show();
                }
                  isFirstLogin();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void isFirstLogin( ){
      SharedPreferences sp=getSharedPreferences("isFirst",Context.MODE_PRIVATE);
        isFristLogin=sp.getBoolean("isFirstLogin",false);
        if(!isFristLogin){
            Intent guideIntent=new Intent(this,GuideActivity.class);
            startActivity(guideIntent);
        }
        else{
            Intent mainIntent=new Intent(this,Main2Activity.class);
            startActivity(mainIntent);
        }
        finish();
    }

}
