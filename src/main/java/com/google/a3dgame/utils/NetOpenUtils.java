package com.google.a3dgame.utils;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by Administrator on 2016/7/5.
 */
public class NetOpenUtils {

    public static boolean isNetConnect(Activity activity){
        boolean flag=false;
        ConnectivityManager cm= (ConnectivityManager)activity.getApplicationContext().
                getSystemService(Context.CONNECTIVITY_SERVICE);
        if(cm==null){
            return flag;
        }
        NetworkInfo ni=cm.getActiveNetworkInfo();
        if (ni!=null||ni.isAvailable()){
            flag=true;
        }
        return flag;
    }
}
