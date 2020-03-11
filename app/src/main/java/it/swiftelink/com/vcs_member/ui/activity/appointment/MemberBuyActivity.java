package it.swiftelink.com.vcs_member.ui.activity.appointment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.DecimalFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.common.event.MsgEvent;
import it.swiftelink.com.common.utils.GlideLoadUtils;
import it.swiftelink.com.common.widget.recycler.BaseRecyclerAdapter;
import it.swiftelink.com.common.widget.stateview.StateView;
import it.swiftelink.com.factory.model.appointment.ForeseeInquiryImageResModel;
import it.swiftelink.com.factory.model.mian.SetMealListResModel;
import it.swiftelink.com.factory.presenter.setmeal.SetMealBuyContract;
import it.swiftelink.com.factory.presenter.setmeal.SetMealBuyPresenter;
import it.swiftelink.com.vcs_member.App;
import it.swiftelink.com.vcs_member.R;
import it.swiftelink.com.vcs_member.base.BasePresenterActivity;

public class MemberBuyActivity extends BasePresenterActivity<SetMealBuyContract.Presenter> implements SetMealBuyContract.View {


    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rvc_member_buy)
    RecyclerView rvcMemberBuy;
    @BindView(R.id.iv_member_buy_agree)
    ImageView ivMemberBuyAgree;
    @BindView(R.id.tv_price_all)
    TextView tvPriceAll;
    @BindView(R.id.stateView)
    StateView stateView;
    @BindView(R.id.iv_member_buy_top)
    ImageView ivMemberBuyTop;
    private String language;

    private BaseRecyclerAdapter<SetMealListResModel.DataBean.PackageItemInfoBean> mAdapter;

    private SetMealListResModel.DataBean.PackageItemInfoBean selectDataListBean;
    private String buyNoticeImg;

    public static void show(Activity activity) {
        activity.startActivity(new Intent(activity, MemberBuyActivity.class));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
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
        return R.layout.activity_member_buy;
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        language=App.getSPUtils().getString(Common.SPApi.SELECT_LANGUAGE, Common.CommonStr.LANGUAGE1);
        tvTitle.setText(getString(R.string.title_video_inquiry));

        rvcMemberBuy.setLayoutManager(new LinearLayoutManager(this));

        rvcMemberBuy.setAdapter(mAdapter = new BaseRecyclerAdapter<SetMealListResModel.DataBean.PackageItemInfoBean>() {
            @Override
            public int getViewType(int pos) {
                return R.layout.item_member_buy;
            }

            @Override
            protected ViewHolder<SetMealListResModel.DataBean.PackageItemInfoBean> createViewHolder(View root, int viewType) {
                return new MyViewHolder(root);
            }

            @Override
            public void onUpdate(SetMealListResModel.DataBean.PackageItemInfoBean dataListBean, ViewHolder<SetMealListResModel.DataBean.PackageItemInfoBean> viewHolder) {

                selectDataListBean = dataListBean;
            }
        });
    }

    @Override
    protected void initData() {
        super.initData();
        mPresenter.getSetMealList(App.getSPUtils().getString(Common.SPApi.SELECT_LANGUAGE, Common.CommonStr.LANGUAGE1), 1, 10);
        mPresenter.getForeseeInquiryImage(App.getSPUtils().getString(Common.SPApi.SELECT_LANGUAGE, Common.CommonStr.LANGUAGE1));
    }

    @Override
    protected SetMealBuyContract.Presenter initPresenter() {
        return new SetMealBuyPresenter(this);
    }


    @Override
    protected StateView setStateView() {
        return stateView;
    }

    @OnClick({R.id.btn_back, R.id.ll_agree_member_buy, R.id.tv_inquiry_submit, R.id.tv_agree_member_buy})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;
            case R.id.ll_agree_member_buy:
                ivMemberBuyAgree.setSelected(!ivMemberBuyAgree.isSelected());
                break;

            case R.id.tv_inquiry_submit:
                next();
                break;

            case R.id.tv_agree_member_buy:
                MemberBuyAgreementActivity.show(this, buyNoticeImg);
                break;
        }
    }

    private void next() {

        if (!ivMemberBuyAgree.isSelected()) {
            App.showToast(R.string.label_notice_and_agree);
            return;
        }
        if (selectDataListBean != null) {
            PackageBuyConfirmActivity.show(this, selectDataListBean, null);
        } else {
            App.showToast(R.string.please_select_package_type);
        }


    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MsgEvent msgEvent) {
        switch (msgEvent.getType()) {
            case Common.EventbusType.PAY_FINISH:
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void retry(int type) {

    }

    @Override
    public void showError(int type, int errorCode ,String errorMsg) {
        super.showError(type, errorCode ,errorMsg);
        showContent();

    }

    @Override
    public void getSetMealListSuccess(SetMealListResModel model) {
        if (model.getData().getList() != null && model.getData().getList().size() > 0) {
            showContent();
            DecimalFormat decimalFormat = new DecimalFormat("0.00");
            mAdapter.replice(model.getData().getList());
            selectDataListBean = model.getData().getList().get(0);
            tvPriceAll.setText("¥" + decimalFormat.format(model.getData().getList().get(0).getDiscountPrice()));
        } else {
            showEmpty();
        }
    }

    @Override
    public void getForeseeInquiryImageSuccess(ForeseeInquiryImageResModel resModel) {

        showContent();
        if (resModel.getData() != null) {
            GlideLoadUtils.getInstance().glideLoadCenterInside(this, resModel.getData().getPackageCardImg(), ivMemberBuyTop, R.mipmap.icon_image_defout);
            buyNoticeImg = resModel.getData().getBuyNoticeImg();
        }

    }


    class MyViewHolder extends BaseRecyclerAdapter.ViewHolder<SetMealListResModel.DataBean.PackageItemInfoBean> {

        @BindView(R.id.iv_member_buy_item)
        ImageView ivMemberBuyItem;
        @BindView(R.id.iv_member_buy_name)
        TextView ivMemberBuyName;
        @BindView(R.id.tv_member_buy_frequency)
        TextView tvMemberBuyFrequency;
        @BindView(R.id.tv_member_buy_price)
        TextView tvMemberBuyPrice;
        @BindView(R.id.ll_package_buy_selected)
        LinearLayout llPackageBuySelected;
        DecimalFormat decimalFormat;

        public MyViewHolder(View itemView) {
            super(itemView);

            LayoutInflater.from(getApplication()).inflate(R.layout.item_member_buy, null);
        }

        @Override
        protected void onBind(SetMealListResModel.DataBean.PackageItemInfoBean dataListBean) {
            decimalFormat = new DecimalFormat("0.00");
            if (mAdapter.singleSelectedId == mPosition) {
                ivMemberBuyItem.setSelected(true);
                llPackageBuySelected.setSelected(true);
            } else {
                ivMemberBuyItem.setSelected(false);
                llPackageBuySelected.setSelected(false);
            }

            SetMealListResModel.DataBean.PackageItemInfoBean.PackageInfosBean  selectPackageInfo = null;
            List<SetMealListResModel.DataBean.PackageItemInfoBean.PackageInfosBean> packageInfosBeans=dataListBean.getPackageInfos();
            for(SetMealListResModel.DataBean.PackageItemInfoBean.PackageInfosBean packageInfosBean:packageInfosBeans){
                if(language.equals(packageInfosBean.getLanguage())){
                    selectPackageInfo=packageInfosBean;
                }
            }
            ivMemberBuyName.setText(selectPackageInfo.getName());
            tvMemberBuyPrice.setText("¥" + decimalFormat.format(dataListBean.getDiscountPrice()));
        }

        @OnClick(R.id.ll_package_buy_selected)
        public void onViewClicked() {
            mAdapter.singleChoose(mPosition);
            tvPriceAll.setText("¥" + decimalFormat.format(mData.getDiscountPrice()));
            selectDataListBean = mData;
        }
    }
}
