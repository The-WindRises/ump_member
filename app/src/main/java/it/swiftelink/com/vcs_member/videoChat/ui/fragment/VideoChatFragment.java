package it.swiftelink.com.vcs_member.videoChat.ui.fragment;


import android.os.Bundle;
import android.os.CountDownTimer;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dalong.marqueeview.MarqueeView;
import com.tencent.rtmp.ui.TXCloudVideoView;
import com.tencent.trtc.TRTCCloud;
import com.tencent.trtc.TRTCCloudDef;

import net.qiujuer.genius.ui.widget.Loading;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.OnClick;
import it.swiftelink.com.common.app.BaseFragment;
import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.common.event.ObjectEvent;
import it.swiftelink.com.common.widget.dialog.ConfirmDialog;
import it.swiftelink.com.vcs_member.R;
import it.swiftelink.com.vcs_member.videoChat.common.TrtcConfig;
import it.swiftelink.com.vcs_member.videoChat.common.TrtcRoomManager;

/**
 * A simple {@link Fragment} subclass.
 */
public class VideoChatFragment extends BaseFragment {

    @BindView(R.id.timeTV)
    TextView timeTV;
    @BindView(R.id.audioIV)
    ImageView audioIV;
    @BindView(R.id.loading_wait)
    Loading loadingWait;
    @BindView(R.id.ll_wait_enter)
    LinearLayout llWaitEnter;
    @BindView(R.id.cvv_remote)
    public TXCloudVideoView cvvRemote;
    @BindView(R.id.cvv_local)
    TXCloudVideoView cvvLocal;
    @BindView(R.id.networkStateTV)
    TextView networkStateTV;
    MarqueeView mMarqueeView;
    private OnVideoExitRoomListener mExitRoomListener;
    private String connectUserId;
    private long startTime;


    public VideoChatFragment() {
        // Required empty public constructor
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_video_chat;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected void initWidget(View view) {
        super.initWidget(view);

        mMarqueeView = (MarqueeView) view.findViewById(R.id.mMarqueeView);
        ;
        loadingWait.start();
        llWaitEnter.setVisibility(View.VISIBLE);
        setAudioStatus(TrtcRoomManager.getInstance().isAudioEnable());
        enterRoom(false);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(ObjectEvent objectEvent) {
        switch (objectEvent.getType()) {
            case Common.EventbusType.VIDEO_FLOAT_DISMISS:
                enterRoom(false);
                Log.i("trtc", "接收到执行进入房间");

                break;
            default:
                break;
        }
    }

    public void enterRoom(boolean isInRoom) {

        final TRTCCloud trtcCloud = TrtcRoomManager.getInstance().getTrtcCloud();
        TrtcConfig trtcConfig = TrtcRoomManager.getInstance().getTrtcConfig();
        TRTCCloudDef.TRTCParams trtcParams = TrtcRoomManager.getInstance().getTrtcParams();

        // 开启视频采集预览
        trtcCloud.stopLocalPreview();
        cvvLocal.removeVideoView();
        trtcCloud.setLocalViewFillMode(trtcConfig.getVideoRenderMode());
        trtcCloud.startLocalPreview(true, cvvLocal);
        if (!isInRoom) {
            //进房
            trtcCloud.startLocalAudio();
            trtcCloud.enterRoom(trtcParams, TRTCCloudDef.TRTC_APP_SCENE_VIDEOCALL);

        }

        if (!TextUtils.isEmpty(connectUserId) && isInRoom) {
            trtcCloud.stopRemoteView(connectUserId);
            cvvRemote.removeVideoView();
            trtcCloud.setRemoteViewFillMode(connectUserId, TRTCCloudDef.TRTC_VIDEO_RENDER_MODE_FILL);
            trtcCloud.startRemoteView(connectUserId, cvvRemote);

        }

    }


    public void setVideoExitRoomListener(OnVideoExitRoomListener listener) {
        this.mExitRoomListener = listener;
    }


    @OnClick({R.id.cameraIV, R.id.exitIV, R.id.audioIV, R.id.minIV})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cameraIV:
                //点击切换前后置摄像头
                TrtcRoomManager.getInstance().onSwitchCamera();
                break;
            case R.id.exitIV:
                finishInquiry();
                break;
            case R.id.audioIV:
                onMuteAudio();
                break;
            case R.id.minIV:
                EventBus.getDefault().post(new ObjectEvent(Common.EventbusType.TO_MESSAGE_CHAT, null,0));
                break;
        }
    }


    public void setNetworkStateTV(String mesg, int visibility) {
        networkStateTV.setText(mesg);
        networkStateTV.setVisibility(visibility);
    }

    /**
     * 点击关闭或者打开本地的麦克风采集
     */
    private void onMuteAudio() {

        TrtcRoomManager.getInstance().startLocalAudio();
        TrtcRoomManager.getInstance().muteLocalAudio();
        setAudioStatus(TrtcRoomManager.getInstance().isAudioEnable());
    }

    private void setAudioStatus(boolean isOpen) {
        TrtcRoomManager.getInstance().setAudioEnable(isOpen);
        if (!getActivity().isFinishing()) {
            if (isOpen) {
                audioIV.setImageResource(R.mipmap.audio_open);
            } else {
                audioIV.setImageResource(R.mipmap.audio_close);
            }
        }
    }


    public void onTimeChage(String time) {
        timeTV.setText(time);
    }


    public void onAudioChange(boolean isOpen) {
        setAudioStatus(isOpen);
    }

    public void onUserEnter(String userId) {

        this.connectUserId = userId;
        llWaitEnter.setVisibility(View.GONE);
        TRTCCloud trtcCloud = TrtcRoomManager.getInstance().getTrtcCloud();
        trtcCloud.stopAllRemoteView();
        trtcCloud.setRemoteViewFillMode(userId, TRTCCloudDef.TRTC_VIDEO_RENDER_MODE_FILL);
        trtcCloud.startRemoteView(userId, cvvRemote);
    }


    private void finishInquiry() {
        ConfirmDialog.newInstance(getString(R.string.label_is_confirm_finish_inquiry),
                getString(R.string.cancel), getString(R.string.confirm), true)
                .setConfirmSelect(new ConfirmDialog.ConfirmSelect() {
                    @Override
                    public void onClickCancel() {
                    }

                    @Override
                    public void onClickQuery() {
                        exitRoom();
                    }
                }).setMargin(50)
                .setOutCancel(true)
                .show(getActivity().getSupportFragmentManager());
    }


    /**
     * 退出视频房间
     */
    public void exitRoom() {
        mExitRoomListener.onVideoExit();


    }
    long exitTime;
    CountDownTimer countDownTimer;
    private boolean isEndTip;


    public void showBuyTip(long time) {
        mMarqueeView.setText(getContext().getResources().getString(R.string.balance_5_minutes));
        mMarqueeView.startScroll();
        isEndTip=false;
        exitTime=time;
        countDownTimer = new CountDownTimer(exitTime, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                if (millisUntilFinished < 60 * 1000) {
                    if(!isEndTip) {
                        mMarqueeView.setText(getContext().getResources().getString(R.string.balance_1_minutes));
                        mMarqueeView.startScroll();
                        isEndTip=true;
                    }
                }

            }

            @Override
            public void onFinish() {
                countDownTimer.cancel();
                exitRoom();
            }
        };

        countDownTimer.start();

    }



    public interface OnVideoExitRoomListener {
        void onVideoExit();
    }
}
