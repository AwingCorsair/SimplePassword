package com.awingcorsair.simplepassword.Activity;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.WindowManager;

import com.awingcorsair.simplepassword.Util.CloseActivityClass;
import com.github.paolorotolo.appintro.AppIntro2;
import com.github.paolorotolo.appintro.AppIntroFragment;

/**
 * Created by Mao on 2016/9/19.
 */
public class IntroActivity extends AppIntro2{

    @Override
    public void onSkipPressed(Fragment currentFragment) {
        super.onSkipPressed(currentFragment);
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        startActivity(new Intent(this,SetLockActivity.class));
    }

    @Override
    public void onSlideChanged(@Nullable Fragment oldFragment, @Nullable Fragment newFragment) {
        super.onSlideChanged(oldFragment, newFragment);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题栏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
        CloseActivityClass.activityList.add(this);

        int color_page1= Color.parseColor("#009933");
        int color_page2=Color.parseColor("#CC9900");
        int color_page3=Color.parseColor("#666666");

        ApplicationInfo appInfo = getApplicationInfo();
        int img_page1 = getResources().getIdentifier("img_page1", "drawable", appInfo.packageName);
        int img_page2 = getResources().getIdentifier("img_page2", "drawable", appInfo.packageName);
        int img_page3 = getResources().getIdentifier("img_page3", "drawable", appInfo.packageName);

        addSlide(AppIntroFragment.newInstance("强加密数据库","AES-256加密标准，保障您的每项数据极致安全",img_page1,color_page1));
        addSlide(AppIntroFragment.newInstance("无需网络连接","所有数据均储存在本地，杜绝上传数据",img_page2,color_page2));
        addSlide(AppIntroFragment.newInstance("极致简单","大道至简，让一切操作简单至极",img_page3,color_page3));

        // OPTIONAL METHODS
        // Override bar/separator color.
        //setBarColor(Color.parseColor("#3F51B5"));
        //setSeparatorColor(Color.parseColor("#2196F3"));

        // Hide Skip/Done button.
        //showSkipButton(false);
        //setProgressButtonEnabled(false);

        // Turn vibration on and set intensity.
        // NOTE: you will probably need to ask VIBRATE permission in Manifest.
        setVibrate(true);
        setVibrateIntensity(30);
        setFlowAnimation();
    }
}
