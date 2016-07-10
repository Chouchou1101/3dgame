package com.google.a3dgame.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.a3dgame.ArticleWebViewActivity;
import com.google.a3dgame.R;
import com.google.a3dgame.adapter.FirstPagerAdapter;
import com.google.a3dgame.adapter.MainPullAdapter;
import com.google.a3dgame.common.NewsSQLiteOpenHelper;
import com.google.a3dgame.customview.FirstCustomFragmentViewPager;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/7/6.
 */

@SuppressLint("ValidFragment")
public class FirstFragment extends Fragment {


    private LoaderManager loaderManager;
    private Context context;

    private NewsSQLiteOpenHelper helper;
    private List<HashMap<String,String>> newsList;
    private SQLiteDatabase  db;

    public FirstFragment(LoaderManager loaderManager, Context context) {
        this.loaderManager = loaderManager;
        this.context = context;
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.main_firstcustomfragmentviewpager,null);
        FirstCustomFragmentViewPager firstCustomFragmentViewPager= (FirstCustomFragmentViewPager) view.findViewById(R.id.main_firstcustomfragment_viewpager);
        int resID[]={R.drawable.default1,R.drawable.default2,R.drawable.default3};
        List<ImageView> list=new ArrayList<>();
        for(int i=0;i<3;i++){
            ImageView imageView=new ImageView(getContext());
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            imageView.setImageResource(resID[i]);
            list.add(imageView);
        }
         FirstPagerAdapter firstPagerAdapter=new FirstPagerAdapter(list);
         firstCustomFragmentViewPager.setAdapter(firstPagerAdapter);

        PullToRefreshListView pullToRefreshListView= (PullToRefreshListView) view.findViewById(R.id.main_first_pulltorefreshlv);
        ListView listView=pullToRefreshListView.getRefreshableView();
        pullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);

        pullToRefreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {

            }
        });


        helper=new NewsSQLiteOpenHelper(context);
        newsList=new ArrayList<HashMap<String,String>>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                db=helper.getReadableDatabase();
                Cursor cursor=db.query("news",new String[]{"litpic","shorttitle","senddate","click"},null,null,null,null,null);
                while (cursor.moveToNext()){
                    HashMap<String,String>  map=new HashMap<String, String>();
                    String imgPath=cursor.getString(cursor.getColumnIndex("litpic"));
                    String shorttitle=cursor.getString(cursor.getColumnIndex("shorttitle"));
                    String senddate=cursor.getString(cursor.getColumnIndex("senddate"));
                    String click=cursor.getString(cursor.getColumnIndex("click"));
                    map.put("imgPath",imgPath);
                    map.put("shorttitle",shorttitle);
                    map.put("senddate",senddate);
                    map.put("click",click);
                    newsList.add(map);
                }
            }
        }).start();


        MainPullAdapter mainPullAdapter=new MainPullAdapter(newsList,context,loaderManager);
        listView.setAdapter(mainPullAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String arcurl=newsList.get(i).get("arcurl");
                Intent intent=new Intent(context,ArticleWebViewActivity.class);
                intent.putExtra("arcurl",arcurl);
                startActivity(intent);
            }
        });
        return view;
    }



}
