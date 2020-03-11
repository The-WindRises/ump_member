package it.swiftelink.com.vcs_member.ui.activity.appointment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import androidx.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.common.event.MsgEvent;
import it.swiftelink.com.common.factory.BaseResModel;
import it.swiftelink.com.common.widget.stateview.StateView;
import it.swiftelink.com.factory.model.appointment.CheckPayPasswordResmodel;
import it.swiftelink.com.factory.model.appointment.PackConfirmModel;
import it.swiftelink.com.factory.model.appointment.PackageOrderConfirmInfoResModel;
import it.swiftelink.com.factory.model.mian.SetMealListResModel;
import it.swiftelink.com.factory.model.order.CouponListResModel;
import it.swiftelink.com.factory.model.order.PackageInfoResModel;
import it.swiftelink.com.factory.model.order.PackageOrderListResModel;
import it.swiftelink.com.factory.model.order.WeixinPayConfigResModel;
import it.swiftelink.com.factory.model.order.ZhifubaoPayConfigResModel;
import it.swiftelink.com.factory.model.wechat.WxChatPayEntity;
import it.swiftelink.com.factory.presenter.order.PayContract;
import it.swiftelink.com.factory.presenter.order.PayPresenter;
import it.swiftelink.com.vcs_member.App;
import it.swiftelink.com.vcs_member.R;
import it.swiftelink.com.vcs_member.base.BasePresenterActivity;
import it.swiftelink.com.vcs_member.ui.activity.account.InputPayPasswordActivity;
import it.swiftelink.com.vcs_member.ui.activity.account.SetPayPsdActivity;
import it.swiftelink.com.vcs_member.ui.activity.my.SelectCouponActivity;
import it.swiftelink.com.vcs_member.ui.activity.order.PayResultActivity;
import it.swiftelink.com.vcs_member.ui.pay.AuthResult;
import it.swiftelink.com.vcs_member.ui.pay.PayResult;
import it.swiftelink.com.vcs_member.wxapi.WxPayConfig;

public class PackageBuyConfirmActivity extends BasePresenterActivity<PayContract.Presenter> implements PayContract.View {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_package_name)
    TextView tvPackageName;
    @BindView(R.id.tv_package_id)
    TextView tvPackageId;
    @BindView(R.id.tv_package_price)
    TextView tvPackagePrice;
    @BindView(R.id.tv_package_price_all)
    TextView tvPackagePriceAll;
    @BindView(R.id.iv_check_weixin)
    ImageView ivCheckWeixin;
    @BindView(R.id.iv_pay_zhifubao)
    ImageView ivPayZhifubao;
    @BindView(R.id.iv_pay_balance)
    ImageView ivPayBalance;
    @BindView(R.id.tv_price_all)
    TextView tvPriceAll;
    @BindView(R.id.tv_inquiry_submit)
    TextView tvInquirySubmit;
    @BindView(R.id.tv_coupon_price)
    TextView tvCouponPrice;
    @BindView(R.id.stateView)
    StateView stateView;
    @BindView(R.id.ll_pay_coupon)
    LinearLayout llPayCoupon;
    @BindView(R.id.ll_pay_weixin)
    LinearLayout llPayWeixin;
    @BindView(R.id.ll_pay_zhifubao)
    LinearLayout llPayZhifubao;


    private static final int TYPE_WEIXIN = 0x001;
    private static final int TYPE_ZHIFUBAO = 0x002;
    private static final int TYPE_AMOUNT = 0x003;


    private String selectCouponId;
    private boolean haveCoupon;
    private String  languageType;
    private int payType = 0x001;

    private String orderId;
    private String orderNum;
    private SetMealListResModel.DataBean.PackageItemInfoBean packageBean;
    private PackageOrderListResModel.DataBean.DataListBean packageOrderBean;
    private PackageInfoResModel.DataBean packageDetailBean;

    private double priceAll;
    private PackageOrderConfirmInfoResModel.DataBean packageOrderConfirmInfo;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        EnvUtils.setEnv(EnvUtils.EnvEnum.SANDBOX);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public static void show(Activity activity, SetMealListResModel.DataBean.PackageItemInfoBean packageBean, PackageOrderListResModel.DataBean.DataListBean packageOrderBean) {

        Intent intent = new Intent(activity, PackageBuyConfirmActivity.class);

        intent.putExtra("packageBean", packageBean);
        intent.putExtra("packageOrderBean", packageOrderBean);


        activity.startActivity(intent);
    }


    public static void showByCardDetail(Activity activity, PackageInfoResModel.DataBean packageBean, PackageOrderListResModel.DataBean.DataListBean packageOrderBean) {

        Intent intent = new Intent(activity, PackageBuyConfirmActivity.class);
        intent.putExtra("packageDetailBean", packageBean);

        activity.startActivity(intent);
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_package_buy_confirm;
    }

    @Override
    protected void initWidget() {
        super.initWidget();

        tvTitle.setText(getString(R.string.label_pay));
    }

    @Override
    protected void initData() {
        super.initData();
        languageType= App.getSPUtils().getString(Common.SPApi.SELECT_LANGUAGE, Common.CommonStr.LANGUAGE1);
        Intent intent = getIntent();
        setPayType(payType);
        if (intent != null) {
            packageBean = (SetMealListResModel.DataBean.PackageItemInfoBean) intent.getSerializableExtra("packageBean");
            packageOrderBean = (PackageOrderListResModel.DataBean.DataListBean) intent.getSerializableExtra("packageOrderBean");
            packageDetailBean = (PackageInfoResModel.DataBean) intent.getSerializableExtra("packageDetailBean");
            DecimalFormat decimalFormat = new DecimalFormat("0.00");
            if (packageDetailBean != null) {
                String language = App.getSPUtils().getString(Common.SPApi.SELECT_LANGUAGE, Common.CommonStr.LANGUAGE1);
                PackageInfoResModel.DataBean.PackageInfosBean selectPackageInfo = null;
                List<PackageInfoResModel.DataBean.PackageInfosBean> packageInfosBeans = packageDetailBean.getPackageInfos();
                for (PackageInfoResModel.DataBean.PackageInfosBean packageBean : packageInfosBeans) {
                    if (language.equals(packageBean.getLanguage())) {
                        selectPackageInfo = packageBean;
                    }
                }
                tvPackageName.setText(selectPackageInfo.getName());
                tvPackageId.setText(packageDetailBean.getId());
                tvPackagePrice.setText("¥" + decimalFormat.format(packageDetailBean.getDiscountPrice()));
                tvPackagePriceAll.setText("¥" + decimalFormat.format(packageDetailBean.getDiscountPrice()));
                tvPriceAll.setText("¥" + decimalFormat.format(packageDetailBean.getDiscountPrice()));
                priceAll = packageDetailBean.getDiscountPrice();
                mPresenter.getCouponList(1, 10, String.valueOf(priceAll), "Not_Use", languageType);
            } else if (packageBean != null) {

                String language = App.getSPUtils().getString(Common.SPApi.SELECT_LANGUAGE, Common.CommonStr.LANGUAGE1);
                SetMealListResModel.DataBean.PackageItemInfoBean.PackageInfosBean selectPackageInfo = null;
                List<SetMealListResModel.DataBean.PackageItemInfoBean.PackageInfosBean> packageInfosBeans = packageBean.getPackageInfos();
                for (SetMealListResModel.DataBean.PackageItemInfoBean.PackageInfosBean packageInfosBean : packageInfosBeans) {
                    if (language.equals(packageInfosBean.getLanguage())) {
                        selectPackageInfo = packageInfosBean;
                    }
                }
                tvPackageName.setText(selectPackageInfo.getName());
                tvPackageId.setText(packageBean.getId());
                tvPackagePrice.setText("¥" + decimalFormat.format(packageBean.getDiscountPrice()));
                tvPackagePriceAll.setText("¥" + decimalFormat.format(packageBean.getDiscountPrice()));
                tvPriceAll.setText("¥" + decimalFormat.format(packageBean.getDiscountPrice()));
                priceAll = packageBean.getDiscountPrice();
                mPresenter.getCouponList(1, 10, String.valueOf(priceAll), "Not_Use", languageType);
            } else if (packageOrderBean != null) {
                orderId = packageOrderBean.getOrderId();
                orderNum = packageOrderBean.getOrderNo();
                llPayCoupon.setEnabled(false);
                tvPackageName.setText(packageOrderBean.getPackageName());
                tvPackageId.setText(packageOrderBean.getOrderNo());
                tvPackagePrice.setText("¥" + decimalFormat.format(packageOrderBean.getDiscountPrice()));
                tvPackagePriceAll.setText("¥" + decimalFormat.format(packageOrderBean.getDiscountPrice()));
                tvPriceAll.setText("¥" + decimalFormat.format(packageOrderBean.getDiscountPrice() - packageOrderBean.getCouponAmount()));
                selectCouponId = packageOrderBean.getCouponId();
                tvCouponPrice.setText("¥" + decimalFormat.format(packageOrderBean.getCouponAmount()));
                priceAll = (packageOrderBean.getDiscountPrice() - packageOrderBean.getCouponAmount());
            } else {
                App.showToast(R.string.label_data_error);
                finish();
            }

        }

    }

    @Override
    protected PayContract.Presenter initPresenter() {
        return new PayPresenter(this);
    }

    @Override
    protected StateView setStateView() {
        return stateView;
    }


    @OnClick({R.id.btn_back, R.id.ll_pay_weixin, R.id.ll_pay_zhifubao, R.id.ll_pay_balance,
            R.id.ll_pay_coupon, R.id.tv_inquiry_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.ll_pay_weixin:
                setPayType(TYPE_WEIXIN);
                break;
            case R.id.ll_pay_zhifubao:
                setPayType(TYPE_ZHIFUBAO);
                break;
            case R.id.ll_pay_balance:
                setPayType(TYPE_AMOUNT);
                break;
            case R.id.ll_pay_coupon:
                if(haveCoupon) {
                    Intent intent = new Intent(this, SelectCouponActivity.class);
                    intent.putExtra("orderAmount", String.valueOf(priceAll));
                    startActivityForResult(intent, Common.RequstCode.SELECT_COUPON);
                }else {
                    Toast toast=Toast.makeText(this,getResources().getString(R.string.no_useful_coupon),Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER,0,0);
                    toast.show();
                }
                break;
            case R.id.tv_inquiry_submit:
                next();
                break;
        }
    }

    private void next() {
        if (packageBean != null && packageOrderBean == null && packageOrderConfirmInfo == null) {
            PackConfirmModel confirmModel = new PackConfirmModel();

            confirmModel.setId(packageBean.getId());
            confirmModel.setTotalAmount(packageBean.getDiscountPrice() + "");
            confirmModel.setCouponId(selectCouponId);

            mPresenter.getPackageOrderConfirmInfo(confirmModel);
        }else if(packageDetailBean != null && packageOrderBean == null && packageOrderConfirmInfo == null){
            PackConfirmModel confirmModel = new PackConfirmModel();

            confirmModel.setId(packageDetailBean.getId());
            confirmModel.setTotalAmount(packageDetailBean.getDiscountPrice() + "");
            confirmModel.setCouponId(selectCouponId);

            mPresenter.getPackageOrderConfirmInfo(confirmModel);
        }

        else if (packageOrderBean != null || packageOrderConfirmInfo != null) {
            toPay();
        } else {
            App.showToast(R.string.msg_order_error);
        }

    }

    private void toPay() {

        if (TextUtils.isEmpty(orderId)) {
            App.showToast(R.string.msg_order_generation_failed);
            return;
        }

        if (priceAll > 0) {
            if (ivCheckWeixin.isSelected()) {
                payType = TYPE_WEIXIN;
                mPresenter.getWeixinPayConfig(orderId);
            }

            if (ivPayZhifubao.isSelected()) {
                payType = TYPE_ZHIFUBAO;
                mPresenter.getZhifuboPayConfig(orderId);
            }

            if (ivPayBalance.isSelected()) {
                payType = TYPE_AMOUNT;
                mPresenter.checkPassword();
            }
        } else {
            payType = TYPE_AMOUNT;
            mPresenter.checkPassword();
        }


    }

    private void setPayType(int payType) {

        ivCheckWeixin.setSelected(false);
        ivPayZhifubao.setSelected(false);
        ivPayBalance.setSelected(false);
        if (TYPE_WEIXIN == payType) {
            ivCheckWeixin.setSelected(true);
        }

        if (TYPE_ZHIFUBAO == payType) {
            ivPayZhifubao.setSelected(true);
        }

        if (TYPE_AMOUNT == payType) {
            ivPayBalance.setSelected(true);
        }
    }


    @Override
    public void getWeixinPayConfigSuccess(WeixinPayConfigResModel resModel) {

        showContent();
        if (resModel != null && resModel.getData() != null) {
            WeixinPayConfigResModel.DataBean modelData = resModel.getData();
            WxChatPayEntity wxChatPayEntity = new WxChatPayEntity();
            wxChatPayEntity.setAppid(modelData.getOrder().getAppid());
            wxChatPayEntity.setNoncestr(modelData.getOrder().getNoncestr());
            wxChatPayEntity.setPackageValue(modelData.getOrder().getPackageX());
            wxChatPayEntity.setPartnerid(modelData.getOrder().getPartnerid());
            wxChatPayEntity.setSign(modelData.getOrder().getSign());
            wxChatPayEntity.setTimeStamp(modelData.getOrder().getTimestamp());
            wxChatPayEntity.setPrepayid(modelData.getOrder().getPrepayid());
            startWxPay(wxChatPayEntity);
        } else {
            App.showToast(R.string.label_pay_error);
        }
    }

    @Override
    public void getZhifuboPayConfigSuccess(ZhifubaoPayConfigResModel resModel) {

        if (resModel.getData() != null) {
            payV2(resModel.getData().getOrder());
        }

    }

    @Override
    public void payAmountSuccess(BaseResModel resModel) {

        showContent();
        onPayFinish(true);
    }

    @Override
    public void checkPasswordSuccess(CheckPayPasswordResmodel resModel) {

        showContent();

        if (resModel.getData() != null) {
            boolean isSetPayPsd = "Y".equals(resModel.getData().getCheckResult());

            if (isSetPayPsd) {
                showInputPayPsdDiaLog();
            } else {
                SetPayPsdActivity.show(this, Common.CommonStr.Pay_PSD_TYPE1);
            }
        }

    }

    private void showInputPayPsdDiaLog() {

        Intent intent = new Intent(this, InputPayPasswordActivity.class);
        startActivityForResult(intent, Common.RequstCode.PAY_INPUT_PASSWORD_RES_CODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Common.RequstCode.PAY_INPUT_PASSWORD_RES_CODE && null != data) {

            String inputPsd = data.getStringExtra("inputPsd");
            mPresenter.payAmount(orderId, inputPsd);

        }

        if (requestCode == Common.RequstCode.SELECT_COUPON && null != data) {
            DecimalFormat decimalFormat = new DecimalFormat("0.00");
            selectCouponId = data.getStringExtra("selectCouponId");
            double selectCouponPrice = data.getDoubleExtra("selectCouponPrice", 0);
            tvCouponPrice.setText("¥" + decimalFormat.format(selectCouponPrice));
            if (packageBean != null) {
                double discountPrice = packageBean.getDiscountPrice();
                double priceAll = discountPrice - selectCouponPrice;
                if (priceAll == 0) {
                    llPayWeixin.setEnabled(false);
                    llPayZhifubao.setEnabled(false);
                    ivPayZhifubao.setSelected(false);
                    ivCheckWeixin.setSelected(false);
                    ivPayBalance.setSelected(true);
                }
                tvPriceAll.setText("¥" + decimalFormat.format(priceAll));
            }

            if (packageOrderBean != null) {
                double discountPrice = packageOrderBean.getDiscountPrice();
                double priceAll = discountPrice - selectCouponPrice;
                tvPriceAll.setText("¥" + decimalFormat.format(priceAll));

                if (priceAll == 0) {
                    llPayWeixin.setEnabled(false);
                    llPayZhifubao.setEnabled(false);
                    ivPayZhifubao.setSelected(false);
                    ivCheckWeixin.setSelected(false);
                    ivPayBalance.setSelected(true);
                }
            }

            if (packageDetailBean != null) {
                double discountPrice = packageDetailBean.getDiscountPrice();
                double priceAll = discountPrice - selectCouponPrice;
                if (priceAll == 0) {
                    llPayWeixin.setEnabled(false);
                    llPayZhifubao.setEnabled(false);
                    ivPayZhifubao.setSelected(false);
                    ivCheckWeixin.setSelected(false);
                    ivPayBalance.setSelected(true);
                }
                tvPriceAll.setText("¥" + decimalFormat.format(priceAll));
            }

        }
    }


    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MsgEvent msgEvent) {
        switch (msgEvent.getType()) {
            case Common.EventbusType.EVENT_PAY_WX_RESULT:

                if (Common.CommonStr.PAY_SUCCESS.equals(msgEvent.getMsg())) {
                    onPayFinish(true);
                } else {
                    onPayFinish(false);
                }

                break;
            default:
                break;
        }
    }

    @Override
    public void getPackageOrderConfirmInfoSuccess(PackageOrderConfirmInfoResModel resModel) {

        showContent();
        packageOrderConfirmInfo = resModel.getData();
        if (packageOrderConfirmInfo != null) {
            orderId = packageOrderConfirmInfo.getId();
            orderNum = packageOrderConfirmInfo.getNo();
        }
        toPay();

    }

    @Override
    public void getCouponListSuccess(CouponListResModel model) {
        if(null!=model){
            if(null!=model.getData()){
                if(null!=model.getData().getDataList() && model.getData().getDataList().size()>0){
                    haveCoupon=true;
                }else{
                    haveCoupon=false;
                }
            }
        }
    }

    @Override
    public void retry(int type) {

    }

    @Override
    public void showError(int type, int errorCode, String errorMsg) {
        super.showError(type, errorCode, errorMsg);
        showContent();
        if (Common.UrlApi.PAY_AMOUNT == type) {
            onPayFinish(false);
        }
        App.showToast(errorMsg);
    }


    private void onPayFinish(boolean isSuccess) {

        finish();
        PayResultActivity.show(this, orderNum, isSuccess);


    }

    /***************************支付宝支付*****************************/
    /**
     * 支付宝支付业务示例
     */
    public void payV2(final String orderInfo) {


        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(PackageBuyConfirmActivity.this);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Log.i("msp", result.toString());

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }


    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        //showAlert(PayActivity.this, "支付成功: " + payResult);
                        App.showToast(R.string.label_pay_success);
                        onPayFinish(true);
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        //showAlert(PayActivity.this, "支付失败: " + payResult);
                        App.showToast(R.string.msg_pay_failed);
                        onPayFinish(false);
                    }
                    break;
                }
                case SDK_AUTH_FLAG: {
                    @SuppressWarnings("unchecked")
                    AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
                    String resultStatus = authResult.getResultStatus();

                    // 判断resultStatus 为“9000”且result_code
                    // 为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
                    if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {
                        // 获取alipay_open_id，调支付时作为参数extern_token 的value
                        // 传入，则支付账户为该授权账户
//                        showAlert(PayActivity.this, "授权成功: " + authResult);
                        App.showToast(R.string.msg_authorization_success);
                        onPayFinish(true);
                    } else {
                        // 其他状态值则为授权失败
                        App.showToast(R.string.label_authorization_fail);
//                        showAlert(PayActivity.this, "授权失败: " + authResult);
                        onPayFinish(false);
                    }
                    break;
                }
                default:
                    break;
            }
        }

    };

    private static void showAlert(Context ctx, String info) {
        showAlert(ctx, info, null);
    }

    private static void showAlert(Context ctx, String info, DialogInterface.OnDismissListener onDismiss) {
        new AlertDialog.Builder(ctx)
                .setMessage(info)
                .setPositiveButton(App.getInstance().getString(R.string.confirm), null)
                .setOnDismissListener(onDismiss)
                .show();

    }

    /*****************************微信支付***************************************/

    /**
     * 微信支付
     */
    public void startWxPay(WxChatPayEntity wxChatPayEntity) {

        if (TextUtils.isEmpty(WxPayConfig.APP_ID)) {
            App.showToast(R.string.msg_pay_failed);
            onPayFinish(false);
            return;
        }

        if (!WxPayConfig.APP_ID.equals(wxChatPayEntity.getAppid())) {
            App.showToast(R.string.msg_pay_failed);
            onPayFinish(false);
            return;
        }
        IWXAPI wxapi = WXAPIFactory.createWXAPI(this, WxPayConfig.APP_ID, true);
        // 将该app注册到微信
        wxapi.registerApp(WxPayConfig.APP_ID);

        PayReq req = new PayReq();
        req.appId = WxPayConfig.APP_ID;
        req.partnerId = wxChatPayEntity.getPartnerid();
        req.prepayId = wxChatPayEntity.getPrepayid();
        req.nonceStr = wxChatPayEntity.getNoncestr();
        req.timeStamp = wxChatPayEntity.getTimeStamp();
        // "Sign=WXPay"
        req.packageValue = wxChatPayEntity.getPackageValue();
        req.sign = wxChatPayEntity.getSign();

        wxapi.sendReq(req);
    }
}
