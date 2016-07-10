package com.google.a3dgame.service;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;
import com.google.a3dgame.model.News;
import com.google.a3dgame.utils.DownLoadUtils;
import com.google.a3dgame.utils.ImgSaveToSDUtils;
import com.google.a3dgame.utils.JsonUtils;
import com.google.a3dgame.utils.SaveToSQLUtils;

import java.util.ArrayList;
import java.util.List;

import static android.widget.Toast.LENGTH_LONG;

public class DownLoadService extends Service {

    private int page=1;
    String urlpath="http://www.3dmgame.com/sitemap/api.php?row=20&typeid=1&paging=1&page=";
    String urlpath2="http://www.3dmgame.com/sitemap/api.php?row=20&typeid=2&paging=1&page=";
    String urlpath151="http://www.3dmgame.com/sitemap/api.php?row=20&typeid=151&paging=1&page=";
    String urlpath152="http://www.3dmgame.com/sitemap/api.php?row=20&typeid=152&paging=1&page=";
    String urlpath153="http://www.3dmgame.com/sitemap/api.php?row=20&typeid=153&paging=1&page=";
    String urlpath154="http://www.3dmgame.com/sitemap/api.php?row=20&typeid=154&paging=1&page=";
    String urlpath196="http://www.3dmgame.com/sitemap/api.php?row=20&typeid=196&paging=1&page=";
    String urlpath197="http://www.3dmgame.com/sitemap/api.php?row=20&typeid=197&paging=1&page=";
    String urlpath199="http://www.3dmgame.com/sitemap/api.php?row=20&typeid=199&paging=1&page=";
    String urlpath25="http://www.3dmgame.com/sitemap/api.php?row=20&typeid=25&paging=1&page=";
    String urlpath179="http://www.3dmgame.com/sitemap/api.php?row=12&typeid=179&paging=1&page=";
    String urlpath181="http://www.3dmgame.com/sitemap/api.php?row=12&typeid=181&paging=1&page=";
    String urlpath182="http://www.3dmgame.com/sitemap/api.php?row=12&typeid=182&paging=1&page=";
    String urlpath183="http://www.3dmgame.com/sitemap/api.php?row=12&typeid=183&paging=1&page=";
    String urlpath184="http://www.3dmgame.com/sitemap/api.php?row=12&typeid=184&paging=1&page=";
    String urlpath185="http://www.3dmgame.com/sitemap/api.php?row=12&typeid=185&paging=1&page=";
    String urlpath186="http://www.3dmgame.com/sitemap/api.php?row=12&typeid=186&paging=1&page=";
    String urlpath187="http://www.3dmgame.com/sitemap/api.php?row=12&typeid=187&paging=1&page=";
    String urlpath188="http://www.3dmgame.com/sitemap/api.php?row=12&typeid=188&paging=1&page=";
    String urlpath189="http://www.3dmgame.com/sitemap/api.php?row=12&typeid=189&paging=1&page=";
    String urlpath190="http://www.3dmgame.com/sitemap/api.php?row=12&typeid=190&paging=1&page=";
    String urlpath191="http://www.3dmgame.com/sitemap/api.php?row=12&typeid=191&paging=1&page=";
    String urlpath192="http://www.3dmgame.com/sitemap/api.php?row=12&typeid=192&paging=1&page=";

    private List<News> list;
    private String json;
    private boolean isSaveToSQL;
    @Override
    public void onCreate() {
        super.onCreate();
        list=new ArrayList<>();
    }
public void runRun(final String Url){
        new Thread(new Runnable() {
            @Override
            public void run() {
                byte[] b= null;
                try {
                    b = DownLoadUtils.downloadNet(Url);
                    if (b!=null){
                        json=new String(b,"utf-8");
                        Log.i("aaa","json="+json);
                    }if (json!=null){
                    list= JsonUtils.getList(json,20*page);}
                    for (News news:list){
                        String img= ImgSaveToSDUtils.getImgSDPath(news.getLitpic(),80,60);
                       SaveToSQLUtils.isSaveToSQL(news,DownLoadService.this,img);
                    }


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();}

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
       runRun(urlpath+page++);
       runRun(urlpath2+page++);
       runRun(urlpath151+page++);
       runRun(urlpath152+page++);
       runRun(urlpath153+page++);
       runRun(urlpath154+page++);
       runRun(urlpath196+page++);
       runRun(urlpath197+page++);
       runRun(urlpath199+page++);
       runRun(urlpath25+page++);
        runRun(urlpath179+page++);
        runRun(urlpath181+page++);
        runRun(urlpath182+page++);
        runRun(urlpath183+page++);
        runRun(urlpath184+page++);
        runRun(urlpath185+page++);
        runRun(urlpath186+page++);
        runRun(urlpath187+page++);
        runRun(urlpath188+page++);
        runRun(urlpath189+page++);
        runRun(urlpath190+page++);
        runRun(urlpath191+page++);
        runRun(urlpath192+page++);


        return START_REDELIVER_INTENT;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


}
