package it.swiftelink.com.common.utils;

import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.webkit.WebSettings;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import it.swiftelink.com.common.app.Application;

public class Utils {
    public static String getUserAgent() {
        String userAgent = "";
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            try {
                userAgent = WebSettings.getDefaultUserAgent(Application.getInstance());
            } catch (Exception e) {
                userAgent = System.getProperty("http.agent");
            }
        } else {
            userAgent = System.getProperty("http.agent");
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0, length = userAgent.length(); i < length; i++) {
            char c = userAgent.charAt(i);
            if (c <= '\u001f' || c >= '\u007f') {
                sb.append(String.format("\\u%04x", (int) c));
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }


    public static String getVersionName() {
        try {
            return "V " + Application.getInstance().getPackageManager().getPackageInfo(Application.getInstance().getPackageName(), 0).versionName + Constants.ENV_NAME;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static int getVersionCode() {
        try {
            return Application.getInstance().getPackageManager().getPackageInfo(Application.getInstance().getPackageName(), 0).versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return 0;
        }
    }


    public static String getClientId() {

        String uuid = UUID.randomUUID().toString();

        String dateToString = getCurrentDateStr();
        return "ANDROID_"+dateToString+"_"+uuid;

    }

    /*时间戳转换成字符窜*/
    public static String getCurrentDateStr() {
        Date date = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        return sf.format(date);
    }

    //网络未连接
    private static final boolean NETWORK_NONE = false;
    //移动数据或无线网络连接
    private static final boolean NETWORK_AVAILABLE = true;

    /**
     * 获取当前网络状态
     * @param context 上下文对象
     * @return boolean
     */
    public static boolean getNetStatus(Context context) {
        // 获取系统连接管理器对象
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        //获取网络状态信息
        NetworkInfo activeNetworkInfo = connectivityManager != null ? connectivityManager
                .getActiveNetworkInfo() : null;
        //判断网络NetworkInfo是否不为空且连接
        if (activeNetworkInfo != null && activeNetworkInfo.isConnected()) {
            //网络连接可用
            return NETWORK_AVAILABLE;

        } else {
            return NETWORK_NONE;//网络不可用（未连接）
        }

    }
}
