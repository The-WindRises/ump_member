package it.swiftelink.com.vcs_member.ui.fragment.main;


import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.header.ClassicsHeader;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import butterknife.BindView;
import butterknife.OnClick;
import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.common.utils.GlideLoadUtils;
import it.swiftelink.com.common.widget.recycler.BaseRecyclerAdapter;
import it.swiftelink.com.common.widget.stateview.StateView;
import it.swiftelink.com.common.widget.view.MyMarqueeView;
import it.swiftelink.com.factory.model.card.InquiryValidCardResModel;
import it.swiftelink.com.factory.model.message.MessageRemindCountResModel;
import it.swiftelink.com.factory.model.mian.BinnerListResModel;
import it.swiftelink.com.factory.model.mian.MarkWordsResModel;
import it.swiftelink.com.factory.model.mian.SetMealListResModel;
import it.swiftelink.com.factory.model.order.ReceiveCardResModel;
import it.swiftelink.com.factory.model.user.UserInfoResModel;
import it.swiftelink.com.factory.presenter.main.HomeContract;
import it.swiftelink.com.factory.presenter.main.HomePresenter;
import it.swiftelink.com.vcs_member.App;
import it.swiftelink.com.vcs_member.R;
import it.swiftelink.com.vcs_member.base.BasePresenterFragment;
import it.swiftelink.com.vcs_member.holders.PackageItemViewHolder;
import it.swiftelink.com.vcs_member.ui.activity.WebViewActivity;
import it.swiftelink.com.vcs_member.ui.activity.account.LoginActivity;
import it.swiftelink.com.vcs_member.ui.activity.appointment.ClinicAppointmentActivity;
import it.swiftelink.com.vcs_member.ui.activity.appointment.ForeseeInquiryDetailActivity;
import it.swiftelink.com.vcs_member.ui.activity.inquiry.EditInquiryActivity;
import it.swiftelink.com.vcs_member.ui.activity.my.MessageActivity;
import it.swiftelink.com.vcs_member.ui.activity.order.PackageCardInfoActivity;
import it.swiftelink.com.vcs_member.utils.Banner;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BasePresenterFragment<HomeContract.Presenter> implements HomeContract.View {


    @BindView(R.id.iv_msg)
    ImageView ivMsg;
    @BindView(R.id.xbanner)
    XBanner xbanner;
    @BindView(R.id.rvc_home)
    RecyclerView rvcHome;
    @BindView(R.id.stateView)
    StateView stateView;
    @BindView(R.id.nsc_home)
    NestedScrollView nscHome;
    @BindView(R.id.marqueeView2)
    TextView marqueeView2;
    @BindView(R.id.marqueeView1)
    MyMarqueeView marqueeView1;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout mRefreshLayout;


    private BaseRecyclerAdapter<SetMealListResModel.DataBean.PackageItemInfoBean> mAdapter;
    private List<SetMealListResModel.DataBean.PackageItemInfoBean> packageItemInfoBeans;
    private List<Banner> list;
    private int pageSize = 10;
    private boolean isLoadMore;
    private int currPage = 1;
    private String languageType = null;

    public HomeFragment() {

    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected HomeContract.Presenter initPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected void initWidget(View view) {
        super.initWidget(view);

        languageType = App.getSPUtils().getString(Common.SPApi.SELECT_LANGUAGE, Common.CommonStr.LANGUAGE1);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        packageItemInfoBeans = new ArrayList<>();
        initRefreshLayout();
        rvcHome.setLayoutManager(layoutManager);
        rvcHome.setAdapter(mAdapter = new BaseRecyclerAdapter<SetMealListResModel.DataBean.PackageItemInfoBean>() {
            @Override
            public int getViewType(int pos) {
                return R.layout.item_packge_layout;

            }

            @Override
            protected ViewHolder<SetMealListResModel.DataBean.PackageItemInfoBean> createViewHolder(View root, int viewType) {

                PackageItemViewHolder itemViewHolder = new PackageItemViewHolder(root, getContext(), languageType);
                itemViewHolder.setGetPackage(new PackageItemViewHolder.GetPackage() {
                    @Override
                    public void onClickGetCard(String id) {
                        mPresenter.postFreePick(id);
                    }
                });
                return itemViewHolder;

            }

            @Override
            public void onUpdate(SetMealListResModel.DataBean.PackageItemInfoBean dataListBean, ViewHolder<SetMealListResModel.DataBean.PackageItemInfoBean> viewHolder) {

            }
        });

        xbanner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                Banner bannerBen = (Banner) model;
                Glide.with(getActivity()).load(bannerBen.getImageUrl()).into((ImageView) view);
            }
        });
        xbanner.setOnItemClickListener(new XBanner.OnItemClickListener() {
            @Override
            public void onItemClick(XBanner banner, Object model, View view, int position) {
                Banner bannerBen = (Banner) model;
                if ("detail".equals(bannerBen.getBannerType())) {
                    //跳转问诊详情
                    ForeseeInquiryDetailActivity.show(getActivity());
                } else if ("linkurl".equals(bannerBen.getBannerType())) {
                    //跳转H5页面
                    WebViewActivity.show(getActivity(), bannerBen.getLinkAddr(), bannerBen.getTitle());
                } else if ("package".equals(bannerBen.getBannerType())) {
                    //跳转套餐卡详情
                    if (!TextUtils.isEmpty(bannerBen.getPackageId())) {
                        startActivity(new Intent(getActivity(), PackageCardInfoActivity.class).putExtra(Common.CommonStr.PACKAGE_CARD_ID, bannerBen.getPackageId()));
                    }
                }

            }
        });

    }

    //下拉刷新 初始化
    private void initRefreshLayout() {
        mRefreshLayout.setRefreshHeader(new ClassicsHeader(Objects.requireNonNull(getActivity())));
        mRefreshLayout.setRefreshFooter(new ClassicsFooter(getActivity()));
        mRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                if (isLoadMore) {
                    currPage++;
                    mPresenter.getSetMealList(languageType, currPage, pageSize);
                }
                refreshLayout.finishLoadMore(500);
            }
        });
        mRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                currPage = 1;
                mPresenter.getSetMealList(languageType, currPage, pageSize);
                mPresenter.checkVersion("android", "member");
                refreshLayout.finishRefresh(800);

            }
        });

    }

    private List<String> topList;

    private void initMarqueeView1(final List<MarkWordsResModel.DataBean.HeadlinesBean> data) {

        if (data != null) {
            if (topList == null) {
                topList = new ArrayList<>();
            }

            topList.clear();

            for (MarkWordsResModel.DataBean.HeadlinesBean dataBean : data) {
                topList.add(dataBean.getTitle());
            }
            marqueeView1.startWithList(topList);
            marqueeView1.setOnItemClickListener((position, textView) -> intentHeadLineConfig(data.get(position)));
        }
    }


    /**
     * 添加： 设置头条跳转页面配置  根据 headlinesBean.bannerType类型区分
     * data: 2019/12/20
     *
     * @param headlinesBean 头条Info,    bannerType ( detail:问诊详情/H5 ; package:套餐详情 ; linkurl: H5)
     *                      <p>
     *                      修改人: lqi
     */
    private void intentHeadLineConfig(MarkWordsResModel.DataBean.HeadlinesBean headlinesBean) {
        String bannerType = headlinesBean.getBannerType();
        if (bannerType.equals("detail")) {
            //跳转问诊详情，如果LinkAddr的地址不为空，跳转外链H5
            if (TextUtils.isEmpty(headlinesBean.getLinkAddr())) {
                String token = App.getSPUtils().getString(Common.SPApi.TOKEN);
                if (!TextUtils.isEmpty(token)) {
                    ForeseeInquiryDetailActivity.show(getActivity());
                } else {
                    LoginActivity.show(Objects.requireNonNull(getActivity()));
                }


            } else {
                WebViewActivity.show(getActivity(), headlinesBean.getLinkAddr(), headlinesBean.getTitle());
            }
        } else if (bannerType.equals("package")) {
            //跳转套餐卡详情
//            App.showToast("套餐卡尚未开放");
            if(!TextUtils.isEmpty(headlinesBean.getPackageId())) {
                startActivity(new Intent(getActivity(), PackageCardInfoActivity.class).putExtra(Common.CommonStr.PACKAGE_CARD_ID, headlinesBean.getPackageId()));
            }
        } else if (bannerType.equals("linkurl")) {
            //跳转H5
            WebViewActivity.show(getActivity(), headlinesBean.getLinkAddr(), headlinesBean.getTitle());
        }

    }


    private void initMarqueeView2(String data) {

//        if (data != null) {
//            marqueeView2.startWithList(data);
//
//            marqueeView2.setOnItemClickListener(new MarqueeView.OnItemClickListener() {
//                @Override
//                public void onItemClick(int position, TextView textView) {
//                    CharSequence text = (CharSequence) marqueeView2.getMessages().get(position);
//
//                }
//            });
//        }
        marqueeView2.setSelected(true);
        marqueeView2.setText(data);

    }
    private void loadData() {
        mPresenter.getBinderList(App.getSPUtils().getString(Common.SPApi.SELECT_LANGUAGE, Common.CommonStr.LANGUAGE1), Common.CommonStr.TYPE1);
        mPresenter.getMarkedWords();

        String token = App.getSPUtils().getString(Common.SPApi.TOKEN);
        if (!TextUtils.isEmpty(token)) {
            mPresenter.getUserInfo();
            mPresenter.getMessageRemind();
        }

        loadPackageData(currPage);
    }

    @Override
    public void onResume() {
        super.onResume();
        currPage = 1;
        loadData();
    }

    private void loadPackageData(int currentPage) {
        mPresenter.getSetMealList(App.getSPUtils().getString(Common.SPApi.SELECT_LANGUAGE, Common.CommonStr.LANGUAGE1), currentPage, pageSize);
    }

    @Override
    protected StateView getStateView() {
        return stateView;
    }


    @OnClick({R.id.iv_msg, R.id.ll_video_chat, R.id.ll_outpatient_appointment})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_msg:
                MessageActivity.show(getActivity());
                break;
            case R.id.ll_video_chat:

//                String token = App.getSPUtils().getString(Common.SPApi.TOKEN);
//                if (TextUtils.isEmpty(token)) {
                    ForeseeInquiryDetailActivity.show(getActivity());
//                } else {
//                    //验证用户是否有套餐卡
//                    mPresenter.getInquiryValidPackageCard();
//                }


                break;
            case R.id.ll_outpatient_appointment:
                ClinicAppointmentActivity.show(getActivity());
                break;
        }
    }

    @Override
    public void getBinderListSuccess(BinnerListResModel model) {
        final List<BinnerListResModel.DataBean> data = model.getData();
        showContent();
        if (data.size() > 0) {
            list = new ArrayList<>();
            for (BinnerListResModel.DataBean dataBean : data) {
                Banner banner = new Banner();
                banner.setImageUrl(dataBean.getImage());
                banner.setLinkAddr(dataBean.getLinkAddr());
                banner.setTitle(dataBean.getTitle());
                banner.setBannerType(dataBean.getBannerType());
                banner.setPackageId(dataBean.getPackageId());
                list.add(banner);
            }
            xbanner.setBannerData(list);
        }
    }

    @Override
    public void getSetMealListSuccess(SetMealListResModel model) {
        if (null != model.getData() && model.isSuccess()) {
            int totalPage = model.getData().getPages();
            if (totalPage > currPage) {
                isLoadMore = true;
                mRefreshLayout.setEnableLoadMore(true);
                mRefreshLayout.setNoMoreData(false);
            } else {
                isLoadMore = false;
                mRefreshLayout.setNoMoreData(true);
            }
            if (currPage == 1) {
                packageItemInfoBeans.clear();
            }
            if (null != model.getData().getList() && model.getData().getList().size() > 0) {
                packageItemInfoBeans.addAll(model.getData().getList());
                if (null != packageItemInfoBeans && packageItemInfoBeans.size() > 0) {
                    showContent();
                    mAdapter.replice(packageItemInfoBeans);
                }
            }
        }

    }

    @Override
    public void getMessageRemindSuccess(MessageRemindCountResModel model) {
        showContent();
        if (model.getData() > 0) {
            GlideLoadUtils.getInstance().glideLoad(getActivity(), R.mipmap.icon_msg_has_read, ivMsg, R.mipmap.icon_msg_no_read);
        } else {
            GlideLoadUtils.getInstance().glideLoad(getActivity(), R.mipmap.icon_msg_no_read, ivMsg, R.mipmap.icon_msg_no_read);
        }
    }

    @Override
    public void getMarkedWordsSuccess(MarkWordsResModel resModel) {
        showContent();
        MarkWordsResModel.DataBean resModelData = resModel.getData();
        if (resModelData != null) {
            if (resModelData.getHints().size() > 0) {
                List<String> hints = resModelData.getHints();
                initMarqueeView2(hints.get(0));
            }


            if (resModelData.getHeadlines().size() > 0) {
                List<MarkWordsResModel.DataBean.HeadlinesBean> headlines = resModelData.getHeadlines();

                initMarqueeView1(headlines);
            }
        }

    }

    @Override
    public void postFreePickSuccess(ReceiveCardResModel resModel) {
        //免费领取成功
        if (resModel.isSuccess()) {
            Toast toast = Toast.makeText(getActivity(), getResources().getText(R.string.get_success), Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        } else {
            Toast toast = Toast.makeText(getActivity(), resModel.getMessage(), Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }

    @Override
    public void getInquiryValidPackageCardSuccess(InquiryValidCardResModel resModel) {
        showContent();
        if (null == resModel.getData()) {
            return;
        }
        if (resModel.getData().isSuccessX()) {
            //当前可问诊
            EditInquiryActivity.show(getActivity(), Common.CommonStr.ENTER_FROM_MAIN);
        } else {
            //当前不可问诊
            int type = resModel.getData().getType();
            //1:当前用户没有套餐卡
            //2：当前时间不符合套餐卡使用条件
            if (type == 1) {
                ForeseeInquiryDetailActivity.show(getActivity());
            } else if (type == 2) {
                Toast.makeText(getActivity(), "当前时间不符合套餐卡使用条件", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void getUserInfoSuccess(UserInfoResModel model) {
        showContent();
        UserInfoResModel.DataBean data = model.getData();

        if (data != null) {
            App.getSPUtils().put(Common.SPApi.HONG_KONG, data.getTerritory());
        }
    }


    @Override
    public void retry(int type) {

    }

    @Override
    public void showError(int type, int errorCode, String errorMsg) {
        showContent();

    }


}
