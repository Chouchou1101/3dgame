package com.google.a3dgame.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.LruCache;

/**
 * Created by Administrator on 2016/7/9.
 */
public class ImgCache {
    private LruCache<String,Bitmap> lruCache;

    public ImgCache() {
        int maxMemory= (int) Runtime.getRuntime().maxMemory();
        int cacheMemory= Math.round(maxMemory/8);
        lruCache=new LruCache<String,Bitmap>(cacheMemory){
            @Override
            protected int sizeOf(String key, Bitmap value) {
                return value.getHeight()*value.getRowBytes()/1024;
            }
        };
    }

    public synchronized void saveToCache(String img,Bitmap bitmap){
        if (img!=null){
            if (bitmap!=null){
                lruCache.put(img,bitmap);
            }
        }

    }

    public Bitmap getFileFromCache(String imgUrl){
        if (imgUrl!=null){
            if (lruCache.get(imgUrl)!=null){
                return lruCache.get(imgUrl);
            }
        }
        return  null;
    }
}
