package it.swiftelink.com.vcs_member.ui.activity.my;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import it.swiftelink.com.common.widget.dialog.CustomDialog;
import it.swiftelink.com.common.widget.nicedialog.BaseNiceDialog;
import it.swiftelink.com.vcs_member.R;
import it.swiftelink.com.vcs_member.base.Image;
import it.swiftelink.com.vcs_member.wxapi.WechatUtils;

public class ShareActivity extends BaseActivity<BaseContract.Presenter> {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_image_agree)
    LargeImageView ivImageAgree;
    @BindView(R.id.btn_right)
    ImageView btnRight;


    public static void show(Activity activity) {
        activity.startActivity(new Intent(activity, ShareActivity.class));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_share;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        tvTitle.setText(getString(R.string.label_share));

        btnRight.setVisibility(View.VISIBLE);

    }

    @Override
    protected void initData() {
        super.initData();


        Glide.with(this).download(R.mipmap.icon_share_bg).into(new SimpleTarget<File>() {
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

            }
        });

    }

    /**
     * Drawable转换成一个Bitmap
     *
     * @param drawable drawable对象
     * @return
     */
    public static final Bitmap drawableToBitmap(Drawable drawable) {
        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight(),
                drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
        drawable.draw(canvas);
        return bitmap;
    }





    @OnClick({R.id.btn_back, R.id.btn_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn_right:
                selectPhoneType();
                break;
        }
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
                                toShare(0);
                                dialog.dismiss();

                            }
                        });

                        ll_share_type2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                               toShare(1);
                                dialog.dismiss();
                            }
                        });

                    }
                })
                .setOutCancel(true)
                .setShowBottom(true)
                .show(getSupportFragmentManager());
    }

    private void toShare(int type) {

        Resources res = getResources();
        Bitmap bmp = BitmapFactory.decodeResource(res, R.mipmap.icon_share_bg);

        WechatUtils.getInstance(this).sendImageToWeiXin(bmp, type);

    }

}
