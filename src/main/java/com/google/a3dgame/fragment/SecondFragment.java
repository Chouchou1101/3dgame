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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.a3dgame.ArticleWebViewActivity;
import com.google.a3dgame.R;
import com.google.a3dgame.adapter.MainPullAdapter;
import com.google.a3dgame.common.NewsSQLiteOpenHelper;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/7/6.
 */
@SuppressLint("ValidFragment")
public class SecondFragment extends Fragment {
    private LoaderManager loaderManager;
    private Context context;

    private NewsSQLiteOpenHelper helper;
    private List<HashMap<String,String>> newsList;
    private SQLiteDatabase db;
    private int typeid;

    public SecondFragment(LoaderManager loaderManager, Context context,int typeid) {
        this.loaderManager = loaderManager;
        this.context = context;
        this.typeid = typeid;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.main_secondfragmentviewpager,null);
        PullToRefreshListView pullToRefreshListView= (PullToRefreshListView) view.findViewById(R.id.main_second_pulltorefreshlv);
        pullToRefreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        ListView listView=pullToRefreshListView.getRefreshableView();

        helper=new NewsSQLiteOpenHelper(context);
        newsList=new ArrayList<HashMap<String,String>>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                db=helper.getReadableDatabase();
                Cursor cursor=db.query("news",new String[]{"litpic","shorttitle","senddate","click","arcurl"},"typeid=?",new String[]{typeid+""},null,null,null);
                while (cursor.moveToNext()){
                    HashMap<String,String>  map=new HashMap<String, String>();
                    String imgPath=cursor.getString(cursor.getColumnIndex("litpic"));
                    String shorttitle=cursor.getString(cursor.getColumnIndex("shorttitle"));
                    String senddate=cursor.getString(cursor.getColumnIndex("senddate"));
                    String click=cursor.getString(cursor.getColumnIndex("click"));
                    String arcurl=cursor.getString(cursor.getColumnIndex("arcurl"));
                    map.put("imgPath",imgPath);
                    map.put("shorttitle",shorttitle);
                    map.put("senddate",senddate);
                    map.put("click",click);
                    map.put("arcurl",arcurl);
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
