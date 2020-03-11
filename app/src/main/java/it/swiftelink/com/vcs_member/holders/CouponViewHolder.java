package it.swiftelink.com.vcs_member.holders;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.BitmapFactory;
import androidx.fragment.app.FragmentActivity;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.kongzue.dialog.v2.CustomDialog;

import butterknife.BindView;
import butterknife.OnClick;
import it.swiftelink.com.common.app.Common;
import it.swiftelink.com.common.widget.recycler.BaseRecyclerAdapter;
import it.swiftelink.com.factory.model.order.CouponListResModel;
import it.swiftelink.com.vcs_member.R;
import it.swiftelink.com.vcs_member.ui.activity.my.SelectPackageCardActivity;


/**
 * @Description:  优惠卷Item界面
 * @Author: klk
 * @CreateDate: 2019/12/23 20:12
 */
public class CouponViewHolder extends BaseRecyclerAdapter.ViewHolder<CouponListResModel.DataBean.CouponListBean> {
    private Context context;
    private String type;

    @BindView(R.id.coupon_item_rl)
    RelativeLayout couponItemRl;

    @BindView(R.id.money_tag_tv)
    TextView moneyTagTv;

    @BindView(R.id.coupon_status_iv)
    ImageView couponStatusIv;

    @BindView(R.id.coupon_icon_iv)
    ImageView couponIconIv;

    @BindView(R.id.money_value_tv)
    TextView moneyValueTv;

    @BindView(R.id.coupon_remark_str)
    TextView couponRemarkTv;

    @BindView(R.id.coupon_name_tv)
    TextView couponNameTv;

    @BindView(R.id.expiration_dec_tv)
    TextView expirationDecTv;

    @BindView(R.id.expiration_date_tv)
    TextView expirationDateTv;

    @BindView(R.id.coupon_tip_tv)
    TextView couponTipTv;

    private FragmentActivity activity;

    public CouponViewHolder(View itemView, Context context,String type,FragmentActivity  activity) {
        super(itemView);
        this.context=context;
        this.type=type;
        this.activity=activity;
        LayoutInflater.from(context).inflate(R.layout.item_rcv_coupon, null);
    }

    @SuppressLint("DefaultLocale")
    @Override
    protected void onBind(CouponListResModel.DataBean.CouponListBean couponListBean) {
        if(type.equals(Common.CommonStr.COUPON_TYPE1)){
            //未使用
            if(mPosition%3==0) {
                couponItemRl.setBackground(context.getResources().getDrawable(R.mipmap.ic_coupon_item_bg1));
                moneyTagTv.setTextColor(context.getResources().getColor(R.color.textColor15));
                moneyValueTv.setTextColor(context.getResources().getColor(R.color.textColor15));
            }else if(mPosition%3==1){
                couponItemRl.setBackground(context.getResources().getDrawable(R.mipmap.ic_coupon_item_bg2));
                moneyTagTv.setTextColor(context.getResources().getColor(R.color.textColor16));
                moneyValueTv.setTextColor(context.getResources().getColor(R.color.textColor16));
            }else if(mPosition%3==2){
                couponItemRl.setBackground(context.getResources().getDrawable(R.mipmap.ic_coupon_item_bg3));
                moneyTagTv.setTextColor(context.getResources().getColor(R.color.textColor17));
                moneyValueTv.setTextColor(context.getResources().getColor(R.color.textColor17));
            }
            couponRemarkTv.setTextColor(context.getResources().getColor(R.color.textColor6));
            couponStatusIv.setVisibility(View.GONE);
            couponNameTv.setTextColor(context.getResources().getColor(R.color.textColor6));
            expirationDecTv.setTextColor(context.getResources().getColor(R.color.textColor7));
            expirationDateTv.setTextColor(context.getResources().getColor(R.color.textColor7));
            couponTipTv.setTextColor(context.getResources().getColor(R.color.textColor19));
            couponIconIv.setImageBitmap(BitmapFactory.decodeResource(context.getResources(),R.mipmap.ic_coupon_item_img));

        }else if(type.equals(Common.CommonStr.COUPON_TYPE2)){
            //已使用
            couponItemRl.setBackground(context.getResources().getDrawable(R.mipmap.ic_coupon_item_bg4));
            couponStatusIv.setImageBitmap(BitmapFactory.decodeResource(context.getResources(),R.mipmap.ic_coupon_used));
            couponStatusIv.setVisibility(View.VISIBLE);
            couponIconIv.setImageBitmap(BitmapFactory.decodeResource(context.getResources(),R.mipmap.ic_coupon_item_img2));
            moneyTagTv.setTextColor(context.getResources().getColor(R.color.textColor18));
            moneyValueTv.setTextColor(context.getResources().getColor(R.color.textColor18));
            couponRemarkTv.setTextColor(context.getResources().getColor(R.color.textColor18));
            couponNameTv.setTextColor(context.getResources().getColor(R.color.textColor18));
            expirationDecTv.setTextColor(context.getResources().getColor(R.color.textColor18));
            expirationDateTv.setTextColor(context.getResources().getColor(R.color.textColor18));
            couponTipTv.setTextColor(context.getResources().getColor(R.color.textColor18));


        }else if(type.equals(Common.CommonStr.COUPON_TYPE3)){
            //已过期
            couponItemRl.setBackground(context.getResources().getDrawable(R.mipmap.ic_coupon_item_bg4));
            couponStatusIv.setVisibility(View.VISIBLE);
            couponIconIv.setImageBitmap(BitmapFactory.decodeResource(context.getResources(),R.mipmap.ic_coupon_item_img2));
            moneyTagTv.setTextColor(context.getResources().getColor(R.color.textColor18));
            moneyValueTv.setTextColor(context.getResources().getColor(R.color.textColor18));
            couponRemarkTv.setTextColor(context.getResources().getColor(R.color.textColor18));
            couponNameTv.setTextColor(context.getResources().getColor(R.color.textColor18));
            expirationDecTv.setTextColor(context.getResources().getColor(R.color.textColor18));
            expirationDateTv.setTextColor(context.getResources().getColor(R.color.textColor18));
            couponTipTv.setTextColor(context.getResources().getColor(R.color.textColor18));
        }

        //优惠卷价格
        moneyValueTv.setText(String.format("%d",mData.getFaceValue()));
        //优惠卷提示
        couponRemarkTv.setText(String.format(context.getString(R.string.coupon_min_price),mData.getOrderAmount()));
        //优惠卷标题
        couponNameTv.setText(mData.getCouponName());
        //优惠券有效期
        expirationDateTv.setText(mData.getExpireDate());

        couponTipTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                CustomDialog.build(context, R.layout.dialog_coupon_tip, new CustomDialog.BindView() {
                    @Override
                    public void onBind(final CustomDialog dialog, View rootView) {
                        TextView contentTv=(TextView) rootView.findViewById(R.id.tip_content_tv);
                        contentTv.setText(mData.getRemark() == null ? "" : mData.getRemark());
                        contentTv.post(() -> {
                            if (contentTv.getLineCount() > 1){
                                //多余一行左对齐
                                contentTv.setGravity(Gravity.LEFT);
                            }
                        });

                        rootView.findViewById(R.id.confirm_tv).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.doDismiss();
                            }
                        });
                    }
                }).setCanCancel(true).showDialog();
            }
        });
    }

    @OnClick()
    public void onViewClicked() {
        if(type.equals(Common.CommonStr.COUPON_TYPE1)){

            SelectPackageCardActivity.show(activity);
        }
    }
}
