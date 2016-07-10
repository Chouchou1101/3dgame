package com.google.a3dgame;


import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.LoaderManager;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.HorizontalScrollView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.google.a3dgame.adapter.MainFragmentPagerAdapter;
import com.google.a3dgame.fragment.FirstFragment;
import com.google.a3dgame.fragment.SecondFragment;
import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends FragmentActivity implements RadioGroup.OnCheckedChangeListener,ViewPager.OnPageChangeListener{

    private Toolbar toolbar;
    private RadioButton top_rb1,top_rb2,top_rb3,top_rb4,top_rb5,top_rb6,top_rb7,top_rb8,top_rb9,top_rb10,bottom_rb1,bottom_rb2,bottom_rb3;
    private RadioGroup top_rg,bottom_rg;
    private ViewPager viewPager;
    private HorizontalScrollView hsv;
    private List<Fragment> fragments;
    private LoaderManager loaderManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        toolbar=new Toolbar(this);
        toolbar.setLogo(R.mipmap.ic_launcher2);
        initView();
        initListener();
        initData();

    }

    private void initView() {
        hsv= (HorizontalScrollView) findViewById(R.id.main_hsv);
        viewPager= (ViewPager) findViewById(R.id.main_viewpager);
        top_rg= (RadioGroup) findViewById(R.id.main_top_rg);
        top_rb1= (RadioButton) findViewById(R.id.main_top_rb1);
        top_rb2= (RadioButton) findViewById(R.id.main_top_rb2);
        top_rb3= (RadioButton) findViewById(R.id.main_top_rb3);
        top_rb4= (RadioButton) findViewById(R.id.main_top_rb4);
        top_rb5= (RadioButton) findViewById(R.id.main_top_rb5);
        top_rb6= (RadioButton) findViewById(R.id.main_top_rb6);
        top_rb7= (RadioButton) findViewById(R.id.main_top_rb7);
        top_rb8= (RadioButton) findViewById(R.id.main_top_rb8);
        top_rb9= (RadioButton) findViewById(R.id.main_top_rb9);
        top_rb10= (RadioButton) findViewById(R.id.main_top_rb10);
        top_rb1.setChecked(true);
        bottom_rg= (RadioGroup) findViewById(R.id.main_bottom_rg);
        bottom_rb1= (RadioButton) findViewById(R.id.main_bottom_rb1);
        bottom_rb2= (RadioButton) findViewById(R.id.main_bottom_rb2);
        bottom_rb3= (RadioButton) findViewById(R.id.main_bottom_rb3);
        bottom_rb1.setChecked(true);
        loaderManager=getSupportLoaderManager();
    }
    private void initListener(){
          top_rg.setOnCheckedChangeListener(this);
          bottom_rg.setOnCheckedChangeListener(this);
          viewPager.addOnPageChangeListener(this);

    }
    private void initData() {
        fragments=new ArrayList<>();
        FirstFragment firstFragment=new FirstFragment(loaderManager,this);
        fragments.add(firstFragment);

        SecondFragment fragment2=new SecondFragment(loaderManager,this,2);
        SecondFragment fragment3=new SecondFragment(loaderManager,this,151);
        SecondFragment fragment4=new SecondFragment(loaderManager,this,152);
        SecondFragment fragment5=new SecondFragment(loaderManager,this,153);
        SecondFragment fragment6=new SecondFragment(loaderManager,this,154);
        SecondFragment fragment7=new SecondFragment(loaderManager,this,196);
        SecondFragment fragment8=new SecondFragment(loaderManager,this,197);
        SecondFragment fragment9=new SecondFragment(loaderManager,this,199);
        SecondFragment fragment10=new SecondFragment(loaderManager,this,25);

        fragments.add(fragment2);
        fragments.add(fragment3);
        fragments.add(fragment4);
        fragments.add(fragment5);
        fragments.add(fragment6);
        fragments.add(fragment7);
        fragments.add(fragment8);
        fragments.add(fragment9);
        fragments.add(fragment10);

        FragmentManager fm=getSupportFragmentManager();
        MainFragmentPagerAdapter adapter=new MainFragmentPagerAdapter(fm,fragments);
        viewPager.setAdapter(adapter);

    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
            switch (i){
                case R.id.main_top_rb1:
                    viewPager.setCurrentItem(0);
                    break;
                case R.id.main_top_rb2:
                    viewPager.setCurrentItem(1);
                    break;
                case R.id.main_top_rb3:
                    viewPager.setCurrentItem(2);
                    break;
                case R.id.main_top_rb4:
                    viewPager.setCurrentItem(3);
                    break;
                case R.id.main_top_rb5:
                    viewPager.setCurrentItem(4);
                    break;
                case R.id.main_top_rb6:
                    viewPager.setCurrentItem(5);
                    break;
                case R.id.main_top_rb7:
                    viewPager.setCurrentItem(6);
                    break;
                case R.id.main_top_rb8:
                    viewPager.setCurrentItem(7);
                    break;
                case R.id.main_top_rb9:
                    viewPager.setCurrentItem(8);
                    break;
                case R.id.main_top_rb10:
                    viewPager.setCurrentItem(9);
                    break;
                case R.id.main_bottom_rb1:
                    viewPager.setCurrentItem(0);
                    break;
                case R.id.main_bottom_rb2:
                    Intent forum_intent=new Intent(this,ForumWebViewActivity.class);
                    startActivity(forum_intent);
                    break;
                case R.id.main_bottom_rb3:
                    Intent game_intent=new Intent(this,GameActivity.class);
                    startActivity(game_intent);
                    break;
            }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        RadioButton radiobutton= (RadioButton) top_rg.getChildAt(position);
        radiobutton.setChecked(true);
        int Left=radiobutton.getLeft();
        hsv.smoothScrollTo(Left,0);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

}
