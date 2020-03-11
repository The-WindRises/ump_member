package it.swiftelink.com.vcs_member;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;

import com.kongzue.dialog.v2.DialogSettings;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.crashreport.CrashReport;
import com.tencent.imsdk.TIMSdkConfig;
import com.tencent.qcloud.tim.uikit.TUIKit;
import com.tencent.qcloud.tim.uikit.config.CustomFaceConfig;
import com.tencent.qcloud.tim.uikit.config.GeneralConfig;
import com.tencent.qcloud.tim.uikit.config.TUIKitConfigs;

import cn.jpush.android.api.JPushInterface;
import it.swiftelink.com.common.app.Application;
import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.common.utils.ConfigHelper;
import it.swiftelink.com.factory.net.NetWork;

import static com.kongzue.dialog.v2.DialogSettings.STYLE_IOS;
import static com.kongzue.dialog.v2.DialogSettings.STYLE_MATERIAL;

public class App extends Application {

    private int ImAppId = 1400242259;

    @Override
    public void onCreate() {
        super.onCreate();
//        ConfigHelper.getInstance().loadConfig();
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);
        Bugly.init(getApplicationContext(), "6adc9e79c4", true);
        CrashReport.initCrashReport(getApplicationContext(), "6adc9e79c4", false);
        NetWork.getInstance().init();
        // 配置一些Config，按需配置
        TUIKitConfigs configs = TUIKit.getConfigs();
        configs.setSdkConfig(new TIMSdkConfig(ImAppId));
        configs.setCustomFaceConfig(new CustomFaceConfig());
        configs.setGeneralConfig(new GeneralConfig());
        TUIKit.init(this, ImAppId, configs);
        DialogSettings.style = STYLE_IOS;


    }

    /**
     * 跳转到权限设置界面
     *
     * @param context
     */
    public static void toSelfSetting(Context context) {
        Intent mIntent = new Intent();
        mIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= 9) {
            mIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            mIntent.setData(Uri.fromParts("package", context.getPackageName(), null));
        } else if (Build.VERSION.SDK_INT <= 8) {
            mIntent.setAction(Intent.ACTION_VIEW);
            mIntent.setClassName("com.android.settings", "com.android.setting.InstalledAppDetails");
            mIntent.putExtra("com.android.settings.ApplicationPkgName", context.getPackageName());
        }
        context.startActivity(mIntent);
    }

}
