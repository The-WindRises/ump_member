package it.swiftelink.com.vcs_member.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;

import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.common.event.MsgEvent;
import it.swiftelink.com.vcs_member.App;


/**
 * Created by Administrator on 2018/12/4.
 */

public class WXPayEntryActivity extends Activity implements IWXAPIEventHandler {

    private static final String TAG = "WXPayEntryActivity";

    private IWXAPI api;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        api = WXAPIFactory.createWXAPI(this, WxPayConfig.APP_ID);
        api.handleIntent(getIntent(), this);
        Window window = getWindow();
        window.setGravity(Gravity.LEFT | Gravity.TOP);
        WindowManager.LayoutParams layoutParams = window.getAttributes();
        layoutParams.x = 0;
        layoutParams.y = 0;
        layoutParams.width = 1;
        layoutParams.height = 1;
        layoutParams.type = WindowManager.LayoutParams.TYPE_PHONE;
        layoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL;
        window.setAttributes(layoutParams);

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq baseReq) {


    }

    @Override
    public void onResp(BaseResp baseResp) {
        Bundle bundle = new Bundle();
        baseResp.toBundle(bundle);

        if (baseResp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            int code = baseResp.errCode;
            switch (code) {
                case 0:
                    App.showToast("支付成功");
                    EventBus.getDefault().post(new MsgEvent(Common.EventbusType.EVENT_PAY_WX_RESULT,Common.CommonStr.PAY_SUCCESS));
                    break;
                case -1:
                    // 支付失败 可能的原因：签名错误、未注册APPID、项目设置APPID不正确、注册的APPID与设置的不匹配、其他异常等
                    App.showToast("支付失败");
                    EventBus.getDefault().post(new MsgEvent(Common.EventbusType.EVENT_PAY_WX_RESULT,Common.CommonStr.PAY_ERROR));
                    break;
                case -2:
                    App.showToast("支付取消");
                    EventBus.getDefault().post(new MsgEvent(Common.EventbusType.EVENT_PAY_WX_RESULT,Common.CommonStr.PAY_ERROR));
                    break;
                default:
                    App.showToast("支付取消");
                    EventBus.getDefault().post(new MsgEvent(Common.EventbusType.EVENT_PAY_WX_RESULT,Common.CommonStr.PAY_ERROR));
                    break;
            }
            finish();

        }
    }
}
