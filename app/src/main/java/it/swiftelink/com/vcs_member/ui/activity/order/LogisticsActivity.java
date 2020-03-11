package it.swiftelink.com.vcs_member.ui.activity.order;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import it.swiftelink.com.common.utils.GlideLoadUtils;
import it.swiftelink.com.common.widget.recycler.BaseRecyclerAdapter;
import it.swiftelink.com.common.widget.stateview.StateView;
import it.swiftelink.com.factory.model.order.LogisticsResModel;
import it.swiftelink.com.factory.presenter.order.LogisticsContract;
import it.swiftelink.com.factory.presenter.order.LogisticsPresenter;
import it.swiftelink.com.vcs_member.App;
import it.swiftelink.com.vcs_member.R;
import it.swiftelink.com.vcs_member.base.BasePresenterActivity;

public class LogisticsActivity extends BasePresenterActivity<LogisticsContract.Presenter> implements LogisticsContract.View {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_logistics_name)
    TextView tvLogisticsName;
    @BindView(R.id.tv_order_id)
    TextView tvOrderId;
    @BindView(R.id.tv_address_received)
    TextView tvAddressReceived;
    @BindView(R.id.rcv_logistics_list)
    RecyclerView rcvLogisticsList;
    @BindView(R.id.stateView)
    StateView stateView;
    @BindView(R.id.iv_logo)
    ImageView ivLogo;

    private BaseRecyclerAdapter<LogisticsResModel.DataBean.ResultBean.ListBean> mAdapter;
    private String orderId;

    public static void show(Activity activity, String orderId) {
        Intent intent = new Intent(activity, LogisticsActivity.class);
        intent.putExtra("orderId", orderId);
        activity.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_check_logistics;
    }

    @Override
    protected void initWidget() {
        super.initWidget();

        rcvLogisticsList.setLayoutManager(new LinearLayoutManager(this));

        rcvLogisticsList.setAdapter(mAdapter = new BaseRecyclerAdapter<LogisticsResModel.DataBean.ResultBean.ListBean>() {
            @Override
            public int getViewType(int pos) {
                return R.layout.item_rcv_logistics;
            }

            @Override
            protected ViewHolder<LogisticsResModel.DataBean.ResultBean.ListBean> createViewHolder(View root, int viewType) {
                return new MyViewHolder(root);
            }

            @Override
            public void onUpdate(LogisticsResModel.DataBean.ResultBean.ListBean logisticsResModel, ViewHolder<LogisticsResModel.DataBean.ResultBean.ListBean> viewHolder) {

            }
        });
        tvTitle.setText(getString(R.string.label_look_logistic));
    }

    @Override
    protected void initData() {
        super.initData();

        Intent intent = getIntent();
        if (intent != null) {
            orderId = intent.getStringExtra("orderId");

            if (!TextUtils.isEmpty(orderId)) {
                mPresenter.getLogistics(orderId);
            }
        }

    }

    @Override
    protected LogisticsContract.Presenter initPresenter() {
        return new LogisticsPresenter(this);
    }

    @Override
    protected StateView setStateView() {
        return stateView;
    }

    @OnClick({R.id.btn_back, R.id.tv_copy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.tv_copy:

                copyText();
                break;
        }
    }

    @Override
    public void getLogisticsSuccess(LogisticsResModel model) {

        showContent();
        LogisticsResModel.DataBean data = model.getData();
        if (data != null) {

            if (data.getResult() != null) {
                LogisticsResModel.DataBean.ResultBean result = data.getResult();
                tvLogisticsName.setText(TextUtils.isEmpty(result.getExpName()) ? "" : result.getExpName());
                tvOrderId.setText(TextUtils.isEmpty(result.getNumber()) ? "" : result.getNumber());
                GlideLoadUtils.getInstance().glideLoadCircle(this, result.getLogo(), ivLogo, R.mipmap.icon_image_defout);

                mAdapter.replice(data.getResult().getList());
            }

            tvAddressReceived.setText(TextUtils.isEmpty(data.getAddressInfo()) ? getString(R.string.label_address) : getString(R.string.label_address) + data.getAddressInfo());

        }


    }

    private void copyText() {

        String orderId = tvOrderId.getText().toString().trim();

        if (!TextUtils.isEmpty(orderId)) {
            //获取剪贴板管理器：
            ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
            // 创建普通字符型ClipData
            ClipData mClipData = ClipData.newPlainText("Label", orderId);
            // 将ClipData内容放到系统剪贴板里。
            cm.setPrimaryClip(mClipData);

            App.showToast(R.string.label_copy_success);
        }


    }

    @Override
    public void retry(int type) {

    }

    @Override
    public void showError(int type, int errorCode ,String errorMsg) {
        super.showError(type, errorCode ,errorMsg);
        showContent();
        App.showToast(errorMsg);
    }


    class MyViewHolder extends BaseRecyclerAdapter.ViewHolder<LogisticsResModel.DataBean.ResultBean.ListBean> {


        @BindView(R.id.tv_logistics_time)
        TextView tvLogisticsTime;
        @BindView(R.id.tv_logistics_msg)
        TextView tvLogisticsMsg;
        @BindView(R.id.iv_circle)
        ImageView ivCircle;

        public MyViewHolder(View itemView) {
            super(itemView);
//            LayoutInflater.from(this).inflate(R.layout.item_rcv_logistics, null);
        }

        @Override
        protected void onBind(LogisticsResModel.DataBean.ResultBean.ListBean listBean) {

            if (mPosition == 0) {
                tvLogisticsTime.setSelected(true);
                tvLogisticsMsg.setSelected(true);
                ivCircle.setSelected(true);
            } else {
                tvLogisticsTime.setSelected(false);
                tvLogisticsMsg.setSelected(false);
                ivCircle.setSelected(false);
            }
            tvLogisticsTime.setText(listBean.getTime());
            tvLogisticsMsg.setText(listBean.getStatus());
        }
    }
}
