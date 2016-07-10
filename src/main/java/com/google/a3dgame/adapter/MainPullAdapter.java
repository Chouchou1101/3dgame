package com.google.a3dgame.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.a3dgame.R;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2016/7/7.
 */
public class MainPullAdapter extends BaseAdapter {

    private List<HashMap<String,String>> data;
    private Context context;
    private LoaderManager loaderManager;
    private int loader_index=1;

    public MainPullAdapter(List<HashMap<String,String>> data, Context context, LoaderManager loaderManager) {
        this.data = data;
        this.context = context;
        this.loaderManager = loaderManager;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int i) {
        return data.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final ViewHolder holder;
        if(view==null){
            view=View.inflate(context, R.layout.main_pulltorefresh_item,null);
            holder=new ViewHolder();
            holder.iv= (ImageView) view.findViewById(R.id.main_pull_imageView);
            holder.tv_title= (TextView) view.findViewById(R.id.main_pull_item_title);
            holder.tv_sendTime= (TextView) view.findViewById(R.id.main_pull_item_sendtime);
            holder.tv_feedback= (TextView) view.findViewById(R.id.main_pull_item_feedback);
            view.setTag(holder);
        }else{
            holder= (ViewHolder) view.getTag();
        }
        HashMap<String,String> map=data.get(i);
        holder.tv_title.setText(map.get("shorttitle"));

        long senddate=Long.parseLong(map.get("senddate"));
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sendtime=sdf.format(senddate);
        holder.tv_sendTime.setText(sendtime);
        holder.tv_feedback.setText(map.get("click"));

        holder.iv.setImageResource(R.drawable.img_default);
        String imgPath=map.get("imgPath");
        Bundle bundle=new Bundle();
        bundle.putString("imgPath",imgPath);
        loaderManager.initLoader(loader_index++, bundle, new LoaderManager.LoaderCallbacks<Bitmap>() {
            @Override
            public Loader<Bitmap> onCreateLoader(int id, Bundle args) {
                return new ImagAsyncLoader(context,args);
            }

            @Override
            public void onLoadFinished(Loader<Bitmap> loader, Bitmap data) {
                if(data!=null){
                    holder.iv.setImageBitmap(data);
                }else{
                    Toast.makeText(context, "图片加载加载异常", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onLoaderReset(Loader<Bitmap> loader) {
               holder.iv.setImageBitmap(null);
            }
        });


        return view;
    }

   static class ImagAsyncLoader extends AsyncTaskLoader<Bitmap>{
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
        ImageView iv;
        TextView  tv_title;
        TextView  tv_sendTime;
        TextView  tv_feedback;
    }
}
