package it.swiftelink.com.vcs_member.ui.activity.user;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ycuwq.datepicker.date.DatePickerDialogFragment;
import com.yuyh.library.imgsel.ISNav;
import com.yuyh.library.imgsel.common.ImageLoader;
import com.yuyh.library.imgsel.config.ISListConfig;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.common.utils.GlideLoadUtils;
import it.swiftelink.com.common.utils.TxtUtils;
import it.swiftelink.com.common.widget.dialog.CustomDialog;
import it.swiftelink.com.common.widget.nicedialog.BaseNiceDialog;
import it.swiftelink.com.common.widget.stateview.StateView;
import it.swiftelink.com.factory.common.UploadResModel;
import it.swiftelink.com.factory.model.user.EditUserInfoModel;
import it.swiftelink.com.factory.model.user.UserInfoResModel;
import it.swiftelink.com.factory.presenter.main.UserInfoContract;
import it.swiftelink.com.factory.presenter.main.UserInfoPresenter;
import it.swiftelink.com.vcs_member.App;
import it.swiftelink.com.vcs_member.R;
import it.swiftelink.com.vcs_member.base.BasePresenterActivity;
import it.swiftelink.com.vcs_member.ui.activity.account.ChangeLoginPasswordActivity;
import it.swiftelink.com.vcs_member.ui.activity.account.SetPayPsdActivity;
import it.swiftelink.com.vcs_member.utils.DateTimeUtils;
import it.swiftelink.com.vcs_member.weight.MyDatePickerDialog;

public class PersonInfoActivity extends BasePresenterActivity<UserInfoContract.Presenter> implements UserInfoContract.View {

    @BindView(R.id.stateView)
    StateView stateView;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_top_right)
    TextView tvTopRight;
    @BindView(R.id.iv_header)
    ImageView ivHeader;
    @BindView(R.id.iv_select_head_enter)
    ImageView ivSelectHeadEnter;
    @BindView(R.id.et_user_name)
    EditText etUserName;
    @BindView(R.id.tv_user_sex)
    TextView tvUserSex;
    @BindView(R.id.iv_select_gender_enter)
    ImageView ivSelectGenderEnter;
    @BindView(R.id.tv_user_birth)
    TextView tvUserBirth;
    @BindView(R.id.iv_user_birth)
    ImageView ivUserBirth;
    @BindView(R.id.tv_user_age)
    TextView tvUserAge;
    @BindView(R.id.et_ID_num)
    EditText etIDNum;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.tv_marital_status)
    TextView tvMaritalStatus;
    @BindView(R.id.iv_select_marital_status)
    ImageView ivSelectMaritalStatus;


    private boolean isEditStatus = false;
    private static final int REQUEST_LIST_CODE = 0;
    private static final int REQUEST_CAMERA_CODE = 1;
    private ImageView currentImagew;
    private String path;
    private String url;
    private UserInfoResModel.DataBean data;


    public static void show(Activity activity) {

        activity.startActivity(new Intent(activity, PersonInfoActivity.class));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_person_info;
    }

    @Override
    protected void initWidget() {
        super.initWidget();

        tvTitle.setText(getString(R.string.label_user_info));
        setEditStatus(false);

        ISNav.getInstance().init(new ImageLoader() {
            @Override
            public void displayImage(Context context, String path, ImageView imageView) {
                Glide.with(context).load(path).into(imageView);
            }
        });
    }

    private void setEditStatus(boolean isEdit) {
        isEditStatus = isEdit;
        if (isEdit) {
            tvTopRight.setText(getString(R.string.label_save));
            tvTopRight.setVisibility(View.VISIBLE);

            ivHeader.setEnabled(true);
            tvUserSex.setEnabled(true);
            tvUserBirth.setEnabled(true);
            tvMaritalStatus.setEnabled(true);

            ivSelectHeadEnter.setVisibility(View.VISIBLE);
            ivSelectGenderEnter.setVisibility(View.VISIBLE);
            ivUserBirth.setVisibility(View.VISIBLE);
            ivSelectMaritalStatus.setVisibility(View.VISIBLE);

            etUserName.setFocusableInTouchMode(true);
            etUserName.setFocusable(true);
            etUserName.requestFocus();
            etUserName.setSelection(etUserName.getText().toString().length());

            etIDNum.setFocusableInTouchMode(true);
            etIDNum.setFocusable(true);
            etIDNum.requestFocus();
            etIDNum.setSelection(etIDNum.getText().toString().length());



            if(!TextUtils.isEmpty(etPhone.getText())){
                etPhone.setFocusable(false);
                etPhone.setFocusableInTouchMode(false);
            }else{
                etPhone.setFocusableInTouchMode(true);
                etPhone.setFocusable(true);
                etPhone.requestFocus();
                etPhone.setSelection(etPhone.getText().toString().length());
            }

        } else {
            tvTopRight.setText(getString(R.string.label_edit));
            tvTopRight.setVisibility(View.VISIBLE);


            ivHeader.setEnabled(false);
            tvUserSex.setEnabled(false);
            tvUserBirth.setEnabled(false);
            tvMaritalStatus.setEnabled(false);

            ivSelectHeadEnter.setVisibility(View.GONE);
            ivSelectGenderEnter.setVisibility(View.GONE);
            ivUserBirth.setVisibility(View.GONE);
            ivSelectMaritalStatus.setVisibility(View.GONE);


            etUserName.setFocusable(false);
            etUserName.setFocusableInTouchMode(false);

            etIDNum.setFocusable(false);
            etIDNum.setFocusableInTouchMode(false);

            etPhone.setFocusable(false);
            etPhone.setFocusableInTouchMode(false);


        }
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter.getUserInfo();
    }


    @Override
    protected UserInfoContract.Presenter initPresenter() {
        return new UserInfoPresenter(this);
    }

    @Override
    protected StateView setStateView() {
        return stateView;
    }

    @Override
    public void getUserInfoSuccess(UserInfoResModel model) {
        showContent();
        data = model.getData();

        GlideLoadUtils.getInstance().glideLoadCircle(this, data.getHeadImg(), ivHeader, R.mipmap.icon_header);

        if (!TextUtils.isEmpty(data.getName())) {
            etUserName.setText(data.getName());
        } else {
            etUserName.setHint(getString(R.string.msg_please_input_name));
        }

        if (!TextUtils.isEmpty(data.getIdentityNumber())) {
            etIDNum.setText(data.getIdentityNumber());
        } else {
            etIDNum.setHint(getString(R.string.please_input_id_card));
        }

        if (!TextUtils.isEmpty(data.getTel())) {
            etPhone.setText(data.getTel());
        } else {
            etPhone.setHint(getString(R.string.please_input_phone));
        }

        if (!TextUtils.isEmpty(data.getGender())) {
            tvUserSex.setText(data.getGender().equals("MALE") ? getString(R.string.label_male) : getString(R.string.label_female));
        } else {
            tvUserSex.setHint(getString(R.string.please_set_gender));
        }

        if (!TextUtils.isEmpty(data.getBirthday())) {
            String birth = DateTimeUtils.getDateToStringDay(data.getBirthday() + "");
            tvUserBirth.setText(birth);
            String age = DateTimeUtils.getAge(birth,this);
            tvUserAge.setText(age);
        } else {
            tvUserBirth.setHint(getString(R.string.please_set_birth));
        }

        if (!TextUtils.isEmpty(data.getMaritalStatus())) {
            tvMaritalStatus.setText(data.getMaritalStatus().equals("Y") ? getString(R.string.label_be_married) : getString(R.string.label_spinsterhood));
        } else {
            tvMaritalStatus.setHint(getString(R.string.please_set_married_statue));
        }

    }

    @Override
    public void EditUserInfoSuccess() {
        setEditStatus(false);
        showContent();

    }

    @Override
    public void uploadImageSuccess(UploadResModel resModel) {
        if (resModel.getFiles() != null && resModel.getFiles().size() > 0 && resModel.getFiles().get(0) != null) {
            UploadResModel.FilesBean filesBean = resModel.getFiles().get(0);
            url = filesBean.getUrl();
        }

        saveChange();

    }

    @Override
    public void retry(int type) {

    }

    @Override
    public void showError(int type, int errorCode ,String errorMsg) {
        super.showError(type,errorCode, errorMsg);
        showContent();
        App.showToast(errorMsg);
    }


    @OnClick({R.id.btn_back, R.id.tv_top_right, R.id.iv_header, R.id.iv_select_head_enter,
            R.id.tv_user_sex, R.id.iv_select_gender_enter, R.id.tv_user_birth, R.id.iv_user_birth,
            R.id.tv_marital_status, R.id.iv_select_marital_status, R.id.ll_set_address,
            R.id.ll_change_psd, R.id.ll_change_pay_psd})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.tv_top_right:
                if (isEditStatus) {
                    uploadImage();
                } else {
                    setEditStatus(true);
                }
                break;
            case R.id.iv_header:
            case R.id.iv_select_head_enter:
                selectPicture(ivHeader);
                break;
            case R.id.tv_user_sex:
            case R.id.iv_select_gender_enter:
                setGender();
                break;
            case R.id.tv_user_birth:
            case R.id.iv_user_birth:
                setBirth();
                break;
            case R.id.tv_marital_status:
            case R.id.iv_select_marital_status:
                setMaritalStatus();
                break;
            case R.id.ll_set_address:
                MyAddressActivity.show(this, -1);
                break;
            case R.id.ll_change_psd:
                ChangeLoginPasswordActivity.show(this);
                break;
            case R.id.ll_change_pay_psd:
                SetPayPsdActivity.show(this, Common.CommonStr.Pay_PSD_TYPE2);
                break;
        }
    }

    private void uploadImage() {
        if (!TextUtils.isEmpty(path)) {
            mPresenter.uploadImage(path);
        } else {
            saveChange();
        }
    }

    private void selectPicture(ImageView currentImagew) {
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
                .maxNum(1)
                .build();

        this.currentImagew = currentImagew;

        ISNav.getInstance().toListActivity(this, config, REQUEST_LIST_CODE);
    }

    private void setGender() {
        CustomDialog.
                newInstance(R.layout.dialog_select_gender)
                .setViewCreateListner(new CustomDialog.ViewCreateListener() {

                    @Override
                    public void onViewCreate(ViewGroup viewGroup, final BaseNiceDialog dialog) {


                        final TextView tv_gender1 = viewGroup.findViewById(R.id.tv_gender1);
                        final TextView tv_gender2 = viewGroup.findViewById(R.id.tv_gender2);


                        tv_gender1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                tvUserSex.setText(getString(R.string.label_male));
                                dialog.dismiss();
                            }
                        });

                        tv_gender2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                tvUserSex.setText(getString(R.string.label_female));
                                dialog.dismiss();

                            }
                        });


                    }
                })
                .setOutCancel(true)
                .setShowBottom(true)
                .show(getSupportFragmentManager());
    }

    private void setMaritalStatus() {
        CustomDialog.
                newInstance(R.layout.dialog_select_marital_status)
                .setViewCreateListner(new CustomDialog.ViewCreateListener() {

                    @Override
                    public void onViewCreate(ViewGroup viewGroup, final BaseNiceDialog dialog) {
                        final TextView tv_marital_status1 = viewGroup.findViewById(R.id.tv_marital_status1);
                        final TextView tv_marital_status2 = viewGroup.findViewById(R.id.tv_marital_status2);


                        tv_marital_status1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                tvMaritalStatus.setText(getString(R.string.label_be_married));
                                dialog.dismiss();
                            }
                        });

                        tv_marital_status2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                tvMaritalStatus.setText(getString(R.string.label_spinsterhood));
                                dialog.dismiss();

                            }
                        });

                    }
                })
                .setOutCancel(true)
                .setShowBottom(true)
                .show(getSupportFragmentManager());
    }


    private void setBirth() {

        MyDatePickerDialog datePickerDialogFragment = new MyDatePickerDialog();
        datePickerDialogFragment.setOnDateChooseListener(new DatePickerDialogFragment.OnDateChooseListener() {
            @Override
            public void onDateChoose(int year, int month, int day) {
                StringBuffer birthTime = new StringBuffer();
                if(month<10){
                    birthTime.append(year + "-" + "0" + month + "-" );
                }else {
                    birthTime.append(year + "-" + month + "-" );
                }

                if(day<10){
                    birthTime.append("0" + day);
                }else {
                    birthTime.append(day);
                }
                tvUserBirth.setText(birthTime);
                String age = DateTimeUtils.getAge(birthTime.toString(),PersonInfoActivity.this);
                tvUserAge.setText(age);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    Date date = simpleDateFormat.parse(year + "-" + month + "-" + day);
                    long time = date.getTime();
                    data.setBirthday(String.valueOf(time));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
        datePickerDialogFragment.show(getSupportFragmentManager(), "DatePickerDialogFragment");

        if(null!=data && !TextUtils.isEmpty(data.getBirthday())){

            long timeL = Long.parseLong(data.getBirthday());
            datePickerDialogFragment.setBirthDay(timeL);
        }
    }

    private void saveChange() {

        String userName = etUserName.getText().toString().trim();
        String userGander = tvUserSex.getText().toString().trim();
        String userBirth = tvUserBirth.getText().toString().trim();
        String userAge = tvUserAge.getText().toString().trim();
        String userIdCard = etIDNum.getText().toString().trim();
        String phone = etPhone.getText().toString().trim();
        String maritalStatus = tvMaritalStatus.getText().toString().trim();



//        if (!TextUtils.isEmpty(phone) && !TxtUtils.isPhoneLegal(phone)) {
//            App.showToast(R.string.please_input_phone_right);
//            return;
//        }

        EditUserInfoModel infoModel = new EditUserInfoModel();


        if (!TextUtils.isEmpty(userName)) {
            infoModel.setName(userName);
        }else {
            App.showToast(R.string.label_username_can_not_empty);
            return;
        }

        if (!TextUtils.isEmpty(userGander)) {
            infoModel.setGender(getString(R.string.label_male).equals(userGander) ? "MALE" : "FEMALE");
        }

        if (!TextUtils.isEmpty(userBirth)) {
            infoModel.setBirthday(userBirth);
            infoModel.setAge(userAge);
        }

        if (!TextUtils.isEmpty(userIdCard)) {
            infoModel.setIdentityNumber(userIdCard);
        }

        if (!TextUtils.isEmpty(phone)) {
            infoModel.setTel(phone);
        }

        if (!TextUtils.isEmpty(maritalStatus)) {
            infoModel.setMaritalStatus(getString(R.string.label_be_married).equals(maritalStatus) ? "Y" : "N");
        }

        if (!TextUtils.isEmpty(url)) {
            infoModel.setHeadImg(url);
        } else {
            if (!TextUtils.isEmpty(data.getHeadImg())) {
                infoModel.setHeadImg(data.getHeadImg());
            }
        }
        String userId = App.getSPUtils().getString(Common.SPApi.ID);

        mPresenter.EditUserInfo(userId, infoModel);

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (REQUEST_LIST_CODE == requestCode && resultCode == RESULT_OK && data != null) {
            List<String> pathList = data.getStringArrayListExtra("result");

            GlideLoadUtils.getInstance().glideLoadCircle(this, pathList.get(0), currentImagew, R.mipmap.ic_launcher);

            path = pathList.get(0);

        } else if (requestCode == REQUEST_CAMERA_CODE && resultCode == RESULT_OK && data != null) {
            path = data.getStringExtra("result");

            GlideLoadUtils.getInstance().glideLoadCircle(this, path, currentImagew, R.mipmap.ic_launcher);
        }
    }


}
