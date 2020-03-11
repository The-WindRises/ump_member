package it.swiftelink.com.vcs_member.holders;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.kongzue.dialog.v2.CustomDialog;

import butterknife.BindView;
import butterknife.OnClick;
import it.swiftelink.com.common.widget.recycler.BaseRecyclerAdapter;
import it.swiftelink.com.factory.model.order.CouponListResModel;
import it.swiftelink.com.vcs_member.R;

public class SelectCouponViewHolder extends BaseRecyclerAdapter.ViewHolder<CouponListResModel.DataBean.CouponListBean> {
    private Context context;

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

    public void setUseCoupon(UseCoupon useCoupon) {
        this.useCoupon = useCoupon;
    }

    private UseCoupon useCoupon;

    public SelectCouponViewHolder(View itemView, Context context) {
        super(itemView);
        this.context=context;
        LayoutInflater.from(context).inflate(R.layout.item_rcv_coupon, null);
    }


    @SuppressLint("DefaultLocale")
    @Override
    protected void onBind(CouponListResModel.DataBean.CouponListBean couponListBean) {
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
                        contentTv.setText(mData.getRemark());
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

        useCoupon.onClickCoupon(mData.getCouponId(),mData.getFaceValue());
    }


    public interface UseCoupon{
        void onClickCoupon(String id,int price);
    }
}
