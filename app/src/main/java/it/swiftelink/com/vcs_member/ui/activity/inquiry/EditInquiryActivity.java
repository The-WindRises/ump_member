package it.swiftelink.com.vcs_member.ui.activity.inquiry;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ycuwq.datepicker.date.DatePickerDialogFragment;
import com.yuyh.library.imgsel.ISNav;
import com.yuyh.library.imgsel.config.ISListConfig;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import it.swiftelink.com.common.app.Application;
import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.common.utils.GlideLoadUtils;
import it.swiftelink.com.common.utils.RxNetTool;
import it.swiftelink.com.common.widget.dialog.ConfirmDialog;
import it.swiftelink.com.common.widget.dialog.CustomDialog;
import it.swiftelink.com.common.widget.nicedialog.BaseNiceDialog;
import it.swiftelink.com.common.widget.stateview.StateView;
import it.swiftelink.com.factory.model.doctor.DiagnosisDoctorResModel;
import it.swiftelink.com.factory.model.inquiry.EditInquiryModel;
import it.swiftelink.com.factory.model.inquiry.EditInquiryResModel;
import it.swiftelink.com.factory.model.inquiry.EditInquiryTagResModel;
import it.swiftelink.com.factory.model.inquiry.ToInquiryResModel;
import it.swiftelink.com.factory.model.order.IsHKResModel;
import it.swiftelink.com.factory.presenter.inquiry.EditInquiryContract;
import it.swiftelink.com.factory.presenter.inquiry.EditInquiryPresenter;
import it.swiftelink.com.vcs_member.App;
import it.swiftelink.com.vcs_member.R;
import it.swiftelink.com.vcs_member.base.BasePresenterActivity;
import it.swiftelink.com.vcs_member.mqtt.MqttService;
import it.swiftelink.com.vcs_member.ui.activity.videoChat.VideoChatActivity;
import it.swiftelink.com.vcs_member.utils.DateTimeUtils;
import it.swiftelink.com.vcs_member.weight.LastInputEditText;
import it.swiftelink.com.vcs_member.weight.MyDatePickerDialog;
import it.swiftelink.com.vcs_member.weight.MyScrollView;

public class EditInquiryActivity extends BasePresenterActivity<EditInquiryContract.Presenter> implements EditInquiryContract.View {


    private static final int REQUEST_LIST_CODE = 0;
    private static final int REQUEST_CAMERA_CODE = 1;

    private static final String TAG = "EditInquiryActivity";

    private static String selectInquiryType;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.et_user_name)
    LastInputEditText etUserName;
    @BindView(R.id.tv_select_man)
    TextView tvSelectMan;
    @BindView(R.id.tv_select_women)
    TextView tvSelectWomen;
    @BindView(R.id.tv_user_birth)
    TextView tvUserBirth;
    @BindView(R.id.tv_select_birth)
    TextView tvSelectBirth;
    @BindView(R.id.tv_user_age)
    TextView tvUserAge;
    @BindView(R.id.tv_select_spinsterhood)
    TextView tvSelectSpinsterhood;
    @BindView(R.id.tv_select_married)
    TextView tvSelectMarried;
    @BindView(R.id.et_user_wight)
    LastInputEditText etUserWight;
    @BindView(R.id.et_inquiry_desc)
    LastInputEditText etInquiryDesc;
    @BindView(R.id.iv_inquiry1)
    ImageView ivInquiry1;
    @BindView(R.id.iv_inquiry2)
    ImageView ivInquiry2;
    @BindView(R.id.iv_inquiry3)
    ImageView ivInquiry3;
    @BindView(R.id.iv_inquiry4)
    ImageView ivInquiry4;
    @BindView(R.id.iv_inquiry5)
    ImageView ivInquiry5;
    @BindView(R.id.stateView)
    StateView stateView;
    @BindView(R.id.ll_language1)
    LinearLayout llLanguage1;
    @BindView(R.id.ll_language2)
    LinearLayout llLanguage2;
    @BindView(R.id.ll_language3)
    LinearLayout llLanguage3;
    @BindView(R.id.fl_edit_inquiry)
    TagFlowLayout flEditInquiry;
    @BindView(R.id.scroll_edit_inquiry)
    MyScrollView scrollEditInquiry;
    @BindView(R.id.tv_warning2)
    TextView tvWarning2;
    @BindView(R.id.tv_warning1)
    TextView tvWarning1;
    @BindView(R.id.doctor_tag_layout)
    TagFlowLayout doctorTagLayout;
    @BindView(R.id.doctor_ll)
    LinearLayout doctorLl;
    @BindView(R.id.doctor_change_another)
    LinearLayout doctorChangeLl;



    private ImageView currentImagew;
    private ToInquiryResModel.DataBean.InfoBean info;
    private String enterType;

    private TagAdapter<String> mTagAdapter;

    private int page = 1;

    private int pageSize = 10;

    private int totalPage;

    boolean isTagClick = false;

    private int doctorTotalPage;

    private int doctorCurPage;



    private List<String> tagList;
    private List<String> descList = new ArrayList<>();
    private HashSet<Integer> selectTagBeanList = new HashSet<>();
    List<DiagnosisDoctorResModel.DataBean.DataListBean> dataListBeans;
    private String selectDoctorId;
    public static void show(Activity activity, String enterType) {
        Intent intent = new Intent(activity, EditInquiryActivity.class);

        intent.putExtra("enterType", enterType);
        activity.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_edit_inquiry;
    }


    @Override
    protected void initWidget() {
        super.initWidget();

        tvTitle.setText(getString(R.string.label_fill_inquiry_information));
        dataListBeans=new ArrayList<>();

        flEditInquiry.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                EditInquiryActivity.this.onTagClick(position);
                return true;
            }
        });
        initDoctorList();
        etInquiryDesc.addTextChangedListener(new TextWatcher() {
            private int biginLength;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                biginLength = s.length();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


            }

            @Override
            public void afterTextChanged(Editable s) {
                int endLength = s.length();
                if (biginLength > endLength && !isTagClick) {
                    onDelete();
                }

                if (s.length() >= 15) {
                    App.showToast(R.string.label_most_15_words);
                }
            }
        });

        llLanguage1.setSelected(true);
//        llLanguage2.setEnabled(false);
//        llLanguage3.setEnabled(false);

    }


    private void onTagLoaded() {
        descList.clear();
        selectTagBeanList.clear();

        ArrayList<String> splitList = new ArrayList<>();
        String desc = etInquiryDesc.getText().toString();
        String[] split = desc.split(",");

        for (int i = 0; i < split.length; i++) {
            String str = split[i];
            splitList.add(str);
            descList.add(str);
        }

        if (tagList != null && tagList.size() > 0) {
            for (int i = 0; i < tagList.size(); i++) {
                String tagStr = tagList.get(i);
                if (splitList.contains(tagStr)) {
                    selectTagBeanList.add(i);
                }
            }
            updateEditText();
        }

    }

    private void onDelete() {
        etInquiryDesc.setText("");
        selectTagBeanList.clear();
        descList.clear();
        if (tagList != null && tagList.size() > 0) {
            updateEditText();
        }
    }


    private void onTagClick(int pos) {

        int oldLength = etInquiryDesc.getText().length();
        int selectTagLength = tagList.get(pos).length();

        String oldDesc = etInquiryDesc.getText().toString();
        String[] split = oldDesc.split(",");


        for (String oldItem : split) {
            if (!descList.contains(oldItem)) {
                descList.add(oldItem);
            }
        }

        if (!descList.contains(tagList.get(pos))) {

            if (oldLength + selectTagLength < 15) {
                descList.add(tagList.get(pos));
                selectTagBeanList.add(pos);
            } else {
                App.showToast(R.string.label_most_15_words);
            }

        } else if (descList.contains(tagList.get(pos))) {
            descList.remove(tagList.get(pos));
            selectTagBeanList.remove(pos);
        }
        isTagClick = true;
        updateEditText();
    }


    private StringBuffer stringBuffer;

    private void updateEditText() {
        mTagAdapter.setSelectedList(selectTagBeanList);
        if (stringBuffer == null) {
            stringBuffer = new StringBuffer();
        }
        if (stringBuffer.length() > 0) {
            stringBuffer.delete(0, stringBuffer.length());
        }

        for (String descStr : descList) {
            stringBuffer.append("," + descStr);
        }

        while (stringBuffer.toString().startsWith(",")) {
            stringBuffer.deleteCharAt(0);
        }
        etInquiryDesc.setText(stringBuffer.toString());
        isTagClick = false;
    }


    @Override
    protected void initData() {
        super.initData();

        Intent intent1 = new Intent(this, MqttService.class);

        startService(intent1);

        Intent intent = getIntent();

        if (intent != null) {
            enterType = intent.getStringExtra("enterType");
        }

        mPresenter.toInquiry();
        mPresenter.getEditInquiryTag(page, pageSize);
        doctorCurPage=1;
        mPresenter.getCollectDoctor(4,doctorCurPage);
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
                .title(getString(R.string.label_select_pic))
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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (REQUEST_LIST_CODE == requestCode && resultCode == RESULT_OK && data != null) {
            List<String> pathList = data.getStringArrayListExtra("result");

            GlideLoadUtils.getInstance().glideLoad(this, pathList.get(0), currentImagew, R.mipmap.ic_launcher);

        } else if (requestCode == REQUEST_CAMERA_CODE && resultCode == RESULT_OK && data != null) {
            String path = data.getStringExtra("result");
            App.showToast("path:" + path);

            GlideLoadUtils.getInstance().glideLoad(this, path, currentImagew, R.mipmap.ic_launcher);
        }
    }


    private void inquiry(String status) {

        showContent();
        String userName = etUserName.getText().toString().trim();
        String gender = tvSelectMan.isSelected() ? "MALE" : "FEMALE";
        String birthday = tvUserBirth.getText().toString();
        String age = tvUserAge.getText().toString();
        String maritalStatus = tvSelectMarried.isSelected() ? "Y" : "N";
        String weight = etUserWight.getText().toString();
        String symptomDescription = etInquiryDesc.getText().toString();


        if (TextUtils.isEmpty(userName)) {
            App.showToast(R.string.msg_please_intpur_name);
            return;
        }

        if (TextUtils.isEmpty(birthday)) {
            App.showToast(R.string.msg_please_input_birth);
            return;
        }

        if (TextUtils.isEmpty(weight) && !age.endsWith(getString(R.string.label_years_of_age))) {
            App.showToast(R.string.msg_please_input_weight);
            return;
        }

        if (TextUtils.isEmpty(symptomDescription)) {
            App.showToast(R.string.msg_please_input_symptoms_described);
            return;
        }
//        StringBuffer stringBuffer = new StringBuffer();
        String language=null;
        if (llLanguage1.isSelected()) {
            language=Common.CommonStr.LANGUAGE1;
//            stringBuffer.append(Common.CommonStr.LANGUAGE1 + ",");
        }

        if (llLanguage2.isSelected()) {
            language=Common.CommonStr.LANGUAGE2;
//            stringBuffer.append(Common.CommonStr.LANGUAGE2 + ",");
        }

        if (llLanguage3.isSelected()) {
            language=Common.CommonStr.LANGUAGE3;
//            stringBuffer.append(Common.CommonStr.LANGUAGE3 + ",");
        }

        if (TextUtils.isEmpty(language)) {

            App.showToast(R.string.tip_input_inqury_language);
            return;

        }

        EditInquiryModel inquiryModel = new EditInquiryModel();

        if (info != null) {
            inquiryModel.setId(info.getId());
            inquiryModel.setNo(info.getNo());
        }


        inquiryModel.setDiagnosisLanguage(language);
        inquiryModel.setPatientName(userName);
        inquiryModel.setGender(gender);
        inquiryModel.setBirthday(birthday);
        inquiryModel.setAge(age);
        inquiryModel.setMaritalStatus(maritalStatus);
        inquiryModel.setWeight(weight);
        inquiryModel.setSymptomDescription(symptomDescription);
        inquiryModel.setStatus(status);
        inquiryModel.setCollectionDoctor(selectDoctorId);

        showConfirmDialog(inquiryModel);


    }

    @Override
    protected EditInquiryContract.Presenter initPresenter() {
        return new EditInquiryPresenter(this);
    }

    @Override
    protected StateView setStateView() {
        return stateView;
    }


    @Override
    public void retry(int type) {

    }

    @Override
    public void showError(int type, int errorCode, String errorMsg) {
        super.showError(type, errorCode, errorMsg);
        showContent();
        App.showToast(errorMsg);

        if (type == Common.UrlApi.TO_INQUIRY) {
            llLanguage1.setSelected(true);
        }
    }

    @Override
    public void toInquirySuccess(ToInquiryResModel model) {
        showContent();

        if (model.getData() != null && model.getData().getInfo() != null) {
            info = model.getData().getInfo();
            if (TextUtils.isEmpty(info.getDiagnosisLanguage())) {
                llLanguage1.setSelected(true);
            }
            etUserName.setText(info.getName());
            etUserName.setSelection(etUserName.getText().length());
            if (!TextUtils.isEmpty(info.getBirthday())) {
                String birthDay = DateTimeUtils.getDateToStringDay(info.getBirthday() + "");
                tvUserBirth.setText(birthDay);
                String age = DateTimeUtils.getAge(birthDay, this);
                tvUserAge.setText(age);
            } else {
                tvUserBirth.setText("");
                tvUserAge.setText("");
            }

            String diagnosisLanguage = info.getDiagnosisLanguage();
//            if (!TextUtils.isEmpty(diagnosisLanguage)) {
//                if (diagnosisLanguage.contains(Common.CommonStr.LANGUAGE1)) {
//                    llLanguage1.setSelected(true);
//                } else {
//                    llLanguage1.setSelected(false);
//                }
//
//                if (diagnosisLanguage.contains(Common.CommonStr.LANGUAGE2)) {
//                    llLanguage2.setSelected(true);
//                } else {
//                    llLanguage2.setSelected(false);
//                }
//
//                if (diagnosisLanguage.contains(Common.CommonStr.LANGUAGE3)) {
//                    llLanguage3.setSelected(true);
//                } else {
//                    llLanguage3.setSelected(false);
//                }
//            }


            if (!TextUtils.isEmpty(info.getGender()) && "MALE".equals(info.getGender())) {
                tvSelectMan.setSelected(true);
            } else {
                tvSelectWomen.setSelected(true);
            }

            if (!TextUtils.isEmpty(info.getMaritalStatus()) && "Y".equals(info.getMaritalStatus())) {
                tvSelectMarried.setSelected(true);
            } else {
                tvSelectSpinsterhood.setSelected(true);
            }
            etUserWight.setText(info.getWeight() == 0 ? "" : info.getWeight() + "");
            etUserWight.setSelection(etUserWight.getText().length());

            if (!TextUtils.isEmpty(info.getSymptomDescription())) {
                etInquiryDesc.setText(info.getSymptomDescription());
                onTagLoaded();
            }

        } else {
            llLanguage1.setSelected(true);
        }
    }

    @Override
    public void editInquirySuccess(EditInquiryResModel model) {
        showContent();
        finish();
    }

    @Override
    public void getEditInquirySuccess(EditInquiryTagResModel model) {

        if (model.getData() != null && model.getData().getSymptomDescription() != null) {
            tagList = model.getData().getSymptomDescription();

            totalPage = model.getData().getTotalPages();
            setFlowTag(tagList);
        }
    }

    @Override
    public void getCollectDoctorSuccess(DiagnosisDoctorResModel diagnosisDoctorResModel) {
        if(null!=diagnosisDoctorResModel.getData()){
            List<DiagnosisDoctorResModel.DataBean.DataListBean> listBeans=diagnosisDoctorResModel.getData().getDataList();
            if(null!=listBeans && listBeans.size()>0){
                doctorLl.setVisibility(View.VISIBLE);
                doctorTotalPage=diagnosisDoctorResModel.getData().getTotalPages();
            }else {
                doctorLl.setVisibility(View.GONE);
            }
        }
        dataListBeans.clear();
        dataListBeans.addAll(diagnosisDoctorResModel.getData().getDataList());
        doctorTagLayout.onChanged();
    }

    @Override
    public void getIsHK(IsHKResModel isHKResModel) {
        if(null != isHKResModel.getData() && null != isHKResModel.getData().getErrorMsg()){
            //
            switch (App.getSPUtils().getString(Common.SPApi.SELECT_LANGUAGE)) {
                case Common.CommonStr.LANGUAGE1:
                    showConfirmHkDialog(isHKResModel.getData().getErrorMsg().getZh_CN());
                    break;
                case Common.CommonStr.LANGUAGE2:
                    showConfirmHkDialog(isHKResModel.getData().getErrorMsg().getZh_TW());
                    break;
                case Common.CommonStr.LANGUAGE3:
                    showConfirmHkDialog(isHKResModel.getData().getErrorMsg().getEn_US());
                    break;
            }
        }else {
            inquiry(Common.CommonStr.INQUIRY_TYPE4);
            selectInquiryType = Common.CommonStr.INQUIRY_TYPE4;
        }
    }

    private void initDoctorList(){

        TagAdapter<DiagnosisDoctorResModel.DataBean.DataListBean> adapter=new TagAdapter<DiagnosisDoctorResModel.DataBean.DataListBean>(dataListBeans) {
            @Override
            public View getView(FlowLayout parent, int position, DiagnosisDoctorResModel.DataBean.DataListBean dataListBean) {
                TextView view = (TextView) LayoutInflater.from(EditInquiryActivity.this).inflate(R.layout.item_flow_edit_inquiry,
                        parent, false);
                if(dataListBean.getOnlineStatus().equals("Offline")){
                    view.setBackground(getDrawable(R.drawable.btn_grey1));
                    view.setTextColor(getResources().getColor(R.color.textColor34));
                }else if(dataListBean.getOnlineStatus().equals("Online")){
                    view.setBackground(getDrawable(R.drawable.flow_edit_inquiry_bg));
                    view.setTextColor(getResources().getColor(R.color.color_main_tab));
                }
                view.setText(dataListBean.getDoctorName());

                return view;
            }

            @Override
            public void onSelected(int position, View view) {
                Log.i("lqi","选择的医生position："+position+"Id:"+dataListBeans.get(position).getDoctorId());
                selectDoctorId=dataListBeans.get(position).getDoctorId();
            }

            @Override
            public void unSelected(int position, View view) {
                selectDoctorId="";
            }
        };



        doctorTagLayout.setAdapter(adapter);

    }

    @OnClick({R.id.btn_back, R.id.iv_name_clear, R.id.tv_select_man,
            R.id.tv_select_women, R.id.tv_select_birth, R.id.tv_select_spinsterhood, R.id.tv_select_married,
            R.id.iv_inquiry1, R.id.iv_select_inquiry1, R.id.iv_inquiry2, R.id.iv_select_inquiry2,
            R.id.iv_inquiry3, R.id.iv_select_inquiry3, R.id.iv_inquiry4, R.id.iv_select_inquiry4,
            R.id.iv_inquiry5, R.id.iv_select_inquiry5, R.id.tv_inquiry_now,
            R.id.ll_language1, R.id.ll_language2, R.id.ll_language3, R.id.ll_change_another, R.id.doctor_change_another})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                 inquiry(Common.CommonStr.INQUIRY_TYPE1);
                 selectInquiryType = Common.CommonStr.INQUIRY_TYPE1;
//                finish();
                break;
            case R.id.iv_name_clear:
                etUserName.setText("");
                etUserName.setHint(getString(R.string.msg_please_intpur_name));
                break;
            case R.id.tv_select_man:
                tvSelectMan.setSelected(true);
                tvSelectWomen.setSelected(false);
                break;
            case R.id.tv_select_women:
                tvSelectMan.setSelected(false);
                tvSelectWomen.setSelected(true);
                break;
            case R.id.tv_select_birth:
                setBirth();
                break;
            case R.id.tv_select_spinsterhood:
                tvSelectMarried.setSelected(false);
                tvSelectSpinsterhood.setSelected(true);
                break;
            case R.id.tv_select_married:
                tvSelectMarried.setSelected(true);
                tvSelectSpinsterhood.setSelected(false);
                break;
            case R.id.iv_inquiry1:
            case R.id.iv_select_inquiry1:
                selectPicture(ivInquiry1);
                break;
            case R.id.iv_inquiry2:
            case R.id.iv_select_inquiry2:
                selectPicture(ivInquiry2);
                break;
            case R.id.iv_inquiry3:
            case R.id.iv_select_inquiry3:
                selectPicture(ivInquiry3);
                break;
            case R.id.iv_inquiry4:
            case R.id.iv_select_inquiry4:
                selectPicture(ivInquiry4);
                break;
            case R.id.iv_inquiry5:
            case R.id.iv_select_inquiry5:
                selectPicture(ivInquiry5);
                break;
//            case R.id.tv_inquiry_later:

//                break;
            case R.id.tv_inquiry_now:
                mPresenter.getIsHK("");
                break;
            case R.id.ll_language1:
                llLanguage1.setSelected(true);
                llLanguage2.setSelected(false);
                llLanguage3.setSelected(false);
                break;
            case R.id.ll_language2:
                llLanguage1.setSelected(false);
                llLanguage2.setSelected(true);
                llLanguage3.setSelected(false);
                break;
            case R.id.ll_language3:
                llLanguage1.setSelected(false);
                llLanguage2.setSelected(false);
                llLanguage3.setSelected(true);
                break;

            case R.id.ll_change_another:

                if (page >= totalPage) {
                    page = 1;
                    mPresenter.getEditInquiryTag(page, pageSize);
                } else {
                    page++;
                    mPresenter.getEditInquiryTag(page, pageSize);
                }
                break;

            case R.id.doctor_change_another:
                if(doctorCurPage<doctorTotalPage){
                    doctorCurPage++;
                    mPresenter.getCollectDoctor(4,doctorCurPage);
                }else {
                    doctorCurPage = 1;
                    mPresenter.getCollectDoctor(4,doctorCurPage);
                }

                break;

        }
    }

    private void showConfirmDialog(final EditInquiryModel inquiryModel) {

        ConfirmDialog.newInstance(getString(R.string.label_confirm_edit_inquiry_info),
                getString(R.string.cancel), getString(R.string.confirm), true)
                .setConfirmSelect(new ConfirmDialog.ConfirmSelect() {
                    @Override
                    public void onClickCancel() {
                    }

                    @Override
                    public void onClickQuery() {

                        if (!RxNetTool.isNetworkAvailable(Application.getInstance())) {

                            App.showToast("当前网络不可用");
                            return;
                        }

                        if (selectInquiryType.equals(Common.CommonStr.INQUIRY_TYPE4)) {
                            App.getSPUtils().put(Common.SPApi.VIDEO_END_TYPE, enterType);
                            String initialDiagnosis = etInquiryDesc.getText().toString();
                            VideoChatActivity.show(EditInquiryActivity.this, inquiryModel, initialDiagnosis);
                            setResult(1);
                            finish();
                        } else {
                            mPresenter.editInquiry(inquiryModel);
                        }

                    }
                }).setMargin(50)
                .setOutCancel(false)
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
                tvUserBirth.setText(birthTime.toString());

                String age = DateTimeUtils.getAge(birthTime.toString(), EditInquiryActivity.this);

                tvUserAge.setText(age);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                try {
                    Date date = simpleDateFormat.parse(year + "-" + month  + "-" + day);
                    long time = date.getTime();
                    info.setBirthday(String.valueOf(time));
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
        });
        datePickerDialogFragment.show(getSupportFragmentManager(), "DatePickerDialogFragment");

        if(null!=info && !TextUtils.isEmpty(info.getBirthday())){
            long timeL = Long.parseLong(info.getBirthday());
            datePickerDialogFragment.setBirthDay(timeL);
        }
    }


    private void setFlowTag(List<String> tagStrList) {

        final LayoutInflater mInflater = LayoutInflater.from(this);
        flEditInquiry.setAdapter(mTagAdapter = new TagAdapter<String>(tagStrList) {
            @Override
            public View getView(FlowLayout parent, int position, String tagStr) {
                View view = mInflater.inflate(R.layout.item_flow_edit_inquiry,
                        flEditInquiry, false);

                TextView tv = view.findViewById(R.id.tv_item);
                tv.setText(tagStr);

                return tv;
            }

        });

        onTagLoaded();
    }

    public void showConfirmHkDialog(final String information){
        CustomDialog.
                newInstance(R.layout.no_cancel_confirm_dialog_layout)
                .setViewCreateListner(new CustomDialog.ViewCreateListener() {

                    @Override
                    public void onViewCreate(ViewGroup viewGroup, final BaseNiceDialog dialog) {

                        final TextView title = viewGroup.findViewById(R.id.title);
                        final TextView confirm = viewGroup.findViewById(R.id.ok);
                        title.setText(information);
                        confirm.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });

                    }
                })
                .setMargin(50)
                .setOutCancel(false)
                .show(getSupportFragmentManager());
    }
}
