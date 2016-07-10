package com.google.a3dgame;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.a3dgame.adapter.MyPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class GuideActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener{

    private ViewPager viewPager;
    private List<View> viewList;
    private LayoutInflater layoutInflater;
    private ImageView[] dots;
    private int currentIndex;
    private LinearLayout ll;
    private MyPagerAdapter pagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initView();
        initDots();
    }
    public void initView(){
        viewPager= (ViewPager) findViewById(R.id.guide_viewpager);
        layoutInflater=getLayoutInflater().from(this);
        View view1=layoutInflater.inflate(R.layout.guide_viewpager1,null);
        View view2=layoutInflater.inflate(R.layout.guide_viewpager2,null);
        View view3=layoutInflater.inflate(R.layout.guide_viewpager3,null);
        viewList=new ArrayList<>();
        viewList.add(view1);
        viewList.add(view2);
        viewList.add(view3);
        pagerAdapter=new MyPagerAdapter(viewList);
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(this);
    }
    public void initDots(){
        ll= (LinearLayout) findViewById(R.id.guide_ll);
        dots=new ImageView[viewList.size()];
        for (int i=0;i<viewList.size();i++){
            dots[i]= (ImageView) ll.getChildAt(i);
            dots[i].setEnabled(false);
        }
        currentIndex=0;
        dots[currentIndex].setEnabled(true);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
      if (position<0||position+1>viewList.size()){
          return ;
      }
        dots[position].setEnabled(true);
        dots[currentIndex].setEnabled(false);
        currentIndex=position;
        if (position==viewList.size()-1){
            Button button= (Button) viewList.get(position).findViewById(R.id.guide_main_btn);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    saveToLogin();
                    Intent mainIntent=new Intent(GuideActivity.this,Main2Activity.class);
                    startActivity(mainIntent);
                    finish();
                }
            });
        }
    }

    public void saveToLogin(){
        SharedPreferences sp=getSharedPreferences("isFirst", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putBoolean("isFirstLogin",true);
        editor.commit();
    }
    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
