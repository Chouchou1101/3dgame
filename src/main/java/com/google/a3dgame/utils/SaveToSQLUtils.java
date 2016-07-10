package com.google.a3dgame.utils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.google.a3dgame.common.NewsSQLiteOpenHelper;
import com.google.a3dgame.model.News;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/7/6.
 */
public class SaveToSQLUtils {

    public static synchronized  boolean isSaveToSQL(News news,Context context,String img){
        NewsSQLiteOpenHelper helper=new NewsSQLiteOpenHelper(context);
        SQLiteDatabase db=helper.getReadableDatabase();
        ContentValues values=new ContentValues();
        boolean flag=false;
        Cursor cursor=db.query("news",new String[]{"title"},null,null,null,null,null);
        while (cursor.moveToNext()){
            String cursor_title=cursor.getString(cursor.getColumnIndex("title")).trim();
            if (cursor_title.equals(news.getTitle().trim())){
                return false;
            }
        }
        if (img!=null){
        values.put("id", news.getId());
        values.put("typeid", news.getTypeid());
        values.put("typeid2", news.getTypeid2());
        values.put("sortrank", news.getSortrank());
        values.put("flag", news.getFlag());
        values.put("ismake", news.getIsmake());
        values.put("channel", news.getChannel());
        values.put("arcrank", news.getArcrank());
        values.put("click", news.getClick());
        values.put("money", news.getMoney());
        values.put("title", news.getTitle());
        values.put("shorttitle", news.getShorttitle());
        values.put("color", news.getColor());
        values.put("writer", news.getWriter());
        values.put("source", news.getSource());
        values.put("litpic", img);
        values.put("pubdate", news.getPubdate());
        values.put("senddate", news.getSenddate());
        values.put("mid", news.getMid());
        values.put("keywords", news.getKeywords());
        values.put("lastpost", news.getLastpost());
        values.put("scores", news.getScores());
        values.put("goodpost", news.getGoodpost());
        values.put("badpost", news.getBadpost());
        values.put("voteid", news.getVoteid());
        values.put("notpost", news.getNotpost());
        values.put("description", news.getDescription());
        values.put("filename", news.getFilename());
        values.put("dutyadmin", news.getDutyadmin());
        values.put("tackid", news.getTackid());
        values.put("mtype", news.getMtype());
        values.put("weight", news.getWeight());
        values.put("fby_id", news.getFby_id());
        values.put("game_id", news.getGame_id());
        values.put("feedback", news.getFeedback());
        values.put("typedir", news.getTypedir());
        values.put("typename", news.getTypename());
        values.put("corank", news.getCorank());
        values.put("isdefault", news.getIsdefault());
        values.put("defaultname", news.getDefaultname());
        values.put("namerule", news.getNamerule());
        values.put("namerule2", news.getNamerule2());
        values.put("ispart", news.getIspart());
        values.put("moresite", news.getMoresite());
        values.put("siteurl", news.getSiteurl());
        values.put("sitepath", news.getSitepath());
        values.put("typeurl", news.getTypeurl());
        values.put("arcurl",news.getArcurl());
        values.put("typeurl",news.getTypeurl());
        db.insert("news",null,values);
        flag=true;
        }
        if (cursor!=null&&!cursor.isClosed()){
            cursor.close();
        }
        if(db!=null&&db.isOpen()){
            db.close();
        }
        return flag;
    }
}
