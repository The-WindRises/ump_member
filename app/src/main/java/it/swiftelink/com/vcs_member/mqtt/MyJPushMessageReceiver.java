package it.swiftelink.com.vcs_member.mqtt;

import android.app.ActivityManager;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.jpush.android.api.CustomMessage;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.JPushMessage;
import cn.jpush.android.api.NotificationMessage;
import cn.jpush.android.service.JPushMessageReceiver;
import it.swiftelink.com.vcs_member.App;
import it.swiftelink.com.vcs_member.ui.activity.inquiry.MyInquiryActivity;
import it.swiftelink.com.vcs_member.ui.activity.my.MessageActivity;
import it.swiftelink.com.vcs_member.ui.activity.user.MyCouponActivity;
import it.swiftelink.com.vcs_member.utils.AppShortCutUtil;

import static android.content.Intent.FLAG_ACTIVITY_SINGLE_TOP;
import static com.just.agentweb.UrlLoaderImpl.TAG;

/**
 * @Description:
 * @Author: klk
 * @CreateDate: 2020/1/3 12:41
 */
public class MyJPushMessageReceiver extends JPushMessageReceiver {
    public static int count;
    private static final String TAG=MyJPushMessageReceiver.class.getSimpleName();
    @Override
    public void onMessage(Context context, CustomMessage customMessage) {
        super.onMessage(context, customMessage);
    }

    @Override
    public void onNotifyMessageArrived(Context context, NotificationMessage notificationMessage) {
        super.onNotifyMessageArrived(context, notificationMessage);
        count=count+1;
        JPushInterface.setBadgeNumber(context,count);
        AppShortCutUtil.setCount(count, context);
    }

    @Override
    public void onNotifyMessageOpened(Context context, NotificationMessage notificationMessage) {
        super.onNotifyMessageOpened(context, notificationMessage);
        //**************解析推送过来的json数据******************
        Log.i(TAG,notificationMessage.toString());
        JSONObject jsonObject;
        try {
            jsonObject = new JSONObject(notificationMessage.notificationExtras);
            String type = jsonObject.getString("type");
            String className = ((ActivityManager.RunningTaskInfo) ((ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE)).getRunningTasks(1).get(0)).topActivity.getClassName();
            if (type.equals("NEWS")) {
                //消息类型为公告列表
                if (!"it.swiftelink.com.vcs_member.ui.activity.my".equals(className)) {
                    Intent intent = new Intent(context, MessageActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            }else if(type.equals("COUPON")){ //优惠券
                if(!"it.swiftelink.com.vcs_member.ui.activity.user.MyCouponActivity".equals(className)){
                    Intent intent = new Intent(context, MyCouponActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);

                }
            }else if(type.equals("REPORT")){ //问诊列表
                if(!"it.swiftelink.com.vcs_member.ui.activity.inquiry.MyInquiryActivity".equals(className)){
                    Intent intent = new Intent(context, MyInquiryActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
