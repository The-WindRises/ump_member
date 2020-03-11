package it.swiftelink.com.vcs_member.ui.activity.account;


import android.content.res.Resources;
import android.os.Build;
import android.os.Handler;

import java.util.Locale;

import it.swiftelink.com.common.app.Application;
import it.swiftelink.com.common.app.BaseActivity;
import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.common.factory.BaseContract;
import it.swiftelink.com.common.utils.LocalManageUtil;
import it.swiftelink.com.vcs_member.App;
import it.swiftelink.com.vcs_member.R;
import it.swiftelink.com.vcs_member.ui.activity.GuideActivity;
import it.swiftelink.com.vcs_member.ui.activity.MainActivity;

public class SplashActivity extends BaseActivity<BaseContract.Presenter> {
    private String currentLanguage;
    private Handler handler = new Handler();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initWidget() {
        super.initWidget();

        final boolean isFirst = App.getSPUtils().getBoolean(Common.SPApi.IS_FIRST, true);

        handler.postDelayed(new Runnable() {

            @Override
            public void run() {
                if (isFirst) {
                    GuideActivity.show(SplashActivity.this);
                    finish();
                } else {
                    langeuage();
                    MainActivity.show(SplashActivity.this);
                    finish();
                }

            }
        }, 1500);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    public void langeuage(){
        currentLanguage = App.getSPUtils().getString(Common.SPApi.SELECT_LANGUAGE);


        Locale locale;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            locale = getResources().getConfiguration().getLocales().get(0);
        } else {
            locale = getResources().getConfiguration().locale;
        }

        String language = locale.getLanguage();
        String languageAndArea = locale.getLanguage() + "-" + locale.getCountry();

        switch (language){
            case "zh":
                if(languageAndArea.equals("zh-HK") || languageAndArea.equals("zh-TW") || locale.toString().equals("zh_CN_#Hant")){
                    App.getSPUtils().put(Common.SPApi.SELECT_LANGUAGE, Common.CommonStr.LANGUAGE2);
                    LocalManageUtil.saveSelectLanguage(Application.getInstance(), 2);
                }else {
                    App.getSPUtils().put(Common.SPApi.SELECT_LANGUAGE, Common.CommonStr.LANGUAGE1);
                    LocalManageUtil.saveSelectLanguage(Application.getInstance(), 1);
                }
                break;
            default:
                App.getSPUtils().put(Common.SPApi.SELECT_LANGUAGE, Common.CommonStr.LANGUAGE3);
                LocalManageUtil.saveSelectLanguage(Application.getInstance(), 3);
                break;
        }
    }
}
