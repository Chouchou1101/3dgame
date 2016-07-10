package com.google.a3dgame;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.app.LoaderManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;

import com.google.a3dgame.adapter.GameGridViewAdapter;
import com.google.a3dgame.common.NewsSQLiteOpenHelper;
import com.google.a3dgame.model.News;
import com.google.a3dgame.utils.GetFileFromSQL;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameActivity extends AppCompatActivity {
//http://www.3dmgame.com/sitemap/api.php?row=12&typeid=<分类ID>&paging=1&page=n
    private String[] games={"游戏首页","动作(ACT)","射击(FPS)","角色扮演(RPG)","养成(GAL)","益智(PUZ)",
        "即时战略(RTS)","策略(SLG)","体育(SPG)","模拟经营(SIM)","赛车(RAC)","冒险(AVG)","动作角色(ARPG)"};
    private Spinner spinner;
    private PullToRefreshGridView pullToRefreshGridView;
    private List<News> list;
    private NewsSQLiteOpenHelper helper;
    private SQLiteDatabase db;
    private int[] typeids={179,181,182,183,184,185,186,187,188,189,190,191,192};
    private  GridView gridView;
    private News  news;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        spinner= (Spinner) findViewById(R.id.game_spinner);
        pullToRefreshGridView= (PullToRefreshGridView) findViewById(R.id.game_pull_gridview);
        pullToRefreshGridView.setMode(PullToRefreshBase.Mode.BOTH);
        list=new ArrayList<News>();
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,games);
        spinner.setAdapter(adapter);
        gridView=pullToRefreshGridView.getRefreshableView();
        list=GetFileFromSQL.queryFromSql(typeids[0],this);
        GameGridViewAdapter gameGridViewAdapter=new GameGridViewAdapter(list,this,getSupportLoaderManager());
        gridView.setAdapter(gameGridViewAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
             list=GetFileFromSQL.queryFromSql(typeids[i],GameActivity.this);
                GameGridViewAdapter gameGridViewAdapter=new GameGridViewAdapter(list,GameActivity.this,getSupportLoaderManager());
                gridView.setAdapter(gameGridViewAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent=new Intent(GameActivity.this,GameDetailActivity.class);
                news=list.get(i);
                Bundle bundle=new Bundle();
                bundle.putSerializable("news",news);
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });

    }



}
