package it.swiftelink.com.vcs_member.ui.activity.my;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Html;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.concurrent.ExecutionException;

import butterknife.BindView;
import butterknife.OnClick;
import it.swiftelink.com.common.app.BaseActivity;
import it.swiftelink.com.common.factory.BaseContract;
import it.swiftelink.com.vcs_member.R;

public class HelpContentActivity extends BaseActivity<BaseContract.Presenter> {


    @BindView(R.id.helpContenTv)
    TextView helpContenTv;
    @BindView(R.id.tv_title)
    TextView tvTitle;

    private Drawable drawable;
    private String content;


    public static void show(Activity activity, String content, String title) {

        Intent intent = new Intent(activity, HelpContentActivity.class);
        intent.putExtra("content", content);
        intent.putExtra("title", title);
        activity.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_help_content;
    }

    @Override
    protected void initData() {
        super.initData();

        if (getIntent() != null) {
            content = getIntent().getStringExtra("content");
            String title = getIntent().getStringExtra("title");

            helpContenTv.setText(Html.fromHtml(content, imageGetter, null));

            tvTitle.setText(title);

        }

    }


    private Html.ImageGetter imageGetter = new Html.ImageGetter() {
        @Override
        public Drawable getDrawable(String s) {
            //多张图片情况根据drawableMap.get(s)获取drawable
            if (drawable != null)
                return drawable;
            else
                initDrawable(s);
            return null;
        }
    };

    /**
     * 加载网络图片
     * @param s
     */
    private void initDrawable(final String s) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    final Drawable drawable = Glide.with(HelpContentActivity.this).load(s).submit().get();
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            WindowManager manager = HelpContentActivity.this.getWindowManager();
                            DisplayMetrics outMetrics = new DisplayMetrics();
                            manager.getDefaultDisplay().getMetrics(outMetrics);
                            int width = outMetrics.widthPixels;
                            int height = outMetrics.heightPixels;
                            if (drawable != null) {
                                drawable.setBounds(0, 0, width, drawable.getIntrinsicHeight());
                                //多张图片情况下进行存储：drawableMap.put(s,drawable);
                                HelpContentActivity.this.drawable = drawable;
                                if (Build.VERSION.SDK_INT >= 24)
                                    helpContenTv.setText(Html.fromHtml(content, Html.FROM_HTML_MODE_COMPACT, imageGetter, null));
                                else
                                    helpContenTv.setText(Html.fromHtml(content, imageGetter, null));
                            }
                        }
                    });
                } catch (InterruptedException | ExecutionException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


    @OnClick(R.id.btn_back)
    public void onViewClicked() {
        finish();
    }
}
