package it.swiftelink.com.vcs_member.ui.activity.user;

import android.app.Activity;
import android.content.Intent;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.OnClick;
import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.common.widget.recycler.BaseRecyclerAdapter;
import it.swiftelink.com.common.widget.stateview.StateView;
import it.swiftelink.com.factory.model.account.AddressListResModel;
import it.swiftelink.com.factory.presenter.account.AddressListContract;
import it.swiftelink.com.factory.presenter.account.AddressListPresenter;
import it.swiftelink.com.vcs_member.App;
import it.swiftelink.com.vcs_member.R;
import it.swiftelink.com.vcs_member.base.BasePresenterActivity;

public class MyAddressActivity extends BasePresenterActivity<AddressListContract.Presenter> implements AddressListContract.View {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rcv_address_list)
    RecyclerView rcvAddressList;
    @BindView(R.id.stateView)
    StateView stateView;

    private BaseRecyclerAdapter<AddressListResModel.DataBean.DataListBean> mAdapter;
    private int requestCode = -1;

    public static void show(Activity activity, int requestCode) {
        Intent intent = new Intent(activity, MyAddressActivity.class);

        if (requestCode != -1) {
            intent.putExtra("requestCode", requestCode);
            activity.startActivityForResult(intent, requestCode);
        } else {
            activity.startActivity(intent);
        }

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_my_address;
    }

    @Override
    protected void initWidget() {
        super.initWidget();


        tvTitle.setText(getString(R.string.label_shipping_address));

        rcvAddressList.setLayoutManager(new LinearLayoutManager(this));

        rcvAddressList.setAdapter(mAdapter = new BaseRecyclerAdapter<AddressListResModel.DataBean.DataListBean>() {
            @Override
            public int getViewType(int pos) {
                return R.layout.item_rcv_address;
            }

            @Override
            protected ViewHolder<AddressListResModel.DataBean.DataListBean> createViewHolder(View root, int viewType) {
                return new AddressViewHolder(root);
            }

            @Override
            public void onUpdate(AddressListResModel.DataBean.DataListBean dataListBean, ViewHolder<AddressListResModel.DataBean.DataListBean> viewHolder) {
                mPresenter.setAddressDefaultSuccess(dataListBean.getId());
            }
        });

    }

    @Override
    protected void initData() {
        super.initData();
        Intent intent = getIntent();

        if (intent != null) {
            requestCode = intent.getIntExtra("requestCode", -1);
        }
        mPresenter.getAddressList(1, 10);
    }

    @Override
    protected AddressListContract.Presenter initPresenter() {
        return new AddressListPresenter(this);
    }

    @Override
    protected StateView setStateView() {
        return stateView;
    }


    @OnClick({R.id.btn_back, R.id.btn_add_address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.btn_add_address:

                CompileAddressActivity.show(this, "");

                break;
        }
    }

    @Override
    public void getAddressListSuccess(AddressListResModel model) {


        if (model.getData()!=null&&model.getData().getDataList()!=null&&model.getData().getDataList().size() > 0) {
            mAdapter.replice(model.getData().getDataList());
            showContent();
        } else {
            showEmpty();
        }
    }

    @Override
    public void setAddressDefaultSuccess() {
        showContent();
        App.showToast(R.string.label_set_defult_address_success);
        mPresenter.getAddressList(1, 10);

    }

    @Override
    public void retry(int type) {

    }

    @Override
    public void showError(int type,int errorCode , String errorMsg) {

        showRetry(type);
        App.showToast(errorMsg);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Common.RequstCode.COMPEILE_ADDRESS && RESULT_OK == resultCode) {
            mPresenter.getAddressList(1, 10);
        }
    }

    class AddressViewHolder extends BaseRecyclerAdapter.ViewHolder<AddressListResModel.DataBean.DataListBean> {

        @BindView(R.id.tv_user_name)
        TextView tvUserName;
        @BindView(R.id.tv_user_phone)
        TextView tvUserPhone;
        @BindView(R.id.tv_user_address)
        TextView tvUserAddress;
        @BindView(R.id.iv_set_default_address)
        ImageView ivSetDefaultAddress;

        public AddressViewHolder(View itemView) {
            super(itemView);

//            LayoutInflater.from(MyAddressActivity.this).inflate(R.layout.item_rcv_address, null);
        }

        @Override
        protected void onBind(AddressListResModel.DataBean.DataListBean addressResModel) {

            tvUserName.setText(addressResModel.getName());
            tvUserPhone.setText(addressResModel.getMobile());
            tvUserAddress.setText(addressResModel.getAddressDetail());
            ivSetDefaultAddress.setSelected("1".equals(addressResModel.getIsDefault() + ""));
        }

        @OnClick({R.id.ll_set_default_address, R.id.tv_address_edit, R.id.ll_item_address})
        public void onViewClicked(View view) {
            switch (view.getId()) {
                case R.id.ll_set_default_address:
                    update(mData);
                    break;
                case R.id.tv_address_edit:
                    CompileAddressActivity.show(MyAddressActivity.this, mData.getId());
                    break;
                case R.id.ll_item_address:
                    if (requestCode != -1) {
                        Intent intent = new Intent();
                        intent.putExtra("selectAddress", mData);
                        setResult(Activity.RESULT_OK, intent);
                        finish();
                    }
                    break;
            }
        }
    }
}
