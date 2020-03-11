package it.swiftelink.com.vcs_member.ui.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DownloadManager;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatTextView;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.hacknife.onpermission.OnPermission;
import com.hacknife.onpermission.Permission;
import com.tencent.imsdk.TIMCallBack;
import com.tencent.imsdk.TIMManager;
import com.tencent.qcloud.tim.uikit.TUIKit;
import com.tencent.qcloud.tim.uikit.base.IUIKitCallBack;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;
import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.common.event.ObjectEvent;
import it.swiftelink.com.common.update.Updater;
import it.swiftelink.com.common.update.UpdaterConfig;
import it.swiftelink.com.common.utils.ACache;
import it.swiftelink.com.common.utils.GlideLoadUtils;
import it.swiftelink.com.common.utils.LogcatHelper;
import it.swiftelink.com.common.utils.Utils;
import it.swiftelink.com.common.widget.dialog.ConfirmDialog;
import it.swiftelink.com.common.widget.dialog.CustomDialog;
import it.swiftelink.com.common.widget.nicedialog.BaseNiceDialog;
import it.swiftelink.com.common.widget.stateview.StateView;
import it.swiftelink.com.factory.model.account.LoginResModel;
import it.swiftelink.com.factory.model.card.InquiryValidCardResModel;
import it.swiftelink.com.factory.model.mian.VersionResModel;
import it.swiftelink.com.factory.model.order.EvaluateLabelModel;
import it.swiftelink.com.factory.model.order.EvaluateLabelResModel;
import it.swiftelink.com.factory.model.order.SaveEvaluateModel;
import it.swiftelink.com.factory.model.videoChat.TrtcConfigResModel;
import it.swiftelink.com.factory.presenter.main.MainCotract;
import it.swiftelink.com.factory.presenter.main.MainPresenter;
import it.swiftelink.com.vcs_member.App;
import it.swiftelink.com.vcs_member.R;
import it.swiftelink.com.vcs_member.base.BasePresenterActivity;
import it.swiftelink.com.vcs_member.ui.activity.appointment.ForeseeInquiryDetailActivity;
import it.swiftelink.com.vcs_member.ui.activity.inquiry.EditInquiryActivity;
import it.swiftelink.com.vcs_member.ui.activity.my.SelectPackageCardActivity;
import it.swiftelink.com.vcs_member.ui.fragment.ViewPagerAdapter;
import it.swiftelink.com.vcs_member.ui.fragment.main.FindFragment;
import it.swiftelink.com.vcs_member.ui.fragment.main.HomeFragment;
import it.swiftelink.com.vcs_member.ui.fragment.main.HospitalFragment;
import it.swiftelink.com.vcs_member.ui.fragment.main.MyFragment;
import it.swiftelink.com.vcs_member.weight.CustomViewPager;
import me.zhanghai.android.materialratingbar.MaterialRatingBar;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class MainActivity extends BasePresenterActivity<MainCotract.Presenter> implements MainCotract.View {

    @BindView(R.id.vp_main)
    CustomViewPager vpMain;
    @BindView(R.id.tv_tab_home)
    TextView tvTabHome;
    @BindView(R.id.tv_tab_hospital)
    TextView tvTabHospital;
    @BindView(R.id.tv_tab_find)
    TextView tvTabFind;
    @BindView(R.id.tv_tab_my)
    TextView tvTabMy;
    @BindView(R.id.tv_video_chat)
    TextView tvVideoChat;
    @BindView(R.id.ll_to_video_chat)
    LinearLayout llToVideoChat;
    @BindView(R.id.stateView)
    StateView stateView;

    private static final int TYPE_HOME = 0;
    private static final int TYPE_TAB_HOSPITAL = 1;
    private static final int TYPE_FIND = 2;
    private static final int TYPE_MY = 3;

    private final static int EXTERNAL_STORAGE_CODE = 1001;

    private List<Fragment> frags;
    private boolean isMember = false;

    private int useCardType;
    private boolean isDialogShow = false;

    private List<EvaluateLabelResModel.DataBean.DataListBean> dataList;
    private TagFlowLayout fl_dialog_evaluate;

    private boolean isAllowPermissions;
    private VersionResModel mVersionResModel;

    public static void show(Activity activity) {

        activity.startActivity(new Intent(activity, MainActivity.class));
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initWidget() {
        super.initWidget();

        initFragments();
        FragmentManager mFragmentManager = getSupportFragmentManager();

        ViewPagerAdapter mViewPagerAdapter = new ViewPagerAdapter(mFragmentManager, frags);

        vpMain.setAdapter(mViewPagerAdapter);
        showDialogPermission();

    }




    @Override
    protected void initData() {
        super.initData();
        //用于免登录测试
//        mPresenter.loginTest();


        String loginId=App.getSPUtils().getString(Common.SPApi.LOGINID);
        if(!TextUtils.isEmpty(loginId)){
            JPushInterface.setAlias(this,0,loginId);
        }
        Intent intent = getIntent();
        if (intent != null) {
            int type = intent.getIntExtra("type", TYPE_HOME);
            if (type == TYPE_MY) {
                setTab(TYPE_MY);
            } else {
                setTab(TYPE_HOME);
            }
        } else {
            setTab(TYPE_HOME);
        }
        mPresenter.checkVersion("android", "member");

    }

    @Override
    protected void onResume() {
        super.onResume();

        String userid = App.getSPUtils().getString(Common.SPApi.LOGINID);
        if (!TextUtils.isEmpty(userid)) {
            mPresenter.getTrtc(userid);
        }
//        mPresenter.getIsMember();

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);

        try {

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, EXTERNAL_STORAGE_CODE);
            } else {
                String loginId = App.getSPUtils().getString(Common.SPApi.LOGINID);
//                LogcatHelper.getInstance(this, loginId).start();
            }
        } catch (Exception e) {
            Log.e("MainActivity", "", e);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected MainCotract.Presenter initPresenter() {
        return new MainPresenter(this);
    }

    @Override
    protected StateView setStateView() {
        return stateView;
    }
    private void initFragments() {

        if (frags == null) {
            frags = new ArrayList<>();
            frags.add(new HomeFragment());
            frags.add(new HospitalFragment());
            frags.add(new FindFragment());
            frags.add(new MyFragment());

        }
    }

    @OnClick({R.id.tv_tab_home, R.id.tv_tab_hospital, R.id.tv_tab_find,
            R.id.tv_tab_my, R.id.ll_to_video_chat})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_tab_home:
                setTab(TYPE_HOME);
                break;
            case R.id.tv_tab_hospital:
                setTab(TYPE_TAB_HOSPITAL);
                break;
            case R.id.tv_tab_find:
                setTab(TYPE_FIND);
                break;
            case R.id.tv_tab_my:
                setTab(TYPE_MY);
                break;
            case R.id.ll_to_video_chat:
//                if (isMember) {
//                    EditInquiryActivity.show(this, Common.CommonStr.ENTER_FROM_MAIN);
//                } else {
//                    String token = App.getSPUtils().getString(Common.SPApi.TOKEN);
//                    if (!TextUtils.isEmpty(token)) {
//                        ForeseeInquiryDetailActivity.show(this);
//                    } else {
//                        LoginActivity.show(this);
//                    }
//                }
                String token=App.getSPUtils().getString(Common.SPApi.TOKEN);
                if(TextUtils.isEmpty(token)){
                    ForeseeInquiryDetailActivity.show(this);
                }else {
                    //验证用户是否有套餐卡
                    mPresenter.getInquiryValidPackageCard();
                }


                break;
        }
    }

    private void setTab(int tabNum) {
        tvTabHome.setSelected(false);
        tvTabHospital.setSelected(false);
        tvTabFind.setSelected(false);
        tvTabMy.setSelected(false);
        switch (tabNum) {
            case TYPE_HOME:
                tvTabHome.setSelected(true);
                vpMain.setCurrentItem(0);
                break;
            case TYPE_TAB_HOSPITAL:
                tvTabHospital.setSelected(true);
                vpMain.setCurrentItem(1);
                break;
            case TYPE_FIND:
                tvTabFind.setSelected(true);
                vpMain.setCurrentItem(2);
                break;
            case TYPE_MY:
                tvTabMy.setSelected(true);
                vpMain.setCurrentItem(3);
                break;

        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            ConfirmDialog.newInstance(getString(R.string.quit_the_application),
                    getString(R.string.cancel), getString(R.string.confirm), true)
                    .setConfirmSelect(new ConfirmDialog.ConfirmSelect() {
                        @Override
                        public void onClickCancel() {
                        }

                        @Override
                        public void onClickQuery() {
                            App.getInstance().finishAll();
                        }
                    }).setMargin(50)
                    .setOutCancel(false)
                    .show(getSupportFragmentManager());
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }


    private static final int REQUEST_OVERLAY = 4444;



    @Subscribe
    public void Event(ObjectEvent objectEvent) {
        switch (objectEvent.getType()) {
            case Common.EventbusType.END_VIDEO_INQUIRY:
                String videoEndType = App.getSPUtils().getString(Common.SPApi.VIDEO_END_TYPE);
                useCardType=objectEvent.getCardType();
                Log.i("lqi","------------收到用户退出视频事件-----------------");
//                SaveEvaluateModel evaluateModel = (SaveEvaluateModel) objectEvent.getObj();
//                startEvaluate(evaluateModel);
                if (Common.CommonStr.ENTER_FROM_MAIN.equals(videoEndType)) {
                    SaveEvaluateModel evaluateModel = (SaveEvaluateModel) objectEvent.getObj();
                    startEvaluate(evaluateModel);
                }
                break;
            default:
                break;
        }
    }


    private void startEvaluate(final SaveEvaluateModel evaluateModel) {
        if (!isDialogShow) {
            CustomDialog.
                    newInstance(R.layout.dialog_evaluate)
                    .setViewCreateListner(new CustomDialog.ViewCreateListener() {
                        @Override
                        public void onViewCreate(ViewGroup viewGroup, final BaseNiceDialog dialog) {

                            ImageView iv_doctor_header = viewGroup.findViewById(R.id.doctorIconIv);
                            TextView doctorNameTv = viewGroup.findViewById(R.id.doctorNameTv);
                            final MaterialRatingBar rb_grade = viewGroup.findViewById(R.id.rb_grade);
                            fl_dialog_evaluate = viewGroup.findViewById(R.id.tabLayout);
                            Button btn_confirm_evaluate = viewGroup.findViewById(R.id.submit);
                            LinearLayout iv_close = viewGroup.findViewById(R.id.closeIv);
                            doctorNameTv.setText(App.getSPUtils().getString(Common.SPApi.DOCTOR_NAME, ""));

                            GlideLoadUtils.getInstance().glideLoadCircle(MainActivity.this,
                                    App.getSPUtils().getString(Common.SPApi.DOCTOR_HEADER_IMAGE), iv_doctor_header, R.mipmap.icon_dc_man);
                            getEvaluateLabelList(rb_grade.getProgress());
                            rb_grade.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
                                @Override
                                public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                                    getEvaluateLabelList(rating);
                                }
                            });

                            btn_confirm_evaluate.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    SaveEvaluateModel saveEvaluateModel = new SaveEvaluateModel();

                                    saveEvaluateModel.setDiagnosisId(evaluateModel.getDiagnosisId());
                                    saveEvaluateModel.setInitialDiagnosis(evaluateModel.getInitialDiagnosis());
                                    saveEvaluateModel.setPatientScore(rb_grade.getProgress());

                                    Set<Integer> selectedList = fl_dialog_evaluate.getSelectedList();
                                    StringBuffer buffer = new StringBuffer();
                                    for (Integer selectPos : selectedList) {
                                        buffer.append(dataList.get(selectPos).getName() + ",");
                                    }
                                    if (buffer.toString().equals("")) {
                                        App.showToast(R.string.plase_sel_tag);
                                        return;
                                    }
                                    saveEvaluateModel.setPatientEvaluation(buffer.toString());
                                    saveEvaluateModel.setDoctorStatus(Common.CommonStr.EVALUATE_TYPE1);
                                    saveEvaluateModel.setPatientStatus(Common.CommonStr.EVALUATE_TYPE2);
                                    mPresenter.saveEvaluate(saveEvaluateModel);
                                    isDialogShow=false;
                                    dialog.dismiss();

                                }
                            });

                            iv_close.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    SaveEvaluateModel saveEvaluateModel = new SaveEvaluateModel();

                                    saveEvaluateModel.setDiagnosisId(evaluateModel.getDiagnosisId());
                                    saveEvaluateModel.setInitialDiagnosis(evaluateModel.getInitialDiagnosis());

                                    saveEvaluateModel.setDoctorStatus(Common.CommonStr.EVALUATE_TYPE1);
                                    saveEvaluateModel.setPatientStatus(Common.CommonStr.EVALUATE_TYPE1);
                                    mPresenter.saveEvaluate(saveEvaluateModel);
                                    isDialogShow=false;
                                    dialog.dismiss();

                                }
                            });

                        }
                    })
                    .setMargin(30)
                    .setOutCancel(false)
                    .show(getSupportFragmentManager());
            isDialogShow=true;
        }

    }

    private void getEvaluateLabelList(float score) {
        EvaluateLabelModel labelModel = new EvaluateLabelModel();
        labelModel.setType("Member");
        labelModel.setLanguage(App.getSPUtils().getString(Common.SPApi.SELECT_LANGUAGE, Common.CommonStr.LANGUAGE1));
        labelModel.setScore((int) score);

        mPresenter.getEvaluateLabel(labelModel);
    }

    @Override
    public void checkVersionSuccess(VersionResModel versionResModel) {
        mVersionResModel = versionResModel;
        if (versionResModel.getData() != null) {
            int versionNumber = Integer.valueOf(versionResModel.getData().getAppVersionNumber());
            int locVersionCode = Utils.getVersionCode();
            if (versionNumber > locVersionCode) {
                showUpdateDialog();
            }
        }

    }

    /**
     * 用于免登录测试
     * @param resModel
     */
    @Override
    public void loginSuccess(LoginResModel resModel) {
        App.getSPUtils().put(Common.SPApi.TOKEN, resModel.getData().getAccessToken());
        App.getSPUtils().put(Common.SPApi.ID, resModel.getData().getUserId());
        App.getSPUtils().put(Common.SPApi.LOGINID, resModel.getData().getLoginId());
        App.getSPUtils().put(Common.SPApi.USER_NAME, resModel.getData().getUserName());
        App.getSPUtils().put(Common.SPApi.USER_HEADER_IMAGE, resModel.getData().getHeadImg());
        showContent();
        JPushInterface.setAlias(this,0,resModel.getData().getLoginId());
//        LogcatHelper logcatHelper = LogcatHelper.getInstance(this,resModel.getData().getLoginId());
//        logcatHelper.stop();
//        logcatHelper.start();
    }

    @Subscribe
    public void checkVersionEvent(VersionResModel versionResModel){
        mVersionResModel = versionResModel;
        if (versionResModel.getData() != null) {
            int versionNumber = Integer.valueOf(versionResModel.getData().getAppVersionNumber());
            int locVersionCode = Utils.getVersionCode();
            if (versionNumber > locVersionCode) {
                showUpdateDialog();
            }
        }
    }


    private void showUpdateDialog() {
        CustomDialog.
                newInstance(R.layout.update_dialog)
                .setViewCreateListner(new CustomDialog.ViewCreateListener() {
                    private AppCompatTextView updlogTv;
                    private Button upgradeNowBtn;
                    private Button ignoreBtn;

                    @Override
                    public void onViewCreate(ViewGroup viewGroup, final BaseNiceDialog dialog) {

                        updlogTv = viewGroup.findViewById(R.id.updlogTv);
                        upgradeNowBtn = viewGroup.findViewById(R.id.upgradeNowBtn);
                        ignoreBtn = viewGroup.findViewById(R.id.ignoreBtn);

                        int isForce = mVersionResModel.getData().getIsForce();

                        if (isForce == Common.RequstCode.ISFORCE) {
                            ignoreBtn.setVisibility(View.GONE);
                        } else if (isForce == Common.RequstCode.ISFORCENO) {
                            ignoreBtn.setVisibility(View.VISIBLE);
                        }

                        String updLog = mVersionResModel.getData().getUpdateLog();

                        updlogTv.setText(updLog.replace("\\n", "\n"));

                        upgradeNowBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                if (isAllowPermissions) {
                                    requestRedAndWrite();
                                } else {
                                    MainActivityPermissionsDispatcher.requestRedAndWriteWithPermissionCheck(MainActivity.this);
                                }
                                if(isForce==Common.RequstCode.ISFORCE){
                                    upgradeNowBtn.setText("正在升级");
                                    upgradeNowBtn.setBackground(getDrawable(R.drawable.btn_def_drawal));
                                    upgradeNowBtn.setClickable(false);
                                }else {
                                    dialog.dismiss();
                                }                            }
                        });
                        ignoreBtn.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ACache.get(MainActivity.this).put("isIgnore", mVersionResModel.getData().getIsForce() + "", 172800);
                                dialog.dismiss();
                            }
                        });

                    }
                })
                .setOutCancel(false)
                .setShowBottom(false)
                .setMargin(50)
                .show(getSupportFragmentManager());
    }

    public void download(String url) {
        UpdaterConfig config = new UpdaterConfig.Builder(this)
                .setTitle(getResources().getString(R.string.app_name))
                .setDescription(getString(R.string.system_download_description))
                .setFileUrl(url)
                .setCanMediaScanner(true)
                .setAllowedNetworkTypes(DownloadManager.Request.NETWORK_MOBILE
                        | DownloadManager.Request.NETWORK_WIFI)
                .build();
        Updater.get().showLog(true).download(config);
    }



    @Override
    public void saveEvaluateSuccess() {
        showContent();
        if(useCardType==2) {
            com.kongzue.dialog.v2.CustomDialog.build(this, R.layout.dialog_buy_tip, (dialog, rootView) -> {

                rootView.findViewById(R.id.confirm_cancel_tv).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.doDismiss();
                    }
                });
                rootView.findViewById(R.id.confirm_buy_tv).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SelectPackageCardActivity.show(MainActivity.this);
                        dialog.doDismiss();
                    }
                });
            }).setCanCancel(true).showDialog();

        }
    }

    @Override
    public void getEvaluateLabelSuccess(EvaluateLabelResModel model) {
        showContent();
        dataList = model.getData().getDataList();

        final LayoutInflater mInflater = LayoutInflater.from(this);
        if (dataList != null && dataList.size() > 0) {
            fl_dialog_evaluate.setAdapter(new TagAdapter<EvaluateLabelResModel.DataBean.DataListBean>(dataList) {
                @Override
                public View getView(FlowLayout parent, int position, EvaluateLabelResModel.DataBean.DataListBean dataListBean1) {
                    TextView tv = (TextView) mInflater.inflate(R.layout.item_flow_tv,
                            fl_dialog_evaluate, false);
                    tv.setText(dataListBean1.getName());
                    return tv;
                }
            });
        }
    }

    @Override
    public void getTrtcSuccess(TrtcConfigResModel resModel) {

        if (resModel != null && resModel.getUsers() != null && resModel.getUsers().size() > 0) {
            TrtcConfigResModel.UsersBean usersBean = resModel.getUsers().get(0);
            imLogin(usersBean);
        }
    }

    @Override
    public void getInquiryValidPackageCardSuccess(InquiryValidCardResModel resModel) {
        showContent();
        if(null==resModel.getData()){
            return;
        }
        if(resModel.getData().isSuccessX()){
            //当前可问诊
            EditInquiryActivity.show(this, Common.CommonStr.ENTER_FROM_MAIN);
        }else {
            //当前不可问诊
            int type=resModel.getData().getType();
            //1:当前用户没有套餐卡
            //2：当前时间不符合套餐卡使用条件
            if(type==1) {
                ForeseeInquiryDetailActivity.show(this);
            }else if(type==2){
                Toast.makeText(this,"当前时间不符合套餐卡使用条件",Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * 初始化im
     *
     * @param usersBean
     */
    public void imLogin(final TrtcConfigResModel.UsersBean usersBean) {

        new OnPermission(this).grant(new Permission() {
            @Override
            public String[] permissions() {
                return new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
            }

            @Override
            public void onGranted(String[] permission) {
                TIMManager.getInstance().autoLogin(usersBean.getUserId(), new TIMCallBack() {
                    @Override
                    public void onError(int i, String s) {
                        TUIKit.login(usersBean.getUserId(), usersBean.getUserToken(), new IUIKitCallBack() {
                            @Override
                            public void onSuccess(Object data) {
                                App.getSPUtils().put("imIsLogin", true);
                            }

                            @Override
                            public void onError(String module, int errCode, String errMsg) {
                                App.getSPUtils().put("imIsLogin", false);
                                imLogin(usersBean);
                            }
                        });
                    }

                    @Override
                    public void onSuccess() {
                        App.getSPUtils().put("imIsLogin", true);
                    }
                });
            }

            @Override
            public void onDenied(String[] permission) {
                App.showToast("拒绝存储权限Im聊天将不可用");
            }
        });

    }

    @Override
    public void retry(int type) {

    }

    @Override
    public void showError(int type, int errorCode, String errorMsg) {
        showContent();
        if (Common.UrlApi.GET_ISMEMBER == type) {
            isMember = false;
        }
    }

    @NeedsPermission({Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void requestRedAndWrite() {
        isAllowPermissions = true;
        download(mVersionResModel.getData().getAppDownloadUrl());
    }

    @NeedsPermission({Manifest.permission.SYSTEM_ALERT_WINDOW})
    void  showDialogPermission(){

    }



    @SuppressLint("NeedOnRequestPermissionsResult")
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode){
            case EXTERNAL_STORAGE_CODE:
                try {
                    if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        String loginId = App.getSPUtils().getString(Common.SPApi.LOGINID);
                        LogcatHelper.getInstance(this, loginId).start();
                    } else {
                        App.showToast("授权失败，日志记录无法运行");
                    }
                } catch (Exception e) {
                    Log.e("MainActivity", "", e);
                }
                break;

        }




        MainActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @OnShowRationale({Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void onShowRationale(final PermissionRequest request) {
        ConfirmDialog.newInstance(getString(R.string.label_need_permission_to_download),
                getString(R.string.cancel), getString(R.string.confirm), true)
                .setConfirmSelect(new ConfirmDialog.ConfirmSelect() {
                    @Override
                    public void onClickCancel() {
                        request.cancel();
                    }

                    @Override
                    public void onClickQuery() {
                        request.proceed();
                    }
                }).setMargin(50)
                .setOutCancel(true)
                .show(getSupportFragmentManager());
    }

    @OnPermissionDenied({Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void onPermissionDenied() {
        App.showToast(R.string.label_denied_file_permission);
    }

    @OnNeverAskAgain({Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void onNerverAskAgain() {
        App.showToast(R.string.please_open_file_permission);
        App.toSelfSetting(this);
    }
}
