package it.swiftelink.com.vcs_member.ui.fragment.main;


import android.graphics.Bitmap;
import androidx.fragment.app.Fragment;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.just.agentweb.AgentWeb;
import com.just.agentweb.WebViewClient;

import butterknife.BindView;
import butterknife.OnClick;
import it.swiftelink.com.common.app.BaseFragment;
import it.swiftelink.com.vcs_member.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FindFragment extends BaseFragment {


    @BindView(R.id.ll_find)
    LinearLayout llFind;
    @BindView(R.id.btn_back)
    ImageView btnBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    String rootUrl = "https://h5.youzan.com/v2/feature/X1npGa5BLN";

    private AgentWeb mAgentWeb;

    public FindFragment() {
        // Required empty public constructor
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_find;
    }

    @Override
    protected void initData() {
        super.initData();
    }

    @Override
    protected void initWidget(View view) {
        super.initWidget(view);

        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(llFind, new LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
                .setWebViewClient(mWebViewClient)
                .createAgentWeb()
                .ready()
                .go(rootUrl);

        btnBack.setVisibility(View.GONE);

        tvTitle.setText(getString(R.string.tab_main_find));
    }

    @Override
    public void onPause() {
        if (mAgentWeb != null) {
            mAgentWeb.getWebLifeCycle().onPause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {

        if (mAgentWeb != null) {
            mAgentWeb.getWebLifeCycle().onResume();
        }
        super.onResume();
    }

    @Override
    public void onDestroy() {
        if (mAgentWeb != null) {
            mAgentWeb.getWebLifeCycle().onDestroy();
        }
        super.onDestroy();
    }


    protected WebViewClient mWebViewClient = new WebViewClient() {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
            if (rootUrl.equals(url)) {
                btnBack.setVisibility(View.GONE);
            } else {
                btnBack.setVisibility(View.VISIBLE);
            }
        }


    };


    @OnClick(R.id.btn_back)
    public void onViewClicked() {
        mAgentWeb.back();
    }
}
