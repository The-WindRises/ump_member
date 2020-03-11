package it.swiftelink.com.vcs_member.holders;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Paint;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.common.widget.recycler.BaseRecyclerAdapter;
import it.swiftelink.com.factory.model.mian.SetMealListResModel;
import it.swiftelink.com.vcs_member.App;
import it.swiftelink.com.vcs_member.R;
import it.swiftelink.com.vcs_member.ui.activity.account.LoginActivity;
import it.swiftelink.com.vcs_member.ui.activity.appointment.PackageBuyConfirmActivity;
import it.swiftelink.com.vcs_member.ui.activity.order.PackageCardInfoActivity;

/**
 * @Description:
 * @Author: klk
 * @CreateDate: 2019/12/24 18:14
 */
public class PackageItemViewHolder extends BaseRecyclerAdapter.ViewHolder<SetMealListResModel.DataBean.PackageItemInfoBean> {
    @BindView(R.id.package_item_root_ll)
    LinearLayout packageRootLayout;
    @BindView(R.id.package_type_iv)
    ImageView packageTypeIv;
    @BindView(R.id.package_date_tv)
    TextView packageDateTv;
    @BindView(R.id.price_tag_tv)
    TextView priceTagTv;
    @BindView(R.id.price_value_tv)
    TextView priceValueTv;
    @BindView(R.id.original_price_tv)
    TextView originalPriceTv;
    @BindView(R.id.package_date_tag_iv)
    ImageView packageDateTagIv;
    @BindView(R.id.package_date_value_tv)
    TextView packageDateValueTv;
    @BindView(R.id.package_item_name_tv)
    TextView packageItemNameTv;
    @BindView(R.id.package_item_tip_tv)
    TextView packageItemTipTv;
    @BindView(R.id.package_item_submit)
    TextView packageItemSubmitTv;
    @BindView(R.id.package_type_tv)
    TextView packageTypeTv;
    private Context mContext;
    private String language;
    private Activity activity;


    private GetPackage mGetPackage;

    public PackageItemViewHolder(View itemView, Context context, String language) {
        super(itemView);
        this.mContext = context;
        this.activity = (Activity) context;
        this.language = language;

    }

    @SuppressLint({"DefaultLocale", "SetTextI18n"})
    @Override
    protected void onBind(SetMealListResModel.DataBean.PackageItemInfoBean packageItemInfoBean) {
        //套餐卡类型
        int type = packageItemInfoBean.getType();
        switch (type) {
            case Common.PackageType.CARD_NORMAL:  //套餐卡
                packageRootLayout.setBackground(mContext.getResources().getDrawable(R.mipmap.ic_package_bg2));
                packageTypeIv.setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.ic_package_type2));
                packageDateTv.setBackground(mContext.getResources().getDrawable(R.drawable.package_item_date_bg2));
                packageDateTv.setTextColor(mContext.getResources().getColor(R.color.textColor25));
                priceTagTv.setTextColor(mContext.getResources().getColor(R.color.textColor25));
                priceValueTv.setTextColor(mContext.getResources().getColor(R.color.textColor25));
                originalPriceTv.setTextColor(mContext.getResources().getColor(R.color.textColor27));
                packageDateTagIv.setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.ic_package_date2));
                packageDateValueTv.setTextColor(mContext.getResources().getColor(R.color.textColor25));
                packageItemNameTv.setTextColor(mContext.getResources().getColor(R.color.textColor25));
                packageItemTipTv.setTextColor(mContext.getResources().getColor(R.color.textColor29));
                packageItemSubmitTv.setBackground(mContext.getResources().getDrawable(R.mipmap.ic_package_submit_bg2));
                int validateDays = packageItemInfoBean.getValidateDays();
                //套餐卡使用时间
                packageDateTv.setText(String.format("%1d%2s", validateDays, mContext.getString(R.string.day_dec_str)));
                //套餐卡名字
                packageTypeTv.setText(mContext.getString(R.string.card_package_str));
                //套餐卡剩余时间
                packageDateValueTv.setText(setRemainTime(packageItemInfoBean.getActivityDueDate()));
                if (mData.getDiscountPrice() == 0) {
                    packageItemSubmitTv.setText(mContext.getString(R.string.experience_submit_str));
                } else {
                    packageItemSubmitTv.setText(mContext.getString(R.string.buy_dec_str));
                }


                break;
            case Common.PackageType.CARD_EXPERIENCE: //体验卡
                packageRootLayout.setBackground(mContext.getResources().getDrawable(R.mipmap.ic_package_bg1));
                packageTypeIv.setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.ic_package_type1));
                packageDateTv.setBackground(mContext.getResources().getDrawable(R.drawable.package_item_date_bg1));
                packageDateTv.setTextColor(mContext.getResources().getColor(R.color.textColor21));
                priceTagTv.setTextColor(mContext.getResources().getColor(R.color.textColor21));
                priceValueTv.setTextColor(mContext.getResources().getColor(R.color.textColor21));
                originalPriceTv.setTextColor(mContext.getResources().getColor(R.color.textColor22));
                packageDateTagIv.setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.ic_package_date1));
                packageDateValueTv.setTextColor(mContext.getResources().getColor(R.color.textColor21));
                packageItemNameTv.setTextColor(mContext.getResources().getColor(R.color.textColor23));
                packageItemTipTv.setTextColor(mContext.getResources().getColor(R.color.textColor23));
                packageItemSubmitTv.setBackground(mContext.getResources().getDrawable(R.mipmap.ic_package_submit_bg1));
                int inquiryDurationTime = packageItemInfoBean.getInquiryDuration();
                //体验卡使用时间
                packageDateTv.setText(String.format("%1d%2s", inquiryDurationTime, mContext.getString(R.string.min_dec_str)));
                //体验卡名字
                packageTypeTv.setText(mContext.getString(R.string.card_expire_str));
                //体验卡剩余时间
                packageDateValueTv.setText(setRemainTime(packageItemInfoBean.getActivityDueDate()));
                if (mData.getDiscountPrice() == 0) {
                    packageItemSubmitTv.setText(mContext.getString(R.string.experience_submit_str));
                } else {
                    packageItemSubmitTv.setText(mContext.getString(R.string.buy_dec_str));
                }


                break;
            case Common.PackageType.CARD_SECONDARY://次卡
                packageRootLayout.setBackground(mContext.getResources().getDrawable(R.mipmap.ic_package_bg3));
                packageTypeIv.setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.ic_package_type3));
                packageDateTv.setBackground(mContext.getResources().getDrawable(R.drawable.package_item_date_bg3));
                packageDateTv.setTextColor(mContext.getResources().getColor(R.color.textColor26));
                priceTagTv.setTextColor(mContext.getResources().getColor(R.color.textColor26));
                priceValueTv.setTextColor(mContext.getResources().getColor(R.color.textColor26));
                originalPriceTv.setTextColor(mContext.getResources().getColor(R.color.textColor28));
                packageDateTagIv.setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.ic_package_date3));
                packageDateValueTv.setTextColor(mContext.getResources().getColor(R.color.textColor26));
                packageItemNameTv.setTextColor(mContext.getResources().getColor(R.color.textColor26));
                packageItemTipTv.setTextColor(mContext.getResources().getColor(R.color.textColor30));
                packageItemSubmitTv.setBackground(mContext.getResources().getDrawable(R.mipmap.ic_package_submit_bg3));
                packageDateTv.setText(String.format("%1d%2s", packageItemInfoBean.getInquiryNum(), mContext.getString(R.string.num_dec_str)));
                packageTypeTv.setText(mContext.getString(R.string.card_second_str));
                int inquiryNum = packageItemInfoBean.getInquiryNum();
                //次卡使用时间
                packageDateTv.setText(String.format("%1d%2s", inquiryNum, mContext.getString(R.string.num_dec_str)));
                //次卡名字
                packageTypeTv.setText(mContext.getString(R.string.card_second_str));
                //次卡剩余时间
                packageDateValueTv.setText(setRemainTime(packageItemInfoBean.getActivityDueDate()));
          
                if (mData.getDiscountPrice() == 0) {
                    packageItemSubmitTv.setText(mContext.getString(R.string.experience_submit_str));
                } else {
                    packageItemSubmitTv.setText(mContext.getString(R.string.buy_dec_str));
                }


                break;
        }
        setPackageName();
        priceValueTv.setText(String.format("%.2f", packageItemInfoBean.getDiscountPrice()));
        originalPriceTv.setText(String.format("%s%.2f", mContext.getString(R.string.money_tag_str), packageItemInfoBean.getPrice()));
        originalPriceTv.getPaint().setFlags(Paint.STRIKE_THRU_TEXT_FLAG | Paint.ANTI_ALIAS_FLAG); //中划线

        packageItemSubmitTv.setOnClickListener(view -> {
            //判断用户是否登录
            String token = App.getSPUtils().getString(Common.SPApi.TOKEN);
            if (TextUtils.isEmpty(token)) {
                LoginActivity.show((Activity) mContext);
            }else{
                String Territory = App.getSPUtils().getString(Common.SPApi.HONG_KONG);
                if(null != Territory && Territory.equals("香港")){
                    App.showToast(R.string.label_hong_kong_not_purchase);
                }else {
                    if(mData.getDiscountPrice()==0){
                        //若价格为0，免费领取
                        if(null!=mGetPackage) {
                            mGetPackage.onClickGetCard(mData.getId());
                        }
                    }else {
                        //跳转购买套餐卡页面
                        PackageBuyConfirmActivity.show((activity), mData, null);
                    }
                }
            }

        });


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
            return String.format(mContext.getString(R.string.label_remaining_time), String.valueOf(betweenDays), String.valueOf(hour), String.valueOf(min));
        }
        return null;
    }


    //设置套餐卡名字
    private void setPackageName() {
        String packageName = null;
        String packageTip = null;
        List<SetMealListResModel.DataBean.PackageItemInfoBean.PackageInfosBean> packageInfo = mData.getPackageInfos();
        for (SetMealListResModel.DataBean.PackageItemInfoBean.PackageInfosBean packageInfosBean : packageInfo) {
            if (language.equals(packageInfosBean.getLanguage())) {
                packageName = packageInfosBean.getName();
                packageTip = packageInfosBean.getDescription();

            }
            packageItemNameTv.setText(packageName);
            packageItemTipTv.setText(packageTip);
        }


    }

    @OnClick()
    public void onViewClicked() {
        //跳转套餐详情
        activity.startActivity(new Intent(activity, PackageCardInfoActivity.class).putExtra(Common.CommonStr.PACKAGE_CARD_ID, mData.getId()));
    }


    public void setGetPackage(GetPackage mGetPackage) {
        this.mGetPackage = mGetPackage;
    }


    public interface GetPackage{
         void onClickGetCard(String id);
    }

}
