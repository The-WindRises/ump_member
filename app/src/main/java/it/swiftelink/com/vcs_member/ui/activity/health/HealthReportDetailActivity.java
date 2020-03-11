package it.swiftelink.com.vcs_member.ui.activity.health;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Rect;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.imageaware.ImageViewAware;
import com.nostra13.universalimageloader.utils.MemoryCacheUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.common.utils.GlideLoadUtils;
import it.swiftelink.com.common.widget.stateview.StateView;
import it.swiftelink.com.factory.model.health.HealthReportListResModel;
import it.swiftelink.com.factory.presenter.health.HealthReportDetailContract;
import it.swiftelink.com.factory.presenter.health.HealthReportDetailPresenter;
import it.swiftelink.com.vcs_member.R;
import it.swiftelink.com.vcs_member.base.BasePresenterActivity;
import it.swiftelink.com.vcs_member.weight.largeImage.PicViewActivity;
import it.swiftelink.com.vcs_member.weight.largeImage.images.MyImageSource;

public class HealthReportDetailActivity extends BasePresenterActivity<HealthReportDetailContract.Presenter> implements HealthReportDetailContract.View {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_name_tips)
    TextView tvNameTips;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_part_tips)
    TextView tvPartTips;
    @BindView(R.id.tv_part)
    TextView tvPart;
    @BindView(R.id.tv_organization_tips)
    TextView tvOrganizationTips;
    @BindView(R.id.tv_organization)
    TextView tvOrganization;
    @BindView(R.id.tv_department)
    TextView tvDepartment;
    @BindView(R.id.tv_look_tips)
    TextView tvLookTips;
    @BindView(R.id.tv_look)
    TextView tvLook;
    @BindView(R.id.tv_result_tips)
    TextView tvResultTips;
    @BindView(R.id.tv_result)
    TextView tvResult;
    @BindView(R.id.iv_image_1)
    ImageView ivImage1;
    @BindView(R.id.iv_image_2)
    ImageView ivImage2;
    @BindView(R.id.iv_image_3)
    ImageView ivImage3;
    @BindView(R.id.iv_image_4)
    ImageView ivImage4;
    @BindView(R.id.iv_image_5)
    ImageView ivImage5;
    @BindView(R.id.stateView)
    StateView stateView;
    @BindView(R.id.ll_part)
    LinearLayout llPart;
    @BindView(R.id.ll_department)
    LinearLayout llDepartment;
    @BindView(R.id.ll_result)
    LinearLayout llResult;

    private String type;

    List<ImageView> ivList = new ArrayList<>();
    List<String> urlList = new ArrayList<>();

    public static void show(Activity activity, String type, String id) {
        Intent intent = new Intent(activity, HealthReportDetailActivity.class);
        intent.putExtra("type", type);
        intent.putExtra("id", id);
        activity.startActivity(intent);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_health_report;
    }

    @Override
    protected void initData() {
        super.initData();

        ivList.add(ivImage1);
        ivList.add(ivImage2);
        ivList.add(ivImage3);
        ivList.add(ivImage4);
        ivList.add(ivImage5);

        Intent intent = getIntent();
        if (intent != null) {

            type = intent.getStringExtra("type");
            String id = intent.getStringExtra("id");
            if (Common.CommonStr.HEALTH_REPORT_TYPE3.equals(type)) {
                tvTitle.setText(getString(R.string.medical_report_details));

                tvNameTips.setText(getString(R.string.label_examination_name));
                llPart.setVisibility(View.GONE);
                llDepartment.setVisibility(View.GONE);
                llResult.setVisibility(View.GONE);
                tvLookTips.setText(getString(R.string.label_examination_finding));

            }

            if (Common.CommonStr.HEALTH_REPORT_TYPE1.equals(type)) {
                tvTitle.setText(getString(R.string.image_data_details));

                tvNameTips.setText(getString(R.string.label_image_name));
                tvPartTips.setText(getString(R.string.label_image_part));
                llPart.setVisibility(View.VISIBLE);
                llDepartment.setVisibility(View.VISIBLE);
                llResult.setVisibility(View.VISIBLE);
                tvLookTips.setText(getString(R.string.label_examination_finding));

            }
            if (Common.CommonStr.HEALTH_REPORT_TYPE2.equals(type)) {
                tvTitle.setText(getString(R.string.Inspection_report_details));

                tvNameTips.setText(getString(R.string.label_examine_name));
                llPart.setVisibility(View.GONE);
                llDepartment.setVisibility(View.VISIBLE);
                llResult.setVisibility(View.GONE);
                tvLookTips.setText(getString(R.string.label_examination_finding));
            }

            mPresenter.getHealthReportList(type, id);
        }


    }

    @Override
    protected HealthReportDetailContract.Presenter initPresenter() {
        return new HealthReportDetailPresenter(this);
    }

    @Override
    protected StateView setStateView() {
        return stateView;
    }

    @Override
    public void getHealthReportListSuccess(HealthReportListResModel model) {

        showContent();

        if (model.getData() != null && model.getData().size() > 0) {
            HealthReportListResModel.DataBean dataBean = model.getData().get(0);

            if (Common.CommonStr.HEALTH_REPORT_TYPE3.equals(type)) {

                tvName.setText(TextUtils.isEmpty(dataBean.getName()) ? "" : dataBean.getName());
                tvOrganization.setText(TextUtils.isEmpty(dataBean.getHospital()) ? "" : dataBean.getHospital());
                tvLook.setText(TextUtils.isEmpty(dataBean.getAppearance()) ? "" : dataBean.getAppearance());

                if (!TextUtils.isEmpty(dataBean.getFileId())) {
                    String fileId = dataBean.getFileId();

                    String[] split = fileId.split(",");
                    urlList.clear();
                    if (split.length > 0) {
                        for (int i = 0; i < split.length; i++) {
                            ImageView imageView = ivList.get(i);
                            String imageUrl = split[i];
                            urlList.add(imageUrl);
                            imageView.setVisibility(View.VISIBLE);
                            GlideLoadUtils.getInstance().glideLoad(this, imageUrl, imageView, R.mipmap.icon_image_defout);
                        }
                    }

                }

            }

            if (Common.CommonStr.HEALTH_REPORT_TYPE1.equals(type)) {

                tvName.setText(TextUtils.isEmpty(dataBean.getName()) ? "" : dataBean.getName());
                tvOrganization.setText(TextUtils.isEmpty(dataBean.getHospital()) ? "" : dataBean.getHospital());
                tvLook.setText(TextUtils.isEmpty(dataBean.getAppearance()) ? "" : dataBean.getAppearance());
                tvPart.setText(TextUtils.isEmpty(dataBean.getImageLocation()) ? "" : dataBean.getImageLocation());
                tvDepartment.setText(TextUtils.isEmpty(dataBean.getSectionOfficeName()) ? "" : dataBean.getSectionOfficeName());
                tvResult.setText(TextUtils.isEmpty(dataBean.getConclusion()) ? "" : dataBean.getConclusion());
                if (!TextUtils.isEmpty(dataBean.getFileId())) {
                    String fileId = dataBean.getFileId();

                    String[] split = fileId.split(",");
                    urlList.clear();
                    if (split.length > 0) {
                        for (int i = 0; i < split.length; i++) {
                            ImageView imageView = ivList.get(i);
                            String imageUrl = split[i];
                            urlList.add(imageUrl);
                            imageView.setVisibility(View.VISIBLE);
                            GlideLoadUtils.getInstance().glideLoad(this, imageUrl, imageView, R.mipmap.icon_image_defout);
                        }
                    }

                }

            }
            if (Common.CommonStr.HEALTH_REPORT_TYPE2.equals(type)) {
                tvName.setText(TextUtils.isEmpty(dataBean.getName()) ? "" : dataBean.getName());
                tvOrganization.setText(TextUtils.isEmpty(dataBean.getHospital()) ? "" : dataBean.getHospital());
                tvLook.setText(TextUtils.isEmpty(dataBean.getAppearance()) ? "" : dataBean.getAppearance());
                tvDepartment.setText(TextUtils.isEmpty(dataBean.getSectionOfficeName()) ? "" : dataBean.getSectionOfficeName());

                if (!TextUtils.isEmpty(dataBean.getFileId())) {
                    String fileId = dataBean.getFileId();


                    String[] split = fileId.split(",");
                    urlList.clear();
                    if (split.length > 0) {
                        for (int i = 0; i < split.length; i++) {
                            ImageView imageView = ivList.get(i);
                            String imageUrl = split[i];
                            urlList.add(imageUrl);
                            imageView.setVisibility(View.VISIBLE);
                            GlideLoadUtils.getInstance().glideLoad(this, imageUrl, imageView, R.mipmap.icon_image_defout);
                        }
                    }

                }
            }

        }
    }

    @Override
    public void retry(int type) {

    }

    @Override
    public void showError(int type, int errorCode ,String errorMsg) {
        super.showError(type,errorCode, errorMsg);
        showContent();
    }


    @OnClick({R.id.iv_image_1, R.id.iv_image_2, R.id.iv_image_3, R.id.iv_image_4, R.id.iv_image_5, R.id.btn_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_image_1:
                if (urlList.size() > 0) {
                    openLargeImage((ImageView) view, urlList.get(0));
                }

                break;
            case R.id.iv_image_2:
                if (urlList.size() > 1) {
                    openLargeImage((ImageView) view, urlList.get(1));
                }
                break;
            case R.id.iv_image_3:
                if (urlList.size() > 2) {
                    openLargeImage((ImageView) view, urlList.get(2));
                }
                break;
            case R.id.iv_image_4:
                if (urlList.size() > 3) {
                    openLargeImage((ImageView) view, urlList.get(3));
                }

                break;
            case R.id.iv_image_5:
                if (urlList.size() > 4) {
                    openLargeImage((ImageView) view, urlList.get(4));
                }
                break;
            case R.id.btn_back:
                finish();
                break;
        }
    }


    private void openLargeImage(final ImageView view, final String imageUrl) {

        final ImageViewAware thumbAware = new ImageViewAware(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HealthReportDetailActivity.this, PicViewActivity.class);
                intent.putExtra("image", new MyImageSource(imageUrl,5760,3840));
                ImageSize targetSize = new ImageSize(thumbAware.getWidth(), thumbAware.getHeight());
                String memoryCacheKey = MemoryCacheUtils.generateKey(imageUrl, targetSize);
                intent.putExtra("cache_key", memoryCacheKey);
                Rect rect = new Rect();
                view.getGlobalVisibleRect(rect);
                intent.putExtra("rect", rect);
                intent.putExtra("scaleType", view.getScaleType());
                startActivity(intent);
            }
        });

    }
}
