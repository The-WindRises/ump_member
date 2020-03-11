package it.swiftelink.com.vcs_member.ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.just.agentweb.AgentWeb;

import butterknife.BindView;
import butterknife.OnClick;
import it.swiftelink.com.common.app.BaseActivity;
import it.swiftelink.com.common.factory.BaseContract;
import it.swiftelink.com.common.widget.dialog.CustomDialog;
import it.swiftelink.com.common.widget.nicedialog.BaseNiceDialog;
import it.swiftelink.com.vcs_member.R;
import it.swiftelink.com.vcs_member.wxapi.WechatUtils;

public class WebViewActivity extends BaseActivity<BaseContract.Presenter> {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.ll_content)
    LinearLayout llContent;
    @BindView(R.id.btn_right)
    ImageView btnRight;

    private AgentWeb mAgentWeb;
    private String url;
    private String title;

    public static void show(Activity activity, String url, String title) {

        Intent intent = new Intent(activity, WebViewActivity.class);
        intent.putExtra("url", url);
        intent.putExtra("title", title);
        activity.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_web_view;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
    }

    @Override
    protected void initData() {
        super.initData();

        btnRight.setVisibility(View.VISIBLE);

        Intent intent = getIntent();

        if (intent != null) {
            url = intent.getStringExtra("url");
            title = intent.getStringExtra("title");

            tvTitle.setText(title);

            mAgentWeb = AgentWeb.with(this)
                    .setAgentWebParent(llContent, new LinearLayout.LayoutParams(-1, -1))
                    .useDefaultIndicator()
                    .createAgentWeb()
                    .ready()
                    .go(url);
        }

    }

    @OnClick({R.id.btn_back, R.id.btn_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn_right:
                selectPhoneType();
//                WechatUtils.getInstance(this).sendPageToWeiXin();
                break;
        }
    }


    @Override
    protected void onPause() {

        if (mAgentWeb != null) {
            mAgentWeb.getWebLifeCycle().onPause();
        }

        super.onPause();

    }

    @Override
    protected void onResume() {
        if (mAgentWeb != null) {
            mAgentWeb.getWebLifeCycle().onResume();
        }

        super.onResume();
    }

    @Override
    protected void onDestroy() {
        if (mAgentWeb != null) {
            mAgentWeb.getWebLifeCycle().onDestroy();
        }
        super.onDestroy();
    }


    private void selectPhoneType() {
        CustomDialog.
                newInstance(R.layout.dialog_select_share_type)
                .setViewCreateListner(new CustomDialog.ViewCreateListener() {

                    @Override
                    public void onViewCreate(ViewGroup viewGroup, final BaseNiceDialog dialog) {

                        final LinearLayout ll_share_type1 = viewGroup.findViewById(R.id.ll_share_type1);
                        final LinearLayout ll_share_type2 = viewGroup.findViewById(R.id.ll_share_type2);

                        ll_share_type1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Resources res = getResources();
                                Bitmap bmp = BitmapFactory.decodeResource(res, R.mipmap.ic_launcher_round);
                                WechatUtils.getInstance(WebViewActivity.this).sendPageToWeiXin(url,bmp,title,title,0);
                                dialog.dismiss();

                            }
                        });

                        ll_share_type2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                WechatUtils.getInstance(WebViewActivity.this).sendPageToWeiXin(url,null,title,title,1);
                                dialog.dismiss();
                            }
                        });

                    }
                })
                .setOutCancel(true)
                .setShowBottom(true)
                .show(getSupportFragmentManager());
    }

}
