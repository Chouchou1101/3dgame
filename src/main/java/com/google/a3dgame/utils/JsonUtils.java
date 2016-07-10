package com.google.a3dgame.utils;

import com.google.a3dgame.model.News;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/5.
 */
public class JsonUtils {
    public static List<News> getList(String json,int j) throws Exception{
        List<News> list=new ArrayList<>();
        JSONObject object=new JSONObject(json).getJSONObject("data");
        for (int i=0;i<j;i++){
        JSONObject iObject=object.getJSONObject(i+"");
            String id = iObject.getString("id");
            String typeid = iObject.getString("typeid");
            String typeid2 = iObject.getString("typeid2");
            String sortrank = iObject.getString("sortrank");
            String flag = iObject.getString("flag");
            String ismake = iObject.getString("ismake");
            String channel = iObject.getString("channel");
            String arcrank = iObject.getString("arcrank");
            String click = iObject.getString("click");
            String money = iObject.getString("money");
            String title = iObject.getString("title");
            String shorttitle = iObject.getString("shorttitle");
            String color = iObject.getString("color");
            String writer = iObject.getString("writer");
            String source = iObject.getString("source");
            String litpic = "http://www.3dmgame.com" + iObject.getString("litpic");
            String pubdate = iObject.getString("pubdate");
            String senddate = iObject.getString("senddate");
            String mid = iObject.getString("mid");
            String keywords = iObject.getString("keywords");
            String lastpost = iObject.getString("lastpost");
            String scores = iObject.getString("scores");
            String goodpost = iObject.getString("goodpost");
            String badpost = iObject.getString("badpost");
            String voteid = iObject.getString("voteid");
            String notpost = iObject.getString("notpost");
            String description = iObject.getString("description");
            String filename = iObject.getString("filename");
            String dutyadmin = iObject.getString("dutyadmin");
            String tackid = iObject.getString("tackid");
            String mtype = iObject.getString("mtype");
            String weight = iObject.getString("weight");
            String fby_id = iObject.getString("fby_id");
            String game_id = iObject.getString("game_id");
            String feedback = iObject.getString("feedback");
            String typedir = iObject.getString("typedir");
            String typename = iObject.getString("typename");
            String corank = iObject.getString("corank");
            String isdefault = iObject.getString("isdefault");
            String defaultname = iObject.getString("defaultname");
            String namerule = iObject.getString("namerule");
            String namerule2 = iObject.getString("namerule2");
            String ispart = iObject.getString("ispart");
            String moresite = iObject.getString("moresite");
            String siteurl = iObject.getString("siteurl");
            String sitepath = iObject.getString("sitepath");
            String arcurl = iObject.getString("arcurl");
            String typeurl = iObject.getString("typeurl");
            News news = new News(id, typeid, typeid2, sortrank, flag, ismake, channel, arcrank, click, money, title, shorttitle, color, writer, source,
                    litpic, pubdate, senddate, mid, keywords, lastpost, scores, goodpost, badpost, voteid, notpost, description, filename, dutyadmin,
                    tackid, mtype, weight, fby_id, game_id, feedback, typedir, typename, corank, isdefault, defaultname, namerule, namerule2, ispart,
                    moresite, siteurl, sitepath, arcurl, typeurl);
            list.add(news);
        }
        return list;
    }
}
