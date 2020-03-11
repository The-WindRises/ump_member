package it.swiftelink.com.vcs_member.ui.activity.order;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;
import it.swiftelink.com.common.app.BaseActivity;
import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.common.event.MsgEvent;
import it.swiftelink.com.common.factory.BaseContract;
import it.swiftelink.com.common.utils.GlideLoadUtils;
import it.swiftelink.com.vcs_member.R;

public class PayResultActivity extends BaseActivity<BaseContract.Presenter> {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_pay_result)
    ImageView ivPayResult;
    @BindView(R.id.tv_pay_result)
    TextView tvPayResult;
    @BindView(R.id.tv_pay_order_id)
    TextView tvPayOrderId;

    public static void show(Activity activity ,String orderId ,boolean isPaySuccess) {
        Intent intent = new Intent(activity, PayResultActivity.class);

        intent.putExtra("isSuccess", isPaySuccess);
        intent.putExtra("orderId", orderId);
        activity.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_pay_result;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        tvTitle.setText(getString(R.string.label_pay_result));
    }

    @Override
    protected void initData() {
        super.initData();

        Intent intent = getIntent();
        if(intent!=null){
            boolean isSuccess = intent.getBooleanExtra("isSuccess", false);
            String orderId = intent.getStringExtra("orderId");

            tvPayOrderId.setText(getString(R.string.label_order_num)+orderId);

            if(isSuccess){
                tvPayResult.setText(getString(R.string.label_pay_success1));
                GlideLoadUtils.getInstance().glideLoad(this,R.mipmap.icon_pay_success,ivPayResult,R.mipmap.icon_pay_success);
            }else{
                tvPayResult.setText(getString(R.string.msg_pay_failed));
                GlideLoadUtils.getInstance().glideLoad(this,R.mipmap.icon_pay_failure,ivPayResult,R.mipmap.icon_pay_failure);
            }


        }
    }

    @OnClick({R.id.btn_back, R.id.btn_back_to_home})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                EventBus.getDefault().post(new MsgEvent(Common.EventbusType.PAY_FINISH,""));
                finish();
                break;
            case R.id.btn_back_to_home:
                EventBus.getDefault().post(new MsgEvent(Common.EventbusType.PAY_FINISH,""));
                finish();

                break;
        }
    }
}
