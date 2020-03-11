package it.swiftelink.com.vcs_member.ui.activity.health;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yuyh.library.imgsel.ISNav;
import com.yuyh.library.imgsel.common.ImageLoader;
import com.yuyh.library.imgsel.config.ISListConfig;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.common.utils.GlideLoadUtils;
import it.swiftelink.com.common.widget.stateview.StateView;
import it.swiftelink.com.factory.common.UploadResModel;
import it.swiftelink.com.factory.model.health.EditHealthReportModel;
import it.swiftelink.com.factory.model.health.HealthDepartmentsResModel;
import it.swiftelink.com.factory.presenter.health.EditHealthReportContract;
import it.swiftelink.com.factory.presenter.health.EditHealthReportPresenter;
import it.swiftelink.com.vcs_member.App;
import it.swiftelink.com.vcs_member.R;
import it.swiftelink.com.vcs_member.base.BasePresenterActivity;

public class EditHealthExaminationActivity extends BasePresenterActivity<EditHealthReportContract.Presenter> implements EditHealthReportContract.View {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_examination_name)
    EditText etExaminationName;
    @BindView(R.id.et_examination_organization)
    EditText etExaminationOrganization;
    @BindView(R.id.tv_examination_look)
    EditText tvExaminationLook;
    @BindView(R.id.stateView)
    StateView stateView;
    @BindView(R.id.select_image1)
    ImageView selectImage1;
    @BindView(R.id.select_image2)
    ImageView selectImage2;
    @BindView(R.id.select_image3)
    ImageView selectImage3;
    @BindView(R.id.select_image4)
    ImageView selectImage4;
    @BindView(R.id.select_image5)
    ImageView selectImage5;


    private static final int REQUEST_LIST_CODE = 0;
    private static final int REQUEST_CAMERA_CODE = 1;

    private ImageView currentImage;
    private int currentPos;
    private List<String> mPathList = new ArrayList<>();


    public static void show(Activity activity) {

        Intent intent = new Intent(activity, EditHealthExaminationActivity.class);

        activity.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_edit_health_examination;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        tvTitle.setText(getString(R.string.label_input_examination_report));

        ISNav.getInstance().init(new ImageLoader() {
            @Override
            public void displayImage(Context context, String path, ImageView imageView) {
                Glide.with(context).load(path).into(imageView);
            }
        });
    }

    @Override
    protected EditHealthReportContract.Presenter initPresenter() {
        return new EditHealthReportPresenter(this);
    }

    @Override
    protected StateView setStateView() {
        return stateView;
    }

    @OnClick({R.id.btn_back, R.id.btn_submit, R.id.select_image1,
            R.id.select_image2, R.id.select_image3, R.id.select_image4, R.id.select_image5})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn_submit:
                if (mPathList != null && mPathList.size() > 0) {
                    mPresenter.uploadImage(mPathList);
                } else {
                    App.showToast(getString(R.string.label_notice_select_check_report_pic));
                }
                break;
            case R.id.select_image1:
                currentImage = selectImage1;
                currentPos = 0;
                selectPicture();
                break;
            case R.id.select_image2:
                currentImage = selectImage2;
                currentPos = 1;
                selectPicture();
                break;
            case R.id.select_image3:
                currentImage = selectImage3;
                currentPos = 2;
                selectPicture();
                break;
            case R.id.select_image4:
                currentImage = selectImage4;
                currentPos = 3;
                selectPicture();
                break;
            case R.id.select_image5:
                currentImage = selectImage5;
                currentPos = 4;
                selectPicture();
                break;
        }
    }

    private void submitData(String imageUrl) {

        String name = etExaminationName.getText().toString().trim();
        String organizationName = etExaminationOrganization.getText().toString().trim();
        String examinationLook = tvExaminationLook.getText().toString().trim();

        if (TextUtils.isEmpty(name)) {

            App.showToast(R.string.please_input_examination_name);
            return;
        }

        if (TextUtils.isEmpty(organizationName)) {
            App.showToast(R.string.msg_input_medical_institutions);
            return;
        }

        if (TextUtils.isEmpty(examinationLook)) {
            App.showToast(R.string.msg_input_examine_look);
            return;
        }


        EditHealthReportModel reportModel = new EditHealthReportModel();

        reportModel.setType(Common.CommonStr.HEALTH_REPORT_TYPE3);
        reportModel.setName(name);
        reportModel.setHospital(organizationName);
        reportModel.setAppearance(examinationLook);
        reportModel.setFileId(imageUrl);

        mPresenter.editHealthReport(reportModel);


    }

    private void selectPicture() {
        ISListConfig config = new ISListConfig.Builder()
                // 是否多选
                .multiSelect(false)
                .btnText("Confirm")
                // 确定按钮文字颜色
                .btnTextColor(Color.WHITE)
                // 使用沉浸式状态栏
                .statusBarColor(getResources().getColor(R.color.textColor5))
                // 返回图标ResId
                .backResId(R.mipmap.icon_back)
                .title(getString(R.string.label_pic))
                .titleColor(Color.WHITE)
                .titleBgColor(getResources().getColor(R.color.textColor5))
                .allImagesText("All Images")
                // 第一个是否显示相机
                .needCamera(true)
                // 最大选择图片数量
                .maxNum(5)
                .build();

        ISNav.getInstance().toListActivity(this, config, REQUEST_LIST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_LIST_CODE && resultCode == RESULT_OK && data != null) {
            List<String> pathList = data.getStringArrayListExtra("result");
            GlideLoadUtils.getInstance().glideLoad(this, pathList.get(0), currentImage, R.mipmap.ic_launcher);

            GlideLoadUtils.getInstance().glideLoad(this, pathList.get(0), currentImage, R.mipmap.icon_image_defout);

            if (this.mPathList.size() < 5) {
                this.mPathList.add(pathList.get(0));
            } else {
                this.mPathList.remove(currentPos);
                this.mPathList.add(currentPos, pathList.get(0));
            }


            setImageVisibility(this.mPathList.size());

        } else if (requestCode == REQUEST_CAMERA_CODE && resultCode == RESULT_OK && data != null) {

            String path = data.getStringExtra("result");
            if (TextUtils.isEmpty(path) && mPathList.size() < 6) {
                mPathList.add(path);
                GlideLoadUtils.getInstance().glideLoad(this, path, currentImage, R.mipmap.icon_image_defout);
                if (this.mPathList.size() < 5) {
                    this.mPathList.add(path);
                } else {
                    this.mPathList.remove(currentPos);
                    this.mPathList.add(currentPos, path);
                }

                setImageVisibility(this.mPathList.size());
            }
        }
    }

    private void setImageVisibility(int listSize) {
        switch (listSize) {
            case 0:
                selectImage1.setVisibility(View.VISIBLE);
                selectImage2.setVisibility(View.GONE);
                selectImage3.setVisibility(View.GONE);
                selectImage4.setVisibility(View.GONE);
                selectImage5.setVisibility(View.GONE);
                break;
            case 1:
                selectImage1.setVisibility(View.VISIBLE);
                selectImage2.setVisibility(View.VISIBLE);
                selectImage3.setVisibility(View.GONE);
                selectImage4.setVisibility(View.GONE);
                selectImage5.setVisibility(View.GONE);
                break;
            case 2:
                selectImage1.setVisibility(View.VISIBLE);
                selectImage2.setVisibility(View.VISIBLE);
                selectImage3.setVisibility(View.VISIBLE);
                selectImage4.setVisibility(View.GONE);
                selectImage5.setVisibility(View.GONE);
                break;
            case 3:
                selectImage1.setVisibility(View.VISIBLE);
                selectImage2.setVisibility(View.VISIBLE);
                selectImage3.setVisibility(View.VISIBLE);
                selectImage4.setVisibility(View.VISIBLE);
                selectImage5.setVisibility(View.GONE);
                break;
            case 4:
            case 5:
                selectImage1.setVisibility(View.VISIBLE);
                selectImage2.setVisibility(View.VISIBLE);
                selectImage3.setVisibility(View.VISIBLE);
                selectImage4.setVisibility(View.VISIBLE);
                selectImage5.setVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void editHealthReportSuccess() {
        showContent();

        App.showToast(R.string.msg_save_success);
        finish();
    }

    @Override
    public void getDepartmentsSuccess(HealthDepartmentsResModel resModel) {


    }

    @Override
    public void uploadImageSuccess(UploadResModel resModel) {
        showContent();
        if (resModel.getFiles() != null && resModel.getFiles().size() > 0) {
            List<UploadResModel.FilesBean> files = resModel.getFiles();

            StringBuffer urlBuffer = new StringBuffer();
            for (UploadResModel.FilesBean filesBean : files) {
                if (filesBean != null && !TextUtils.isEmpty(filesBean.getUrl())) {
                    urlBuffer.append(filesBean.getUrl() + ",");
                }

            }

            String url = urlBuffer.toString();
            submitData(url);
        }
    }

    @Override
    public void retry(int type) {

    }

    @Override
    public void showError(int type, int errorCode ,String errorMsg) {
        showContent();
        App.showToast(errorMsg);
    }


}
