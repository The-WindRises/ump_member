package it.swiftelink.com.vcs_member.videoChat.ui;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lzf.easyfloat.EasyFloat;
import com.lzf.easyfloat.interfaces.OnInvokeView;
import com.tencent.rtmp.ui.TXCloudVideoView;
import com.tencent.trtc.TRTCCloud;
import com.tencent.trtc.TRTCCloudDef;

import net.qiujuer.genius.kit.handler.Run;
import net.qiujuer.genius.kit.handler.runable.Action;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.common.event.ObjectEvent;
import it.swiftelink.com.common.widget.dialog.ConfirmDialog;
import it.swiftelink.com.common.widget.stateview.StateView;
import it.swiftelink.com.factory.event.CancleFavoriteDoctorEvent;
import it.swiftelink.com.factory.event.FavoriteDoctorEvent;
import it.swiftelink.com.factory.model.doctor.DoctorInfoResModel;
import it.swiftelink.com.factory.model.order.SaveEvaluateModel;
import it.swiftelink.com.factory.presenter.videoChat.VideoChatContract;
import it.swiftelink.com.factory.presenter.videoChat.VideoChatPresenter;
import it.swiftelink.com.vcs_member.App;
import it.swiftelink.com.vcs_member.R;
import it.swiftelink.com.vcs_member.base.BasePresenterActivity;
import it.swiftelink.com.vcs_member.ui.fragment.MyPagerAdapter;
import it.swiftelink.com.vcs_member.utils.LogUpload;
import it.swiftelink.com.vcs_member.videoChat.common.TrtcConfig;
import it.swiftelink.com.vcs_member.videoChat.common.TrtcRoomManager;
import it.swiftelink.com.vcs_member.videoChat.ui.fragment.DoctorDetailFragment;
import it.swiftelink.com.vcs_member.videoChat.ui.fragment.MessageChatFragment;
import it.swiftelink.com.vcs_member.videoChat.ui.fragment.VideoChatFragment;
import it.swiftelink.com.vcs_member.weight.CustomViewPager;

import static com.tencent.trtc.TRTCCloudDef.TRTC_QUALITY_Bad;
import static com.tencent.trtc.TRTCCloudDef.TRTC_QUALITY_Down;
import static com.tencent.trtc.TRTCCloudDef.TRTC_QUALITY_Excellent;
import static com.tencent.trtc.TRTCCloudDef.TRTC_QUALITY_Good;
import static com.tencent.trtc.TRTCCloudDef.TRTC_QUALITY_Poor;
import static com.tencent.trtc.TRTCCloudDef.TRTC_QUALITY_Vbad;

/**
 * Module:   TRTCMainActivity
 */
public class TRTCMainActivity extends BasePresenterActivity<VideoChatContract.Presenter> implements VideoChatContract.View, VideoChatFragment.OnVideoExitRoomListener {
    @BindView(R.id.stateView)
    StateView stateView;
    @BindView(R.id.vp_video_chat)
    CustomViewPager vpVideoChat;
    @BindView(R.id.ll_to_other_page)
    LinearLayout llToOtherPage;
    @BindView(R.id.iv_doctorCollect)
    ImageView ivDoctorCollect;
    @BindView(R.id.tv_doctorCollect)
    TextView tvDoctorCollect;


    View videoCloseView;
    private String diagnosisId;
    private String initialDiagnosis;
    private VideoChatFragment videoChatFragment;
    private Handler timeHandler = null;
    private static boolean isDocotorCollect = false;
    private  int usePackagetype;
    private int inquiryDuration;
    private boolean isClickedCollect;

    CountDownTimer inquiryDurationTimer;

    private Integer[] list_Title = {R.string.tab_my_inquiry1, R.string.tab_my_inquiry2, R.string.tab_my_inquiry3};

    private int currentPos = 0;

    private List<Fragment> fragmentList = new ArrayList<>();
    private DoctorDetailFragment mDoctorDetailFragment;


    CountDownTimer countDownTimer = new CountDownTimer(30000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {

        }

        @Override
        public void onFinish() {
            countDownTimer.cancel();
            LogUpload.upload(false);
            ConfirmDialog.newInstance(getString(R.string.no_enter),
                    getString(R.string.no), getString(R.string.yes), false)
                    .setConfirmSelect(new ConfirmDialog.ConfirmSelect() {
                        @Override
                        public void onClickCancel() {

                        }

                        @Override
                        public void onClickQuery() {
                            restartApplication();
                            onVideoExit();

                        }
                    }).setMargin(50)
                    .setOutCancel(false)
                    .show(getSupportFragmentManager());
        }
    };









    private String connectUserId;

    @Override
    protected int getLayoutId() {
        return R.layout.vc_activity;
    }


    @Override
    protected void initWindows() {
        super.initWindows();
        //应用运行时，保持屏幕高亮，不锁屏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        //没有标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setStatusBarFullTransparent();

    }

    @Override
    protected void initWidget() {
        super.initWidget();

        countDownTimer.start();
        initFragment();
        videoCloseView = LayoutInflater.from(this).inflate(R.layout.video_close_layout, null);
        vpVideoChat.setAdapter(new MyPagerAdapter(getSupportFragmentManager(), TRTCMainActivity.this, fragmentList, list_Title));
        vpVideoChat.setOffscreenPageLimit(2);
        vpVideoChat.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                currentPos = i;
                if (i == 0) {
                    EasyFloat.dismiss(TRTCMainActivity.this);
                    llToOtherPage.setVisibility(View.VISIBLE);
                } else {
                    llToOtherPage.setVisibility(View.GONE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });


        TrtcRoomManager.getInstance().setRoomListener(new TrtcRoomManager.RoomListener() {
            @Override
            public void onTimeChange(String time) {

            }

            @Override
            public void onCameraChange(boolean isOpen) {
            }

            @Override
            public void onAudioChange(boolean isOpen) {

                if (videoChatFragment != null) {
                    videoChatFragment.onAudioChange(isOpen);
                }
            }

            @Override
            public void onExitRoom() {
                if (mPresenter != null) {
                    inquiryDurationTimer.cancel();
                    SaveEvaluateModel evaluateModel = new SaveEvaluateModel();
                    evaluateModel.setDiagnosisId(diagnosisId);
                    evaluateModel.setInitialDiagnosis(initialDiagnosis);
                    EventBus.getDefault().post(new ObjectEvent(Common.EventbusType.END_VIDEO_INQUIRY, evaluateModel,usePackagetype));
                    TrtcRoomManager.getInstance().exitRoom();
                    Run.onUiSync(new Action() {
                        @Override
                        public void call() {
                            mPresenter.endVideoChat(diagnosisId, true);
                        }
                    }, 2000, true);
                } else {
                    finish();
                }
            }

            @Override
            public void received(int cmdID, byte[] message) {

            }

            @Override
            public void onUserEnter(String userId) {

                countDownTimer.cancel();
                if (timeHandler != null)
                    timeHandler.sendEmptyMessageDelayed(1, 1000);
                connectUserId = userId;
                if(usePackagetype==2){
                    inquiryDurationTimer.start();
                }
                if (videoChatFragment != null) {
                    videoChatFragment.onUserEnter(userId);
                }
            }

            @Override
            public void onNetworkQuality(TRTCCloudDef.TRTCQuality localQuality, TRTCCloudDef.TRTCQuality remoteQuality) {

                if (localQuality.quality == TRTC_QUALITY_Bad || localQuality.quality == TRTC_QUALITY_Vbad || localQuality.quality == TRTC_QUALITY_Down) {
                    //当前网络差
                    if (videoChatFragment != null)
                        videoChatFragment.setNetworkStateTV(getString(R.string.msg_network_is_poor), View.VISIBLE);

                }
                if (localQuality.quality == TRTC_QUALITY_Poor || localQuality.quality == TRTC_QUALITY_Good || localQuality.quality == TRTC_QUALITY_Excellent) {
                    //当前网络恢复可用

                    if (videoChatFragment != null)
                        videoChatFragment.setNetworkStateTV("", View.GONE);
                }

                if (remoteQuality.quality == TRTC_QUALITY_Bad || remoteQuality.quality == TRTC_QUALITY_Vbad || remoteQuality.quality == TRTC_QUALITY_Down) {
                    //对方网络差

                    if (videoChatFragment != null)
                        videoChatFragment.setNetworkStateTV(getString(R.string.msg_opposite_network_poor), View.VISIBLE);
                }
                if (remoteQuality.quality == TRTC_QUALITY_Poor || remoteQuality.quality == TRTC_QUALITY_Good || remoteQuality.quality == TRTC_QUALITY_Excellent) {
                    //对方网恢复可用
                    if (videoChatFragment != null)
                        videoChatFragment.setNetworkStateTV("", View.GONE);
                }
            }

            @Override
            public void onUserVideoAvailable(String userid, boolean available) {
//                if (available) {
//                    if (videoChatFragment != null) {
//                        videoChatFragment.enterRoom(true);
//                    }
//                } else {
//                    if (videoChatFragment != null && userid.equals(connectUserId)) {
//                        videoChatFragment.cvvRemote.addView(videoCloseView);
//                    }
//                }
            }
        });
    }

    private void restartApplication() {
        Intent intent = getBaseContext().getPackageManager()
                .getLaunchIntentForPackage(getBaseContext().getPackageName());
        PendingIntent restartIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, PendingIntent.FLAG_ONE_SHOT);
        AlarmManager mgr = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, restartIntent); // 100毫秒秒钟后重启应用
        System.exit(0);
    }

    /**
     * 隐藏键盘
     * }
     */
    protected void hideInput() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        View v = getWindow().peekDecorView();
        if (null != v) {
            imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }

    @SuppressLint("HandlerLeak")
    @Override
    protected void initData() {
        super.initData();
        //获取前一个页面得到的进房参数
        Intent intent = getIntent();
        int sdkAppId = intent.getIntExtra("sdkAppId", 0);
        int roomId = intent.getIntExtra("roomId", 0);
        String selfUserId = intent.getStringExtra("userId");
        String userSig = intent.getStringExtra("userSig");
        final long startTime = intent.getLongExtra("startTime", System.currentTimeMillis());
        diagnosisId = intent.getStringExtra("diagnosisId");
        initialDiagnosis = intent.getStringExtra("initialDiagnosis");
        usePackagetype=intent.getIntExtra("packageType",usePackagetype);
        inquiryDuration=intent.getIntExtra("inquiryDuration",inquiryDuration);
        inquiryDurationTimer=new CountDownTimer(inquiryDuration*60*1000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                    if(millisUntilFinished<5*60*1000){
                        videoChatFragment.showBuyTip(millisUntilFinished);
                        inquiryDurationTimer.cancel();
                    }
            }

            @Override
            public void onFinish() {

            }
        };
        TrtcRoomManager.getInstance().prepareRoom(this, sdkAppId, roomId, selfUserId, userSig, startTime);
        timeHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                long current = System.currentTimeMillis();
                int time = (int) ((current - startTime) / 1000);
                String showTime = caulateTime(time);
                if (videoChatFragment != null) {
                    videoChatFragment.onTimeChage(showTime);
                }
                sendEmptyMessageDelayed(1, 1000);

            }
        };

    }

    private void initFragment() {
        videoChatFragment = new VideoChatFragment();
        mDoctorDetailFragment=new DoctorDetailFragment();
        videoChatFragment.setVideoExitRoomListener(this);
        fragmentList.add(videoChatFragment);
        fragmentList.add(new MessageChatFragment());
        fragmentList.add(mDoctorDetailFragment);

    }

    @Override
    protected VideoChatContract.Presenter initPresenter() {
        return new VideoChatPresenter(this);
    }

    /**
     * 全透状态栏
     */
    protected void setStatusBarFullTransparent() {
        if (Build.VERSION.SDK_INT >= 21) {//21表示5.0
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= 19) {//19表示4.4
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        }

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(ObjectEvent objectEvent) {
        switch (objectEvent.getType()) {
            case Common.EventbusType.END_VIDEO_INQUIRY:
                finish();
                break;
            case Common.EventbusType.VIDEO_FLOAT_DISMISS:
                hideInput();
                vpVideoChat.setCurrentItem(0, false);

                break;

            case Common.EventbusType.TO_MESSAGE_CHAT:
                vpVideoChat.setCurrentItem(1, false);
                showFloatView();
                break;
            default:
                break;
        }
    }


    @OnClick({R.id.tv_to_message_chat, R.id.tv_look_doctor_msg, R.id.collect_layout})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_to_message_chat:
                vpVideoChat.setCurrentItem(1, false);
                showFloatView();
                break;
            case R.id.tv_look_doctor_msg:
                vpVideoChat.setCurrentItem(2, false);
                showFloatView();
                break;
            case R.id.collect_layout:
                isClickedCollect=true;
                String doctorId = App.getSPUtils().getString(Common.SPApi.DOCTOR_ID);
                if (isDocotorCollect) {
                    mPresenter.cancelCollectDoctor(doctorId);
                } else {
                    mPresenter.collectDoctor(doctorId);
                }
                break;
        }
    }

    public void setDoctorCollectState(boolean isCollect) {
        isDocotorCollect = isCollect;
        ivDoctorCollect.setSelected(isCollect);
        tvDoctorCollect.setText(isCollect ? getString(R.string.saved_doctor_str) : getString(R.string.collect_doctor_str));
        if(isCollect && isClickedCollect){
            Toast toast = Toast.makeText(this, getString(R.string.collect_success), Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            isClickedCollect=false;
        }else if(!isCollect && isClickedCollect) {
            Toast toast = Toast.makeText(this, getString(R.string.cancel_collect_success), Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            isClickedCollect=false;
        }
    }

    private void showFloatView() {
        EasyFloat.dismiss(this);
        EasyFloat.with(this).setLayout(R.layout.float_room_show_view, new OnInvokeView() {
            @Override
            public void invoke(View view) {

                TXCloudVideoView cvvFloatRemote = view.findViewById(R.id.cvv_float_remote);
                TXCloudVideoView cvvFloatLocal = view.findViewById(R.id.cvv_float_local);

                enterRoom(cvvFloatRemote, cvvFloatLocal);

                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        EasyFloat.dismiss(TRTCMainActivity.this);
                        vpVideoChat.setCurrentItem(0, false);
                        EventBus.getDefault().post(new ObjectEvent(Common.EventbusType.VIDEO_FLOAT_DISMISS, null,0));
                    }
                });
            }
        }).show();
    }

    private void enterRoom(final TXCloudVideoView cvvFloatRemote, TXCloudVideoView cvvFloatLocal) {

        final TRTCCloud trtcCloud = TrtcRoomManager.getInstance().getTrtcCloud();
        TrtcConfig trtcConfig = TrtcRoomManager.getInstance().getTrtcConfig();
        timeHandler.sendEmptyMessageDelayed(1, 1000);
        trtcCloud.stopLocalPreview();
        trtcCloud.setLocalViewFillMode(trtcConfig.getVideoRenderMode());
        trtcCloud.startLocalPreview(true, cvvFloatLocal);
        trtcCloud.startLocalAudio();

        if (!TextUtils.isEmpty(connectUserId)) {
            trtcCloud.stopAllRemoteView();
            cvvFloatRemote.removeVideoView();
            trtcCloud.setRemoteViewFillMode(connectUserId, TRTCCloudDef.TRTC_VIDEO_RENDER_MODE_FILL);
            trtcCloud.startRemoteView(connectUserId, cvvFloatRemote);
        }


    }

    @Override
    public void finish() {
        super.finish();
        if(null!=inquiryDurationTimer) {
            inquiryDurationTimer.cancel();
        }
        overridePendingTransition(0, R.anim.out_toptobottom);

    }


    @Override
    public void onBackPressed() {
        if (currentPos > 0) {
            EventBus.getDefault().post(new ObjectEvent(Common.EventbusType.VIDEO_FLOAT_DISMISS, null,0));
        } else {
            finishInquiry();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        String doctorId = App.getSPUtils().getString(Common.SPApi.DOCTOR_ID);
        mPresenter.getDoctorInfo(doctorId);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);


    }

    private void finishInquiry() {
        ConfirmDialog.newInstance(getString(R.string.label_is_confirm_finish_inquiry),
                getString(R.string.no), getString(R.string.yes), true)
                .setConfirmSelect(new ConfirmDialog.ConfirmSelect() {
                    @Override
                    public void onClickCancel() {
                    }

                    @Override
                    public void onClickQuery() {

                        onVideoExit();
                    }
                }).setMargin(50)
                .setOutCancel(true)
                .show(getSupportFragmentManager());
    }

    /**
     * 退出视频房间
     */
    @Override
    public void onVideoExit() {
        if (timeHandler != null) {
            timeHandler.removeCallbacksAndMessages(null);
            timeHandler = null;
        }
        if(null!=inquiryDurationTimer){
            inquiryDurationTimer.cancel();
        }

        mPresenter.endVideoChat(diagnosisId, false);
        TrtcRoomManager.getInstance().exitRoom();
        SaveEvaluateModel evaluateModel = new SaveEvaluateModel();
        evaluateModel.setDiagnosisId(diagnosisId);
        evaluateModel.setInitialDiagnosis(initialDiagnosis);
        EventBus.getDefault().post(new ObjectEvent(Common.EventbusType.END_VIDEO_INQUIRY, evaluateModel,usePackagetype));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        TrtcRoomManager.getInstance().exitRoom();
        if(null!=inquiryDurationTimer){
            inquiryDurationTimer.cancel();
        }
        EventBus.getDefault().unregister(this);
        if (timeHandler != null) {
            timeHandler.removeCallbacksAndMessages(null);
            timeHandler = null;
        }
        super.onDestroy();
    }

    @Override
    protected StateView setStateView() {
        return stateView;
    }

    @Override
    public void endVideoChatSuccess() {

        finish();
    }




    @Subscribe()
    public void setDoctorEvent(FavoriteDoctorEvent favoriteDoctorEvent){
        if(favoriteDoctorEvent.isSuccess()){

            setDoctorCollectState(true);
        }
    }


    @Subscribe()
    public void setCancleDoctorEvent(CancleFavoriteDoctorEvent cancleFavoriteDoctorEvent){
        if(cancleFavoriteDoctorEvent.isSuccess()){

            setDoctorCollectState(false);
        }
    }




    @Override
    public void getDoctorInfoSuccess(DoctorInfoResModel model) {
        Log.i("lqi","获取医生收藏状态："+model.getData().isFavorite());
        if(model.getData().isFavorite()){
            isDocotorCollect=true;
            setDoctorCollectState(true);
        }else{
            isDocotorCollect=false;
            setDoctorCollectState(false);
        }
    }

    @Override
    public void retry(int type) {

    }

    @Override
    public void showError(int type, int errorCode, String errorMsg) {
        super.showError(type, errorCode, errorMsg);
        finish();
    }

    private String caulateTime(int time) {
        if (time < 10) {
            return "0:0" + time;
        } else if (time < 60) {
            return "0:" + time;
        } else {
            String timeS = "";
            int minuts = time / 60;
            if (minuts > 60) {
                int hours = minuts / 60;
                timeS = hours + ":" + minuts % 60 + ":";
            } else {
                timeS = minuts + ":";
            }
            int second = time % 60;
            if (second < 10) {
                timeS += "0" + second;
            } else {
                timeS += second;
            }
            return timeS;
        }
    }

    private void divide(long valueOne,long valueTwo){
        BigDecimal b1=new BigDecimal(valueOne);
        BigDecimal b2=new BigDecimal(valueTwo);
        BigDecimal result=b1.divide(b2, 2, BigDecimal.ROUND_HALF_UP);

    }
}
