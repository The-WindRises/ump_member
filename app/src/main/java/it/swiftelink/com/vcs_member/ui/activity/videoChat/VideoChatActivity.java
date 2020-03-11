package it.swiftelink.com.vcs_member.ui.activity.videoChat;

import android.Manifest;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.CountDownTimer;
import android.os.IBinder;
import androidx.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import net.qiujuer.genius.kit.handler.Run;
import net.qiujuer.genius.kit.handler.runable.Action;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;
import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.common.utils.GlideLoadUtils;
import it.swiftelink.com.common.widget.dialog.ConfirmDialog;
import it.swiftelink.com.common.widget.dialog.ConfirmWithContentDialog;
import it.swiftelink.com.common.widget.stateview.StateView;
import it.swiftelink.com.factory.model.inquiry.EditInquiryModel;
import it.swiftelink.com.factory.model.inquiry.EditInquiryResModel;
import it.swiftelink.com.factory.model.videoChat.TrtcConfigResModel;
import it.swiftelink.com.factory.model.videoChat.VideoChatConfigResModel;
import it.swiftelink.com.factory.model.videoChat.VideoChatRecivedResModel;
import it.swiftelink.com.factory.presenter.videoChat.PrepareVideoChatContract;
import it.swiftelink.com.factory.presenter.videoChat.PrepareVideoChatPresenter;
import it.swiftelink.com.vcs_member.App;
import it.swiftelink.com.vcs_member.R;
import it.swiftelink.com.vcs_member.base.BasePresenterActivity;
import it.swiftelink.com.vcs_member.mqtt.MqttMessageCallBack;
import it.swiftelink.com.vcs_member.mqtt.MqttService;
import it.swiftelink.com.vcs_member.utils.MediaPlayerUtils;
import it.swiftelink.com.vcs_member.videoChat.ui.TRTCMainActivity;
import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

@RuntimePermissions
public class VideoChatActivity extends BasePresenterActivity<PrepareVideoChatContract.Presenter> implements PrepareVideoChatContract.View {

    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.tv_time_start)
    TextView tvTimeStart;
    @BindView(R.id.stateView)
    StateView stateView;
    @BindView(R.id.iv_head_image)
    ImageView ivHeadImage;
    //是否已经授权
    private boolean isAllowPermissions = false;
    private ServiceConnection conn;
    private  int usePackagetype;
    private int inquiryDuration;

    private static final String TAG = "VideoChatActivity";

    private static boolean isDoctorEnter = false;

    private Set<String> uuidSet = new HashSet<>();

    /**
     * 倒计时60秒，一次1秒
     */
    CountDownTimer timer = new CountDownTimer(60 * 1000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {

            tvTimeStart.setText(getString(R.string.label_wait_inquiry1) + millisUntilFinished / 1000 + getString(R.string.label_wait_inquiry2));
        }

        @Override
        public void onFinish() {

            timer.cancel();
            ConfirmDialog.newInstanceWithColor(getString(R.string.msg_wait_finish),
                    getString(R.string.msg_cancel), getString(R.string.msg_confirm), true)
                    .setConfirmSelect(new ConfirmDialog.ConfirmSelect() {
                        @Override
                        public void onClickCancel() {

                            mPresenter.cancelInquiry(diagnosisId, Common.CommonStr.INQUIRY_TYPE5);
                        }

                        @Override
                        public void onClickQuery() {
                            timer.start();
                        }
                    }).setMargin(50)
                    .setOutCancel(false)
                    .show(getSupportFragmentManager());
        }
    };
    private String userId;
    private String uuid;
    private String diagnosisId;
    private String initialDiagnosis;
    private Intent intent;
    private EditInquiryModel editInquiryModel;


    public static void show(Activity activity, EditInquiryModel inquiryModel, String initialDiagnosis) {

        Intent intent = new Intent(activity, VideoChatActivity.class);

        intent.putExtra("initialDiagnosis", initialDiagnosis);
        intent.putExtra("EditInquiryModel", inquiryModel);

        activity.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_video_chat;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        MediaPlayerUtils.getInstance().init(this);

        if (isAllowPermissions) {
            startVideoChat();
        } else {
            VideoChatActivityPermissionsDispatcher.startVideoChatWithPermissionCheck(VideoChatActivity.this);
        }
    }

    @Override
    protected void initData() {
        super.initData();

        tvUserName.setText(App.getSPUtils().getString(Common.SPApi.USER_NAME));
        GlideLoadUtils.getInstance().glideLoad(this, R.mipmap.doctor_man, ivHeadImage, R.mipmap.doctor_man);
        Intent intent = getIntent();

        if (intent != null) {
            initialDiagnosis = intent.getStringExtra("initialDiagnosis");
            editInquiryModel = (EditInquiryModel) intent.getSerializableExtra("EditInquiryModel");
        }

        if (isAllowPermissions) {
            MediaPlayerUtils.getInstance().playSound();
            timer.start();
            if (editInquiryModel != null) {
                bindService();
                mPresenter.editInquiry(editInquiryModel);
            }
        }
    }


    @Override
    protected void onResume() {
        super.onResume();

        if (mTrtcResModel != null && isDoctorEnter) {
            startChat(mTrtcResModel);
        }
    }

    private void bindService() {
        intent = new Intent(this, MqttService.class);
        bindService(intent, conn = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName name, IBinder service) {

                MqttService.CustomBinder customBinder = (MqttService.CustomBinder) service;

                MqttService mqttService = customBinder.getService();


                mqttService.setMqttMessageCallBack(new MqttMessageCallBack() {
                    @Override
                    public void messageArrived(String topic, String message) {
                        Log.i(TAG, "messageArrived: " + message);

                        Gson gson = new Gson();

                        VideoChatRecivedResModel resModel = gson.fromJson(message, VideoChatRecivedResModel.class);
                        String uuid = resModel.getData().getUuid();
                        if (!TextUtils.isEmpty(diagnosisId) && uuid.equals(diagnosisId) && uuidSet.add(uuid)) {
                            getVideoChatConfigSuccess(resModel);
                            Log.i("请求trtc", "trtc");
                        }

                    }
                });
            }

            @Override
            public void onServiceDisconnected(ComponentName name) {

            }
        }, Context.BIND_AUTO_CREATE);
    }

    public void getVideoChatConfigSuccess(VideoChatRecivedResModel model) {

        VideoChatRecivedResModel.DataBean data = model.getData();

        if (data != null) {
            userId = data.getPatientId();
            uuid = data.getUuid();

            App.getSPUtils().put(Common.SPApi.UUID, data.getUuid());
            App.getSPUtils().put(Common.SPApi.PATIENT_NAME, data.getPatientName());
            App.getSPUtils().put(Common.SPApi.DOCTOR_ID, data.getDoctorId());
            App.getSPUtils().put(Common.SPApi.DOCTOR_NAME, data.getDoctorName());

            App.getSPUtils().put(Common.SPApi.DOCTOR_HEADER_IMAGE, data.getDoctorHeadImg());

            mPresenter.getTrtcConfig(uuid, userId);
        }

    }

    @Override
    protected PrepareVideoChatContract.Presenter initPresenter() {
        return new PrepareVideoChatPresenter(this);
    }

    //权限申请成功
    @NeedsPermission({Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO,
            Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void startVideoChat() {
        isAllowPermissions = true;
        MediaPlayerUtils.getInstance().playSound();
        timer.start();
        if (editInquiryModel != null && mPresenter != null) {
            bindService();
            mPresenter.editInquiry(editInquiryModel);
        }

    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        VideoChatActivityPermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    //为什么申请权限
    @OnShowRationale({Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO, Manifest.
            permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void showWhy(final PermissionRequest request) {
        ConfirmDialog.newInstance(getString(R.string.label_have_camera_permission),
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

    //权限申请失败
    @OnPermissionDenied({Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO,
            Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void showDenied() {
    }

    //当用户勾选不再提示并且拒绝调用的方法
    @OnNeverAskAgain({Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO,
            Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void ShowAskAgain() {
    }


    @OnClick(R.id.btn_cancel_video_chat)
    public void onViewClicked() {
        if (TextUtils.isEmpty(diagnosisId)) {
            finish();
        } else {
            ConfirmWithContentDialog.newInstance(getString(R.string.cancel_call),getString(R.string.msg_wait_cancel),
                    getString(R.string.cancel), getString(R.string.confirm),true)
                    .setConfirmSelect(new ConfirmWithContentDialog.ConfirmSelect() {
                        @Override
                        public void onClickCancel() {

                        }

                        @Override
                        public void onClickQuery() {
                            mPresenter.cancelInquiry(diagnosisId, Common.CommonStr.INQUIRY_TYPE5);
                        }
                    }).setMargin(50)
                    .setOutCancel(false)
                    .show(getSupportFragmentManager());
        }

    }

    @Override
    public void onBackPressed() {

        if (TextUtils.isEmpty(diagnosisId)) {
            finish();
        } else {
            mPresenter.cancelInquiry(diagnosisId, Common.CommonStr.INQUIRY_TYPE5);
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        isDoctorEnter = false;
        if (conn != null) {
            unbindService(conn);
            stopService(intent);

        }

        if (timer != null) {
            MediaPlayerUtils.getInstance().closeSound();
            timer.cancel();
        }


    }


    private void startChat(TrtcConfigResModel model) {

        if (!TextUtils.isEmpty(diagnosisId) && !TextUtils.isEmpty(initialDiagnosis)) {
            TrtcConfigResModel.UsersBean usersBean = model.getUsers().get(0);

            App.getSPUtils().put(Common.SPApi.SDK_APP_ID, model.getSdkappid());
            App.getSPUtils().put(Common.SPApi.USER_ID, usersBean.getUserId());
            App.getSPUtils().put(Common.SPApi.USER_TOKEN, usersBean.getUserToken());
            App.getSPUtils().put(Common.SPApi.ROOM_ID, model.getRoomId());

            Intent intent = new Intent(VideoChatActivity.this, TRTCMainActivity.class);
            intent.putExtra("sdkAppId", model.getSdkappid());
            intent.putExtra("userId", usersBean.getUserId());
            intent.putExtra("userSig", usersBean.getUserToken());
            intent.putExtra("roomId", model.getRoomId());
            intent.putExtra("startTime", System.currentTimeMillis());
            intent.putExtra("diagnosisId", diagnosisId);
            intent.putExtra("initialDiagnosis", initialDiagnosis);
            intent.putExtra("packageType",usePackagetype);
            intent.putExtra("inquiryDuration",inquiryDuration);

            finish();

            startActivity(intent);
        } else {
            App.showToast("缺少参数");
        }
    }

    @Override
    protected StateView setStateView() {
        return stateView;
    }

    TrtcConfigResModel mTrtcResModel;

    @Override
    public void editInquirySuccess(EditInquiryResModel model) {
        showContent();
        if (model.getData() != null) {
            //获取用户体验卡时间
            EditInquiryResModel.DataBean.UsePackageBean usePackageBean=model.getData().getUsePackage();
            if(null!=usePackageBean) {
                usePackagetype = usePackageBean.getType();
                if (usePackagetype == 2) {
                    //体验卡问诊时长
                    inquiryDuration = usePackageBean.getInquiryDuration();

                }
            }
            diagnosisId = model.getData().getId();

        }
    }

    @Override
    public void getTrtcConfigSuccess(TrtcConfigResModel model) {
        timer.cancel();
        mTrtcResModel = model;
        TrtcConfigResModel.UsersBean usersBean = model.getUsers().get(0);
        App.getSPUtils().put(Common.SPApi.IM_USERID, usersBean.getUserId());
        App.getSPUtils().put(Common.SPApi.IM_USER_TOKEN, usersBean.getUserToken());
        if (isAppOnForeground()) {
            startChat(model);
        } else {
            isDoctorEnter = true;
        }

    }

    @Override
    public void getVideoChatConfigSuccess(VideoChatConfigResModel model) {
        VideoChatConfigResModel.DataBean data = model.getData();

        if (data != null) {
            userId = data.getPatientId();
            uuid = data.getUuid();

            App.getSPUtils().put(Common.SPApi.UUID, data.getUuid());
            App.getSPUtils().put(Common.SPApi.PATIENT_NAME, data.getPatientName());
            App.getSPUtils().put(Common.SPApi.DOCTOR_ID, data.getDoctorId());
            App.getSPUtils().put(Common.SPApi.DOCTOR_NAME, data.getDoctorName());
            App.getSPUtils().put(Common.SPApi.DOCTOR_HEADER_IMAGE, data.getDoctorHeadImg());

            mPresenter.getTrtcConfig(uuid, userId);
        }
    }


    @Override
    public void cancelInquirySuccess() {

        finish();
    }


    @Override
    public void retry(int type) {

    }

    private int tryNum = 0;

    @Override
    public void showError(int type, int errorCode, String errorMsg) {


        if (mPresenter != null && type == Common.UrlApi.GET_VIDEO_CHAT_CONFIG) {
            Run.onUiSync(new Action() {
                @Override
                public void call() {
                    mPresenter.getVideoChatConfig();
                }
            }, 2000, true);

        }

        if (mPresenter != null && type == Common.UrlApi.GET_TRTC_CONFIG && tryNum < 3) {
            tryNum++;
            mPresenter.getTrtcConfig(uuid, userId);
        }

        if (type == Common.UrlApi.CANCEL_INQUIRY) {
            finish();
        }

    }

    public boolean isAppOnForeground() {
        ActivityManager activityManager = (ActivityManager) getApplicationContext()
                .getSystemService(Context.ACTIVITY_SERVICE);
        String packageName = getApplicationContext().getPackageName();

        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        if (appProcesses == null)
            return false;

        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            // The name of the process that this object is associated with.
            if (appProcess.processName.equals(packageName)
                    && appProcess.importance == ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
                return true;
            }
        }

        return false;
    }

}
