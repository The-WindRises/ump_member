package it.swiftelink.com.vcs_member.ui.activity.appointment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.shizhefei.view.largeimage.LargeImageView;
import com.shizhefei.view.largeimage.factory.FileBitmapDecoderFactory;

import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;
import it.swiftelink.com.common.app.BaseActivity;
import it.swiftelink.com.common.factory.BaseContract;
import it.swiftelink.com.common.widget.stateview.StateView;
import it.swiftelink.com.vcs_member.R;
import it.swiftelink.com.vcs_member.base.Image;

public class MemberBuyAgreementActivity extends BaseActivity<BaseContract.Presenter> {


    @BindView(R.id.iv_image_agree)
    LargeImageView ivImageAgree;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.stateView)
    StateView stateView;


    public static void show(Activity activity, String imageURL) {

        Intent intent = new Intent(activity, MemberBuyAgreementActivity.class);

        intent.putExtra("imageURL", imageURL);

        activity.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_member_buy_agreement;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        tvTitle.setText(getString(R.string.label_memner_buy_agree));
    }

    @Override
    protected void initData() {
        super.initData();

        Intent intent = getIntent();
        stateView.showLoading();
        if (intent != null && !TextUtils.isEmpty(intent.getStringExtra("imageURL"))) {

            String imageURL = intent.getStringExtra("imageURL");
            Glide.with(this).download(imageURL).into(new SimpleTarget<File>() {
                @Override
                public void onResourceReady(@NonNull File resource, @Nullable Transition<? super File> transition) {
                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inJustDecodeBounds = true;// 这个方式不会在内存创建一张图片，
                    BitmapFactory.decodeFile(resource.getAbsolutePath(), options);
                    Image image = new Image(resource, options.outWidth, options.outHeight, 1);
                    int width = getApplicationContext().getResources().getDisplayMetrics().widthPixels;
                    int height = width * image.height / image.width;
                    ViewGroup.LayoutParams layoutParams = ivImageAgree.getLayoutParams();
                    layoutParams.height = height;
                    ivImageAgree.setLayoutParams(layoutParams);
                    ivImageAgree.setImage(new FileBitmapDecoderFactory(image.file));
                    stateView.showContent();
                }
            });
        }


    }

    @OnClick({R.id.btn_back, R.id.tv_title})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
        }
    }

}
