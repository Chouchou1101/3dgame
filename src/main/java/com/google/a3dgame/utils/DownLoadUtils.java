package com.google.a3dgame.utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2016/7/5.
 */
public class DownLoadUtils {
    public static byte[] downloadNet(String urlpath) throws Exception{
        URL url=new URL(urlpath);
        HttpURLConnection huc= (HttpURLConnection) url.openConnection();
        huc.setRequestMethod("GET");
        huc.setReadTimeout(50000);
        huc.setConnectTimeout(50000);
        huc.connect();
        if (huc.getResponseCode()==200){
            ByteArrayOutputStream baos=new ByteArrayOutputStream();
            InputStream is=huc.getInputStream();
            int len=0;
            byte[] b=new byte[1024*6];
            while ((len=is.read(b))!=-1){
                baos.write(b,0,len);
            }
            if(is!=null){
                is.close();
            }
            return baos.toByteArray();
        }
        return null;
    }
}
