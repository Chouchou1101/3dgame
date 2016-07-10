package com.google.a3dgame.adapter;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.a3dgame.R;
import com.google.a3dgame.model.News;
import com.google.a3dgame.utils.ImgCache;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/7/9.
 */
public class GameGridViewAdapter extends BaseAdapter {

    private List<News> list;
    private Context context;
    private News news;
    private LoaderManager loaderManager;
    private int loader_index=10000;
    private static ImgCache imgCache;
    public GameGridViewAdapter(List<News> list, Context context, LoaderManager loaderManager) {
        this.list = list;
        this.context = context;
        this.loaderManager=loaderManager;
        imgCache=new ImgCache();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder=null;
        if(view==null){
            holder=new ViewHolder();
            view=View.inflate(context, R.layout.game_pull_gridview_item,null);
            holder.game_iv= (ImageView) view.findViewById(R.id.game_pull_gridview_iv);
            holder.game_tv= (TextView) view.findViewById(R.id.game_pull_gridview_tv);
            view.setTag(holder);
        }else{
            holder= (ViewHolder) view.getTag();
        }
        news=list.get(i);
        holder.game_tv.setText(news.getShorttitle());

        holder.game_iv.setImageResource(R.drawable.img_default);

        String imgPath=news.getLitpic();
        Bundle bundle=new Bundle();
        bundle.putString("imgPath",imgPath);
        final ViewHolder finalHolder = holder;
        loaderManager.initLoader(loader_index++, bundle, new LoaderManager.LoaderCallbacks<Bitmap>() {
            @Override
            public Loader<Bitmap> onCreateLoader(int id, Bundle args) {
                return new ImagAsyncLoader(context,args);
            }

            @Override
            public void onLoadFinished(Loader<Bitmap> loader, Bitmap data) {
                if(data!=null){
                    finalHolder.game_iv.setImageBitmap(data);
                }else{
                    Toast.makeText(context, "图片加载加载异常", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onLoaderReset(Loader<Bitmap> loader) {
                finalHolder.game_iv.setImageBitmap(null);
            }
        });


        return view;
    }

    static class ImagAsyncLoader extends AsyncTaskLoader<Bitmap> {
        private Bundle bundle;
        public ImagAsyncLoader(Context context,Bundle bundle) {
            super(context);
            this.bundle=bundle;
        }

        @Override
        protected void onStartLoading() {
            super.onStartLoading();
            forceLoad();
        }

        @Override
        public Bitmap loadInBackground() {
            ByteArrayOutputStream baos=null;
            FileInputStream fis=null;
            Bitmap bitmap1=null;
            try {
                fis=new FileInputStream(bundle.getString("imgPath"));
                baos=new ByteArrayOutputStream();
                byte[] b=new byte[1024*6];
                int len=0;
                while((len=fis.read(b))!=-1){
                    baos.write(b,0,len);
                }
                byte[] bytes=baos.toByteArray();
                bitmap1= BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                imgCache.saveToCache(bundle.getString("imgPath"),bitmap1);
            } catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                if (fis!=null){
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return bitmap1;
        }
    }



    class ViewHolder{
        ImageView game_iv;
        TextView game_tv;
    }
}
