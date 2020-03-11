package it.swiftelink.com.vcs_member.ui.activity.health;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import it.swiftelink.com.common.widget.dialog.CustomDialog;
import it.swiftelink.com.common.widget.nicedialog.BaseNiceDialog;
import it.swiftelink.com.common.widget.stateview.StateView;
import it.swiftelink.com.factory.common.UploadResModel;
import it.swiftelink.com.factory.model.health.EditHealthReportModel;
import it.swiftelink.com.factory.model.health.HealthDepartmentsResModel;
import it.swiftelink.com.factory.presenter.health.EditHealthReportContract;
import it.swiftelink.com.factory.presenter.health.EditHealthReportPresenter;
import it.swiftelink.com.vcs_member.App;
import it.swiftelink.com.vcs_member.R;
import it.swiftelink.com.vcs_member.base.BasePresenterActivity;
import it.swiftelink.com.vcs_member.weight.addressSelector.AddressSelector;
import it.swiftelink.com.vcs_member.weight.addressSelector.CityInterface;
import it.swiftelink.com.vcs_member.weight.addressSelector.Departments;
import it.swiftelink.com.vcs_member.weight.addressSelector.OnItemClickListener;

public class EditImageDataActivity extends BasePresenterActivity<EditHealthReportContract.Presenter> implements EditHealthReportContract.View {


    private static final int REQUEST_LIST_CODE = 0;
    private static final int REQUEST_CAMERA_CODE = 1;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_image_name)
    EditText etImageName;
    @BindView(R.id.et_image_part)
    EditText etImagePart;
    @BindView(R.id.et_image_organization)
    EditText etImageOrganization;
    @BindView(R.id.tv_image_department)
    TextView tvImageDepartment;
    @BindView(R.id.et_image_look)
    EditText etImageLook;
    @BindView(R.id.et_image_result)
    EditText etImageResult;
    @BindView(R.id.btn_submit)
    Button btnSubmit;
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

    private ImageView currentImage;
    private int currentPos;
    private List<String> mPathList = new ArrayList<>();

    private BaseNiceDialog mSelectDepartmentDialog;
    private String mSelectId;


    public static void show(Activity activity) {
        Intent intent = new Intent(activity, EditImageDataActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_input_image_data;
    }


    @Override
    protected void initWidget() {
        super.initWidget();
        tvTitle.setText(getString(R.string.label_Input_image_data));
        ISNav.getInstance().init(new ImageLoader() {
            @Override
            public void displayImage(Context context, String path, ImageView imageView) {
                Glide.with(context).load(path).into(imageView);
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();

    }

    @Override
    protected EditHealthReportContract.Presenter initPresenter() {
        return new EditHealthReportPresenter(this);
    }

    @Override
    protected StateView setStateView() {
        return stateView;
    }

    @OnClick({R.id.btn_back, R.id.tv_image_department, R.id.btn_submit, R.id.select_image1,
            R.id.select_image2, R.id.select_image3, R.id.select_image4, R.id.select_image5})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.tv_image_department:
                mPresenter.getDepartments(App.getSPUtils().getString(Common.SPApi.SELECT_LANGUAGE, Common.CommonStr.LANGUAGE1));
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

    private void submitData(String uploadUrl) {

        String name = etImageName.getText().toString().trim();
        String imagePart = etImagePart.getText().toString().trim();
        String organizationName = etImageOrganization.getText().toString().trim();
        String departmentName = tvImageDepartment.getText().toString().trim();//设置科室
        String imageLook = etImageLook.getText().toString().trim();
        String imageResult = etImageResult.getText().toString().trim();

        if (TextUtils.isEmpty(name)) {

            App.showToast(R.string.msg_input_examine_name);
            return;
        }

        if (TextUtils.isEmpty(organizationName)) {
            App.showToast(R.string.msg_input_medical_institutions);
            return;
        }

        if (TextUtils.isEmpty(departmentName)) {
            App.showToast(R.string.label_notice_select_department);
            return;
        }

        if (TextUtils.isEmpty(imageLook)) {
            App.showToast(R.string.msg_input_examine_look);
            return;
        }

        if (TextUtils.isEmpty(imageResult)) {
            App.showToast(R.string.label_please_image_result);
            return;
        }


        EditHealthReportModel reportModel = new EditHealthReportModel();

        reportModel.setType(Common.CommonStr.HEALTH_REPORT_TYPE1);
        reportModel.setName(name);
        reportModel.setHospital(organizationName);
        reportModel.setAppearance(imageLook);
        reportModel.setConclusion(imageResult);
        reportModel.setSectionOfficeId(mSelectId);
        reportModel.setImageLocation(imagePart);
        reportModel.setFileId(uploadUrl);

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
                .maxNum(9)
                .build();

        ISNav.getInstance().toListActivity(this, config, REQUEST_LIST_CODE);
    }


    private ArrayList<Departments> topStrs = new ArrayList<>();
    private ArrayList<Departments> sonStrs = new ArrayList<>();


    private void selectDistrict(HealthDepartmentsResModel resModel) {

        final List<HealthDepartmentsResModel.DataBean> beanList = resModel.getData();

        if (beanList != null && beanList.size() > 0) {
            topStrs.clear();
            for (int i = 0; i < beanList.size(); i++) {
                HealthDepartmentsResModel.DataBean dataBean = beanList.get(i);
                Departments departments = new Departments();
                departments.setName(dataBean.getName());
                departments.setId(dataBean.getId());
                departments.setPos(i);
                topStrs.add(departments);


            }
            mSelectDepartmentDialog = CustomDialog.
                    newInstance(R.layout.dialog_select_address)
                    .setViewCreateListner(new CustomDialog.ViewCreateListener() {

                        @Override
                        public void onViewCreate(ViewGroup viewGroup, final BaseNiceDialog dialog) {

                            AddressSelector mAddressSelector = viewGroup.findViewById(R.id.address);

                            mAddressSelector.setTabAmount(2);
                            mAddressSelector.setCities(topStrs);

                            mAddressSelector.setOnTabSelectedListener(new AddressSelector.OnTabSelectedListener() {
                                @Override
                                public void onTabSelected(AddressSelector addressSelector, AddressSelector.Tab tab) {
                                    switch (tab.getIndex()) {
                                        case 0:
                                            addressSelector.setCities(topStrs);
                                            break;
                                    }
                                }

                                @Override
                                public void onTabReselected(AddressSelector addressSelector, AddressSelector.Tab tab) {

                                }
                            });

                            mAddressSelector.setOnItemClickListener(new OnItemClickListener() {
                                @Override
                                public void itemClick(AddressSelector addressSelector, CityInterface city, int tabPosition) {
                                    switch (tabPosition) {
                                        case 0:
                                            sonStrs.clear();
                                            HealthDepartmentsResModel.DataBean dataBean = beanList.get(city.getPos());
                                            List<HealthDepartmentsResModel.DataBean.ChildrenBean> sectionOfficeList = dataBean.getChildren();

                                            for (HealthDepartmentsResModel.DataBean.ChildrenBean data : sectionOfficeList) {

                                                Departments departments = new Departments();
                                                departments.setName(data.getName());
                                                departments.setId(data.getId());
                                                sonStrs.add(departments);

                                            }
                                            addressSelector.setCities(sonStrs);

                                            break;
                                        case 1:

                                            Departments sonDepartment = (Departments) city;
                                            mSelectId = sonDepartment.getId();
                                            tvImageDepartment.setText(sonDepartment.getCityName());
                                            mSelectDepartmentDialog.dismiss();

                                            break;

                                    }
                                }

                            });
                        }
                    })
                    .setOutCancel(true)
                    .setShowBottom(true).show(getSupportFragmentManager());
        }


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
        showContent();
        selectDistrict(resModel);
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
    public void showError(int type,int errorCode , String errorMsg) {
        showContent();
        App.showToast(errorMsg);
    }


}
