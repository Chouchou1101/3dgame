package com.google.a3dgame;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.a3dgame.model.News;
import com.google.a3dgame.utils.ImgCache;

public class GameDetailActivity extends AppCompatActivity {

    private ImageView iv;
    private TextView title_tv;
    private TextView typename_tv;
    private TextView arcurl_tv;
    private TextView detail_tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_detail);
        iv= (ImageView) findViewById(R.id.game_detail_iv);
        title_tv= (TextView) findViewById(R.id.game_detail_title_tv);
        typename_tv= (TextView) findViewById(R.id.game_detail_typename_tv);
        arcurl_tv= (TextView) findViewById(R.id.game_detail_arcurl_tv);
        detail_tv= (TextView) findViewById(R.id.game_detail_tv);
        Intent intent=getIntent();
        News news= (News) intent.getSerializableExtra("news");
        ImgCache imgCache=new ImgCache();
        String img=news.getLitpic();
        Bitmap bitmap=imgCache.getFileFromCache(img);
        iv.setImageBitmap(bitmap);
        title_tv.setText("游戏名称："+news.getShorttitle());
        typename_tv.setText("游戏类型："+news.getTypename());
        arcurl_tv.setText("官方网站："+news.getArcurl());
        detail_tv.setText("主题："+news.getKeywords()+"\r\n内容："+news.getDescription()+"" +
                "\r\n作者："+news.getWriter());
    }
}
