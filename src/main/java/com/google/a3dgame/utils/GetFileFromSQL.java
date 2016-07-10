package com.google.a3dgame.utils;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.google.a3dgame.common.NewsSQLiteOpenHelper;
import com.google.a3dgame.model.News;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/7/9.
 */
public class GetFileFromSQL {

    public static List<News> queryFromSql(final int x, Context context){
        List<News> list=new ArrayList<>();
        NewsSQLiteOpenHelper  helper=new NewsSQLiteOpenHelper(context);
        SQLiteDatabase db=helper.getReadableDatabase();
        Cursor cursor=db.query("news",null,"typeid=?",new String[]{x+""},null,null,null);
                while (cursor.moveToNext()){
                    String id = cursor.getString(cursor.getColumnIndex("id"));
                    String typeid=cursor.getString(cursor.getColumnIndex("typeid"));
                    String typeid2=cursor.getString(cursor.getColumnIndex("typeid2"));
                    String sortrank = cursor.getString(cursor.getColumnIndex("sortrank"));
                    String flag = cursor.getString(cursor.getColumnIndex("flag"));
                    String ismake = cursor.getString(cursor.getColumnIndex("ismake"));
                    String channel = cursor.getString(cursor.getColumnIndex("channel"));
                    String arcrank=cursor.getString(cursor.getColumnIndex("arcrank"));
                    String click = cursor.getString(cursor.getColumnIndex("click"));
                    String money=cursor.getString(cursor.getColumnIndex("money"));
                    String title = cursor.getString(cursor.getColumnIndex("title"));
                    String shorttitle = cursor.getString(cursor.getColumnIndex("shorttitle"));
                    String color=cursor.getString(cursor.getColumnIndex("color"));
                    String writer = cursor.getString(cursor.getColumnIndex("writer"));
                    String source = cursor.getString(cursor.getColumnIndex("source"));
                    String litpic = cursor.getString(cursor.getColumnIndex("litpic"));
                    String pubdate = cursor.getString(cursor.getColumnIndex("pubdate"));
                    String senddate = cursor.getString(cursor.getColumnIndex("senddate"));
                    String mid = cursor.getString(cursor.getColumnIndex("mid"));
                    String keywords = cursor.getString(cursor.getColumnIndex("keywords"));
                    String lastpost=cursor.getString(cursor.getColumnIndex("lastpost"));
                    String scores=cursor.getString(cursor.getColumnIndex("scores"));
                    String goodpost=cursor.getString(cursor.getColumnIndex("goodpost"));
                    String badpost=cursor.getString(cursor.getColumnIndex("badpost"));
                    String voteid=cursor.getString(cursor.getColumnIndex("voteid"));
                    String notpost=cursor.getString(cursor.getColumnIndex("notpost"));
                    String description = cursor.getString(cursor.getColumnIndex("description"));
                    String filename=cursor.getString(cursor.getColumnIndex("filename"));
                    String dutyadmin = cursor.getString(cursor.getColumnIndex("dutyadmin"));
                    String tackid=cursor.getString(cursor.getColumnIndex("tackid"));
                    String mtype=cursor.getString(cursor.getColumnIndex("mtype"));
                    String weight = cursor.getString(cursor.getColumnIndex("weight"));
                    String fby_id=cursor.getString(cursor.getColumnIndex("fby_id"));
                    String game_id=cursor.getString(cursor.getColumnIndex("game_id"));
                    String feedback=cursor.getString(cursor.getColumnIndex("feedback"));
                    String typedir = cursor.getString(cursor.getColumnIndex("typedir"));
                    String typename = cursor.getString(cursor.getColumnIndex("typename"));
                    String corank=cursor.getString(cursor.getColumnIndex("corank"));
                    String isdefault = cursor.getString(cursor.getColumnIndex("isdefault"));
                    String defaultname = cursor.getString(cursor.getColumnIndex("defaultname"));
                    String namerule = cursor.getString(cursor.getColumnIndex("namerule"));
                    String namerule2 = cursor.getString(cursor.getColumnIndex("namerule2"));
                    String ispart=cursor.getString(cursor.getColumnIndex("ispart"));
                    String moresite=cursor.getString(cursor.getColumnIndex("moresite"));
                    String siteurl=cursor.getString(cursor.getColumnIndex("siteurl"));
                    String sitepath = cursor.getString(cursor.getColumnIndex("isdefault"));
                    String arcurl = cursor.getString(cursor.getColumnIndex("arcurl"));
                    String typeurl = cursor.getString(cursor.getColumnIndex("typeurl"));

                    News news=new News(id,typeid,typeid2,sortrank,flag,ismake, channel,arcrank,click, money,title,shorttitle,
                      color, writer,source,litpic, pubdate,senddate,mid,keywords,lastpost,scores,
                        goodpost,badpost,voteid, notpost,description,filename,dutyadmin,tackid,
                       mtype,weight,fby_id, game_id,feedback,typedir,typename,corank,isdefault,defaultname,
                        namerule,namerule2,ispart,moresite, siteurl, sitepath,arcurl,typeurl) ;
                    list.add(news);

                }
        if (cursor!=null&&!cursor.isClosed()){
            cursor.close();
        }
        if (db!=null&&db.isOpen()){
            db.close();
        }
        return list;
            }

}

