package it.swiftelink.com.vcs_member.holders;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.BitmapFactory;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.common.widget.recycler.BaseRecyclerAdapter;
import it.swiftelink.com.factory.model.card.CardListResModel;
import it.swiftelink.com.vcs_member.R;
import it.swiftelink.com.vcs_member.utils.DateTimeUtils;

/**
 * @Description: 我的套餐卡
 * @Author: klk
 * @CreateDate: 2019/12/31 10:07
 */
public class MyCardItemViewHolder extends BaseRecyclerAdapter.ViewHolder<CardListResModel.DataBean.CardItemBean> {
    @BindView(R.id.card_item_root_ll)
    ConstraintLayout cardRootLayout;
    @BindView(R.id.card_type_iv)
    ImageView cardTypeIv;
    @BindView(R.id.card_type_tv)
    TextView cardTypeTv;
    @BindView(R.id.card_date_tv)
    TextView cardDateTv;
    @BindView(R.id.card_date_tag_iv)
    ImageView cardDateTagIv;
    @BindView(R.id.card_item_name_tv)
    TextView cardItemNameTv;
    @BindView(R.id.card_item_tip_tv)
    TextView cardItemTipTv;
    @BindView(R.id.card_item_submit)
    TextView cardItemSubmitTv;
    @BindView(R.id.card_expiration_data_tv)
    TextView cardExpirationDataTv;
    @BindView(R.id.card_diagnosis_iv)
    ImageView cardDiagnosisIv;
    @BindView(R.id.card_diagnosis_tv)
    TextView cardDiagnosisTv;
    @BindView(R.id.remaining_times_ll)
    LinearLayout cardRemainingTimesLl;
    @BindView(R.id.remaining_times_iv)
    ImageView cardRemainingTimesIv;
    @BindView(R.id.remaining_times_tv)
    TextView cardRemainingTimesTv;


    private Context mContext;
    private String language;

    public MyCardItemViewHolder(View itemView, Context context,String language) {
        super(itemView);
        this.language=language;
        this.mContext = context;
    }



    @Override
    protected void onBind(CardListResModel.DataBean.CardItemBean cardItemBean) {
        //套餐卡类型
        int type = cardItemBean.getType();
        if (mData.getValidFlag() == 0) {
            //失效套餐卡
            cardRootLayout.setBackground(mContext.getResources().getDrawable(R.mipmap.ic_package_bg5));
            cardTypeIv.setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.ic_package_type5));
            cardDateTv.setBackground(mContext.getResources().getDrawable(R.drawable.package_item_data_bg5));
            cardDateTv.setTextColor(mContext.getResources().getColor(R.color.textColor32));
            cardDateTagIv.setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.ic_package_date5));
            cardItemNameTv.setTextColor(mContext.getResources().getColor(R.color.textColor31));
            cardItemTipTv.setTextColor(mContext.getResources().getColor(R.color.textColor31));
            cardItemSubmitTv.setBackground(mContext.getResources().getDrawable(R.mipmap.ic_package_submit_bg5));
            cardExpirationDataTv.setTextColor(mContext.getResources().getColor(R.color.textColor32));
            cardDiagnosisIv.setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.ic_package_date5));
            cardDiagnosisTv.setTextColor(mContext.getResources().getColor(R.color.textColor32));
            if (type == Common.PackageType.CARD_SECONDARY) {
                cardRemainingTimesLl.setVisibility(View.VISIBLE);
                cardRemainingTimesIv.setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.ic_package_date5));
                cardRemainingTimesTv.setTextColor(mContext.getResources().getColor(R.color.textColor32));
            } else {
                cardRemainingTimesLl.setVisibility(View.GONE);
            }
            cardItemSubmitTv.setText(mContext.getString(R.string.has_failed_str));
        } else {
            switch (type) {
                case Common.PackageType.CARD_NORMAL: //套餐卡
                    cardRootLayout.setBackground(mContext.getResources().getDrawable(R.mipmap.ic_package_bg2));
                    cardTypeIv.setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.ic_package_type2));
                    cardDateTv.setBackground(mContext.getResources().getDrawable(R.drawable.package_item_date_bg2));
                    cardDateTv.setTextColor(mContext.getResources().getColor(R.color.textColor21));
                    cardDateTagIv.setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.ic_package_date2));
                    cardItemNameTv.setTextColor(mContext.getResources().getColor(R.color.textColor25));
                    cardItemTipTv.setTextColor(mContext.getResources().getColor(R.color.textColor29));
                    cardItemSubmitTv.setBackground(mContext.getResources().getDrawable(R.mipmap.ic_package_submit_bg2));
                    cardExpirationDataTv.setTextColor(mContext.getResources().getColor(R.color.textColor25));
                    cardDiagnosisIv.setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.ic_package_date2));
                    cardDiagnosisTv.setTextColor(mContext.getResources().getColor(R.color.textColor25));
                    cardRemainingTimesLl.setVisibility(View.GONE);

                    break;
                case Common.PackageType.CARD_EXPERIENCE: //体验卡
                    cardRootLayout.setBackground(mContext.getResources().getDrawable(R.mipmap.ic_package_bg2));
                    cardTypeIv.setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.ic_package_type1));
                    cardDateTv.setBackground(mContext.getResources().getDrawable(R.drawable.package_item_date_bg1));
                    cardDateTv.setTextColor(mContext.getResources().getColor(R.color.textColor25));
                    cardDateTagIv.setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.ic_package_date1));
                    cardItemNameTv.setTextColor(mContext.getResources().getColor(R.color.textColor23));
                    cardItemTipTv.setTextColor(mContext.getResources().getColor(R.color.textColor23));
                    cardItemSubmitTv.setBackground(mContext.getResources().getDrawable(R.mipmap.ic_package_submit_bg1));
                    cardExpirationDataTv.setTextColor(mContext.getResources().getColor(R.color.textColor21));
                    cardDiagnosisIv.setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.ic_package_date1));
                    cardDiagnosisTv.setTextColor(mContext.getResources().getColor(R.color.textColor21));
                    cardRemainingTimesLl.setVisibility(View.GONE);
                    break;
                case Common.PackageType.CARD_SECONDARY: //次卡
                    cardRootLayout.setBackground(mContext.getResources().getDrawable(R.mipmap.ic_package_bg3));
                    cardTypeIv.setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.ic_package_type3));
                    cardDateTv.setBackground(mContext.getResources().getDrawable(R.drawable.package_item_date_bg3));
                    cardDateTv.setTextColor(mContext.getResources().getColor(R.color.textColor26));
                    cardDateTagIv.setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.ic_package_date3));
                    cardItemNameTv.setTextColor(mContext.getResources().getColor(R.color.textColor26));
                    cardItemTipTv.setTextColor(mContext.getResources().getColor(R.color.textColor30));
                    cardItemSubmitTv.setBackground(mContext.getResources().getDrawable(R.mipmap.ic_package_submit_bg3));
                    cardExpirationDataTv.setTextColor(mContext.getResources().getColor(R.color.textColor26));
                    cardDiagnosisIv.setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.ic_package_date3));
                    cardDiagnosisTv.setTextColor(mContext.getResources().getColor(R.color.textColor26));
                    cardRemainingTimesIv.setImageBitmap(BitmapFactory.decodeResource(mContext.getResources(), R.mipmap.ic_package_date3));
                    cardRemainingTimesLl.setVisibility(View.VISIBLE);
                    cardRemainingTimesTv.setTextColor(mContext.getResources().getColor(R.color.textColor26));


                    break;


            }
            cardItemSubmitTv.setText(mContext.getString(R.string.has_used_str));
        }
        setDiagnosisTime();
        setCardItemInfo(type);
        setExpirationTime();


    }


    public boolean isContinuity(String[] arr) {
        if (arr.length < 3) {
            return false;
        }

        boolean b = false;
        for (int i = 1; i < arr.length - 1; i++) {
            if ((Integer.parseInt(arr[i]) * 2) != Integer.parseInt(arr[i - 1]) + Integer.parseInt(arr[i + 1])) {
                b = false;
                break;
            }
            if (Math.abs(Integer.parseInt(arr[i + 1]) - Integer.parseInt(arr[i])) != 1) {
                b = false;
                break;
            }
            if ((Integer.parseInt(arr[i + 1]) - Integer.parseInt(arr[i])) != (Integer.parseInt(arr[i]) - Integer.parseInt(arr[i - 1]))) {
                b = false;
                break;
            }
            b = true;
            continue;
        }
        return b;
    }

    private String replaceStr(String day) {
        String temp = "";
        switch (Integer.parseInt(day)) {
            case 1:
                temp = mContext.getString(R.string.mon_dec_str);
                break;
            case 2:
                temp = mContext.getString(R.string.tues_dec_str);
                break;
            case 3:
                temp = mContext.getString(R.string.wed_dec_str);
                break;
            case 4:
                temp = mContext.getString(R.string.thur_dec_str);
                break;
            case 5:
                temp = mContext.getString(R.string.fri_dec_str);
                break;
            case 6:
                temp = mContext.getString(R.string.sat_dec_str);
                break;
            case 7:
                temp = mContext.getString(R.string.sun_dec_str);
                break;


        }
        return temp;
    }

    /**
     * 设置套餐卡信息
     * @param type 套餐卡类型
     */
    @SuppressLint({"DefaultLocale", "SetTextI18n"})
    private void setCardItemInfo(int type) {
        switch (type) {
            case Common.PackageType.CARD_NORMAL:
                cardTypeTv.setText(mContext.getString(R.string.card_package_str));
                int validateDays = mData.getValidateDays();
                cardDateTv.setText(String.format("%1d%2s", validateDays, mContext.getString(R.string.day_dec_str)));
//                cardItemTipTv.setText(String.format("%1d%2s", validateDays, mContext.getString(R.string.package_tip_str)));
                break;
            case Common.PackageType.CARD_EXPERIENCE:
                cardTypeTv.setText(mContext.getString(R.string.card_expire_str));
                int inquiryDurationTime = mData.getInquiryDuration();
                cardDateTv.setText(String.format("%1d%2s", inquiryDurationTime, mContext.getString(R.string.min_dec_str)));
//                if (inquiryDurationTime >= 60) {
//                    cardItemTipTv.setText(String.format("%1d%2s", inquiryDurationTime / 60, mContext.getString(R.string.experience_tip_str)));
//                } else {
//                    cardItemTipTv.setText(String.format("%1d%2s", inquiryDurationTime, mContext.getString(R.string.experience_tip_time_str)));
//                }

                break;
            case Common.PackageType.CARD_SECONDARY:
                cardTypeTv.setText(mContext.getString(R.string.card_second_str));
                int inquiryNum = mData.getInquiryNum();
                cardDateTv.setText(String.format("%1d%2s", inquiryNum, mContext.getString(R.string.num_dec_str)));
//                if (inquiryNum >0) {
//                    cardItemTipTv.setText(String.format("%1d%2s", inquiryNum, mContext.getString(R.string.second_tip_str)));
//                }

                cardRemainingTimesTv.setText(mContext.getString(R.string.label_remaining)+String.format("%1d%2s", mData.getResidueNum(), mContext.getString(R.string.num_dec_str)));
                break;
        }
        setPackageName();

    }

    /**
     * 设置套餐卡可问诊时间
     */
    @SuppressLint("SetTextI18n")
    private void setDiagnosisTime(){
        String str = mData.getWeek();
        String[] weeks = str.split(",");
        String diagnosisDecValue = mContext.getString(R.string.diagnosis_dec_str);
        String diagnosisValue ="";
        if (isContinuity(weeks)) {
            diagnosisValue = replaceStr(weeks[0]) + "~" + replaceStr(weeks[weeks.length - 1]);
        } else {
            StringBuffer tempStr = new StringBuffer();
            for (String day : weeks) {
                tempStr.append(replaceStr(day));
                tempStr.append(",");
                diagnosisValue = tempStr.toString();
            }
        }
        cardDiagnosisTv.setText(diagnosisDecValue+diagnosisValue+" "+mData.getInquiryStartTime()+"~"+mData.getInquiryEndTime());

    }

    //设置套餐卡名字
    private void setPackageName() {
        String packageName = null;
        String packageTip=null;
        List<CardListResModel.DataBean.CardItemBean.PackageSnapshotInfosBean> packageInfo = mData.getPackageSnapshotInfos();
        for (CardListResModel.DataBean.CardItemBean.PackageSnapshotInfosBean packageInfosBean : packageInfo) {
            if (language.equals(packageInfosBean.getLanguage())) {
                packageName = packageInfosBean.getName();
                packageTip=packageInfosBean.getDescription();
            }
        }
        cardItemNameTv.setText(packageName);
        cardItemTipTv.setText(packageTip);


    }

    /**
     *  设置有效期时间
     */
    @SuppressLint("SetTextI18n")
    private void setExpirationTime(){
       String expirationTime= DateTimeUtils.timeStamp2Date(mData.getExpireDate());
       cardExpirationDataTv.setText(mContext.getString(R.string.before_use_tag_dec_str)+" "+expirationTime+mContext.getString(R.string.use_before_dec_str));
    }


}
