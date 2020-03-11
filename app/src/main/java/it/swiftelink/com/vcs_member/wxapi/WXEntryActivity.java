package it.swiftelink.com.vcs_member.wxapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.google.gson.Gson;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.common.event.MsgEvent;
import it.swiftelink.com.common.net.ApiException;
import it.swiftelink.com.factory.model.account.LoginModel;
import it.swiftelink.com.factory.model.account.LoginResModel;
import it.swiftelink.com.factory.model.wechat.WXAccessTokenEntity;
import it.swiftelink.com.factory.model.wechat.WXBaseRespEntity;
import it.swiftelink.com.factory.model.wechat.WXUserInfo;
import it.swiftelink.com.factory.net.NetWork;
import it.swiftelink.com.vcs_member.App;
import it.swiftelink.com.vcs_member.R;
import it.swiftelink.com.vcs_member.ui.activity.account.BindPhoneActivity;
import rx.Observable;

/**
 * 微信第三方登录调转页面
 */
public class WXEntryActivity extends Activity implements IWXAPIEventHandler {

    /**
     * 微信登录相关
     */
    private IWXAPI api;
    private Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //通过WXAPIFactory工厂获取IWXApI的示例
        api = WXAPIFactory.createWXAPI(this, WxPayConfig.APP_ID, true);
        //将应用的appid注册到微信
        api.registerApp(WxPayConfig.APP_ID);
        try {
            boolean result = api.handleIntent(getIntent(), this);
            if (!result) {
                finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        api.handleIntent(data, this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
        finish();
    }

    @Override
    public void onReq(BaseReq baseReq) {
        //TODO 第三方登錄返回數據
    }

    @Override
    public void onResp(BaseResp baseResp) {
        gson = new Gson();
        WXBaseRespEntity entity = gson.fromJson(gson.toJson(baseResp), WXBaseRespEntity.class);
        String result = "";
        switch (baseResp.errCode) {
            case BaseResp.ErrCode.ERR_OK:
                switch (baseResp.transaction) {
                    case "login":
                        result = getResources().getString(R.string.msg_authorization_success);
                        Observable<WXAccessTokenEntity> observable = NetWork.getRequest().accreditWeixin(WxPayConfig.APP_ID, WxPayConfig.APP_SECRET, entity.getCode(), "authorization_code");
                        NetWork.getInstance().baseNetRequest(observable, new NetWork.NetCallback<WXAccessTokenEntity>() {
                            @Override
                            public void onError(ApiException e) {
//                                App.showToast(e.getMessage());
                                finish();
                            }

                            @Override
                            public void onSuccess(WXAccessTokenEntity resModel) {
                                if (resModel != null) {
                                    getUserInfo(resModel);
                                } else {
//                                    App.showToast("获取失败");
                                    finish();
                                }
                            }
                        });
                        break;
                    case "supplier":
                        result = "分享成功";
                        finish();
                        break;
                    case "recommend":
                        result = "推荐成功";
                        finish();
                        break;
                    default:
                        break;
                }
                break;
            case BaseResp.ErrCode.ERR_USER_CANCEL:
                switch (baseResp.transaction) {
                    case "login":
                        result = "授权取消";
                        break;
                    case "supplier":
                        result = "分享取消";
                        break;
                    case "recommend":
                        result = "推荐取消";
                        finish();
                        break;
                    default:
                        break;
                }
                finish();
                break;
            case BaseResp.ErrCode.ERR_AUTH_DENIED:
                switch (baseResp.transaction) {
                    case "login":
                        result = "授权被拒绝";
                        break;
                    case "supplier":
                        result = "分享被拒绝";
                        break;
                    case "recommend":
                        result = "推荐被拒绝";
                        finish();
                        break;
                    default:
                        break;
                }
                finish();
                break;
            default:
                switch (baseResp.transaction) {
                    case "login":
                        result = "授权返回";
                        break;
                    case "supplier":
                        result = "分享返回";
                        break;
                    case "recommend":
                        result = "推荐返回";
                        finish();
                        break;
                    default:
                        break;
                }
                finish();
                break;
        }
//        App.showToast(result);
    }

    /**
     * 获取个人信息
     *
     * @param accessTokenEntity
     */
    private void getUserInfo(WXAccessTokenEntity accessTokenEntity) {
        Map<String, Object> map = new HashMap<>();
        map.put("access_token", accessTokenEntity.getAccess_token());
        map.put("openid", accessTokenEntity.getOpenid());//openid:授权用户唯一标识

        Observable<WXUserInfo> observable = NetWork.getRequest().getWeixinUserInfo(accessTokenEntity.getAccess_token(), accessTokenEntity.getOpenid());

        NetWork.getInstance().baseNetRequest(observable, new NetWork.NetCallback<WXUserInfo>() {
            @Override
            public void onError(ApiException e) {
//                App.showToast(e.getMessage());
                finish();
            }

            @Override
            public void onSuccess(WXUserInfo wxUserInfo) {

                if (wxUserInfo != null) {
                    weixinLogin(wxUserInfo);
                } else {
//                    App.showToast("获取错误...");
                    finish();
                }

            }
        });
    }


    private void weixinLogin(WXUserInfo resModel) {

        final LoginModel loginModel = new LoginModel();
        loginModel.setOpen_id(resModel.getOpenid());
        //微信登陆增加unionId
        loginModel.setExtType(1);
        loginModel.setUnionId(resModel.getUnionid());
        loginModel.setHeadImg(resModel.getHeadimgurl());
        loginModel.setUserName(resModel.getNickname());

        Observable<LoginResModel> observable = NetWork.getRequest().weixinLogin(loginModel);
        NetWork.getInstance().netRequest(observable, new NetWork.NetCallback<LoginResModel>() {
            @Override
            public void onError(ApiException e) {
//                App.showToast(e.getMessage());
                finish();
            }

            @Override
            public void onSuccess(LoginResModel resModel) {
                if (resModel.isSuccess()) {
                    App.getSPUtils().put(Common.SPApi.TOKEN, resModel.getData().getAccessToken());
                    App.getSPUtils().put(Common.SPApi.ID, resModel.getData().getUserId());
                    App.getSPUtils().put(Common.SPApi.LOGINID, resModel.getData().getLoginId());
                    App.getSPUtils().put(Common.SPApi.USER_NAME,resModel.getData().getUserName());
                    App.getSPUtils().put(Common.SPApi.USER_HEADER_IMAGE,resModel.getData().getHeadImg());

                    EventBus.getDefault().post(new MsgEvent(Common.EventbusType.LOGIN_WX, null));
                } else {
                    if (403 == resModel.getCode()) {
                        BindPhoneActivity.show(WXEntryActivity.this, loginModel);
                        finish();
                    }
//                    App.showToast(resModel.getMessage());
                    finish();
                }


            }
        });
    }
}
