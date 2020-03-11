package it.swiftelink.com.vcs_member.ui.activity.videoChat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.View;
import android.view.WindowManager;

import com.tencent.imsdk.TIMConversationType;
import com.tencent.qcloud.tim.uikit.component.TitleBarLayout;
import com.tencent.qcloud.tim.uikit.modules.chat.ChatLayout;
import com.tencent.qcloud.tim.uikit.modules.chat.base.ChatInfo;
import com.tencent.qcloud.tim.uikit.modules.chat.layout.input.InputLayout;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.common.widget.stateview.StateView;
import it.swiftelink.com.factory.presenter.order.MessageChatContract;
import it.swiftelink.com.factory.presenter.order.MessageChatPresenter;
import it.swiftelink.com.vcs_member.App;
import it.swiftelink.com.vcs_member.R;
import it.swiftelink.com.vcs_member.base.BasePresenterActivity;

public class MessageChatActivity extends BasePresenterActivity<MessageChatContract.Presenter> implements MessageChatContract.View {


    @BindView(R.id.chat_layout)
    ChatLayout chatLayout;
    @BindView(R.id.stateView)
    StateView stateView;

    private String diagnosisId;
    private String initialDiagnosis;

    public static void show(Activity activity, String diagnosisId, String initialDiagnosis) {
        Intent intent = new Intent(activity, MessageChatActivity.class);
        intent.putExtra("diagnosisId", diagnosisId);
        intent.putExtra("initialDiagnosis", initialDiagnosis);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_message_chat;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        chatLayout.initDefault();
        ChatInfo chatInfo = new ChatInfo();
        chatInfo.setChatName(App.getSPUtils().getString(Common.SPApi.DOCTOR_NAME));
        chatInfo.setId(App.getSPUtils().getString(Common.SPApi.DOCTOR_ID));
        chatInfo.setType(TIMConversationType.C2C);
        chatLayout.setChatInfo(chatInfo);
        chatLayout.getMessageLayout().setOnItemClickListener(null);
        TitleBarLayout titleBar = chatLayout.getTitleBar();
        titleBar.getRightGroup().setVisibility(View.GONE);
        InputLayout inputLayout = chatLayout.getInputLayout();
        // 隐藏拍照并发送
        inputLayout.disableCaptureAction(true);
        // 隐藏摄像并发送
        inputLayout.disableVideoRecordAction(true);
    }

    @Override
    protected void initData() {
        super.initData();
        Intent intent = getIntent();
        if (intent != null) {
            diagnosisId = intent.getStringExtra("diagnosisId");
            initialDiagnosis = intent.getStringExtra("initialDiagnosis");
        }
    }

    @Override
    protected MessageChatContract.Presenter initPresenter() {
        return new MessageChatPresenter(this);
    }

    @Override
    protected StateView setStateView() {
        return stateView;
    }


    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(0, R.anim.out_toptobottom);
    }

    @Override
    public void onBackPressed() {
    }

    @Override
    public void endVideoChatSuccess() {
        finish();
    }

    @Override
    public void retry(int type) {
    }

    @Override
    public void showError(int type, int errorCode, String errorMsg) {
        showContent();
        App.showToast(errorMsg);
        if (Common.UrlApi.END_INQUIRY == type) {
            finish();
        }
    }
}
