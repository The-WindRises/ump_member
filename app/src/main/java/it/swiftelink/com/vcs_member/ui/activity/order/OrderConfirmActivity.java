package it.swiftelink.com.vcs_member.ui.activity.order;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.DecimalFormat;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.common.event.MsgEvent;
import it.swiftelink.com.common.widget.recycler.BaseRecyclerAdapter;
import it.swiftelink.com.common.widget.stateview.StateView;
import it.swiftelink.com.factory.model.InquiryReport.CofirmRecipeOrderModel;
import it.swiftelink.com.factory.model.InquiryReport.CofirmRecipeOrderResModel;
import it.swiftelink.com.factory.model.account.AddressListResModel;
import it.swiftelink.com.factory.model.order.WeixinPayConfigResModel;
import it.swiftelink.com.factory.model.order.ZhifubaoPayConfigResModel;
import it.swiftelink.com.factory.model.recipe.OrderInfoFromOrderDetailResModel;
import it.swiftelink.com.factory.model.recipe.OrderInfoFromOrderIdResModel;
import it.swiftelink.com.factory.model.recipe.PrescriptionDrugsBean;
import it.swiftelink.com.factory.model.wechat.WxChatPayEntity;
import it.swiftelink.com.factory.presenter.recipe.ComfirmRecipeOrderContract;
import it.swiftelink.com.factory.presenter.recipe.ComfirmRecipeOrderPresenter;
import it.swiftelink.com.vcs_member.App;
import it.swiftelink.com.vcs_member.R;
import it.swiftelink.com.vcs_member.base.BasePresenterActivity;
import it.swiftelink.com.vcs_member.ui.activity.user.MyAddressActivity;
import it.swiftelink.com.vcs_member.ui.pay.AuthResult;
import it.swiftelink.com.vcs_member.ui.pay.PayResult;
import it.swiftelink.com.vcs_member.wxapi.WxPayConfig;

public class OrderConfirmActivity extends BasePresenterActivity<ComfirmRecipeOrderContract.Presenter> implements ComfirmRecipeOrderContract.View {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_user_name)
    TextView tvUserName;
    @BindView(R.id.tv_user_phone)
    TextView tvUserPhone;
    @BindView(R.id.tv_user_address)
    TextView tvUserAddress;
    @BindView(R.id.rcv_inquiry_list)
    RecyclerView rcvInquiryList;
    @BindView(R.id.iv_check_weixin)
    ImageView ivCheckWeixin;
    @BindView(R.id.iv_pay_zhifubao)
    ImageView ivPayZhifubao;
    @BindView(R.id.tv_inquiry_num)
    TextView tvInquiryNum;
    @BindView(R.id.tv_price_all)
    TextView tvPriceAll;
    @BindView(R.id.stateView)
    StateView stateView;
    @BindView(R.id.rl_address_select)
    RelativeLayout rlAddressSelect;
    @BindView(R.id.ll_no_address)
    LinearLayout llNoAddress;
    @BindView(R.id.iv_select_address_enter)
    ImageView ivSelectAddressEnter;
    @BindView(R.id.iv_select_address)
    ImageView ivSelectAddress;
    @BindView(R.id.tv_goods_price)
    TextView tvGoodsPrice;
    @BindView(R.id.tv_express_price)
    TextView tvExpressPrice;


    private BaseRecyclerAdapter<PrescriptionDrugsBean> mAdapter;

    private final int PAY_WEIXIN = 0x001;
    private final int PAY_ZHIFUBAO = 0x002;

    private int payType = 0x001;
    private AddressListResModel.DataBean.DataListBean selectAddress;
    private String selectedAddressId;
    private String orderId;
    private String orderNo;
    private OrderInfoFromOrderDetailResModel.DataBean modelData;
    private OrderInfoFromOrderIdResModel.DataBean dataBean;
    private String prescriptionDrugIds;
    private String prescriptionId;

    public static void show(Activity activity, String prescriptionId, String prescriptionDrugIds, String orderId, String orderNo) {

        Intent intent = new Intent(activity, OrderConfirmActivity.class);
        intent.putExtra("prescriptionId", prescriptionId);
        intent.putExtra("prescriptionDrugIds", prescriptionDrugIds);
        intent.putExtra("orderId", orderId);
        intent.putExtra("orderNo", orderNo);
        activity.startActivity(intent);
    }

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

    @Override
    protected int getLayoutId() {
        return R.layout.activity_order_confirm;
    }

    @Override
    protected void initWidget() {
        super.initWidget();

        tvTitle.setText(getString(R.string.label_order_confirm));

        rcvInquiryList.setLayoutManager(new LinearLayoutManager(this));

        rcvInquiryList.setAdapter(mAdapter = new BaseRecyclerAdapter<PrescriptionDrugsBean>() {
            @Override
            public int getViewType(int pos) {
                return R.layout.item_rcv_confirm_order;
            }

            @Override
            protected ViewHolder<PrescriptionDrugsBean> createViewHolder(View root, int viewType) {
                return new MyViewHolder(root);
            }

            @Override
            public void onUpdate(PrescriptionDrugsBean prescriptionDrugsBean, ViewHolder<PrescriptionDrugsBean> viewHolder) {

            }
        });

        selectPayType(payType);
    }

    @Override
    protected void initData() {
        super.initData();

        Intent intent = getIntent();

        if (intent != null) {
            prescriptionId = intent.getStringExtra("prescriptionId");
            prescriptionDrugIds = intent.getStringExtra("prescriptionDrugIds");

            orderId = intent.getStringExtra("orderId");
            orderNo = intent.getStringExtra("orderNo");

            if (!TextUtils.isEmpty(prescriptionId) && !TextUtils.isEmpty(prescriptionDrugIds)) {

                mPresenter.getOrderInfoFromDetail(prescriptionId, prescriptionDrugIds);

                rlAddressSelect.setEnabled(true);
                llNoAddress.setEnabled(true);
                ivSelectAddressEnter.setVisibility(View.VISIBLE);
                ivSelectAddress.setVisibility(View.VISIBLE);
            }

            if (!TextUtils.isEmpty(orderId)) {

                rlAddressSelect.setEnabled(false);
                llNoAddress.setEnabled(false);
                ivSelectAddressEnter.setVisibility(View.GONE);
                ivSelectAddress.setVisibility(View.GONE);
                mPresenter.getOrderInfoFromOrderId(orderId);
            }
        }

    }

    @Override
    protected ComfirmRecipeOrderContract.Presenter initPresenter() {
        return new ComfirmRecipeOrderPresenter(this);
    }

    @Override
    protected StateView setStateView() {
        return stateView;
    }

    @OnClick({R.id.btn_back, R.id.rl_address_select, R.id.ll_no_address, R.id.ll_pay_weixin, R.id.ll_pay_zhifubao, R.id.tv_inquiry_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.rl_address_select:
            case R.id.ll_no_address:
                MyAddressActivity.show(this, Common.RequstCode.SELECT_ADDRESS);
                break;
            case R.id.ll_pay_weixin:
                selectPayType(PAY_WEIXIN);
                break;
            case R.id.ll_pay_zhifubao:
                selectPayType(PAY_ZHIFUBAO);
                break;
            case R.id.tv_inquiry_submit:
                next();
                break;
        }
    }

    private void next() {

        CofirmRecipeOrderModel orderModel = new CofirmRecipeOrderModel();
        if (selectAddress != null) {
            orderModel.setPatientAddressId(selectAddress.getId());
        } else if (!TextUtils.isEmpty(selectedAddressId)) {
            orderModel.setPatientAddressId(selectedAddressId);
        } else {
            App.showToast(R.string.msg_select_receive_address);
            return;
        }

        if (modelData != null) {
            orderModel.setQty(modelData.getQty() + "");
            orderModel.setPrescriptionId(prescriptionId);
            orderModel.setTotalAmount(modelData.getTotalAmount() + "");
            orderModel.setPrescriptionDrugIds(prescriptionDrugIds);
            if (modelData.getTotalAmount() < 100) {
                orderModel.setExpressPrice(10 + "");
            }
            mPresenter.confirmRecipeOrder(orderModel);
        } else if (dataBean != null) {
            toPay();
        } else {
            App.showToast(R.string.label_order_error);
        }

    }

    private void toPay() {

        if (TextUtils.isEmpty(orderId)) {
            App.showToast(R.string.label_order_error);
            return;
        }
        if (ivCheckWeixin.isSelected()) {
            payType = PAY_WEIXIN;
            mPresenter.getWeixinPayConfig(orderId);
        }

        if (ivPayZhifubao.isSelected()) {
            payType = PAY_ZHIFUBAO;
            mPresenter.getZhifuboPayConfig(orderId);
        }

    }

    private void selectPayType(int payType) {

        ivPayZhifubao.setSelected(false);
        ivCheckWeixin.setSelected(false);

        switch (payType) {
            case PAY_WEIXIN:
                ivCheckWeixin.setSelected(true);
                break;
            case PAY_ZHIFUBAO:
                ivPayZhifubao.setSelected(true);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == Common.RequstCode.SELECT_ADDRESS && resultCode == Activity.RESULT_OK) {

            selectAddress = (AddressListResModel.DataBean.DataListBean) data.getSerializableExtra("selectAddress");
            rlAddressSelect.setVisibility(View.VISIBLE);
            llNoAddress.setVisibility(View.GONE);

            tvUserName.setText(selectAddress.getName());
            tvUserPhone.setText(selectAddress.getMobile());
            tvUserAddress.setText(selectAddress.getAddressDetail());

        }
    }

    @Override
    public void getOrderInfoFromDetailSuccess(OrderInfoFromOrderDetailResModel model) {

        modelData = model.getData();
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        showContent();
        if (modelData != null && modelData.getPrescriptionDrugs().size() > 0) {
            orderId = modelData.getPrescriptionId();


            tvGoodsPrice.setText("¥" + decimalFormat.format(modelData.getTotalAmount()));
            if (modelData.getTotalAmount() > 100) {
                tvExpressPrice.setText("¥" + 0.00);
                tvPriceAll.setText(getString(R.string.label_total_money) + decimalFormat.format(modelData.getTotalAmount()));
            } else {
                tvExpressPrice.setText("¥" + 10.00);
                tvPriceAll.setText(getString(R.string.label_total_money) + decimalFormat.format(modelData.getTotalAmount() + 10));
            }
            tvInquiryNum.setText(getString(R.string.label_together) + modelData.getQty() + getString(R.string.piece));
            mAdapter.replice(modelData.getPrescriptionDrugs());
        }

        if (modelData != null && modelData.getAddress() != null && !TextUtils.isEmpty(modelData.getAddress().getId())) {
            rlAddressSelect.setVisibility(View.VISIBLE);
            llNoAddress.setVisibility(View.GONE);

            tvUserName.setText(modelData.getAddress().getName());
            tvUserPhone.setText(modelData.getAddress().getMobile());
            tvUserAddress.setText(modelData.getAddress().getAddressInfo());
            selectedAddressId = modelData.getAddress().getId();
        } else {
            rlAddressSelect.setVisibility(View.GONE);
            llNoAddress.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public void getOrderInfoFromOrderIdSuccess(OrderInfoFromOrderIdResModel model) {
        dataBean = model.getData();
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        showContent();
        if (dataBean != null && dataBean.getPrescriptionDrugs().size() > 0) {
            tvInquiryNum.setText(getString(R.string.label_together) + dataBean.getQty() + getString(R.string.piece));
            mAdapter.replice(dataBean.getPrescriptionDrugs());
            tvGoodsPrice.setText("¥" + decimalFormat.format(dataBean.getTotalAmount()));
            if (dataBean.getTotalAmount() > 100) {
                tvExpressPrice.setText("¥" + 0.00);
                tvPriceAll.setText(getString(R.string.label_total_money) + decimalFormat.format(dataBean.getTotalAmount()));
            } else {
                tvExpressPrice.setText("¥" + 10.00);
                tvPriceAll.setText(getString(R.string.label_total_money) + decimalFormat.format((dataBean.getTotalAmount() + 10)));
            }
        }

        if (dataBean != null && dataBean.getAddress() != null && !TextUtils.isEmpty(dataBean.getAddress().getId())) {
            rlAddressSelect.setVisibility(View.VISIBLE);
            llNoAddress.setVisibility(View.GONE);

            tvUserName.setText(dataBean.getAddress().getName());
            tvUserPhone.setText(dataBean.getAddress().getMobile());
            tvUserAddress.setText(dataBean.getAddress().getAddressInfo());
            selectedAddressId = dataBean.getAddress().getId();

        } else {
            rlAddressSelect.setVisibility(View.GONE);
            llNoAddress.setVisibility(View.VISIBLE);
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
            App.showToast(R.string.pay_error);
        }
    }

    @Override
    public void getZhifuboPayConfigSuccess(ZhifubaoPayConfigResModel resModel) {
        if (resModel.getData() != null) {
            payV2(resModel.getData().getOrder());
        }
    }

    @Override
    public void confirmRecipeOrderSuccess(CofirmRecipeOrderResModel resModel) {

        if (resModel.getData() != null) {
            orderId = resModel.getData().getId();
            orderNo = resModel.getData().getNo();
            toPay();
        } else {
            App.showToast(R.string.order_create_fail);
        }
    }

    @Override
    public void retry(int type) {

    }

    @Override
    public void showError(int type, int errorCode, String errorMsg) {

        showContent();
        App.showToast(errorMsg);
    }


    private void onPayFinish(boolean isSuccess) {

        finish();
        PayResultActivity.show(this, orderNo, isSuccess);


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


    /***************************支付宝支付*****************************/
    /**
     * 支付宝支付业务示例
     */
    public void payV2(final String orderInfo) {


        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(OrderConfirmActivity.this);
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
                        App.showToast(R.string.label_pay_error);
                        onPayFinish(true);
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        //showAlert(PayActivity.this, "支付失败: " + payResult);
                        App.showToast(R.string.label_pay_error);
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
            App.showToast(R.string.label_pay_error);
            return;
        }

        if (!WxPayConfig.APP_ID.equals(wxChatPayEntity.getAppid())) {
            App.showToast(R.string.label_pay_error);
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


    class MyViewHolder extends BaseRecyclerAdapter.ViewHolder<PrescriptionDrugsBean> {

        @BindView(R.id.tv_drag_name)
        TextView tvDragName;
        @BindView(R.id.tv_price_single)
        TextView tvPriceSingle;
        @BindView(R.id.tv_drug_spec)
        TextView tvDrugSpec;
        @BindView(R.id.tv_drug_unit)
        TextView tvDrugUnit;
        @BindView(R.id.tv_drug_num)
        TextView tvDrugNum;
        @BindView(R.id.tv_drug_num_all)
        TextView tvDrugNumAll;
        @BindView(R.id.tv_price_all)
        TextView tvPriceAll;

        public MyViewHolder(View itemView) {
            super(itemView);

//            LayoutInflater.from(getBaseContext()).inflate(R.layout.item_rcv_confirm_order, null);
        }

        @Override
        protected void onBind(PrescriptionDrugsBean dataBean) {

            DecimalFormat decimalFormat = new DecimalFormat("0.00");

            tvDragName.setText(dataBean.getName());
            tvPriceSingle.setText(getString(R.string.label_unit_price) + decimalFormat.format(dataBean.getPrice()));
            tvPriceSingle.setText("¥" + decimalFormat.format(dataBean.getPrice()));
            tvDrugNum.setText("X" + dataBean.getQuantity());
//            String unit = TextUtils.isEmpty(dataBean.getOnceUnit()) ? "" : dataBean.getOnceUnit();
            tvDrugNumAll.setText(getString(R.string.label_together) + dataBean.getQuantity() + getString(R.string.unit));
            tvPriceAll.setText("¥" + decimalFormat.format(dataBean.getQuantity() * dataBean.getPrice()));
//            tvDrugUnit.setText(TextUtils.isEmpty(dataBean.getOnceUnit()) ? "" : dataBean.getOnceUnit());
            tvDrugSpec.setText(TextUtils.isEmpty(dataBean.getSpecification()) ? "" : dataBean.getSpecification());

        }
    }
}
