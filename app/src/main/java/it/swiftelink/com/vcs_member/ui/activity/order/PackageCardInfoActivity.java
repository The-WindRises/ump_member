package it.swiftelink.com.vcs_member.ui.activity.order;

import android.annotation.SuppressLint;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;

import androidx.annotation.NonNull;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.common.widget.dialog.CustomDialog;
import it.swiftelink.com.common.widget.nicedialog.BaseNiceDialog;
import it.swiftelink.com.common.widget.stateview.StateView;
import it.swiftelink.com.factory.model.order.PackageInfoResModel;

import it.swiftelink.com.factory.model.order.ReceiveCardResModel;
import it.swiftelink.com.factory.presenter.order.PackageInfoContract;
import it.swiftelink.com.factory.presenter.order.PackageInfoPresenter;
import it.swiftelink.com.vcs_member.App;
import it.swiftelink.com.vcs_member.R;
import it.swiftelink.com.vcs_member.base.BasePresenterActivity;
import it.swiftelink.com.vcs_member.ui.activity.appointment.PackageBuyConfirmActivity;
import it.swiftelink.com.vcs_member.utils.WebUtils;
import it.swiftelink.com.vcs_member.wxapi.WechatUtils;

/**
 * @Description: 套餐卡详情
 * @Author: klk
 * @CreateDate: 2019/12/25 12:04
 */
public class PackageCardInfoActivity extends BasePresenterActivity<PackageInfoContract.Presenter> implements PackageInfoContract.View {


    @BindView(R.id.package_item_name_tv)
    TextView packageItemNameTv;

    @BindView(R.id.package_type_tv)
    TextView packageTypeTv;

    @BindView(R.id.package_card_detail_ll)
    LinearLayout packageDetailLayout;

    @BindView(R.id.package_root_ll)
    LinearLayout packageCardRootLayout;

    @BindView(R.id.package_detail_date_tv)
    TextView packageDateTv;

    @BindView(R.id.price_tag_tv)
    TextView packagePriceTagTv;

    @BindView(R.id.price_value_tv)
    TextView packagePriceValueTv;

    @BindView(R.id.original_price_tv)
    TextView originalPriceTv;

    @BindView(R.id.package_date_tag_iv)
    ImageView packageDateTagIv;

    @BindView(R.id.package_date_value_tv)
    TextView packageDateValueTv;

    @BindView(R.id.package_item_tip_tv)
    TextView packageItemTipTv;

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.package_info_rl)
    RecyclerView mRecyclerView;

    @BindView(R.id.tab_layout)
    TabLayout mTabLayout;

    @OnClick({R.id.btn_back, R.id.btn_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_back:
                finish();
                break;

            case R.id.btn_right:
                selectPhoneType();
                break;
        }
    }



    @BindView(R.id.stateView)
    StateView stateView;

    @BindView(R.id.package_submit_btn)
    TextView packageBtn;

    private TabRecyclerAdapter mAdapter;
    private LinearLayoutManager mManager;
    private List<String> packageDecList = new ArrayList<>();
    private int rootWidth = 0;

    //套餐卡类型
    private int type;
    //语言类型
    private String language;
    Bitmap imageBitmap = null;
    private String packageName;
    private String packageTip;
    private String link;

    protected void loadData() {
        language = App.getSPUtils().getString(Common.SPApi.SELECT_LANGUAGE, Common.CommonStr.LANGUAGE1);
        //套餐卡ID
        String packageCardId = getIntent().getStringExtra(Common.CommonStr.PACKAGE_CARD_ID);
        if (null != mPresenter) {

            mPresenter.getPackageCarInfo(packageCardId, language);
        }
    }

    private void selectPhoneType() {
        CustomDialog.
                newInstance(R.layout.dialog_select_share_type)
                .setViewCreateListner(new CustomDialog.ViewCreateListener() {

                    @Override
                    public void onViewCreate(ViewGroup viewGroup, final BaseNiceDialog dialog) {

                        final LinearLayout ll_share_type1 = viewGroup.findViewById(R.id.ll_share_type1);
                        final LinearLayout ll_share_type2 = viewGroup.findViewById(R.id.ll_share_type2);

                        ll_share_type1.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Resources res = getResources();
                                Bitmap bmp = BitmapFactory.decodeResource(res, R.mipmap.ic_launcher_round);
                                WechatUtils.getInstance(PackageCardInfoActivity.this).sendPageToWeiXin(link,bmp,packageName,packageTip,0);
                                dialog.dismiss();

                            }
                        });

                        ll_share_type2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Resources res = getResources();
                                Bitmap bmp = BitmapFactory.decodeResource(res, R.mipmap.ic_launcher_round);
                                WechatUtils.getInstance(PackageCardInfoActivity.this).sendPageToWeiXin(link,bmp,packageName,packageTip,1);
                                dialog.dismiss();
                            }
                        });

                    }
                })
                .setOutCancel(true)
                .setShowBottom(true)
                .show(getSupportFragmentManager());
    }

    @Override
    protected PackageInfoContract.Presenter initPresenter() {
        return new PackageInfoPresenter(PackageCardInfoActivity.this);
    }

    @Override
    public void getPackageCarInfoSuccess(PackageInfoResModel packageInfoResModel) {
        showContent();
        if (null != packageInfoResModel.getData()) {
            type = packageInfoResModel.getData().getType();
            double price=packageInfoResModel.getData().getDiscountPrice();
            if(price==0){
                packageBtn.setText(getResources().getText(R.string.experience_submit_str));
            }else {
                packageBtn.setText(getResources().getText(R.string.now_purchase));
            }

            String Territory = App.getSPUtils().getString(Common.SPApi.HONG_KONG);

            packageBtn.setOnClickListener(view -> {
                if(null != Territory && Territory.equals("香港")){
                    App.showToast(R.string.label_hong_kong_not_purchase);
                }else {
                    if(price==0){
                        mPresenter.postFreePick(packageInfoResModel.getData().getId());
                    }else {
                        PackageBuyConfirmActivity.showByCardDetail(this,packageInfoResModel.getData(),null);
                    }
                }
            });


            if (null != packageInfoResModel.getData().getPackageInfos() && packageInfoResModel.getData().getPackageInfos().size() > 0) {
                List<PackageInfoResModel.DataBean.PackageInfosBean> packageBeanList = packageInfoResModel.getData().getPackageInfos();
                PackageInfoResModel.DataBean.PackageInfosBean currentPackageBean = null;
                for (PackageInfoResModel.DataBean.PackageInfosBean packageBean : packageBeanList) {
                    if (language.equals(packageBean.getLanguage())) {
                        currentPackageBean = packageBean;
                    }
                }
                packageDecList.clear();
                if (null != currentPackageBean) {

                    packageDecList.add(currentPackageBean.getEquityExplain());
                    packageDecList.add(currentPackageBean.getBuyNotice());
                    packageDecList.add(currentPackageBean.getCommonProblem());

                    mAdapter.notifyDataSetChanged();
                }
            }
            initPackageCardHeadView(packageInfoResModel.getData());
        }


    }

    @Override
    public void postFreePickSuccess(ReceiveCardResModel resModel) {
        //免费领取成功
        if(resModel.isSuccess()){
            Toast toast=Toast.makeText(this,getResources().getString(R.string.get_success),Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER,0,0);
            toast.show();
            this.finish();
        }else {
            Toast toast = Toast.makeText(this, resModel.getMessage(), Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }

    //初始化套餐卡详情头布局
    @SuppressLint("DefaultLocale")
    private void initPackageCardHeadView(PackageInfoResModel.DataBean packageDetailBean) {
        switch (type) {
            case Common.PackageType.CARD_NORMAL:  //套餐卡
                packageCardRootLayout.setBackground(getResources().getDrawable(R.mipmap.ic_package_detail_bg2));
                packageTypeTv.setBackground(getResources().getDrawable(R.mipmap.ic_package_detail_type1));
                packageDateTv.setBackground(getResources().getDrawable(R.drawable.package_item_date_bg2));
                packageDateTv.setTextColor(getResources().getColor(R.color.textColor25));
                packagePriceTagTv.setTextColor(getResources().getColor(R.color.textColor25));
                packagePriceValueTv.setTextColor(getResources().getColor(R.color.textColor25));
                originalPriceTv.setTextColor(getResources().getColor(R.color.textColor27));
                packageDateTagIv.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_package_date2));
                packageDateValueTv.setTextColor(getResources().getColor(R.color.textColor25));
                packageItemNameTv.setTextColor(getResources().getColor(R.color.textColor25));
                packageItemTipTv.setTextColor(getResources().getColor(R.color.textColor29));
                int validateDays = packageDetailBean.getValidateDays();
                //套餐卡使用时间
                packageDateTv.setText(String.format("%1d%2s", validateDays, "天"));
                //套餐卡名字
                packageTypeTv.setText(getString(R.string.card_package_str));
                //套餐卡剩余时间
                packageDateValueTv.setText(setRemainTime(packageDetailBean.getActivityDueDate()));
//                //套餐卡使用时间提示
//                packageItemTipTv.setText(String.format("%1d%2s", validateDays, getString(R.string.package_tip_str)));
                break;
            case Common.PackageType.CARD_EXPERIENCE: //体验卡
                packageCardRootLayout.setBackground(getResources().getDrawable(R.mipmap.ic_package_detail_bg1));
                packageTypeTv.setBackground(getResources().getDrawable(R.mipmap.ic_package_detail_type2));
                packageDateTv.setBackground(getResources().getDrawable(R.drawable.package_item_date_bg1));
                packageDateTv.setTextColor(getResources().getColor(R.color.textColor21));
                packagePriceTagTv.setTextColor(getResources().getColor(R.color.textColor21));
                packagePriceValueTv.setTextColor(getResources().getColor(R.color.textColor21));
                originalPriceTv.setTextColor(getResources().getColor(R.color.textColor22));
                packageDateTagIv.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_package_date1));
                packageDateValueTv.setTextColor(getResources().getColor(R.color.textColor21));
                packageItemNameTv.setTextColor(getResources().getColor(R.color.textColor23));
                packageItemTipTv.setTextColor(getResources().getColor(R.color.textColor23));
                int inquiryDurationTime = packageDetailBean.getInquiryDuration();
                //体验卡使用时间
                packageDateTv.setText(String.format("%1d%2s", inquiryDurationTime, "分钟"));
                //体验卡名字
                packageTypeTv.setText(getString(R.string.card_expire_str));

//                //体验卡使用时间提示
//                if (inquiryDurationTime >= 60) {
//                    packageItemTipTv.setText(String.format("%1d%2s", inquiryDurationTime / 60, getString(R.string.experience_tip_str)));
//                } else {
//                    packageItemTipTv.setText(String.format("%1d%2s", inquiryDurationTime, getString(R.string.experience_tip_time_str)));
//                }

                break;
            case Common.PackageType.CARD_SECONDARY://次卡
                packageCardRootLayout.setBackground(getResources().getDrawable(R.mipmap.ic_package_detail_bg3));
                packageTypeTv.setBackground(getResources().getDrawable(R.mipmap.ic_package_detail_type3));
                packageDateTv.setBackground(getResources().getDrawable(R.drawable.package_item_date_bg3));
                packageDateTv.setTextColor(getResources().getColor(R.color.textColor26));
                packagePriceTagTv.setTextColor(getResources().getColor(R.color.textColor26));
                packagePriceValueTv.setTextColor(getResources().getColor(R.color.textColor26));
                originalPriceTv.setTextColor(getResources().getColor(R.color.textColor28));
                packageDateTagIv.setImageBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_package_date3));
                packageDateValueTv.setTextColor(getResources().getColor(R.color.textColor26));
                packageItemNameTv.setTextColor(getResources().getColor(R.color.textColor26));
                packageItemTipTv.setTextColor(getResources().getColor(R.color.textColor30));
                int inquiryNum = packageDetailBean.getInquiryNum();
                //次卡使用时间
                packageDateTv.setText(String.format("%1d%2s", inquiryNum, "次"));
                //次卡名字
                packageTypeTv.setText(getString(R.string.card_expire_str));
                //次卡剩余时间
                packageDateValueTv.setText(setRemainTime(packageDetailBean.getActivityDueDate()));
//                //次卡使用时间提示
//                if (inquiryNum > 0) {
//                    packageItemTipTv.setText(String.format("%1d%2s", inquiryNum, getString(R.string.second_tip_str)));
//                }
                break;


        }

        packageDateValueTv.setText(setRemainTime(packageDetailBean.getActivityDueDate()));
        int maxWidth = rootWidth - packageDateTv.getMeasuredWidth();
        packageItemNameTv.setMaxWidth(maxWidth);
        setPackageName(packageDetailBean);
        packagePriceValueTv.setText(String.format("%.2f", packageDetailBean.getDiscountPrice()));
        originalPriceTv.setText(String.format("%s%.2f", getString(R.string.money_tag_str), packageDetailBean.getPrice()));
        originalPriceTv.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG); //中划线
        link=packageDetailBean.getLink();




    }

    //设置套餐卡剩余时间
    @SuppressLint("SetTextI18n")
    private String setRemainTime(long expireDate) {

        if (expireDate != 0) {
            long timeInMillis = Calendar.getInstance().getTimeInMillis();
            long time = expireDate - timeInMillis;
            long betweenDays = (time) / (1000 * 3600 * 24);
            long hourTime = time - betweenDays * (1000 * 3600 * 24);
            long hour = hourTime / (1000 * 3600);

            long minTime = hourTime - hour * (1000 * 3600);
            long min = minTime / (1000 * 60);
            return getString(R.string.label_remaining) + betweenDays + getString(R.string.label_day) + hour + getString(R.string.label_hour) + min + getString(R.string.label_min);
        }
        return null;
    }

    //设置套餐卡名字
    private void setPackageName(PackageInfoResModel.DataBean packageDetailBean) {
        List<PackageInfoResModel.DataBean.PackageInfosBean> packageInfo = packageDetailBean.getPackageInfos();
        for (PackageInfoResModel.DataBean.PackageInfosBean packageInfosBean : packageInfo) {
            if (language.equals(packageInfosBean.getLanguage())) {
                packageName = packageInfosBean.getName();
                packageTip=packageInfosBean.getDescription();
                packageItemNameTv.setText(packageName);
                packageItemTipTv.setText(packageTip);

            }
        }



    }

    @Override
    protected void onResume() {
        super.onResume();
        loadData();
    }

    @Override
    protected void initWidget() {
        super.initWidget();
        tvTitle.setText(getString(R.string.package_title_str));
        initTextView();
        packageDecList = new ArrayList<>();
        mAdapter = new TabRecyclerAdapter();
        mManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mManager);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                mTabLayout.setScrollPosition(mManager.findFirstVisibleItemPosition(), 0, true);
            }
        });
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //点击tab的时候，RecyclerView自动滑到该tab对应的item位置
                mManager.scrollToPositionWithOffset(tab.getPosition(), 0);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private void initTextView() {


        packageDetailLayout.post(new Runnable() {
            @Override
            public void run() {
                rootWidth = packageDetailLayout.getMeasuredWidth();


            }
        });


    }


    @Override
    protected StateView setStateView() {
        return stateView;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_package_card_info;
    }

    @Override
    public void retry(int type) {

    }

    public class TabRecyclerAdapter extends RecyclerView.Adapter {
        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
            final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.package_card_info_item, parent, false);
            return new ItemViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            if (position != packageDecList.size()) {
                ((ItemViewHolder) holder).setData(position);
            }
        }

        @Override
        public int getItemCount() {
            return packageDecList.size();
        }
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView tipTitleTv;
        WebView contentWeb;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            tipTitleTv=itemView.findViewById(R.id.tip_dec_title_tv);
            contentWeb=itemView.findViewById(R.id.content_wb);
            WebUtils.setWebViewProp(contentWeb);
            contentWeb.clearHistory();
            contentWeb.reload();
        }

        public void setData(int position) {
            if(position==0){
                tipTitleTv.setVisibility(View.GONE);
            }
            if(position==1){
                tipTitleTv.setText(getString(R.string.tip_dec_title_str));
                tipTitleTv.setVisibility(View.VISIBLE);
            }
            if(position==2){
                tipTitleTv.setText(getString(R.string.common_problem_str));
                tipTitleTv.setVisibility(View.VISIBLE);
            }
            if (!TextUtils.isEmpty(packageDecList.get(position))&&packageDecList.get(position)!=null) {
                contentWeb.loadDataWithBaseURL(null,packageDecList.get(position), "text/html" , "utf-8", null);
            }else {
                contentWeb.setVisibility(View.INVISIBLE);
            }
        }

    }
}
