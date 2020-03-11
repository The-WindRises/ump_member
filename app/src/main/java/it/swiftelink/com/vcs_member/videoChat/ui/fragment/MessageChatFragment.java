package it.swiftelink.com.vcs_member.videoChat.ui.fragment;


import androidx.fragment.app.Fragment;
import android.text.TextUtils;
import android.view.View;

import com.tencent.imsdk.TIMConversationType;
import com.tencent.qcloud.tim.uikit.TUIKit;
import com.tencent.qcloud.tim.uikit.base.IUIKitCallBack;
import com.tencent.qcloud.tim.uikit.component.TitleBarLayout;
import com.tencent.qcloud.tim.uikit.modules.chat.ChatLayout;
import com.tencent.qcloud.tim.uikit.modules.chat.base.ChatInfo;
import com.tencent.qcloud.tim.uikit.modules.chat.layout.input.InputLayout;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import it.swiftelink.com.common.app.BaseFragment;
import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.common.event.ObjectEvent;
import it.swiftelink.com.vcs_member.App;
import it.swiftelink.com.vcs_member.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MessageChatFragment extends BaseFragment {


    @BindView(R.id.chat_layout)
    ChatLayout chatLayout;

    public MessageChatFragment() {
        // Required empty public constructor
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_message_chat;
    }

    @Override
    protected void initWidget(View view) {
        super.initWidget(view);

        String imUserid = App.getSPUtils().getString(Common.SPApi.IM_USERID);
        String imUserToken = App.getSPUtils().getString(Common.SPApi.IM_USER_TOKEN);

        if (!TextUtils.isEmpty(imUserid) && !TextUtils.isEmpty(imUserToken)) {
            TUIKit.login(imUserid, imUserToken, new IUIKitCallBack() {
                @Override
                public void onSuccess(Object data) {
                    initView();
                }

                @Override
                public void onError(String module, final int errCode, final String errMsg) {

                }
            });
        }
    }


    private void initView() {
        chatLayout.initDefault();

        ChatInfo chatInfo = new ChatInfo();
        chatInfo.setChatName(App.getSPUtils().getString(Common.SPApi.DOCTOR_NAME));
        chatInfo.setId(App.getSPUtils().getString(Common.SPApi.DOCTOR_ID));
        chatInfo.setType(TIMConversationType.C2C);

        chatLayout.setChatInfo(chatInfo);
        chatLayout.getMessageLayout().setOnItemClickListener(null);

        TitleBarLayout titleBar = chatLayout.getTitleBar();

        titleBar.getLeftGroup().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new ObjectEvent(Common.EventbusType.VIDEO_FLOAT_DISMISS, null,0));
            }
        });

        titleBar.getRightGroup().setVisibility(View.GONE);
        InputLayout inputLayout = chatLayout.getInputLayout();
        // 隐藏拍照并发送
        inputLayout.disableCaptureAction(true);
        // 隐藏摄像并发送
        inputLayout.disableVideoRecordAction(true);
        //隐藏发送文件
        inputLayout.disableSendFileAction(true);
    }


}
