package it.swiftelink.com.vcs_member.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.stx.xhb.xbanner.XBanner;
import com.yuyh.library.imgsel.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import it.swiftelink.com.common.app.Application;
import it.swiftelink.com.common.app.BaseActivity;
import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.common.factory.BaseContract;
import it.swiftelink.com.common.utils.GlideLoadUtils;
import it.swiftelink.com.common.utils.LocalManageUtil;
import it.swiftelink.com.vcs_member.App;
import it.swiftelink.com.vcs_member.R;
import it.swiftelink.com.vcs_member.base.Guide;

public class GuideActivity extends BaseActivity<BaseContract.Presenter> {

    @BindView(R.id.xbanner)
    XBanner xbanner;


    public static void show(Activity activity){
        activity.startActivity(new Intent(activity,GuideActivity.class));
    }
    @Override
    protected int getLayoutId() {

        return R.layout.activity_guide;
    }

    @Override
    protected void initData() {
        super.initData();

        List<Guide> resCnList = new ArrayList<>();
        List<Guide> resHkList = new ArrayList<>();
        List<Guide> resEnList = new ArrayList<>();


        resCnList.add(new Guide(R.mipmap.icon_cn1));
        resCnList.add(new Guide(R.mipmap.icon_cn2));
        resCnList.add(new Guide(R.mipmap.icon_cn3));


        resHkList.add(new Guide(R.mipmap.icon_hk1));
        resHkList.add(new Guide(R.mipmap.icon_hk2));
        resHkList.add(new Guide(R.mipmap.icon_hk3));


        resEnList.add(new Guide(R.mipmap.icon_en1));
        resEnList.add(new Guide(R.mipmap.icon_en2));
        resEnList.add(new Guide(R.mipmap.icon_en3));

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
                    xbanner.setBannerData(resHkList);
                    App.getSPUtils().put(Common.SPApi.SELECT_LANGUAGE, Common.CommonStr.LANGUAGE2);
                    LocalManageUtil.saveSelectLanguage(Application.getInstance(), 2);
                }else {
                    xbanner.setBannerData(resCnList);
                    App.getSPUtils().put(Common.SPApi.SELECT_LANGUAGE, Common.CommonStr.LANGUAGE1);
                    LocalManageUtil.saveSelectLanguage(Application.getInstance(), 1);
                }
                break;
            default:
                xbanner.setBannerData(resEnList);
                App.getSPUtils().put(Common.SPApi.SELECT_LANGUAGE, Common.CommonStr.LANGUAGE3);
                LocalManageUtil.saveSelectLanguage(Application.getInstance(), 3);
                break;
        }

        xbanner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                Guide bannerBen = (Guide) model;
                GlideLoadUtils.getInstance().glideLoadCenterInside(GuideActivity.this, bannerBen.getResId(), (ImageView) view, bannerBen.getResId());

            }
        });

        xbanner.setOnItemClickListener(new XBanner.OnItemClickListener() {
            @Override
            public void onItemClick(XBanner banner, Object model, View view, int position) {
                if(position==2){
                    MainActivity.show(GuideActivity.this);
                    App.getSPUtils().put(Common.SPApi.IS_FIRST,false);
                    finish();

                }
            }
        });
    }

}
