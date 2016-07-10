package com.google.a3dgame.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by Administrator on 2016/7/6.
 */
public class ImgSaveToSDUtils {
    public static synchronized String getImgSDPath(String imgPath,int reqWidth,int reqHeight) throws Exception{
        File imgFile=null;
        FileOutputStream fos=null;
        String imgSDPath=null;
        byte[] b=DownLoadUtils.downloadNet(imgPath);
        Bitmap bitmap=compressImg(b,reqWidth,reqHeight);
        byte[] b1=bitmapToByte(bitmap);
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){

            File root=Environment.getExternalStorageDirectory();
            File file=new File(root,"imgCache");
            if(!file.exists()){file.mkdirs();}
            String imgName=imgPath.substring(imgPath.lastIndexOf("/")+1);
            imgFile=new File(file,imgName);
            if (imgFile.exists()){imgFile.delete();}
            imgFile.createNewFile();
            fos=new FileOutputStream(imgFile);
            fos.write(b1,0,b1.length);
            imgSDPath=imgFile.getAbsolutePath();
            ImgCache imgCache=new ImgCache();
            imgCache.saveToCache(imgSDPath,BitmapFactory.decodeByteArray(b1,0,b1.length));
        }
        if(fos!=null){
            fos.close();
        }
        return imgSDPath;
    }


    public static Bitmap compressImg(byte[] bytes,int requestWidth,int requestHeight){
        BitmapFactory.Options options=new BitmapFactory.Options();
        options.inJustDecodeBounds=true;
        int rate=1;
        int width=options.outWidth;
        int height=options.outHeight;
        if(width>requestWidth||height>requestHeight){
            int widthRate= Math.round((float)width/requestWidth);
            int heihtRate=Math.round((float)height/requestHeight);
            rate=widthRate>heihtRate?heihtRate:widthRate;
        }
        options.inSampleSize=rate;
        options.inJustDecodeBounds=false;
        Bitmap bitmap=BitmapFactory.decodeByteArray(bytes,0,bytes.length,options);
        return bitmap;
    }

    public static byte[] bitmapToByte(Bitmap bitmap){
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100,baos);
        return baos.toByteArray();
    }


    //
    public static Bitmap getFileFrom(String img){
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)&&img!=null){
            String imgName=img.substring(img.lastIndexOf("/")+1);
            return BitmapFactory.decodeFile(imgName);
    }
        return null;
    }
}
