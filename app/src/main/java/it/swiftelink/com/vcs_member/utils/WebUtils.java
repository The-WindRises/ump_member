package it.swiftelink.com.vcs_member.utils;

import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * @Description:
 * @Author: klk
 * @CreateDate: 2019/12/26 13:12
 */
public class WebUtils {
    public static void setWebViewProp(WebView mWebView) {
        // 设置WebSettings
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
        // 设置可以支持缩放
        settings.setSupportZoom(false);
        //扩大比例的缩放
        settings.setUseWideViewPort(false);
        //自适应屏幕
        settings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        settings.setLoadWithOverviewMode(true);
        // 设置WebViewClient
        mWebView.setWebViewClient(new WebViewClient());
        // 设置WebChromeClient
        mWebView.setWebChromeClient(new WebChromeClient());
    }

}
